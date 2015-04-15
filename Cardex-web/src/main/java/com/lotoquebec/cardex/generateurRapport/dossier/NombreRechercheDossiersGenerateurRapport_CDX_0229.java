package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.NombreRechercheDossierVO_CDX_0229;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ClasseCleListeCache;
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
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class NombreRechercheDossiersGenerateurRapport_CDX_0229 extends CritereGenererRapport {
 
	
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_NOMBRE_DOSSIERS);
	}
	
	public CritereRapportVO construireNouveauRapportVO() {
		return new NombreRechercheDossierVO_CDX_0229();
	}
	
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/rapportNombreRechercheDossiers");
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		List list = new ArrayList();
		Map mapRapport = new HashMap();
		NombreRechercheDossierVO_CDX_0229 criteresRechercheDossierVO = (NombreRechercheDossierVO_CDX_0229) rapportVO;
    	ListeCache cache = ListeCache.getInstance();
    	
		mapRapport.put("entiteDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getEntite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.ENTITE, GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
		mapRapport.put("genreDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getGenre(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, criteresRechercheDossierVO.getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
   		mapRapport.put("natureDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getNature(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, criteresRechercheDossierVO.getGenre(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
   		mapRapport.put("typeDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getType(), new TypeCleMultiListeCache(subject, criteresRechercheDossierVO.getNature())));
   		mapRapport.put("categorieDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getCategorie(), new CategorieCleMultiListeCache(subject, criteresRechercheDossierVO.getType())));
   		mapRapport.put("statutDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.DOSSIER)));
   		mapRapport.put("severiteDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getSeverite(), new SeveriteCleListeCache(subject)));
   		mapRapport.put("confidentialiteDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getConfidentialite(), new ConfidentialiteCleListeCache(subject)));
   		mapRapport.put("fondeDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getFonde(), new FondeCleListeCache(subject)));
   		mapRapport.put("endroitDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getEndroit(), new EndroitCleListeCache(subject)));
   		mapRapport.put("localisationDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getLocalisation(), new LocalisationCleListeCache(subject)));
   		mapRapport.put("siteOrigineDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getSiteOrigine(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, criteresRechercheDossierVO.getEntite(), GlobalConstants.ActionSecurite.SELECTION)));
   		mapRapport.put("siteApplicableDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getSiteApplicable(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, criteresRechercheDossierVO.getEntite(), GlobalConstants.ActionSecurite.SELECTION)));
   		mapRapport.put("intervenantDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getIntervenant(), new IntervenantParSiteCle(subject.getLocale().getLanguage(), criteresRechercheDossierVO.getSiteOrigine())));
		mapRapport.put("origineDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getOrigine(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.ORIGINE, criteresRechercheDossierVO.getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
		mapRapport.put("classeDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getClasse(), new ClasseCleListeCache(subject)));
		mapRapport.put("rechercherSousCategorie", criteresRechercheDossierVO.getRechercherSousCategorie());
		mapRapport.put("rechercherTous", criteresRechercheDossierVO.getRechercherTous());
		mapRapport.put("numeroCardex",  getNumeroCardexTexte(criteresRechercheDossierVO.getNumeroCardex()));
		mapRapport.put("numeroDossier", criteresRechercheDossierVO.getNumeroDossier());
		mapRapport.put("numeroFicheSujet", criteresRechercheDossierVO.getNumeroFicheSujet());
		mapRapport.put("reference1", criteresRechercheDossierVO.getReference1());		
		mapRapport.put("reference2", criteresRechercheDossierVO.getReference2());		
		mapRapport.put("reference3", criteresRechercheDossierVO.getReference3());		
		mapRapport.put("descriptif", criteresRechercheDossierVO.getDescriptif());		
		mapRapport.put("typeJeuDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getTypeJeu(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.TYPE_JEU, GlobalConstants.ActionSecurite.TOUTES_ACTIONS)));
		mapRapport.put("jeuDescription", cache.obtenirLabel(subject, criteresRechercheDossierVO.getJeu(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.JEUX, GlobalConstants.ActionSecurite.TOUTES_ACTIONS)));
		mapRapport.put("enregistrementNumerique", criteresRechercheDossierVO.getEnregistrementNumerique());		
		mapRapport.put("enregistrementConserve", criteresRechercheDossierVO.getEnregistrementConserve());	
		mapRapport.put("referenceVideo", criteresRechercheDossierVO.getReferenceVideo());
		mapRapport.put("dateCreationDu", DateFormat.format(criteresRechercheDossierVO.getDateCreationDu()));
		mapRapport.put("dateCreationAu", DateFormat.format(criteresRechercheDossierVO.getDateCreationAu()));
		mapRapport.put("dateDebutDu", DateFormat.format(criteresRechercheDossierVO.getDateDebutDu()));
		mapRapport.put("dateDebutAu", DateFormat.format(criteresRechercheDossierVO.getDateDebutAu()));
		mapRapport.put("dateFinDu", DateFormat.format(criteresRechercheDossierVO.getDateFinDu()));
		mapRapport.put("dateFinAu", DateFormat.format(criteresRechercheDossierVO.getDateFinAu()));

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		int nombre = delegate.nombreDossiers(subject, criteresRechercheDossierVO);
		
		mapRapport.put("total", String.valueOf(nombre));		
		list.add(mapRapport);
	        
        return new JRMapCollectionDataSource(list);
	}

	public String getNumeroCardexTexte(String numeroCardex) {
		if (StringUtils.isNotEmpty(numeroCardex))
			return StringUtils.left(numeroCardex,3)+"-"+StringUtils.mid(numeroCardex,3,10)+"-"+StringUtils.right(numeroCardex, 4);
		return "";
	}

}
