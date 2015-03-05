package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.RapportDossierVO;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class TableauEndroitsRegroupesGenerateurRapport_CDX_0280 extends GenererRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.TABLEAU_ENDROITS_REGROUPES);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/tableauEndroitsRegroupes");
	}
	
	public RapportVO construireNouveauRapportVO() {
		return new StatistiqueDossierRapportVO();
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException,BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		StatistiqueDossierRapportVO statistiqueDossierRapportVO = (StatistiqueDossierRapportVO) rapportVO;
		ResultSet resultSet = delegate.rapportTableauEndroitsRegroupes(statistiqueDossierRapportVO);
		return new JRResultSetDataSource(resultSet);
	}

	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		StatistiqueDossierRapportVO statistiqueDossierRapportVO = (StatistiqueDossierRapportVO) rapportVO;
		CardexUser cardexUser = (CardexUser) subject.getUser();
		parameters.put("DateDebut", StringUtils.substring(statistiqueDossierRapportVO.getDateDebutDu().toString(),0,10));
		parameters.put("DateFin", StringUtils.substring(statistiqueDossierRapportVO.getDateDebutAu().toString(),0,10));
		parameters.put("UTILISATEUR", cardexUser.getCode());
		return parameters;
	}
	
}
