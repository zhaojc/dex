/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire
 * d'un objet Billet ne sont pas respectées.
 *
 * @author mazzucr
 * @version 
 */
public class BilletBusinessRuleException
    extends BusinessRuleException {
    
    /**
     * Le numéro de contrôle d'un billet de type, achat
     * mystère, ne respecte pas le format 07-9999-999999.
     */
    public static final int NUMERO_DE_CONTROLE_INVALID = 0;

    /**
     * Constructor a BilletBusinessRuleException instance
     *
     * @see java.lang.Exception
     */
    public BilletBusinessRuleException() {
        super();
    }

    /**
     * Constructor a BilletBusinessRuleException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BilletBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a BilletBusinessRuleException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public BilletBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a BilletBusinessRuleException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BilletBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
