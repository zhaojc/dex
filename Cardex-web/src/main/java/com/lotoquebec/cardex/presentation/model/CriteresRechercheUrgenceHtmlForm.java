package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaires à l'obtention des valeurs
 * relatives à un formulaire de recherche du service d'urgence.
 *
 * @author $Author: mazzucr $
 * @version $Date: 2014/03/13 $
 */
public interface CriteresRechercheUrgenceHtmlForm extends CriteresRecherche{

    // Getters
    
    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caractère.
     */    
    public String getClasse();
    
    /**
     * Retourne le numéro de dossier Cardex.
     *
     * @return String Valeur de numeroDossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de confidentialite en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne le lien site d'une société.
     *
     * @return String Valeur de lienSiteSociete en caractère.
     */
    public String getLienSiteSociete();
    
    /**
     * Retourne l'entité.
     *
     * @return String Valeur de entite en caractère.
     */
    public String getEntite();
    
    /**
     * Retourne le lien avec la société.
     *
     * @return String Valeur de lienSociete en caractère.
     */
    public String getLienSociete();
    
    /**
     * Retourne le site origine.
     *
     * @return String Valeur de siteOrigine en caractère.
     */
    public String getSiteOrigine();
    
    /**
     * Retourne le motif.
     *
     * @return String Valeur de motif en caractère.
     */
    public String getMotif();

    /**
     * Retourne le statut.
     *
     * @return String Valeur de statut en caractère.
     */
    public String getStatut();
    
    /**
     * Retourne la description de la classe.
     *
     * @return String Valeur de classeDescription en caractère.
     */
    public String getClasseDescription();

    /**
     * Retourne le nom de la société
     *
     * @return String Valeur de societe en caractère.
     */
    public String getSociete();
 
    /**
     * Retourne l'unité.
     *
     * @return String Valeur de l'unité en caractère.
     */
    public String getUnite();

    /**
     * Retourne le district.
     *
     * @return String Valeur du district en caractère.
     */
    public String getDistrict();
    
    /**
     * Retourne le contact.
     *
     * @return String Valeur du contact en caractère.
     */
    public String getContact();

    /**
     * Retourne le contactPrenom.
     *
     * @return String Valeur du contactPrenom en carcatère.
     */
    public String getContactPrenom();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de ville en caractère.
     */
    public String getVille();

    /**
     * Retourne l'évènement.
     *
     * @return String Valeur d'évenement en caractère.
     */
    public String getEvenement();

    /**
     * Retourne la fonction.
     *
     * @return String Valeur de fonction en caractère.
     */
    public String getFonction();

    /**
     * Retourne une matricule.
     *
     * @return matricule Valeur de la matricule d'Urgences.
     */
    public String getMatricule();
 
    /**
     * Retourne une collection de service d'urgence.
     *
     * @return Collection Valeur de la collection d'Urgences.
     */
    public Collection getUrgences();

    // Setters

    /**
     *Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur de numeroFiche en caractère.
     */
    public void setClasse(String classe);
    
    /**
     *Affecte une confidentialité.
     *
     * @param confidentialite Valeur de confidentialite en caractère.
     */
    public void setConfidentialite(String confidentialite);
    
    /**
     *Affecte un lienSiteSociete.
     *
     * @param lienSiteSociete Valeur de lienSiteSociete en caractère.
     */
    public void setLienSiteSociete(String lienSiteSociete);
    
    /**
     *Affecte une entite.
     *
     * @param entite Valeur de entite en caractère.
     */
    public void setEntite(String entite);
    
    /**
     *Affecte un lienSociete.
     *
     * @param lienSociete Valeur de lienSociete en caractère.
     */
    public void setLienSociete(String lienSociete);
    
    /**
     *Affecte un siteOrigine.
     *
     * @param lienSociete Valeur de lienSociete en caractère.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     *Affecte un motif.
     *
     * @param motif Valeur de motif en caractère.
     */
    public void setMotif(String motif);
    
    /**
     *Affecte un statut.
     *
     * @param statut Valeur de statut en caractère.
     */
    public void setStatut(String statut);

    /**
     *Affecte la description de la classe.
     *
     * @param classeDescription Valeur de classeDescription en caractère.
     */
    public void setClasseDescription(String classeDescription);
    
    /**
     *Affecte un numéro de dossier Cardex.
     *
     * @param numeroDossier Valeur de numeroDossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);
    
    /**
     *Affecte un nom de société.
     *
     * @param societe Valeur de societe en caractère.
     */
    public void setSociete(String societe);

    /**
     *Affecte une unité.
     *
     * @param unite Valeur d'unite en caractère.
     */
    public void setUnite(String unite);

    /**
     * Affecte un district.
     *
     * @param district Valeur du district en caractère.
     */
    public void setDistrict(String district);

    /**
     * Affecte un contact.
     *
     * @param contact Valeur du contact en caractère.
     */
    public void setContact(String contact);

    /**
     * Affecte un contactPrenom.
     *
     * @param contactPrenom Valeur du contactPrenom en caractère.
     */
    public void setContactPrenom(String contactPrenom);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de ville en caractère.
     */
    public void setVille(String ville);

    /**
     * Affecte un évènement.
     *
     * @param evenement Valeur de evenement en caractère.
     */
    public void setEvenement(String evenement);

    /**
     * Affecte une fonction.
     *
     * @param fonction Valeur de fonction en caractère.
     */
    public void setFonction(String fonction);

    /**
     * Affecte une matricule.
     *
     * @param matricule Valeur de la matricule en caractère.
     */
    public void setMatricule(String matricule);

    /**
     * Ajoute un service d'urgence.
     *
     * @param urgence Valeur du service d'urgence à ajouter.
     */
    public void addUrgence(UrgenceHtmlForm urgence);
    
}