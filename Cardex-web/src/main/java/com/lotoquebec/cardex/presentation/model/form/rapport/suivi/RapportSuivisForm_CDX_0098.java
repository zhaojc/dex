package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.suivi.RapportSuivisGenerateurRapport_CDX_0098;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

public class RapportSuivisForm_CDX_0098 extends SuiviRapportForm{
	
	private static final long serialVersionUID = 7412715275951030833L;
	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String activite = "";

	public RapportSuivisForm_CDX_0098(){
		super( new RapportSuivisGenerateurRapport_CDX_0098() );
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

}