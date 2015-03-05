package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.PhotoHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de consultation de
 * photo.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.PhotoHtmlForm
 */
public class PhotoForm  extends ValidatorForm implements PhotoHtmlForm, EntiteCardexForm, Serializable {

	private String cle = "";
	private String site = "";
    private String cleDossier = "";
    private String siteDossier = "";
    private String confidentialite = "";
    private String description = "";
    private String typeMultimedia = "";
    private String dateCreation = "";
    private String extension = "";
    private String intervenant = "";
    private String lienMultimedia = "";
    private String lienSiteMultimedia = "";
    private String lien = "";
    private String lienSite = "";
    private String lienElement = "";
    private String lienSiteElement = "";
    private String url = "";
    private String severite = "";
    private String severiteDescription = "";
    private String index = "";
    private byte[] image;
    protected FormFile cardex01;
    protected FormFile sourceFile;
    private String filePath = "";
    private boolean attachDossier;
    private boolean attachSujet;
    private boolean selectionner;
    private String reference = "";
	private String numeroDossier = "";
    private DossierHtmlForm dossier = null;
    private SujetHtmlForm sujet = null;
    private List<DossierForm> sujetInteretDossiers = new ArrayList<DossierForm>();

   /**
     * Retourne la r�f�rence
     *
     * @return String Valeur de la r�f�rence
     */
    public String getReference() {
      return this.reference;
    }

    /**
     * Affecte une r�f�rence
     *
     * @param reference Valeur de la r�f�rence
     */
    public void setReference(String reference){
      this.reference = reference;
    }

    /**
     * Retourne le dossier asssocie .
     *
     * @return DossierForm Valeur de la cle en caract�re.
     */
    public DossierHtmlForm getDossier() {
        return this.dossier;
    }

    /**
     * Retourne le sujet asssocie.
     *
     * @return SujetForm Valeur de la cle en caract�re.
     */
    public SujetHtmlForm getSujet() {
        return this.sujet;
    }

    /**
     * Affecte un un dossier associe .
     *
     * @param dossier Le dossier associe.
     */
    public void setDossier(DossierHtmlForm dossier) {
        this.dossier = dossier;
    }

    /**
     * Affecte un sujet associe .
     *
     * @param sujet Le sujet associe.
     */
    public void setSujet(SujetHtmlForm sujet) {
        this.sujet = sujet;
    }

    /**
     * Test si la photo est attach� � un sujet.
     *
     * @return boolean True si la photo est attach� � un sujet.
     */
    public boolean isAttachDossier() {
        return attachDossier;
    }

    /**
     * Test si la photo est attach� � un sujet.
     *
     * @return boolean True si la photo est attach� � un sujet.
     */
    public boolean isAttachSujet() {
        return attachSujet;
    }

    /**
     * Determine si la photo est attach� � un dossier.
     *
     * @param isAttachSujet Est-ce que la photo est attach� � un dossier.
     * caract�re.
     */
    public void setAttachDossier(boolean attachDossier) {
      this.attachDossier = attachDossier;
    }

    /**
     * Determine si la photo est attach� � un sujet.
     *
     * @param isAttachSujet Est-ce que la photo est attach� � un sujet.
     * caract�re.
     */
    public void setAttachSujet(boolean attachSujet) {
      this.attachSujet = attachSujet;
    }

    /**
     * Constructeur de PhotoForm par d�faut.
     */
    public PhotoForm() {}


    // Getters


    /**
     * Retourne une repr�sentation du fichier que l'usager upload�.
     *
     * @return cardex01 Valeur du fichier upload�.
     */
    public FormFile getCardex01() {
        return cardex01;
    }

    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription() {
        if (this.description !=null && this.description.length() > 50) {
          this.description = this.description.substring(this.description.length()-50,this.description.length());
        }
        return this.description;
    }

    /**
     * Retourne le type de multim�dia.
     *
     * @return String Valeur du type de multim�dia en caract�re.
     */
    public String getTypeMultimedia() {
        return this.typeMultimedia;
    }

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation() {
        return this.dateCreation;
    }

	public String getSansHeureDateCreation() {
		return StringUtils.left(dateCreation, 10);
	}
	
    /**
     * Retourne l'extension.
     *
     * @return String Valeur de l'extension en caract�re.
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le lien multim�dia.
     *
     * @return String Valeur du lien multim�dia en caract�re.
     */
    public String getLienMultimedia() {
        return this.lienMultimedia;
    }

    /**
     * Retourne le lien multim�dia du site.
     *
     * @return String Valeur du lien multim�dia du site en caract�re.
     */
    public String getLienSiteMultimedia() {
        return this.lienSiteMultimedia;
    }

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne le lien �l�ment.
     *
     * @return String Valeur du lien �l�ment en caract�re.
     */
    public String getLienElement() {
        return this.lienElement;
    }

