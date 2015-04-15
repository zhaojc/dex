package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.rapports.sql.VisitesRegionAdministrativeGenererRapportSQL_CDX_0253;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class VisitesRegionAdministrative_CDX_0253 extends CriteresRapportForm{
	
	private static final long serialVersionUID = 8718892017639289280L;
	private String vague = "";
		
	public VisitesRegionAdministrative_CDX_0253() {
		super( new VisitesRegionAdministrativeGenererRapportSQL_CDX_0253() );
	}
	 
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
	}
	
	public String getVague() {
		return vague;
	}

	public void setVague(String vague) {
		this.vague = vague;
	}	

}
