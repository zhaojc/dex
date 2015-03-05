package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.DelaiTraitementEnquetesGenererRapport_CDX_0246;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class DelaiTraitementEnquetesRapportForm_CDX_0246 extends CriteresRapportForm{

	private String activite = "";
	private String entite = "";
	
	public DelaiTraitementEnquetesRapportForm_CDX_0246() {
		super();
	}

	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        setEntite(String.valueOf(user.getEntite()));
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new DelaiTraitementEnquetesGenererRapport_CDX_0246();
	}
	
	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}
	
}
