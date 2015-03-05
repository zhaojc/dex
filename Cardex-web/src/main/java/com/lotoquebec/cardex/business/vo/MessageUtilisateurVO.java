package com.lotoquebec.cardex.business.vo;

import java.util.Date;

public class MessageUtilisateurVO {

	private long cle = 0;
	private String message = "";
	private int niveau = 0;
	private Date dateDebut = null;
	private Date dateFin = null;
	
	
	public long getCle() {
		return cle;
	}
	
	public void setCle(long cle) {
		this.cle = cle;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getNiveau() {
		return niveau;
	}
	
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public Date getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	
}
