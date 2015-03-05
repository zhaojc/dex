/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import java.io.Serializable;
import java.security.Principal;

/**
 * A simple version of the principal.
 *
 * @see
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/02/01 21:06:38 $
 */
public class SimplePrincipal implements Principal, Serializable {
    protected String username = null;

    /**
     * default constructor to create a principal with just a user name
     */
    public SimplePrincipal(String username) {
        this.username = username;
    }

    /**
     * override the equals method to do our own comparison of principals
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SimplePrincipal) {
            SimplePrincipal another = (SimplePrincipal) obj;

            if (username == null) {
                return another.username == null;
            } else {
                return username.equals(another.username);
            }
        }

        return false;
    }

    /**
     * override to have our own hashcode
     */
    public int hashCode() {
        return username.hashCode();
    }

    /**
     * provided by Principal interface
     */
    public String getName() {
        return username;
    }

    /**
     * override to provide something meaningful
     */
    public String toString() {
        return username;
    }

}

