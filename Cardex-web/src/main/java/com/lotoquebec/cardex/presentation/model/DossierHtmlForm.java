package com.lotoquebec.cardex.presentation.model;

import java.util.List;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.16 $, $Date: 2002/03/18 21:47:41 $
 */
public interface DossierHtmlForm {


    // Getters


    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine();

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caract�re.
     */
    public String getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType();

    /**
     * Retourne le type et la cat�gorie.
     *
     * @return String Valeur num�rique du type.
     */
    public String getTypeCategorie();

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie();

	/**
	 * Retourne les �valuations associ�es.
	 *
	 * @return List
	 */
	public List getEvaluations();
    
    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne l'attribut "fonde".
     *
     * @return String Valeur de l'attribut "fonde" en caract�re.
     */
    public String getFonde();

    /**
     * Retourne le num�ro de cardex.
     *
     * @return NumeroCardex Valeur du num�ro de cardex.
     */
    public NumeroCardex getNumeroCardex();

    /**
     * Retourne le num�ro de cardex texte.
     *
     * @return NumeroCardex Valeur du num�ro de cardex.
     */
    public String getNumeroCardexTexte();

    /**
     * Retourne la date de d�but.
     *
     * @return String Valeur de la date de d�but en caract�re.
     */
    public String getDateDebut();
    
    public String getDateDebut16();
    
    public String getDateDebutLeft(int i);
    
    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caract�re.
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
     * Retourne l'heure de d�but.
     *
     * @return String Valeur de l'heure de d�but en caract�re.
     */
    public String getHeureDebut();

    /**
     * Retourne l'heure de fin.
     *
     * @return String Valeur de l'heure de fin en caract�re.
     */
    public String getHeureFin();

    /**
     * Retourne le nombre de mois.
     *
     * @return String Valeur du nombre de mois en caract�re.
     */
    public String getMois();

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1();

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�re.
     */
    public String getReference3();

    /**
     * Retourne le type de vid�o.
     *
     * @return String Valeur du type de vid�o en caract�re.
     */
    public String getTypeVideo();

    /**
     * Retourne la r�f�rence de vid�o.
     *
     * @return String Valeur de la r�f�rencede vid�o en caract�re.
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
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur num�rique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine();

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur num�rique de la s�v�rit�.
     */
    public String getSeverite();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur num�rique de la confidentialit�.
     */
    public String getConfidentialite();

    /**
     * Retourne la hierarchie.
     *
     * @return long Valeur num�rique de la hierarchie.
     */
    public String getHierarchie();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public String getLocalisation();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public String getEndroitDescription();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public String getLocalisationDescription();

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
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public String getSite();

    /**
     * Retourne la p�riode.
     *
     * @return long Valeur num�rique de la pr�riode.
     */
    public String getPeriode();

    /**
     * Retourne le mot de passe courant.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasseCourant();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasse();

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur de la confirmation du mot de passe en caract�re.
     */
    public String getConfirmationMotPasse();

    /**
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public String getDuree();

    /**
     * Retourne la date rapport�e.
     *
     * @return String Valeur de la date rapport�e en caract�re.
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
     * @return String Valeur de la date d'assignation en caract�re.
     */
    public String getDateAssignation();

    /**
     * Retourne la date d'�v�nement.
     *
     * @return String Valeur de la date d'�v�nement en caract�re.
     */
    public String getDateEvenement();

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

    /**
     * Retourne le lien.
     *
     * @return String Valeur num�rique du lien.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur num�rique du lien du site.
     */
    public String getLienSite();

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
    public String getRole();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le cr�ateur du dossier.
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getCreateur();

    /**
     * Retourne la date de cr�ation du dossier.
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateCreation();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur num�rique du type de lien.
     */
    public String getTypeLien();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();


    /**
     * Retourne l'entit�.
     *
     * @return String Valeur num�rique de l'entit�.
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
     * Test si le dossier est nouvellement cr��e.
     *
     * @return True si le dossier est nouvellement cr��e.
     */
    public boolean isNew();

    public boolean getImprimerLogoSCQ();
    
    // Setters


    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(String origine);

