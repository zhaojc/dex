package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res, n�cesssaires � la recherche de
 * v�hicule.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.7 $, $Date: 2002/02/22 21:13:44 $
 */
public interface CriteresRechercheVehicule extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public long getSiteOrigine();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getImmatriculation();

    /**
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur en caract�res du num�ro de fiche.
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
     * Retourne le mod�le.
     *
     * @return long Valeur du mod�le.
     */
    public long getModele();

    /**
     * Champ utilitaire pour retrouver un mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public long getModeleMarque();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite();

    /**
     * Retourne la premi�re particularit�.
     *
     * @return long Valeur de la premi�re particularit�.
     */
    public long getParticularite1();

    /**
     * Retourne la deuxi�me particularit�.
     *
     * @return long Valeur de la deuxi�me particularit�.
     */
    public long getParticularite2();

    /**
     * Retourne la troisi�me particularit�.
     *
     * @return long Valeur de la troisi�me particularit�.
     */
    public long getParticularite3();

    /**
     * Retourne la quatri�me particularit�.
     *
     * @return long Valeur de la quatri�me particularit�.
     */
    public long getParticularite4();

    /**
     * Retourne la cinqui�me particularit�.
     *
     * @return long Valeur de la cinqui�me particularit�.
     */
    public long getParticularite5();

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
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setSiteOrigine(long siteOrigine);

    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
     */
    public void setImmatriculation(String immatriculation);

    /**
     *Affecte un numeroFiche.
     *
     * @param numeroFiche Valeur du num�ro de fiche en caract�re.
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
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le.
     */
    public void setModele(long modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModeleMarque(long modeleMarque);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une premi�re particularit�.
     *
     * @param particularite1 Valeur de la premi�re particularit�.
     */
    public void setParticularite1(long particularite1);

    /**
     * Affecte une deuxi�me particularit�.
     *
     * @param particularite2 Valeur de la deuxi�me particularit�.
     */
    public void setParticularite2(long particularite2);

    /**
     * Affecte une troisi�me particularit�.
     *
     * @param particularite3 Valeur de la troisi�me particularit�.
     */
    public void setParticularite3(long particularite3);

    /**
     * Affecte une quatri�me particularit�.
     *
     * @param particularite4 Valeur de la quatri�me particularit�.
     */
    public void setParticularite4(long particularite4);

    /**
     * Affecte une cinqui�me particularit�.
     *
     * @param particularite5 Valeur de la cinqui�me particularit�.
     */
    public void setParticularite5(long particularite5);

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
     * Retourne la cl� de la province.
     *
     */
    public long getCleProvince();

    /**
     * Affecte la cl� de la province.
     *
     * @param cleProvince Valeur cleProvince.
     */
    public void setCleProvince(long cleProvince);

    
}