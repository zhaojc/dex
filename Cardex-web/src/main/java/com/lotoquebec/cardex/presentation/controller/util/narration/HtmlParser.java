package com.lotoquebec.cardex.presentation.controller.util.narration;

import java.util.HashMap;
import java.util.Map;

import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * @author levassc
 */
public class HtmlParser {

	private String tag = "";
	
	public HtmlParser(String iTag) {
		super();
		tag = iTag;
	}
	
	public void parse(String html, HtmlHandler handler){
		String startTag = "<"+tag;
		String endTag = "</"+tag+">";
		
		int start = html.indexOf(startTag);
		int end = html.indexOf(endTag, start);
		
		while (start != -1) {
			String partHtml = StringUtils.mid(html, start, end-start+endTag.length());
			String valeur = obtenirValeur(partHtml);
			Map attributMap = obtenirAttribut(partHtml);
			handler.startElement(attributMap, valeur);
			start = html.indexOf(startTag, end);
			end = html.indexOf(endTag, start);
		}
	}

	private Map obtenirAttribut(String partHtml) {
		Map attributMap = new HashMap();
		int end = partHtml.indexOf(">");
		
		int space = partHtml.indexOf(" ");
		int equal = partHtml.indexOf("=");
		
		while (space != 0 && equal != 0 && space < end && equal < end) {
			String key = StringUtils.mid(partHtml, space+1, equal-space-1);
			
			if (key.indexOf(" ") != -1){
				int inSpace = key.indexOf(" ");
				space = partHtml.indexOf(" ", space+1);
				key = StringUtils.right(key, inSpace-1);
			}
			space = partHtml.indexOf(" ", space+1);
			
			// c'est la fin de l'element
			if (space > end || space == -1)
				space = end;
			String value = StringUtils.mid(partHtml, equal+1, space-equal-1);
			
			attributMap.put(key, value);
			equal = partHtml.indexOf("=", equal+1);
		}
		
		return attributMap;
	}

	private String obtenirValeur(String partHtml) {
		int start = partHtml.indexOf(">")+1;
		int end = partHtml.indexOf("</"+tag+">", start);
		
		return StringUtils.mid(partHtml, start, end-start);
	}
	
}
