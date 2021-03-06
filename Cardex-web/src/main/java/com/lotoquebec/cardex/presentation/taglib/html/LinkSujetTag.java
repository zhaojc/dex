package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.taglib.html.LinkCardexTag;
import com.lotoquebec.cardexCommun.presentation.taglib.html.TagLibUtils;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Genere un hyperlink URL-encoded au URI sp�cifi� avec
 * les param�tres de query string correspondant aux
 * propri�t�s cle, site, et mot de passe du sujet
 * sp�cifi�.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkSujetTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    private String sujet;


    public String getSujet() {
      return this.sujet;
    }

    public void setSujet(String sujet) {
      this.sujet = sujet;
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
    	
	  	if (StringUtils.isNotEmpty(securityConstraint))
	  		state = GestionnaireSecuriteCardex.obtenirAdhocUIComponentState(subject, securityConstraint);
	  	else
	  		state = GestionnaireSecuriteCardex.obtenirURLUIComponentState(subject, getPage());
      SujetHtmlForm sujetBean = null;

      if (UIComponentState.HIDDEN.equals(state)){
	return (EVAL_BODY_INCLUDE);
      }else if (UIComponentState.DISABLED.equals(state)) {
        this.setPage(null);
        this.setForward(null);
        this.setHref("#");
        return super.doStartTag();
      }

      log.fine("doStartTag()");
      log.fine("   sujet '" + this.sujet + "'");

      // Cas sp�cial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }

      // Insere le genre et la nature comme parametres de requete
      if (sujet != null ) {
        Object bean = pageContext.findAttribute(sujet);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+name+"' in LinkSujetTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (bean instanceof  SujetHtmlForm){
            sujetBean = (SujetHtmlForm)bean;
            Map parameters = new HashMap();
            parameters.put("cle",sujetBean.getCle());
            parameters.put("site",sujetBean.getSite());
            parameters.put("motPasse",sujetBean.getMotPasse());
            pageContext.setAttribute("sujet.recherche.parameters",parameters);
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for bean name '"+ name +"' in LinkSujetTag, the bean type must be '"+ SujetHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
      }


      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "sujet.recherche.parameters", property, scope, transaction);
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
      StringBuffer results = new StringBuffer("<a href=\"javascript:windowOpenLocation('"+url+"')\"");
      
      if (target != null) {
          results.append(" target=\"");
          results.append(target);
          results.append("\"");
      }
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

}