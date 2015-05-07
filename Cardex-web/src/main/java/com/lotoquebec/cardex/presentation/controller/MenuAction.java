package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.presentation.model.form.IntervenantForm;
import com.lotoquebec.cardex.presentation.model.form.ProfilsForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.AuthenticationServiceFactory;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.ModifiableAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.IntervenantVO;
import com.lotoquebec.cardexCommun.exception.AuthenticationException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.FabriqueDAO;
import com.lotoquebec.cardexCommun.integration.dao.SecuriteDAO;
import com.lotoquebec.cardexCommun.user.CardexUser;

public class MenuAction extends Action {

    /**
     * L'instance du gestionnaire de journalisation.
     */
    private Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Traite la requ�te HTTP sp�cifi�, et cr�e la r�ponse HTTP correspondante
     * (ou redirige le controle vers un autre composant web).
     * Retourne une instance <code>ActionForward</code> qui d�crit ou et comment
     * le contr�le doit �tre aiguill�, ou <code>null</code> si la r�ponse � d�ja �t�
     * compl�t�e.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException {
        AuthenticationSubject subject = null;

        // Le profil utilisateur est extrait de la base de donn�es cardex
        // et la locale est initialis�e
        log.debug("Authentification de l'utilisateur");
        /*
        System.out.println("-------------------------------------"+request.getRemoteUser());
        System.err.println("-------------------------------------"+request.getRemoteUser());
        log.error("-------------------------------------"+request.getRemoteUser());
        log.info("-------------------------------------"+request.getRemoteUser());*/
        
        try {
        		saveToken(request);
        		assignerMessageResourcesSession(request);
        	
                String userName = null;
        		String token = "";
        		//La premi�re �tape consiste � d�terminer l'environnement, production ou d�veloppement
        		//On lit d'abord le poste de travail d'o� provient la requ�te.
				/*InetAddress ia = java.net.InetAddress.getLocalHost();
				String host = ia.getHostName();
				log.debug("Host identifi� : " + ia.getHostName());
				
				if(host.equals("Z500W19696") || host.equals("Z500W20037") || host.equals("Z500W19935")){ //Poste de d�veloppement
	                log.debug("Environnement de d�veloppement");
					//Dans ce cas, on cr�e un jeton pour le contr�le de la s�curit� avec ClearTrust.
					//token = SecurityManager.getInstance().getToken();
					//userName = "GUERINF"; //On initalise le code d'utilisateur pour aller chercher
										  //les valeurs dans la base de donn�es.
				}else{ //Production.
					//Dans ce cas, un jeton a automatiquement �t� cr�� apr�s l'authentification r�ussie
					//par ClearTrust. Ce jeton est plac� dans la liste des "cookies" et identifi�
					//par CTSESSION. 
	                Cookie[] cookies = request.getCookies();
	                log.debug("Environnement de production");
				    if (cookies != null) {
				        for (int i=0; i < cookies.length; i++) { 
				            Cookie cookie = cookies[i];
				            log.debug("Cookie : " + cookie.getName() + " - Valeur : " + cookie.getValue());
				            if (cookie.getName().equals("CTSESSION")) {
				            	token = cookie.getValue();
				            	//On d�code ensuite le jeton, pour �liminer les caract�res
				            	//de contr�le et obtenir un jeton valide, reconnaissable par ClearTrust.
			                    token = java.net.URLDecoder.decode(token, "UTF-8");
			                    log.debug("Jeton : " + token);
				            }
				        }
				    }
				}
				//� ce stade, un jeton doit obligatoirement avoir �t� trouv�, sinon on annule le
				//chargement de l'application. 
				//--> N.B.: un jeton est utilis� � la place du code d'utilisateur pour toutes les
				//v�rifications par ClearTrust de mani�re � pr�venir toute usurpation du code d'utilisateur 
				//de mani�re mal intentionn�e avec des outils sp�cifiques. En utilisant plut�t le jeton,
				//prot�g� par le mode SSL, il est impossible d'acc�der aux donn�es par l'application
				//de mani�re illicite. <--//
			    if(token == null || token.equals("")){
			    	
	                if (request.getHeader("ct-remote-user") != null ) {
	                    userName = request.getHeader("ct-remote-user");
	                    log.debug("Utilisation de la variable CT_REMOTE_USER pour s'authentifier au cardex : " + userName);
	                  }else if (request.getRemoteUser() != null ) {
	                    userName = request.getRemoteUser();
	                    log.debug("Utilisation de la variable REMOTE_USER pour s'authentifier au cardex : " + userName);
	                  }else if (request.getUserPrincipal() != null){
	                    userName = request.getUserPrincipal().getName();
	                    log.debug("Utilisation de la variable USER_PRINCIPAL pour s'authentifier au cardex : " + userName);
	                  }else {
	                    String message = "Aucune variable n'a �t� trouv�e. Authentification impossible...";
	                    log.error(message);
	                    return mapping.findForward("authentication");
	                }			    	
			    	
			    	log.debug("Aucun token userName:"+userName);
			    	subject = AuthenticationServiceFactory.authenticate(createAuthenticationSubject(userName, ""));
			    }else{
					//Une fois assur� d'un jeton valide, il faut �galement un code autoris�. 
					//On recherche le code � partir du jeton valide.
					//userName = SecurityManager.getInstance().getValeurs(token);
					
	                subject = AuthenticationServiceFactory.authenticate(createAuthenticationSubject(userName,"",token));					
		        }*/
        		subject = AuthenticationServiceFactory.authenticate(createAuthenticationSubject("AMAITRE", ""));
        		
			    if (subject == null){
			    	log.error("Aucun usager Cardex userName:"+userName+" token:"+token);
			    	throw new AssertionError("Aucun usager Cardex userName:"+userName+" token:"+token);
			    }
			    
                log.debug("Est-ce que l'utilisateur '"+userName+"' est  authentifié: " + subject.isAuthenticated());
                
                if ( subject.isAuthenticated() ) {
                        // On �tablit de la session de l'application
                        establishSession(subject, request);
                        List<IntervenantVO> listeIntervenant = obtenirProfil(subject);
                        
                        //ajouterRoles(subject, listeIntervenant.get(0));
                        
                        if (listeIntervenant.size() > 1){
                        	ProfilsForm profilForm = new ProfilsForm();
                	    	for(IntervenantVO intervenantVO:listeIntervenant){
                	    		IntervenantForm intervenantForm = new IntervenantForm(); 
                	    		ValueObjectMapper.convert(intervenantVO, intervenantForm);
                	    		profilForm.getListeProfils().add(intervenantForm);
                	    	}
                	    	request.setAttribute("profils",profilForm);
                        	return mapping.findForward("choisirProfil");
                        }else if (listeIntervenant.size() == 1){
                        		subject = FabriqueDAO.getInstance().getIntervenantDAO().find(listeIntervenant.get(0).getCode());

                        	 }else {
                        		CardexUser cardexUser = (CardexUser)subject.getUser();
                        		throw new SecurityException("Aucun profil n'a �t� trouv� pour l'utilisateur "+cardexUser.getCode());
                        	 }
        		} else {
        			// couldn't get authenticated...
        			// prepare error
        			String message = "L'utilisateur n'est pas authentifi� ...";
        			log.error(message);
        			return mapping.findForward("authentication");
        		}
        } catch ( AuthenticationException ae ) {
        		ae.printStackTrace();
                String message = "Le serveur d'authentification est hors service ...";
                log.error(message+" "+ae);
                return mapping.findForward("authentication");
                // something happend, let's handle it...
        } catch (DAOException e) {
            String message = "Probl�me pour charger le role "+((CardexUser)subject.getUser()).getCode();
            log.error(message,e);
			return mapping.findForward("error");
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (SQLException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}

		ProfilAction.assignerValeursAuLogon((CardexAuthenticationSubject) subject, request);

        // L'instance ActionForward est retourn�
        return mapping.findForward("menu");
    }

    private List<IntervenantVO> obtenirProfil(AuthenticationSubject subject) {
    	SecuriteDAO securiteDAO = new SecuriteDAO();
    	CardexUser cardexUser = (CardexUser) subject.getUser();
    	try {
			return securiteDAO.obtenirListeProfils(cardexUser.getCode());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new SecurityException("Probl�me lors du chargement des profils");
		}
	}

	/**
     * Cr�e un sujet authentifi�
     */
    private ModifiableAuthenticationSubject createAuthenticationSubject(String userName, String password) {
            ModifiableAuthenticationSubject subject = new CardexAuthenticationSubject(userName, password);
            return subject;
    }	
	
	private void assignerMessageResourcesSession(HttpServletRequest request) {
		MessageResources messageResources = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
		request.getSession().setAttribute(Globals.MESSAGES_KEY, messageResources);
	}

    /**
     * Cr�e un sujet authentifi�
     */
    private ModifiableAuthenticationSubject createAuthenticationSubject(String userName, String password, String token) {
            ModifiableAuthenticationSubject subject = new CardexAuthenticationSubject(userName, password, token);
            return subject;
    }

    
    /**
     * �tablit la session applicative , le sujet authentifi� est stock� dans
     * dans la session, la locale de pr�f�rence du sujet est �galement stock� dans
     * la session.
     * @throws DAOException 
     * @throws SQLException 
     */
    private void establishSession(AuthenticationSubject subject, HttpServletRequest request) throws DAOException, SQLException {
        // save authentication into session...
        request.getSession().setAttribute(AuthenticationSubject.class.getName(),subject);
        System.out.print(AuthenticationSubject.class.getName());
        // create the application session and save it into http session
        request.getSession().setAttribute(Globals.LOCALE_KEY, subject.getLocale());
        
        setLocale( request, subject.getLocale() );
        //Locale.setDefault(Locale.CANADA_FRENCH); // ne dois pas �tre forc�. Contrat AE anglais
        ServletContext sc = getServlet().getServletContext();         
        sc.setAttribute(Globals.LOCALE_KEY, subject.getLocale().toString());
        
        ExceptionHandler.registerExceptionHandler();
        Connection connection = DAOConnection.getInstance().getConnection((CardexAuthenticationSubject) subject);
        String instance = connection.getMetaData().getURL();
        instance = instance.substring(18,instance.length());
        request.getSession().setAttribute("instance",instance);
    }

    /**
     * Stocke les messages d'erreurs concernant les erreurs syst�mes.
     */
    protected void handleBusinessResourceException(BusinessResourceException be,ActionMessages errors, HttpServletRequest request) {
    	log.error("BusinessResourceException",be);
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("cardex_erreur_registre",be));
            saveErrors(request, errors);
    }

 


}