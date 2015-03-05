package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.PSUMandatHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class PSUMandatForm extends ValidatorForm  implements PSUMandatHtmlForm, EntiteCardexForm, Serializable {

    private String cle = "";
    private String site = "";
    private String typeAction = "";
    private String numeroMandat = "";
    private String destinataireA = "";
	private String destinataireCC = "";
    private String destinataireCCI = "";
    private String message = "";
    private String statut = "";
    private String statutDescription = "";
    private String genreFichier = "";
    private String referenceCle = "";
    private String referenceSite = "";
    private String siteCible = "";
    private String entite = "";
    private String description = "";
    private String createur = "";
    private String genre = "";
    private String nature = "";
	private String dateDebut = "";
	private String dateFin = "";
    private String dateCreation = "";
    private String modificateur = "";
    private String dateModification = "";
    private String approbateur = "";
    private String dateApprobation = "";
    private String reference1 = "";
	private String type = "";
    private String categorie = "";
    private String fonde = "";
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
	private String total = "";
    private boolean modifiable = false;

    /**
     * Constructeur de SuiviForm par défaut.
     */
    public PSUMandatForm() {}


    // Getters

   
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
       this.cle = "";
       this.site = "";
       this.typeAction = "no";
       this.numeroMandat = "";
       this.createur = "";
	   this.destinataireA = "";
	   this.destinataireCC = "";
       this.dateCreation = "";
       this.dateModification = "";
       this.description = "";
       this.destinataireCCI = "";
       this.message = "";
       this.statut = "";
       this.genreFichier = "";
       this.referenceCle = "";
       this.referenceSite = "";
       this.siteCible = "";
       this.modificateur = "";
       this.approbateur = "";
       this.dateApprobation = "";
       this.reference1 = "";
       this.entite = "";
       this.genre = "";
       this.nature = "";
       this.type = "";
	   this.categorie = "";
	   this.fonde = "";
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
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @return
	 */
	public String getCle() {
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
	public String getDateApprobation() {
		return dateApprobation;
	}

	/**
	 * @return
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * @return
	 */
	public String getDateModification() {
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
	public String getEntite() {
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
	public String getFonde() {
		return fonde;
	}

	/**
	 * @return
	 */
	public String getGenre() {
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
	public String getNature() {
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
	public String getReferenceCle() {
		return referenceCle;
	}

	/**
	 * @return
	 */
	public String getReferenceSite() {
		return referenceSite;
	}

	/**
	 * @return
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @return
	 */
	public String getSiteCible() {
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
	public String getStatut() {
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
	public String getType() {
		return type;
	}

	/**
	 * @return
	 */
	public String getTypeAction() {
		return typeAction;
	}


	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public boolean isModifiable() {
		return modifiable;
	}

	/**
	 * Sets the isModifiable.
	 * @param isModifiable The isModifiable to set
	 */
	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
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
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * @param string
	 */
	public void setCle(String cle) {
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
	public void setDateApprobation(String dateApprobation) {
		this.dateApprobation = dateApprobation;
	}

	/**
	 * @param string
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @param string
	 */
	public void setDateModification(String dateModification) {
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
	public void setEntite(String entite) {
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
	public void setFonde(String fonde) {
		this.fonde = fonde;
	}

	/**
	 * @param string
	 */
	public void setGenre(String genre) {
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
	public void setNature(String nature) {
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
	public void setReferenceCle(String referenceCle) {
		this.referenceCle = referenceCle;
	}

	/**
	 * @param string
	 */
	public void setReferenceSite(String referenceSite) {
		this.referenceSite = referenceSite;
	}

	/**
	 * @param string
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @param string
	 */
	public void setSiteCible(String siteCible) {
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
	public void setStatut(String statut) {
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
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param string
	 */
	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	/**
	 * @return
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	public String getDateDebut10() {
    	if (StringUtils.isNotEmpty(this.dateDebut))
    		return this.dateDebut.substring(0, 10);
    	return "";
	}
	
	/**
	 * @return
	 */
	public String getDateFin() {
		return dateFin;
	}

	public String getDateFin10() {
    	if (StringUtils.isNotEmpty(this.dateFin))
    		return this.dateFin.substring(0, 10);
    	return "";
	}	
	
	/**
	 * @param string
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @param string
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param string
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	public String getStatutDescription() {
		return statutDescription;
	}
	
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	statutDescription = cache.obtenirLabel(subject, getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.DOSSIER));
	}	
}