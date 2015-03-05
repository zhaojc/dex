package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.EspaceJeuxAutoexclusionActifGenerateurRapport_CDX_0260;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class EspaceJeuxAutoexclusionActifRapportForm_CDX_0260 extends CriteresRapportForm{

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private String site = "";
	
	public EspaceJeuxAutoexclusionActifRapportForm_CDX_0260() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new EspaceJeuxAutoexclusionActifGenerateurRapport_CDX_0260();
	}

	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
}
