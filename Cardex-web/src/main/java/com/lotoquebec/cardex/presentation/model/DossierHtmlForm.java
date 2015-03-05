package com.lotoquebec.cardex.presentation.model;

import java.util.List;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.16 $, $Date: 2002/03/18 21:47:41 $
 */
public interface DossierHtmlForm {


    // Getters


    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine();

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caractère.
     */
    public String getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public String getType();

    /**
     * Retourne le type et la catégorie.
     *
     * @return String Valeur numérique du type.
     */
    public String getTypeCategorie();

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie();

	/**
	 * Retourne les évaluations associées.
	 *
	 * @return List
	 */
	public List getEvaluations();
    
    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne l'attribut "fonde".
     *
     * @return String Valeur de l'attribut "fonde" en caractère.
     */
    public String getFonde();

    /**
     * Retourne le numéro de cardex.
     *
     * @return NumeroCardex Valeur du numéro de cardex.
     */
    public NumeroCardex getNumeroCardex();

    /**
     * Retourne le numéro de cardex texte.
     *
     * @return NumeroCardex Valeur du numéro de cardex.
     */
    public String getNumeroCardexTexte();

    /**
     * Retourne la date de début.
     *
     * @return String Valeur de la date de début en caractère.
     */
    public String getDateDebut();
    
    public String getDateDebut16();
    
    public String getDateDebutLeft(int i);
    
    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caractère.
     */
    public String getDateFin();

    public String getDateFin10();
    
	/**
	 * Retourne la date de liaison.
	 *
	 * @return String date de liaison
	 */
	public String getLienDateCreation();

    /**
     * Retourne l'heure de début.
     *
     * @return String Valeur de l'heure de début en caractère.
     */
    public String getHeureDebut();

    /**
     * Retourne l'heure de fin.
     *
     * @return String Valeur de l'heure de fin en caractère.
     */
    public String getHeureFin();

    /**
     * Retourne le nombre de mois.
     *
     * @return String Valeur du nombre de mois en caractère.
     */
    public String getMois();

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne la troisième référence.
     *
     * @return String Valeur de la troisième référence en caractère.
     */
    public String getReference3();

    /**
     * Retourne le type de vidéo.
     *
     * @return String Valeur du type de vidéo en caractère.
     */
    public String getTypeVideo();

    /**
     * Retourne la référence de vidéo.
     *
     * @return String Valeur de la référencede vidéo en caractère.
     */
    public String getReferenceVideo();

    /**
     * Retourne si un enregistrement existe.
     *
     * @return String Valeur en caractère.
     */
    public String getEnregistrementNumerique();

    /**
     * Retourne si un enregistrement est conservé.
     *
     * @return String Valeur en caractère.
     */
    public String getEnregistrementConserve();

    /**
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur numérique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine();

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur numérique de la sévérité.
     */
    public String getSeverite();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur numérique de la confidentialité.
     */
    public String getConfidentialite();

    /**
     * Retourne la hierarchie.
     *
     * @return long Valeur numérique de la hierarchie.
     */
    public String getHierarchie();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur numérique de l'endroit.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur numérique de la localisation.
     */
    public String getLocalisation();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur numérique de l'endroit.
     */
    public String getEndroitDescription();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur numérique de la localisation.
     */
    public String getLocalisationDescription();

    /**
     * Retourne la descriptif.
     *
     * @return String Valeur du descriptif en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne la cle.
     *
     * @return long Valeur numérique de la cle.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur numérique du site.
     */
    public String getSite();

    /**
     * Retourne la période.
     *
     * @return long Valeur numérique de la prériode.
     */
    public String getPeriode();

    /**
     * Retourne le mot de passe courant.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasseCourant();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasse();

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur de la confirmation du mot de passe en caractère.
     */
    public String getConfirmationMotPasse();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree();

    /**
     * Retourne la date rapportée.
     *
     * @return String Valeur de la date rapportée en caractère.
     */
    public String getDateRapportee();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public String getClasse();

    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public String getRace();

    /**
     * Retourne la date d'assignation.
     *
     * @return String Valeur de la date d'assignation en caractère.
     */
    public String getDateAssignation();

    /**
     * Retourne la date d'événement.
     *
     * @return String Valeur de la date d'événement en caractère.
     */
    public String getDateEvenement();

    /**
     * Retourne la quatrième référence.
     *
     * @return String Valeur de la quatrième référence en caractère.
     */
    public String getReference4();

    /**
     * Retourne la cinquième référence.
     *
     * @return String Valeur de la cinquième référence en caractère.
     */
    public String getReference5();

