package com.lotoquebec.cardex.generateurRapport.dossier;

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
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class SuiviDossierGenerateurRapport_CDX_0223 extends CritereGenererRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_SUIVIS);
	}

	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent � ceux du rapport.
	private List construireListeDataSource(CardexAuthenticationSubject subject, Dossier dossier, Map mapRapportDossier)
	 			throws BusinessException{
		List list = new ArrayList();
    	ListeCache cache = ListeCache.getInstance();
    	
    	//On ajoute les informations du dossier
        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
        mapRapportDossier.put("intervenantDescription", dossier.getIntervenantDescription());
        mapRapportDossier.put("dossierCle", BigDecimal.valueOf(dossier.getCle()));
        mapRapportDossier.put("dossierSite", BigDecimal.valueOf(dossier.getSite()));
        String categorieDescription = cache.obtenirLabel(subject, dossier.getCategorie(), new CategorieCleMultiListeCache(subject, dossier.getType()));
        mapRapportDossier.put("categorie", categorieDescription);
        String typeDescription = cache.obtenirLabel(subject, dossier.getType(), new TypeCleMultiListeCache(subject, dossier.getNature()));
        mapRapportDossier.put("type", typeDescription);
        //mapRapportDossier.put("numeroCardex", getNumeroCardexTexte(dossier.getNumeroCardex()));
        mapRapportDossier.put("numeroCardex", dossier.getNumeroCardex());
        mapRapportDossier.put("reference1", dossier.getReference1());
        mapRapportDossier.put("duree", dossier.getDuree());
        mapRapportDossier.put("dateDebut", dossier.getDateDebut());
        mapRapportDossier.put("dateFin", dossier.getDateFin());

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va chercher le sujet reli�
        Collection liensSujets;
        Iterator it;
        liensSujets = delegate.findLiensSujet(subject, dossier);
        it = liensSujets.iterator();
        while(it.hasNext()) {
            Sujet linkSujet = (Sujet) it.next();
            //On passe la cl� et le site du sujet pour le sous-rapport
            //On exclut cependant les sujets dont le r�le est demandeur (348), 
            //car ce rapport sert principalement aux enqu�tes et on n'enqu�te pas
            //le sujet qui a fait la demande d'enqu�te!
            if(linkSujet.getRole() != GlobalConstants.Role.DEMANDEUR){
            	mapRapportDossier.put("sujetCle", BigDecimal.valueOf(linkSujet.getCle()));
            	mapRapportDossier.put("sujetSite", BigDecimal.valueOf(linkSujet.getSite()));
            }
		}

        list.add(mapRapportDossier);

	        
		return list;
	}

	public String getNumeroCardexTexte(String numeroCardex) {
		return StringUtils.left(numeroCardex,3)+"-"+StringUtils.mid(numeroCardex,3,10)+"-"+StringUtils.right(numeroCardex, 4);
	}
	
	private Map construireListeLibelles(Dossier dossier)
		throws BusinessException{
		List list = new ArrayList();
		//On remplit d'abord les libell�s
		Map mapLibelles = new HashMap(); 
		
		mapLibelles.put("confidentiel", bundle.getString("confidentiel"));
		mapLibelles.put("libelleNoCardex", bundle.getString("v_do_numero_dossier")+ bundle.getString("2.points"));
		mapLibelles.put("libelleNoDossier", bundle.getString("v_do_numero_t"));
		mapLibelles.put("libelleReference1", bundle.getString("v_do_reference1_t"));
		mapLibelles.put("libelleDuree", bundle.getString("v_is_duree_t2"));
		mapLibelles.put("libelleDateDebut", bundle.getString("d_date_debut_t"));
		mapLibelles.put("libelleDateFin", bundle.getString("d_date_fin_t"));
		mapLibelles.put("libelleType", bundle.getString("i_ty_cle_t"));
		mapLibelles.put("libelleCategorie", bundle.getString("i_ca_cle_t"));
	
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
		parameters.put("sous_rapport_sujet_suivis", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_SUJET_SUIVIS)));
		parameters.put("sous_rapport_sujet_suivis_anglais", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_SUJET_SUIVIS_ANGLAIS)));
		parameters.put("dossier_suivis", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_DOSSIER_SUIVIS)));
		parameters.put("dossier_categorie", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_DOSSIER_CATEGORIE)));
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
