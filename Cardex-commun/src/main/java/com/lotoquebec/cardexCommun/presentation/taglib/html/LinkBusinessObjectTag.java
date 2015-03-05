package com.lotoquebec.cardexCommun.presentation.taglib.html;

import javax.servlet.jsp.JspException;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;

public class LinkBusinessObjectTag extends LinkCardexTag {

	private UIComponentState state = null;
	
     /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	    state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());

      if (UIComponentState.ENABLED.equals(state)){
	return (EVAL_BODY_INCLUDE);
      }else if (UIComponentState.DISABLED.equals(state)){
        this.setDisabled(true);
	return (EVAL_BODY_INCLUDE);
      } else if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }
	return (EVAL_BODY_INCLUDE);
    }

    /**
     * Process the end of this tag.
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

      if (UIComponentState.ENABLED.equals(state)){
        return (EVAL_PAGE);
      }else if (UIComponentState.DISABLED.equals(state)){
        this.setDisabled(true);
        return (EVAL_PAGE);
      } else if (UIComponentState.HIDDEN.equals(state)){
          return (EVAL_PAGE);
      }
        return (EVAL_PAGE);
    }

}