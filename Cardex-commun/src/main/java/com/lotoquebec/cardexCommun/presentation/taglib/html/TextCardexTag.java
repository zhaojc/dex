package com.lotoquebec.cardexCommun.presentation.taglib.html;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Custom tag pour les input fields de type "text".
 *
 * @see org.apache.struts.taglib.html.TextTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $ $Date: 2002/03/11 22:23:25 $
 */

public class TextCardexTag extends org.apache.struts.taglib.html.TextTag {

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
    	UIComponentState state = CardexTagUtil.getInstance().obtenirUIComponentState(pageContext, getName(), property, getSecurityConstraint());
	    
	    if (StringUtils.isEmpty(value))
	    	value = (String) tagUtils.lookup(pageContext, getName(), getProperty(), null);
	    
	    if (value == null)
	    	value = "";
	    
      if (UIComponentState.ENABLED.equals(state)){
    	  TagUtils.getInstance().write(this.pageContext, this.renderInputElement());
          return EVAL_BODY_INCLUDE;
          
      }else if (UIComponentState.DISABLED.equals(state)){
    	  StringBuffer results = new StringBuffer(value);
    	  results.append(construireHidden());
    	  tagUtils.write(pageContext, results.toString());
    	  
      } else if (UIComponentState.HIDDEN.equals(state)){
          // Create an appropriate "input" hidden element
          StringBuffer results = construireHidden();
          // Print this field to our output writer
          tagUtils.write(pageContext, results.toString());
          return (SKIP_BODY);
      }
	return (EVAL_BODY_INCLUDE);
    }

	private StringBuffer construireHidden() {
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
		return results;
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
    
}
