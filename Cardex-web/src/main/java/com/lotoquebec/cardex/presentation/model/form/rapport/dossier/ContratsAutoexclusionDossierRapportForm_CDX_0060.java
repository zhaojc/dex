package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.ContratsAutoexclusionDossierGenerateurRapport_CDX_0060;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class ContratsAutoexclusionDossierRapportForm_CDX_0060 extends CriteresRapportForm{

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	
	public ContratsAutoexclusionDossierRapportForm_CDX_0060() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}

	public String getGenre() {
		return cascadeEGNTC.get(HierarchieEGNTC.GENRE);
	}

	public void setGenre(String genre) {
		this.cascadeEGNTC.set(HierarchieEGNTC.GENRE, genre);
	}

	public String getEntite() {
		return cascadeEGNTC.get(HierarchieEGNTC.ENTITE);
	}

	public void setEntite(String entite) {
		this.cascadeEGNTC.set(HierarchieEGNTC.ENTITE, entite);
	}
	
    public String getType() {
    	return cascadeEGNTC.get(HierarchieEGNTC.TYPE);
    }
    
    public String getNature() {
    	return cascadeEGNTC.get(HierarchieEGNTC.NATURE);
    }
    
    public String getCategorie() {
    	return cascadeEGNTC.get(HierarchieEGNTC.CATEGORIE);
    }
    
    public void setNature(String nature) {
    	cascadeEGNTC.set(HierarchieEGNTC.NATURE, nature);
    }
    
    public void setType(String type) {
    	cascadeEGNTC.set(HierarchieEGNTC.TYPE, type);
    }
    
    public void setCategorie(String categorie) {
    	cascadeEGNTC.set(HierarchieEGNTC.CATEGORIE, categorie);
    }
	
	@Override
	public GenererRapport getGenererRapport() {
		return new ContratsAutoexclusionDossierGenerateurRapport_CDX_0060();
	}
	
}
