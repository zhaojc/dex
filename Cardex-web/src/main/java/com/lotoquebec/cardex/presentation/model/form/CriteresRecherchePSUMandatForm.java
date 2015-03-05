package com.lotoquebec.cardex.presentation.model.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRecherchePSUMandatHtmlForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulatire de recherche de
 * consignation.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/29 15:39:24 $
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheConsignationHtmlForm
 */
public class CriteresRecherchePSUMandatForm extends ValidatorForm
        implements CriteresRecherchePSUMandatHtmlForm, RechercheListeResultat {

    private String entite = "";
    private String intervenant = "";
	private boolean approuve = false;
	private boolean nonApprouve = false;
    private String siteOrigine = "";
	private String numeroMandat = "";
    private String dateDebut = "";
    private String dateFin = "";
    private String typeAction = "";
    private String    ordreTriRecherche = "";
    private boolean   ordreCroissantRecherche = true;
    private String maximumResultatsRecherche = "";
    private ListeResultat listeResultat = new ListeResultat();
    private int sequence = 0;
    

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init() {
       this.entite = "";
       this.intervenant = "";
	   this.approuve = false;
	   this.nonApprouve = false;
       this.siteOrigine = "";
       this.dateDebut = "";
       this.dateFin = "";
       this.numeroMandat = "";
       this.typeAction = "";
       this.ordreTriRecherche = "";
       this.ordreCroissantRecherche = true;
       this.maximumResultatsRecherche = "";
       listeResultat.init();
       genererNumeroSequence();
    }
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {		  
		  this.approuve = false;
		  this.nonApprouve = false;
		  this.ordreCroissantRecherche = true;
	}

	// Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSuiviForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheSuiviForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheConsignationHtmlForm : ");
        stringBuffer.append("entite = '" + entite);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', type = '" + typeAction);
        stringBuffer.append("', numeroMandat = '" + numeroMandat);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
                + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
                + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the dateDebut.
	 * @return String
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * Returns the dateFin.
	 * @return String
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * Returns the entite.
	 * @return String
	 */
	public String getEntite() {
		return entite;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the maximumResultatsRecherche.
	 * @return String
	 */
	public String getMaximumResultatsRecherche() {
		return maximumResultatsRecherche;
	}

	/**
	 * Returns the numeroMandat.
	 * @return String
	 */
	public String getNumeroMandat() {
		return numeroMandat;
	}

	/**
	 * Returns the ordreTriRecherche.
	 * @return String
	 */
	public String getOrdreTriRecherche() {
		return ordreTriRecherche;
	}

	/**
	 * Returns the siteOrigine.
	 * @return String
	 */
	public String getSiteOrigine() {
		return siteOrigine;
	}

	/**
	 * Returns the type.
	 * @return String
	 */
	public String getTypeAction() {
		return typeAction;
	}

	/**
	 * Sets the dateDebut.
	 * @param dateDebut The dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Sets the dateFin.
	 * @param dateFin The dateFin to set
	 */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Sets the entite.
	 * @param entite The entite to set
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the maximumResultatsRecherche.
	 * @param maximumResultatsRecherche The maximumResultatsRecherche to set
	 */
	public void setMaximumResultatsRecherche(String maximumResultatsRecherche) {
		this.maximumResultatsRecherche = maximumResultatsRecherche;
	}

	/**
	 * Sets the ordreTriRecherche.
	 * @param ordreTriRecherche The ordreTriRecherche to set
	 */
	public void setOrdreTriRecherche(String ordreTriRecherche) {
		this.ordreTriRecherche = ordreTriRecherche;
	}

	/**
	 * Sets the siteOrigine.
	 * @param siteOrigine The siteOrigine to set
	 */
	public void setSiteOrigine(String siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	/**
	 * Sets the numeroMandat.
	 * @param numeroMandat The numeroMandat to set
	 */
	public void setNumeroMandat(String numeroMandat) {
		this.numeroMandat = numeroMandat;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

	/**
	 * @return
	 */
	public boolean isApprouve() {
		return this.approuve;
	}

	/**
	 * @return
	 */
	public boolean isNonApprouve() {
		return this.nonApprouve;
	}

	/**
	 * @param string
	 */
	public void setApprouve(boolean approuve) {
		this.approuve = approuve;
	}

	/**
	 * @param string
	 */
	public void setNonApprouve(boolean nonApprouve) {
		this.nonApprouve = nonApprouve;
	}

	public ListeResultat getListeResultat() {
		return listeResultat;
	}
	
	public void setListeResultat(List list) {
		this.listeResultat.setResultatComplet( list );
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
}