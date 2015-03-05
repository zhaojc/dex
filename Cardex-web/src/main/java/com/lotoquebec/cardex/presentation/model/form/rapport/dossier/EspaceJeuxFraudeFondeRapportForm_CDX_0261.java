package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.EspaceJeuxFraudeFondeGenerateurRapport_CDX_0261;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class EspaceJeuxFraudeFondeRapportForm_CDX_0261 extends CriteresRapportForm{

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	
	public EspaceJeuxFraudeFondeRapportForm_CDX_0261() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new EspaceJeuxFraudeFondeGenerateurRapport_CDX_0261();
	}

	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
}
