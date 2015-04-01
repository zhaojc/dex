/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 
/**origine.item.list
 * Classe qui contient toutes les consantes
 * globales de l'application cardex.
 *
 * @see
 * @author $Author: mlibersan $
 * @version $Revision: 1.38 $, $Date: 2002/04/25 19:49:46 $
 */
public final class GlobalConstants {

    public static final String VB_BROWSER_ENABLLED_PROPERTY = "vb.browser.enabled";
    public static final String FICHER_IMAGE_TEMPORAIRE = "C:/Program Files/Loto-Qu�bec/Cardex/cardex01.jpg";
    public static final String APPLICATION_CARDEX = "cardex";
    public static final String APPLICATION_PILOTAGE = "pilotage";
    public static final String APPLICATION_PRODUCTION = "/cardex";
    public static final String APPLICATION_FORMATION = "/cardexFormation";
    public static final String REPERTOIRE_IMAGES = "repertoire.sauvegarde.images";
    
    /**
     * Constructeur priv� pour interdire
     * l'instantiation de cette classe
     */
    private GlobalConstants() {}

    /**
     *
     * @author $Author: mlibersan $
     */
    public static class Impression {
        public static final String LOCALE_KEY ="printed.locale.key";

        public static final String DOSSIER_KEY ="printed.dossier.key";

        public static final String SUJET_KEY ="printed.sujet.key";

        public static final String ADRESSE_KEY ="printed.addresse.key";

        public static final String PHOTO_KEY ="printed.photo.key";

        public static final String PHOTO_SUJET_KEY ="printed.photo.sujet.key";

        public static final String INSCRIPTION_KEY ="printed.inscription.key";

        public static final String SUIVI_KEY ="printed.suivi.key";

        public static final String CONSIGNATION_KEY ="printed.consignation.key";

        public static final String JOURNAL_KEY ="printed.journal.key";

        public static final String JOURNAL_CATEGORIE ="rapportCategorie";

        public static final String JOURNAL_CATEGORIE_INTERVENANT ="rapportCategorieIntervenant";

		public static final String CONSIGNATION_RAPPORT ="rapportConsignation";

		public static final String CONSIGNATION_DEVISE_DENOMINATION ="rapportDeviseDenomination";

		public static final String CONSIGNATION_FAUX_BILLET ="rapportFauxBillets";

		public static final String CONSIGNATION_FAUX_BILLET_PERIODE ="rapportFauxBilletsPeriode";

    }

    /**
     * Inner Class qui contient la valeur Oracle pour
     * le type de dossier Contrat
     *
     * @author $Author: mlibersan $
     */
    public static class Contrat {
        public static final int contrat = 22470;
    }

    /**
     * Inner Class qui contient la valeur Oracle pour
     * le r�le par d�faut dans une association
     *
     * @author $Author: mlibersan $
     */
    public static class Role {
    	public static final int ECHANTILLON = 660;
    	public static final int ENQUETE = 21595;
        public static final int ROLE_DEFAUT = 21595;
        public static final int DETAILLANT_VENDEUR = 25995;
        public static final int DETAILLANT_VALIDEUR = 25996;
        public static final int DETAILLANT_ENQUETE = 26176;        
        public static final int RESPONSABLE = 659;        
        public static final int DEMANDEUR = 348;       
        public static final int SANS_OBJET = 15732;
    }

    /**
     * Inner Class qui contient la valeur Oracle pour
     * pour l'affichage de donn�es � partir
     * de la table TR_TRADUCTION dans la langue de l'utilisateur
     * (utilis� entre autres dans le servlet RafraichirListe).
     *
     * @author $Author: mlibersan $
     */
    public static class Langues {
        public static final int FRANCAIS = 1;
        public static final int ANGLAIS = 15472;
    }

    /**
     * Inner Class qui contient la valeur Oracle pour
     * la s�v�rit� par d�faut dans un dossier
     *
     * @author $Author: mlibersan $
     */
    public static class Severite {
        public static final int SEVERITE_DEFAUT = 14955;
        public static final long SEVERITE_2 = 14954; //S�v�rit� 2
        public static final long SEVERITE_3 = 14955; //S�v�rit� 3
        public static final long SEVERITE_4 = 1328;  //S�v�rit� 4
    }

    /**
     * Inner Class qui contient les valeurs
     * pour les menu du cardex
     *
     * @author $Author: mlibersan $
     */
    public static class MotDePasse {
        public static final int MAX_ATTEMPS = 3;

        public static final String DOSSIER_ATTEMPS = "dossier.attemps";

        public static final String SUJET_ATTEMPS = "sujet.attemps";

        public static final String SOCIETE_ATTEMPS = "societe.attemps";

        public static final String VEHICULE_ATTEMPS = "vehicule.attemps";
    }

    /**
     * Inner Class qui contient les valeurs
     * pour les menu du cardex
     *
     * @author $Author: mlibersan $
     */
    public static class Menu {
        public static final String GENRES_RECHERCHE ="menu.genres.recherche";
        public static final String NATURES_RECHERCHE ="menu.natures.recherche";
        public static final String GENRES_GALERIE ="menu.genres.galerie";
        public static final String NATURES_GALERIE ="menu.natures.galerie";
    }

    /**
     * Inner Class qui contient les valeurs
     * pour les types multimedia du cardex
     *
     * @author $Author: mlibersan $
     */
    public static class TypeMutliMedia {
        public static final String IMAGE ="388";

        public static final String PHOTO ="389";

        public static final String SON = "390";

        public static final String VIDEO = "391";

        public static final String DOCUMENT_ANNEXE ="18826";

        public static final String FICHIER ="19616";

        public static final String AUTRE ="392";

    }

	/**
	 * Inner Class qui contient les valeurs
	 * pour les types d'action dans un mandat PSU.
	 *
	 * @author $Author: mlibersan $
	 */
	public static class TypeAction {
		public static final String CONSULTATION ="387";

		public static final String LIAISON ="23740";

		public static final String RECHERCHE = "23741";

		public static final String MISE_A_JOUR = "386";

		public static final String AJOUT ="384";

		public static final String SUPPRESSION ="385";

	}

    public static class TypeAge {
        public static final long REEL =1351;
        public static final long ESTIME =1352;
        public static final long INCONNU =1353;

    }
	
	/**
	 * Inner Class qui contient les valeurs
	 * pour les types de dossiers.
	 *
	 * @author $Author: mlibersan $
	 */
	public static class Type {
		public static final String REPERAGE ="200";
		public static final String ACCIDENT_MALADIE ="194";
		public static final String RECLAMATION_CONSOMMATEUR ="27480";
		public static final String RECLAMATION_PAR ="26097";
        public static final long VENTE_MINEUR = 23661;
	}

	/**
	 * Les types de jeux sont utilis� dans l'�valuation
	 * pour la caract�riser
	 * @author levassc
	 *
	 */
	public static class TypeJeu {
		public static final String MACHINES_A_SOUS = "519";
		public static final String TABLES_DE_JEUX = "520";
		public static final String LOTERIES = "1001";
	}
	
	/**
	 * Inner Class qui contient les valeurs
	 * pour les types de suivi.
	 *
	 * @author $Author: mlibersan $
	 */
	public static class TypeSuivi {

		public static final String COMPLEMENT ="18226";
		public static final String SUIVI_24_HEURES ="25086";
		public static final String UN_JOUR ="1";
		public static final String DELAI_21_JOURS ="25087";
		public static final String VINGT_ET_UN_JOURS ="21";
		public static final String DELAI_30_JOURS ="25088";
		public static final String TRENTE_JOURS ="30";		
	}

	/**
     * Inner Class qui contient les valeurs
     * par d�faut de pays et de province
     *
     */
    public static class Adresse {
    	
    	public static class Statut {
    		public static final String VALIDE = "V"; 
    	}
    	
        public static final String PAYS ="702"; //Canada

        public static final String PROVINCE ="1101"; //Qu�bec

        public static final String RESIDENCE_PRINCIPALE = "621";
    }

    /**
     * Inner Class qui contient les valeurs
     * pour les genre de fichier du cardex
     *
     * @author $Author: mlibersan $
     */
    public static class GenreFichier {
    	public static final String ADRESSE ="AD";
		public static final String CARACTERISTIQUE ="CR";
		public static final String CATEGORIE ="CA";
		public static final String CONSIGNATION ="CN";
        public static final String DOSSIER ="DO";
        public static final String INSCRIPTION = "IS";
		public static final String JEUX ="JE";
		public static final String JOURNAL ="JL";
		public static final String GALERIE ="GA";
        public static final String GENRE ="GE";
        public static final String NARRATION ="CO";
        public static final String NATURE ="NA";
		public static final String PARTICULARITE ="PT";
        public static final String PAYS ="PA";
        public static final String PHOTOS ="LMM";
        public static final String PROVINCE ="PR";
		public static final String POSTE ="PO";
		public static final String PSU_MANDAT ="PSU";
        public static final String SUJET ="SU";
        public static final String SOCIETE = "SO";
        public static final String SUIVI ="SV";
        public static final String TYPE ="TY";
        public static final String VEHICULE = "VE";
        public static final String VILLE ="VI";
        public static final String EVALUATION ="EV";
        public static final String URGENCE ="UR";
    }

    /**
     * Inner Class qui contient les valeurs
     * pour les statut de narration
     *
     * @author $Author: mlibersan $
     */
    public static class StatutApprobation {

        public static final String APPROUVE ="APPROUVE";

        public static final String NON_APPROUVE ="NON_APPROUVE";

