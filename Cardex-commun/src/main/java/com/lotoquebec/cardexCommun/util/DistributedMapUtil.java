package com.lotoquebec.cardexCommun.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DistributedMapUtil {

	private static DistributedMapUtil distributedMapUtil = null;
	//private static DistributedMap distributedMap = null;
	private static Map distributedMap = new HashMap<>();
	//DistributedObjectCache 
	public static DistributedMapUtil getInstance(){
		
		if (distributedMapUtil == null){
			distributedMapUtil = new DistributedMapUtil();

			try{
				Context ctx = new InitialContext();
				System.err.println("DistributedMap distributedMap");
				//distributedMap = (DistributedMap)ctx.lookup(configuration.getConfig(GlobalConstants.Configuration.SERVICES_CACHE));
				ctx.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return distributedMapUtil;
	}
	
	public Map obtenirCache(String cle){
		
		if (distributedMap.containsKey(cle) == false)
			distributedMap.put(cle, new HashMap());
		
		return (Map) distributedMap.get(cle);
	}
	
	public Set obtenirCacheSet(String cle){
		
		if (distributedMap.containsKey(cle) == false)
			distributedMap.put(cle, new HashSet());
		
		return (Set) distributedMap.get(cle);
	}	
	
	public void assignerDistributedCache(String cle, Map cacheMap){
		distributedMap.put(cle, cacheMap);
	}
	
	public void assignerDistributedCache(String cle, Set cacheSet){
		distributedMap.put(cle, cacheSet);
	}
	
}
