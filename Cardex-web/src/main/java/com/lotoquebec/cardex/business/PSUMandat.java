package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulaire de consultation des mandats PSU.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface PSUMandat extends Modifiable{


    // Getters

    /**
     * Retourne la clé.
     *
     * @return long Valeur de la clé en caractère.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSite();

    /**
     * Retourne le type de l'action.
     *
     * @return long Valeur du type en caractère.
     */
    public long getTypeAction();

    /**
     * Retourne le numéro du mandat.
     *
     * @return String Valeur du mandaten caractère.
     */
    public String getNumeroMandat();

    /**
     * Retourne le destinataire principal.
     *
     * @return String Valeur du destinataire en caractère.
     */
    public String getDestinataireA();

	/**
	 * Retourne le destinataire en copie conforme.
	 *
	 * @return String Valeur du destinataire en caractère.
	 */
	public String getDestinataireCC();

	/**
	 * Retourne le destinataire en copie conforme invisible.
	 *
	 * @return String Valeur du destinataire en caractère.
	 */
	public String getDestinataireCCI();

	/**
	 * Retourne le message envoyé aux destinataires.
	 *
	 * @return String Valeur du message en caractère.
	 */
	public String getMessage();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut en caractère.
     */
    public long getStatut();

    /**
     * Retourne le genre du fichier.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenreFichier();

    /**
     * Retourne clé de référence.
     *
     * @return long Valeur de la clé en caractère.
     */
    public long getReferenceCle();

    /**
     * Retourne le site de référence.
     *
     * @return long Valeur du site en caractère.
     */
    public long getReferenceSite();

    /**
     * Retourne le site où s'applique le mandat.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSiteCible();

    /**
     * Retourne l'entité cible.
     *
     * @return long Valeur de l'entité en caractère en caractère.
     */
    public long getEntite();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne le genre du dossier.
     *
     * @return long Valeur du genre en caractère.
     */
    public long getGenre();

	/**
	 * Retourne référence 1 du dossier.
	 *
	 * @return String Valeur reference1 en caractère.
	 */
	public String getReference1();

    /**
     * Retourne la nature.
     *
     * @return long Valeur de la nature en caractère.
     */
    public long getNature();

	/**
	 * Retourne le type de dossier.
	 *
	 * @return long Valeur du type en caractère.
	 */
	public long getType();

	/**
	 * Retourne la catégorie.
	 *
	 * @return long Valeur de la catégorie en caractère.
	 */
	public long getCategorie();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

   /**
	 * Retourne la date de départ du mandat.
	 *
	 * @return Timestamp Valeur de la date de début en caractère.
	 */
	public Timestamp getDateDebut();

	/**
	 * Retourne la date de fin du mandat.
	 *
	 * @return Timestamp Valeur de la date de fin en caractère.
	 */
	public Timestamp getDateFin();

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création en caractère.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caractère.
     */
    public Timestamp getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return Timestamp Valeur de la date d'approbateur en caractère.
     */
    public Timestamp getDateApprobation();

    /**
     * Retourne si le dossier est fondé.
     *
     * @return long Valeur de fondé en caractère.
     */
    public long getFonde();

    /**
     * Retourne l'intervenant à suivre.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro en caractère.
     */
    public String getNumeroDossier();

	/**
	 * Retourne le numéro de dossier Cardex.
	 *
	 * @return String Valeur du numéro en caractère.
	 */
	public String getNumeroCardex();

    /**
     * Retourne le numéro de la fiche sujet.
     *
     * @return String Valeur de la fiche en caractère.
     */
    public String getFicheSujet();

    /**
     * Retourne le nom du sujet.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getSujetNom();

	/**
	 * Retourne le prénom du sujet.
	 *
	 * @return String Valeur du prénom en caractère.
	 */
	public String getSujetPrenom();

	/**
	 * Retourne la fiche Société.
	 *
	 * @return String Valeur de la fiche en caractère.
	 */
	public String getFicheSociete();

	/**
	 * Retourne le nom de la société.
	 *
	 * @return String Valeur du nom en caractère.
	 */
	public String getSocieteNom();

	/**
	 * Retourne l'immatriculation du véhicule.
	 *
	 * @return String Valeur de l'immatriculation en caractère.
	 */
	public String getImmatriculation();

	/**
	 * Retourne la province d'origine du véhicule.
	 *
	 * @return String Valeur de la province en caractère.
	 */
	public String getProvince();

	/**
	 * Retourne un mot clé dans les narrations.
	 *
	 * @return String Valeur du mot clé en caractère.
	 */
	public String getMotCle1();

	/**
	 * Retourne un mot clé dans les narrations.
	 *
	 * @return String Valeur du mot clé en caractère.
	 */
	public String getMotCle2();

	/**
	 * Retourne un mot clé dans les narrations.
	 *
	 * @return String Valeur du mot clé en caractère.
	 */
	public String getMotCle3();

	/**
	 * Retourne le total des actions consignées pour le mandat dans la liste
	 * des résultats.
	 *
	 * @return long Valeur du total en caractère.
	 */
	public long getTotal();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la clé en caractère.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(long site);

    /**
     * Affecte l'approbateur.
     *
     * @param activite Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte une catégorie de dossier.
     *
     * @param commentaire Valeur de la catégorie en caractère.
     */
    public void setCategorie(long categorie);

	/**
	 * Affecte la date de début.
	 *
	 * @param dateDebut Valeur de la date de début en caractère.
	 */
	public void setDateDebut(Timestamp dateDebut);

	/**
	 * Affecte la date de fin.
	 *
	 * @param dateFin Valeur de la date de fin en caractère.
	 */
	public void setDateFin(Timestamp dateFin);

    /**
     * Affecte la date d'approbation.
     *
     * @param dateApprobation Valeur de la date d'approbation en caractère.
     */
    public void setDateApprobation(Timestamp dateApprobation);

	/**
	 * Affecte la date de création.
	 *
	 * @param dateCreation Valeur de la date de création en caractère.
	 */
	public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une description.
     *
     * @return description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte un destinataire.
     *
     * @param devise Valeur du destinataire en caractère.
     */
    public void setDestinataireA(String destinataireA);

	/**
	 * Affecte un destinataire.
	 *
	 * @param devise Valeur du destinataire en caractère.
	 */
	public void setDestinataireCC(String destinataireCC);

	/**
	 * Affecte un destinataire.
	 *
	 * @param devise Valeur du destinataire en caractère.
	 */
	public void setDestinataireCCI(String destinataireCCI);

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(long entite);

	/**
	 * Affecte une Fiche société.
	 *
	 * @param ficheSociete Valeur de la fiche en caractère.
	 */
	public void setFicheSociete(String ficheSociete);

	/**
	 * Affecte une Fiche sujet.
	 *
	 * @param ficheSujet Valeur de la fiche en caractère.
	 */
	public void setFicheSujet(String ficheSujet);

    /**
     * Affecte l'attribut fondé.
     *
     * @param fonde Valeur de fondé en caractère.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un genre .
     *
     * @param genre Valeur du genre en caractère.
     */
    public void setGenre(long genre);

	/**
	 * Affecte le genre du fichier.
	 *
	 * @param genreFichier Valeur du genre en caractère.
	 */
	public void setGenreFichier(String genreFichier);

    /**
     * Affecte l'immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en
     * caractère.
     */
    public void setImmatriculation(String immatriculation);

	/**
	 * Affecte l'intervenant.
	 *
	 * @param intervenant Valeur de l'intervenant en
	 * caractère.
	 */
	public void setIntervenant(String intervenant);

    /**
     * Affecte le message.
     *
     * @param message Valeur du message de série en
     * caractère.
     */
    public void setMessage(String message);

    /**
     * Affecte un mot clé.
     *
     * @param mot clé Valeur du mot clé
     * en caractère.
     */
    public void setMotCle1(String motCle1);

	/**
	 * Affecte un mot clé.
	 *
	 * @param mot clé Valeur du mot clé
	 * en caractère.
	 */
	public void setMotCle2(String motCle2);

	/**
	 * Affecte un mot clé.
	 *
	 * @param mot clé Valeur du mot clé
	 * en caractère.
	 */
	public void setMotCle3(String motCle3);

    /**
     * Affecte la nature du dossier.
     *
     * @param nature Valeur du nature
     */
    public void setNature(long nature);

    /**
     * Affecte le numéro Cardex.
     *
     * @param numeroCardex Valeur du numeroCardex
     */
    public void setNumeroCardex(String numeroCardex);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte un type d'action.
     *
     * @param typeAction Valeur type 
     * en caractère.
     */
    public void setTypeAction(long typeAction);

    /**
     * Affecte le numéro du mandat.
     *
     * @param numeroMandat Valeur de numeroMandat en caractère.
     */
    public void setNumeroMandat(String numeroMandat);

    /**
     * Retourne la province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province);

    /**
     * Affecte la clé de la donnée de référence.
     *
     * @param referenceCle Valeur de la clé en caractère.
     */
    public void setReferenceCle(long referenceCle);

	/**
	 * Affecte le site de la donnée de référence.
	 *
	 * @param referenceSite Valeur du site en caractère.
	 */
	public void setReferenceSite(long referenceSite);

    /**
     * Affecte un site cible.
     *
     * @param siteCible Valeur du site en caractère.
     */
    public void setSiteCible(long siteCible);

    /**
     * Affecte le statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(long statut);

    /**
     * Test si un suivi peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(Boolean isModifiable);

	/**
	 * Affecte le nom du sujet.
	 *
	 * @param sujetNom Valeur du sujetNom en caractère.
	 */
	public void setSujetNom(String sujetNom);

	/**
	 * Affecte le prénom.
	 *
	 * @param sujetPrenom Valeur du sujetPrenom en caractère.
	 */
	public void setSujetPrenom(String sujetPrenom);

	/**
	 * Affecte le nom de la société.
	 *
	 * @param societeNom Valeur du nom de société en caractère.
	 */
	public void setSocieteNom(String societeNom);

	/**
	 * Affecte le type.
	 *
	 * @param type Valeur du type en caractère.
	 */
	public void setType(long type);

	/**
	 * Affecte la référence 1 du dossier.
	 *
	 * @param reference1 Valeur de reference1 en caractère.
	 */
	public void setReference1(String reference1);

	/**
	 * Affecte le total des actions consignées.
	 *
	 * @param total Valeur de total en caractère.
	 */
	public void setTotal(long total);

}