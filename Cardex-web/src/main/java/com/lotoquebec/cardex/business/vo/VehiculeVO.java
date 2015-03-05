package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la consultation d'un
 * véhicule de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.12 $, $Date: 2002/02/28 23:46:52 $
 * @see com.lotoquebec.cardex.business.Vehicule
 */
public class VehiculeVO implements Vehicule {

    private long cle = 0;
    private long site = 0;
    private long entite = 0;
    private String numeroDossier = "";
    private String siteDossier = "";
    private long marque = 0;
    private long modele = 0;
    private long modeleMarque = 0;
    private String immatriculation = "";
    private String province = "";
    private long cleProvince = 0;
    private String annee = "";
    private String vignette = "";
    private String commentaire = "";
    private Timestamp dateExpirationVignette = null;
    private String numeroSerie = "";
    private String assurance = "";
    private Timestamp dateExpirationAssurance = null;
    private String police = "";
    private long confidentialite = 0;
    private String motPasse = "";
    private String confirmationMotPasse = "";
    private String createur = "";
    private Timestamp dateCreation = null;
    private Timestamp lienDateCreation = null;
    private long lien = 0;
    private long lienSite = 0;
	private String lienCreateur = "";
    private long role = 0;
    private long typeLien = 0;
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";

    private Set<LiaisonEntiteVO> liaisonEntites = new HashSet<LiaisonEntiteVO>();
    
    /**
     * Constructeur de VehiculeVO par défaut.
     */
    public VehiculeVO() {}

    /**
	 * @param cle
	 * @param site
	 */
	public VehiculeVO(long cle, long site) {
		super();
		this.cle = cle;
		this.site = site;
	}

