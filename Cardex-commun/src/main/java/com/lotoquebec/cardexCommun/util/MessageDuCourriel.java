package com.lotoquebec.cardexCommun.util;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

/**
 * Permet de construire le message à partir d'une base
 * @author levassc
 *
 */
public interface MessageDuCourriel {

	public String construireObjectMessage(CardexAuthenticationSubject subject) throws BusinessResourceException;
	
	public String construireMessage(String type);
	
}
