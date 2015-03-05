package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.facade.rapport.ClientMystereCDX_0255RapportCardex;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;


public class ClientsMysteresFichierMaitreGenererRapportSQL_CDX_0255 extends GenererRapport {

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/clientsMysteresFichierMaitre");
	}

	@Override
	protected JRDataSource construireDataSource(
			CardexAuthenticationSubject subject, RapportVO rapportVO,
			Connection connection) throws BusinessResourceException,
			BusinessException {
		return null;
	}

	@Override
	protected InputStream obtenirGabarit() {
		return null;
	}

	@Override
	public JasperPrint executer(CardexAuthenticationSubject subject, RapportVO rapportVO, ResourceBundle bundle, Locale locale) throws BusinessException {
		validerSecurite(subject);
		return (new ClientMystereCDX_0255RapportCardex(subject)).executer();
	}

	

}
