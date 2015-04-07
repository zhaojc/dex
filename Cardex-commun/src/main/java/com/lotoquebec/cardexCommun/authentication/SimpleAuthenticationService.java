/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.exception.AuthenticationException;

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
        LoggerFactory.getLogger((this.getClass()));

    /**
     * main method to provide the authentication service
     */
    public AuthenticationSubject authenticate(ModifiableAuthenticationSubject subject)
            throws AuthenticationException {
        if (subject != null) {

            // make sure principal == credential
            log.debug("principal : " + subject.getPrincipal());
            log.debug("credential: " + subject.getCredential());

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

