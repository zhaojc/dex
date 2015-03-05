package com.lotoquebec.cardexCommun.user;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.lotoquebec.cardexCommun.authentication.Privilege;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe represente les privilèges d'un
 * utilisateur du systeme CARDEX.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/27 15:36:16 $
 */
public class CardexPrivilege implements Privilege, Serializable {

	private static final String INACTIF = ".inactif";
    private Map UI_COMPONENT_STATES = new HashMap();

    /**
     * Niveau d'autorité de l'utilisateur
     */
    private long niveauAuthorite;

    /**
     * Niveau de confidentialité de l'utilisateur
     */
    private long niveauConfidentialite;

    /**
     * Rôle que l'usager possède
     */
    private Set<String> roles = new HashSet<String>();
    
    /**
     * Construit une instance CardexUser.
     */
    public CardexPrivilege() {
    }


    /**
     * Construit une instance CardexUser.
     */
    public CardexPrivilege(long authorite, long confidentialite) {
      this.niveauAuthorite = authorite;
      this.niveauConfidentialite = confidentialite;
    }

    /**
     * Stocke l'état dans lequel l'utilisateur à les droits
     * nécessaire pour voir le composant.
     *
     * @param securityConstraint La règles de sécutité applicable
     * au composant visuel
     * @param state L'état dans lequel l'utilisateur à les droits
     *              nécessaire pour voir le composant
     */
    public void storeUIComponentState(String securityConstraint,UIComponentState state) {
          UI_COMPONENT_STATES.put(securityConstraint,state);
    }

    /**
     * Retourne le niveau d'autorité de l'utilisateur
     *
     * @return String Le niveau d'autorité de l'utilisateur
     */
    public long getNiveauAuthorite() {
        return this.niveauAuthorite;
    }

    /**
     * Retourne le niveau de confidentialité de l'utilisateur
     *
     * @return String Le niveau de confidentialité de l'utilisateur
     */
    public long getNiveauConfidentialite() {
        return this.niveauConfidentialite;
    }

    /**
     * Affecte le niveau d'autorité de l'utilisateur
     *
     * @param niveau le niveau d'autorité de l'utilisateur
     */
    public void setNiveauAuthorite(long niveau) {
        this.niveauAuthorite = niveau;
    }

    /**
     * Affecte le niveau de confidentialité de l'utilisateur
     *
     * @param niveau Le niveau de confidentialité de l'utilisateur
     */
    public void setNiveauConfidentialite(long niveau) {
        this.niveauConfidentialite = niveau;
    }

    public boolean isRolesEmpty(){
    	return roles.isEmpty();
    }
    
    public boolean hasRole(String testRole) {
    	
    	if (StringUtils.isEmpty(testRole))
    		return true;
    	
    	for(String role:roles)
    		if (testRole.equals(role)) 
    			return true;
    	return false;
    }

    public boolean hasRoleActifouInactif(String testRole) {
    	String testRoleDisabled = testRole+INACTIF;
    	
    	if (StringUtils.isEmpty(testRole))
    		return true;
    	
    	for(String role:roles){
    		if (testRole.equals(role)
    		|| testRoleDisabled.equals(role)) 
    			return true;
    	}
    	return false;
    }
    
    public UIComponentState hasUIRole(String testRole) {
    	String testRoleDisabled = testRole+INACTIF;
    	UIComponentState state = UIComponentState.HIDDEN;
    	
    	for(String role:roles){
    		if (testRole.equals(role))
    			return UIComponentState.ENABLED;
    		
    		if (testRoleDisabled.equals(role))
    			state = UIComponentState.DISABLED;
    	}
    	return state;
    }
    
    public void setRoles(Set<String> roles){
    	this.roles = roles;
    }
    
    public void viderRole(){
    	roles.clear();
    }
    
}