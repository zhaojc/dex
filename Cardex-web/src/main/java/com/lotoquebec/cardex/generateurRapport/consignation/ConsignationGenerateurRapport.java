package com.lotoquebec.cardex.generateurRapport.consignation;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.sql.GenererRapportSQL;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

public abstract class ConsignationGenerateurRapport extends GenererRapportSQL {

	@Override
	public RapportVO construireNouveauRapportVO() {
		return new ConsignationRapportVO();
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		ConsignationRapportVO consignationRapportVO = (ConsignationRapportVO) rapportVO; 
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("SITE", consignationRapportVO.getSite());
		parameters.put("NATURE", consignationRapportVO.getNature());
		parameters.put("TYPE", consignationRapportVO.getType());
		parameters.put("CATEGORIE", consignationRapportVO.getCategorie());
		return parameters;
	}
}
