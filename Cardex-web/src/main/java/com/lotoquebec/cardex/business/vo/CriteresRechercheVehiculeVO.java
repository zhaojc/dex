package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresRechercheVehicule;

/**
 * Permet de transiter les informations relatives � la recherche d'une
 * soci�t� de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.11 $, $Date: 2002/02/25 19:38:44 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheVehicule
 */
public class CriteresRechercheVehiculeVO implements CriteresRechercheVehicule {

    private String immatriculation = "";
    private String numeroFiche = "";
    private String province = "";
    private long cleProvince = 0;
    private long marque = 0;
    private long modele = 0;
    private long modeleMarque = 0;
    private long confidentialite = 0;
    private long entite = 0;
    private long siteOrigine = 0;
	private long particularite1 = 0;
    private long particularite2 = 0;
    private long particularite3 = 0;
    private long particularite4 = 0;
    private long particularite5 = 0;
    private String ordreTriRecherche = "";
    private Boolean ordreCroissantRecherche = true;
    private long maximumResultatsRecherche = 0;
    private int sequence = 0;
    
    /**
     * Constructeur de CriteresRechercheVehiculeVO par d�faut.
     */
    public CriteresRechercheVehiculeVO() {}


    // Getters


    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite() {
        return this.entite;
    }

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * @return the numeroFiche
     */
    public String getNumeroFiche()
    {
        return numeroFiche;
    }


    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public long getMarque() {
        return this.marque;
    }

    /**
     * Retourne le mod�le.
     *
     * @return long Valeur du mod�le.
     */
    public long getModele() {
        return this.modele;
    }

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne la premi�re particularit�.
     *
     * @return long Valeur de la premi�re particularit�.
     */
    public long getParticularite1() {
        return this.particularite1;
    }

    /**
     * Retourne la deuxi�me particularit�.
     *
     * @return long Valeur de la deuxi�me particularit�.
     */
    public long getParticularite2() {
        return this.particularite2;
    }

    /**
     * Retourne la troisi�me particularit�.
     *
     * @return long Valeur de la troisi�me particularit�.
     */
    public long getParticularite3() {
        return this.particularite3;
    }

    /**
     * Retourne la quatri�me particularit�.
     *
     * @return long Valeur de la quatri�me particularit�.
     */
    public long getParticularite4() {
        return this.particularite4;
    }

    /**
     * Retourne la cinqui�me particularit�.
     *
     * @return long Valeur de la cinqui�me particularit�.
     */
    public long getParticularite5() {
        return this.particularite5;
    }

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }


    // Setters


    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }


    /**
     * @param numeroFiche the numeroFiche to set
     */
    public void setNumeroFiche(String numeroFiche)
    {
        this.numeroFiche = numeroFiche;
    }


    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque) {
        this.marque = marque;
    }

    /**
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le.
     */
    public void setModele(long modele) {
        this.modele = modele;
    }

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }
 
    /**
     * Affecte une premi�re particularit�.
     *
     * @param particularite1 Valeur de la premi�re particularit�.
     */
    public void setParticularite1(long particularite1) {
        this.particularite1 = particularite1;
    }

    /**
     * Affecte une deuxi�me particularit�.
     *
     * @param particularite2 Valeur de la deuxi�me particularit�.
     */
    public void setParticularite2(long particularite2) {
        this.particularite2 = particularite2;
    }

    /**
     * Affecte une troisi�me particularit�.
     *
     * @param particularite3 Valeur de la troisi�me particularit�.
     */
    public void setParticularite3(long particularite3) {
        this.particularite3 = particularite3;
    }

    /**
     * Affecte une quatri�me particularit�.
     *
     * @param particularite4 Valeur de la quatri�me particularit�.
     */
    public void setParticularite4(long particularite4) {
        this.particularite4 = particularite4;
    }

    /**
     * Affecte une cinqui�me particularit�.
     *
     * @param particularite5 Valeur de la cinqui�me particularit�.
     */
    public void setParticularite5(long particularite5) {
        this.particularite5 = particularite5;
    }

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheVehiculeVO.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheVehiculeVO en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheVehiculeVO : ");
        stringBuffer.append("', immatriculation = '" + immatriculation);
        stringBuffer.append("', numeroFiche = '" + numeroFiche);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', cleProvince = '" + cleProvince);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', particularite1 = '" + particularite1);
        stringBuffer.append("', particularite2 = '" + particularite2);
        stringBuffer.append("', particularite3 = '" + particularite3);
        stringBuffer.append("', particularite4 = '" + particularite4);
        stringBuffer.append("', particularite5 = '" + particularite5);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the modeleMarque.
	 * @return long
	 */
	public long getModeleMarque() {
		return modeleMarque;
	}

	/**
	 * Sets the modeleMarque.
	 * @param modeleMarque The modeleMarque to set
	 */
	public void setModeleMarque(long modeleMarque) {
		this.modeleMarque = modeleMarque;
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

	public long getCleProvince() {
		return cleProvince;
	}

	public void setCleProvince(long cleProvince) {
		this.cleProvince = cleProvince;
	}
	
    /**
	 * @return siteOrigine
	 */
	public long getSiteOrigine() {
		return siteOrigine;
	}


	/**
	 * @param siteOrigine siteOrigine � d�finir
	 */
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

}