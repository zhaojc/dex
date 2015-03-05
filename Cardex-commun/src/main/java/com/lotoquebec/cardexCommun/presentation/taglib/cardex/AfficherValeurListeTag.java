package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListeUtil;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag défini dans la requête des attributs par rapport à la sécurité ClearTrust
 */
public class AfficherValeurListeTag extends BaseHandlerTag {
	
	private String name = "";
	private String property = "";
	private String classe = "";
	private String valeurTableValeur = "";
	private String valeurDiscriminant = "";
	private String actionSecurite = "";
	private String valeur = "";
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {

		try{
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.getSession().getAttribute(AuthenticationSubject.class.getName());
			TagUtils tagUtils = TagUtils.getInstance();
			
			if (StringUtils.isEmpty(valeur)){
			
				if (tagUtils.lookup(pageContext, name, null) == null)
					return (SKIP_BODY);  // Nothing to output

                // Look up the requested property value
		        valeur = String.valueOf(tagUtils.lookup(pageContext, name, property, null));
		    }
	        
			if (valeur == null || StringUtils.isEmpty( classe ))
	            return (SKIP_BODY);  // Nothing to output
			
	        String html = "";
	        String str = obtenirLabel(subject, valeur);

	        if (StringUtils.isNotEmpty( str ))
	        	html += str;
	        
			tagUtils.write( pageContext, html.toString() );

		}
		catch (Throwable e) {
			e.printStackTrace();
		}    	
		
		return (SKIP_BODY);
    }
	
	private String obtenirLabel(CardexAuthenticationSubject subject, String valeur) throws BusinessResourceException, ValueObjectMapperException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		ListeCache cache = ListeCache.getInstance();
		
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		CleListe cleListe = CleListeUtil.creerCleListe(subject, request, classe, valeurTableValeur, valeurDiscriminant, actionSecurite);

        // Si le discriminant est requis et qu'il n'est pas là, c'est la liste vide!
        if (cleListe.isDiscriminantValeurRequis() && cleListe.isDiscreminantVide())
        	return "";
        
		return cache.obtenirLabel(subject, valeur, cleListe);
	}
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getValeurDiscriminant() {
		return valeurDiscriminant;
	}
	public void setValeurDiscriminant(String valeurDiscriminant) {
		this.valeurDiscriminant = valeurDiscriminant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}

	public void setValeurTableValeur(String valeurTableValeur) {
		this.valeurTableValeur = valeurTableValeur;
	}

	public void setActionSecurite(String actionSecurite) {
		this.actionSecurite = actionSecurite;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
}
