package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CaracteristiquesHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CaracteristiqueCleListeCache;
import com.lotoquebec.cardexCommun.model.DoubleListe;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de consultation des
 * caract�ristiques.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.CaracteristiquesHtmlForm
 */
public class CaracteristiquesForm extends ValidatorForm  implements CaracteristiquesHtmlForm, Serializable {

    private String cle = "";
    private String site = "";
    private String numeroFiche = "";
    private String nom = "";
    private String prenom = "";
    private String lien = "";
    private String lienSite = "";
    private DoubleListe doubleListe = new DoubleListe();

    /**
     * Constructeur de CaracteristiquesForm par d�faut.
     */
    public CaracteristiquesForm() {}


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
     * Retourne le pr�nom.
     *
     * @return String Valeur du pr�nom en caract�re.
     */
    public String getPrenom() {
        return this.prenom;
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
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite() {
        return this.lienSite;
    }

    /**
     * D�termine si la liste de caract�ristiques est vide.
     *
     * @return True si la liste de caract�ristiques est vide.
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
     * Affecte un pr�nom.
     *
     * @param prenom Valeur du pr�nom en caract�re.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
        this.numeroFiche = "";
        this.nom = "";
        this.prenom = "";
        this.lien = "";
        this.lienSite = "";
        this.doubleListe.vider();
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CaracteristiquesForm.
     *
     * @return String Valeur de tout les attributs du CaracteristiquesForm en
     * caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CaracteristiquesForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', numeroFiche = '" + numeroFiche);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', prenom = '" + prenom);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', caracteristiquesChoisis = (");

        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

	public DoubleListe getDoubleListe() {
		return doubleListe;
	}

	public void setDoubleListe(DoubleListe doubleListe) {
		this.doubleListe = doubleListe;
	}

	public void initDoubleListe(CardexAuthenticationSubject subject, Collection<String> caracteristiquesChoisis) throws BusinessResourceException{
		List<LabelValue> labelValueList = (List<LabelValue>) ListeCache.getInstance().obtenirListe(subject, new CaracteristiqueCleListeCache(subject));
    	doubleListe.assignerValeurListeChoisie(caracteristiquesChoisis, labelValueList);
    }
	
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        doubleListe.viderDroiteCol();
    }
    
}