    /**
     * Retourne la sixième référence.
     *
     * @return String Valeur de la sixième référence en caractère.
     */
    public String getReference6();

    /**
     * Retourne la septième référence.
     *
     * @return String Valeur de la septième référence en caractère.
     */
    public String getReference7();

    /**
     * Retourne l'attribut "fonde description".
     *
     * @return String Valeur de l'attribut "fonde description" en caractère.
     */
    public String getFondeDescription();

    /**
     * Retourne le lien.
     *
     * @return String Valeur numérique du lien.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur numérique du lien du site.
     */
    public String getLienSite();

	/**
	 * Retourne le créateur du lien.
	 *
	 * @return String Valeur du créateur en caractère.
	 */
	public String getLienCreateur();

    /**
     * Retourne le rôle.
     *
     * @return String Valeur numérique du rôle.
     */
    public String getRole();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le créateur du dossier.
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getCreateur();

    /**
     * Retourne la date de création du dossier.
     *
     * @return String Valeur numérique de date.
     */
    public String getDateCreation();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur numérique du type de lien.
     */
    public String getTypeLien();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();


    /**
     * Retourne l'entité.
     *
     * @return String Valeur numérique de l'entité.
     */
    public String getEntite();

    /**
     * Test si le dossier est avec inscription.
     *
     * @return True si le dossier est avec inscription.
     */
    public boolean isInscription();

    /**
     * Test si le dossier est modifiable.
     *
     * @return True si le dossier est modifiable.
     */
    public boolean isModifiable();

    /**
     * Test si le dossier est nouvellement créée.
     *
     * @return True si le dossier est nouvellement créée.
     */
    public boolean isNew();

    public boolean getImprimerLogoSCQ();
    
    // Setters


    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(String origine);

