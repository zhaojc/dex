package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;


public abstract class DossierRapportForm extends RapportForm{

	public DossierRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
		String cle = (String) request.getParameter("cle");
		String site = (String) request.getParameter("site");
		Dossier dossier = new DossierVO(Long.valueOf(cle),Long.valueOf(site));
		dossier = new DossierBusinessDelegate().find(subject, dossier);
		return genererRapport.executer(subject, dossier, locale);
	}

	
}
