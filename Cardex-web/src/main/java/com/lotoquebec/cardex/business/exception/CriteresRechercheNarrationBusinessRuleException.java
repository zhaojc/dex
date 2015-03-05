/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.ChainedException;

/**
 * Cette exception survient lorsque les règles d'affaire
 * d'un objet dossier ne sont pas respectées.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/11 15:05:48 $
 */
public class CriteresRechercheNarrationBusinessRuleException
    extends BusinessRuleException {


    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int DATE_DEBUT_SUPERIEUR_DATE_FIN = 0;

    /**
     * Constructor a NarrationBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheNarrationBusinessRuleException() {
        super();
    }

    /**
     * Constructor a NarrationBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheNarrationBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a NarrationBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheNarrationBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a NarrationBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheNarrationBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}

