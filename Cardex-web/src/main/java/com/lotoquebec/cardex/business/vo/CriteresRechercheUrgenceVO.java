package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresRechercheUrgence;

/**
 * Permet de transiter les informations relatives à la recherche d'un
 * service d'urgence de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mazzucr $
 * 
 * @see com.lotoquebec.cardex.business.CriteresRechercheUrgence
 */
public class CriteresRechercheUrgenceVO implements CriteresRechercheUrgence {


    private String unite = "";
    private String district = "";
    private String contact = "";
    private String contactPrenom = "";
    private String ville = "";
    private String evenement = "";
    private String fonction = "";
    private String matricule = "";
    private long confidentialite = 0;
    private long motif = 0;
    private long statut = 0;
    private long classe =0;
    private String numeroDossier = "";
    private String societe = "";
    private long lienSiteSociete = 0;
    private long entite = 0;
    private long lienSociete = 0;
    private long siteOrigine = 0;
    private String ordreTriRecherche = "";
    private Boolean ordreCroissantRecherche = true;
    private long maximumResultatsRecherche = 0;
    private int sequence = 0;
    
    /**
     * Constructeur de CriteresRechercheUrgenceVO par défaut.
     */
    public CriteresRechercheUrgenceVO() {}

    // Getters

    /**
     * @return the societe
     */
    public String getSociete()
    {
        return societe;
    }

    /**
     * @return the unite
     */
    public String getUnite()
    {
        return unite;
    }

    /**
     * @return the district
     */
    public String getDistrict()
    {
        return district;
    }

    /**
     * @return the contact
     */
    public String getContact()
    {
        return contact;
    }

    /**
     * @return the contactPrenom
     */
    public String getContactPrenom()
    {
        return contactPrenom;
    }

    /**
     * @return the ville
     */
    public String getVille()
    {
        return ville;
    }

    /**
     * @return the evenement
     */
    public String getEvenement()
    {
        return evenement;
    }

    /**
     * @return the fonction
     */
    public String getFonction()
    {
        return fonction;
    }

    /**
     * @return the matricule
     */
    public String getMatricule()
    {
        return matricule;
    }

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }

    /**
     * @return the confidentialite
     */
    public long getConfidentialite()
    {
        return confidentialite;
    }

    /**
     * @return the numeroDossier
     */
    public String getNumeroDossier()
    {
        return numeroDossier;
    }

    /**
     * @return the lienSiteSociete
     */
    public long getLienSiteSociete()
    {
        return lienSiteSociete;
    }

    /**
     * @return the entite
     */
    public long getEntite()
    {
        return entite;
    }

    /**
     * @return the lienSociete
     */
    public long getLienSociete()
    {
        return lienSociete;
    }

    /**
     * @return the siteOrigine
     */
    public long getSiteOrigine()
    {
        return siteOrigine;
    }

    // Setters

    /**
     * @param societe the societe to set
     */
    public void setSociete(String societe)
    {
        this.societe = societe;
    }

    /**
     * @param siteOrigine the siteOrigine to set
     */
    public void setSiteOrigine(long siteOrigine)
    {
        this.siteOrigine = siteOrigine;
    }

    /**
     * @param unite the unite to set
     */
    public void setUnite(String unite)
    {
        this.unite = unite;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district)
    {
        this.district = district;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     * @param contactPrenom the contactPrenom to set
     */
    public void setContactPrenom(String contactPrenom)
    {
        this.contactPrenom = contactPrenom;
    }

    /**
     * @param ville the ville to set
     */
    public void setVille(String ville)
    {
        this.ville = ville;
    }

    /**
     * @param evenement the evenement to set
     */
    public void setEvenement(String evenement)
    {
        this.evenement = evenement;
    }

    /**
     * @param fonction the fonction to set
     */
    public void setFonction(String fonction)
    {
        this.fonction = fonction;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * @param lienSociete the lienSociete to set
     */
    public void setLienSociete(long lienSociete)
    {
        this.lienSociete = lienSociete;
    }

    /**
     * @param entite the entite to set
     */
    public void setEntite(long entite)
    {
        this.entite = entite;
    }

    /**
     * @param confidentialite the confidentialite to set
     */
    public void setConfidentialite(long confidentialite)
    {
        this.confidentialite = confidentialite;
    }

    /**
     * @param numeroDossier the numeroDossier to set
     */
    public void setNumeroDossier(String numeroDossier)
    {
        this.numeroDossier = numeroDossier;
    }

    /**
     * @param lienSiteSociete the lienSiteSociete to set
     */
    public void setLienSiteSociete(long lienSiteSociete)
    {
        this.lienSiteSociete = lienSiteSociete;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheVehiculeVO.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheVehiculeVO en caractère.
     */
    public String toString() {
        
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheUrgenceVO : ");
        stringBuffer.append("', unite = '" + unite);
        stringBuffer.append("', district = '" + district);
        stringBuffer.append("', contact = '" + contact);
        stringBuffer.append("', contactPrenom = '" + contactPrenom);
        stringBuffer.append("', ville = '" + ville);
        stringBuffer.append("', evenement = '" + evenement);
        stringBuffer.append("', fonction = '" + fonction);
        stringBuffer.append("', matricule = '" + matricule);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', motif = '" + motif);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', lienSiteSociete = '" + lienSiteSociete);
        stringBuffer.append("', entite = '" + entite);
        stringBuffer.append("', lienSociete = '" + lienSociete);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Boolean getOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}

    /**
     * @return the motif
     */
    public long getMotif()
    {
        return motif;
    }

    /**
     * @param motif the motif to set
     */
    public void setMotif(long motif)
    {
        this.motif = motif;
    }

    /**
     * @return the statut
     */
    public long getStatut()
    {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(long statut)
    {
        this.statut = statut;
    }

    /**
     * @return the classe
     */
    public long getClasse()
    {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(long classe)
    {
        this.classe = classe;
    }
}