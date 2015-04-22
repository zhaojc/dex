package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.clientMystere.ClientMystereGenerateurRapport_CDX_0255;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


public class ClientsMysteresFichierMaitre_CDX_0255 extends CriteresRapportForm{
	
	private static final long serialVersionUID = -6382781205931257447L;
	private String vague = "";
		
	public ClientsMysteresFichierMaitre_CDX_0255() {
		super(new ClientMystereGenerateurRapport_CDX_0255());
	}
	 
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
	}
	
	public String getVague() {
		return vague;
	}

	public void setVague(String vague) {
		this.vague = vague;
	}	

}
