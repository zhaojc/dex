package com.lotoquebec.cardexCommun.model;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardexCommun.business.vo.VO;

public class VOForm<T extends VO> extends ValidatorForm{

	protected T entite;

	protected VOForm() {
		super();
	}

	protected VOForm(T entiteCardex) {
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
		VOForm other = (VOForm) obj;
		if (entite == null) {
			if (other.entite != null)
				return false;
		} else if (!entite.equals(other.entite))
			return false;
		return true;
	}
	
	
}