        public static final String APPROUVE_PAR_MOI ="APPROUVE_PAR_MOI";
    }

    public static class OrdreAffichageRechercheNarration{

        public static final String AFFICHAGE_PAR_DATE_DEBUT_DOSSIER = "dateDebutDossier";

        public static final String AFFICHAGE_PAR_DATE_CREATION_NARRATION = "dateCreationNarration";

    }

    /**
     * Inner Class qui contient les valeurs
     * pour les types de recherche dans les narrations
     *
     * @author $Author: guerinf $
     */
    public static class TypeRecherche {

        public static final String TOUS ="TOUS";  //Tous les mots

        public static final String ANY ="ANY";  //N'importe lequel des mots

        public static final String DERIVES ="DERIVES"; //Mots d�riv�s

        public static final String LETTRES ="LETTRES";  //Mots contenant les lettres

    }

    /**
     * Inner Class qui contient les valeurs
     * pour les statut de suivi
     *
     * @author $Author: mlibersan $
     */
    public static class StatutSuivi {

        public static final String APPROUVE ="APPROUVE";

        public static final String NON_APPROUVE ="NON_APPROUVE";

        public static final String EMIS_PAR_MOI ="EMIS_PAR_MOI";

        public static final String EMIS_POUR_MOI ="EMIS_POUR_MOI";

        public static final String EN_COURS ="18266";

        public static final String COMPLETE ="18267";

    }

    /**
     * Inner Class qui contient les valeurs
     * pour les statut de suivi
     *
     * @author $Author: mlibersan $
     */
    public static class StatutEmissionSuivi {

        public static final String EMIS_PAR_MOI ="EMIS_PAR_MOI";

        public static final String EMIS_POUR_MOI ="EMIS_POUR_MOI";
    }

    /**
     * Inner Class qui contient les valeurs
     * des cl�s de session qui contiennent les r�sultats
     * de recherche(ValueListIterator).
     *
     * @author $Author: mlibersan $
     */
    public static class RechercheList {

        public static final String ACCES ="acces.recherche.list";

        public static final String ACCES_CURRENT_LIST ="acces.recherche.current.list";

        public static final String ACCES_CURRENT_PAGE ="acces.recherche.current.page";

        public static final String ACCES_MAX_NUMBER_OF_PAGES ="acces.recherche.max.pages";

        public static final String ACCES_HAS_NEXT ="acces.recherche.has.next";

        public static final String ACCES_HAS_PREVIOUS ="acces.recherche.has.previous";

        public static final String CLASSEMENT_ORDRE_TRI_DEFAULT ="tren.v_tr_description,TR1.V_TR_DESCRIPTION,TR2.V_TR_DESCRIPTION,TR3.V_TR_DESCRIPTION,TR4.V_TR_DESCRIPTION";
        
        public static final String CLASSEMENT ="classement.recherche.list";

        public static final String CLASSEMENT_CURRENT_LIST ="classement.recherche.current.list";
        
        public static final String DICTIONNAIRE ="dictionnaire.recherche.list";

        public static final String DICTIONNAIRE_CURRENT_LIST ="dictionnaire.recherche.current.list";
        
        public static final String DOSSIER_ORDRE_TRI_DEFAULT ="";

        public static final String DOSSIER_GENRE_DEFAULT ="dossier.recherche.list.genre.default";

        public static final String DOSSIER_NATURE_DEFAULT ="dossier.recherche.list.nature.default";

        public static final String DOSSIER ="dossier.recherche.list";

        public static final String DOSSIER_CURRENT_LIST ="dossier.recherche.current.list";

        public static final String DOSSIER_CURRENT_PAGE ="dossier.recherche.current.page";

        public static final String DOSSIER_MAX_NUMBER_OF_PAGES ="dossier.recherche.max.pages";

        public static final String DOSSIER_HAS_NEXT ="dossier.recherche.has.next";

        public static final String DOSSIER_HAS_PREVIOUS ="dossier.recherche.has.previous";
        
        public static final String FICHIERS_MAITRES ="fichiers.maitres.recherche.list";

        public static final String FICHIERS_MAITRES_CURRENT_LIST ="fichiers.maitres.recherche.current.list";

        public static final String GENRES_ORDRE_TRI_DEFAULT ="tren.v_tr_description,TR1.V_TR_DESCRIPTION";
        
        public static final String GROUPES ="groupes.recherche.list";

        public static final String GROUPES_CURRENT_LIST ="groupes.recherche.current.list";

        public static final String HISTORIQUE ="historique.recherche.list";

        public static final String HISTORIQUE_CURRENT_LIST ="historique.recherche.current.list";

        public static final String INTERVENTIONS ="interventions.recherche.list";

        public static final String INTERVENTIONS_CURRENT_LIST ="interventions.recherche.current.list";

		public static final String JEUX ="jeux.recherche.list";

		public static final String JEUX_CURRENT_LIST ="jeux.recherche.current.list";

        public static final String JOURNAL_ORDRE_TRI_DEFAULT ="";

        public static final String JOURNAL ="journal.recherche.list";

        public static final String JOURNAL_CURRENT_LIST ="journal.recherche.current.list";

        public static final String JOURNAL_CURRENT_PAGE ="journal.recherche.current.page";

        public static final String JOURNAL_MAX_NUMBER_OF_PAGES ="journal.recherche.max.pages";

        public static final String JOURNAL_HAS_NEXT ="journal.recherche.has.next";

        public static final String JOURNAL_HAS_PREVIOUS ="journal.recherche.has.previous";
        
		public static final String LOCALISATION ="localisation.recherche.list";

		public static final String LOCALISATION_CURRENT_LIST ="localisation.recherche.current.list";
        
        public static final String MAX_RESULTATS_DEFAULT ="200";
        
        public static final String MEMBRES ="membres.list";
        
        public static final String PAYS_ORDRE_TRI_DEFAULT ="T.V_TR_DESCRIPTION";
        
        public static final String PROVINCE_ORDRE_TRI_DEFAULT ="T.V_TR_DESCRIPTION";

        public static final String ROLES ="roles.recherche.list";

        public static final String ROLES_CURRENT_LIST ="roles.recherche.current.list";
        
        public static final String SUJET_ORDRE_TRI_DEFAULT ="";

        public static final String SUJET ="sujet.recherche.list";

        public static final String SUJET_CURRENT_LIST ="sujet.recherche.current.list";

        public static final String SUJET_CURRENT_PAGE ="sujet.recherche.current.page";

        public static final String SUJET_MAX_NUMBER_OF_PAGES ="sujet.recherche.max.pages";

        public static final String SUJET_HAS_NEXT ="sujet.recherche.has.next";

        public static final String SUJET_HAS_PREVIOUS ="sujet.recherche.has.previous";

        public static final String SOCIETE_ORDRE_TRI_DEFAULT ="";

        public static final String SOCIETE = "societe.recherche.list";

        public static final String SOCIETE_CURRENT_LIST ="societe.recherche.current.list";

        public static final String SOCIETE_CURRENT_PAGE ="societe.recherche.current.page";

        public static final String SOCIETE_MAX_NUMBER_OF_PAGES ="societe.recherche.max.pages";

        public static final String SOCIETE_HAS_NEXT ="societe.recherche.has.next";

        public static final String SOCIETE_HAS_PREVIOUS ="societe.recherche.has.previous";

        public static final String SOUS_GROUPES ="sous.groupes.list";
        
		public static final String TYPE_CONSIGNATION ="type.consignation.recherche.list";

		public static final String TYPE_CONSIGNATION_CURRENT_LIST ="type.consignation.recherche.current.list";

        public static final String VEHICULE ="vehicule.recherche.list";
        
        public static final String VEHICULE_CURRENT_LIST ="vehicule.recherche.current.list";

        public static final String VEHICULE_CURRENT_PAGE ="vehicule.recherche.current.page";

        public static final String VEHICULE_MAX_NUMBER_OF_PAGES ="vehicule.recherche.max.pages";

        public static final String VEHICULE_HAS_NEXT ="vehicule.recherche.has.next";

        public static final String VEHICULE_HAS_PREVIOUS ="vehicule.recherche.has.previous";

        public static final String VEHICULES_ORDRE_TRI_DEFAULT ="V_MD_MODELE";
        
        public static final String VILLE_ORDRE_TRI_DEFAULT ="replace(convert(upper(v_vi_ville),'US7ASCII'),'-','a')";

        public static final String NARRATION_ORDRE_TRI_DEFAULT ="";

        public static final String NARRATION ="narration.recherche.list";

        public static final String NARRATION_CURRENT_LIST ="narration.recherche.current.list";

        public static final String NARRATION_CURRENT_PAGE ="narration.recherche.current.page";

        public static final String NARRATION_MAX_NUMBER_OF_PAGES ="narration.recherche.max.pages";

        public static final String NARRATION_HAS_NEXT ="narration.recherche.has.next";

        public static final String NARRATION_HAS_PREVIOUS ="narration.recherche.has.previous";


        public static final String APPROBATION_ORDRE_TRI_DEFAULT ="";

        public static final String APPROBATION ="approbation.recherche.list";

        public static final String APPROBATION_CURRENT_LIST ="approbation.recherche.current.list";

        public static final String APPROBATION_CURRENT_PAGE ="approbation.recherche.current.page";

        public static final String APPROBATION_MAX_NUMBER_OF_PAGES ="approbation.recherche.max.pages";

        public static final String APPROBATION_HAS_NEXT ="approbation.recherche.has.next";

