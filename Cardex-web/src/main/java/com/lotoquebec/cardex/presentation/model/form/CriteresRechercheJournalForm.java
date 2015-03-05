package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRechercheJournalHtmlForm;
import com.lotoquebec.cardex.presentation.model.JournalHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulatire de recherche du
 * journal.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.11 $, $Date: 2002/03/06 20:50:33 $
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheJournalHtmlForm
 */
public class CriteresRechercheJournalForm extends ValidatorForm
        implements CriteresRechercheJournalHtmlForm, RechercheListeResultat {

    private String entite = "";
    private String site = "";
    private String genre = "";
    private String nature = "";
    private String numeroDossier = "";
    private String type = "";
    private String fonde = "";
    private String descriptionType = "";
    private String categorie = "";
    private String intervenant = "";
    private String secteur = "";
    private String dateCreationDu = "";
    private String dateCreationAu = "";
    private String typeRapport = "";
    private String numeroEmploye = "";
    private String reference2 = "";
    private String reference3 = "";
    private String descriptif = "";
    private String endroit = "";
    private String localisation = "";
    private String origine = "";
    private String ordreTriRecherche = "";
    private boolean  ordreCroissantRecherche = true;
    private String    maximumResultatsRecherche = "";
    private ArrayList journaux = new ArrayList();
    private JournalHtmlForm journal = null;
    private ListeResultat listeResultat = new ListeResultat();
    private int sequence = 0;
    
    /**
     * Constructeur de CriteresRechercheJournalForm par défaut.
     */
    public CriteresRechercheJournalForm() {}


    // Getters

    /**
     * Ajoute un journal.
     *
     * @param journal Valeur du journal à ajouter.
     */
    public void addJournal(JournalHtmlForm journal) {
        this.journaux.add(journal);
    }

    /**
     * Retourne le journal asssocie.
     *
     * @return JournalForm Valeur de la cle en caractère.
     */
    public JournalHtmlForm getJournal() {
        return this.journal;
    }

    /**
     * Affecte un journal associe .
     *
     * @param journal Le journal associe.
     */
    public void setJournal(JournalHtmlForm journal) {
        this.journal = journal;
    }

	/**
	 * Returns the categorie.
	 * @return String
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * Returns the dateCreationAu.
	 * @return String
	 */
	public String getDateCreationAu() {
		return dateCreationAu;
	}

	/**
	 * Returns the dateCreationDu.
	 * @return String
	 */
	public String getDateCreationDu() {
		return dateCreationDu;
	}

	/**
	 * Returns the entite.
	 * @return String
	 */
	public String getEntite() {
		return entite;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the journaux.
	 * @return Collection
	 */
	public Collection getJournaux() {
		return this.journaux;
	}

	/**
	 * Returns the maximumResultatsRecherche.
	 * @return String
	 */
	public String getMaximumResultatsRecherche() {
		return maximumResultatsRecherche;
	}

	/**
	 * Returns the ordreCroissantRecherche.
	 * @return boolean
	 */
	public boolean isOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}

	/**
	 * Returns the ordreTriRecherche.
	 * @return String
	 */
	public String getOrdreTriRecherche() {
		return ordreTriRecherche;
	}

	/**
	 * Returns the site.
	 * @return String
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Returns the type.
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the typeRapport.
	 * @return String
	 */
	public String getTypeRapport() {
		return typeRapport;
	}

	/**
	 * Sets the categorie.
	 * @param categorie The categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Sets the dateCreationAu.
	 * @param dateCreationAu The dateCreationAu to set
	 */
	public void setDateCreationAu(String dateCreationAu) {
		this.dateCreationAu = dateCreationAu;
	}

	/**
	 * Sets the dateCreationDu.
	 * @param dateCreationDu The dateCreationDu to set
	 */
	public void setDateCreationDu(String dateCreationDu) {
		this.dateCreationDu = dateCreationDu;
	}

	/**
	 * Sets the entite.
	 * @param entite The entite to set
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the journaux.
	 * @param journaux The journaux to set
	 */
	public void setJournaux(ArrayList journaux) {
		this.journaux = journaux;
	}

	/**
	 * Sets the maximumResultatsRecherche.
	 * @param maximumResultatsRecherche The maximumResultatsRecherche to set
	 */
	public void setMaximumResultatsRecherche(String maximumResultatsRecherche) {
		this.maximumResultatsRecherche = maximumResultatsRecherche;
	}

	/**
	 * Sets the ordreCroissantRecherche.
	 * @param ordreCroissantRecherche The ordreCroissantRecherche to set
	 */
	public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche) {
		this.ordreCroissantRecherche = ordreCroissantRecherche;
	}

	/**
	 * Sets the ordreTriRecherche.
	 * @param ordreTriRecherche The ordreTriRecherche to set
	 */
	public void setOrdreTriRecherche(String ordreTriRecherche) {
		this.ordreTriRecherche = ordreTriRecherche;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the typeRapport.
	 * @param typeRapport The typeRapport to set
	 */
	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}

	/**
	 * Returns the endroit.
	 * @return String
	 */
	public String getEndroit() {
		return endroit;
	}

	/**
	 * Returns the localisation.
	 * @return String
	 */
	public String getLocalisation() {
		return localisation;
	}

	/**
	 * Sets the endroit.
	 * @param endroit The endroit to set
	 */
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	/**
	 * Sets the localisation.
	 * @param localisation The localisation to set
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * Returns the groupe de sécurité.
	 * @return String
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * Sets the groupe.
	 * @param groupe The groupe to set
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     * @param subject 
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(CardexAuthenticationSubject subject) {
        CardexUser user = (CardexUser) subject.getUser();
        
        this.entite = "";
        this.site = "";
        this.genre = "";
        this.nature = "";
        this.type = "";
        this.descriptionType = "";
        this.categorie = "";
        this.numeroDossier = "";
        this.intervenant = "";
        this.endroit = "";
        this.secteur = "";
        this.fonde = "";
        this.localisation = "";
        this.origine = "";
        this.descriptif = "";
        this.numeroEmploye = "";
		this.reference2 = "";
		this.reference3 = "";
        this.dateCreationDu = "";
        this.dateCreationAu = "";
        this.ordreTriRecherche = "";
        this.ordreCroissantRecherche = true;
        this.maximumResultatsRecherche = "";
        
        initialiserGenreNature(user);
        
        setMaximumResultatsRecherche(GlobalConstants.RechercheList.MAX_RESULTATS_DEFAULT);
        setOrdreTriRecherche(GlobalConstants.RechercheList.JOURNAL_ORDRE_TRI_DEFAULT);
        setOrdreCroissantRecherche(true);
        
        genererNumeroSequence();
    }

    private void initialiserGenreNature(CardexUser user){
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
        
        if(getEntite().equals(GlobalConstants.Entite.LOTO_QUEBEC)){
                setGenre(GlobalConstants.Genre.SECURITE_LQ);
                setNature(String.valueOf(GlobalConstants.Nature.JOURNAL_SECURITE));                    
        }else{
        	//Les Salons de jeux ont accès au journal de sécurité.
            if((getSite().equals(GlobalConstants.SiteMaisonJeux.LUDOPLEX_QUEBEC) || getSite().equals(GlobalConstants.SiteMaisonJeux.LUDOPLEX_TROIS_RIVIERE))
            		&& (user.getSecteur() == Long.valueOf(GlobalConstants.Secteur.AGENT_SECURITE) || 
                			user.getSecteur() == Long.valueOf(GlobalConstants.Secteur.GESTIONNAIRE_SECURITE) ||
                			user.getSecteur() == Long.valueOf(GlobalConstants.Secteur.SUPERVISEUR_SECURITE)))  {
        	    setGenre(GlobalConstants.Genre.SECURITE);
                setNature(String.valueOf(GlobalConstants.Nature.JOURNAL_SECURITE_CASINOS));
            }else{
        	    setGenre(GlobalConstants.Genre.SURVEILLANCE);
                setNature(String.valueOf(GlobalConstants.Nature.JOURNAL));
            }
        }
    }
    
    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }
    
	/**
	 * Returns the descriptionType.
	 * @return String
	 */
	public String getDescriptionType() {
		return descriptionType;
	}

	/**
	 * Sets the descriptionType.
	 * @param descriptionType The descriptionType to set
	 */
	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	/**
	 * Returns the descriptif.
	 * @return String
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * Returns the numeroEmploye.
	 * @return String
	 */
	public String getNumeroEmploye() {
		return numeroEmploye;
	}

	/**
	 * Sets the descriptif.
	 * @param descriptif The descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	/**
	 * Sets the numeroEmploye.
	 * @param numeroEmploye The numeroEmploye to set
	 */
	public void setNumeroEmploye(String numeroEmploye) {
		this.numeroEmploye = numeroEmploye;
	}

	/**
	 * Returns the origine.
	 * @return String
	 */
	public String getOrigine() {
		return origine;
	}

	/**
	 * Sets the origine.
	 * @param origine The origine to set
	 */
	public void setOrigine(String origine) {
		this.origine = origine;
	}

	/**
	 * Returns the genre.
	 * @return String
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Returns the nature.
	 * @return String
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * Sets the genre.
	 * @param genre The genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Sets the nature.
	 * @param nature The nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	public ListeResultat getListeResultat() {
		return listeResultat;
	}
	
	public void setListeResultat(List list) {
		this.listeResultat.setResultatComplet( list );
	}
	/**
	 * @return Returns the fonde.
	 */
	public String getFonde() {
		return fonde;
	}
	/**
	 * @param fonde The fonde to set.
	 */
	public void setFonde(String fonde) {
		this.fonde = fonde;
	}


	/**
	 * @return reference2
	 */
	public String getReference2() {
		return reference2;
	}


	/**
	 * @param reference2 reference2 à définir
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}


	/**
	 * @return reference3
	 */
	public String getReference3() {
		return reference3;
	}


	/**
	 * @param reference3 reference3 à définir
	 */
	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}


	public int getSequence() {
		return sequence;
	}


	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	
	
}