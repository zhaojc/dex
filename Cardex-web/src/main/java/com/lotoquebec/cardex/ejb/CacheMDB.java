package com.lotoquebec.cardex.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.lotoquebec.cardexCommun.util.ViderCacheUtils;
/**
 * Bean implementation class for Enterprise Bean: CardexDiffereMDB
 */
@MessageDriven(
	//name = "CacheMDB",
	activationConfig = {
			@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
			@ActivationConfigProperty(propertyName="destination", propertyValue="java:/lq/crp/cdx/jms/top/cache"),				
			@ActivationConfigProperty(propertyName="acknowledgeMode", propertyValue="Auto-acknowledge"),
			@ActivationConfigProperty(propertyName="useJndi", propertyValue = "true")/*,
Ça ne fonctionne pas avant la version v6.3 de jboss			
		    @ActivationConfigProperty(propertyName="subscriptionDurability", propertyValue = "Durable"),
		    @ActivationConfigProperty(propertyName="clientId", propertyValue = "CacheMDBId"), 
		    @ActivationConfigProperty(propertyName="subscriptionName", propertyValue = "CacheMDBSub")*/				
	})
@TransactionManagement(TransactionManagementType.BEAN)
public class CacheMDB implements MessageListener {

	//private final static Logger log = LoggerFactory.getLogger(CacheMDB.class);

	/**
	 * onMessage
	 * @param message 
	 */
	public void onMessage(Message msg) {
		//TextMessage textMessage = (TextMessage) msg;
		ViderCacheUtils.viderCache();
	}

}
