package com.lotoquebec.cardex.presentation.model.form.rapport;

import java.io.Serializable;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;

public abstract class RapportForm extends ValidatorForm implements Serializable{

	private static final long serialVersionUID = -9025528633097478133L;
	protected GenererRapport genererRapport = null;
	private boolean lancerRapport = false;

	public RapportForm(GenererRapport genererRapport){
		this.genererRapport = genererRapport;
	}
	
	public RapportVO obtenirRapportVO(){
		return genererRapport.construireNouveauRapportVO();
	}
	
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
        RapportVO rapportVO = obtenirRapportVO();
		return genererRapport.executer(subject, rapportVO, locale);
	}
	
	public boolean isLancerRapport() {
		return lancerRapport;
	}

	public void setLancerRapport(boolean lancerRapport) {
		this.lancerRapport = lancerRapport;
	}

	public GenererRapport getGenererRapport() {
		return genererRapport;
	}

	
}
