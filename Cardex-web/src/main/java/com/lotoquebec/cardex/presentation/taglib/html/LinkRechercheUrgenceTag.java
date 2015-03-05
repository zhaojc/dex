package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheUrgenceHtmlForm;
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
 * critères spécifiés.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mazzucr $
 * @version $Date: 2014/04/01 $
 */

public class LinkRechercheUrgenceTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
    private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

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
    public int doStartTag() throws JspException
    {
        TagUtils tagUtils = TagUtils.getInstance();
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject) pageContext.findAttribute(AuthenticationSubject.class.getName());
        UIComponentState state = null;

        if (StringUtils.isNotEmpty(securityConstraint))
            state = GestionnaireSecuriteCardex.obtenirAdhocUIComponentState(subject, securityConstraint);
        else
            state = GestionnaireSecuriteCardex.obtenirURLUIComponentState(subject, getPage());

        if (UIComponentState.HIDDEN.equals(state))
        {
            return (SKIP_BODY);
        }
        else if (UIComponentState.DISABLED.equals(state))
        {
            this.setPage(null);
            this.setForward(null);
            this.setHref("#");
            return super.doStartTag();
        }

        log.fine("doStartTag()");
        log.fine("   page '" + this.page + "'");
        log.fine("   securityConstraint '" + this.securityConstraint + "'");

        // Cas spécial pour le nom anchors
        if (linkName != null)
        {
            StringBuffer results = new StringBuffer("<a name=\"");
            results.append(linkName);
            results.append("\">");
            tagUtils.write(pageContext, results.toString());
            return (EVAL_BODY_INCLUDE);
        }

        Map parameters = new HashMap();
        // Insere les criteres de recherche dossiers comme parametres de requete
        if (criteres != null)
        {
            Object bean = pageContext.findAttribute(criteres);
            if (bean == null)
            {
                JspException e = new JspException("Unable to find bean name '" + name + "' in LinkRechercheUrgenceTag.");
                tagUtils.saveException(pageContext, e);
                throw e;
            }

            if (bean instanceof CriteresRechercheUrgenceHtmlForm)
            {
                CriteresRechercheUrgenceHtmlForm criteresBean = (CriteresRechercheUrgenceHtmlForm) bean;
                parameters.put("numeroDossier", criteresBean.getNumeroDossier());
                parameters.put("classeDescription", criteresBean.getClasseDescription());
                parameters.put("classe", criteresBean.getClasse());
                parameters.put("confidentialite", criteresBean.getConfidentialite());
                parameters.put("entite", criteresBean.getEntite());
                parameters.put("societe", criteresBean.getSociete());
                parameters.put("unite", criteresBean.getUnite());
                parameters.put("motif", criteresBean.getMotif());
                parameters.put("statut", criteresBean.getStatut());
                parameters.put("district", criteresBean.getDistrict());
                parameters.put("contact", criteresBean.getContact());
                parameters.put("contactPrenom", criteresBean.getContactPrenom());
                parameters.put("ville", criteresBean.getVille());
                parameters.put("evenement", criteresBean.getEvenement());
                parameters.put("fonction", criteresBean.getFonction());
                parameters.put("matricule", criteresBean.getMatricule());
            }
            else
            {
                JspException e = new JspException("Invalid type '" + bean.getClass().getName() + "' for bean name '" + name + "' in LinkLiaisonUrgenceTag, the bean type must be '" + CriteresRechercheUrgenceHtmlForm.class.getName() + "'.");
                tagUtils.saveException(pageContext, e);
                throw e;
            }
        }
        pageContext.setAttribute("urgence.recherche.parameters", parameters);

        Map params = tagUtils.computeParameters(pageContext, paramId, paramName, paramProperty, paramScope, "urgence.recherche.parameters", property, scope, transaction);
        TagLibUtils.ajouterTokenParam(pageContext, params);
        String url = null;
        try
        {
            url = tagUtils.computeURL(pageContext, forward, href, page, action, module, params, anchor, false);
        }
        catch (MalformedURLException e)
        {
            tagUtils.saveException(pageContext, e);
            throw new JspException(messages.getMessage("rewrite.url", e.toString()));
        }

        // Generation de la balise ouvrante de l'élément anchor
        StringBuffer results = new StringBuffer("<a href=\"javascript:windowOpenLocation('" + url + "')\"");

        if (target != null)
        {
            results.append(" target=\"");
            results.append(target);
            results.append("\"");
        }
        results.append(prepareStyles());
        results.append(prepareEventHandlers());
        results.append(">");

        // Affichage de l'élément dans le output writer
        tagUtils.write(pageContext, results.toString());

        // Evalaution du body pour ce tag
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