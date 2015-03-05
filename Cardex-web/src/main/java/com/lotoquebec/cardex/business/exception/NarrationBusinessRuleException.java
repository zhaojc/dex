/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire d'un objet Societe
 * ne sont pas respectées.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/04/11 19:20:29 $
 */
public class NarrationBusinessRuleException
    extends BusinessRuleException {

    /**
     * Une valeur dans le champ Temps consacré est obligatoire pour les dossiers.
     */
    public static final int NARRATION_TEMPS_CONSACRE = 0;


    /**
     * Constructor a NarrationBusinessRuleException instance
     *
     * @see java.lang.Exception
     */
    public NarrationBusinessRuleException() {
        super();
    }

    /**
     * Constructor a SocieteBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public NarrationBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a NarrationBusinessRuleException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public NarrationBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a NarrationBusinessRuleException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public NarrationBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}

