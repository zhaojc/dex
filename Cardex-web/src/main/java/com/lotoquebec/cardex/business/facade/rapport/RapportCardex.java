package com.lotoquebec.cardex.business.facade.rapport;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public abstract class RapportCardex {

	protected CardexAuthenticationSubject subject;
	
	protected abstract InputStream obtenirJasperReport();
	protected abstract Collection construireDonneeRapport(CardexAuthenticationSubject subject) throws DAOException;
	protected abstract Map construireParametres();
	
	public RapportCardex(CardexAuthenticationSubject subject) {
		super();
		this.subject = subject;
	}
	
	public JasperPrint executer() throws BusinessResourceException{
		
		try {
			InputStream jasperReport = obtenirJasperReport();
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(construireDonneeRapport(subject));
			return JasperFillManager.fillReport(jasperReport, construireParametres(), beanCollectionDataSource);
		} catch (JRException e) {
			throw (new BusinessResourceException(e));
		} catch (DAOException e) {
			throw (new BusinessResourceException(e));
		}
	}
	
}
