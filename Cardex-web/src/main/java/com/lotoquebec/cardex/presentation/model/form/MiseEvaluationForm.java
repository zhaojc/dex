package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.DoubleListe;
import com.lotoquebec.cardexCommun.model.SectionEscamotable;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Une MiseEvaluation est une observation d'un comportement client
 * par rapport à un ensemble de fréquence de visite.
 * @author levassc
 *
 */
public class MiseEvaluationForm implements SectionEscamotable{

    private String commentaireJeu = "";
    private String miseMinimum = "";
    private String miseMaximum = "";
    private String miseMoyenne = "";
    private String miseTotal = "";
    private String gainPerte = "";
    private String tempsJeuMinimum = "";
    private String tempsJeuMaximum = "";
    private String tempsJeuTotal = "";
    private boolean sectionOuverte = false;
    private JeuxForm jeuxForm = new JeuxForm();
	private List<FrequenceVisitesForm> frequencesVisites = new ArrayList<FrequenceVisitesForm>();
    
    // get / set
	public String getCommentaireJeu() {
		return commentaireJeu;
	}
	
	public void setCommentaireJeu(String commentaireJeu) {
		this.commentaireJeu = commentaireJeu;
	}
	
	public String getMiseMinimum() {
		return miseMinimum;
	}
	
	public void setMiseMinimum(String miseMinimum) {
		this.miseMinimum = miseMinimum;
	}
	
	public String getMiseMaximum() {
		return miseMaximum;
	}
	
	public void setMiseMaximum(String miseMaximum) {
		this.miseMaximum = miseMaximum;
	}
	
	public String getMiseMoyenne() {
		return miseMoyenne;
	}
	
	public void setMiseMoyenne(String miseMoyenne) {
		this.miseMoyenne = miseMoyenne;
	}
	
	public String getMiseTotal() {
		return miseTotal;
	}
	
	public void setMiseTotal(String miseTotal) {
		this.miseTotal = miseTotal;
	}
	
	public String getGainPerte() {
		return gainPerte;
	}
	
	public void setGainPerte(String gainPerte) {
		this.gainPerte = gainPerte;
	}
	
	public String getTempsJeuMinimum() {
		return tempsJeuMinimum;
	}
	
	public void setTempsJeuMinimum(String tempsJeuMinimum) {
		this.tempsJeuMinimum = tempsJeuMinimum;
	}
	
	public String getTempsJeuMaximum() {
		return tempsJeuMaximum;
	}
	
	public void setTempsJeuMaximum(String tempsJeuMaximum) {
		this.tempsJeuMaximum = tempsJeuMaximum;
	}
	
	public String getTempsJeuTotal() {
		return tempsJeuTotal;
	}
	
	public void setTempsJeuTotal(String tempsJeuTotal) {
		this.tempsJeuTotal = tempsJeuTotal;
	}

	public List<FrequenceVisitesForm> getFrequencesVisites() {
		return frequencesVisites;
	}

	public void setFrequencesVisites(List<FrequenceVisitesForm> frequencesVisites) {
		this.frequencesVisites = frequencesVisites;
	}

	public boolean isSectionOuverte() {
		return sectionOuverte;
	}

	public void setSectionOuverte(boolean sectionOuverte) {
		this.sectionOuverte = sectionOuverte;
	}

	public JeuxForm getJeuxForm() {
		return jeuxForm;
	}

	public void setJeuxForm(JeuxForm jeuxForm) {
		this.jeuxForm = jeuxForm;
	}
	
}
