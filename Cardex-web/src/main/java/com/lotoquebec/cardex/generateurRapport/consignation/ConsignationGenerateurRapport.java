package com.lotoquebec.cardex.generateurRapport.consignation;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.generateurRapport.sql.GenererRapportSQL;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;

public abstract class ConsignationGenerateurRapport extends GenererRapportSQL {

	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new ConsignationRapportVO();
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		ConsignationRapportVO consignationRapportVO = (ConsignationRapportVO) vo; 
		Map parameters = super.construireParametres(subject, vo, connection);
		parameters.put("SITE", consignationRapportVO.getSite());
		parameters.put("NATURE", consignationRapportVO.getNature());
		parameters.put("TYPE", consignationRapportVO.getType());
		parameters.put("CATEGORIE", consignationRapportVO.getCategorie());
		return parameters;
	}
}
