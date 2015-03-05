/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardexCommun.model;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * @author levassc
 */
public class TimeStampComparator implements Comparator {

    public int compare( Object o1, Object o2 ) {
    	Timestamp timestamp1 = (Timestamp) o1;
    	Timestamp timestamp2 = (Timestamp) o2;

    	if (timestamp1 == null && timestamp2 != null)
    		return 1;

    	if (timestamp1 != null && timestamp2 == null)
    		return -1;

    	if (timestamp1 == null && timestamp2 == null)
    		return 0;
    	
    	return timestamp1.compareTo( timestamp2 );
    }
}
