package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.DelaiTraitementEnqueteRapportVO_CDX_0246;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class DelaiTraitementEnquetesGenererRapport_CDX_0246 extends GenererRapport {
 
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/delaiTraitementEnquetes");
	}

	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.DELAI_TRAITEMENT_ENQUETES);
	}

	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		DelaiTraitementEnqueteRapportVO_CDX_0246 delaiTraitementEnqueteRapportVO_CDX_0246 = (DelaiTraitementEnqueteRapportVO_CDX_0246) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		Collection liste = delegate.rapportDelaiTraitementEnquetes(delaiTraitementEnqueteRapportVO_CDX_0246);
		return new JRMapCollectionDataSource(liste);
	}
	
	public RapportVO construireNouveauRapportVO() {
		return new DelaiTraitementEnqueteRapportVO_CDX_0246();
	}
		
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		DelaiTraitementEnqueteRapportVO_CDX_0246 delaiTraitementEnqueteRapportVO_CDX_0246 = (DelaiTraitementEnqueteRapportVO_CDX_0246) rapportVO;
    	ListeCache cache = ListeCache.getInstance();
    	try{
    		parameters.put("TYPE_SUIVI", cache.obtenirLabel(subject, delaiTraitementEnqueteRapportVO_CDX_0246.getActivite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.TYPE_ACTIVITE, GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER)));
    	} catch (BusinessResourceException e) {
    		e.printStackTrace();
    	}
		
		return parameters;
	}

}
