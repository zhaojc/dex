package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresRechercheSujet;

/**
 * Permet de transiter les informations relatives à la recherche d'un sujet de
 * la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.12 $, $Date: 2002/02/22 21:34:48 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSujet
 */
public class CriteresRechercheSujetVO implements CriteresRechercheSujet {

    private long age = 0;
    private String dateNaissance = "";
    private String numeroFiche = "";
    private String nomPhonetique = "";
    private String prenomPhonetique = "";
    private String alias = "";
    private long pays = 0;
    private long province = 0;
    private long ville = 0;
    private String nom = "";
    private long sexe = 0;
    private long entite = 0;
    private long siteOrigine = 0;
    private long ethnie = 0;
    private long role = 0;
    private long langue = 0;
    private long race = 0;
    private long statut = 0;
    private long severite = 0;
    private long severiteCasino = 0;
	private long severiteAutres = 0;
    private long confidentialite = 0;
    private long caracteristique1 = 0;
    private long caracteristique2 = 0;
    private long caracteristique3 = 0;
    private long caracteristique4 = 0;
    private long caracteristique5 = 0;
    private String or1 = "";
    private String or2 = "";
    private String or3 = "";
    private String passeport = "";
	private String numeroAssuranceMaladie = "";
	private String numeroPermisConduire = "";
    private String numeroClient = "";
    private String reference = "";
    private String    ordreTriRecherche = "";
    private Boolean   ordreCroissantRecherche = true;
    private long      maximumResultatsRecherche = 0;
    private Boolean ageEstime = false;
    private Boolean ageReel = false;
    private Boolean ageReelPlusMoins = false;
    private Boolean ageInconnu = false;
    private int sequence = 0;
    

    /**
     * Constructeur de CriteresRechercheSujetVO par défaut.
     */
    public CriteresRechercheSujetVO() {}


