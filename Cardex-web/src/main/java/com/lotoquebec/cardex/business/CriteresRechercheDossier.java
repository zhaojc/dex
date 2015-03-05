package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Les critères de recherche dossiers regroupent toutes les données reliées aux
 * événements relatifs aux recherches, aux contrats ou aux décisions
 * administratives internes.  La nature ainsi que le nombre de classeurs
 * varient en fonction de l'entité administrative à laquelle l'utilisateur est
 * rattaché ainsi qu'en fonction de ses privilèges d'accès
 *
 * @author $Author: pcaron $
 * @version $Revison: $, $Date: 2002/02/22 21:02:10 $
 */
public interface CriteresRechercheDossier extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur numérique de l'endroit.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur numérique de la localisation.
     */
    public long getLocalisation();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur numérique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne le site applicable.
     *
     * @return long Valeur numérique du site applicable.
     */
    public long getSiteApplicable();

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
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caractère.
     */
    public long getJeu();


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
     * Retourne le statut.
     *
     * @return long Valeur numérique du statut.
     */
    public long getStatut();

    /**
     * Retourne le service.
     *
     * @return long Valeur du service en caractère.
     */
    public long getService();

    /**
     * Retourne l'attribut fonde.
     *
     * @return long Valeur numérique de l'attribut fonde.
     */
    public long getFonde();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le numéro de cardex.
     *
     * @return String Valeur du numéro de cardex en caractère.
     */
    public String getNumeroCardex();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le numéro de fiche sujet.
     *
     * @return String Valeur du numéro de fiche sujet en caractère.
     */
    public String getNumeroFicheSujet();

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
     * Retourne la cinquième référence.
     *
     * @return String Valeur de la cinquième référence en caractère.
     */
    public String getReference5();

    /**
     * Retourne la date de début.
     *
     * @return Timestamp Valeur de la date de début (yyyy-MM-dd).
     */
    public Timestamp getDateDebutDu();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin (yyyy-MM-dd).
     */
    public Timestamp getDateFinDu();

    /**
     * Retourne la date de début au.
     *
     * @return Timestamp Valeur de la date de début au (yyyy-MM-dd).
     */
    public Timestamp getDateDebutAu();

    /**
     * Retourne la date de fin au.
     *
     * @return Timestamp Valeur de la date de fin au (yyyy-MM-dd).
     */
    public Timestamp getDateFinAu();

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
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public long getOrigine();

    /**
     * Retourne la référence vidéo (numéro séquentiel).
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
     * Retourne la date de création du.
     *
     * @return Timestamp Valeur de la date de création du (yyyy-MM-dd).
     */
    public Timestamp getDateCreationDu();

    /**
     * Retourne la date de création au.
     *
     * @return Timestamp Valeur de la date de création au (yyyy-MM-dd).
     */
    public Timestamp getDateCreationAu();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne la période.
     *
     * @return long Valeur numérique de la période.
     */
    public long getPeriode();

    /**
     * Retourne le critère pour la recherche des sous-catégories seulement.
     *
     * @return String Valeur du rapport.
     */
    public String getRechercherSousCategorie();

    /**
     * Retourne le critère pour la recherche des sous-catégories et des dossiers
     *
     * @return String Valeur du rapport.
     */
    public String getRechercherTous();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();


    // Setters


    /**
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite);

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
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(long site);

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur numérique du site applicable.
     */
    public void setSiteApplicable(long site);

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
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur numérique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur numérique de la catégorie.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur numérique du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte le service.
     *
     * @param service Valeur du service en caractère.
     */
    public void setService(long service);

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur numérique de l'attribut fonde.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un numéro de cardex.
     *
     * @param numeroCardex Valeur du numéro de cardex en caractère.
     */
    public void setNumeroCardex(String noCardex);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String noDossier);

    /**
     * Affecte un numéro de fiche sujet.
     *
     * @param numeroFicheSujet Valeur du numéro de fiche sujet en caractère.
     */
    public void setNumeroFicheSujet(String noFicheSujet);

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
     * Affecte la cinquième référence.
     *
     * @param reference5 Valeur de la cinquième référence en caractère.
     */
    public void setReference5(String reference5);

    /**
     * Affecte la date de début du.
     *
     * @param dateDebutDu Valeur de la date de début du (yyyy-MM-dd).
     */
    public void setDateDebutDu(Timestamp dateDebutDu);

    /**
     * Affecte la date de fin du.
     *
     * @param dateFinDu Valeur de la date de fin du (yyyy-MM-dd).
     */
    public void setDateFinDu(Timestamp dateFinDu);

    /**
     * Affecte la date de début au.
     *
     * @param dateDebutAu Valeur de la date de début au (yyyy-MM-dd).
     */
    public void setDateDebutAu(Timestamp dateDebutAu);

    /**
     * Affecte la date de fin au.
     *
     * @param dateFinAu Valeur de la date de fin au (yyyy-MM-dd).
     */
    public void setDateFinAu(Timestamp dateFinAu);

    /**
     * Affecte la sévérité.
     *
     * @param severite Valeur numérique de la sévérité.
     */
    public void setSeverite(long severite);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caractère.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caractère.
     */
    public void setJeu(long jeu);

    /**
     * Affecte la confidentialité.
     *
     * @param confidentialite Valeur numérique de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caractère.
     */
    public void setOrigine(long origine);

    /**
     * Affecte la référence vidéo.
     *
     * @param referenceVideo Valeur numérique de la référence vidéo.
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
     * Affecte la date de création du.
     *
     * @param dateCreationDu Valeur de la date de création du (yyyy-MM-dd).
     */
    public void setDateCreationDu(Timestamp dateCreationDu);

    /**
     * Affecte la date de création au.
     *
     * @param dateCreationAu Valeur de la date de création au (yyyy-MM-dd).
     */
    public void setDateCreationAu(Timestamp dateCreationAu);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur numérique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte la période.
     *
     * @param periode Valeur numérique de la période.
     */
    public void setPeriode(long periode);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);
    
    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximum);
    
	public void setRechercherSousCategorie(String rechercherSousCategorie);
		
	public void setRechercherTous(String rechercherTous);

	public int getSequence();

	public void setSequence(int sequence);

    public String getSequenceNumeroCardex();

    public void setSequenceNumeroCardex(String sequence);
	
    public long getSiteNumeroCardex();

    public String getDateNumeroCardex();
    
    public void setDateNumeroCardex(String dateNumeroCardex);

    public void setSiteNumeroCardex(long site);
    
	public long getClasse();

	public void setClasse(long classe);

	public long getTypeJeu();

	public void setTypeJeu(long typeJeu);
	
}