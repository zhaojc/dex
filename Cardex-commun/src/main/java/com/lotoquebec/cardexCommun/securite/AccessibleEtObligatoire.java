package com.lotoquebec.cardexCommun.securite;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AccessibleEtObligatoire implements Serializable {

	private Set<String> cleAccessibleActifEtInactifSet = new HashSet<String>();
	private boolean obligatoire = false;
	
	
	/**
	 * @param cleAccessibleActifEtInactifSet
	 * @param obligatoire
	 */
	public AccessibleEtObligatoire(Set<String> cleAccessibleActifEtInactifSet,
			boolean obligatoire) {
		super();
		this.cleAccessibleActifEtInactifSet = cleAccessibleActifEtInactifSet;
		this.obligatoire = obligatoire;
	}


	public Set<String> getCleAccessibleActifEtInactifSet() {
		return cleAccessibleActifEtInactifSet;
	}


	public boolean isObligatoire() {
		return obligatoire;
	}
	
	
}
