package com.lotoquebec.cardex.presentation.model;

import java.util.List;

import com.lotoquebec.cardex.presentation.model.form.DossierForm;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulatire de consultation de photo.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/19 17:56:29 $
 */
public interface PhotoHtmlForm {


	// Getters

	/**
	  * Retourne la cl� du dossier
	  *
	  * @return String Valeur de la cl�
	  */
	 public String getCleDossier();

	 /**
	  * Affecte la cl� du dossier
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
	  * Retourne le num�ro de dossier (ancienne r�f�rence)
	  *
	  * @return String Valeur de NumeroDossier
	  */
	 public String getNumeroDossier();

	 /**
	  * Affecte un num�ro de dossier
	  *
	  * @param numeroDossier Valeur de NumeroDossier
	  */
	 public void setNumeroDossier(String numeroDossier);

   /**
     * Retourne la r�f�rence
     *
     * @return String Valeur de la r�f�rence
     */
    public String getReference();

    /**
     * Affecte une r�f�rence
     *
     * @param reference Valeur de la r�f�rence
     */
    public void setReference(String reference);

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
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription();

    /**
     * Retourne le type de multim�dia.
     *
     * @return String Valeur du type de multim�dia en caract�re.
     */
    public String getTypeMultimedia();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne l'extension.
     *
     * @return String Valeur de l'extension en caract�re.
     */
    public String getExtension();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le lien multim�dia.
     *
     * @return String Valeur du lien multim�dia en caract�re.
     */
    public String getLienMultimedia();

    /**
     * Retourne le lien multim�dia du site.
     *
     * @return String Valeur du lien multim�dia du site en caract�re.
     */
    public String getLienSiteMultimedia();

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

    /**
     * Retourne le lien �l�ment.
     *
     * @return String Valeur du lien �l�ment en caract�re.
     */
    public String getLienElement();

    /**
     * Retourne le lien �l�ment du site.
     *
     * @return String Valeur du lien �l�ment du site en caract�re.
     */
    public String getLienSiteElement();

    /**
     * Retourne l'Url
     *
     * @return String Valeur de l'Url
     */
    public String getUrl();

    /**
     * Retourne la s�v�rit�
     *
     * @return String Valeur du code de s�v�rit�
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
     * Affecte une confidentialit�.
     *
     * @param site Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

    /**
     * Affecte un type de multim�dia.
     *
     * @param typeMultimedia Valeur du type de multim�dia en caract�re.
     */
    public void setTypeMultimedia(String typeMultimedia);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte une extension.
     *
     * @param intervenant Valeur de l'extension en caract�re.
     */
    public void setExtension(String extension);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un lien multim�dia.
     *
     * @param lien Valeur du lien multim�dia en caract�re.
     */
    public void setLienMultimedia(String lienMultimedia);

    /**
     * Affecte un lien multim�dia du site.
     *
     * @param lienSite Valeur du lien multim�dia du site en caract�re.
     */
    public void setLienSiteMultimedia(String lienSiteMultimedia);

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
     * Affecte un lien �l�ment.
     *
     * @param lienElement Valeur du lien �l�ment en caract�re.
     */
    public void setLienElement(String lienElement);

    /**
     * Affecte un lien �l�ment du site.
     *
     * @param lienSiteElement Valeur du lien �l�ment du site en caract�re.
     */
    public void setLienSiteElement(String lienSiteElement);

    /**
     * Affecte un Url.
     *
     * @param url Valeur de l'Url.
     */
    public void setUrl(String url);

    /**
     * Affecte la s�v�rit�
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
     * Test si la photo est attach� � un sujet.
     *
     * @return boolean True si la photo est attach� � un sujet.
     */
    public boolean isAttachDossier();

    /**
     * Test si la photo est attach� � un sujet.
     *
     * @return boolean True si la photo est attach� � un sujet.
     */
    public boolean isAttachSujet();

    /**
     * Determine si la photo est attach� � un dossier.
     *
     * @param isAttachSujet Est-ce que la photo est attach� � un dossier.
     * caract�re.
     */
    public void setAttachDossier(boolean isAttachDossier);

    /**
     * Determine si la photo est attach� � un sujet.
     *
     * @param isAttachSujet Est-ce que la photo est attach� � un sujet.
     * caract�re.
     */
    public void setAttachSujet(boolean isAttachSujet);

    /**
     * Retourne le dossier asssocie .
     *
     * @return DossierForm Valeur de la cle en caract�re.
     */
    public DossierHtmlForm getDossier();

    /**
     * Retourne le sujet asssocie.
     *
     * @return SujetForm Valeur de la cle en caract�re.
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