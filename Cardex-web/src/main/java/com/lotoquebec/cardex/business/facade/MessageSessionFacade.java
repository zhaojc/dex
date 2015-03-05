/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.facade;

import java.util.Locale;
import java.util.Map;

import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.MessageDAO;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Le MessageSessionFacade offre les
 * services d'affaires pour
 * l'internationalisation.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.3 $, $Date: 2002/02/18 23:54:17 $
 */
public class MessageSessionFacade {

    /**
     * Construit une instance de  MessageSessionFacade
     */
    public MessageSessionFacade() {}

    /**
     * Retourne un Map de tous les messsages cardex.La clé est
     * l'identifiant du message et la valeur la description du message
     * dans la locale spécifié.
     *
     * @param locale La locale des messages a obtenir
     */
    public Map getMessages(Locale locale)
            throws BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getMessageDAO().getMessages(locale);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

}

