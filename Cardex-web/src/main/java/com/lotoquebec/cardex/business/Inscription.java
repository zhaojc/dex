package com.lotoquebec.cardex.business;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
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
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public String getDuree();

    /**
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date de d�but.
     */
    public Timestamp getDateDebut();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin.
     */
    public Timestamp getDateFin();

    /**
     * Retourne la p�riode.
     *
     * @return long Valeur de la p�riode.
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
     * Retourne l'entit�.
     *
     * @return long Valeur du lien de l'entit�.
     */
    public long getEntite();

    /**
     * Retourne la date du courriel d'aide.
     *
     * @return String Valeur de la date en caract�re.
     */
    public Timestamp getDateCourrielAide();

    /**
     * Retourne la date du courriel de suivi.
     *
     * @return Timestamp Valeur de la date en caract�re.
     */
    public Timestamp getDateCourrielSuivi();

    /**
     * Retourne la date de rencontre initiale.
     *
     * @return Timestamp Valeur de la date en caract�re.
     */
    public Timestamp getDateRencontreInitiale();

    /**
     * Retourne la date de rencontre finale.
     *
     * @return Timestamp Valeur de la date de rencontre en caract�re.
     */
    public Timestamp getDateRencontreFinale();

    /**
     * Retourne l'intervenant de la rencontre initiale.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenantRencontreInitiale();

    /**
     * Retourne l'intervenant de la rencontre finale.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenantRencontreFinale();

    /**
     * Retourne la liste de sites choisis.
     *
     * @return Collection Valeur de la liste de sites choisis.
     */
    public Collection getSitesChoisis();

    /**
     * Retourne le lien de l'aide (sert � indiquer si une aide est requise par le sujet
     * qui demande une autoexclusion).
     *
     * @return String Valeur du lien de l'aide en caract�re.
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
     * Affecte une dur�e.
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(String duree);

    /**
     * Affecte une date de d�but.
     *
     * @param dateDebut Valeur de la date de d�but.
     */
    public void setDateDebut(Timestamp dateDebut);

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin.
     */
    public void setDateFin(Timestamp dateFin);

    /**
     * Affecte une p�riode.
     *
     * @param periode Valeur de la p�riode.
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
     * @param dateCourrielAide Valeur de la date en caract�re.
     */
    public void setDateCourrielAide(Timestamp dateCourrielAide);

    /**
     * Affecte la date du courriel de suivi.
     *
     * @param dateCourrielSuivi Valeur de la date en caract�re.
     */
    public void setDateCourrielSuivi(Timestamp dateCourrielSuivi);

    /**
     * Affecte la date de rencontre initiale.
     *
     * @param dateRencontreInitiale Valeur de la date en caract�re.
     */
    public void setDateRencontreInitiale(Timestamp dateRencontreInitiale);

    /**
     * Affecte la date de rencontre finale.
     *
     * @param dateRencontreFinale Valeur de la date de rencontre en caract�re.
     */
    public void setDateRencontreFinale(Timestamp dateRencontreFinale);

    /**
     * Affecte l'intervenant de la rencontre initiale.
     *
     * @param intervenantRencontreInitiale Valeur de l'intervenant en caract�re.
     */
    public void setIntervenantRencontreInitiale(String intervenantRencontreInitiale);

    /**
     * Retourne l'intervenant de la rencontre finale.
     *
     * @param intervenantRencontreFinale Valeur de l'intervenant en caract�re.
     */
    public void setIntervenantRencontreFinale(String intervenantRencontreFinale);

    /**
     * Affecte l'entit�.
     *
     * @param entite Valeur du lien de l'entit�.
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
     * @param lienSite Valeur du lien de l'aide en caract�re.
     */
    public void setAide(String aide);

	public Boolean isAideImmediate();
	
	public void setAideImmediate(Boolean aideImmediate);
	
	public Boolean isAideInitiale();
	
	public void setAideInitiale(Boolean aideInitiale);

	public Boolean isTousSitesApplicables();
	
	public void setTousSitesApplicables(Boolean tousSitesApplicables);
	
}