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
 * Les dossiers regroupent toutes les données reliées aux événements,
 * aux contrats ou aux décisions administratives internes.  La nature
 * ainsi que le nombre de classeurs varient en fonction de l'entité
 * administrative à laquelle l'utilisateur est rattaché ainsi
 * qu'en fonction de ses privilèges d'accès
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/04 00:25:27 $
 */
public interface Dossier extends LiaisonEntiteCardex, Modifiable, Business{


    // Getters
 

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur numérique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne le genre.
     *
     * @return long Valeur numérique du genre.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur numérique de la nature.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return long Valeur numérique du type.
     */
    public long getType();

    /**
     * Retourne le type et la catégorie.
     *
     * @return long Valeur numérique du type.
     */
    public long getTypeCategorie();

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur numérique de la catégorie.
     */
    public long getCategorie();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le statut.
     *
     * @return long Valeur numérique du statut.
     */
    public long getStatut();

    /**
     * Retourne l'attribut "fonde".
     *
     * @return long Valeur numérique de l'attribut "fonde".
     */
    public long getFonde();

    /**
     * Retourne le numéro de cardex.
     *
     * @return String Valeur du numéro de cardex en caractère.
     */
    public String getNumeroCardex();

	/**
	 * Retourne la date de création de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

    /**
     * Retourne la date de début.
     *
     * @return Timestamp Valeur de la date de début (yyyy-MM-dd).
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
     * @return String Valeur du nombre de mois en caractères.
     */
    public String getMois();

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractères.
     */
    public String getReference1();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractères.
     */
    public String getReference2();

    /**
     * Retourne la troisième référence.
     *
     * @return String Valeur de la troisième référence en caractères.
     */
    public String getReference3();

    /**
     * Retourne le type vidéo.
     *
     * @return long Valeur numérique du type vidéo.
     */
    public long getTypeVideo();

    /**
     * Retourne la référence vidéo.
     *
     * @return String Valeur de la référence vidéo en caractère.
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
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenantDescription();

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenantDescription(String intervenant);

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public long getOrigine();

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur numérique de la sévérité.
     */
    public long getSeverite();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur numérique de la confidentialité.
     */
    public long getConfidentialite();

    /**
     * Retourne la hierarchie.
     *
     * @return long Valeur numérique de la hierarchie.
     */
    public long getHierarchie();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur numérique de l'endroit.
     */
    public long getEndroit();

    /**
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur numérique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur numérique de la localisation.
     */
    public long getLocalisation();

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
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur numérique du site.
     */
    public long getSite();

    /**
     * Retourne la période.
     *
     * @return long Valeur numérique de la prériode.
     */
    public long getPeriode();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasse();

    /**
     * Retourne le mot de passe courant.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasseCourant();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree();

    /**
     * Retourne la date rapportée.
     *
     * @return Timestamp Valeur de la date rapportée (yyyy-MM-dd).
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
     * Retourne la date d'événement.
     *
     * @return Timestamp Valeur de la date d'événement (yyyy-MM-dd).
     */
    public Timestamp getDateEvenement();

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

    // Attributs du lien

    /**
     * Retourne le lien.
     *
     * @return String Valeur numérique du lien.
     */
    public long getLien();
    
    public long getLienCle();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur numérique du lien du site.
     */
    public long getLienSite();

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
    public long getRole();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne la date de création
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le créateur
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getCreateur();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur numérique du type de lien.
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
     * Test si le dossier est nouvellement créée.
     *
     * @return True si le dossier est nouvellement créée.
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
     * Retourne les véhicules.
     *
     * @return Collection Valeur des véhicules.
     */
    public Collection getVehicules();

