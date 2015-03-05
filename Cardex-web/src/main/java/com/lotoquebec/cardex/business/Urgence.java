package com.lotoquebec.cardex.business;

import java.sql.Timestamp;


/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives aux services d'urgence.
 *
 * @author $Author: guerinf $
 * @version $Revision: 1.4 $, $Date: 2013/07/08 17:28:36 $
 */
public interface Urgence extends Modifiable{


    // Getters
    /**
     * Retourne certaines informations sur le dossier retourn�
     * par une recherche de narrations.  Ces informations sont
     * utilis�es pour appeler le dossier � l'�cran � partir de la
     * liste de r�sultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier();
    
    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSite();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe en caract�re.
     */
    public long getClasse();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut en caract�re.
     */
    public long getStatut();

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
    public long getPoste();

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
     * @return String Valeur du fonctionGrade en caract�re.
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
     * @return Timestamp Valeur de la date de cr�ation en caract�re.
     */
    public Timestamp getDateCreation();

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
     * @return long Valeur du motif en caract�re.
     */
    public long getMotif();

    /**
     * Retourne le nom de la soci�t�.
     *
     * @return String Valeur de societe en caract�re.
     */
    public String getSociete();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caract�re.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caract�re.
     */
    public long getLienSite();

    /**
     * Retourne le lien � une soci�t�.
     *
     * @return long Valeur du lien en caract�re.
     */
    public long getLienSociete();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caract�re.
     */
    public long getLienSiteSociete();

    /**
     * Retourne le lien du num�ro de dossier Cardex.
     *
     * @return String Valeur du numeroDossier en caract�re.
     */
    public String getNumeroDossier();
    
    
    // Setters

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourn� par une recherche de narrations.
     */
    public void setDossier(Dossier dossier);
    
    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caract�re.
     */
    public void setClasse(long classe);

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
     * Affecte une fonction/grade.
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
     * Affecte un num�ro de poste t�l�phonique.
     *
     * @param poste Valeur du poste t�l�phonique.
     */
    public void setPoste(long poste);

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
    public void setMotif(long motif);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(long statut);

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
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte un lien � une soci�t�.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLienSociete(long lienSociete);

    /**
     * Affecte un lien du site de la soci�t�.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSiteSociete(long lienSiteSociete);

    /**
     * Affecte le nom de la soci�t�.
     *
     * @param site Valeur de societe en caract�re.
     */
    public void setSociete(String societe);
   
    /**
     * Affecte le num�ro de dossier de Cardex.
     *
     * @param numeroDossier Valeur du num�ro de dossier de Cardex en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);
    
	public Boolean isRepondant();
	
	public void setRepondant(Boolean repondant);

}