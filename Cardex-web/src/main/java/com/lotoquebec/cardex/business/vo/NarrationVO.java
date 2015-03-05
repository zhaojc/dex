package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à une narration de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.14 $, $Date: 2002/03/08 17:33:45 $
 * @see com.lotoquebec.cardex.business.Narration
 */
public class NarrationVO implements Narration {

    private long cle = 0;
    private long site = 0;
    private long entite = 0;
    private long lien = 0;
    private long lienSite = 0;
    private String genreLiaison = "";
    private double montant = 0;
    private long confidentialiteNarration = 0;
    private long confidentialiteApprobateur = 0;
    private long confidentialiteCreateur = 0;
    private long autoriteNarration = 0;
    private long autoriteApprobateur = 0;
    private long autoriteCreateur = 0;
    private String tempsConsacre = "";
    private String rapporteur = "";
    private String createur = "";
	private String secteur = "";
    private String modificateur = "";
    private String approbateur = "";
    private String reference = "";
    /*Les champs référence servent à conserver les valeurs clés de la fiche
      à laquelle la narration est rattachée. 
      Pour DO : numéro Cardex et numéro de dossier
      Pour SU : fiche, nom et prénom
      Pour SO : nom
      Pour VE : immatriculation
    */
    private String reference1 = "";
    private String reference2 = "";
    private String reference3 = "";
    private Timestamp dateCreation = null;
    private Timestamp dateModification = null;
    private Timestamp dateApprobation = null;
    private String texte = "";
    private String narrationAvecFormat = "";
    private String narrationSansFormat = "";
    private String narrationTemporaire = "";
    private Dossier dossier = new DossierVO();
    private Boolean modifiable = false;
    private Boolean approuvable = false;
    private Boolean permettreModification = false;
    private Boolean nouveau = false;
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";
    private long gabaritUtilise;
    
    /**
     * Constructeur de NarrationVO par défaut.
     */
    public NarrationVO() {}


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne le montant.
     *
     * @return double Valeur du montant.
     */
    public double getMontant() {
        return this.montant;
    }

    /**
     * Retourne la confidentialité de la narration.
     *
     * @return long Valeur de la confidentialité de la narration.
     */
    public long getConfidentialiteNarration() {
        return this.confidentialiteNarration;
    }

    /**
     * Retourne la confidentialité de l'approbateur.
     *
     * @return long Valeur de la confidentialité de l'approbateur.
     */
    public long getConfidentialiteApprobateur() {
        return this.confidentialiteApprobateur;
    }

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return long Valeur de la confidentialité du créateur.
     */
    public long getConfidentialiteCreateur() {
        return this.confidentialiteCreateur;
    }

    /**
     * Retourne l'autorité de la narration.
     *
     * @return long Valeur de l'autorité de la narration.
     */
    public long getAutoriteNarration() {
        return this.autoriteNarration;
    }

    /**
     * Retourne l'autorité de l'approbateur.
     *
     * @return long Valeur de l'autorité de l'approbateur.
     */
    public long getAutoriteApprobateur() {
        return this.autoriteApprobateur;
    }

    /**
     * Retourne l'autorité du créateur.
     *
     * @return long Valeur de l'autorité du créateur.
     */
    public long getAutoriteCreateur() {
        return this.autoriteCreateur;
    }

    /**
     * Retourne le temps consacré.
     *
     * @return String Valeur du temps consacré en caractère.
     */
    public String getTempsConsacre() {
        return this.tempsConsacre;
    }

    /**
     * Retourne le rapporteur.
     *
     * @return String Valeur du rapporteur en caractère.
     */
    public String getRapporteur() {
        return this.rapporteur;
    }

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur() {
        return this.modificateur;
    }

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur() {
        return this.approbateur;
    }

    /**
     * Retourne la référence.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification() {
        return this.dateModification;
    }

    /**
     * Retourne la date de l'approbation.
     *
     * @return Timestamp Valeur de la date de l'approbation (yyyy-MM-dd).
     */
    public Timestamp getDateApprobation() {
        return this.dateApprobation;
    }

    /**
     * Retourne le texte.
     *
     * @return String Valeur du texte en caractère.
     */
    public String getTexte() {
        return this.texte;
    }

    /**
     * Retourne la narration avec format.
     *
     * @return String Valeur de la narration avec format en caractère.
     */
    public String getNarrationAvecFormat() {
        return this.narrationAvecFormat;
    }

