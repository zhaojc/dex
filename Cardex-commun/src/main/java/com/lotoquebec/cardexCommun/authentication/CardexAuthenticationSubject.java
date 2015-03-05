/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import java.security.Principal;
import java.util.Locale;

/**
 * Class declaration
 *
 * @see
 * @author $Author: pcaron $
 * @version $Revision: 1.3 $, $Date: 2002/02/18 23:54:19 $
 */
public class CardexAuthenticationSubject implements ModifiableAuthenticationSubject {
    protected boolean    authenticated = false;
    protected Locale     locale =
        Locale.ENGLISH;    // default it to English
    protected Principal  principal = null;
    protected Credential credential = null;
    protected User       user = null;
    protected Privilege  privilege = null;
    //Le token est créé par ClearTrust dans un cookie identifié par CTSESSION.
    //Ce token va servir pour toutes les vérifications d'accès aux ressources.
    protected String  token = "";
    private Long derniereRequete = new Long(0);

    /**
     * default constructor for buidling an empty authentication subject
     */
    public CardexAuthenticationSubject() {
        super();
    }

    /**
     * constructor for building an subject with name and password
     */
    public CardexAuthenticationSubject(String name, String pwd, String token) {
        this();

        // create principal and credential based on these Strings
        this.setToken(token);
        this.setPrincipal(new SimplePrincipal(name));
        this.setCredential(new SimpleCredential(pwd));
  //        this.setToken(new SimpleToken(token));
    }

    /**
     * constructor for building an subject with name and password
     */
    public CardexAuthenticationSubject(String name, String pwd) {
        this();

        // create principal and credential based on these Strings
        this.setPrincipal(new SimplePrincipal(name));
        this.setCredential(new SimpleCredential(pwd));
    }    
    
    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public Credential getCredential() {
        return credential;
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public Principal getPrincipal() {
        return principal;
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public Privilege getPrivilege() {
        return privilege;
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public User getUser() {
        return user;
    }

    /**
     * Method declaration
     *
     *
     * @param authenticated
     *
     * @see
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    /**
     * Method declaration
     *
     *
     * @param credential
     *
     * @see
     */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    /**
     * Method declaration
     *
     *
     * @param locale
     *
     * @see
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Method declaration
     *
     *
     * @param principal
     *
     * @see
     */
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    /**
     * Method declaration
     *
     *
     * @param privilege
     *
     * @see
     */
    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    /**
     * Method declaration
     *
     *
     * @param user
     *
     * @see
     */
    public void setUser(User user) {
        this.user = user;
    }

	/**
	 * Returns the token.
	 * @return String
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 * @param token The token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	public void assignerActivite() {
		this.derniereRequete = System.currentTimeMillis();
	}

	public boolean isActif(int minutes){
		return derniereRequete + (minutes*60*1000) > System.currentTimeMillis();
	}
	
}

