package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.PSUMandat;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class PSUMandatVO extends ValidatorForm  implements PSUMandat {

    private long cle = 0;
    private long site = 0;
    private long typeAction = 0;
    private String numeroMandat = "";
    private String destinataireA = "";
	private String destinataireCC = "";
    private String destinataireCCI = "";
    private String message = "";
    private long statut = 0;
    private String genreFichier = "";
    private long referenceCle = 0;
    private long referenceSite = 0;
    private long siteCible = 0;
    private long entite = 0;
    private String description = "";
    private String createur = "";
    private long genre = 0;
    private long nature = 0;
	private Timestamp dateDebut = null;
	private Timestamp dateFin = null;
    private Timestamp dateCreation = null;
    private String modificateur = "";
    private Timestamp dateModification = null;
    private String approbateur = "";
    private Timestamp dateApprobation = null;
    private String reference1 = "";
	private long type = 0;
    private long categorie = 0;
    private long fonde = 0;
	private long total = 0;
    private String intervenant = "";
    private String numeroDossier = "";
    private String numeroCardex = "";
	private String ficheSujet = "";
	private String sujetNom = "";
	private String sujetPrenom = "";
	private String ficheSociete = "";
	private String societeNom = "";
	private String immatriculation = "";
	private String province = "";
	private String motCle1 = "";
	private String motCle2 = "";
	private String motCle3 = "";
    private Boolean modifiable = false;

    /**
     * Constructeur de SuiviForm par défaut.
     */
    public PSUMandatVO() {}


    // Getters

   
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
       this.cle = 0;
       this.site = 0;
       this.typeAction = 0;
       this.numeroMandat = "";
       this.createur = "";
	   this.destinataireA = "";
	   this.destinataireCC = "";
       this.dateCreation = null;
       this.dateModification = null;
       this.description = "";
       this.destinataireCCI = "";
       this.message = "";
       this.statut = 0;
       this.genreFichier = "";
       this.referenceCle = 0;
       this.referenceSite = 0;
       this.siteCible = 0;
       this.modificateur = "";
       this.approbateur = "";
       this.dateApprobation = null;
       this.reference1 = "";
       this.entite = 0;
       this.genre = 0;
       this.nature = 0;
       this.type = 0;
	   this.categorie = 0;
	   this.fonde = 0;
	   this.intervenant = "";
	   this.numeroCardex = "";
	   this.numeroDossier = "";
	   this.ficheSujet = "";
	   this.sujetNom = "";
	   this.sujetPrenom = "";
	   this.ficheSociete = "";
	   this.societeNom = "";
	   this.immatriculation = "";
	   this.province = "";
	   this.motCle1 = "";
	   this.motCle2 = "";
	   this.motCle3 = "";
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du SuiviForm.
     *
     * @return String Valeur de tout les attributs du ConsignationForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ConsignationForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', ficheSujet = '" + ficheSujet);
        stringBuffer.append("', numeroCardex = '" + numeroCardex);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', dateCreation = '"
                + dateCreation);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateModification = '"
                + dateModification);
        stringBuffer.append("', approbateur = '" + approbateur);
        stringBuffer.append("', dateApprobation = '"
                + dateApprobation);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', referenceCle = '" + referenceCle);
        stringBuffer.append("', referenceSite = '" + referenceSite );
        stringBuffer.append("', numeroMandat = '" + numeroMandat );
        stringBuffer.append("', typeAction = '" + typeAction );
        stringBuffer.append("']");
        return stringBuffer.toString();
    }


	/**
	 * @return
	 */
	public String getApprobateur() {
		return approbateur;
	}

	/**
	 * @return
	 */
	public long getCategorie() {
		return categorie;
	}

	/**
	 * @return
	 */
	public long getCle() {
		return cle;
	}

	/**
	 * @return
	 */
	public String getCreateur() {
		return createur;
	}

	/**
	 * @return
	 */
	public Timestamp getDateApprobation() {
		return dateApprobation;
	}

	/**
	 * @return
	 */
	public Timestamp getDateCreation() {
		return dateCreation;
	}

	/**
	 * @return
	 */
	public Timestamp getDateModification() {
		return dateModification;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getDestinataireA() {
		return destinataireA;
	}

	/**
	 * @return
	 */
	public String getDestinataireCC() {
		return destinataireCC;
	}

	/**
	 * @return
	 */
	public String getDestinataireCCI() {
		return destinataireCCI;
	}

	/**
	 * @return
	 */
	public long getEntite() {
		return entite;
	}

	/**
	 * @return
	 */
	public String getFicheSociete() {
		return ficheSociete;
	}

	/**
	 * @return
	 */
	public String getFicheSujet() {
		return ficheSujet;
	}

	/**
	 * @return
	 */
	public long getFonde() {
		return fonde;
	}

	/**
	 * @return
	 */
	public long getGenre() {
		return genre;
	}

	/**
	 * @return
	 */
	public String getGenreFichier() {
		return genreFichier;
	}

	/**
	 * @return
	 */
	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * @return
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return
	 */
	public String getModificateur() {
		return modificateur;
	}

	/**
	 * @return
	 */
	public String getMotCle1() {
		return motCle1;
	}

	/**
	 * @return
	 */
	public String getMotCle2() {
		return motCle2;
	}

	/**
	 * @return
	 */
	public String getMotCle3() {
		return motCle3;
	}

	/**
	 * @return
	 */
	public long getNature() {
		return nature;
	}

	/**
	 * @return
	 */
	public String getNumeroCardex() {
		return numeroCardex;
	}

	/**
	 * @return
	 */
	public String getNumeroDossier() {
		return numeroDossier;
	}

	/**
	 * @return
	 */
	public String getNumeroMandat() {
		return numeroMandat;
	}

	/**
	 * @return
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @return
	 */
	public String getReference1() {
		return reference1;
	}

	/**
	 * @return
	 */
	public long getReferenceCle() {
		return referenceCle;
	}

	/**
	 * @return
	 */
	public long getReferenceSite() {
		return referenceSite;
	}

	/**
	 * @return
	 */
	public long getSite() {
		return site;
	}

	/**
	 * @return
	 */
	public long getSiteCible() {
		return siteCible;
	}

	/**
	 * @return
	 */
	public String getSocieteNom() {
		return societeNom;
	}

	/**
	 * @return
	 */
	public long getStatut() {
		return statut;
	}

	/**
	 * @return
	 */
	public String getSujetNom() {
		return sujetNom;
	}

	/**
	 * @return
	 */
	public String getSujetPrenom() {
		return sujetPrenom;
	}

	/**
	 * @return
	 */
	public long getType() {
		return type;
	}

	/**
	 * @return
	 */
	public long getTypeAction() {
		return typeAction;
	}

	/**
	 * @param string
	 */
	public void setApprobateur(String approbateur) {
		this.approbateur = approbateur;
	}

	/**
	 * @param string
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}

	/**
	 * @param string
	 */
	public void setCle(long cle) {
		this.cle = cle;
	}

	/**
	 * @param string
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

	/**
	 * @param string
	 */
	public void setDateApprobation(Timestamp dateApprobation) {
		this.dateApprobation = dateApprobation;
	}

	/**
	 * @param string
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @param string
	 */
	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * @param string
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param string
	 */
	public void setDestinataireA(String destinataireA) {
		this.destinataireA = destinataireA;
	}

	/**
	 * @param string
	 */
	public void setDestinataireCC(String destinataireCC) {
		this.destinataireCC = destinataireCC;
	}

	/**
	 * @param string
	 */
	public void setDestinataireCCI(String destinataireCCI) {
		this.destinataireCCI = destinataireCCI;
	}

	/**
	 * @param string
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * @param string
	 */
	public void setFicheSociete(String ficheSociete) {
		this.ficheSociete = ficheSociete;
	}

	/**
	 * @param string
	 */
	public void setFicheSujet(String ficheSujet) {
		this.ficheSujet = ficheSujet;
	}

	/**
	 * @param string
	 */
	public void setFonde(long fonde) {
		this.fonde = fonde;
	}

	/**
	 * @param string
	 */
	public void setGenre(long genre) {
		this.genre = genre;
	}

	/**
	 * @param string
	 */
	public void setGenreFichier(String genreFichier) {
		this.genreFichier = genreFichier;
	}

	/**
	 * @param string
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @param string
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * @param string
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param string
	 */
	public void setModificateur(String modificateur) {
		this.modificateur = modificateur;
	}

	/**
	 * @param string
	 */
	public void setMotCle1(String motCle1) {
		this.motCle1 = motCle1;
	}

	/**
	 * @param string
	 */
	public void setMotCle2(String motCle2) {
		this.motCle2 = motCle2;
	}

	/**
	 * @param string
	 */
	public void setMotCle3(String motCle3) {
		this.motCle3 = motCle3;
	}

	/**
	 * @param string
	 */
	public void setNature(long nature) {
		this.nature = nature;
	}

	/**
	 * @param string
	 */
	public void setNumeroCardex(String numeroCardex) {
		this.numeroCardex = numeroCardex;
	}

	/**
	 * @param string
	 */
	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	/**
	 * @param string
	 */
	public void setNumeroMandat(String numeroMandat) {
		this.numeroMandat = numeroMandat;
	}

	/**
	 * @param string
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @param string
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * @param string
	 */
	public void setReferenceCle(long referenceCle) {
		this.referenceCle = referenceCle;
	}

	/**
	 * @param string
	 */
	public void setReferenceSite(long referenceSite) {
		this.referenceSite = referenceSite;
	}

	/**
	 * @param string
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * @param string
	 */
	public void setSiteCible(long siteCible) {
		this.siteCible = siteCible;
	}

	/**
	 * @param string
	 */
	public void setSocieteNom(String societeNom) {
		this.societeNom = societeNom;
	}

	/**
	 * @param string
	 */
	public void setStatut(long statut) {
		this.statut = statut;
	}

	/**
	 * @param string
	 */
	public void setSujetNom(String sujetNom) {
		this.sujetNom = sujetNom;
	}

	/**
	 * @param string
	 */
	public void setSujetPrenom(String sujetPrenom) {
		this.sujetPrenom = sujetPrenom;
	}

	/**
	 * @param string
	 */
	public void setType(long type) {
		this.type = type;
	}

	/**
	 * @param string
	 */
	public void setTypeAction(long typeAction) {
		this.typeAction = typeAction;
	}

	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public Boolean isModifiable() {
		return modifiable;
	}

	/**
	 * Sets the isModifiable.
	 * @param isModifiable The isModifiable to set
	 */
	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 * @return
	 */
	public Timestamp getDateDebut() {
		return dateDebut;
	}

	/**
	 * @return
	 */
	public Timestamp getDateFin() {
		return dateFin;
	}

	/**
	 * @param timestamp
	 */
	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @param timestamp
	 */
	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param l
	 */
	public void setTotal(long total) {
		this.total = total;
	}


	public Boolean getModifiable() {
		return modifiable;
	}

}