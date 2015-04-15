package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.dossier.SocietesInactivesGenerateurRapport_CDX_0256;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

public class SocietesInactives_CDX_0256 extends CriteresRapportForm{
	
	private static final long serialVersionUID = 1866421650570993671L;
	private String vague = "";
    private String societe = "";
    private String dateInactivation = "";
		
	public SocietesInactives_CDX_0256() {
		super(new SocietesInactivesGenerateurRapport_CDX_0256());
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

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }   

    public String getDateInactivation() {
        return dateInactivation;
    }

    public void setDateInactivation(String dateInactivation) {
        this.dateInactivation = dateInactivation;
    }   

}
