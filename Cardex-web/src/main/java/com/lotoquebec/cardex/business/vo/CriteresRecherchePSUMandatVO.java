package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRecherchePSUMandat;

/**
 * Permet de transiter les informations relatives à la recherche d'un suivi de
 * la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/03/29 15:50:58 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSuivi
 */
public class CriteresRecherchePSUMandatVO
        implements CriteresRecherchePSUMandat {

    private long entite = 0;
    private String intervenant = "";
	private boolean approuve = false;
	private boolean nonApprouve = false;
    private long siteOrigine = 0;
    private String description = "";
    private Timestamp dateDebut = null;
    private Timestamp dateFin = null;
    private String numeroMandat = "";
    private long typeAction = 0;
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
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', numeroMandat = '" + numeroMandat);
        stringBuffer.append("', type = '" + typeAction);
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
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the maximumResultatsRecherche.
	 * @return long
	 */
	public long getMaximumResultatsRecherche() {
		return maximumResultatsRecherche;
	}

	/**
	 * Returns the numeroMandat.
	 * @return String
	 */
	public String getNumeroMandat() {
		return numeroMandat;
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
	 * Returns the typeAction.
	 * @return long
	 */
	public long getTypeAction() {
		return typeAction;
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
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the maximumResultatsRecherche.
	 * @param maximumResultatsRecherche The maximumResultatsRecherche to set
	 */
	public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
		this.maximumResultatsRecherche = maximumResultatsRecherche;
	}

	/**
	 * Sets the numeroMandat.
	 * @param numeroMandat The numeroMandat to set
	 */
	public void setNumeroMandat(String numeroMandat) {
		this.numeroMandat = numeroMandat;
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
	 * Sets the typeAction.
	 * @param typeAction The typeAction to set
	 */
	public void setTypeAction(long typeAction) {
		this.typeAction = typeAction;
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
	public boolean isApprouve() {
		return this.approuve;
	}

	/**
	 * @return
	 */
	public boolean isNonApprouve() {
		return this.nonApprouve;
	}

	/**
	 * @param string
	 */
	public void setApprouve(boolean approuve) {
		this.approuve = approuve;
	}

	/**
	 * @param string
	 */
	public void setNonApprouve(boolean nonApprouve) {
		this.nonApprouve = nonApprouve;
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