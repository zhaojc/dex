/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import java.io.Serializable;
import java.security.Principal;
import java.util.Locale;


/**
 * An encapsulation of a subject.  Each possible user who has
 * been authenticated into this application will have his/her own
 * instance of an authentication subject that is active throughout
 * the duration of his/her session.  This authentication subject
 * instance provide the identify of the particular user, as well
 * as keeping a reference to the actual 'user' object that
 * represents the user, and keeps any privilege information
 * that to be needed to do any authorization checking.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/02/08 17:37:21 $
 */
public interface AuthenticationSubject extends Serializable {

    public Principal getPrincipal();
    public Credential getCredential();
    public Locale getLocale();
    public boolean isAuthenticated();
    public User getUser();
    public Privilege getPrivilege();
    public String getToken();
}

