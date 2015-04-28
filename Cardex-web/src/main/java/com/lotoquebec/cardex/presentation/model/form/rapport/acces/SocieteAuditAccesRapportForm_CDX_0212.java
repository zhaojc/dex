package com.lotoquebec.cardex.presentation.model.form.rapport.acces;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.generateurRapport.acces.SocieteAuditAccesGenerateurRapport_CDX_0212;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;


public class SocieteAuditAccesRapportForm_CDX_0212 extends RapportForm{

	private static final long serialVersionUID = 2117658154803567639L;

	public SocieteAuditAccesRapportForm_CDX_0212() {
		super( new SocieteAuditAccesGenerateurRapport_CDX_0212() );
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
		String cle = (String) request.getParameter("cle");
		String site = (String) request.getParameter("site");
		Societe societe = new SocieteVO(Long.valueOf(cle),Long.valueOf(site));
		societe = new SocieteBusinessDelegate().find(subject, societe);
		return genererRapport.executer(subject, societe, locale);
	}

	
}
