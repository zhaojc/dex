/*
 * Created on 8-Apr-2008
 */
package com.lotoquebec.cardexCommun.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.FabriqueDAO;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.CleHardListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

/**
 * @author levassc
 */
public class ListeCache {

	private static ListeCache listeCache = null;
	private static Map<Object, Map> CACHE = null;
	private static Set<TableValeurCleSQLListeCache> tableValeurSansRoleEtActive = null;
	private static final Logger log = LoggerFactory.getLogger(ListeCache.class);
	
	private ListeCache() {
		super();
	}
	
	public synchronized static ListeCache getInstance(){

		if (listeCache == null){
			listeCache = new ListeCache();
			CACHE = new HashMap<Object, Map>();
			tableValeurSansRoleEtActive = new HashSet<TableValeurCleSQLListeCache>();
		}
		ViderCacheUtil.getInstance().validerSiVider();
		
		return listeCache;
	}
	
	// ne pas retirer le new
	public synchronized List<LabelValue> obtenirListe(CardexAuthenticationSubject subject, CleListe cleListe) throws BusinessResourceException{
		 return new ArrayList( obtenirMap(subject, cleListe).values() );
	}
	
	public synchronized List<LabelValue> obtenirListe(CardexAuthenticationSubject subject, CleListe cleListe, List<Predicate> predicates) throws BusinessResourceException{
		List<LabelValue> labelValueBeans = new ArrayList<LabelValue>( obtenirMap(subject, cleListe).values() );
		filtrer(labelValueBeans,predicates);
		return labelValueBeans;
	}
	
	private void filtrer(Collection<LabelValue> labelValueBeans, List<Predicate> predicates){
		
		if (predicates != null){
		
			for (Predicate predicate:predicates)
				CollectionUtils.filter(labelValueBeans, predicate);
		}
	}
	
	public Collection<LabelValue> obtenirListe(CardexAuthenticationSubject subject, CleListe cleListe, String valeur, List<Predicate> predicates) throws BusinessResourceException {
		Collection<LabelValue> labelValueBeans = obtenirMapLabelValue(subject, cleListe, valeur).values();
		filtrer(labelValueBeans,predicates);
		return labelValueBeans;
	}
	
	public List<LabelValue> obtenirListe(CardexAuthenticationSubject subject, CleListe cleListe, String[] valeur, List<Predicate> predicates) throws BusinessResourceException {
		List<LabelValue> labelValueBeans = new ArrayList<LabelValue>( obtenirMapLabelValue(subject, cleListe, valeur).values());
		filtrer(labelValueBeans,predicates);
		return labelValueBeans;
	}
	
	public synchronized List<LabelValue> obtenirListe(CardexAuthenticationSubject subject, CleListe cleListe, String valeur) throws BusinessResourceException{
		 return new ArrayList<LabelValue>( obtenirMapLabelValue(subject, cleListe, valeur).values() );
	} 
		 
	public synchronized List<LabelValue> obtenirListe(CardexAuthenticationSubject subject, CleListe cleListe, String[] valeur) throws BusinessResourceException{
		return new ArrayList<LabelValue>( obtenirMapLabelValue(subject, cleListe, valeur).values() );
	} 

	public synchronized Map obtenirMap(CardexAuthenticationSubject subject, CleListe cleListe) throws BusinessResourceException{
		return obtenirMapLabelValue(subject, cleListe, "");
	}	
	
	/**
	 * Valide si on peut consulter cette donn�e.
	 * Si la liste n'existe pas, c'est que c'est invalide.
	 * Si la liste est vide avec un action autre que "toutes" il faut
	 * refaire une recherche avec cette valeur.
	 * @param subject
	 * @param cleListe
	 * @param valeur
	 * @return
	 * @throws BusinessResourceException
	 */
	public synchronized boolean isValeurAccessible(CardexAuthenticationSubject subject, TableValeurCleSQLListeCache cleListe, String valeur) throws BusinessResourceException{
		return obtenirMapLabelValue(subject, cleListe, valeur).containsKey(valeur);
	}

