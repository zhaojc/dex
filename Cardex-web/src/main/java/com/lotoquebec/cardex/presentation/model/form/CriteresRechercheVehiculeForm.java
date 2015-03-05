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
 * Conserve les diff�rentes valeurs relatives au formulatire de recherche du
 * v�hicule.
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
    //Ces champs servent au choix de r�le dans le formulaire de liaison
    //lors de l'ajout d'un nouveau v�hicule.
    private String     roleLiaison = "";
    private String     lienSite = "";

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
     * Constructeur de CriteresRechercheVehiculeForm par d�faut.
     */
    public CriteresRechercheVehiculeForm() {}


    // Getters


    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
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
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque() {
        return this.marque;
    }

    /**
     * Retourne le mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public String getModele() {
        return this.modele;
    }

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite() {
        return this.entite;
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
     * Retourne la premi�re particularit�.
     *
     * @return String Valeur de la premi�re particularit� en caract�re.
     */
    public String getParticularite1() {
        return this.particularite1;
    }

    /**
     * Retourne la deuxi�me particularit�.
     *
     * @return String Valeur de la deuxi�me particularit� en caract�re.
     */
    public String getParticularite2() {
        return this.particularite2;
    }

    /**
     * Retourne la troisi�me particularit�.
     *
     * @return String Valeur de la troisi�me particularit� en caract�re.
     */
    public String getParticularite3() {
        return this.particularite3;
    }

    /**
     * Retourne la quatri�me particularit�.
     *
     * @return String Valeur de la quatri�me particularit� en caract�re.
     */
    public String getParticularite4() {
        return this.particularite4;
    }

    /**
     * Retourne la cinqui�me particularit�.
     *
     * @return String Valeur de la cinqui�me particularit� en caract�re.
     */
    public String getParticularite5() {
        return this.particularite5;
    }

    /**
     * Retourne une collection de v�hicules.
     *
     * @return Collection Valeur de la collection de v�hicules.
     */
    public Collection getVehicules() {
        return this.vehicules;
    }


    // Setters


    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite) {
        this.entite = entite;
    }

    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
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
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModele(String modele) {
        this.modele = modele;
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
     * Affecte une premi�re particularit�.
     *
     * @param particularite1 Valeur de la premi�re particularit� en caract�re.
     */
    public void setParticularite1(String particularite1) {
        this.particularite1 = particularite1;
    }

    /**
     * Affecte une deuxi�me particularit�.
     *
     * @param particularite2 Valeur de la deuxi�me particularit� en caract�re.
     */
    public void setParticularite2(String particularite2) {
        this.particularite2 = particularite2;
    }

    /**
     * Affecte une troisi�me particularit�.
     *
     * @param particularite3 Valeur de la troisi�me particularit� en caract�re.
     */
    public void setParticularite3(String particularite3) {
        this.particularite3 = particularite3;
    }

    /**
     * Affecte une quatri�me particularit�.
     *
     * @param particularite4 Valeur de la quatri�me particularit� en caract�re.
     */
    public void setParticularite4(String particularite4) {
        this.particularite4 = particularite4;
    }

    /**
     * Affecte une cinqui�me particularit�.
     *
     * @param particularite5 Valeur de la cinqui�me particularit� en caract�re.
     */
    public void setParticularite5(String particularite5) {
        this.particularite5 = particularite5;
    }

    /**
     * Ajoute un v�hicule.
     *
     * @param sujet Valeur du v�hicule � ajouter.
     */
    public void addVehicule(VehiculeHtmlForm vehicule) {
        this.vehicules.add(vehicule);
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheVehiculeForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheVehiculeForm en caract�re.
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
     * R�initialise tous les attributs � leur valeur par d�faut.
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

    // Apr�s une requ�te il faut g�n�rer un nouveau num�ro de s�quence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }
    
	/*
	 * L'entit� est assigner � blanc si:
	 * - Si l'usager � droit de voir l'entit� (mandat 4.2)
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
	 * @param siteOrigine siteOrigine � d�finir
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