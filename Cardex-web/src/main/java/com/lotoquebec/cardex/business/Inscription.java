package com.lotoquebec.cardex.business;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives aux inscriptions.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/03/12 21:39:17 $
 */
public interface Inscription {


    // Getters

    /**
     * Retourne la createur.
     *
     * @return String Valeur du createur.
     */
    public String getCreateur();

    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

    /**
     * Retourne la date d'inscription.
     *
     * @return Timestamp Valeur de la date d'inscription.
     */
    public Timestamp getDateInscription();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree();

    /**
     * Retourne la date de début.
     *
     * @return Timestamp Valeur de la date de début.
     */
    public Timestamp getDateDebut();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin.
     */
    public Timestamp getDateFin();

    /**
     * Retourne la période.
     *
     * @return long Valeur de la période.
     */
    public long getPeriode();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

    /**
     * Retourne l'entité.
     *
     * @return long Valeur du lien de l'entité.
     */
    public long getEntite();

    /**
     * Retourne la date du courriel d'aide.
     *
     * @return String Valeur de la date en caractère.
     */
    public Timestamp getDateCourrielAide();

    /**
     * Retourne la date du courriel de suivi.
     *
     * @return Timestamp Valeur de la date en caractère.
     */
    public Timestamp getDateCourrielSuivi();

    /**
     * Retourne la date de rencontre initiale.
     *
     * @return Timestamp Valeur de la date en caractère.
     */
    public Timestamp getDateRencontreInitiale();

    /**
     * Retourne la date de rencontre finale.
     *
     * @return Timestamp Valeur de la date de rencontre en caractère.
     */
    public Timestamp getDateRencontreFinale();

    /**
     * Retourne l'intervenant de la rencontre initiale.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenantRencontreInitiale();

    /**
     * Retourne l'intervenant de la rencontre finale.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenantRencontreFinale();

    /**
     * Retourne la liste de sites choisis.
     *
     * @return Collection Valeur de la liste de sites choisis.
     */
    public Collection getSitesChoisis();

    /**
     * Retourne le lien de l'aide (sert à indiquer si une aide est requise par le sujet
     * qui demande une autoexclusion).
     *
     * @return String Valeur du lien de l'aide en caractère.
     */
    public String getAide();


    // Setters

    /**
     * Affecte un createur.
     *
     * @param createur Valeur du createur.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

    /**
     * Affecte une date d'inscription.
     *
     * @param dateInscription Valeur de la date d'inscription.
     */
    public void setDateInscription(Timestamp dateInscription);

    /**
     * Affecte une durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree);

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de la date de début.
     */
    public void setDateDebut(Timestamp dateDebut);

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin.
     */
    public void setDateFin(Timestamp dateFin);

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période.
     */
    public void setPeriode(long periode);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte la date du courriel d'aide.
     *
     * @param dateCourrielAide Valeur de la date en caractère.
     */
    public void setDateCourrielAide(Timestamp dateCourrielAide);

    /**
     * Affecte la date du courriel de suivi.
     *
     * @param dateCourrielSuivi Valeur de la date en caractère.
     */
    public void setDateCourrielSuivi(Timestamp dateCourrielSuivi);

    /**
     * Affecte la date de rencontre initiale.
     *
     * @param dateRencontreInitiale Valeur de la date en caractère.
     */
    public void setDateRencontreInitiale(Timestamp dateRencontreInitiale);

    /**
     * Affecte la date de rencontre finale.
     *
     * @param dateRencontreFinale Valeur de la date de rencontre en caractère.
     */
    public void setDateRencontreFinale(Timestamp dateRencontreFinale);

    /**
     * Affecte l'intervenant de la rencontre initiale.
     *
     * @param intervenantRencontreInitiale Valeur de l'intervenant en caractère.
     */
    public void setIntervenantRencontreInitiale(String intervenantRencontreInitiale);

    /**
     * Retourne l'intervenant de la rencontre finale.
     *
     * @param intervenantRencontreFinale Valeur de l'intervenant en caractère.
     */
    public void setIntervenantRencontreFinale(String intervenantRencontreFinale);

    /**
     * Affecte l'entité.
     *
     * @param entite Valeur du lien de l'entité.
     */
    public void setEntite(long entite);

    /**
     * Ajoute un site.
     *
     * @param site Valeur du site.
     */
    public void addSite(String site);

    /**
     * Affecte un lien de l'aide.
     *
     * @param lienSite Valeur du lien de l'aide en caractère.
     */
    public void setAide(String aide);

	public Boolean isAideImmediate();
	
	public void setAideImmediate(Boolean aideImmediate);
	
	public Boolean isAideInitiale();
	
	public void setAideInitiale(Boolean aideInitiale);

	public Boolean isTousSitesApplicables();
	
	public void setTousSitesApplicables(Boolean tousSitesApplicables);
	
}