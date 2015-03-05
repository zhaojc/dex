package com.lotoquebec.cardex.presentation.model.form.rapport.acces;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.acces.AuditAccesSujetIntervenantGenerateurRapport_CDX_0072;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class AuditAccesSujetIntervenantRapportForm_CDX_0072 extends CriteresRapportForm{

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	private String intervenant = "";
	private String nombreAcces = "";
	
	public AuditAccesSujetIntervenantRapportForm_CDX_0072() {
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

	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getEntite() {
		return cascadeEGNTC.get(HierarchieEGNTC.ENTITE);
	}

	public void setEntite(String entite) {
		this.cascadeEGNTC.set(HierarchieEGNTC.ENTITE, entite);
	}

	//Paramètre pour filter le nombre d'accès
	public String getNombreAcces() {
		return nombreAcces;
	}
	
	public void setNombreAcces(String nombreAcces) {
		this.nombreAcces = nombreAcces;
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new AuditAccesSujetIntervenantGenerateurRapport_CDX_0072();
	}
	
}