        public static final String APPROBATION_HAS_PREVIOUS ="approbation.recherche.has.previous";

        public static final String VEHICULES ="vehicules.recherche.list";
        
        public static final String VEHICULES_CURRENT_LIST ="vehicules.recherche.current.list";

        public static final String SUIVI_ORDRE_TRI_DEFAULT ="";

        public static final String SUIVI ="suivi.recherche.list";

        public static final String SUIVI_CURRENT_LIST ="suivi.recherche.current.list";

        public static final String SUIVI_CURRENT_PAGE ="suivi.recherche.current.page";

        public static final String SUIVI_MAX_NUMBER_OF_PAGES ="suivi.recherche.max.pages";

        public static final String SUIVI_HAS_NEXT ="suivi.recherche.has.next";

        public static final String SUIVI_HAS_PREVIOUS ="suivi.recherche.has.previous";


        public static final String CONSIGNATION_ORDRE_TRI_DEFAULT ="";

        public static final String CONSIGNATION ="consignation.recherche.list";

        public static final String CONSIGNATION_CURRENT_LIST ="consignation.recherche.current.list";

        public static final String CONSIGNATION_CURRENT_PAGE ="consignation.recherche.current.page";

        public static final String CONSIGNATION_MAX_NUMBER_OF_PAGES ="consignation.recherche.max.pages";

        public static final String CONSIGNATION_HAS_NEXT ="consignation.recherche.has.next";

        public static final String CONSIGNATION_HAS_PREVIOUS ="consignation.recherche.has.previous";


        public static final String PHOTO_GENRE_DEFAULT ="photo.recherche.list.genre.default";

        public static final String PHOTO_NATURE_DEFAULT ="photo.recherche.list.nature.default";

        public static final String PHOTO_ORDRE_TRI_DEFAULT ="";

        public static final String PHOTO ="photo.recherche.list";

        public static final String PHOTO_CURRENT_LIST ="photo.recherche.current.list";

        public static final String PHOTO_LAYOUT_LIST ="photo.recherche.layout.list";

        public static final String PHOTO_CURRENT_PAGE ="photo.recherche.current.page";

        public static final String PHOTO_MAX_NUMBER_OF_PAGES ="photo.recherche.max.pages";

        public static final String PHOTO_HAS_NEXT ="photo.recherche.has.next";

        public static final String PHOTO_HAS_PREVIOUS ="photo.recherche.has.previous";

		public static final String PSU_MANDAT ="psu.mandat.recherche.list";

		public static final String PSU_MANDAT_CURRENT_LIST ="psu.mandat.recherche.current.list";

		public static final String PSU_MANDAT_LAYOUT_LIST ="psu.mandat.recherche.layout.list";

		public static final String PSU_MANDAT_CURRENT_PAGE ="psu.mandat.recherche.current.page";

		public static final String PSU_MANDAT_MAX_NUMBER_OF_PAGES ="psu.mandat.recherche.max.pages";

		public static final String PSU_MANDAT_HAS_NEXT ="psu.mandat.recherche.has.next";

		public static final String PSU_MANDAT_HAS_PREVIOUS ="psu.mandat.recherche.has.previous";

        public static final String LIAISON_DOSSIER_GENRE_DEFAULT ="dossier.recherche.list.liaison.genre.default";

        public static final String LIAISON_DOSSIER_NATURE_DEFAULT ="dossier.recherche.list.liaison.nature.default";

        public static final String LIAISON_DOSSIER ="liaison.dossier.recherche.list";

        public static final String LIAISON_DOSSIER_CURRENT_LIST ="liaison.dossier.recherche.current.list";

        public static final String LIAISON_DOSSIER_CURRENT_PAGE ="liaison.dossier.recherche.current.page";

        public static final String LIAISON_DOSSIER_MAX_NUMBER_OF_PAGES ="liaison.dossier.recherche.max.pages";

        public static final String LIAISON_DOSSIER_HAS_NEXT ="liaison.dossier.recherche.has.next";

        public static final String LIAISON_DOSSIER_HAS_PREVIOUS ="liaison.dossier.recherche.has.previous";


        public static final String LIAISON_SUJET ="sujet.recherche.list";

        public static final String LIAISON_SUJET_CURRENT_LIST ="sujet.recherche.current.list";

        public static final String LIAISON_SUJET_CURRENT_PAGE ="sujet.recherche.current.page";

        public static final String LIAISON_SUJET_MAX_NUMBER_OF_PAGES ="sujet.recherche.max.pages";

        public static final String LIAISON_SUJET_HAS_NEXT ="sujet.recherche.has.next";

        public static final String LIAISON_SUJET_HAS_PREVIOUS ="sujet.recherche.has.previous";


        public static final String LIAISON_SOCIETE = "liaison.societe.recherche.list";

        public static final String LIAISON_SOCIETE_CURRENT_LIST ="liaison.societe.recherche.current.list";

        public static final String LIAISON_SOCIETE_CURRENT_PAGE ="liaison.societe.recherche.current.page";

        public static final String LIAISON_SOCIETE_MAX_NUMBER_OF_PAGES ="liaison.societe.recherche.max.pages";

        public static final String LIAISON_SOCIETE_HAS_NEXT ="liaison.societe.recherche.has.next";

        public static final String LIAISON_SOCIETE_HAS_PREVIOUS ="liaison.societe.recherche.has.previous";


        public static final String LIAISON_VEHICULE = "liaison.vehicule.recherche.list";

        public static final String LIAISON_VEHICULE_CURRENT_LIST ="liaison.vehicule.recherche.current.list";

        public static final String LIAISON_VEHICULE_CURRENT_PAGE ="liaison.vehicule.recherche.current.page";

        public static final String LIAISON_VEHICULE_MAX_NUMBER_OF_PAGES ="liaison.vehicule.recherche.max.pages";

        public static final String LIAISON_VEHICULE_HAS_NEXT ="liaison.vehicule.recherche.has.next";

        public static final String LIAISON_VEHICULE_HAS_PREVIOUS ="liaison.vehicule.recherche.has.previous";

        public static final String POSTES ="postes.recherche.list";

        public static final String POSTES_CURRENT_LIST ="postes.recherche.current.list";

    }

    /**
     * Constantes pour les genres de dossiers
     *
     * @author $Author: mlibersan $
     */
    public static class Genre {
        public static final String ENQUETE ="413";

        public static final String SECURITE ="414";

        public static final String SECURITE_LQ ="26528";        

        public static final long SUJETS_INTERET = 415;

        public static final String DOSSIERS = "15492";

        public static final String SURVEILLANCE = "18046";

        public static final String DOSSIERS_LQ ="16500";
        
        public static final String DOSSIERS_POL ="24423";

        public static final String INVESTIGATION ="21876";

        public static final String PROCEDURES ="20916";
    }
    
    /**
     * Constantes pour les natures de dossiers
     *
     * @author $Author: mlibersan $
     */
    public static class Nature {

        public static final long AUTO_EXCLUSION = 416;

        public static final long ACCES_INTERDIT = 417;

        public static final long ACCES_INTERDIT_LOTERIE = 27074;

        public static final long AVIS_DE_GUET = 78;

        public static final long AVIS_DE_GUET_LOTERIE = 27118;

        public static final long DOSSIERS = 82;

        public static final long INCIDENT = 83;

        public static final long EVENEMENTS = 15493;

        public static final long OBSERVATIONS = 15821;

        public static final long EVENEMENTS_LQ = 16008;
        
        public static final long EVENEMENTS_DCSI = 24024;

        public static final long PLAINTES_POL = 24468;

        public static final long RECLAMATIONS_POL = 24424;

        public static final long EVENEMENTS_POL = 16501;

        public static final long INVESTIGATIONS = 16182;

        public static final long PROCEDURES = 20917;

        public static final long JOURNAL = 21791;

        public static final long JOURNAL_ENQUETES = 26707;
        
        public static final long JOURNAL_SECURITE = 27817;

        public static final long JOURNAL_SECURITE_CASINOS = 28050;
    }

  
    /**
     * Constantes pour les statuts de dossiers
     *
     * @author $Author: mlibersan $
     */
    public static class Statut {
        public static final long DOSSIER_ACTIF = 359;
        public static final String DOSSIER_INACTIF ="361";
        public static final String SUJET_ACTIF ="363";
        public static final String SUJET_INACTIF = "365";
        public static final String SUJET_PROVISOIRE ="26463";
        public static final String SOCIETE_REGULIER = "18973";
        public static final String INTERVENANT_ACTIF ="532";
        public static final String INTERVENANT_INACTIF ="533";
        public static final String INSCRIPTION_INACTIF = DOSSIER_INACTIF;
        public static final long INSCRIPTION_ACTIF = DOSSIER_ACTIF;
        public static final long REFUS_AVANT_APPEL = 857;
        
    }
    
    /**
     * Constantes pour la propri�t fond� d'un dossier
     *
     * @author $Author: mlibersan $
     */
    public static class Fonde {

        public static final String INDETERMINE ="16122";
        public static final String OUI ="16123";
        public static final String NON ="16124";
        public static final String A_SUIVRE ="22830";
    }

    /**
     * Constantes pour la propri�t� num�ro cardex d'un dossier
     *
     * @author $Author: mlibersan $
     */
    public static class  NumeroCardex {

        public static final String DEFAULT ="                   *";
    }

    /**
     * Constantes pour la propri�t� num�ro cardex d'un dossier
     *
     * @author $Author: mlibersan $
     */
    public static class  NumeroFiche {

