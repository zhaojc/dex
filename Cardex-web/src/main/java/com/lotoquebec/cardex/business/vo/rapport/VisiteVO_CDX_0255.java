package com.lotoquebec.cardex.business.vo.rapport;

import java.util.Date;

import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.DateUtils;

public class VisiteVO_CDX_0255 implements Cloneable{

	
	private long categorie;
	private Date visiteDate; // Date de la visite de la société de la série de vague
	private Boolean carte; // Si oui ou non le client a été carté.  Null affiche vide
	private String numeroBillet; // Le numéro de livret de l'instantané récupéré par le client
	private String envoieAvisDate; // Date le l'envoie de la lettre d'avis
	private Date receptionAvisDate; // Date de reception de la lettre d'avis
	//private Date suspensionDate;  La date de suspension est égale a la date de confirmation de la formation
	private int nbJoursSuspension; // Nombre de jour de suspension du terminal
	private boolean visiteConfirme; // Un X signifie que la société peut être séclectionnée pour une nouvelle vague
	
	// L'information n'est pas valide.
	public boolean isValid(){
		return visiteDate != null;
	}
		
	public boolean isSuspension(){
		return nbJoursSuspension > 0;
	}
	
	public boolean isSuspensionPasse(){
		
		if (nbJoursSuspension == 0 || receptionAvisDate == null)
			return true;
		return DateUtils.ajoutJour(receptionAvisDate, nbJoursSuspension).before(new Date());
	}
	
	public String getCarte() {
		
		if (carte == null)
			return "";
		
		if (carte == true)
			return "oui";
		else
			return "non";
	}
	
	public String getVisiteConfirme() {
		
		if (visiteConfirme)
			return "X";
		return "";
	}
	
	public String getHeureVisiteDate(){
		if (visiteDate != null)
			return DateFormat.format(visiteDate, "HH:mm:ss");
		return "";
	}
	
	
	
	@Override
	public VisiteVO_CDX_0255 clone() throws CloneNotSupportedException {
		VisiteVO_CDX_0255 visiteVO_CDX_0255 = new VisiteVO_CDX_0255();
		visiteVO_CDX_0255.setCategorie(this.categorie);
		visiteVO_CDX_0255.setVisiteDate(this.visiteDate);
		visiteVO_CDX_0255.setCarte(this.carte);
		visiteVO_CDX_0255.setNumeroBillet(this.numeroBillet);
		visiteVO_CDX_0255.setEnvoieAvisDate(this.envoieAvisDate);
		visiteVO_CDX_0255.setReceptionAvisDate(this.receptionAvisDate);
		visiteVO_CDX_0255.setNbJoursSuspension(this.nbJoursSuspension);
		visiteVO_CDX_0255.setVisiteConfirme(this.visiteConfirme);
		
		return visiteVO_CDX_0255;
	}

	// get / set
	public Date getVisiteDate() {
		return visiteDate;
	}
	
	public void setVisiteDate(Date visiteDate) {
		this.visiteDate = visiteDate;
	}
	
	public Boolean isCarte() {
		
		if (carte == null)
			return false;
		return carte;
	}
	
	public void setCarte(Boolean carte) {
		this.carte = carte;
	}
	
	public String getNumeroBillet() {
		return numeroBillet;
	}
	
	public void setNumeroBillet(String numeroBillet) {
		this.numeroBillet = numeroBillet;
	}

	public String getEnvoieAvisDate() {
		return envoieAvisDate;
	}

	public void setEnvoieAvisDate(String envoieAvisDate) {
		this.envoieAvisDate = envoieAvisDate;
	}

	public Date getReceptionAvisDate() {
		return receptionAvisDate;
	}

	public void setReceptionAvisDate(Date receptionAvisDate) {
		this.receptionAvisDate = receptionAvisDate;
	}

	public int getNbJoursSuspension() {
		return nbJoursSuspension;
	}
	
	public void setNbJoursSuspension(int nbJoursSuspension) {
		this.nbJoursSuspension = nbJoursSuspension;
	}
	
	public boolean isVisiteConfirme() {
		return visiteConfirme;
	}
	
	public void setVisiteConfirme(boolean visiteConfirme) {
		this.visiteConfirme = visiteConfirme;
	}

	public long getCategorie() {
		return categorie;
	}

	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}
	
	
}
