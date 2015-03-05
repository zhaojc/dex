package com.lotoquebec.cardexCommun.util; 

import java.util.Date;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardexCommun.text.DateFormat;

public class DateMemeJourPredicate implements Predicate{

	private Date date = null;
	
	public DateMemeJourPredicate(Date date){
		this.date = date;
	}

	public boolean evaluate(Object o) {
		if (o instanceof Date == false) return false;
		return DateFormat.format(date).equals( DateFormat.format((Date)o) );
	}

}
