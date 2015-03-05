package com.lotoquebec.cardexCommun.presentation.taglib.cardex;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;

import com.lotoquebec.cardexCommun.model.SectionEscamotable;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class SectionEscamotableUtils {

	public static boolean isSectionOuvert(PageContext pageContext, String name, String property) throws JspException{
		
		if (StringUtils.isNotEmpty(name) || StringUtils.isNotEmpty(property)){
			TagUtils tagUtils = TagUtils.getInstance();
			SectionEscamotable sectionEscamotable = (SectionEscamotable) tagUtils.lookup(pageContext, name, property, null);
			return sectionEscamotable.isSectionOuverte();
		}
		return false;
	}
}
