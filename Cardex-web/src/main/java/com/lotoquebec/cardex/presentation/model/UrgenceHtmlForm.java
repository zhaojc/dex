package com.lotoquebec.cardex.presentation.model;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;


/**
 * Permet de transiter les informations relatives � la consultation d'une
 * service d'urgence de la couche de pr�sentation � la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/28 17:28:36 $
 */
public interface UrgenceHtmlForm {


    // Getters

    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite();

    /**
     * Retourne le nom de la soci�t�.
     *
     * @return String Valeur de societe en caract�re.
     */
    public String getSociete();

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caract�re.
     */
    public String getClasse();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne le num�ro de t�l�phone.
     *
     * @return String Valeur du num�ro de t�l�phone en caract�re.
     */
    public String getTelephone();

    /**
     * Retourne le num�ro de poste t�l�phonique.
     *
     * @return long Valeur du num�ro de poste t�l�phonique.
     */
    public String getPoste();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caract�re.
     */
    public String getVille();

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
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne le contact.
     *
     * @return String Valeur du constact en caract�re.
     */
    public String getContact();

    /**
     * Retourne le pr�nom du contact.
     *
     * @return String Valeur du constactPrenom en caract�re.
     */
    public String getContactPrenom();
    
    /**
     * Retourne la fonction/grade.
     *
     * @return String Valeur de la fonctionGrade en caract�re.
     */
    public String getFonctionGrade();

    /**
     * Retourne la matricule.
     *
     * @return String Valeur de la matricule en caract�re.
     */
    public String getMatricule();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne le t�l�copieur.
     *
     * @return String Valeur du t�l�copieur en caract�re.
     */
    public String getTelecopieur();

    /**
     * Retourne le courriel.
     *
     * @return String Valeur du courriel en caract�re.
     */
    public String getCourriel();

    /**
     * Retourne le num�ro d'�v�nement.
     *
     * @return String Valeur du num�ro d'�v�nement en caract�re.
     */
    public String getEvenement();

    /**
     * Retourne le motif.
     *
     * @return String Valeur du motif en caract�re.
     */
    public String getMotif();

    /**
     * Retourne le r�pondant.
     *
     * @return boolean Valeur du r�pondant en caract�re.
     */
    public boolean isRepondant();

	public void setRepondant(boolean repondant);
    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();

    /**
     * Retourne le lien � une soci�t�.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLienSociete();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSiteSociete();

    /**
     * Retourne le num�ro de dossier Cardex.
     *
     * @return String Valeur du num�ro de dossier Cardex en caract�re.
     */
    public String getNumeroDossier();
    

    // Setters

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte le nom de la soci�t�.
     *
     * @param site Valeur de societe en caract�re.
     */
    public void setSociete(String societe);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caract�re.
     */
    public void setClasse(String classe);

    /**
     * Affecte un contact.
     *
     * @param adresse2 Valeur du contact en caract�re.
     */
    public void setContact(String contact);

    /**
     * Affecte un contactPrenom.
     *
     * @param contactPrenom Valeur du contactPrenom en caract�re.
     */
    public void setContactPrenom(String contactPrenom);

    /**
     * Affecte une fonctionGrade.
     *
     * @param fonctionGrade Valeur du fonctionGrade en caract�re.
     */
    public void setFonctionGrade(String fonctionGrade);

    /**
     * Affecte une matricule.
     *
     * @param matricule Valeur de la matricule en caract�re.
     */
    public void setMatricule(String matricule);

    /**
     * Affecte un premier de t�l�phone.
     *
     * @param telephone1 Valeur du premier t�l�phone en caract�re.
     */
    public void setTelephone(String telephone);

    /**
     * Affecte le num�ro de poste t�l�phonique.
     *
     * @param poste Valeur du poste t�l�phonique.
     */
    public void setPoste(String poste);

    /**
     * Affecte un courriel en caract�re.
     *
     * @param telephone2 Valeur du courriel en caract�re.
     */
    public void setCourriel(String courriel);

    /**
     * Affecte un district.
     *
     * @param pays Valeur du district en caract�re.
     */
    public void setDistrict(String district);

    /**
     * Affecte un num�ro d'�v�nement.
     *
     * @param evenement Valeur du num�ro d'evenement en caract�re.
     */
    public void setEvenement(String evenement);

    /**
     * Affecte un motif.
     *
     * @param motif Valeur du motif en caract�re.
     */
    public void setMotif(String motif);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caract�re.
     */
    public void setVille(String ville);

    /**
     * Affecte un telecopieur.
     *
     * @param commentaire Valeur du telecopieur en caract�re.
     */
    public void setTelecopieur(String telecopieur);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une unite.
     *
     * @param unite Valeur du unite en caract�re.
     */
    public void setUnite(String unite);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte un lien � la soci�t�.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLienSociete(String lienSociete);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSiteSociete(String lienSiteSociete);

    /**
     * Affecte un num�ro de dossier Cardex.
     *
     * @param numeroDossier Valeur du num�ro de dossier Cardex en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);
    
    /**
     * @param subject
     */
    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException;
    
}