package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de sujet.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/22 21:55:43 $
 */
public interface CriteresRechercheSujetHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'âge estimé.
     *
     * @return String Valeur de l'âge en caractère.
     */
    public String getAge();

    /**
     * Retourne la date de naissance.
     *
     * @return String Valeur de la date de naissance en caractère.
     */
    public String getDateNaissance();

    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche();

    /**
     * Retourne le nom phonétique.
     *
     * @return String Valeur du nom phonétique en caractère.
     */
    public String getNomPhonetique();

    /**
     * Retourne le prenom phonétique.
     *
     * @return String Valeur du prenom phonétique en caractère.
     */
    public String getPrenomPhonetique();

    /**
     * Retourne l'alias.
     *
     * @return String Valeur de l'alias en caractère.
     */
    public String getAlias();

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caractère.
     */
    public String getPays();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caractère.
     */
    public String getVille();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

    /**
     * Retourne le sexe.
     *
     * @return String Valeur du sexe en caractère.
     */
    public String getSexe();

    /**
     * Retourne l'ethnie.
     *
     * @return String Valeur de l'ethnie en caractère.
     */
    public String getEthnie();

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
     * Retourne le rôle.
     *
     * @return String Valeur du rôle en caractère.
     */
    public String getRole();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caractère.
     */
    public String getLangue();

    /**
     * Retourne la race.
     *
     * @return String Valeur de la race en caractère.
     */
    public String getRace();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

	/**
	 * Retourne le numéro de permis de conduire.
	 *
	 * @return String Valeur du numéro de permis de conduire en caractère.
	 */
	public String getNumeroPermisConduire();

    /**
     * Retourne la sévérité Investigation.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite();

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeveriteCasino();

	/**
	 * Retourne la sévérité.
	 *
	 * @return String Valeur de la sévérité en caractère.
	 */
	public String getSeveriteAutres();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne le numéro du client.
     *
     * @return String Valeur du numéro en caractère.
     */
    public String getNumeroClient();

    /**
     * Retourne la référence 1.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference();

    /**
     * Retourne la première caractéristique.
     *
     * @return String Valeur de la première caractéristique en caractère.
     */
    public String getCaracteristique1();

    /**
     * Retourne le type d'âge
     *
     * @return boolean Valeur du type d'âge.
     */
    public boolean isAgeEstime();
    
    public boolean isAgeReel();
    
    public boolean isAgeReelPlusMoins();
    
    public boolean isAgeInconnu();
    

    /**
     * Retourne la deuxième caractéristique.
     *
     * @return String Valeur de la deuxième caractéristique en caractère.
     */
    public String getCaracteristique2();

    /**
     * Retourne la troisième caractéristique.
     *
     * @return String Valeur de la troisième caractéristique en caractère.
     */
    public String getCaracteristique3();

    /**
     * Retourne la quatrième caractéristique.
     *
     * @return String Valeur de la quatrième caractéristique en caractère.
     */
    public String getCaracteristique4();

    /**
     * Retourne la cinquième caractéristique.
     *
     * @return String Valeur de la cinquième caractéristique en caractère.
     */
    public String getCaracteristique5();

    /**
     * Retourne le passeport.
     *
     * @return String Valeur du passeport en caractère.
     */
    public String getPasseport();

	/**
	 * Retourne le numéro d'assurance maladie.
	 *
	 * @return String Valeur du numéro d'assurance maladie en caractère.
	 */
	public String getNumeroAssuranceMaladie();

	/**
     * Retourne une collection de sujets.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets();


    // Setters


    /**
     * Affecte la date de naissance du sujet.
     *
     * @param dateNaissance Valeur de la date de naissance en caractère.
     */
    public void setDateNaissance(String dateNaissance);

    /**
     * Affecte l'âge estimé du sujet.
     *
     * @param age Valeur de l'âge en caractère.
     */
    public void setAge(String age);

    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche);


    /**
     * Affecte un nom phonétique.
     *
     * @param nomPhonetique Valeur du nom phonétique en caractère.
     */
    public void setNomPhonetique(String nomPhonetique);

    /**
     * Affecte un prenom phonétique.
     *
     * @param prenomPhonetique Valeur du prenom phonétique en caractère.
     */
    public void setPrenomPhonetique(String prenomPhonetique);

    /**
     * Affecte un alias.
     *
     * @param alias Valeur de l'alias en caractère.
     */
    public void setAlias(String alias);

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays en caractère.
     */
    public void setPays(String pays);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caractère.
     */
    public void setVille(String ville);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom);

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe en caractère.
     */
    public void setSexe(String sexe);

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie en caractère.
     */
    public void setEthnie(String ethnie);

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
     * Affecte un rôle.
     *
     * @param role Valeur du rôle en caractère.
     */
    public void setRole(String role);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(String langue);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caractère.
     */
    public void setRace(String race);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

	/**
	 * Affecte une sévérité Investigation.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeverite(String severite);

    /**
     * Affecte une sévérité casino.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeveriteCasino(String severiteCasino);

	/**
	 * Affecte une sévérité.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeveriteAutres(String severiteAutres);

	/**
	 * Affecte un numéro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du numéro de permis de conduire en
	 * caractère.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte le numréo du client.
     *
     * @param numeroClient Valeur du numéro en caractère.
     */
    public void setNumeroClient(String numeroClient);

    /**
     * Affecte la référence 1.
     *
     * @param reference Valeur de la reference en caractère.
     */
    public void setReference(String reference);

    /**
     * Affecte une première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique en caractère.
     */
    public void setCaracteristique1(String caracteristique1);


    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique en caractère.
     */
    public void setCaracteristique2(String caracteristique2);

    /**
     * Affecte une troisième caractéristique.
     *
     * @param caracteristique3 Valeur de la troisième caractéristique en caractère.
     */
    public void setCaracteristique3(String caracteristique3);

    /**
     * Affecte une quatrième caractéristique.
     *
     * @param caracteristique4 Valeur de la quatrième caractéristique en caractère.
     */
    public void setCaracteristique4(String caracteristique4);

    /**
     * Affecte une cinquième caractéristique.
     *
     * @param caracteristique5 Valeur de la cinquième caractéristique en caractère.
     */
    public void setCaracteristique5(String caracteristique5);

    /**
     * Affecte un passeport.
     *
     * @param passeport Valeur du passeport en caractère.
     */
    public void setPasseport(String passeport);

	/**
	 * Affecte un numéro d'assurance maladie.
	 *
	 * @param numeroAssuranceMaladie Valeur du numéro d'assurance maladie en caractère.
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie);

	
    /**
     * Ajoute un sujet.
     *
     * @param sujet Valeur du sujet à ajouter.
     */
    public void addSujet(SujetHtmlForm sujet);

}