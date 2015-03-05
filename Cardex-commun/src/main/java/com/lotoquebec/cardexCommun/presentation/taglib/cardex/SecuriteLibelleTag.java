package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.jsp.JspException;

import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.bean.MessageTag;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag affiche ou n'affiche pas l'étiquette celon la sécurité 
 */
public class SecuriteLibelleTag extends MessageTag {

    /**
     * La contrainte de sécurité applicable.
     */
    private String securityConstraint = "";
    private String urlSecurite = "";
	private String valeurTableValeur = "";
	private String actionSecurite = "";
	private String classe = "";
	private String valeurDiscriminant = "";
	private String valeur = "";
	private String nomFormulaire = "";
	private String propertyFormulaire = "";
	private boolean deuxPoints = true;
	private boolean gras = true;
	private static final String keyDeuxPoints = "2.points";
    

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

    public void setKey(String key) {
		this.key = key;
	}

	/**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		UIComponentState state = UIComponentState.ENABLED;

		try{
		  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
		    
		  	if (StringUtils.isNotEmpty(getSecurityConstraint()))
		  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
		  	
		  	else if (StringUtils.isNotEmpty(urlSecurite))
		  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, urlSecurite);

		  	else if (StringUtils.isNotEmpty(nomFormulaire) && StringUtils.isNotEmpty(propertyFormulaire)){
		        ModuleConfig config = (ModuleConfig) pageContext.getRequest().getAttribute(Globals.MODULE_KEY);
		  		state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, config.findFormBeanConfig(nomFormulaire).getType(), propertyFormulaire);

		  	}else if (StringUtils.isNotEmpty(classe)
		  	&& StringUtils.isNotEmpty(valeurTableValeur)){
		    	ListeCache listeCache = ListeCache.getInstance();
		    	TableValeurCleSQLListeCache tableValeurCleSQLListeCache = new TableValeurCleSQLListeCache(subject, valeurTableValeur, valeurDiscriminant, actionSecurite);
		  		
		  		if (StringUtils.isNotEmpty(valeur)
		  		&& listeCache.isValeurAccessible(subject, tableValeurCleSQLListeCache, valeur) == false)
					state = UIComponentState.HIDDEN;
		  		
		  		if (StringUtils.isEmpty(valeur) 
		  		&& listeCache.isUneValeurAccessible(subject, tableValeurCleSQLListeCache) == false)
					state = UIComponentState.HIDDEN;
			}
			
			if (UIComponentState.ENABLED.equals(state)
			|| UIComponentState.DISABLED.equals(state)){
				TagUtils tagUtils = TagUtils.getInstance();
				StringBuilder html = new StringBuilder();
				
				if (gras)
					html.append("<b>");
				
				html.append( tagUtils.message(pageContext, this.bundle, this.localeKey, key) );
				
				if (deuxPoints)
					html.append( tagUtils.message(pageContext, this.bundle, this.localeKey, keyDeuxPoints) );
				
				if (gras)
					html.append("</b>");
				
				tagUtils.write(pageContext, html.toString());
			}
		}
		catch (Throwable e) {}    	
    	
		return SKIP_BODY; // Il y a aucun body...
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

	public void setDeuxPoints(boolean deuxPoints) {
		this.deuxPoints = deuxPoints;
	}

	public void setGras(boolean gras) {
		this.gras = gras;
	}


}
