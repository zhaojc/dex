package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Predicate;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.controller.util.narration.NarrationVersSujet;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.presentation.util.ExclureLabelValuesPredicate;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de consultation
 * d'une narration.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.13 $, $Date: 2002/03/27 17:39:28 $
 * @see com.lotoquebec.cardex.presentation.model.NarrationHtmlForm
 */
public class NarrationForm extends ValidatorForm implements NarrationHtmlForm, EntiteCardexForm, Serializable {

    private String cle = "";
    private String site = "";
    private String siteDescription = "";
	private String entite = "";
    private String lien = "";
    private String lienSite = "";
    private String genreLiaison = "";
    private String montant = "";
    private String confidentialiteNarration = "";
    private String confidentialiteNarrationDescription = "";
    private String confidentialiteApprobateur = "";
    private String confidentialiteCreateur = "";
    private String autoriteNarration = "";
    private String autoriteApprobateur = "";
    private String autoriteCreateur = "";
    private String tempsConsacre = "";
    private String tempsConsacreHeure = "";
    private String tempsConsacreMinute = "";
    private String rapporteur = "";
    private String rapporteurDescription = "";
    private String createur = "";
    private String createurDescription = "";
	private String secteur = "";
    private String modificateur = "";
    private String approbateur = "";
    private String approbateurDescription = "";
    private String reference = "";
    /*Les champs r�f�rence servent � conserver les valeurs cl�s de la fiche
      � laquelle la narration est rattach�e. 
      Pour DO : num�ro Cardex et num�ro de dossier
      Pour SU : fiche, nom et pr�nom
      Pour SO : nom
      Pour VE : immatriculation
    */
    private String reference1 = "";
    private String reference2 = "";
    private String reference3 = "";
    private String dateCreation = "";
    private String dateModification = "";
    private String dateApprobation = "";
    private String texte = "";
    private String narrationAvecFormat = "";
    private String narrationSansFormat = "";
    private String narrationTemporaire = "";
    private boolean modifiable = false;
    private boolean approuvable = false;
    private boolean nouveau = false;
    private DossierForm dossier = new DossierForm();
    private SujetForm sujet = new SujetForm();
    private SocieteForm societe = new SocieteForm();
    private VehiculeForm vehicule = new VehiculeForm();
    private boolean permettreModification = false;
    private boolean rapportActiviteQuotidienne = false;
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    private boolean premiereNarrationTemporaire = true;
    private String gabaritUtilise = "";
    private List<Predicate> gabaritFiltrerOptionPredicates = new ArrayList<Predicate>();
    
    /**
     * Constructeur de NarrationForm par d�faut.
     */
    public NarrationForm() {}


    // Getters

    /**
     * Retourne le dossier asssocie � la narration .
     *
     * @return DossierForm Valeur de la cle en caract�re.
     */
    public DossierForm getDossier() {
        return this.dossier;
    }

    /**
     * Retourne le sujet asssocie � la narration .
     *
     * @return SujetForm Valeur de la cle en caract�re.
     */
    public SujetForm getSujet() {
        return this.sujet;
    }

    /**
     * Retourne le societe asssocie � la narration .
     *
     * @return SocieteForm Valeur de la cle en caract�re.
     */
    public SocieteForm getSociete() {
        return this.societe;
    }

    /**
     * Retourne le vehicule asssocie � la narration .
     *
     * @return VehiculeForm Valeur de la cle en caract�re.
     */
    public VehiculeForm getVehicule() {
        return this.vehicule;
    }

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
     * Retourne le montant.
     *
     * @return String Valeur du montant en caract�re.
     */
    public String getMontant() {
        return this.montant;
    }

    /**
     * Retourne la confidentialit� de la narration.
     *
     * @return String Valeur de la confidentialit� de la narration en caract�re.
     */
    public String getConfidentialiteNarration() {
        return this.confidentialiteNarration;
    }

    /**
     * Retourne la confidentialit� de l'approbateur.
     *
     * @return String Valeur de la confidentialit� de l'approbateur en
     * caract�re.
     */
    public String getConfidentialiteApprobateur() {
        return this.confidentialiteApprobateur;
    }

    /**
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return String Valeur de la confidentialit� du cr�ateur en caract�re.
     */
    public String getConfidentialiteCreateur() {
        return this.confidentialiteCreateur;
    }

    /**
     * Retourne l'autorit� de la narration.
     *
     * @return String Valeur de l'autorit� de la narration en caract�re.
     */
    public String getAutoriteNarration() {
        return this.autoriteNarration;
    }

