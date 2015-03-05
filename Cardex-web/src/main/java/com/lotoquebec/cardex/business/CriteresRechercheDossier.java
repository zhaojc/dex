package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Les crit�res de recherche dossiers regroupent toutes les donn�es reli�es aux
 * �v�nements relatifs aux recherches, aux contrats ou aux d�cisions
 * administratives internes.  La nature ainsi que le nombre de classeurs
 * varient en fonction de l'entit� administrative � laquelle l'utilisateur est
 * rattach� ainsi qu'en fonction de ses privil�ges d'acc�s
 *
 * @author $Author: pcaron $
 * @version $Revison: $, $Date: 2002/02/22 21:02:10 $
 */
public interface CriteresRechercheDossier extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public long getLocalisation();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne le site applicable.
     *
     * @return long Valeur num�rique du site applicable.
     */
    public long getSiteApplicable();

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
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caract�re.
     */
    public long getJeu();


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
     * Retourne le statut.
     *
     * @return long Valeur num�rique du statut.
     */
    public long getStatut();

    /**
     * Retourne le service.
     *
     * @return long Valeur du service en caract�re.
     */
    public long getService();

    /**
     * Retourne l'attribut fonde.
     *
     * @return long Valeur num�rique de l'attribut fonde.
     */
    public long getFonde();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le num�ro de cardex.
     *
     * @return String Valeur du num�ro de cardex en caract�re.
     */
    public String getNumeroCardex();

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
     * Retourne la cinqui�me r�f�rence.
     *
     * @return String Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public String getReference5();

    /**
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date de d�but (yyyy-MM-dd).
     */
    public Timestamp getDateDebutDu();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin (yyyy-MM-dd).
     */
    public Timestamp getDateFinDu();

    /**
     * Retourne la date de d�but au.
     *
     * @return Timestamp Valeur de la date de d�but au (yyyy-MM-dd).
     */
    public Timestamp getDateDebutAu();

    /**
     * Retourne la date de fin au.
     *
     * @return Timestamp Valeur de la date de fin au (yyyy-MM-dd).
     */
    public Timestamp getDateFinAu();

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
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine();

    /**
     * Retourne la r�f�rence vid�o (num�ro s�quentiel).
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
     * Retourne la date de cr�ation du.
     *
     * @return Timestamp Valeur de la date de cr�ation du (yyyy-MM-dd).
     */
    public Timestamp getDateCreationDu();

    /**
     * Retourne la date de cr�ation au.
     *
     * @return Timestamp Valeur de la date de cr�ation au (yyyy-MM-dd).
     */
    public Timestamp getDateCreationAu();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne la p�riode.
     *
     * @return long Valeur num�rique de la p�riode.
     */
    public long getPeriode();

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

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
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
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite);

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
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long site);

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur num�rique du site applicable.
     */
    public void setSiteApplicable(long site);

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
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur num�rique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte le service.
     *
     * @param service Valeur du service en caract�re.
     */
    public void setService(long service);

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur num�rique de l'attribut fonde.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String noCardex);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String noDossier);

    /**
     * Affecte un num�ro de fiche sujet.
     *
     * @param numeroFicheSujet Valeur du num�ro de fiche sujet en caract�re.
     */
    public void setNumeroFicheSujet(String noFicheSujet);

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
     * Affecte la cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference5);

    /**
     * Affecte la date de d�but du.
     *
     * @param dateDebutDu Valeur de la date de d�but du (yyyy-MM-dd).
     */
    public void setDateDebutDu(Timestamp dateDebutDu);

    /**
     * Affecte la date de fin du.
     *
     * @param dateFinDu Valeur de la date de fin du (yyyy-MM-dd).
     */
    public void setDateFinDu(Timestamp dateFinDu);

    /**
     * Affecte la date de d�but au.
     *
     * @param dateDebutAu Valeur de la date de d�but au (yyyy-MM-dd).
     */
    public void setDateDebutAu(Timestamp dateDebutAu);

    /**
     * Affecte la date de fin au.
     *
     * @param dateFinAu Valeur de la date de fin au (yyyy-MM-dd).
     */
    public void setDateFinAu(Timestamp dateFinAu);

    /**
     * Affecte la s�v�rit�.
     *
     * @param severite Valeur num�rique de la s�v�rit�.
     */
    public void setSeverite(long severite);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caract�re.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caract�re.
     */
    public void setJeu(long jeu);

    /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine);

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur num�rique de la r�f�rence vid�o.
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
     * Affecte la date de cr�ation du.
     *
     * @param dateCreationDu Valeur de la date de cr�ation du (yyyy-MM-dd).
     */
    public void setDateCreationDu(Timestamp dateCreationDu);

    /**
     * Affecte la date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au (yyyy-MM-dd).
     */
    public void setDateCreationAu(Timestamp dateCreationAu);

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur num�rique du groupe.
     */
    public void setGroupesIntervenants(String groupe);

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur num�rique de la p�riode.
     */
    public void setPeriode(long periode);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);
    
    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
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