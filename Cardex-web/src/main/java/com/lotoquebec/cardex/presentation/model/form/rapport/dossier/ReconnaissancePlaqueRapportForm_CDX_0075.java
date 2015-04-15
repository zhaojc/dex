package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.ReconnaissancePlaqueGenerateurRapport_CDX_0075;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class ReconnaissancePlaqueRapportForm_CDX_0075 extends CriteresRapportForm{

	private static final long serialVersionUID = -8826350095803935072L;
	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	
	public ReconnaissancePlaqueRapportForm_CDX_0075() {
		super(new ReconnaissancePlaqueGenerateurRapport_CDX_0075());
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
        setGenre(String.valueOf(GlobalConstants.Genre.SUJETS_INTERET));
        setNature(String.valueOf(GlobalConstants.Nature.AUTO_EXCLUSION));
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
	
}