	// Equals
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
		VehiculeVO other = (VehiculeVO) obj;
		if (cle != other.cle)
			return false;
		if (site != other.site)
			return false;
		return true;
	}	
	
    // Getters

	/**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caractère.
     */
    public String getSiteDossier() {
        return this.siteDossier;
    }

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public long getMarque() {
        return this.marque;
    }

    /**
     * Retourne le modèle.
     *
     * @return long Valeur du modèle.
     */
    public long getModele() {
        return this.modele;
    }

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province.
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Retourne l'année.
     *
     * @return String Valeur de l'année en caractère.
     */
    public String getAnnee() {
        return this.annee;
    }

    /**
     * Retourne la vignette.
     *
     * @return String Valeur de la vignette en caractère.
     */
    public String getVignette() {
        return this.vignette;
    }

    /**
     * Retourne la date d'expiration de la vignette.
     *
     * @return Timestamp Valeur de la date d'expiration de la vignette
     * (yyyy-MM-dd).
     */
    public Timestamp getDateExpirationVignette() {
        return this.dateExpirationVignette;
    }

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    /**
     * Retourne l'assurance.
     *
     * @return String Valeur de l'assurance en caractère.
     */
    public String getAssurance() {
        return this.assurance;
    }

    /**
     * Retourne la date d'expiration de l'assurance.
     *
     * @return Timestamp Valeur de la date d'expiration de l'assurance
     * (yyyy-MM-dd).
     */
    public Timestamp getDateExpirationAssurance() {
        return this.dateExpirationAssurance;
    }

    /**
     * Retourne la police.
     *
     * @return String Valeur de la police en caractère.
     */
    public String getPolice() {
        return this.police;
    }

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasse() {
        return this.motPasse;
    }

    /**
     * Retourne le créateur de la fiche Véhicule.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la date de création de la fiche Véhicule
     *
     * @return Timestamp Valeur de création.
     * (yyyy-MM-dd).
     */
    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne le rôle.
     *
     * @return long Valeur du rôle.
     */
    public long getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return long Valeur du type de lien.
     */
    public long getTypeLien() {
        return this.typeLien;
    }


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caractère.
     */
    public void setSiteDossier(String siteDossier) {
        this.siteDossier = siteDossier;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque) {
        this.marque = marque;
    }

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle.
     */
    public void setModele(long modele) {
        this.modele = modele;
    }

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Affecte une année.
     *
     * @param annee Valeur de l'année en caractère.
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    /**
     * Affecte une vignette.
     *
     * @param vignette Valeur de la vignette en caractère.
     */
    public void setVignette(String vignette) {
        this.vignette = vignette;
    }

    /**
     * Affecte une date d'expiration de la vignette.
     *
     * @param dateExpirationVignette Valeur de la date d'expiration de la
     * vignette (yyyy-MM-dd).
     */
    public void setDateExpirationVignette(Timestamp dateExpirationVignette) {
        this.dateExpirationVignette = dateExpirationVignette;
    }

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série en caractère.
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * Affecte une assurance.
     *
     * @param assurance Valeur de l'assurance en caractère.
     */
    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    /**
     * Affecte une date d'expiration de l'assurance.
     *
     * @param dateExpirationAssurance Valeur de la date d'expiration de
     * l'assurance (yyyy-MM-dd).
     */
    public void setDateExpirationAssurance(Timestamp dateExpirationAssurance) {
        this.dateExpirationAssurance = dateExpirationAssurance;
    }

    /**
     * Affecte une police.
     *
     * @param police Valeur de la police en caractère.
     */
    public void setPolice(String police) {
        this.police = police;
    }

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caractère.
     */
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
   public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte une date de création de la fiche Véhicule.
     *
     * @param dateCreation Valeur de la date d'expiration de la
     * vignette (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle.
     */
    public void setRole(long role) {
        this.role = role;
    }

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien.
     */
    public void setTypeLien(long typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du VehiculeVO.
     *
     * @return String Valeur de tout les attributs du VehiculeVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[VehiculeVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("numeroDossier = '" + numeroDossier);
        stringBuffer.append("', siteDossier = '" + siteDossier);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', immatriculation = '" + immatriculation);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', cleProvince = '" + cleProvince);
        stringBuffer.append("', annee = '" + annee);
        stringBuffer.append("', vignette = '" + vignette);
        stringBuffer.append("', dateExpirationVignette = '"
                + TimestampFormat.format(dateExpirationVignette));
        stringBuffer.append("', numeroSerie = '" + numeroSerie);
        stringBuffer.append("', assurance = '" + assurance);
        stringBuffer.append("', commentaire = '" + commentaire);
        stringBuffer.append("', dateExpirationAssurance = '"
                + TimestampFormat.format(dateExpirationAssurance));
        stringBuffer.append("', police = '" + police);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', motPasse = '" + motPasse);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', dateCreation = '"
                + TimestampFormat.format(dateCreation));
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', typeLien = '" + typeLien);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the modeleMarque.
	 * @return long
	 */
	public long getModeleMarque() {
		return modeleMarque;
	}

	/**
	 * Sets the modeleMarque.
	 * @param modeleMarque The modeleMarque to set
	 */
	public void setModeleMarque(long modeleMarque) {
		this.modeleMarque = modeleMarque;
	}

	/**
	 * @return
	 */
	public String getLienCreateur() {
		return lienCreateur;
	}

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur) {
		this.lienCreateur = lienCreateur;
	}

	public long getEntite() {
		return entite;
	}

	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * @return dateChangement
	 */
	public Timestamp getDateChangement() {
		return dateChangement;
	}

	/**
	 * @param dateChangement dateChangement à définir
	 */
	public void setDateChangement(Timestamp dateChangement) {
		this.dateChangement = dateChangement;
	}

	/**
	 * @return changePar
	 */
	public String getChangePar() {
		return changePar;
	}

	/**
	 * @param changePar changePar à définir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
	}

	public String getConfirmationMotPasse() {
		return confirmationMotPasse;
	}

	public void setConfirmationMotPasse(String confirmationMotPasse) {
		this.confirmationMotPasse = confirmationMotPasse;
	}

	public Timestamp getLienDateCreation() {
		return lienDateCreation;
	}

	public void setLienDateCreation(Timestamp lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}

	public long getCleProvince() {
		return cleProvince;
	}

	public void setCleProvince(long cleProvince) {
		this.cleProvince = cleProvince;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Set<LiaisonEntiteVO> getLiaisonEntites() {
		return liaisonEntites;
	}

}