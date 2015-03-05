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
public class SocieteBusinessRuleException
    extends BusinessRuleException {

    /**
     * Un Societe ne peut pas être relié à lui-même.
     */
    public static final int SOCIETE_RELIEE_A_ELLE_MEME = 0;

    /**
     * Un Societe ne peut pas être relié plus d'une fois à un même Societe.
     */
    public static final int SOCIETE_RELIEE_PLUS_UNE_FOIS = 1;

    /**
     * Un Societe ne peut pas être relié plus d'une fois à un même Societe.
     */
    public static final int SOCIETE_DATE_CREATION = 2;

    /**
     * Un dossier avec inscription doit être lié
     * à au moins une inscription.
     */
    public static final int MOT_DE_PASSE_INVALID = 3;

    /**
     * Constructor a SocieteBusinessRuleException instance
     *
     * @see java.lang.Exception
     */
    public SocieteBusinessRuleException() {
        super();
    }

    /**
     * Constructor a SocieteBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public SocieteBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a SocieteBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public SocieteBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a SocieteBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public SocieteBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}