    /**
     * Affecte un genre.
     *
     * @param genre Valeur numérique du genre.
     */
    public void setGenre(String genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur numérique de la nature.
     */
    public void setNature(String nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur numérique du type.
     */
    public void setType(String type);

    /**
     * Affecte un type et une catégorie.
     *
     * @param type Valeur numérique du type.
     */
    public void setTypeCategorie(String type);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur numérique de la catégorie.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String noDossier);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur numérique du statut.
     */
    public void setStatut(String statut);

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur numérique de l'attribut "fonde".
     */
    public void setFonde(String fonde);

    /**
     * Affecte un numéro de cardex.
     *
     * @param numeroCardex Valeur du numéro de cardex.
     */
    public void setNumeroCardex(NumeroCardex numeroCardex);

    /**
     * Affecte un numéro de cardex.
     *
     * @param stringNumeroCardex Valeur du numéro de cardex en caractère.
     */
    public void setNumeroCardex(String numeroCardex);

    /**
     * Affecte un numéro de cardex en texte.
     *
     * @param stringNumeroCardex Valeur du numéro de cardex en caractère.
     */
    public void setNumeroCardexTexte(String numeroCardex);

    /**
     * Affecte la date de début.
     *
     * @param dateDebut Valeur de la date du début (yyyy-MM-dd).
     */
    public void setDateDebut(String dateDebut);

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(String dateFin);

    /**
     * Affecte l'heure de début.
     *
     * @param heureDebut Valeur de l'heure du début (hh:mm:ss).
     */
    public void setHeureDebut(String heureDebut);

    /**
     * Affecte l'heure de fin.
     *
     * @param heureFin Valeur de l'heure du fin (hh:mm:ss).
     */
    public void setHeureFin(String heureFin);

    /**
     * Affecte un nombre de mois.
     *
     * @param mois Valeur du nombre de mois en caractère.
     */
    public void setMois(String mois);

    /**
     * Affecte la première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte la troisième référence.
     *
     * @param reference3 Valeur de la troisième référence en caractère.
     */
    public void setReference3(String reference3);

    /**
     * Affecte le type vidéo.
     *
     * @param typeVideo Valeur numérique du type vidéo.
     */
    public void setTypeVideo(String type);

    /**
     * Affecte la référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo en caractère.
     */
    public void setReferenceVideo(String reference);

    /**
     * Affecte l'enregistrement numérique.
     *
     * @param enregistrementNumerique Valeur numérique .
     */
    public void setEnregistrementNumerique(String enregistrementNumerique);

    /**
     * Affecte l'enregistrement conservé.
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setEnregistrementConserve(String enregistrementConserve);

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine);

    /**
     * Affecte la sévérité.
     *
     * @param severite Valeur numérique de la sévérité.
     */
    public void setSeverite(String severite);

    /**
     * Affecte la confidentialité.
     *
     * @param confidentialite Valeur numérique de la confidentialité.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur numérique de la hierarchie.
     */
    public void setHierarchie(String hierarchie);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur numérique de l'endroit.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur numérique de la localisation.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur numérique de l'endroit.
     */
    public void setEndroitDescription(String endroitDescription);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur numérique de la localisation.
     */
    public void setLocalisationDescription(String localisationDescription);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caractère.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte la cle.
     *
     * @param cle Valeur numérique de la cle.
     */
    public void setCle(String cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(String site);

    /**
     * Affecte la période.
     *
     * @param periode Valeur numérique de la période.
     */
    public void setPeriode(String periode);

    /**
     * Affecte le mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caractère.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte le mot de passe courant.
     *
     * @param motPasse Valeur du mot de passe en caractère.
     */
    public void setMotPasseCourant(String motPasseCourant);

    /**
     * Affecte la confirmation du mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en caractère.
     */
    public void setConfirmationMotPasse(String confirmationMotPasse);

    /**
     * Affecte la durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree);

    /**
     * Affecte de la date rapportée.
     *
     * @param dateRapportee Valeur de la date rapportée en caractère.
     */
    public void setDateRapportee(String dateRapportee);

    /**
     * Affecte la classe.
     *
     * @param classe Valeur numérique de la classe.
     */
    public void setClasse(String classe);

    /**
     * Affecte la race.
     *
     * @param race Valeur numérique de la classe.
     */
    public void setRace(String race);

    /**
     * Affecte de la date d'assignation.
     *
     * @param dateAssignation Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public void setDateAssignation(String dateAssignation);

    /**
     * Affecte la date d'événement.
     *
     * @param dateEvenement Valeur la date d'événement (yyyy-MM-dd).
     */
    public void setDateEvenement(String dateEvenement);

    /**
     * Affecte la quatrième référence.
     *
     * @param reference4 Valeur de la quatrième référence en caractère.
     */
    public void setReference4(String reference4);

    /**
     * Affecte la cinquième référence.
     *
     * @param reference5 Valeur de la cinquième référence en caractère.
     */
    public void setReference5(String reference5);

    /**
     * Affecte la sixième référence.
     *
     * @param reference6 Valeur de la sixième référence en caractère.
     */
    public void setReference6(String reference6);

    /**
     * Affecte la septième référence.
     *
     * @param reference7 Valeur de la septième référence en caractère.
     */
    public void setReference7(String reference7);

    /**
     * Affecte l'attribut "fonde description".
     *
     * @param fondeDescription Valeur de l'attribut "fonde description" en
     * caractère.
     */
    public void setFondeDescription(String fondeDescription);

    /**
     * Affecte le lien cle.
     *
     * @param lien Valeur numérique du lien cle.
     */
    public void setLien(String lien);

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setLienSite(String lienSite);

	/**
	 * Affecte un créateur du lien.
	 *
	 * @param lienCreateur Valeur du créateur en caractère.
	 */
	public void setLienCreateur(String lienCreateur);

    /**
     * Affecte le rôle.
     *
     * @param role Valeur numérique du rôle.
     */
    public void setRole(String role);

    /**
     * Affecte la date du changement (pour l'audit).
     *
     * @param dateChangement Valeur dateChangement.
     */
    public void setDateChangement(String dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * Affecte la date de création.
     *
     * @param dateChangement Valeur dateCreation.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte le créateur du dossier.
     *
     * @param createur Valeur createur.
     */
    public void setCreateur(String createur);

    /**
     * Affecte le type de lien.
     *
     * @param typeLien Valeur numérique du type de lien.
     */
    public void setTypeLien(String typeLien);

    /**
     * Affecte l'entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(String entite);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur numérique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

    /**
     * Détermine si le dossier est avec inscription.
     *
     * @param isInscription Valeur si le dossier est avec inscription.
     */
    public void setInscription(boolean isInscription);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);

	/**
	 * Affecte une date de liaison.
	 *
	 * @param lienDateCreation Valeur de la date de liaison en caractère.
	 */
	public void setLienDateCreation(String lienDateCreation);

    /**
     * Détermine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Détermine si le dossier est nouvellement créer.
     *
     * @param isNew Valeur si le dossier est nouvellement créer.
     */
    public void setNew(boolean isNew);

	/**
	 * 
	 */
	public void init(CardexAuthenticationSubject subject);

}

