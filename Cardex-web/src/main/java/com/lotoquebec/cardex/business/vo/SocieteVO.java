package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives � la consultation d'une
 * soci�t� de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.14 $, $Date: 2002/04/15 14:03:08 $
 * @see com.lotoquebec.cardex.business.Societe
 */
public class SocieteVO implements Societe {

    private long cle = 0;
    private long site = 0;
    private long entite = 0;
    private String numeroFiche = "";
    private String nom = "";
    private String referenceNom = "";
    private String referencePrenom = "";
    private String raisonEtre = "";
    private long classe = 0;
    private Timestamp dateDeFondation = null;
    private String reference1 = "";
    private String reference2 = "";
    private long statut = 0;
    private long langue = 0;
    private long severite = 0;
	private long severiteCasino = 0;
    private long confidentialite = 0;
    private String motPasse = "";
    private String confirmationMotPasse = "";
    private long lien = 0;
    private long lienSite = 0;
	private Timestamp lienDateCreation = null;
	private String lienCreateur = "";
	private long role = 0;
    private long typeLien = 0;
    private String createur = "";
    private Timestamp dateCreation = null;
    private List adresses = new ArrayList(); //AdresseVO
    private String audit = "";    
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";
    //Septembre 2012, donn�es du syst�me RDD (R�seau des d�taillants)
    private String centreRegional = "";
    private String centreRegionalDescription = "";
    private String codeCompte = "";
    private String codeCompteDescription = "";
    private String district = "";
    private String districtDescription = "";
    private boolean actif = false;
    private Timestamp dateInactif = null;
    private String commentaire = "";
	private boolean indicateurRdd = false;
    private String raisonDesactivation = "";
        
    //M�thode d'�chantillonnage des d�taillants RDD
	private long echantillonnage = 0;
	
	private Set<LiaisonEntiteVO> liaisonEntites = new HashSet<LiaisonEntiteVO>();
	
	
    /**
     * Constructeur de SocieteVO par d�faut.
     */
    public SocieteVO() {}

    /**
	 * @param cle
	 * @param site
	 */
	public SocieteVO(long cle, long site) {
		super();
		this.cle = cle;
		this.site = site;
	}

