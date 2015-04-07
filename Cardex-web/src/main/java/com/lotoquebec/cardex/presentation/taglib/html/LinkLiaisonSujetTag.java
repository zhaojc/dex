package com.lotoquebec.cardex.presentation.taglib.html;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
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
 * @version $Revision: 1.6 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkLiaisonSujetTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    private String source;
    private String sourceProperty;
    private String sujet;

    public String getSource() {
      return this.source;
    }

    public String getSourceProperty() {
      return this.sourceProperty;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public void setSourceProperty(String property) {
      this.sourceProperty = property;
    }

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
      Map parameters = new HashMap();

      if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        this.setPage(null);
        this.setForward(null);
        this.setHref("#");
        return super.doStartTag();
      }

      log.debug("doStartTag()");
      log.debug("   sujet '" + this.sujet + "'");

      // Cas sp�cial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }

      // Insere le genre et la nature comme parametres de requete
      if (source != null ) {
        Object bean = pageContext.findAttribute(source);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+source+"' in LinkLiaisonSujetTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (sourceProperty != null) {
          try {
              bean = PropertyUtils.getProperty(bean, sourceProperty);
              if (bean == null){
                JspException e = new JspException("Unable to find under bean name '"+source+"' and under property'"+sourceProperty+"'in LinkLiaisonSujetTag.");
                tagUtils.saveException(pageContext, e);
                throw e;
              }
          } catch (IllegalAccessException e) {
              throw new JspException("IllegalAccess in the LinkLiaisonSujetTag for the sourceProperty");
          } catch (InvocationTargetException e) {
              Throwable t = e.getTargetException();
              throw new JspException("NoSuchMethodException in the LinkLiaisonSujetTag for the sourceProperty:" + t.toString());
          } catch (NoSuchMethodException e) {
              throw new JspException("NoSuchMethodException in the LinkLiaisonSujetTag for the sourceProperty");
          }
        }else {
        }

        if (bean instanceof  DossierHtmlForm){
            DossierHtmlForm dossier = (DossierHtmlForm)bean;
            parameters.put("cleSource",dossier.getCle());
            parameters.put("siteSource",dossier.getSite());
        }else if (bean instanceof SujetHtmlForm) {
            SujetHtmlForm sujet = (SujetHtmlForm)bean;
            parameters.put("cleSource",sujet.getCle());
            parameters.put("siteSource",sujet.getSite());
        }else if (bean instanceof SocieteHtmlForm) {
            SocieteHtmlForm societe = (SocieteHtmlForm)bean;
            parameters.put("cleSource",societe.getCle());
            parameters.put("siteSource",societe.getSite());
        }else if (bean instanceof VehiculeHtmlForm) {
            VehiculeHtmlForm vehicule = (VehiculeHtmlForm)bean;
            parameters.put("cleSource",vehicule.getCle());
            parameters.put("siteSource",vehicule.getSite());
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for the bean specified by the source attribute '"+source+"' in LinkLiaisonDossierTag, the bean  must be of type '"+DossierHtmlForm.class.getName()+"' or '"+SujetHtmlForm.class.getName()+"' or '"+SocieteHtmlForm.class.getName()+"' or '"+VehiculeHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
      }


      if (sujet != null ) {
        Object bean = pageContext.findAttribute(sujet);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name for the specified sujet attrtibute '"+sujet+"' in LinkLiaisonSujetTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (bean instanceof  SujetHtmlForm){
            sujetBean = (SujetHtmlForm)bean;
            parameters.put("cle",sujetBean.getLien());
            parameters.put("site",sujetBean.getLienSite());
            parameters.put("role",sujetBean.getRole());
            parameters.put("typeLien",sujetBean.getTypeLien());
            parameters.put("cleDestination",sujetBean.getCle());
            parameters.put("siteDestination",sujetBean.getSite());
            pageContext.setAttribute("sujet.recherche.parameters",parameters);
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for the bean specified by the sujet attribute '"+sujet+"' in LinkLiaisonSujetTag, the bean  must be of type '"+SujetHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
        pageContext.setAttribute("sujet.recherche.parameters",parameters);

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

}