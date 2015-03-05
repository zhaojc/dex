package com.lotoquebec.cardexCommun.presentation.taglib.html;


import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Tag pour les input fields de type "radio".
 *
 * @see org.apache.struts.taglib.html.RadioTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $ $Date: 2002/03/11 22:23:25 $
 */

public class RadioCardexTag extends org.apache.struts.taglib.html.RadioTag {

    /**
     * L'état par défaut si aucune règle de sécurité n'est applicable.
     */
    protected String defaultState= UIComponentState.ENABLED.toString();

    /**
     * Retourne l'état par dédauft si aucune règel de sécurité n'est applicable.
     */
    public String getDefaultState() {
        return (this.defaultState);
    }

    /**
     * Affecte l'état par dédauft si aucune règel de sécurité n'est applicable.
     *
     * @param securityConstraint La contrainte de sécurité applicable
     */
    public void setDefaultState(String state) {
        this.defaultState = state;
    }

    /**
     * La contrainte de sécurité applicable.
     */
    protected String securityConstraint= null;

    /**
     * Retourne la contrainte de sécurité
     */
    public String getSecurityConstraint() {
        return (this.securityConstraint);
    }

    /**
     * Affecte la contrainte de sécurité
     *
     * @param securityConstraint La contrainte de sécurité applicable
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
    	TagUtils tagUtils = TagUtils.getInstance();
	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	  	Object formulaire = tagUtils.lookup(pageContext, getName(), null);
	    UIComponentState state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, formulaire.getClass(), getProperty());

      if (UIComponentState.ENABLED.equals(state)){
        return super.doStartTag();
      }else if (UIComponentState.DISABLED.equals(state)){
        this.setDisabled(true);
        return super.doStartTag();
      } else if (UIComponentState.HIDDEN.equals(state)){
          // Create an appropriate "input" hidden element
          StringBuffer results = new StringBuffer("<input type=\"");
          results.append("hidden");
          results.append("\" name=\"");
          results.append(property);
          results.append("\"");
          results.append(" value=\"");
          
          if (StringUtils.isNotEmpty(value))
        	  results.append(ResponseUtils.filter(value.toString()));
          
          results.append("\"");
          results.append(">");
          // Print this field to our output writer
          tagUtils.write(pageContext, results.toString());
          return (SKIP_BODY);
      }
	return (EVAL_BODY_INCLUDE);
    }

}
