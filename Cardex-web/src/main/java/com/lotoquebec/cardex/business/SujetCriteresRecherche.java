package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res communs et n�cesssaires � la recherche de sujet.
 *
 */
public interface SujetCriteresRecherche extends CriteresRecherche
{

    // Getters

    /**
     * Retourne l'�ge.
     *
     * @return String Valeur de l'�ge en caract�re.
     */
    public long getAge();

    /**
     * Retourne l'indicateur d'�ge estim�.
     *
     * @return Boolean Indicateur d'�ge estim�.
     */
    public Boolean isAgeEstime();
    
    /**
     * Retourne l'indicateur d'�ge r�el.
     *
     * @return Boolean Indicateur d'�ge r�el.
     */
    public Boolean isAgeReel();
    
    /**
     * Retourne l'indicateur d'�ge r�el plus ou 
     * moins 5 ans.
     *
     * @return Boolean Indicateur d'�ge estim� 
     * plus ou moins 5 ans.
     */
    public Boolean isAgeReelPlusMoins();
    

    /**
     * Retourne l'indicateur d'�ge inconnu
     *
     * @return Boolean Indicateur d'�ge inconnu.
     */
    public Boolean isAgeInconnu();


    // Setters

    /**
     * Affecte l'�ge.
     *
     * @param age Valeur de l'�ge.
     */
    public void setAge(long age);
 
    /**
     * Affecte l'�ge estim�.
     *
     * @param ageEstime indicateur d'�ge estim�.
     */
    public void setAgeEstime(Boolean ageEstime);
    
    
    /**
     * Affecte l'�ge r�el.
     *
     * @param ageReel indicateur d'�ge r�el.
     */
    public void setAgeReel(Boolean ageReel);
    
    /**
     * Affecte l'�ge r�el plus ou moins 5 ans.
     *
     * @param ageReelPlusMoins indicateur d'�ge r�el
     * plus ou moins 5 ans.
     */
    public void setAgeReelPlusMoins(Boolean ageReelPlusMoins);

    /**
     * Affecte l'�ge inconnu.
     *
     * @param ageInconnu indicateur d'�ge inconnu
     */
    public void setAgeInconnu(Boolean ageInconnu);
    
}