	// Equals
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cle ^ (cle >>> 32));
		result = prime * result + (int) (site ^ (site >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocieteVO other = (SocieteVO) obj;
		if (cle != other.cle)
			return false;
		if (site != other.site)
			return false;
		return true;
	}	
	
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
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroFiche() {
        return this.numeroFiche;
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
     * Retourne le nom de r�f�rence.
     *
     * @return String Valeur du nom de r�f�rence en caract�re.
     */
    public String getReferenceNom() {
        return this.referenceNom;
    }

    /**
     * Retourne le pr�nom de r�f�rence.
     *
     * @return String Valeur du pr�nom de r�f�rence en caract�re.
     */
    public String getReferencePrenom() {
        return this.referencePrenom;
    }

    /**
     * Retourne la raison d'�tre.
     *
     * @return String Valeur de la raison d'�tre en caract�re.
     */
    public String getRaisonEtre() {
        return this.raisonEtre;
    }

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse() {
        return this.classe;
    }

    /**
     * Retourne la date de fondation.
     *
     * @return Timestamp Valeur de la date de fondation (yyyy-MM-dd).
     */
    public Timestamp getDateDeFondation() {
        return this.dateDeFondation;
    }

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2() {
        return this.reference2;
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
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue() {
        return this.langue;
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
     * Retourne la confidentialite.
     *
     * @return long Valeur de la confidentialite.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasse() {
        return this.motPasse;
    }

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getConfirmationMotPasse() {
        return this.confirmationMotPasse;
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
     * Retourne le r�le.
     *
     * @return long Valeur du lien du r�le.
     */
    public long getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return long Valeur du lien du type de lien.
     */
    public long getTypeLien() {
        return this.typeLien;
    }

    /**
     * Retourne le cr�ateur de la soci�t�.
     *
     * @return String Code du cr�ateur
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date.
     */
    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    // Setters


    /**
     * Affecte une cle.
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
     * Affecte un num�ro de fiche.
     *
     * @param numeroFiche Valeur du num�ro de fiche en caract�re.
     */
    public void setNumeroFiche(String numeroFiche) {
        this.numeroFiche = numeroFiche;
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
     * Affecte un nom de r�f�rence.
     *
     * @param referenceNom Valeur du nom de r�f�rence en caract�re.
     */
    public void setReferenceNom(String referenceNom) {
        this.referenceNom = referenceNom;
    }

    /**
     * Affecte un pr�nom de r�f�rence.
     *
     * @param referencePrenom Valeur du pr�nom de r�f�rence en caract�re.
     */
    public void setReferencePrenom(String referencePrenom) {
        this.referencePrenom = referencePrenom;
    }

    /**
     * Affecte une raison d'�tre.
     *
     * @param raisonEtre Valeur de la raison d'�tre en caract�re.
     */
    public void setRaisonEtre(String raisonEtre) {
        this.raisonEtre = raisonEtre;
    }

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe.
     */
    public void setClasse(long classe) {
        this.classe = classe;
    }

    /**
     * Affecte une date de fondation.
     *
     * @param dateDeFondation Valeur de la date de fondation (yyyy-MM-dd).
     */
    public void setDateDeFondation(Timestamp dateDeFondation) {
        this.dateDeFondation = dateDeFondation;
    }

    /**
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    /**
     * Affecte une deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2) {
        this.reference2 = reference2;
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
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue) {
        this.langue = langue;
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
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setConfirmationMotPasse(String motPasse) {
        this.confirmationMotPasse = motPasse;
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
     * Affecte un r�le.
     *
     * @param role Valeur du r�le.
     */
    public void setRole(long role) {
        this.role = role;
    }

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien.
     */
    public void setTypeLien(long typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * Affecte le cr�ateur de la soci�t�.
     *
     * @param createur Code du cr�ateur.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte la date de cr�ation.
     *
     * @param dateCreation Valeur de la date.
     */
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

	/**
	 * @return
	 */
	public String getLienCreateur() {
		return lienCreateur;
	}

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur) {
		this.lienCreateur = lienCreateur;
	}

   public List getAdresses() {
		return adresses;
	}

/*	public void setAdresses(List adresses) {
		this.adresses = adresses;
	}
*/
	/**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du SocieteVO.
     *
     * @return String Valeur de tout les attributs du SocieteVO en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[SocieteVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', numeroFiche = '" + numeroFiche);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', referenceNom = '" + referenceNom);
        stringBuffer.append("', referencePrenom = '" + referencePrenom);
        stringBuffer.append("', raisonEtre = '" + raisonEtre);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', dateDeFondation = '"
                    + TimestampFormat.format(dateDeFondation));
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', statut = '" + statut );
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', motPasse = '" + motPasse);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', date de cr�ation = '" + TimestampFormat.format(dateCreation));
        stringBuffer.append("', typeLien = '" + typeLien);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.business.Societe#getCleUnique()
     */
	public String getCleUnique() {
		return getCle()+"-"+getSite();
	}

	public void addAdresse(Adresse adresse){
		adresses.add(adresse);
	}

	public long getEntite() {
		return entite;
	}

	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * @return dateChangement
	 */
	public Timestamp getDateChangement() {
		return dateChangement;
	}

	/**
	 * @param dateChangement dateChangement � d�finir
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
	 * @param changePar changePar � d�finir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
	}

	public Timestamp getLienDateCreation() {
		return lienDateCreation;
	}

	public void setLienDateCreation(Timestamp lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}

	/**
	 * Indique si la donn�es provient de l'audit des changements
	 * @return
	 */
	public String getAudit(){
		return audit;
	}

	/**
	 * @param string
	 */
	public void setAudit(String audit){
		this.audit = audit;
	}

	/**
	 * @return centreRegional
	 */
	public String getCentreRegional() {
		return centreRegional;
	}

	/**
	 * @param centreRegional centreRegional � d�finir
	 */
	public void setCentreRegional(String centreRegional) {
		this.centreRegional = centreRegional;
	}

	/**
	 * @return codeCompte
	 */
	public String getCodeCompte() {
		return codeCompte;
	}

	/**
	 * @param codeCompte codeCompte � d�finir
	 */
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	/**
	 * @return district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district district � d�finir
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif actif � d�finir
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

	/**
	 * @return dateInactif
	 */
	public Timestamp getDateInactif() {
		return dateInactif;
	}

	/**
	 * @param dateInactif dateInactif � d�finir
	 */
	public void setDateInactif(Timestamp dateInactif) {
		this.dateInactif = dateInactif;
	}

	/**
	 * @return commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * @param commentaire commentaire � d�finir
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * @return indicateurRdd
	 */
	public boolean isIndicateurRdd() {
		return indicateurRdd;
	}

	/**
	 * @param indicateurRdd indicateurRdd � d�finir
	 */
	public void setIndicateurRdd(boolean indicateurRdd) {
		this.indicateurRdd = indicateurRdd;
	}

	/**
	 * @return centreRegionalDescription
	 */
	public String getCentreRegionalDescription() {
		return centreRegionalDescription;
	}

	/**
	 * @param centreRegionalDescription centreRegionalDescription � d�finir
	 */
	public void setCentreRegionalDescription(String centreRegionalDescription) {
		this.centreRegionalDescription = centreRegionalDescription;
	}

	/**
	 * @return codeCompteDescription
	 */
	public String getCodeCompteDescription() {
		return codeCompteDescription;
	}

	/**
	 * @param codeCompteDescription codeCompteDescription � d�finir
	 */
	public void setCodeCompteDescription(String codeCompteDescription) {
		this.codeCompteDescription = codeCompteDescription;
	}

	/**
	 * @return raisonDesactivation
	 */
	public String getRaisonDesactivation() {
		return raisonDesactivation;
	}

	/**
	 * @param raisonDesactivation raisonDesactivation � d�finir
	 */
	public void setRaisonDesactivation(String raisonDesactivation) {
		this.raisonDesactivation = raisonDesactivation;
	}
	
	/**
	 * @return districtDescription
	 */
	public String getDistrictDescription() {
		return districtDescription;
	}


	/**
	 * @param districtDescription districtDescription � d�finir
	 */
	public void setDistrictDescription(String districtDescription) {
		this.districtDescription = districtDescription;
	}

	public long getSeveriteCasino() {
		return severiteCasino;
	}

	public void setSeveriteCasino(long severiteCasino) {
		this.severiteCasino = severiteCasino;
	}

	public long getEchantillonnage() {
		return echantillonnage;
	}

	public void setEchantillonnage(long echantillonnage) {
		this.echantillonnage = echantillonnage;
	}

	public Set<LiaisonEntiteVO> getLiaisonEntites() {
		return liaisonEntites;
	}

}