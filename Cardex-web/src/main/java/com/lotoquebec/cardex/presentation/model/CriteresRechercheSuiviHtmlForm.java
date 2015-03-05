package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:42:39 $
 */
public interface CriteresRechercheSuiviHtmlForm extends CriteresRecherche{

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

    // Getters


    /**
     * Retourne le secteur assign�.
     *
     * @return String Valeur du secteur assign� en caract�re.
     */
    public String getSecteurAssigne();

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
    public String getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur();

    /**
     * Retourne l'activit�.
     *
     * @return String Valeur de l'activit�.
     */
    public String getActivite();

    /**
     * Affecte une activit�.
     *
     * @param activite Valeur de l'activit� en caract�re.
     */
    public void setActivite(String activite);

    /**
     * Retourne la date d'�mission au d�but.
     *
     * @return String Valeur de la date d'�mission au d�but en caract�re.
     */
    public String getDateEmisDebut();

    /**
     * Retourne la date pr�vue au d�but.
     *
     * @return String Valeur de la date pr�vue au d�but en caract�re.
     */
    public String getDatePrevueDebut();

    /**
     * Retourne la date compl�t�e au d�but.
     *
     * @return String Valeur de la date compl�t�e au d�but en caract�re.
     */
    public String getDateCompleteeDebut();

    /**
     * Retourne la date d'�mission � la fin.
     *
     * @return String Valeur de la date d'�mission � la fin en caract�re.
     */
    public String getDateEmisFin();

    /**
     * Retourne la date pr�vue � la fin.
     *
     * @return String Valeur de la date pr�vue � la fin en caract�re.
     */
    public String getDatePrevueFin();

    /**
     * Retourne la date compl�t�e � la fin.
     *
     * @return String Valeur de la date compl�t�e � la fin en caract�re).
     */
    public String getDateCompleteeFin();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite();
    
    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine();

    /**
     * Retourne une collection de suivis.
     *
     * @return Collection Valeur de la collection de suivis.
     */
    public Collection getSuivis();


    // Setters


    /**
     * Affecte un secteur assign�.
     *
     * @param secteurAssigne Valeur du secteur assign� en caract�re.
     */
    public void setSecteurAssigne(String secteurAssigne);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caract�re.
     */
    public void setSecteurOrigine(String secteurOrigine);

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caract�re.
     */
    public void setDemandeur(String demandeur);

    /**
     * Affecte une date d'�mission au d�but.
     *
     * @param dateEmisDebut Valeur de la date d'�mission au d�but en caract�re.
     */
    public void setDateEmisDebut(String dateEmisDebut);

    /**
     * Affecte une date pr�vue au d�but.
     *
     * @param datePrevueDebut Valeur de la date pr�vue au d�but en caract�re.
     */
    public void setDatePrevueDebut(String datePrevueDebut);

    /**
     * Affecte une date compl�t�e au d�but.
     *
     * @param dateCompleteeDebut Valeur de la date compl�t�e au d�but en
     * caract�re.
     */
    public void setDateCompleteeDebut(String dateCompleteeDebut);

    /**
     * Affecte une date d'�mission � la fin.
     *
     * @param dateEmisFin Valeur de la date d'�mission � la fin en caract�re.
     */
    public void setDateEmisFin(String dateEmisFin);

    /**
     * Affecte une date pr�vue � la fin.
     *
     * @param datePrevueFin Valeur de la date pr�vue � la fin en caract�re.
     */
    public void setDatePrevueFin(String datePrevueFin);

    /**
     * Affecte une date compl�t�e � la fin.
     *
     * @param dateCompleteeFin Valeur de la date compl�t�e � la fin en
     * caract�re.
     */
    public void setDateCompleteeFin(String dateCompleteeFin);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);


    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

    /**
     * Ajoute un suivi.
     *
     * @param suivi Valeur de la suivi � ajouter.
     */
    public void addSuivi(SuiviHtmlForm suivi);

    /**
     * Affecte le crit�re d'entit�.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte le crit�re du site.
     *
     * @param lien Valeur du site en caract�re.
     */
    public void setSiteOrigine(String siteOrigine);

    
}