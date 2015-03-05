package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MiseEvaluationVO {

	private long cle = 0;
    private long site = 0;
    private long lien = 0;
    private long lienSite = 0;
    private long typeJeu = 0;
    private String commentaireJeu = "";
    private double miseMinimum = 0;
    private double miseMaximum = 0;
    private double miseMoyenne = 0;
    private double miseTotal = 0;
    private double gainPerte = 0;
    private long tempsJeuMinimum = 0;
    private long tempsJeuMaximum = 0;
    private double tempsJeuTotal = 0;
    private List<FrequenceVisitesVO> frequenceVisites = new ArrayList<FrequenceVisitesVO>();
    private Set<Long> jeux = new HashSet<Long>(); 
    private String createur = "";
    private Timestamp dateCreation = null;
	
    
    // get / set
    public long getTypeJeu() {
		return typeJeu;
	}
	
	public long getCle() {
		return cle;
	}

	public void setCle(long cle) {
		this.cle = cle;
	}

	public long getSite() {
		return site;
	}

	public void setSite(long site) {
		this.site = site;
	}

	public void setTypeJeu(long typeJeu) {
		this.typeJeu = typeJeu;
	}
	
	public String getCommentaireJeu() {
		return commentaireJeu;
	}
	
	public void setCommentaireJeu(String commentaireJeu) {
		this.commentaireJeu = commentaireJeu;
	}
	
	public double getMiseMinimum() {
		return miseMinimum;
	}
	
	public void setMiseMinimum(double miseMinimum) {
		this.miseMinimum = miseMinimum;
	}
	
	public double getMiseMaximum() {
		return miseMaximum;
	}
	
	public void setMiseMaximum(double miseMaximum) {
		this.miseMaximum = miseMaximum;
	}
	
	public double getMiseMoyenne() {
		return miseMoyenne;
	}
	
	public void setMiseMoyenne(double miseMoyenne) {
		this.miseMoyenne = miseMoyenne;
	}
	
	public double getMiseTotal() {
		return miseTotal;
	}
	
	public void setMiseTotal(double miseTotal) {
		this.miseTotal = miseTotal;
	}
	
	public double getGainPerte() {
		return gainPerte;
	}
	
	public void setGainPerte(double gainPerte) {
		this.gainPerte = gainPerte;
	}
	
	public long getTempsJeuMinimum() {
		return tempsJeuMinimum;
	}
	
	public void setTempsJeuMinimum(long tempsJeuMinimum) {
		this.tempsJeuMinimum = tempsJeuMinimum;
	}
	
	public long getTempsJeuMaximum() {
		return tempsJeuMaximum;
	}
	
	public void setTempsJeuMaximum(long tempsJeuMaximum) {
		this.tempsJeuMaximum = tempsJeuMaximum;
	}
	
	public double getTempsJeuTotal() {
		return tempsJeuTotal;
	}
	
	public void setTempsJeuTotal(double tempsJeuTotal) {
		this.tempsJeuTotal = tempsJeuTotal;
	}
	
	public List<FrequenceVisitesVO> getFrequenceVisites() {
		return frequenceVisites;
	}

	public void setFrequenceVisites(List<FrequenceVisitesVO> frequenceVisites) {
		this.frequenceVisites = frequenceVisites;
	}

	public long getLien() {
		return lien;
	}

	public void setLien(long lien) {
		this.lien = lien;
	}

	public long getLienSite() {
		return lienSite;
	}

	public void setLienSite(long lienSite) {
		this.lienSite = lienSite;
	}

	public Set<Long> getJeux() {
		return jeux;
	}

	public void setJeux(Set<Long> jeux) {
		this.jeux = jeux;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public Timestamp getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	
}
