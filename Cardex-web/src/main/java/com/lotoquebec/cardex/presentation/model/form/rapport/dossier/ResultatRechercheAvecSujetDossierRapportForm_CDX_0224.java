package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.generateurRapport.dossier.ResultatRechercheAvecSujetDossierGenerateurRapport_CDX_0224;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;

public class ResultatRechercheAvecSujetDossierRapportForm_CDX_0224 extends RapportForm{

	private static final long serialVersionUID = -8749945083085554538L;

	public ResultatRechercheAvecSujetDossierRapportForm_CDX_0224() {
		super(null);
	}

	@Override
	public JasperPrint genererRapport(CardexAuthenticationSubject subject, HttpServletRequest request, Locale locale) throws BusinessException, JRException{
		CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) request.getSession().getAttribute("rechercheDossier");
		return (new ResultatRechercheAvecSujetDossierGenerateurRapport_CDX_0224()).executer(subject, criteresRechercheDossierForm, locale);
	}

	
}
