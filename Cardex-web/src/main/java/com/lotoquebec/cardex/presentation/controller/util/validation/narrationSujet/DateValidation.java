/*
 * Created on 26-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class DateValidation {

	private String strDate = "";
	private Date dateUp = new Date();
	private Date dateDown = null;
	
	public DateValidation(String strDate) {
		this.strDate = strDate;
		try {
			dateDown = DateFormat.parse( DateFormat.DATE_DEFAULT );
		} catch (ParseException e) {
			e.printStackTrace();
			throw new AssertionError("Problème de convertion de dans DateValidation");
		}
	}

	public boolean valider() {
		//Sert à valider la date saisie
		if (StringUtils.isNotEmpty( strDate )){
			
			try {
				GregorianCalendar calendar = new GregorianCalendar(); 

				int anneeStrDate = Integer.parseInt(strDate.substring(0,4));
				int moisStrDate = Integer.parseInt(strDate.substring(5,7));
				int jourStrDate = Integer.parseInt(strDate.substring(8,10));

				calendar.set(anneeStrDate, moisStrDate-1, jourStrDate); 

				int annee = calendar.get(Calendar.YEAR); 
				int mois = calendar.get(Calendar.MONTH)+1; 
				int jour = calendar.get(Calendar.DAY_OF_MONTH); 
				
				if(annee==anneeStrDate & mois==moisStrDate & jour==jourStrDate){ 
					Date date = DateFormat.parse(strDate);
					boolean isDateApresDown = DateFormat.format(date).compareTo( DateFormat.format( dateDown ) ) >= 0;
					boolean isDateAvantDown = date.getTime() <= dateUp.getTime();
					return isDateApresDown && isDateAvantDown;
				}else{
					return false;
				}
			} catch (ParseException e) {
				return false;
			}
		}
		return true;
	}	
}
