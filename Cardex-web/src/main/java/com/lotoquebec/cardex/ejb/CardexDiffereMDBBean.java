package com.lotoquebec.cardex.ejb;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.lotoquebec.cardex.ejb.flux.Flux;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lotoquebec.iris.infrastructure.services.management.ServicesManager;
import com.lotoquebec.iris.infrastructure.services.messaging.MessageEAI;
import com.lotoquebec.iris.infrastructure.services.messaging.MessagingService;
/**
 * Bean implementation class for Enterprise Bean: CardexDiffereMDB
 */
public class CardexDiffereMDBBean
	implements
		javax.ejb.MessageDrivenBean,
		javax.jms.MessageListener {

	private final static Logger log = (Logger)LoggerCardex.getLogger(CardexDiffereMDBBean.class);
	
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;

	/**
	 * getMessageDrivenContext
	 */
	public javax.ejb.MessageDrivenContext getMessageDrivenContext() {
		return fMessageDrivenCtx;
	}

	/**
	 * setMessageDrivenContext
	 */
	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx) {
		fMessageDrivenCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() {
	}

	/**
	 * onMessage
	 * @param message 
	 */
	public void onMessage(Message msg) {
		String msgXML = null;
		MessageEAI msgEAI = null;
		String jetonStatut = null;
		log.fine("Message re�u");
        try{
			msgXML = ((TextMessage) msg).getText();

			// Si le journal de d�boguage est actif, on journalise le message re�u
			if ( msgXML != null )
			{
				log.fine("Le message suivant a �t� re�u par CardexDiffereMDBBean : " + msgXML);
			}
			
			if (msgXML == null)
				throw new Exception("Le message est null"); 

			// D�termine le jeton pour les messages de statut
			if ( (msg.getJMSCorrelationID() != null) && (msg.getJMSCorrelationID().length() > 0) )
			{
				// Si le message a �t� trait� par un flux aiguilleur, son ID est dans le champ de corr�lation
				// Voir anomalie GAN 000631
				jetonStatut = msg.getJMSCorrelationID();
			}
			else
			{
				// On utilise directement le MessageID
				jetonStatut = msg.getJMSMessageID();
			}	
			log.fine("Envoie de statut");
			// Envoie un message pour pr�venir que la demande de diff�r� a �t� re�ue
			MessageEAI msgStatut = new MessageEAI(msgXML);
			msgStatut.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
			msgStatut.setStatut(MessagingService.STATUT_RECU_WAS);
			msgStatut.setJeton(jetonStatut);
			
			ServicesManager.getMessagingServer().envoyerStatut(msgStatut, msg.getJMSReplyTo());
			
            msgEAI = new MessageEAI(msgXML);

            if (!(msgEAI.getTypeFlux().equalsIgnoreCase(MessagingService.TYPE_FLUX_DIFFERE))) {
                throw new Exception("MAUVAIS TYPE DE FLUX TRAIT� PAR LE MDB");
            }

            if (msgEAI.getNomFlux() == null) {
                throw new Exception("Le flux " + msgEAI.getNomFlux() +
                    " n'est pas d�fini dans le fichier de configuration actions.properties");
            }
            log.fine("Pr�paration de la class");
            Flux flux = (Flux) Class.forName("com.cardex.ejb.flux."+msgEAI.getNomFlux()).newInstance();
            log.fine("D�but de l'ex�cution");
            // Ex�cute l'action du diff�r�
            flux.execute();
            log.fine("Fin de l'ex�cution");
			// Si l'action s'est ex�cut�e sans erreur, on envoie un statut via un message bas� sur le m�me message EAI
			msgEAI.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
			msgEAI.setStatut(MessagingService.STATUT_COMPLETE_WAS);
			msgEAI.setJeton(jetonStatut);
			log.fine("envoie de message de statut");
			ServicesManager.getMessagingServer().envoyerStatut(msgEAI, msg.getJMSReplyTo());
			
			CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			CourrielAction.envoyerCourrielDestinataire(subject, "Le flux "+msgEAI.getNomFlux()+" s'est termin� avec succ�s.", "", GlobalConstants.TypesIntervention.COURRIEL_EXCEPTION, "A");
			
        } catch (Throwable ex) {
            ExceptionHandler.getInstance().handle(ex);
			
			try {
				StringBuilder stringBuilder = new StringBuilder(0);
				stringBuilder.setLength(0);
				stringBuilder.append(MessagingService.STATUT_ERREUR_WAS);
				stringBuilder.append(",");
				stringBuilder.append(ex.getMessage());
				String statut = stringBuilder.toString();

				// On envoie un statut via un message
				if (msgEAI != null) {
					// On r�utilise le m�me message EAI
					msgEAI.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
					msgEAI.setStatut(statut);
					msgEAI.setJeton(this.obtenirJetonStatut(msg));
					ServicesManager.getMessagingServer().envoyerStatut(msgEAI, msg.getJMSReplyTo());
				} else if (msgXML != null) {
					// On tente de cr�er un nouveau message EAI � partir du m�me XML
					MessageEAI msgStatut = new MessageEAI(msgXML);
					msgStatut.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
					msgStatut.setStatut(statut);
					msgStatut.setJeton(this.obtenirJetonStatut(msg));
					ServicesManager.getMessagingServer().envoyerStatut(msgStatut, msg.getJMSReplyTo());
				} else {
					ExceptionHandler.getInstance().handle(new Exception("Impossible d'envoyer un statut du traitement"));
				}
				CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
				CourrielAction.envoyerCourrielDestinataire(subject, "Le flux "+msgEAI.getNomFlux()+" s'est termin� en ERREUR.", "", GlobalConstants.TypesIntervention.COURRIEL_EXCEPTION, "A");
				
			} catch (Throwable throwable2) {
				ExceptionHandler.getInstance().handle(new Exception("Erreur lors de l'envoi du statut du traitement", throwable2));
			}
			
     	}		
        log.fine("Fin du flux");
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/*private void envoyerMessageStatut(MessageEAI messageStatut, Destination destination) throws Exception {
		
		InitialContext context = null;
		ConnectionFactory connectionFactory = null;
		Queue queue = null;
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;

		IServiceJournalisation journalisation = ServiceJournalisation.obtenirInstance();

		if (destination != null) {
			if (destination instanceof Queue) {
				try {
					context = new InitialContext();
					connectionFactory = (ConnectionFactory)context.lookup("java:/RemoteJmsXA");
					connection = connectionFactory.createConnection();
					session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
					queue = (Queue) destination;
					producer = session.createProducer(queue);

					// Compl�te les champs du message EAI avec le contexte courant
					completerMessageEAI(messageStatut);

					TextMessage message = session.createTextMessage();
					initialiserMessageJMS(message, messageStatut);
					message.setText(messageStatut.toXML());

					// Si un jeton est pr�sent dans le message, on le copie dans
					// le correlation ID
					if (messageStatut.getJeton() != null) {
						message.setJMSCorrelationID(messageStatut.getJeton());
					}

					producer.send(message);
				} finally {
					// Lib�re les ressources
					libereRessourcesJMS(producer, session, connection);
				}
			} else {
				throw new Exception("La destination du message de statut est incorrecte");
			}
		} else {
			// La destination n'est pas sp�cifi�e
			journalisation.trace("Un message de statut aurait d� �tre envoy� mais la destination (queue) n'�tait pas sp�cifi�e");
		}
	}*/

	
	/**
	 * D�termine le jeton pour le message de statut.
	 * 
	 * @param message le message JMS
	 * @return le jeton du message
	 * 
	 * @throws Exception
	 */
	private String obtenirJetonStatut(Message message) {
		
		String jetonStatut = null;

		try {
			if (message.getJMSCorrelationID() != null && message.getJMSCorrelationID().length() > 0) {
				// Si le message a �t� trait� par un flux aiguilleur, son ID est
				// dans le champ de corr�lation
				jetonStatut = message.getJMSCorrelationID();
			} else {
				// On utilise directement le MessageID
				jetonStatut = message.getJMSMessageID();
			}
		} catch (JMSException exception) {
			throw new UndeclaredThrowableException(exception);
		}

		return jetonStatut;
	}

}
