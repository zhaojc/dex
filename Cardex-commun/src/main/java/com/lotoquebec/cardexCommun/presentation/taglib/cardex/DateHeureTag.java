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
 * Ce tag sert à afficher un calendrier pour la date et l'heure
 * 
 * Requis
 * <jsp:include page='/scripts/scriptsCommun.jsp' flush="true"/>
 * 
 */
public class DateHeureTag extends BaseHandlerTag {
	
	private String name = "";
	private String property = "";
	private String securityConstraint = "";
	private String nomProchainChamp = "";
	private String calendrier = "false";
	private String tabindex = "";
	private String onChange = "";
	private String onBlur = "";
	private String onKeydown = "";	
	private String onFocusout = "";	
	private String onClick = "";
	private String id = "";
	
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
			html += generationInput();
		
		
		tagUtils.write( pageContext, html.toString() );

		return EVAL_BODY_INCLUDE;
	}
	
	private String generationInput() throws JspException{
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		
        String valeur = (String) tagUtils.lookup(pageContext, name, property, null);
		html += generationInputText( valeur );

		if (Boolean.valueOf(calendrier).booleanValue())
			html += generationCalendrier();
		
		return html;
	}
	
	private String generationInputText(String valeur){
		String html = "";
		
		if (StringUtils.isEmpty(valeur))
			valeur = "";
		//<input type='text' name="numeroCardex.date" size='10' maxlength='10' onkeyup="doTraits(this,'champSuivant');" onkeydown="return isNumericDate(event.keyCode);"/>
		html += "<input type='text' name='"+property+"' " +
				"onkeyup="+'"'+"doTraitsDateTAG(this, event.keyCode, '"+nomProchainChamp+"');"+'"'+" " +
				//On s'assure que des lettres ne sont pas saisies dans la date :
				"onkeydown="+'"'+"return isNumericDateHeureTag(event.keyCode);"+'"'+" " +
				//La date et l'heure sont formatés correctement lors du changement de champ :
				"onfocusout="+'"'+"formatDateHeure(this);"+'"'+" " +
				"onchange="+'"'+onChange+'"'+" " +
				"onblur="+'"'+onBlur+'"'+" " +
				"onClick="+'"'+onClick+'"'+" " +
				"value='"+valeur+"' tabindex='"+tabindex+"' size='19' maxlength='19' />";
		
		return html;
	}
	
	public String generationCalendrier(){
        /*<a href="javascript:openDateTime('document.forms(0).dateCreationDu', document.forms(0).dateCreationDu.value);" onmousedown="setXY(event.x + 110, event.y);"><img src="/cardex/images/cal.gif" border="0"></a>*/
		String html = "";
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		html += "<a href="+'"'+"javascript:openDateTime('document.forms(0)."+property+"', document.forms(0)."+property+".value);"+'"'+" " +
		"id='"+id+"' onmousedown="+'"'+"setXY(window.event.clientX+document.body.scrollLeft+160, window.event.clientY+document.body.scrollTop+10);"+'"'+"> " +
		"<img src='"+request.getContextPath()+"/images/cal.gif' border='0'/></a> ";
		return html;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	public void setSecurityConstraint(String securityConstraint) {
		this.securityConstraint = securityConstraint;
	}
	
	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	public void setNomProchainChamp(String nomProchainChamp) {
		this.nomProchainChamp = nomProchainChamp;
	}

	public void setCalendrier(String calendrier) {
		this.calendrier = calendrier;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public void setOnBlur(String onBlur) {
		this.onBlur = onBlur;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public void setId(String id) {
		this.id = id;
	}

}
