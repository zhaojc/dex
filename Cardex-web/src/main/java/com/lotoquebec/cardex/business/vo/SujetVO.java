package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.business.vo.EntiteCardexVO;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives � la consultation d'un sujet
 * de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.21 $, $Date: 2002/04/15 14:03:08 $
 * @see com.lotoquebec.cardex.business.Sujet
 */
public class SujetVO extends EntiteCardexVO implements Sujet  {

	private long entite = 0;
	private String numeroFiche = GlobalConstants.NumeroFiche.DEFAULT;
	private String nom = "";
	private String prenom = "";
	private String alias = "";
	private Timestamp dateNaissance = null;
	private Timestamp dateFinEmploi = null;
	private String age = "";
	private String reference1 = "";
	private String reference2 = "";
	private String numeroClientEmploye = "";
	private long sexe = 0;
	private long langue = 0;
	private long ethnie = 0;
	private long race = 0;
	private long statut = 0;
	private long severite = 0;
	private long severiteCasino = 0;
	private long severiteAutres = 0;
	private String numeroAssuranceSociale = "";
	private Boolean NASCanadien = false;
	private String numeroAssuranceMaladie = "";
	private String numeroPermisConduire = "";
	private long confidentialite = 0;
	private String passeport = "";
	private String motPasse = "";
	private String confirmationMotPasse = "";
	private long lien = 0;
	private long lienSite = 0;
	private String lienCreateur = "";
	private Timestamp lienDateCreation = null;
	private long role = 0;
	private long typeLien = 0;
	private String createur = "";
	private Timestamp dateCreation = null;
	private List adresses = new ArrayList();
    private String audit = "";    
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";
    //Indique si la donn�e vient de RDD
	private boolean indicateurRdd = false;
    //Mai 2013, date de fin d'enqu�te d'un sujet pour l'onglet Sujets d'une Soci�t�
    private Timestamp dateFinEnquete = null;
    private Set<LiaisonEntiteVO> liaisonEntites = new HashSet<LiaisonEntiteVO>();
    private long typeAge;
	
	/**
	 * Constructeur de SujetVO par d�faut.
	 */
	public SujetVO() {}

