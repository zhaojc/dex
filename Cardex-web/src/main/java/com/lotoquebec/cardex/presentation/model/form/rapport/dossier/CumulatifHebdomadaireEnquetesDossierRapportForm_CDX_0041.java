package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.CumulatifHebdomadaireEnquetesDossierGenerateurRapport_CDX_0041;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class CumulatifHebdomadaireEnquetesDossierRapportForm_CDX_0041 extends CriteresRapportForm{

	private String entite = "";
	private String site = "";
	
	public CumulatifHebdomadaireEnquetesDossierRapportForm_CDX_0041() {
		super();
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
	 
	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new CumulatifHebdomadaireEnquetesDossierGenerateurRapport_CDX_0041();
	}
	
}
