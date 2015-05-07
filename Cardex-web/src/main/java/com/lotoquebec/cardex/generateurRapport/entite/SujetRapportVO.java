package com.lotoquebec.cardex.generateurRapport.entite;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EthnieCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LangueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RaceCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SexeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeAgeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SujetRapportVO extends RapportVO<Sujet> {

	private String severiteDescriptionAutres;
	private String sexeDescription;
	private String langueDescription;
	private String statutDescription;
	private String typeAgeDescription;
	private String raceDescription;
	private String severiteDescription;
	private String severiteCasinoDescription;
	private String confidentialiteDescription;
	private String ethnieDescription;
	private String dateNaissance;
	
	public SujetRapportVO(Sujet sujet) {
		super();
		this.entite = sujet;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	ethnieDescription = cache.obtenirLabel(subject, entite.getEthnie(), new EthnieCleListeCache(subject));
    	langueDescription = cache.obtenirLabel(subject, entite.getLangue(), new LangueCleListeCache(subject));
    	raceDescription = cache.obtenirLabel(subject, entite.getRace(), new RaceCleListeCache(subject));
    	severiteDescription = cache.obtenirLabel(subject, entite.getSeverite(), new SeveriteCleListeCache(subject));
    	severiteDescriptionAutres = cache.obtenirLabel(subject, entite.getSeveriteAutres(), new SeveriteCleListeCache(subject));
    	severiteCasinoDescription = cache.obtenirLabel(subject, entite.getSeveriteCasino(), new SeveriteCleListeCache(subject));
    	confidentialiteDescription = cache.obtenirLabel(subject, entite.getConfidentialite(), new ConfidentialiteCleListeCache(subject));
    	sexeDescription = cache.obtenirLabel(subject, entite.getSexe(), new SexeCleListeCache(subject));
		typeAgeDescription = cache.obtenirLabel(subject, entite.getTypeAge(), new TypeAgeCleListeCache(subject));
		statutDescription = cache.obtenirLabel(subject, entite.getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.SUJET));
		dateNaissance = DateFormat.format(entite.getDateNaissance());
	}

	public long getCle(){
		return entite.getCle();
	}
	
	// get
	public String getSeveriteDescriptionAutres() {
		return severiteDescriptionAutres;
	}

	public String getSexeDescription() {
		return sexeDescription;
	}

	public String getLangueDescription() {
		return langueDescription;
	}

	public String getNom() {
		return entite.getNom();
	}

	public String getPrenom() {
		return entite.getPrenom();
	}

	public String getAlias() {
		return entite.getAlias();
	}

	public String getStatutDescription() {
		return statutDescription;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public String getNumeroAssuranceSociale() {
		return entite.getNumeroAssuranceSociale();
	}

	public String getNumeroPermisConduire() {
		return entite.getNumeroPermisConduire();
	}

	public String getReference1() {
		return entite.getReference1();
	}

	public String getReference2() {
		return entite.getReference2();
	}

	public String getNumeroFiche() {
		return entite.getNumeroFiche();
	}

	public String getTypeAgeDescription() {
		return typeAgeDescription;
	}

	public String getRaceDescription() {
		return raceDescription;
	}

	public String getPasseport() {
		return entite.getPasseport();
	}

	public String getNmeroAssuranceMaladie() {
		return entite.getNumeroAssuranceMaladie();
	}

	public String getSeveriteDescription() {
		return severiteDescription;
	}

	public String getSeveriteCasinoDescription() {
		return severiteCasinoDescription;
	}

	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}

	public String getNumeroClientEmploye() {
		return entite.getNumeroClientEmploye();
	}

	public String getEthnieDescription() {
		return ethnieDescription;
	}

	public String getNumeroAssuranceMaladie() {
		return entite.getNumeroAssuranceMaladie();
	}

	public void setSeveriteDescriptionAutres(String severiteDescriptionAutres) {
		this.severiteDescriptionAutres = severiteDescriptionAutres;
	}

	public void setSexeDescription(String sexeDescription) {
		this.sexeDescription = sexeDescription;
	}

	public void setLangueDescription(String langueDescription) {
		this.langueDescription = langueDescription;
	}

	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}

	public void setTypeAgeDescription(String typeAgeDescription) {
		this.typeAgeDescription = typeAgeDescription;
	}

	public void setRaceDescription(String raceDescription) {
		this.raceDescription = raceDescription;
	}

	public void setSeveriteDescription(String severiteDescription) {
		this.severiteDescription = severiteDescription;
	}

	public void setSeveriteCasinoDescription(String severiteCasinoDescription) {
		this.severiteCasinoDescription = severiteCasinoDescription;
	}

	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}

	public void setEthnieDescription(String ethnieDescription) {
		this.ethnieDescription = ethnieDescription;
	}

	public long getSite() {
		return entite.getSite();
	}

	// Setters
	
	
	public void setNom(String nom) {
		this.entite.setNom(nom);
	}

	public void setPrenom(String prenom) {
		this.entite.setPrenom(prenom);
	}

	public void setAlias(String alias) {
		this.entite.setAlias(alias);
	}

	public void setDateNaissance(Timestamp dateNaissance) {
		this.entite.setDateNaissance(dateNaissance);
	}

	public void setReference1(String reference1) {
		this.entite.setReference1(reference1);
	}

	public void setReference2(String reference2) {
		this.entite.setReference2(reference2);
	}

	public void setNumeroClientEmploye(String numeroClientEmploye) {
		this.entite.setNumeroClientEmploye(numeroClientEmploye);
	}

	public void setNumeroAssuranceSociale(String numeroAssuranceSociale) {
		this.entite.setNumeroAssuranceSociale(numeroAssuranceSociale);
	}

	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.entite.setNumeroPermisConduire(numeroPermisConduire);
	}

	public void setPasseport(String passeport) {
		this.entite.setPasseport(passeport);
	}

	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie) {
		this.entite.setNumeroAssuranceMaladie(numeroAssuranceMaladie);
	}

	public void setNumeroFiche(String numeroFiche) {
		this.entite.setNumeroFiche(numeroFiche);
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
}
