package com.lotoquebec.cardexCommun.util;

import java.util.HashMap;
import java.util.Map;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.securite.RolesCacheSecuriteCache;

public class ViderCacheUtil {

	private static ViderCacheUtil viderCacheUtil = null;
	//private final static Logger log = LoggerFactory.getLogger(ViderCacheUtil.class);
	
	private ViderCacheUtil(){}
	
	public synchronized static ViderCacheUtil getInstance(){
		
		if (viderCacheUtil == null){
			viderCacheUtil = new ViderCacheUtil();
		}
		return viderCacheUtil;
	}
	
	public synchronized void validerSiVider(){
		
		if ( isViderDeclenche() )
			vider();
	}
	
	/**
	 * On ne peut pas cibler la classe ou la liste exacte.
	 * �a ne fonctionne pas avec la DistributedCache.
	 * Il faut d'abord flager que c'est fait avant de le faire..
	 */
 	private void vider() {
 		Map<String,Boolean> viderCacheListeMap = obtenirServicesCacheListe();
		String nomReference = ApplicationUtil.getInstance().getNomReference();
		
		System.err.println("Vider la cache de "+nomReference);
		
		viderCacheListeMap.put(nomReference, false);
		DistributedMapUtil.getInstance().assignerDistributedCache(GlobalConstants.Configuration.SERVICES_VIDER_CACHE_LISTE, viderCacheListeMap);

		//Liste de caches � vider.
		ListeCache.getInstance().vider();
		RolesCacheSecuriteCache.getInstance().vider();
	}
	
	public synchronized void assignerViderCaches(){
		System.err.println("Assigner vider cache");
		Map<String,Boolean> viderCacheListeMap = obtenirServicesCacheListe();
		
		for (Map.Entry<String, Boolean> entry:viderCacheListeMap.entrySet()){
			entry.setValue(true);
		}
		
		DistributedMapUtil.getInstance().assignerDistributedCache(GlobalConstants.Configuration.SERVICES_VIDER_CACHE_LISTE, viderCacheListeMap);
	}
	
	private Map<String,Boolean> obtenirServicesCacheListe(){
		Map<String,Boolean> viderCacheListeMap = DistributedMapUtil.getInstance().obtenirCache(GlobalConstants.Configuration.SERVICES_VIDER_CACHE_LISTE);
		String nomReference = ApplicationUtil.getInstance().getNomReference();
		boolean assignerDistributedCache = false;
		
		if (viderCacheListeMap == null){
			System.err.println("viderCacheListeMap est null");
			viderCacheListeMap = new HashMap<String, Boolean>();
			assignerDistributedCache = true;
		}
		
		if (viderCacheListeMap.containsKey(nomReference) == false){
			System.err.println("cle "+nomReference+" est null");
			viderCacheListeMap.put(nomReference, false);
			assignerDistributedCache = true;
		}
		
		if (assignerDistributedCache)
			DistributedMapUtil.getInstance().assignerDistributedCache(GlobalConstants.Configuration.SERVICES_VIDER_CACHE_LISTE, viderCacheListeMap);
		
		return viderCacheListeMap;
	}
	
	private boolean isViderDeclenche(){
		String nomReference = ApplicationUtil.getInstance().getNomReference();
		Map<String,Boolean> viderCacheListeMap = obtenirServicesCacheListe();
		
		return viderCacheListeMap.get(nomReference);
	}

	
}
