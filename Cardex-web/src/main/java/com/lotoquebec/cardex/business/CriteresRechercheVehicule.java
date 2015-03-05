package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères, nécesssaires à la recherche de
 * véhicule.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.7 $, $Date: 2002/02/22 21:13:44 $
 */
public interface CriteresRechercheVehicule extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public long getSiteOrigine();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation();

    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur en caractères du numéro de fiche.
     */
    public String getNumeroFiche();

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public String getProvince();

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public long getMarque();

    /**
     * Retourne le modèle.
     *
     * @return long Valeur du modèle.
     */
    public long getModele();

    /**
     * Champ utilitaire pour retrouver un modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public long getModeleMarque();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite();

    /**
     * Retourne la première particularité.
     *
     * @return long Valeur de la première particularité.
     */
    public long getParticularite1();

    /**
     * Retourne la deuxième particularité.
     *
     * @return long Valeur de la deuxième particularité.
     */
    public long getParticularite2();

    /**
     * Retourne la troisième particularité.
     *
     * @return long Valeur de la troisième particularité.
     */
    public long getParticularite3();

    /**
     * Retourne la quatrième particularité.
     *
     * @return long Valeur de la quatrième particularité.
     */
    public long getParticularite4();

    /**
     * Retourne la cinquième particularité.
     *
     * @return long Valeur de la cinquième particularité.
     */
    public long getParticularite5();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();


    // Setters


    /**
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setSiteOrigine(long siteOrigine);

    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setImmatriculation(String immatriculation);

    /**
     *Affecte un numeroFiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(String province);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque);

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle.
     */
    public void setModele(long modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModeleMarque(long modeleMarque);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une première particularité.
     *
     * @param particularite1 Valeur de la première particularité.
     */
    public void setParticularite1(long particularite1);

    /**
     * Affecte une deuxième particularité.
     *
     * @param particularite2 Valeur de la deuxième particularité.
     */
    public void setParticularite2(long particularite2);

    /**
     * Affecte une troisième particularité.
     *
     * @param particularite3 Valeur de la troisième particularité.
     */
    public void setParticularite3(long particularite3);

    /**
     * Affecte une quatrième particularité.
     *
     * @param particularite4 Valeur de la quatrième particularité.
     */
    public void setParticularite4(long particularite4);

    /**
     * Affecte une cinquième particularité.
     *
     * @param particularite5 Valeur de la cinquième particularité.
     */
    public void setParticularite5(long particularite5);

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximum);

    /**
     * Retourne la clé de la province.
     *
     */
    public long getCleProvince();

    /**
     * Affecte la clé de la province.
     *
     * @param cleProvince Valeur cleProvince.
     */
    public void setCleProvince(long cleProvince);

    
}