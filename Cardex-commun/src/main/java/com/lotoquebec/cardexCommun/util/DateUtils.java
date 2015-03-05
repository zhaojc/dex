package com.lotoquebec.cardexCommun.util;

import java.util.Calendar;
import java.util.Date;

import com.lotoquebec.cardexCommun.text.DateFormat;

public class DateUtils {

	public static Date ajoutJour(Date date, int amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, amount);
		return calendar.getTime();
	}

	public static Date ajoutSemaine(Date date, int amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, amount);
		return calendar.getTime();
	}
	
	public static boolean isMemeJour(Date date1, Date date2){
		String strDate1 = DateFormat.format(date1);
		String strDate2 = DateFormat.format(date2);
		return strDate1.equals( strDate2 );
	}
	
	public static String defaultString(Date date){
		if (date != null)
			return DateFormat.format(date);
		return "";
	}
	
}
