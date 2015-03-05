/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.Locale;
import java.util.Map;

import com.lotoquebec.cardex.business.facade.MessageSessionFacade;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

/**
 * Le MessageBusinessDelegate offre les services d'affaires pour
 * l'internationalisation.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/18 23:54:14 $
 */
public class MessageBusinessDelegate extends BusinessDelegate {

    /**
     * Construit une instance de MessageBusinessDelegate
     */
    public MessageBusinessDelegate() {}

    /**
     * Retourne un Map de tous les messsages cardex.La clé est
     * l'identifiant du message et la valeur la description du message
     * dans la locale spécifié.
     *
     * @param locale La locale des messages a obtenir
     */
    public Map getMessages(Locale locale) throws BusinessResourceException {
        MessageSessionFacade facade = new MessageSessionFacade();
        return facade.getMessages(locale);
    }

}