        public static final String DEFAULT ="                                                 *";
    }

    /**
     * Constantes pour la confidentialit� 8 (en vue de la suppression).
     *
     */
    public static class  Confidentialite {
    	public static final long UN = 366;
    	public static final long QUATRE = 369;
        public static final long HUIT = 14920;
        public static final long C = 307;
    }

    public static class NiveauHierachique {
        public static final long UN = 379;
    }

    /**
     * Inner Class qui contient les valeurs
     * des cl�s de session pour les LabelValueBean
     * qui contiennent les donn�es de
     * r�f�rences relatives aux consignations.
     *
     * @author $Author: mlibersan $
     */
    public static class Consignations {
		public static final long CDN = 23630;

		public static final long FAUX_BILLETS = 23639;

    }

    /**
     * Valeur de groupe de s�curit� qui n�cessite
     * un traitement fonctionnel sp�cial
     * @author levassc
     */
    public static class GroupeSecurite{
    	public final static long ADMINISTRATION = 1909;
    	public static final int INVESTIGATION_PAG = 225;
    }

    public static class Periode{
    	public static final String A_SUIVRE = "22950";
    	public static final String FIXE = "605";
    	public static final String A_SUIVRE_CONSEILLER = "477";
    }

    public static class ChoixImpressionContrat{
    	public static final String CONTRAT_AUTOEXCLUSION = "484";
    	public static final String CONTRAT_AUTOEXCLUSION_MONTREAL = "autoexclusionMontreal";
    	public static final String CONTRAT_AUTOEXCLUSION_ESPACEJEUX = "486";
    	public static final String CONTRAT_INSCRIPTIONS = "avecInscriptions";
    	public static final String CONTRAT_SUIVIS = "487";
    	public static final String CONTRAT_READMISSION = "550";
    }

    public static class ChoixImpressionDossier{
    	public static final String DOSSIER = "542";
    	public static final String DOSSIER_HISTORIQUE = "543";
    	public static final String DOSSIER_PIECES_JOINTES = "544";
    	public static final String DOSSIER_PIECES_JOINTES_HISTORIQUE = "545";
    	public static final String DOSSIER_UNIFORMISE = "546";
    	public static final String DOSSIER_UNIFORMISE_HISTORIQUE = "547";
    	public static final String DOSSIER_VIGILANCE_SOMMAIRE = "548";
    	public static final String DOSSIER_VIGILANCE_DETAILLE = "549";
    }

    public static class ChoixImpressionListe{
    	public static final String IMPRIMER_PAGE_RESULTATS = "491";
    	public static final String IMPRIMER_TOUTES_PAGES_RESULTATS = "492";
    	public static final String IMPRIMER_DOSSIERS_AVEC_SUJETS = "489";
    	public static final String IMPRIMER_DOSSIERS_SANS_SUJETS = "490";
    }

    public static class ChoixRapport{
    	public static final String ACCES_INTERVENANT ="CDX_0122_audit_acces_intervenant.jrxml";
    	public static final String ACCES_NOUVEAUX_INTERVENANTS ="Acces des nouveaux intervenants";
    	public static final String ACCES_SUPERTUILISATEURS ="Acces des superutilisateurs";
    	public static final String ADRESSE_INVALIDE = "com.lotoquebec.cardex.generateurRapport.adresse.AdresseInvalide";
    	public static final String AUDIT_CHANGEMENTS_ADRESSES = "CDX_0180_audit_changements_adresses.jrxml";
    	public static final String AUDIT_CHANGEMENTS_DOSSIERS = "CDX_0182_audit_changements_dossiers.jrxml";
    	public static final String AUDIT_CHANGEMENTS_INTERVENANTS ="CDX_0183_audit_changements_intervenants.jrxml";
    	public static final String AUDIT_CHANGEMENTS_NARRATIONS = "CDX_0181_audit_changements_narrations.jrxml";
    	public static final String AUDIT_CHANGEMENTS_SOCIETES = "CDX_0184_audit_changements_societes.jrxml";
    	public static final String AUDIT_CHANGEMENTS_SUJETS = "CDX_0185_audit_changements_sujets.jrxml";
    	public static final String AUDIT_CHANGEMENTS_SUIVIS = "CDX_0186_audit_changements_suivis.jrxml";
    	public static final String AUDIT_CHANGEMENTS_VEHICULES = "CDX_0187_audit_changements_vehicules.jrxml";
    	public static final String AUDIT_ACCES_DOSSIERS = "CDX_0210_impression_audit_acces_dossiers.jrxml";
    	public static final String AUDIT_ACCES_SUJETS = "CDX_0211_impression_audit_acces_sujets.jrxml";
    	public static final String AUDIT_ACCES_SOCIETES = "CDX_0212_impression_audit_acces_societes.jrxml";
    	public static final String AUDIT_ACCES_VEHICULES = "CDX_0213_impression_audit_acces_v�hicules.jrxml";
    	public static final String DETAIL_ACCES_DOSSIERS ="CDX_0121_audit_detail_acces.jrxml";
    	public static final String DETAIL_ACCES_INTERVENANT ="CDX_0123_audit_detail_acces_intervenant.jrxml";
    	public static final String ECRAN = "ECRAN";
    	public static final String FICHIER = "FICHIER";
    	public static final String IMPRESSION_SUJET = "CDX_0002_sujet.jrxml";
    	public static final String IMPRESSION_SOCIETE = "CDX_0003_societe.jrxml";
    	public static final String IMPRESSION_DOSSIER = "dossier.jrxml";
    	public static final String IMPRESSION_GALERIE = "CDX_0004_sujet-galerie.jrxml";
    	public static final String JOURNAL_CATEGORIE = "rapport_journal_categorie.jrxml";
    	public static final String JOURNAL_CATEGORIE_INTERVENANT = "rapport_journal_categorie_intervenant.jrxml";
    	
    	
        public static final String MATRICE_GROUPES ="CDX_0170_matrice_roles_groupes.jrxml";
        public static final String MATRICE_PROFILS ="CDX_0171_matrice_roles_profils.jrxml";
        public static final String MATRICE_GROUPES_DESCRIPTION ="CDX_0172_matrice_roles_groupes_description.jrxml";
    	public static final String MENSUEL_RENCONTRES_FINALES = "CDX_0063_rapport_rencontres_finales.jrxml";
    	public static final String MENSUEL_RENCONTRES_FINALES_DETAIL = "CDX_0064_rapport_rencontres_finales_detail.jrxml";
    	public static final String REPERAGES_AVIS_DE_GUET = "CDX_0013_reperages_avis_guet.jrxml";
    	public static final String SOMMAIRE_ACCES_DOSSIERS ="CDX_0120_audit_sommaire_acces.jrxml";
    	public static final String RESULTATS_RECHERCHE_DOSSIERS = "CDX_0110_resultats_recherche_dossiers.jrxml";
    	public static final String ONGLET_DOSSIERS_DOSSIER = "CDX_0114_liste_dossiers_onglet_dossiers_dossier.jrxml";
    	public static final String ONGLET_DOSSIERS_SUJET = "CDX_0115_liste_dossiers_onglet_dossiers_sujet.jrxml";
    	public static final String ONGLET_SUJET_SOCIETE = "CDX_0116_liste_sujets_onglet_sujets_soci�t�.jrxml";
    	public static final String RESULTATS_RECHERCHE_DOSSIERS_COMPLET = "resultats_recherche_dossiers_complet.jrxml";
    	public static final String RESULTATS_RECHERCHE_SUJETS = "CDX_0111_resultats_recherche_sujets.jrxml";
    	public static final String RESULTATS_RECHERCHE_ADRESSE_SUJETS = "resultats_recherche_adresse_sujets.jrxml";
    	public static final String RESULTATS_RECHERCHE_SUJETS_COMPLET = "resultats_recherche_sujets_complet.jrxml";
    	public static final String RESULTATS_RECHERCHE_SOCIETES = "CDX_0112_resultats_recherche_societes.jrxml";
    	public static final String RESULTATS_RECHERCHE_SOCIETES_COMPLET = "resultats_recherche_societes_complet.jrxml";
    	public static final String RESULTATS_RECHERCHE_ADRESSE_SOCIETES = "resultats_recherche_adresse_societes.jrxml";
    	public static final String RESULTATS_RECHERCHE_VEHICULES = "CDX_0113_resultats_recherche_vehicules.jrxml";
    	public static final String RESULTATS_RECHERCHE_VEHICULES_COMPLET = "resultats_recherche_vehicules_complet.jrxml";
        public static final String RESULTATS_RECHERCHE_URGENCE = "CDX_0272_resultats_recherche_service_urgence.jrxml";
        public static final String RESULTATS_RECHERCHE_URGENCE_COMPLET = "resultats_recherche_service_urgence_complet.jrxml";
    	public static final String RESULTATS_RECHERCHE_SUIVIS = "CDX_0096_resultats_recherche_suivis.jrxml";
    	public static final String RESULTATS_RECHERCHE_MANDATS = "CDX_0190_resultat_recherche_mandats.jrxml";
    	public static final String RESULTATS_RECHERCHE_GALERIE = "CDX_0201_impression_galerie.jrxml";
    	public static final String CONSIGNATION_ACTIONS_MANDATS = "CDX_0191_historique_actions_mandats.jrxml";
    	public static final String RESULTATS_RECHERCHE_CONSIGNATIONS = "CDX_0155_resultats_recherche_consignations.jrxml";
    	public static final String IMPRESSION_INSCRIPTION = "CDX_0200_impression_inscription.jrxml";
    	public static final String IMPRESSION_SUIVI = "CDX_0097_impression_suivi.jrxml";
    	public static final String RESULTATS_RECHERCHE_DOSSIERS_AVEC_SUJETS = "CDX_0224_impression_dossiers_avec_sujets.jrxml";
    	public static final String RESULTATS_RECHERCHE_DOSSIERS_SANS_SUJETS = "CDX_0225_impression_dossiers_sans_sujets.jrxml";
    	public static final String IMPRESSION_DETAILLE_DOSSIER_VIGILANCE = "CDX_0233_rapport_vigilance_detaille.jrxml";
    	public static final String IMPRESSION_SOMMAIRE_DOSSIER_VIGILANCE = "CDX_0232_rapport_vigilance_sommaire.jrxml";
    	public static final String RAPPORT_ACCES_SUJETS = "CDX_0124_audit_acces_sujets.jrxml";
    	public static final String RAPPORT_ACCES_SUJETS_FOURNISSEURS = "CDX_0125_audit_acces_sujets_fournisseurs.jrxml";
    	public static final String RAPPORT_ACCES_NARRATIONS = "CDX_0126_audit_acces_narrations.jrxml";
    	//Les rapports suivants vont ensemble. Les choix d�terminent la proc�dure qui sera appel�e. Les donn�es retourn�es
    	//sont pass�es au rapport CDX_0072.
    	public static final String ANALYSE_ACCES = "CDX_0072_rapport_analyse_acces.jrxml";
    	public static final String ANALYSE_ACCES_SUJETS = "Consultation des sujets";
    	public static final String ANALYSE_ACCES_SUJETS_INTERVENANT = "Consultation des sujets par intervenant";
    	public static final String ANALYSE_ACCES_DOSSIERS ="Acces aux dossiers";
} 