    /**
     * Retourne l'autorit� de l'approbateur.
     *
     * @return String Valeur de l'autorit� de l'approbateur en caract�re.
     */
    public String getAutoriteApprobateur() {
        return this.autoriteApprobateur;
    }

    /**
     * Retourne l'autorit� du cr�ateur.
     *
     * @return String Valeur de l'autorit� du cr�ateur en caract�re.
     */
    public String getAutoriteCreateur() {
        return this.autoriteCreateur;
    }

    /**
     * Retourne le temps consacr�.
     *
     * @return String Valeur du temps consacr� en caract�re.
     */
    public String getTempsConsacre() {
        return this.tempsConsacre;
    }

    /**
     * Retourne le nombre d'heure de temps consacr�.
     *
     * @return String Valeur du nombre d'heure de temps consacr� en caract�re.
     */
    public String getTempsConsacreHeure() {
        try{
	        if (this.tempsConsacre != null && this.tempsConsacre.trim().length() > 0) {
	          long tempsTotalMinutes = Long.parseLong(this.tempsConsacre);
	          String heures  = Long.toString(tempsTotalMinutes/60);
	          return heures;
	        }else {
	          return "";
	        }
        }catch (NumberFormatException e){
        	return e.toString();
        }
    }

    /**
     * Retourne le nombre de minute de temps consacr�.
     *
     * @return String Valeur du nombre de minute de temps consacr� en caract�re.
     */
    public String getTempsConsacreMinute() {
		try{
	       if (this.tempsConsacre != null && this.tempsConsacre.trim().length() > 0) {
	          long tempsTotalMinutes = Long.parseLong(this.tempsConsacre);
	          long heures  = tempsTotalMinutes/60;
	          String minutes = Long.toString(tempsTotalMinutes-(heures*60));
	          return minutes;
	       }else {
	        return "";
	       }
		}catch (NumberFormatException e){
			return e.toString();
		}
    }

    /**
     * Retourne le rapporteur.
     *
     * @return String Valeur du rapporteur en caract�re.
     */
    public String getRapporteur() {
        return this.rapporteur;
    }

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur() {
        return this.modificateur;
    }

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur() {
        return this.approbateur;
    }

    /**
     * Retourne la r�f�rence.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caract�re.
     */
    public String getDateModification() {
        return this.dateModification;
    }

    /**
     * Retourne la date de l'approbation.
     *
     * @return String Valeur de la date de l'approbation en caract�re
     */
    public String getDateApprobation() {
        return this.dateApprobation;
    }

    /**
     * Retourne le texte.
     *
     * @return String Valeur du texte en caract�re.
     */
    public String getTexte() {
        return this.texte;
    }

    /**
     * Retourne la narration avec format.
     *
     * @return String Valeur de la narration avec format en caract�re.
     */
    public String getNarrationAvecFormat() {
        return this.narrationAvecFormat;
    }

    /**
     * Retourne la narration sans format.
     *
     * @return String Valeur de la narration sans format en caract�re.
     */
    public String getNarrationSansFormat() {
        return this.narrationSansFormat;
    }

    /**
     * Test si une approbation est approuv�.
     *
     * @return boolean True si la narration est approuv�.
     */
    public boolean isApprouve() {
        return (this.dateApprobation != null && this.dateApprobation.trim().length() > 0);
    }

    /**
     * Test si une narration peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isModifiable() {
        return this.modifiable;
    }

    /**
     * Test si une narration est nouvelle.
     *
     * @return boolean True si la narration est nouvelle.
     */
    public boolean isNouveau() {
        return this.nouveau;
    }

    /**
     * Test si une narration peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable() {
        return this.approuvable;
    }

    /**
     * Test si on peut permettre la modification d'une narration.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public boolean isPermettreModification(){
      return this.permettreModification;
    }

    // Setters


    /**
     * Affecte un un dossier associe a la narration.
     *
     * @param dossier Le dossier associe.
     */
    public void setDossier(DossierForm dossier) {
        this.dossier = dossier;
    }

    /**
     * Affecte un sujet associe a la narration.
     *
     * @param sujet Le sujet associe.
     */
    public void setSujet(SujetForm sujet) {
        this.sujet = sujet;
    }

    /**
     * Affecte un vehicule associe a la narration.
     *
     * @param vehicule Le vehicule associe.
     */
    public void setVehicule(VehiculeForm vehicule) {
        this.vehicule = vehicule;
    }

