package com.lotoquebec.cardex.business.vo;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * photo de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.13 $, $Date: 2002/04/30 17:47:13 $
 * @see com.lotoquebec.cardex.business.Photo
 */
public class PhotoVO implements Photo {

    private long cle = 0;
    private long site = 0;
	private long cleDossier = 0;
	private long siteDossier = 0;
    private long confidentialite = 0;
    private String description = "";
    private long typeMultimedia = 0;
    private Timestamp dateCreation = null;
    private String extension = "";
    private String intervenant = "";
    private long lienMultimedia = 0;
    private long lienSiteMultimedia = 0;
    private long lien = 0;
    private long lienSite = 0;
    private long lienElement = 0;
    private long lienSiteElement = 0;
    private String url = "";
    private int index = 0;
    private byte[] image;
    private File file;
    private String[] nomFichier = null;
    private String genreFichier = "";
    private String reference = "";
    private Set<Dossier> sujetInteretDossiers = new HashSet<Dossier>();
    private String numeroDossier = "";
    private long severite = 0;
    private Boolean attachDossier = false;
    private Boolean attachSujet = false;
    private Dossier dossier = new DossierVO();
    private Sujet sujet = new SujetVO();
    private Boolean selectionner = false;
    
    /**
     * Retourne le dossier asssocie .
     *
     * @return DossierForm Valeur de la cle en caractère.
     */
    public Dossier getDossier() {
        return this.dossier;
    }

    /**
     * Retourne le sujet asssocie.
     *
     * @return SujetForm Valeur de la cle en caractère.
     */
    public Sujet getSujet() {
        return this.sujet;
    }

    /**
     * Affecte un un dossier associe .
     *
     * @param dossier Le dossier associe.
     */
    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    /**
     * Affecte un sujet associe .
     *
     * @param sujet Le sujet associe.
     */
    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }


    /**
     * Test si la photo est attaché à un sujet.
     *
     * @return boolean True si la photo est attaché à un sujet.
     */
    public Boolean isAttachDossier() {
        return attachDossier;
    }

    /**
     * Test si la photo est attaché à un sujet.
     *
     * @return boolean True si la photo est attaché à un sujet.
     */
    public Boolean isAttachSujet() {
        return attachSujet;
    }

    /**
     * Determine si la photo est attaché à un dossier.
     *
     * @param attachSujet Est-ce que la photo est attaché à un dossier.
     * caractère.
     */
    public void setAttachDossier(Boolean attachDossier) {
      this.attachDossier = attachDossier;
    }

    /**
     * Determine si la photo est attaché à un sujet.
     *
     * @param isAttachSujet Est-ce que la photo est attaché à un sujet.
     * caractère.
     */
    public void setAttachSujet(Boolean attachSujet) {
      this.attachSujet = attachSujet;
    }


