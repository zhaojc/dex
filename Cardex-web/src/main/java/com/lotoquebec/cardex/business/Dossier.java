package com.lotoquebec.cardex.business;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardexCommun.business.Business;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.LiaisonEntiteCardex;

/**
 * Les dossiers regroupent toutes les donn�es reli�es aux �v�nements,
 * aux contrats ou aux d�cisions administratives internes.  La nature
 * ainsi que le nombre de classeurs varient en fonction de l'entit�
 * administrative � laquelle l'utilisateur est rattach� ainsi
 * qu'en fonction de ses privil�ges d'acc�s
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/04 00:25:27 $
 */
public interface Dossier extends LiaisonEntiteCardex, Modifiable, Business{


    // Getters
 

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne le genre.
     *
     * @return long Valeur num�rique du genre.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur num�rique de la nature.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return long Valeur num�rique du type.
     */
    public long getType();

    /**
     * Retourne le type et la cat�gorie.
     *
     * @return long Valeur num�rique du type.
     */
    public long getTypeCategorie();

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur num�rique de la cat�gorie.
     */
    public long getCategorie();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne le statut.
     *
     * @return long Valeur num�rique du statut.
     */
    public long getStatut();

    /**
     * Retourne l'attribut "fonde".
     *
     * @return long Valeur num�rique de l'attribut "fonde".
     */
    public long getFonde();

    /**
     * Retourne le num�ro de cardex.
     *
     * @return String Valeur du num�ro de cardex en caract�re.
     */
    public String getNumeroCardex();

	/**
	 * Retourne la date de cr�ation de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

    /**
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date de d�but (yyyy-MM-dd).
     */
    public Timestamp getDateDebut();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin (yyyy-MM-dd).
     */
    public Timestamp getDateFin();

    /**
     * Retourne le nombre de mois.
     *
     * @return String Valeur du nombre de mois en caract�res.
     */
    public String getMois();

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�res.
     */
    public String getReference1();

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�res.
     */
    public String getReference2();

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�res.
     */
    public String getReference3();

    /**
     * Retourne le type vid�o.
     *
     * @return long Valeur num�rique du type vid�o.
     */
    public long getTypeVideo();

    /**
     * Retourne la r�f�rence vid�o.
     *
     * @return String Valeur de la r�f�rence vid�o en caract�re.
     */
    public String getReferenceVideo();

    /**
     * Retourne si un enregistrement existe.
     *
     * @return String Valeur en caract�re.
     */
    public String getEnregistrementNumerique();

    /**
     * Retourne si un enregistrement est conserv�.
     *
     * @return String Valeur en caract�re.
     */
    public String getEnregistrementConserve();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenantDescription();

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenantDescription(String intervenant);

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine();

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur num�rique de la s�v�rit�.
     */
    public long getSeverite();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur num�rique de la confidentialit�.
     */
    public long getConfidentialite();

    /**
     * Retourne la hierarchie.
     *
     * @return long Valeur num�rique de la hierarchie.
     */
    public long getHierarchie();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public long getEndroit();

    /**
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur num�rique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public long getLocalisation();

    /**
     * Retourne la descriptif.
     *
     * @return String Valeur du descriptif en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne la cle.
     *
     * @return long Valeur num�rique de la cle.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public long getSite();

    /**
     * Retourne la p�riode.
     *
     * @return long Valeur num�rique de la pr�riode.
     */
    public long getPeriode();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasse();

    /**
     * Retourne le mot de passe courant.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasseCourant();

    /**
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public String getDuree();

    /**
     * Retourne la date rapport�e.
     *
     * @return Timestamp Valeur de la date rapport�e (yyyy-MM-dd).
     */
    public Timestamp getDateRapportee();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse();

    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public long getRace();

    /**
     * Retourne la date d'assignation.
     *
     * @return Timestamp Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public Timestamp getDateAssignation();

    /**
     * Retourne la date d'�v�nement.
     *
     * @return Timestamp Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public Timestamp getDateEvenement();

    /**
     * Retourne la quatri�me r�f�rence.
     *
     * @return String Valeur de la quatri�me r�f�rence en caract�re.
     */
    public String getReference4();

    /**
     * Retourne la cinqui�me r�f�rence.
     *
     * @return String Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public String getReference5();

    /**
     * Retourne la sixi�me r�f�rence.
     *
     * @return String Valeur de la sixi�me r�f�rence en caract�re.
     */
    public String getReference6();

    /**
     * Retourne la septi�me r�f�rence.
     *
     * @return String Valeur de la septi�me r�f�rence en caract�re.
     */
    public String getReference7();

