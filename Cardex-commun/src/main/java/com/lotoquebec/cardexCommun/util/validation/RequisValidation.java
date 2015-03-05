/*
 * Created on 20-Mar-2008
 */
package com.lotoquebec.cardexCommun.util.validation;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class RequisValidation implements Validation {

	private CardexAuthenticationSubject subject = null;
	private String key = "";
	private String champ = "";
	private MessageResources mResources;
	
	public RequisValidation(CardexAuthenticationSubject subject, String key, String champ, MessageResources mResources) {
		super();
		this.subject = subject;
		this.key = key;
		this.champ = champ;
		this.mResources = mResources;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#valider(org.apache.struts.action.ActionErrors)
	 */
	public boolean valider(ActionMessages actionErrors) {
		
		if (StringUtils.isEmpty( champ )){
			String strKeyResources = mResources.getMessage( subject.getLocale(), key );			
			actionErrors.add(Globals.ERROR_KEY, new ActionMessage("errors.required", strKeyResources));
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#add(com.lotoquebec.cardex.presentation.controller.util.validation.Validation)
	 */
	public void add(Validation validation) {
	}

}
