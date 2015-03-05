package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresRechercheSujet;

/**
 * Permet de transiter les informations relatives � la recherche d'un sujet de
 * la couche pr�sentation � la couche d'affaire.
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
     * Constructeur de CriteresRechercheSujetVO par d�faut.
     */
    public CriteresRechercheSujetVO() {}


    // Getters


    /**
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroFiche() {
        return this.numeroFiche;
    }

    
    /**
     * Retourne le nom phon�tique.
     *
     * @return String Valeur du nom phon�tique en caract�re.
     */
    public String getNomPhonetique() {
        return this.nomPhonetique;
    }

    
    /**
     * Retourne le prenom phon�tique.
     *
     * @return String Valeur du prenom phon�tique en caract�re.
     */
    public String getPrenomPhonetique() {
        return this.prenomPhonetique;
    }

    
    /**
     * Retourne l'alias.
     *
     * @return String Valeur de l'alias en caract�re.
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
     * @return String Valeur du nom en caract�re.
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
     * @return boolean Valeur bool�enne indiquant si la condition OR est utilis�e
     * pour la caract�ristique
     */
    public String isOr1() {
        return this.or1;
    }

    
    /**
     * Retourne si la condition.
     *
     * @return boolean Valeur bool�enne indiquant si la condition OR est utilis�e
     * pour la caract�ristique
     */
    public String isOr2() {
        return this.or2;
    }
    
    
    /**
     * Retourne si la condition.
     *
     * @return boolean Valeur bool�enne indiquant si la condition OR est utilis�e
     * pour la caract�ristique
     */
    public String isOr3() {
        return this.or3;
    }
    
	
    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite() {
        return this.entite;
    }

    
    /**
     * Retourne le r�le.
     *
     * @return long Valeur du r�le.
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
     * Retourne la s�v�rit�.
     *
     * @return long Valeur de la s�v�rit�.
     */
    public long getSeverite() {
        return this.severite;
    }

    
    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    
    /**
     * Retourne la premi�re caract�ristique.
     *
     * @return long Valeur de la premi�re caract�ristique.
     */
    public long getCaracteristique1() {
        return this.caracteristique1;
    }

    
    /**
     * Retourne la deuxi�me caract�ristique.
     *
     * @return long Valeur de la deuxi�me caract�ristique.
     */
    public long getCaracteristique2() {
        return this.caracteristique2;
    }

    
    /**
     * Retourne la troisi�me caract�ristique.
     *
     * @return long Valeur de la troisi�me caract�ristique.
     */
    public long getCaracteristique3() {
        return this.caracteristique3;
    }

    
    /**
     * Retourne la quatri�me caract�ristique.
     *
     * @return long Valeur de la quatri�me caract�ristique.
     */
    public long getCaracteristique4() {
        return this.caracteristique4;
    }

    
    /**
     * Retourne la cinqui�me caract�ristique.
     *
     * @return long Valeur de la cinqui�me caract�ristique.
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
     * @return String Valeur du passeport en caract�re.
     */
    public String getPasseport() {
        return this.passeport;
    }
    
    
    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    
    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    
    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
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
     * Affecte un num�ro de fiche.
     *
     * @param numeroFiche Valeur du num�ro de fiche en caract�re.
     */
    public void setNumeroFiche(String numeroFiche) {
        this.numeroFiche = numeroFiche;
    }


    /**
     * Affecte un nom phon�tique.
     *
     * @param nomPhonetique Valeur du nom phon�tique en caract�re.
     */
    public void setNomPhonetique(String nomPhonetique) {
        this.nomPhonetique = nomPhonetique;
    }

    
    /**
     * Affecte un prenom phon�tique.
     *
     * @param prenomPhonetique Valeur du prenom phon�tique en caract�re.
     */
    public void setPrenomPhonetique(String prenomPhonetique) {
        this.prenomPhonetique = prenomPhonetique;
    }

    
    /**
     * Affecte un alias.
     *
     * @param alias Valeur de l'alias en caract�re.
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
     * @param nom Valeur du nom en caract�re.
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
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }

    
    /**
     * Affecte un r�le.
     *
     * @param role Valeur du r�le.
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
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit�.
     */
    public void setSeverite(long severite) {
        this.severite = severite;
    }

    
    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    
    /**
     * Affecte une premi�re caract�ristique.
     *
     * @param caracteristique1 Valeur de la premi�re caract�ristique.
     */
    public void setCaracteristique1(long caracteristique1) {
        this.caracteristique1 = caracteristique1;
    }

    
    /**
     * Affecte une deuxi�me caract�ristique.
     *
     * @param caracteristique2 Valeur de la deuxi�me caract�ristique.
     */
    public void setCaracteristique2(long caracteristique2) {
        this.caracteristique2 = caracteristique2;
    }

    
    /**
     * Affecte une troisi�me caract�ristique.
     *
     * @param caracteristique3 Valeur de la troisi�me caract�ristique.
     */
    public void setCaracteristique3(long caracteristique3) {
        this.caracteristique3 = caracteristique3;
    }

    
    /**
     * Affecte une quatri�me caract�ristique.
     *
     * @param caracteristique4 Valeur de la quatri�me caract�ristique.
     */
    public void setCaracteristique4(long caracteristique4) {
        this.caracteristique4 = caracteristique4;
    }

    
    /**
     * Affecte une cinqui�me caract�ristique.
     *
     * @param caracteristique5 Valeur de la cinqui�me caract�ristique.
     */
    public void setCaracteristique5(long caracteristique5) {
        this.caracteristique5 = caracteristique5;
    }

    
    /**
     * Affecte un passeport.
     *
     * @param passeport Valeur du passeport en caract�re.
     */
    public void setPasseport(String passeport) {
        this.passeport = passeport;
    }
    
    
    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    
    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    
    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    
    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheSujetVO.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheSujetVO
     * en caract�re.
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
     * @param or1 Valeur bool�enne indiquant si la condition est utilis�e
     */
    public void setOr1(String or1) {
        this.or1 = or1;
    }

    
    /**
     * Affecte la condition OR.
     *
     * @param or1 Valeur bool�enne indiquant si la condition est utilis�e
     */
    public void setOr2(String or2) {
        this.or2 = or2;
    }

    
    /**
     * Affecte la condition OR.
     *
     * @param or1 Valeur bool�enne indiquant si la condition est utilis�e
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
	 * @param numeroAssuranceMaladie numeroAssuranceMaladie � d�finir
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
	 * @param severiteAutres severiteAutres � d�finir
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
	 * @param numeroPermisConduire numeroPermisConduire � d�finir
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
	 * @param siteOrigine siteOrigine � d�finir
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
     * Affecte l'indicateur d'�ge estim�.
     *
     * @param ageEstime Indicateur d'�ge estim�.
     */
    public void setAgeEstime(Boolean ageEstime) {
        this.ageEstime = ageEstime;
    }


    /**
     * Affecte l'indicateur d'�ge r��el.
     *
     * @param ageReel Indicateur d'�ge r�el.
     */
    public void setAgeReel(Boolean ageReel) {
        this.ageReel = ageReel;        
    }


    /**
     * Affecte l'indicateur d'�ge r��el plus ou moins.
     *
     * @param ageReelPlusMoins Indicateur d'�ge r�el plus ou moins.
     */
    public void setAgeReelPlusMoins(Boolean ageReelPlusMoins) {
        this.ageReelPlusMoins = ageReelPlusMoins;        
    }

    /**
     * Affecte l'indicateur d'�ge inconnu.
     *
     * @param ageInconnu Indicateur d'�ge inconnu.
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
