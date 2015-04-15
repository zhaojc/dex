package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.InscriptionVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteApplicableTableValeurCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class AutoexclusionDossierGenerateurRapport_CDX_0220 extends CritereGenererRapport {
 
	private boolean tousCasinoEtLudoplex = false;
	
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUTOEXCLUSION);
	}

	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent � ceux du rapport.
	private List construireListeDataSource(CardexAuthenticationSubject subject, Dossier dossier, Map mapRapportDossier)
	 			throws BusinessException{
		List list = new ArrayList();
    	ListeCache cache = ListeCache.getInstance();

    	//On ajoute les informations du dossier
        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
        mapRapportDossier.put("dateDebut", StringUtils.substring(dossier.getDateDebut().toString(),0,10));
        mapRapportDossier.put("dateFin", StringUtils.substring(dossier.getDateFin().toString(),0,10));
        mapRapportDossier.put("intervenantDescription", dossier.getIntervenantDescription());

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va d'abord chercher le sujet reli�
        Collection liensSujets;
        Iterator it;
        
        liensSujets = delegate.findLiensSujet(subject, dossier);
        it = liensSujets.iterator();
        
        if(it.hasNext()) {
            Sujet linkSujet = (Sujet) it.next();
            //On passe la cl� et le site du sujet pour le sous-rapport
			mapRapportDossier.put("sujetCle", BigDecimal.valueOf(linkSujet.getCle()));
			mapRapportDossier.put("sujetSite", BigDecimal.valueOf(linkSujet.getSite()));
			//On va ensuite chercher le commentaire dans l'adresse pour les contrats bonifi�s (champ contactePar)
			SujetBusinessDelegate delegateSujet = new SujetBusinessDelegate();
		    Collection liensAdresse = delegateSujet.findLiensAdresse(subject, linkSujet);
		    it = liensAdresse.iterator();
		    
	        while(it.hasNext()) {
	        	Adresse linkAdresse = (Adresse) it.next();
	        	long typeAdresse = linkAdresse.getStatut();
	        	
	        	if(String.valueOf(typeAdresse).equals(GlobalConstants.Adresse.RESIDENCE_PRINCIPALE)){
	        		mapRapportDossier.put("commentaire", linkAdresse.getCommentaire());
	        	}
	        }
	        // Recherche de la photo � afficher
	        Collection liensPhoto = delegateSujet.findLiensPhoto(subject, linkSujet);
	        it = liensPhoto.iterator();
	        PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
	        while (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                if(linkPhoto.isSelectionner()){
                	FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, linkPhoto.getLienElement(), linkPhoto.getLienSiteElement(), false);
                	if(fichierMultimediaVO.getImageByte() != null){
                		InputStream photo = new ByteArrayInputStream(fichierMultimediaVO.getImageByte());
                		mapRapportDossier.put("photo", photo);
                	}
                }
	        }//while
        }
        //Recherche des jeux
        Jeux liensJeux = delegate.findLiensJeux(subject, dossier);
        it = liensJeux.getJeuxChoisis().iterator();
        String listeJeux = "";
        
        while(it.hasNext()) {
        	String jeu = (String)it.next(); 
        	String jeuDescription = cache.obtenirLabel(subject, jeu, new TableValeurCleSQLListeCache(
        			locale.getLanguage(), 
        			GlobalConstants.TableValeur.JEUX, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
        	listeJeux = listeJeux + jeuDescription + ", ";
        }
        mapRapportDossier.put("listeJeux", listeJeux);
        //Recherche des sites
        Collection liensInscription = delegate.findLiensInscription(subject, dossier);
        it = liensInscription.iterator();
        
        if(it.hasNext()) {
        	InscriptionVO inscription = (InscriptionVO)it.next();
        	
            mapRapportDossier.put("stringSitesChoisis", obtenirStringSitesChoisis(subject, inscription));
            mapRapportDossier.put("tousCasinoEtLudoplex", String.valueOf(tousCasinoEtLudoplex));
            mapRapportDossier.put("duree", inscription.getDuree());
            mapRapportDossier.put("aideInitialeDemandee", inscription.getAideInitiale());
            mapRapportDossier.put("aideInitiale", StringUtils.convertirBooleanLangue(inscription.getAideInitiale(), locale));
            mapRapportDossier.put("aideImmediate", StringUtils.convertirBooleanLangue(inscription.getAideImmediate(), locale));
        }

        list.add(mapRapportDossier);
        		
		return list;
	}
	
	public String obtenirStringSitesChoisis(CardexAuthenticationSubject subject, Inscription inscription) throws BusinessResourceException{
		String stringSitesChoisis = "";
		ListeCache listeCache = ListeCache.getInstance();
		Collection listeSiteApplicable = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, GlobalConstants.Entite.MAISON_JEUX, GlobalConstants.ActionSecurite.MODIFICATION));
		int nbSiteApplicableMax = listeSiteApplicable.size();
		//On soustrait l'entr�e vide qui est ajout�e par d�faut dans la liste
		if (inscription.getSitesChoisis().size() >= nbSiteApplicableMax-1)
			tousCasinoEtLudoplex = true;
		else{
			Iterator iter = listeSiteApplicable.iterator();

			while (iter.hasNext()) {
				LabelValueBean label = (LabelValueBean) iter.next();

				if (inscription.getSitesChoisis().contains( label.getValue() )){

					if (stringSitesChoisis.length() > 0)
						stringSitesChoisis += ", ";
					stringSitesChoisis += label.getLabel();
				}
			}
		}

		listeSiteApplicable = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, GlobalConstants.Entite.LOTO_QUEBEC, GlobalConstants.ActionSecurite.MODIFICATION));
		nbSiteApplicableMax = listeSiteApplicable.size();
		Iterator iter = listeSiteApplicable.iterator();

		while (iter.hasNext()) {
			LabelValueBean label2 = (LabelValueBean) iter.next();
			
			if (inscription.getSitesChoisis().contains( label2.getValue() )){

				if (stringSitesChoisis.length() > 0)
					stringSitesChoisis += ", ";
				stringSitesChoisis += label2.getLabel();
			}
		}
		return stringSitesChoisis;
	}
	
	private Map construireListeLibelles(Dossier dossier)
		throws BusinessException{
		List list = new ArrayList();
		//On remplit d'abord les libell�s
		Map mapLibelles = new HashMap(); 
		
		mapLibelles.put("titre", bundle.getString("titreAE"));
		mapLibelles.put("confidentiel", bundle.getString("confidentiel"));
		
		if((dossier.getSiteOrigine() == Long.valueOf(GlobalConstants.SiteMaisonJeux.LUDOPLEX_QUEBEC)) ||
		(dossier.getSiteOrigine() == Long.valueOf(GlobalConstants.SiteMaisonJeux.LUDOPLEX_TROIS_RIVIERE))){
			mapLibelles.put("societe", bundle.getString("societeSEJ"));
			mapLibelles.put("enonce", bundle.getString("enonceSEJ"));
		}else{
			mapLibelles.put("societe", bundle.getString("societeSCQ"));
			mapLibelles.put("enonce", bundle.getString("enonceSCQ"));
		}
		mapLibelles.put("libelleAideImmediate", bundle.getString("libelle.aide.immediate"));
		mapLibelles.put("demande", bundle.getString("demande"));
		mapLibelles.put("libelleDemandeDebut", bundle.getString("libelle.demande.debut")); 
		mapLibelles.put("libelleDemandeFin", bundle.getString("libelle.demande.fin"));
		mapLibelles.put("libellePeriode", bundle.getString("libelle.periode"));
		mapLibelles.put("au", bundle.getString("au_t"));
		mapLibelles.put("libelleAideInitiale", bundle.getString("libelle.aide.initiale"));
		mapLibelles.put("libelleJeux", bundle.getString("tabpage_jeu") + bundle.getString("2.points"));
		mapLibelles.put("libelleCondition1", bundle.getString("libelle.condition1"));
		mapLibelles.put("libelleCondition2", bundle.getString("libelle.condition2"));
		mapLibelles.put("sites", bundle.getString("libelle.sites"));
		mapLibelles.put("tousSites", bundle.getString("libelle.tous.maisons"));
		mapLibelles.put("remarque", bundle.getString("remarque"));
		mapLibelles.put("signatures", bundle.getString("signatures"));
		mapLibelles.put("contactePar", bundle.getString("contacte.par"));
	
		return mapLibelles;
	}
	
	public CritereRapportVO construireNouveauRapportVO() {
		return new EntiteRapportVO();
	}
	
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.DOSSIER, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), "484");
	}
	
	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, vo, connection);
		parameters.put("sujet_photo", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_AUTOEXCLUSION_SUJET_PHOTO));
		parameters.put("sous_rapport_sujet_autoexclusion", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_AUTOEXCLUSION_SUJET_FRANCAIS));
		parameters.put("sous_rapport_sujet_autoexclusion_anglais", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_AUTOEXCLUSION_SUJET_ANGALAIS));
		parameters.put("REPORT_CONNECTION", connection);
		parameters.put("langue", locale.toString());
		
		return parameters;
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		List list = new ArrayList();
		EntiteRapportVO entiteRapportVO = (EntiteRapportVO) rapportVO;
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
       	Dossier dossierVO = new DossierVO();
       	dossierVO.setCle(entiteRapportVO.getCle());
       	dossierVO.setSite(entiteRapportVO.getSite());
		Dossier dossier = delegate.find(subject, dossierVO);
		
		//On commence par construire le contenu des libell�s, selon la langue demand�e pour le contrat.
		Map mapRapportDossier = construireListeLibelles(dossier);
		//On ajout ensuite les champs qui seront imprim�s sur le contrat
		list.addAll(construireListeDataSource(subject, dossier, mapRapportDossier));
	
		return new JRMapCollectionDataSource(list);
	}



}
