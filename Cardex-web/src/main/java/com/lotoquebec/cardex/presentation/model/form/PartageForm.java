package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.PartageHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantActifParSiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Conserve les différentes valeurs relatives au formulatire de consultation des
 * caractéristiques.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.CaracteristiquesHtmlForm
 */
public class PartageForm extends ValidatorForm  implements PartageHtmlForm, Serializable {

    private String cle = "";
    private String site = "";
    private String intervenant = "";
    private String intervenantDescription = "";
    private String profil = "";
    private String profilDescription = "";
    private String siteIntervenant = "";
    private String siteIntervenantDescription = "";
    private String lien = "";
    private String lienSite = "";
    private String numeroDossier = "";
    private String type = "";
    private String categorie = "";
    private String entite = "";
    private String siteOrigine = "";
    private String [] intervenantsChoisis = null;	
    private Collection intervenantsActifParSite = new ArrayList();
    private Collection intervenants = new ArrayList();
    private String genrePartage = ""; //Pargage ouvert ou restreint
    
    /**
     * Constructeur de PartageForm par défaut.
     */
    public PartageForm() {}


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
     * Retourne le nom de l'intervenant qui crée le partage.
     *
     * @return String Valeur de l'intercenant en caractère.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le profil.
     *
     * @return String Valeur du profil en caractère.
     */
    public String getProfil() {
        return this.profil;
    }

    /**
     * Retourne le site des intervenants.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSiteIntervenant() {
        return this.siteIntervenant;
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
     * Affecte un intervenant.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un profil.
     *
     * @param nom Valeur du profil en caractère.
     */
    public void setProfil(String profil) {
        this.profil = profil;
    }

    /**
     * Affecte un site de l'intervenant.
     *
     * @param prenom Valeur du site en caractère.
     */
    public void setSiteIntervenant(String siteIntervenant) {
        this.siteIntervenant = siteIntervenant;
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
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.cle = "";
        this.site = "";
        this.numeroDossier = "";
        this.categorie = "";
        this.lien = "";
        this.lienSite = "";
        this.intervenantsChoisis = null;
    }
    
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     * @throws ValueObjectMapperException 
     * @throws BusinessResourceException 
     */
    public void init(CardexAuthenticationSubject subject, String dossierSite) throws BusinessResourceException, ValueObjectMapperException {
    	ListeCache listeCache = ListeCache.getInstance();
        this.cle = "";
        this.site = "";
        this.intervenant = "";
        this.profil = "";
        this.siteIntervenant = "";
        this.lien = "";
        this.lienSite = "";
        this.intervenantsChoisis = null;
        
        assignerIntervenantsActifParSite(subject, dossierSite);
        intervenants = listeCache.obtenirListe(subject, new IntervenantCle(subject));
    }
    
    public void assignerIntervenantsActifParSite(CardexAuthenticationSubject subject, String dossierSite) throws BusinessResourceException, ValueObjectMapperException{
    	ListeCache listeCache = ListeCache.getInstance();
    	intervenantsActifParSite = listeCache.obtenirListe(subject, new IntervenantActifParSiteCle(subject, dossierSite));
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
     * Détermine si la liste des intervenants est vide.
     *
     * @return True si la listeest vide.
     */
    public boolean isEmpty() {
      if (this.intervenantsChoisis == null) {
        return true;
      }else {
        return this.intervenantsChoisis.length == 0;
      }
    }

    
    /**
     * Retourne une chaîne d'intervenants reflétant la valeur de tous les
     * attributs du PartageForm.
     *
     * @return String Valeur de tous les attributs du PartageForm en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[PartageForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', profil = '" + profil);
        stringBuffer.append("', siteIntervenant = '" + siteIntervenant);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', intervenantsChoisis = (");
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }


	/**
	 * @return numeroDossier
	 */
	public String getNumeroDossier() {
		return numeroDossier;
	}


	/**
	 * @param numeroDossier numeroDossier à définir
	 */
	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}


	/**
	 * @return categorie
	 */
	public String getCategorie() {
		return categorie;
	}


	/**
	 * @param categorie categorie à définir
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	/**
	 * @return entite
	 */
	public String getEntite() {
		return entite;
	}


	/**
	 * @param entite entite à définir
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
	 * @param siteOrigine siteOrigine à définir
	 */
	public void setSiteOrigine(String siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	intervenantDescription = cache.obtenirLabel(subject, intervenant, new IntervenantCle(subject));
    	//profilDescription = cache.obtenirLabel(subject, profil, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SECTEUR, "", GlobalConstants.ActionSecurite.RECHERCHE));
    	//Changement le 11-05-2011. Le profil (secteur) est maintenant directement écrit dans profil.
    	profilDescription = profil;
    	siteIntervenantDescription = cache.obtenirLabel(subject, siteIntervenant, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION));
    }


	/**
	 * @return intervenantDescription
	 */
	public String getIntervenantDescription() {
		return intervenantDescription;
	}


	/**
	 * @param intervenantDescription intervenantDescription à définir
	 */
	public void setIntervenantDescription(String intervenantDescription) {
		this.intervenantDescription = intervenantDescription;
	}


	/**
	 * @return profilDescription
	 */
	public String getProfilDescription() {
		return profilDescription;
	}


	/**
	 * @param profilDescription profilDescription à définir
	 */
	public void setProfilDescription(String profilDescription) {
		this.profilDescription = profilDescription;
	}


	/**
	 * @return siteIntervenantDescription
	 */
	public String getSiteIntervenantDescription() {
		return siteIntervenantDescription;
	}


	/**
	 * @param siteIntervenantDescription siteIntervenantDescription à définir
	 */
	public void setSiteIntervenantDescription(String siteIntervenantDescription) {
		this.siteIntervenantDescription = siteIntervenantDescription;
	}	

    /**
     * Retourne une collection d'intervenants.
     *
     * @return String [] Valeur de la liste de chaîne de caractère.
     */
    public String [] getIntervenantsChoisis() {
        return this.intervenantsChoisis;
    }

    /**
     * Affecte une collection d'intervenants.
     *
     * @param caracteristiquesChoisis Valeur de la liste de chaîne de caractère
     * 
     */
    public void setIntervenantsChoisis(String [] intervenantsChoisis) {
        this.intervenantsChoisis = intervenantsChoisis;
    }


	public Collection getIntervenantsActifParSite() {
		return intervenantsActifParSite;
	}


	public void setIntervenantsActifParSite(Collection intervenantsActifParSite) {
		this.intervenantsActifParSite = intervenantsActifParSite;
	}


	public Collection getIntervenants() {
		return intervenants;
	}


	public void setIntervenants(Collection intervenants) {
		this.intervenants = intervenants;
	}


	public String getGenrePartage() {
		return genrePartage;
	}


	public void setGenrePartage(String genrePartage) {
		this.genrePartage = genrePartage;
	}
  
	
}