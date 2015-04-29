package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.net.InetAddress;
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
import com.lotoquebec.cardexCommun.authentication.SimplePrincipal;
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
import com.lotoquebec.cardexCommun.util.StringUtils;

public class MenuAction extends Action {

    /**
     * L'instance du gestionnaire de journalisation.
     */
    private Logger      log =
        LoggerFactory.getLogger((this.getClass()));


    /**
     * Traite la requête HTTP spécifié, et crée la réponse HTTP correspondante
     * (ou redirige le controle vers un autre composant web).
     * Retourne une instance <code>ActionForward</code> qui décrit ou et comment
     * le contrôle doit être aiguillé, ou <code>null</code> si la réponse à déjà été
     * complétée.
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     *
     * @exception IOException si une erreur d'entrée/sortie if an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException {
        AuthenticationSubject subject = null;
        
        // Le profil utilisateur est extrait de la base de données Cardex
        // et la locale est initialisée
        log.debug("Authentification de l'utilisateur");
        
        try {
        		saveToken(request);
        		assignerMessageResourcesSession(request);
        	
                String userName = null;

        		//La première étape consiste à déterminer l'environnement, production ou développement
        		//On lit d'abord le poste de travail d'où provient la requête.
				InetAddress ia = java.net.InetAddress.getLocalHost();
				String host = ia.getHostName();
				log.debug("Host identifié : " + ia.getHostName());
        		
				if(request.getRemoteUser()==null ) {
					//Si c'est le cas, on ne peut pas aller plus loin.
                    String message = "Aucune variable n'a été trouvée. Authentification impossible...";
                    log.error(message);
                    return mapping.findForward("authentication");
				}else{
					userName = rechercheCodeCardex(request.getRemoteUser());
				}

				//On vérifie si un code a été retourné par Cardex :
				if(StringUtils.isEmpty(userName)){
                    String message = "Aucun code parent n'a été trouvé dans Cardex avec le code d'authentification : " + request.getRemoteUser();
                    log.error(message);
                    return mapping.findForward("erreurCodeParent");
				}
				subject = AuthenticationServiceFactory.authenticate(createAuthenticationSubject(userName, ""));
				
			    if (subject == null){
			    	log.error("Aucun utilisteur Cardex avec le code : "+userName);
			    	throw new AssertionError("Aucun usager Cardex userName:"+userName);
			    }
				
                log.debug("Est-ce que l'utilisateur '"+userName+"' est  authentifié: " + subject.isAuthenticated());
                
                if ( subject.isAuthenticated() ) {
                        // On �tablit de la session de l'application
                        establishSession(subject, request);
                        List<IntervenantVO> listeIntervenant = obtenirProfil(subject);
                        
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
                        		throw new SecurityException("Aucun profil n'a pas été trouvé pour l'utilisateur "+cardexUser.getCode());
                        	 }
        		} else {
        			// couldn't get authenticated...
        			// prepare error
        			String message = "L'utilisateur n'est pas authentifié ...";
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
            String message = "Problème pour charger le role "+((CardexUser)subject.getUser()).getCode();
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

    //Cette procédure sert à trouver l'appariement entre le code authentifé par KERBEROS et le code de profil unique
    //dans Cardex. La raison est qu'on peut trouver des codes identiques dans deux domaines séparés, mais dans
    //Cardex les codes doivent être uniques. La table aut_authentification sert à relier un code KERBEROS et son domaine
    //à son code Cardex.    
    private String rechercheCodeCardex(String codeAuthentifie) {
    	SecuriteDAO securiteDAO = new SecuriteDAO();
    	try {
			return securiteDAO.rechercheCodeCardex(codeAuthentifie.toUpperCase());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new SecurityException("Problème lors de la recherche du code Cardex");
		}
	}

    private List<IntervenantVO> obtenirProfil(AuthenticationSubject subject) {
    	SecuriteDAO securiteDAO = new SecuriteDAO();
    	CardexUser cardexUser = (CardexUser) subject.getUser();
    	try {
			return securiteDAO.obtenirListeProfils(cardexUser.getCode());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new SecurityException("Problème lors du chargement des profils");
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