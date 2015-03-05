package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.EvaluationHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.DoubleListe;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.util.ListeCache;
	
public class EvaluationForm extends ValidatorForm implements EvaluationHtmlForm, EntiteCardexForm, Serializable {

	private static final long serialVersionUID = 3876829305184080925L;
	private String cle = "";
    private String site = "";
    private String entite = "";
    private String siteDescription = "";
    private String lien = "";
    private String lienSite = "";
    private String dateDebutEval = "";
    private String dateFinEval = "";
    private String numeroClientBijou = "";
    private String typeJeu = "";
    private List<MiseEvaluationForm> misesEvaluation = new ArrayList<MiseEvaluationForm>();
    private String faitsConnus = "";
    private String createur = "";
    private String createurDescription = "";
    private String dateCreation = "";    
    private String proximite = "";
    private String gradation = "";
    private String transaction = "";
    private String commentairePropos = "";
    private String commentaireEtat = "";
    private String commentaireAutre = "";
    private String commentaireSigne = "";
    private String signataire1 = "";
    private String signataire2 = "";
    private String signataire3 = "";
    private String signataire4 = "";
    private String signataire5 = "";
    private String dateEvaluation = "";
    private String modificateur = "";
    private String dateModification = "";
    private boolean modifiable = false;
    private DoubleListe doubleListeEtat = new DoubleListe();
    private DoubleListe doubleListePropos = new DoubleListe();
    private String index = "";
    private String indexMise = ""; //Sert à connaître la ligne qu'il faut supprimer dans la section Mise
    private String indexFrequenceVisites = ""; //Sert à connaître la ligne qu'il faut supprimer dans la section Fréquence des visites

    
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     */
    public void init() {
    	this.cle = "";
    	this.site = "";
    	this.lien = "";
    	this.lienSite = "";
    	this.dateDebutEval = "";
    	this.dateFinEval = "";
    	this.numeroClientBijou = "";
    	this.faitsConnus = "";
    	this.createur = "";
    	this.dateCreation = "";    
    	this.proximite = "";
    	this.gradation = "";
    	this.transaction = "";
    	this.commentairePropos = "";
    	this.commentaireEtat = "";
    	this.commentaireAutre = "";
    	this.commentaireSigne = "";
    	this.signataire1 = "";
    	this.signataire2 = "";
    	this.signataire3 = "";
    	this.signataire4 = "";
    	this.signataire5 = "";
    	this.dateEvaluation = "";
    	this.doubleListeEtat.vider();
    	this.doubleListePropos.vider();
    	this.misesEvaluation = new ArrayList<MiseEvaluationForm>();
    	this.indexMise = "";
    	this.indexFrequenceVisites = "";
    }    
    
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        
        for (MiseEvaluationForm miseEvaluationForm:misesEvaluation)
        	miseEvaluationForm.getJeuxForm().getDoubleListe().viderDroiteCol();
        
        doubleListeEtat.viderDroiteCol();
        doubleListePropos.viderDroiteCol();
    }


