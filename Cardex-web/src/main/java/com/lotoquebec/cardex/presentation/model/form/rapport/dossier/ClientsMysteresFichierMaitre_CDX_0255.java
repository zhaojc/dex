package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.rapports.sql.ClientsMysteresFichierMaitreGenererRapportSQL_CDX_0255;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;


public class ClientsMysteresFichierMaitre_CDX_0255 extends CriteresRapportForm{
	
	private static final long serialVersionUID = -6382781205931257447L;
	private String vague = "";
		
	public ClientsMysteresFichierMaitre_CDX_0255() {
		super(new ClientsMysteresFichierMaitreGenererRapportSQL_CDX_0255());
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
