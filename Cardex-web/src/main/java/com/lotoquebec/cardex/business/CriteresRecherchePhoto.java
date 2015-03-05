package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à une recherche de photo.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.9 $, $Date: 2002/03/19 17:03:02 $
 */
public interface CriteresRecherchePhoto extends SujetCriteresRecherche {


    // Getters


    /**
     * Retourne le surnom (alias) d'un sujet
     *
     * @return String Valeur de l'alias en caractère.
     */
    public String getAlias();

    /**
     * Retourne le site applicable.
     *
     * @return long Valeur du site applicable.
     */
    public long getSiteApplicable();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeverite();

    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public long getRace();

    /**
     * Pour demander plus d'un repérage (par exemple, sélection des dossiers qui ont plus de 5 repérages).
     *
     * @return long 
     */
    public long getNombreReperages();

    /**
     * Retourne la nature.
     *
     * @return long Valeur de la nature.
     */
    public long getNature();

    /**
     * Retourne la date d'ajout au début.
     *
     * @return Timestamp Valeur de la date d'ajout au début (yyyy-MM-dd).
     */
    public Timestamp getDateAjoutDebut();

    /**
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue();

    /**
     * Retourne le type.
     *
     * @return long Valeur du type.
     */
    public long getType();

    /**
     * Retourne la date d'ajout à la fin.
     *
     * @return Timestamp Valeur de la date d'ajout à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateAjoutFin();

    /**
     * Retourne le sexe.
     *
     * @return long Valeur du sexe.
     */
    public long getSexe();

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur de la catégorie.
     */
    public long getCategorie();

    /**
     * Retourne la date de validité au début.
     *
     * @return Timestamp Valeur de la date de validité au début (yyyy-MM-dd).
     */
    public Timestamp getDateValideDebut();

    /**
     * Retourne l'ethnie.
     *
     * @return long Valeur de l'ethnie.
     */
    public long getEthnie();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne la date de validité à la fin.
     *
     * @return Timestamp Valeur de la date de validité à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateValideFin();

    /**
     * Retourne le jeu.
     *
     * @return long Valeur du jeu.
     */
    public long getJeu();

    /**
     * Retourne la fondation.
     *
     * @return long Valeur de la fondation.
     */
    public long getFonde();

    /**
     * Retourne la date se terminant au début.
     *
     * @return Timestamp Valeur de la date se terminant au début (yyyy-MM-dd).
     */
    public Timestamp getDateTermineDebut();

    /**
     * Retourne la première caractéristique.
     *
     * @return long Valeur de la première caractéristique.
     */
    public long getCaracteristique1();

    /**
     * Retourne la période.
     *
     * @return long Valeur de la période.
     */
    public long getPeriode();

    /**
     * Retourne la date se terminant à la fin.
     *
     * @return Timestamp Valeur de la date se terminant à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateTermineFin();

    /**
     * Retourne la deuxième caractéristique.
     *
     * @return long Valeur de la deuxième caractéristique.
     */
    public long getCaracteristique2();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le numéro de sujet.
     *
     * @return String Valeur du numéro de sujet en caractère.
     */
    public String getNumeroSujet();

    /**
     * Retourne la troisième caractéristique.
     *
     * @return long Valeur de la troisième caractéristique.
     */
    public long getCaracteristique3();

    /**
     * Retourne la référence vidéo.
     *
     * @return long Valeur de la référence vidéo.
     */
    public long getReferenceVideo();

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
     * Retourne la quatrième caractéristique.
     *
     * @return long Valeur de la quatrième caractéristique.
     */
    public long getCaracteristique4();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

    /**
     * Retourne si le sujet est mis en attache.
     *
     * @return boolean Valeur booléenne indiquant si le sujet est mis en
     * attache.
     */
    public Boolean isSujetAttache();


    /**
     * Retourne le prénom.
     *
     * @return String Valeur du prénom en caractère.
     */
    public String getPrenom();

    /**
     * Retourne si le dossier est mis en attache.
     *
     * @return boolean Valeur booléenne indiquant si le dossier est mis en
     * attache.
     */
    public Boolean isDossierAttache();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine.
     */
    public long getOrigine();

    /**
     * Retourne le nom ordinaire.
     *
     * @return String Valeur du nom ordinaire en caractère.
     */
    public String getNomOrdinaire();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

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
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();


    
    // Setters


    /**
     * Affecte l'alias.
     *
     * @param age Valeur de l'alias en caractère.
     */
    public void setAlias(String alias);

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable.
     */
    public void setSiteApplicable(long siteApplicable);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race.
     */
    public void setRace(long race);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature.
     */
    public void setNature(long nature);

    /**
     * Affecte une date d'ajout au début.
     *
     * @param dateAjoutDebut Valeur de la date d'ajout au début (yyyy-MM-dd).
     */
    public void setDateAjoutDebut(Timestamp dateAjoutDebut);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type.
     */
    public void setType(long type);

