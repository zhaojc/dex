/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import com.lotoquebec.cardexCommun.exception.AuthenticationException;

/**
 * Interface that defines one simple <code>public
 * AuthenticationSubject authenticate(ModifiableAuthenticationSubject
 * subject)</code> method.  As one will notice, this denotes that
 * for any given authentication subject (that has not been
 * authenticated yet), ask some 'authentication service' to
 * 'authenticate it.  What we get back is an already modified
 * version of authentication subject - with additional
 * information filled in - such as User object and privilege,
 * and flag to let the call know that this subject 'has' now been
 * authenticated.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/02/08 17:37:21 $
 */
public interface AuthenticationService {

    /**
     * Simple <code>public AuthenticationSubject authenticate
     * </code> method.
     *
     * @param subject Subject to authenticate
     *
     * @return Authenticated subject
     *
     * @throws AuthenticationException
     */
    public AuthenticationSubject authenticate(ModifiableAuthenticationSubject subject)
            throws AuthenticationException;
}

