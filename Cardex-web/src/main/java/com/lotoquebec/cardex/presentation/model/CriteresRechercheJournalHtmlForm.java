package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de journal.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/22 21:55:43 $
 */
public interface CriteresRechercheJournalHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne la description du lieu.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne le num�ro d'employ�.
     *
     * @return String Valeur du num�ro d'employ� en caract�re.
     */
    public String getNumeroEmploye();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSite();

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de nature en caract�re.
     */
    public String getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType();

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie();

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�re.
     */
    public String getReference3();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur de s�curit�.
     *
     * @return String Valeur du groupe en caract�re.
     */
    public String getSecteur();

    /**
     * Retourne l'attribut "fonde".
     *
     * @return String Valeur de l'attribut "fonde" en caract�re.
     */
    public String getFonde();

    /**
     * Retourne la date de cr�ation du.
     *
     * @return String Valeur de la date de cr�ation du en caract�re.
     */
    public String getDateCreationDu();

    /**
     * Retourne la date de cr�ation au.
     *
     * @return String Valeur de la date de cr�ation au en caract�re.
     */
    public String getDateCreationAu();

    /**
     * Retourne l'endroit.
     *
     * @return String Valeur de l'endroit en caract�re.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caract�re.
     */
    public String getLocalisation();

    /**
     * Retourne le type de rapport.
     *
     * @return String Valeur du type de rapport en caract�re.
     */
    public String getTypeRapport();

    /**
     * Retourne la description du type (pour les rapports d�taill�s)..
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescriptionType();

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
    public boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public String getMaximumResultatsRecherche();

    /**
     * Retourne une collection de journaux.
     *
     * @return Collection Valeur de la collection de journaux.
     */
    public Collection getJournaux();


    // Setters


    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param site Valeur du site d'origine en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte une description de type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setDescriptionType(String descriptionType);

    /**
     * Affecte un genre.
     *
     * @param type Valeur du genre en caract�re.
     */
    public void setGenre(String genre);
    
    /**
     * Affecte une nature.
     *
     * @param type Valeur de nature en caract�re.
     */
    public void setNature(String nature);
    
    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setType(String type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur d'une cat�gorie en caract�re.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

   /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur de l'attribut "fonde" en caract�re.
     */
    public void setFonde(String fonde);

    /**
     * Affecte un groupe de s�curit�.
     *
     * @param secteur Valeur du groupe en caract�re.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte une date de cr�ation du.
     *
     * @param dateCreationDu Valeur de la date de cr�ation du en caract�re.
     */
    public void setDateCreationDu(String dateCreationDu);

    /**
     * Affecte une date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au en caract�re.
     */
    public void setDateCreationAu(String dateCreationAu);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caract�re.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caract�re.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte une deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3);

    /**
     * Affecte le type de rapport.
     *
     * @param typeRapport Valeur du type de rapport en caract�re.
     */
    public void setTypeRapport(String typeRapport);

    /**
     * Affecte la description du lieu.
     *
     * @param descriptif Valeur de la description en caract�re.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte le num�ro d'employ�.
     *
     * @param numeroEmploye Valeur du num�ro d'employ� en caract�re.
     */
    public void setNumeroEmploye(String numeroEmploye);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(String origine);

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
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(String maximum);

    /**
     * Ajoute un journal.
     *
     * @param journal Valeur du journal � ajouter.
     */
    public void addJournal(JournalHtmlForm journal);

}