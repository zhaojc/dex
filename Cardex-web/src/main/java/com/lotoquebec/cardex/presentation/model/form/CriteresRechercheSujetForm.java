package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheSujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EntiteCardexLiaison;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulaire de recherche du
 * sujet.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.11 $, $Date: 2002/03/06 20:50:33 $
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheSujetHtmlForm
 */
public class CriteresRechercheSujetForm
	extends ValidatorForm
	implements CriteresRechercheSujetHtmlForm,RechercheListeResultat, EntiteCardexLiaison {

	private String age = "";
	private String dateNaissance = "";
	private String numeroFiche = "";
	private String nomPhonetique = "";
	private String prenomPhonetique = "";
	private String alias = "";
	private String pays = "";
	private String province = "";
	private String ville = "";
	private String nom = "";
	private String sexe = "";
	private String entite = "";
    private String siteOrigine = "";
	private String ethnie = "";
	private String role = "";
	private String langue = "";
	private String race = "";
	private String statut = "";
	private String severite = "";
	private String severiteCasino = "";
	private String severiteAutres = "";
	private String confidentialite = "";
	private String caracteristique1 = "";
	private String caracteristique2 = "";
	private String caracteristique3 = "";
	private String caracteristique4 = "";
	private String caracteristique5 = "";
	private String or1 = "";
	private String or2 = "";
	private String or3 = "";
	private String passeport = "";
	private String numeroAssuranceMaladie = "";
	private String numeroPermisConduire = "";
	private String numeroClient = "";
	private String reference = "";
	private boolean ageEstime = true;
	private boolean ageReel = true;
	private boolean ageReelPlusMoins = true;
	private boolean ageInconnu = false;
	private ArrayList sujets = new ArrayList();
	private DossierHtmlForm dossier = null;
	private SujetHtmlForm sujet = null;
	private SocieteHtmlForm societe = null;
	private VehiculeHtmlForm vehicule = null;
	private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
	private EntiteCardexForm entiteCardexLiaison = null;
	private int sequence = 0;
    //Ces champs servent au choix de rôle dans le formulaire de liaison
    //lors de l'ajout d'un nouveau sujet.
    private String     roleLiaison = "";
    private String     lienSite = "";
    private String     typeLien = "";
	
	

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
	 * Constructeur de CriteresRechercheSujetForm par défaut.
	 */
	public CriteresRechercheSujetForm() {
	}

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
	 * Retourne le nom phonétique.
	 *
	 * @return String Valeur du nom phonétique en caractère.
	 */
	public String getNomPhonetique() {
		return this.nomPhonetique;
	}

	/**
	 * Retourne le prenom phonétique.
	 *
	 * @return String Valeur du prenom phonétique en caractère.
	 */
	public String getPrenomPhonetique() {
		return this.prenomPhonetique;
	}

	/**
	 * Retourne l'alias.
	 *
	 * @return String Valeur de l'alias en caractère.
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * Retourne le pays.
	 *
	 * @return String Valeur du pays en caractère.
	 */
	public String getPays() {
		return this.pays;
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
	 * Retourne la ville.
	 *
	 * @return String Valeur de la ville en caractère.
	 */
	public String getVille() {
		return this.ville;
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
	 * Retourne le sexe.
	 *
	 * @return String Valeur du sexe en caractère.
	 */
	public String getSexe() {
		return this.sexe;
	}

	/**
	 * Retourne l'ethnie.
	 *
	 * @return String Valeur de l'ethnie en caractère.
	 */
	public String getEthnie() {
		return this.ethnie;
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
	 * Retourne le rôle.
	 *
	 * @return String Valeur du rôle en caractère.
	 */
	public String getRole() {
		return this.role;
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
	 * Retourne la race.
	 *
	 * @return String Valeur de la race en caractère.
	 */
	public String getRace() {
		return this.race;
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
	 * Retourne la confidentialité.
	 *
	 * @return String Valeur de la confidentialité en caractère.
	 */
	public String getConfidentialite() {
		return this.confidentialite;
	}

	/**
	 * Retourne la première caractéristique.
	 *
	 * @return String Valeur de la première caractéristique en caractère.
	 */
	public String getCaracteristique1() {
		return this.caracteristique1;
	}

	/**
	 * Retourne la deuxième caractéristique.
	 *
	 * @return String Valeur de la deuxième caractéristique en caractère.
	 */
	public String getCaracteristique2() {
		return this.caracteristique2;
	}

	/**
	 * Retourne la troisième caractéristique.
	 *
	 * @return String Valeur de la troisième caractéristique en caractère.
	 */
	public String getCaracteristique3() {
		return this.caracteristique3;
	}

	/**
	 * Retourne la quatrième caractéristique.
	 *
	 * @return String Valeur de la quatrième caractéristique en caractère.
	 */
	public String getCaracteristique4() {
		return this.caracteristique4;
	}

	/**
	 * Retourne la cinquième caractéristique.
	 *
	 * @return String Valeur de la cinquième caractéristique en caractère.
	 */
	public String getCaracteristique5() {
		return this.caracteristique5;
	}

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caractère.
	 */
	public String getPasseport() {
		return this.passeport;
	}

	/**
	 * Retourne une collection de sujets.
	 *
	 * @return Collection Valeur de la collection de sujets.
	 */
	public Collection getSujets() {
		return this.sujets;
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
	 * Affecte un nom phonétique.
	 *
	 * @param nomPhonetique Valeur du nom phonétique en caractère.
	 */
	public void setNomPhonetique(String nomPhonetique) {
		this.nomPhonetique = nomPhonetique;
	}

	/**
	 * Affecte un prenom phonétique.
	 *
	 * @param prenomPhonetique Valeur du prenom phonétique en caractère.
	 */
	public void setPrenomPhonetique(String prenomPhonetique) {
		this.prenomPhonetique = prenomPhonetique;
	}

	/**
	 * Affecte un alias.
	 *
	 * @param alias Valeur de l'alias en caractère.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Affecte un pays.
	 *
	 * @param pays Valeur du pays en caractère.
	 */
	public void setPays(String pays) {
		this.pays = pays;
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
	 * Affecte une ville.
	 *
	 * @param ville Valeur de la ville en caractère.
	 */
	public void setVille(String ville) {
		this.ville = ville;
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
	 * Affecte un sexe.
	 *
	 * @param sexe Valeur du sexe en caractère.
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * Affecte un ethnie.
	 *
	 * @param ethnie Valeur de l'ethnie en caractère.
	 */
	public void setEthnie(String ethnie) {
		this.ethnie = ethnie;
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
	 * Affecte un rôle.
	 *
	 * @param role Valeur du rôle en caractère.
	 */
	public void setRole(String role) {
		this.role = role;
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
	 * Affecte une race.
	 *
	 * @param race Valeur de la race en caractère.
	 */
	public void setRace(String race) {
		this.race = race;
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
	 * Affecte une confidentialité.
	 *
	 * @param confidentialite Valeur de la confidentialité en caractère.
	 */
	public void setConfidentialite(String confidentialite) {
		this.confidentialite = confidentialite;
	}

	/**
	 * Affecte une première caractéristique.
	 *
	 * @param caracteristique1 Valeur de la première caractéristique en caractère.
	 */
	public void setCaracteristique1(String caracteristique1) {
		this.caracteristique1 = caracteristique1;
	}

	/**
	 * Affecte une deuxième caractéristique.
	 *
	 * @param caracteristique2 Valeur de la deuxième caractéristique en caractère.
	 */
	public void setCaracteristique2(String caracteristique2) {
		this.caracteristique2 = caracteristique2;
	}

	/**
	 * Affecte une troisième caractéristique.
	 *
	 * @param caracteristique3 Valeur de la troisième caractéristique en caractère.
	 */
	public void setCaracteristique3(String caracteristique3) {
		this.caracteristique3 = caracteristique3;
	}

	/**
	 * Affecte une quatrième caractéristique.
	 *
	 * @param caracteristique4 Valeur de la quatrième caractéristique en caractère.
	 */
	public void setCaracteristique4(String caracteristique4) {
		this.caracteristique4 = caracteristique4;
	}

	/**
	 * Affecte une cinquième caractéristique.
	 *
	 * @param caracteristique5 Valeur de la cinquième caractéristique en caractère.
	 */
	public void setCaracteristique5(String caracteristique5) {
		this.caracteristique5 = caracteristique5;
	}

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caractère.
	 */
	public void setPasseport(String passeport) {
		this.passeport = passeport;
	}


	/**
	 * Ajoute un sujet.
	 *
	 * @param sujet Valeur du sujet à ajouter.
	 */
	public void addSujet(SujetHtmlForm sujet) {
		this.sujets.add(sujet);
	}


	/**
	 * Retourne une chaîne de caractère reflétant la valeur de tout les
	 * attributs du CriteresRechercheSujetForm.
	 *
	 * @return String Valeur de tout les attributs du CriteresRechercheSujetForm en caractère.
	 */
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[CriteresRechercheSujetForm : ");
		stringBuffer.append("numeroFiche = '" + numeroFiche);
		stringBuffer.append("', nomPhonetique = '" + nomPhonetique);
		stringBuffer.append("', prenomPhonetique = '" + prenomPhonetique);
		stringBuffer.append("', alias = '" + alias);
		stringBuffer.append("', pays = '" + pays);
		stringBuffer.append("', province = '" + province);
		stringBuffer.append("', ville = '" + ville);
		stringBuffer.append("', nom = '" + nom);
		stringBuffer.append("', sexe = '" + sexe);
		stringBuffer.append("', ethnie = '" + ethnie);
		stringBuffer.append("', role = '" + role);
		stringBuffer.append("', langue = '" + langue);
		stringBuffer.append("', race = '" + race);
		stringBuffer.append("', statut = '" + statut);
		stringBuffer.append("', ageEstime = '" + ageEstime);
        stringBuffer.append("', ageReel = '" + ageReel);
        stringBuffer.append("', ageReelPlusMoins = '" + ageReelPlusMoins);
        stringBuffer.append("', ageInconnu = '" + ageInconnu);
		stringBuffer.append("', confidentialite = '" + confidentialite);
		stringBuffer.append("', caracteristique1 = '" + caracteristique1);
		stringBuffer.append("', caracteristique2 = '" + caracteristique2);
		stringBuffer.append("', caracteristique3 = '" + caracteristique3);
		stringBuffer.append("', caracteristique4 = '" + caracteristique4);
		stringBuffer.append("', caracteristique5 = '" + caracteristique5);
		stringBuffer.append("', passeport = '" + passeport);
		stringBuffer.append("', assurance maladie = '" + numeroAssuranceMaladie);
		stringBuffer.append("']");
		return stringBuffer.toString();
	}

	/**
	 * Gets the age
	 * @return Returns a String
	 */
	public String getAge() {
		return age;
	}
	/**
	 * Sets the age
	 * @param age The age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Returns the numeroClient.
	 * @return String
	 */
	public String getNumeroClient() {
		return numeroClient;
	}

	/**
	 * Returns the reference.
	 * @return String
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the numeroClient.
	 * @param numeroClient The numeroClient to set
	 */
	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	/**
	 * Sets the reference.
	 * @param reference The reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Returns the dateNaissance.
	 * @return String
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Sets the dateNaissance.
	 * @param dateNaissance The dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public void init(CardexAuthenticationSubject subject){
		assignerEntite(subject);
        this.siteOrigine = "";
		this.numeroFiche = "";
		this.nomPhonetique = "";
		this.prenomPhonetique = "";
		this.alias = "";
		this.age = "";
		this.dateNaissance = "";
		this.pays = "";
		this.province = "";
		this.ville = "";
		this.nom = "";
		this.sexe = "";
		this.ethnie = "";
		this.role = "";
		this.langue = "";
		this.race = "";
		this.statut = "";
		this.severite = "";
        this.severiteCasino = "";
		this.severiteAutres = "";
		this.confidentialite = "";
		this.caracteristique1 = "";
		this.caracteristique2 = "";
		this.caracteristique3 = "";
		this.caracteristique4 = "";
		this.caracteristique5 = "";
		this.or1 = GlobalConstants.Operateur.ET;
		this.or2 = GlobalConstants.Operateur.ET;
		this.or3 = GlobalConstants.Operateur.ET;
		this.passeport = "";
		this.numeroAssuranceMaladie = "";
		this.numeroPermisConduire = "";
		this.numeroClient = "";
		this.reference = "";
		this.sujets = new ArrayList();
		this.dossier = null;
		this.sujet = null;
		this.societe = null;
		this.vehicule = null;        
        listeResultat.init();
        listeResultatAudit.init();
        this.ageEstime = true;
        this.ageReel = true;
        this.ageReelPlusMoins = true;
        this.ageInconnu = false;
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
	 * - Le groupe de l'usager est investigation ou
	 * - Si l'usager à droit de voir l'entité (mandat 4.2)
	 */
	private void assignerEntite(CardexAuthenticationSubject subject) {
		UIComponentState state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, this.getClass(), "entite");
		CardexUser user = (CardexUser) subject.getUser();
		
		if (GlobalConstants.GroupeSecurite.INVESTIGATION_PAG == user.getSecteur()  
				|| UIComponentState.ENABLED.equals(state))
					setEntite("");
				else
					setEntite(String.valueOf(user.getEntite()));
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.ageEstime = false;
		this.ageReel = false;
		this.ageReelPlusMoins = false;
		this.ageInconnu = false;
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
	 * @return Returns the or1.
	 */
	public String isOr1() {
		return this.or1;
	}
	/**
	 * @param or1 The or1 to set.
	 */
	public void setOr1(String or1) {
		this.or1 = or1;
	}
	/**
	 * @return Returns the or2.
	 */
	public String isOr2() {
		return this.or2;
	}
	/**
	 * @param or2 The or2 to set.
	 */
	public void setOr2(String or2) {
		this.or2 = or2;
	}
	/**
	 * @return Returns the or3.
	 */
	public String isOr3() {
		return this.or3;
	}
	/**
	 * @param or3 The or3 to set.
	 */
	public void setOr3(String or3) {
		this.or3 = or3;
	}
	/**
	 * @return Returns the or1.
	 */
	public String getOr1() {
		return or1;
	}
	/**
	 * @return Returns the or2.
	 */
	public String getOr2() {
		return or2;
	}
	/**
	 * @return Returns the or3.
	 */
	public String getOr3() {
		return or3;
	}
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardex) {
		this.entiteCardexLiaison = entiteCardex;
	}

	/**
	 * @return assuranceMaladie
	 */
	public String getNumeroAssuranceMaladie() {
		return numeroAssuranceMaladie;
	}

	/**
	 * @param assuranceMaladie assuranceMaladie à définir
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie) {
		this.numeroAssuranceMaladie = numeroAssuranceMaladie;
	}

	/**
	 * @return severiteAutres
	 */
	public String getSeveriteAutres() {
		return severiteAutres;
	}

	/**
	 * @param severiteAutres severiteAutres à définir
	 */
	public void setSeveriteAutres(String severiteAutres) {
		this.severiteAutres = severiteAutres;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return numeroPermisConduire
	 */
	public String getNumeroPermisConduire() {
		return numeroPermisConduire;
	}

	/**
	 * @param numeroPermisConduire numeroPermisConduire à définir
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.numeroPermisConduire = numeroPermisConduire;
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

	public String getLienSite() {
		return lienSite;
	}

	public void setLienSite(String lienSite) {
		this.lienSite = lienSite;
	}

	public String getTypeLien() {
		return typeLien;
	}

	public void setTypeLien(String typeLien) {
		this.typeLien = typeLien;
	}

    /**
     * @return the ageEstime
     */
    public boolean isAgeEstime()
    {
        return ageEstime;
    }


    public void setAgeEstime(boolean ageEstime){
        this.ageEstime = ageEstime;
    }

    /**
     * @return the ageReel
     */
    public boolean isAgeReel()
    {
        return ageReel;
    }

    /**
     * @param ageReel the ageReel to set
     */
    public void setAgeReel(boolean ageReel)
    {
        this.ageReel = ageReel;
    }

    /**
     * @return the ageReelPlusMoins
     */
    public boolean isAgeReelPlusMoins()
    {
        return ageReelPlusMoins;
    }

    /**
     * @param ageReelPlusMoins the ageReelPlusMoins to set
     */
    public void setAgeReelPlusMoins(boolean ageReelPlusMoins)
    {
        this.ageReelPlusMoins = ageReelPlusMoins;
    }

    /**
     * @return the ageInconnu
     */
    public boolean isAgeInconnu()
    {
        return ageInconnu;
    }
    
    /**
     * @param ageInconnu the ageInconnu to set
     */
    public void setAgeInconnu(boolean ageInconnu)
    {
        this.ageInconnu = ageInconnu;
    }
}