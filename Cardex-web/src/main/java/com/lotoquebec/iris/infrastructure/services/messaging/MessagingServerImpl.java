package com.lotoquebec.iris.infrastructure.services.messaging;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import com.lotoquebec.iris.infrastructure.services.configuration.ConfigProperties;
import com.lotoquebec.iris.infrastructure.services.configuration.PathConfiguration;


/**
 * <p>Implemente le service de messagerie.
 * Elle re�oit un message d�un service et le transforme en message JMS qui sera 
 * compris par WMQI. Elle s�occupe aussi de r�soudre les r�f�rences aux ressources 
 * JMS pour �tre en mesure de publier le message.</p>  
 * 
 * @author Jerome Caron
 * @author Marco Montestruque
 *
 * @see MessagingService
 */
public class MessagingServerImpl implements MessagingService {
	/** La seule instance du service.*/
    private static MessagingServerImpl instance = null;
    private static ConfigProperties config = null;

    /**
     * <p>Construit une instance du service de messagerie.</p>
     *
     * @exception Exception
     */
    public MessagingServerImpl() throws Exception {


        if (config == null) {
            loadConfig();
        }
    }

	/**
	 * Charge la configuration copurante du service.
	 * 
	 * @throws Exception
	 */
    private void loadConfig() throws Exception {
        config = new ConfigProperties();

        config.load(PathConfiguration.class.getResourceAsStream("messaging.properties"));
    }

	/*
	 *  (non-Javadoc)
	 * @see com.iris.infrastructure.services.messaging.MessagingService#envoyerMessage(com.iris.infrastructure.services.messaging.MessageEAI)
	 */
    public void envoyerMessage(MessageEAI msg) throws Exception {
        InitialContext context = null;
        QueueConnectionFactory queueConnectionFactory = null;
		Queue queue = null;
		QueueConnection queueConnection = null;
		QueueSession queueSession = null;
		QueueSender queueSender = null;        
        
		try
		{
	        context = new InitialContext();
			String str_queueCF = config.getProperty(msg.getTypeFlux() + "_QUEUE_CONNECTION_FACTORY");
			String str_queue = config.getProperty(msg.getTypeFlux() + "_QUEUE");
	        queueConnectionFactory = (QueueConnectionFactory) context.lookup(str_queueCF);
	
	        queueConnection = queueConnectionFactory.createQueueConnection();
	        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	        queue = (Queue) context.lookup(str_queue);
	        queueSender = queueSession.createSender(queue);
	
	        TextMessage message = queueSession.createTextMessage();
			initialiserMessageJMS(message, msg);
	        message.setText(msg.toXML());
	
        	System.out.println("Le message suivant va �tre d�pos� dans la queue " +
                str_queue + " : " + msg.toXML());
	        
	        queueSender.send(message);
	
			if ( msg.getJeton() == null )
			{
				// S'il n'y avait pas de jeton, on l'initialise avec le message ID
				msg.setJeton(message.getJMSMessageID());
			}
	
        	System.out.println("Le message suivant a �t� d�pos� dans la queue " +
                str_queue + " : " + msg.toXML());
		}
		finally
		{
			// Lib�re les ressources
			libereRessourcesJMS(queueSender, queueSession, queueConnection);
		}	        
    }