    public static class Classes{
    	public static final long POMPIER = 25196;
    	public static final long POLICE = 18967;
    	public static final long AMBULANCE = 23910;
    	public static final long EMPLOYE = 24004;
    }

    public static class SiteMaisonJeux{
    	public static final String MONTREAL = "7";
    	public static final String CHARLEVOIX = "5";
    	public static final String LAC_LEAMY = "6";
    	public static final String LUDOPLEX_TROIS_RIVIERE = "23";
    	public static final String MONT_TREMBLANT = "24";
    	public static final String LUDOPLEX_QUEBEC = "28";
    	public static final String LUDOPLEX_LAVAL = "29";
    	public static final String DOMREMY = "55";
    	public static final String CENTRE_CASA = "50";
    	public static final String CENTRE_UBALD_VILLENEUVE = "51";
    	public static final String CENTRE_EVALUATION = "10";
    	public static final String MAISON_JEAN_LAPOINTE = "52";
    	public static final String ESPACEJEUX = "75";

    }

    public static class AbreviationSites{
    	public static final String MONTREAL = "MTL";
    	public static final String CHARLEVOIX = "CHA";
    	public static final String LAC_LEAMY = "CLL";
    	public static final String MONT_TREMBLANT = "CMT";
    }

    public static class Sites{
    	public static final String LOTO_QUEBEC = "8";
    	public static final String CLIENTS_MYSTERES = "25";
    	public static final String ESPACEJEUX = "Espacejeux";
		public static final String INVESTIGATION = "30";
    	public static final String INVESTIGATION_FACTUREE = "11";
	}
    

    public static class LienRole{
    	public static final long CLIENT = 15293;
    	public static final long N_A = 15732;
    	//Valeur arbitraire pour indiquer qu'il s'agit d'une relation entre 2 sujets ou deux soci�t�s.
    	//Dans ce cas, la liste des r�les � afficher est diff�rente.
    	public static final String RELATION = "1";
    }

    public static class LangueListe{
    	public static final long FRANCAIS = 410;
    }

    public static class Dossier{
    	public static final String NUMERO_UNIQUE = "numeroUnique";
    }

    public static class Entite{
    	public static final String MAISON_JEUX = "2";
    	public static final String LOTO_QUEBEC = "3";
    	public static final long INVESTIGATION = 20495;
    }

    public static class Langue{
    	public static final String FRANCAIS = "fr";
    	public static final String ANGLAIS = "en";
    }

    public static class ChoixRechercheAdresse{
    	public static final String SOCIETE = "societe";
    	public static final String SUJET = "sujet";
    }

    public static class ListeCache{

    	public static class Statut{
    		public static final String ADRESSE = "AD";
    		public static final String DOSSIER = "DO";
    		public static final String SUIVI = "SV";
    		public static final String SUJET = "SU";
    		public static final String SOCIETE = "SO";
    		public static final String INTERVENANT = "PO";
    		public static final String URGENCE = "UR";
    	}

    	public static final String TYPE_UNITE = "TU";
    	public static final String CARACTERISTIQUE = "CR";
    	public static final String CARDINALITE = "CT";
    	public static final String CONFIDENTIALITE = "CC";
    	public static final String LOCALISATION = "CD";
    	public static final String TYPE_RUE = "TR";
    	public static final String FONDE = "FO";
    	public static final String CLASSE = "CL";
    	public static final String DEVISE = "DE";
    	public static final String SEVERITE = "SE";
    	public static final String DENOMINATION = "DN";
    	public static final String ETHNIE = "NT";
    	public static final String ENDROIT = "OR";
    	public static final String LANGUE = "LS";
    	public static final String NIVEAU_HIERARCHIQUE = "NH";
    	public static final String PARTICULARITE = "PT";
    	public static final String PERIODE = "PE";
    	public static final String RACE = "RA";
    	public static final String REGROUPEMENTS_ENDROITS = "SR"; //SR_SERVICE
    	public static final String ROLE = "RO";
    	public static final String SEXE = "SX";
    	//public static final String STATUT = "ST";
    	public static final String PAYS = "PA";
    	public static final String PROVINCE = "PR";
    	public static final String REFERENCE_VIDEO = "RF";
    	public static final String TELEPHONE_UTIL = "TE";
    	public static final String TYPE_ACTION = "TA";
    	public static final String TYPE_ACTIVITE = "TC";
    	public static final String TYPE_CONSIGNATION = "TN";
    	public static final String TYPE_JEUX = "TJ";
    	public static final String TYPE_AGE = "TYA";
    	public static final String ANNEE = "ANNEE";
    	public static final String MOIS = "MOIS";

    	public static final String EXPEDITEUR_COURRIEL = "EXPED";
    	public static final String EXPEDITEUR_COURRIEL_RH = "EXPRH";
    }

    public static class CleListe{ 
    	public static final String ACTION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.ActionTableValeurCleSQL";
    	public static final String ANNEE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.AnneesCleListeCache";
    	public static final String APPLICATION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ApplicationCleMultiListeCache";
    	public static final String CARACTERISTIQUE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CaracteristiqueCleListeCache";
    	public static final String CATEGORIE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache";
    	public static final String CLASSE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ClasseCleListeCache";
    	public static final String CODE_TABLE_VALEUR = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CodeTableValeurCleSQL";
    	public static final String CODE_AJOUT_TABLE_VALEUR = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CodeAjoutTableValeurCleSQL";
    	public static final String DEFILEMENT = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.DefilementCleHardListe";
    	public static final String DENOMINATION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.DenominationCleListeCache";
    	public static final String DEVISE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.DeviseCleListeCache";
    	public static final String ENTITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EntiteCleMultiListeCache";
    	public static final String ENDROIT = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EndroitCleListeCache";
    	public static final String ETHNIE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EthnieCleListeCache";
    	public static final String FONDE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.FondeCleListeCache";
    	public static final String INTERVENANT = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle";
    	public static final String INTERVENANT_ACTIF_PAR_SITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantActifParSiteCle";
    	public static final String INTERVENANT_PAR_SECTEUR = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSecteurCle";
    	public static final String INTERVENANT_PAR_SITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle";
    	public static final String GENRE_FICHIER = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.GenreFichierCle";
    	public static final String GROUPE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.GroupeCleMultiListeCache";
    	public static final String LANGUE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LangueCleListeCache";
    	public static final String LANGUE_APPLICATION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.LangueCleHardListe";
    	public static final String LOCALISATION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LocalisationCleListeCache";
    	public static final String MARQUE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.MarqueCleMultiListeCache";
    	public static final String MODELE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.ModeleCleMultiListeCache";
    	public static final String MODELE_MARQUE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.ModeleMarqueCle";
    	public static final String MOIS = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.MoisCleListeCache";
    	public static final String NIVEAU = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.NiveauCleHardListe";
    	public static final String NIVEAU_HIERARCHIQUE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.NiveauHierarchiqueCle";
    	public static final String PARTICULARITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ParticularitesCleListeCache";
    	public static final String PAYS = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache";
    	public static final String PERIODE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PeriodeCleListeCache";
    	public static final String PROFIL = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.ProfilsCle";
    	public static final String PROVINCE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceCleMultiListeCache";
    	public static final String PROVINCE_SANS_REQUIS = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.ProvinceSansRequisCleMultiListeCache";
    	public static final String RACE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RaceCleListeCache";
    	public static final String RAPPORT_SECTION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe.RapportSectionCleRessourceHardListe";
    	public static final String REFERENCE_VIDEO = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ReferenceVideoCleListeCache";
    	public static final String REGROUPEMENT_ENDROITS = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RegroupementEndroitsCleListeCache";
    	public static final String ROLE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RoleCleListeCache";
    	public static final String ROLE_SECURITE_ADMINISTRER_PAR_ECRAN = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.RoleSecuriteAdministrerCle";
    	public static final String SECTEUR_INTERVENANT_PAR_SITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SecteurIntervenantParSiteCle";
    	public static final String SOUS_SECTEUR_PAR_SECTEUR = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SousSecteurTableValeurCle";
    	public static final String SEVERITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache";
    	public static final String SEXE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SexeCleListeCache";
    	public static final String SITE_APPLICABLE_TABLE_VALEUR = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteApplicableTableValeurCle";
    	public static final String SITE_INTERVENTION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.SiteInterventionCle";
    	public static final String SITE_ORIGINE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteOrigineTableValeurCle";
    	public static final String SOCIETE_PAR_CLASSE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SocieteParClasseCle";
    	public static final String STATUT = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache";
    	public static final String TABLE_VALEUR = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache";
    	public static final String TABLE_VALEUR_ABREVIATION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleAbreviationSQLListeCache";
    	public static final String TABLE_VALEUR_DISCRIMINANT_REQUIS = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleDiscriminantRequisSQLListeCache";
    	public static final String TYPE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache";
    	public static final String TYPE_ACTIVITE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeActiviteCleListeCache";
    	public static final String TYPE_ACTION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeActionCleListeCache";
    	public static final String TYPE_AGE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeAgeCleListeCache";
    	public static final String TYPE_CATEGORIE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TypeCategorieCle";
    	public static final String TYPE_INTERVENTION = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.TypeInterventionCle";
    	public static final String VILLE = "com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache";
    }

