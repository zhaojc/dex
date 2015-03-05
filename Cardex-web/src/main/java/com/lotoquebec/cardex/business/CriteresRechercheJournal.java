package com.lotoquebec.cardex.business;
import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res n�cesssaires � la recherche 
 * dans le journal de surveillance.
 *
 * @author $Author: Fran�ois Gu�rin $
 * @version $Revision: 1.8 $, $Date: 2004/04/20 21:22:03 $
 */
public interface CriteresRechercheJournal extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine();

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
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine en caract�re.
     */
    public long getSite();

    /**
     * Retourne le genre.
     *
     * @return long Valeur du genre en caract�re.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur de nature en caract�re.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public long getType();

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur de la cat�gorie en caract�re.
     */
    public long getCategorie();

    /**
     * Retourne l'entit�.
     *
     * @return long Valeur de l'entit� en caract�re.
     */
    public long getEntite();

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
     * Retourne l'attribut fonde.
     *
     * @return long Valeur num�rique de l'attribut fonde.
     */
    public long getFonde();

    /**
     * Retourne la date de cr�ation du.
     *
     * @return Timestamp Valeur de la date de cr�ation du en caract�re.
     */
    public Timestamp getDateCreationDu();

    /**
     * Retourne la date de cr�ation au.
     *
     * @return Timestamp Valeur de la date de cr�ation au en caract�re.
     */
    public Timestamp getDateCreationAu();

    /**
     * Retourne la description du type (pour les rapports d�taill�s)..
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescriptionType();

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
     * Retourne l'endroit.
     *
     * @return long Valeur de l'endroit en caract�re.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caract�re.
     */
    public long getLocalisation();

    /**
     * Retourne le type de rapport.
     *
     * @return String Valeur du type de rapport en caract�re.
     */
    public String getTypeRapport();

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


    // Setters


    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param site Valeur du site d'origine en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte un genre.
     *
     * @param type Valeur du genre en caract�re.
     */
    public void setGenre(long genre);
    
    /**
     * Affecte une nature.
     *
     * @param nature Valeur de nature en caract�re.
     */
    public void setNature(long nature);
    
    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setType(long type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur d'une cat�gorie en caract�re.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un groupe.
     *
     * @param groupe Valeur du secteur en caract�re.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte une date de cr�ation du.
     *
     * @param dateCreationDu Valeur de la date de cr�ation du en caract�re.
     */
    public void setDateCreationDu(Timestamp dateCreationDu);

    /**
     * Affecte une date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au en caract�re.
     */
    public void setDateCreationAu(Timestamp dateCreationAu);

    /**
     * Affecte une description de type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setDescriptionType(String descriptionType);

    /**
     * Affecte le type de rapport.
     *
     * @param typeRapport Valeur du type de rapport en caract�re.
     */
    public void setTypeRapport(String typeRapport);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine);

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur num�rique de l'attribut fonde.
     */
    public void setFonde(long fonde);

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


}