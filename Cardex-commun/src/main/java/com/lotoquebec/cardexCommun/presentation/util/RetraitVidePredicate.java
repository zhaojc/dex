package com.lotoquebec.cardexCommun.presentation.util;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardexCommun.util.StringUtils;

public class RetraitVidePredicate implements Predicate {

	public RetraitVidePredicate() {
		super();
	}

	public boolean evaluate(Object o) {
		
		if (o instanceof LabelValueBean == false)
			return false;
		LabelValueBean labelValueBean = (LabelValueBean) o;
		
		return StringUtils.isNotEmpty(labelValueBean.getValue());
	}

}
