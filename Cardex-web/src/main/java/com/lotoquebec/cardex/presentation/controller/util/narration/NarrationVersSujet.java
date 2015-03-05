/*
 * Created on 11-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.narration;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class NarrationVersSujet {

	private final static String NOM_HTML_TABLE = "TablePersonne1"; // le chiffre, c'est la version de la table.
	// on ne prend pas de version de table Personne avec une version inférieur.
	
	public static boolean isContientFichePersonne(String narrationAvecFormat){
		return narrationAvecFormat.indexOf("<TABLE id="+NOM_HTML_TABLE) != -1;
	}
	
	public static SujetNarration obtenirSujetDeNarration(String narrationAvecFormat) throws ParserConfigurationException, SAXException, IOException{
		SujetXMLHandler xmlHandler = new SujetXMLHandler();
		HtmlParser htmlParser = new HtmlParser("TD");
		String xmlPersonne = obtenirXMLPersonne(narrationAvecFormat);
		htmlParser.parse(xmlPersonne, xmlHandler);
		
		return xmlHandler.getSujetNarration();
	}
	
	private static String obtenirXMLPersonne(String narrationAvecFormat){
		
		if (isContientFichePersonne(narrationAvecFormat) == false)
			return "";
		
		int start = narrationAvecFormat.indexOf("<TABLE id="+NOM_HTML_TABLE);
		int end = narrationAvecFormat.indexOf("</TABLE>", start);
		
		return StringUtils.mid(narrationAvecFormat, start, end);
	}
	
}
