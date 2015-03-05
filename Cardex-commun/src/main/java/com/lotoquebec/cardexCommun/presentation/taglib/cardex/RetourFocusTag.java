package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardexCommun.model.RetourFocus;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag créer le input type=text du autoCompleter
 */
public class RetourFocusTag extends BaseHandlerTag {
	
	private String name = "";
	private final static String scope = "session";
	private final static String NOM_CHAMP_RETOUR_FOCUS = "nomChampRetourFocus";
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		
		if (tagUtils.lookup(pageContext, name, scope) == null)
            return (SKIP_BODY);  // Nothing to output
		
        RetourFocus retourFocus =
            (RetourFocus) tagUtils.lookup(pageContext, name, scope);

        html += generationScriptFocus(retourFocus);
        html += generationInputHiddenFocus();
        
        tagUtils.write(pageContext, html);
		return SKIP_BODY;
	}
	
	/*
	 * Ce champs Hidden sert à conserver la valeur du champs qui a un focus
	 */
	private String generationInputHiddenFocus() {
		String html = "";
		
		html += "<input type='hidden' name='"+NOM_CHAMP_RETOUR_FOCUS+"' />";
		
		return html;
	}

	private String generationScriptFocus(RetourFocus retourFocus) {
		String html = "";
		
		html += "<SCRIPT language='JavaScript' type='text/javascript'>";
		
		// Cette méthode assigne le focus sur le champs passé en paramètre
		if (StringUtils.isNotEmpty( retourFocus.getNomChampRetourFocus() )){ 
			html += "window.onload=function(){";
			html += "var champ = document.getElementsByName('"+retourFocus.getNomChampRetourFocus()+"');";
			html += "dernierChamps = document.activeElement.name;";
			html += "if (champ != null && champ != '' && champ != 'undefined'){";
			html += "champ[0].focus();";
			html += "}};";
		}
		
		// Cette méthode converve dans le champs hidden le nom du champs à faire un focus.
		html += "document.onactivate=function(){";
		html += "if (document.activeElement != null && document.activeElement.name != null && document.activeElement.name != '' && document.activeElement.name != 'undefined'){";
		html += "dernierChamps = document.activeElement.name;";
		html += "document.getElementsByName('"+NOM_CHAMP_RETOUR_FOCUS+"')[0].value = document.activeElement.name;";
		html += "}};";
		html += "</script>";
		
		return html;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
