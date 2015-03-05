package com.lotoquebec.cardex.presentation.taglib.html;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.taglib.html.LinkCardexTag;
import com.lotoquebec.cardexCommun.presentation.taglib.html.TagLibUtils;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Genere un hyperlink URL-encoded au URI spécifié avec
 * les paramètres de query string correspondant aux
 * propriétés cle, site, et mot de passe du dossier
 * spécifié.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.9 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkLiaisonDossierTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    private String source;
    private String sourceProperty;
    private String dossier;

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

    public String getDossier() {
      return this.dossier;
    }

    public void setDossier(String dossier) {
      this.dossier = dossier;
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
      DossierHtmlForm dossierBean = null;
      Map parameters = new HashMap();

      if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        this.setPage(null);
        this.setForward(null);
        this.setHref("#");
        return super.doStartTag();
      }

      log.fine("doStartTag()");
      log.fine("   dossier '" + this.dossier + "'");

      // Cas spécial pour le nom anchors
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
            JspException e = new JspException("Unable to find bean name '"+source+"' in LinkLiaisonDossierTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (sourceProperty != null) {
          try {
              bean = PropertyUtils.getProperty(bean, sourceProperty);
              if (bean == null){
                JspException e = new JspException("Unable to find under bean name '"+source+"' and under property'"+sourceProperty+"'in LinkLiaisonDossierTag.");
                tagUtils.saveException(pageContext, e);
                throw e;
              }
          } catch (IllegalAccessException e) {
              throw new JspException("IllegalAccess in the LinkLiaisonDossierTag for the sourceProperty");
          } catch (InvocationTargetException e) {
              Throwable t = e.getTargetException();
              throw new JspException("NoSuchMethodException in the LinkLiaisonDossierTag for the sourceProperty:" + t.toString());
          } catch (NoSuchMethodException e) {
              throw new JspException("NoSuchMethodException in the LinkLiaisonDossierTag for the sourceProperty");
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


      if (dossier != null ) {
        Object bean = pageContext.findAttribute(dossier);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name for the specified dossier attrtibute '"+dossier+"' in LinkLiaisonDossierTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (bean instanceof  DossierHtmlForm){
            dossierBean = (DossierHtmlForm)bean;
            parameters.put("cle",dossierBean.getLien());
            parameters.put("site",dossierBean.getLienSite());
            parameters.put("role",dossierBean.getRole());
            parameters.put("typeLien",dossierBean.getTypeLien());
            parameters.put("cleDestination",dossierBean.getCle());
            parameters.put("siteDestination",dossierBean.getSite());
            pageContext.setAttribute("dossier.recherche.parameters",parameters);
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for the bean specified by the dossier attribute '"+dossier+"' in LinkLiaisonDossierTag, the bean  must be of type '"+DossierHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
        pageContext.setAttribute("dossier.recherche.parameters",parameters);

      }


      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "dossier.recherche.parameters", property, scope, transaction);
      TagLibUtils.ajouterTokenParam(pageContext, params);
      String url = null;
      try {
          url = tagUtils.computeURL(pageContext, forward, href, page, action, module, params, anchor, false);
      } catch (MalformedURLException e) {
      		tagUtils.saveException(pageContext, e);
          throw new JspException
              (messages.getMessage("rewrite.url", e.toString()));
      }

      // Generation de la balise ouvrante de l'élément anchor
      StringBuffer results = new StringBuffer("<a href=\"");
      results.append(url);
      results.append("\" ");
      results.append(prepareStyles());
      results.append(prepareEventHandlers());
      results.append(">");

      // Affichage de l'élément dans le output writer
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