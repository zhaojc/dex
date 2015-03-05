package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulaire de consultation des mandats PSU.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface PSUMandat extends Modifiable{


    // Getters

    /**
     * Retourne la cl�.
     *
     * @return long Valeur de la cl� en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSite();

    /**
     * Retourne le type de l'action.
     *
     * @return long Valeur du type en caract�re.
     */
    public long getTypeAction();

    /**
     * Retourne le num�ro du mandat.
     *
     * @return String Valeur du mandaten caract�re.
     */
    public String getNumeroMandat();

    /**
     * Retourne le destinataire principal.
     *
     * @return String Valeur du destinataire en caract�re.
     */
    public String getDestinataireA();

	/**
	 * Retourne le destinataire en copie conforme.
	 *
	 * @return String Valeur du destinataire en caract�re.
	 */
	public String getDestinataireCC();

	/**
	 * Retourne le destinataire en copie conforme invisible.
	 *
	 * @return String Valeur du destinataire en caract�re.
	 */
	public String getDestinataireCCI();

	/**
	 * Retourne le message envoy� aux destinataires.
	 *
	 * @return String Valeur du message en caract�re.
	 */
	public String getMessage();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut en caract�re.
     */
    public long getStatut();

    /**
     * Retourne le genre du fichier.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenreFichier();

    /**
     * Retourne cl� de r�f�rence.
     *
     * @return long Valeur de la cl� en caract�re.
     */
    public long getReferenceCle();

    /**
     * Retourne le site de r�f�rence.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getReferenceSite();

    /**
     * Retourne le site o� s'applique le mandat.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSiteCible();

    /**
     * Retourne l'entit� cible.
     *
     * @return long Valeur de l'entit� en caract�re en caract�re.
     */
    public long getEntite();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription();

    /**
     * Retourne le genre du dossier.
     *
     * @return long Valeur du genre en caract�re.
     */
    public long getGenre();

	/**
	 * Retourne r�f�rence 1 du dossier.
	 *
	 * @return String Valeur reference1 en caract�re.
	 */
	public String getReference1();

    /**
     * Retourne la nature.
     *
     * @return long Valeur de la nature en caract�re.
     */
    public long getNature();

	/**
	 * Retourne le type de dossier.
	 *
	 * @return long Valeur du type en caract�re.
	 */
	public long getType();

	/**
	 * Retourne la cat�gorie.
	 *
	 * @return long Valeur de la cat�gorie en caract�re.
	 */
	public long getCategorie();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

   /**
	 * Retourne la date de d�part du mandat.
	 *
	 * @return Timestamp Valeur de la date de d�but en caract�re.
	 */
	public Timestamp getDateDebut();

	/**
	 * Retourne la date de fin du mandat.
	 *
	 * @return Timestamp Valeur de la date de fin en caract�re.
	 */
	public Timestamp getDateFin();

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date de cr�ation en caract�re.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caract�re.
     */
    public Timestamp getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return Timestamp Valeur de la date d'approbateur en caract�re.
     */
    public Timestamp getDateApprobation();

    /**
     * Retourne si le dossier est fond�.
     *
     * @return long Valeur de fond� en caract�re.
     */
    public long getFonde();

    /**
     * Retourne l'intervenant � suivre.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro en caract�re.
     */
    public String getNumeroDossier();

	/**
	 * Retourne le num�ro de dossier Cardex.
	 *
	 * @return String Valeur du num�ro en caract�re.
	 */
	public String getNumeroCardex();

    /**
     * Retourne le num�ro de la fiche sujet.
     *
     * @return String Valeur de la fiche en caract�re.
     */
    public String getFicheSujet();

    /**
     * Retourne le nom du sujet.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getSujetNom();

	/**
	 * Retourne le pr�nom du sujet.
	 *
	 * @return String Valeur du pr�nom en caract�re.
	 */
	public String getSujetPrenom();

	/**
	 * Retourne la fiche Soci�t�.
	 *
	 * @return String Valeur de la fiche en caract�re.
	 */
	public String getFicheSociete();

	/**
	 * Retourne le nom de la soci�t�.
	 *
	 * @return String Valeur du nom en caract�re.
	 */
	public String getSocieteNom();

	/**
	 * Retourne l'immatriculation du v�hicule.
	 *
	 * @return String Valeur de l'immatriculation en caract�re.
	 */
	public String getImmatriculation();

	/**
	 * Retourne la province d'origine du v�hicule.
	 *
	 * @return String Valeur de la province en caract�re.
	 */
	public String getProvince();

	/**
	 * Retourne un mot cl� dans les narrations.
	 *
	 * @return String Valeur du mot cl� en caract�re.
	 */
	public String getMotCle1();

	/**
	 * Retourne un mot cl� dans les narrations.
	 *
	 * @return String Valeur du mot cl� en caract�re.
	 */
	public String getMotCle2();

	/**
	 * Retourne un mot cl� dans les narrations.
	 *
	 * @return String Valeur du mot cl� en caract�re.
	 */
	public String getMotCle3();

	/**
	 * Retourne le total des actions consign�es pour le mandat dans la liste
	 * des r�sultats.
	 *
	 * @return long Valeur du total en caract�re.
	 */
	public long getTotal();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cl� en caract�re.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte l'approbateur.
     *
     * @param activite Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte une cat�gorie de dossier.
     *
     * @param commentaire Valeur de la cat�gorie en caract�re.
     */
    public void setCategorie(long categorie);

	/**
	 * Affecte la date de d�but.
	 *
	 * @param dateDebut Valeur de la date de d�but en caract�re.
	 */
	public void setDateDebut(Timestamp dateDebut);

	/**
	 * Affecte la date de fin.
	 *
	 * @param dateFin Valeur de la date de fin en caract�re.
	 */
	public void setDateFin(Timestamp dateFin);

    /**
     * Affecte la date d'approbation.
     *
     * @param dateApprobation Valeur de la date d'approbation en caract�re.
     */
    public void setDateApprobation(Timestamp dateApprobation);

	/**
	 * Affecte la date de cr�ation.
	 *
	 * @param dateCreation Valeur de la date de cr�ation en caract�re.
	 */
	public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une description.
     *
     * @return description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

    /**
     * Affecte un destinataire.
     *
     * @param devise Valeur du destinataire en caract�re.
     */
    public void setDestinataireA(String destinataireA);

	/**
	 * Affecte un destinataire.
	 *
	 * @param devise Valeur du destinataire en caract�re.
	 */
	public void setDestinataireCC(String destinataireCC);

	/**
	 * Affecte un destinataire.
	 *
	 * @param devise Valeur du destinataire en caract�re.
	 */
	public void setDestinataireCCI(String destinataireCCI);

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(long entite);

	/**
	 * Affecte une Fiche soci�t�.
	 *
	 * @param ficheSociete Valeur de la fiche en caract�re.
	 */
	public void setFicheSociete(String ficheSociete);

	/**
	 * Affecte une Fiche sujet.
	 *
	 * @param ficheSujet Valeur de la fiche en caract�re.
	 */
	public void setFicheSujet(String ficheSujet);

    /**
     * Affecte l'attribut fond�.
     *
     * @param fonde Valeur de fond� en caract�re.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un genre .
     *
     * @param genre Valeur du genre en caract�re.
     */
    public void setGenre(long genre);

	/**
	 * Affecte le genre du fichier.
	 *
	 * @param genreFichier Valeur du genre en caract�re.
	 */
	public void setGenreFichier(String genreFichier);

    /**
     * Affecte l'immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en
     * caract�re.
     */
    public void setImmatriculation(String immatriculation);

	/**
	 * Affecte l'intervenant.
	 *
	 * @param intervenant Valeur de l'intervenant en
	 * caract�re.
	 */
	public void setIntervenant(String intervenant);

    /**
     * Affecte le message.
     *
     * @param message Valeur du message de s�rie en
     * caract�re.
     */
    public void setMessage(String message);

    /**
     * Affecte un mot cl�.
     *
     * @param mot cl� Valeur du mot cl�
     * en caract�re.
     */
    public void setMotCle1(String motCle1);

	/**
	 * Affecte un mot cl�.
	 *
	 * @param mot cl� Valeur du mot cl�
	 * en caract�re.
	 */
	public void setMotCle2(String motCle2);

	/**
	 * Affecte un mot cl�.
	 *
	 * @param mot cl� Valeur du mot cl�
	 * en caract�re.
	 */
	public void setMotCle3(String motCle3);

    /**
     * Affecte la nature du dossier.
     *
     * @param nature Valeur du nature
     */
    public void setNature(long nature);

    /**
     * Affecte le num�ro Cardex.
     *
     * @param numeroCardex Valeur du numeroCardex
     */
    public void setNumeroCardex(String numeroCardex);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte un type d'action.
     *
     * @param typeAction Valeur type 
     * en caract�re.
     */
    public void setTypeAction(long typeAction);

    /**
     * Affecte le num�ro du mandat.
     *
     * @param numeroMandat Valeur de numeroMandat en caract�re.
     */
    public void setNumeroMandat(String numeroMandat);

    /**
     * Retourne la province.
     *
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province);

    /**
     * Affecte la cl� de la donn�e de r�f�rence.
     *
     * @param referenceCle Valeur de la cl� en caract�re.
     */
    public void setReferenceCle(long referenceCle);

	/**
	 * Affecte le site de la donn�e de r�f�rence.
	 *
	 * @param referenceSite Valeur du site en caract�re.
	 */
	public void setReferenceSite(long referenceSite);

    /**
     * Affecte un site cible.
     *
     * @param siteCible Valeur du site en caract�re.
     */
    public void setSiteCible(long siteCible);

    /**
     * Affecte le statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(long statut);

    /**
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caract�re.
     */
    public void setModifiable(Boolean isModifiable);

	/**
	 * Affecte le nom du sujet.
	 *
	 * @param sujetNom Valeur du sujetNom en caract�re.
	 */
	public void setSujetNom(String sujetNom);

	/**
	 * Affecte le pr�nom.
	 *
	 * @param sujetPrenom Valeur du sujetPrenom en caract�re.
	 */
	public void setSujetPrenom(String sujetPrenom);

	/**
	 * Affecte le nom de la soci�t�.
	 *
	 * @param societeNom Valeur du nom de soci�t� en caract�re.
	 */
	public void setSocieteNom(String societeNom);

	/**
	 * Affecte le type.
	 *
	 * @param type Valeur du type en caract�re.
	 */
	public void setType(long type);

	/**
	 * Affecte la r�f�rence 1 du dossier.
	 *
	 * @param reference1 Valeur de reference1 en caract�re.
	 */
	public void setReference1(String reference1);

	/**
	 * Affecte le total des actions consign�es.
	 *
	 * @param total Valeur de total en caract�re.
	 */
	public void setTotal(long total);

}