/*
 * Created on 6-May-2008
 */
package com.lotoquebec.cardexCommun.authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.FabriqueDAO;

/**
 * @author levassc
 */
public class AutentificationCardex {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
    
	public AuthenticationSubject obtenirSubjet(HttpServletRequest request) {
		// Le profile utilisateur est extrait de la base de donn�es cardex
        // et la locale est initialis�
		AuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
		
		if (subject == null) {
			subject = obtenirRechercherSubjet(request);
			request.getSession().setAttribute(AuthenticationSubject.class.getName(),subject);
		}
        
		return subject;
	}    
    
	private AuthenticationSubject obtenirRechercherSubjet(HttpServletRequest request) {
		AuthenticationSubject subject = null;
		
		try {
			String token = "";
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				System.out.println("cookies != null");

				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					log.debug("Cookie : " + cookie.getName()
							+ " - Valeur : " + cookie.getValue());

					if (cookie.getName().equals("CTSESSION")) {
						token = cookie.getValue();
						//On d�code ensuite le jeton, pour �liminer les
						// caract�res
						//de contr�le et obtenir un jeton valide,
						// reconnaissable par ClearTrust.
						token = java.net.URLDecoder.decode(token, "UTF-8");
						log.debug("Jeton : " + token);
					}
				}
			}
			System.out.println("Token = " + token);
/* désactiver le token CT
			if (StringUtils.isNotEmpty(token)){
				String userName = SecurityManager.getInstance().getValeurs(token);
				
				System.out.println("userName = " + userName);
				
				subject = AuthenticationServiceFactory
					.authenticate(
							createAuthenticationSubject(userName, "", token));
				
                if ( subject.isAuthenticated() ) {
                    // On �tablit de la session de l'application
                    establishSession(subject, request);
                } else {
                    // couldn't get authenticated...
                    // prepare error
                    String message = "L'utilisateur n'est pas authentifi� ...";
                    log.error(message);
                }				
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subject;
	}
	
    /**
     * �tablit la session applicative , le sujet authentifi� est stock� dans
     * dans la session, la locale de pr�f�rence du sujet est �galement stock� dans
     * la session.
     */
    private void establishSession(AuthenticationSubject subject, HttpServletRequest request) {
            // save authentication into session...
            request.getSession().setAttribute(AuthenticationSubject.class.getName(),subject);
            System.out.print(AuthenticationSubject.class.getName());
            // create the application session and save it into http session
            request.getSession().setAttribute(Globals.LOCALE_KEY, subject.getLocale());
    }	

	private ModifiableAuthenticationSubject createAuthenticationSubject(String userName, String password, String token) {
        ModifiableAuthenticationSubject subject = new CardexAuthenticationSubject(userName, password, token);
        return subject;
    }

	/**
	 * Cet utilisateur est un utilisateur syst�me.  Il ne doit �tre utilis� que dans 
	 * le cadre d'un processus syst�me du Cardex (ex: diff�r�)
	 * @return
	 */
	public static CardexAuthenticationSubject construireCardexAuthenticationSubjectSystem(){
		try {
			return FabriqueDAO.getInstance().getIntervenantDAO().find("CARDEX");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
