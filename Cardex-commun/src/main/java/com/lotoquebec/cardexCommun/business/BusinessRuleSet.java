/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.business;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Set of business rule that are statement that defines constrains on some aspect
 * of the business. It is intended to assert business structure, or to control
 * or influence the behavior of a specific business object.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/02/08 17:37:19 $
 */
public interface BusinessRuleSet {

    /**
     * Validate business rule that are constrains
     * for some aspect of the business. It is intended to assert
     * business structure, or to control or influence the behavior
     * of the business object specified.
     *
     *
     * @param businessObject Business object to validate again business
     * rules
     *
     * @throws BusinessRuleException If a business rule is not respected
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException; 
}

