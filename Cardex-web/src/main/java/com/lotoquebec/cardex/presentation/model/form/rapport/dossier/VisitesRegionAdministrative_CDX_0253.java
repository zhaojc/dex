package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.sql.VisitesRegionAdministrativeGenererRapportSQL_CDX_0253;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class VisitesRegionAdministrative_CDX_0253 extends CriteresRapportForm{
	
	private String vague = "";
		
	public VisitesRegionAdministrative_CDX_0253() {
		super();
	}
	 
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new VisitesRegionAdministrativeGenererRapportSQL_CDX_0253();
	}
	
	public String getVague() {
		return vague;
	}

	public void setVague(String vague) {
		this.vague = vague;
	}	

}
