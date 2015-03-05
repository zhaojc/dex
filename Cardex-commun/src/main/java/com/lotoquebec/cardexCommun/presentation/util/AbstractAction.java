/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.presentation.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * <p>Une <strong>Action</strong> abstraite qui redirige les requêtes
 * vers une methode publique qui est specifiée par la propriété parameter
 * d'un <strong>ActionMapping</strong>.  Cette Action est utilile pour combiner
 * plusieurs actions similaires dans une seule classe, et ainsi simplifier
 * le design de l'application.  Cette Action extrait également le profile
 * utilisateur de la base de donnée du cardex, si celui-ci n'est pas présent
 * dans la session utilisateur.  Le nom de l'utilisateur est déterminé à
 * l'aide de l'objet Principal présent dans l'objet HttpServletRequest.
 * L'Authentification est effectué par un serveur cleartrust.
 *
 * <p>Voici un exemple de configuration dans le fichier
 * <code>struts-config.xml</code>, pour une action typique:</p>
 *
 * <code>
 *   &lt;action path="/deleteSubscription"
 *           type="org.apache.struts.actions.DispatchAction"
 *           name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *      parameter="delete"/&gt;
 *   &lt;action path="/insertSubscription"
 *           type="org.apache.struts.actions.DispatchAction"
 *           name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *      parameter="insert"/&gt;
 *   &lt;action path="/updateSubscription"
 *           type="org.apache.struts.actions.DispatchAction"
 *           name="subscriptionForm"
 *          scope="request"
 *          input="/subscription.jsp"
 *      parameter="update"/&gt;
 * </code>
 *
 * <p>L'action utilise la valeur de l'attribut parameter pour exécuter
 * la méthode "perform" appropriée , qui doit avoir la même signature
 * (autre que le nom de la méthode "perform") de la méthode standard
 * Action.perform().  Par exemple, on peut retrouver les trois méthodes
 * suivante dans la même action:</p>
 * <ul>
 * <li>public ActionForward delete(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws IOException, ServletException</li>
 * <li>public ActionForward insert(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws IOException, ServletException</li>
 * <li>public ActionForward update(ActionMapping mapping, ActionForm form,
 *     HttpServletRequest request, HttpServletResponse response)
 *     throws IOException, ServletException</li>
 * </ul>
 * <p>et appelé l'une de ces méthohdes avec les URL's suivants::</p>
 * <code>
 *   http://localhost:8080/myapp/deleteSubscription.do
 *   http://localhost:8080/myapp/insertSubscription.do
 *   http://localhost:8080/myapp/updateSubscription.do
 * </code>
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.29 $ $Date: 2002/04/23 21:08:51 $
 */
public abstract class AbstractAction extends Action {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * L'instance de Class pour cette classe de <code>AbstractAction</code>.
     */
    private Class               clazz = this.getClass();

    /**
     * L'ensemble des objets Method qui ont fait l'objet d'introspection pour
     * cette classe, la clé étant le nom de la méthode. Cette collection est
     * populée au fur et a mesure que les méthodes sont appelé , de cette façon
     * l'introspection n'est exécuté qu'une seule fois par méthode.
     */
    private HashMap             methods = new HashMap();

    /**
     * L'ensemble des types des arguments de la méthode a appelé par réflexion.
     * Ils sont les mêmes pour tous les appels de méthode.
     */
    private Class               types[] = {
        CardexAuthenticationSubject.class, ActionMapping.class, ActionForm.class,
        HttpServletRequest.class, HttpServletResponse.class
    };

    /**
     * Traite la requête HTTP spécifié, et crée la réponse HTTP correspondante
     * (ou redirige le controle vers un autre composant web).
     * Retourne une instance <code>ActionForward</code> qui décrit ou et comment
     * le contrôle doit être aiguillé, ou <code>null</code> si la réponse à déja été
     * complétée.
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException {
    	
        AuthenticationSubject subject = null;
        
        //subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
        subject = (new AutentificationCardex()).obtenirSubjet(request);
        
        //On doit vérifier si la connexion provient de la page de changement
		//du mot de passe. Si c'est le cas, "subject" n'a pas encore été
		//initialisé, ce qui provoque des erreurs.
        boolean isChangementMDP = "/changement".equals(mapping.getPath());

        if (isChangementMDP == false){
    		if (subject == null)
            	throw new IllegalArgumentException("Le subject ne peut être null");
            else{
    			try{
    				GestionnaireSecurite.validerSecuriteURL((CardexAuthenticationSubject) subject, mapping.getPath());
    	        } catch (SecurityException e) {
    				e.printStackTrace();
    				request.getSession().setAttribute("message", e.getMessage());
    				return mapping.findForward("errorSecurity");				
    	        }
            }
    		
			log.fine("beanMotPasse est null");
	          //Chargement des données de références en cache
	          try{
	              String instance = DAOConnection.getInstance().getConnection((CardexAuthenticationSubject) subject).getMetaData().getURL();
	              instance = instance.substring(18,instance.length());
	              request.getSession().setAttribute("instance",instance);
	          }catch (DAOException se) {
	            String message = "Incapacité de charger les donnée de référence ";
	            LoggerCardex.severe(log,message,se);
	            return mapping.findForward("error");
	          } catch (SQLException e) {
		            LoggerCardex.severe(log,e);
		            return mapping.findForward("error");
			}

	          // Assigner de l'activité à l'utilisateur
	  		((CardexAuthenticationSubject)subject).assignerActivite();
        }

		//afficherRequete(request);
		

          // Identification du nom de la méthode vers laquelle redirige la requête
          log.fine("Identification de la méthode vers laquelle redirige la requête");
          String methodName = mapping.getParameter();

          if (methodName == null) {
              String message =
                  "La propriété 'parameter' de l'ActionMapping '"
                  + mapping.getName() + "' n'est pas définie.";

              log.severe(message);
              return mapping.findForward("error");
          }
          
          if ("actionMethod".equals(methodName)){
          	methodName = (String) request.getParameter("actionMethod");
          }

          // Identification de l'objet Method
          Method method = null;

          try {
              method = getMethod(methodName);
          } catch (NoSuchMethodException e) {
              String message =
                  "La méthode '" + methodName
                  + "' n'est pas implanter dans la classe '"
                  + this.getClass().getName() + "'.";

              log.severe(message);
              return mapping.findForward("error");
          }

          AideController.retraitXSS(form);
          
          // Redirection vers la méthode approprié
		ActionForward forward = forward((CardexAuthenticationSubject) subject,
				mapping, form, request, response, method);
		
		// Le Nested Diagnostic Contexts de log4j est effacer pour ce thread
		//NDC.remove();

		// L'instance ActionForward est retourné
		return (forward);				
	}
    
    private void afficherRequete(HttpServletRequest request) {
		StringBuilder stringURL = new StringBuilder();
		stringURL.append(request.getContextPath());
		stringURL.append(request.getServletPath());
		StringBuilder stringParemetre = new StringBuilder();

		for(Object o : request.getParameterMap().entrySet()){
			Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) o;
				
			if (stringParemetre.length() == 0)
				stringParemetre.append("?");
			else
				stringParemetre.append("&");
			stringParemetre.append(entry.getKey());
			stringParemetre.append("=");
			stringParemetre.append(entry.getValue()[0]);
		}
		stringURL.append(stringParemetre);
		System.out.println("URL: "+stringURL.toString());
	}

	/**
     * Redirige les requêtes vers la methode qui est specifiée.
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param method La méthode a invoquée
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    private ActionForward forward(CardexAuthenticationSubject subject, ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  Method method) throws IOException,
                                  ServletException {
        ActionForward forward = null;

        try {
            Object args[] = {
                subject, mapping, form, request, response
            };

            forward = (ActionForward) method.invoke(this, args);
        } catch (ClassCastException e) {
            String message =
                "Une ClassCastException est survenue lors de l'invocation de la méthode '"
                + method.getName() + "' de la classe '"
                + this.getClass().getName() + "'.";

            log.severe(message);
            return mapping.findForward("error");
        } catch (IllegalAccessException e) {
            String message =
                "Une IllegalAccessException est survenue lors de l'invocation de la méthode '"
                + method.getName() + "' de la classe '"
                + this.getClass().getName() + "'.";

            LoggerCardex.severe(log,message, e);
            return mapping.findForward("error");
        } catch (InvocationTargetException e) {
			
			if (e.getTargetException() instanceof SecurityException){
				e.printStackTrace();
				request.getSession().setAttribute("message",e.getTargetException().getMessage());
				return mapping.findForward("errorSecurity");				
			}
			
            String message =
                "Une InvocationTargetException est survenue lors de l'invocation de la méthode '"
                + method.getName() + "' de la classe '"
                + this.getClass().getName() + "'.";

            ExceptionHandler.getInstance().traiterException((CardexAuthenticationSubject) subject, message, e);
            return mapping.findForward("error");
        }

        return forward;
    }

    /**
     * Introspection de la classe courante pour identifier la méthode spécifiée
     * qui accepte les même types de paramètres que la méthode <code>perform()</code>.
     *
     * @param methodName Nom de la méthode faisant l'onjet d'introspection
     *
     * @exception NoSuchMethodException si la méthode ne peut être trouvé
     */
    private Method getMethod(String methodName)
            throws NoSuchMethodException {
        synchronized (methods) {
            Method method = (Method) methods.get(methodName);

            if (method == null) {
                method = clazz.getMethod(methodName, types);

                methods.put(methodName, method);
            }

            return (method);
        }
    }

    /**
     * Stocke les messages d'erreurs concernant les règles d'affaires.
     */
    protected void handleBusinessException(BusinessException be,ActionMessages errors, HttpServletRequest request) {
            BusinessMessageResult messageResult =
                be.getBusinessMessageResult();
            Iterator              iterator =
                messageResult.getMessages().iterator();

            while (iterator.hasNext()) {
              BusinessMessage message = (BusinessMessage) iterator.next();
              String   key = message.getKey();
              Object[] args = message.getArguments().toArray();
              errors.add(ActionMessages.GLOBAL_MESSAGE,
              		createActionError(key, args));
            }
            saveErrors(request, errors);
    }

    protected void ajouterActionMessage(List<BusinessMessage> businessMessages,ActionMessages actionMessages, HttpServletRequest request) {
    	
    	for(BusinessMessage businessMessage:businessMessages){
    		actionMessages.add(ActionMessages.GLOBAL_MESSAGE, createActionMessage(businessMessage));
        }
        saveMessages(request, actionMessages);
}
    
    private ActionMessage createActionMessage(BusinessMessage businessMessage){
    	return new ActionMessage(businessMessage.getKey(), businessMessage.getArguments().toArray());
    }
    
    // à retirer lorsque la version de struts changera.
    private ActionMessage createActionError(String key, Object[] args){
    	
    	if (args.length == 1)
    		return new ActionMessage(key, args[0]);
    	
    	else if (args.length == 2)
    		return new ActionMessage(key, args[0], args[1]);

    	else if (args.length == 3)
    		return new ActionMessage(key, args[0], args[1], args[2]);

    	else if (args.length == 4)
    		return new ActionMessage(key, args[0], args[1], args[2], args[3]);

    	return new ActionMessage(key);
    }    
    
    /**
     * Stocke les messages d'erreurs concernant les erreurs systèmes.
     */
    protected void handleBusinessResourceException(BusinessException be,ActionMessages errors, HttpServletRequest request) {
            ExceptionHandler.getInstance().traiterException(request, "BusinessResourceException", be);
            
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("erreur_message",be));
            saveErrors(request, errors);
		    request.getSession().setAttribute("message", be);
    }

    /**
     * Stocke les messages d'erreurs concernant les erreurs systèmes.
     */
    protected void handleIteratorException(IteratorException ie,ActionMessages errors, HttpServletRequest request) {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
            		new ActionMessage("error.system",ie));
            
            ExceptionHandler.getInstance().traiterException(request, "IteratorException", ie);
            
            saveErrors(request, errors);
    }


    /**
     * Stocke les messages d'erreurs concernant les erreurs systèmes.
     */
    protected void handleValueObjectMapperException(ValueObjectMapperException voe, HttpServletRequest request) {
    	ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.system",voe));
        
        ExceptionHandler.getInstance().traiterException(request, "ValueObjectMapperException", voe);
        
        saveErrors(request, errors);
}
    
    protected void handleValueObjectMapperException(ValueObjectMapperException voe,ActionMessages errors, HttpServletRequest request) {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("error.system",voe));
            
            ExceptionHandler.getInstance().traiterException(request, "ValueObjectMapperException", voe);
            
            saveErrors(request, errors);
    }

    protected void verifierToken(HttpServletRequest request){
        if ( isTokenValid(request) == false ){
        	System.out.println("------> Erreur de token <------");
        	HttpSession session = request.getSession(false);
        	System.out.println("Session = "+session);
            String saved = (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
            String token = request.getParameter(Globals.TOKEN_KEY);
            System.out.println("saved = "+saved);
            System.out.println("token = "+token);
            if(StringUtils.isNotEmpty(request.getRemoteUser())){
            	System.out.println("utilisateur = "+request.getRemoteUser());
            }

        	throw new RuntimeException("Erreur de token");
        }
    }
    
    public ActionForward rafraichir(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
		log.fine("rafraichir");
		return mapping.findForward("success");
	}
    
}

