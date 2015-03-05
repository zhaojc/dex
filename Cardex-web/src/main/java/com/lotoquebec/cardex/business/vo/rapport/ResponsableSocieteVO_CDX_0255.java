package com.lotoquebec.cardex.business.vo.rapport;

import java.util.Date;

import com.lotoquebec.cardexCommun.util.StringUtils;


public class ResponsableSocieteVO_CDX_0255 implements Cloneable{

	private String nomSociete;
	private String prenom;
	private String nom;
	private Date liaisonResponsableDate;

	// '(a/s ' || su.v_su_nom || ' ' || su.v_su_prenom || ')'
	// sr.v_so_nom || ' (a/s ' || s.v_so_reference_prenom || ' ' || s.v_so_reference_nom || ')'
	public String getAffichageResponsable() {
		StringBuilder responsable = new StringBuilder();
		
		if (StringUtils.isEmpty(nomSociete)
		&& StringUtils.isEmpty(prenom)
		&& StringUtils.isEmpty(nom))
			return "";
		
		if (StringUtils.isNotEmpty(nomSociete))
			responsable.append(nomSociete);
		responsable.append(" (a/s ");
		responsable.append(prenom);
		responsable.append(" ");
		responsable.append(nom);
		responsable.append(")");
		return responsable.toString();
	}
	
	@Override
	public String toString() {
		return "ResponsableSociete [nomSociete=" + nomSociete + ", prenom="
				+ prenom + ", nom=" + nom + ", liaisonResponsableDate="
				+ liaisonResponsableDate + "]";
	}
	
	@Override
	public ResponsableSocieteVO_CDX_0255 clone() throws CloneNotSupportedException {
		ResponsableSocieteVO_CDX_0255 responsableSocieteVO_CDX_0255 = new ResponsableSocieteVO_CDX_0255();
		responsableSocieteVO_CDX_0255.setNomSociete(this.nomSociete);
		responsableSocieteVO_CDX_0255.setPrenom(this.prenom);
		responsableSocieteVO_CDX_0255.setNom(this.nom);
		responsableSocieteVO_CDX_0255.setLiaisonResponsableDate(this.liaisonResponsableDate);
		
		return responsableSocieteVO_CDX_0255;
	}
	
	// get / set
	public String getNomSociete() {
		return nomSociete;
	}
	
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getLiaisonResponsableDate() {
		return liaisonResponsableDate;
	}

	public void setLiaisonResponsableDate(Date liaisonResponsableDate) {
		this.liaisonResponsableDate = liaisonResponsableDate;
	}
	
}
