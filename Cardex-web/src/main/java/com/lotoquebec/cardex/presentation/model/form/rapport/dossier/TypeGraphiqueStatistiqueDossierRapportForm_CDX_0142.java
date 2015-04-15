package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.rapports.sql.TypeGraphiqueStatistiqueDossierGenererRapportSQL_CDX_0142;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class TypeGraphiqueStatistiqueDossierRapportForm_CDX_0142 extends CriteresRapportForm{

	private static final long serialVersionUID = 8445518542692938919L;
	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String fonde = "";
	
	public TypeGraphiqueStatistiqueDossierRapportForm_CDX_0142() {
		super( new TypeGraphiqueStatistiqueDossierGenererRapportSQL_CDX_0142() );
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
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

	public String getFonde() {
		return fonde;
	}

	public void setFonde(String fonde) {
		this.fonde = fonde;
	}	
	
}
