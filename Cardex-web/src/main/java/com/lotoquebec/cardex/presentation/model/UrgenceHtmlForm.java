package com.lotoquebec.cardex.presentation.model;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;


/**
 * Permet de transiter les informations relatives à la consultation d'une
 * service d'urgence de la couche de présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/28 17:28:36 $
 */
public interface UrgenceHtmlForm {


    // Getters

    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne le nom de la société.
     *
     * @return String Valeur de societe en caractère.
     */
    public String getSociete();

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caractère.
     */
    public String getClasse();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne le numéro de téléphone.
     *
     * @return String Valeur du numéro de téléphone en caractère.
     */
    public String getTelephone();

    /**
     * Retourne le numéro de poste téléphonique.
     *
     * @return long Valeur du numéro de poste téléphonique.
     */
    public String getPoste();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caractère.
     */
    public String getVille();

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
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne le contact.
     *
     * @return String Valeur du constact en caractère.
     */
    public String getContact();

    /**
     * Retourne le prénom du contact.
     *
     * @return String Valeur du constactPrenom en caractère.
     */
    public String getContactPrenom();
    
    /**
     * Retourne la fonction/grade.
     *
     * @return String Valeur de la fonctionGrade en caractère.
     */
    public String getFonctionGrade();

    /**
     * Retourne la matricule.
     *
     * @return String Valeur de la matricule en caractère.
     */
    public String getMatricule();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne le télécopieur.
     *
     * @return String Valeur du télécopieur en caractère.
     */
    public String getTelecopieur();

    /**
     * Retourne le courriel.
     *
     * @return String Valeur du courriel en caractère.
     */
    public String getCourriel();

    /**
     * Retourne le numéro d'événement.
     *
     * @return String Valeur du numéro d'événement en caractère.
     */
    public String getEvenement();

    /**
     * Retourne le motif.
     *
     * @return String Valeur du motif en caractère.
     */
    public String getMotif();

    /**
     * Retourne le répondant.
     *
     * @return boolean Valeur du répondant en caractère.
     */
    public boolean isRepondant();

	public void setRepondant(boolean repondant);
    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

    /**
     * Retourne le lien à une société.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLienSociete();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSiteSociete();

    /**
     * Retourne le numéro de dossier Cardex.
     *
     * @return String Valeur du numéro de dossier Cardex en caractère.
     */
    public String getNumeroDossier();
    

    // Setters

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte le nom de la société.
     *
     * @param site Valeur de societe en caractère.
     */
    public void setSociete(String societe);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caractère.
     */
    public void setClasse(String classe);

    /**
     * Affecte un contact.
     *
     * @param adresse2 Valeur du contact en caractère.
     */
    public void setContact(String contact);

    /**
     * Affecte un contactPrenom.
     *
     * @param contactPrenom Valeur du contactPrenom en caractère.
     */
    public void setContactPrenom(String contactPrenom);

    /**
     * Affecte une fonctionGrade.
     *
     * @param fonctionGrade Valeur du fonctionGrade en caractère.
     */
    public void setFonctionGrade(String fonctionGrade);

    /**
     * Affecte une matricule.
     *
     * @param matricule Valeur de la matricule en caractère.
     */
    public void setMatricule(String matricule);

    /**
     * Affecte un premier de téléphone.
     *
     * @param telephone1 Valeur du premier téléphone en caractère.
     */
    public void setTelephone(String telephone);

    /**
     * Affecte le numéro de poste téléphonique.
     *
     * @param poste Valeur du poste téléphonique.
     */
    public void setPoste(String poste);

    /**
     * Affecte un courriel en caractère.
     *
     * @param telephone2 Valeur du courriel en caractère.
     */
    public void setCourriel(String courriel);

    /**
     * Affecte un district.
     *
     * @param pays Valeur du district en caractère.
     */
    public void setDistrict(String district);

    /**
     * Affecte un numéro d'événement.
     *
     * @param evenement Valeur du numéro d'evenement en caractère.
     */
    public void setEvenement(String evenement);

    /**
     * Affecte un motif.
     *
     * @param motif Valeur du motif en caractère.
     */
    public void setMotif(String motif);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caractère.
     */
    public void setVille(String ville);

    /**
     * Affecte un telecopieur.
     *
     * @param commentaire Valeur du telecopieur en caractère.
     */
    public void setTelecopieur(String telecopieur);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une unite.
     *
     * @param unite Valeur du unite en caractère.
     */
    public void setUnite(String unite);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte un lien à la société.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLienSociete(String lienSociete);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSiteSociete(String lienSiteSociete);

    /**
     * Affecte un numéro de dossier Cardex.
     *
     * @param numeroDossier Valeur du numéro de dossier Cardex en caractère.
     */
    public void setNumeroDossier(String numeroDossier);
    
    /**
     * @param subject
     */
    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException;
    
}