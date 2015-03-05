package com.lotoquebec.cardexCommun.util;

import java.util.Set;

public class SetUtils {

	public static<T> T get(Set<T> ts, T cle){
		
		for (T t:ts)
			if (t.equals(cle))
				return t;
		return null;
	}
	
	public static<T> T get1(Set<T> ts){
		return ts.iterator().next();
	}
	
}
