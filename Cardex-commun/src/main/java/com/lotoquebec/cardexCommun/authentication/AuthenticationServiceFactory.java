/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.authentication;

import com.lotoquebec.cardexCommun.exception.AuthenticationException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.FabriqueDAO;

/**
 * Factory that construct AuthenticationService base
 * on the AuthenticationServiceFactory.properties
 * property file.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/02/08 17:37:21 $
 */
public class AuthenticationServiceFactory {

    /**
     * main method to provide the authentication service.
     *
     * Authentication is actually performed by the Web server so we just
     * retrieve the subject profile. We could re-authenticate again here
     * to protect from "direct" access to servlets, etc.
     */
    public static AuthenticationSubject authenticate(ModifiableAuthenticationSubject subject)
            throws AuthenticationException {
        if (subject != null) {
            String userName = subject.getPrincipal().getName();
            String token	= subject.getToken();
/*	        CardexUser user = new CardexUser(userName);
	        CardexPrivilege privilege = new CardexPrivilege();
	        subject.setUser(user);
	        subject.setPrivilege(privilege);
*/
            try {
              subject = FabriqueDAO.getInstance().getIntervenantDAO().find(userName);
              subject.setAuthenticated(true);
			  //On remet le token lu dans MenuAction dans le "subject" retourn� par "find"
			  subject.setToken(token);
            } catch (DAOException dae) {
                throw new AuthenticationException(dae);
            }
        }

        return subject;
    }

}

