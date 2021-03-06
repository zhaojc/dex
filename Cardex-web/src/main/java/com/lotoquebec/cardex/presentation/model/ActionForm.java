package com.lotoquebec.cardex.presentation.model;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * <p>This class extends <strong>ActionForm</strong> and provides
 * basic field validation based on an XML file. This class offer also
 * logging services.</p>
 *
 * <ul><li>See /WEB-INF/validation.xml for validation rules.</li></ul>
 *
 * @author Martin Libersan
*/

public class ActionForm extends ValidatorForm {

    /**
     * Log manager instance.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
       super.reset(mapping, request);
    }

    

}