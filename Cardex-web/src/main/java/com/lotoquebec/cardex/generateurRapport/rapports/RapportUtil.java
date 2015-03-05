package com.lotoquebec.cardex.generateurRapport.rapports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class RapportUtil {

    
	// Hier à 6h
	public static Date dateHier6h(Date date){
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
	public static Date dateAujourdHuiFin6h(Date date){
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
}
