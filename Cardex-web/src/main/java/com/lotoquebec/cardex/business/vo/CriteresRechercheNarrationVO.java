package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la recherche d'une
 * narration de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.13 $, $Date: 2002/04/22 18:01:06 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheNarration
 */
public class CriteresRechercheNarrationVO
        implements CriteresRechercheNarration {

    private long entite = 0;
    private long site = 0;
    private long genre = 0;
    private long nature = 0;
    private long type = 0;
    private long categorie = 0;
    private long origine = 0;
    private long endroit = 0;
    private long localisation = 0;
    private long approuve = 0;
    private long secteur = 0;
    private long fonde = 0;
    private String intervenant = "";
    private Timestamp dateCreationDebut = null;
    private Timestamp dateApprobationDebut = null;
    private Timestamp dateCreationFin = null;
    private Timestamp dateApprobationFin = null;
    private String motCle1 = "";
    private String motCle2 = "";
    private String motCle3 = "";
    private long lien = 0;
    private long lienSite = 0;
    private String statutApprobation = "";
    private String typeRecherche = "";
    private String ordreAffichage = "";
    private int sequence = 0;

    /**
     * Constructeur de CriteresRechercheNarrationVO par défaut.
     */
    public CriteresRechercheNarrationVO() {}


    // Getters


    /**
     * Retourne l'approbation.
     *
     * @return long Valeur de l'approbation.
     */
    public long getApprouve() {
        return this.approuve;
    }

    /**
     * Retourne le secteur.
     *
     * @return long Valeur du secteur.
     */
    public long getSecteur() {
        return this.secteur;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne la date de création au début.
     *
     * @return Timestamp Valeur de la date de création au début (yyyy-MM-dd).
     */
    public Timestamp getDateCreationDebut() {
        return this.dateCreationDebut;
    }

    /**
     * Retourne la date d'approbation au début.
     *
     * @return Timestamp Valeur de la date d'approbation au début (yyyy-MM-dd).
     */
    public Timestamp getDateApprobationDebut() {
        return this.dateApprobationDebut;
    }

    /**
     * Retourne la date de création à la fin.
     *
     * @return Timestamp Valeur de la date de création à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateCreationFin() {
        return this.dateCreationFin;
    }

    /**
     * Retourne la date d'approbation à la fin.
     *
     * @return Timestamp Valeur de la date d'approbation à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateApprobationFin() {
        return this.dateApprobationFin;
    }

    /**
     * Retourne le premier mot clé.
     *
     * @return String Valeur du premier mot clé en caractère.
     */
    public String getMotCle1() {
        return this.motCle1;
    }

    /**
     * Retourne le deuxième mot clé.
     *
     * @return String Valeur du deuxième mot clé en caractère.
     */
    public String getMotCle2() {
        return this.motCle2;
    }

    /**
     * Retourne le troisième mot clé.
     *
     * @return String Valeur du troisième mot clé en caractère.
     */
    public String getMotCle3() {
        return this.motCle3;
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
     * Retourne le critère d'approbation.
     *
     * @return String Valeur du critère d'approbation dans l'écran de recherche
     * en caractère.
     */
    public String getStatutApprobation() {
        return this.statutApprobation;
    }

    // Setters


    /**
     * Affecte une approbation.
     *
     * @param approuve Valeur de l'approbation.
     */
    public void setApprouve(long approuve) {
        this.approuve = approuve;
    }

    /**
     * Affecte un secteur.
     *
     * @param secteur Valeur du secteur.
     */
    public void setSecteur(long secteur) {
        this.secteur = secteur;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte une date de création au début.
     *
     * @param dateCreationDebut Valeur de la date de création au début
     * (yyyy-MM-dd).
     */
    public void setDateCreationDebut(Timestamp dateCreationDebut) {
        this.dateCreationDebut = dateCreationDebut;
    }

    /**
     * Affecte une date d'approbation au début.
     *
     * @param dateApprobationDebut Valeur de la date d'approbation au début
     * (yyyy-MM-dd).
     */
    public void setDateApprobationDebut(Timestamp dateApprobationDebut) {
        this.dateApprobationDebut = dateApprobationDebut;
    }

    /**
     * Affecte une date de création à la fin.
     *
     * @param dateCreationFin Valeur de la date de création à la fin
     * (yyyy-MM-dd).
     */
    public void setDateCreationFin(Timestamp dateCreationFin) {
        this.dateCreationFin = dateCreationFin;
    }

    /**
     * Affecte une date d'approbation à la fin.
     *
     * @param dateApprobationFin Valeur de la date d'approbation à la fin
     * (yyyy-MM-dd).
     */
    public void setDateApprobationFin(Timestamp dateApprobationFin) {
        this.dateApprobationFin = dateApprobationFin;
    }

    /**
     * Affecte un premier mot clé.
     *
     * @param motCle1 Valeur du premier mot clé en caractère.
     */
    public void setMotCle1(String motCle1) {
        this.motCle1 = motCle1;
    }

    /**
     * Affecte un deuxième mot clé.
     *
     * @param motCle2 Valeur du deuxième mot clé en caractère.
     */
    public void setMotCle2(String motCle2) {
        this.motCle2 = motCle2;
    }

    /**
     * Affecte un troisième mot clé.
     *
     * @param motCle3 Valeur du troisième mot clé en caractère.
     */
    public void setMotCle3(String motCle3) {
        this.motCle3 = motCle3;
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
     * Affecte un critère d'approbation.
     *
     * @param statut Valeur du critère d'approbation dans l'écran de recherche
     * en caractère.
     */
    public void setStatutApprobation(String statutApprobation) {
        this.statutApprobation = statutApprobation;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheNarrationVO.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheNarrationVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheNarrationVO : ");
        stringBuffer.append("approuve = '" + approuve);
        stringBuffer.append("', secteur = '" + secteur);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', dateCreationDebut = '"
                    + TimestampFormat.format(dateCreationDebut));
        stringBuffer.append("', dateApprobationDebut = '"
                    + TimestampFormat.format(dateApprobationDebut));
        stringBuffer.append("', dateCreationFin = '"
                    + TimestampFormat.format(dateCreationFin));
        stringBuffer.append("', dateApprobationFin = '"
                    + TimestampFormat.format(dateApprobationFin));
        stringBuffer.append("', motCle1 = '" + motCle1);
        stringBuffer.append("', motCle2 = '" + motCle2);
        stringBuffer.append("', motCle3 = '" + motCle3);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', statutApprobation = '" + statutApprobation);

        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the typeRecherche.
	 * @return String
	 */
	public String getTypeRecherche() {
		return typeRecherche;
	}

	/**
	 * Sets the typeRecherche.
	 * @param typeRecherche The typeRecherche to set
	 */
	public void setTypeRecherche(String typeRecherche) {
		this.typeRecherche = typeRecherche;
	}

	/**
	 * @return Returns the categorie.
	 */
	public long getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie The categorie to set.
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return Returns the endroit.
	 */
	public long getEndroit() {
		return endroit;
	}
	/**
	 * @param endroit The endroit to set.
	 */
	public void setEndroit(long endroit) {
		this.endroit = endroit;
	}
	/**
	 * @return Returns the entite.
	 */
	public long getEntite() {
		return entite;
	}
	/**
	 * @param entite The entite to set.
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}
	/**
	 * @return Returns the genre.
	 */
	public long getGenre() {
		return genre;
	}
	/**
	 * @param genre The genre to set.
	 */
	public void setGenre(long genre) {
		this.genre = genre;
	}
	/**
	 * @return Returns the localisation.
	 */
	public long getLocalisation() {
		return localisation;
	}
	/**
	 * @param localisation The localisation to set.
	 */
	public void setLocalisation(long localisation) {
		this.localisation = localisation;
	}
	/**
	 * @return Returns the nature.
	 */
	public long getNature() {
		return nature;
	}
	/**
	 * @param nature The nature to set.
	 */
	public void setNature(long nature) {
		this.nature = nature;
	}
	/**
	 * @return Returns the origine.
	 */
	public long getOrigine() {
		return origine;
	}
	/**
	 * @param origine The origine to set.
	 */
	public void setOrigine(long origine) {
		this.origine = origine;
	}
	/**
	 * @return Returns the site.
	 */
	public long getSite() {
		return site;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(long site) {
		this.site = site;
	}
	/**
	 * @return Returns the type.
	 */
	public long getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(long type) {
		this.type = type;
	}
	public String getOrdreAffichage() {
		return ordreAffichage;
	}
	public void setOrdreAffichage(String ordreAffichage) {
		this.ordreAffichage = ordreAffichage;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	/**
	 * @return fonde
	 */
	public long getFonde() {
		return fonde;
	}


	/**
	 * @param fonde fonde à définir
	 */
	public void setFonde(long fonde) {
		this.fonde = fonde;
	}
	
}