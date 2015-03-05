package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

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
 * propriétés cle, site du véhicule
 * spécifié.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $ $Date: 2002/04/22 18:25:01 $
 */

public class LinkLiaisonVehiculeTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    private String source;
    private String vehicule;

    public String getSource() {
      return this.source;
    }

    public void setSource(String source) {
      this.source = source;
    }

    public String getVehicule() {
      return this.vehicule;
    }

    public void setVehicule(String vehicule) {
      this.vehicule = vehicule;
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
      VehiculeHtmlForm vehiculeBean = null;

      if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        this.setPage(null);
        this.setForward(null);
        this.setHref("#");
        return super.doStartTag();
      }

      log.fine("doStartTag()");
      log.fine("   vehicule '" + this.vehicule + "'");

      // Cas spécial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }

      // Insere le genre et la nature comme parametres de requete
      if (source != null && vehicule != null ) {

        //Insertion des parametres de requete relatifs a la source
        Object bean = pageContext.findAttribute(source);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+source+"' in LinkLiaisonVehiculeTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        Map parameters = new HashMap();
        if (bean instanceof  DossierHtmlForm){
            DossierHtmlForm dossierBean = (DossierHtmlForm)bean;
            parameters.put("cleSource",dossierBean.getCle());
            parameters.put("siteSource",dossierBean.getSite());
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
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for the bean specified by the source attribute '"+source+"' in LinkLiaisonVehiculeTag, the bean  must be of type '"+DossierHtmlForm.class.getName()+"' or '"+SujetHtmlForm.class.getName()+"' or '"+SocieteHtmlForm.class.getName()+"' or '"+VehiculeHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }

        //Insertion des parametres de requete relatifs au véhicule
        bean = pageContext.findAttribute(vehicule);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+vehicule+"' in LinkLiaisonVehiculeTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (bean instanceof  VehiculeHtmlForm){
            vehiculeBean = (VehiculeHtmlForm)bean;
            parameters.put("cle",vehiculeBean.getLien());
            parameters.put("site",vehiculeBean.getLienSite());
            parameters.put("role",vehiculeBean.getRole());
            parameters.put("typeLien",vehiculeBean.getTypeLien());
            parameters.put("cleDestination",vehiculeBean.getCle());
            parameters.put("siteDestination",vehiculeBean.getSite());
            pageContext.setAttribute("vehicule.recherche.parameters",parameters);
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for bean name '"+vehicule+"' in LinkLiaisonVehiculeTag, the bean type must be '"+VehiculeHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
        pageContext.setAttribute("vehicule.recherche.parameters",parameters);

      }


      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "vehicule.recherche.parameters", property, scope, transaction);
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