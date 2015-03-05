package com.lotoquebec.cardex.presentation.taglib.html;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSocieteForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSujetForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheVehiculeForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
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
 * @version $Revision: 1.8 $ $Date: 2002/04/22 18:25:01 $
 */
public class LinkObjectTag extends LinkCardexTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    private String object;


    public String getObject() {
      return this.object;
    }

    public void setObject(String object) {
      this.object = object;
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
    	
	  	if (StringUtils.isNotEmpty(getSecurityConstraint()))
	  		state = GestionnaireSecuriteCardex.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
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

      log.fine("doStartTag()");
      log.fine("   object '" + this.object + "'");

      // Cas spécial pour le nom anchors
      if (linkName != null) {
          StringBuffer results = new StringBuffer("<a name=\"");
          results.append(linkName);
          results.append("\">");
          tagUtils.write(pageContext, results.toString());
          return (EVAL_BODY_INCLUDE);
      }
      Map parameters = new HashMap();
      
      // Insere le genre et la nature comme parametres de requete
      if (StringUtils.isNotEmpty(object)) {
        Object bean = pageContext.findAttribute(object);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+name+"' in LinkObjectTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }
        parameters = retrieveQueryParameters(bean);
      }
      pageContext.setAttribute("object.parameters",parameters);

      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "object.parameters", property, scope, transaction);
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


    private Map retrieveQueryParameters(Object bean) {
         Map queryParameters = new HashMap();
         try {
          Map properties = BeanUtils.describe(bean);
          Set<Map.Entry> entrySet = properties.entrySet();
          
          for(Map.Entry entry:entrySet){
        	  String name = (String) entry.getKey();

              if (name != null && (PropertyUtils.getPropertyType(bean,name) == String.class
            		  || PropertyUtils.getPropertyType(bean,name).isAssignableFrom( long.class ) )){
                  Object value = entry.getValue();
                  if (value != null && value.toString().length() < 50) {

                    log.fine("Adding parameter '"+name+"' with value '"+value+"'.");
                    queryParameters.put(name,value);
                  }//if
             } else if (PropertyUtils.getPropertyType(bean,name) == boolean.class){
            	 Object value = properties.get(name);
            	 log.fine("Adding parameter '"+name+"' with value '"+value+"'.");
            	 queryParameters.put(name,value);
             }
          }
          if (bean instanceof CriteresRechercheDossierForm){
            CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm)bean;
            queryParameters.put("numeroCardex.site",criteresRechercheDossierForm.getNumeroCardex().getSite());
            queryParameters.put("numeroCardex.date",criteresRechercheDossierForm.getNumeroCardex().getDate());
            queryParameters.put("numeroCardex.sequence",criteresRechercheDossierForm.getNumeroCardex().getSequence());
            queryParameters.put("dossier.cle",criteresRechercheDossierForm.getDossier().getCle());
            queryParameters.put("dossier.site",criteresRechercheDossierForm.getDossier().getSite());
            queryParameters.put("sujet.cle",criteresRechercheDossierForm.getSujet().getCle());
            queryParameters.put("sujet.site",criteresRechercheDossierForm.getSujet().getSite());
            queryParameters.put("societe.cle",criteresRechercheDossierForm.getSociete().getCle());
            queryParameters.put("societe.site",criteresRechercheDossierForm.getSociete().getSite());
            queryParameters.put("vehicule.cle",criteresRechercheDossierForm.getVehicule().getCle());
            queryParameters.put("vehicule.site",criteresRechercheDossierForm.getVehicule().getSite());
          }else if (bean instanceof CriteresRechercheSujetForm){
            CriteresRechercheSujetForm criteresRechercheSujetForm = (CriteresRechercheSujetForm)bean;
            queryParameters.put("dossier.cle",criteresRechercheSujetForm.getDossier().getCle());
            queryParameters.put("dossier.site",criteresRechercheSujetForm.getDossier().getSite());
            queryParameters.put("sujet.cle",criteresRechercheSujetForm.getSujet().getCle());
            queryParameters.put("sujet.site",criteresRechercheSujetForm.getSujet().getSite());
            queryParameters.put("societe.cle",criteresRechercheSujetForm.getSociete().getCle());
            queryParameters.put("societe.site",criteresRechercheSujetForm.getSociete().getSite());
            queryParameters.put("vehicule.cle",criteresRechercheSujetForm.getVehicule().getCle());
            queryParameters.put("vehicule.site",criteresRechercheSujetForm.getVehicule().getSite());
          }else if (bean instanceof CriteresRechercheVehiculeForm){
            CriteresRechercheVehiculeForm criteresRechercheVehiculeForm = (CriteresRechercheVehiculeForm)bean;
            queryParameters.put("dossier.cle",criteresRechercheVehiculeForm.getDossier().getCle());
            queryParameters.put("dossier.site",criteresRechercheVehiculeForm.getDossier().getSite());
            queryParameters.put("sujet.cle",criteresRechercheVehiculeForm.getSujet().getCle());
            queryParameters.put("sujet.site",criteresRechercheVehiculeForm.getSujet().getSite());
            queryParameters.put("societe.cle",criteresRechercheVehiculeForm.getSociete().getCle());
            queryParameters.put("societe.site",criteresRechercheVehiculeForm.getSociete().getSite());
            queryParameters.put("vehicule.cle",criteresRechercheVehiculeForm.getVehicule().getCle());
            queryParameters.put("vehicule.site",criteresRechercheVehiculeForm.getVehicule().getSite());
          }else if (bean instanceof CriteresRechercheSocieteForm){
            CriteresRechercheSocieteForm criteresRechercheSocieteForm = (CriteresRechercheSocieteForm)bean;
            queryParameters.put("dossier.cle",criteresRechercheSocieteForm.getDossier().getCle());
            queryParameters.put("dossier.site",criteresRechercheSocieteForm.getDossier().getSite());
            queryParameters.put("sujet.cle",criteresRechercheSocieteForm.getSujet().getCle());
            queryParameters.put("sujet.site",criteresRechercheSocieteForm.getSujet().getSite());
            queryParameters.put("societe.cle",criteresRechercheSocieteForm.getSociete().getCle());
            queryParameters.put("societe.site",criteresRechercheSocieteForm.getSociete().getSite());
            queryParameters.put("vehicule.cle",criteresRechercheSocieteForm.getVehicule().getCle());
            queryParameters.put("vehicule.site",criteresRechercheSocieteForm.getVehicule().getSite());
          }else if (bean instanceof DossierForm){
            DossierForm dossierForm = (DossierForm)bean;
            queryParameters.put("numeroCardex.site",dossierForm.getNumeroCardex().getSite());
            queryParameters.put("numeroCardex.date",dossierForm.getNumeroCardex().getDate());
            queryParameters.put("numeroCardex.sequence",dossierForm.getNumeroCardex().getSequence());
            queryParameters.put("dossier.cle",dossierForm.getCle());
            queryParameters.put("dossier.site",dossierForm.getSite());
		 }else if (bean instanceof PSUMandatForm){
		   PSUMandatForm psuMandatForm = (PSUMandatForm)bean;
		   queryParameters.put("mandat.cle",psuMandatForm.getCle());
		   queryParameters.put("mandat.site",psuMandatForm.getSite());
		 }
        }catch (Throwable e) {
          LoggerCardex.severe(log,"Unable to retrieve property '"+name+"' of the bean specified in LinkObjectTag.",e);
        }
        return queryParameters;
    }
}