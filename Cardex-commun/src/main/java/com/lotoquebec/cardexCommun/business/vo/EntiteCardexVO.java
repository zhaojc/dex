package com.lotoquebec.cardexCommun.business.vo;

import java.util.Set;

import com.lotoquebec.cardexCommun.business.EntiteCardex;

public class EntiteCardexVO implements EntiteCardex{

    private long cle = 0;
    private long site = 0;
    
	public long getCle() {
		return cle;
	}
	public void setCle(long cle) {
		this.cle = cle;
	}
	public long getSite() {
		return site;
	}
	public void setSite(long site) {
		this.site = site;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cle ^ (cle >>> 32));
		result = prime * result + (int) (site ^ (site >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntiteCardexVO other = (EntiteCardexVO) obj;
		if (cle != other.cle)
			return false;
		if (site != other.site)
			return false;
		return true;
	}


}
