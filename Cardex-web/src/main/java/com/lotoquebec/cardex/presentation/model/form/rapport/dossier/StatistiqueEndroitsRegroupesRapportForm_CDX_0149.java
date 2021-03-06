package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.StatistiqueEndroitsRegroupesGenerateurRapport_CDX_0149;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class StatistiqueEndroitsRegroupesRapportForm_CDX_0149 extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String regroupement = "";   
	
	public String getRegroupement() {
		return regroupement;
	}

	public void setRegroupement(String regroupement) {
		this.regroupement = regroupement;
	}

	public StatistiqueEndroitsRegroupesRapportForm_CDX_0149() {
		super();
	}
	
	@Override
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new StatistiqueEndroitsRegroupesGenerateurRapport_CDX_0149();
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
    
}