    // Getters


    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche() {
        return this.numeroFiche;
    }

    
    /**
     * Retourne le nom phonétique.
     *
     * @return String Valeur du nom phonétique en caractère.
     */
    public String getNomPhonetique() {
        return this.nomPhonetique;
    }

    
    /**
     * Retourne le prenom phonétique.
     *
     * @return String Valeur du prenom phonétique en caractère.
     */
    public String getPrenomPhonetique() {
        return this.prenomPhonetique;
    }

    
    /**
     * Retourne l'alias.
     *
     * @return String Valeur de l'alias en caractère.
     */
    public String getAlias() {
        return this.alias;
    }

    
    /**
     * Retourne le pays.
     *
     * @return long Valeur du pays.
     */
    public long getPays() {
        return this.pays;
    }

    
    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince() {
        return this.province;
    }

    
    /**
     * Retourne la ville.
     *
     * @return long Valeur de la ville.
     */
    public long getVille() {
        return this.ville;
    }

    
    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom() {
        return this.nom;
    }

    
    /**
     * Retourne le sexe.
     *
     * @return long Valeur du sexe.
     */
    public long getSexe() {
        return this.sexe;
    }

    
    /**
     * Retourne l'ethnie.
     *
     * @return long Valeur de l'ethnie.
     */
    public long getEthnie() {
        return this.ethnie;
    }

    
    /**
     * Retourne si la condition.
     *
     * @return boolean Valeur booléenne indiquant si la condition OR est utilisée
     * pour la caractéristique
     */
    public String isOr1() {
        return this.or1;
    }

    
    /**
     * Retourne si la condition.
     *
     * @return boolean Valeur booléenne indiquant si la condition OR est utilisée
     * pour la caractéristique
     */
    public String isOr2() {
        return this.or2;
    }
    
    
    /**
     * Retourne si la condition.
     *
     * @return boolean Valeur booléenne indiquant si la condition OR est utilisée
     * pour la caractéristique
     */
    public String isOr3() {
        return this.or3;
    }
    
	
    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite() {
        return this.entite;
    }

    
    /**
     * Retourne le rôle.
     *
     * @return long Valeur du rôle.
     */
    public long getRole() {
        return this.role;
    }

    
    /**
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue() {
        return this.langue;
    }

    
    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public long getRace() {
        return this.race;
    }

    
    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    
    /**
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeverite() {
        return this.severite;
    }

    
    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    
    /**
     * Retourne la première caractéristique.
     *
     * @return long Valeur de la première caractéristique.
     */
    public long getCaracteristique1() {
        return this.caracteristique1;
    }

    
    /**
     * Retourne la deuxième caractéristique.
     *
     * @return long Valeur de la deuxième caractéristique.
     */
    public long getCaracteristique2() {
        return this.caracteristique2;
    }

    
    /**
     * Retourne la troisième caractéristique.
     *
     * @return long Valeur de la troisième caractéristique.
     */
    public long getCaracteristique3() {
        return this.caracteristique3;
    }

    
    /**
     * Retourne la quatrième caractéristique.
     *
     * @return long Valeur de la quatrième caractéristique.
     */
    public long getCaracteristique4() {
        return this.caracteristique4;
    }

    
    /**
     * Retourne la cinquième caractéristique.
     *
     * @return long Valeur de la cinquième caractéristique.
     */
    public long getCaracteristique5() {
        return this.caracteristique5;
    }

	
    /**
	 * @return Returns the or1.
	 */
	public String getOr1() {
		return or1;
	}
	
	
	/**
	 * @return Returns the or2.
	 */
	public String getOr2() {
		return or2;
	}
	
	
	/**
	 * @return Returns the or3.
	 */
	public String getOr3() {
		return or3;
	}

	
	/**
     * Retourne le passeport.
     *
     * @return String Valeur du passeport en caractère.
     */
    public String getPasseport() {
        return this.passeport;
    }
    
    
    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    
    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    
    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }


    /**
     * @return ageEstime
     */
    public Boolean isAgeEstime() {
        return ageEstime;
    }
    

    /**
     * @return ageReel
     */
    public Boolean isAgeReel() {
        return ageReel;
    }
    

    /**
     * @return ageReelPlusMoins
     */
    public Boolean isAgeReelPlusMoins() {
        return ageReelPlusMoins;
    }

    
    /**
     * @return ageInconnu
     */
    public Boolean isAgeInconnu() {
        return ageInconnu;
    }
    
    // Setters


    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche) {
        this.numeroFiche = numeroFiche;
    }


    /**
     * Affecte un nom phonétique.
     *
     * @param nomPhonetique Valeur du nom phonétique en caractère.
     */
    public void setNomPhonetique(String nomPhonetique) {
        this.nomPhonetique = nomPhonetique;
    }

    
    /**
     * Affecte un prenom phonétique.
     *
     * @param prenomPhonetique Valeur du prenom phonétique en caractère.
     */
    public void setPrenomPhonetique(String prenomPhonetique) {
        this.prenomPhonetique = prenomPhonetique;
    }

    
    /**
     * Affecte un alias.
     *
     * @param alias Valeur de l'alias en caractère.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    
    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays.
     */
    public void setPays(long pays) {
        this.pays = pays;
    }

    
    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province) {
        this.province = province;
    }

    
    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville.
     */
    public void setVille(long ville) {
        this.ville = ville;
    }

    
    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe.
     */
    public void setSexe(long sexe) {
        this.sexe = sexe;
    }

    
    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie.
     */
    public void setEthnie(long ethnie) {
        this.ethnie = ethnie;
    }

    
    /**
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }

    
    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle.
     */
    public void setRole(long role) {
        this.role = role;
    }

    
    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue) {
        this.langue = langue;
    }

    
    /**
     * Affecte une race.
     *
     * @param race Valeur de la race.
     */
    public void setRace(long race) {
        this.race = race;
    }

    
    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    
    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite) {
        this.severite = severite;
    }

    
    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    
    /**
     * Affecte une première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique.
     */
    public void setCaracteristique1(long caracteristique1) {
        this.caracteristique1 = caracteristique1;
    }

    
    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique.
     */
    public void setCaracteristique2(long caracteristique2) {
        this.caracteristique2 = caracteristique2;
    }

    
    /**
     * Affecte une troisième caractéristique.
     *
     * @param caracteristique3 Valeur de la troisième caractéristique.
     */
    public void setCaracteristique3(long caracteristique3) {
        this.caracteristique3 = caracteristique3;
    }

    
    /**
     * Affecte une quatrième caractéristique.
     *
     * @param caracteristique4 Valeur de la quatrième caractéristique.
     */
    public void setCaracteristique4(long caracteristique4) {
        this.caracteristique4 = caracteristique4;
    }

    
    /**
     * Affecte une cinquième caractéristique.
     *
     * @param caracteristique5 Valeur de la cinquième caractéristique.
     */
    public void setCaracteristique5(long caracteristique5) {
        this.caracteristique5 = caracteristique5;
    }

    
    /**
     * Affecte un passeport.
     *
     * @param passeport Valeur du passeport en caractère.
     */
    public void setPasseport(String passeport) {
        this.passeport = passeport;
    }
    
    
    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    
    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    
    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    
    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSujetVO.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheSujetVO
     * en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheSujetVO : ");
        stringBuffer.append("numeroFiche = '" + numeroFiche);
        stringBuffer.append("', nomPhonetique = '" + nomPhonetique);
        stringBuffer.append("', prenomPhonetique = '" + prenomPhonetique);
        stringBuffer.append("', alias = '" + alias);
        stringBuffer.append("', pays = '" + pays);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', ville = '" + ville);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', sexe = '" + sexe);
        stringBuffer.append("', ethnie = '" + ethnie );
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', race = '" + race);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', ageEstime = '" + ageEstime);
        stringBuffer.append("', ageReel = '" + ageReel);
        stringBuffer.append("', ageReelPlusMoins = '" + ageReelPlusMoins);
        stringBuffer.append("', ageInconnu = '" + ageInconnu);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', caracteristique1 = '" + caracteristique1);
        stringBuffer.append("', caracteristique2 = '" + caracteristique2);
        stringBuffer.append("', caracteristique3 = '" + caracteristique3);
        stringBuffer.append("', caracteristique4 = '" + caracteristique4);
        stringBuffer.append("', caracteristique5 = '" + caracteristique5);
        stringBuffer.append("', passeport = '" + passeport);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	
    /**
	 * Gets the age
	 * @return Returns a long
	 */
	public long getAge() {
		return age;
	}
	
	
	/**
	 * Sets the age
	 * @param age The age to set
	 */
	public void setAge(long age) {
		this.age = age;
	}

    
	/**
     * Affecte la condition OR.
     *
     * @param or1 Valeur booléenne indiquant si la condition est utilisée
     */
    public void setOr1(String or1) {
        this.or1 = or1;
    }

    
    /**
     * Affecte la condition OR.
     *
     * @param or1 Valeur booléenne indiquant si la condition est utilisée
     */
    public void setOr2(String or2) {
        this.or2 = or2;
    }

    
    /**
     * Affecte la condition OR.
     *
     * @param or1 Valeur booléenne indiquant si la condition est utilisée
     */
    public void setOr3(String or3) {
        this.or3 = or3;
    }

	
    /**
	 * Returns the numeroClient.
	 * @return String
	 */
	public String getNumeroClient() {
		return numeroClient;
	}

	
	/**
	 * Returns the reference.
	 * @return String
	 */
	public String getReference() {
		return reference;
	}

	
	/**
	 * Sets the numeroClient.
	 * @param numeroClient The numeroClient to set
	 */
	public void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}

	
	/**
	 * Sets the reference.
	 * @param reference The reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	
	/**
	 * Returns the dateNaissance.
	 * @return String
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	
	/**
	 * Sets the dateNaissance.
	 * @param dateNaissance The dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

    
	/**
	 * @return numeroAssuranceMaladie
	 */
	public String getNumeroAssuranceMaladie() {
		return numeroAssuranceMaladie;
	}


	/**
	 * @param numeroAssuranceMaladie numeroAssuranceMaladie à définir
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie) {
		this.numeroAssuranceMaladie = numeroAssuranceMaladie;
	}

    
	/**
	 * @return severiteAutres
	 */
	public long getSeveriteAutres() {
		return severiteAutres;
	}

	
	/**
	 * @param severiteAutres severiteAutres à définir
	 */
	public void setSeveriteAutres(long severiteAutres) {
		this.severiteAutres = severiteAutres;
	}

	
	public int getSequence() {
		return sequence;
	}

	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	public Boolean getOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}


    /**
	 * @return numeroPermisConduire
	 */
	public String getNumeroPermisConduire() {
		return numeroPermisConduire;
	}

	
	/**
	 * @param numeroPermisConduire numeroPermisConduire à définir
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.numeroPermisConduire = numeroPermisConduire;
	}

	
	/**
	 * @return siteOrigine
	 */
	public long getSiteOrigine() {
		return siteOrigine;
	}

	
	/**
	 * @param siteOrigine siteOrigine à définir
	 */
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	
	public long getSeveriteCasino() {
		return severiteCasino;
	}

	
	public void setSeveriteCasino(long severiteCasino) {
		this.severiteCasino = severiteCasino;
	}


    /**
     * Affecte l'indicateur d'âge estimé.
     *
     * @param ageEstime Indicateur d'âge estimé.
     */
    public void setAgeEstime(Boolean ageEstime) {
        this.ageEstime = ageEstime;
    }


    /**
     * Affecte l'indicateur d'âge rééel.
     *
     * @param ageReel Indicateur d'âge réel.
     */
    public void setAgeReel(Boolean ageReel) {
        this.ageReel = ageReel;        
    }


    /**
     * Affecte l'indicateur d'âge rééel plus ou moins.
     *
     * @param ageReelPlusMoins Indicateur d'âge réel plus ou moins.
     */
    public void setAgeReelPlusMoins(Boolean ageReelPlusMoins) {
        this.ageReelPlusMoins = ageReelPlusMoins;        
    }

    /**
     * Affecte l'indicateur d'âge inconnu.
     *
     * @param ageInconnu Indicateur d'âge inconnu.
     */
    public void setAgeInconnu(Boolean ageInconnu) {
        this.ageInconnu = ageInconnu;
    }
    
    
    /**
     * @return the ageEstime
     */
    public Boolean getAgeEstime()
    {
        return ageEstime;
    }


    /**
     * @return the ageReel
     */
    public Boolean getAgeReel()
    {
        return ageReel;
    }


    /**
     * @return the ageReelPlusMoins
     */
    public Boolean getAgeReelPlusMoins()
    {
        return ageReelPlusMoins;
    }


    /**
     * @return the ageInconnu
     */
    public Boolean getAgeInconnu()
    {
        return ageInconnu;
    }
}
