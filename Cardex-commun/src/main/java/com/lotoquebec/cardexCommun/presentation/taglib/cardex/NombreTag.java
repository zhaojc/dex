package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag créer le input nombre seulement
 */
public class NombreTag  extends org.apache.struts.taglib.html.TextTag {
	
	 /**
     * L'état par défaut si aucune règle de sécurité n'est applicable.
     */
    protected String defaultState= UIComponentState.ENABLED.toString();
    protected String onfocusout = "";

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

    public String getOnfocusout() {
		return onfocusout;
	}

	public void setOnfocusout(String onfocusout) {
		this.onfocusout = onfocusout;
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
		UIComponentState state = null;
    	
	  	if (StringUtils.isNotEmpty(securityConstraint))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, securityConstraint);
	  	else
	  		state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, formulaire.getClass(), getProperty());

      if (UIComponentState.ENABLED.equals(state)){
    	  TagUtils.getInstance().write(this.pageContext, this.renderInputElement());
          return EVAL_BODY_INCLUDE;
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

          results.append( obtenirOnFocusOut() );
          
          results.append("\"");
          results.append(">");
          // Print this field to our output writer
          tagUtils.write(pageContext, results.toString());
          return (SKIP_BODY);
      }
	return (EVAL_BODY_INCLUDE);
    }
    
    protected String renderInputElement() throws JspException {
	    StringBuffer results = new StringBuffer("<input");
	
	    prepareAttribute(results, "type", this.type);
	    prepareAttribute(results, "name", prepareName());
	    prepareAttribute(results, "accesskey", getAccesskey());
	    prepareAttribute(results, "accept", getAccept());
	    prepareAttribute(results, "maxlength", getMaxlength());
	    prepareAttribute(results, "size", getCols());
	    prepareAttribute(results, "tabindex", getTabindex());
	    
	    results.append( obtenirOnFocusOut() );
	    results.append( obtenirOnKeyDown() );
	    
	    prepareValue(results);
	    results.append(this.prepareEventHandlers());
	    results.append(this.prepareStyles());
	    prepareOtherAttributes(results);
	    results.append(this.getElementClose());
	
	    return results.toString();
	}    
    
    private String obtenirOnFocusOut(){
    	StringBuffer retour = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty( onfocusout )){
    		retour.append(" onfocusout='"+onfocusout+"' ");
    	}
    	return retour.toString();
    }
	
    private String obtenirOnKeyDown(){
    	StringBuilder onkeydown = new StringBuilder();
    	onkeydown.append("onkeydown=");
    	onkeydown.append('"');
    	onkeydown.append("return isNumericTag(event.keyCode);"+'"'+" ");
    	return onkeydown.toString();
    }
	
}
