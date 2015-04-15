package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.EspaceJeuxTricherieFondeGenerateurRapport_CDX_0262;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


public class EspaceJeuxTricherieFondeRapportForm_CDX_0262 extends CriteresRapportForm{

	private static final long serialVersionUID = -1384296006839689190L;
	private String site = "";
	
	public EspaceJeuxTricherieFondeRapportForm_CDX_0262() {
		super(new EspaceJeuxTricherieFondeGenerateurRapport_CDX_0262());
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
}
