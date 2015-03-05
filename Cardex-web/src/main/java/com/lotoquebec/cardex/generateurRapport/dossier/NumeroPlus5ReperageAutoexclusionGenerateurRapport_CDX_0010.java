package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForward;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ReperageAutoexclusionDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class NumeroPlus5ReperageAutoexclusionGenerateurRapport_CDX_0010 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersReperages5PlusAutoexclusionNumero");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new ReperageAutoexclusionDossierRapportVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
			ReperageAutoexclusionDossierRapportVO rapportDossierVO =(ReperageAutoexclusionDossierRapportVO) rapportVO;
			RapportBusinessDelegate delegate = new RapportBusinessDelegate();
			Collection liste = delegate.rapportReperageAutoexclusion(subject, rapportDossierVO, "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NUMERO", 5);
	       	return new JRMapCollectionDataSource(liste);
	}

	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("sous-rapport_reperages", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_REPERAGES_AUTOEXCLUSIONS)));
		return parameters;
	}
	
	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO);
	}

}
