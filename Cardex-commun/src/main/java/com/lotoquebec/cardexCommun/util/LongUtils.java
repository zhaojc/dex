package com.lotoquebec.cardexCommun.util;

import java.util.List;



public class LongUtils {

	public static long valueOf(String s){
		
		if (StringUtils.isEmpty(s))
			return 0;
		return Long.valueOf(s);
	}
	
	public static long[] valueOf(String...strings){
		long[] sortie = new long[strings.length];
		
		for (int i=0;i<strings.length;i++)
			sortie[i] = valueOf(strings[i]);
		return sortie;
	}

	public static long[] listValueOf(String strings){
		
		if (StringUtils.isEmpty(strings))
			return new long[0];
		long[] sortie = new long[1];
		sortie[0] = valueOf(strings);
		return sortie;
	}

	public static long[] listValueOf(long l){
		
		if (l == 0)
			return new long[0];
		long[] sortie = new long[1];
		sortie[0] = l;
		return sortie;
	}

	public static long[] toArray(List<Long> longs){
		long[] sortie = new long[longs.size()];
		int i = 0;
		
		for(Long l:longs)
			sortie[i++] = l;
		return sortie;
	}
	
}
