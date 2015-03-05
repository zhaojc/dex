package com.lotoquebec.cardexCommun.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.util.ApplicationUtil;
import com.lq.std.conf.impl.AppConfig;

/**
 * Cette classe est responsables des "java.util.logging.Logger" 
 * La classe g�n�re le logger avec la configuration de base de l'object LoggerConfig. 
 * @author levassc
 *
 */
public class LoggerCardex{

	private static Map<String, Logger> loggerMap = null;
	private static Logger defautLog = null;
	
	private LoggerCardex() {
		super();
	}
	
	private static void init(){

		if (loggerMap == null){
			loggerMap = new Hashtable<String, Logger>();
		}

		if (defautLog == null){
			defautLog = Logger.getLogger(AppConfig.INSTANCE.get("app.name"));
			initLoggerDefaut(defautLog);
			loggerMap.put(AppConfig.INSTANCE.get("app.name"), defautLog);
		}
	}
	
	public synchronized static Logger getLogger(Class classe){
		return getLogger(classe.toString());
	}
	
	private static Logger getLogger(String strLogger){
		init();
		StringBuffer loggerId = new StringBuffer(strLogger);
		loggerId.append( " - " );
		loggerId.append( ApplicationUtil.getInstance().getNomReference() );
		
		if (loggerMap.containsKey(loggerId.toString()) == false){
			Logger logger = Logger.getLogger(loggerId.toString());
			initLogger(logger);
			loggerMap.put(loggerId.toString(), logger);
		}
		return (Logger) loggerMap.get(loggerId.toString());
	}
	
	private static void initLogger(Logger logger){
		viderHandler(logger);
		logger.addHandler(defautLog.getHandlers()[0]);
		logger.setLevel(defautLog.getLevel());
	}
	
	private static void initLoggerDefaut(Logger logger){
		LoggerFileHandlerCardex loggerFileHandlerCardex = LoggerFileHandlerCardex.getInstance();
		Level level = Level.INFO;
		logger.setLevel(level);
		viderHandler(logger);
		logger.addHandler(loggerFileHandlerCardex.getFileHandlerDefault());
	}		

	private static void viderHandler(Logger logger) {
		
		for (int i = 0; i < logger.getHandlers().length; i++) {
			Handler handler = logger.getHandlers()[i];
			logger.removeHandler(handler);
		}
	}

    public static void severe(Logger logger, String message, Throwable t){
    	logger.severe(message + " "+getStackTrace(t));
    }
	
    public static void severe(Logger logger, Throwable t){
    	logger.severe(getStackTrace(t));
    }
    
    private static String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
