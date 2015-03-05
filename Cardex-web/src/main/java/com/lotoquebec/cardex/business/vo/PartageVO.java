package com.lotoquebec.cardex.business.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardex.business.Partage;

/**
 * Conserve les différentes valeurs relatives au formulatire de consultation des
 * caractéristiques.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.CaracteristiquesHtmlForm
 */
public class PartageVO implements Partage {

    private long cle = 0;
    private long site = 0;
    private long entite = 0;
    private String intervenant = "";
    private String profil = "";
    private long siteIntervenant = 0;
    private long lien = 0;
    private long lienSite = 0;
    private String createur = "";
    private ArrayList intervenantsChoisis = new ArrayList();    
//    private String numeroDossier = "";
//    private long categorie = 0;
//    private long entite = 0;
//    private long siteOrigine = 0;
    //Genre de partage : restreint : seuls les utilisateurs inscrits dans l'onglet Partage peuvent ouvrir le dossier
    //					 ouvert : les utilisateurs avec le code de confidentialité égal ou supérieur et les utilisateurs
    //inscrits dans l'onglet Partage.
    private String genrePartage = "";
    
    
    /**
     * Constructeur de PartageVO par défaut.
     */
    public PartageVO() {}


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caractère.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne le nom de l'intervenant qui crée le partage.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le profil.
     *
     * @return String Valeur du profil en caractère.
     */
    public String getProfil() {
        return this.profil;
    }

    /**
     * Retourne le site des intervenants.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSiteIntervenant() {
        return this.siteIntervenant;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caractère.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caractère.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte un intervenant.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un profil.
     *
     * @param nom Valeur du profil en caractère.
     */
    public void setProfil(String profil) {
        this.profil = profil;
    }

    /**
     * Affecte un site de l'intervenant.
     *
     * @param prenom Valeur du site en caractère.
     */
    public void setSiteIntervenant(long siteIntervenant) {
        this.siteIntervenant = siteIntervenant;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }


    /**
     * Retourne une chaîne d'intervenants reflétant la valeur de tous les
     * attributs du PartageForm.
     *
     * @return String Valeur de tous les attributs du PartageForm en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[PartageForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', profil = '" + profil);
        stringBuffer.append("', siteIntervenant = '" + siteIntervenant);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }


	/**
	 * @return createur
	 */
	public String getCreateur() {
		return createur;
	}


	/**
	 * @param createur createur à définir
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

    /**
     * Ajoute un intervenant.
     *
     * @param caracteristique Valeur de l'intervenant.
     */
    public void addIntervenant(String intervenant) {
        this.intervenantsChoisis.add(intervenant);
    }

    /**
     * Retourne la liste des intervenants choisis.
     *
     * @return Collection Valeur de la liste des intervenants choisis.
     */
    public Collection getIntervenantsChoisis() {
        return this.intervenantsChoisis;
    }


	public long getEntite() {
		return entite;
	}


	public void setEntite(long entite) {
		this.entite = entite;
	}


	public String getGenrePartage() {
		return genrePartage;
	}


	public void setGenrePartage(String genrePartage) {
		this.genrePartage = genrePartage;
	}
    
}