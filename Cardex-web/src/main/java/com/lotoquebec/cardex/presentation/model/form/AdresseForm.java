package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.AdresseHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchiePPV;
import com.lotoquebec.cardex.presentation.taglib.html.AffichageAdresse;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CardinaliteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUniteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeUtilTelephoneCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.ProvinceAbreviationCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.LienCascade;
import com.lotoquebec.cardexCommun.model.RetourFocus;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/20 22:06:56 $
 * @see com.lotoquebec.cardex.presentation.model.AdresseHtmlForm
 */
public class AdresseForm extends ValidatorForm implements AdresseHtmlForm, RetourFocus, Serializable {

    private String cle = "";
    private String site = "";
    private String siteDescription = "";
    private String numeroMunicipal = "";
    private String typeRue  = "";
    private String typeRueDescription  = "";
    private String nomRue = "";
    private String pointCardinal = "";
    private String pointCardinalDescription = "";
    private String unite = "";
    private String uniteDescription = "";
    private String numeroUnite = "";
    private String adressePostal = "";
    private String adresse = "";
    private String adresse2 = "";
    private String typeUtilTelephone1 = "";
    private String typeUtilTelephone1Description = "";
    private String telephone1 = "";
    private String typeUtilTelephone2 = "";
    private String typeUtilTelephone2Description = "";
    private String telephone2 = "";
    private String typeUtilTelephone3 = "";
    private String typeUtilTelephone3Description = "";
    private String telephone3 = "";
    private LienCascade hierarchiePPV = new HierarchiePPV();
    private String adresseElectronique1 = "";
    private String adresseElectronique2 = "";
    private String statut = GlobalConstants.Adresse.RESIDENCE_PRINCIPALE;
    private String statutDescription = "";
    private String villeDescription = "";
    private String paysDescription = "";
    private String provinceAbreviation = "";
    private String provinceDescription = "";
    private String codePostal = "";
    private String commentaire = "";
    private String createur = "";
    private String modificateur = "";
    private String dateCreation = "";
    private String dateModification = "";
    private String lien = "";
    private String lienSite = "";
    private String adresseTag1 = "";
    private String adresseTag2 = "";
    private String nomChampRetourFocus = "";
    private String message = "";
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    //Septembre 2012, donnée du système RDD (Réseau des détaillants)
    private boolean indicateurRdd = false;
    //Ajout des périodes d'utilisation du téléphone
    private String periodeTelephone1 = "";
    private String periodeTelephone2 = "";
    private String periodeTelephone3 = "";

    
    /**
     * Constructeur de AdresseForm par défaut.
     */
    public AdresseForm() {
    	hierarchiePPV.set(HierarchiePPV.PAYS, GlobalConstants.Adresse.PAYS);
    	hierarchiePPV.set(HierarchiePPV.PROVINCE, GlobalConstants.Adresse.PROVINCE);
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
     * Retourne l'adresse.
     *
     * @return String Valeur de l'adresse en caractère.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Retourne la seconde adresse.
     *
     * @return String Valeur de la seconde adresse en caractère.
     */
    public String getAdresse2() {
        return this.adresse2;
    }

    /**
     * Retourne le premier numéro de téléphone.
     *
     * @return String Valeur du premier numéro de téléphone en caractère.
     */
    public String getTelephone1() {
        return this.telephone1;
    }

    /**
     * Retourne le deuxième numéro de téléphone.
     *
     * @return String Valeur du deuxième numéro de téléphone en caractère.
     */
    public String getTelephone2() {
        return this.telephone2;
    }

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caractère.
     */
    public String getPays() {
        return hierarchiePPV.get(HierarchiePPV.PAYS);
    }

    /**
     * Retourne le troisième numéro de téléphone.
     *
     * @return String Valeur du troisième numéro de téléphone en caractère.
     */
    public String getTelephone3() {
        return this.telephone3;
    }

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince() {
        return hierarchiePPV.get(HierarchiePPV.PROVINCE);
    }

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caractère.
     */
    public String getVille() {
        return hierarchiePPV.get(HierarchiePPV.VILLE);
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
     * Retourne le code postal.
     *
     * @return String Valeur du code postal en caractère.
     */
    public String getCodePostal() {
        return this.codePostal;
    }

    /**
     * Retourne le commentaire.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur() {
        return this.modificateur;
    }

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification() {
        return this.dateModification;
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
     * Affecte une adresse.
     *
     * @param adresse Valeur de l'adresse en caractère.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Affecte une seconde adresse.
     *
     * @param adresse2 Valeur de seconde adresse en caractère.
     */
    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    /**
     * Affecte un premier numéro de téléphone.
     *
     * @param telephone1 Valeur du premier numéro de téléphone en caractère.
     */
    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    /**
     * Affecte un deuxième numéro de téléphone en caractère.
     *
     * @param telephone2 Valeur du deuxième numéro de téléphone en caractère.
     */
    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays en caractère.
     */
    public void setPays(String pays) {
        hierarchiePPV.set(HierarchiePPV.PAYS, pays);
    }

    /**
     * Affecte un troisième numéro de téléphone.
     *
     * @param telephone3 Valeur du troisième numéro de téléphone en caractère.
     */
    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province) {
        hierarchiePPV.set(HierarchiePPV.PROVINCE, province);
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
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caractère.
     */
    public void setVille(String ville) {
        hierarchiePPV.set(HierarchiePPV.VILLE, ville);
    }

    /**
     * Affecte un code postal.
     *
     * @param codePostal Valeur du code postal en caractère.
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caractère.
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur) {
        this.modificateur = modificateur;
    }

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
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

	public String getAdresseElectronique1() {
		return adresseElectronique1;
	}
	
	public void setAdresseElectronique1(String adresseElectronique1) {
		this.adresseElectronique1 = adresseElectronique1;
	}
	
	public String getAdresseElectronique2() {
		return adresseElectronique2;
	}
	
	public void setAdresseElectronique2(String adresseElectronique2) {
		this.adresseElectronique2 = adresseElectronique2;
	}
	
	public String getAdressePostal() {
		return adressePostal;
	}
	
	public void setAdressePostal(String adressePostal) {
		this.adressePostal = adressePostal;
	}
	
	public String getNomRue() {
		return nomRue;
	}
	
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	
	public String getNumeroMunicipal() {
		return numeroMunicipal;
	}
	
	public void setNumeroMunicipal(String numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}
	
	public String getPointCardinal() {
		return pointCardinal;
	}
	
	public void setPointCardinal(String pointCardinal) {
		this.pointCardinal = pointCardinal;
	}
	
	public String getTypeRue() {
		return typeRue;
	}
	
	public void setTypeRue(String typeRue) {
		this.typeRue = typeRue;
	}
	
	public String getNumeroUnite() {
		return numeroUnite;
	}
	
	public void setNumeroUnite(String numeroUnite) {
		this.numeroUnite = numeroUnite;
	}
	
	public String getUnite() {
		return unite;
	}
	
	public void setUnite(String unite) {
		this.unite = unite;
	}
	
    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du AdresseForm.
     *
     * @return String Valeur de tout les attributs du AdresseForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[AdresseForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', adresse = '" + adresse);
        stringBuffer.append("', adresse2 = '" + adresse2);
        stringBuffer.append("', telephone1 = '" + telephone1);
        stringBuffer.append("', periodeTelephone1 = '" + periodeTelephone1);
        stringBuffer.append("', telephone2 = '" + telephone2);
        stringBuffer.append("', periodeTelephone2 = '" + periodeTelephone2);
        stringBuffer.append("', pays = '" + getPays());
        stringBuffer.append("', telephone3 = '" + telephone3);
        stringBuffer.append("', periodeTelephone3 = '" + periodeTelephone3);
        stringBuffer.append("', province = '" + getProvince());
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', ville = '" + getVille());
        stringBuffer.append("', codePostal = '" + codePostal);
        stringBuffer.append("', commentaire = '" + commentaire);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateCreation = '" + dateCreation);
        stringBuffer.append("', dateModification = '" + dateModification);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

    public boolean getAfficherAdresse1(){
    	return StringUtils.isNotEmpty( adresse );
    }
    
    public boolean getAfficherAdresse2(){
    	return StringUtils.isNotEmpty( adresse2 );
    }
    
	public String getTypeUtilTelephone1() {
		return typeUtilTelephone1;
	}
	
	public void setTypeUtilTelephone1(String typeUtilTelephone1) {
		this.typeUtilTelephone1 = typeUtilTelephone1;
	}
	
	public String getTypeUtilTelephone2() {
		return typeUtilTelephone2;
	}
	
	public void setTypeUtilTelephone2(String typeUtilTelephone2) {
		this.typeUtilTelephone2 = typeUtilTelephone2;
	}
	
	public String getTypeUtilTelephone3() {
		return typeUtilTelephone3;
	}
	
	public void setTypeUtilTelephone3(String typeUtilTelephone3) {
		this.typeUtilTelephone3 = typeUtilTelephone3;
	}
	
    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	siteDescription = cache.obtenirLabel(subject, site, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION));
    	statutDescription = cache.obtenirLabel(subject, statut, new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.ADRESSE));
    	villeDescription = cache.obtenirLabel(subject, getVille(), new VilleCleMultiExterneListeCache(subject, getProvince()));
    	adresseTag1 = AffichageAdresse.construireAffichageAdresse(this, "1", subject);
    	adresseTag2 = AffichageAdresse.construireAffichageAdresse(this, "2", subject);
    	paysDescription = cache.obtenirLabel(subject, getPays(), new PaysCleListeCache(subject));
    	provinceAbreviation = cache.obtenirLabel(subject, getProvince(), new ProvinceAbreviationCle(subject, getPays()));
    	provinceDescription = cache.obtenirLabel(subject, getProvince(), new ProvinceCleMultiListeCache(subject, getPays()));
    	typeRueDescription = cache.obtenirLabel(subject, typeRue, new TypeRueCleListeCache(subject));
    	pointCardinalDescription = cache.obtenirLabel(subject, pointCardinal, new CardinaliteCleListeCache(subject));
    	uniteDescription = cache.obtenirLabel(subject, unite, new TypeUniteCleListeCache(subject));
    	typeUtilTelephone1Description = cache.obtenirLabel(subject, typeUtilTelephone1Description, new TypeUtilTelephoneCleListeCache(subject));
    	typeUtilTelephone2Description = cache.obtenirLabel(subject, typeUtilTelephone2Description, new TypeUtilTelephoneCleListeCache(subject));
    	typeUtilTelephone3Description = cache.obtenirLabel(subject, typeUtilTelephone3Description, new TypeUtilTelephoneCleListeCache(subject));
    }
    
	public String getSiteDescription() {
		return siteDescription;
	}
	public String getStatutDescription() {
		return statutDescription;
	}
	public String getVilleDescription() {
		return villeDescription;
	}
	public String getAdresseTag1() {
		return adresseTag1;
	}
	
	public String getAdresseTag2() {
		return adresseTag2;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ServletContext application = getServlet().getServletContext();
        ActionErrors errors = new ActionErrors();

        String validationKey = getValidationKey(mapping, request);

        Validator validator =
            Resources.initValidator(validationKey, this, application, request,
                errors, page);
        
        try {
        	testRequis(errors);
        	
            validatorResults = validator.validate();
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

        return errors;
    }

	private void testRequis(ActionErrors errors) {
		if (StringUtils.isEmpty(getProvince())
		&& StringUtils.isEmpty(getTelephone1())){
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("cardex_adresse_requis"));
		}
	}	
	
	public void assignerPPV(long pays, long province, long ville){
	    hierarchiePPV = new HierarchiePPV(String.valueOf(pays), String.valueOf(province), String.valueOf(ville));
	}


	public String getNomChampRetourFocus() {
		return nomChampRetourFocus;
	}


	public void setNomChampRetourFocus(String nomChampRetourFocus) {
		this.nomChampRetourFocus = nomChampRetourFocus;
	}
	
	public void init(){
	    cle = "";
	    site = "";
	    siteDescription = "";
	    numeroMunicipal = "";
	    typeRue  = "";
	    nomRue = "";
	    pointCardinal = "";
	    unite = "";
	    numeroUnite = "";
	    adressePostal = "";
	    adresse = "";
	    adresse2 = "";
	    typeUtilTelephone1 = "";
	    telephone1 = "";
	    periodeTelephone1 = "";
	    typeUtilTelephone2 = "";
	    telephone2 = "";
	    periodeTelephone2 = "";
	    typeUtilTelephone3 = "";
	    telephone3 = "";
	    periodeTelephone3 = "";
	    hierarchiePPV = new HierarchiePPV();
	    adresseElectronique1 = "";
	    adresseElectronique2 = "";
	    statut = GlobalConstants.Adresse.RESIDENCE_PRINCIPALE;
	    statutDescription = "";
	    villeDescription = "";
	    codePostal = "";
	    commentaire = "";
	    createur = "";
	    modificateur = "";
    	dateCreation = "";
    	dateModification = "";
    	lien = "";
    	lienSite = "";
    	adresseTag1 = "";
    	adresseTag2 = "";
    	nomChampRetourFocus = "";
        this.changePar = "";
	    this.dateChangement = "";
	}


	public String getTypeRueDescription() {
		return typeRueDescription;
	}


	public void setTypeRueDescription(String typeRueDescription) {
		this.typeRueDescription = typeRueDescription;
	}


	public String getPointCardinalDescription() {
		return pointCardinalDescription;
	}


	public void setPointCardinalDescription(String pointCardinalDescription) {
		this.pointCardinalDescription = pointCardinalDescription;
	}


	public String getUniteDescription() {
		return uniteDescription;
	}


	public void setUniteDescription(String uniteDescription) {
		this.uniteDescription = uniteDescription;
	}


	public String getPaysDescription() {
		return paysDescription;
	}


	public void setPaysDescription(String paysDescription) {
		this.paysDescription = paysDescription;
	}


	public String getProvinceDescription() {
		return provinceDescription;
	}


	public void setProvinceDescription(String provinceDescription) {
		this.provinceDescription = provinceDescription;
	}


	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}


	public void setVilleDescription(String villeDescription) {
		this.villeDescription = villeDescription;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getProvinceAbreviation() {
		return provinceAbreviation;
	}

	public void copierExtentionDansAdresse(AdresseForm adresseForm){
		setCle(adresseForm.getCle());
		setSite(adresseForm.getSite());
		setLien(adresseForm.getLien());
		setLienSite(adresseForm.getLienSite());
		setTelephone1(adresseForm.getTelephone1());
		setTelephone2(adresseForm.getTelephone2());
		setTelephone3(adresseForm.getTelephone3());
		setTypeUtilTelephone1(adresseForm.getTypeUtilTelephone1());
		setTypeUtilTelephone2(adresseForm.getTypeUtilTelephone2());
		setTypeUtilTelephone3(adresseForm.getTypeUtilTelephone3());
		setAdresseElectronique1(adresseForm.getAdresseElectronique1());
		setAdresseElectronique2(adresseForm.getAdresseElectronique2());
		setStatut(adresseForm.getStatut());
		setCommentaire(adresseForm.getCommentaire());
		setCreateur(adresseForm.getCreateur());
		setModificateur(adresseForm.getModificateur());
		setDateCreation(adresseForm.getDateCreation());
		setDateModification(adresseForm.getDateModification());
	}

	/**
	 * @return dateChangement
	 */
	public String getDateChangement() {
		return dateChangement;
	}


	/**
	 * @param dateChangement dateChangement à définir
	 */
	public void setDateChangement(String dateChangement) {
		this.dateChangement = dateChangement;
	}


	/**
	 * @return changePar
	 */
	public String getChangePar() {
		return changePar;
	}


	/**
	 * @param changePar changePar à définir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
	}


	/**
	 * @return typeUtilTelephone1Description
	 */
	public String getTypeUtilTelephone1Description() {
		return typeUtilTelephone1Description;
	}


	/**
	 * @param typeUtilTelephone1Description typeUtilTelephone1Description à définir
	 */
	public void setTypeUtilTelephone1Description(
			String typeUtilTelephone1Description) {
		this.typeUtilTelephone1Description = typeUtilTelephone1Description;
	}


	/**
	 * @return typeUtilTelephone2Description
	 */
	public String getTypeUtilTelephone2Description() {
		return typeUtilTelephone2Description;
	}


	/**
	 * @param typeUtilTelephone2Description typeUtilTelephone2Description à définir
	 */
	public void setTypeUtilTelephone2Description(
			String typeUtilTelephone2Description) {
		this.typeUtilTelephone2Description = typeUtilTelephone2Description;
	}


	/**
	 * @return typeUtilTelephone3Description
	 */
	public String getTypeUtilTelephone3Description() {
		return typeUtilTelephone3Description;
	}


	/**
	 * @param typeUtilTelephone3Description typeUtilTelephone3Description à définir
	 */
	public void setTypeUtilTelephone3Description(
			String typeUtilTelephone3Description) {
		this.typeUtilTelephone3Description = typeUtilTelephone3Description;
	}


	/**
	 * @param siteDescription siteDescription à définir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}


	/**
	 * @param provinceAbreviation provinceAbreviation à définir
	 */
	public void setProvinceAbreviation(String provinceAbreviation) {
		this.provinceAbreviation = provinceAbreviation;
	}


	/**
	 * @return indicateurRdd
	 */
	public boolean isIndicateurRdd() {
		return indicateurRdd;
	}


	/**
	 * @param indicateurRdd indicateurRdd à définir
	 */
	public void setIndicateurRdd(boolean indicateurRdd) {
		this.indicateurRdd = indicateurRdd;
	}


	public String getPeriodeTelephone1() {
		return periodeTelephone1;
	}


	public void setPeriodeTelephone1(String periodeTelephone1) {
		this.periodeTelephone1 = periodeTelephone1;
	}


	public String getPeriodeTelephone2() {
		return periodeTelephone2;
	}


	public void setPeriodeTelephone2(String periodeTelephone2) {
		this.periodeTelephone2 = periodeTelephone2;
	}


	public String getPeriodeTelephone3() {
		return periodeTelephone3;
	}


	public void setPeriodeTelephone3(String periodeTelephone3) {
		this.periodeTelephone3 = periodeTelephone3;
	}

}