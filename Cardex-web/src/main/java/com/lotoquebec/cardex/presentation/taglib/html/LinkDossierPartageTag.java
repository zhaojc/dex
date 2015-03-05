package com.lotoquebec.cardex.presentation.taglib.html;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.taglib.html.LinkCardexTag;
import com.lotoquebec.cardexCommun.presentation.taglib.html.TagLibUtils;


/**
 * Genere un hyperlink URL-encoded au URI spécifié avec
 * les paramètres de query string correspondant aux
 * propriétés cle, site, et mot de passe du dossier
 * spécifié.
 *
 * Ici il n'y a pas de sécurité, car tout ce qui s'affiche dans le partage
 * est sécurisé au départ.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkDossierPartageTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    private String dossier;


    public String getDossier() {
      return this.dossier;
    }

    public void setDossier(String dossier) {
      this.dossier = dossier;
    }

    private String dossierProperty;


    public String getDossierProperty() {
      return this.dossierProperty;
    }

    public void setDossierProperty(String property) {
      this.dossierProperty = property;
    }

    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	  	DossierHtmlForm dossierBean = null;

		log.fine("doStartTag()");
		log.fine("   dossier '" + this.dossier + "'");

		// Insere le genre et la nature comme parametres de requete
		if (dossier != null ) {
			Object bean = pageContext.findAttribute(dossier);
			
			if (bean == null) {
				JspException e = new JspException("Unable to find bean name '"+dossier+"' in LinkDossierTag.");
				tagUtils.saveException(pageContext, e);
				throw e;
			}

			if (dossierProperty != null) {
				try {
					bean = PropertyUtils.getProperty(bean, dossierProperty);
					if (bean == null){
						JspException e = new JspException("Unable to find under bean name '"+dossier+"' and under property'"+dossierProperty+"'in LinkDossierTag.");
						tagUtils.saveException(pageContext, e);
						throw e;
					}
				} catch (IllegalAccessException e) {
					throw new JspException("IllegalAccess in the LinkDossierTag for the property");
				} catch (InvocationTargetException e) {
					Throwable t = e.getTargetException();
					throw new JspException("NoSuchMethodException in the LinkDossierTag for the property:" + t.toString());
				} catch (NoSuchMethodException e) {
					throw new JspException("NoSuchMethodException in the LinkDossierTag for the property");
				}
			}

	        if (bean instanceof  DossierHtmlForm){
	            dossierBean = (DossierHtmlForm)bean;
	            Dossier dossierVO = new DossierVO();
	            try {
					ValueObjectMapper.convertDossierHtmlForm(dossierBean, dossierVO, subject.getLocale());
				} catch (ValueObjectMapperException e) {
					e.printStackTrace();
				}
	            
	            Map parameters = new HashMap();
	            parameters.put("cle",dossierBean.getCle());
	            parameters.put("site",dossierBean.getSite());
	            parameters.put("motPasse",dossierBean.getMotPasse());
	            parameters.put("motPasseCourant",dossierBean.getMotPasse());
	            parameters.put("confirmationMotPasse",dossierBean.getConfirmationMotPasse());
	
	            HttpSession session = pageContext.getSession(); 
	            
	            pageContext.setAttribute("dossier.recherche.parameters",parameters);
	        }else {
	              JspException e = new JspException("Invalid type '"+bean.getClass().getName()+"' for bean name '"+name+"' in LinkDossierTag, the bean type must be '"+DossierHtmlForm.class.getName()+"'.");
	              tagUtils.saveException(pageContext, e);
	              throw e;
	        }
      }

      // Cas spécial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
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
      StringBuffer results = new StringBuffer("<a href=\"javascript:windowOpenLocation('"+url+"')\"");
      
      if (target != null) {
          results.append(" target=\"");
          results.append(target);
          results.append("\"");
      }
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