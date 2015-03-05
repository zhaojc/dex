package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères, nécesssaires à la recherche de sujet.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $, $Date: 2002/02/22 21:22:03 $
 */
public interface CriteresRechercheSujet extends SujetCriteresRecherche{


    // Getters


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
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

	/**
	 * Retourne le numéro de permis de conduire.
	 *
	 * @return String Valeur du numéro de permis de conduire en caractère.
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
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur numérique du site d'origine.
     */
    public long getSiteOrigine();

	/**
	 * Retourne le numéro d'assurance maladie.
	 *
	 * @return String Valeur du numéro d'assurance maladie en caractère.
	 */
	public String getNumeroAssuranceMaladie();

    /**
     * Retourne le rôle.
     *
     * @return long Valeur du rôle.
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
     * Retourne la sévérité Investigation.
     *
     * @return long Valeur de la sévérité en caractère.
     */
    public long getSeverite();

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité en caractère.
     */
    public long getSeveriteCasino();

    /**
     * Affecte une sévérité casino.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeveriteCasino(long severiteCasino);

	/**
	 * Retourne la sévérité.
	 *
	 * @return long Valeur de la sévérité en caractère.
	 */
	public long getSeveriteAutres();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite();

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
     * @return long Valeur de la première caractéristique.
     */
    public long getCaracteristique1();

    /**
     * Retourne la deuxième caractéristique.
     *
     * @return long Valeur de la deuxième caractéristique.
     */
    public long getCaracteristique2();

    /**
     * Retourne la troisième caractéristique.
     *
     * @return long Valeur de la troisième caractéristique.
     */
    public long getCaracteristique3();

    /**
     * Retourne la quatrième caractéristique.
     *
     * @return long Valeur de la quatrième caractéristique.
     */
    public long getCaracteristique4();

    /**
     * Retourne la cinquième caractéristique.
     *
     * @return long Valeur de la cinquième caractéristique.
     */
    public long getCaracteristique5();

    /**
     * Retourne la condition OR.
     *
     * @return String Valeur de la condition OR en caractère.
     */
    public String isOr1();

    /**
     * Retourne la condition OR.
     *
     * @return String Valeur de la condition OR en caractère.
     */
    public String isOr2();

    /**
     * Retourne la condition OR.
     *
     * @return String Valeur de la condition OR en caractère.
     */
    public String isOr3();
    
    /**
     * Retourne le passeport.
     *
     * @return String Valeur du passeport en caractère.
     */
    public String getPasseport();

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
     * Affecte la date de naissance du sujet.
     *
     * @param dateNaissance Valeur de la date de naissance en caractère.
     */
    public void setDateNaissance(String dateNaissance);

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
     * @param nom Valeur du nom en caractère.
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
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(long site);

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle.
     */
    public void setRole(long role);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caractère.
     */
    public void setRace(long race);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

	/**
	 * Affecte une sévérité Investigation.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeverite(long severite);

	/**
	 * Affecte une sévérité.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeveriteAutres(long severiteAutres);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique.
     */
    public void setCaracteristique1(long caracteristique1);

    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique.
     */
    public void setCaracteristique2(long caracteristique2);

    /**
     * Affecte une troisième caractéristique.
     *
     * @param caracteristique3 Valeur de la troisième caractéristique.
     */
    public void setCaracteristique3(long caracteristique3);

    /**
     * Affecte une quatrième caractéristique.
     *
     * @param caracteristique4 Valeur de la quatrième caractéristique.
     */
    public void setCaracteristique4(long caracteristique4);

    /**
     * Affecte une cinquième caractéristique.
     *
     * @param caracteristique5 Valeur de la cinquième caractéristique.
     */
    public void setCaracteristique5(long caracteristique5);

    /**
     * Affecte une condition OR.
     *
     * @param caracteristique1 Valeur de la condition en caractère.
     */
    public void setOr1(String or1);

    /**
     * Affecte une condition OR.
     *
     * @param caracteristique1 Valeur de la condition en caractère.
     */
    public void setOr2(String or2);

    /**
     * Affecte une condition OR.
     *
     * @param caracteristique1 Valeur de la condition en caractère.
     */
    public void setOr3(String or3);

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
	 * Affecte un numéro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du numéro de permis de conduire en
	 * caractère.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

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

}