/*	public SujetForm getSujetForm() {
		return sujetForm;
	}

	public void setSujetForm(SujetForm sujetForm) {
		this.sujetForm = sujetForm;
	}
*/

	/**
	 * @return cle
	 */
	public String getCle() {
		return cle;
	}


	/**
	 * @param cle cle à définir
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}


	/**
	 * @return site
	 */
	public String getSite() {
		return site;
	}


	/**
	 * @param site site à définir
	 */
	public void setSite(String site) {
		this.site = site;
	}


	/**
	 * @return lien
	 */
	public String getLien() {
		return lien;
	}


	/**
	 * @param lien lien à définir
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}


	/**
	 * @return lienSite
	 */
	public String getLienSite() {
		return lienSite;
	}


	/**
	 * @param lienSite lienSite à définir
	 */
	public void setLienSite(String lienSite) {
		this.lienSite = lienSite;
	}


	/**
	 * @return dateDebutEval
	 */
	public String getDateDebutEval() {
		return dateDebutEval;
	}


	/**
	 * @param dateDebutEval dateDebutEval à définir
	 */
	public void setDateDebutEval(String dateDebutEval) {
		this.dateDebutEval = dateDebutEval;
	}


	/**
	 * @return dateFinEval
	 */
	public String getDateFinEval() {
		return dateFinEval;
	}


	/**
	 * @param dateFinEval dateFinEval à définir
	 */
	public void setDateFinEval(String dateFinEval) {
		this.dateFinEval = dateFinEval;
	}


	/**
	 * @return numeroClientBijou
	 */
	public String getNumeroClientBijou() {
		return numeroClientBijou;
	}


	/**
	 * @param numeroClientBijou numeroClientBijou à définir
	 */
	public void setNumeroClientBijou(String numeroClientBijou) {
		this.numeroClientBijou = numeroClientBijou;
	}


	/**
	 * @return faitsConnus
	 */
	public String getFaitsConnus() {
		return faitsConnus;
	}


	/**
	 * @param faitsConnus faitsConnus à définir
	 */
	public void setFaitsConnus(String faitsConnus) {
		this.faitsConnus = faitsConnus;
	}


	/**
	 * @return createur
	 */
	public String getCreateur() {
		return createur;
	}


	/**
	 * @param createur createur à définir
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}


	/**
	 * @return dateCreation
	 */
	public String getDateCreation() {
		return dateCreation;
	}


	/**
	 * @param dateCreation dateCreation à définir
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}


	/**
	 * @return proximite
	 */
	public String getProximite() {
		return proximite;
	}


	/**
	 * @param proximite proximite à définir
	 */
	public void setProximite(String proximite) {
		this.proximite = proximite;
	}


	/**
	 * @return gradation
	 */
	public String getGradation() {
		return gradation;
	}


	/**
	 * @param gradation gradation à définir
	 */
	public void setGradation(String gradation) {
		this.gradation = gradation;
	}


	/**
	 * @return transaction
	 */
	public String getTransaction() {
		return transaction;
	}


	/**
	 * @param transaction transaction à définir
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}


	/**
	 * @return commentairePropos
	 */
	public String getCommentairePropos() {
		return commentairePropos;
	}


	/**
	 * @param commentairePropos commentairePropos à définir
	 */
	public void setCommentairePropos(String commentairePropos) {
		this.commentairePropos = commentairePropos;
	}


	/**
	 * @return commentaireEtat
	 */
	public String getCommentaireEtat() {
		return commentaireEtat;
	}


	/**
	 * @param commentaireEtat commentaireEtat à définir
	 */
	public void setCommentaireEtat(String commentaireEtat) {
		this.commentaireEtat = commentaireEtat;
	}


	/**
	 * @return commentaireAutre
	 */
	public String getCommentaireAutre() {
		return commentaireAutre;
	}


	/**
	 * @param commentaireAutre commentaireAutre à définir
	 */
	public void setCommentaireAutre(String commentaireAutre) {
		this.commentaireAutre = commentaireAutre;
	}


	/**
	 * @return commentaireSigne
	 */
	public String getCommentaireSigne() {
		return commentaireSigne;
	}


	/**
	 * @param commentaireSigne commentaireSigne à définir
	 */
	public void setCommentaireSigne(String commentaireSigne) {
		this.commentaireSigne = commentaireSigne;
	}


	/**
	 * @return signataire1
	 */
	public String getSignataire1() {
		return signataire1;
	}


	/**
	 * @param signataire1 signataire1 à définir
	 */
	public void setSignataire1(String signataire1) {
		this.signataire1 = signataire1;
	}


	/**
	 * @return signataire2
	 */
	public String getSignataire2() {
		return signataire2;
	}


	/**
	 * @param signataire2 signataire2 à définir
	 */
	public void setSignataire2(String signataire2) {
		this.signataire2 = signataire2;
	}


	/**
	 * @return signataire3
	 */
	public String getSignataire3() {
		return signataire3;
	}


	/**
	 * @param signataire3 signataire3 à définir
	 */
	public void setSignataire3(String signataire3) {
		this.signataire3 = signataire3;
	}


	/**
	 * @return signataire4
	 */
	public String getSignataire4() {
		return signataire4;
	}


	/**
	 * @param signataire4 signataire4 à définir
	 */
	public void setSignataire4(String signataire4) {
		this.signataire4 = signataire4;
	}


	/**
	 * @return signataire5
	 */
	public String getSignataire5() {
		return signataire5;
	}


	/**
	 * @param signataire5 signataire5 à définir
	 */
	public void setSignataire5(String signataire5) {
		this.signataire5 = signataire5;
	}


	/**
	 * @return dateEvaluation
	 */
	public String getDateEvaluation() {
		return dateEvaluation;
	}


	/**
	 * @param dateEvaluation dateEvaluation à définir
	 */
	public void setDateEvaluation(String dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}


	/**
	 * @return modificateur
	 */
	public String getModificateur() {
		return modificateur;
	}


	/**
	 * @param modificateur modificateur à définir
	 */
	public void setModificateur(String modificateur) {
		this.modificateur = modificateur;
	}


	/**
	 * @return dateModification
	 */
	public String getDateModification() {
		return dateModification;
	}


	/**
	 * @param dateModification dateModification à définir
	 */
	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
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

	/**
	 * @return createurDescription
	 */
	public String getCreateurDescription() {
		return createurDescription;
	}


	/**
	 * @param createurDescription createurDescription à définir
	 */
	public void setCreateurDescription(String createurDescription) {
		this.createurDescription = createurDescription;
	}

    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
		siteDescription = cache.obtenirLabel(subject, getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
		createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject.getLocale().getLanguage()));
    }

	/**
	 * @param jeux jeux à définir
	 */
    public DoubleListe getDoubleListeEtat() {
		return doubleListeEtat;
	}

    public DoubleListe getDoubleListePropos() {
		return doubleListePropos;
	}

    /**
     * Détermine si la liste de jeux est vide.
     *
     * @return True si la liste de jeux est vide.
     */
    public boolean isEmptyEtat() {
    	return doubleListeEtat.getDroiteCols().isEmpty();
    }

    public boolean isEmptyPropos() {
    	return doubleListePropos.getDroiteCols().isEmpty();
    }

    public void setDoubleListeEtat(DoubleListe doubleListeEtat) {
		this.doubleListeEtat = doubleListeEtat;
	}

    public void setDoubleListePropos(DoubleListe doubleListePropos) {
		this.doubleListePropos = doubleListePropos;
	}

    public void initDoubleListeEtat(CardexAuthenticationSubject subject, Collection<String> etatsChoisis) throws BusinessResourceException{
		List<LabelValue> labelValueList = ListeCache.getInstance().obtenirListe(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.ETAT, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
    	doubleListeEtat.assignerValeurListeChoisie(etatsChoisis, labelValueList);
    }
    
    public void initDoubleListePropos(CardexAuthenticationSubject subject, Collection<String> proposChoisis) throws BusinessResourceException{
		List<LabelValue> labelValueList = ListeCache.getInstance().obtenirListe(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.PROPOS, GlobalConstants.ActionSecurite.TOUTES_ACTIONS));
    	doubleListePropos.assignerValeurListeChoisie(proposChoisis, labelValueList);
    }

	public String getIndexMise() {
		return indexMise;
	}

	public void setIndexMise(String indexMise) {
		this.indexMise = indexMise;
	}

	public String getIndexFrequenceVisites() {
		return indexFrequenceVisites;
	}

	public void setIndexFrequenceVisites(String indexFrequenceVisites) {
		this.indexFrequenceVisites = indexFrequenceVisites;
	}

	public List<MiseEvaluationForm> getMisesEvaluation() {
		return misesEvaluation;
	}

	public void setMisesEvaluation(List<MiseEvaluationForm> misesEvaluation) {
		this.misesEvaluation = misesEvaluation;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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
	
	
}