package com.lotoquebec.cardexCommun.model;

import com.lotoquebec.cardexCommun.business.EntiteCardex;

public class EntiteForm<T extends EntiteCardex> extends VOForm<T> implements EntiteCardexForm{

	protected EntiteForm() {
		super();
	}

	protected EntiteForm(T entiteCardex) {
		super();
		this.entite = entiteCardex;
	}
	
	public String getCle() {
		return String.valueOf( entite.getCle() );
	}

	public String getSite() {
		return String.valueOf( entite.getSite() );
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
		EntiteForm other = (EntiteForm) obj;
		if (entite == null) {
			if (other.entite != null)
				return false;
		} else if (!entite.equals(other.entite))
			return false;
		return true;
	}
	
	
}