    /**
     * Affecte une societe associe a la narration.
     *
     * @param societe Le societe associe.
     */
    public void setSociete(SocieteForm societe) {
        this.societe = societe;
    }

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
     * Affecte un montant.
     *
     * @param montant Valeur du montant en caract�re.
     */
    public void setMontant(String montant) {
        this.montant = montant;
    }

    /**
     * Affecte une confidentialit� de la narration.
     *
     * @param confidentialiteNarration Valeur de la confidentialit� de la
     * narration en caract�re.
     */
    public void setConfidentialiteNarration(String confidentialiteNarration) {
        this.confidentialiteNarration = confidentialiteNarration;
    }

    /**
     * Affecte une confidentialit� de l'approbation.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialit� de
     * l'approbateur en caract�re.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur) {
        this.confidentialiteApprobateur = confidentialiteApprobateur;
    }

    /**
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur
     * en caract�re.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur) {
        this.confidentialiteCreateur = confidentialiteCreateur;
    }

    /**
     * Affecte une autorit� de la narration.
     *
     * @param autoriteNarration Valeur de l'autorit� de la narration en
     * caract�re.
     */
    public void setAutoriteNarration(String autoriteNarration) {
        this.autoriteNarration = autoriteNarration;
    }

    /**
     * Affecte une autorit� de l'approbateur.
     *
     * @param autoriteApprobateur Valeur de l'autorit� de l'approbateur en
     * caract�re.
     */
    public void setAutoriteApprobateur(String autoriteApprobateur) {
        this.autoriteApprobateur = autoriteApprobateur;
    }

    /**
     * Affecte une autorit� du cr�ateur.
     *
     * @param autoriteCreateur Valeur de l'autorit� du cr�ateur en caract�re.
     */
    public void setAutoriteCreateur(String autoriteCreateur) {
        this.autoriteCreateur = autoriteCreateur;
    }

    /**
     * Affecte un temps consacr�.
     *
     * @param tempsConsacre Valeur du temps consacr� en caract�re.
     */
    public void setTempsConsacre(String tempsConsacre) {
        this.tempsConsacre = tempsConsacre;
    }

    /**
     * Affecte un nombre d'heure de temps consacr�.
     *
     * @param tempsConsacreHeure Valeur du nombre d'heure temps consacr� en
     * caract�re.
     */
    public void setTempsConsacreHeure(String tempsConsacreHeure) {
        this.tempsConsacreHeure = tempsConsacreHeure;
    }

    /**
     * Affecte un nombre de minute de temps consacr�.
     *
     * @param tempsConsacre Valeur du nombre de minute de temps consacr� en
     * caract�re.
     */
    public void setTempsConsacreMinute(String tempsConsacreMinute) {
        this.tempsConsacreMinute = tempsConsacreMinute;
    }

