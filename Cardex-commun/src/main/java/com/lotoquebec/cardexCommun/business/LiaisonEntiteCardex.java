package com.lotoquebec.cardexCommun.business;

import java.util.Set;

import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;


/**
* Interfacede liaison Cardex
*/
public interface LiaisonEntiteCardex extends EntiteCardex {

	public abstract Set<LiaisonEntiteVO> getLiaisonEntites();	
	
	
}
