package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.vo.CriteresRechercheSuiviVO;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheSuiviHtmlForm;
import com.lotoquebec.cardex.presentation.model.SuiviHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de recherche de
 * suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/29 15:39:24 $
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheSuiviHtmlForm
 */
public class CriteresRechercheSuiviForm extends ValidatorForm
        implements CriteresRechercheSuiviHtmlForm, RechercheListeResultat {

    private String activite = "";
    private String secteurAssigne = "";
    private String intervenant = "";
    private String secteurOrigine = "";
    private String demandeur = "";
    private String dateEmisDebut = "";
    private String datePrevueDebut = "";
    private String dateCompleteeDebut = "";
    private String dateEmisFin = "";
    private String datePrevueFin = "";
    private String dateCompleteeFin = "";
    private String lien = "";
    private String lienSite = "";
    private ArrayList suivis = new ArrayList();
    private String statutSuivi = "";
    private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
    private String choixRapport = "";
    private String entite = "";
    private String siteOrigine = "";
    private int sequence = 0;

    /**
     * Retourne le statut suivi.
     *
     * @return String Valeur du statut suivi.
     */
    public String getStatutSuivi() {
        return this.statutSuivi;
    }

    /**
     * Affecte un statut suivi.
     *
     * @param statutSuivi Valeur du statut suivi.
     */
    public void setStatutSuivi(String statutSuivi) {
        this.statutSuivi = statutSuivi;
    }

    private String statutApprobation = "";

    /**
     * Retourne le statut approbation.
     *
     * @return String Valeur du statut approbation.
     */
    public String getStatutApprobation() {
        return this.statutApprobation;
    }

    /**
     * Affecte un statut approbation.
     *
     * @param statutApprobation Valeur du statut approbation.
     */
    public void setStatutApprobation(String statutApprobation) {
        this.statutApprobation = statutApprobation;
    }

    /**
     * Constructeur de CriteresRechercheSuiviForm par d�faut.
     */
    public CriteresRechercheSuiviForm() {}


    // Getters


    /**
     * Retourne le secteur assign�.
     *
     * @return String Valeur du secteur assign� en caract�re.
     */
    public String getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caract�re.
     */
    public String getSecteurOrigine() {
        return this.secteurOrigine;
    }

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur() {
        return this.demandeur;
    }

    /**
     * Retourne la date d'�mission au d�but.
     *
     * @return String Valeur de la date d'�mission au d�but en caract�re.
     */
    public String getDateEmisDebut() {
        return this.dateEmisDebut;
    }

    /**
     * Retourne la date pr�vue au d�but.
     *
     * @return String Valeur de la date pr�vue au d�but en caract�re.
     */
    public String getDatePrevueDebut() {
        return this.datePrevueDebut;
    }

    /**
     * Retourne la date compl�t�e au d�but.
     *
     * @return String Valeur de la date compl�t�e au d�but en caract�re.
     */
    public String getDateCompleteeDebut() {
        return this.dateCompleteeDebut;
    }

    /**
     * Retourne la date d'�mission � la fin.
     *
     * @return String Valeur de la date d'�mission � la fin en caract�re.
     */
    public String getDateEmisFin() {
        return this.dateEmisFin;
    }

    /**
     * Retourne la date pr�vue � la fin.
     *
     * @return String Valeur de la date pr�vue � la fin en caract�re.
     */
    public String getDatePrevueFin() {
        return this.datePrevueFin;
    }

    /**
     * Retourne la date compl�t�e � la fin.
     *
     * @return String Valeur de la date compl�t�e � la fin en caract�re).
     */
    public String getDateCompleteeFin() {
        return this.dateCompleteeFin;
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
     * Retourne une collection de suivis.
     *
     * @return Collection Valeur de la collection de suivis.
     */
    public Collection getSuivis() {
        return this.suivis;
    }


    // Setters


    /**
     * Affecte un secteur assign�.
     *
     * @param secteurAssigne Valeur du secteur assign� en caract�re.
     */
    public void setSecteurAssigne(String secteurAssigne) {
        this.secteurAssigne = secteurAssigne;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caract�re.
     */
    public void setSecteurOrigine(String secteurOrigine) {
        this.secteurOrigine = secteurOrigine;
    }

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caract�re.
     */
    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    /**
     * Affecte une date d'�mission au d�but.
     *
     * @param dateEmisDebut Valeur de la date d'�mission au d�but en caract�re.
     */
    public void setDateEmisDebut(String dateEmisDebut) {
        this.dateEmisDebut = dateEmisDebut;
    }

    /**
     * Affecte une date pr�vue au d�but.
     *
     * @param datePrevueDebut Valeur de la date pr�vue au d�but en caract�re.
     */
    public void setDatePrevueDebut(String datePrevueDebut) {
        this.datePrevueDebut = datePrevueDebut;
    }

    /**
     * Affecte une date compl�t�e au d�but.
     *
     * @param dateCompleteeDebut Valeur de la date compl�t�e au d�but en
     * caract�re.
     */
    public void setDateCompleteeDebut(String dateCompleteeDebut) {
        this.dateCompleteeDebut = dateCompleteeDebut;
    }

    /**
     * Affecte une date d'�mission � la fin.
     *
     * @param dateEmisFin Valeur de la date d'�mission � la fin en caract�re.
     */
    public void setDateEmisFin(String dateEmisFin) {
        this.dateEmisFin = dateEmisFin;
    }

    /**
     * Affecte une date pr�vue � la fin.
     *
     * @param datePrevueFin Valeur de la date pr�vue � la fin en caract�re.
     */
    public void setDatePrevueFin(String datePrevueFin) {
        this.datePrevueFin = datePrevueFin;
    }

    /**
     * Affecte une date compl�t�e � la fin.
     *
     * @param dateCompleteeFin Valeur de la date compl�t�e � la fin en
     * caract�re.
     */
    public void setDateCompleteeFin(String dateCompleteeFin) {
        this.dateCompleteeFin = dateCompleteeFin;
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
     * Ajoute un suivi.
     *
     * @param suivi Valeur du suivi � ajouter.
     */
    public void addSuivi(SuiviHtmlForm suivi) {
        this.suivis.add(suivi);
    }

    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     * @throws BusinessResourceException 
     */
    public void init(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	CardexUser user = (CardexUser)subject.getUser();
       this.secteurAssigne = "";
       this.intervenant = "";
       this.secteurOrigine = "";
       this.demandeur = "";
       this.activite = "";
       this.dateEmisDebut = "";
       this.datePrevueDebut = "";
       this.dateCompleteeDebut = "";
       this.dateEmisFin = "";
       this.datePrevueFin = "";
       this.dateCompleteeFin = "";
       this.lien = "";
       this.lienSite = "";
       this.suivis = new ArrayList();
       this.statutSuivi = "";
       this.statutApprobation = "";
       listeResultat.init();
       listeResultatAudit.init();
       setSecteurAssigne(user.getSecteur()+"");
       genererNumeroSequence();
       
       if (GestionnaireSecuriteCardex.validerAccesSecurite(subject, new CriteresRechercheSuiviVO(), "entite", user.getEntite()+"", GlobalConstants.ActionSecurite.RECHERCHE)){
	       setEntite(String.valueOf(user.getEntite()));
	       setSiteOrigine(String.valueOf(user.getSite()));
       }
    }
    
    // Apr�s une requ�te il faut g�n�rer un nouveau num�ro de s�quence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheSuiviForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheSuiviForm en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheSuiviForm : ");
        stringBuffer.append("secteurAssigne = '" + secteurAssigne);
        stringBuffer.append("', activite = '" + activite);
        stringBuffer.append("', statutSuivi = '" + statutSuivi);
        stringBuffer.append("', statutApprobation = '" + statutApprobation);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', secteurOrigine = '" + secteurOrigine);
        stringBuffer.append("', demandeur = '" + demandeur);
        stringBuffer.append("', dateEmisDebut = '" + dateEmisDebut);
        stringBuffer.append("', datePrevueDebut = '" + datePrevueDebut);
        stringBuffer.append("', dateCompleteeDebut = '" + dateCompleteeDebut);
        stringBuffer.append("', dateEmisFin = '" + dateEmisFin);
        stringBuffer.append("', datePrevueFin = '" + datePrevueFin);
        stringBuffer.append("', dateCompleteeFin = '" + dateCompleteeFin);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.model.form.RechercheListeResultat#getListeResultat()
	 */
	public ListeResultat getListeResultat() {
		return listeResultat;
	}

	public void setListeResultat(List list) {
		this.listeResultat.setResultatComplet( list );
	}

	public ListeResultat getListeResultatAudit() {
		return listeResultatAudit;
	}
	
	public void setListeResultatAudit(List list) {
		this.listeResultatAudit.setResultatCompletAudit(list);
	}
	
	/**
	 * @return choixRapport
	 */
	public String getChoixRapport() {
		return choixRapport;
	}

	/**
	 * @param choixRapport choixRapport � d�finir
	 */
	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}

	/**
	 * @return entite
	 */
	public String getEntite() {
		return entite;
	}

	/**
	 * @param entite entite � d�finir
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}

	/**
	 * @return siteOrigine
	 */
	public String getSiteOrigine() {
		return siteOrigine;
	}

	/**
	 * @param siteOrigine siteOrigine � d�finir
	 */
	public void setSiteOrigine(String siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return activite
	 */
	public String getActivite() {
		return activite;
	}

	/**
	 * @param activite activite � d�finir
	 */
	public void setActivite(String activite) {
		this.activite = activite;
	}    
	
	
}