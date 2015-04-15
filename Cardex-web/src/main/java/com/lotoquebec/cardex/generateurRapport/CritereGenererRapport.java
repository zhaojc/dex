package com.lotoquebec.cardex.generateurRapport;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.text.DateFormat;

public abstract class CritereGenererRapport extends GenererRapport {

	protected abstract JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException;

	@Override
	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		CritereRapportVO critereRapportVO = (CritereRapportVO) vo;
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
        parameters.put("DATE_DEBUT",DateFormat.format(critereRapportVO.getDateDebutDu()));
        parameters.put("DATE_FIN",DateFormat.format(critereRapportVO.getDateDebutAu()));
		return parameters;
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		return construireDataSource(subject, (CritereRapportVO)vo, connection);
	}
	
}
