package com.lotoquebec.cardexCommun.util;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.securite.RolesCacheSecuriteCache;

@Named
public class ViderCacheUtils {

	private final static Logger log = LoggerFactory.getLogger(ViderCacheUtils.class);
	private static ViderCacheUtils viderCacheUtils = null;
	
    @Resource(mappedName = "java:/lq/crp/cdx/jms/cf/amq")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/lq/crp/cdx/jms/top/cache")
    private Topic topic;

	public ViderCacheUtils() {
		super();
	}
	/*
	public static ViderCacheUtils getInstance() {
		
		if (viderCacheUtils == null)
			viderCacheUtils = new ViderCacheUtils();
		return viderCacheUtils;
	}*/

	public void assignerViderCaches() {
		log.warn("Assigner vider la cache");
		envoyerMessageViderCache();
	}

	public static void viderCache(Class<? extends CleListe>...classes){
		viderCache();
		//ListeCache.getInstance().vider(classes);
	}
	
	public static void viderCache(){
		log.warn("Vider la cache!");
		ListeCache.getInstance().vider();
		RolesCacheSecuriteCache.getInstance().vider();
	}
	
	private void envoyerMessageViderCache(){
	   Destination destination = topic;
	   Connection connection = null;
	   
	   try {
			connection = connectionFactory.createConnection();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        MessageProducer messageProducer = session.createProducer(destination);
	        TextMessage message = session.createTextMessage();
	        message.setText("Vider la cache!");
	        messageProducer.send(message);
	   } catch (JMSException e) {
			e.printStackTrace();
	   } finally {
           if (connection != null) {
               try {
                   connection.close();
               } catch (JMSException e) {
                   e.printStackTrace();
               }
           }
       }
	}
   
}
