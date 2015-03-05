/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;
import java.util.Comparator;

import com.lotoquebec.cardexCommun.util.StringComparator;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class NumeriqueComparator implements Comparator, Serializable {

    public int compare( Object o1, Object o2 ) {
		String str1 = (String) o1;
		String str2 = (String) o2;
		
		str1 = str1.replace(',', '.');
		str2 = str2.replace(',', '.');
		
		boolean isStr1Vide = StringUtils.isEmpty( str1 );
		boolean isStr2Vide = StringUtils.isEmpty( str2 );

		boolean isStr1Numeric = StringUtils.isNumeric( StringUtils.replaceOnce(str1, ".", "") );
		boolean isStr2Numeric = StringUtils.isNumeric( StringUtils.replaceOnce(str2, ".", "") );
		
		if (isStr1Vide && isStr2Vide)
			return 0;
		
		if (isStr1Vide && isStr2Vide == false)
			return 1;

		if (isStr1Vide == false && isStr2Vide)
			return -1;
		
		if (isStr1Numeric == false || isStr2Numeric == false)
			return (new StringComparator()).compare(o1, o2);

		double d1 = Double.valueOf( str1 ).doubleValue();
		double d2 = Double.valueOf( str2 ).doubleValue();
		
		if (d1 < d2)
			return -1;

		if (d1 > d2)
			return 1;
		
		return 0;
    }
}
