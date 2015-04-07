package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheVehiculeHtmlForm;
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
 * crit�res sp�cifi�s.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $ $Date: 2002/04/22 18:25:01 $
 */

public class LinkRechercheVehiculeTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    private String criteres;


    public String getCriteres() {
      return this.criteres;
    }

    public void setCriteres(String criteres) {
      this.criteres = criteres;
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

      if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        this.setPage(null);
        this.setForward(null);
        this.setHref("#");
        return super.doStartTag();
      }

      log.debug("doStartTag()");
      log.debug("   page '" + this.page + "'");
      log.debug("   securityConstraint '" + this.securityConstraint + "'");

      // Cas sp�cial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }

      Map parameters = new HashMap();
      // Insere les criteres de recherche dossiers comme parametres de requete
      if (criteres != null ) {
        Object bean = pageContext.findAttribute(criteres);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+name+"' in LinkRechercheSujetTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }

        if (bean instanceof  CriteresRechercheVehiculeHtmlForm ){
            CriteresRechercheVehiculeHtmlForm criteresBean = (CriteresRechercheVehiculeHtmlForm)bean;
            parameters.put("immatriculation",criteresBean.getImmatriculation());
            parameters.put("numeroFiche", criteresBean.getNumeroFiche());
            parameters.put("province",criteresBean.getProvince());
            parameters.put("marque",criteresBean.getMarque());
            parameters.put("modele",criteresBean.getModele());
            parameters.put("confidentialite",criteresBean.getConfidentialite());
            parameters.put("particularite1",criteresBean.getParticularite1());
            parameters.put("particularite2",criteresBean.getParticularite2());
            parameters.put("particularite3",criteresBean.getParticularite3());
            parameters.put("particularite4",criteresBean.getParticularite4());
            parameters.put("particularite5",criteresBean.getParticularite5());
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for bean name '"+name+"' in LinkLiaisonVehiculeTag, the bean type must be '"+CriteresRechercheVehiculeHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
      }
      pageContext.setAttribute("vehicule.recherche.parameters",parameters);



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