    /**
     * Retourne la narration sans format.
     *
     * @return String Valeur de la narration sans format en caractère.
     */
    public String getNarrationSansFormat() {
        return this.narrationSansFormat;
    }

    /**
     * Retourne certaines informations sur le dossier retourné
     * par une recherche de narrations.  Ces informations sont
     * utilisées pour appeler le dossier à l'écran à partir de la
     * liste de résultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier() {
        return this.dossier;
    }

    /**
     * Test si une narration peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable() {
        return this.modifiable;
    }

    /**
     * Test si une narration est nouvelle.
     *
     * @return boolean True si la narration est nouvelle.
     */
    public Boolean isNouveau() {
        return this.nouveau;
    }

    /**
     * Test si on peut permettre la modification d'une narration.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public Boolean isPermettreModification(){
      return this.permettreModification;
    }

    /**
     * Test si une narration peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable() {
        return this.approuvable;
    }

    // Setters


    /**
     * Affecte une clé.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte un montant.
     *
     * @param montant Valeur du montant.
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /**
     * Affecte une confidentialité de la narration.
     *
     * @param confidentialiteNarration Valeur de la confidentialité de la
     * narration.
     */
    public void setConfidentialiteNarration(long confidentialiteNarration) {
        this.confidentialiteNarration = confidentialiteNarration;
    }

