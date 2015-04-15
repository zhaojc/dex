package com.lotoquebec.cardex.business.vo.rapport;

import java.util.Date;

public class CritereRapportVO implements RapportVO{

	private Date dateDebutDu = null;
	private Date dateDebutAu = null;

    private Date dateHeureDebutDu = null;
    private Date dateHeureDebutAu = null;
	
	public Date getDateDebutDu() {
		return dateDebutDu;
	}
	
	public void setDateDebutDu(Date dateDebutDu) {
		this.dateDebutDu = dateDebutDu;
	}
	
	public Date getDateDebutAu() {
		return dateDebutAu;
	}
	
	public void setDateDebutAu(Date dateDebutAu) {
		this.dateDebutAu = dateDebutAu;
	}

    /**
     * @return dateHeureDebutDu
     */
    public Date getDateHeureDebutDu() {
        return dateHeureDebutDu;
    }

    /**
     * @param dateHeureDebutDu dateHeureDebutDu � d�finir
     */
    public void setDateHeureDebutDu(Date dateHeureDebutDu) {
        this.dateHeureDebutDu = dateHeureDebutDu;
    }

    /**
     * @return dateHeureDebutAu
     */
    public Date getDateHeureDebutAu() {
        return dateHeureDebutAu;
    }

    /**
     * @param dateHeureDebutAu dateHeureDebutAu � d�finir
     */
    public void setDateHeureDebutAu(Date dateHeureDebutAu) {
        this.dateHeureDebutAu = dateHeureDebutAu;
    }
	
}
