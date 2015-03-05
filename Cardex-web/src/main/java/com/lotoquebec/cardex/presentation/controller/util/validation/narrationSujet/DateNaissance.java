/*
 * Created on 17-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardexCommun.util.validation.Validation;

/**
 * @author levassc
 */
public class DateNaissance extends DateValidation implements Validation{

	public DateNaissance(String dateNaissance) {
		super(dateNaissance);
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#valider(org.apache.struts.action.ActionErrors)
	 */
	public boolean valider(ActionMessages actionErrors) {

		if (valider() == false){
			actionErrors.add(Globals.ERROR_KEY, new ActionMessage("cardex_required_date_naissance"));
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
