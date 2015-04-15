package com.lotoquebec.cardex.presentation.model.form.rapport.sujet;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;


public abstract class SujetRapportForm extends RapportForm{


	public SujetRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
		String cle = (String) request.getParameter("cle");
		String site = (String) request.getParameter("site");
		Sujet sujet = new SujetVO(Long.valueOf(cle),Long.valueOf(site));
		sujet = new SujetBusinessDelegate().find(subject, sujet);
        ResourceBundle bundle = ResourceBundle.getBundle("resources.application", locale);
		return genererRapport.executer(subject, sujet, bundle, locale);
	}

	
}
