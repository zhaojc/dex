package com.lotoquebec.cardex.ejb;

import java.lang.reflect.UndeclaredThrowableException;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iris.infrastructure.services.messaging.MessageEAI;
import com.iris.infrastructure.services.messaging.MessagingService;
import com.lotoquebec.cardex.ejb.flux.Flux;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.util.CourrielAction;
/**
 * Bean implementation class for Enterprise Bean: CardexDiffereMDB
 */
@MessageDriven(
		//name = "CardexDiffereMDB",
		activationConfig = {
				@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
				@ActivationConfigProperty(propertyName="destination", propertyValue="java:/lq/crp/cdx/jms/que/differe"),				
				@ActivationConfigProperty(propertyName="acknowledgeMode", propertyValue="Auto-acknowledge"),
				@ActivationConfigProperty(propertyName="useJndi", propertyValue = "true")
		})
@TransactionManagement(TransactionManagementType.BEAN)
public class CardexDiffereMDB implements MessageListener {

	private final static Logger log = LoggerFactory.getLogger(CardexDiffereMDB.class);
	
    @Resource(mappedName = "java:/lq/crp/cdx/jms/cf/amq")
    private ConnectionFactory connectionFactory;
	
    @Resource(mappedName = "java:/lq/crp/cdx/jms/que/statut")
    private Queue statutQueue;

	
	private MessageDrivenContext fMessageDrivenCtx;

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
	 * onMessage
	 * @param message 
	 */
	public void onMessage(Message msg) {
		String msgXML = null;
		MessageEAI msgEAI = null;
		String jetonStatut = null;
		log.info("Message reçu");
        
		try{
			msgXML = ((TextMessage) msg).getText();

			// Si le journal de déboguage est actif, on journalise le message reçu
			if ( msgXML != null ){
				log.info("Le message suivant a été reçu par CardexDiffereMDBBean : " + msgXML);
			}
			
			if (msgXML == null)
				throw new Exception("Le message est null"); 

			// Détermine le jeton pour les messages de statut
			if ( (msg.getJMSCorrelationID() != null) && (msg.getJMSCorrelationID().length() > 0) ){
				// Si le message a été traité par un flux aiguilleur, son ID est dans le champ de corrélation
				// Voir anomalie GAN 000631
				jetonStatut = msg.getJMSCorrelationID();
			}else{
				// On utilise directement le MessageID
				jetonStatut = msg.getJMSMessageID();
			}	
			log.info("Envoie de statut");
			// Envoie un message pour prévenir que la demande de différé a été reçue
			MessageEAI msgStatut = new MessageEAI(msgXML);
			msgStatut.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
			msgStatut.setStatut(MessagingService.STATUT_RECU_WAS);
			msgStatut.setJeton(jetonStatut);
			
			envoyerStatut(msgStatut);
			
            msgEAI = new MessageEAI(msgXML);

            if (!(msgEAI.getTypeFlux().equalsIgnoreCase(MessagingService.TYPE_FLUX_DIFFERE))) {
                throw new Exception("MAUVAIS TYPE DE FLUX TRAITÉ PAR LE MDB");
            }

            if (msgEAI.getNomFlux() == null) {
                throw new Exception("Le flux " + msgEAI.getNomFlux() +
                    " n'est pas défini dans le fichier de configuration actions.properties");
            }
            log.info("Préparation de la class com.lotoquebec.cardex.ejb.flux."+msgEAI.getNomFlux());
            Flux flux = (Flux) Class.forName("com.lotoquebec.cardex.ejb.flux."+msgEAI.getNomFlux()).newInstance();
            log.info("Début de l'exécution");
            // Exécute l'action du différé
            flux.execute();
            log.info("Fin de l'exécution");
			// Si l'action s'est exécutée sans erreur, on envoie un statut via un message basé sur le même message EAI
			msgEAI.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
			msgEAI.setStatut(MessagingService.STATUT_COMPLETE_WAS);
			msgEAI.setJeton(jetonStatut);
			log.info("envoie de message de statut");
			envoyerStatut(msgEAI);
			
			CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			CourrielAction.envoyerCourrielDestinataire(subject, "Le flux "+msgEAI.getNomFlux()+" s'est terminé avec succès.", "", GlobalConstants.TypesIntervention.COURRIEL_EXCEPTION, "A");
			
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
					// On réutilise le même message EAI
					msgEAI.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
					msgEAI.setStatut(statut);
					msgEAI.setJeton(this.obtenirJetonStatut(msg));
					envoyerStatut(msgEAI);
				} else if (msgXML != null) {
					// On tente de créer un nouveau message EAI à partir du même XML
					MessageEAI msgStatut = new MessageEAI(msgXML);
					msgStatut.setTypeFlux(MessagingService.TYPE_FLUX_STATUT);
					msgStatut.setStatut(statut);
					msgStatut.setJeton(this.obtenirJetonStatut(msg));
					envoyerStatut(msgStatut);
				} else {
					ExceptionHandler.getInstance().handle(new Exception("Impossible d'envoyer un statut du traitement"));
				}
				CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
				CourrielAction.envoyerCourrielDestinataire(subject, "Le flux "+msgEAI.getNomFlux()+" s'est terminé en ERREUR.", "", GlobalConstants.TypesIntervention.COURRIEL_EXCEPTION, "A");
				
			} catch (Throwable throwable2) {
				ExceptionHandler.getInstance().handle(new Exception("Erreur lors de l'envoi du statut du traitement", throwable2));
			}
			
     	}		
        log.info("Fin du flux");
	}

	private void envoyerStatut(MessageEAI msg) throws JMSException{
		Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer(statutQueue);
        connection.start();
        TextMessage message = session.createTextMessage();
        message.setText(msg.toXML());
        messageProducer.send(message);
	}
	
	private String obtenirJetonStatut(Message message) {
		
		String jetonStatut = null;

		try {
			if (message.getJMSCorrelationID() != null && message.getJMSCorrelationID().length() > 0) {
				// Si le message a été traité par un flux aiguilleur, son ID est
				// dans le champ de corrélation
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
