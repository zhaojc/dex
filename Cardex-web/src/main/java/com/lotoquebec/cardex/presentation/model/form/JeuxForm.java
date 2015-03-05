package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.Predicate;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.JeuxHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.DoubleListe;
import com.lotoquebec.cardexCommun.presentation.util.ExclureLabelValuesPredicate;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.presentation.util.RetraitVidePredicate;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.ListeCacheUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les différentes valeurs relatives au formulatire de consultation des
 * jeux.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.JeuxHtmlForm
 */
public class JeuxForm extends ValidatorForm implements JeuxHtmlForm, Serializable {

	private static final long serialVersionUID = 4922270874799536272L;
	private String cle = "";
    private String site = "";
    private String entite = "";
    private String numeroDossier = "";
    private String type = "";
    private String categorie = "";
    private String lien = "";
    private String lienSite = "";
    private String typeJeu = "";
    private String typeJeuDescription = "";
    private DoubleListe doubleListe = new DoubleListe();
    private List<Predicate> listeGauchePredicates = new ArrayList<Predicate>();
    
    /**
     * Constructeur de JeuxForm par défaut.
     */
    public JeuxForm() {

    }


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie() {
        return this.categorie;
    }

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite() {
        return this.lienSite;
    }

  
    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur de la catégorie en caractère.
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init() {
        this.cle = "";
        this.site = "";
        this.entite = "";
        this.numeroDossier = "";
        this.categorie = "";
        this.type = "";
        this.typeJeu = "";
        this.lien = "";
        this.lienSite = "";
        this.doubleListe.vider();
    }

    public DoubleListe getDoubleListe() {
		return doubleListe;
	}

    /**
     * Détermine si la liste de jeux est vide.
     *
     * @return True si la liste de jeux est vide.
     */
    public boolean isEmpty() {
    	return doubleListe.getDroiteCols().isEmpty();
    }

	public void setDoubleListe(DoubleListe doubleListe) {
		this.doubleListe = doubleListe;
	}

	/**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du JeuxForm.
     *
     * @return String Valeur de tout les attributs du JeuxForm en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[JeuxForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', categorie = '" + categorie);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', jeuxChoisis = (");

        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

	public void initDoubleListe(CardexAuthenticationSubject subject, Collection<String> jeuxChoisis) throws BusinessResourceException{
    	doubleListe.vider();
    	initListeDroit(subject, jeuxChoisis);
    	initListeGauche(subject);
    	doubleListe.trier();
    }

    
    private void initListeDroit(CardexAuthenticationSubject subject, Collection<String> jeuxChoisis) throws BusinessResourceException{
    	List<LabelValue> labelValueList = ListeCache.getInstance().obtenirListe(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.JEUX, GlobalConstants.ActionSecurite.TOUTES_ACTIONS), StringUtils.toArray(jeuxChoisis));
    	//List<LabelValueBean> labelValueList = (List<LabelValueBean>) ListeCache.getInstance().obtenirListe(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.JEUX, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
    	
    	for (LabelValue labelValueBean:labelValueList){
    		
    		if (StringUtils.isEmpty(labelValueBean.getValue()))
    			continue;
    		
    		if (jeuxChoisis.contains(labelValueBean.getValue()))
    			doubleListe.addDroiteCol(labelValueBean);
    	}
    }    
    
    // La liste total ne contient pas la somme de la liste de droite plus la liste de gauche.  Il est possible que ça cause un problème
    public void initListeGauche(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache listeCache = ListeCache.getInstance();
    	doubleListe.viderGaucheCol();
    	
    	if (StringUtils.isEmpty(typeJeu))
    		return;
    	assignerExclusionListeGauche( ListeCacheUtils.obtenirListeValue( doubleListe.getDroiteCols() ) );

    	doubleListe.setGaucheCols( listeCache.obtenirListe(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.JEUX, new String[]{entite, typeJeu}, GlobalConstants.ActionSecurite.AJOUT), doubleListe.getDroiteColSTR(), listeGauchePredicates) );
    }   
	
    public void assignerExclusionListeGauche(List<Long> exclureListe){
    	listeGauchePredicates.clear();
    	listeGauchePredicates.add( new RetraitVidePredicate() );
    	listeGauchePredicates.add( new ExclureLabelValuesPredicate(exclureListe) );
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        doubleListe.viderDroiteCol();
    }

	public String getTypeJeu() {
		return typeJeu;
	}

	public void setTypeJeu(String typeJeu) {
		this.typeJeu = typeJeu;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public String getTypeJeuDescription() {
		return typeJeuDescription;
	}

	public void setTypeJeuDescription(String typeJeuDescription) {
		this.typeJeuDescription = typeJeuDescription;
	}
    
    
}