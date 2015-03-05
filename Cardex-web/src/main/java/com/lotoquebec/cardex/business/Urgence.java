package com.lotoquebec.cardex.business;

import java.sql.Timestamp;


/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives aux services d'urgence.
 *
 * @author $Author: guerinf $
 * @version $Revision: 1.4 $, $Date: 2013/07/08 17:28:36 $
 */
public interface Urgence extends Modifiable{


    // Getters
    /**
     * Retourne certaines informations sur le dossier retourné
     * par une recherche de narrations.  Ces informations sont
     * utilisées pour appeler le dossier à l'écran à partir de la
     * liste de résultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier();
    
    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caractère.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSite();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe en caractère.
     */
    public long getClasse();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut en caractère.
     */
    public long getStatut();

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
    public long getPoste();

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
     * @return String Valeur du fonctionGrade en caractère.
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
     * @return Timestamp Valeur de la date de création en caractère.
     */
    public Timestamp getDateCreation();

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
     * @return long Valeur du motif en caractère.
     */
    public long getMotif();

    /**
     * Retourne le nom de la société.
     *
     * @return String Valeur de societe en caractère.
     */
    public String getSociete();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caractère.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caractère.
     */
    public long getLienSite();

    /**
     * Retourne le lien à une société.
     *
     * @return long Valeur du lien en caractère.
     */
    public long getLienSociete();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caractère.
     */
    public long getLienSiteSociete();

    /**
     * Retourne le lien du numéro de dossier Cardex.
     *
     * @return String Valeur du numeroDossier en caractère.
     */
    public String getNumeroDossier();
    
    
    // Setters

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourné par une recherche de narrations.
     */
    public void setDossier(Dossier dossier);
    
    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(long site);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caractère.
     */
    public void setClasse(long classe);

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
     * Affecte une fonction/grade.
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
     * Affecte un numéro de poste téléphonique.
     *
     * @param poste Valeur du poste téléphonique.
     */
    public void setPoste(long poste);

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
    public void setMotif(long motif);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(long statut);

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
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte un lien à une société.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLienSociete(long lienSociete);

    /**
     * Affecte un lien du site de la société.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSiteSociete(long lienSiteSociete);

    /**
     * Affecte le nom de la société.
     *
     * @param site Valeur de societe en caractère.
     */
    public void setSociete(String societe);
   
    /**
     * Affecte le numéro de dossier de Cardex.
     *
     * @param numeroDossier Valeur du numéro de dossier de Cardex en caractère.
     */
    public void setNumeroDossier(String numeroDossier);
    
	public Boolean isRepondant();
	
	public void setRepondant(Boolean repondant);

}