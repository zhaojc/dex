package com.lotoquebec.cardex.presentation.model;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation d'une inscription.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/13 17:50:54 $
 */
public interface InscriptionHtmlForm {


    // Getters

    /**
     * Retourne l'entite.
     *
     * @return String Valeur de l'entite.
     */
    public String getEntite();

    /**
     * Retourne la createur.
     *
     * @return String Valeur du createur.
     */
    public String getCreateur();

    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne la date d'inscription.
     *
     * @return String Valeur de la date d'inscription en caractère.
     */
    public String getDateInscription();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree();

    /**
     * Retourne la date de début.
     *
     * @return String Valeur de la date de début en caractère.
     */
    public String getDateDebut();

    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caractère.
     */
    public String getDateFin();

    /**
     * Retourne la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getPeriode();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

    /**
     * Retourne la date du courriel d'aide.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateCourrielAide();

    /**
     * Retourne la date du courriel de suivi.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateCourrielSuivi();

    /**
     * Retourne la date de rencontre initiale.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateRencontreInitiale();

    /**
     * Retourne la date de rencontre finale.
     *
     * @return String Valeur de la date de rencontre en caractère.
     */
    public String getDateRencontreFinale();

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
     * Retourne la collection de sites choisis.
     *
     * @return String [] Valeur de la liste de chaîne de caractère de sites
     * choisis.
     */
    public String [] getSitesChoisis();


    


    // Setters

    /**
     * Affecte une entite.
     *
     * @param createur Valeur de l'entite.
     */
    public void setEntite(String entite);

    /**
     * Affecte un createur.
     *
     * @param createur Valeur du createur.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte une date d'inscription.
     *
     * @param dateInscription Valeur de la date d'inscription en caractère.
     */
    public void setDateInscription(String dateInscription);

    /**
     * Affecte une durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree);

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de la date de début en caractère.
     */
    public void setDateDebut(String dateDebut);

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin en caractère.
     */
    public void setDateFin(String dateFin);

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setPeriode(String periode);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte une collection de sites choisis.
     *
     * @param sitesChoisis Valeur de la liste de chaîne de caractère de sites
     * choisis.
     */
    public void setSitesChoisis(String [] sitesChoisis);

	
	public String getAideImmediate();
	
	public void setAideImmediate(String aideImmediate);
	
	public String getAideInitiale();
	
	public void setAideInitiale(String aideInitiale);

    /**
     * Affecte la date du courriel d'aide.
     *
     * @param dateCourrielAide Valeur de la date en caractère.
     */
    public void setDateCourrielAide(String dateCourrielAide);

    /**
     * Affecte la date du courriel de suivi.
     *
     * @param dateCourrielSuivi Valeur de la date en caractère.
     */
    public void setDateCourrielSuivi(String dateCourrielSuivi);

    /**
     * Affecte la date de rencontre initiale.
     *
     * @param dateRencontreInitiale Valeur de la date en caractère.
     */
    public void setDateRencontreInitiale(String dateRencontreInitiale);

    /**
     * Affecte la date de rencontre finale.
     *
     * @param dateRencontreFinale Valeur de la date de rencontre en caractère.
     */
    public void setDateRencontreFinale(String dateRencontreFinale);

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

	
}