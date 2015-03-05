/*
 * Created on 29-Jan-2008
 */
package com.lotoquebec.cardexCommun.rapport;

import java.util.Collection;

/**
 * @author levassc
 */
public interface SousCollection {

	public void addSousCollection(Object element);
	
	public Collection getSousCollection();
	
	public Object cloneSansCollection();
}
