/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import java.security.Principal;
import java.util.Locale;

/**
 * A simple version of the authentication subject.
 *
 * @author $Autor: $
 * @version $Revision: 1.3 $, $Date: 2002/02/01 21:06:38 $
 */
public class SimpleAuthenticationSubject
    implements ModifiableAuthenticationSubject {
    protected boolean    authenticated = false;
    protected Locale     locale =
        Locale.ENGLISH;    // default it to English
    protected Principal  principal = null;
    protected Credential credential = null;
    protected User       user = null;
    protected Privilege  privilege = null;
    //Le token est créé par ClearTrust dans un cookie identifié par CTSESSION.
    //Ce token va servir pour toutes les vérifications d'accès aux ressources.
    //protected Token  token = null;
    protected String  token = "";

    /**
     * default constructor for buidling an empty authentication subject
     */
    public SimpleAuthenticationSubject() {
        super();
    }

    /**
     * constructor for building an subject with name and password
     */
    public SimpleAuthenticationSubject(String name, String pwd) {
        this();

        // create principal and credential based on these Strings
        this.setPrincipal(new SimplePrincipal(name));
        this.setCredential(new SimpleCredential(pwd));
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public Credential getCredential() {
        return credential;
    }

    public Locale getLocale() {
        return locale;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public User getUser() {
        return user;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

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

}

