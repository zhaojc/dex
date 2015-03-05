package com.lotoquebec.cardexCommun.securite;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe vise à accélérer la validation de la sécurité dans les boucles de filtres de sécurité.
 * Le but est de valider un object et d'appliquer cette validation à tous les objects à filtrer.
 * 
 * @author levassc
 *
 */
public class RaccourcitGestionAccesSecurite implements Serializable{

	private static final long serialVersionUID = -6186730441204899021L;
	private String nomChamp = "";
	private RoleCache roleCache = null;
	private Object valeurCible = null;
	private Class parametreType = null;
	private boolean administrer = false;
	private boolean roleActifouInactif = false;
	private Map<String, AccessibleEtObligatoire> accessibleEtObligatoireMap = new HashMap<String, AccessibleEtObligatoire>();
	
	public String getNomChamp() {
		return nomChamp;
	}
	
	public void setNomChamp(String nomChamp) {
		this.nomChamp = nomChamp;
	}
	
	public RoleCache getRoleCache() {
		return roleCache;
	}
	
	public void setRoleCache(RoleCache roleCache) {
		this.roleCache = roleCache;
	}

	public Method getMethodSet(Object vo) {
		try {
			return vo.getClass().getDeclaredMethod("set" + StringUtils.capitalise(nomChamp), parametreType);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException("Problème avec l'objet "+vo.getClass());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException("Problème avec l'objet "+vo.getClass());
		}
	}
	
	public void assignerParametreType(Field field) {
		parametreType = field.getType();
	}
	
	public boolean isAdministrer() {
		return administrer;
	}
	
	public void setAdministrer(boolean administrer) {
		this.administrer = administrer;
	}
	
	public boolean isRoleActifouInactif() {
		return roleActifouInactif;
	}
	
	public void setRoleActifouInactif(boolean roleActifouInactif) {
		this.roleActifouInactif = roleActifouInactif;
	}

	public Map<String, AccessibleEtObligatoire> getAccessibleEtObligatoireMap() {
		return accessibleEtObligatoireMap;
	}

	public void setAccessibleEtObligatoireMap(
			Map<String, AccessibleEtObligatoire> accessibleEtObligatoireMap) {
		this.accessibleEtObligatoireMap = accessibleEtObligatoireMap;
	}

	public Object getValeurCible() {
		return valeurCible;
	}

	public void setValeurCible(Object valeurCible) {
		this.valeurCible = valeurCible;
	}
	
	
}