    /**
     * Retourne le lien �l�ment du site.
     *
     * @return String Valeur du lien �l�ment du site en caract�re.
     */
    public String getLienSiteElement() {
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

    // Setters


    /**
     * Affecte une repr�sentation du fichier que l'usager upload�.
     *
     * @param cardex01 Valeur du fichier upload�.
     */
    public void setCardex01(FormFile cardex01) {
        this.cardex01 = cardex01;
    }

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caract�re.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Affecte un type de multim�dia.
     *
     * @param typeMultimedia Valeur du type de multim�dia en caract�re.
     */
    public void setTypeMultimedia(String typeMultimedia) {
        this.typeMultimedia = typeMultimedia;
    }

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte une extension.
     *
     * @param extension Valeur de l'extension en caract�re.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un lien multim�dia.
     *
     * @param lienMultimedia Valeur du lien multim�dia en caract�re.
     */
    public void setLienMultimedia(String lienMultimedia) {
        this.lienMultimedia = lienMultimedia;
    }

    /**
     * Affecte un lien multim�dia du site.
     *
     * @param lienSiteMultimedia Valeur du lien multim�dia du site en caract�re.
     */
    public void setLienSiteMultimedia(String lienSiteMultimedia) {
        this.lienSiteMultimedia = lienSiteMultimedia;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte un lien �l�ment.
     *
     * @param lien Valeur du lien �l�ment en caract�re.
     */
    public void setLienElement(String lienElement) {
        this.lienElement = lienElement;
    }

    /**
     * Affecte un lien �l�ment du site.
     *
     * @param lienSite Valeur du lien �l�ment du site en caract�re.
     */
    public void setLienSiteElement(String lienSiteElement) {
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
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
       this.cle = "";
       this.site = "";
       this.description = "";
       this.typeMultimedia = "";
       this.dateCreation = "";
       //this.extension = "";
       this.intervenant = "";
       this.lienMultimedia = "";
       this.lienSiteMultimedia = "";
       this.lien = "";
       this.lienSite = "";
       this.lienElement = "";
       this.lienSiteElement = "";
       this.url = "";
       this.cardex01 = null;
       this.attachDossier = false;
       this.attachSujet = false;
       this.dossier = null;
       this.sujet = null;
       this.selectionner = false;
    }


    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du PhotoForm.
     *
     * @return String Valeur de tout les attributs du PhotoForm en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[PhotoForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', typeMultimedia = '" + typeMultimedia);
        stringBuffer.append("', dateCreation = '" + dateCreation);
        stringBuffer.append("', extension = '" + extension);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', lienMultimedia = '" + lienMultimedia);
        stringBuffer.append("', lienSiteMultimedia = '" + lienSiteMultimedia);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', lienElement = '" + lienElement);
        stringBuffer.append("', lienSiteElement = '" + lienSiteElement);
        stringBuffer.append("', index = '" + index);
        //stringBuffer.append("', image = '" + image.length);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the severite.
	 * @return String
	 */
	public String getSeverite() {
		return severite;
	}

	/**
	 * Sets the severite.
	 * @param severite The severite to set
	 */
	public void setSeverite(String severite) {
		this.severite = severite;
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
	 * @return String
	 */
	public String getIndex() {
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
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Returns the confidentialite.
	 * @return String
	 */
	public String getConfidentialite() {
		return confidentialite;
	}

	/**
	 * Sets the confidentialite.
	 * @param confidentialite The confidentialite to set
	 */
	public void setConfidentialite(String confidentialite) {
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
	 * @return
	 */
	public String getCleDossier() {
		return cleDossier;
	}

	/**
	 * @return
	 */
	public String getSiteDossier() {
		return siteDossier;
	}

	/**
	 * @param string
	 */
	public void setCleDossier(String cleDossier) {
		this.cleDossier = cleDossier;
	}

	/**
	 * @param string
	 */
	public void setSiteDossier(String siteDossier) {
		this.siteDossier = siteDossier;
	}

	public FormFile getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(FormFile sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public String getFilePathDoubleSlash() {
		return StringUtils.replace(filePath, "\\", "\\\\");
	}	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FormFile getUploadImage() {
		
		if (cardex01 != null && cardex01.getFileSize() > 0){
			//filePath = "C:\\temp\\capture\\cardex01.jpg";
			return cardex01;
		}else
			return sourceFile;
	}

	public String getExtensionDeFilePath() {
		int lastIndex = StringUtils.lastIndexOfAny(filePath, ".");
		
		if (lastIndex > 0){
			int lenghtExtension = filePath.length() - lastIndex - 1; 
			
			if (lenghtExtension < 5)
				return StringUtils.right(filePath, lenghtExtension);
		}		
		return "";
	}

	public boolean isSelectionner() {
		return selectionner;
	}

	public void setSelectionner(boolean selectionner) {
		this.selectionner = selectionner;
	}
	
	public boolean isTailleAccepte(){
		FormFile   file = getUploadImage();
		
		if (file == null)
			return false;
		
		int mb = 7;
		
		return file.getFileSize() < (mb*1024000);		
	}
	
	public boolean isTaillePieceAccepte(){
		FormFile   file = getUploadImage();
		
		if (file == null)
			return false;
		
		int mb = 10;
		return file.getFileSize() < (mb*1024000);		
	}

	public boolean isPhoto(){
		return GlobalConstants.Image.EXTENTION_IMAGE.indexOf(extension.toUpperCase()) > -1;
	}

    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	severiteDescription = cache.obtenirLabel(subject, severite, new SeveriteCleListeCache(subject));
    }

	/**
	 * @return severiteDescription
	 */
	public String getSeveriteDescription() {
		return severiteDescription;
	}

	/**
	 * @param severiteDescription severiteDescription � d�finir
	 */
	public void setSeveriteDescription(String severiteDescription) {
		this.severiteDescription = severiteDescription;
	}

	public List<DossierForm> getSujetInteretDossiers() {
		return sujetInteretDossiers;
	}

	public void setSujetInteretDossiers(List<DossierForm> sujetInteretDossiers) {
		this.sujetInteretDossiers = sujetInteretDossiers;
	}


	
}