    /**
     * Retourne l'attribut "fonde description".
     *
     * @return String Valeur de l'attribut "fonde description" en caract�re.
     */
    public String getFondeDescription();

    // Attributs du lien

    /**
     * Retourne le lien.
     *
     * @return String Valeur num�rique du lien.
     */
    public long getLien();
    
    public long getLienCle();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur num�rique du lien du site.
     */
    public long getLienSite();

	/**
	 * Retourne le cr�ateur du lien.
	 *
	 * @return String Valeur du cr�ateur en caract�re.
	 */
	public String getLienCreateur();

    /**
     * Retourne le r�le.
     *
     * @return String Valeur num�rique du r�le.
     */
    public long getRole();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne la date de cr�ation
     *
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le cr�ateur
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getCreateur();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur num�rique du type de lien.
     */
    public long getTypeLien();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();

    /**
     * Test si le dossier est avec inscription.
     *
     * @return True si le dossier est avec inscription.
     */
    public Boolean isInscription();

    /**
     * Test si le dossier est modifiable.
     *
     * @return True si le dossier est modifiable.
     */
    public Boolean isModifiable();

    /**
     * Test si le dossier est nouvellement cr��e.
     *
     * @return True si le dossier est nouvellement cr��e.
     */
    public Boolean isNouveau();

    /**
     * Retourne les narrations.
     *
     * @return Collection Valeur des narrations.
     */
    public Collection getNarrations();

    /**
     * Retourne les dossier.
     *
     * @return Collection Valeur des dossier.
     */
    public Collection getDossiers();

    /**
     * Retourne les jeux.
     *
     * @return Collection Valeur des jeux.
     */
    public Collection getJeux();

    /**
     * Retourne les v�hicules.
     *
     * @return Collection Valeur des v�hicules.
     */
    public Collection getVehicules();

    /**
     * Retourne les soci�t�s.
     *
     * @return Collection Valeur des soci�t�s.
     */
    public Collection getSocietes();

    /**
     * Retourne les sujets.
     *
     * @return Collection Valeur des sujets.
     */
    public Collection getSujets();

    /**
     * Retourne les inscriptions.
     *
     * @return Collection Valeur des inscriptions.
     */
    public Collection getInscriptions();

    /**
     * Retourne les photos.
     *
     * @return Collection Valeur des photos.
     */
    public Collection getPhotos();

    /**
     * Retourne les suivis.
     *
     * @return Collection Valeur des suivis.
     */
    public Collection getSuivis();


