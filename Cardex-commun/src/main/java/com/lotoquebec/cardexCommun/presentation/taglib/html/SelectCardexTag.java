package com.lotoquebec.cardexCommun.presentation.taglib.html;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.ResponseUtils;

import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Custom tag qui représente un élément HTML select , associé avec une
 * propriété de bean spécifié  par les attributs.  Ce tag doit être imbriqué
 * dans un form tag.
 *
 * @see org.apache.struts.taglib.html.SelectTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $ $Date: 2002/04/24 17:39:18 $
 */
public class SelectCardexTag extends org.apache.struts.taglib.html.SelectTag {

    /**
     * L'état par défaut si aucune règle de sécurité n'est applicable.
     */
    protected String defaultState= UIComponentState.ENABLED.toString();
    
    private String id="";

    private UIComponentState state = null;
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
    	state = CardexTagUtil.getInstance().obtenirUIComponentState(pageContext, getName(), property, getSecurityConstraint());

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
          
          if (StringUtils.isNotEmpty(id)){
        	  results.append(" id=\"");
              results.append(id);
              results.append("\" ");  
          }
          
          results.append(" value=\"");
          if (value != null) {
            results.append(ResponseUtils.filter(value));
          } else {
              Object bean = pageContext.findAttribute(name);
              if (bean == null) {
                  JspException e = new JspException
                      (messages.getMessage("getter.bean", name));
                  tagUtils.saveException(pageContext, e);
                  throw e;
              }
              try {
                  String values[] = BeanUtils.getArrayProperty(bean, property);
                  if (values == null){
                    results.append("");
                  }else {
                    for (int i=0;i < values.length;i++){
                      results.append(values[i]);
                      if (i != (values.length-1)) {
                        results.append(",");
                      }
                    }
                  }
              } catch (IllegalAccessException e) {
              	tagUtils.saveException(pageContext, e);
                  throw new JspException
                      (messages.getMessage("getter.access", property, name));
              } catch (InvocationTargetException e) {
                  Throwable t = e.getTargetException();
                  tagUtils.saveException(pageContext, t);
                  throw new JspException
                      (messages.getMessage("getter.result",
                                           property, t.toString()));
              } catch (NoSuchMethodException e) {
              	tagUtils.saveException(pageContext, e);
                  throw new JspException
                      (messages.getMessage("getter.method", property, name));
              }
          }
          results.append("\"");
          results.append(">");
          // Print this field to our output writer
          tagUtils.write(pageContext, results.toString());
          return (SKIP_BODY);
      }
	return (EVAL_BODY_INCLUDE);
    }

    /**
     * Create an appropriate select start element based on our parameters.
     *
     * @throws JspException
     * @since Struts 1.1
     */
    protected String renderSelectStartElement()
        throws JspException {
        StringBuffer results = new StringBuffer("<select");

        prepareAttribute(results, "id", id);
        prepareAttribute(results, "name", prepareName());
        prepareAttribute(results, "accesskey", getAccesskey());

        if (multiple != null) {
            results.append(" multiple=\"multiple\"");
        }

        prepareAttribute(results, "size", getSize());
        prepareAttribute(results, "tabindex", getTabindex());
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        prepareOtherAttributes(results);
        results.append(">");

        return results.toString();
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

	public void setId(String id) {
		this.id = id;
	}
    
    
}
