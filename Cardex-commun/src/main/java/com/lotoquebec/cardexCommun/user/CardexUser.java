package com.lotoquebec.cardexCommun.user;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.authentication.User;

/**
 * Cette classe represente le profile utilisateur
 * du systeme CARDEX.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.7 $, $Date: 2002/02/18 23:54:24 $
 */
public class CardexUser implements User, Serializable {

    /**
     * Code utilisateur
     */
    private String code;

    /**
     * Code parent utilisateur
     */
    private String codeParent;

    /**
     * Site
     */
    private long site;

    /**
     * Entité
     */
    private long entite;


    /**
     * Secteur
     */
    private long secteur;
    
    /**
     * Secteur
     */
    private long sousSecteur;

    /**
     * Information poste de l'utilisateur
     */
    private String fureteur = "";
    private String versionPoste = "";
    private String dimentionEcran = "";
    private String adresseIP = "";
    private Boolean cookiesActif;
    private Boolean javaActif;
    private Boolean scriptFileSystemObjectActif;
    private Boolean clipBoardActif;

    /**
     * Construit une instance CardexUser.
     */
    public CardexUser() {
    }

    /**
     * Construit une instance CardexUser.
     */
    public CardexUser(String code) {
      this.code = code;
    }

    public CardexUser(String code, long site) {
        this.code = code;
        this.site = site;
      }
    
    /**
     * Retourne le code utilisateur.
     *
     * @return long Code utilisateur
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Retourne le site
     *
     * @return long le site
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne l'entité
     *
     * @return long l'entité
     */
    public long getEntite() {
        return this.entite;
    }

    /**
     * Retourne le secteur
     *
     * @return String l'entité
     */
    public long getSecteur() {
        return this.secteur;
    }

    /**
     * Affecte le code utilisateur
     *
     * @param code Le code utilisateur
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Affecte le site
     *
     * @param site Le site
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte l'entité
     *
     * @param entite l'entité
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }

    /**
     * Affecte le secteur
     *
     * @param secteur le secteur
     */
    public void setSecteur(long secteur) {
        this.secteur = secteur;
    }

	/**
	 * @return codeParent
	 */
	public String getCodeParent() {
		return codeParent;
	}

	/**
	 * @param codeParent codeParent à définir
	 */
	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	public String getFureteur() {
		return fureteur;
	}

	public void setFureteur(String fureteur) {
		this.fureteur = fureteur;
	}

	public String getVersionPoste() {
		return versionPoste;
	}

	public void setVersionPoste(String version) {
		this.versionPoste = version;
	}

	public String getDimentionEcran() {
		return dimentionEcran;
	}

	public void setDimentionEcran(String dimentionEcran) {
		this.dimentionEcran = dimentionEcran;
	}

	public String getAdresseIP() {
		return adresseIP;
	}

	public void setAdresseIP(String adresseIP) {
		this.adresseIP = adresseIP;
	}

	public Boolean getCookiesActif() {
		return cookiesActif;
	}

	public void setCookiesActif(Boolean cookiesActif) {
		this.cookiesActif = cookiesActif;
	}

	public Boolean getJavaActif() {
		return javaActif;
	}

	public void setJavaActif(Boolean javaActif) {
		this.javaActif = javaActif;
	}

	public Boolean getScriptFileSystemObjectActif() {
		return scriptFileSystemObjectActif;
	}

	public void setScriptFileSystemObjectActif(Boolean scriptFileSystemObjectActif) {
		this.scriptFileSystemObjectActif = scriptFileSystemObjectActif;
	}

	public Boolean getClipBoardActif() {
		return clipBoardActif;
	}

	public void setClipBoardActif(Boolean clipBoardActif) {
		this.clipBoardActif = clipBoardActif;
	}

	/**
	 * @return sousSecteur
	 */
	public long getSousSecteur() {
		return sousSecteur;
	}

	/**
	 * @param sousSecteur sousSecteur à définir
	 */
	public void setSousSecteur(long sousSecteur) {
		this.sousSecteur = sousSecteur;
	}
    
	
}