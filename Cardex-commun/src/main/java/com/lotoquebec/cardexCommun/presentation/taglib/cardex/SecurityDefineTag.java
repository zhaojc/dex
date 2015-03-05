package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.jsp.JspException;

import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag défini dans la requête des attributs par rapport à la sécurité ClearTrust
 */
public class SecurityDefineTag extends BaseHandlerTag {

	public static final String ENABLED = "Enabled"; 
	public static final String HIDDEN = "Hidden";
	
    /**
     * La contrainte de sécurité applicable.
     */
    private String securityConstraint = "";
    private String urlSecurite = "";
    private String nameDefine = "";
	private String valeurTableValeur = "";
	private String actionSecurite = "";
	private String classe = "";
	private String valeurDiscriminant = "";
	private String valeur = "";
	private String nomFormulaire = "";
	private String propertyFormulaire = "";
    

    /**
     * Retourne la contrainte de sécurité
     */
    public String getSecurityConstraint() {
        return (this.securityConstraint);
    }

    /**
     * Affecte la contrainte de sécurité
     *
     * @param securityConstraint La contrainte de sécurité applicable
     */
    public void setSecurityConstraint(String securityConstraint) {
        this.securityConstraint = securityConstraint;
    }

	public String getNameDefine() {
		return nameDefine;
	}
	
	public void setNameDefine(String nameDefine) {
		this.nameDefine = nameDefine;
	}
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		UIComponentState state = UIComponentState.ENABLED;

	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	    
	  	if (StringUtils.isNotEmpty(getSecurityConstraint()))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
	  	
	  	else if (StringUtils.isNotEmpty(urlSecurite)){
	  		// lors d'une liste de URL, un seul accès suffit à afficher la section.  Voir écran des rapports
	  		for (String url:urlSecurite.split(",")){ 
	  			state = GestionnaireSecurite.obtenirURLUIComponentState(subject, url);
	  			
	  			if (UIComponentState.ENABLED.equals(state))
	  				break;
	  		}

	  	}else if (StringUtils.isNotEmpty(nomFormulaire) && StringUtils.isNotEmpty(propertyFormulaire)){
	        ModuleConfig config = (ModuleConfig) pageContext.getRequest().getAttribute(Globals.MODULE_KEY);
	        config.findFormBeanConfig(nomFormulaire).getType();
			state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, config.findFormBeanConfig(nomFormulaire).getType(), propertyFormulaire);

	  	}else if (StringUtils.isNotEmpty(classe)
	  	&& StringUtils.isNotEmpty(valeurTableValeur)){
	    	ListeCache listeCache = ListeCache.getInstance();
	    	TableValeurCleSQLListeCache tableValeurCleSQLListeCache = new TableValeurCleSQLListeCache(subject, valeurTableValeur, valeurDiscriminant, actionSecurite);
	  		
	  		try {
				if (StringUtils.isNotEmpty(valeur)
				&& listeCache.isValeurAccessible(subject, tableValeurCleSQLListeCache, valeur) == false)
					state = UIComponentState.HIDDEN;

				if (StringUtils.isEmpty(valeur) 
  		  		&& listeCache.isUneValeurAccessible(subject, tableValeurCleSQLListeCache) == false)
  					state = UIComponentState.HIDDEN;
				
			} catch (BusinessResourceException e) {
				throw new JspException(e);
			}
	  		
		}
		
		if (UIComponentState.ENABLED.equals(state)){
			pageContext.getRequest().setAttribute(nameDefine+HIDDEN, "false");
			pageContext.getRequest().setAttribute(nameDefine+ENABLED, "true");
		}else if (UIComponentState.DISABLED.equals(state)){
			pageContext.getRequest().setAttribute(nameDefine+HIDDEN, "false");
			pageContext.getRequest().setAttribute(nameDefine+ENABLED, "false");
		}else if (UIComponentState.HIDDEN.equals(state)){
			pageContext.getRequest().setAttribute(nameDefine+HIDDEN, "true");
			pageContext.getRequest().setAttribute(nameDefine+ENABLED, "false");
		}
  	
    	
		if (UIComponentState.ENABLED.equals(state) || UIComponentState.DISABLED.equals(state)){
			return (EVAL_BODY_INCLUDE);
		}
		
		return (SKIP_BODY);
    }

	public void setUrlSecurite(String urlSecurite) {
		this.urlSecurite = urlSecurite;
	}

	public void setValeurTableValeur(String valeurTableValeur) {
		this.valeurTableValeur = valeurTableValeur;
	}

	public void setActionSecurite(String actionSecurite) {
		this.actionSecurite = actionSecurite;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public void setValeurDiscriminant(String valeurDiscriminant) {
		this.valeurDiscriminant = valeurDiscriminant;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public void setNomFormulaire(String nomFormulaire) {
		this.nomFormulaire = nomFormulaire;
	}

	public void setPropertyFormulaire(String propertyFormulaire) {
		this.propertyFormulaire = propertyFormulaire;
	}


}
