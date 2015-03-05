package com.lotoquebec.cardex.securite;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.SecuritePredicate;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * L'intervenant cr�ateur � carte blanche sur la consultation et cr�ation 
 * C'est bon pour v�hicule, soci�t�, narration et sujet
 * @author levassc
 *
 */
public class IntervenantEstLeCreateur extends SecuritePredicate {

	public boolean validerSecurite(CardexAuthenticationSubject subject, Object vo) {
		CardexUser user = (CardexUser) subject.getUser();
		
		return isIntervenantCreateur(getCreateur(vo), user.getCode());
	}
	
	public static String getCreateur(Object vo){
		return getValeur(vo, "createur");
	}
	
	public static boolean isIntervenantCreateur(String createur, String intervenant){
		return intervenant.equals( createur );
	}
}
