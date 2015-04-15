package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.EspaceJeuxAutoexclusionActifGenerateurRapport_CDX_0260;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


public class EspaceJeuxAutoexclusionActifRapportForm_CDX_0260 extends CriteresRapportForm{

	private static final long serialVersionUID = -5978497123034336780L;
	private String site = "";
	
	public EspaceJeuxAutoexclusionActifRapportForm_CDX_0260() {
		super(new EspaceJeuxAutoexclusionActifGenerateurRapport_CDX_0260());
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
