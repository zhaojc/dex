package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheSocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EntiteCardexLiaison;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchiePPV;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.LienCascade;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulatire de recherche de
 * la société.
 *
 * @author $Author: mdemers $
 * @version $Revision: 1.17 $, $Date: 2002/03/11 17:32:18 $
 * @see
 * com.lotoquebec.cardex.presentation.model.CriteresRechercheSocieteHtmlForm;
 */
public class CriteresRechercheSocieteForm extends ValidatorForm
        implements CriteresRechercheSocieteHtmlForm, RechercheListeResultat, EntiteCardexLiaison {

    private String numeroFiche = "";
    private String classe = "";
    private String raisonEtre = "";
    private String role = "";
    private String nom = "";
    private String nomPhonetique = "";
    private String nomSujet = "";
    private String prenomSujet = "";
    private String entite = "";
    private String siteOrigine = "";
    private String langue = "";
    private String confidentialite = "";
    private LienCascade hierarchiePPV = new HierarchiePPV();
    private String reference1 = "";
    private String reference2 = "";
    private String statut = "";
    private String severite = "";
	private String severiteCasino = "";
    private String lien = "";
    private String lienSite = "";
    private ArrayList societes = new ArrayList();
    private DossierHtmlForm dossier = null;
    private SujetHtmlForm sujet = null;
    private SocieteHtmlForm societe = null;
    private VehiculeHtmlForm vehicule = null;
    private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
    private EntiteCardexForm entiteCardexLiaison = null;
    private int sequence = 0;
    //Ce champ sert au choix de rôle dans le formulaire de liaison
    //lors de l'ajout d'une nouvelle société.
    private String     roleLiaison = "";
    //Détermine si le rôle est requis. Pas nécessaire dans le cas d'une liaison entre 2 sociétés.
    private boolean lienRoleRequis = true;
    
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
     * Constructeur de CriteresRechercheSocieteForm par défaut.
     */
    public CriteresRechercheSocieteForm() {}


    // Getters


    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche() {
        return this.numeroFiche;
    }

    /**
     * Retourne la raison d'être.
     *
     * @return String Valeur de la raison d'être en caractère.
     */
    public String getRaisonEtre() {
        return this.raisonEtre;
    }

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * @return the nomPhonetique
     */
    public String getNomPhonetique()
    {
        return nomPhonetique;
    }

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caractère.
     */
    public String getPays() {
        return hierarchiePPV.get(HierarchiePPV.PAYS);
    }

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince() {
        return hierarchiePPV.get(HierarchiePPV.PROVINCE);
    }

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractère.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caractère.
     */
    public String getVille() {
        return hierarchiePPV.get(HierarchiePPV.VILLE);
    }

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caractère.
     */
    public String getClasse() {
        return this.classe;
    }

    /**
     * Retourne le rôle.
     *
     * @return String Valeur du rôle en caractère.
     */
    public String getRole() {
        return this.role;
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
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caractère.
     */
    public String getLangue() {
        return this.langue;
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
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut() {
        return this.statut;
    }

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite() {
        return this.severite;
    }

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne une collection de societes.
     *
     * @return Collection Valeur de la collection de societes.
     */
    public Collection getSocietes() {
        return this.societes;
    }


    // Setters

    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche) {
        this.numeroFiche = numeroFiche;
    }

    /**
     * Affecte une raison d'être.
     *
     * @param raisonEtre Valeur de la raison d'être en caractère.
     */
    public void setRaisonEtre(String raisonEtre) {
        this.raisonEtre = raisonEtre;
    }

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Affecte un non phonétique
     * 
     * @param nomPhonetique the nomPhonetique to set
     */
    public void setNomPhonetique(String nomPhonetique)
    {
        this.nomPhonetique = nomPhonetique;
    }

    /**
     * Affecte un le pays.
     *
     * @param pays Valeur du pays en caractère.
     */
    public void setPays(String pays) {
        hierarchiePPV.set(HierarchiePPV.PAYS, pays);
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province) {
        hierarchiePPV.set(HierarchiePPV.PROVINCE, province);
    }

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caractère.
     */
    public void setVille(String ville) {
        hierarchiePPV.set(HierarchiePPV.VILLE, ville);
    }

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caractère.
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle en caractère.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite) {
        this.entite = entite;
    }

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(String langue) {
        this.langue = langue;
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
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeverite(String severite) {
        this.severite = severite;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Ajoute une société.
     *
     * @param sujet Valeur de la société à ajouter.
     */
    public void addSociete(SocieteHtmlForm societe) {
        this.societes.add(societe);
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSocieteForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheSocieteForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheSocieteForm : ");
        stringBuffer.append("numeroFiche = '" + numeroFiche);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', raisonEtre = '" + raisonEtre);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', nomPhonetique = '" + nomPhonetique);
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', pays = '" + getPays());
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', province = '" + getProvince());
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', ville = '" + getVille());
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

    public void init(CardexAuthenticationSubject subject) {
    	assignerEntite(subject);
        this.siteOrigine = "";
        this.numeroFiche = "";
        this.classe = "";
        this.raisonEtre = "";
        this.role = "";
        this.nom = "";
        this.nomPhonetique = "";
        this.nomSujet = "";
        this.prenomSujet = "";
        this.langue = "";
        this.hierarchiePPV = new HierarchiePPV();
        this.confidentialite = "";
        this.reference1 = "";
        this.reference2 = "";
        this.statut = "";
        this.severite = "";
        this.severiteCasino = "";
        this.lien = "";
        this.lienSite = "";
        this.societes = new ArrayList();
        this.roleLiaison = "";
        listeResultat.init();
        listeResultatAudit.init();
        entiteCardexLiaison = null;
        genererNumeroSequence();
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
		UIComponentState state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, CriteresRechercheSocieteForm.class, "entite");
		
		if (UIComponentState.ENABLED.equals(state))
			setEntite("");
		else{
			CardexUser user = (CardexUser) subject.getUser();
			setEntite(String.valueOf(user.getEntite()));
		}
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
	
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardexLiaison) {
		this.entiteCardexLiaison = entiteCardexLiaison;
	}

	/**
	 * @return nomSujet
	 */
	public String getNomSujet() {
		return nomSujet;
	}

	/**
	 * @param nomSujet nomSujet à définir
	 */
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}

	/**
	 * @return prenomSujet
	 */
	public String getPrenomSujet() {
		return prenomSujet;
	}

	/**
	 * @param prenomSujet prenomSujet à définir
	 */
	public void setPrenomSujet(String prenomSujet) {
		this.prenomSujet = prenomSujet;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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

	public String getSeveriteCasino() {
		return severiteCasino;
	}

	public void setSeveriteCasino(String severiteCasino) {
		this.severiteCasino = severiteCasino;
	}

	public String getRoleLiaison() {
		return roleLiaison;
	}

	public void setRoleLiaison(String roleLiaison) {
		this.roleLiaison = roleLiaison;
	}

	public String getReference2() {
		return reference2;
	}

	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	
    /**
     * Test si le rôle est requis lors d'une liaison.
     * Le rôle n'est pas requis dans le cas d'un lien entre 2 sociétés
     *
     * @return boolean si le rôle est requis
     */
    public boolean isLienRoleRequis() {
      return this.lienRoleRequis;
    }

    /**
     * Détermine si le rôle est requis lors d'une liaison
     * (non requis entre 2 sociétés).
     *
     * @param isNew Valeur si la societe est nouvellement créer.
     */
    public void setLienRoleRequis(boolean lienRoleRequis){
        this.lienRoleRequis = lienRoleRequis;
    }

    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
	  this.lienRoleRequis = true;
    }
    
}
