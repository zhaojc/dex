package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Modifiable;
import com.lotoquebec.cardex.business.Urgence;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/20 22:06:56 $
 * @see com.lotoquebec.cardex.presentation.model.AdresseHtmlForm
 */
public class UrgenceVO implements Urgence, Modifiable{

    private long cle = 0;
    private long site = 0;
    private String district = "";
    private long classe  = 0;
    private String unite = "";
    private String contact = "";
    private String contactPrenom = "";
    private String fonctionGrade = "";
    private String matricule = "";
    private String evenement = "";
    private String telephone = "";
    private long poste = 0;
    private String telecopieur = "";
    private String courriel = "";
    private long motif = 0;
    private Boolean repondant = false;
    private long statut = 0;
    private String ville = "";
    private String createur = "";
    private String societe = "";
    private Timestamp dateCreation = null;
    private long lien = 0;
    private long lienSite = 0;
    private long lienSociete = 0;
    private long lienSiteSociete = 0;
    private String numeroDossier = "";
    private Boolean modifiable = false;
    private Dossier dossier = new DossierVO();
    
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
    public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public long getClasse() {
		return classe;
	}
	public void setClasse(long classe) {
		this.classe = classe;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPrenom()
    {
        return contactPrenom;
    }
    public void setContactPrenom(String contactPrenom)
    {
        this.contactPrenom = contactPrenom;
    }
    public String getFonctionGrade()
    {
        return fonctionGrade;
    }
    public void setFonctionGrade(String fonctionGrade)
    {
        this.fonctionGrade = fonctionGrade;
    }
    public String getMatricule()
    {
        return matricule;
    }
    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }
    public String getEvenement() {
		return evenement;
	}
	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    public long getPoste(){
        return poste;
    }
    public void setPoste(long poste){
        this.poste = poste;
    }
	public String getTelecopieur() {
		return telecopieur;
	}
	public void setTelecopieur(String telecopieur) {
		this.telecopieur = telecopieur;
	}
	public String getCourriel() {
		return courriel;
	}
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
	public long getMotif() {
		return motif;
	}
	public void setMotif(long motif) {
		this.motif = motif;
	}
	public Boolean isRepondant() {
		return repondant;
	}
	public Boolean getRepondant() {
		return this.repondant;
	}
	public void setRepondant(Boolean repondant) {
		this.repondant = repondant;
	}

	public long getStatut() {
		return statut;
	}
	public void setStatut(long statut) {
		this.statut = statut;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
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
	public long getLienSociete() {
		return lienSociete;
	}
	public void setLienSociete(long lienSociete) {
		this.lienSociete = lienSociete;
	}
	public long getLienSiteSociete() {
		return lienSiteSociete;
	}
	public void setLienSiteSociete(long lienSiteSociete) {
		this.lienSiteSociete = lienSiteSociete;
	}

	public Boolean getModifiable() {
		return modifiable;
	}

	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public Boolean isModifiable() {
		return modifiable;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
    /**
     * @return the dossier
     */
    public Dossier getDossier()
    {
        return dossier;
    }
    /**
     * @param dossier the dossier to set
     */
    public void setDossier(Dossier dossier)
    {
        this.dossier = dossier;
    }
    /**
     * @return the numeroDossier
     */
    public String getNumeroDossier()
    {
        return numeroDossier;
    }
    /**
     * @param numeroDossier the numeroDossier to set
     */
    public void setNumeroDossier(String numeroDossier)
    {
        this.numeroDossier = numeroDossier;
    }
	
}