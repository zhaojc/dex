/*
 * Created on 17-Mar-2008
 */
package com.lotoquebec.cardexCommun.util.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionMessages;

/**
 * @author levassc
 */
public class ValidationList implements Validation{

	private List validateurList = new ArrayList();
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#valider(org.apache.struts.action.ActionErrors)
	 */
	public boolean valider(ActionMessages actionErrors) {
		boolean valideFinal = true;
		Iterator iter = validateurList.iterator();
		
		while (iter.hasNext()) {
			Validation validation = (Validation) iter.next();
			boolean valide = validation.valider(actionErrors);
			
			if (valide == false)
				valideFinal = false;
		}
		
		return valideFinal;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#add(com.lotoquebec.cardex.presentation.controller.util.validation.Validation)
	 */
	public void add(Validation validation) {
		validateurList.add( validation ); 
	}

}
