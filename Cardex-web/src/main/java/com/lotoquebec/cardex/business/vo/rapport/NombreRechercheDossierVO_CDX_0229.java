package com.lotoquebec.cardex.business.vo.rapport;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * La classe CriteresRechercheDossierVO est responsable de concerver les
 * informations relatives � la page "Recherche de Dossier".  Offrant les
 * diff�rents "getter" et "setter" associ�s au attribut, afin de modifier leur
 * valeur.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.17 $, $Date: 2002/02/23 02:16:07 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheDossier
 */
public class NombreRechercheDossierVO_CDX_0229 extends CritereRapportVO implements CriteresRechercheDossier {

    private long      entite = 0;
    private long      siteOrigine = 0;
    private long      siteApplicable = 0;
    private long      genre = 0;
    private long      nature = 0;
    private long      type = 0;
    private long      typeCategorie = 0;
    private long      categorie = 0;
    private long      statut = 0;
    private long      service = 0;
    private long      fonde = 0;
    private long		typeJeu = 0;
    private long      jeu = 0;
    private long      endroit = 0;
    private long      localisation = 0;
    private String    intervenant = "";
    private String    groupesIntervenants = "";
    private String    numeroCardex = "";
    private String    numeroDossier = "";
    private String    numeroFicheSujet = "";
    private String    descriptif = "";
    private String    reference1 = "";
    private String    reference2 = "";
    private String    reference3 = "";
    private String    reference5 = "";
    private Timestamp dateFinDu = null;
    private Timestamp dateFinAu = null;
    private long      severite = 0;
    private long      confidentialite = 0;
    private long      origine = 0;
    private String    choixRapport = "";
    private String    referenceVideo = "";
    private String    enregistrementNumerique = "";
    private String    enregistrementConserve = "";
    private Timestamp dateCreationDu = null;
    private Timestamp dateCreationAu = null;
    private String    ordreTriRecherche = "";
    private Boolean   ordreCroissantRecherche = true;
    private long      periode = 0;
    private long      maximumResultatsRecherche = 0;
    private String	  rechercherSousCategorie = "";
    private String	  rechercherTous = "";
    private int sequence = 0;
	private long siteNumeroCardex = 0;
    private String dateNumeroCardex = "";
    private String sequenceNumeroCardex = "";
	private long classe = 0;

    /**
     * Constructeur CriteresRechercheDossierVO par defaut.
     */
    public NombreRechercheDossierVO_CDX_0229() {
        this.entite = 0;
        this.siteOrigine = 0;
        this.siteApplicable = 0;
        this.genre = 0;
        this.nature = 0;
        this.type = 0;
        this.categorie = 0;
        this.statut = 0;
        this.service = 0;
        this.fonde = 0;
        this.intervenant = "";
        this.numeroCardex = "";
        this.numeroDossier = "";
        this.numeroFicheSujet = "";
        this.reference1 = "";
        this.reference2 = "";
        this.reference3 = "";
        this.reference5 = "";
        this.dateFinDu = null;
        this.dateFinAu = null;
        this.severite = 0;
        this.confidentialite = 0;
        this.origine = 0;
        this.referenceVideo = "";
        this.enregistrementNumerique = "";
        this.enregistrementConserve = "";
        this.dateCreationDu = null;
        this.dateCreationAu = null;
        this.ordreTriRecherche = "";
        this.ordreCroissantRecherche = true;
        this.rechercherSousCategorie = "";
        this.rechercherTous = "";
        this.periode = 0;
        this.maximumResultatsRecherche = 0;
    }


    // Getters


    /**
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite() {
        return this.entite;
    }

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine() {
        return this.siteOrigine;
    }

    /**
     * Retourne le site applicable.
     *
     * @return long Valeur num�rique du site applicable.
     */
    public long getSiteApplicable() {
        return this.siteApplicable;
    }

    /**
     * Retourne le genre.
     *
     * @return long Valeur num�rique du genre.
     */
    public long getGenre() {
        return this.genre;
    }

