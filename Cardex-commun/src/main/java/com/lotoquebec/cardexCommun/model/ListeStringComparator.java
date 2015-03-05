/*
 * Created on 22-Jan-2009
 */
package com.lotoquebec.cardexCommun.model;
 
import java.util.Comparator;
import java.util.List;

import com.lotoquebec.cardexCommun.util.StringComparator;

/**
 * @author levassc
 */
public class ListeStringComparator implements Comparator {

	private int i = 0;
	
	public ListeStringComparator(int i) {
		super();
		this.i = i;
	}
	
    public int compare( Object o1, Object o2 ) {
		List list1 = (List) o1;
		List list2 = (List) o2;
		
		if (list1.isEmpty() && list2.isEmpty())
			return 0;
		
		if (list1.isEmpty() && list2.isEmpty() == false)
			return 1;

		if (list1.isEmpty() == false && list2.isEmpty())
			return -1;
		
        return (new StringComparator()).compare( list1.get(i) , list2.get(i) );
    }
}
