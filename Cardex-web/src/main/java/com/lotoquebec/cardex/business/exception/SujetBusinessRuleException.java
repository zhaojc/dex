/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les r�gles d'affaire
 * d'un objet Sujet ne sont pas respect�es.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/04/11 19:20:29 $
 */
public class SujetBusinessRuleException
    extends BusinessRuleException {

    /**
     * Un Sujet ne peut pas �tre reli� � lui-m�me.
     */
    public static final int SUJET_RELIE_A_LUI_MEME = 0;

    /**
     * Un Sujet ne peut pas �tre reli� plus d'une fois � un m�me Sujet.
     */
    public static final int SUJET_RELIE_PLUS_UNE_FOIS = 1;

    /**
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     */
    public static final int DATE_NAISSANCE_INVALIDE = 2;

    /**
     * Un dossier avec inscription doit �tre li�
     * � au moins une inscription.
     */
    public static final int MOT_DE_PASSE_INVALID = 3;

    /**
     * Constructor a SujetBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public SujetBusinessRuleException() {
        super();
    }

    /**
     * Constructor a SujetBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public SujetBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a SujetBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public SujetBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a SujetBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public SujetBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