    /**
     * Affecte une confidentialité de l'approbation.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialité de
     * l'approbateur.
     */
    public void setConfidentialiteApprobateur(long confidentialiteApprobateur) {
        this.confidentialiteApprobateur = confidentialiteApprobateur;
    }

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur) {
        this.confidentialiteCreateur = confidentialiteCreateur;
    }

    /**
     * Affecte une autorité de la narration.
     *
     * @param autoriteNarration Valeur de l'autorité de la narration.
     */
    public void setAutoriteNarration(long autoriteNarration) {
        this.autoriteNarration = autoriteNarration;
    }

    /**
     * Affecte une autorité de l'approbateur.
     *
     * @param autoriteApprobateur Valeur de l'autorité de l'approbateur.
     */
    public void setAutoriteApprobateur(long autoriteApprobateur) {
        this.autoriteApprobateur = autoriteApprobateur;
    }

    /**
     * Affecte une autorité du créateur.
     *
     * @param autoriteCreateur Valeur de l'autorité du créateur.
     */
    public void setAutoriteCreateur(long autoriteCreateur) {
        this.autoriteCreateur = autoriteCreateur;
    }

    /**
     * Affecte un temps consacré.
     *
     * @param tempsConsacre Valeur du temps consacré en caractère.
     */
    public void setTempsConsacre(String tempsConsacre) {
        this.tempsConsacre = tempsConsacre;
    }

    /**
     * Affecte un rapporteur.
     *
     * @param rapporteur Valeur du rapporteur en caractère.
     */
    public void setRapporteur(String rapporteur) {
        this.rapporteur = rapporteur;
    }

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur) {
        this.modificateur = modificateur;
    }

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur) {
        this.approbateur = approbateur;
    }

    /**
     * Affecte un nouvelle narration.
     *
     * @param isNouveau Valeur de l'approbateur en caractère.
     */
    public void setNouveau(Boolean nouveau) {
        this.nouveau = nouveau;
    }

    /**
     * Affecte une référence.
     *
     * @param reference Valeur de la référence en caractère.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Affecte une date de l'approbation.
     *
     * @param dateApprobation Valeur de la date de l'approbation (yyyy-MM-dd).
     */
    public void setDateApprobation(Timestamp dateApprobation) {
        this.dateApprobation = dateApprobation;
    }

    /**
     * Affecte un texte.
     *
     * @param texte Valeur du texte en caractère.
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Affecte une narration avec format.
     *
     * @param narrationAvecFormat Valeur de la narration avec format en
     * caractère.
     */
    public void setNarrationAvecFormat(String narrationAvecFormat) {
        this.narrationAvecFormat = narrationAvecFormat;
    }

    /**
     * Affecte une narration sans format.
     *
     * @param narrationSansFormat Valeur de la narration sans format en
     * caractère.
     */
    public void setNarrationSansFormat(String narrationSansFormat) {
        this.narrationSansFormat = narrationSansFormat;
    }

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourné par une recherche de narrations.
     */
    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }

    /**
     * Determine si une narration est modifiable
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caractère.
     */
    public void setModifiable(Boolean modifiable) {
      this.modifiable = modifiable;
    }

    /**
     * Determine si une narration est approuvanle
     *
     * @param isApprouvable Est-ce que la narration est approuvable
     * caractère.
     */
    public void setApprouvable(Boolean approuvable) {
      this.approuvable = approuvable;
    }

    /**
     * Détermine si on peut permettre la modification d'une narration.
     *
     * @param isPermettreModification Est-ce qu'on peut permettre la modification d'une narration.
     * caractère.
     */
    public void setPermettreModification(Boolean permettreModification){
      this.permettreModification = permettreModification;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du NarrationVO.
     *
     * @return String Valeur de tout les attributs du NarrationVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[NarrationVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', montant = '" + montant);
        stringBuffer.append("', confidentialiteNarration = '"
                + confidentialiteNarration);
        stringBuffer.append("', confidentialiteApprobateur = '"
                + confidentialiteApprobateur);
        stringBuffer.append("', confidentialiteCreateur = '"
                + confidentialiteCreateur);
        stringBuffer.append("', autoriteNarration = '" + autoriteNarration);
        stringBuffer.append("', autoriteApprobateur = '" + autoriteApprobateur);
        stringBuffer.append("', autoriteCreateur = '" + autoriteCreateur);
        stringBuffer.append("', tempsConsacre = '" + tempsConsacre);
        stringBuffer.append("', rapporteur = '" + rapporteur);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', approbateur = '" + approbateur);
        stringBuffer.append("', reference = '" + reference);
        stringBuffer.append("', dateCreation = '"
                + TimestampFormat.format(dateCreation));
        stringBuffer.append("', dateModification = '"
                + TimestampFormat.format(dateModification));
        stringBuffer.append("', dateApprobation = '"
                + TimestampFormat.format(dateApprobation));
        stringBuffer.append("']" );
        return stringBuffer.toString();
    }

	/**
	 * Returns the reference2.
	 * @return String
	 */
	public String getReference2() {
		return reference2;
	}

	/**
	 * Returns the reference3.
	 * @return String
	 */
	public String getReference3() {
		return reference3;
	}

	/**
	 * Sets the reference2.
	 * @param reference2 The reference2 to set
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * Sets the reference3.
	 * @param reference3 The reference3 to set
	 */
	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}

	/**
	 * Returns the reference1.
	 * @return String
	 */
	public String getReference1() {
		return reference1;
	}

	/**
	 * Sets the reference1.
	 * @param reference1 The reference1 to set
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * @return
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * @param string
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public Boolean isRapportActiviteQuotidienne() {
		return String.valueOf(GlobalConstants.GabaritNarration.RAPPORT_ACTIVITE_QUOTIDIEN).equals(gabaritUtilise);
	}
	
	/**
	 * @return dateChangement
	 */
	public Timestamp getDateChangement() {
		return dateChangement;
	}


	/**
	 * @param dateChangement dateChangement à définir
	 */
	public void setDateChangement(Timestamp dateChangement) {
		this.dateChangement = dateChangement;
	}


	/**
	 * @return changePar
	 */
	public String getChangePar() {
		return changePar;
	}


	/**
	 * @param changePar changePar à définir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
	}


	public Boolean getModifiable() {
		return modifiable;
	}


	public Boolean getApprouvable() {
		return approuvable;
	}


	public Boolean getPermettreModification() {
		return permettreModification;
	}


	public Boolean getNouveau() {
		return nouveau;
	}




	/**
	 * @return entite
	 */
	public long getEntite() {
		return entite;
	}


	/**
	 * @param entite entite à définir
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}


	public String getNarrationTemporaire() {
		return narrationTemporaire;
	}


	public void setNarrationTemporaire(String narrationTemporaire) {
		this.narrationTemporaire = narrationTemporaire;
	}


	public String getGenreLiaison() {
		return genreLiaison;
	}

	public void setGenreLiaison(String genreLiaison) {
		this.genreLiaison = genreLiaison;
	}

	public long getGabaritUtilise() {
		return gabaritUtilise;
	}

	public void setGabaritUtilise(long gabaritUtilise) {
		this.gabaritUtilise = gabaritUtilise;
	}

}