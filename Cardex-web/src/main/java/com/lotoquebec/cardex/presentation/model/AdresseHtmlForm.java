package com.lotoquebec.cardex.presentation.model;


/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/28 17:28:36 $
 */
public interface AdresseHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne l'adresse.
     *
     * @return String Valeur de l'adresse en caractère.
     */
    public String getAdresse();

    /**
     * Retourne la seconde adresse.
     *
     * @return String Valeur de la seconde adresse en caractère.
     */
    public String getAdresse2();

    /**
     * Retourne le premier numéro de téléphone.
     *
     * @return String Valeur du premier numéro de téléphone en caractère.
     */
    public String getTelephone1();

    /**
     * Retourne la première période d'utilisation du téléphone.
     *
     * @return String Valeur du code postal en caractère.
     */
    public String getPeriodeTelephone1();

    /**
     * Retourne la 2e période d'utilisation du téléphone.
     *
     * @return String Valeur du code postal en caractère.
     */
    public String getPeriodeTelephone2();

    /**
     * Retourne la 3e période d'utilisation du téléphone.
     *
     * @return String Valeur du code postal en caractère.
     */
    public String getPeriodeTelephone3();

    /**
     * Retourne le deuxième numéro de téléphone.
     *
     * @return String Valeur du deuxième numéro de téléphone en caractère.
     */
    public String getTelephone2();

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caractère.
     */
    public String getPays();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le troisième numéro de téléphone.
     *
     * @return String Valeur du troisième numéro de téléphone en caractère.
     */
    public String getTelephone3();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caractère.
     */
    public String getVille();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne le code postal.
     *
     * @return String Valeur du code postal en caractère.
     */
    public String getCodePostal();

    /**
     * Retourne le commentaire.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaire();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte une adresse.
     *
     * @param adresse Valeur de l'adresse en caractère.
     */
    public void setAdresse(String adresse);

    /**
     * Affecte une seconde adresse.
     *
     * @param adresse2 Valeur de la seconde adresse en caractère.
     */
    public void setAdresse2(String adresse2);

    /**
     * Affecte un premier numéro de téléphone.
     *
     * @param telephone1 Valeur du premier numéro de téléphone en caractère.
     */
    public void setTelephone1(String telephone1);

    /**
     * Affecte une première période d'utilisation du téléphone.
     *
     * @param telephone1 Valeur de la première période d'utilisation du téléphone en caractère.
     */
    public void setPeriodeTelephone1(String periodeTelephone1);

    /**
     * Affecte un deuxième numéro de téléphone en caractère.
     *
     * @param telephone2 Valeur du deuxième numéro de téléphone en caractère.
     */
    public void setTelephone2(String telephone2);

    /**
     * Affecte une 2e période d'utilisation du téléphone.
     *
     * @param telephone2 Valeur de la 2e période d'utilisation du téléphone en caractère.
     */
    public void setPeriodeTelephone2(String periodeTelephone2);

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays en caractère.
     */
    public void setPays(String pays);

    /**
     * Affecte un troisième numéro de téléphone.
     *
     * @param telephone3 Valeur du troisième numéro de téléphone en caractère.
     */
    public void setTelephone3(String telephone3);

    /**
     * Affecte une 3e période d'utilisation du téléphone.
     *
     * @param telephone3 Valeur de la 3e période d'utilisation du téléphone en caractère.
     */
    public void setPeriodeTelephone3(String periodeTelephone3);

   /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caractère.
     */
    public void setVille(String ville);

    /**
     * Affecte un code postal.
     *
     * @param codePostal Valeur du code postal en caractère.
     */
    public void setCodePostal(String codePostal);

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caractère.
     */
    public void setCommentaire(String commentaire);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
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
	 * Retourne si l'adresse provient du système RDD (Réseau des détaillants)
	 * @return
	 */
	public boolean isIndicateurRdd();

	/**
	 * @param string
	 */
	public void setIndicateurRdd(boolean indicateurRdd);


}