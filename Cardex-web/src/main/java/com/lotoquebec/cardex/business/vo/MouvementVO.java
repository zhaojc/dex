package com.lotoquebec.cardex.business.vo;

//import com.lotoquebec.cardex.text.TimestampFormat;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Contient les données sur les employés retournées par les requêtes.
 * 
 */
public class MouvementVO {

    private String cle = "";
    
    private String nom = "";
    private String prenom = "";
    private String statut = ""; //égale au typeEmploi
    private String siteConcerne = "";
    private String codeSite = ""; //Lettre qui représente le site
    private String typeMouvement = "";
    private String typeCodeIntervention = "";
    private Timestamp dateNaissance = null;
    private Timestamp dateDepart = null;
    private Timestamp dateEmbauche = null;
    private Timestamp dateConge = null;
    private Timestamp dateFinConge = null;
    private String numeroClientEmploye = "";
    private String descriptionConge = "";
    private String descriptionEmploi = "";
    private String descriptionSecteur = "";
    private String administrateur = "";
    private String descriptionDepart = "";

    /**
     * Constructeur de SujetVO par défaut.
     */
    public MouvementVO() {}

	/**
	 * Returns the cle.
	 * @return String
	 */
	public String getCle() {
		
		if (StringUtils.isEmpty(cle)){
			cle = ""+numeroClientEmploye;
			
			if (dateEmbauche != null) 
				cle = cle + "/" + dateEmbauche.toString().substring(0, 10);
			if (dateDepart != null)
				cle = cle + "/" + dateDepart.toString().substring(0, 10);
			if (dateConge != null)
				cle = cle + "/" + dateConge.toString().substring(0, 10);
			if (dateFinConge != null)
				cle = cle + "/" + dateFinConge.toString().substring(0, 10);
		}
		return cle;
	}

	/**
	 * Returns the dateConge.
	 * @return Timestamp
	 */
	public Timestamp getDateConge() {
		return dateConge;
	}

	/**
	 * Returns the dateDepart.
	 * @return Timestamp
	 */
	public Timestamp getDateDepart() {
		return dateDepart;
	}

	/**
	 * Returns the dateEmbauche.
	 * @return Timestamp
	 */
	public Timestamp getDateEmbauche() {
		return dateEmbauche;
	}

	/**
	 * Returns the dateFinConge.
	 * @return Timestamp
	 */
	public Timestamp getDateFinConge() {
		return dateFinConge;
	}

	/**
	 * Returns the dateNaissance.
	 * @return Timestamp
	 */
	public Timestamp getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Returns the descriptionConge.
	 * @return String
	 */
	public String getDescriptionConge() {
		return descriptionConge;
	}

	/**
	 * Returns the descriptionEmploi.
	 * @return String
	 */
	public String getDescriptionEmploi() {
		return descriptionEmploi;
	}

	/**
	 * Returns the nom.
	 * @return String
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Returns the numeroClientEmploye.
	 * @return String
	 */
	public String getNumeroClientEmploye() {
		return numeroClientEmploye;
	}

	/**
	 * Returns the prenom.
	 * @return String
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Returns the site.
	 * @return String
	 */
	public String getCodeSite() {
		return codeSite;
	}

	/**
	 * Returns the siteConcerne.
	 * @return String
	 */
	public String getSiteConcerne() {
		return siteConcerne;
	}

	/**
	 * Returns the statut.
	 * @return String
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * Returns the typeMouvement.
	 * @return String
	 */
	public String getTypeMouvement() {
		
		if (StringUtils.isEmpty(typeMouvement)){

			  //Vérification du type de mouvement
			  //Si Congé n'est pas null et que Départ est null, il s'agit d'un congé
			  //sinon c'est un départ, car un départ a toujours lieu après un congé.
			if(dateConge != null && dateDepart == null)
				setTypeMouvement(GlobalConstants.Mouvements.Conge);
			if(dateConge != null && dateDepart != null)
				setTypeMouvement(GlobalConstants.Mouvements.Depart);
			if(dateConge == null && dateDepart != null)
				setTypeMouvement(GlobalConstants.Mouvements.Depart);
			if(dateConge == null && dateDepart == null)
				setTypeMouvement(GlobalConstants.Mouvements.Embauche);
		}
		
		return typeMouvement;
	}

	
	public void setTypeCodeIntervention(String typeCodeIntervention) {
		this.typeCodeIntervention = typeCodeIntervention;
	}

	public String getTypeCodeIntervention() {
		if (StringUtils.isEmpty(typeCodeIntervention)){

			if(dateConge != null && dateDepart == null)
				setTypeCodeIntervention(GlobalConstants.TypesIntervention.Conge);
			if(dateConge != null && dateDepart != null)
				setTypeCodeIntervention(GlobalConstants.TypesIntervention.Depart);
			if(dateConge == null && dateDepart != null)
				setTypeCodeIntervention(GlobalConstants.TypesIntervention.Depart);
			if(dateConge == null && dateDepart == null)
				setTypeCodeIntervention(GlobalConstants.TypesIntervention.Embauche);
		}
		
		return typeCodeIntervention;
	}

