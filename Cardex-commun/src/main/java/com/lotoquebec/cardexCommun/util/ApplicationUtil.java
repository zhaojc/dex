package com.lotoquebec.cardexCommun.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lq.std.conf.impl.AppConfig;

public class ApplicationUtil {

	private static ApplicationUtil applicationUtil = null;
	private static String nomReference = "";
	private static String nomApplication = ""; 
	
	private ApplicationUtil(){}
	
	public static ApplicationUtil getInstance(){
		
		if (applicationUtil == null){
			applicationUtil = new ApplicationUtil();
			nomApplication = AppConfig.INSTANCE.get( GlobalConstants.Configuration.APPLICATION );
			nomReference = obtenirNomReference();
		}
		return applicationUtil;
	}
	
	private static String obtenirNomReference(){
		StringBuilder stringBuilder = new StringBuilder("");
		System.err.println("ServerName.getDisplayName()");
		try {
			String nomJVM = InetAddress.getLocalHost().getHostName();
			stringBuilder.append(nomApplication);
			stringBuilder.append("-");
			stringBuilder.append(nomJVM);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}

	public String getNomReference() {
		return nomReference;
	}

	public String getNomApplication() {
		return nomApplication;
	}
	
}
