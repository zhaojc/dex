package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res, n�cesssaires � la recherche du
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
     * @return long Valeur num�rique de motif.
     */
    public long getMotif();

    /**
     * Retourne le statut.
     *
     * @return long Valeur num�rique du statut.
     */    
    public long getStatut();
    
    /**
     * Retourne la classe.
     *
     * @return long Valeur num�rique de a classe.
     */    
    public long getClasse();
    
    /**
     * Retourne la description de la soci�t�.
     *
     * @return String Valeur de societe en caract�res
     */    
    public String getSociete();
    
    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite();
   
    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine();
  
    /**
     * Retourne la cl� de la soci�t�.
     *
     * @return long Valeur num�rique du lienSociete.
     */
    public long getLienSociete();

    /**
     * Retourne le lien du site de la soci�t�.
     *
     * @return long Valeur num�rique de lienSiteSociete.
     */
    public long getLienSiteSociete();

    /**
     * Retourne l'unit�.
     *
     * @return String Valeur d'Unite en caract�re.
     */
    public String getUnite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getDistrict();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getContact();

    /**
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur en caract�res du num�ro de fiche.
     */
    public String getContactPrenom();

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public String getVille();

    /**
     * Retourne le mod�le.
     *
     * @return long Valeur du mod�le.
     */
    public String getEvenement();

    /**
     * Champ utilitaire pour retrouver un mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public String getFonction();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public String getMatricule();

    /**
     * Retourne le num�rod de dossier.
     *
     * @return String Valeur en caract�re du num�ro de dossier.
     */
    public String getNumeroDossier();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite();

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
     * Affecte un motif.
     *
     * @param motif Valeur num�rique de motif.
     */
    public void setMotif(long motif);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique de statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur num�rique de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte une soci�t�.
     *
     * @param societe Valeur societe en caract�res.
     */
    public void setSociete(String societe);
    
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
     * Affecte un lien avec une soci�t�.
     *
     * @param lienSociete Valeur num�rique de lienSociete.
     */
    public void setLienSociete(long lienSociete);

    /**
     * Affecte un lien du site de la soci�t�.
     *
     * @param lienSiteSociete Valeur num�rique de lienSiteSociete.
     */
    public void setLienSiteSociete(long lienSiteSociete);

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setUnite(String unite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setDistrict(String district);

    /**
     *Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
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
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le.
     */
    public void setEvenement(String evenement);


    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setFonction(String fonction);

    /**
     * Affecte une premi�re particularit�.
     *
     * @param particularite1 Valeur de la premi�re particularit�.
     */
    public void setMatricule(String matricule);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du numeroDossier.
     */
    public void setNumeroDossier(String numeroDossier);
    
    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur  de confidentialite.
     */
    public void setConfidentialite(long confidentialite);
    
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