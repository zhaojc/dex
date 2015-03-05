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
import com.lotoquebec.cardex.business.vo.rapport.CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class CumulatifHebdomadaireEnquetesDossierGenerateurRapport_CDX_0041 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersCumulatifHebdomadaireEnquetes");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041 rapportDossierVO =(CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.cumulatifHebdomadaireEnquetesDossier(rapportDossierVO);
       	return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("sous_rapport_cumul_hebdo_enquetes_fermees", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream("sous_rapport_cumul_hebdo_enquetes_fermees.jasper")));
		parameters.put("sous_rapport_cumul_hebdo_enquetes_actives", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream("sous_rapport_cumul_hebdo_enquetes_actives.jasper")));
		parameters.put("sous_rapport_cumul_hebdo_total_enquetes_fermees", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream("sous_rapport_cumul_hebdo_total_enquetes_fermees.jasper")));
		parameters.put("sous_rapport_cumul_hebdo_total_enquetes_actives", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream("sous_rapport_cumul_hebdo_total_enquetes_actives.jasper")));
		parameters.put("sous_rapport_cumul_hebdo_enquetes", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream("sous_rapport_cumul_hebdo_enquetes.jasper")));
		parameters.put("REPORT_CONNECTION", connection);
		
		return parameters;
	}
	
	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.CUMUL_HEBDOMADAIRE);
	}

}
