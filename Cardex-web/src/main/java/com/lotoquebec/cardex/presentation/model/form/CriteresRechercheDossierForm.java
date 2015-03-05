package com.lotoquebec.cardex.presentation.model.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheDossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EntiteCardexLiaison;
import com.lotoquebec.cardex.presentation.model.NumeroCardex;
import com.lotoquebec.cardex.presentation.model.RapportAffichagePDFForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de recherche de
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/06 01:47:48 $
 * @see
 * com.lotoquebec.cardex.presentation.model.CriteresRechercheDossierHtmlForm
 */
public class CriteresRechercheDossierForm extends ValidatorForm
        implements CriteresRechercheDossierHtmlForm, RechercheListeResultat, EntiteCardexLiaison, RapportAffichagePDFForm {

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
    private String       siteOrigine = "";
    private String       siteApplicable = "";
    private String       statut = "";
    private String       service = "";
    private String       fonde = "";
    private String       intervenant = "";
    private NumeroCardex numeroCardex = new NumeroCardex();
    private String       numeroDossier = "";
    private String       numeroFicheSujet = "";
    private String       descriptif = "";
    private String       reference1 = "";
    private String       reference2 = "";
    private String       reference3 = "";
    private String       reference5 = "";
    private String       dateDebutDu = "";
    private String       dateFinDu = "";
    private String       dateDebutAu = "";
    private String       dateFinAu = "";
    private String       typeCategorie = "";
    private String       groupesIntervenants = "";
    private String       severite = "";
    private String       confidentialite = "";
    private String       origine = "";
    private String       endroit = "";
    private String       localisation = "";
    private String       referenceVideo = "";
    private String       enregistrementNumerique = "";
    private String       enregistrementConserve = "";
    private String       dateCreationDu = "";
    private String       dateCreationAu = "";
    private String       choixRapport = "";
    private String 		 typeJeu = "";
    private String 		 jeu = "";
    private String classe = "";
    private String	rechercherSousCategorie = ""; //sert � rechercher uniquement les sous-cat�gories
    private String	rechercherTous = ""; //Sert � rechercher tous les dossiers et les sous-cat�gories
    private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
    private DossierHtmlForm dossier = null;
    private SujetHtmlForm sujet = null;
    private SocieteHtmlForm societe = null;
    private VehiculeHtmlForm vehicule = null;
    private EntiteCardexForm entiteCardexLiaison = null;
    private int sequence = 0;
    private String nombreDossiers = ""; //sert pour le rapport CDX_0229
    //D�termine si le r�le est requis. Pas n�cessaire dans le cas d'une liaison entre 2 dossiers.
    private boolean lienRoleRequis = true;
    
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
     * Retourne le societe asssocie  .
     *
     * @return SocieteForm Valeur de la cle en caract�re.
     */
    public SocieteHtmlForm getSociete() {
        return this.societe;
    }

    /**
     * Retourne le societe asssocie .
     *
     * @return SocieteForm Valeur de la cle en caract�re.
     */
    public VehiculeHtmlForm getVehicule() {
        return this.vehicule;
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
     * Affecte un vehicule associe .
     *
     * @param vehicule Le vehicule associe.
     */
    public void setVehicule(VehiculeHtmlForm vehicule) {
        this.vehicule = vehicule;
    }

    /**
     * Affecte une societe associe .
     *
     * @param societe Le societe associe.
     */
    public void setSociete(SocieteHtmlForm societe) {
        this.societe = societe;
    }


    /**
     * Constructeur de CriteresRechercheDossierForm par d�faut.
     */
    public CriteresRechercheDossierForm() {}


    // Getters

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite() {
    	return cascadeEGNTC.get(HierarchieEGNTC.ENTITE);
    }

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine() {
        return this.siteOrigine;
    }

    /**
     * Retourne le site applicable.
     *
     * @return String Valeur du site applicable en caract�re.
     */
    public String getSiteApplicable() {
        return this.siteApplicable;
    }

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenre() {
    	return cascadeEGNTC.get(HierarchieEGNTC.GENRE);
    }

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caract�re.
     */
    public String getNature() {
    	return cascadeEGNTC.get(HierarchieEGNTC.NATURE);
    }

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType() {
    	return cascadeEGNTC.get(HierarchieEGNTC.TYPE);
    }

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie() {
    	return cascadeEGNTC.get(HierarchieEGNTC.CATEGORIE);
    }

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut() {
        return this.statut;
    }

    /**
     * Retourne l'attribut "fonde".
     *
     * @return String Valeur de l'attribut "fonde" en caract�re.
     */
    public String getFonde() {
        return this.fonde;
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
     * Retourne le num�ro de cardex.
     *
     * @return NumeroCardex Valeur du num�ro de cardex.
     */
    public NumeroCardex getNumeroCardex() {
        return this.numeroCardex;
    }

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le num�ro de fiche sujet.
     *
     * @return String Valeur du num�ro de fiche sujet en caract�re.
     */
    public String getNumeroFicheSujet() {
        return this.numeroFicheSujet;
    }

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2() {
        return this.reference2;
    }

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�re.
     */
    public String getReference3() {
        return this.reference3;
    }

    /**
     * Retourne la cinqui�me r�f�rence.
     *
     * @return String Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public String getReference5() {
        return this.reference5;
    }

    /**
     * Retourne la date de d�but du.
     *
     * @return String Valeur de la date de d�but du en caract�re.
     */
    public String getDateDebutDu() {
        return this.dateDebutDu;
    }

    /**
     * Retourne la date de fin du.
     *
     * @return String Valeur de la date de fin du en caract�re.
     */
    public String getDateFinDu() {
        return this.dateFinDu;
    }

    /**
     * Retourne la date de d�but au.
     *
     * @return String Valeur de la date de d�but au en caract�re.
     */
    public String getDateDebutAu() {
        return this.dateDebutAu;
    }

    /**
     * Retourne la date de fin au.
     *
     * @return String Valeur de la date de fin au en caract�re.
     */
    public String getDateFinAu() {
        return this.dateFinAu;
    }

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine() {
        return this.origine;
    }

    /**
     * Retourne la r�f�rence vid�o.
     *
     * @return String Valeur de la r�f�rence vid�o en caract�re.
     */
    public String getReferenceVideo() {
        return this.referenceVideo;
    }

    /**
     * Retourne la date de cr�ation du.
     *
     * @return String Valeur de la date de cr�ation du en caract�re.
     */
    public String getDateCreationDu() {
        return this.dateCreationDu;
    }

    /**
     * Retourne la date de cr�ation au.
     *
     * @return String Valeur de la date de cr�ation au en caract�re.
     */
    public String getDateCreationAu() {
        return this.dateCreationAu;
    }


    /**
     * Retourne une collection de dossiers.
     *
     * @return Collection Valeur de la collection de dossiers.
     */
    /*public Collection getDossiers() {
        return this.dossiers;
    }*/


    // Setters

    /**
     * Affecte une societe associe a la narration.
     *
     * @param societe Le societe associe.
     */
    public void setSociete(SocieteForm societe) {
        this.societe = societe;
    }

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite) {
        cascadeEGNTC.set(HierarchieEGNTC.ENTITE, entite);
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setSiteOrigine(String siteOrigine) {
        this.siteOrigine = siteOrigine;
    }

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable en caract�re.
     */
    public void setSiteApplicable(String siteApplicable) {
        this.siteApplicable = siteApplicable;
    }

    /**
     * Affecte un genre.
     *
     * @param genre Valeur du genre en caract�re.
     */
    public void setGenre(String genre) {
    	cascadeEGNTC.set(HierarchieEGNTC.GENRE, genre);
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caract�re.
     */
    public void setNature(String nature) {
    	cascadeEGNTC.set(HierarchieEGNTC.NATURE, nature);
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setType(String type) {
        cascadeEGNTC.set(HierarchieEGNTC.TYPE, type);
    }

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur d'une cat�gorie en caract�re.
     */
    public void setCategorie(String categorie) {
    	cascadeEGNTC.set(HierarchieEGNTC.CATEGORIE, categorie);
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur d'un statut en caract�re.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur de l'attribut "fonde" en caract�re.
     */
    public void setFonde(String fonde) {
        this.fonde = fonde;
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
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex.
     */
    public void setNumeroCardex(NumeroCardex numeroCardex) {
        this.numeroCardex = numeroCardex;
    }

    /**
     * Affecte un num�ro de cardex.
     *
     * @param stringNumeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String stringNumeroCardex) {
        numeroCardex.setNumeroCardex(stringNumeroCardex);
    }

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un num�ro de fiche sujet.
     *
     * @param numeroFicheSujet Valeur du num�ro de fiche sujet en caract�re.
     */
    public void setNumeroFicheSujet(String numeroFicheSujet) {
        this.numeroFicheSujet = numeroFicheSujet;
    }

    /**
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    /**
     * Affecte une deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    /**
     * Affecte une troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3) {
        this.reference3 = reference3;
    }

    /**
     * Affecte une cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference5) {
        this.reference5 = reference5;
    }

    /**
     * Affecte une date de d�but du.
     *
     * @param dateDebutDu Valeur de la date de d�but du en caract�re.
     */
    public void setDateDebutDu(String dateDebutDu) {
        this.dateDebutDu = dateDebutDu;
    }

    /**
     * Affecte une date de fin du.
     *
     * @param dateFinDu Valeur de la date de fin du en caract�re.
     */
    public void setDateFinDu(String dateFinDu) {
        this.dateFinDu = dateFinDu;
    }

    /**
     * Affecte une date de d�but au.
     *
     * @param dateDebutAu Valeur de la date de d�but au en caract�re.
     */
    public void setDateDebutAu(String dateDebutAu) {
        this.dateDebutAu = dateDebutAu;
    }

    /**
     * Affecte une date de fin au.
     *
     * @param dateFinAu Valeur de la date de fin au en caract�re.
     */
    public void setDateFinAu(String dateFinAu) {
        this.dateFinAu = dateFinAu;
    }

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeverite(String severite) {
        this.severite = severite;
    }

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de la origine en caract�re.
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * Affecte une r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String referenceVideo) {
        this.referenceVideo = referenceVideo;
    }

    /**
     * Affecte une date de cr�ation du.
     *
     * @param dateCreationDu Valeur de la date de cr�ation du en caract�re.
     */
    public void setDateCreationDu(String dateCreationDu) {
        this.dateCreationDu = dateCreationDu;
    }

    /**
     * Affecte une date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au en caract�re.
     */
    public void setDateCreationAu(String dateCreationAu) {
        this.dateCreationAu = dateCreationAu;
    }
    /**
     * Ajoute un dossier.
     *
     * @param dossier Valeur du dossier � ajouter.
     */
    /*public void addDossier(DossierHtmlForm dossier) {
        this.dossiers.add(dossier);
    }*/

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheDossierForm.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheDossierForm en
     * caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheDossierForm : ");
        stringBuffer.append("entite = '" + getEntite());
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', siteApplicable = '" + siteApplicable);
        stringBuffer.append("', genre = '" + getGenre());
        stringBuffer.append("', nature = '" + getNature());
        stringBuffer.append("', type = '" + getType());
        stringBuffer.append("', categorie = '" + getCategorie());
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', numeroCardex = '" + numeroCardex.toString());
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', numeroFicheSujet = '" + numeroFicheSujet);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', reference3 = '" + reference3);
        stringBuffer.append("', reference5 = '" + reference5);
        stringBuffer.append("', dateDebutDu = '" + dateDebutDu);
        stringBuffer.append("', dateFinDu = '" + dateFinDu);
        stringBuffer.append("', dateDebutAu = '" + dateDebutAu);
        stringBuffer.append("', dateFinAu = '" + dateFinAu);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', origine = '" + origine);
        stringBuffer.append("', referenceVideo = '" + referenceVideo);
        stringBuffer.append("', dateCreationDu = '" + dateCreationDu);
        stringBuffer.append("', dateCreationAu = '" + dateCreationAu);
        stringBuffer.append("', typeJeu = '" + typeJeu);
        stringBuffer.append("', jeu = '" + jeu);
        stringBuffer.append("', classe = '" + classe);

        return stringBuffer.toString();
    }

    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     * @throws BusinessResourceException 
     */
    public void init(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	GererTacheUtilisateur gererTacheUtilisateur = GererTacheUtilisateur.getInstanceOf();
    	CardexUser user = (CardexUser) subject.getUser();
    	
        this.siteOrigine = "";
        this.siteApplicable = "";
        cascadeEGNTC = new HierarchieEGNTC();
        this.statut = "";
        this.service = "";
        this.fonde = "";
        this.intervenant = "";
        this.numeroCardex = new NumeroCardex();
        this.numeroDossier = "";
        this.numeroFicheSujet = "";
        this.reference1 = "";
        this.reference2 = "";
        this.reference3 = "";
        this.reference5 = "";
        this.descriptif = "";
        this.dateDebutDu = "";
        this.dateFinDu = "";
        this.dateDebutAu = "";
        this.dateFinAu = "";
        this.severite = "";
        this.confidentialite = "";
        this.origine = "";
        this.endroit = "";
        this.localisation = "";
        this.referenceVideo = "";
        this.enregistrementNumerique = "";
        this.enregistrementConserve = "";
        this.dateCreationDu = "";
        this.dateCreationAu = "";
        this.choixRapport = "";
        this.typeJeu = "";
        this.jeu = "";
        this.classe = "";
        listeResultat.init();
        listeResultatAudit.init();
        entiteCardexLiaison = null;
        this.rechercherSousCategorie = "";
        this.rechercherTous = "";
        numeroCardex.setCodeSite(String.valueOf(user.getSite()));
        genererNumeroSequence();
    }

    // Apr�s une requ�te il faut g�n�rer un nouveau num�ro de s�quence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }
    
	/**
	 * Returns the endroit.
	 * @return String
	 */
	public String getEndroit() {
		return endroit;
	}

	/**
	 * Returns the localisation.
	 * @return String
	 */
	public String getLocalisation() {
		return localisation;
	}


	/**
	 * Sets the endroit.
	 * @param endroit The endroit to set
	 */
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	/**
	 * Sets the localisation.
	 * @param localisation The localisation to set
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
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
	
	public ListeResultat getListeResultatAudit() {
		return listeResultatAudit;
	}
	
	public void setListeResultatAudit(List list) {
		this.listeResultatAudit.setResultatCompletAudit(list);
	}
	
	/**
	 * @return Returns the descriptif.
	 */
	public String getDescriptif() {
		return descriptif;
	}
	/**
	 * @param descriptif The descriptif to set.
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	/**
	 * @return Returns the typeCategorie.
	 */
	public String getTypeCategorie() {
		return typeCategorie;
	}
	/**
	 * @param typeCategorie The typeCategorie to set.
	 */
	public void setTypeCategorie(String typeCategorie) {
		this.typeCategorie = typeCategorie;
	}
	/**
	 * @return Returns the groupesIntervenants.
	 */
	public String getGroupesIntervenants() {
		return groupesIntervenants;
	}
	/**
	 * @param groupesIntervenants The groupesIntervenants to set.
	 */
	public void setGroupesIntervenants(String groupesIntervenants) {
		this.groupesIntervenants = groupesIntervenants;
	}
	
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardexLiaison) {
		this.entiteCardexLiaison = entiteCardexLiaison;
	}

	/**
	 * @return choixRapport
	 */
	public String getChoixRapport() {
		return choixRapport;
	}

	/**
	 * @param choixRapport choixRapport � d�finir
	 */
	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}

	/**
	 * @return enregistrementNumerique
	 */
	public String getEnregistrementNumerique() {
		return enregistrementNumerique;
	}

	/**
	 * @param enregistrementNumerique enregistrementNumerique � d�finir
	 */
	public void setEnregistrementNumerique(String enregistrementNumerique) {
		this.enregistrementNumerique = enregistrementNumerique;
	}

	/**
	 * @return enregistrementConserve
	 */
	public String getEnregistrementConserve() {
		return enregistrementConserve;
	}

	/**
	 * @param enregistrementConserve enregistrementConserve � d�finir
	 */
	public void setEnregistrementConserve(String enregistrementConserve) {
		this.enregistrementConserve = enregistrementConserve;
	}

	/**
	 * @return service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service service � d�finir
	 */
	public void setService(String service) {
		this.service = service;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.rechercherSousCategorie = "";
		this.rechercherTous = "";
	    this.lienRoleRequis = true;
	}

	/**
	 * @return jeu
	 */
	public String getJeu() {
		return jeu;
	}

	/**
	 * @param jeu jeu � d�finir
	 */
	public void setJeu(String jeu) {
		this.jeu = jeu;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * @return rechercherSousCategorie
	 */
	public String getRechercherSousCategorie() {
		return rechercherSousCategorie;
	}

	/**
	 * @param rechercherSousCategorie rechercherSousCategorie � d�finir
	 */
	public void setRechercherSousCategorie(String rechercherSousCategorie) {
		this.rechercherSousCategorie = rechercherSousCategorie;
	}

	/**
	 * @return rechercherTous
	 */
	public String getRechercherTous() {
		return rechercherTous;
	}

	/**
	 * @param rechercherTous rechercherTous � d�finir
	 */
	public void setRechercherTous(String rechercherTous) {
		this.rechercherTous = rechercherTous;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

    public String getSequenceNumeroCardex() {
        return this.numeroCardex.getSequence();
    }

    public void setSequenceNumeroCardex(String sequence) {
        this.numeroCardex.setSequence(sequence);
    }
	
    public String getSiteNumeroCardex() {
        return this.numeroCardex.getCodeSite();
    }

    public String getDateNumeroCardex() {
        return this.numeroCardex.getDate();
    }

    public void setSiteNumeroCardex(String site) {
        this.numeroCardex.setCodeSite(site);
    }

    public void setdateNumeroCardex(String date) {
        this.numeroCardex.setDate(date);
    }

	/**
	 * @return nombreDossiers
	 */
	public String getNombreDossiers() {
		return nombreDossiers;
	}

	/**
	 * @param nombreDossiers nombreDossiers � d�finir
	 */
	public void setNombreDossiers(String nombreDossiers) {
		this.nombreDossiers = nombreDossiers;
	}

	public boolean isLienRoleRequis() {
		return lienRoleRequis;
	}

	public void setLienRoleRequis(boolean lienRoleRequis) {
		this.lienRoleRequis = lienRoleRequis;
	}

	public String getTypeJeu() {
		return typeJeu;
	}

	public void setTypeJeu(String typeJeu) {
		this.typeJeu = typeJeu;
	}
	
	
}