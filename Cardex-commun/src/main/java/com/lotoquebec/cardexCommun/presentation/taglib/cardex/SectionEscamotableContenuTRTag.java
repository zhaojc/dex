package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;


/**
 * Ce tag défini dans la requête des attributs par rapport à la sécurité ClearTrust
 */
public class SectionEscamotableContenuTRTag extends SectionEscamotableTag {
	
	private String colSpan = "";
	private String sufixId = "";
	private String name = "";
	private String property = "";
	
    /**
     * Generate the required input tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
	public int doStartTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		html += generationTR();
		tagUtils.write(pageContext, html);
		
		if (isSecurityConstraint())
			return EVAL_BODY_INCLUDE;
		else
			return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		html += "</td>";
		html += "</tr>";
		tagUtils.write(pageContext, html);
		
		return EVAL_BODY_INCLUDE;
	}	
	
	private String generationTR() throws JspException{
		String html = "";
		
		if (SectionEscamotableUtils.isSectionOuvert(pageContext, name, property))
			html += "<tr id='sectionEscamotableContenuTr"+sufixId+"' style='display: ;'>";
		else
			html += "<tr id='sectionEscamotableContenuTr"+sufixId+"' style='display: none;'>";
			
		html += "<td id='sectionEscamotableContenuTd"+sufixId+"' colspan="+colSpan+">";
		
		return html;
	}
	
	// get / set
	public void setColSpan(String colSpan) {
		this.colSpan = colSpan;
	}
	
	public void setSufixId(String sufixId) {
		this.sufixId = sufixId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	
}
