package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheSujetHtmlForm;
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
 * propri�t�s genre, et nature sp�cifi�.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkRechercheSujetTag extends LinkCardexTag {

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

        if (bean instanceof  CriteresRechercheSujetHtmlForm ){
            CriteresRechercheSujetHtmlForm criteresBean = (CriteresRechercheSujetHtmlForm)bean;
            parameters.put("alias",criteresBean.getAlias());
            parameters.put("caracteristique1",criteresBean.getCaracteristique1());
            parameters.put("caracteristique2",criteresBean.getCaracteristique2());
            parameters.put("caracteristique3",criteresBean.getCaracteristique3());
            parameters.put("caracteristique4",criteresBean.getCaracteristique4());
            parameters.put("caracteristique5",criteresBean.getCaracteristique5());
            parameters.put("confidentialite",criteresBean.getConfidentialite());
            parameters.put("ethnie",criteresBean.getEthnie());
            parameters.put("langue",criteresBean.getLangue());
            parameters.put("nom",criteresBean.getNom());
            parameters.put("nomPhonetique",criteresBean.getNomPhonetique());
            parameters.put("numeroFiche",criteresBean.getNumeroFiche());
            parameters.put("passeport",criteresBean.getPasseport());
            parameters.put("pays",criteresBean.getPays());
            parameters.put("prenomPhonetique",criteresBean.getPrenomPhonetique());
            parameters.put("province",criteresBean.getProvince());
            parameters.put("race",criteresBean.getRace());
            parameters.put("role",criteresBean.getRole());
            parameters.put("severite",criteresBean.getSeverite());
            parameters.put("severiteCasino",criteresBean.getSeveriteCasino());
            parameters.put("severiteAutres",criteresBean.getSeveriteAutres());
            parameters.put("sexe",criteresBean.getSexe());
            parameters.put("statut",criteresBean.getStatut());
            parameters.put("ville",criteresBean.getVille());
        }else {
              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for bean name '"+name+"' in LinkLiaisonSujetTag, the bean type must be '"+CriteresRechercheSujetHtmlForm.class.getName()+"'.");
              tagUtils.saveException(pageContext, e);
              throw e;
        }
      }
      pageContext.setAttribute("sujet.recherche.parameters",parameters);



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