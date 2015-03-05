package com.lotoquebec.cardex.presentation.model;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de dossier.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.12 $, $Date: 2002/02/21 19:39:11 $
 */
public interface CriteresRechercheDossierHtmlForm extends CriteresRecherche{

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine();

    /**
     * Retourne le site applicable.
     *
     * @return String Valeur du site applicable en caract�re.
     */
    public String getSiteApplicable();

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
     * Retourne le type et la cat�gorie.
     *
     * @return String Valeur num�rique du type.
     */
    public String getTypeCategorie();

    /**
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur num�rique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne le service.
     *
     * @return String Valeur du service en caract�re.
     */
    public String getService();

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
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le num�ro de cardex.
     *
     * @return NumeroCardex Valeur du num�ro de cardex.
     */
    public NumeroCardex getNumeroCardex();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne le num�ro de fiche sujet.
     *
     * @return String Valeur du num�ro de fiche sujet en caract�re.
     */
    public String getNumeroFicheSujet();

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
     * Retourne la cinqui�me r�f�rence.
     *
     * @return String Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public String getReference5();

    /**
     * Retourne la date de d�but du.
     *
     * @return String Valeur de la date de d�but du en caract�re.
     */
    public String getDateDebutDu();

    /**
     * Retourne la date de fin du.
     *
     * @return String Valeur de la date de fin du en caract�re.
     */
    public String getDateFinDu();

    /**
     * Retourne la date de d�but au.
     *
     * @return String Valeur de la date de d�but au en caract�re.
     */
    public String getDateDebutAu();

    /**
     * Retourne la date de fin au.
     *
     * @return String Valeur de la date de fin au en caract�re.
     */
    public String getDateFinAu();

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite();

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine();

    /**
     * Retourne la r�f�rence vid�o.
     *
     * @return String Valeur de la r�f�rence vid�o en caract�re.
     */
    public String getReferenceVideo();

    /**
     * Retourne la date de cr�ation du.
     *
     * @return String Valeur de la date de cr�ation du en caract�re.
     */
    public String getDateCreationDu();

    /**
     * Retourne la date de cr�ation au.
     *
     * @return String Valeur de la date de cr�ation au en caract�re.
     */
    public String getDateCreationAu();


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
     * Retourne le jeu.
     *
     * @return String Valeur du jeu en caract�re.
     */
    public String getJeu();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();

    /**
     * Retourne le crit�re pour la recherche des sous-cat�gories seulement.
     *
     * @return String Valeur du rapport.
     */
    public String getRechercherSousCategorie();

    /**
     * Retourne le crit�re pour la recherche des sous-cat�gories et des dossiers
     *
     * @return String Valeur du rapport.
     */
    public String getRechercherTous();

    // Setters


    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable en caract�re.
     */
    public void setSiteApplicable(String siteApplicable);

    /**
     * Affecte un genre.
     *
     * @param genre Valeur du genre en caract�re.
     */
    public void setGenre(String genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caract�re.
     */
    public void setNature(String nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setType(String type);

    /**
     * Affecte un type et une cat�gorie.
     *
     * @param type Valeur num�rique du type.
     */
    public void setTypeCategorie(String type);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur num�rique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur d'une cat�gorie en caract�re.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur d'un statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur de l'attribut "fonde" en caract�re.
     */
    public void setFonde(String fonde);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

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
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un num�ro de fiche sujet.
     *
     * @param numeroFicheSujet Valeur du num�ro de fiche sujet en caract�re.
     */
    public void setNumeroFicheSujet(String numeroFicheSujet);

    /**
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3);

    /**
     * Affecte une cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference5);

    /**
     * Affecte une date de d�but du.
     *
     * @param dateDebutDu Valeur de la date de d�but du en caract�re.
     */
    public void setDateDebutDu(String dateDebutDu);

    /**
     * Affecte une date de fin du.
     *
     * @param dateFinDu Valeur de la date de fin du en caract�re.
     */
    public void setDateFinDu(String dateFinDu);

    /**
     * Affecte une date de d�but au.
     *
     * @param dateDebutAu Valeur de la date de d�but au en caract�re.
     */
    public void setDateDebutAu(String dateDebutAu);

    /**
     * Affecte une date de fin au.
     *
     * @param dateFinAu Valeur de la date de fin au en caract�re.
     */
    public void setDateFinAu(String dateFinAu);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de la origine en caract�re.
     */
    public void setOrigine(String origine);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caract�re.
     */
    public void setJeu(String jeu);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caract�re.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte le service.
     *
     * @param service Valeur du service en caract�re.
     */
    public void setService(String service);

    /**
     * Affecte une r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String reference);

    /**
     * Affecte une date de cr�ation du.
     *
     * @param dateCreationDu Valeur de la date de cr�ation du en caract�re.
     */
    public void setDateCreationDu(String dateCreationDu);

    /**
     * Affecte une date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au en caract�re.
     */
    public void setDateCreationAu(String dateCreationAu);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroit(String endroit);

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public void setLocalisation(String localisation);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);

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
     * Affecte le crit�re rechercherSousCategorie.
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setRechercherSousCategorie(String rechercherSousCategorie);
    
    /**
     * Affecte le crit�re rechercherTous
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