    /**
     * Retourne les sociétés.
     *
     * @return Collection Valeur des sociétés.
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
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine);

    /**
     * Affecte un genre.
     *
     * @param genre Valeur numérique du genre.
     */
    public void setGenre(long genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur numérique de la nature.
     */
    public void setNature(long nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur numérique du type.
     */
    public void setType(long type);

    /**
     * Affecte un type et une catégorie.
     *
     * @param type Valeur numérique du type.
     */
    public void setTypeCategorie(long type);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur numérique de la catégorie.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur numérique du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur numérique de l'attribut fonde.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un numéro de cardex.
     *
     * @param numeroCardex Valeur du numéro de cardex en caractère.
     */
    public void setNumeroCardex(String numeroCardex);

    /**
     * Affecte la date de début.
     *
     * @param Timestamp Valeur de la date du début (yyyy-MM-dd).
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
    public void setTypeVideo(long typeVideo);

    /**
     * Affecte la référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo en caractère.
     */
    public void setReferenceVideo(String referenceVideo);

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
    public void setOrigine(long origine);

    /**
     * Affecte la sévérité.
     *
     * @param severite Valeur numérique de la sévérité.
     */
    public void setSeverite(long severite);

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur numérique de la hierarchie.
     */
    public void setHierarchie(long hierarchie);

    /**
     * Affecte la confidentialité.
     *
     * @param confidentialite Valeur numérique de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur numérique de l'endroit.
     */
    public void setEndroit(long endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur numérique de la localisation.
     */
    public void setLocalisation(long localisation);

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
    public void setCle(long cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(long site);

    /**
     * Affecte la période.
     *
     * @param periode Valeur numérique de la période.
     */
    public void setPeriode(long periode);

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
     * Affecte la durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree);

    /**
     * Affecte de la date rapportée.
     *
     * @param dateRapportee Valeur de la date rapportée (yyyy-MM-dd).
     */
    public void setDateRapportee(Timestamp dateRapportee);

    /**
     * Affecte la classe.
     *
     * @param classe Valeur numérique de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte la race.
     *
     * @param race Valeur numérique de la classe.
     */
    public void setRace(long race);

    /**
     * Affecte de la date d'assignation.
     *
     * @param dateAssignation Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public void setDateAssignation(Timestamp dateAssignation);

    /**
     * Affecte de la date d'événement.
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateEvenement(Timestamp dateEvenement);

    /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte de la date de création.
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte la quatrième référence.
     *
     * @param reference4 Valeur de la quatrième référence en caractère.
     */
    public void setReference4(String reference);

    /**
     * Affecte la cinquième référence.
     *
     * @param reference5 Valeur de la cinquième référence en caractère.
     */
    public void setReference5(String reference);

    /**
     * Affecte la sixième référence.
     *
     * @param reference6 Valeur de la sixième référence en caractère.
     */
    public void setReference6(String reference);

    /**
     * Affecte la septième référence.
     *
     * @param reference7 Valeur de la septième référence en caractère.
     */
    public void setReference7(String reference);

    /**
     * Affecte l'attribut "fonde description".
     *
     * @param fondeDescription Valeur de l'attribut "fonde description" en
     * caractère.
     */
    public void setFondeDescription(String fondeDescription);

    // Attributs du lien de dossiers :

    /**
     * Affecte le lien cle.
     *
     * @param lienCle Valeur numérique du lien cle.
     */
    public void setLien(long lienCle);

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur numérique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

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
    public void setRole(long role);

    /**
     * Affecte le type de lien.
     *
     * @param typeLien Valeur numérique du type de lien.
     */
    public void setTypeLien(long typeLien);

    /**
     * Détermine si le dossier est avec inscription.
     *
     * @param isInscription Valeur si le dossier est avec inscription.
     */
    public void setInscription(Boolean isInscription);

    /**
     * Détermine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(Boolean isModifiable);

    /**
     * Détermine si le dossier est nouvellement créer.
     *
     * @param isNew Valeur si le dossier est nouvellement créer.
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
     * Ajoute un véhicule.
     *
     * @param vehicule Valeur du véhicule.
     */
    public void addVehicule(Vehicule vehicule);

    /**
     * Ajoute une société.
     *
     * @param societe Valeur de la société.
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
     * @return String Valeur de la confirmation du mot de passe en caractère.
     */
    public String getConfirmationMotPasse();

    /**
     * Affecte la confirmation du mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
     * caractère.
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
     * Affecte le créateur.
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
     * Affecte l'entité
     *
     * @param entite Valeur de la confirmation de l'entité
     */
    public void setEntite(long entite);

	public Set<SousCategorieVO> getSousCategories();

	public void setSousCategories(Set<SousCategorieVO> sousCategories);
}