//Champs additionnels pour conserver les données provenant d'une recherche
//à partir de la Galerie

    /**
     * Constructeur de PhotoVO par défaut.
     */
    public PhotoVO() {}


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retourne le type de multimédia.
     *
     * @return long Valeur du type de multimédia.
     */
    public long getTypeMultimedia() {
        return this.typeMultimedia;
    }

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Retourne l'extension.
     *
     * @return String Valeur de l'extension en caractère.
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le lien multimédia.
     *
     * @return long Valeur du lien multimédia.
     */
    public long getLienMultimedia() {
        return this.lienMultimedia;
    }

    /**
     * Retourne le lien multimédia du site.
     *
     * @return long Valeur du lien multimédia du site.
     */
    public long getLienSiteMultimedia() {
        return this.lienSiteMultimedia;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne le lien élément.
     *
     * @return long Valeur du lien élément.
     */
    public long getLienElement() {
        return this.lienElement;
    }

    /**
     * Retourne le lien élément du site.
     *
     * @return long Valeur du lien élément du site.
     */
    public long getLienSiteElement() {
        return this.lienSiteElement;
    }

    /**
     * Retourne l'Url
     *
     * @return String Valeur de l'Url
     */
    public String getUrl() {
        return this.url;
    }

   /**
     * Retourne le nom de fichier
     *
     * @return String Valeur du nom de fichier
     */
    public String[] getNomFichier() {
      return this.nomFichier;
    }

   /**
     * Retourne le genre de fichier
     *
     * @return String Valeur du genre de fichier
     */
    public String getGenreFichier() {
      return this.genreFichier;
    }

   /**
     * Retourne la référence
     *
     * @return String Valeur de la référence
     */
    public String getReference() {
      return this.reference;
    }

   /**
     * Retourne la sévérité
     *
     * @return long Valeur de la sévérité
     */
    public long getSeverite() {
      return this.severite;
    }

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Affecte un type de multimédia.
     *
     * @param typeMultimedia Valeur du type de multimédia.
     */
    public void setTypeMultimedia(long typeMultimedia) {
        this.typeMultimedia = typeMultimedia;
    }

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte une extension.
     *
     * @param extension Valeur de l'extension en caractère.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un lien multimédia.
     *
     * @param multimedia Valeur du lien multimédia.
     */
    public void setLienMultimedia(long lienMultimedia) {
        this.lienMultimedia = lienMultimedia;
    }

    /**
     * Affecte un lien multimédia du site.
     *
     * @param lienSiteMultimedia Valeur du lien multimédia du site.
     */
    public void setLienSiteMultimedia(long lienSiteMultimedia) {
        this.lienSiteMultimedia = lienSiteMultimedia;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte un lien élément.
     *
     * @param lienElement Valeur du lien élément.
     */
    public void setLienElement(long lienElement) {
        this.lienElement = lienElement;
    }

    /**
     * Affecte un lien élément du site.
     *
     * @param lienSiteElement Valeur du lien élément du site.
     */
    public void setLienSiteElement(long lienSiteElement) {
        this.lienSiteElement = lienSiteElement;
    }

    /**
     * Affecte un Url.
     *
     * @param url Valeur de l'Url.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Affecte un nom de fichier.
     *
     * @param fichier Valeur du nom de fichier.
     */
    public void setNomFichier(String[] fichier){
      this.nomFichier = fichier;
    }

    /**
     * Affecte un genre de fichier.
     *
     * @param genreFichier Valeur du genre de fichier.
     */
    public void setGenreFichier(String genreFichier){
      this.genreFichier = genreFichier;
    }

    /**
     * Affecte une référence
     *
     * @param reference Valeur de la référence
     */
    public void setReference(String reference){
      this.reference = reference;
    }

    /**
     * Affecte une sévérité
     *
     * @param severite Valeur de la sévérité
     */
    public void setSeverite(long severite){
      this.severite = severite;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du PhotoVO.
     *
     * @return String Valeur de tout les attributs du PhotoVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[PhotoVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', typeMultimedia = '" + typeMultimedia);
        stringBuffer.append("', dateCreation = '"
                + TimestampFormat.format(dateCreation));
        stringBuffer.append("', extension = '" + extension);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', lienMultimedia = '" + lienMultimedia);
        stringBuffer.append("', lienSiteMultimedia = '" + lienSiteMultimedia);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', lienElement = '" + lienElement);
        stringBuffer.append("', lienSiteElement = '" + lienSiteElement);
        stringBuffer.append("', nomFichier = '" + Arrays.toString(nomFichier));
        stringBuffer.append("', url = '" + url);
        stringBuffer.append("', genre = '" + genreFichier);
        stringBuffer.append("', référence = '" + reference);
        stringBuffer.append("', sévérité = '" + severite);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the image.
	 * @return byte[]
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Returns the index.
	 * @return int
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the image.
	 * @param image The image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Sets the index.
	 * @param index The index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Returns the confidentialite.
	 * @return long
	 */
	public long getConfidentialite() {
		return confidentialite;
	}

	/**
	 * Sets the confidentialite.
	 * @param confidentialite The confidentialite to set
	 */
	public void setConfidentialite(long confidentialite) {
		this.confidentialite = confidentialite;
	}

	/**
	 * @return
	 */
	public String getNumeroDossier() {
		return numeroDossier;
	}

	/**
	 * @param string
	 */
	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	/**
	 * @param string
	 */
	public void setCleDossier(long cleDossier) {
		this.cleDossier = cleDossier;
	}

	/**
	 * @param string
	 */
	public void setSiteDossier(long siteDossier) {
		this.siteDossier = siteDossier;
	}

	/**
	 * @return
	 */
	public long getCleDossier() {
		return cleDossier;
	}

	/**
	 * @return
	 */
	public long getSiteDossier() {
		return siteDossier;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Boolean isSelectionner() {
		return selectionner;
	}
	public void setSelectionner(Boolean selectionner) {
		this.selectionner = selectionner;
	}

	public Boolean getAttachDossier() {
		return attachDossier;
	}

	public Boolean getAttachSujet() {
		return attachSujet;
	}

	public Boolean getSelectionner() {
		return selectionner;
	}

	public Set<Dossier> getSujetInteretDossiers() {
		return sujetInteretDossiers;
	}

	public void setSujetInteretDossiers(Set<Dossier> sujetInteretDossiers) {
		this.sujetInteretDossiers = sujetInteretDossiers;
	}


	
}