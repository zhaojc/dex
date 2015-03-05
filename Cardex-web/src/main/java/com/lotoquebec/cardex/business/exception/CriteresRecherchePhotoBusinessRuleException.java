/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire
 * d'un objet dossier ne sont pas respectées.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/04 20:40:48 $
 */
public class CriteresRecherchePhotoBusinessRuleException
    extends BusinessRuleException {


    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int DATE_DEBUT_SUPERIEUR_DATE_FIN = 0;

    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int PAS_AU_MOINS_UNE_CASE_COCHEE = 1;

    /**
     * Constructor a PhotoBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public CriteresRecherchePhotoBusinessRuleException() {
        super();
    }

    /**
     * Constructor a PhotoBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRecherchePhotoBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a PhotoBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public CriteresRecherchePhotoBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a PhotoBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRecherchePhotoBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}

