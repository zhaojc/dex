package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.EspaceJeuxTricherieFondeGenerateurRapport_CDX_0262;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class EspaceJeuxTricherieFondeRapportForm_CDX_0262 extends CriteresRapportForm{

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	
	public EspaceJeuxTricherieFondeRapportForm_CDX_0262() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new EspaceJeuxTricherieFondeGenerateurRapport_CDX_0262();
	}

	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
}
