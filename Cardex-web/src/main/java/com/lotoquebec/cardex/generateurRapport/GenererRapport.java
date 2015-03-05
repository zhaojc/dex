package com.lotoquebec.cardex.generateurRapport;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.text.DateFormat;

public abstract class GenererRapport {

	protected Locale locale;
	protected ResourceBundle bundle;
	
	/**
	 * Cette m�thode valide si l'utilisateur � acc�s au rapport.
	 * @param subject
	 */
	protected abstract void validerSecurite(CardexAuthenticationSubject subject);
	
	/**
	 * Cette m�thode retourne le stream du .jasper
	 * @return
	 */
	protected abstract InputStream obtenirGabarit();
	
	/**
	 * Retourne la source de donn�e qui fournie le rapport
	 * @param subject TODO
	 * @param rapportVO
	 * @return
	 * @throws BusinessResourceException
	 * @throws BusinessException
	 */
	protected abstract JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException;
	
	public RapportVO construireNouveauRapportVO() {
		return new RapportVO();
	}
	
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = new HashMap();
        parameters.put("DATE_DEBUT",DateFormat.format(rapportVO.getDateDebutDu()));
        parameters.put("DATE_FIN",DateFormat.format(rapportVO.getDateDebutAu()));
        parameters.put("UTILISATEUR",subject.getPrincipal().getName());
        parameters.put("REPORT_CONNECTION",connection);
		return parameters;
	}
	
	/**
	 * G�n�re le contenu du rapport et utilise JASPER
	 * pour g�n�rer le print
	 * @param mResources TODO
	 * @param ParameterMap
	 * @return
	 */
	public JasperPrint executer(CardexAuthenticationSubject subject, RapportVO rapportVO, ResourceBundle bundle, Locale locale) throws BusinessException, JRException{
		Connection connection = null;
		this.locale = locale;
		this.bundle = bundle;
		
		try {
			connection = DAOConnection.getInstance().getConnection();
			validerSecurite(subject);
			InputStream gabarit = obtenirGabarit();
			JRDataSource dataSource = construireDataSource(subject, rapportVO, connection);
			Map parameters = construireParametres(subject, rapportVO, connection);
			return JasperFillManager.fillReport( gabarit, parameters, dataSource);	
			
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null){
		         try {
					//connection.commit();
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
	
	
}
