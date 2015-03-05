package com.lotoquebec.cardex.presentation.taglib.html;

import java.lang.reflect.InvocationTargetException;
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
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.taglib.html.LinkCardexTag;
import com.lotoquebec.cardexCommun.presentation.taglib.html.TagLibUtils;


/**
 * Genere un hyperlink URL-encoded au URI spécifié avec
 * les paramètres de query string correspondant aux
 * propriétés cle, site, et mot de passe du dossier
 * spécifié.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mdemers $
 * @version $Revision: 1.2 $ $Date: 2002/04/12 14:24:56 $
 */
public class WriteObjectURLTag extends LinkCardexTag {

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

    private String objectProperty;


    public String getObjectProperty() {
      return this.objectProperty;
    }

    public void setObjectProperty(String property) {
      this.objectProperty = property;
    }

    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
      log.fine("doStartTag()");
      log.fine("   object '" + this.object + "'");

      // Cas spécial pour le nom anchors
      if (linkName != null) {
      	tagUtils.write(pageContext, "");
		return (EVAL_BODY_INCLUDE);
      }

      Object bean = null;
      // Insere le genre et la nature comme parametres de requete
      if (object != null ) {
        bean = pageContext.findAttribute(object);
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+name+"' in WriteObjectURLTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }
      }

      if (objectProperty != null) {
          try {
              bean = PropertyUtils.getProperty(bean, objectProperty);
              if (bean == null){
                JspException e = new JspException("Unable to find under bean name '"+object+"' and under property'"+objectProperty+"'in WriteObjectURLTag.");
                tagUtils.saveException(pageContext, e);
                throw e;
              }
          } catch (IllegalAccessException e) {
              throw new JspException("IllegalAccess in the WriteObjectURLTag for the property");
          } catch (InvocationTargetException e) {
              Throwable t = e.getTargetException();
              throw new JspException("NoSuchMethodException in the WriteObjectURLTag for the property:" + t.toString());
          } catch (NoSuchMethodException e) {
              throw new JspException("NoSuchMethodException in the WriteObjectURLTag for the property");
          }
      }

        Map parameters = retrieveQueryParameters(bean);
      
        pageContext.setAttribute("object.parameters",parameters);

      Map params = tagUtils.computeParameters
          (pageContext, paramId, paramName, paramProperty, paramScope,
           "object.parameters", property, scope, transaction);
      TagLibUtils.ajouterTokenParam(pageContext, params);
      
      String url = null;
      String vbCall = null;
      try {
          url = tagUtils.computeURLWithCharEncoding(pageContext, forward, href, page, action, module, params, anchor, false, true);
      } catch (MalformedURLException e) {
      	tagUtils.saveException(pageContext, e);
          throw new JspException
              (messages.getMessage("rewrite.url", e.toString()));
      }

      // Affichage de l'élément dans le output writer
      tagUtils.write(pageContext,url);

