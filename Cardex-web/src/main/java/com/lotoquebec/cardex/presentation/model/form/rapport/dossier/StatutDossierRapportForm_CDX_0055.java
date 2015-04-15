package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.StatutDossierGenerateurRapport_CDX_0055;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class StatutDossierRapportForm_CDX_0055 extends CriteresRapportForm{

	private static final long serialVersionUID = 3927602314905031362L;
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	
	public StatutDossierRapportForm_CDX_0055() {
		super(new StatutDossierGenerateurRapport_CDX_0055());
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
	}
	
	public String getEntite() {
		return hierarchieEGNTC.getEntite();
	}

	public void setEntite(String entite) {
		this.hierarchieEGNTC.setEntite(entite);
	}
	
    public String getGenre() {
    	return hierarchieEGNTC.getGenre();
    }

    public void setGenre(String genre) {
    	hierarchieEGNTC.setGenre(genre);
    }
    
	public String getNature() {
		return hierarchieEGNTC.getNature();
	}

	public void setNature(String nature) {
		this.hierarchieEGNTC.setNature(nature);
	}
	
}
