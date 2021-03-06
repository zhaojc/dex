package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.taglib.html.LinkCardexTag;
import com.lotoquebec.cardexCommun.presentation.taglib.html.TagLibUtils;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Genere un hyperlink URL-encoded au URI sp�cifi� avec
 * les param�tres de query string correspondant aux
 * propri�t�s genre, et nature sp�cifi�.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkGalerieTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    private String genre;
    private String nature;


    public String getGenre() {
      return this.genre;
    }

    public String getNature() {
      return this.nature;
    }

    public void setGenre(String genre) {
      this.genre = genre;
    }

    public void setNature(String nature) {
      this.nature = nature;
    }

    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();

        if (isSecuriteValide() == false){
      	  return (SKIP_BODY);
        }

      log.fine("doStartTag()");
      log.fine("   genre '" + this.genre + "'");
      log.fine("   nature '" + this.nature + "'");
      log.fine("   page '" + this.page + "'");
      log.fine("   securityConstraint '" + this.securityConstraint + "'");

      // Cas sp�cial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }

      Map parameters = new HashMap();
      // Insere le genre comme parametre de requete
      if (genre != null) {
        parameters.put("genre",genre);
      }

      // Insere la nature comme parametre de requete
      if (nature != null) {
        parameters.put("nature",nature);
      }

      pageContext.setAttribute("photo.recherche.parameters",parameters);



      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "photo.recherche.parameters", property, scope, transaction);
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
    
    private boolean isSecuriteValide() {
    	ListeCache listeCache = ListeCache.getInstance();
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());

    	try {
    		boolean isURLAccessible = UIComponentState.ENABLED.equals( GestionnaireSecuriteCardex.obtenirURLUIComponentState(subject, getPage()) );
			boolean isRoleValideGenre = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, "", GlobalConstants.ActionSecurite.RECHERCHE_GALERIE), getGenre());
	    	boolean isRoleValideNature = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, "", GlobalConstants.ActionSecurite.RECHERCHE_GALERIE), getNature());
			return isURLAccessible && isRoleValideGenre && isRoleValideNature;
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			return true;
		}
	}
    
    public int doEndTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
    	String html = "";
    	html += "</a>";
    	tagUtils.write(pageContext, html.toString());
    	return (EVAL_BODY_INCLUDE);
    }

}