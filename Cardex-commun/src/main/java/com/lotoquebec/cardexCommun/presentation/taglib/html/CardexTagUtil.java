package com.lotoquebec.cardexCommun.presentation.taglib.html;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class CardexTagUtil {

	private static CardexTagUtil cardexTagUtil = null;
	
	private CardexTagUtil(){}
	
	public static CardexTagUtil getInstance(){
		
		if (cardexTagUtil == null)
			cardexTagUtil = new CardexTagUtil();
		
		return cardexTagUtil;
	}
	
	public UIComponentState obtenirUIComponentState(PageContext pageContext, String name, String property, String securityConstraint) throws JspException{
    	TagUtils tagUtils = TagUtils.getInstance();
	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	  	Object formulaire = tagUtils.lookup(pageContext, name, null);
    	
	  	if (StringUtils.isNotEmpty(securityConstraint))
	  		return GestionnaireSecurite.obtenirAdhocUIComponentState(subject, securityConstraint);
	  	else
	  		return CardexTagUtil.getInstance().obtenirUIComponentState(subject, formulaire, property);
	}
	
    private UIComponentState obtenirUIComponentState(CardexAuthenticationSubject subject, Object formulaire, String property) throws JspException{
    	
    	if (property.indexOf("[") > -1 && property.indexOf("]") > -1){
	    	String propertyName = property.substring(0, property.indexOf("["));
	    	int index = Integer.valueOf( property.substring(property.indexOf("[")+1, property.indexOf("]")) );
	    	
    		try {
				formulaire = ((List)(new PropertyUtilsBean()).getProperty(formulaire, propertyName)).get(index);
				property = property.substring(property.indexOf("]")+2);
				return obtenirUIComponentState(subject, formulaire, property);
			} catch (IllegalAccessException e) {
				throw new JspException(e);
			} catch (InvocationTargetException e) {
				throw new JspException(e);
			} catch (NoSuchMethodException e) {
				throw new JspException(e);
			}
    	}else{
	  		return GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, formulaire.getClass(), property);
    	}
    }
}
