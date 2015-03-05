/*
 * Created on 13-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.narration;

import java.text.ParseException;
import java.util.Date;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EthnieCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LangueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RaceCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RoleCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SexeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class SujetNarration {

	private String role = "";
	private long roleLong = 0;
	private String numeroBijou = "";
	private String numeroAeAgAi = "";
	private String indexSujet = "";
	private String nom = "";
	private String prenom = "";
	private String dateNaissance = "";
	private String age = "";
	private String sexe = "";
	private long sexeLong = 0;
	private String alias = "";
	private String ethnie = "";
	private long ethnieLong = 0;
	private String race = "";
	private long raceLong = 0;
	private String langue = "";
	private long langueLong = 0;
	private String adresse = "";

    private String numeroMunicipal = "";
    private String typeRue = "";
    private long typeRueLong  = 0;
    private String nomRue = "";
    private String pointCardinal = "";
    private long pointCardinalLong = 0;
    private String unite = "";
    private long uniteLong = 0;
    private String numeroUnite = "";
    private String adressePostal = "";
	
	private String ville = "";
	private long villeLong = 0;
	private String province = "";
	private long provinceLong = 0;
	private String pays = "";
	private long paysLong = 0;
	private String codePostal = "";
	private String telephoneResidence = "";
	private String telephoneAutre = "";
	private String adresseElectronique = "";
	private String numeroPermisConduire = "";
	private String taille = "";
	private String cheveux = "";
	private String masse = "";
	private String autreCaracteristiques = "";

	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getAdresseElectronique() {
		return adresseElectronique;
	}
	public void setAdresseElectronique(String adresseElectronique) {
		this.adresseElectronique = adresseElectronique;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAutreCaracteristiques() {
		return autreCaracteristiques;
	}
	public void setAutreCaracteristiques(String autreCaracteristiques) {
		this.autreCaracteristiques = autreCaracteristiques;
	}
	public String getCheveux() {
		return cheveux;
	}
	public void setCheveux(String cheveux) {
		this.cheveux = cheveux;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public String getDateNaissanceDefault() {
		
		if (StringUtils.isEmpty(dateNaissance))
			return DateFormat.DATE_DEFAULT;
		return dateNaissance;
	}	
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getEthnie() {
		return ethnie;
	}
	public void setEthnie(String ethnie) {
		this.ethnie = ethnie;
	}
	public String getIndexSujet() {
		return indexSujet;
	}
	public void setIndexSujet(String indexSujet) {
		this.indexSujet = indexSujet;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getMasse() {
		return masse;
	}
	public void setMasse(String masse) {
		this.masse = masse;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = StringUtils.capitalise( nom );
	}
	public String getNumeroAeAgAi() {
		return numeroAeAgAi;
	}
	public void setNumeroAeAgAi(String numeroAeAgAi) {
		this.numeroAeAgAi = numeroAeAgAi;
	}
	public String getNumeroBijou() {
		return numeroBijou;
	}
	public void setNumeroBijou(String numeroBijou) {
		this.numeroBijou = numeroBijou;
	}
	public String getNumeroPermisConduire() {
		return numeroPermisConduire;
	}
	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.numeroPermisConduire = numeroPermisConduire;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = StringUtils.capitalise( prenom );
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public String getTelephoneAutre() {
		return telephoneAutre;
	}
	public void setTelephoneAutre(String telephoneAutre) {
		this.telephoneAutre = telephoneAutre;
	}
	public String getTelephoneResidence() {
		return telephoneResidence;
	}
	public void setTelephoneResidence(String telephoneResidence) {
		this.telephoneResidence = telephoneResidence;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getAdressePostal() {
		return adressePostal;
	}
	public void setAdressePostal(String adressePostal) {
		this.adressePostal = adressePostal;
	}
	public void setEthnieLong(long ethnieLong) {
		this.ethnieLong = ethnieLong;
	}
	public void setLangueLong(long langueLong) {
		this.langueLong = langueLong;
	}
	public String getNomRue() {
		return nomRue;
	}
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	public String getNumeroMunicipal() {
		return numeroMunicipal;
	}
	public void setNumeroMunicipal(String numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}
	public String getNumeroUnite() {
		return numeroUnite;
	}
	public void setNumeroUnite(String numeroUnite) {
		this.numeroUnite = numeroUnite;
	}
	public void setPaysLong(long paysLong) {
		this.paysLong = paysLong;
	}
	public String getPointCardinal() {
		return pointCardinal;
	}
	public void setPointCardinal(String pointCardinal) {
		this.pointCardinal = pointCardinal;
	}
	public long getPointCardinalLong(CardexAuthenticationSubject subject) throws BusinessResourceException {
		pointCardinalLong = obtenirListeLong(subject, new CardinaliteCleListeCache(subject), pointCardinal, pointCardinalLong);
		return pointCardinalLong;
	}

	public String getTypeRue() {
		return typeRue;
	}
	public void setTypeRue(String typeRue) {
		this.typeRue = typeRue;
	}
	public long getTypeRueLong(CardexAuthenticationSubject subject) throws BusinessResourceException {
		typeRueLong = obtenirListeLong(subject, new TypeRueCleListeCache(subject), typeRue, typeRueLong);
		return typeRueLong;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}
	public long getUniteLong(CardexAuthenticationSubject subject) throws BusinessResourceException {
		
		if ("APP".equalsIgnoreCase(unite)
		|| "APT".equalsIgnoreCase(unite))
			unite = "APPARTEMENT";		
		
		uniteLong = obtenirListeLong(subject, new TypeUniteCleListeCache(subject), unite, uniteLong);
		return uniteLong;
	}
	private long obtenirListeLong(CardexAuthenticationSubject subject, CleListe cleListe, String cleValue, long longValue) throws BusinessResourceException{
		ListeCache cache = ListeCache.getInstance();
		
		if (StringUtils.isEmpty( cleValue ) || longValue != 0)
			return longValue;
		
		return cache.obtenirKeyDeLabel(subject, cleValue, cleListe);
	}
	public Date getDateNaissanceDate(){
		Date dateDefault = null;
			
		try {
			dateDefault = DateFormat.parse(DateFormat.DATE_DEFAULT);
		} catch (ParseException e1) {
		}
		
		if (StringUtils.isEmpty( dateNaissance )){
			return dateDefault;
		}
		
		try {
			return DateFormat.parse(dateNaissance);
		} catch (ParseException e) {
			return dateDefault;
		}
	}
	
	public long getProvinceLong(CardexAuthenticationSubject subject) throws BusinessResourceException{
		
		if ("qc".equalsIgnoreCase(province))
			province = "Québec";
		
		provinceLong = obtenirListeLong(subject, new ProvinceCleMultiListeCache(subject, String.valueOf(getPaysLong(subject))), province, provinceLong);
		
		return provinceLong;
	}	
	
	public long getPaysLong(CardexAuthenticationSubject subject) throws BusinessResourceException{
		
		if ("ca".equalsIgnoreCase(pays))
			pays = "Canada";

		paysLong = obtenirListeLong(subject, new PaysCleListeCache(subject), pays, paysLong);
		
		return paysLong;
	}	

	public long getRoleLong(CardexAuthenticationSubject subject) throws BusinessResourceException{

		// Défault : N / A
		if (StringUtils.isEmpty( role ))
			roleLong = GlobalConstants.LienRole.CLIENT;
		
		roleLong = obtenirListeLong(subject, new RoleCleListeCache(subject), role, roleLong);
		
		// Défault : N / A
		if (roleLong == 0)
			roleLong = GlobalConstants.LienRole.CLIENT;
			
		return roleLong;		
	}
	
	public long getEthnieLong(CardexAuthenticationSubject subject) throws BusinessResourceException{
		ethnieLong = obtenirListeLong(subject, new EthnieCleListeCache(subject), ethnie, ethnieLong);
		return ethnieLong;		
	}
	
	public long getLangueLong(CardexAuthenticationSubject subject) throws BusinessResourceException{
		langueLong = obtenirListeLong(subject, new LangueCleListeCache(subject), langue, langueLong);
		
		if (langueLong == 0)
			langueLong = GlobalConstants.LangueListe.FRANCAIS;
		
		return langueLong;		
	}	

	public long getSexeLong(CardexAuthenticationSubject subject) throws BusinessResourceException{

		if ("f".equalsIgnoreCase(sexe))
			sexe = "Féminin";

		else if ("m".equalsIgnoreCase(sexe))
			sexe = "Masculin";
		
		sexeLong = obtenirListeLong(subject, new SexeCleListeCache(subject), sexe, sexeLong);
		return sexeLong;		
	}
	
	public long getRaceLong(CardexAuthenticationSubject subject) throws BusinessResourceException{
		raceLong = obtenirListeLong(subject, new RaceCleListeCache(subject), race, raceLong);
		return raceLong;		
	}	

	public long getVilleLong(CardexAuthenticationSubject subject) throws BusinessResourceException{

		// Pas de province, on utilise celle du québec par default
		if (getProvinceLong(subject) == 0){
			long provinceTemp = Long.valueOf( GlobalConstants.Adresse.PROVINCE ).longValue();
			villeLong = obtenirListeLong(subject, new VilleCleMultiExterneListeCache(subject, String.valueOf(provinceTemp)), ville, villeLong);
			
			if (villeLong != 0){
				this.provinceLong = Long.valueOf( GlobalConstants.Adresse.PROVINCE ).longValue();
				this.paysLong = Long.valueOf( GlobalConstants.Adresse.PAYS ).longValue();
			}
		}else{
			villeLong = obtenirListeLong(subject, new VilleCleMultiExterneListeCache(subject, String.valueOf(provinceLong)), ville, villeLong);
		}
		
		return villeLong;		
	}
	
	public boolean isPossedeAdresse(){
		return StringUtils.isNotEmpty( ville );
	}
	
}
