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
 * Affiche un image.
 *
 * @see org.apache.struts.taglib.html.ImageTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $ $Date: 2002/03/11 22:23:25 $
 */
public class ImageCardexTag extends org.apache.struts.taglib.html.ImageTag {

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
     * Process the end of this tag.
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	    UIComponentState state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());

      if (UIComponentState.ENABLED.equals(state)){
        return super.doEndTag();
      }else if (UIComponentState.DISABLED.equals(state)){
        this.setDisabled(true);
        return super.doEndTag();
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
          return (EVAL_PAGE);
      }
        return (EVAL_PAGE);
    }


}
