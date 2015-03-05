package com.lotoquebec.cardexCommun.presentation.taglib.html;


import javax.servlet.jsp.JspException;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Genere un hyperlink URL-encoded au URI sp�cifi�.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $ $Date: 2002/04/22 18:25:21 $
 */
public class LinkCardexTag extends org.apache.struts.taglib.html.LinkTag {

	private UIComponentState state = null;

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
     * 2009-04-14
     * La fonction a d� �tre chang�e avec la nouvelle librairie de Struts, car une valeur 
     * retourn�e par Struts faussait l'algorithme initial.
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
    	
	  	if (StringUtils.isNotEmpty(getSecurityConstraint()))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
	  	
	  	else if (StringUtils.isNotEmpty(getPage()))
	  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, getPage());
	  	else
	  		state = UIComponentState.ENABLED;
	  	
        if (UIComponentState.ENABLED.equals(state)){
          super.doStartTag();
          return EVAL_BODY_AGAIN;
        }else if (UIComponentState.DISABLED.equals(state)){
          this.setPage(null);
          this.setForward(null);
          this.setHref("#");
          super.doStartTag();
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
        return super.doEndTag();
      }else if (UIComponentState.DISABLED.equals(state)){
        this.setDisabled(true);
        return super.doEndTag();
      } else if (UIComponentState.HIDDEN.equals(state)){
        return (EVAL_PAGE);
      }
        return (EVAL_PAGE);
    }

}
