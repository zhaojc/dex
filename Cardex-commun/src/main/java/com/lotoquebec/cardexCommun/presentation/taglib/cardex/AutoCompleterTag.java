package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag créer le input type=text du autoCompleter
 */
public class AutoCompleterTag extends BaseHandlerTag {
	
	private String name = "";
	private String property = "";
	private String nbrAmorce = "";
	private String classeControl = "";
	private String securityConstraint = "";
	private String height = "";
	private String width = "";
	private String tabindex = "";
	private String maxlength = "";
	private String style = "";
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
		Object formulaire = tagUtils.lookup(pageContext, name, null);
    	UIComponentState state = null;
    	
	  	if (StringUtils.isNotEmpty(securityConstraint))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, securityConstraint);
	  	else
	  		state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, formulaire.getClass(), property);
		String html = "";
		
		if (UIComponentState.ENABLED.equals(state))
			html += generationInputEtDiv();
		
		tagUtils.write( pageContext, html.toString() );

		return EVAL_BODY_INCLUDE;
	}
	
	private String generationInputEtDiv() throws JspException{
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		
        String valeur =
            (String) tagUtils.lookup(pageContext, name, property, null);
        
        if (StringUtils.isEmpty(valeur)) //Retrait du null
        	valeur="";
		html += generationInputText( valeur );
		html += "<div id='menuDiv"+property+"' style='overflow: auto; height:"+height+"px; " +
				"width: "+width+"px; position: absolute; background-color: white; " +
				"border-style:solid; border-width:1px; display: none; z-index: 2;' onmouseleave="+'"'+"cacherDiv('menuDiv"+property+"');cacherFrame('saisieAuto"+property+"');"+'"'+"/></div>";
		
		return html;
	}
	
	private String generationInputText(String valeur){
		String html = "";
		//String url = '<%= request.getContextPath() + "/vide.html"%>';
		html += "<script>var ct"+property+";</script>";
		html += "<input type='text' name='"+property+"' " +
		"onkeyup="+'"'+"ct"+property+"=autoCompleter('"+property+"', "+nbrAmorce+", '"+classeControl+"');"+'"'+" " +
		"onfocusout="+'"'+"onFocusOut(ct"+property+");"+'"'+" " +
		"value="+'"'+valeur+'"'+" tabindex='"+tabindex+"' maxlength='"+maxlength+"' " +
		"style='"+style+"'/><br>" +
		"<iframe id='saisieAuto"+property+"' style='position:absolute;visibility: hidden;height:150;width:150;' frameBorder='0' scrolling='no' src='"+((HttpServletRequest)pageContext.getRequest()).getContextPath()+"/vide.html'></iframe>";
		
		return html;
	}
	
	public void setClasseControl(String classeControl) {
		this.classeControl = classeControl;
	}
	
	public void setHeight(String height) {
		this.height = height;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNbrAmorce(String nbrAmorce) {
		this.nbrAmorce = nbrAmorce;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}
	
	public void setSecurityConstraint(String securityConstraint) {
		this.securityConstraint = securityConstraint;
	}
	
	public void setWidth(String width) {
		this.width = width;
	}
	
	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
	
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}
	
}
