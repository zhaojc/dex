package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheNarrationVO;
import com.lotoquebec.cardex.business.vo.rapport.ActifIntervenantDossierRapportVO_CDX_0102;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.entite.AccesRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class RAQDossierGenerateurRapport_CDX_0070 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersPartagesParIntervenant");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new CriteresRechercheNarrationVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		CriteresRechercheNarrationVO criteresRechercheNarrationVO = (CriteresRechercheNarrationVO) rapportVO;
		try {
			List<RapportVOCDX_00070> rapportVOCDX_00070s = FabriqueCardexDAO.getInstance().getRapportDAO().globalRAQCDX_00070(subject, criteresRechercheNarrationVO.getDateDebutDu(), criteresRechercheNarrationVO.getDateDebutAu(), 0);
	       	return new JRBeanCollectionDataSource(rapportVOCDX_00070s);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
       	
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_ACTIVITES_CDX_0070);
	}

	@Override
	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
		parameters.put("TITRE", "Rapport d'activité quotidien");
		return parameters;
	}

	
}
