package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.sql.DetaillantsFautifsClientsMysteresGenererRapportSQL_CDX_0252;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class DetaillantsFautifsClientsMysteres_CDX_0252 extends CriteresRapportForm{
	
	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String fonde = "";
	private String categorie = "";
	private String region = "";
	private String vague = "";	
		
	public DetaillantsFautifsClientsMysteres_CDX_0252() {
		super();
	}
	 
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(GlobalConstants.Entite.LOTO_QUEBEC);
        setSite(GlobalConstants.Sites.CLIENTS_MYSTERES);
        setGenre(GlobalConstants.Genre.DOSSIERS_LQ);
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new DetaillantsFautifsClientsMysteresGenererRapportSQL_CDX_0252();
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

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}	

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getVague() {
		return vague;
	}

	public void setVague(String vague) {
		this.vague = vague;
	}	

}
