package com.lotoquebec.cardexCommun.presentation.taglib.html;

import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.struts.Globals;

public class TagLibUtils {

	/**
	 * Assigner la valeur du token dans la liste de paramètre qui constitura le lien <a/>
	 * @param pageContext
	 * @param params
	 */
	public static void ajouterTokenParam(PageContext pageContext, Map params){
	      String token = (String) pageContext.getSession().getAttribute(Globals.TRANSACTION_TOKEN_KEY);
	      params.put(Globals.TOKEN_KEY, token);
	}
}
