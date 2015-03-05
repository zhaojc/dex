package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.sql.VisitesCentreRegionalGenererRapportSQL_CDX_0254;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class VisitesCentreRegional_CDX_0254 extends CriteresRapportForm{
	
	private String vague = "";
		
	public VisitesCentreRegional_CDX_0254() {
		super();
	}
	 
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new VisitesCentreRegionalGenererRapportSQL_CDX_0254();
	}
	
	public String getVague() {
		return vague;
	}

	public void setVague(String vague) {
		this.vague = vague;
	}	

}
