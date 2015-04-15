package com.lotoquebec.cardex.generateurRapport.suivi;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SiteIntervenantRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SiteIntervenantSuiviGenerateurRapport_CDX_0094 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/suiviSiteIntervenant");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new SiteIntervenantRapportVO();
	}
	
	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		SiteIntervenantRapportVO siteIntervenantRapportVO = (SiteIntervenantRapportVO) rapportVO;
		CardexUser cardexUser = (CardexUser) subject.getUser();
		ListeCache listeCache = ListeCache.getInstance();
		String activite = "";
		try {
			activite = listeCache.obtenirLabel(subject, String.valueOf(siteIntervenantRapportVO.getActivite()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.TYPE_ACTIVITE, GlobalConstants.ActionSecurite.SELECTION));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("SITE", siteIntervenantRapportVO.getSite());
		parameters.put("ACTIVITE", activite);
		return parameters;
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	ResultSet resultSet = delegate.rapportSuivisIntervenant((SiteIntervenantRapportVO) rapportVO, "CARDEX_RAPPORT.SP_RAP_SUIVI_INVESTIGATION",connection);
       	return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SUIVIS_INVESTIGATION);
	}

}