    public static class Categorie{
    	public static final String AUTOEXCLUSION = "112";
    	public static final String AE_ESPACEJEUX = "26722";
    	public static final String REPERAGE_AUTOEXCLUSION = "220";
    	public static final String REPERAGE_ACCES_INTERDIT = "239";
    	public static final String REPERAGE_AVIS_GUET = "263";
    	public static final long COMITE_VIGILANCE = 539;
        
    }

    public static class CategorieClientMystere{
        public static final long CONFORME_VISITE_1 = 23667;
        public static final long CONFORME_VISITE_2 = 653;
        public static final long CONFORME_VISITE_3 = 654;
        public static final long CONFORME_VISITE_4 = 655;
        public static final long CONFORME_VISITE_5 = 656;
        public static final long ECHANTILLON = 662;
        public static final long RETRAIT_PROCESSUS = 657;
        public static final long GESTION_DETAILLANT = 27769;
        public static final long INFRACTION_VISITE_1 = 23668;
        public static final long INFRACTION_VISITE_2 = 23669;
        public static final long INFRACTION_VISITE_3 = 23670;
        public static final long INFRACTION_VISITE_4 = 23735;
        public static final long INFRACTION_VISITE_5 = 24863;        
    }

    public static class BooleanString{
    	public static final String TRUE = "true";
    	public static final String FALSE = "false";
    }

    public static class AutoCompleterClass{
    	public static final String LISTE_CACHE_STRING = "listeCacheString";
    	
    	public static final String DESCRIPTIF_DOSSIER_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.DescriptifDossierAutoCompleter";
    	public static final String FOURNISSEUR_CONSIGNATION_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.FournisseurConsignationAutoCompleter";
    	public static final String REFERENCE1_DOSSIER_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.Reference1DossierAutoCompleter";
    	public static final String REFERENCE2_DOSSIER_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.Reference2DossierAutoCompleter";
    	public static final String REFERENCE3_DOSSIER_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.Reference3DossierAutoCompleter";
    	public static final String MARQUE_CONSIGNATION_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.MarqueConsignationAutoCompleter";
    	public static final String MODELE_CONSIGNATION_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.ModeleConsignationAutoCompleter";
    	public static final String NOM_SUJET_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.NomSujetAutoCompleter";
    	public static final String NOM_SOCIETE_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.NomSocieteAutoCompleter";
    	public static final String NOM_BILLET_AUTO_COMPLETER = "com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.NomBilletAutoCompleter";
    }

    public static class SensTri{
    	public static final String ASC = "asc";
    	public static final String DESC = "desc";
    }

    public static class Operateur{
    	public static final String ET = "et";
    	public static final String OU = "ou";
    }
    public static class RechercheDossier{
    	public static final String GENRE = "genre";
    	public static final String NATURE = "nature";
    	public static final String TYPE = "type";
    	public static final String CATEGORIE = "categorie";
    }


    public static class SQL{
    	public final static String TRUE = "yes";
    	public final static String FALSE = "no";
    	public final static String FALSE_FIXE = "no ";
    	public final static String OUI = "OUI";
    	public final static String NON = "NON";
    }


    public static class Origine{
    	public final static String ENQUETE = "534";
    }

    public static class Rapport{
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_ACTIVITE_QUOTIDIEN = "repertoire.sauvegarde.rapport.activite.quotidien";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_COHERENCE = "repertoire.sauvegarde.rapport.coherence";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_EPURATION = "repertoire.sauvegarde.rapport.epuration";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_VERIFICATION_LIVRETS = "repertoire.sauvegarde.rapport.verification.livrets";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_CHANGEMENTS_DETAILLANTS = "repertoire.sauvegarde.rapport.changements.detaillants";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_RECONNAISSANCE_PLAQUES = "repertoire.sauvegarde.rapport.reconnaissance.plaques";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_LAISSEZ_PASSER = "repertoire.sauvegarde.rapport.laissez.passer";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_VERIFICATION_CLIENT_MYSTERE = "repertoire.sauvegarde.rapport.verification.client.mystere";
    	public final static String REPERTOIRE_SAUVEGARDE_RAPPORT_CLIENT_MYSTERE = "repertoire.sauvegarde.rapport.client.mystere";
    }

    /**
     * Inner Class qui contient les valeurs
     * par d�faut des types d'intervention
     *
     */
    public static class TypesIntervention {
        public static final String NASDouble ="ND"; //Num�ro d'assurance sociale en double
        public static final String NoDoubleDepart ="NOD"; //Num�ro d'employ� en double avec d�part.
        public static final String NASDoubleDepart ="NDD"; //Num�ro d'assurance sociale en double avec d�part
        public static final String SujetDouble ="SD"; //Sujet en double
        public static final String SujetDoubleDepart ="SDD"; //Sujet en double avec d�part
        public static final String DossiersDouble ="DAD"; //Dossiers actifs en double
        public static final String Depart ="DEP"; //D�part
        public static final String DossierActifDepart ="DDA"; //D�part avec dossier actif
        public static final String EmbaucheSansEnquete ="ESE"; //Embauche sans enqu�te
        public static final String Conge ="CON"; //D�part en cong�
        public static final String Embauche ="EMB"; //Embauche
        public static final String Fin ="FIN"; //Pour aviser un intervenant de la fin du traitement.
        public static final String Fin_Prevention_Sans_Courriel ="FPSC"; //Pour aviser un que le traitement c'est bien pass�, m�me si aucun courriel n'a �t� envoy�.
        public static final String Suivi ="SUIVI"; //Pour aviser un intervenant de l'Universit� Laval d'une demande de suivi de la part d'un autoexclu.
        public static final String Aide ="AIDE"; //Pour aviser un intervenant de l'Universit� Laval d'une demande d'aide de la part d'un autoexclu..
        public static final String RAQ ="RAQ"; //Pour aviser que leur transmettre le rapport d'activit� quotidien
        public static final String RAQ_LQ_EVENEMENT_DCSI ="RAQED"; //Pour aviser que leur transmettre le rapport d'activit� quotidien pour les �v�nements DCSI
        public static final String RAQ_LQ_SANS_EVENEMENT_DCSI ="RQSED"; //Pour aviser que leur transmettre le rapport d'activit� quotidien pour TOUT sauf les �v�nements DCSI
        public static final String ERREUR_SECURITE ="ERS"; //Pour aviser qu'il y a eu une erreur de s�curit�
        public static final String COURRIEL_EXCEPTION ="CEXC"; //Pour aviser qu'il y a eu une exception
        public static final String Coherence ="COH"; //Pour aviser du rapport sur la coh�rence des donn�es
        public static final String Readmission ="READ"; //Pour aviser des clients pour lesquels on doit �valuer la r�admission.
        public static final String VerificationLivrets ="VERL"; //Pour aviser du rapport sur la v�rification des livrets
        public static final String ChangementsDetaillants ="CHGT"; //Pour aviser du rapport sur les changements aux d�taillants de clients myst�res
        public static final String VerificationClientMystere ="VERCM"; //Pour aviser du rapport sur la v�rification du client myst�re
        public static final String DemandesIncompletes1 ="DI1"; //Premier avis pour les demandes incompl�tes
        public static final String DemandesIncompletes2 ="DI2"; //Deuxi�me avis pour les demandes incompl�tes
        public static final String DemandesIncompletes3 ="DI3"; //Troisi�me avis pour les demandes incompl�tes
    }

    public static class Securite{
    	public final static String ERREUR_COURRIEL = "Erreur de s�curit�";
    	public final static String SESSION_RACCOURCIT_GESTION_ACCES_SECURITE = "raccourcitGestionAccesSecurite";
    }

    public static class TypeRue{
    	public final static String RUE = "24400";
    }

    public static class Courriel{
    	public final static String FIN_TRAITEMENT_SANS_COURRIEL = "AIDE / SUIVI";
    }

    public static class Mouvements {
        public static final String Embauche = "Embauche";
        public static final String Depart ="D�part";
        public static final String Conge ="Cong�";
    }