    /**
     * Retourne la nature.
     *
     * @return long Valeur num�rique de la nature.
     */
    public long getNature() {
        return this.nature;
    }

    /**
     * Retourne le type.
     *
     * @return long Valeur num�rique du type.
     */
    public long getType() {
        return this.type;
    }

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur num�rique de la cat�gorie.
     */
    public long getCategorie() {
        return this.categorie;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur num�rique du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    /**
     * Retourne l'attribut fonde.
     *
     * @return long Valeur num�rique de l'attribut fonde.
     */
    public long getFonde() {
        return this.fonde;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le num�ro de cardex.
     *
     * @return String Valeur du num�ro de cardex en caract�re.
     */
    public String getNumeroCardex() {
        return this.numeroCardex;
    }

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le num�ro de fiche sujet.
     *
     * @return String Valeur du num�ro de fiche sujet en caract�re.
     */
    public String getNumeroFicheSujet() {
        return this.numeroFicheSujet;
    }

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�res.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�res.
     */
    public String getReference2() {
        return this.reference2;
    }

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�res.
     */
    public String getReference3() {
        return this.reference3;
    }

    /**
     * Retourne la cinqui�me r�f�rence.
     *
     * @return String Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public String getReference5() {
        return this.reference5;
    }

       /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin (yyyy-MM-dd).
     */
    public Timestamp getDateFinDu() {
        return this.dateFinDu;
    }


    /**
     * Retourne la date de fin au.
     *
     * @return Timestamp Valeur de la date de fin au (yyyy-MM-dd).
     */
    public Timestamp getDateFinAu() {
        return this.dateFinAu;
    }

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur num�rique de la s�v�rit�.
     */
    public long getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur num�rique de la confidentialit�.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine() {
        return this.origine;
    }

    /**
     * Retourne la r�f�rence vid�o.
     *
     * @return String Valeur de la r�f�rence vid�o en caract�re.
     */
    public String getReferenceVideo() {
        return this.referenceVideo;
    }

    /**
     * Retourne la date de cr�ation du.
     *
     * @return Timestamp Valeur de la date de cr�ation du (yyyy-MM-dd).
     */
    public Timestamp getDateCreationDu() {
        return this.dateCreationDu;
    }

    /**
     * Retourne la date de cr�ation au.
     *
     * @return Timestamp Valeur de la date de cr�ation au (yyyy-MM-dd).
     */
    public Timestamp getDateCreationAu() {
        return this.dateCreationAu;
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
     * Retourne la p�riode.
     *
     * @return long Valeur num�rique de la p�riode.
     */
    public long getPeriode() {
        return this.periode;
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

    // Setters


    /**
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine) {
        this.siteOrigine = siteOrigine;
    }

    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur num�rique du site applicable.
     */
    public void setSiteApplicable(long siteApplicable) {
        this.siteApplicable = siteApplicable;
    }

    /**
     * Affecte un genre.
     *
     * @param genre Valeur num�rique du genre.
     */
    public void setGenre(long genre) {
        this.genre = genre;
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur num�rique de la nature.
     */
    public void setNature(long nature) {
        this.nature = nature;
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(long type) {
        this.type = type;
    }

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(long categorie) {
        this.categorie = categorie;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur num�rique de l'attribut fonde.
     */
    public void setFonde(long fonde) {
        this.fonde = fonde;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String numeroCardex) {
        this.numeroCardex = numeroCardex;
    }

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un num�ro de fiche sujet.
     *
     * @param numeroFicheSujet Valeur du num�ro de fiche sujet en caract�re.
     */
    public void setNumeroFicheSujet(String numeroFicheSujet) {
        this.numeroFicheSujet = numeroFicheSujet;
    }

    /**
     * Affecte la premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference) {
        this.reference1 = reference;
    }

    /**
     * Affecte la deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference) {
        this.reference2 = reference;
    }

    /**
     * Affecte la troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference) {
        this.reference3 = reference;
    }

    /**
     * Affecte la cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference) {
        this.reference5 = reference;
    }

    /**
     * Affecte la date de fin du.
     *
     * @param dateFinDu Valeur de la date de fin du (yyyy-MM-dd).
     */
    public void setDateFinDu(Timestamp dateFinDu) {
        this.dateFinDu = dateFinDu;
    }


    /**
     * Affecte la date de fin au.
     *
     * @param dateFinAu Valeur de la date de fin au (yyyy-MM-dd).
     */
    public void setDateFinAu(Timestamp dateFinAu) {
        this.dateFinAu = dateFinAu;
    }

    /**
     * Affecte la s�v�rit�.
     *
     * @param severite Valeur num�rique de la s�v�rit�.
     */
    public void setSeverite(long severite) {
        this.severite = severite;
    }

    /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine) {
        this.origine = origine;
    }

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur num�rique de la r�f�rence vid�o.
     */
    public void setReferenceVideo(String reference) {
        this.referenceVideo = reference;
    }

    /**
     * Affecte la date de cr�ation du.
     *
     * @param dateCreationDu Valeur de la date de cr�ation du (yyyy-MM-dd).
     */
    public void setDateCreationDu(Timestamp dateCreationDu) {
        this.dateCreationDu = dateCreationDu;
    }

    /**
     * Affecte la date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au (yyyy-MM-dd).
     */
    public void setDateCreationAu(Timestamp dateCreationAu) {
        this.dateCreationAu = dateCreationAu;
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
     * Affecte la p�riode.
     *
     * @param periode Valeur num�rique de la p�riode.
     */
    public void setPeriode(long periode) {
        this.periode = periode;
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
     * attributs du CriteresRechercheDossierVO.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheDossierVO
     * en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheDossierVo : ");
        stringBuffer.append("entite = '" + entite);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', siteApplicable = '" + siteApplicable);
        stringBuffer.append("', genre = '" + genre);
        stringBuffer.append("', nature = '" + nature);
        stringBuffer.append("', type = '" + type);
        stringBuffer.append("', categorie = '" + categorie);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', numeroCardex = '" + numeroCardex);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', numeroFicheSujet = '" + numeroFicheSujet);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', reference3 = '" + reference3);
        stringBuffer.append("', reference5 = '" + reference5);
       stringBuffer.append("', dateFinDu = '"
                + TimestampFormat.format(dateFinDu));
        stringBuffer.append("', dateFinAu = '"
                + TimestampFormat.format(dateFinAu));
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', origine = '" + origine);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', referenceVideo = '" + referenceVideo);
        if (dateCreationDu != null) {
            stringBuffer.append("', dateCreationDu = '"
                                + TimestampFormat.format(dateCreationDu));
        } else {
            stringBuffer.append("', dateCreationDu = 'null");
        }
        if (dateCreationDu != null) {
            stringBuffer.append("', dateCreationAu = '"
                                + TimestampFormat.format(dateCreationAu));
        } else {
            stringBuffer.append("', dateCreationAu = 'null");
        }
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the endroit.
	 * @return long
	 */
	public long getEndroit() {
		return endroit;
	}

	/**
	 * Returns the localisation.
	 * @return long
	 */
	public long getLocalisation() {
		return localisation;
	}

	/**
	 * Sets the endroit.
	 * @param endroit The endroit to set
	 */
	public void setEndroit(long endroit) {
		this.endroit = endroit;
	}

	/**
	 * Sets the localisation.
	 * @param localisation The localisation to set
	 */
	public void setLocalisation(long localisation) {
		this.localisation = localisation;
	}

	/**
	 * @return Returns the descriptif.
	 */
	public String getDescriptif() {
		return descriptif;
	}
	/**
	 * @param descriptif The descriptif to set.
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	/**
	 * @return Returns the typeCategorie.
	 */
	public long getTypeCategorie() {
		return typeCategorie;
	}
	/**
	 * @param typeCategorie The typeCategorie to set.
	 */
	public void setTypeCategorie(long typeCategorie) {
		this.typeCategorie = typeCategorie;
	}
	/**
	 * @return Returns the groupesIntervenants.
	 */
	public String getGroupesIntervenants() {
		return groupesIntervenants;
	}
	/**
	 * @param groupesIntervenants The groupesIntervenants to set.
	 */
	public void setGroupesIntervenants(String groupesIntervenants) {
		this.groupesIntervenants = groupesIntervenants;
	}


	/**
	 * @return choixRapport
	 */
	public String getChoixRapport() {
		return choixRapport;
	}


	/**
	 * @param choixRapport choixRapport � d�finir
	 */
	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}


	/**
	 * @return enregistrementNumerique
	 */
	public String getEnregistrementNumerique() {
		return enregistrementNumerique;
	}


	/**
	 * @param enregistrementNumerique enregistrementNumerique � d�finir
	 */
	public void setEnregistrementNumerique(String enregistrementNumerique) {
		this.enregistrementNumerique = enregistrementNumerique;
	}


	/**
	 * @return enregistrementConserve
	 */
	public String getEnregistrementConserve() {
		return enregistrementConserve;
	}


	/**
	 * @param enregistrementConserve enregistrementConserve � d�finir
	 */
	public void setEnregistrementConserve(String enregistrementConserve) {
		this.enregistrementConserve = enregistrementConserve;
	}


	/**
	 * @return service
	 */
	public long getService() {
		return service;
	}


	/**
	 * @param service service � d�finir
	 */
	public void setService(long service) {
		this.service = service;
	}


	/**
	 * @return jeu
	 */
	public long getJeu() {
		return jeu;
	}


	/**
	 * @param jeu jeu � d�finir
	 */
	public void setJeu(long jeu) {
		this.jeu = jeu;
	}


	/**
	 * @return rechercherSousCategorie
	 */
	public String getRechercherSousCategorie() {
		return rechercherSousCategorie;
	}


	/**
	 * @param rechercherSousCategorie rechercherSousCategorie � d�finir
	 */
	public void setRechercherSousCategorie(String rechercherSousCategorie) {
		this.rechercherSousCategorie = rechercherSousCategorie;
	}


	/**
	 * @return rechercherTous
	 */
	public String getRechercherTous() {
		return rechercherTous;
	}


	/**
	 * @param rechercherTous rechercherTous � d�finir
	 */
	public void setRechercherTous(String rechercherTous) {
		this.rechercherTous = rechercherTous;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public long getSiteNumeroCardex() {
		return siteNumeroCardex;
	}

	public void setSiteNumeroCardex(long siteNumeroCardex) {
		this.siteNumeroCardex = siteNumeroCardex;
	}

	public String getDateNumeroCardex() {
		return dateNumeroCardex;
	}

	public void setDateNumeroCardex(String dateNumeroCardex) {
		this.dateNumeroCardex = dateNumeroCardex;
	}

	public String getSequenceNumeroCardex() {
		return sequenceNumeroCardex;
	}

	public void setSequenceNumeroCardex(String sequenceNumeroCardex) {
		this.sequenceNumeroCardex = sequenceNumeroCardex;
	}


	public Boolean getOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}


	public void setDateDebutAu(Timestamp dateDebutAu) {
		super.setDateDebutAu(dateDebutAu);
	}

	public void setDateDebutDu(Timestamp dateDebutDu) {
		super.setDateDebutDu(dateDebutDu);
	}

	public Timestamp getDateDebutAu(){
		return (Timestamp) super.getDateDebutAu();
	}
	
	public Timestamp getDateDebutDu(){
		return (Timestamp) super.getDateDebutDu();
	}


	public long getClasse() {
		return classe;
	}


	public void setClasse(long classe) {
		this.classe = classe;
	}


	public long getTypeJeu() {
		return typeJeu;
	}


	public void setTypeJeu(long typeJeu) {
		this.typeJeu = typeJeu;
	}
	
}