package com.lotoquebec.iris.infrastructure.services.messaging;

import javax.jms.Destination;

import com.lotoquebec.iris.infrastructure.services.configuration.ConfigProperties;
import com.lotoquebec.iris.infrastructure.services.management.ServiceComponent;


/**
 * <p>Cette classe implemente le service de messagerie.
 * Elle re�oit un message d�un service et le transforme en message JMS qui sera 
 * compris par WMQI. Elle s�occupe aussi de r�soudre les r�f�rences aux ressources 
 * JMS pour �tre en mesure de publier le message.</p> 
 * 
 * @author Jerome Caron
 * @author Marco Montestruque
 *
 * @see MessagingService
 */
public class MessagingServer extends ServiceComponent
    implements MessagingService {
    /** La seule instance du service.*/
    private static MessagingService instance = null;

    /** Garde la seule instance de la classe d�l�gu�e.*/
    private MessagingService delegate = null;

    /**
     * <p>Construit une instance du service de messagerie.
     * Le chargement des propri�t�s est accompli par <code>ServiceComponent</code>.</p>
     *
     * @param config Configuration courante des propri�t�s.
     * @exception Exception 
     */
    private MessagingServer(ConfigProperties config) throws Exception {
        super(config);
    }

	/**
     * <p>Retourne la seule instance du service.</p> 
     * 
	 * @param ConfigProperties config
	 * @return MessagingService 
	 * @throws Exception
	 */
    public static MessagingService getInstance(ConfigProperties config)
        throws Exception {
        if (instance == null) {
            instance = new MessagingServer(config);
        }

        return instance;
    }

    /**
     * <p>R� achemine la demande d'envoi vers la classe d�l�gu�e.</p>
     * 
     * @param MessageEAI msg
     *   
     * @see com.iris.infrastructure.services.messaging.MessagingService#envoyerMessage(com.iris.infrastructure.services.messaging.MessageEAI)
     */
    public void envoyerMessage(MessageEAI msg) throws Exception {
        getDelegate().envoyerMessage(msg);
    }

	/**
	 * 
	 * @return MessageService 
	 * @throws Exception
	 */
    private MessagingService getDelegate() throws Exception {
        if (delegate == null) {
            delegate = new MessagingServerImpl();
        }

        return delegate;
    }

    
	/**
	 * <p>R� achemine la demande d'envoi vers la classe d�l�gu�e.</p>
	 * 
	 * @param MessageEAI msg
	 *   
	 * @see com.iris.infrastructure.services.messaging.MessagingService#envoyerStatut(MessageEAI, Destination)
	 */
	public void envoyerStatut(MessageEAI msg, Destination destination) throws Exception {
		getDelegate().envoyerStatut(msg, destination);
	}    
}
