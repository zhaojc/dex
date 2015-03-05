package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatutDossierRapportVO_CDX_0055;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class StatutDossierGenerateurRapport_CDX_0055 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersStatutDossier");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new StatutDossierRapportVO_CDX_0055();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		StatutDossierRapportVO_CDX_0055 statutDossierRapportVO_CDX_0055 = (StatutDossierRapportVO_CDX_0055) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	ResultSet resultSet = delegate.statutDossiers(statutDossierRapportVO_CDX_0055);
		return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		StatutDossierRapportVO_CDX_0055 statutDossierRapportVO_CDX_0055 = (StatutDossierRapportVO_CDX_0055) rapportVO;
		
		parameters.put("NATURE", statutDossierRapportVO_CDX_0055.getNature()+"");
		
		parameters.put("sous_rapport_enquetes_actives", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_ACTIVES)));
		parameters.put("sous_rapport_enquetes_en_cours", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_EN_COURS)));
		parameters.put("sous_rapport_enquetes_nouvelles", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_NOUVELLES)));
		parameters.put("sous_rapport_enquetes_terminees", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_TERMINEES)));
		
		parameters.put("sous_rapport_total_enquetes_actives", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_ACTIVES)));
		parameters.put("sous_rapport_total_enquetes_en_cours", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_EN_COURS)));
		parameters.put("sous_rapport_total_enquetes_nouvelles", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_NOUVELLES)));
		parameters.put("sous_rapport_total_enquetes_terminees", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_TERMINEES)));
		
		parameters.put("REPORT_CONNECTION", connection);
		
		return parameters;
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.STATUT_DOSSIERS);
	}

}
