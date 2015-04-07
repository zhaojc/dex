/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.businessRules;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.rule.AdresseBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.BilletBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.CriteresRechercheDossierBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.CriteresRechercheNarrationBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.CriteresRecherchePhotoBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.CriteresRechercheSocieteBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.CriteresRechercheSujetBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.DossierBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.InscriptionBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.NarrationBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.RegroupementBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.SocieteBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.SousCategoriesBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.SuiviBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.SujetBusinessRuleSet;
import com.lotoquebec.cardex.business.rule.UrgenceBusinessRuleSet;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * The BusinessRulesValidator is use to validate business rule that are constrains
 * for some aspect of the business. It is intended to assert business structure,
 * or to control or influence the behavior of the business.
 *
 * @see
 * @author $Autor: $
 * @version $Revision: 1.4 $, $Date: 2002/02/08 17:37:19 $
 */
@Deprecated
public class BusinessRulesValidator {

    /**
     * Logger instance.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Business rules
     */
    private static HashMap                rules = new HashMap();

    /**
     * Singleton instance
     */
    private static BusinessRulesValidator instance;

    /**
     * Create an instance of BusinessRulesValidator class.
     *
     * @throws BusinessResourceException If is not possible to
     * create a BusinessRulesValidator
     */
    public BusinessRulesValidator() throws BusinessResourceException {
        loadBusinessRules();
    }

    /**
     * Return instance of BusinessRulesValidator class.
     *
     * @return Singleton instance for the BusinessRulesValidator
     *
     * @throws BusinessResourceException If is not possible to
     * create a BusinessRulesValidator
     */
    public static BusinessRulesValidator getInstance()
            throws BusinessResourceException {
        if (BusinessRulesValidator.instance == null) {
            BusinessRulesValidator.instance =
                new BusinessRulesValidator();
        }

        return BusinessRulesValidator.instance;
    }

    /**
     * C'est l'ancienne façon de faire des gestions des règles d'affaires.
     */
    private void loadBusinessRules()
            throws BusinessResourceException {
        log.debug("loadBusinessRules()");

        rules.put("com.lotoquebec.cardex.business.rule.CriteresRechercheDossierBusinessRuleSet", new CriteresRechercheDossierBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.CriteresRechercheNarrationBusinessRuleSet", new CriteresRechercheNarrationBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.CriteresRecherchePhotoBusinessRuleSet", new CriteresRecherchePhotoBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.CriteresRechercheSocieteBusinessRuleSet", new CriteresRechercheSocieteBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.CriteresRechercheSujetBusinessRuleSet", new CriteresRechercheSujetBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.AdresseBusinessRuleSet", new AdresseBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.DossierBusinessRuleSet", new DossierBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.NarrationBusinessRuleSet", new NarrationBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.InscriptionBusinessRuleSet", new InscriptionBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.SocieteBusinessRuleSet", new SocieteBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.SuiviBusinessRuleSet", new SuiviBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.SujetBusinessRuleSet", new SujetBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.SousCategoriesBusinessRuleSet", new SousCategoriesBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.RegroupementBusinessRuleSet", new RegroupementBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.BilletBusinessRuleSet", new BilletBusinessRuleSet());
        rules.put("com.lotoquebec.cardex.business.rule.UrgenceBusinessRuleSet", new UrgenceBusinessRuleSet());

    }

    /**
     * Check business rules for the specified
     * business object.
     *
     * @throws BusinessRuleException If the business object is not conform
     * to the business rules associated with it.
     */
    public void checkBusinessRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.debug("checkBusinessRules('"
                  + businessObject.getClass().getName() + "')");

        Class[] interfaces =
            businessObject.getClass().getInterfaces();

        for (int i = 0; i < interfaces.length; i++) {
            BusinessRuleSet businessRuleSet =
                (BusinessRuleSet) rules.get(interfaces[i].getName()
                                                 + ".rules");

            if (businessRuleSet != null) {
                businessRuleSet.checkRules(subject, businessObject);
            } else {
//                log.warn("No business rule set define for '"
//                         + interfaces[i].getName() + "'");
            }
        }
    }
    
}

