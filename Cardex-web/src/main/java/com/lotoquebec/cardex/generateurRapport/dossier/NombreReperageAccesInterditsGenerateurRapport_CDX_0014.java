package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ReperageAutoexclusionDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class NombreReperageAccesInterditsGenerateurRapport_CDX_0014 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersReperagesAccesInterditsNombre");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new ReperageAutoexclusionDossierRapportVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		ReperageAutoexclusionDossierRapportVO rapportDossierVO =(ReperageAutoexclusionDossierRapportVO) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		Collection liste = delegate.rapportReperageAccesInterdit(subject, rapportDossierVO, "CARDEX_RAPPORT.SP_RAP_REP_AI_TRI_NOMBRE");
       	return new JRMapCollectionDataSource(liste);
	}

	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("sous-rapport_reperages", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_REPERAGES_AUTOEXCLUSIONS)));
		return parameters;
	}
	
	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.REPERAGES_ACCES_INTERDITS_NOMBRE);
	}

}
