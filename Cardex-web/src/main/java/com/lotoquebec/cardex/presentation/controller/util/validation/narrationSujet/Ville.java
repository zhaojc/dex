/*
 * Created on 17-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.presentation.controller.util.narration.SujetNarration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.validation.Validation;

/**
 * @author levassc
 */
public class Ville implements Validation {

	private SujetNarration sujetNarration = null;
	private CardexAuthenticationSubject subject;

	public Ville(CardexAuthenticationSubject subject, SujetNarration sujetNarration) throws BusinessResourceException {
		super();
		this.sujetNarration = sujetNarration;
		this.subject = subject;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#valider(org.apache.struts.action.ActionErrors)
	 */
	public boolean valider(ActionMessages actionErrors) {

		if (StringUtils.isNotEmpty( sujetNarration.getVille() )){
			
			try{
				long ville = sujetNarration.getVilleLong(subject);

				if (ville == 0){
					actionErrors.add(Globals.LOCALE_KEY, new ActionMessage("cardex_required_ville"));
					return false;
				}
			} catch (BusinessResourceException e) {
				e.printStackTrace();
				actionErrors.add(Globals.LOCALE_KEY, new ActionMessage("erreur_message"));
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#add(com.lotoquebec.cardex.presentation.controller.util.validation.Validation)
	 */
	public void add(Validation validation) {
	}

}
