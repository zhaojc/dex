package com.lotoquebec.cardex.presentation.rapport.dossier;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.InscriptionForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;
/**
 * @Deprecated sera retirer dans la version 5.5 
 */
public class DossierAutoexclusion extends DossierRapport {
 
	protected InputStream obtenirGabarit() {
		//return RapportsConfiguration.class.getResourceAsStream("rapports/" + RapportsConfiguration.AUTOEXCLUSION);
		return getClass().getClassLoader().getResourceAsStream("rapports/" + RapportsConfiguration.AUTOEXCLUSION);
	}

	protected Dossier produireRapport(CardexAuthenticationSubject subject,
			Dossier dossier) throws BusinessException {
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va rechercher le dossier au complet au cas où plus d'un dossier serait affiché dans la session Cardex. Dans ce cas,
		//l'impression se fait avec le dernier dossier ouvert et non avec le dossier affiché.
		return delegate.find(subject, dossier);
	}

	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, Dossier dossier, Locale langueImpression, MessageResources mResources) throws BusinessException {
		List list = new ArrayList();
		Map mapRapportDossier = new HashMap();
		//On commence par construire le contenu des libellés, selon la langue demandée pour le contrat.
		mapRapportDossier = construireListeLibelles(dossier, langueImpression, mResources);
		//On ajout ensuite les champs qui seront imprimés sur le contrat
		list.addAll(construireListeDataSource(subject, dossier, langueImpression, mapRapportDossier));

		return new JRMapCollectionDataSource(list);
	}
	
	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent à ceux du rapport.
	private List construireListeDataSource(CardexAuthenticationSubject subject, Dossier dossier, Locale langueImpression, Map mapRapportDossier)
	 			throws BusinessException{
		List list = new ArrayList();
    	ListeCache cache = ListeCache.getInstance();

    	//On ajoute les informations du dossier
        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
        mapRapportDossier.put("dateDebut", StringUtils.substring(dossier.getDateDebut().toString(),0,10));
        mapRapportDossier.put("dateFin", StringUtils.substring(dossier.getDateFin().toString(),0,10));
        mapRapportDossier.put("intervenantDescription", dossier.getIntervenantDescription());

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va d'abord chercher le sujet relié
        Collection liensSujets;
        Iterator it;
        try{
	        liensSujets = delegate.findLiensSujet(subject, dossier);
	        it = liensSujets.iterator();
	        if(it.hasNext()) {
	            Sujet linkSujet = (Sujet) it.next();
	            //On passe la clé et le site du sujet pour le sous-rapport
				mapRapportDossier.put("sujetCle", BigDecimal.valueOf(linkSujet.getCle()));
				mapRapportDossier.put("sujetSite", BigDecimal.valueOf(linkSujet.getSite()));
				//On va ensuite chercher le commentaire dans l'adresse pour les contrats bonifiés (champ contactePar)
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
			}
	        //Recherche des jeux
	        Jeux liensJeux = delegate.findLiensJeux(subject, dossier);
	        it = liensJeux.getJeuxChoisis().iterator();
	        String listeJeux = "";
	        while(it.hasNext()) {
	        	String jeu = (String)it.next(); 
	        	String jeuDescription = cache.obtenirLabel(subject, jeu, new TableValeurCleSQLListeCache(langueImpression.getLanguage(), GlobalConstants.TableValeur.JEUX, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
	        	listeJeux = listeJeux + jeuDescription + ", ";
	        }
	        mapRapportDossier.put("listeJeux", listeJeux);
	        //Recherche des sites
	        Collection liensInscription = delegate.findLiensInscription(subject, dossier);
	        it = liensInscription.iterator();
	        if(it.hasNext()) {
	        	Inscription inscription = (Inscription)it.next();
	        	InscriptionForm inscriptionForm = new InscriptionForm();
	        	ValueObjectMapper.convertInscription(inscription, inscriptionForm,subject.getLocale());
	        	inscriptionForm.assignerListeSitesChoisis(subject);
	            mapRapportDossier.put("stringSitesChoisis", inscriptionForm.getStringSitesChoisis());
	            mapRapportDossier.put("tousCasinoEtLudoplex", String.valueOf(inscriptionForm.isTousCasinoEtLudoplex()));
	            mapRapportDossier.put("duree", inscriptionForm.getDuree());
	            mapRapportDossier.put("aideInitialeDemandee", inscriptionForm.getAideInitiale());
	            //mapRapportDossier.put("aideInitiale", StringUtils.convertirBooleanLangue(inscriptionForm.getAideInitiale(), langueImpression));
	            //mapRapportDossier.put("aideImmediate", StringUtils.convertirBooleanLangue(inscriptionForm.getAideImmediate(), langueImpression));
	        }

	        list.add(mapRapportDossier);
	        
        } catch (ValueObjectMapperException vome) {
			  vome.printStackTrace();
        }			
		return list;
	}

private Map construireListeLibelles(Dossier dossier, Locale langueImpression, MessageResources mResources)
	throws BusinessException{
	List list = new ArrayList();
	//On remplit d'abord les libellés
	Map mapLibelles = new HashMap(); 
	
	mapLibelles.put("titre", mResources.getMessage(langueImpression, "titreAE"));
	mapLibelles.put("confidentiel", mResources.getMessage(langueImpression, "confidentiel"));
	if((dossier.getSiteOrigine() == Long.valueOf(GlobalConstants.SiteMaisonJeux.LUDOPLEX_QUEBEC)) ||
			(dossier.getSiteOrigine() == Long.valueOf(GlobalConstants.SiteMaisonJeux.LUDOPLEX_TROIS_RIVIERE))){
		mapLibelles.put("societe", mResources.getMessage(langueImpression, "societeSEJ"));
		mapLibelles.put("enonce", mResources.getMessage(langueImpression, "enonceSEJ"));
	}else{
		mapLibelles.put("societe", mResources.getMessage(langueImpression, "societeSCQ"));
		mapLibelles.put("enonce", mResources.getMessage(langueImpression, "enonceSCQ"));
	}
	mapLibelles.put("libelleAideImmediate", mResources.getMessage(langueImpression, "libelle.aide.immediate"));
	mapLibelles.put("demande", mResources.getMessage(langueImpression, "demande"));
	mapLibelles.put("libelleDemandeDebut", mResources.getMessage(langueImpression, "libelle.demande.debut")); 
	mapLibelles.put("libelleDemandeFin", mResources.getMessage(langueImpression, "libelle.demande.fin"));
	mapLibelles.put("libellePeriode", mResources.getMessage(langueImpression, "libelle.periode"));
	mapLibelles.put("au", mResources.getMessage(langueImpression, "au_t"));
	mapLibelles.put("libelleAideInitiale", mResources.getMessage(langueImpression, "libelle.aide.initiale"));
	mapLibelles.put("libelleJeux", mResources.getMessage(langueImpression, "tabpage_jeu") + mResources.getMessage(langueImpression, "2.points"));
	mapLibelles.put("libelleCondition1", mResources.getMessage(langueImpression, "libelle.condition1"));
	mapLibelles.put("libelleCondition2", mResources.getMessage(langueImpression, "libelle.condition2"));
	mapLibelles.put("sites", mResources.getMessage(langueImpression, "libelle.sites"));
	mapLibelles.put("tousSites", mResources.getMessage(langueImpression, "libelle.tous.maisons"));
	mapLibelles.put("remarque", mResources.getMessage(langueImpression, "remarque"));
	mapLibelles.put("signatures", mResources.getMessage(langueImpression, "signatures"));
	mapLibelles.put("contactePar", mResources.getMessage(langueImpression, "contacte.par"));

	return mapLibelles;
	}

public RapportVO construireNouveauRapportVO() {
	// TODO Auto-generated method stub
	return null;
}

public JasperPrint executer(CardexAuthenticationSubject subject,
		RapportVO rapportVO) throws BusinessException, JRException {
	// TODO Auto-generated method stub
	return null;
}

public void validerSecurite(CardexAuthenticationSubject subject) {
	// TODO Auto-generated method stub
	
}

@Override
protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO,
		Connection connection) throws BusinessResourceException,
		BusinessException {
	// TODO Auto-generated method stub
	return null;
}



}
