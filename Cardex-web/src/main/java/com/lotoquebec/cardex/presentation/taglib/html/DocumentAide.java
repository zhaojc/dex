package com.lotoquebec.cardex.presentation.taglib.html;


import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag créer le Link d'aide
 */
public class DocumentAide extends BaseHandlerTag {
	
	private String securityConstraint = "";
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		
		CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	  	UIComponentState state = null;
	  	
	  	if (StringUtils.isNotEmpty(securityConstraint))
	  		state = GestionnaireSecuriteCardex.obtenirAdhocUIComponentState(subject, securityConstraint);

	  	if (UIComponentState.HIDDEN.equals(state))
			return (SKIP_BODY);
		
        html += generationLinkDocumentAide(subject);
        
        tagUtils.write(pageContext, html);
		return EVAL_BODY_INCLUDE;
	}
	
	private String generationLinkDocumentAide(AuthenticationSubject subject) {
		String html = "";
		String url = "";
		CardexUser cardexUser = (CardexUser) subject.getUser();
		if(cardexUser.getSousSecteur() == 0){
			url = "/Aide-Cardex/manuel "+cardexUser.getSecteur()+".pdf";
		}else{
			url = "/Aide-Cardex/manuel "+cardexUser.getSecteur()+"_"+cardexUser.getSousSecteur()+".pdf";
		}
		html += "<a href=\"javascript:windowOpenLocation('"+url+"')\"";
		html += "'>";
		return html;
	}

    public int doEndTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
    	String html = "";
    	html += "</a>";
    	tagUtils.write(pageContext, html.toString());
    	return (EVAL_BODY_INCLUDE);
    }

	public void setSecurityConstraint(String securityConstraint) {
		this.securityConstraint = securityConstraint;
	}
	
    
}
