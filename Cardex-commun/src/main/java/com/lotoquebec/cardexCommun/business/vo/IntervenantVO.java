package com.lotoquebec.cardexCommun.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardexCommun.business.Intervenant;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/28 17:28:14 $
 * @see com.lotoquebec.cardexCommun.business.Adresse
 */
public class IntervenantVO implements Intervenant {

    private long cle = 0;
    private long site = 0;
    private String code = "";
    private String commentaire = "";
    private long confidentialite = 0;
    private long hierarchie = 0;
    private String courriel = "";
    private String DN = "";
    private long statut = 0;
    private String createur = "";
    private String modificateur = "";
    private String motPasse = "";
    private Timestamp dateCreation = null;
    private Timestamp dateModification = null;
    private long langue = 0;
    private String nom = "";
    private String prenom = "";
    private String numero = "";
    private long entite = 0;
    private long secteur = 0;
    private long sousSecteur = 0;
    private String groupe = "";
    private String codeParent = "";
    private ArrayList groupesCT = new ArrayList();
    private String secteurDescription = "";
    private String sousSecteurDescription = "";
    private String statutDescription = "";
    private String autoriteDescription = "";
    private String confidentialiteDescription = "";
    private String siteDescription = "";
    private String gestionnaire = "";
    private Timestamp dateChangement= null;
    private ArrayList groupesChoisis = new ArrayList();
    private String     groupesIntervenants = "";
    
    /**
     * Constructeur de AdresseVO par défaut.
     */
    public IntervenantVO() {}



    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du AdresseVO.
     *
     * @return String Valeur de tout les attributs du AdresseVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[IntervenantVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', commentaire = '" + commentaire);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateCreation = '"
                    + TimestampFormat.format(dateCreation));
        stringBuffer.append("', dateModification = '"
                    + TimestampFormat.format(dateModification));
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

    /**
     * Ajoute un groupe ClearTrust
     *
     * @param groupeCT Valeur du groupe
     */
    public void addGroupesCT(String groupeCT) {
        this.groupesCT.add(groupeCT);
    }

    /**
     * Retourne liste de groupes
     *
     * @return Collection Valeur de la liste des groupes ClearTrust de l'intervenant.
     */
    public Collection getGroupesCT() {
        return this.groupesCT;
    }

	/**
	 * Gets the cle
	 * @return Returns a long
	 */
	public long getCle() {
		return cle;
	}
	/**
	 * Sets the cle
	 * @param cle The cle to set
	 */
	public void setCle(long cle) {
		this.cle = cle;
	}

	/**
	 * Gets the code
	 * @return Returns a String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Sets the code
	 * @param code The code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the commentaire
	 * @return Returns a String
	 */
	public String getCommentaire() {
		return commentaire;
	}
	/**
	 * Sets the commentaire
	 * @param commentaire The commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * Gets the confidentialite
	 * @return Returns a long
	 */
	public long getConfidentialite() {
		return confidentialite;
	}
	/**
	 * Sets the confidentialite
	 * @param confidentialite The confidentialite to set
	 */
	public void setConfidentialite(long confidentialite) {
		this.confidentialite = confidentialite;
	}

	/**
	 * Gets the courriel
	 * @return Returns a String
	 */
	public String getCourriel() {
		return courriel;
	}
	/**
	 * Sets the courriel
	 * @param courriel The courriel to set
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	/**
	 * Gets the createur
	 * @return Returns a String
	 */
	public String getCreateur() {
		return createur;
	}
	/**
	 * Sets the createur
	 * @param createur The createur to set
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

	/**
	 * Gets the dateCreation
	 * @return Returns a Timestamp
	 */
	public Timestamp getDateCreation() {
		return dateCreation;
	}
	/**
	 * Sets the dateCreation
	 * @param dateCreation The dateCreation to set
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the dateModification
	 * @return Returns a Timestamp
	 */
	public Timestamp getDateModification() {
		return dateModification;
	}
	/**
	 * Sets the dateModification
	 * @param dateModification The dateModification to set
	 */
	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the dN
	 * @return Returns a String
	 */
	public String getDN() {
		return DN;
	}
	/**
	 * Sets the dN
	 * @param dN The dN to set
	 */
	public void setDN(String dN) {
		DN = dN;
	}

	/**
	 * Gets the hierarchie
	 * @return Returns a long
	 */
	public long getHierarchie() {
		return hierarchie;
	}
	/**
	 * Sets the hierarchie
	 * @param hierarchie The hierarchie to set
	 */
	public void setHierarchie(long hierarchie) {
		this.hierarchie = hierarchie;
	}

	/**
	 * Gets the langue
	 * @return Returns a long
	 */
	public long getLangue() {
		return langue;
	}
	/**
	 * Sets the langue
	 * @param langue The langue to set
	 */
	public void setLangue(long langue) {
		this.langue = langue;
	}

