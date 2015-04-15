package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

public abstract class SuiviRapportForm extends CriteresRapportForm{

	private static final long serialVersionUID = 3358180373474876015L;
	private HierarchieES hierarchieES = new HierarchieES();
	private String intervenant = "";
	private String activite = "";
	
	public SuiviRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	
	public String getEntite() {
		return hierarchieES.getEntite();
	}
	
	public void setEntite(String entite) {
		hierarchieES.setEntite(entite);
	}
	
	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}
	
	public void setSite(String siteOrigine) {
		hierarchieES.setSiteOrigine(siteOrigine);
	}

	public String getActivite() {
		return this.activite;
	}
	
	public void setActivite(String activite) {
		this.activite = activite;
	}
	
}
