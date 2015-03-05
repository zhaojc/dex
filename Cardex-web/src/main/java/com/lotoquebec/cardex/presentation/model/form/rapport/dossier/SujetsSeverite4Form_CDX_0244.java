package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.SujetsSeverite4GenerateurRapport_CDX_0244;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class SujetsSeverite4Form_CDX_0244 extends CriteresRapportForm{
	
	private String severite = "";
	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
		
	public SujetsSeverite4Form_CDX_0244() {
		super();
	}
	 
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setSeverite(String.valueOf(GlobalConstants.Severite.SEVERITE_4));
        setEntite("");
        setSite("");
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new SujetsSeverite4GenerateurRapport_CDX_0244();
	}
	
	public String getSeverite() {
		return severite;
	}

	public void setSeverite(String severite) {
		this.severite = severite;
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
	
}
