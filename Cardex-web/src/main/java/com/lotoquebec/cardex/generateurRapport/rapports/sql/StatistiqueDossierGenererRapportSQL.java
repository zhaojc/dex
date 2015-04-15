package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.sql.GenererRapportSQL;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;


public abstract class StatistiqueDossierGenererRapportSQL extends GenererRapportSQL {

	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new StatistiqueDossierRapportVO();
	}
	
	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		StatistiqueDossierRapportVO cumulatifDossierRapportVO = (StatistiqueDossierRapportVO) vo;
		
		Map parameters = new HashMap();
		parameters.put("NATURE", cumulatifDossierRapportVO.getNature());
		parameters.put("TYPE", cumulatifDossierRapportVO.getType());
		parameters.put("SITE", cumulatifDossierRapportVO.getSite());
		parameters.put("FONDE", cumulatifDossierRapportVO.getFonde());
		parameters.put("DATE_DEBUT", cumulatifDossierRapportVO.getDateDebutDu());
		parameters.put("DATE_FIN", cumulatifDossierRapportVO.getDateDebutAu());
		parameters.put("UTILISATEUR", subject.getPrincipal().getName());
		parameters.put("VAGUE", cumulatifDossierRapportVO.getVague());
		return parameters;
	}
	
}
