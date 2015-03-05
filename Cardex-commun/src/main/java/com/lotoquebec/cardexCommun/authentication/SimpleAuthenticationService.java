/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import java.util.logging.Logger;

import com.lotoquebec.cardexCommun.exception.AuthenticationException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * A simple version of the authentication service, authenticates
 * as long as the principal == credential, and populates no additional
 * information
 *
 * @author $Autor: $
 * @version $Revision: 1.4 $, $Date: 2002/02/05 20:31:05 $
 */
public class SimpleAuthenticationService
    implements AuthenticationService {
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * main method to provide the authentication service
     */
    public AuthenticationSubject authenticate(ModifiableAuthenticationSubject subject)
            throws AuthenticationException {
        if (subject != null) {

            // make sure principal == credential
            log.fine("principal : " + subject.getPrincipal());
            log.fine("credential: " + subject.getCredential());

            if (subject.getPrincipal().toString().equals(subject.getCredential().toString())) {

                // it's good enough.
                subject.setAuthenticated(true);
            } else {

                // set a message code into subject to be returned to the caller
                throw new AuthenticationException("Password not match");
            }
        }

        return subject;
    }

}

