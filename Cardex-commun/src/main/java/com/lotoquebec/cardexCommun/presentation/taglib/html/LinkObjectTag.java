package com.lotoquebec.cardexCommun.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;



/**
 * Genere un hyperlink URL-encoded au URI sp�cifi� avec
 * les param�tres de query string correspondant aux
 * propri�t�s cle, site, et mot de passe du dossier
 * sp�cifi�.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkObjectTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    private String object;


    public String getObject() {
      return this.object;
    }

    public void setObject(String object) {
      this.object = object;
    }

    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
    	UIComponentState state = null;
    	
	  	if (StringUtils.isNotEmpty(getSecurityConstraint()))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
	  	else
	  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, getPage());
      
      if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        this.setPage(null);
        this.setForward(null);
        this.setHref("#");
        return super.doStartTag();
      }

      log.debug("doStartTag()");
      log.debug("   object '" + this.object + "'");

      // Cas sp�cial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }
      Map parameters = new HashMap();
      
      // Insere le genre et la nature comme parametres de requete
      if (StringUtils.isNotEmpty(object)) {
        Object bean = pageContext.findAttribute(object);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+name+"' in LinkObjectTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }
        parameters = retrieveQueryParameters(bean);
      }
      pageContext.setAttribute("object.parameters",parameters);

      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "object.parameters", property, scope, transaction);
      TagLibUtils.ajouterTokenParam(pageContext, params);
      String url = null;
      try {
          url = tagUtils.computeURL(pageContext, forward, href, page, action, module, params, anchor, false);
      } catch (MalformedURLException e) {
      	tagUtils.saveException(pageContext, e);
          throw new JspException
              (messages.getMessage("rewrite.url", e.toString()));
      }

      // Generation de la balise ouvrante de l'�l�ment anchor
      StringBuffer results = new StringBuffer("<a href=\"");
      results.append(url);
      results.append("\" ");
      results.append(prepareStyles());
      results.append(prepareEventHandlers());
      results.append(">");

      // Affichage de l'�l�ment dans le output writer
      tagUtils.write(pageContext, results.toString());

      // Evalaution du  body pour ce tag
      this.text = null;
      return (EVAL_BODY_INCLUDE);
    }

    public int doEndTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
    	String html = "";
    	html += "</a>";
    	tagUtils.write(pageContext, html.toString());
    	return (EVAL_BODY_INCLUDE);
    }


    private Map retrieveQueryParameters(Object bean) {
        Map queryParameters = new HashMap();
        String name = null;
        try {
         Map properties = BeanUtils.describe(bean);

         // Loop through the property name/value pairs to be set
         Iterator names = properties.keySet().iterator();
         while (names.hasNext()) {
             // Identify the property name and value(s) to be assigned
             name = (String) names.next();
             if (StringUtils.isNotEmpty(name)){
            	 
	             if (PropertyUtils.getPropertyType(bean,name) == String.class){
	                 Object value = properties.get(name);
	                 
	                 if (value != null && value.toString().length() < 50) {
	
	                   log.debug("Adding parameter '"+name+"' with value '"+value+"'.");
	                   queryParameters.put(name,value);
	                 }
	             } else if (PropertyUtils.getPropertyType(bean,name) == boolean.class){
	            	 Object value = properties.get(name);
	            	 log.debug("Adding parameter '"+name+"' with value '"+value+"'.");
	            	 queryParameters.put(name,value);
	             }
             }
         }//while

       }catch (Throwable e) {
         log.error("Unable to retrieve property '"+name+"' of the bean specified in LinkObjectTag.",e);
       }
       return queryParameters;
   }
}