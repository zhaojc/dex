package com.lotoquebec.cardex.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.iris.infrastructure.services.messaging.MessageAsync;
/**
 * Bean implementation class for Enterprise Bean: CardexDiffereMDB
 */
@MessageDriven(
		name = "CardexDiffereMDB",
		activationConfig = {
				@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
				@ActivationConfigProperty(propertyName="destination", propertyValue="java:/lq/iris/gf/jms/que/messageasync"),				
				@ActivationConfigProperty(propertyName="acknowledgeMode", propertyValue="Auto-acknowledge"),
				@ActivationConfigProperty(propertyName="useJndi", propertyValue = "true")
		})
public class CardexDiffereMDB implements MessageListener {

	private final static Logger log = LoggerFactory.getLogger(CardexDiffereMDB.class);
	
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
	 * ejbCreate
	 */
	public void ejbCreate() {
	}

	/**
	 * onMessage
	 * @param message 
	 */
	public void onMessage(Message msg) {
		String jetonStatut = null;
		log.debug("Message reçu");
		
        try {
            //on récupère la structure MessageAsync du message JMS
            MessageAsync message = (MessageAsync) ((ObjectMessage) msg).getObject();
/*
            final TextMessage textMessage = (TextMessage) message;
            final String question = textMessage.getText();
  */          
            /*
            String className = actions.getProperty(message.getType());

            if (className == null)
               throw new RuntimeException("Le flux " + message.getType() +" n'est pas défini dans le fichier de configuration actionsAsync.properties");
            log.info("Préparation de la class");
            Flux flux = (Flux) Class.forName("com.cardex.ejb.flux."+msgEAI.getNomFlux()).newInstance();
            log.info("Début de l'exécution");
            flux.execute();*/
            log.debug("Fin de l'exécution");
            
        }catch (Throwable throwable){
        	ExceptionHandler.getInstance().handle(new Exception("Erreur lors de l'envoi du statut du traitement", throwable));
        }
        log.debug("Fin du flux");
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	

}
