package com.lotoquebec.cardexCommun.securite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.util.StringUtils;

/* 
 * La classe "SecuritePredicate" est une "carte blanche" qui permet d'acc�der � un enregistrement
 * m�me si l'usager ne poss�de pas les r�les requis.
 */
public abstract class SecuritePredicate {

	public boolean validerSecurite(CardexAuthenticationSubject subject, Object vo){return false;}

	protected static String getValeur(Object vo, String champ){
		Class[] classGet = new Class[0];
		String prefix = "get";
		
		try {
			Method methodGet = vo.getClass().getDeclaredMethod(prefix+StringUtils.capitalise(champ), classGet);
			return getValeur(vo, methodGet);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// C'est possible et normal, ce n'est pas toutes les objects qui ont un cr�ateur
			// e.printStackTrace();
		}
		return "";
	}
	
	private static String getValeur(Object vo, Method methodGet){
		String valeur = "";
		
		try {
			if (methodGet.invoke(vo) != null)
				valeur = methodGet.invoke(vo).toString();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return valeur;
	}
	
}
