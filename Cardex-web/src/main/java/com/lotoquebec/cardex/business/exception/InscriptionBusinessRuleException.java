/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire
 * d'un objet dossier ne sont pas respectées.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/04 00:25:19 $
 */
public class InscriptionBusinessRuleException
    extends BusinessRuleException {

    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int DATE_DEBUT_INVALIDE = 0;

    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int DATE_DEBUT_SUPERIEUR_DATE_FIN = 1;


    /**
     * Constructor a InscriptionBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public InscriptionBusinessRuleException() {
        super();
    }

    /**
     * Constructor a InscriptionBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public InscriptionBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a InscriptionBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public InscriptionBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a InscriptionBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public InscriptionBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