    /**
     * Affecte un genre.
     *
     * @param genre Valeur num�rique du genre.
     */
    public void setGenre(String genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur num�rique de la nature.
     */
    public void setNature(String nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(String type);

    /**
     * Affecte un type et une cat�gorie.
     *
     * @param type Valeur num�rique du type.
     */
    public void setTypeCategorie(String type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String noDossier);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique du statut.
     */
    public void setStatut(String statut);

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur num�rique de l'attribut "fonde".
     */
    public void setFonde(String fonde);

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex.
     */
    public void setNumeroCardex(NumeroCardex numeroCardex);

    /**
     * Affecte un num�ro de cardex.
     *
     * @param stringNumeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String numeroCardex);

    /**
     * Affecte un num�ro de cardex en texte.
     *
     * @param stringNumeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardexTexte(String numeroCardex);

    /**
     * Affecte la date de d�but.
     *
     * @param dateDebut Valeur de la date du d�but (yyyy-MM-dd).
     */
    public void setDateDebut(String dateDebut);

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(String dateFin);

    /**
     * Affecte l'heure de d�but.
     *
     * @param heureDebut Valeur de l'heure du d�but (hh:mm:ss).
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
    public void setTypeVideo(String type);

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String reference);

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
    public void setOrigine(String origine);

    /**
     * Affecte la s�v�rit�.
     *
     * @param severite Valeur num�rique de la s�v�rit�.
     */
    public void setSeverite(String severite);

    /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(String hierarchie);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur num�rique de la localisation.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroitDescription(String endroitDescription);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur num�rique de la localisation.
     */
    public void setLocalisationDescription(String localisationDescription);

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
    public void setCle(String cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(String site);

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur num�rique de la p�riode.
     */
    public void setPeriode(String periode);

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
     * Affecte la confirmation du mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en caract�re.
     */
    public void setConfirmationMotPasse(String confirmationMotPasse);

    /**
     * Affecte la dur�e.
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(String duree);

    /**
     * Affecte de la date rapport�e.
     *
     * @param dateRapportee Valeur de la date rapport�e en caract�re.
     */
    public void setDateRapportee(String dateRapportee);

    /**
     * Affecte la classe.
     *
     * @param classe Valeur num�rique de la classe.
     */
    public void setClasse(String classe);

    /**
     * Affecte la race.
     *
     * @param race Valeur num�rique de la classe.
     */
    public void setRace(String race);

    /**
     * Affecte de la date d'assignation.
     *
     * @param dateAssignation Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public void setDateAssignation(String dateAssignation);

    /**
     * Affecte la date d'�v�nement.
     *
     * @param dateEvenement Valeur la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateEvenement(String dateEvenement);

    /**
     * Affecte la quatri�me r�f�rence.
     *
     * @param reference4 Valeur de la quatri�me r�f�rence en caract�re.
     */
    public void setReference4(String reference4);

    /**
     * Affecte la cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference5);

    /**
     * Affecte la sixi�me r�f�rence.
     *
     * @param reference6 Valeur de la sixi�me r�f�rence en caract�re.
     */
    public void setReference6(String reference6);

    /**
     * Affecte la septi�me r�f�rence.
     *
     * @param reference7 Valeur de la septi�me r�f�rence en caract�re.
     */
    public void setReference7(String reference7);

    /**
     * Affecte l'attribut "fonde description".
     *
     * @param fondeDescription Valeur de l'attribut "fonde description" en
     * caract�re.
     */
    public void setFondeDescription(String fondeDescription);

    /**
     * Affecte le lien cle.
     *
     * @param lien Valeur num�rique du lien cle.
     */
    public void setLien(String lien);

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur num�rique du lien du site.
     */
    public void setLienSite(String lienSite);

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
     * Affecte la date de cr�ation.
     *
     * @param dateChangement Valeur dateCreation.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte le cr�ateur du dossier.
     *
     * @param createur Valeur createur.
     */
    public void setCreateur(String createur);

    /**
     * Affecte le type de lien.
     *
     * @param typeLien Valeur num�rique du type de lien.
     */
    public void setTypeLien(String typeLien);

    /**
     * Affecte l'entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(String entite);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur num�rique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

    /**
     * D�termine si le dossier est avec inscription.
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
	 * @param lienDateCreation Valeur de la date de liaison en caract�re.
	 */
	public void setLienDateCreation(String lienDateCreation);

    /**
     * D�termine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * D�termine si le dossier est nouvellement cr�er.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNew(boolean isNew);

	/**
	 * 
	 */
	public void init(CardexAuthenticationSubject subject);

}

