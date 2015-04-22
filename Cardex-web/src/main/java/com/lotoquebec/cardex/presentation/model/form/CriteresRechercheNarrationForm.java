package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.generateurRapport.dossier.raq.GlobalRAQDossierGenerateurRapport_CDX_0070;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheNarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de recherche d'une
 * narration.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/25 20:42:42 $
 * @see
 * com.lotoquebec.cardex.presentation.model.CriteresRechercheNarrationHtmlForm
 */
public class CriteresRechercheNarrationForm extends ValidatorForm
        implements CriteresRechercheNarrationHtmlForm, RechercheListeResultat {

	private static final long serialVersionUID = 5339392028941779037L;
	private String entite = "";
    private String site = "";
    private String genre = "";
    private String nature = "";
    private String type = "";
    private String endroit = "";
    private String localisation = "";
    private String origine = "";
    private String categorie = "";
    private String approuve = "";
    private String secteur = "";
    private String intervenant = "";
    private String dateCreationDebut = "";
    private String dateApprobationDebut = "";
    private String dateCreationFin = "";
    private String dateApprobationFin = "";
    private String motCle1 = "";
    private String motCle2 = "";
    private String motCle3 = "";
    private String lien = "";
    private String lienSite = "";
    private String statutApprobation = "";
    private String fonde = "";
    private String typeRecherche = GlobalConstants.TypeRecherche.TOUS;
    private String ordreAffichage = GlobalConstants.OrdreAffichageRechercheNarration.AFFICHAGE_PAR_DATE_DEBUT_DOSSIER;
    private ArrayList narrations = new ArrayList();
    private ListeResultat listeResultat = new ListeResultat();
    private ListeResultat listeResultatAudit = new ListeResultat();
    private int sequence = 0;    

    /**
     * Constructeur de CriteresRechercheNarrationForm par d�faut.
     */
    public CriteresRechercheNarrationForm() {
    }


    // Getters


    /**
     * Retourne l'approbation.
     *
     * @return String Valeur de l'approbation en caract�re.
     */
    public String getApprouve() {
        return this.approuve;
    }

    /**
     * Retourne le secteur.
     *
     * @return String Valeur du secteur en caract�re.
     */
    public String getSecteur() {
        return this.secteur;
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
     * Retourne la date de cr�ation au d�but.
     *
     * @return String Valeur de la date de cr�ation au d�but en caract�re.
     */
    public String getDateCreationDebut() {
        return this.dateCreationDebut;
    }

    /**
     * Retourne la date d'approbation au d�but.
     *
     * @return String Valeur de la date d'approbation au d�but en caract�re.
     */
    public String getDateApprobationDebut() {
        return this.dateApprobationDebut;
    }

    /**
     * Retourne la date de cr�ation � la fin.
     *
     * @return String Valeur de la date de cr�ation � la fin en caract�re.
     */
    public String getDateCreationFin() {
        return this.dateCreationFin;
    }

    /**
     * Retourne la date d'approbation � la fin.
     *
     * @return String Valeur de la date d'approbation � la fin en caract�re.
     */
    public String getDateApprobationFin() {
        return this.dateApprobationFin;
    }

    /**
     * Retourne le premier mot cl�.
     *
     * @return String Valeur du premier mot cl� en caract�re.
     */
    public String getMotCle1() {
        return this.motCle1;
    }

    /**
     * Retourne le deuxi�me mot cl�.
     *
     * @return String Valeur du deuxi�me mot cl� en caract�re.
     */
    public String getMotCle2() {
        return this.motCle2;
    }

    /**
     * Retourne le troisi�me mot cl�.
     *
     * @return String Valeur du troisi�me mot cl� en caract�re.
     */
    public String getMotCle3() {
        return this.motCle3;
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
     * Retourne le crit�re d'approbation.
     *
     * @return String Valeur du crit�re d'approbation dans l'�cran de recherche
     * en caract�re.
     */
    public String getStatutApprobation() {
        return this.statutApprobation;
    }
    /**
     * Retourne une collection de narrations.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations() {
        return this.narrations;
    }


    // Setters


    /**
     * Affecte une approbation.
     *
     * @param approuve Valeur de l'approbation en caract�re.
     */
    public void setApprouve(String approuve) {
        this.approuve = approuve;
    }

    /**
     * Affecte un secteur.
     *
     * @param secteur Valeur du secteur en caract�re.
     */
    public void setSecteur(String secteur) {
        this.secteur = secteur;
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
     * Affecte une date de cr�ation au d�but.
     *
     * @param dateCreationDebut Valeur de la date de cr�ation au d�but en
     * caract�re.
     */
    public void setDateCreationDebut(String dateCreationDebut) {
        this.dateCreationDebut = dateCreationDebut;
    }

    /**
     * Affecte une date d'approbation au d�but.
     *
     * @param dateApprobationDebut Valeur de la date d'approbation au d�but en
     * caract�re.
     */
    public void setDateApprobationDebut(String dateApprobationDebut) {
        this.dateApprobationDebut = dateApprobationDebut;
    }

    /**
     * Affecte une date de cr�ation � la fin.
     *
     * @param dateCreationFin Valeur de la date de cr�ation � la fin en
     * caract�re.
     */
    public void setDateCreationFin(String dateCreationFin) {
        this.dateCreationFin = dateCreationFin;
    }

    /**
     * Affecte une date d'approbation � la fin.
     *
     * @param dateApprobationFin Valeur de la date d'approbation � la fin en
     * caract�re.
     */
    public void setDateApprobationFin(String dateApprobationFin) {
        this.dateApprobationFin = dateApprobationFin;
    }

    /**
     * Affecte un premier mot cl�.
     *
     * @param motCle1 Valeur du premier mot cl� en caract�re.
     */
    public void setMotCle1(String motCle1) {
        this.motCle1 = motCle1;
    }

    /**
     * Affecte un deuxi�me mot cl�.
     *
     * @param motCle2 Valeur du deuxi�me mot cl� en caract�re.
     */
    public void setMotCle2(String motCle2) {
        this.motCle2 = motCle2;
    }

    /**
     * Affecte un troisi�me mot cl�.
     *
     * @param motCle3 Valeur du troisi�me mot cl� en caract�re.
     */
    public void setMotCle3(String motCle3) {
        this.motCle3 = motCle3;
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
     * Affecte un crit�re d'approbation.
     *
     * @param statutApprobation Valeur du crit�re d'approbation dans l'�cran de
     * recherche en caract�re.
     */
    public void setStatutApprobation(String statutApprobation) {
        this.statutApprobation = statutApprobation;
    }

    /**
     * Ajoute une narration.
     *
     * @param sujet Valeur de la narration � ajouter.
     */
    public void addNarration(NarrationHtmlForm narration) {
        this.narrations.add(narration);
    }

    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(CardexAuthenticationSubject subject) {
        CardexUser user = (CardexUser) subject.getUser();
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
        genre = "";
        nature = "";
        type = "";
        endroit = "";
        localisation = "";
        origine = "";
        categorie = "";
        approuve = "";
        secteur = "";
        intervenant = "";
        dateCreationDebut = "";
        dateApprobationDebut = "";
        dateCreationFin = "";
        dateApprobationFin = "";
        motCle1 = "";
        motCle2 = "";
        motCle3 = "";
        lien = "";
        lienSite = "";
        statutApprobation = "";
        fonde = "";
        typeRecherche = GlobalConstants.TypeRecherche.TOUS;
        ordreAffichage = GlobalConstants.OrdreAffichageRechercheNarration.AFFICHAGE_PAR_DATE_DEBUT_DOSSIER;
        narrations.clear();
        listeResultat.init();
        listeResultatAudit.init();
        genererNumeroSequence();
    }

    // Apr�s une requ�te il faut g�n�rer un nouveau num�ro de s�quence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheNarrationForm.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheNarrationForm en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheNarrationForm : ");
        stringBuffer.append("approuve = '" + approuve);
        stringBuffer.append("', secteur = '" + secteur);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', dateCreationDebut = '" + dateCreationDebut);
        stringBuffer.append("', dateApprobationDebut = '"
                + dateApprobationDebut);
        stringBuffer.append("', dateCreationFin = '" + dateCreationFin);
        stringBuffer.append("', dateApprobationFin = '" + dateApprobationFin);
        stringBuffer.append("', motCle1 = '" + motCle1);
        stringBuffer.append("', motCle2 = '" + motCle2);
        stringBuffer.append("', motCle3 = '" + motCle3);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', statutApprobation = '" + statutApprobation);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the typeRecherche.
	 * @return String
	 */
	public String getTypeRecherche() {
		return typeRecherche;
	}

	/**
	 * Sets the typeRecherche.
	 * @param typeRecherche The typeRecherche to set
	 */
	public void setTypeRecherche(String typeRecherche) {
		this.typeRecherche = typeRecherche;
	}

	public ListeResultat getListeResultatAudit() {
		return listeResultatAudit;
	}
	
	public void setListeResultatAudit(List list) {
		this.listeResultatAudit.setResultatCompletAudit(list);
	}
	
	/**
	 * @return Returns the endroit.
	 */
	public String getEndroit() {
		return endroit;
	}
	/**
	 * @param endroit The endroit to set.
	 */
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}
	/**
	 * @return Returns the categorie.
	 */
	public String getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie The categorie to set.
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return Returns the entite.
	 */
	public String getEntite() {
		return entite;
	}
	/**
	 * @param entite The entite to set.
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}
	/**
	 * @return Returns the genre.
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre The genre to set.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @return Returns the localisation.
	 */
	public String getLocalisation() {
		return localisation;
	}
	/**
	 * @param localisation The localisation to set.
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	/**
	 * @return Returns the nature.
	 */
	public String getNature() {
		return nature;
	}
	/**
	 * @param nature The nature to set.
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	/**
	 * @return Returns the origine.
	 */
	public String getOrigine() {
		return origine;
	}
	/**
	 * @param origine The origine to set.
	 */
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	/**
	 * @return Returns the site.
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public ListeResultat getListeResultat() {
		return listeResultat;
	}
	
	public void setListeResultat(List list) {
		this.listeResultat.setResultatComplet( list );
	}

	public String getOrdreAffichage() {
		return ordreAffichage;
	}

	public void setOrdreAffichage(String ordreAffichage) {
		this.ordreAffichage = ordreAffichage;
	}


	public int getSequence() {
		return sequence;
	}


	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	/**
	 * @return fonde
	 */
	public String getFonde() {
		return fonde;
	}


	/**
	 * @param fonde fonde � d�finir
	 */
	public void setFonde(String fonde) {
		this.fonde = fonde;
	}	
	
	
}