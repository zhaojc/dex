/*
 * Created on 26-Sep-2008
 */
package com.lotoQuebec.correcteurAdresse.util;

import com.lotoQuebec.correcteurAdresse.config.Configuration;

/**
 * @author levassc
 */
public class CorrecteurAdresseHelper {

	final private static String DELIMITATEUR = ";";
	
	public static String ajouterParametre(Configuration configuration, String s){
		return ajouterParametre(s, configuration.getConfig(s));
	}
	
	public static String ajouterParametre(String champ, String valeur){
		return champ+"="+valeur+DELIMITATEUR;
	}	
}