    /**
     * Affecte un rapporteur.
     *
     * @param rapporteur Valeur du rapporteur en caract�re.
     */
    public void setRapporteur(String rapporteur) {
        this.rapporteur = rapporteur;
    }

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur) {
        this.modificateur = modificateur;
    }

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur) {
        this.approbateur = approbateur;
    }

    /**
     * Affecte une r�f�rence.
     *
     * @param reference Valeur de la r�f�rence en caract�re.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Affecte une date de l'approbation.
     *
     * @param dateApprobation Valeur de la date de l'approbation en caract�re.
     */
    public void setDateApprobation(String dateApprobation) {
        this.dateApprobation = dateApprobation;
    }

    /**
     * Affecte un texte.
     *
     * @param texte Valeur du texte en caract�re.
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Affecte une narration avec format.
     *
     * @param narrationAvecFormat Valeur de la narration avec format en
     * caract�re.
     */
    public void setNarrationAvecFormat(String narrationAvecFormat) {
        this.narrationAvecFormat = narrationAvecFormat;
    }

    /**
     * Affecte une narration sans format.
     *
     * @param narrationSansFormat Valeur de la narration sans format en
     * caract�re.
     */
    public void setNarrationSansFormat(String narrationSansFormat) {
        this.narrationSansFormat = narrationSansFormat;
    }

    /**
     * Determine si une narration est modifiable
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caract�re.
     */
    public void setModifiable(boolean modifiable) {
      this.modifiable = modifiable;
    }

    /**
     * Determine si une narration est nouvelle
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caract�re.
     */
    public void setNouveau(boolean nouveau) {
      this.nouveau = nouveau;
    }
    /**
     * Determine si une narration est approuvanle
     *
     * @param isApprouvable Est-ce que la narration est approuvable
     * caract�re.
     */
    public void setApprouvable(boolean approuvable) {
      this.approuvable = approuvable;
    }

    /**
     * D�termine si on peut permettre la modification d'une narration.
     *
     * @param isPermettreModification Est-ce qu'on peut permettre la modification d'une narration.
     * caract�re.
     */
    public void setPermettreModification(boolean permettreModification){
      this.permettreModification = permettreModification;
    }


    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(List<Long> exclureGabarit) {
       this.cle = "";
       this.site = "";
       this.lien = "";
       this.lienSite = "";
       this.montant = "";
       this.confidentialiteNarration = "";
       this.confidentialiteApprobateur = "";
       this.confidentialiteCreateur = "";
       this.autoriteNarration = "";
       this.autoriteApprobateur = "";
       this.autoriteCreateur = "";
       this.tempsConsacre = "";
       this.tempsConsacreHeure = "";
       this.tempsConsacreMinute = "";
       this.rapporteur = "";
       this.createur = "";
       this.modificateur = "";
       this.approbateur = "";
       this.reference = "";
       this.gabaritUtilise = "";
       this.dateCreation = "";
       this.dateModification = "";
       this.dateApprobation = "";
       this.texte = "";
       this.narrationAvecFormat = "";
       this.narrationSansFormat = "";
       this.modifiable = false;
       this.approuvable = false;
       this.dossier = new DossierForm();
       this.sujet = new SujetForm();
       this.societe = new SocieteForm();
       this.vehicule = new VehiculeForm();
       this.permettreModification = false;
       this.changePar = "";
       this.dateChangement = "";
       this.entite = "";
       this.narrationTemporaire = "";
       this.premiereNarrationTemporaire = true;
       
       assignerFiltreGabarit(exclureGabarit);
    }

    /*
	 * RP0002	Narration Vigilance filtrer les gabarits : afficher seulement le 
	 * gabarit de vigilance qui est pertinente d'avoir dans la liste des gabarits 
	 * d'une narration d'un dossier de vigilance.  Par exemple, s'il existe d�j� une 
	 * narration avec le gabarit "Vigilance 2 - Actions propos�es" la seule possibilit� 
	 * de gabarit de vigilance est "Vigilance 3 - D�cision du comit�". 
     */
    public void assignerFiltreGabarit(List<Long> exclureGabarit){
        getGabaritFiltrerOptionPredicates().clear();
        getGabaritFiltrerOptionPredicates().add( new ExclureLabelValuesPredicate(exclureGabarit) );    	
    }
    
    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du NarrationForm.
     *
     * @return String Valeur de tout les attributs du NarrationForm en
     * caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[NarrationForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', montant = '" + montant);
        stringBuffer.append("', confidentialiteNarration = '"
                + confidentialiteNarration);
        stringBuffer.append("', confidentialiteApprobateur = '"
                + confidentialiteApprobateur);
        stringBuffer.append("', confidentialiteCreateur = '"
                + confidentialiteCreateur);
        stringBuffer.append("', autoriteNarration = '" + autoriteNarration);
        stringBuffer.append("', autoriteApprobateur = '" + autoriteApprobateur);
        stringBuffer.append("', autoriteCreateur = '" + autoriteCreateur);
        stringBuffer.append("', tempsConsacre = '" + tempsConsacre);
        stringBuffer.append("', tempsConsacreHeure = '" + tempsConsacreHeure);
        stringBuffer.append("', tempsConsacreMinute = '" + tempsConsacreMinute);
        stringBuffer.append("', rapporteur = '" + rapporteur);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', approbateur = '" + approbateur);
        stringBuffer.append("', reference = '" + reference);
        stringBuffer.append("', gabarit = '" + gabaritUtilise);
        stringBuffer.append("', dateCreation = '" + dateCreation);
        stringBuffer.append("', dateModification = '" + dateModification);
        stringBuffer.append("', dateApprobation = '" + dateApprobation);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the reference2.
	 * @return String
	 */
	public String getReference2() {
		return reference2;
	}

	/**
	 * Returns the reference3.
	 * @return String
	 */
	public String getReference3() {
		return reference3;
	}

	/**
	 * Sets the reference2.
	 * @param reference2 The reference2 to set
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * Sets the reference3.
	 * @param reference3 The reference3 to set
	 */
	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}

	/**
	 * Returns the reference1.
	 * @return String
	 */
	public String getReference1() {
		return reference1;
	}

	/**
	 * Sets the reference1.
	 * @param reference1 The reference1 to set
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * @return
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * @param string
	 */
	public void setSecteur(String string) {
		secteur = string;
	}

	public boolean getDroitBtnAjoutSujet(){
		boolean isContientFichePersonne = NarrationVersSujet.isContientFichePersonne( narrationAvecFormat );
		return isContientFichePersonne;
	}
	/*
	 * L'usager � le droit de modifier, sans sauvegarder, une narration s'il
	 * n'aurait pas le droit normalement, mais que le narration poss�de
	 * une personne. 
	 */
	public boolean getDroitModifierNarrationSansSauvegarder(){
		return isModifiable() == false
		&& getDroitBtnAjoutSujet() == true;
	}
	
	public String getCreateurDescription() {
		return createurDescription;
	}
	
	public String getApprobateurDescription() {
		return approbateurDescription;
	}
	
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	siteDescription = cache.obtenirLabel(subject, getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION));
    	createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject));
    	approbateurDescription = cache.obtenirLabel(subject, getApprobateur(), new IntervenantCle(subject));
    	rapporteurDescription = cache.obtenirLabel(subject, getRapporteur(), new IntervenantCle(subject));
    	confidentialiteNarrationDescription = cache.obtenirLabel(subject, getConfidentialiteNarration(), new ConfidentialiteCleListeCache(subject));
	}	
	
	public String getSiteDescription() {
		return siteDescription;
	}
	
	public String getConfidentialiteNarrationDescription() {
		return confidentialiteNarrationDescription;
	}

	public boolean isRapportActiviteQuotidienne() {
		
		if (rapportActiviteQuotidienne)
			return rapportActiviteQuotidienne;
		
		rapportActiviteQuotidienne = String.valueOf(GlobalConstants.GabaritNarration.RAPPORT_ACTIVITE_QUOTIDIEN).equals(gabaritUtilise);
		return rapportActiviteQuotidienne;
	}

	public void setRapportActiviteQuotidienne(boolean rapportActiviteQuotidienne) {
		this.rapportActiviteQuotidienne = rapportActiviteQuotidienne;
	}


	/**
	 * @return dateChangement
	 */
	public String getDateChangement() {
		return dateChangement;
	}


	/**
	 * @param dateChangement dateChangement � d�finir
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
	 * @param changePar changePar � d�finir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
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
	 * @param siteDescription siteDescription � d�finir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}


	/**
	 * @param confidentialiteNarrationDescription confidentialiteNarrationDescription � d�finir
	 */
	public void setConfidentialiteNarrationDescription(
			String confidentialiteNarrationDescription) {
		this.confidentialiteNarrationDescription = confidentialiteNarrationDescription;
	}


	/**
	 * @param createurDescription createurDescription � d�finir
	 */
	public void setCreateurDescription(String createurDescription) {
		this.createurDescription = createurDescription;
	}


	/**
	 * @param approbateurDescription approbateurDescription � d�finir
	 */
	public void setApprobateurDescription(String approbateurDescription) {
		this.approbateurDescription = approbateurDescription;
	}


	/**
	 * @return rapporteurDescription
	 */
	public String getRapporteurDescription() {
		return rapporteurDescription;
	}


	/**
	 * @param rapporteurDescription rapporteurDescription � d�finir
	 */
	public void setRapporteurDescription(String rapporteurDescription) {
		this.rapporteurDescription = rapporteurDescription;
	}

	public String getNarrationTemporaire() {
		return narrationTemporaire;
	}

	public void setNarrationTemporaire(String narrationTemporaire) {
		this.narrationTemporaire = narrationTemporaire;
	}

	public String getGenreLiaison() {
		return genreLiaison;
	}

	public void setGenreLiaison(String genreLiaison) {
		this.genreLiaison = genreLiaison;
	}

	public boolean getDisableRechargerNarrationTemporaire(){
		if (StringUtils.isNotEmpty(getNarrationTemporaire()))
			return false;
		else
			return true;
	}

	public boolean isPremiereNarrationTemporaire() {
		return premiereNarrationTemporaire;
	}

	public void setPremiereNarrationTemporaire(boolean premiereNarrationTemporaire) {
		this.premiereNarrationTemporaire = premiereNarrationTemporaire;
	}

	public String getGabaritUtilise() {
		return gabaritUtilise;
	}

	public void setGabaritUtilise(String gabaritUtilise) {
		this.gabaritUtilise = gabaritUtilise;
	}

	public List<Predicate> getGabaritFiltrerOptionPredicates() {
		return gabaritFiltrerOptionPredicates;
	}

	
}