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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;

public abstract class GenererRapport {

	protected Locale locale;
	protected ResourceBundle bundle;
	
	/**
	 * Cette m�thode valide si l'utilisateur � acc�s au rapport.
	 * @param subject
	 */
	protected abstract void validerSecurite(CardexAuthenticationSubject subject);
	
	/**
	 * Cette m�thode retourne le stream du .jrxml
	 * @return
	 */
	protected abstract InputStream obtenirGabarit();
	
	/**
	 * Retourne la source de donn�e qui fournie le rapport
	 * @param subject TODO
	 * @param vo
	 * @return
	 * @throws BusinessResourceException
	 * @throws BusinessException
	 */
	protected abstract JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException;
	
	public CritereRapportVO construireNouveauRapportVO() {
		return new CritereRapportVO();
	}
	
	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters = new HashMap<String,Object>();
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
	public JasperPrint executer(CardexAuthenticationSubject subject, VO vo, ResourceBundle bundle, Locale locale) throws BusinessException, JRException{
		Connection connection = null;
		this.locale = locale;
		this.bundle = bundle;
		
		try {
			connection = DAOConnection.getInstance().getConnection();
			validerSecurite(subject);
			InputStream gabarit = obtenirGabarit();
			JRDataSource dataSource = construireDataSource(subject, vo, connection);
			Map<String,Object> parameters = construireParametres(subject, vo, connection);
			
			if (gabarit == null)
				throw new IllegalArgumentException("Le gabarit du rapport ne peut pas être null");
			
			JasperReport jasperReport = JasperCompileManager.compileReport(gabarit);
			return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			
			if (connection != null){
		         try {
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
