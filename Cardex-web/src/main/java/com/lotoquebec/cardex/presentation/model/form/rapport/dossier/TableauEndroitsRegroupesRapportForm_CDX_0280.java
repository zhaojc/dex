package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.TableauEndroitsRegroupesGenerateurRapport_CDX_0280;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class TableauEndroitsRegroupesRapportForm_CDX_0280 extends CriteresRapportForm{

	private static final long serialVersionUID = -4865635638210766678L;
	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String regroupement = "";   
	
	public String getRegroupement() {
		return regroupement;
	}

	public void setRegroupement(String regroupement) {
		this.regroupement = regroupement;
	}

	public TableauEndroitsRegroupesRapportForm_CDX_0280() {
		super(new TableauEndroitsRegroupesGenerateurRapport_CDX_0280());
	}
	
	@Override
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