	/**
	 * Valide si la liste comporte au moins une valeur que nous pouvons utiliser
	 * @param subject
	 * @param cleListe
	 * @param valeur
	 * @return
	 * @throws BusinessResourceException
	 */
	public synchronized boolean isUneValeurAccessible(CardexAuthenticationSubject subject, TableValeurCleSQLListeCache cleListe) throws BusinessResourceException{
		return obtenirMapLabelValue(subject, cleListe, "").size() > 1;
	}
	
	
	/**
	 * Obtenir le contenu de la liste
	 * @param subject
	 * @param cleListe
	 * @param valeur pour obtenir les valeurs inactives d'une liste
	 * @return
	 * @throws BusinessResourceException
	 */
	private Map obtenirMapBase(CardexAuthenticationSubject subject, CleListe cleListe) throws BusinessResourceException{
		Map map = (Map) CACHE.get( cleListe );
		
		if (CACHE.containsKey( cleListe ) == false){
			map = getMapListValeur(subject, cleListe);
			CACHE.put( cleListe, map );
		}
		
		if (cleListe instanceof TableValeurCleSQLListeCache){
			TableValeurCleSQLListeCache tableValeurCleSQLListeCache = (TableValeurCleSQLListeCache) cleListe;
			
			if (map.isEmpty()
			&& GlobalConstants.ActionSecurite.TOUTES_ACTIONS.equals(tableValeurCleSQLListeCache.getActionSecurite()) == false){
				TableValeurCleSQLListeCache tableValeurCleSQLListeCacheNouveau = tableValeurCleSQLListeCache.nouveauTableValeurCleSQLListeCache(GlobalConstants.ActionSecurite.TOUTES_ACTIONS);
				
				// Si la liste de nouveau est dans SansR�leEtActive celle qui l'a appel� aussi.
				if (tableValeurSansRoleEtActive.contains(tableValeurCleSQLListeCacheNouveau))
					tableValeurSansRoleEtActive.add(tableValeurCleSQLListeCache);
				return obtenirMapBase(subject, tableValeurCleSQLListeCacheNouveau);
			}
		}
		return map;
	}	
	
