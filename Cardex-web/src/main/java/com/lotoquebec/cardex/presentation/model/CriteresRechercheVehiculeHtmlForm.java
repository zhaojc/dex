package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de véhicule.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.5 $, $Date: 2002/02/22 22:21:20 $
 */
public interface CriteresRechercheVehiculeHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation();

    /**
     * Retourne le NumeroFiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche();
    
    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque();

    /**
     * Retourne le modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public String getModele();

    /**
     * Champ utilitaire pour retrouver un modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public String getModeleMarque();

    /**
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne la première particularité.
     *
     * @return String Valeur de la première particularité en caractère.
     */
    public String getParticularite1();

    /**
     * Retourne la deuxième particularité.
     *
     * @return String Valeur de la deuxième particularité en caractère.
     */
    public String getParticularite2();

    /**
     * Retourne la troisième particularité.
     *
     * @return String Valeur de la troisième particularité en caractère.
     */
    public String getParticularite3();

    /**
     * Retourne la quatrième particularité.
     *
     * @return String Valeur de la quatrième particularité en caractère.
     */
    public String getParticularite4();

    /**
     * Retourne la cinquième particularité.
     *
     * @return String Valeur de la cinquième particularité en caractère.
     */
    public String getParticularite5();

    /**
     * Retourne une collection de véhicules.
     *
     * @return Collection Valeur de la collection de véhicules.
     */
    public Collection getVehicules();


    // Setters


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
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModele(String modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModeleMarque(String modeleMarque);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une première particularité.
     *
     * @param particularite1 Valeur de la première particularité en caractère.
     */
    public void setParticularite1(String particularite1);

    /**
     * Affecte une deuxième particularité.
     *
     * @param particularite2 Valeur de la deuxième particularité en caractère.
     */
    public void setParticularite2(String particularite2);

    /**
     * Affecte une troisième particularité.
     *
     * @param particularite3 Valeur de la troisième particularité en caractère.
     */
    public void setParticularite3(String particularite3);

    /**
     * Affecte une quatrième particularité.
     *
     * @param particularite4 Valeur de la quatrième particularité en caractère.
     */
    public void setParticularite4(String particularite4);

    /**
     * Affecte une cinquième particularité.
     *
     * @param particularite5 Valeur de la cinquième particularité en caractère.
     */
    public void setParticularite5(String particularite5);

    /**
     * Ajoute un véhicule.
     *
     * @param sujet Valeur de la véhicule à ajouter.
     */
    public void addVehicule(VehiculeHtmlForm vehicule);

	public String getCleProvince();

	public void setCleProvince(String cleProvince);
    
}