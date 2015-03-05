package com.lotoquebec.cardex.presentation.model.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheConsignationHtmlForm;
import com.lotoquebec.cardex.presentation.model.RapportAffichagePDFForm;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulatire de recherche de
 * consignation.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/29 15:39:24 $
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheConsignationHtmlForm
 */
public class CriteresRechercheConsignationForm extends ValidatorForm
        implements CriteresRechercheConsignationHtmlForm, RechercheListeResultat, RapportAffichagePDFForm {

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
    private String entite = "";
    private String intervenant = "";
	private String approuve = "";
	private String nonApprouve = "";
    private String siteOrigine = "";
    private String description = "";
	private String devise = "";
	private String denomination = "";
    private String dateDebut = "";
    private String dateFin = "";
    private String marque = "";
    private String modele = "";
    private String fournisseur = "";
    private String numeroSerie = "";
    private String typeConsignation = "";
    private String choixRapport = "";
    private ListeResultat listeResultat = new ListeResultat();
    private String classe = "";
    private int sequence = 0;
    
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init() {
       //this.entite = "";
       this.intervenant = "";
	   this.approuve = "";
	   this.nonApprouve = "";
       //this.siteOrigine = "";
       this.description = "";
       this.dateDebut = "";
       this.dateFin = "";
       this.marque = "";
       this.modele = "";
       this.fournisseur = "";
       this.numeroSerie = "";
       this.typeConsignation = "";
       this.choixRapport = "";
       this.denomination = "";
       this.devise = "";
       this.cascadeEGNTC = new HierarchieEGNTC();
       listeResultat.init();
       genererNumeroSequence();
    }

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.approuve = "";
		this.nonApprouve = "";
    }
    
    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }
    
    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSuiviForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheSuiviForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheConsignationForm : ");
        stringBuffer.append("entite = '" + entite);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', fournisseur = '" + fournisseur);
        stringBuffer.append("', numeroSerie = '" + numeroSerie);
        stringBuffer.append("', type = '" + typeConsignation);
        stringBuffer.append("', choixRapport = '" + choixRapport);

        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the dateDebut.
	 * @return String
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * Returns the dateFin.
	 * @return String
	 */
	public String getDateFin() {
		return dateFin;
	}

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenre() {
    	return cascadeEGNTC.get(HierarchieEGNTC.GENRE);
    }

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caractère.
     */
    public String getNature() {
    	return cascadeEGNTC.get(HierarchieEGNTC.NATURE);
    }

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public String getType() {
    	return cascadeEGNTC.get(HierarchieEGNTC.TYPE);
    }

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie() {
    	return cascadeEGNTC.get(HierarchieEGNTC.CATEGORIE);
    }

    /**
     * Affecte un genre.
     *
     * @param genre Valeur du genre en caractère.
     */
    public void setGenre(String genre) {
    	cascadeEGNTC.set(HierarchieEGNTC.GENRE, genre);
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caractère.
     */
    public void setNature(String nature) {
    	cascadeEGNTC.set(HierarchieEGNTC.NATURE, nature);
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setType(String type) {
        cascadeEGNTC.set(HierarchieEGNTC.TYPE, type);
    }

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur d'une catégorie en caractère.
     */
    public void setCategorie(String categorie) {
    	cascadeEGNTC.set(HierarchieEGNTC.CATEGORIE, categorie);
    }

    /**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the entite.
	 * @return String
	 */
	public String getEntite() {
		return entite;
	}

	/**
	 * Returns the fournisseur.
	 * @return String
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the marque.
	 * @return String
	 */
	public String getMarque() {
		return marque;
	}


	/**
	 * Returns the modele.
	 * @return String
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * Returns the numeroSerie.
	 * @return String
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}


	/**
	 * Returns the siteOrigine.
	 * @return String
	 */
	public String getSiteOrigine() {
		return siteOrigine;
	}

	/**
	 * Returns the type.
	 * @return String
	 */
	public String getTypeConsignation() {
		return typeConsignation;
	}

	/**
	 * Returns the typeResultat.
	 * @return String
	 */
	public String getChoixRapport() {
		return choixRapport;
	}

	/**
	 * Sets the dateDebut.
	 * @param dateDebut The dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Sets the dateFin.
	 * @param dateFin The dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the entite.
	 * @param entite The entite to set
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}

	/**
	 * Sets the fournisseur.
	 * @param fournisseur The fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the marque.
	 * @param marque The marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}


	/**
	 * Sets the modele.
	 * @param modele The modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * Sets the numeroSerie.
	 * @param numeroSerie The numeroSerie to set
	 */
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	/**
	 * Sets the siteOrigine.
	 * @param siteOrigine The siteOrigine to set
	 */
	public void setSiteOrigine(String siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setTypeConsignation(String typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	/**
	 * Sets the typeResultat.
	 * @param typeResultat The typeResultat to set
	 */
	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}

	/**
	 * @return
	 */
	public String getApprouve() {
		return approuve;
	}

	/**
	 * @return
	 */
	public String getNonApprouve() {
		return nonApprouve;
	}

	/**
	 * @param string
	 */
	public void setApprouve(String string) {
		approuve = string;
	}

	/**
	 * @param string
	 */
	public void setNonApprouve(String string) {
		nonApprouve = string;
	}

	/**
	 * @return
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param string
	 */
	public void setDenomination(String string) {
		denomination = string;
	}

	/**
	 * @return
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @param string
	 */
	public void setDevise(String string) {
		devise = string;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.model.form.RechercheListeResultat#getListeResultat()
	 */
	public ListeResultat getListeResultat() {
		return listeResultat;
	}
	
	public void setListeResultat(List list) {
		this.listeResultat.setResultatComplet( list );
	}
	
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	
}