/*
 * Created on 17-Mar-2008
 */
package com.lotoquebec.cardexCommun.util.validation;

import org.apache.struts.action.ActionMessages;

/**
 * @author levassc
 */
public interface Validation {

	/**
	 * Permet de lancer une validation X
	 * @param actionErrors
	 * @return
	 */
	public boolean valider(ActionMessages actionErrors);

	/**
	 * Ajout d'une validation supplémentaire
	 * @param validation
	 */
	public void add(Validation validation);
}
