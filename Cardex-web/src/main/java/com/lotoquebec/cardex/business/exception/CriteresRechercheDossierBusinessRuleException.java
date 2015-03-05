/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire
 * d'un objet dossier ne sont pas respectées.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/04/03 15:13:33 $
 */
public class CriteresRechercheDossierBusinessRuleException
    extends BusinessRuleException {


    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int DATE_DEBUT_SUPERIEUR_DATE_FIN = 0;

    /**
     * Dates de début inférieures ou égales aux dates de fin.
     */
    public static final int PAS_DE_CRITERES_SAISIS_EN_PLUS_DES_VALEURS_PAR_DEFAULT = 1;

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheDossierBusinessRuleException() {
        super();
    }

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheDossierBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheDossierBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheDossierBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}

