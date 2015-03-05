package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaires � l'obtention des valeurs
 * relatives � un formulaire de recherche du service d'urgence.
 *
 * @author $Author: mazzucr $
 * @version $Date: 2014/03/13 $
 */
public interface CriteresRechercheUrgenceHtmlForm extends CriteresRecherche{

    // Getters
    
    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caract�re.
     */    
    public String getClasse();
    
    /**
     * Retourne le num�ro de dossier Cardex.
     *
     * @return String Valeur de numeroDossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de confidentialite en caract�re.
     */
    public String getConfidentialite();

    /**
     * Retourne le lien site d'une soci�t�.
     *
     * @return String Valeur de lienSiteSociete en caract�re.
     */
    public String getLienSiteSociete();
    
    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de entite en caract�re.
     */
    public String getEntite();
    
    /**
     * Retourne le lien avec la soci�t�.
     *
     * @return String Valeur de lienSociete en caract�re.
     */
    public String getLienSociete();
    
    /**
     * Retourne le site origine.
     *
     * @return String Valeur de siteOrigine en caract�re.
     */
    public String getSiteOrigine();
    
    /**
     * Retourne le motif.
     *
     * @return String Valeur de motif en caract�re.
     */
    public String getMotif();

    /**
     * Retourne le statut.
     *
     * @return String Valeur de statut en caract�re.
     */
    public String getStatut();
    
    /**
     * Retourne la description de la classe.
     *
     * @return String Valeur de classeDescription en caract�re.
     */
    public String getClasseDescription();

    /**
     * Retourne le nom de la soci�t�
     *
     * @return String Valeur de societe en caract�re.
     */
    public String getSociete();
 
    /**
     * Retourne l'unit�.
     *
     * @return String Valeur de l'unit� en caract�re.
     */
    public String getUnite();

    /**
     * Retourne le district.
     *
     * @return String Valeur du district en caract�re.
     */
    public String getDistrict();
    
    /**
     * Retourne le contact.
     *
     * @return String Valeur du contact en caract�re.
     */
    public String getContact();

    /**
     * Retourne le contactPrenom.
     *
     * @return String Valeur du contactPrenom en carcat�re.
     */
    public String getContactPrenom();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de ville en caract�re.
     */
    public String getVille();

    /**
     * Retourne l'�v�nement.
     *
     * @return String Valeur d'�venement en caract�re.
     */
    public String getEvenement();

    /**
     * Retourne la fonction.
     *
     * @return String Valeur de fonction en caract�re.
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
     *Affecte un num�ro de fiche.
     *
     * @param numeroFiche Valeur de numeroFiche en caract�re.
     */
    public void setClasse(String classe);
    
    /**
     *Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de confidentialite en caract�re.
     */
    public void setConfidentialite(String confidentialite);
    
    /**
     *Affecte un lienSiteSociete.
     *
     * @param lienSiteSociete Valeur de lienSiteSociete en caract�re.
     */
    public void setLienSiteSociete(String lienSiteSociete);
    
    /**
     *Affecte une entite.
     *
     * @param entite Valeur de entite en caract�re.
     */
    public void setEntite(String entite);
    
    /**
     *Affecte un lienSociete.
     *
     * @param lienSociete Valeur de lienSociete en caract�re.
     */
    public void setLienSociete(String lienSociete);
    
    /**
     *Affecte un siteOrigine.
     *
     * @param lienSociete Valeur de lienSociete en caract�re.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     *Affecte un motif.
     *
     * @param motif Valeur de motif en caract�re.
     */
    public void setMotif(String motif);
    
    /**
     *Affecte un statut.
     *
     * @param statut Valeur de statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     *Affecte la description de la classe.
     *
     * @param classeDescription Valeur de classeDescription en caract�re.
     */
    public void setClasseDescription(String classeDescription);
    
    /**
     *Affecte un num�ro de dossier Cardex.
     *
     * @param numeroDossier Valeur de numeroDossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);
    
    /**
     *Affecte un nom de soci�t�.
     *
     * @param societe Valeur de societe en caract�re.
     */
    public void setSociete(String societe);

    /**
     *Affecte une unit�.
     *
     * @param unite Valeur d'unite en caract�re.
     */
    public void setUnite(String unite);

    /**
     * Affecte un district.
     *
     * @param district Valeur du district en caract�re.
     */
    public void setDistrict(String district);

    /**
     * Affecte un contact.
     *
     * @param contact Valeur du contact en caract�re.
     */
    public void setContact(String contact);

    /**
     * Affecte un contactPrenom.
     *
     * @param contactPrenom Valeur du contactPrenom en caract�re.
     */
    public void setContactPrenom(String contactPrenom);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de ville en caract�re.
     */
    public void setVille(String ville);

    /**
     * Affecte un �v�nement.
     *
     * @param evenement Valeur de evenement en caract�re.
     */
    public void setEvenement(String evenement);

    /**
     * Affecte une fonction.
     *
     * @param fonction Valeur de fonction en caract�re.
     */
    public void setFonction(String fonction);

    /**
     * Affecte une matricule.
     *
     * @param matricule Valeur de la matricule en caract�re.
     */
    public void setMatricule(String matricule);

    /**
     * Ajoute un service d'urgence.
     *
     * @param urgence Valeur du service d'urgence � ajouter.
     */
    public void addUrgence(UrgenceHtmlForm urgence);
    
}