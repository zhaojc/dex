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
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @Deprecated sera retirer dans la version 5.5 
 */

public class DossierAutoexclusionEspacejeux extends DossierRapport {
 
	protected InputStream obtenirGabarit() {
		return getClass().getClassLoader().getResourceAsStream("rapports/" + RapportsConfiguration.AUTOEXCLUSION_ESPACEJEUX);
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
	        
		return list;
	}

	private Map construireListeLibelles(Dossier dossier, Locale langueImpression, MessageResources mResources)
		throws BusinessException{
		List list = new ArrayList();
		//On remplit d'abord les libellés
		Map mapLibelles = new HashMap(); 
		
		mapLibelles.put("titre", mResources.getMessage(langueImpression, "titreAE"));
		mapLibelles.put("confidentiel", mResources.getMessage(langueImpression, "confidentiel"));
		mapLibelles.put("societe", mResources.getMessage(langueImpression, "societeEJ"));
		mapLibelles.put("enonce", mResources.getMessage(langueImpression, "enonceEJ"));
		mapLibelles.put("demande", mResources.getMessage(langueImpression, "demandeEJ"));
		mapLibelles.put("libelleDemandeDebut", mResources.getMessage(langueImpression, "libelle.demandeEJ")); 
		mapLibelles.put("libellePeriode", mResources.getMessage(langueImpression, "libelle.periode"));
		mapLibelles.put("au", mResources.getMessage(langueImpression, "au_t"));
		mapLibelles.put("libelleCondition1", mResources.getMessage(langueImpression, "libelle.conditionEJ"));
		mapLibelles.put("libelleCondition2", mResources.getMessage(langueImpression, "acceptationEJ"));
		mapLibelles.put("remarque", mResources.getMessage(langueImpression, "cocherEJ"));
		mapLibelles.put("signatures", mResources.getMessage(langueImpression, "signatures"));
	
		return mapLibelles;
	}

	public RapportVO construireNouveauRapportVO() {
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