	/**
	 * 
	 * @return ConfigProperties
	 */
	public ConfigProperties getConfig() {
        return config;
    }
    
	
	/*
	 *  (non-Javadoc)
	 * @see com.iris.infrastructure.services.messaging.MessagingService#envoyerStatut(com.iris.infrastructure.services.messaging.MessageEAI, javax.jms.Destination)
	 */
	public void envoyerStatut(MessageEAI msg, Destination destination) throws Exception
	{
		InitialContext context = null;
		QueueConnectionFactory queueConnectionFactory = null;
		Queue queue = null;
		QueueConnection queueConnection = null;
		QueueSession queueSession = null;
		QueueSender queueSender = null;

		if ( destination != null )
		{
			if ( destination instanceof Queue )
			{
				try
				{
					context = new InitialContext();
					String str_queueCF = config.getProperty(msg.getTypeFlux() + "_QUEUE_CONNECTION_FACTORY");
					queueConnectionFactory = (QueueConnectionFactory) context.lookup(str_queueCF);
			
					queueConnection = queueConnectionFactory.createQueueConnection();
					queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
					queue = (Queue)destination;
					queueSender = queueSession.createSender(queue);
	
					TextMessage message = queueSession.createTextMessage();
					initialiserMessageJMS(message, msg);
					message.setText(msg.toXML());
			
					// Si un jeton est pr�sent dans le message, on le copie dans le correlation ID
					if ( msg.getJeton() != null )
					{
						message.setJMSCorrelationID(msg.getJeton());
					}
					
					System.out.println("Le message de statut suivant va �tre d�pos� dans la queue " +
							queue.getQueueName() + " : " + msg.toXML());
										
					queueSender.send(message);

					System.out.println("Le message de statut suivant a �t� d�pos� dans la queue " +
							queue.getQueueName() + " : " + msg.toXML());
				}
				finally
				{
					// Lib�re les ressources
					libereRessourcesJMS(queueSender, queueSession, queueConnection);
				}
			}
			else
			{
				throw new Exception("La destination du message de statut est incorrecte");
			}
		}
		else
		{
			// La destination n'est pas sp�cifi�e
			System.out.println("Un message de statut aurait d� �tre envoy� mais la destination (queue) n'�tait pas sp�cifi�e");
		}
	}


	/**
	 * <p>Initialise le header du message JMS avec les donn�es du message EAI.</p>
	 * 
	 * @param message	le message JMS devant �tre initialis�
	 * @param msg		le message EAI contenant les informations
	 * 
	 * @throws Exception
	 */	
	private void initialiserMessageJMS(Message message, MessageEAI msg) throws Exception
	{
		// Initialise la destination pour la r�ponse
		message.setJMSReplyTo(msg.getDestinationStatut());
		
		// Initialise les champs du header JMS
		message.setStringProperty(MessageEAI.TAG_JETON, msg.getJeton());
		message.setStringProperty(MessageEAI.TAG_INITIATEUR, msg.getInitiateur());
		message.setStringProperty(MessageEAI.TAG_ID_TRANS, msg.getIdTrans());
		message.setStringProperty(MessageEAI.TAG_ESTAMPILLE, msg.getEstampille());
		message.setStringProperty(MessageEAI.TAG_ID_EMETTEUR, msg.getIdEmetteur());
		message.setStringProperty(MessageEAI.TAG_ENV_EMETTEUR, msg.getEnvEmetteur());
		message.setStringProperty(MessageEAI.TAG_ENV_RECEPTEUR, msg.getEnvRecepteur());
		message.setStringProperty(MessageEAI.TAG_NOM_FLUX, msg.getNomFlux());
		message.setStringProperty(MessageEAI.TAG_TYPE_FLUX, msg.getTypeFlux());
		message.setStringProperty(MessageEAI.TAG_VERSION_FLUX, msg.getVersionFlux());
		message.setStringProperty(MessageEAI.TAG_TYPE_SERVICE, msg.getTypeService());
		message.setStringProperty(MessageEAI.TAG_DESTINATAIRE, msg.getDestinataire());
		message.setStringProperty(MessageEAI.TAG_OBJET, msg.getObjet());
		message.setStringProperty(MessageEAI.TAG_CLE, msg.getCle());
		message.setStringProperty(MessageEAI.TAG_STATUT, msg.getStatut());
	}
	
	/**
	 * <p>Fait un close sur les ressources JMS initialis�es.</p>
	 * 
	 * @param queueSender		le QueueSender (ou null)
	 * @param queueSession		la QueueSession (ou null)
	 * @param queueConnection	la QueueConnection (ou null)
	 */
	private void libereRessourcesJMS(QueueSender queueSender, QueueSession queueSession, QueueConnection queueConnection)
	{
		if ( queueSender != null ) {
			try {
				queueSender.close();
			} catch (Exception e) {
				// Ne fait rien
			}
		}
		if ( queueSession != null ) {
			try {
				queueSession.close();
			} catch (Exception e) {
				// Ne fait rien
			}
		}
		if ( queueConnection != null ) {
			try {
				queueConnection.close();
			} catch (Exception e) {
				// Ne fait rien
			}
		}
	}
}
