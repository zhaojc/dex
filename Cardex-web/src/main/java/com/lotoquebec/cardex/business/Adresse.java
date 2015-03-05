package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoQuebec.correcteurAdresse.AdresseEntree;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à une adresse.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.7 $, $Date: 2002/02/28 17:28:10 $
 */
public interface Adresse extends AdresseEntree {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

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
     * Retourne le deuxième numéro de téléphone.
     *
     * @return String Valeur du deuxième numéro de téléphone en caractère.
     */
    public String getTelephone2();

    /**
     * Retourne le pays.
     *
     * @return long Valeur du pays.
     */
    public long getPays();

    /**
     * Retourne le troisième numéro de téléphone.
     *
     * @return String Valeur du troisième numéro de téléphone en caractère.
     */
    public String getTelephone3();

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince();

    /**
     * Retourne la ville.
     *
     * @return long Valeur de la ville.
     */
    public long getVille();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

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
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

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
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

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
     * Affecte un deuxième numéro de téléphone.
     *
     * @param telephone2 Valeur du deuxième numéro de téléphone en caractère.
     */
    public void setTelephone2(String telephone2);

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays.
     */
    public void setPays(long pays);

    /**
     * Affecte un troisième numéro de téléphone.
     *
     * @param telephone3 Valeur du troisième numéro de téléphone en caractère.
     */
    public void setTelephone3(String telephone3);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville.
     */
    public void setVille(long ville);

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
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

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
	
	public long getPointCardinal();
	
	public void setPointCardinal(long pointCardinal);
	
	public long getTypeRue();
	
	public void setTypeRue(long typeRue);    
	
	public String getNumeroUnite();
	
	public void setNumeroUnite(String numeroUnite);
	
	public long getUnite();
	
	public void setUnite(long unite);
	
	public long getTypeUtilTelephone1();
	
	public void setTypeUtilTelephone1(long typeUtilTelephone1);
	
	public long getTypeUtilTelephone2();
	
	public void setTypeUtilTelephone2(long typeUtilTelephone2);
	
	public long getTypeUtilTelephone3();
	
	public void setTypeUtilTelephone3(long typeUtilTelephone3);
	
	public String getVilleDescription();

	public void setVilleDescription(String villeDescription);

	public String getPaysDescription();

	public void setPaysDescription(String paysDescription);

	public String getProvinceDescription();

	public void setProvinceDescription(String provinceDescription);

	public String getPointCardinalDescription();

	public void setPointCardinalDescription(String pointCardinalDescription);

	public String getTypeRueDescription();

	public void setTypeRueDescription(String typeRueDescription);
	
	public void setProvinceAbreviation(String provinceAbreviation);

	public void setUniteDescription(String uniteDescription);
	
	public void assignerDescription(CardexAuthenticationSubject subject) throws BusinessResourceException;
	
	public String getMessage();

	public void setMessage(String message);	

	//Indique si la donnée vient de RDD
	public boolean isIndicateurRdd();

	/**
	 * @param Boolean
	 */
	public void setIndicateurRdd(boolean indicateurRdd);
		
    /**
     * Retourne la première période d'utilisation du téléphone.
     *
     * @return String Valeur du code postal en caractère.
     */
    public long getPeriodeTelephone1();

    /**
     * Retourne la 2e période d'utilisation du téléphone.
     *
     * @return String Valeur du code postal en caractère.
     */
    public long getPeriodeTelephone2();

    /**
     * Retourne la 3e période d'utilisation du téléphone.
     *
     * @return String Valeur du code postal en caractère.
     */
    public long getPeriodeTelephone3();

    /**
     * Affecte une première période d'utilisation du téléphone.
     *
     * @param telephone1 Valeur de la première période d'utilisation du téléphone en caractère.
     */
    public void setPeriodeTelephone1(long periodeTelephone1);

    /**
     * Affecte une 2e période d'utilisation du téléphone.
     *
     * @param telephone2 Valeur de la 2e période d'utilisation du téléphone en caractère.
     */
    public void setPeriodeTelephone2(long periodeTelephone2);

    /**
     * Affecte une 3e période d'utilisation du téléphone.
     *
     * @param telephone3 Valeur de la 3e période d'utilisation du téléphone en caractère.
     */
    public void setPeriodeTelephone3(long periodeTelephone3);

}