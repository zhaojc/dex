package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.EmployeDossierGenerateurRapport_CDX_0042;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class EmployeDossierRapportForm_CDX_0042 extends CriteresRapportForm{
	
	private static final long serialVersionUID = 2866763657588558500L;
	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String role = "";
	private String fonde = "";
	private String statut = "";

	public EmployeDossierRapportForm_CDX_0042() {
		super(new EmployeDossierGenerateurRapport_CDX_0042());
	}
	
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
        setRole("0");
        setFonde("0");
        setStatut("0");
	}
	 
	public String getEntite() {
		return hierarchieES.getEntite();
	}

	public void setEntite(String entite) {
		this.hierarchieES.setEntite(entite);
		this.hierarchieEGNTC.setEntite(entite);
	}

	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}

	public void setSite(String site) {
		this.hierarchieES.setSiteOrigine(site);
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
    	hierarchieEGNTC.setNature(nature);
    }
        
    public String getType() {
    	return hierarchieEGNTC.getType();
    }

    public void setType(String type) {
    	hierarchieEGNTC.setType(type);
    }
        
    public String getCategorie() {
    	return hierarchieEGNTC.getCategorie();
    }

    public void setCategorie(String categorie) {
    	hierarchieEGNTC.setCategorie(categorie);
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFonde() {
		return fonde;
	}

	public void setFonde(String fonde) {
		this.fonde = fonde;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
        
}
