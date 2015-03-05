/*
 * Created on 31-Jul-2008
 */
package com.lotoquebec.cardexCommun.presentation.taglib.cardex;

import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;

/**
 * @author levassc
 */
public abstract class SectionEscamotableTag extends BaseHandlerTag{

	protected String securityConstraint = "";
	
	protected boolean isSecurityConstraint(){
		UIComponentState state = UIComponentState.ENABLED;

		try{
		  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
		    state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, securityConstraint);
			
			if (UIComponentState.ENABLED.equals(state)){
				return true;
			}else{
				return false;
			}
		}
		catch (Throwable e) {}    	
    	
		if (UIComponentState.ENABLED.equals(state)){
			return true;
		}
		
		return false;		
	}
	
	public void setSecurityConstraint(String securityConstraint) {
		this.securityConstraint = securityConstraint;
	}
}
