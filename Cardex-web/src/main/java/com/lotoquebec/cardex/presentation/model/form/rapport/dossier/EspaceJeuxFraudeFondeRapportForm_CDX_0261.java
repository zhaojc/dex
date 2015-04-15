package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.EspaceJeuxFraudeFondeGenerateurRapport_CDX_0261;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class EspaceJeuxFraudeFondeRapportForm_CDX_0261 extends CriteresRapportForm{

	private static final long serialVersionUID = -6520615854219440489L;
	private String site = "";
	
	public EspaceJeuxFraudeFondeRapportForm_CDX_0261() {
		super(new EspaceJeuxFraudeFondeGenerateurRapport_CDX_0261());
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}

	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
}