    /**
     * Inner Class qui contient les valeurs
     * des types d'emploi. Ces types correspondent aux codes
     * utilis�s dans l'entrep�t de donn�es.
     *
     */
    public static class Emplois {
        public static final String TempsComplet = "TC";
        public static final String TempsCompletTemporaire ="TCT";
        public static final String TempsPartiel ="TP";
        public static final String TempsPartielTemporaire ="TPT"; //�quivalent de sunum�raire ou occasionnel
        public static final String TC = "temps complet";
        public static final String TCT ="temps complet temporaire";
        public static final String TP ="temps partiel";
        public static final String TPT ="temps partiel temporaire"; //�quivalent de sunum�raire ou occasionnel

    }

    /**
     * Inner Class qui contient les valeurs
     * des cat�gories de dossiers li�s aux mouvements.
     * Ces cat�gories sont utilis�s pour la cr�ation de dossiers.
     *
     */
    public static class Categories {
        public static final long CongeTC = 22375;
        public static final long CongeTCT = 22376;
        public static final long CongeTP = 22377;
        public static final long CongeTPT = 22378;
        public static final long DepartTC = 22379;
        public static final long DepartTCT = 22381;
        public static final long DepartTP = 22380;
        public static final long DepartTPT = 22382;
        public static final long EmbaucheTC = 22383;
        public static final long EmbaucheTCT = 22384;
        public static final long EmbaucheTP = 22385;
        public static final long EmbaucheTPT = 22386;

    }

    public static class Action{
    	public static final String INSERER = "I";
    	public static final String MODIFIER = "U";
    	public static final String SUPPRIMER = "D";
    	public static final String SELECTION = "S";
    	public static final String RECHERCHE = "R";
    	public static final String LIAISON = "L";
    }

    public static class TableValeur{
    	public static final String ACTIONS = "ActionSecurite";
    	public static final String CONFIDENTIALITE = "Confidentialite";
    	public static final String ECRAN = "Ecran";
    	public static final String ENTITE = "Entite";
    	public static final String JEU_TYPE = "JeuType";
    	public static final String JEUX = "Jeux";
    	public static final String GABARIT_NARRATION = "GabaritNarration";
    	public static final String GENRE = "Genre";
    	public static final String NATURE = "Nature";
    	public static final String NIVEAU_HIERARCHIQUE = "NiveauHierarchique";
    	public static final String ORIGINE = "Origine";
    	public static final String PERIODE = "Periode";
    	public static final String RAPPORT_DOSSIER = "RapConsultDossier";
    	public static final String RAPPORT_RECHERCHE_DOSSIER = "RapportRechDossier";
    	public static final String RAPPORT_LISTE_RECHERCHE_DOSSIER = "RapportListeRechDo";
    	public static final String RAPPORT_RECHERCHE_SUIVI = "RapportRechSuivi";
    	public static final String RAPPORT_RECHERCHE_JOURNAL = "RapportRechJournal";
    	public static final String RAPPORT_RECHERCHE_SOCIETE = "RapportRechSociete";
    	public static final String RAPPORT_RECHERCHE_SUJET = "RapportRechSujet";
    	public static final String RAPPORT_RECHERCHE_VEHICULE = "RapportRechVehicule";
    	public static final String RAPPORT_REGROUPEMENT = "RapportRegroupement";
    	public static final String RAPPORT_CONSIGNATION = "RapportConsignation";
    	public static final String ROLE_LIAISON = "RoleLiaison";
    	//Liste des r�les de liaison pour les onglets Relation (sujet � sujet et soci�t� � soci�t�)
    	public static final String ROLE_LIAISON_RELATION = "RoleLiaisonRelation";
    	public static final String SECTEUR = "Secteur";
    	public static final String SOUS_SECTEUR = "SousSecteur";
    	public static final String SITE = "Site";
    	public static final String TABLE_VALEUR = "TableValeur";
    	public static final String TYPE_CONSIGNATION = "TypeConsignation";
    	public static final String TYPE_LOTERIE = "TypeLoterie";
    	public static final String TYPE_MISE = "TypeMise";
    	public static final String TYPE_ACTIVITE = "TypeActivite";
    	public static final String TYPE_JEU = "TypeJeu";
    	public static final String DOSSIER = "RapportDossier"; //Pour les dossiers avec inscriptions
    	public static final String ETAT = "Etat"; //�tats d'esprit du client pour le comit� de vigilance
    	public static final String PROPOS = "Propos"; //propos du client pour le comit� de vigilance
    	public static final String MOTIF = "Motif"; //Motif d'intervention des ambulances (Services d'urgence)
    	public static final String METHODE_ECHANTILLONNAGE = "Echantillonnage"; //M�thode d'�chantillonnage des d�taillants RDD
    	public static final String PERIODE_TELEPHONE = "PeriodeTelephone"; //P�riodes souhait�es pour les t�l�phones
    	public static final String CLASSE_URGENCE = "ClasseUrgence"; //Classes pour les services d'urgence
    
    }

    public static class Configuration{
    	public static final String SERVICES_CACHE = "services.cache.cardex";
    	public static final String SERVICES_VIDER_CACHE_LISTE = "servicesVIDERCacheListe";
    	public static final String SERVICES_CACHE_NUMERO_SEQUENCE_DOSSIER = "servicesNumeroSequenceDossier";
    	public static final String APPLICATION = "app.name";
    	
    	public static final String DATASOURCE = "app.datasource";
    	public static final String DATASOURCE_REMPHOR = "app.datasource.remphor";
    	public final static String REPERTOIRE_LOG = "repertoire.log";
    }

    public static class NombreEnregistrementRetourneRecherche{
    	public static final Integer RECHERCHE_ADRESSE_SOCIETE = 500;
    	public static final Integer RECHERCHE_ADRESSE_SUJET = 5000;
    	public static final Integer RECHERCHE_APPROBATION = 500;
    	public static final Integer RECHERCHE_BILLET = 2000;
    	public static final Integer RECHERCHE_CONSIGNATION = 500;
    	public static final Integer RECHERCHE_DOSSIER = 2000;
    	public static final Integer RECHERCHE_GALERIE = 5000;
    	public static final Integer RECHERCHE_JOURNAL = 1000;
    	public static final Integer RECHERCHE_MANDAT_PSU = 1500;
    	public static final Integer RECHERCHE_NARRATION = 500;
    	public static final Integer RECHERCHE_SUIVI = 500;
    	public static final Integer RECHERCHE_SUJET = 5000;
    	public static final Integer RECHERCHE_SOCIETE = 5000;
    	public static final Integer RECHERCHE_VEHICULE = 1000;
    	public static final Integer RECHERCHE_URGENCE = 500;

    }

    public static class SecuriteRoleAdhoc{
    	public static final String DOSSIER_SUJETS_ONGLET = "cardex.dossier.sujets.onglet";
    	public static final String DOSSIER_INSCRIPTIONS_ONGLET = "cardex.dossier.inscriptions.onglet";
    	public static final String DOSSIER_PHOTOS_ONGLET = "cardex.dossier.photos.onglet";
    	public static final String DOSSIER_SUIVIS_ONGLET = "cardex.dossier.suivis.onglet";
    	public static final String DOSSIER_NARRATIONS_ONGLET = "cardex.dossier.narrations.onglet";
    	public static final String DOSSIER_BILLETS_ONGLET = "cardex.dossier.billets.onglet";
    	public static final String DOSSIER_PIECES_ONGLET = "cardex.dossier.pieces.onglet";
    	public static final String DOSSIER_RELATIONS_ONGLET = "cardex.dossier.dossiers.onglet";
    	public static final String DOSSIER_JEUX_ONGLET = "cardex.dossier.jeux.onglet";
    	public static final String DOSSIER_VEHICULES_ONGLET = "cardex.dossier.vehicules.onglet";
    	public static final String DOSSIER_SOCIETES_ONGLET = "cardex.dossier.societes.onglet";
    	public static final String DOSSIER_CONSIGNATIONS_ONGLET = "cardex.dossier.consignation.onglet";
    	public static final String DOSSIER_SOUSCATEGORIES_ONGLET = "cardex.dossier.souscategories.onglet";
    	public static final String DOSSIER_PARTAGE_ONGLET = "cardex.dossier.partage.onglet";
    	public static final String DOSSIER_RESULTAT_VEHICULES_ONGLET = "cardex.dossier.onglet.vehicules.liste.resultat";
    	public static final String DOSSIER_RESULTAT_SUJETS_ONGLET = "cardex.dossier.onglet.sujets.liste.resultat";
    	public static final String DOSSIER_RESULTAT_IMPRIMER = "cardex.recherche.dossiers.imprimer";
    	public static final String DOSSIER_EVALUATIONS_ONGLET = "cardex.dossier.evaluations.onglet";
    	public static final String DOSSIER_URGENCE_ONGLET = "cardex.dossier.urgence.onglet";

    	public static final String SOCIETE_NARRATIONS_ONGLET = "cardex.societe.narrations.onglet";
    	public static final String SOCIETE_DOSSIERS_ONGLET = "cardex.societe.dossiers.onglet";
    	public static final String SOCIETE_SUJETS_ONGLET = "cardex.societe.sujets.onglet";
    	public static final String SOCIETE_RELATIONS_ONGLET = "cardex.societe.relations.onglet";
    	public static final String SOCIETE_ADRESSES_ONGLET = "cardex.societe.adresses.onglet";
    	public static final String SOCIETE_PH0TOS_ONGLET = "cardex.societe.photos.onglet";
    	public static final String SOCIETE_VEHICULES_ONGLET = "cardex.societe.vehicules.onglet";
    	public static final String SOCIETE_PROPRIETAIRES_ONGLET = "cardex.societe.proprietaires.onglet";
    	public static final String SOCIETE_RESULTAT_IMPRIMER = "cardex.recherche.societes.imprimer";

