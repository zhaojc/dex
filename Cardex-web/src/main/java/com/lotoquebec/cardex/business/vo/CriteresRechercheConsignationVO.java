package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRechercheConsignation;

/**
 * Permet de transiter les informations relatives à la recherche d'un suivi de
 * la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/03/29 15:50:58 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSuivi
 */
public class CriteresRechercheConsignationVO
        implements CriteresRechercheConsignation {

    private long entite = 0;
    private long      genre = 0;
    private long      nature = 0;
    private long      type = 0;
    private long      categorie = 0;
    private String intervenant = "";
	private String approuve = "";
	private String nonApprouve = "";
    private long siteOrigine = 0;
    private String description = "";
    private Timestamp dateDebut = null;
    private Timestamp dateFin = null;
    private String marque = "";
    private String modele = "";
    private String fournisseur = "";
    private String numeroSerie = "";
    private long typeConsignation = 0;
	private long devise = 0;
	private long denomination = 0;
    private String typeResultat = "";
    private String    ordreTriRecherche = "";
    private Boolean   ordreCroissantRecherche = true;
    private long maximumResultatsRecherche = 0;
    private int sequence = 0;

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
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSuiviVO.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheSuiviVO
     * en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheConsignationVO : ");
        stringBuffer.append("entite = '" + entite);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', fournisseur = '" + fournisseur);
        stringBuffer.append("', numeroSerie = '" + numeroSerie);
        stringBuffer.append("', type = '" + typeConsignation);
        stringBuffer.append("', typeResultat = '" + typeResultat);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
                + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
                + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the dateDebut.
	 * @return Timestamp
	 */
	public Timestamp getDateDebut() {
		return dateDebut;
	}

	/**
	 * Returns the dateFin.
	 * @return Timestamp
	 */
	public Timestamp getDateFin() {
		return dateFin;
	}

	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the entite.
	 * @return long
	 */
	public long getEntite() {
		return entite;
	}

	/**
	 * Returns the fournisseur.
	 * @return String
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the marque.
	 * @return String
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * Returns the maximumResultatsRecherche.
	 * @return long
	 */
	public long getMaximumResultatsRecherche() {
		return maximumResultatsRecherche;
	}

	/**
	 * Returns the modele.
	 * @return String
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * Returns the numeroSerie.
	 * @return String
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}

	/**
	 * Returns the ordreTriRecherche.
	 * @return String
	 */
	public String getOrdreTriRecherche() {
		return ordreTriRecherche;
	}

	/**
	 * Returns the siteOrigine.
	 * @return long
	 */
	public long getSiteOrigine() {
		return siteOrigine;
	}

	/**
	 * Returns the typeConsignation.
	 * @return long
	 */
	public long getTypeConsignation() {
		return typeConsignation;
	}

	/**
	 * Returns the typeResultat.
	 * @return String
	 */
	public String getTypeResultat() {
		return typeResultat;
	}

	/**
	 * Sets the dateDebut.
	 * @param dateDebut The dateDebut to set
	 */
	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Sets the dateFin.
	 * @param dateFin The dateFin to set
	 */
	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the entite.
	 * @param entite The entite to set
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * Sets the fournisseur.
	 * @param fournisseur The fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the marque.
	 * @param marque The marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * Sets the maximumResultatsRecherche.
	 * @param maximumResultatsRecherche The maximumResultatsRecherche to set
	 */
	public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
		this.maximumResultatsRecherche = maximumResultatsRecherche;
	}

	/**
	 * Sets the modele.
	 * @param modele The modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * Sets the numeroSerie.
	 * @param numeroSerie The numeroSerie to set
	 */
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	/**
	 * Sets the ordreTriRecherche.
	 * @param ordreTriRecherche The ordreTriRecherche to set
	 */
	public void setOrdreTriRecherche(String ordreTriRecherche) {
		this.ordreTriRecherche = ordreTriRecherche;
	}

	/**
	 * Sets the siteOrigine.
	 * @param siteOrigine The siteOrigine to set
	 */
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	/**
	 * Sets the typeConsignation.
	 * @param typeConsignation The typeConsignation to set
	 */
	public void setTypeConsignation(long typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	/**
	 * Sets the typeResultat.
	 * @param typeResultat The typeResultat to set
	 */
	public void setTypeResultat(String typeResultat) {
		this.typeResultat = typeResultat;
	}

	/**
	 * Sets the ordreCroissantRecherche.
	 * @param ordreCroissantRecherche The ordreCroissantRecherche to set
	 */
	public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
		this.ordreCroissantRecherche = ordreCroissantRecherche;
	}

	/**
	 * @return
	 */
	public String getApprouve() {
		return approuve;
	}

	/**
	 * @return
	 */
	public String getNonApprouve() {
		return nonApprouve;
	}

	/**
	 * @param string
	 */
	public void setApprouve(String string) {
		approuve = string;
	}

	/**
	 * @param string
	 */
	public void setNonApprouve(String string) {
		nonApprouve = string;
	}

	/**
	 * @return
	 */
	public long getDenomination() {
		return denomination;
	}

	/**
	 * @param l
	 */
	public void setDenomination(long l) {
		denomination = l;
	}

	/**
	 * @return
	 */
	public long getDevise() {
		return devise;
	}

	/**
	 * @param l
	 */
	public void setDevise(long l) {
		devise = l;
	}


	/**
	 * @return genre
	 */
	public long getGenre() {
		return genre;
	}


	/**
	 * @param genre genre à définir
	 */
	public void setGenre(long genre) {
		this.genre = genre;
	}


	/**
	 * @return nature
	 */
	public long getNature() {
		return nature;
	}


	/**
	 * @param nature nature à définir
	 */
	public void setNature(long nature) {
		this.nature = nature;
	}


	/**
	 * @return type
	 */
	public long getType() {
		return type;
	}


	/**
	 * @param type type à définir
	 */
	public void setType(long type) {
		this.type = type;
	}


	/**
	 * @return categorie
	 */
	public long getCategorie() {
		return categorie;
	}


	/**
	 * @param categorie categorie à définir
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
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

	
}