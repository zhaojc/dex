package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresRechercheVehicule;

/**
 * Permet de transiter les informations relatives à la recherche d'une
 * société de la couche présentation à la couche d'affaire.
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
     * Constructeur de CriteresRechercheVehiculeVO par défaut.
     */
    public CriteresRechercheVehiculeVO() {}


    // Getters


    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite() {
        return this.entite;
    }

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
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
     * Retourne le modèle.
     *
     * @return long Valeur du modèle.
     */
    public long getModele() {
        return this.modele;
    }

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne la première particularité.
     *
     * @return long Valeur de la première particularité.
     */
    public long getParticularite1() {
        return this.particularite1;
    }

    /**
     * Retourne la deuxième particularité.
     *
     * @return long Valeur de la deuxième particularité.
     */
    public long getParticularite2() {
        return this.particularite2;
    }

    /**
     * Retourne la troisième particularité.
     *
     * @return long Valeur de la troisième particularité.
     */
    public long getParticularite3() {
        return this.particularite3;
    }

    /**
     * Retourne la quatrième particularité.
     *
     * @return long Valeur de la quatrième particularité.
     */
    public long getParticularite4() {
        return this.particularite4;
    }

    /**
     * Retourne la cinquième particularité.
     *
     * @return long Valeur de la cinquième particularité.
     */
    public long getParticularite5() {
        return this.particularite5;
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


    // Setters


    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
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
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle.
     */
    public void setModele(long modele) {
        this.modele = modele;
    }

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }
 
    /**
     * Affecte une première particularité.
     *
     * @param particularite1 Valeur de la première particularité.
     */
    public void setParticularite1(long particularite1) {
        this.particularite1 = particularite1;
    }

    /**
     * Affecte une deuxième particularité.
     *
     * @param particularite2 Valeur de la deuxième particularité.
     */
    public void setParticularite2(long particularite2) {
        this.particularite2 = particularite2;
    }

    /**
     * Affecte une troisième particularité.
     *
     * @param particularite3 Valeur de la troisième particularité.
     */
    public void setParticularite3(long particularite3) {
        this.particularite3 = particularite3;
    }

    /**
     * Affecte une quatrième particularité.
     *
     * @param particularite4 Valeur de la quatrième particularité.
     */
    public void setParticularite4(long particularite4) {
        this.particularite4 = particularite4;
    }

    /**
     * Affecte une cinquième particularité.
     *
     * @param particularite5 Valeur de la cinquième particularité.
     */
    public void setParticularite5(long particularite5) {
        this.particularite5 = particularite5;
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
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheVehiculeVO.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheVehiculeVO en caractère.
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
	 * @param siteOrigine siteOrigine à définir
	 */
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

}