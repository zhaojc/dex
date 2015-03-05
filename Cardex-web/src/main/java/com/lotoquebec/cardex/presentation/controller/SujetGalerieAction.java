package com.lotoquebec.cardex.presentation.controller;

import java.util.Collection;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

public class SujetGalerieAction extends SujetAction{

	@Override
	protected Collection findLiensDossier(CardexAuthenticationSubject subject,Sujet sujet, SujetBusinessDelegate delegate) throws BusinessException, BusinessResourceException {
		return delegate.findLiensDossierGalerie(subject, sujet);
	}

	
	
}
