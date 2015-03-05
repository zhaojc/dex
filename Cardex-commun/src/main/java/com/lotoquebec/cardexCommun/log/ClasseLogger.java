package com.lotoquebec.cardexCommun.log;

import java.io.Serializable;
import java.util.logging.Level;

import com.lotoquebec.cardexCommun.util.StringUtils;

/*
 * Cette classe est un VO et représente une configuration de logger
 * d'une classe X.
 */
public class ClasseLogger implements Serializable{

	private static final long serialVersionUID = -2765322977443961100L;
	private String nomClasse = "";
	private String prefix = "";
	private transient FileHandler fileHandler = null;
	private Level level = null;
	
	public ClasseLogger() {
		super();
	}
	
	public ClasseLogger(String nomClasse, String prefix, Level level) {
		super();
		LoggerFileHandlerCardex loggerFileHandlerCardex = LoggerFileHandlerCardex.getInstance();
		this.nomClasse = nomClasse;
		this.prefix = prefix;
		this.fileHandler = loggerFileHandlerCardex.obtenirFileHandler(prefix);
		this.level = level;
	}

	public ClasseLogger(String nomClasse, FileHandler fileHandler, Level level) {
		super();
		this.nomClasse = nomClasse;
		this.fileHandler = fileHandler;
		this.level = level;
	}
	
	public ClasseLogger(String nomClasse, Level level) {
		super();
		LoggerFileHandlerCardex loggerFileHandlerCardex = LoggerFileHandlerCardex.getInstance();
		this.nomClasse = nomClasse;
		this.fileHandler = loggerFileHandlerCardex.getFileHandlerDefault();
		this.level = level;
	}	

	public String getNomClasse() {
		return nomClasse;
	}
	
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public Level getLevel() {
		return level;
	}

	public String getStringLevel() {
		return level.getName();
	}
	
	public void setStringLevel(String stringLevel) {
		level = Level.parse(stringLevel);
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}

	public FileHandler getFileHandler() {
		LoggerFileHandlerCardex loggerFileHandlerCardex = LoggerFileHandlerCardex.getInstance();
		
		if (fileHandler == null)
			fileHandler = loggerFileHandlerCardex.obtenirFileHandler(prefix);
		
		return fileHandler;
	}

	public void initFileHandler(){
		
		if (StringUtils.isNotEmpty(prefix)){
			LoggerFileHandlerCardex loggerFileHandlerCardex = LoggerFileHandlerCardex.getInstance();
			fileHandler = loggerFileHandlerCardex.obtenirFileHandler(prefix);
		}
	}
	
	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	
}
