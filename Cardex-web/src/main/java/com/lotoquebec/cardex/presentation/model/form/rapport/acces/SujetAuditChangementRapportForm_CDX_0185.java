package com.lotoquebec.cardex.presentation.model.form.rapport.acces;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.generateurRapport.acces.SujetAuditChangementGenerateurRapport_CDX_0185;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;


public class SujetAuditChangementRapportForm_CDX_0185 extends RapportForm{

	private static final long serialVersionUID = 3267452903677436281L;

	public SujetAuditChangementRapportForm_CDX_0185() {
		super( new SujetAuditChangementGenerateurRapport_CDX_0185() );
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
		String cle = (String) request.getParameter("cle");
		String site = (String) request.getParameter("site");
		Sujet sujet = new SujetVO(Long.valueOf(cle),Long.valueOf(site));
		sujet = new SujetBusinessDelegate().find(subject, sujet);
		return genererRapport.executer(subject, sujet, locale);
	}

	
}
