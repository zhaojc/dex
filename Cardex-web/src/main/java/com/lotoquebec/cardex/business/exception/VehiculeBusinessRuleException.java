/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les r�gles d'affaire
 * d'un objet Vehicule ne sont pas respect�es.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/23 21:09:22 $
 */
public class VehiculeBusinessRuleException
    extends BusinessRuleException {

    /**
     * Un Vehicule ne peut pas �tre reli� � lui-m�me.
     */
    public static final int VEHICULE_RELIE_A_LUI_MEME = 0;

    /**
     * Un Vehicule ne peut pas �tre reli� plus d'une fois � un m�me Vehicule.
     */
    public static final int VEHICULE_RELIE_PLUS_UNE_FOIS = 1;

    /**
     * Un dossier avec inscription doit �tre li�
     * � au moins une inscription.
     */
    public static final int MOT_DE_PASSE_INVALID = 2;

    /**
     * Constructor a VehiculeBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public VehiculeBusinessRuleException() {
        super();
    }

    /**
     * Constructor a VehiculeBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public VehiculeBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a VehiculeBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public VehiculeBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a VehiculeBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public VehiculeBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
