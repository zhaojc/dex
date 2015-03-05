/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire
 * d'un objet dossier ne sont pas respectées.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/04 20:40:52 $
 */
public class SuiviBusinessRuleException
    extends BusinessRuleException {

    /**
     * Date complétée invalide.
     */
    public static final int DATE_COMPLETE_INVALIDE = 0;

    /**
     * Date prévue inférieure à la date de création.
     */
    public static final int DATE_PREVUE_INVALIDE = 1;


    /**
     * Constructor a SuiviBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public SuiviBusinessRuleException() {
        super();
    }

    /**
     * Constructor a SuiviBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public SuiviBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a SuiviBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public SuiviBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a SuiviBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public SuiviBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