	/**
	 * Obtenir un map d'une liste pour l'affichage de l'application.
	 * @param subject
	 * @param cle
	 * @param langue
	 * @return
	 * @throws BusinessResourceException
	 */
	private Map getMapListValeur(CardexAuthenticationSubject subject, CleListe cleListe)
	throws BusinessResourceException {
		try {
			
			if (cleListe.getClass().getName().equals(GlobalConstants.CleListe.ANNEE)){
				return FabriqueDAO.getInstance().getItemListDAO().getMapListValeurStatique(subject, GlobalConstants.ListeCache.ANNEE);
			}
			if (cleListe.getClass().getName().equals(GlobalConstants.CleListe.MOIS)){
				return FabriqueDAO.getInstance().getItemListDAO().getMapListValeurStatique(subject, GlobalConstants.ListeCache.MOIS);
			}
			if (cleListe instanceof CleHardListe){
				CleHardListe cleHardListe = (CleHardListe) cleListe;
				return cleHardListe.getMap();
			}
			
			if (cleListe instanceof TableValeurCleSQLListeCache){
				return FabriqueDAO.getInstance().getItemListDAO().getMapListTableValeur(subject, (TableValeurCleSQLListeCache) cleListe);
			}else
				return FabriqueDAO.getInstance().getItemListDAO().getMapListValeur(subject, cleListe);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
	

	private Map obtenirMapLabelValue(CardexAuthenticationSubject subject, CleListe cleListe, String... valeur) throws BusinessResourceException{
		Map map = obtenirMapBase(subject, cleListe);
		
		if (cleListe instanceof TableValeurCleSQLListeCache){
			TableValeurCleSQLListeCache tableValeurCleSQLListeCache = (TableValeurCleSQLListeCache) cleListe;
			
			if (tableValeurSansRoleEtActive.contains(cleListe) == false)
				return obtenirMapFiltre(subject, map, tableValeurCleSQLListeCache, valeur);
			//else ne doit pas �tre requis
				//return CACHE.get(cleListe);
		}
		
		return map;
	}	
	
	/**
	 * Obtenir la liste filtr� avec la s�curit�
	 * @param subject
	 * @param mapTotal
	 * @param cleListe
	 * @param valeur
	 * @return
	 */
	private Map obtenirMapFiltre(CardexAuthenticationSubject subject, Map<String, TableValeur> mapTotal, TableValeurCleSQLListeCache cleListe, String... valeur) {
		CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
		final Map<String, LabelValueBean> mapFiltre = new LinkedHashMap<String, LabelValueBean>();
		boolean isTousSansRoleEtActif = true;
		
		for(Map.Entry<String, TableValeur> entry: mapTotal.entrySet()){
			TableValeur tableValeur = entry.getValue();
			Arrays.sort(valeur);
			
			/*
			 *  La valeur s'affiche dans la liste, si la valeur est actif
			 *  ou si la valeur est d�j� disponible dans les d�j�s choisis
			 *  ou si c'est une action de recherche. 
			 */
			if (tableValeur.isActif() || Arrays.binarySearch(valeur, tableValeur.getCle()) > -1
			|| GlobalConstants.ActionSecurite.RECHERCHE.equals(cleListe.getActionSecurite())
			|| GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER.equals(cleListe.getActionSecurite())
			|| GlobalConstants.ActionSecurite.RECHERCHE_GALERIE.equals(cleListe.getActionSecurite())
			|| GlobalConstants.ActionSecurite.RECHERCHE_PILOTAGE.equals(cleListe.getActionSecurite())){ 
				
				/*
				 * Nous affichons la valeur si l'intervenant est 
				 * - ou la ligne n'est pas administrer.
				 * - ou si elle est administrer, l'utilisateur doit avoir le r�le
				 */
				if (tableValeur.isAdministrer() == false
				|| (tableValeur.isAdministrer() && userPrivilege.hasRole(tableValeur.getRole()))){
					LabelValueBean valueBean = new LabelValueBean(tableValeur.getDescription(), entry.getKey());
					
					if (mapFiltre.size() == 0)
						mapFiltre.put("", new LabelValueBean("", ""));			
					mapFiltre.put(entry.getKey(), valueBean);
				}
			}
			
			if (isTousSansRoleEtActif && (tableValeur.isActif() == false
			 || tableValeur.isAdministrer()))
				isTousSansRoleEtActif = false;
		}
		
		// La liste est accessible � tous et toute active.
		if (isTousSansRoleEtActif){
			tableValeurSansRoleEtActive.add(cleListe);
			CACHE.put( cleListe, mapFiltre );	
		}
		return mapFiltre;
	}
	
	/**
	 * Attention: Ne doit �tre affich� � l'utilisateur.
	 * Pour obtenir le map des valeurs que l'utilisateur a le droit d'afficher, actif et inactif.
	 * @param subject
	 * @param cleListe
	 * @return
	 * @throws BusinessResourceException
	 */
	public synchronized Map obtenirMapLabelValueActifEtInactif(CardexAuthenticationSubject subject, CleListe cleListe) throws BusinessResourceException{
		Map map = obtenirMapBase(subject, cleListe);
		
		if (cleListe instanceof TableValeurCleSQLListeCache){
			TableValeurCleSQLListeCache tableValeurCleSQLListeCache = (TableValeurCleSQLListeCache) cleListe;
			
			if (tableValeurSansRoleEtActive.contains(cleListe) == false)
				return obtenirMapFiltreActifEtInactif(subject, map, tableValeurCleSQLListeCache);
		}
		
		return map;
	}	
	
	/**
	 * Obtenir la liste filtr� par la s�curit�, que les valeurs soient actives ou non.
	 * @param subject
	 * @param mapTotal
	 * @param cleListe
	 * @param valeur
	 * @return
	 */
	private Map obtenirMapFiltreActifEtInactif(CardexAuthenticationSubject subject, Map<String, TableValeur> mapTotal, TableValeurCleSQLListeCache cleListe) {
		CardexPrivilege userPrivilege = (CardexPrivilege)subject.getPrivilege();
		final Map<String, LabelValueBean> mapFiltre = new LinkedHashMap<String, LabelValueBean>();
		
		for(Map.Entry<String, TableValeur> entry: mapTotal.entrySet()){
			TableValeur tableValeur = entry.getValue();
				
			/*
			 * Nous affichons la valeur si la ligne n'est pas administrer.
			 * Si elle est administrer, l'utilisateur doit avoir le r�le
			 */
			if (tableValeur.isAdministrer() == false
			|| (tableValeur.isAdministrer() && userPrivilege.hasRole(tableValeur.getRole()))){
				LabelValueBean valueBean = new LabelValueBean(tableValeur.getDescription(), entry.getKey());
				
				if (mapFiltre.size() == 0)
					mapFiltre.put("", new LabelValueBean("", ""));			
				mapFiltre.put(entry.getKey(), valueBean);
			}
			
		}
		
		return mapFiltre;
	}	
	
	public synchronized String obtenirRole(CardexAuthenticationSubject subject, TableValeurCleSQLListeCache cleListe, String... valeur) throws BusinessResourceException {
		StringBuilder role = new StringBuilder();
		Map<String, TableValeur> mapTotal = obtenirMapBase(subject, cleListe);
		
		for(Map.Entry<String, TableValeur> entry: mapTotal.entrySet()){
			TableValeur tableValeur = entry.getValue();
			Arrays.sort(valeur);
			
			if (tableValeur.isAdministrer() && Arrays.binarySearch(valeur, tableValeur.getCle()) > -1){ 
				
				if (role.length() > 0)
					role.append(", ");
				
				role.append(tableValeur.getRole());
			}
		}
		
		return role.toString();
	}

	/**
	 * Valide si cette valeur est une valeur Obligatoire.
	 * Une valeur obligatoire est une valeur qui est primordiale pour consulter
	 * un enregistrement.
	 * @param subject
	 * @param cleListe
	 * @return
	 * @throws BusinessResourceException
	 */
	public synchronized boolean isObligatoire(CardexAuthenticationSubject subject, TableValeurCleSQLListeCache cleListe) throws BusinessResourceException{
		Map map = obtenirMapBase(subject, cleListe);
		
		// Il est possible que la liste soit vide
		if (map.isEmpty() == false && tableValeurSansRoleEtActive.contains(cleListe) == false){
			// Prendre le premier, c'est simplement pour voir si cette liste est obligatoire.
			TableValeur tableValeur = (TableValeur) map.values().iterator().next();
			return tableValeur.isObligatoire();
		}else
			return false;
	}
	
	public synchronized String obtenirLabel(CardexAuthenticationSubject subject, long l, CleListe cleListe) throws BusinessResourceException {
		return obtenirLabel(subject, String.valueOf(l), cleListe);
	}
	
	public synchronized String obtenirLabel(CardexAuthenticationSubject subject, String valeurLong, CleListe cleListe) throws BusinessResourceException {
		
		if (StringUtils.isNotEmpty(valeurLong)
    	&& "0".equals(valeurLong) == false){
    		Map classeMap = obtenirMapLabelValue(subject, cleListe, valeurLong);
    		
    		// valeur non trouv�
    		if (classeMap.get(valeurLong) == null)
    			return "";
    		
    		return ((LabelValueBean) classeMap.get(valeurLong)).getLabel();
    	}
		return "";
	}

	public synchronized long obtenirKeyDeLabel(CardexAuthenticationSubject subject, String value, CleListe cleListe) throws BusinessResourceException {
		
		if (StringUtils.isNotEmpty(value)){
    		Map classeMap = obtenirMap(subject, cleListe);
    		return obtenirKeyLabelValue(classeMap.values(), value);
    	}
		return 0;
	}
	
	public synchronized long obtenirKeyLabelValue(Collection listLabelValue, String value){
		long keyLabelValue = 0;
		
		if (listLabelValue.size() == 0)
			return 0;
		
		Iterator iter = listLabelValue.iterator();
		
		while (iter.hasNext()) {
			LabelValueBean valueBean = (LabelValueBean) iter.next();
			
			if (value.equalsIgnoreCase( valueBean.getLabel() )){
				keyLabelValue = Long.parseLong( valueBean.getValue() );
				break;
			}
		}
		return keyLabelValue;
	}	
	
	/**
	 * On ne peut pas cibler la classe ou la liste exacte.
	 * �a ne fonctionne pas avec la DistributedCache.
	 */
	public synchronized void vider(){
		log.info("Vider la ListeCache");
		CACHE.clear();
		tableValeurSansRoleEtActive.clear();
	}
	
	public synchronized void viderTableValeur(String...valeursTableValeur){
		ViderCacheUtil.getInstance().assignerViderCaches();
		/*Map<Object, Map> CACHE = DistributedMapUtil.getInstance().obtenirCache(GlobalConstants.Configuration.SERVICES_CACHE_LISTE_CACHE);
		Set<TableValeurCleSQLListeCache> tableValeurSansRoleEtActive = DistributedMapUtil.getInstance().obtenirCacheSet(GlobalConstants.Configuration.SERVICES_CACHE_TABLE_VALEUR_SANS_ROLE_ET_ACTIVE);;
		Iterator<Entry<Object, Map>> iter = CACHE.entrySet().iterator();
		Class parametre = null;
		
		while (iter.hasNext()) {
			Map.Entry<Object, Map> mapEntry = (Map.Entry<Object, Map>) iter.next();
			String oValeurTableValeur = getValeurTableValeur(mapEntry.getKey());

			for (String valeurTableValeur:valeursTableValeur){
				
				if (valeurTableValeur.equals( oValeurTableValeur )){
					iter.remove();
					break;
				}
			}
		}
		Iterator<TableValeurCleSQLListeCache> iterTV = tableValeurSansRoleEtActive.iterator();
		
		while (iterTV.hasNext()) {
			Object oTableValeurCleSQLListeCache = iterTV.next();
			String oValeurTableValeur = getValeurTableValeur(oTableValeurCleSQLListeCache);
			
			for (String valeurTableValeur:valeursTableValeur){
				
				if (valeurTableValeur.equals( oValeurTableValeur )){
					iterTV.remove();
					break;
				}
			}
		}
		assignerDistributedCache(CACHE, tableValeurSansRoleEtActive);*/
	}	
	
	/*private String getValeurTableValeur(Object o){
		try {
			Method methodGet = o.getClass().getMethod("getValeurTableValeur");
			return (String) methodGet.invoke(o);

		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		return "";
	}*/
	
	/**
	 * Vider une liste de classe donn�es
	 * @param classes
	 */
	public synchronized void vider(Class<? extends CleListe>...classes){
		ViderCacheUtil.getInstance().assignerViderCaches();
		/*Map<Object, Map> CACHE = DistributedMapUtil.getInstance().obtenirCache(GlobalConstants.Configuration.SERVICES_CACHE_LISTE_CACHE);
		Set<TableValeurCleSQLListeCache> tableValeurSansRoleEtActive = DistributedMapUtil.getInstance().obtenirCacheSet(GlobalConstants.Configuration.SERVICES_CACHE_TABLE_VALEUR_SANS_ROLE_ET_ACTIVE);;
		Iterator<Map.Entry<Object, Map>> iter = CACHE.entrySet().iterator();
		
		while (iter.hasNext()) {
			Map.Entry<Object, Map> mapEntry = (Map.Entry<Object, Map>) iter.next();
			
			for (Class<? extends CleListe> classe:classes){
				  
				if (mapEntry.getKey().getClass().toString().equals( classe.toString() )){
					iter.remove();
					break;
				}
			}			
		}
		
		for (Class<? extends CleListe> classe:classes){
			
			if (classe.isAssignableFrom( TableValeurCleSQLListeCache.class )){
				Iterator iterTV = tableValeurSansRoleEtActive.iterator();
				
				while (iterTV.hasNext()) {
					Object oTableValeurCleSQLListeCache = iterTV.next();
					
					if (oTableValeurCleSQLListeCache.getClass().toString().equals( classe.toString() )){
						iterTV.remove();
						break;
					}
				}
			}
		}
		assignerDistributedCache(CACHE, tableValeurSansRoleEtActive);*/
	}


	
}