	/**
	 * Sets the cle.
	 * @param cle The cle to set
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}

	/**
	 * Sets the dateConge.
	 * @param dateConge The dateConge to set
	 */
	public void setDateConge(Timestamp dateConge) {
		this.dateConge = dateConge;
	}

	/**
	 * Sets the dateDepart.
	 * @param dateDepart The dateDepart to set
	 */
	public void setDateDepart(Timestamp dateDepart) {
		this.dateDepart = dateDepart;
	}

	/**
	 * Sets the dateEmbauche.
	 * @param dateEmbauche The dateEmbauche to set
	 */
	public void setDateEmbauche(Timestamp dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	/**
	 * Sets the dateFinConge.
	 * @param dateFinConge The dateFinConge to set
	 */
	public void setDateFinConge(Timestamp dateFinConge) {
		this.dateFinConge = dateFinConge;
	}

	/**
	 * Sets the dateNaissance.
	 * @param dateNaissance The dateNaissance to set
	 */
	public void setDateNaissance(Timestamp dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Sets the descriptionConge.
	 * @param descriptionConge The descriptionConge to set
	 */
	public void setDescriptionConge(String descriptionConge) {
		this.descriptionConge = descriptionConge;
	}

	/**
	 * Sets the descriptionEmploi.
	 * @param descriptionEmploi The descriptionEmploi to set
	 */
	public void setDescriptionEmploi(String descriptionEmploi) {
		this.descriptionEmploi = descriptionEmploi;
	}

	/**
	 * Sets the nom.
	 * @param nom The nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Sets the numeroClientEmploye.
	 * @param numeroClientEmploye The numeroClientEmploye to set
	 */
	public void setNumeroClientEmploye(String numeroClientEmploye) {
		this.numeroClientEmploye = numeroClientEmploye;
	}

	/**
	 * Sets the prenom.
	 * @param prenom The prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setCodeSite(String site) {
		this.codeSite = site;
	}

	/**
	 * Sets the siteConcerne.
	 * @param siteConcerne The siteConcerne to set
	 */
	public void setSiteConcerne(String siteConcerne) {
		this.siteConcerne = siteConcerne;
	}

	/**
	 * Sets the statut.
	 * @param statut The statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * Sets the typeMouvement.
	 * @param typeMouvement The typeMouvement to set
	 */
	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	/**
	 * Returns the administrateur.
	 * @return String
	 */
	public String getAdministrateur() {
		return administrateur;
	}

	/**
	 * Returns the descriptionSecteur.
	 * @return String
	 */
	public String getDescriptionSecteur() {
		return descriptionSecteur;
	}

	/**
	 * Sets the administrateur.
	 * @param administrateur The administrateur to set
	 */
	public void setAdministrateur(String administrateur) {
		this.administrateur = administrateur;
	}

	/**
	 * Sets the descriptionSecteur.
	 * @param descriptionSecteur The descriptionSecteur to set
	 */
	public void setDescriptionSecteur(String descriptionSecteur) {
		this.descriptionSecteur = descriptionSecteur;
	}

	/**
	 * Returns the descriptionDepart.
	 * @return String
	 */
	public String getDescriptionDepart() {
		return descriptionDepart;
	}

	/**
	 * Sets the descriptionDepart.
	 * @param descriptionDepart The descriptionDepart to set
	 */
	public void setDescriptionDepart(String descriptionDepart) {
		this.descriptionDepart = descriptionDepart;
	}

	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("cle: "+getCle()+"\r");
		stringBuilder.append("nom: "+nom+"\r");
		stringBuilder.append("prenom: "+prenom+"\r");
		stringBuilder.append("statut: "+statut+"\r");
		stringBuilder.append("siteConcerne: "+siteConcerne+"\r");
		stringBuilder.append("codeSite: "+codeSite+"\r");
		//stringBuilder.append("dateNaissance: "+dateNaissance+"\r");
		stringBuilder.append("dateDepart: "+dateDepart+"\r");
		stringBuilder.append("dateEmbauche: "+dateEmbauche+"\r");
		stringBuilder.append("dateConge: "+dateConge+"\r");
		stringBuilder.append("dateFinConge: "+dateFinConge);
		stringBuilder.append("numeroClientEmploye: "+numeroClientEmploye+"\r");
		stringBuilder.append("descriptionConge: "+descriptionConge+"\r");
		stringBuilder.append("descriptionEmploi: "+descriptionEmploi+"\r");
		stringBuilder.append("descriptionSecteur: "+descriptionSecteur+"\r");
		stringBuilder.append("administrateur: "+administrateur+"\r");
		stringBuilder.append("descriptionDepart: "+descriptionDepart);
		
		return stringBuilder.toString();
	}
}
