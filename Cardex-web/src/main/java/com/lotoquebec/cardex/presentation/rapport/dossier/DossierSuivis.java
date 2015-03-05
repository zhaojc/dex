package com.lotoquebec.cardex.presentation.rapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts.util.MessageResources;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import java.math.BigDecimal;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.RapportDossierVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.InscriptionForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
//import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.JeuCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LocalisationCleListeCache;
//import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.JeuxEntiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @Deprecated sera retirer dans la version 5.5 
 */
public class DossierSuivis extends DossierRapport {
 
	protected InputStream obtenirGabarit() {
		return getClass().getClassLoader().getResourceAsStream("rapports/" + RapportsConfiguration.RAPPORT_SUIVIS);
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
    	DossierForm dossierForm = new DossierForm();
    	try{
	    	ValueObjectMapper.convertDossier(dossier, dossierForm, langueImpression);
	    	dossierForm.assignerValeurDeListe(subject);
	    	//On ajoute les informations du dossier
	        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
	        mapRapportDossier.put("intervenantDescription", dossier.getIntervenantDescription());
	        mapRapportDossier.put("dossierCle", BigDecimal.valueOf(dossier.getCle()));
	        mapRapportDossier.put("dossierSite", BigDecimal.valueOf(dossier.getSite()));
	        mapRapportDossier.put("categorie", dossierForm.getCategorieDescription());
	        mapRapportDossier.put("type", dossierForm.getTypeDescription());
	        mapRapportDossier.put("numeroCardex", dossierForm.getNumeroCardexTexte());
	        mapRapportDossier.put("reference1", dossierForm.getReference1());
	        mapRapportDossier.put("duree", dossierForm.getDuree());
	        mapRapportDossier.put("dateDebut", dossier.getDateDebut());
	        mapRapportDossier.put("dateFin", dossier.getDateFin());
	        mapRapportDossier.put("categorie", dossierForm.getCategorieDescription());
	
			DossierBusinessDelegate delegate = new DossierBusinessDelegate();
			//On va chercher le sujet relié
	        Collection liensSujets;
	        Iterator it;
		        liensSujets = delegate.findLiensSujet(subject, dossier);
		        it = liensSujets.iterator();
		        if(it.hasNext()) {
		            Sujet linkSujet = (Sujet) it.next();
		            //On passe la clé et le site du sujet pour le sous-rapport
					mapRapportDossier.put("sujetCle", BigDecimal.valueOf(linkSujet.getCle()));
					mapRapportDossier.put("sujetSite", BigDecimal.valueOf(linkSujet.getSite()));
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
