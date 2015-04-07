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
 * propri�t�s cle, site, et mot de passe de la soci�t�
 * sp�cifi�.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkLiaisonSocieteTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    private String source;
    private String sourceProperty;
    private String societe;

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

    public String getSociete() {
      return this.societe;
    }

    public void setSociete(String societe) {
      this.societe = societe;
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
      SocieteHtmlForm societeBean = null;
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
      log.debug("   societe '" + this.societe + "'");

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
            JspException e = new JspException("Unable to find bean name '"+source+"' in LinkLiaisonSocieteTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (sourceProperty != null) {
          try {
              bean = PropertyUtils.getProperty(bean, sourceProperty);
              if (bean == null){
                JspException e = new JspException("Unable to find under bean name '"+source+"' and under property'"+sourceProperty+"'in LinkLiaisonSocieteTag.");
                tagUtils.saveException(pageContext, e);
                throw e;
              }
          } catch (IllegalAccessException e) {
              throw new JspException("IllegalAccess in the LinkLiaisonSocieteTag for the sourceProperty");
          } catch (InvocationTargetException e) {
              Throwable t = e.getTargetException();
              throw new JspException("NoSuchMethodException in the LinkLiaisonSocieteTag for the sourceProperty:" + t.toString());
          } catch (NoSuchMethodException e) {
              throw new JspException("NoSuchMethodException in the LinkLiaisonSocieteTag for the sourceProperty");
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
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for the bean specified by the source attribute '"+source+"' in LinkLiaisonSocieteTag, the bean  must be of type '"+DossierHtmlForm.class.getName()+"' or '"+SujetHtmlForm.class.getName()+"' or '"+SocieteHtmlForm.class.getName()+"' or '"+VehiculeHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
        log.debug(parameters.toString());
      }


      if (societe != null ) {
        Object bean = pageContext.findAttribute(societe);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name for the specified societe attrtibute '"+societe+"' in LinkLiaisonSocieteTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (bean instanceof  SocieteHtmlForm){
            societeBean = (SocieteHtmlForm)bean;
            parameters.put("cle",societeBean.getLien());
            parameters.put("site",societeBean.getLienSite());
            parameters.put("role",societeBean.getRole());
            parameters.put("typeLien",societeBean.getTypeLien());
            parameters.put("cleDestination",societeBean.getCle());
            parameters.put("siteDestination",societeBean.getSite());
            pageContext.setAttribute("societe.recherche.parameters",parameters);
            log.debug(parameters.toString());
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for the bean specified by the societe attribute '"+societe+"' in LinkLiaisonSocieteTag, the bean  must be of type '"+SocieteHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
        pageContext.setAttribute("societe.recherche.parameters",parameters);

      }


      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "societe.recherche.parameters", property, scope, transaction);
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