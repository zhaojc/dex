/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import java.security.Principal;
import java.util.Locale;

/**
 * Another type of authentication subject that is modifiable.  Ie.
 * have setter to its attributes in addition to just gtters.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/02/08 17:37:21 $
 */
public interface ModifiableAuthenticationSubject
    extends AuthenticationSubject {

    public void setPrincipal(Principal newValue);
    public void setCredential(Credential newValue);
    public void setLocale(Locale newValue);
    public void setAuthenticated(boolean newValue);
    public void setUser(User newValue);
    public void setPrivilege(Privilege newValue);
    //public void setToken(Token newValue);
    public void setToken(String newValue);
}

