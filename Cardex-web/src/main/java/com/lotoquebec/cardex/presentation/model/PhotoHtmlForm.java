package com.lotoquebec.cardex.presentation.model;

import java.util.List;

import com.lotoquebec.cardex.presentation.model.form.DossierForm;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation de photo.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/19 17:56:29 $
 */
public interface PhotoHtmlForm {


	// Getters

	/**
	  * Retourne la clé du dossier
	  *
	  * @return String Valeur de la clé
	  */
	 public String getCleDossier();

	 /**
	  * Affecte la clé du dossier
	  *
	  * @param cleDossier Valeur de cleDossier
	  */
	 public void setCleDossier(String cleDossier);

	/**
	  * Retourne le site du dossier
	  *
	  * @return String Valeur du site
	  */
	 public String getSiteDossier();

	 /**
	  * Affecte le site du dossier
	  *
	  * @param siteDossier Valeur de siteDossier
	  */
	 public void setSiteDossier(String siteDossier);

	/**
	  * Retourne le numéro de dossier (ancienne référence)
	  *
	  * @return String Valeur de NumeroDossier
	  */
	 public String getNumeroDossier();

	 /**
	  * Affecte un numéro de dossier
	  *
	  * @param numeroDossier Valeur de NumeroDossier
	  */
	 public void setNumeroDossier(String numeroDossier);

   /**
     * Retourne la référence
     *
     * @return String Valeur de la référence
     */
    public String getReference();

    /**
     * Affecte une référence
     *
     * @param reference Valeur de la référence
     */
    public void setReference(String reference);

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
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne le type de multimédia.
     *
     * @return String Valeur du type de multimédia en caractère.
     */
    public String getTypeMultimedia();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

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
     * @return String Valeur du lien multimédia en caractère.
     */
    public String getLienMultimedia();

    /**
     * Retourne le lien multimédia du site.
     *
     * @return String Valeur du lien multimédia du site en caractère.
     */
    public String getLienSiteMultimedia();

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

    /**
     * Retourne le lien élément.
     *
     * @return String Valeur du lien élément en caractère.
     */
    public String getLienElement();

    /**
     * Retourne le lien élément du site.
     *
     * @return String Valeur du lien élément du site en caractère.
     */
    public String getLienSiteElement();

    /**
     * Retourne l'Url
     *
     * @return String Valeur de l'Url
     */
    public String getUrl();

    /**
     * Retourne la sévérité
     *
     * @return String Valeur du code de sévérité
     */
    public String getSeverite();

    /**
     * Retourne l'index de la photo dans la collection (sert pour l'affichage dans
     * le servlet AffichageImage)
     *
     * @return String Valeur de l'index
     */
    public String getIndex();

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
     * Affecte une confidentialité.
     *
     * @param site Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte un type de multimédia.
     *
     * @param typeMultimedia Valeur du type de multimédia en caractère.
     */
    public void setTypeMultimedia(String typeMultimedia);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

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
     * @param lien Valeur du lien multimédia en caractère.
     */
    public void setLienMultimedia(String lienMultimedia);

    /**
     * Affecte un lien multimédia du site.
     *
     * @param lienSite Valeur du lien multimédia du site en caractère.
     */
    public void setLienSiteMultimedia(String lienSiteMultimedia);

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
     * Affecte un lien élément.
     *
     * @param lienElement Valeur du lien élément en caractère.
     */
    public void setLienElement(String lienElement);

    /**
     * Affecte un lien élément du site.
     *
     * @param lienSiteElement Valeur du lien élément du site en caractère.
     */
    public void setLienSiteElement(String lienSiteElement);

    /**
     * Affecte un Url.
     *
     * @param url Valeur de l'Url.
     */
    public void setUrl(String url);

    /**
     * Affecte la sévérité
     *
     * @param severite Valeur du code.
     */
    public void setSeverite(String severite);

    /**
     * Affecte l'index
     *
     * @param index Valeur du code.
     */
    public void setIndex(String index);

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
    public boolean isAttachDossier();

    /**
     * Test si la photo est attaché à un sujet.
     *
     * @return boolean True si la photo est attaché à un sujet.
     */
    public boolean isAttachSujet();

    /**
     * Determine si la photo est attaché à un dossier.
     *
     * @param isAttachSujet Est-ce que la photo est attaché à un dossier.
     * caractère.
     */
    public void setAttachDossier(boolean isAttachDossier);

    /**
     * Determine si la photo est attaché à un sujet.
     *
     * @param isAttachSujet Est-ce que la photo est attaché à un sujet.
     * caractère.
     */
    public void setAttachSujet(boolean isAttachSujet);

    /**
     * Retourne le dossier asssocie .
     *
     * @return DossierForm Valeur de la cle en caractère.
     */
    public DossierHtmlForm getDossier();

    /**
     * Retourne le sujet asssocie.
     *
     * @return SujetForm Valeur de la cle en caractère.
     */
    public SujetHtmlForm getSujet();

    /**
     * Affecte un un dossier associe .
     *
     * @param dossier Le dossier associe.
     */
    public void setDossier(DossierHtmlForm dossier);

    /**
     * Affecte un sujet associe .
     *
     * @param sujet Le sujet associe.
     */
    public void setSujet(SujetHtmlForm sujet);
    
    public List<DossierForm> getSujetInteretDossiers();

}