    	public static final String DOSSIER_RETIRER = "cardex.dossier.reactiver";
    	public static final String SUJET_ADRESSES_ONGLET = "cardex.sujet.adresses.onglet";
    	public static final String SUJET_NARRATIONS_ONGLET = "cardex.sujet.narrations.onglet";
    	public static final String SUJET_DOSSIERS_ONGLET = "cardex.sujet.dossiers.onglet";
    	public static final String SUJET_SOCIETES_ONGLET = "cardex.sujet.societes.onglet";
    	public static final String SUJET_RELATIONS_ONGLET = "cardex.sujet.relations.onglet";
    	public static final String SUJET_CARACTERISTIQUES_ONGLET = "cardex.sujet.caracteristiques.onglet";
    	public static final String SUJET_PHOTOS_ONGLET = "cardex.sujet.photos.onglet";
    	public static final String SUJET_VEHICULES_ONGLET = "cardex.sujet.vehicules.onglet";
    	public static final String SUJET_RESULTAT_SUJETS_ONGLET = "cardex.sujet.onglet.vehicules.liste.resultat";
    	public static final String SUJET_RESULTAT_IMPRIMER = "cardex.recherche.sujets.imprimer";
    	public static final String SUJET_ADRESSE_EMPLOYE = "cardex.adresse.employe.onglet";

    	public static final String URGENCE_RESULTAT_IMPRIMER = "cardex.recherche.urgences.imprimer";
    	
    	public static final String VEHICULE_NARRATIONS_ONGLET = "cardex.vehicule.narrations.onglet";
    	public static final String VEHICULE_DOSSIERS_ONGLET = "cardex.vehicule.dossiers.onglet";
    	public static final String VEHICULE_SUJETS_ONGLET = "cardex.vehicule.sujets.onglet";
    	public static final String VEHICULE_SOCIETES_ONGLET = "cardex.vehicule.societes.onglet";
    	public static final String VEHICULE_PARTICULARITES_ONGLET = "cardex.vehicule.particularites.onglet";
    	public static final String VEHICULE_PHOTOS_ONGLET = "cardex.vehicule.photos.onglet";
    	public static final String VEHICULE_RESULTAT_IMPRIMER = "cardex.recherche.vehicules.imprimer";
    }

    public static class Ecran{
    	public static final String AUTRES = "423"; //sert � regrouper tous les r�les Autres
		  //et ceux de la table des valeurs.	
    }
    
    public static class ActionSecurite{
    	public static final String AJOUT = "122";
    	public static final String AUTRES = "423"; //sert � regrouper tous les r�les Autres
		  //et ceux de la table des valeurs.
    	public static final String CONSULTER_NARRATION = "420";
    	public static final String MODIFICATION = "124";
    	public static final String RECHERCHE = "126";
    	public static final String RECHERCHE_DOSSIER = "419";
    	public static final String CONSULTER_DOSSIER = "563";
    	public static final String RECHERCHE_GALERIE = "421";
    	public static final String CONSULTER_GALERIE = "564";
    	public static final String RECHERCHE_PILOTAGE = "422";
    	public static final String SELECTION = "125";
    	public static final String SUPPRESSION = "123";
    	public static final String TOUT = "%"; //Pour obtenir tous les codes.
    	public static final String TOUTES_ACTIONS = "120";
    }

    public static class Secteur{
    	public static final String Admin = "210";
    	public static final String AGENT_SECURITE = "230";
    	public static final String GESTIONNAIRE_SECURITE = "268";
    	public static final String SUPERVISEUR_SECURITE = "242";
    	public static final String TECHNICIEN_SURVEILLANCE = "231";
    }

    public static class Application{
    	public static final String CARDEX = "cardex";
    	public static final String PILOTAGE = "pilotage";
    }

    public static class Onglet{
    	public static final String SUJET = "#TAB_INDIVIDUALS";
    	public static final String INSCRIPTION = "#TAB_INSCRIPTIONS";
    	public static final String PHOTOS = "#TAB_PHOTOS";
    	public static final String SUIVI = "#TAB_FOLLOW_UP";
    	public static final String NARRATION = "#TAB_NARRATION";
    	public static final String BILLET = "#TAB_BILLET";
    	public static final String PIECES_JOINTES = "#TAB_JOINED_PC";
    	public static final String DOSSIER = "#TAB_FOLDERS";
    	public static final String JEUX = "#TAB_GAMES";
    	public static final String VEHICULES = "#TAB_VEHICLES";
    	public static final String SOCIETIES = "#TAB_SOCIETIES";
    	public static final String CONSIGNATION = "#TAB_CONSIGNATION";
    	public static final String SOUS_CATEGORIES = "#TAB_SOUS_CATEGORIES";
    	public static final String PARTAGE = "#TAB_PARTAGE";
    	public static final String PARTICULARITES = "#TAB_PARTICULARITES";
    	public static final String ADRESSE = "#TAB_ADDRESS";
    	public static final String PROPRIETAIRES = "#TAB_PROPRIETAIRES";
    	public static final String CARACTERISTICS = "#TAB_CARACTERISTICS";
    	public static final String EVALUATIONS = "#TAB_EVALUATIONS";
    	public static final String URGENCE = "#TAB_URGENCE";

    }

    public static class Image{
    	public final static int MAX_THUMBNAIL_WIDTH = 320;
    	public final static int MAX_THUMBNAIL_HEIGHT = 240;
    	public final static String EXTENTION_IMAGE = "JPG-JPEG-GIF-TIF-TIFF-PEF-PNG-BMP-DJVU-RAW";
    	public final static String EXTENTION_IMAGE_COMPRESSION_ACCEPTE = "JPG-JPEG-GIF-PEF-PNG-BMP-DJVU-RAW";
    }

    public static class Tables{
    	public final static String SU_SUJET = "SU_SUJET";
    	public final static String SO_SOCIETE = "SO_SOCIETE";
    }
    
    public static class ClientMystereInfractions{
        public final static String FORMATION = "Formation";
        public final static String AVIS_FORMEL = "Avis formel";
        public final static String SUSPENSION_15 = "Suspension 15 jours";
        public final static String SUSPENSION_30 = "Suspension 30 jours";
        public final static String RETRAIT_PERMIS = "Retrait du permis";
    }

    public static class ClientMystereDistricts{
        public final static String DISTRICT_21 = "21";
        public final static String DISTRICT_22 = "22";
        public final static String DISTRICT_23 = "23";
        public final static String DISTRICT_67 = "67";
        public final static String DISTRICT_98 = "98";
    }

    /**
     * Constantes pour le niveau hi�rarchique par d�faut d'un intervenant.
     *
     * @author $Author: Fran�ois Gu�rin $
     */
    public static class Hierarchie {

        public static final String CODE_AUTORITE ="379";
    }

    public static class Poste {

        public static final long DEFAUT = 19835;
    }    

    public static class TypeTelephone {

        public static final long AFFAIRE = 23931;
        public static final long FAX = 23936;
        public static final long SANS_FRAIS = 23932;
    }    

    public static class TypeSortieServlet{
    	public static final String PDF = "application/pdf";
    	public static final String EXCEL = "application/vnd.ms-excel";
    }
    
    public static class Suivi{
    	public static final int DELAI_SQ = 5;
    	public static final int DELAI_RACJ = 1;
    }
    
    public static class GenrePartage{
    	public static final String RESTREINT = "RES";
    	public static final String OUVERT = "OUV";
    	public static final String RESTREINT_LONG = "Restreint";
    	public static final String OUVERT_LONG = "Ouvert";
    }

    // Valeur des gabarits dans les narrations.
    public static class GabaritNarration{
    	public static List<Long> ExclureGabarit = new ArrayList<Long>(Arrays.asList(
    			GlobalConstants.GabaritNarration.VIGILANCE_1_RESUME_ANALYSE,
    			GlobalConstants.GabaritNarration.VIGILANCE_2_ACTIONS_PROPOSEES,
    			GlobalConstants.GabaritNarration.VIGILANCE_3_DECISION_DU_COMITE,
    			GlobalConstants.GabaritNarration.VIGILANCE_4_ACTIONS_PRISES,
    			GlobalConstants.GabaritNarration.VIGILANCE_5_FERMETURE_DOSSIER,
    			GabaritNarration.VIGILANCE_DEMANDE_REINTEGRATION_CLIENT,
    			GabaritNarration.VIGILANCE_SUIVI
    		));
    	
    	
    	public static final long RAPPORT_ACTIVITE_QUOTIDIEN = 1336;
    	
    	public static final long VIGILANCE_1_RESUME_ANALYSE = 1344;
    	public static final long VIGILANCE_2_ACTIONS_PROPOSEES = 1345;
    	public static final long VIGILANCE_3_DECISION_DU_COMITE = 1346; 
    	public static final long VIGILANCE_4_ACTIONS_PRISES = 1347;
    	public static final long VIGILANCE_5_FERMETURE_DOSSIER = 1348;

    	public static final long VIGILANCE_SUIVI = 1349;
    	public static final long VIGILANCE_DEMANDE_REINTEGRATION_CLIENT = 1350;

    }
    
    
}


