package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.InscriptionHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PeriodeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteApplicableTableValeurCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteOrigineTableValeurCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.MapUtils;

/**
 * Conserve les différentes valeurs relatives au formulatire de consultation
 * d'une inscription.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.9 $, $Date: 2002/04/19 17:56:37 $
 * @see com.lotoquebec.cardex.presentation.model.InscriptionHtmlForm
 */
public class InscriptionForm extends ValidatorForm implements InscriptionHtmlForm, Serializable {

    private String cle = "";
    private String site = "";
    private String entite = "";
    private String dateInscription = "";
    private String duree = "";
    private String createur = "";
    private String createurDescription = "";
    private String dateDebut = "";
    private String dateFin = "";
    private String periode = "";
    private String periodeDescription = "";
    private String statut = "";
    private String statutDescription = "";
    private String lien = "";
    private String lienSite = "";
    private String [] sitesChoisis = new String [0];
    private List sitesChoisisDescription = new ArrayList();
    private String aideInitiale = "";
    private String aideImmediate = "";// Rencontre immédiate directement au casino
    private String dateCourrielAide = "";
    private String dateCourrielSuivi = "";
    private String dateRencontreInitiale = "";
    private String dateRencontreFinale = "";
    private String intervenantRencontreInitiale = "";
    private String intervenantRencontreFinale = "";
    private boolean tousCasinoEtLudoplex = false;
    private String stringSitesChoisis = "";
    private boolean tousSitesApplicables = false;
    private Collection siteApplicable = new ArrayList();
    private Collection siteEspaceJeux = new ArrayList();
    
    /**
     * Constructeur de InscriptionForm par défaut.
     */
    public InscriptionForm() {}


    // Getters

    /**
     * Retourne l'entite.
     *
     * @return String Valeur de l'entite.
     */
    public String getEntite() {
        return this.entite;
    }

    /**
     * Retourne la createur.
     *
     * @return String Valeur du createur.
     */
    public String getCreateur() {
        return this.createur;
    }

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
     * Retourne la date d'inscription.
     *
     * @return String Valeur de la date d'inscription en caractère.
     */
    public String getDateInscription() {
        return this.dateInscription;
    }

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree() {
        return this.duree;
    }

    /**
     * Retourne la date de début.
     *
     * @return String Valeur de la date de début en caractère.
     */
    public String getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caractère.
     */
    public String getDateFin() {
        return this.dateFin;
    }

    /**
     * Retourne la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getPeriode() {
        return this.periode;
    }

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut() {
        return this.statut;
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

    /**
     * Retourne la collection de sites choisis.
     *
     * @return String [] Valeur de la liste de chaîne de caractère de sites
     * choisis.
     */
    public String [] getSitesChoisis() {
        return this.sitesChoisis;
    }


    // Setters

    /**
     * Affecte une entite.
     *
     * @param createur Valeur de l'entite.
     */
    public void setEntite(String entite) {
        this.entite = entite;
    }

