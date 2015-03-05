package com.lotoquebec.cardex.business.vo.rapport;

import java.util.Date;

public class SocietesInactivesRapportVO extends RapportVO{
    private String vague = "";
    private String societe = "";
    private Date dateInactivation = null;

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

    public Date getDateInactivation() {
        return dateInactivation;
    }

    public void setDateInactivation(Date dateInactivation) {
        this.dateInactivation = dateInactivation;
    }   
	
}
