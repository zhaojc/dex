/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les règles d'affaire de critères de
 * recherche de société ne sont pas respectées. Il n'y a pas vraiment
 * d'exceptions pour l'instant.
 *
 * @author $Author: mdemers $
 * @version $Revision: 1.1 $, $Date: 2002/02/28 15:30:47 $
 */
public class CriteresRechercheSocieteBusinessRuleException
    extends BusinessRuleException {

    /**
     * Constructor a CriteresRechercheSocieteBusinessRuleException instance
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheSocieteBusinessRuleException() {
        super();
    }

    /**
     * Constructor a CriteresRechercheSocieteBusinessRuleException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheSocieteBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a CriteresRechercheSocieteBusinessRuleException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public CriteresRechercheSocieteBusinessRuleException(Exception e) {
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
    public CriteresRechercheSocieteBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