    // Setters


    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine);

    /**
     * Affecte un genre.
     *
     * @param genre Valeur num�rique du genre.
     */
    public void setGenre(long genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur num�rique de la nature.
     */
    public void setNature(long nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(long type);

    /**
     * Affecte un type et une cat�gorie.
     *
     * @param type Valeur num�rique du type.
     */
    public void setTypeCategorie(long type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur num�rique de l'attribut fonde.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String numeroCardex);

    /**
     * Affecte la date de d�but.
     *
     * @param Timestamp Valeur de la date du d�but (yyyy-MM-dd).
     */
    public void setDateDebut(Timestamp dateDebut);

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(Timestamp dateFin);

    /**
     * Affecte un nombre de mois.
     *
     * @param mois Valeur du nombre de mois en caract�re.
     */
    public void setMois(String mois);

    /**
     * Affecte la premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte la troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3);

    /**
     * Affecte le type vid�o.
     *
     * @param typeVideo Valeur num�rique du type vid�o.
     */
    public void setTypeVideo(long typeVideo);

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String referenceVideo);

    /**
     * Affecte l'enregistrement num�rique.
     *
     * @param enregistrementNumerique Valeur num�rique .
     */
    public void setEnregistrementNumerique(String enregistrementNumerique);

    /**
     * Affecte l'enregistrement conserv�.
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setEnregistrementConserve(String enregistrementConserve);

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine);

    /**
     * Affecte la s�v�rit�.
     *
     * @param severite Valeur num�rique de la s�v�rit�.
     */
    public void setSeverite(long severite);

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(long hierarchie);

    /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroit(long endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur num�rique de la localisation.
     */
    public void setLocalisation(long localisation);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caract�re.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte la cle.
     *
     * @param cle Valeur num�rique de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(long site);

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur num�rique de la p�riode.
     */
    public void setPeriode(long periode);

    /**
     * Affecte le mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte le mot de passe courant.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setMotPasseCourant(String motPasseCourant);

    /**
     * Affecte la dur�e.
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(String duree);

    /**
     * Affecte de la date rapport�e.
     *
     * @param dateRapportee Valeur de la date rapport�e (yyyy-MM-dd).
     */
    public void setDateRapportee(Timestamp dateRapportee);

    /**
     * Affecte la classe.
     *
     * @param classe Valeur num�rique de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte la race.
     *
     * @param race Valeur num�rique de la classe.
     */
    public void setRace(long race);

    /**
     * Affecte de la date d'assignation.
     *
     * @param dateAssignation Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public void setDateAssignation(Timestamp dateAssignation);

    /**
     * Affecte de la date d'�v�nement.
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateEvenement(Timestamp dateEvenement);

    /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte de la date de cr�ation.
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte la quatri�me r�f�rence.
     *
     * @param reference4 Valeur de la quatri�me r�f�rence en caract�re.
     */
    public void setReference4(String reference);

    /**
     * Affecte la cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference);

    /**
     * Affecte la sixi�me r�f�rence.
     *
     * @param reference6 Valeur de la sixi�me r�f�rence en caract�re.
     */
    public void setReference6(String reference);

    /**
     * Affecte la septi�me r�f�rence.
     *
     * @param reference7 Valeur de la septi�me r�f�rence en caract�re.
     */
    public void setReference7(String reference);

    /**
     * Affecte l'attribut "fonde description".
     *
     * @param fondeDescription Valeur de l'attribut "fonde description" en
     * caract�re.
     */
    public void setFondeDescription(String fondeDescription);

    // Attributs du lien de dossiers :

    /**
     * Affecte le lien cle.
     *
     * @param lienCle Valeur num�rique du lien cle.
     */
    public void setLien(long lienCle);

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur num�rique du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur num�rique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

	/**
	 * Affecte un cr�ateur du lien.
	 *
	 * @param lienCreateur Valeur du cr�ateur en caract�re.
	 */
	public void setLienCreateur(String lienCreateur);

    /**
     * Affecte le r�le.
     *
     * @param role Valeur num�rique du r�le.
     */
    public void setRole(long role);

    /**
     * Affecte le type de lien.
     *
     * @param typeLien Valeur num�rique du type de lien.
     */
    public void setTypeLien(long typeLien);

    /**
     * D�termine si le dossier est avec inscription.
     *
     * @param isInscription Valeur si le dossier est avec inscription.
     */
    public void setInscription(Boolean isInscription);

    /**
     * D�termine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(Boolean isModifiable);

    /**
     * D�termine si le dossier est nouvellement cr�er.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNouveau(Boolean nouveau);

    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarration(Narration narration);

    /**
     * Ajoute un dossier.
     *
     * @param dossier Valeur du dossier.
     */
    public void addDossier(Dossier dossier);

    /**
     * Ajoute un jeu.
     *
     * @param jeu Valeur du jeu.
     */
    public void addJeu(String jeux);

    /**
     * Ajoute un v�hicule.
     *
     * @param vehicule Valeur du v�hicule.
     */
    public void addVehicule(Vehicule vehicule);

    /**
     * Ajoute une soci�t�.
     *
     * @param societe Valeur de la soci�t�.
     */
    public void addSociete(Societe societe);

    /**
     * Ajoute un sujet.
     *
     * @param sujet Valeur du sujet.
     */
    public void addSujet(Sujet sujet);

    /**
     * Ajoute une inscription.
     *
     * @param inscription Valeur de l'inscription.
     */
    public void addInscription(Inscription inscription);

    /**
     * Ajoute une photo.
     *
     * @param photo Valeur de la photo.
     */
    public void addPhoto(Photo photo);

    /**
     * Ajoute un suivi.
     *
     * @param suivi Valeur du suivi.
     */
    public void addSuivi(Suivi suivi);

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur de la confirmation du mot de passe en caract�re.
     */
    public String getConfirmationMotPasse();

    /**
     * Affecte la confirmation du mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
     * caract�re.
     */
    public void setConfirmationMotPasse(String confirmationMotPasse);

	/**
	 * Affecte une date de liaison.
	 *
	 * @param dateNaissance Valeur de la date de liaison (yyyy-MM-dd).
	 */
	public void setLienDateCreation(Timestamp lienDateCreation);

   /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * Affecte le cr�ateur.
     *
     * @param createur Valeur createur.
     */
    public void setCreateur(String createur);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);
    
    /**
     * Affecte l'entit�
     *
     * @param entite Valeur de la confirmation de l'entit�
     */
    public void setEntite(long entite);

	public Set<SousCategorieVO> getSousCategories();

	public void setSousCategories(Set<SousCategorieVO> sousCategories);
}