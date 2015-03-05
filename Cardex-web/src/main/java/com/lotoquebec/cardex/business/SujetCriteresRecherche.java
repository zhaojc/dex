package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères communs et nécesssaires à la recherche de sujet.
 *
 */
public interface SujetCriteresRecherche extends CriteresRecherche
{

    // Getters

    /**
     * Retourne l'âge.
     *
     * @return String Valeur de l'âge en caractère.
     */
    public long getAge();

    /**
     * Retourne l'indicateur d'âge estimé.
     *
     * @return Boolean Indicateur d'âge estimé.
     */
    public Boolean isAgeEstime();
    
    /**
     * Retourne l'indicateur d'âge réel.
     *
     * @return Boolean Indicateur d'âge réel.
     */
    public Boolean isAgeReel();
    
    /**
     * Retourne l'indicateur d'âge réel plus ou 
     * moins 5 ans.
     *
     * @return Boolean Indicateur d'âge estimé 
     * plus ou moins 5 ans.
     */
    public Boolean isAgeReelPlusMoins();
    

    /**
     * Retourne l'indicateur d'âge inconnu
     *
     * @return Boolean Indicateur d'âge inconnu.
     */
    public Boolean isAgeInconnu();


    // Setters

    /**
     * Affecte l'âge.
     *
     * @param age Valeur de l'âge.
     */
    public void setAge(long age);
 
    /**
     * Affecte l'âge estimé.
     *
     * @param ageEstime indicateur d'âge estimé.
     */
    public void setAgeEstime(Boolean ageEstime);
    
    
    /**
     * Affecte l'âge réel.
     *
     * @param ageReel indicateur d'âge réel.
     */
    public void setAgeReel(Boolean ageReel);
    
    /**
     * Affecte l'âge réel plus ou moins 5 ans.
     *
     * @param ageReelPlusMoins indicateur d'âge réel
     * plus ou moins 5 ans.
     */
    public void setAgeReelPlusMoins(Boolean ageReelPlusMoins);

    /**
     * Affecte l'âge inconnu.
     *
     * @param ageInconnu indicateur d'âge inconnu
     */
    public void setAgeInconnu(Boolean ageInconnu);
    
}
