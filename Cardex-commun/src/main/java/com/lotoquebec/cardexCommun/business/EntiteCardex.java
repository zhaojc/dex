package com.lotoquebec.cardexCommun.business;

import com.lotoquebec.cardexCommun.business.vo.VO;



/**
* Cette interface permet d'optenir la clé unique de Cardex qui est
* constituée de la cle et du site.
*/
public interface EntiteCardex extends VO{

	public void setCle(long cle);
	public long getCle();
	
	public void setSite(long site);
	public long getSite();	
	
	//public Set<LiaisonEntiteVO> getLiaisonEntites();	
	
	
}