	/**
	 * @param cle
	 * @param site
	 */
	public SujetVO(long cle, long site) {
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
		SujetVO other = (SujetVO) obj;
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
	 * Retourne le prenom.
	 *
	 * @return String Valeur du prenom en caract�re.
	 */
	public String getPrenom() {
		return this.prenom;
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
	 * Retourne la date de naissance.
	 *
	 * @return Timestamp Valeur de la date de naissance (yyy-MM-dd).
	 */
	public Timestamp getDateNaissance() {
		return this.dateNaissance;
	}

	/**
	 * Retourne l'�ge.
	 *
	 * @return String Valeur de l'�ge en caract�re.
	 */
	public String getAge() {
		
		if (this.dateNaissance != null) {
		    Calendar naissance = new GregorianCalendar(); 
		    Calendar cToday = new GregorianCalendar(); 
		    naissance.setTime(this.dateNaissance); 
		 
		    int yearDiff = cToday.get(Calendar.YEAR) - naissance.get(Calendar.YEAR); 
		    naissance.set(Calendar.YEAR, cToday.get(Calendar.YEAR));
		    
		    if (!naissance.after(cToday)) { 
		        return String.valueOf(yearDiff); //Birthday already celebrated this year 
		    } 
		    else { 
		    	return String.valueOf(Math.max(0, yearDiff-1)); //Need a max to avoid -1 for baby 
		    } 
		}else{
			return "";
		}		
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
	 * Retourne le num�ro de client ou employ�.
	 *
	 * @return String Valeur du num�ro de client ou employ� en caract�re.
	 */
	public String getNumeroClientEmploye() {
		return this.numeroClientEmploye;
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
	 * Retourne la langue.
	 *
	 * @return long Valeur de la langue.
	 */
	public long getLangue() {
		return this.langue;
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
	 * Retourne le num�ro d'assurance sociale.
	 *
	 * @return String Valeur du num�ro d'assurance sociale en caract�re.
	 */
	public String getNumeroAssuranceSociale() {
		return this.numeroAssuranceSociale;
	}

	/**
	 * Retourne le num�ro de permis de conduire.
	 *
	 * @return String Valeur du num�ro de permis de conduire en caract�re.
	 */
	public String getNumeroPermisConduire() {
		return this.numeroPermisConduire;
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
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caract�re.
	 */
	public String getPasseport() {
		return this.passeport;
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
	 * @return String Valeur num�rique du lien.
	 */
	public long getLien() {
		return this.lien;
	}    // Liens de dossiers

	/**
	 * Retourne le lien du site.
	 *
	 * @return String Valeur num�rique du lien du site.
	 */
	public long getLienSite() {
		return this.lienSite;
	}

	/**
	 * Retourne le r�le.
	 *
	 * @return String Valeur num�rique du r�le.
	 */
	public long getRole() {
		return this.role;
	}

	/**
	 * Retourne le type de lien.
	 *
	 * @return String Valeur num�rique du type de lien.
	 */
	public long getTypeLien() {
		return this.typeLien;
	}

	/**
	 * Retourne le cr�ateur du sujet.
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

    /**
     * Retourne le type d'�ge.
     *
     * @return la valeur num�rique du type d'�ge.
     */
    public long getTypeAge() {
        return this.typeAge;
    }

    // Setters


	/**
	 * Affecte un site.
	 *
	 * @param site Valeur du site.
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Affecte une cle.
	 *
	 * @param cle Valeur de la cle.
	 */
	public void setCle(long cle) {
		this.cle = cle;
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
	 * Affecte un prenom.
	 *
	 * @param prenom Valeur du prenom en caract�re.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	 * Affecte une date de naissance.
	 *
	 * @param dateNaissance Valeur de la date de naissance (yyyy-MM-dd).
	 */
	public void setDateNaissance(Timestamp dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Affecte un �ge.
	 *
	 * @param age Valeur de l'�ge en caract�re.
	 */
	public void setAge(String age) {
		this.age = age;
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
	 * @param reference1 Valeur de la deuxi�me r�f�rence en caract�re.
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * Affecte un num�ro de client ou employ�.
	 *
	 * @param numeroClientEmploye Valeur du num�ro de client ou employ� en caract�re.
	 */
	public void setNumeroClientEmploye(String numeroClientEmploye) {
		this.numeroClientEmploye = numeroClientEmploye;
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
	 * Affecte une langue.
	 *
	 * @param langue Valeur de la langue.
	 */
	public void setLangue(long langue) {
		this.langue = langue;
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
	 * Affecte un num�ro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du num�ro d'assurance sociale en caract�re.
	 */
	public void setNumeroAssuranceSociale(String numeroAssuranceSociale) {
		this.numeroAssuranceSociale = numeroAssuranceSociale;
	}

	/**
	 * Affecte un num�ro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du num�ro de permis de conduire en caract�re.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.numeroPermisConduire = numeroPermisConduire;
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
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caract�re.
	 */
	public void setPasseport(String passeport) {
		this.passeport = passeport;
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
	 * Affecte le lien cle.
	 *
	 * @param lien Valeur num�rique du lien cle.
	 */
	public void setLien(long lien) {
		this.lien = lien;
	}

	/**
	 * Affecte le lien du site.
	 *
	 * @param lienSite Valeur num�rique du lien du site.
	 */
	public void setLienSite(long lienSite) {
		this.lienSite = lienSite;
	}

	/**
	 * Affecte le r�le.
	 *
	 * @param role Valeur num�rique du r�le.
	 */
	public void setRole(long role) {
		this.role = role;
	}

	/**
	 * Affecte le type de lien.
	 *
	 * @param typeLien Valeur num�rique du type de lien.
	 */
	public void setTypeLien(long typeLien) {
		this.typeLien = typeLien;
	}

	/**
	 * Affecte le cr�ateur du sujet.
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
	 * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
	 * attributs du SujetVO.
	 *
	 * @return String Valeur de tout les attributs du SujetVO en caract�re.
	 */
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[SujetVO : ");
		stringBuffer.append("cle = '" + cle);
		stringBuffer.append("', site = '" + site);
		stringBuffer.append("', numeroFiche = '" + numeroFiche);
		stringBuffer.append("', nom = '" + nom);
		stringBuffer.append("', prenom = '" + prenom);
		stringBuffer.append("', alias = '" + alias);
		stringBuffer.append("', dateNaissance = '"
				+ TimestampFormat.format(dateNaissance));
		stringBuffer.append("', age = '" + age);
		stringBuffer.append("', reference1 = '" + reference1);
		stringBuffer.append("', numeroClientEmploye = '" + numeroClientEmploye);
		stringBuffer.append("', sexe = '" + sexe );
		stringBuffer.append("', langue = '" + langue);
		stringBuffer.append("', ethnie = '" + ethnie);
		stringBuffer.append("', race = '" + race);
		stringBuffer.append("', statut = '" + statut);
		stringBuffer.append("', severite = '" + severite);
		stringBuffer.append("', numeroAssuranceSociale = '"
				+ numeroAssuranceSociale);
		stringBuffer.append("', numeroPermisConduire = '"
				+ numeroPermisConduire);
		stringBuffer.append("', confidentialite = '" + confidentialite);
		stringBuffer.append("', passeport = '" + passeport);
		stringBuffer.append("', motPasse = '" + motPasse);
		stringBuffer.append("', lien = '" + lien);
		stringBuffer.append("', lienSite = '" + lienSite);
		stringBuffer.append("', role = '" + role);
		stringBuffer.append("', createur = '" + createur);
		stringBuffer.append("', date de cr�ation = '" + TimestampFormat.format(dateCreation));
		stringBuffer.append("', typeLien = '" + typeLien + "']");
		stringBuffer.append("', typeAge = '" + typeAge + "']");
		return stringBuffer.toString();
	}

	/**
	 * @return
	 */
	public long getEntite() {
		return entite;
	}

	/**
	 * @param l
	 */
	public void setEntite(long l) {
		entite = l;
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
	 * @return lienDateCreation
	 */
	public Timestamp getLienDateCreation() {
		return lienDateCreation;
	}


	/**
	 * @param lienDateCreation lienDateCreation � d�finir
	 */
	public void setLienDateCreation(Timestamp lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}


	/**
	 * Ajoute une adresses associ�s.
	 */
	public void addAdresse(Adresse adresse) {
		this.adresses.add(adresse);
	}

	public Object getCleUnique() {
		return getCle()+"-"+getSite();
	}

   public List getAdresses() {
		return adresses;
	}

	public Boolean isNASCanadien() {
		return NASCanadien;
	}

	public Boolean getNASCanadien() {
		return NASCanadien;
	}

	public void setNASCanadien(Boolean canadien) {
		NASCanadien = canadien;
	}

	/*public void setNASCanadien(String canadien) {
		
		if (canadien != null)
			this.NASCanadien = Boolean.valueOf( (String) canadien );
	}*/
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.Sujet#isPossedeCle()
	 */
	public boolean isPossedeCle() {
		return getCle() != 0;
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

	/* Devrait �tre pr�sent.
	 * public void setAdresses(List adresses) {
		this.adresses = adresses;
	}*/

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
	 * @return dateFinEnquete
	 */
	public Timestamp getDateFinEnquete() {
		return this.dateFinEnquete;
	}

	/**
	 * @param dateFinEnquete dateFinEnquete � d�finir
	 */
	public void setDateFinEnquete(Timestamp dateFinEnquete) {
		this.dateFinEnquete = dateFinEnquete;
	}

	public long getSeveriteCasino() {
		return severiteCasino;
	}

	public void setSeveriteCasino(long severiteCasino) {
		this.severiteCasino = severiteCasino;
	}

	public Timestamp getDateFinEmploi() {
		return this.dateFinEmploi;
	}

	public void setDateFinEmploi(Timestamp dateFinEmploi) {
		this.dateFinEmploi = dateFinEmploi;
	}

	public Set<LiaisonEntiteVO> getLiaisonEntites() {
		return liaisonEntites;
	}

	public void setTypeAge(long typeAge) {
	    this.typeAge = typeAge;
	}
}
