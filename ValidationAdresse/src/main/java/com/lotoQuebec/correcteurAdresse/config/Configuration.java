/*
 * Created on 28-Apr-2008
 */
package com.lotoQuebec.correcteurAdresse.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author levassc
 */
public class Configuration {
	
	private static Configuration configuration = null;
    private static final String CONFIG_PROPERTIES = "config.properties";
    private Logger logger = LoggerFactory.getLogger(Configuration.class);
	private Properties props = null;

	private Configuration() {
		super();
	}

	private void init() {
		configuration.loadProperties();
	}
	
	public static Configuration getInstance(){
		
		if (configuration == null){
			configuration = new Configuration();
			configuration.init();
		}
		return configuration;
	}

    private void loadProperties() {
        try {
            // Load the specified property resource
            InputStream is = Configuration.class.getResourceAsStream(CONFIG_PROPERTIES);
            
            if (is != null) {
                props = new Properties();
                props.load(is);
                is.close();
            }
            else {
            	logger.debug("is = null!");
            }
        }
        catch (IOException ie) {
            throw new AssertionError("Unable to load property file '"
                    + CONFIG_PROPERTIES +"'.");
        }
    }

	public String getConfig(String key){
    	return props.getProperty( key );
    }

    public boolean getBoolConfig(String key){
    	return Boolean.valueOf( props.getProperty( key ) ).booleanValue();
    }
    
    public int getIntConfig(String key){
    	return Integer.valueOf( props.getProperty( key ) ).intValue();
    }    
    
	public List getListeConfig(String cleNbAgent1, String cleNbAgent2, String cleString){
		List liste1 = new ArrayList();
		int nbLogger1 = getIntConfig(cleNbAgent1);
		
		for(int i1=1;i1<nbLogger1+1;i1++){
			List liste2 = new ArrayList();
			int nbLogger2 = getIntConfig(cleNbAgent2+i1);
			
			for(int i2=1;i2<nbLogger2+1;i2++){
				liste2.add( getConfig(cleString+i1+"."+i2) );
			}
			liste1.add( liste2 );
		}
		return liste1;
	}    

}
