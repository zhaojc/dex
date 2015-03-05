/*
 * Created on 8-Apr-2008
 */
package com.lotoquebec.cardex.presentation.taglib.html;

import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;

import com.lotoquebec.cardex.presentation.model.AdresseHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class AffichageAdresse extends BaseHandlerTag {

	private String name = "";
	private String numeroLigne = "";
	private String langueKey = "";
	
	public int doStartTag() throws JspException {
		AdresseHtmlForm adresseForm = obtenirAdresseHtmlForm();
		CardexAuthenticationSubject sujet = (CardexAuthenticationSubject)pageContext.getSession().getAttribute(AuthenticationSubject.class.getName());
		
		String sortie = construireAffichageAdresse(adresseForm, numeroLigne, sujet);
		
		TagUtils tagUtils = TagUtils.getInstance();
		tagUtils.write( pageContext, sortie.toString() );

		return (SKIP_BODY);
	}
	
	public static String construireAffichageAdresse(AdresseHtmlForm adresseForm, String numeroLigne, CardexAuthenticationSubject sujet) {
		String sortie = "";
		
		if ("1".equals(numeroLigne) ){
			
			if (GlobalConstants.Langue.FRANCAIS.equals( sujet.getLocale().getLanguage() )){
				sortie += obtenirAdresseFrFormatLigne1(sujet, adresseForm);
				
			} else if (GlobalConstants.Langue.ANGLAIS.equals( sujet.getLocale().getLanguage() )){
				sortie += obtenirAdresseEnFormatLigne1(sujet, adresseForm);
			}
			
		}else if ("2".equals(numeroLigne) )
			sortie += obtenirAdresseFormatLigne2(sujet, adresseForm);
		return sortie;
	}

	private AdresseHtmlForm obtenirAdresseHtmlForm(){
		AdresseHtmlForm adresseHtmlForm = (AdresseHtmlForm) pageContext.getSession().getAttribute( name );
		
		if (adresseHtmlForm != null)
			return adresseHtmlForm;
		
		adresseHtmlForm = (AdresseHtmlForm) pageContext.getAttribute(name);

		if (adresseHtmlForm != null)
			return adresseHtmlForm;
		
		return null;
	}
	
	private Locale obtenirLocale(){
		Locale locale = (Locale) pageContext.getSession().getAttribute( langueKey );
		
		if (locale != null)
			return locale;
		
		AuthenticationSubject sujet = (AuthenticationSubject)pageContext.getSession().getAttribute(AuthenticationSubject.class.getName());
		
		return sujet.getLocale();
	}
	
	private static String obtenirAdresseFrFormatLigne1(CardexAuthenticationSubject sujet, AdresseHtmlForm adresseForm){
		String sortie = "";
		
		sortie += formerElementAdresseVirgule( adresseForm.getNumeroMunicipal() );
		sortie += formerElementAdresseMap(sujet, adresseForm.getTypeRue(), new TypeRueCleListeCache(GlobalConstants.Langue.FRANCAIS));
		sortie += formerElementAdresse( adresseForm.getNomRue() );
		sortie += formerElementAdresseMap(sujet, adresseForm.getPointCardinal(), new CardinaliteCleListeCache(GlobalConstants.Langue.FRANCAIS));
		
		return sortie;
	}
	
	private static String obtenirAdresseEnFormatLigne1(CardexAuthenticationSubject sujet, AdresseHtmlForm adresseForm){
		String sortie = "";
		
		sortie += formerElementAdresseVirgule( adresseForm.getNumeroMunicipal() );
		sortie += formerElementAdresse( adresseForm.getNomRue() );
		sortie += formerElementAdresseMap(sujet, adresseForm.getPointCardinal(), new CardinaliteCleListeCache(GlobalConstants.Langue.ANGLAIS));
		sortie += formerElementAdresseMap(sujet, adresseForm.getTypeRue(), new TypeRueCleListeCache(GlobalConstants.Langue.ANGLAIS));
		
		return sortie;
	}	

	private static String obtenirAdresseFormatLigne2(CardexAuthenticationSubject sujet, AdresseHtmlForm adresseForm){
		String sortie = "";
		sortie += formerElementAdresseMap(sujet, adresseForm.getUnite(), new TypeUniteCleListeCache(GlobalConstants.Langue.FRANCAIS));
		sortie += formerElementAdresse( adresseForm.getNumeroUnite() );
		
		if (StringUtils.isNotEmpty( sortie ))
			sortie = "<BR>"+sortie;
		
		return sortie;
	}
	
	private static String formerElementAdresseMap(CardexAuthenticationSubject sujet, String numero, CleListe cleListe){
		
		if (StringUtils.isNotEmpty( numero )){
			ListeCache listeCache = ListeCache.getInstance();
			try {
				Map map = listeCache.obtenirMap(sujet, cleListe);
				LabelValueBean valueBean = (LabelValueBean) map.get( numero );
				
				if (valueBean != null)
					return formerElementAdresse( valueBean.getLabel() );
			} catch (BusinessResourceException e) {
				e.printStackTrace();
				throw new AssertionError("Problème dans formerElementAdresseMap");
			}
		}
		return "";
	}

	private static String formerElementAdresseVirgule(String element){
		
		if (StringUtils.isNotEmpty( element ))
			return element + ", ";
		return "";
	}
	
	
	private static String formerElementAdresse(String element){
		
		if (StringUtils.isNotEmpty( element ))
			return element + " ";
		return "";
	}

	public String getLangueKey() {
		return langueKey;
	}
	public void setLangueKey(String langueKey) {
		this.langueKey = langueKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumeroLigne() {
		return numeroLigne;
	}
	public void setNumeroLigne(String numeroLigne) {
		this.numeroLigne = numeroLigne;
	}
}
