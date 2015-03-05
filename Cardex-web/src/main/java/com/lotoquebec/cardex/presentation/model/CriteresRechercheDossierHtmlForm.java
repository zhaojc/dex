package com.lotoquebec.cardex.presentation.model;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de dossier.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.12 $, $Date: 2002/02/21 19:39:11 $
 */
public interface CriteresRechercheDossierHtmlForm extends CriteresRecherche{

    /**
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine();

    /**
     * Retourne le site applicable.
     *
     * @return String Valeur du site applicable en caractère.
     */
    public String getSiteApplicable();

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
     * Retourne le type et la catégorie.
     *
     * @return String Valeur numérique du type.
     */
    public String getTypeCategorie();

    /**
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur numérique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne le service.
     *
     * @return String Valeur du service en caractère.
     */
    public String getService();

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
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le numéro de cardex.
     *
     * @return NumeroCardex Valeur du numéro de cardex.
     */
    public NumeroCardex getNumeroCardex();

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
     * Retourne la cinquième référence.
     *
     * @return String Valeur de la cinquième référence en caractère.
     */
    public String getReference5();

    /**
     * Retourne la date de début du.
     *
     * @return String Valeur de la date de début du en caractère.
     */
    public String getDateDebutDu();

    /**
     * Retourne la date de fin du.
     *
     * @return String Valeur de la date de fin du en caractère.
     */
    public String getDateFinDu();

    /**
     * Retourne la date de début au.
     *
     * @return String Valeur de la date de début au en caractère.
     */
    public String getDateDebutAu();

    /**
     * Retourne la date de fin au.
     *
     * @return String Valeur de la date de fin au en caractère.
     */
    public String getDateFinAu();

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine();

    /**
     * Retourne la référence vidéo.
     *
     * @return String Valeur de la référence vidéo en caractère.
     */
    public String getReferenceVideo();

    /**
     * Retourne la date de création du.
     *
     * @return String Valeur de la date de création du en caractère.
     */
    public String getDateCreationDu();

    /**
     * Retourne la date de création au.
     *
     * @return String Valeur de la date de création au en caractère.
     */
    public String getDateCreationAu();


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
     * Retourne le jeu.
     *
     * @return String Valeur du jeu en caractère.
     */
    public String getJeu();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();

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

    // Setters


    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable en caractère.
     */
    public void setSiteApplicable(String siteApplicable);

    /**
     * Affecte un genre.
     *
     * @param genre Valeur du genre en caractère.
     */
    public void setGenre(String genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caractère.
     */
    public void setNature(String nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setType(String type);

    /**
     * Affecte un type et une catégorie.
     *
     * @param type Valeur numérique du type.
     */
    public void setTypeCategorie(String type);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur numérique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur d'une catégorie en caractère.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur d'un statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur de l'attribut "fonde" en caractère.
     */
    public void setFonde(String fonde);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

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
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un numéro de fiche sujet.
     *
     * @param numeroFicheSujet Valeur du numéro de fiche sujet en caractère.
     */
    public void setNumeroFicheSujet(String numeroFicheSujet);

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisième référence.
     *
     * @param reference3 Valeur de la troisième référence en caractère.
     */
    public void setReference3(String reference3);

    /**
     * Affecte une cinquième référence.
     *
     * @param reference5 Valeur de la cinquième référence en caractère.
     */
    public void setReference5(String reference5);

    /**
     * Affecte une date de début du.
     *
     * @param dateDebutDu Valeur de la date de début du en caractère.
     */
    public void setDateDebutDu(String dateDebutDu);

    /**
     * Affecte une date de fin du.
     *
     * @param dateFinDu Valeur de la date de fin du en caractère.
     */
    public void setDateFinDu(String dateFinDu);

    /**
     * Affecte une date de début au.
     *
     * @param dateDebutAu Valeur de la date de début au en caractère.
     */
    public void setDateDebutAu(String dateDebutAu);

    /**
     * Affecte une date de fin au.
     *
     * @param dateFinAu Valeur de la date de fin au en caractère.
     */
    public void setDateFinAu(String dateFinAu);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de la origine en caractère.
     */
    public void setOrigine(String origine);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caractère.
     */
    public void setJeu(String jeu);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caractère.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte le service.
     *
     * @param service Valeur du service en caractère.
     */
    public void setService(String service);

    /**
     * Affecte une référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo en caractère.
     */
    public void setReferenceVideo(String reference);

    /**
     * Affecte une date de création du.
     *
     * @param dateCreationDu Valeur de la date de création du en caractère.
     */
    public void setDateCreationDu(String dateCreationDu);

    /**
     * Affecte une date de création au.
     *
     * @param dateCreationAu Valeur de la date de création au en caractère.
     */
    public void setDateCreationAu(String dateCreationAu);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur numérique de l'endroit.
     */
    public void setEndroit(String endroit);

    /**
     * Retourne la localisation.
     *
     * @return long Valeur numérique de la localisation.
     */
    public void setLocalisation(String localisation);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);

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
     * Affecte le critère rechercherSousCategorie.
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setRechercherSousCategorie(String rechercherSousCategorie);
    
    /**
     * Affecte le critère rechercherTous
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setRechercherTous(String rechercherTous);
    
	public int getSequence();

	public void setSequence(int sequence);

    public String getSequenceNumeroCardex();

    public void setSequenceNumeroCardex(String sequence);
	
    public String getSiteNumeroCardex();

    public String getDateNumeroCardex();

    public void setSiteNumeroCardex(String site);
    
    public String getClasse();

    public void setClasse(String classe);

	public boolean isLienRoleRequis();

	public void setLienRoleRequis(boolean lienRoleRequis);

}