/*
 * Created on 26-Sep-2008
 */
package com.lotoQuebec.correcteurAdresse;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;


/**
 * @author levassc
 */
public class AdresseSortie {

	//PS_CAN_out_return_code
	private String codeRetour = "";
	//PS_CAN_out_address_type
	private String type = "";
	//PS_CAN_out_address_line
	private String adresseligne = "";
	//PS_CAN_out_street_number	
	private String numeroMunicipale = "";
	//PS_CAN_out_street_suffix
	private String sufixMunicipale = "";
	//PS_CAN_out_street_name
	private String nomRue = "";
	//PS_CAN_out_street_type
	private String typeRue = "";
	//PS_CAN_out_street_direction
	private String cardinaliteRue = "";
	//PS_CAN_out_unit_type
	private String typeUnite = "";
	//PS_CAN_out_unit_number
	private String numeroUnite = "";
	//PS_CAN_out_service_type
	private String typeService = "";
	//PS_CAN_out_service_number
	private String numeroService = "";
	//PS_CAN_out_service_area_name
	private String nomRegionService = "";
	//PS_CAN_out_service_area_type
	private String typeRegionService = "";
	//PS_CAN_out_service_area_qualifier
	private String qualificatifRegionService = "";
	//PS_CAN_out_city
	private String ville = "";
	//PS_CAN_out_city_abbrev_long
	private String villeLong = "";
	//PS_CAN_out_city_abbrev_short
	private String villeAbreviation = "";
	//PS_CAN_out_province
	private String province = "";
	//PS_CAN_out_postal_code
	private String codePostal = "";
	//PS_CAN_out_country
	private String pays = "";
	//PS_CAN_out_additional_information
	private String informationAdditionnel1 = "";
	//PS_CAN_out_extra_information
	private String informationAdditionnel2 = "";
	//PS_CAN_out_unidentified_component
	private String informationNonIdentifier = "";
	//"PS_ARG_out_function_messages
	private String messages = "";
	//PS_CAN_out_status_flag
	private String statut = "";
	//PS_CAN_out_status_messages
	private String messageStatut = "";
	
	
	public String getAdresseligne() {
		return adresseligne;
	}
	public void setAdresseligne(String adresseligne) {
		this.adresseligne = adresseligne;
	}
	public String getCardinaliteRue() {
		return cardinaliteRue;
	}
	public void setCardinaliteRue(String cardinaliteRue) {
		this.cardinaliteRue = cardinaliteRue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getCodeRetour() {
		return codeRetour;
	}
	public void setCodeRetour(String codeRetour) {
		this.codeRetour = codeRetour;
	}
	public String getInformationAdditionnel1() {
		return informationAdditionnel1;
	}
	public void setInformationAdditionnel1(String informationAdditionnel1) {
		this.informationAdditionnel1 = informationAdditionnel1;
	}
	public String getInformationAdditionnel2() {
		return informationAdditionnel2;
	}
	public void setInformationAdditionnel2(String informationAdditionnel2) {
		this.informationAdditionnel2 = informationAdditionnel2;
	}
	public String getInformationNonIdentifier() {
		return informationNonIdentifier;
	}
	public void setInformationNonIdentifier(String informationNonIdentifier) {
		this.informationNonIdentifier = informationNonIdentifier;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getMessageStatut() {
		return messageStatut;
	}
	public void setMessageStatut(String messageStatut) {
		this.messageStatut = messageStatut;
	}
	public String getNomRegionService() {
		return nomRegionService;
	}
	public void setNomRegionService(String nomRegionService) {
		this.nomRegionService = nomRegionService;
	}
	public String getNomRue() {
		return nomRue;
	}
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	public String getNumeroMunicipale() {
		return numeroMunicipale;
	}
	public void setNumeroMunicipale(String numeroMunicipale) {
		this.numeroMunicipale = numeroMunicipale;
	}
	public String getNumeroService() {
		return numeroService;
	}
	public void setNumeroService(String numeroService) {
		this.numeroService = numeroService;
	}
	public String getNumeroUnite() {
		return numeroUnite;
	}
	public void setNumeroUnite(String numeroUnite) {
		this.numeroUnite = numeroUnite;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getQualificatifRegionService() {
		return qualificatifRegionService;
	}
	public void setQualificatifRegionService(String qualificatifRegionService) {
		this.qualificatifRegionService = qualificatifRegionService;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getSufixMunicipale() {
		return sufixMunicipale;
	}
	public void setSufixMunicipale(String sufixMunicipale) {
		this.sufixMunicipale = sufixMunicipale;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeRegionService() {
		return typeRegionService;
	}
	public void setTypeRegionService(String typeRegionService) {
		this.typeRegionService = typeRegionService;
	}
	public String getTypeRue() {
		return typeRue;
	}
	public void setTypeRue(String typeRue) {
		this.typeRue = typeRue;
	}
	public String getTypeService() {
		return typeService;
	}
	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}
	public String getTypeUnite() {
		return typeUnite;
	}
	public void setTypeUnite(String typeUnite) {
		this.typeUnite = typeUnite;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getVilleAbreviation() {
		return villeAbreviation;
	}
	public void setVilleAbreviation(String villeAbreviation) {
		this.villeAbreviation = villeAbreviation;
	}
	public String getVilleLong() {
		return villeLong;
	}
	public void setVilleLong(String villeLong) {
		this.villeLong = villeLong;
	}
	public boolean isValide(){
		return "V".equals( getStatut() );
	}
	public boolean isCorrected(){
		return "C".equals( getStatut() );
	}
	public boolean isNotValide(){
		return "N".equals( getStatut() );
	}
	public String obtenirVillePlusLong(){
		
		if (StringUtils.isNotEmpty(getVilleLong()))
			return getVilleLong();
		return getVille();
	}
	
	public String obtenirMessageHTML() {
		String messageSortie = new String(messages);
		
		//Retirer les deux premières lignes
		int premiereLigne = messageSortie.indexOf("\r");
		int deuxiemeLigne = messageSortie.indexOf("\r",premiereLigne+1);
		
		if (deuxiemeLigne == -1)
			return "";
		
		messageSortie = StringUtils.right(messageSortie, messageSortie.length()-deuxiemeLigne-1);
		messageSortie = StringUtils.replace(messageSortie, "\r", "</br>");
		messageSortie = StringUtils.replace(messageSortie, "ESS ", "<b>Essayer :</b>");
		messageSortie = StringUtils.replace(messageSortie, "OPT ", "<b>Optionnel :</b>");
		messageSortie = StringUtils.replace(messageSortie, "ERR ", "<b>Erreur :</b>");
		messageSortie = StringUtils.replace(messageSortie, "INF ", "<b>Information :</b>");
		messageSortie = StringUtils.replace(messageSortie, "CHG ", "<b>Changer :</b>");
		messageSortie = StringUtils.replace(messageSortie, "COR ", "<b>Corriger :</b>");
		return messageSortie;
	}

	private String ajouterEspace(String element){
		
		if (StringUtils.isNotEmpty( element ))
			return element + " ";
		return "";
	}
	
	public String obtenirAdressePostal(){
		String adressePostal = "";
		adressePostal += ajouterEspace(getTypeService())+" ";
		adressePostal += ajouterEspace(getNumeroService())+" ";
		adressePostal += ajouterEspace(getNomRegionService())+" ";
		adressePostal += ajouterEspace(getTypeRegionService())+" ";
		adressePostal += ajouterEspace(getQualificatifRegionService());
		//adressePostal += ajouterEspace(getInformationAdditionnel1());
		//adressePostal += ajouterEspace(getInformationAdditionnel2());
		//informationSuplementaire += ajouterEspace(adresseSortie.getInformationNonIdentifier());
		
		return adressePostal.trim();		
	}
}
