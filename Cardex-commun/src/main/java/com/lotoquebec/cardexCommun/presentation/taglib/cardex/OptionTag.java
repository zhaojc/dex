package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.SelectTag;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListeUtil;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag défini dans la requête des attributs par rapport à la sécurité ClearTrust
 */
public class OptionTag extends BaseHandlerTag {
	
	private final Logger log = (Logger)LoggerCardex.getLogger(this.getClass());
	
	private String classe = "";
	private String valeurDiscriminant = "";
	private String valeurTableValeur = "";
	private String actionSecurite = "";
	private String filtrerOptionPredicatesList = "";
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		
		try{
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.getSession().getAttribute(AuthenticationSubject.class.getName());
			
	        SelectTag selectTag =
	            (SelectTag) pageContext.getAttribute(Constants.SELECT_KEY);
			
	        //List<LabelValueBean> labelValueBeans = obtenirListe(subject, classe);
	        Collection labelValueBeans = obtenirListe(subject, classe);
	        Iterator iter = labelValueBeans.iterator();
	        StringBuilder html = new StringBuilder();
	        
	        if (iter.hasNext()){
		        while (iter.hasNext()) {
					LabelValueBean label = (LabelValueBean) iter.next();
					html.append("<option value='"+label.getValue()+"' ");
					
					//if (value.equals( label.getValue() )){
					if (selectTag != null && selectTag.isMatched(label.getValue())){
						html.append("SELECTED='true' ");
					}
					html.append(">"+label.getLabel()+"</option>\r");
				}
	        }else{
				html.append("<option value='' SELECTED='true' ></option>\r");
	        }
	        
	        tagUtils.write(pageContext, html.toString());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}    	
		
		return (SKIP_BODY);
    }


	protected Collection obtenirListe(CardexAuthenticationSubject subject, String classe) throws BusinessResourceException, ValueObjectMapperException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JspException {
		ListeCache cache = ListeCache.getInstance();
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		CleListe cleListe = CleListeUtil.creerCleListe(subject, request, classe, valeurTableValeur, valeurDiscriminant, actionSecurite);
		List<Predicate> filtrerOptionPredicates = null;
		
        if (StringUtils.isNotEmpty(filtrerOptionPredicatesList))
        	filtrerOptionPredicates = (List<Predicate>) this.pageContext.getAttribute(filtrerOptionPredicatesList);

        // Si le discriminant est requis et qu'il n'est pas là, c'est la liste vide!
        if (cleListe.isDiscriminantValeurRequis() && cleListe.isDiscreminantVide())
        	return new ArrayList();
		
        if (cleListe instanceof TableValeurCleSQLListeCache){
	        //obtenir la valeur du tag supérieur
	        TagUtils tagUtils = TagUtils.getInstance();
	        
	        SelectTag selectTag = (SelectTag) pageContext.getAttribute(Constants.SELECT_KEY);        
	        Object oValeur = tagUtils.lookup(pageContext, selectTag.getName(), selectTag.getProperty(), null);

	        if (oValeur instanceof String)
	        	return cache.obtenirListe(subject, cleListe, (String) oValeur, filtrerOptionPredicates);
	        else if (oValeur instanceof String[])
	        	return cache.obtenirListe(subject, cleListe, (String[]) oValeur, filtrerOptionPredicates);
		}
    	return cache.obtenirListe(subject, cleListe, filtrerOptionPredicates);
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
	
	public void setValeurTableValeur(String valeurTableValeur) {
		this.valeurTableValeur = valeurTableValeur;
	}

	public void setActionSecurite(String actionSecurite) {
		this.actionSecurite = actionSecurite;
	}

	public void setFiltrerOptionPredicatesList(String filtrerOptionPredicatesList) {
		this.filtrerOptionPredicatesList = filtrerOptionPredicatesList;
	}
	
}
