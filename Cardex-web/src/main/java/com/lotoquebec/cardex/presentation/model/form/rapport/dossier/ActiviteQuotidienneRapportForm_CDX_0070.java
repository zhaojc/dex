package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.generateurRapport.dossier.raq.GlobalRAQDossierGenerateurRapport_CDX_0070;
import com.lotoquebec.cardex.generateurRapport.entite.ActiviteQuotidienneRapportVO;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.LongUtils;


public class ActiviteQuotidienneRapportForm_CDX_0070 extends RapportForm{

	private static final long serialVersionUID = 3267452903677436281L;

	public ActiviteQuotidienneRapportForm_CDX_0070() {
		super( new GlobalRAQDossierGenerateurRapport_CDX_0070() );
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
    	String dateDebutString = (String)request.getParameter("DATE_DEBUT");
    	String dateFinString = (String)request.getParameter("DATE_FIN");
    	String siteString = (String)request.getParameter("SITE");

    	ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO  = new ActiviteQuotidienneRapportVO();

    	try {
    		activiteQuotidienneRapportVO.setDateDebutDu( DateFormat.parse(dateDebutString) );
			activiteQuotidienneRapportVO.setDateDebutAu( DateFormat.parse(dateFinString) );
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	activiteQuotidienneRapportVO.setSite(LongUtils.valueOf(siteString));
		return genererRapport.executer(subject, activiteQuotidienneRapportVO, locale);
	}

	
}
