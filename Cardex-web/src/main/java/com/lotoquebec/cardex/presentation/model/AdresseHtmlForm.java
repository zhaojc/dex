package com.lotoquebec.cardex.presentation.model;


/**
 * Permet de transiter les informations relatives � la consultation d'une
 * adresse de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/28 17:28:36 $
 */
public interface AdresseHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite();

    /**
     * Retourne l'adresse.
     *
     * @return String Valeur de l'adresse en caract�re.
     */
    public String getAdresse();

    /**
     * Retourne la seconde adresse.
     *
     * @return String Valeur de la seconde adresse en caract�re.
     */
    public String getAdresse2();

    /**
     * Retourne le premier num�ro de t�l�phone.
     *
     * @return String Valeur du premier num�ro de t�l�phone en caract�re.
     */
    public String getTelephone1();

    /**
     * Retourne la premi�re p�riode d'utilisation du t�l�phone.
     *
     * @return String Valeur du code postal en caract�re.
     */
    public String getPeriodeTelephone1();

    /**
     * Retourne la 2e p�riode d'utilisation du t�l�phone.
     *
     * @return String Valeur du code postal en caract�re.
     */
    public String getPeriodeTelephone2();

    /**
     * Retourne la 3e p�riode d'utilisation du t�l�phone.
     *
     * @return String Valeur du code postal en caract�re.
     */
    public String getPeriodeTelephone3();

    /**
     * Retourne le deuxi�me num�ro de t�l�phone.
     *
     * @return String Valeur du deuxi�me num�ro de t�l�phone en caract�re.
     */
    public String getTelephone2();

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caract�re.
     */
    public String getPays();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le troisi�me num�ro de t�l�phone.
     *
     * @return String Valeur du troisi�me num�ro de t�l�phone en caract�re.
     */
    public String getTelephone3();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caract�re.
     */
    public String getVille();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne le code postal.
     *
     * @return String Valeur du code postal en caract�re.
     */
    public String getCodePostal();

    /**
     * Retourne le commentaire.
     *
     * @return String Valeur du commentaire en caract�re.
     */
    public String getCommentaire();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caract�re.
     */
    public String getDateModification();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte une adresse.
     *
     * @param adresse Valeur de l'adresse en caract�re.
     */
    public void setAdresse(String adresse);

    /**
     * Affecte une seconde adresse.
     *
     * @param adresse2 Valeur de la seconde adresse en caract�re.
     */
    public void setAdresse2(String adresse2);

    /**
     * Affecte un premier num�ro de t�l�phone.
     *
     * @param telephone1 Valeur du premier num�ro de t�l�phone en caract�re.
     */
    public void setTelephone1(String telephone1);

    /**
     * Affecte une premi�re p�riode d'utilisation du t�l�phone.
     *
     * @param telephone1 Valeur de la premi�re p�riode d'utilisation du t�l�phone en caract�re.
     */
    public void setPeriodeTelephone1(String periodeTelephone1);

    /**
     * Affecte un deuxi�me num�ro de t�l�phone en caract�re.
     *
     * @param telephone2 Valeur du deuxi�me num�ro de t�l�phone en caract�re.
     */
    public void setTelephone2(String telephone2);

    /**
     * Affecte une 2e p�riode d'utilisation du t�l�phone.
     *
     * @param telephone2 Valeur de la 2e p�riode d'utilisation du t�l�phone en caract�re.
     */
    public void setPeriodeTelephone2(String periodeTelephone2);

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays en caract�re.
     */
    public void setPays(String pays);

    /**
     * Affecte un troisi�me num�ro de t�l�phone.
     *
     * @param telephone3 Valeur du troisi�me num�ro de t�l�phone en caract�re.
     */
    public void setTelephone3(String telephone3);

    /**
     * Affecte une 3e p�riode d'utilisation du t�l�phone.
     *
     * @param telephone3 Valeur de la 3e p�riode d'utilisation du t�l�phone en caract�re.
     */
    public void setPeriodeTelephone3(String periodeTelephone3);

   /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caract�re.
     */
    public void setVille(String ville);

    /**
     * Affecte un code postal.
     *
     * @param codePostal Valeur du code postal en caract�re.
     */
    public void setCodePostal(String codePostal);

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caract�re.
     */
    public void setCommentaire(String commentaire);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte la date du changement (pour l'audit).
     *
     * @param dateChangement Valeur dateChangement.
     */
    public void setDateChangement(String dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

	public String getAdresseElectronique1();
	
	public void setAdresseElectronique1(String adresseElectronique1);
	
	public String getAdresseElectronique2();
	
	public void setAdresseElectronique2(String adresseElectronique2);
	
	public String getAdressePostal();
	
	public void setAdressePostal(String adressePostal);
	
	public String getNomRue();
	
	public void setNomRue(String nomRue);
	
	public String getNumeroMunicipal();
	
	public void setNumeroMunicipal(String numeroMunicipal);
	
	public String getPointCardinal();
	
	public void setPointCardinal(String pointCardinal);
	
	public String getTypeRue();
	
	public void setTypeRue(String typeRue);
	
	public String getNumeroUnite();
	
	public void setNumeroUnite(String numeroUnite);
	
	public String getUnite();
	
	public void setUnite(String unite);
	
	public String getMessage();
	
	public void setMessage(String message);
	
	/**
	 * Retourne si l'adresse provient du syst�me RDD (R�seau des d�taillants)
	 * @return
	 */
	public boolean isIndicateurRdd();

	/**
	 * @param string
	 */
	public void setIndicateurRdd(boolean indicateurRdd);


}