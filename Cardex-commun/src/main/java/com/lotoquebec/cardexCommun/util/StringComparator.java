/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardexCommun.util;

import java.io.Serializable;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

/**
 * @author levassc
 */
public class StringComparator implements Comparator, Serializable {

    public int compare( Object o1, Object o2 ) {
		String str1 = (String) o1;
		String str2 = (String) o2;
		boolean isStr1Vide = StringUtils.isEmpty( str1 );
		boolean isStr2Vide = StringUtils.isEmpty( str2 );
		
		if (isStr1Vide && isStr2Vide)
			return 0;
		
		if (isStr1Vide && isStr2Vide == false)
			return 1;

		if (isStr1Vide == false && isStr2Vide)
			return -1;

		RuleBasedCollator ruleBasedCollator = (RuleBasedCollator) Collator.getInstance(Locale.FRANCE);
        return ruleBasedCollator.compare( str1.toUpperCase() , str2.toUpperCase() );
    }
}
