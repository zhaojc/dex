package com.lotoquebec.cardexCommun.securite;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Cardex est l'utilisateur système de Cardex. 
 * @author levassc
 *
 */
public class CardexIntervenantSecuritePredicate extends SecuritePredicate {

	public boolean validerSecurite(CardexAuthenticationSubject subject, Object vo) {
		CardexUser user = (CardexUser) subject.getUser();
		
		return "CARDEX".equals( user.getCode() );
	}
}