    /**
     * Affecte un createur.
     *
     * @param createur Valeur du createur.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

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
     * Affecte une date d'inscription.
     *
     * @param dateInscription Valeur de la date d'inscription en caractère.
     */
    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    /**
     * Affecte une durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree) {
        this.duree = duree;
    }

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de la date de début en caractère.
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin en caractère.
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setPeriode(String periode) {
        this.periode = periode;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut) {
        this.statut = statut;
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

    /**
     * Affecte une collection de sites choisis.
     *
     * @param sitesChoisis Valeur de la liste de chaîne de caractère de sites
     * choisis.
     */
    public void setSitesChoisis(String [] sitesChoisis) {
        this.sitesChoisis = sitesChoisis;
    }

    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        ActionErrors errors = super.validate(mapping,request);
        if (this.sitesChoisis == null || this.sitesChoisis.length < 1) {
          ActionMessage error = new ActionMessage("cardex_required_site");
          errors.add(ActionMessages.GLOBAL_MESSAGE,error);
        }
        return errors;
    }


    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du InscriptionForm.
     *
     * @return String Valeur de tout les attributs du InscriptionForm en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[InscriptionForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', dateInscription = '" + dateInscription);
        stringBuffer.append("', duree = '" + duree);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', aideInitiale = '" + aideInitiale);
        stringBuffer.append("', aideImmediate = '" + aideImmediate);
        stringBuffer.append("', sitesChoisis = (");
        if ( sitesChoisis != null ) {
            for ( int i = 0; i < sitesChoisis.length; i++ ) {
                stringBuffer.append("'" + sitesChoisis[i] + "',");
            }
        }
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

    
    public void init(CardexAuthenticationSubject subject) throws BusinessResourceException, ValueObjectMapperException{
    	ListeCache listeCache = ListeCache.getInstance();
    	CardexUser user = (CardexUser)subject.getUser();
        setStatut(String.valueOf(GlobalConstants.Statut.INSCRIPTION_ACTIF));
        setEntite(Long.toString(user.getEntite()));
        setDateInscription(TimestampFormat.format(new Timestamp(System.currentTimeMillis()), subject.getLocale(), false));
        setAideInitiale("");
        setAideImmediate("");
        setTousSitesApplicables(false);

        siteApplicable = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, entite, GlobalConstants.ActionSecurite.MODIFICATION));
        siteEspaceJeux = listeCache.obtenirListe(subject, new SiteOrigineTableValeurCle(subject, entite, GlobalConstants.ActionSecurite.MODIFICATION));;
    }
    
    public Collection getSiteApplicable() {
		return siteApplicable;
	}

    public Collection getSiteEspaceJeux() {
		return siteEspaceJeux;
	}

	public boolean isPeriodeASuivre(){
	    	return GlobalConstants.Periode.A_SUIVRE_CONSEILLER.equals( getPeriode() );
    }

    public boolean isDemandeAideGeneral(){
    	return isPeriodeASuivre() || isBoolAideInitiale();
    }

	public String getAideImmediate() {
		return aideImmediate;
	}
	public void setAideImmediate(String aideImmediate) {
		this.aideImmediate = aideImmediate;
	}
	public String getAideInitiale() {
		return aideInitiale;
	}
	public void setAideInitiale(String aideInitiale) {
		this.aideInitiale = aideInitiale;
	}

	public boolean isBoolAideInitiale() {
		return GlobalConstants.BooleanString.TRUE.equals( getAideInitiale() );
	}
	public boolean isTousCasinoEtLudoplex() {
		return tousCasinoEtLudoplex;
	}
	public void setTousCasinoEtLudoplex(boolean tousCasinoEtLudoplex) {
		this.tousCasinoEtLudoplex = tousCasinoEtLudoplex;
	}
	public void assignerListeSitesChoisis(CardexAuthenticationSubject subject) throws BusinessResourceException, ValueObjectMapperException{
		stringSitesChoisis = "";
		ListeCache listeCache = ListeCache.getInstance();
		Collection listeSiteApplicable = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, GlobalConstants.Entite.MAISON_JEUX, GlobalConstants.ActionSecurite.MODIFICATION));
		int nbSiteApplicableMax = listeSiteApplicable.size();
		//On soustrait l'entrée vide qui est ajoutée par défaut dans la liste
		if (sitesChoisis.length >= (nbSiteApplicableMax -1))
			setTousCasinoEtLudoplex(true);
		else{
			Iterator iter = listeSiteApplicable.iterator();
			Map mapSitesChoisis = MapUtils.getMap(sitesChoisis);

			while (iter.hasNext()) {
				LabelValueBean label = (LabelValueBean) iter.next();

				if (mapSitesChoisis.containsKey( label.getValue() )){

					if (stringSitesChoisis.length() > 0)
						stringSitesChoisis += ", ";
					stringSitesChoisis += label.getLabel();
				}
			}
		}

		listeSiteApplicable = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, GlobalConstants.Entite.LOTO_QUEBEC, GlobalConstants.ActionSecurite.MODIFICATION));
		nbSiteApplicableMax = listeSiteApplicable.size();
		Iterator iter = listeSiteApplicable.iterator();

		while (iter.hasNext()) {
			LabelValueBean label2 = (LabelValueBean) iter.next();
			Map mapSitesChoisis2 = MapUtils.getMap(sitesChoisis);
			
			if (mapSitesChoisis2.containsKey( label2.getValue() )){

				if (stringSitesChoisis.length() > 0)
					stringSitesChoisis += ", ";
				stringSitesChoisis += label2.getLabel();
			}
		}
	}

	public String getStringSitesChoisis() {
		return stringSitesChoisis;
	}
	public void setStringSitesChoisis(String stringSitesChoisis) {
		this.stringSitesChoisis = stringSitesChoisis;
	}
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();

    	createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject));
    	periodeDescription = cache.obtenirLabel(subject, getPeriode(), new PeriodeCleListeCache(subject));
    	statutDescription = cache.obtenirLabel(subject, getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.DOSSIER));
    	sitesChoisisDescription = new ArrayList();

    	for (int i = 0; i < sitesChoisis.length; i++) {
			String strSite = sitesChoisis[i];
			sitesChoisisDescription.add( cache.obtenirLabel(subject, strSite, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION)) );
		}
	}
	public String getCreateurDescription() {
		return createurDescription;
	}
	public String getPeriodeDescription() {
		return periodeDescription;
	}
	public List getSitesChoisisDescription() {
		return sitesChoisisDescription;
	}
	public String getStatutDescription() {
		return statutDescription;
	}
	/**
	 * @return Returns the dateCourrielAide.
	 */
	public String getDateCourrielAide() {
		return dateCourrielAide;
	}
	/**
	 * @param dateCourrielAide The dateCourrielAide to set.
	 */
	public void setDateCourrielAide(String dateCourrielAide) {
		this.dateCourrielAide = dateCourrielAide;
	}
	/**
	 * @return Returns the dateCourrielSuivi.
	 */
	public String getDateCourrielSuivi() {
		return dateCourrielSuivi;
	}
	/**
	 * @param dateCourrielSuivi The dateCourrielSuivi to set.
	 */
	public void setDateCourrielSuivi(String dateCourrielSuivi) {
		this.dateCourrielSuivi = dateCourrielSuivi;
	}
	/**
	 * @return Returns the dateRencontreFinale.
	 */
	public String getDateRencontreFinale() {
		return dateRencontreFinale;
	}
	/**
	 * @param dateRencontreFinale The dateRencontreFinale to set.
	 */
	public void setDateRencontreFinale(String dateRencontreFinale) {
		this.dateRencontreFinale = dateRencontreFinale;
	}
	/**
	 * @return Returns the dateRencontreInitiale.
	 */
	public String getDateRencontreInitiale() {
		return dateRencontreInitiale;
	}
	/**
	 * @param dateRencontreInitiale The dateRencontreInitiale to set.
	 */
	public void setDateRencontreInitiale(String dateRencontreInitiale) {
		this.dateRencontreInitiale = dateRencontreInitiale;
	}
	/**
	 * @return Returns the intervenantRencontreFinale.
	 */
	public String getIntervenantRencontreFinale() {
		return intervenantRencontreFinale;
	}
	/**
	 * @param intervenantRencontreFinale The intervenantRencontreFinale to set.
	 */
	public void setIntervenantRencontreFinale(String intervenantRencontreFinale) {
		this.intervenantRencontreFinale = intervenantRencontreFinale;
	}
	/**
	 * @return Returns the intervenantRencontreInitiale.
	 */
	public String getIntervenantRencontreInitiale() {
		return intervenantRencontreInitiale;
	}
	/**
	 * @param intervenantRencontreInitiale The intervenantRencontreInitiale to set.
	 */
	public void setIntervenantRencontreInitiale(
			String intervenantRencontreInitiale) {
		this.intervenantRencontreInitiale = intervenantRencontreInitiale;
	}

	public boolean isTousSitesApplicables() {
		return tousSitesApplicables;
	}

	public void setTousSitesApplicables(boolean tousSitesApplicables) {
		this.tousSitesApplicables = tousSitesApplicables;
	}

}