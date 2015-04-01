package com.lotoquebec.iris.infrastructure.services.management;

import java.io.InputStream;

import com.lotoquebec.iris.infrastructure.services.configuration.ConfigProperties;
import com.lotoquebec.iris.infrastructure.services.configuration.PathConfiguration;
import com.lotoquebec.iris.infrastructure.services.messaging.MessagingServer;
import com.lotoquebec.iris.infrastructure.services.messaging.MessagingService;


public class ServicesManager{

	public static MessagingService getMessagingServer() {
		InputStream is = PathConfiguration.class.getResourceAsStream("messaging.properties");
		ConfigProperties propCfg;
		try {
			propCfg = new ConfigProperties(is);
			return MessagingServer.getInstance(propCfg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	
}