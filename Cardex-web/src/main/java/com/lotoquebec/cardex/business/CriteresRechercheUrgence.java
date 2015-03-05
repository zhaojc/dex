package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères, nécesssaires à la recherche du
 * service d'urgence.
 *
 * @author $Author: mazzucr $
 * 
 */
public interface CriteresRechercheUrgence extends CriteresRecherche{


    // Getters
    /**
     * Retourne le motif.
     *
     * @return long Valeur numérique de motif.
     */
    public long getMotif();

    /**
     * Retourne le statut.
     *
     * @return long Valeur numérique du statut.
     */    
    public long getStatut();
    
    /**
     * Retourne la classe.
     *
     * @return long Valeur numérique de a classe.
     */    
    public long getClasse();
    
    /**
     * Retourne la description de la société.
     *
     * @return String Valeur de societe en caractères
     */    
    public String getSociete();
    
    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite();
   
    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur numérique du site d'origine.
     */
    public long getSiteOrigine();
  
    /**
     * Retourne la clé de la société.
     *
     * @return long Valeur numérique du lienSociete.
     */
    public long getLienSociete();

    /**
     * Retourne le lien du site de la société.
     *
     * @return long Valeur numérique de lienSiteSociete.
     */
    public long getLienSiteSociete();

    /**
     * Retourne l'unité.
     *
     * @return String Valeur d'Unite en caractère.
     */
    public String getUnite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getDistrict();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getContact();

    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur en caractères du numéro de fiche.
     */
    public String getContactPrenom();

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public String getVille();

    /**
     * Retourne le modèle.
     *
     * @return long Valeur du modèle.
     */
    public String getEvenement();

    /**
     * Champ utilitaire pour retrouver un modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public String getFonction();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public String getMatricule();

    /**
     * Retourne le numérod de dossier.
     *
     * @return String Valeur en caractère du numéro de dossier.
     */
    public String getNumeroDossier();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite();

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
     * Affecte un motif.
     *
     * @param motif Valeur numérique de motif.
     */
    public void setMotif(long motif);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur numérique de statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur numérique de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte une société.
     *
     * @param societe Valeur societe en caractères.
     */
    public void setSociete(String societe);
    
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
     * Affecte un lien avec une société.
     *
     * @param lienSociete Valeur numérique de lienSociete.
     */
    public void setLienSociete(long lienSociete);

    /**
     * Affecte un lien du site de la société.
     *
     * @param lienSiteSociete Valeur numérique de lienSiteSociete.
     */
    public void setLienSiteSociete(long lienSiteSociete);

    /**
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setUnite(String unite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setDistrict(String district);

    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setContact(String contact);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setContactPrenom(String contactPrenom);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setVille(String ville);

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle.
     */
    public void setEvenement(String evenement);


    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setFonction(String fonction);

    /**
     * Affecte une première particularité.
     *
     * @param particularite1 Valeur de la première particularité.
     */
    public void setMatricule(String matricule);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numeroDossier.
     */
    public void setNumeroDossier(String numeroDossier);
    
    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur  de confidentialite.
     */
    public void setConfidentialite(long confidentialite);
    
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
 
}