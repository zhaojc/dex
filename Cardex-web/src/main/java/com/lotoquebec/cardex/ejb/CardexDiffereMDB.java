package com.lotoquebec.cardex.ejb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.lotoquebec.cardex.ejb.flux.Flux;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
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

	private final static Logger log = (Logger)LoggerCardex.getLogger(CardexDiffereMDB.class);
	
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
		log.fine("Message reçu");
		
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
            log.fine("Préparation de la class");
            Flux flux = (Flux) Class.forName("com.cardex.ejb.flux."+msgEAI.getNomFlux()).newInstance();
            log.fine("Début de l'exécution");
            flux.execute();*/
            log.fine("Fin de l'exécution");
            
        }catch (Throwable throwable){
        	ExceptionHandler.getInstance().handle(new Exception("Erreur lors de l'envoi du statut du traitement", throwable));
        }
        log.fine("Fin du flux");
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	

}