    /**
     * Affecte une date d'ajout à la fin.
     *
     * @param dateAjoutFin Valeur de la date d'ajout à la fin (yyyy-MM-dd).
     */
    public void setDateAjoutFin(Timestamp dateAjoutFin);

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe.
     */
    public void setSexe(long sexe);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur de la catégorie.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte une date de validité au début.
     *
     * @param dateValideDebut Valeur de la date de validité au début
     * (yyyy-MM-dd).
     */
    public void setDateValideDebut(Timestamp dateValideDebut);

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie.
     */
    public void setEthnie(long ethnie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une date de validité à la fin.
     *
     * @param dateValideFin Valeur de la date de validité à la fin (yyyy-MM-dd).
     */
    public void setDateValideFin(Timestamp dateValideFin);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu.
     */
    public void setJeu(long jeu);

    /**
     * Affecte une fondation.
     *
     * @param fonde Valeur de la fondation.
     */
    public void setFonde(long fonde);

    /**
     * Affecte une date se terminant au début.
     *
     * @param dateTermineDebut Valeur de la date se terminant au début
     * (yyyy-MM-dd).
     */
    public void setDateTermineDebut(Timestamp dateTermineDebut);

    /**
     * Pour demander plus d'un repérage (par exemple, sélection des dossiers qui ont plus de 5 repérages).
     *
     * @param nombreReperages
     */
    public void setNombreReperages(long nombreReperages);

    /**
     * Affecte un première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique.
     */
    public void setCaracteristique1(long caracteristique1);

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période.
     */
    public void setPeriode(long periode);

    /**
     * Affecte une date se terminant à la fin.
     *
     * @param dateTermineFin Valeur de la date se terminant à la fin
     * (yyyy-MM-dd).
     */
    public void setDateTermineFin(Timestamp dateTermineFin);

    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique.
     */
    public void setCaracteristique2(long caracteristique2);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un numéro de sujet.
     *
     * @param numeroSujet Valeur du numéro de sujet en caractère.
     */
    public void setNumeroSujet(String numeroSujet);

    /**
     * Affecte une troisième caractéristique.
     *
     * @param caracteristique3 Valeur de la troisième caractéristique.
     */
    public void setCaracteristique3(long caracteristique3);

    /**
     * Affecte une référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo.
     */
    public void setReferenceVideo(long referenceVideo);

    /**
     * Affecte une quatrième caractéristique.
     *
     * @param caracteristique4 Valeur de la quatrième caractéristique.
     */
    public void setCaracteristique4(long caracteristique4);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom);

    /**
     * Affecte si le sujet est mis en attache.
     *
     * @param sujetAttache Valeur booléenne indiquant si le sujet est mis en
     * attache.
     */
    public void setSujetAttache(Boolean sujetAttache);


    /**
     * Affecte un prénom.
     *
     * @param prenom Valeur du prénom en caractère.
     */
    public void setPrenom(String prenom);

    /**
     * Affecte si le dossier est mis en attache.
     *
     * @param dossierAttache Valeur booléenne indiquant si le dossier est mis
     * en attache.
     */
    public void setDossierAttache(Boolean dossierAttache);

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de l'origine.
     */
    public void setOrigine(long origine);

    /**
     * Affecte un nom ordinaire.
     *
     * @param nomOrdinaire Valeur du nom ordinaire en caractère.
     */
    public void setNomOrdinaire(String nomOrdinaire);

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
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

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
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximum);

	public String getDescriptif();
	
	public void setDescriptif(String descriptif);
	
	public long getEndroit();
	
	public void setEndroit(long endroit);
    
	public String getIntervenant();
	
	public void setIntervenant(String intervenant);
	
	public long getLocalisation();
		
	public void setLocalisation(long localisation);	
	
	public String getCaracteristique1et2();
	
	public void setCaracteristique1et2(String caracteristique1et2);
	
	public String getCaracteristique2et3();
	
	public void setCaracteristique2et3(String caracteristique2et3);
	
	public String getCaracteristique3et4();
	
	public void setCaracteristique3et4(String caracteristique3et4);
	
	public String getOrdreTri();
	
    /**
     * Retourne la référence 1.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne la référence 2.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference2();

    /**
     * Affecte la référence 1.
     *
     * @param lienSite Valeur de la référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la référence 2.
     *
     * @param lienSite Valeur de la référence en caractère.
     */
    public void setReference2(String reference2);

    public void setOrdreTri(String ordreTri);
		
	public String getSensTri();
	
	public void setSensTri(String sensTri);    
	
	public int getSequence();

	public void setSequence(int sequence);
	
    
	public long getEntite();

	public void setEntite(long entite);

	public long getTypeJeu();

	public void setTypeJeu(long typeJeu);
}