      // Evalaution du  body pour ce tag
      this.text = null;
	return (EVAL_BODY_INCLUDE);
    }


    private Map retrieveQueryParameters(Object bean) {
         Map queryParameters = new HashMap();
         try {
          Map properties = BeanUtils.describe(bean);

          Set<Map.Entry> entrySet = properties.entrySet();
          
          for(Map.Entry entry:entrySet){
        	  String name = (String) entry.getKey();

              if (name != null && (PropertyUtils.getPropertyType(bean,name) == String.class) ){
                  Object value = entry.getValue();
                  if (value != null && value.toString().length() < 50) {

                    log.fine("Adding parameter '"+name+"' with value '"+value+"'.");
                    queryParameters.put(name,value);
                  }//if
              }//if
          }//while
          if (bean instanceof CriteresRechercheDossierForm){
            CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm)bean;
            
            if (criteresRechercheDossierForm.getDossier() != null){
	            queryParameters.put("dossier.cle",criteresRechercheDossierForm.getDossier().getCle());
	            queryParameters.put("dossier.site",criteresRechercheDossierForm.getDossier().getSite());
            }
            
            if (criteresRechercheDossierForm.getSujet() != null){
	            queryParameters.put("sujet.cle",criteresRechercheDossierForm.getSujet().getCle());
	            queryParameters.put("sujet.site",criteresRechercheDossierForm.getSujet().getSite());
            }
            
            if (criteresRechercheDossierForm.getSociete() != null){
	            queryParameters.put("societe.cle",criteresRechercheDossierForm.getSociete().getCle());
	            queryParameters.put("societe.site",criteresRechercheDossierForm.getSociete().getSite());
            }
            
            if (criteresRechercheDossierForm.getVehicule() != null){
	            queryParameters.put("vehicule.cle",criteresRechercheDossierForm.getVehicule().getCle());
	            queryParameters.put("vehicule.site",criteresRechercheDossierForm.getVehicule().getSite());
            }
          }else if (bean instanceof CriteresRechercheSujetForm){
            CriteresRechercheSujetForm criteresRechercheSujetForm = (CriteresRechercheSujetForm)bean;
            
            if (criteresRechercheSujetForm.getDossier() != null){
	            queryParameters.put("dossier.cle",criteresRechercheSujetForm.getDossier().getCle());
	            queryParameters.put("dossier.site",criteresRechercheSujetForm.getDossier().getSite());
            }
            
            if (criteresRechercheSujetForm.getSujet() != null){
	            queryParameters.put("sujet.cle",criteresRechercheSujetForm.getSujet().getCle());
	            queryParameters.put("sujet.site",criteresRechercheSujetForm.getSujet().getSite());
            }
            
            if (criteresRechercheSujetForm.getSociete() != null){
	            queryParameters.put("societe.cle",criteresRechercheSujetForm.getSociete().getCle());
	            queryParameters.put("societe.site",criteresRechercheSujetForm.getSociete().getSite());
            }
            
            if (criteresRechercheSujetForm.getVehicule() != null){ 
	            queryParameters.put("vehicule.cle",criteresRechercheSujetForm.getVehicule().getCle());
	            queryParameters.put("vehicule.site",criteresRechercheSujetForm.getVehicule().getSite());
            }
          }else if (bean instanceof CriteresRechercheVehiculeForm){
            CriteresRechercheVehiculeForm criteresRechercheVehiculeForm = (CriteresRechercheVehiculeForm)bean;
            
            if (criteresRechercheVehiculeForm.getDossier() != null){
	            queryParameters.put("dossier.cle",criteresRechercheVehiculeForm.getDossier().getCle());
	            queryParameters.put("dossier.site",criteresRechercheVehiculeForm.getDossier().getSite());
            }
            
            if (criteresRechercheVehiculeForm.getSujet() != null){
	            queryParameters.put("sujet.cle",criteresRechercheVehiculeForm.getSujet().getCle());
	            queryParameters.put("sujet.site",criteresRechercheVehiculeForm.getSujet().getSite());
            }
            
            if (criteresRechercheVehiculeForm.getSociete() != null){
	            queryParameters.put("societe.cle",criteresRechercheVehiculeForm.getSociete().getCle());
	            queryParameters.put("societe.site",criteresRechercheVehiculeForm.getSociete().getSite());
            }
            
            if (criteresRechercheVehiculeForm.getVehicule() != null){
	            queryParameters.put("vehicule.cle",criteresRechercheVehiculeForm.getVehicule().getCle());
	            queryParameters.put("vehicule.site",criteresRechercheVehiculeForm.getVehicule().getSite());
            }
          }else if (bean instanceof CriteresRechercheSocieteForm){
            CriteresRechercheSocieteForm criteresRechercheSocieteForm = (CriteresRechercheSocieteForm)bean;
            
            if (criteresRechercheSocieteForm.getDossier() != null){
	            queryParameters.put("dossier.cle",criteresRechercheSocieteForm.getDossier().getCle());
	            queryParameters.put("dossier.site",criteresRechercheSocieteForm.getDossier().getSite());
            }
            
            if (criteresRechercheSocieteForm.getSujet() != null){
	            queryParameters.put("sujet.cle",criteresRechercheSocieteForm.getSujet().getCle());
	            queryParameters.put("sujet.site",criteresRechercheSocieteForm.getSujet().getSite());
            }
            
            if (criteresRechercheSocieteForm.getSociete() != null){
	            queryParameters.put("societe.cle",criteresRechercheSocieteForm.getSociete().getCle());
	            queryParameters.put("societe.site",criteresRechercheSocieteForm.getSociete().getSite());
            }
            
            if (criteresRechercheSocieteForm.getVehicule() != null){
	            queryParameters.put("vehicule.cle",criteresRechercheSocieteForm.getVehicule().getCle());
	            queryParameters.put("vehicule.site",criteresRechercheSocieteForm.getVehicule().getSite());
            }
          }
        }catch (Throwable e) {
          LoggerCardex.severe(log,"Unable to retrieve property '"+name+"' of the bean specified in WriteObjectURLTag.",e);
        }
        return queryParameters;
    }

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}