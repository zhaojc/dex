package com.lotoquebec.cardex.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class RapportUtils {

    
	// Hier � 6h
	public static Date dateHier7h(Date date){
		Calendar calendar = new GregorianCalendar(); 
		calendar = Calendar.getInstance();
		
		if (date == null){
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1);
		}else
			calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}

	// Aujourd'hui 5h59
	public static Date dateAujourdHuiFin6h59(Date date){
		Calendar calendar = new GregorianCalendar(); 
		calendar = Calendar.getInstance();
		
		if (date == null){
			calendar.setTime(new Date());
		}else
			calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 5);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
	}
	
	public static Date convertirStringEnSqlDate(String dateStr, Date defaut) throws ParseException{
	    
		if(StringUtils.isEmpty(dateStr))
			return defaut;
		else
			return DateFormat.parse(dateStr);
	}
	
	public static JasperReport compiler(String nomGabarit) throws JRException{
		return JasperCompileManager.compileReport(RapportsConfiguration.class.getResourceAsStream(nomGabarit));	
	}
	
    public static String concateneFleche(String valeur, String precedentValeur) {
    	
    	if(StringUtils.isEmpty(valeur) || StringUtils.isEmpty(precedentValeur))
    		valeur = "";
    	
    	if(StringUtils.isDifferent(valeur, precedentValeur))
    		return "&#151;&#62; " + valeur;
    	else{
    		if (StringUtils.isNotEmpty(valeur))
    			return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + valeur;
    		else
    			return "";
    	}
    }

    
}
