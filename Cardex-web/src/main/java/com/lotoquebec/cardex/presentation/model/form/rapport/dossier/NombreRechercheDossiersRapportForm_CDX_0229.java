package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.NombreRechercheDossiersGenerateurRapport_CDX_0229;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

public class NombreRechercheDossiersRapportForm_CDX_0229 extends CriteresRapportForm implements RapportForm{

    private String entite = "";
    private String siteOrigine = "";
    private String siteApplicable = "";
    private String genre = "";
    private String nature = "";
    private String type = "";
    private String typeCategorie = "";
    private String categorie = "";
    private String statut = "";
    private String service = "";
    private String fonde = "";
    private String typeJeu = "";
    private String jeu = "";
    private String endroit = "";
    private String localisation = "";
    private String intervenant = "";
	private String groupesIntervenants = "";
	private String numeroCardex = "";
	private String numeroDossier = "";
	private String numeroFicheSujet = "";
	private String descriptif = "";
	private String reference1 = "";
	private String reference2 = "";
	private String reference3 = "";
	private String reference5 = "";
	private String dateFinDu = "";
	private String dateFinAu = "";
	private String severite = "";
	private String confidentialite = "";
	private String origine = "";
	private String choixRapport = "";
	private String referenceVideo = "";
	private String enregistrementNumerique = "";
	private String enregistrementConserve = "";
	private String dateCreationDu = "";
	private String dateCreationAu = "";
	private String periode = "";
	private String rechercherSousCategorie = "";
	private String rechercherTous = "";
	private String sequence = "";
	private String siteNumeroCardex = "";
	private String dateNumeroCardex = "";
	private String sequenceNumeroCardex = "";
    private String classe = "";
	
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(String.valueOf(user.getEntite()));
        setSiteOrigine(String.valueOf(user.getSite()));
	}
	
	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public String getSiteOrigine() {
		return siteOrigine;
	}

	public void setSiteOrigine(String siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	public String getSiteApplicable() {
		return siteApplicable;
	}

	public void setSiteApplicable(String siteApplicable) {
		this.siteApplicable = siteApplicable;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeCategorie() {
		return typeCategorie;
	}

	public void setTypeCategorie(String typeCategorie) {
		this.typeCategorie = typeCategorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getFonde() {
		return fonde;
	}

	public void setFonde(String fonde) {
		this.fonde = fonde;
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
		this.jeu = jeu;
	}

	public String getEndroit() {
		return endroit;
	}

	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getGroupesIntervenants() {
		return groupesIntervenants;
	}

	public void setGroupesIntervenants(String groupesIntervenants) {
		this.groupesIntervenants = groupesIntervenants;
	}

	public String getNumeroCardex() {
		return numeroCardex;
	}

	public void setNumeroCardex(String numeroCardex) {
		this.numeroCardex = numeroCardex;
	}

	public String getNumeroDossier() {
		return numeroDossier;
	}

	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	public String getNumeroFicheSujet() {
		return numeroFicheSujet;
	}

	public void setNumeroFicheSujet(String numeroFicheSujet) {
		this.numeroFicheSujet = numeroFicheSujet;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getReference1() {
		return reference1;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	public String getReference2() {
		return reference2;
	}

	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	public String getReference3() {
		return reference3;
	}

	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}

	public String getReference5() {
		return reference5;
	}

	public void setReference5(String reference5) {
		this.reference5 = reference5;
	}

	public String getDateFinDu() {
		return dateFinDu;
	}

	public void setDateFinDu(String dateFinDu) {
		this.dateFinDu = dateFinDu;
	}

	public String getDateFinAu() {
		return dateFinAu;
	}

	public void setDateFinAu(String dateFinAu) {
		this.dateFinAu = dateFinAu;
	}

	public String getSeverite() {
		return severite;
	}

	public void setSeverite(String severite) {
		this.severite = severite;
	}

	public String getConfidentialite() {
		return confidentialite;
	}

	public void setConfidentialite(String confidentialite) {
		this.confidentialite = confidentialite;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getChoixRapport() {
		return choixRapport;
	}

	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}

	public String getReferenceVideo() {
		return referenceVideo;
	}

	public void setReferenceVideo(String referenceVideo) {
		this.referenceVideo = referenceVideo;
	}

	public String getEnregistrementNumerique() {
		return enregistrementNumerique;
	}

	public void setEnregistrementNumerique(String enregistrementNumerique) {
		this.enregistrementNumerique = enregistrementNumerique;
	}

	public String getEnregistrementConserve() {
		return enregistrementConserve;
	}

	public void setEnregistrementConserve(String enregistrementConserve) {
		this.enregistrementConserve = enregistrementConserve;
	}

	public String getDateCreationDu() {
		return dateCreationDu;
	}

	public void setDateCreationDu(String dateCreationDu) {
		this.dateCreationDu = dateCreationDu;
	}

	public String getDateCreationAu() {
		return dateCreationAu;
	}

	public void setDateCreationAu(String dateCreationAu) {
		this.dateCreationAu = dateCreationAu;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getRechercherSousCategorie() {
		return rechercherSousCategorie;
	}

	public void setRechercherSousCategorie(String rechercherSousCategorie) {
		this.rechercherSousCategorie = rechercherSousCategorie;
	}

	public String getRechercherTous() {
		return rechercherTous;
	}

	public void setRechercherTous(String rechercherTous) {
		this.rechercherTous = rechercherTous;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSiteNumeroCardex() {
		return siteNumeroCardex;
	}

	public void setSiteNumeroCardex(String siteNumeroCardex) {
		this.siteNumeroCardex = siteNumeroCardex;
	}

	public String getDateNumeroCardex() {
		return dateNumeroCardex;
	}

	public void setDateNumeroCardex(String dateNumeroCardex) {
		this.dateNumeroCardex = dateNumeroCardex;
	}

	public String getSequenceNumeroCardex() {
		return sequenceNumeroCardex;
	}

	public void setSequenceNumeroCardex(String sequenceNumeroCardex) {
		this.sequenceNumeroCardex = sequenceNumeroCardex;
	}

	public GenererRapport getGenererRapport() {
		return new NombreRechercheDossiersGenerateurRapport_CDX_0229();
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getTypeJeu() {
		return typeJeu;
	}

	public void setTypeJeu(String typeJeu) {
		this.typeJeu = typeJeu;
	}
	
	
	
}
