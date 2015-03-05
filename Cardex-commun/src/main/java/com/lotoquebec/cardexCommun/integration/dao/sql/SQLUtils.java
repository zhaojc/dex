package com.lotoquebec.cardexCommun.integration.dao.sql;

import java.math.BigDecimal;
import java.util.Date;

import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class SQLUtils {

	public final static String AND = " AND ";
	
	public static String construireRechercheWhereAND(String parametre, Object valeur){
		String retour = construireRechercheWhere(parametre, valeur);
		
		if (StringUtils.isNotEmpty(retour))
			return AND+retour;
		else
			return "";
	}

	public static String construireRechercheWhereDateAND(String parametre, String separateur, Date date){
		String retour = construireRechercheDateWhere(parametre, separateur, date);
		
		if (StringUtils.isNotEmpty(retour))
			return AND+retour;
		else
			return "";
	}	
	
	public static String construireRechercheWhere(String parametre, Object valeur){
		StringBuilder retour = new StringBuilder();
		
		if (valeur instanceof String){
			String strValeur = (String) valeur;
			
			if (StringUtils.isNotEmpty(strValeur)){
				retour.append(parametre+" like '"+valeur+"' ");;
			}			
		}else if (valeur instanceof Long){
			Long longValeur = (Long) valeur;
			
			if (longValeur != 0)
				retour.append(parametre+" = "+valeur+" ");;
		}else if (valeur instanceof BigDecimal){
			BigDecimal bigValeur = (BigDecimal) valeur;
			
			if (bigValeur != new BigDecimal("0.00"))
				retour.append(parametre+" = "+bigValeur.doubleValue()+" ");;
		}
		return retour.toString();
	}
	
	public static String construireRechercheDateWhere(String parametre, String separateur, Date date){
		StringBuilder retour = new StringBuilder();
		
		if (date != null){
			String valeur = DateFormat.format(date);
			retour.append(parametre+" "+separateur+" TO_DATE('"+valeur+"','YYYY-MM-DD') ");
		}
		
		return retour.toString();
	}	
	
}
