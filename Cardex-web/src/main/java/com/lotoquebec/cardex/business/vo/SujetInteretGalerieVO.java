package com.lotoquebec.cardex.business.vo;

import java.util.HashSet;
import java.util.Set;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;

public class SujetInteretGalerieVO {

	private Sujet sujet;
	private Set<Dossier> actifDossierSujetInteret = new HashSet<Dossier>();
	private Set<Dossier> inactifDossierSujetInteret = new HashSet<Dossier>();
	
	/**
	 * @param sujet
	 */
	public SujetInteretGalerieVO(Sujet sujet) {
		super();
		this.sujet = sujet;
	}

	// get / set
	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public Set<Dossier> getActifDossierSujetInteret() {
		return actifDossierSujetInteret;
	}

	public void setActifDossierSujetInteret(Set<Dossier> actifDossierSujetInteret) {
		this.actifDossierSujetInteret = actifDossierSujetInteret;
	}

	public Set<Dossier> getInactifDossierSujetInteret() {
		return inactifDossierSujetInteret;
	}

	public void setInactifDossierSujetInteret(
			Set<Dossier> inactifDossierSujetInteret) {
		this.inactifDossierSujetInteret = inactifDossierSujetInteret;
	}
	
	
	
}
