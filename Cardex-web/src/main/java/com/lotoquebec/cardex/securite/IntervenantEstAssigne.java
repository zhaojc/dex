package com.lotoquebec.cardex.securite;

import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.SecuritePredicate;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * L'intervenant assignée à un dossier à carte blanche sur la consultation et création 
 * @author levassc
 *
 */
public class IntervenantEstAssigne extends SecuritePredicate {

	public boolean validerSecurite(CardexAuthenticationSubject subject, Object vo) {
		CardexUser user = (CardexUser) subject.getUser();
		
		if (vo instanceof DossierVO == false)
			return false;
		else
			return user.getCode().equals( ((DossierVO)vo).getIntervenant() );
	}
}
