package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.sql.CategorieStatistiqueDossierGenererRapportSQL_CDX_0140;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class CategoriesStatistiqueDossierRapportForm_CDX_0140 extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String fonde = "";
	
	public CategoriesStatistiqueDossierRapportForm_CDX_0140() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new CategorieStatistiqueDossierGenererRapportSQL_CDX_0140();
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
		this.hierarchieEGNTC.setNature(nature);
	}

	public String getType() {
		return this.hierarchieEGNTC.getType();
	}

	public void setType(String type) {
		this.hierarchieEGNTC.setType(type);
	}

	public String getFonde() {
		return fonde;
	}

	public void setFonde(String fonde) {
		this.fonde = fonde;
	}	
    
    
	
}
