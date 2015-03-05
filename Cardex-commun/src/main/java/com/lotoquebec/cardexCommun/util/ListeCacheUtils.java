package com.lotoquebec.cardexCommun.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardexCommun.presentation.util.LabelValue;

public class ListeCacheUtils {

	public static List<Long> obtenirListeValue(Collection<LabelValue> labelValueBeans){
		List<Long> listeValue = new ArrayList<Long>();

		for(LabelValue labelValueBean:labelValueBeans)
			listeValue.add( Long.valueOf(labelValueBean.getValue()) );
		return listeValue;
	}
	
}
