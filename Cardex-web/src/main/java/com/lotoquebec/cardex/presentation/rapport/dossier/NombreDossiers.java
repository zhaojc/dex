package com.lotoquebec.cardex.presentation.rapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EndroitCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.FondeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LocalisationCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;

//Rapport CDX_0229 pour connaître le nombre de dossiers retournés par des critères de recherche
/**
 * @Deprecated sera retirer dans la version 5.5 
 */
public class NombreDossiers extends RechercheDossierRapport {
 
	protected InputStream obtenirGabarit() {
		return getClass().getClassLoader().getResourceAsStream("rapports/" + RapportsConfiguration.RAPPORT_NOMBRE_DOSSIERS);
	}

	protected int produireRapport(CardexAuthenticationSubject subject,
			CriteresRechercheDossier criteres) throws BusinessException {
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On calcule le nombre de dossiers retournés par les critères inscrits à l'écran.
		return delegate.nombreDossiers(subject, criteres);
	}

	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent à ceux du rapport.
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CriteresRechercheDossierForm criteres, MessageResources mResources)
	 			throws BusinessException{
		List list = new ArrayList();
		Map mapRapport = new HashMap();
    	ListeCache cache = ListeCache.getInstance();
    	
		mapRapport.put("entiteDescription", cache.obtenirLabel(subject, criteres.getEntite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.ENTITE, GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
		mapRapport.put("genreDescription", cache.obtenirLabel(subject, criteres.getGenre(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, criteres.getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
   		mapRapport.put("natureDescription", cache.obtenirLabel(subject, criteres.getNature(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, criteres.getGenre(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
   		mapRapport.put("typeDescription", cache.obtenirLabel(subject, criteres.getType(), new TypeCleMultiListeCache(subject, criteres.getNature())));
   		mapRapport.put("categorieDescription", cache.obtenirLabel(subject, criteres.getCategorie(), new CategorieCleMultiListeCache(subject, criteres.getType())));
   		mapRapport.put("statutDescription", cache.obtenirLabel(subject, criteres.getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.DOSSIER)));
   		mapRapport.put("severiteDescription", cache.obtenirLabel(subject, criteres.getSeverite(), new SeveriteCleListeCache(subject)));
   		mapRapport.put("confidentialiteDescription", cache.obtenirLabel(subject, criteres.getConfidentialite(), new ConfidentialiteCleListeCache(subject)));
   		mapRapport.put("fondeDescription", cache.obtenirLabel(subject, criteres.getFonde(), new FondeCleListeCache(subject)));
   		mapRapport.put("endroitDescription", cache.obtenirLabel(subject, criteres.getEndroit(), new EndroitCleListeCache(subject)));
   		mapRapport.put("localisationDescription", cache.obtenirLabel(subject, criteres.getLocalisation(), new LocalisationCleListeCache(subject)));
   		mapRapport.put("siteOrigineDescription", cache.obtenirLabel(subject, criteres.getSiteOrigine(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, criteres.getEntite(), GlobalConstants.ActionSecurite.SELECTION)));
   		mapRapport.put("siteApplicableDescription", cache.obtenirLabel(subject, criteres.getSiteApplicable(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, criteres.getEntite(), GlobalConstants.ActionSecurite.SELECTION)));
   		mapRapport.put("intervenantDescription", cache.obtenirLabel(subject, criteres.getIntervenant(), new IntervenantParSiteCle(subject.getLocale().getLanguage(), criteres.getSiteOrigine())));
		mapRapport.put("origineDescription", cache.obtenirLabel(subject, criteres.getOrigine(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.ORIGINE, criteres.getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
		mapRapport.put("rechercherSousCategorie", criteres.getRechercherSousCategorie());
		mapRapport.put("rechercherTous", criteres.getRechercherTous());
		mapRapport.put("numeroCardex",  criteres.getNumeroCardex().getSite()+"-"+criteres.getNumeroCardex().getDate()+"-"+criteres.getNumeroCardex().getSequence());
		mapRapport.put("numeroDossier", criteres.getNumeroDossier());
		mapRapport.put("numeroFicheSujet", criteres.getNumeroFicheSujet());
		mapRapport.put("reference1", criteres.getReference1());		
		mapRapport.put("reference2", criteres.getReference2());		
		mapRapport.put("reference3", criteres.getReference3());		
		mapRapport.put("descriptif", criteres.getDescriptif());		
		mapRapport.put("jeuDescription", cache.obtenirLabel(subject, criteres.getJeu(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.JEUX, GlobalConstants.ActionSecurite.TOUTES_ACTIONS)));
		mapRapport.put("enregistrementNumerique", criteres.getEnregistrementNumerique());		
		mapRapport.put("enregistrementConserve", criteres.getEnregistrementConserve());	
		mapRapport.put("referenceVideo", criteres.getReferenceVideo());		
		mapRapport.put("dateCreationDu", criteres.getDateCreationDu());		
		mapRapport.put("dateCreationAu", criteres.getDateCreationAu());		
		mapRapport.put("dateDebutDu", criteres.getDateDebutDu());		
		mapRapport.put("dateDebutAu", criteres.getDateDebutAu());		
		mapRapport.put("dateFinDu", criteres.getDateFinDu());		
		mapRapport.put("dateFinAu", criteres.getDateFinAu());		

		mapRapport.put("total", criteres.getNombreDossiers());		
		list.add(mapRapport);
	        
        return new JRMapCollectionDataSource(list);
	}

private Map construireListeLibelles(Dossier dossier, Locale langueImpression, MessageResources mResources)
	throws BusinessException{
	List list = new ArrayList();
	//On remplit d'abord les libellés
	Map mapLibelles = new HashMap(); 
	
	mapLibelles.put("confidentiel", mResources.getMessage(langueImpression, "confidentiel"));
	mapLibelles.put("libelleNoCardex", mResources.getMessage(langueImpression, "v_do_numero_dossier")+ mResources.getMessage(langueImpression, "2.points"));
	mapLibelles.put("libelleNoDossier", mResources.getMessage(langueImpression, "v_do_numero_t"));
	mapLibelles.put("libelleReference1", mResources.getMessage(langueImpression, "v_do_reference1_t"));
	mapLibelles.put("libelleDuree", mResources.getMessage(langueImpression, "v_is_duree_t2"));
	mapLibelles.put("libelleDateDebut", mResources.getMessage(langueImpression, "d_date_debut_t"));
	mapLibelles.put("libelleDateFin", mResources.getMessage(langueImpression, "d_date_fin_t"));
	mapLibelles.put("libelleType", mResources.getMessage(langueImpression, "i_ty_cle_t"));
	mapLibelles.put("libelleCategorie", mResources.getMessage(langueImpression, "i_ca_cle_t"));

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
