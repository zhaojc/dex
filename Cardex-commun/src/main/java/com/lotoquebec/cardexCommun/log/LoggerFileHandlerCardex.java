package com.lotoquebec.cardexCommun.log;

import java.util.Hashtable;
import java.util.Map;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lq.std.conf.impl.AppConfig;

/**
 * Cette classe g�re les diff�rents fichiers de log.
 * Si deux fichiers ont le m�me nom, il �crira dans le m�me fichier.
 * @author levassc
 *
 */
public class LoggerFileHandlerCardex{

	private static LoggerFileHandlerCardex loggerFileHandlerCardex = null;
	private FileHandler fileHandlerDefault = null;
	private static Map<String, FileHandler> fileHandlerMap = new Hashtable<String, FileHandler>();
	
	private LoggerFileHandlerCardex() {
		super();
	}
	
	public synchronized static LoggerFileHandlerCardex getInstance(){
		
		if (loggerFileHandlerCardex == null){
			loggerFileHandlerCardex = new LoggerFileHandlerCardex();
		}
		return loggerFileHandlerCardex;
	}

	private void chargerFicheHandlerDefault() {
		String prefix = AppConfig.INSTANCE.get("app.name");
		fileHandlerDefault = obtenirFileHandler(prefix);
	}

	public synchronized FileHandler obtenirFileHandler(String prefix) {
		String repertoire = AppConfig.INSTANCE.get(GlobalConstants.Configuration.REPERTOIRE_LOG);
		
		if (StringUtils.isEmpty(prefix))
			return fileHandlerDefault;
		
		if (fileHandlerMap.containsKey(prefix)){
			return (FileHandler) fileHandlerMap.get(prefix);
		}
		FileHandler fh;
		try {
			fh = new FileHandler(repertoire, prefix+"-", ".log");
			//fh.setFormatter(new SimpleFormatter());
			fileHandlerMap.put(prefix, fh);
			return fh;
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public synchronized void vider(){
		fileHandlerMap.clear();
		chargerFicheHandlerDefault();
	}

	public FileHandler getFileHandlerDefault() {
		
		if (fileHandlerDefault == null)
			chargerFicheHandlerDefault();
		
		return fileHandlerDefault;
	}

}
