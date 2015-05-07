/*
 * Created on 7-Mar-2008
 */
package com.lotoquebec.cardexCommun.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author levassc
 */
public class StringHelper {

	public static final String CARACTERES_CONTROLE = "&*();\"";
	private static String[] formerExpressionReguliaire = {"[aA]","[bB]","[cC]","[dD]","[eE��������]","[fF]","[gG]","[hH]","[iI����]","[jJ]","[kK]","[lL]","[mM]","[nN]","[oO��������]","[pP]","[qQ]","[rR]","[sS]","[tT]","[uU����]","[vV]","[wW]","[xX]","[yY]","[zZ]"};
	private static String carteBlancheExpressionReguliaire = "[a-zA-Z]*";

	
	public static String removeCaractereRecherche(String text){
		text = StringHelper.doublerQuote(text);
		return removeCaractere(text, CARACTERES_CONTROLE);
	}

	//On traite ensuite la cha�ne de caract�re afin de retirer des caract�res de contr�le
	//utilis�s dans la syntaxe SQL.  Cette op�ration a pour but de contrer d'�ventuelles
	//requ�tes illicites ou tentatives d'intrusion d�sign�es sous le terme "SQL Injection".
	public static String removeCaractere(String text, String toRemove){
        StringBuffer buffer = new StringBuffer();
        
        if (StringUtils.isEmpty(text))
        	return buffer.toString(); 
        
        int sz = text.length();
        
        for (int i=0; i<sz; i++) {
        	
            if(toRemove.indexOf(text.charAt(i)) == -1) {
                buffer.append(text.charAt(i));
            }
        }		
        return buffer.toString();
	}	
	
	public static String doublerQuote(String text){
		return StringUtils.replace(text, "'", "''");
	}	
	
    public static String stripNonAlphanumeric(String s) { 
        return s.replaceAll("[^a-zA-Z0-9]", ""); 
     } 

    // retirer tous les espaces blancs en extra
    public static String retirerEspacesBlancs(String string){
    	String retour = string;
    	
    	while (retour.indexOf("  ") != -1){
    		retour = StringUtils.replace(retour, "  ", " ");
    	}
    	
    	return retour;
    }
	
    public static String obtenirPremiereString(Object o){
    	
    	if (o instanceof String[]){
    		return ((String[]) o)[0];
    	}
    	return "";
    }
    
    public static String trimUppercase(String s){
    	s = StringUtils.trim(s);
    	return StringUtils.upperCase(s);
    }
    
	private static String retounerExpressionRegulaire(String expression){
		StringBuilder expressionRegulaire = new StringBuilder();
		char[] charsExpression = expression.toCharArray();
		
		if (expression.startsWith("*") == false)
			expressionRegulaire.append(carteBlancheExpressionReguliaire);
		
		for (char charExpression:charsExpression){
			boolean trouve = false;
			
			if (charExpression == '*'){
				expressionRegulaire.append(carteBlancheExpressionReguliaire);
				continue;
			}
			
			for(String element:formerExpressionReguliaire){
				int indexElement = element.indexOf(charExpression);
				
				if (indexElement > 0){
					expressionRegulaire.append(element);
					trouve = true;
					break;
				}
			}
			// nous n'avons pas trouv� cette expression
			if (trouve == false){
				expressionRegulaire.append("[");
				expressionRegulaire.append(charExpression);
				expressionRegulaire.append("]");
			}
		}
		if (expression.endsWith("*") == false)
			expressionRegulaire.append(carteBlancheExpressionReguliaire);
		
		return expressionRegulaire.toString();
	}
	
    public static String encadrer(String texte, String cible, String prefix, String sufix) {
    	
    	if (StringUtils.isEmpty(texte) || StringUtils.isEmpty(cible)) {
			return null;
		}
    	cible = retounerExpressionRegulaire(cible);
    	StringBuffer sb = new StringBuffer();

    	Pattern p = Pattern.compile(cible);
        // Create a matcher with an input string
        Matcher m = p.matcher(texte);
        int debut = 0; 
        int fin = 0;
        
        while (m.find()){
        	fin = m.start();
        	sb.append(texte.substring(debut, fin));
        	sb.append(prefix);
        	debut = m.start();
        	fin = m.end();
        	sb.append(texte.substring(debut, fin));
        	sb.append(sufix);
        	debut = fin;
        }
        sb.append(texte.substring(debut));

    	return sb.toString();
	}
    
    // utilisez LongUtils.valueof
    @Deprecated
    public static long convertirStringEnLong(String string){
    	
    	if (StringUtils.isNotEmpty(string) && StringUtils.isNumeric(string))
    		return Long.valueOf(string);
    	return 0;
    }
    
    
	public static String retirer(String text, String...aRetirers) {

		for (String aRetirer:aRetirers){
			text = text.replaceAll(aRetirer, "");
		}
		return text;
	}
}
