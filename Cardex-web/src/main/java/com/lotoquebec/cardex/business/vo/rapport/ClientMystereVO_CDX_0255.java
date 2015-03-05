package com.lotoquebec.cardex.business.vo.rapport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.DossierVO;

public class ClientMystereVO_CDX_0255 extends RapportVO implements Cloneable{

	private long cle;
	private long site;
	
	private String district;
	private String regionAdministrative;
	private String numero;
	private String nom;
	private Date creationAdresseDate;
	private String adresse;
	private String ville;
	private String codePostal;
	private String centreRegional;
	private String telephone;
	private String langue;
	private String codeCommerce;
	private String nomCompte;
	private ResponsableSocieteVO_CDX_0255 responsableSociete;
	private List<SectionSocieteVO_CDX_0255> sectionSocieteCDX255VOs = new ArrayList<SectionSocieteVO_CDX_0255>();

	
	public ClientMystereVO_CDX_0255(long cle, long site) {
		super();
		this.cle = cle;
		this.site = site;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cle ^ (cle >>> 32));
		result = prime * result + (int) (site ^ (site >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientMystereVO_CDX_0255 other = (ClientMystereVO_CDX_0255) obj;
		if (cle != other.cle)
			return false;
		if (site != other.site)
			return false;
		return true;
	}

	@Override
	public ClientMystereVO_CDX_0255 clone() throws CloneNotSupportedException {
		ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255 = new ClientMystereVO_CDX_0255(this.cle, this.site);
		clientMystereVO_CDX_0255.setDistrict(this.getDistrict());
		clientMystereVO_CDX_0255.setRegionAdministrative(this.regionAdministrative);
		clientMystereVO_CDX_0255.setNumero(this.numero);
		clientMystereVO_CDX_0255.setNom(this.nom);
		clientMystereVO_CDX_0255.setCreationAdresseDate(this.creationAdresseDate);
		clientMystereVO_CDX_0255.setAdresse(this.adresse);
		clientMystereVO_CDX_0255.setVille(this.ville);
		clientMystereVO_CDX_0255.setCodePostal(this.codePostal);
		clientMystereVO_CDX_0255.setCentreRegional(this.centreRegional);
		clientMystereVO_CDX_0255.setTelephone(this.telephone);
		clientMystereVO_CDX_0255.setLangue(this.langue);
		clientMystereVO_CDX_0255.setCodeCommerce(this.codeCommerce);
		clientMystereVO_CDX_0255.setNomCompte(this.nomCompte);
		
		if (this.responsableSociete != null)
			clientMystereVO_CDX_0255.setResponsableSociete( this.responsableSociete.clone() );

		for (SectionSocieteVO_CDX_0255 sectionSocieteVO_CDX_0255:this.sectionSocieteCDX255VOs)
			clientMystereVO_CDX_0255.addSectionSocieteCDX255VO( sectionSocieteVO_CDX_0255.clone() );
		
		return clientMystereVO_CDX_0255;
	}

	public SectionSocieteVO_CDX_0255 obtenirSectionSocieteCDX0255VO(Dossier echantillonDossier){
		
		for (int i = sectionSocieteCDX255VOs.size()-1; i > -1; i--) { // on prends le dernier en premier
		    SectionSocieteVO_CDX_0255 sectionSocieteCDX0255VO = sectionSocieteCDX255VOs.get(i);
			
			if (sectionSocieteCDX0255VO.getEchantillonDossier().equals(echantillonDossier))
				return sectionSocieteCDX0255VO;
		}
		// section non trouvée, création d'une section
		SectionSocieteVO_CDX_0255 sectionSocieteCDX0255VO = new SectionSocieteVO_CDX_0255();
		Dossier nouveauEchantillonDossier = new DossierVO(echantillonDossier.getCle(), echantillonDossier.getSite());
		nouveauEchantillonDossier.setNumeroDossier(echantillonDossier.getNumeroDossier());
		sectionSocieteCDX0255VO.setEchantillonDossier(nouveauEchantillonDossier);
		sectionSocieteCDX0255VO.setVague(echantillonDossier.getNumeroDossier());
		sectionSocieteCDX255VOs.add(sectionSocieteCDX0255VO);
		
		return sectionSocieteCDX0255VO;
	}
	
	public void doublerSectionSociete(Date changementGestionDetaillantDate){
		
		if (sectionSocieteCDX255VOs.size() == 0)
			return;
		SectionSocieteVO_CDX_0255 sectionSocieteCDX0255VO = sectionSocieteCDX255VOs.get(sectionSocieteCDX255VOs.size()-1);
		SectionSocieteVO_CDX_0255 doubleSectionSocieteCDX0255VO = new SectionSocieteVO_CDX_0255();
		sectionSocieteCDX255VOs.add(doubleSectionSocieteCDX0255VO);
		Dossier nouveauEchantillonDossier = new DossierVO(sectionSocieteCDX0255VO.getEchantillonDossier().getCle(), sectionSocieteCDX0255VO.getEchantillonDossier().getSite());
		nouveauEchantillonDossier.setNumeroDossier(sectionSocieteCDX0255VO.getEchantillonDossier().getNumeroDossier());
		doubleSectionSocieteCDX0255VO.setEchantillonDossier(nouveauEchantillonDossier);
		doubleSectionSocieteCDX0255VO.setVague( sectionSocieteCDX0255VO.getVague() );
		doubleSectionSocieteCDX0255VO.setChangementGestionDetaillantDate(changementGestionDetaillantDate);
	}

	@Override
	public String toString() {
		return "CDX0255VO [district=" + district + ", numero=" + numero + ", nom="
				+ nom + ", adresse=" + adresse + ", ville=" + ville
				+ ", codePostal=" + codePostal + ", langue=" + langue
				+ ", codeCommerce=" + codeCommerce + "]";
	}

	// get / set
	public List<SectionSocieteVO_CDX_0255> getSectionSocieteCDX255VOs() {
		return sectionSocieteCDX255VOs;
	}

	public void setSectionSocieteCDX255VOs(List<SectionSocieteVO_CDX_0255> sectionSocieteCDX255VOs) {
		this.sectionSocieteCDX255VOs = sectionSocieteCDX255VOs;
	}

	public void addSectionSocieteCDX255VO(SectionSocieteVO_CDX_0255 sectionSocieteCDX255VO) {
		this.sectionSocieteCDX255VOs.add(sectionSocieteCDX255VO);
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getCreationAdresseDate() {
		return creationAdresseDate;
	}

	public void setCreationAdresseDate(Date creationAdresseDate) {
		this.creationAdresseDate = creationAdresseDate;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getCodeCommerce() {
		return codeCommerce;
	}

	public void setCodeCommerce(String codeCommerce) {
		this.codeCommerce = codeCommerce;
	}

	public ResponsableSocieteVO_CDX_0255 getResponsableSociete() {
		return responsableSociete;
	}

	public void setResponsableSociete(ResponsableSocieteVO_CDX_0255 responsableSociete) {
		this.responsableSociete = responsableSociete;
	}

	public long getCle() {
		return cle;
	}

	public long getSite() {
		return site;
	}

	public String getRegionAdministrative() {
		return regionAdministrative;
	}

	public void setRegionAdministrative(String regionAdministrative) {
		this.regionAdministrative = regionAdministrative;
	}

	public String getCentreRegional() {
		return centreRegional;
	}

	public void setCentreRegional(String centreRegional) {
		this.centreRegional = centreRegional;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNomCompte() {
		return nomCompte;
	}

	public void setNomCompte(String nomCompte) {
		this.nomCompte = nomCompte;
	}
	
	
	
}
