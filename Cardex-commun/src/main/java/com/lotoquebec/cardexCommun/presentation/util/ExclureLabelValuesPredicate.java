package com.lotoquebec.cardexCommun.presentation.util;

import java.util.List;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardexCommun.util.LongUtils;

/**
 * Exclure une collection de valeur
 * @author levassc
 *
 */
public class ExclureLabelValuesPredicate implements Predicate{

	private List<Long> exclureValeurs;	
	
	public ExclureLabelValuesPredicate(List<Long> exclureValeurs){
		super();
		this.exclureValeurs = exclureValeurs;
	}
	
	public boolean evaluate(Object o) {

		if (o instanceof LabelValueBean == false)
			return false;
		LabelValueBean labelValueBean = (LabelValueBean) o;
		
		if (exclureValeurs.contains(LongUtils.valueOf(labelValueBean.getValue())))
			return false;
		
		return true;
	}

	

}
