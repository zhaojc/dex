package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.ParticularitesHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ParticularitesCleListeCache;
import com.lotoquebec.cardexCommun.model.DoubleListe;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de consultation des
 * particularit�s.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.ParticularitesHtmlForm
 */
public class ParticularitesForm extends ValidatorForm implements ParticularitesHtmlForm, Serializable {

    private String cle = "";
    private String site = "";
    private String numeroDossier = "";
    private String siteDossier = "";
    private String marque = "";
    private String modele = "";
    private String immatriculation = "";
    private String lien = "";
    private String lienSite = "";
    private DoubleListe doubleListe = new DoubleListe();

    /**
     * Constructeur de ParticularitesForm par d�faut.
     */
    public ParticularitesForm() {}


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite() {
        return this.site;
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
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caract�re.
     */
    public String getSiteDossier() {
        return this.siteDossier;
    }

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque() {
        return this.marque;
    }

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caract�re.
     */
    public String getModele() {
        return this.modele;
    }

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien() {
        return this.lien;
    }

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite() {
        return this.lienSite;
    }

    /**
     * D�termine si la liste des particularit�s est vide.
     *
     * @return True si la liste des particularit�s est vide.
     */
    public boolean isEmpty() {
    	return doubleListe.getDroiteCols().isEmpty();
    }


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site) {
        this.site = site;
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
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caract�re.
     */
    public void setSiteDossier(String siteDossier) {
        this.siteDossier = siteDossier;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caract�re.
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init() {
        this.cle = "";
        this.site = "";
        this.immatriculation = "";
        this.modele = "";
        this.marque = "";
        this.lien = "";
        this.lienSite = "";
        this.doubleListe.vider();
    }

    public DoubleListe getDoubleListe() {
		return doubleListe;
	}

	public void setDoubleListe(DoubleListe doubleListe) {
		this.doubleListe = doubleListe;
	}

	/**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du ParticularitesForm.
     *
     * @return String Valeur de tout les attributs du ParticularitesForm en
     * caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ParticularitesForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', siteDossier = '" + siteDossier);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', particularitesChoisis = (");

        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

    public void initDoubleListe(CardexAuthenticationSubject subject, Collection<String> particularitesChoisis) throws BusinessResourceException{
		List<LabelValue> labelValueList = ListeCache.getInstance().obtenirListe(subject, new ParticularitesCleListeCache(subject));
    	doubleListe.assignerValeurListeChoisie(particularitesChoisis, labelValueList);
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        doubleListe.viderDroiteCol();
    }
    
    
}