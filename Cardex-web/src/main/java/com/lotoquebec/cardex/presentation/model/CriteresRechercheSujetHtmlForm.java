package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de sujet.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/22 21:55:43 $
 */
public interface CriteresRechercheSujetHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'�ge estim�.
     *
     * @return String Valeur de l'�ge en caract�re.
     */
    public String getAge();

    /**
     * Retourne la date de naissance.
     *
     * @return String Valeur de la date de naissance en caract�re.
     */
    public String getDateNaissance();

    /**
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroFiche();

    /**
     * Retourne le nom phon�tique.
     *
     * @return String Valeur du nom phon�tique en caract�re.
     */
    public String getNomPhonetique();

    /**
     * Retourne le prenom phon�tique.
     *
     * @return String Valeur du prenom phon�tique en caract�re.
     */
    public String getPrenomPhonetique();

    /**
     * Retourne l'alias.
     *
     * @return String Valeur de l'alias en caract�re.
     */
    public String getAlias();

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caract�re.
     */
    public String getPays();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caract�re.
     */
    public String getVille();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getNom();

    /**
     * Retourne le sexe.
     *
     * @return String Valeur du sexe en caract�re.
     */
    public String getSexe();

    /**
     * Retourne l'ethnie.
     *
     * @return String Valeur de l'ethnie en caract�re.
     */
    public String getEthnie();

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
     * Retourne le r�le.
     *
     * @return String Valeur du r�le en caract�re.
     */
    public String getRole();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caract�re.
     */
    public String getLangue();

    /**
     * Retourne la race.
     *
     * @return String Valeur de la race en caract�re.
     */
    public String getRace();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

	/**
	 * Retourne le num�ro de permis de conduire.
	 *
	 * @return String Valeur du num�ro de permis de conduire en caract�re.
	 */
	public String getNumeroPermisConduire();

    /**
     * Retourne la s�v�rit� Investigation.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite();

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeveriteCasino();

	/**
	 * Retourne la s�v�rit�.
	 *
	 * @return String Valeur de la s�v�rit� en caract�re.
	 */
	public String getSeveriteAutres();

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite();

    /**
     * Retourne le num�ro du client.
     *
     * @return String Valeur du num�ro en caract�re.
     */
    public String getNumeroClient();

    /**
     * Retourne la r�f�rence 1.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference();

    /**
     * Retourne la premi�re caract�ristique.
     *
     * @return String Valeur de la premi�re caract�ristique en caract�re.
     */
    public String getCaracteristique1();

    /**
     * Retourne le type d'�ge
     *
     * @return boolean Valeur du type d'�ge.
     */
    public boolean isAgeEstime();
    
    public boolean isAgeReel();
    
    public boolean isAgeReelPlusMoins();
    
    public boolean isAgeInconnu();
    

    /**
     * Retourne la deuxi�me caract�ristique.
     *
     * @return String Valeur de la deuxi�me caract�ristique en caract�re.
     */
    public String getCaracteristique2();

    /**
     * Retourne la troisi�me caract�ristique.
     *
     * @return String Valeur de la troisi�me caract�ristique en caract�re.
     */
    public String getCaracteristique3();

    /**
     * Retourne la quatri�me caract�ristique.
     *
     * @return String Valeur de la quatri�me caract�ristique en caract�re.
     */
    public String getCaracteristique4();

    /**
     * Retourne la cinqui�me caract�ristique.
     *
     * @return String Valeur de la cinqui�me caract�ristique en caract�re.
     */
    public String getCaracteristique5();

    /**
     * Retourne le passeport.
     *
     * @return String Valeur du passeport en caract�re.
     */
    public String getPasseport();

	/**
	 * Retourne le num�ro d'assurance maladie.
	 *
	 * @return String Valeur du num�ro d'assurance maladie en caract�re.
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
     * @param dateNaissance Valeur de la date de naissance en caract�re.
     */
    public void setDateNaissance(String dateNaissance);

    /**
     * Affecte l'�ge estim� du sujet.
     *
     * @param age Valeur de l'�ge en caract�re.
     */
    public void setAge(String age);

    /**
     * Affecte un num�ro de fiche.
     *
     * @param numeroFiche Valeur du num�ro de fiche en caract�re.
     */
    public void setNumeroFiche(String numeroFiche);


    /**
     * Affecte un nom phon�tique.
     *
     * @param nomPhonetique Valeur du nom phon�tique en caract�re.
     */
    public void setNomPhonetique(String nomPhonetique);

    /**
     * Affecte un prenom phon�tique.
     *
     * @param prenomPhonetique Valeur du prenom phon�tique en caract�re.
     */
    public void setPrenomPhonetique(String prenomPhonetique);

    /**
     * Affecte un alias.
     *
     * @param alias Valeur de l'alias en caract�re.
     */
    public void setAlias(String alias);

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays en caract�re.
     */
    public void setPays(String pays);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caract�re.
     */
    public void setVille(String ville);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setNom(String nom);

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe en caract�re.
     */
    public void setSexe(String sexe);

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie en caract�re.
     */
    public void setEthnie(String ethnie);

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
     * Affecte un r�le.
     *
     * @param role Valeur du r�le en caract�re.
     */
    public void setRole(String role);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caract�re.
     */
    public void setLangue(String langue);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caract�re.
     */
    public void setRace(String race);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

	/**
	 * Affecte une s�v�rit� Investigation.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeverite(String severite);

    /**
     * Affecte une s�v�rit� casino.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeveriteCasino(String severiteCasino);

	/**
	 * Affecte une s�v�rit�.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeveriteAutres(String severiteAutres);

	/**
	 * Affecte un num�ro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du num�ro de permis de conduire en
	 * caract�re.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte le numr�o du client.
     *
     * @param numeroClient Valeur du num�ro en caract�re.
     */
    public void setNumeroClient(String numeroClient);

    /**
     * Affecte la r�f�rence 1.
     *
     * @param reference Valeur de la reference en caract�re.
     */
    public void setReference(String reference);

    /**
     * Affecte une premi�re caract�ristique.
     *
     * @param caracteristique1 Valeur de la premi�re caract�ristique en caract�re.
     */
    public void setCaracteristique1(String caracteristique1);


    /**
     * Affecte une deuxi�me caract�ristique.
     *
     * @param caracteristique2 Valeur de la deuxi�me caract�ristique en caract�re.
     */
    public void setCaracteristique2(String caracteristique2);

    /**
     * Affecte une troisi�me caract�ristique.
     *
     * @param caracteristique3 Valeur de la troisi�me caract�ristique en caract�re.
     */
    public void setCaracteristique3(String caracteristique3);

    /**
     * Affecte une quatri�me caract�ristique.
     *
     * @param caracteristique4 Valeur de la quatri�me caract�ristique en caract�re.
     */
    public void setCaracteristique4(String caracteristique4);

    /**
     * Affecte une cinqui�me caract�ristique.
     *
     * @param caracteristique5 Valeur de la cinqui�me caract�ristique en caract�re.
     */
    public void setCaracteristique5(String caracteristique5);

    /**
     * Affecte un passeport.
     *
     * @param passeport Valeur du passeport en caract�re.
     */
    public void setPasseport(String passeport);

	/**
	 * Affecte un num�ro d'assurance maladie.
	 *
	 * @param numeroAssuranceMaladie Valeur du num�ro d'assurance maladie en caract�re.
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie);

	
    /**
     * Ajoute un sujet.
     *
     * @param sujet Valeur du sujet � ajouter.
     */
    public void addSujet(SujetHtmlForm sujet);

}