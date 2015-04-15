package com.lotoquebec.cardex.generateurRapport.entite;

import com.lotoquebec.cardexCommun.business.vo.VO;

public class RapportVO<T extends VO> {

	protected T entite;

	protected RapportVO() {
		super();
	}

	protected RapportVO(T entiteCardex) {
		super();
		this.entite = entiteCardex;
	}

	public T getEntite() {
		return entite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entite == null) ? 0 : entite.hashCode());
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
		RapportVO other = (RapportVO) obj;
		if (entite == null) {
			if (other.entite != null)
				return false;
		} else if (!entite.equals(other.entite))
			return false;
		return true;
	}
	
	
}
