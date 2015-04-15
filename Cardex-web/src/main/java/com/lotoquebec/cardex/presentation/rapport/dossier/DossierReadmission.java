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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @Deprecated sera retirer dans la version 5.5 
 */

public class DossierReadmission extends DossierRapport { 
 
	protected InputStream obtenirGabarit() {
		//return RapportsConfiguration.class.getResourceAsStream("rapports/" + RapportsConfiguration.AUTOEXCLUSION);
		return getClass().getClassLoader().getResourceAsStream("rapports/" + RapportsConfiguration.READMISSION);
	}

	protected Dossier produireRapport(CardexAuthenticationSubject subject,
			Dossier dossier) throws BusinessException {
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va rechercher le dossier au complet au cas o� plus d'un dossier serait affich� dans la session Cardex. Dans ce cas,
		//l'impression se fait avec le dernier dossier ouvert et non avec le dossier affich�.
		return delegate.find(subject, dossier);
	}

	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, Dossier dossier, Locale langueImpression, MessageResources mResources) throws BusinessException {
		List list = new ArrayList();
		Map mapRapportDossier = new HashMap();
		//On ajoute les champs qui seront imprim�s sur le contrat
		list.addAll(construireListeDataSource(subject, dossier, langueImpression, mapRapportDossier));

		return new JRMapCollectionDataSource(list);
	}
	
	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent � ceux du rapport.
	private List construireListeDataSource(CardexAuthenticationSubject subject, Dossier dossier, Locale langueImpression, Map mapRapportDossier)
	 			throws BusinessException{
		List list = new ArrayList();
    	ListeCache cache = ListeCache.getInstance();

    	//On ajoute les informations du dossier
        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
        mapRapportDossier.put("numeroCardex", dossier.getNumeroCardex());
        mapRapportDossier.put("duree", dossier.getDuree());
        mapRapportDossier.put("dateDebut", StringUtils.substring(dossier.getDateDebut().toString(),0,10));

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va chercher le sujet reli�
        Collection liensSujets;
        Iterator it;
	        liensSujets = delegate.findLiensSujet(subject, dossier);
	        it = liensSujets.iterator();
	        if(it.hasNext()) {
	            Sujet linkSujet = (Sujet) it.next();
	            //On passe la cl� et le site du sujet pour le sous-rapport
				mapRapportDossier.put("nom", linkSujet.getNom());
				mapRapportDossier.put("prenom", linkSujet.getPrenom());
	        }
	        list.add(mapRapportDossier);
		return list;
	}

	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO,
			Connection connection) throws BusinessResourceException,
			BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		// TODO Auto-generated method stub
		
	}
	
}
