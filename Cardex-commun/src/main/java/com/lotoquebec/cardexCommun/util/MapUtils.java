/*
 * Created on 4-Jul-2008
 */
package com.lotoquebec.cardexCommun.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author levassc
 */
public class MapUtils {

	public static Map getMap(String str[]){
		Map map = new HashMap();
		
		for (int i = 0; i < str.length; i++) {
			map.put(str[i], str[i]);
		}
		
		return map;
	}
}