	/**
	 * Gets the modificateur
	 * @return Returns a String
	 */
	public String getModificateur() {
		return modificateur;
	}
	/**
	 * Sets the modificateur
	 * @param modificateur The modificateur to set
	 */
	public void setModificateur(String modificateur) {
		this.modificateur = modificateur;
	}

	/**
	 * Gets the nom
	 * @return Returns a String
	 */
	public String getNom() {
		return nom;
	}

	public String getNomComplet() {
		return prenom+", "+nom;
	}
	
	/**
	 * Sets the nom
	 * @param nom The nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets the numero
	 * @return Returns a String
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * Sets the numero
	 * @param numero The numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the prenom
	 * @return Returns a String
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Sets the prenom
	 * @param prenom The prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Gets the site
	 * @return Returns a long
	 */
	public long getSite() {
		return site;
	}
	/**
	 * Sets the site
	 * @param site The site to set
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Gets the statut
	 * @return Returns a long
	 */
	public long getStatut() {
		return statut;
	}
	/**
	 * Sets the statut
	 * @param statut The statut to set
	 */
	public void setStatut(long statut) {
		this.statut = statut;
	}

	/**
	 * Gets the entite
	 * @return Returns a long
	 */
	public long getEntite() {
		return entite;
	}
	/**
	 * Sets the entite
	 * @param entite The entite to set
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * Gets the groupe
	 * @return Returns a String
	 */
	public String getGroupe() {
		return groupe;
	}
	/**
	 * Sets the groupe
	 * @param groupe The groupe to set
	 */
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	/**
	 * Gets the motPasse
	 * @return Returns a String
	 */
	public String getMotPasse() {
		return motPasse;
	}
	/**
	 * Sets the motPasse
	 * @param motPasse The motPasse to set
	 */
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getCodeParent() {
		return codeParent;
	}

	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}

	/**
	 * @return secteur
	 */
	public long getSecteur() {
		return secteur;
	}


	/**
	 * @param secteur secteur à définir
	 */
	public void setSecteur(long secteur) {
		this.secteur = secteur;
	}



	/**
	 * @return secteurDescription
	 */
	public String getSecteurDescription() {
		return secteurDescription;
	}



	/**
	 * @param secteurDescription secteurDescription à définir
	 */
	public void setSecteurDescription(String secteurDescription) {
		this.secteurDescription = secteurDescription;
	}



	/**
	 * @return statutDescription
	 */
	public String getStatutDescription() {
		return statutDescription;
	}



	/**
	 * @param statutDescription statutDescription à définir
	 */
	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}



	/**
	 * @return autoriteDescription
	 */
	public String getAutoriteDescription() {
		return autoriteDescription;
	}



	/**
	 * @param autoriteDescription autoriteDescription à définir
	 */
	public void setAutoriteDescription(String autoriteDescription) {
		this.autoriteDescription = autoriteDescription;
	}



	/**
	 * @return confidentialiteDescription
	 */
	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}



	/**
	 * @param confidentialiteDescription confidentialiteDescription à définir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}



	/**
	 * @return siteDescription
	 */
	public String getSiteDescription() {
		return siteDescription;
	}



	/**
	 * @param siteDescription siteDescription à définir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public Timestamp getDateChangement() {
		return dateChangement;
	}

	public void setDateChangement(Timestamp dateChangement) {
		this.dateChangement = dateChangement;
	}

    /**
     * Retourne liste des groupes choisis.
     *
     * @return Collection Valeur de la liste des groupes choisis.
     */
    public Collection getGroupesChoisis() {
        return this.groupesChoisis;
    }

    /**
	 * @return groupesChoisis
	 */
    public void addGroupesChoisis(String groupe) {
        this.groupesChoisis.add(groupe);
    }



	/**
	 * @return sousSecteur
	 */
	public long getSousSecteur() {
		return sousSecteur;
	}



	/**
	 * @param sousSecteur sousSecteur à définir
	 */
	public void setSousSecteur(long sousSecteur) {
		this.sousSecteur = sousSecteur;
	}



	/**
	 * @return sousSecteurDescription
	 */
	public String getSousSecteurDescription() {
		return sousSecteurDescription;
	}



	/**
	 * @param sousSecteurDescription sousSecteurDescription à définir
	 */
	public void setSousSecteurDescription(String sousSecteurDescription) {
		this.sousSecteurDescription = sousSecteurDescription;
	}



	public String getGestionnaire() {
		return gestionnaire;
	}



	public void setGestionnaire(String gestionnaire) {
		this.gestionnaire = gestionnaire;
	}



	public String getGroupesIntervenants() {
		return groupesIntervenants;
	}



	public void setGroupesIntervenants(String groupesIntervenants) {
		this.groupesIntervenants = groupesIntervenants;
	}
	
}