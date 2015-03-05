package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * inscription de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/03/12 21:39:25 $
 * @see com.lotoquebec.cardex.business.Inscription
 */
public class InscriptionVO implements Inscription {

    private long cle = 0;
    private long site = 0;
    private Timestamp dateInscription = null;
    private String duree = "";
    private String createur = "";
    private Timestamp dateDebut = null;
    private Timestamp dateFin = null;
    private long periode = 0;
    private long statut = 0;
    private long lien = 0;
    private long lienSite = 0;
    private long entite = 0;
    private String aide = "on"; //Oui par défaut
    private Boolean aideInitiale = false;
    private Boolean aideImmediate = false;
    private Timestamp dateCourrielAide = null;
    private Timestamp dateCourrielSuivi = null;
    private Timestamp dateRencontreInitiale = null;
    private Timestamp dateRencontreFinale = null;
    private String intervenantRencontreInitiale = "";
    private String intervenantRencontreFinale = "";
    private ArrayList sitesChoisis = new ArrayList();
    private Boolean tousSitesApplicables = false;

    /**
     * Constructeur de InscriptionVO par défaut.
     */
    public InscriptionVO() {}


    // Getters


    /**
     * Retourne la createur.
     *
     * @return String Valeur du createur.
     */
    public String getCreateur() {
        return this.createur;
    }

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
     * Retourne la date d'inscription.
     *
     * @return Timestamp Valeur de la date d'inscription.
     */
    public Timestamp getDateInscription() {
        return this.dateInscription;
    }

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree() {
        return this.duree;
    }

    /**
     * Retourne la date de début.
     *
     * @return Timestamp Valeur de la date de début.
     */
    public Timestamp getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin.
     */
    public Timestamp getDateFin() {
        return this.dateFin;
    }

    /**
     * Retourne la période.
     *
     * @return long Valeur de la période.
     */
    public long getPeriode() {
        return this.periode;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut() {
        return this.statut;
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
     * Retourne la liste de sites choisis.
     *
     * @return String [] Valeur de la liste de sites choisis.
     */
    public Collection getSitesChoisis() {
        return this.sitesChoisis;
    }


    // Setters


    /**
     * Affecte un createur.
     *
     * @param createur Valeur du createur.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

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
     * Affecte une date d'inscription.
     *
     * @param dateInscription Valeur de la date d'inscription.
     */
    public void setDateInscription(Timestamp dateInscription) {
        this.dateInscription = dateInscription;
    }

    /**
     * Affecte une durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree) {
        this.duree = duree;
    }

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de la date de début.
     */
    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin.
     */
    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période.
     */
    public void setPeriode(long periode) {
        this.periode = periode;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
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
     * Ajoute un site.
     *
     * @param site Valeur du site.
     */
    public void addSite(String site) {
        this.sitesChoisis.add(site);
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du InscriptionVO.
     *
     * @return String Valeur de tout les attributs du InscriptionVO en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[InscriptionVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', dateInscription = '"
                    + TimestampFormat.format(dateInscription));
        stringBuffer.append("', duree = '" + duree);
        stringBuffer.append("', dateDebut = '"
                    + TimestampFormat.format(dateDebut));
        stringBuffer.append("', dateFin = '"
                    + TimestampFormat.format(dateFin));
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', sitesChoisis = (");
        for ( int i = 0; i < sitesChoisis.size(); i++ ) {
            stringBuffer.append("'" + sitesChoisis.get(i) + "',");
        }
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

	/**
	 * Returns the aide.
	 * @return long
	 */
	public String getAide() {
		return aide;
	}

	/**
	 * Sets the aide.
	 * @param aide The aide to set
	 */
	public void setAide(String aide) {
		this.aide = aide;
	}

	public Boolean isAideImmediate() {
		return aideImmediate;
	}
	public void setAideImmediate(Boolean aideImmediate) {
		this.aideImmediate = aideImmediate;
	}
	public Boolean isAideInitiale() {
		return aideInitiale;
	}
	public void setAideInitiale(Boolean aideInitiale) {
		this.aideInitiale = aideInitiale;
	}
	/**
	 * @return Returns the dateCourrielAide.
	 */
	public Timestamp getDateCourrielAide() {
		return dateCourrielAide;
	}
	/**
	 * @param dateCourrielAide The dateCourrielAide to set.
	 */
	public void setDateCourrielAide(Timestamp dateCourrielAide) {
		this.dateCourrielAide = dateCourrielAide;
	}
	/**
	 * @return Returns the dateCourrielSuivi.
	 */
	public Timestamp getDateCourrielSuivi() {
		return dateCourrielSuivi;
	}
	/**
	 * @param dateCourrielSuivi The dateCourrielSuivi to set.
	 */
	public void setDateCourrielSuivi(Timestamp dateCourrielSuivi) {
		this.dateCourrielSuivi = dateCourrielSuivi;
	}
	/**
	 * @return Returns the dateRencontreFinale.
	 */
	public Timestamp getDateRencontreFinale() {
		return dateRencontreFinale;
	}
	/**
	 * @param dateRencontreFinale The dateRencontreFinale to set.
	 */
	public void setDateRencontreFinale(Timestamp dateRencontreFinale) {
		this.dateRencontreFinale = dateRencontreFinale;
	}
	/**
	 * @return Returns the dateRencontreInitiale.
	 */
	public Timestamp getDateRencontreInitiale() {
		return dateRencontreInitiale;
	}
	/**
	 * @param dateRencontreInitiale The dateRencontreInitiale to set.
	 */
	public void setDateRencontreInitiale(Timestamp dateRencontreInitiale) {
		this.dateRencontreInitiale = dateRencontreInitiale;
	}
	/**
	 * @return Returns the intervenantRencontreFinale.
	 */
	public String getIntervenantRencontreFinale() {
		return intervenantRencontreFinale;
	}
	/**
	 * @param intervenantRencontreFinale The intervenantRencontreFinale to set.
	 */
	public void setIntervenantRencontreFinale(String intervenantRencontreFinale) {
		this.intervenantRencontreFinale = intervenantRencontreFinale;
	}
	/**
	 * @return Returns the intervenantRencontreInitiale.
	 */
	public String getIntervenantRencontreInitiale() {
		return intervenantRencontreInitiale;
	}
	/**
	 * @param intervenantRencontreInitiale The intervenantRencontreInitiale to set.
	 */
	public void setIntervenantRencontreInitiale(
			String intervenantRencontreInitiale) {
		this.intervenantRencontreInitiale = intervenantRencontreInitiale;
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

	public Boolean isTousSitesApplicables() {
		return tousSitesApplicables;
	}
	
	public void setTousSitesApplicables(Boolean tousSitesApplicables) {
		this.tousSitesApplicables = tousSitesApplicables;
	}


	public Boolean getAideInitiale() {
		return aideInitiale;
	}


	public Boolean getAideImmediate() {
		return aideImmediate;
	}


	public Boolean getTousSitesApplicables() {
		return tousSitesApplicables;
	}


	/* devrait être présent
	 * public void setSitesChoisis(ArrayList sitesChoisis) {
		this.sitesChoisis = sitesChoisis;
	}*/
	
}