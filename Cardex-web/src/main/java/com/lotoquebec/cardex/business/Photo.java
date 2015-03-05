package com.lotoquebec.cardex.business;

import java.io.File;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à une photo.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.14 $, $Date: 2002/04/30 17:47:10 $
 */
public interface Photo {


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
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité en caractère.
     */
    public long getConfidentialite();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne le type de multimédia.
     *
     * @return long Valeur du type de multimédia.
     */
    public long getTypeMultimedia();

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

    /**
     * Retourne l'extension.
     *
     * @return String Valeur de l'extension en caractère.
     */
    public String getExtension();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le lien multimédia.
     *
     * @return long Valeur du lien multimédia.
     */
    public long getLienMultimedia();

    /**
     * Retourne le lien multimédia du site.
     *
     * @return long Valeur du lien multimédia du site.
     */
    public long getLienSiteMultimedia();

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

    /**
     * Retourne le lien élément.
     *
     * @return long Valeur du lien élément.
     */
    public long getLienElement();

    /**
     * Retourne le lien élément du site.
     *
     * @return long Valeur du lien élément du site.
     */
    public long getLienSiteElement();


    /**
     * Retourne l'Url
     *
     * @return String Valeur de l'Url
     */
    public String getUrl();

    /**
     * Retourne le nom de fichier
     *
     * @return String Valeur du nom de fichier
     */
    public String[] getNomFichier();

    /**
     * Retourne le genre du fichier auquel est associée la photo
     *
     * @return String Valeur du genre
     */
    public String getGenreFichier();

	/**
	  * Retourne le numéro de dossier (ancienne référence)
	  *
	  * @return String Valeur de NumeroDossier
	  */
	 public String getNumeroDossier();

    /**
     * Retourne le numéro de référence du dossier ou du sujet
     *
     * @return String Valeur de la référence
     */
    public String getReference();

    /**
     * Retourne la sévérité du dossier ou du sujet
     *
     * @return String Valeur de la sévérité
     */
    public long getSeverite();

    /**
     * Retourne l'index de la photo dans la collection (sert pour l'affichage dans
     * le servlet AffichageImage)
     *
     * @return int Valeur de l'index
     */
    public int getIndex();

    /**
     * Retourne le contenu du champ Blob 
     *
     * @return String Valeur du contenu
     */
    public byte[] getImage();

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
     * Affecte une confidentialité.
     *
     * @param site Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte un type de multimédia.
     *
     * @param typeMultimedia Valeur du type de multimédia.
     */
    public void setTypeMultimedia(long typeMultimedia);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une extension.
     *
     * @param intervenant Valeur de l'extension en caractère.
     */
    public void setExtension(String extension);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un lien multimédia.
     *
     * @param lienMultimedia Valeur du lien multimédia.
     */
    public void setLienMultimedia(long lienMultimedia);

    /**
     * Affecte un lien multimédia du site.
     *
     * @param lienSiteMultimedia Valeur du lien multimédia du site.
     */
    public void setLienSiteMultimedia(long lienSiteMultimedia);

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

    /**
     * Affecte un lien élément.
     *
     * @param lienElement Valeur du lien élément.
     */
    public void setLienElement(long lienElement);

    /**
     * Affecte un lien élément du site.
     *
     * @param lienSiteElement Valeur du lien élément du site.
     */
    public void setLienSiteElement(long lienSiteElement);

    /**
     * Affecte un Url.
     *
     * @param url Valeur de l'Url.
     */
    public void setUrl(String url);

    /**
     * Affecte un nom de fichier.
     *
     * @param fichier Valeur du nom de fichier.
     */
    public void setNomFichier(String[] fichier);

    /**
     * Affecte un genre de fichier.
     *
     * @param genreFichier Valeur du genre de fichier.
     */
    public void setGenreFichier(String genreFichier);

    /**
     * Affecte une référence
     *
     * @param reference Valeur de la référence.
     */
    public void setReference(String reference);

   	 /**
	  * Affecte un numéro de dossier
	  *
	  * @param numeroDossier Valeur de NumeroDossier
	  */
	 public void setNumeroDossier(String numeroDossier);

	/**
     * Affecte une sévérité
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite);

    /**
     * Affecte l'index
     *
     * @param index Valeur du code.
     */
    public void setIndex(int index);

    /**
     * Affecte le contenu
     *
     * @param contenu Valeur du code.
     */
    public void setImage(byte[] image);

    /**
     * Test si la photo est attaché à un sujet.
     *
     * @return boolean True si la photo est attaché à un sujet.
     */
    public Boolean isAttachDossier();

    /**
     * Test si la photo est attaché à un sujet.
     *
     * @return boolean True si la photo est attaché à un sujet.
     */
    public Boolean isAttachSujet();

    /**
     * Determine si la photo est attaché à un dossier.
     *
     * @param isAttachSujet Est-ce que la photo est attaché à un dossier.
     * caractère.
     */
    public void setAttachDossier(Boolean isAttachDossier);

    /**
     * Determine si la photo est attaché à un sujet.
     *
     * @param isAttachSujet Est-ce que la photo est attaché à un sujet.
     * caractère.
     */
    public void setAttachSujet(Boolean isAttachSujet);

    /**
     * Retourne le dossier asssocie .
     *
     * @return DossierForm Valeur de la cle en caractère.
     */
    public Dossier getDossier();

    /**
     * Retourne le sujet asssocie.
     *
     * @return SujetForm Valeur de la cle en caractère.
     */
    public Sujet getSujet();

    /**
     * Affecte un un dossier associe .
     *
     * @param dossier Le dossier associe.
     */
    public void setDossier(Dossier dossier);

    /**
     * Affecte un sujet associe .
     *
     * @param sujet Le sujet associe.
     */
    public void setSujet(Sujet sujet);

	/**
	  * Retourne la clé du dossier
	  *
	  * @return String Valeur de la clé
	  */
	 public long getCleDossier();

	 /**
	  * Affecte la clé du dossier
	  *
	  * @param cleDossier Valeur de cleDossier
	  */
	 public void setCleDossier(long cleDossier);

	/**
	  * Retourne le site du dossier
	  *
	  * @return String Valeur du site
	  */
	 public long getSiteDossier();

	 /**
	  * Affecte le site du dossier
	  *
	  * @param siteDossier Valeur de siteDossier
	  */
	 public void setSiteDossier(long siteDossier);

	public File getFile();
	
	public void setFile(File file);

	public Boolean isSelectionner();
	
	public void setSelectionner(Boolean selectionner);

	public Set<Dossier> getSujetInteretDossiers();
	
	public void setSujetInteretDossiers(Set<Dossier> sujetInteretDossiers);
	
}