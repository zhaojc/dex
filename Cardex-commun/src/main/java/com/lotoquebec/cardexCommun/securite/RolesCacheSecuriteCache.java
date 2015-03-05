package com.lotoquebec.cardexCommun.securite;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.SecuriteDAO;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.ApplicationUtil;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ViderCacheUtil;
import com.lq.std.conf.impl.AppConfig;

public class RolesCacheSecuriteCache {

	private static RolesCacheSecuriteCache rolesCache = null;
	// Cle: URL | Valeur: RoleCache
	private static Map<String, RoleCache> urlRole = null; 
	// Cle: classeFormMethode | Valeur: RoleCache
	private static Map<String, RoleCache> formRole = null; 
	// Cle: classeVOMethode | Valeur: RoleCache
	private static Map<String, RoleCache> voRole = null;
	private static Map<String, RoleCache> mapRole = null;
	private static final Logger log = (Logger)LoggerCardex.getLogger((RolesCacheSecuriteCache.class));
	
	private RolesCacheSecuriteCache() {
	}
	
	public synchronized static RolesCacheSecuriteCache getInstance(){
		
		ViderCacheUtil.getInstance().validerSiVider();
		
		if (rolesCache == null){
			rolesCache = new RolesCacheSecuriteCache();
			
			urlRole = new HashMap<String, RoleCache>();
			formRole = new HashMap<String, RoleCache>();
			voRole = new HashMap<String, RoleCache>();
			mapRole = new HashMap<String, RoleCache>();		
			
			chargerRoles();
		}
		return rolesCache;
	}

	public synchronized RoleCache obtenirRoleDeURL(String url){
		return urlRole.get(url);
	}

	public synchronized RoleCache obtenirRoleDeFormulaireMethode(String classeMethode){
		return formRole.get(classeMethode);
	}

	public synchronized RoleCache obtenirRoleCacheDeVOMethode(String voMethode){
		return voRole.get(voMethode);
	}
	
	private synchronized static void chargerRoles() {
		log.info("Charger les roles");
		
		String nomApplication = ApplicationUtil.getInstance().getNomApplication();
		urlRole.clear();
		formRole.clear();
		voRole.clear();
		mapRole.clear();
		
		try {
			Set<RoleCache> listeRoles = (new SecuriteDAO()).obtenirListeRoleCache(nomApplication);
			
			for (RoleCache roleCache:listeRoles) {
				
				if (StringUtils.isNotEmpty(roleCache.getUrl())){
					urlRole.put(roleCache.getUrl(), roleCache);
					continue; // on ne doit pas m�langer les URL avec les autres.
				}
				
				if (StringUtils.isNotEmpty(roleCache.getClasseForm()))
					formRole.put(roleCache.getClasseForm(), roleCache);
				
				if (StringUtils.isNotEmpty(roleCache.getClasseVO()))
					voRole.put(roleCache.getClasseVO(), roleCache);
				mapRole.put( roleCache.getRole(), roleCache );
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new RuntimeException("Probl�me a charger les r�les caches");
		}
	}
	
	public synchronized void vider(){
		chargerRoles();
	}

	public synchronized RoleCache get(String role){
		return mapRole.get(role);
	}
	
	public synchronized void statut(){
		String nomApplication = AppConfig.INSTANCE.get( GlobalConstants.Configuration.APPLICATION );
		System.out.println("------ Statut DEBUT RolesCacheSecuriteCache ------");
		System.out.println("rolesCache != null => "+(rolesCache!=null));
		
		System.out.println("urlRole != null => "+(urlRole!=null));
		
		if (urlRole != null)
			System.out.println("urlRole size => "+urlRole.size());
		
		System.out.println("formRole != null => "+(formRole!=null));
		
		if (formRole != null)
			System.out.println("formRole size => "+formRole.size());
		
		System.out.println("voRole != null => "+(voRole!=null));
		
		if (voRole != null)
			System.out.println("voRole size => "+voRole.size());
		
		System.out.println("mapRole != null => "+(mapRole!=null));
		
		if (mapRole != null)
			System.out.println("urlRole size => "+mapRole.size());

		System.out.println("nomApplication = "+nomApplication);
		
		System.out.println("------ Statut FIN RolesCacheSecuriteCache ------");
	}
}
