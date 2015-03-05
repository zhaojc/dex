package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res, n�cesssaires � la recherche de sujet.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $, $Date: 2002/02/22 21:22:03 $
 */
public interface CriteresRechercheSujet extends SujetCriteresRecherche{


    // Getters


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
     * @return long Valeur du pays.
     */
    public long getPays();

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince();

    /**
     * Retourne la ville.
     *
     * @return long Valeur de la ville.
     */
    public long getVille();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getNom();

	/**
	 * Retourne le num�ro de permis de conduire.
	 *
	 * @return String Valeur du num�ro de permis de conduire en caract�re.
	 */
	public String getNumeroPermisConduire();

    /**
     * Retourne le sexe.
     *
     * @return long Valeur du sexe.
     */
    public long getSexe();

    /**
     * Retourne l'ethnie.
     *
     * @return longring Valeur de l'ethnie.
     */
    public long getEthnie();

    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine();

	/**
	 * Retourne le num�ro d'assurance maladie.
	 *
	 * @return String Valeur du num�ro d'assurance maladie en caract�re.
	 */
	public String getNumeroAssuranceMaladie();

    /**
     * Retourne le r�le.
     *
     * @return long Valeur du r�le.
     */
    public long getRole();

    /**
     * Retourne la langue.
     *
     * @return long Valeur la langue.
     */
    public long getLangue();

    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public long getRace();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne la s�v�rit� Investigation.
     *
     * @return long Valeur de la s�v�rit� en caract�re.
     */
    public long getSeverite();

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur de la s�v�rit� en caract�re.
     */
    public long getSeveriteCasino();

    /**
     * Affecte une s�v�rit� casino.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeveriteCasino(long severiteCasino);

	/**
	 * Retourne la s�v�rit�.
	 *
	 * @return long Valeur de la s�v�rit� en caract�re.
	 */
	public long getSeveriteAutres();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite();

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
     * @return long Valeur de la premi�re caract�ristique.
     */
    public long getCaracteristique1();

    /**
     * Retourne la deuxi�me caract�ristique.
     *
     * @return long Valeur de la deuxi�me caract�ristique.
     */
    public long getCaracteristique2();

    /**
     * Retourne la troisi�me caract�ristique.
     *
     * @return long Valeur de la troisi�me caract�ristique.
     */
    public long getCaracteristique3();

    /**
     * Retourne la quatri�me caract�ristique.
     *
     * @return long Valeur de la quatri�me caract�ristique.
     */
    public long getCaracteristique4();

    /**
     * Retourne la cinqui�me caract�ristique.
     *
     * @return long Valeur de la cinqui�me caract�ristique.
     */
    public long getCaracteristique5();

    /**
     * Retourne la condition OR.
     *
     * @return String Valeur de la condition OR en caract�re.
     */
    public String isOr1();

    /**
     * Retourne la condition OR.
     *
     * @return String Valeur de la condition OR en caract�re.
     */
    public String isOr2();

    /**
     * Retourne la condition OR.
     *
     * @return String Valeur de la condition OR en caract�re.
     */
    public String isOr3();
    
    /**
     * Retourne le passeport.
     *
     * @return String Valeur du passeport en caract�re.
     */
    public String getPasseport();

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
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public long getMaximumResultatsRecherche();

    
    // Setters

    /**
     * Affecte la date de naissance du sujet.
     *
     * @param dateNaissance Valeur de la date de naissance en caract�re.
     */
    public void setDateNaissance(String dateNaissance);

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
     * @param pays Valeur du pays.
     */
    public void setPays(long pays);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville.
     */
    public void setVille(long ville);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setNom(String nom);

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe.
     */
    public void setSexe(long sexe);

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie.
     */
    public void setEthnie(long ethnie);

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long site);

    /**
     * Affecte un r�le.
     *
     * @param role Valeur du r�le.
     */
    public void setRole(long role);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caract�re.
     */
    public void setRace(long race);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

	/**
	 * Affecte une s�v�rit� Investigation.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeverite(long severite);

	/**
	 * Affecte une s�v�rit�.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeveriteAutres(long severiteAutres);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une premi�re caract�ristique.
     *
     * @param caracteristique1 Valeur de la premi�re caract�ristique.
     */
    public void setCaracteristique1(long caracteristique1);

    /**
     * Affecte une deuxi�me caract�ristique.
     *
     * @param caracteristique2 Valeur de la deuxi�me caract�ristique.
     */
    public void setCaracteristique2(long caracteristique2);

    /**
     * Affecte une troisi�me caract�ristique.
     *
     * @param caracteristique3 Valeur de la troisi�me caract�ristique.
     */
    public void setCaracteristique3(long caracteristique3);

    /**
     * Affecte une quatri�me caract�ristique.
     *
     * @param caracteristique4 Valeur de la quatri�me caract�ristique.
     */
    public void setCaracteristique4(long caracteristique4);

    /**
     * Affecte une cinqui�me caract�ristique.
     *
     * @param caracteristique5 Valeur de la cinqui�me caract�ristique.
     */
    public void setCaracteristique5(long caracteristique5);

    /**
     * Affecte une condition OR.
     *
     * @param caracteristique1 Valeur de la condition en caract�re.
     */
    public void setOr1(String or1);

    /**
     * Affecte une condition OR.
     *
     * @param caracteristique1 Valeur de la condition en caract�re.
     */
    public void setOr2(String or2);

    /**
     * Affecte une condition OR.
     *
     * @param caracteristique1 Valeur de la condition en caract�re.
     */
    public void setOr3(String or3);

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
	 * Affecte un num�ro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du num�ro de permis de conduire en
	 * caract�re.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

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
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximum);

}