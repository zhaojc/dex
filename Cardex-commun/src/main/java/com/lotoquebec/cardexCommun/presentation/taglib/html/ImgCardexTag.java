package com.lotoquebec.cardexCommun.presentation.taglib.html;


import javax.servlet.jsp.JspException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;


/**
 * Affiche un img tag
 *
 * @see org.apache.struts.taglib.html.ImgTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $ $Date: 2002/03/11 22:23:25 $
 */
public class ImgCardexTag extends org.apache.struts.taglib.html.ImgTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * L'�tat par d�faut si aucune r�gle de s�curit� n'est applicable.
     */
    protected String defaultState= UIComponentState.ENABLED.toString();

    /**
     * Retourne l'�tat par d�dauft si aucune r�gel de s�curit� n'est applicable.
     */
    public String getDefaultState() {
        return (this.defaultState);
    }

    /**
     * Affecte l'�tat par d�dauft si aucune r�gel de s�curit� n'est applicable.
     *
     * @param securityConstraint La contrainte de s�curit� applicable
     */
    public void setDefaultState(String state) {
        this.defaultState = state;
    }

    /**
     * La contrainte de s�curit� applicable.
     */
    protected String securityConstraint= null;

    /**
     * Retourne la contrainte de s�curit�
     */
    public String getSecurityConstraint() {
        return (this.securityConstraint);
    }

    /**
     * Affecte la contrainte de s�curit�
     *
     * @param securityConstraint La contrainte de s�curit� applicable
     */
    public void setSecurityConstraint(String securityConstraint) {
        this.securityConstraint = securityConstraint;
    }

    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	    UIComponentState state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
	    
      if (UIComponentState.ENABLED.equals(state)){
        return super.doStartTag();
      }else if (UIComponentState.DISABLED.equals(state)){
        this.setDisabled(true);
        return super.doStartTag();
      } else if (UIComponentState.HIDDEN.equals(state)){
          return (SKIP_BODY);
      }
	return (EVAL_BODY_INCLUDE);
    }
}
