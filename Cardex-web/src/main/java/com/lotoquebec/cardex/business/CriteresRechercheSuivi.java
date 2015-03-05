package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res, n�cesssaires � la recherche d'un
 * suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:40:38 $
 */
public interface CriteresRechercheSuivi extends CriteresRecherche{


    /**
     * Retourne l'activit�.
     *
     * @return long Valeur de l'activit�.
     */
    public long getActivite();

    /**
     * Affecte une activit�.
     *
     * @param activite Valeur de l'activit�.
     */
    public void setActivite(long activite);

    /**
     * Retourne le statut approbation.
     *
     * @return String Valeur du statut approbation.
     */
    public String getStatutApprobation();

    /**
     * Affecte un statut approbation.
     *
     * @param statutApprobation Valeur du statut approbation.
     */
    public void setStatutApprobation(String statutApprobation);

    /**
     * Retourne le statut suivi.
     *
     * @return String Valeur du statut suivi.
     */
    public String getStatutSuivi();

    /**
     * Affecte un statut suivi.
     *
     * @param statutSuivi Valeur du statut suivi.
     */
    public void setStatutSuivi(String statutSuivi);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();


    /**
     * Retourne le secteur assign�.
     *
     * @return String Valeur du secteur assign� en caract�re.
     */
    public long getSecteurAssigne();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caract�re.
     */
    public long getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur();

    /**
     * Retourne la date d'�mission au d�but.
     *
     * @return Timestamp Valeur de la date d'�mission au d�but (yyyy-MM-dd).
     */
    public Timestamp getDateEmisDebut();

    /**
     * Retourne la date pr�vue au d�but.
     *
     * @return Timestamp Valeur de la date pr�vue au d�but (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueDebut();

    /**
     * Retourne la date compl�t�e au d�but.
     *
     * @return Timestamp Valeur de la date compl�t�e au d�but (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeDebut();

    /**
     * Retourne la date d'�mission � la fin.
     *
     * @return Timestamp Valeur de la date d'�mission � la fin (yyyy-MM-dd).
     */
    public Timestamp getDateEmisFin();

    /**
     * Retourne la date pr�vue � la fin.
     *
     * @return Timestamp Valeur de la date pr�vue � la fin (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueFin();

    /**
     * Retourne la date compl�t�e � la fin.
     *
     * @return Timestamp Valeur de la date compl�t�e � la fin (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeFin();

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
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public long getMaximumResultatsRecherche();

    /**
     * Retourne l'entit�.
     *
     * @return long Valeur de l'entit� en caract�re.
     */
    public long getEntite();
    
    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine en caract�re.
     */
    public long getSiteOrigine();


    // Setters


    /**
     * Affecte un secteur assign�.
     *
     * @param secteurAssigne Valeur du secteur assign� en caract�re.
     */
    public void setSecteurAssigne(long secteurAssigne);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);
    
    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caract�re.
     */
    public void setSecteurOrigine(long secteurOrigine);

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caract�re.
     */
    public void setDemandeur(String demandeur);

    /**
     * Affecte une date d'�mission au d�but.
     *
     * @param dateEmisDebut Valeur de la date d'�mission au d�but (yyyy-MM-dd).
     */
    public void setDateEmisDebut(Timestamp dateEmisDebut);

    /**
     * Affecte une date pr�vue au d�but.
     *
     * @param datePrevueDebut Valeur de la date pr�vue au d�but (yyyy-MM-dd).
     */
    public void setDatePrevueDebut(Timestamp datePrevueDebut);

    /**
     * Affecte une date compl�t�e au d�but.
     *
     * @param dateCompleteeDebut Valeur de la date compl�t�e au d�but
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeDebut(Timestamp dateCompleteeDebut);

    /**
     * Affecte une date d'�mission � la fin.
     *
     * @param dateEmisFin Valeur de la date d'�mission � la fin (yyyy-MM-dd).
     */
    public void setDateEmisFin(Timestamp dateEmisFin);

    /**
     * Affecte une date pr�vue � la fin.
     *
     * @param datePrevueFin Valeur de la date pr�vue � la fin (yyyy-MM-dd).
     */
    public void setDatePrevueFin(Timestamp datePrevueFin);

    /**
     * Affecte une date compl�t�e � la fin.
     *
     * @param dateCompleteeFin Valeur de la date compl�t�e � la fin
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeFin(Timestamp dateCompleteeFin);

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
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximum);

    /**
     * Affecte le crit�re d'entit�.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setEntite(long entite);

    /**
     * Affecte le crit�re du site.
     *
     * @param lien Valeur du site en caract�re.
     */
    public void setSiteOrigine(long siteOrigine);


}