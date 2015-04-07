/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.presentation.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * <p>Une <strong>Action</strong> abstraite qui redirige les requ�tes
 * vers une methode publique qui est specifi�e par la propri�t� parameter
 * d'un <strong>ActionMapping</strong>.  Cette Action est utilile pour combiner
 * plusieurs actions similaires dans une seule classe, et ainsi simplifier
 * le design de l'application.  Cette Action extrait �galement le profile
 * utilisateur de la base de donn�e du cardex, si celui-ci n'est pas pr�sent
 * dans la session utilisateur.  Le nom de l'utilisateur est d�termin� �
 * l'aide de l'objet Principal pr�sent dans l'objet HttpServletRequest.
 * L'Authentification est effectu� par un serveur cleartrust.
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
 * <p>L'action utilise la valeur de l'attribut parameter pour ex�cuter
 * la m�thode "perform" appropri�e , qui doit avoir la m�me signature
 * (autre que le nom de la m�thode "perform") de la m�thode standard
 * Action.perform().  Par exemple, on peut retrouver les trois m�thodes
 * suivante dans la m�me action:</p>
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
 * <p>et appel� l'une de ces m�thohdes avec les URL's suivants::</p>
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
        LoggerFactory.getLogger((this.getClass()));

    /**
     * L'instance de Class pour cette classe de <code>AbstractAction</code>.
     */
    private Class               clazz = this.getClass();

    /**
     * L'ensemble des objets Method qui ont fait l'objet d'introspection pour
     * cette classe, la cl� �tant le nom de la m�thode. Cette collection est
     * popul�e au fur et a mesure que les m�thodes sont appel� , de cette fa�on
     * l'introspection n'est ex�cut� qu'une seule fois par m�thode.
     */
    private HashMap             methods = new HashMap();

    /**
     * L'ensemble des types des arguments de la m�thode a appel� par r�flexion.
     * Ils sont les m�mes pour tous les appels de m�thode.
     */
    private Class               types[] = {
        CardexAuthenticationSubject.class, ActionMapping.class, ActionForm.class,
        HttpServletRequest.class, HttpServletResponse.class
    };

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
        
        //subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
        subject = (new AutentificationCardex()).obtenirSubjet(request);
        
        //On doit v�rifier si la connexion provient de la page de changement
		//du mot de passe. Si c'est le cas, "subject" n'a pas encore �t�
		//initialis�, ce qui provoque des erreurs.
        boolean isChangementMDP = "/changement".equals(mapping.getPath());

        if (isChangementMDP == false){
    		if (subject == null)
            	throw new IllegalArgumentException("Le subject ne peut �tre null");
            else{
    			try{
    				GestionnaireSecurite.validerSecuriteURL((CardexAuthenticationSubject) subject, mapping.getPath());
    	        } catch (SecurityException e) {
    				e.printStackTrace();
    				request.getSession().setAttribute("message", e.getMessage());
    				return mapping.findForward("errorSecurity");				
    	        }
            }
    		
			log.debug("beanMotPasse est null");
	          //Chargement des donn�es de r�f�rences en cache
	          try{
	              String instance = DAOConnection.getInstance().getConnection((CardexAuthenticationSubject) subject).getMetaData().getURL();
	              instance = instance.substring(18,instance.length());
	              request.getSession().setAttribute("instance",instance);
	          }catch (DAOException se) {
	            String message = "Incapacit� de charger les donn�e de r�f�rence ";
	            log.error(message,se);
	            return mapping.findForward("error");
	          } catch (SQLException e) {
		            log.error("SQLException",e);
		            return mapping.findForward("error");
			}

	          // Assigner de l'activit� � l'utilisateur
	  		((CardexAuthenticationSubject)subject).assignerActivite();
        }

		//afficherRequete(request);
		

          // Identification du nom de la m�thode vers laquelle redirige la requ�te
          log.debug("Identification de la m�thode vers laquelle redirige la requ�te");
          String methodName = mapping.getParameter();

          if (methodName == null) {
              String message =
                  "La propri�t� 'parameter' de l'ActionMapping '"
                  + mapping.getName() + "' n'est pas d�finie.";

              log.warn(message);
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
                  "La m�thode '" + methodName
                  + "' n'est pas implanter dans la classe '"
                  + this.getClass().getName() + "'.";

              log.warn(message);
              return mapping.findForward("error");
          }

          AideController.retraitXSS(form);
          
          // Redirection vers la m�thode appropri�
		ActionForward forward = forward((CardexAuthenticationSubject) subject,
				mapping, form, request, response, method);
		
		// Le Nested Diagnostic Contexts de log4j est effacer pour ce thread
		//NDC.remove();

		// L'instance ActionForward est retourn�
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
     * Redirige les requ�tes vers la methode qui est specifi�e.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param method La m�thode a invoqu�e
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
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
                "Une ClassCastException est survenue lors de l'invocation de la m�thode '"
                + method.getName() + "' de la classe '"
                + this.getClass().getName() + "'.";

            log.error(message);
            return mapping.findForward("error");
        } catch (IllegalAccessException e) {
            String message =
                "Une IllegalAccessException est survenue lors de l'invocation de la m�thode '"
                + method.getName() + "' de la classe '"
                + this.getClass().getName() + "'.";

            log.error("IllegalAccessException",message, e);
            return mapping.findForward("error");
        } catch (InvocationTargetException e) {
			
			if (e.getTargetException() instanceof SecurityException){
				e.printStackTrace();
				request.getSession().setAttribute("message",e.getTargetException().getMessage());
				return mapping.findForward("errorSecurity");				
			}
			
            String message =
                "Une InvocationTargetException est survenue lors de l'invocation de la m�thode '"
                + method.getName() + "' de la classe '"
                + this.getClass().getName() + "'.";

            ExceptionHandler.getInstance().traiterException((CardexAuthenticationSubject) subject, message, e);
            return mapping.findForward("error");
        }

        return forward;
    }

    /**
     * Introspection de la classe courante pour identifier la m�thode sp�cifi�e
     * qui accepte les m�me types de param�tres que la m�thode <code>perform()</code>.
     *
     * @param methodName Nom de la m�thode faisant l'onjet d'introspection
     *
     * @exception NoSuchMethodException si la m�thode ne peut �tre trouv�
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
     * Stocke les messages d'erreurs concernant les r�gles d'affaires.
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
    
    // � retirer lorsque la version de struts changera.
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
     * Stocke les messages d'erreurs concernant les erreurs syst�mes.
     */
    protected void handleBusinessResourceException(BusinessException be,ActionMessages errors, HttpServletRequest request) {
            ExceptionHandler.getInstance().traiterException(request, "BusinessResourceException", be);
            
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("erreur_message",be));
            saveErrors(request, errors);
		    request.getSession().setAttribute("message", be);
    }

    /**
     * Stocke les messages d'erreurs concernant les erreurs syst�mes.
     */
    protected void handleIteratorException(IteratorException ie,ActionMessages errors, HttpServletRequest request) {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
            		new ActionMessage("error.system",ie));
            
            ExceptionHandler.getInstance().traiterException(request, "IteratorException", ie);
            
            saveErrors(request, errors);
    }


    /**
     * Stocke les messages d'erreurs concernant les erreurs syst�mes.
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
		log.debug("rafraichir");
		return mapping.findForward("success");
	}
    
}

