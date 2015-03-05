package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheVehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EntiteCardexLiaison;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulatire de recherche du
 * véhicule.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.9 $, $Date: 2002/04/11 18:49:04 $
 * @see
 * com.lotoquebec.cardex.presentation.model.CriteresRechercheVehiculeHtmlForm
 */
public class CriteresRechercheVehiculeForm extends ValidatorForm
        implements CriteresRechercheVehiculeHtmlForm,RechercheListeResultat, EntiteCardexLiaison {

    private String entite = "";
    private String siteOrigine = "";
    private String immatriculation = "";
    private String numeroFiche = "";
    private String province = "";
    private String cleProvince = "";
    private String marque = "";
    private String modele = "";
    private String modeleMarque = "";
    private String confidentialite = "";
    private String particularite1 = "";
    private String particularite2 = "";
    private String particularite3 = "";
    private String particularite4 = "";
    private String particularite5 = "";
    private ArrayList vehicules = new ArrayList();
    private DossierHtmlForm dossier = null;
    private SujetHtmlForm sujet = null;
    private SocieteHtmlForm societe = null;
    private VehiculeHtmlForm vehicule = null;
    private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
    private EntiteCardexForm entiteCardexLiaison = null;
    private int sequence = 0;
    //Ces champs servent au choix de rôle dans le formulaire de liaison
    //lors de l'ajout d'un nouveau véhicule.
    private String     roleLiaison = "";
    private String     lienSite = "";

    /**
     * Retourne le dossier asssocie .
     *
     * @return DossierForm Valeur de la cle en caractère.
     */
    public DossierHtmlForm getDossier() {
        return this.dossier;
    }

    /**
     * Retourne le sujet asssocie.
     *
     * @return SujetForm Valeur de la cle en caractère.
     */
    public SujetHtmlForm getSujet() {
        return this.sujet;
    }

    /**
     * Retourne le societe asssocie  .
     *
     * @return SocieteForm Valeur de la cle en caractère.
     */
    public SocieteHtmlForm getSociete() {
        return this.societe;
    }

    /**
     * Retourne le societe asssocie .
     *
     * @return SocieteForm Valeur de la cle en caractère.
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
     * Constructeur de CriteresRechercheVehiculeForm par défaut.
     */
    public CriteresRechercheVehiculeForm() {}


    // Getters


    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * @return the numeroFiche
     */
    public String getNumeroFiche()
    {
        return numeroFiche;
    }

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque() {
        return this.marque;
    }

    /**
     * Retourne le modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public String getModele() {
        return this.modele;
    }

    /**
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite() {
        return this.entite;
    }

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne la première particularité.
     *
     * @return String Valeur de la première particularité en caractère.
     */
    public String getParticularite1() {
        return this.particularite1;
    }

    /**
     * Retourne la deuxième particularité.
     *
     * @return String Valeur de la deuxième particularité en caractère.
     */
    public String getParticularite2() {
        return this.particularite2;
    }

    /**
     * Retourne la troisième particularité.
     *
     * @return String Valeur de la troisième particularité en caractère.
     */
    public String getParticularite3() {
        return this.particularite3;
    }

    /**
     * Retourne la quatrième particularité.
     *
     * @return String Valeur de la quatrième particularité en caractère.
     */
    public String getParticularite4() {
        return this.particularite4;
    }

    /**
     * Retourne la cinquième particularité.
     *
     * @return String Valeur de la cinquième particularité en caractère.
     */
    public String getParticularite5() {
        return this.particularite5;
    }

    /**
     * Retourne une collection de véhicules.
     *
     * @return Collection Valeur de la collection de véhicules.
     */
    public Collection getVehicules() {
        return this.vehicules;
    }


    // Setters


    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite) {
        this.entite = entite;
    }

    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * @param numeroFiche the numeroFiche to set
     */
    public void setNumeroFiche(String numeroFiche)
    {
        this.numeroFiche = numeroFiche;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte une première particularité.
     *
     * @param particularite1 Valeur de la première particularité en caractère.
     */
    public void setParticularite1(String particularite1) {
        this.particularite1 = particularite1;
    }

    /**
     * Affecte une deuxième particularité.
     *
     * @param particularite2 Valeur de la deuxième particularité en caractère.
     */
    public void setParticularite2(String particularite2) {
        this.particularite2 = particularite2;
    }

    /**
     * Affecte une troisième particularité.
     *
     * @param particularite3 Valeur de la troisième particularité en caractère.
     */
    public void setParticularite3(String particularite3) {
        this.particularite3 = particularite3;
    }

    /**
     * Affecte une quatrième particularité.
     *
     * @param particularite4 Valeur de la quatrième particularité en caractère.
     */
    public void setParticularite4(String particularite4) {
        this.particularite4 = particularite4;
    }

    /**
     * Affecte une cinquième particularité.
     *
     * @param particularite5 Valeur de la cinquième particularité en caractère.
     */
    public void setParticularite5(String particularite5) {
        this.particularite5 = particularite5;
    }

    /**
     * Ajoute un véhicule.
     *
     * @param sujet Valeur du véhicule à ajouter.
     */
    public void addVehicule(VehiculeHtmlForm vehicule) {
        this.vehicules.add(vehicule);
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheVehiculeForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheVehiculeForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheVehiculeForm : ");
        stringBuffer.append("', immatriculation = '" + immatriculation);
        stringBuffer.append("', numeroFiche = '" + numeroFiche);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', cleProvince = '" + cleProvince);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', particularite1 = '" + particularite1);
        stringBuffer.append("', particularite2 = '" + particularite2);
        stringBuffer.append("', particularite3 = '" + particularite3);
        stringBuffer.append("', particularite4 = '" + particularite4);
        stringBuffer.append("', particularite5 = '" + particularite5);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
    /**
     * Réinitialise tous les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(CardexAuthenticationSubject subject) {
    	assignerEntite(subject);
        this.siteOrigine = "";
        this.immatriculation = "";
        this.numeroFiche = "";
        this.province = "";
        this.cleProvince = "";
        this.marque = "";
        this.modele = "";
        this.confidentialite = "";
        this.particularite1 = "";
        this.particularite2 = "";
        this.particularite3 = "";
        this.particularite4 = "";
        this.particularite5 = "";
        this.vehicules = new ArrayList();
        this.dossier = null;
        this.sujet = null;
        this.societe = null;
        this.vehicule = null;
        listeResultat.init();
        listeResultatAudit.init();
        entiteCardexLiaison = null;
        genererNumeroSequence();
        this.lienSite = "";
        this.roleLiaison = "";
    }

    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }
    
	/*
	 * L'entité est assigner à blanc si:
	 * - Si l'usager à droit de voir l'entité (mandat 4.2)
	 */
	private void assignerEntite(CardexAuthenticationSubject subject) {
		UIComponentState state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, this.getClass(), "entite");
		
		if (UIComponentState.ENABLED.equals(state))
			setEntite("");
		else{
			CardexUser user = (CardexUser) subject.getUser();
			setEntite(String.valueOf(user.getEntite()));
		}
	}    
    
	/**
	 * Returns the modeleMarque.
	 * @return String
	 */
	public String getModeleMarque() {
		return modeleMarque;
	}

	/**
	 * Sets the modeleMarque.
	 * @param modeleMarque The modeleMarque to set
	 */
	public void setModeleMarque(String modeleMarque) {
		this.modeleMarque = modeleMarque;
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
	
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardexLiaison) {
		this.entiteCardexLiaison = entiteCardexLiaison;
	}

	public ListeResultat getListeResultatAudit() {
		return listeResultatAudit;
	}
	
	public void setListeResultatAudit(List list) {
		this.listeResultatAudit.setResultatCompletAudit(list);
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String getCleProvince() {
		return cleProvince;
	}


	public void setCleProvince(String cleProvince) {
		this.cleProvince = cleProvince;
	}

	/**
	 * @return siteOrigine
	 */
	public String getSiteOrigine() {
		return siteOrigine;
	}

	/**
	 * @param siteOrigine siteOrigine à définir
	 */
	public void setSiteOrigine(String siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	public String getRoleLiaison() {
		return roleLiaison;
	}

	public void setRoleLiaison(String roleLiaison) {
		this.roleLiaison = roleLiaison;
	}

	public void setVehicules(ArrayList vehicules) {
		this.vehicules = vehicules;
	}

	public String getLienSite() {
		return lienSite;
	}

	public void setLienSite(String lienSite) {
		this.lienSite = lienSite;
	}
	
}