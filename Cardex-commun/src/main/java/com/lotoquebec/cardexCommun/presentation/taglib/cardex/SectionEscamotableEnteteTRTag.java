package com.lotoquebec.cardexCommun.presentation.taglib.cardex;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Ce tag défini dans la requête des attributs par rapport à la sécurité ClearTrust
 */
public class SectionEscamotableEnteteTRTag extends SectionEscamotableTag {
	
	private String httpServletAjax = "";
	private String cle = "";
	private String site = "";
	private String sufixIdCible = "";
	private String onMouseOver = "";
	private String onMouseOut = "";
	private String name = "";
	private String property = "";
	private static String PLUS = "/images/plus.gif";
	private static String MOINS = "/images/moins.gif";
	
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
		
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		TagUtils tagUtils = TagUtils.getInstance();
		String html = "";
		html += "</tr>";
		tagUtils.write(pageContext, html);
		
		return EVAL_BODY_INCLUDE;
	}
	
	private String generationTR() throws JspException{
		String html = "";
		String context = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
		html += "<tr onMouseOver="+'"'+onMouseOver+'"'+" onMouseOut="+'"'+onMouseOut+'"'+">";
			html += "<td "+generationOnClick()+">";
			boolean isSectionOuvert = SectionEscamotableUtils.isSectionOuvert(pageContext, name, property);
			
			if (isSectionOuvert)
				html += "<img id='sectionEscamotableImg"+sufixIdCible+"' src='"+context+MOINS+"' />";
			else
				html += "<img id='sectionEscamotableImg"+sufixIdCible+"' src='"+context+PLUS+"' />";
			
			if (StringUtils.isNotEmpty(property))
				html += "<input type='hidden' name='"+property+".sectionOuverte' value='"+isSectionOuvert+"'/>";
			html += "</td>";
		
		return html;
	}
	
	private String generationOnClick(){
		String onClick = "";
		
		onClick += "onclick="+'"'+"basculerSectionEscamotable('"+property+".sectionOuverte', '"+sufixIdCible+"'); ";
		
		if ( isAjaxReady() ){
			String context = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
			String url = context + "/"+httpServletAjax + "?CLE=" + cle + "&SITE="+ site;
			onClick += "callAjaxTr('"+url+"', '"+sufixIdCible+"'); ";
		}		
		
		onClick += '"';
		return onClick;
	}
	
	private boolean isAjaxReady(){
		final boolean isSecurityConstraintOk = isSecurityConstraint();
		final boolean isHttpServletAjaxNotEmpty = StringUtils.isNotEmpty( httpServletAjax );
		final boolean isCleNotEmpty = StringUtils.isNotEmpty( cle );
		final boolean isSiteNotEmpty = StringUtils.isNotEmpty( site );
		final boolean isSufixIdCibleNotEmpty = StringUtils.isNotEmpty( sufixIdCible );
		
		return isSecurityConstraintOk && isHttpServletAjaxNotEmpty && isCleNotEmpty
		&& isSiteNotEmpty && isSufixIdCibleNotEmpty;
	}
	
	// get / set
	public void setCle(String cle) {
		this.cle = cle;
	}
	
	public void setHttpServletAjax(String httpServletAjax) {
		this.httpServletAjax = httpServletAjax;
	}
	
	public void setSufixIdCible(String sufixIdCible) {
		this.sufixIdCible = sufixIdCible;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public void setOnMouseOut(String onMouseOut) {
		this.onMouseOut = onMouseOut;
	}
	
	public void setOnMouseOver(String onMouseOver) {
		this.onMouseOver = onMouseOver;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	
	
}
