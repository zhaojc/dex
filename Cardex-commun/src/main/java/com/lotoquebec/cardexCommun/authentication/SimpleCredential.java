/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

/**
 * A simple version of the authentication subject.
 *
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/02/01 21:06:38 $
 */
public class SimpleCredential implements Credential {
    protected String password = null;

    /**
     * default constructor to create a credential with a String password
     */
    public SimpleCredential(String password) {
        super();

        this.password = password;
    }

    /**
     * override the equals method to do our own comparison of credential
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SimpleCredential) {
            SimpleCredential another = (SimpleCredential) obj;

            if (password == null) {
                return another.password == null;
            } else {
                return password.equals(another.password);
            }
        }

        return false;
    }
    

    /**
     * override to have our own hashcode
     */
    public int hashCode() {
        return password.hashCode();
    }

    /**
     * override to provide something meaningful
     */
    public String toString() {
        return password;
    }

}

