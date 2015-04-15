package com.lotoquebec.cardex.presentation.model.form.rapport.acces;

import com.lotoquebec.cardex.generateurRapport.acces.AuditAccesNarrationGenerateurRapport_CDX_0126;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;


public class AuditAccesNarrationRapportForm_CDX_0126 extends CriteresRapportForm{

	private static final long serialVersionUID = -505621840640292868L;
	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	private String intervenant = "";
	private String groupe = "";
	private String nombreAcces = "";
	
	public AuditAccesNarrationRapportForm_CDX_0126() {
		super(new AuditAccesNarrationGenerateurRapport_CDX_0126());
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
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

	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		if(StringUtils.isEmpty(intervenant)){
			//On prend tous les intervenants si aucune s�lection n'est faite.
			this.intervenant = "%";
		}else{
			this.intervenant = intervenant;
		}
	}

	public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getEntite() {
		return cascadeEGNTC.get(HierarchieEGNTC.ENTITE);
	}

	public void setEntite(String entite) {
		this.cascadeEGNTC.set(HierarchieEGNTC.ENTITE, entite);
	}

	//Param�tre pour filter le nombre d'acc�s
	public String getNombreAcces() {
		return nombreAcces;
	}
	
	public void setNombreAcces(String nombreAcces) {
		this.nombreAcces = nombreAcces;
	}

	
}
