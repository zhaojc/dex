package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de v�hicule.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.5 $, $Date: 2002/02/22 22:21:20 $
 */
public interface CriteresRechercheVehiculeHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getImmatriculation();

    /**
     * Retourne le NumeroFiche.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroFiche();
    
    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque();

    /**
     * Retourne le mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public String getModele();

    /**
     * Champ utilitaire pour retrouver un mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public String getModeleMarque();

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
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite();

    /**
     * Retourne la premi�re particularit�.
     *
     * @return String Valeur de la premi�re particularit� en caract�re.
     */
    public String getParticularite1();

    /**
     * Retourne la deuxi�me particularit�.
     *
     * @return String Valeur de la deuxi�me particularit� en caract�re.
     */
    public String getParticularite2();

    /**
     * Retourne la troisi�me particularit�.
     *
     * @return String Valeur de la troisi�me particularit� en caract�re.
     */
    public String getParticularite3();

    /**
     * Retourne la quatri�me particularit�.
     *
     * @return String Valeur de la quatri�me particularit� en caract�re.
     */
    public String getParticularite4();

    /**
     * Retourne la cinqui�me particularit�.
     *
     * @return String Valeur de la cinqui�me particularit� en caract�re.
     */
    public String getParticularite5();

    /**
     * Retourne une collection de v�hicules.
     *
     * @return Collection Valeur de la collection de v�hicules.
     */
    public Collection getVehicules();


    // Setters


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
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque);

    /**
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModele(String modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModeleMarque(String modeleMarque);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une premi�re particularit�.
     *
     * @param particularite1 Valeur de la premi�re particularit� en caract�re.
     */
    public void setParticularite1(String particularite1);

    /**
     * Affecte une deuxi�me particularit�.
     *
     * @param particularite2 Valeur de la deuxi�me particularit� en caract�re.
     */
    public void setParticularite2(String particularite2);

    /**
     * Affecte une troisi�me particularit�.
     *
     * @param particularite3 Valeur de la troisi�me particularit� en caract�re.
     */
    public void setParticularite3(String particularite3);

    /**
     * Affecte une quatri�me particularit�.
     *
     * @param particularite4 Valeur de la quatri�me particularit� en caract�re.
     */
    public void setParticularite4(String particularite4);

    /**
     * Affecte une cinqui�me particularit�.
     *
     * @param particularite5 Valeur de la cinqui�me particularit� en caract�re.
     */
    public void setParticularite5(String particularite5);

    /**
     * Ajoute un v�hicule.
     *
     * @param sujet Valeur de la v�hicule � ajouter.
     */
    public void addVehicule(VehiculeHtmlForm vehicule);

	public String getCleProvince();

	public void setCleProvince(String cleProvince);
    
}