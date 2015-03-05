package com.lotoquebec.cardex.presentation.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

import com.lotoQuebec.correcteurAdresse.AdresseSortie;
import com.lotoQuebec.correcteurAdresse.AdresseSortieRecherche;
import com.lotoQuebec.correcteurAdresse.config.ConstantValidationAdresse;
import com.lotoquebec.cardex.business.Acces;
import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.CriteresRechercheConsignation;
import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.CriteresRechercheJournal;
import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardex.business.CriteresRecherchePSUMandat;
import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.business.CriteresRechercheSociete;
import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardex.business.CriteresRechercheSujet;
import com.lotoquebec.cardex.business.CriteresRechercheUrgence;
import com.lotoquebec.cardex.business.CriteresRechercheVehicule;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.FrequenceVisites;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Journal;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.PSUMandat;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.RapportConsignation;
import com.lotoquebec.cardex.business.RapportJournal;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheBilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRecherchePhotoVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.presentation.controller.util.narration.SujetNarration;
import com.lotoquebec.cardex.presentation.model.AccesHtmlForm;
import com.lotoquebec.cardex.presentation.model.AdresseHtmlForm;
import com.lotoquebec.cardex.presentation.model.CaracteristiquesHtmlForm;
import com.lotoquebec.cardex.presentation.model.ConsignationActionPSUHtmlForm;
import com.lotoquebec.cardex.presentation.model.ConsignationHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheConsignationHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheDossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheJournalHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheNarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRecherchePSUMandatHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRecherchePhotoHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheSocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheSuiviHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheSujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheUrgenceHtmlForm;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheVehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EvaluationHtmlForm;
import com.lotoquebec.cardex.presentation.model.FrequenceVisitesHtmlForm;
import com.lotoquebec.cardex.presentation.model.InscriptionHtmlForm;
import com.lotoquebec.cardex.presentation.model.JeuxHtmlForm;
import com.lotoquebec.cardex.presentation.model.JournalHtmlForm;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.PSUMandatHtmlForm;
import com.lotoquebec.cardex.presentation.model.PartageHtmlForm;
import com.lotoquebec.cardex.presentation.model.ParticularitesHtmlForm;
import com.lotoquebec.cardex.presentation.model.PhotoHtmlForm;
import com.lotoquebec.cardex.presentation.model.RapportConsignationHtmlForm;
import com.lotoquebec.cardex.presentation.model.RapportJournalHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SuiviHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.UrgenceHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.form.AdresseRechercheForm;
import com.lotoquebec.cardex.presentation.model.form.BilletForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheBilletForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.FrequenceVisitesForm;
import com.lotoquebec.cardex.presentation.model.form.MiseEvaluationForm;
import com.lotoquebec.cardex.presentation.model.form.PostCopy;
import com.lotoquebec.cardex.presentation.model.util.NumeroDossierDossierFormComparator;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PaysCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeRueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.VilleCleMultiExterneListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CardinaliteAbreviationCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.ProvinceAbreviationCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.UniteAbreviationCle;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.presentation.util.LabelValue;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.text.CurrencyFormat;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.LongUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;



/**
 * Méthodes utilitaires pour convertir(mettre en correspondance)
 * les value objects et les action form de l'application cardex.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.71 $, $Date: 2002/05/01 20:25:21 $
 */
public class ValueObjectMapper {

    /**
     * Constructeur de ValueObjectMapper par défaut.
     */
    private ValueObjectMapper() {}

    /**
     * L'instance du gestionnaire de journalisation.
     */
    private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Convertis une structure Dossier représentant le dossier dans le model
     * d'affaire de l'application, en une structure DossierHtmlForm,
     * représentant les informations du formulaire de dossier.
     *
     * @param dossier Structure contenant les informations du dossier.
     * @param form Structure contenant les informations relative au formulaire
     * de dossier.
     */
    public static void convertDossier(Dossier dossier, DossierForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(dossier);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("numeroCardex");
            map.remove("dateDebut");
            map.remove("dateFin");
            map.remove("dateRapportee");
            map.remove("dateAssignation");
            map.remove("dateEvenement");
            map.remove("dateChangement");
            map.remove("dateCreation");
            map.remove("lienDateCreation");
            map.remove("jeux");
            String formulaire = form.getFormulaire();
            BeanUtils.populate(form, map);
            form.assignerEGNTC(dossier.getEntite(), dossier.getGenre(), dossier.getNature(), dossier.getType(), dossier.getCategorie());
            form.setFormulaire(formulaire);
            form.setNumeroCardex(dossier.getNumeroCardex());
            form.setDateDebut(TimestampFormat.format(
                    dossier.getDateDebut(), locale, true));
            form.setDateFin(TimestampFormat.format(dossier.getDateFin(),
                    locale, true));
            form.setDateRapportee(TimestampFormat.format(
                    dossier.getDateRapportee(), locale, true));
            form.setDateAssignation(TimestampFormat.format(
                    dossier.getDateAssignation(), locale, true));
            form.setDateEvenement(TimestampFormat.format(
                    dossier.getDateEvenement(), locale, true));
            form.setDateChangement(TimestampFormat.format(
                    dossier.getDateChangement(), locale, true));
            form.setDateCreation(TimestampFormat.format(
                    dossier.getDateCreation(), locale, true));
            form.setLienDateCreation(TimestampFormat.format(
                    dossier.getLienDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure FrequenceVisites représentant le dossier dans le model
     * d'affaire de l'application, en une structure FrequenceVisitesHtmlForm,
     * représentant les informations du formulaire de dossier.
     *
     * @param dossier Structure contenant les informations du dossier.
     * @param form Structure contenant les informations relative au formulaire
     * de dossier.
     */
    public static void convertFrequenceVisites(FrequenceVisites frequenceVisites, FrequenceVisitesForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(frequenceVisites);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            BeanUtils.populate(form, map);
            form.setDateCreation(TimestampFormat.format(
            		frequenceVisites.getDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Consignation représentant le dossier dans le model
     * d'affaire de l'application, en une structure ConsignationHtmlForm,
     * représentant les informations du formulaire de Consignation.
     *
     * @param dossier Structure contenant les informations de Consignation.
     * @param form Structure contenant les informations relative au formulaire
     * de Consignation.
     */
    public static void convertConsignation(Consignation consignation, ConsignationHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(consignation);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateApprobation");
			map.remove("montant");
			map.remove("prix");
			map.remove("dossier");
            BeanUtils.populate(form, map);
            form.setDateCreation(TimestampFormat.format(
                    consignation.getDateCreation(), locale, true));
            form.setDateModification(TimestampFormat.format(consignation.getDateModification(),
                    locale, true));
            form.setDateApprobation(TimestampFormat.format(
                    consignation.getDateApprobation(), locale, true));
			form.setMontant(CurrencyFormat.format(consignation.getMontant(),locale));
			form.setPrix(CurrencyFormat.format(consignation.getPrix(),locale));
		} catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Journal représentant le dossier dans le model
     * d'affaire de l'application, en une structure JournalHtmlForm,
     * représentant les informations du formulaire de dossier.
     *
     * @param dossier Structure contenant les informations du dossier.
     * @param form Structure contenant les informations relative au formulaire
     * de dossier.
     */
    public static void convertJournal(Journal journal, JournalHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(journal);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("numeroDossier");
            map.remove("dateDebut");
            map.remove("dateFin");
            map.remove("dateCreation");
            BeanUtils.populate(form, map);
            form.setNumeroDossier(journal.getNumeroDossier());
            form.setDateDebut(TimestampFormat.format(
                    journal.getDateDebut(), locale, true));
            form.setDateFin(TimestampFormat.format(journal.getDateFin(),
                    locale, true));
            form.setDateCreation(TimestampFormat.format(journal.getDateCreation(),
                    locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Journal représentant le dossier dans le model
     * d'affaire de l'application, en une structure JournalHtmlForm,
     * représentant les informations du formulaire de dossier.
     *
     * @param dossier Structure contenant les informations du dossier.
     * @param form Structure contenant les informations relative au formulaire
     * de dossier.
     */
    public static void convertRapportJournal(RapportJournal rapportJournal, RapportJournalHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(rapportJournal);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateDebut");
            map.remove("dateFin");
            BeanUtils.populate(form, map);
            form.setDateDebut(TimestampFormat.format(
                    rapportJournal.getDateDebut(), locale, true));
            form.setDateFin(TimestampFormat.format(rapportJournal.getDateFin(),
                    locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

	/**
	 * Convertis une structure Journal représentant le dossier dans le model
	 * d'affaire de l'application, en une structure JournalHtmlForm,
	 * représentant les informations du formulaire de dossier.
	 *
	 * @param dossier Structure contenant les informations du dossier.
	 * @param form Structure contenant les informations relative au formulaire
	 * de dossier.
	 */
	public static void convertRapportConsignation(RapportConsignation rapportConsignation, RapportConsignationHtmlForm form,
			Locale locale) throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(rapportConsignation);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (NumeroCardex et Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateDebut");
			map.remove("dateFin");
			BeanUtils.populate(form, map);
			form.setDateDebut(TimestampFormat.format(
					rapportConsignation.getDateDebut(), locale, true));
			form.setDateFin(TimestampFormat.format(rapportConsignation.getDateFin(),
					locale, true));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		}
	}

    /**
     * Convertis une structure Sujet représentant le sujet dans le model
     * d'affaire de l'application, en une structure SujetHtmlForm,
     * représentant les informations du formulaire de sujet.
     *
     * @param sujet Structure contenant les informations du sujet.
     * @param form Structure contenant les informations relative au formulaire
     * de sujet.
     */
    public static void convertSujet(Sujet sujet, SujetHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(sujet);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (Timestamp) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("dateNaissance");
            map.remove("dateCreation");
            map.remove("dateChangement");
            map.remove("lienDateCreation");
            map.remove("dateFinEnquete");
            map.remove("dateFinEmploi");
            BeanUtils.populate(form, map);
            form.setDateNaissance(TimestampFormat.format(
                    sujet.getDateNaissance(), locale, false));
            form.setDateCreation(TimestampFormat.format(
                    sujet.getDateCreation(), locale, true));
            form.setDateChangement(TimestampFormat.format(
                    sujet.getDateChangement(), locale, true));
            form.setLienDateCreation(TimestampFormat.format(
                    sujet.getLienDateCreation(), locale, true));
          //log.debug("Sujet converti à HTML : " + form.toString());
            form.setDateNaissance(TimestampFormat.format(
                    sujet.getDateNaissance(), locale, false));
            form.setDateFinEnquete(TimestampFormat.format(
                    sujet.getDateFinEnquete(), locale, false));
            form.setDateFinEmploi(TimestampFormat.format(
                    sujet.getDateFinEmploi(), locale, false));

        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
    
    private String convertDate(Date date){
    	SimpleDateFormat dateFormatterDateJava19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	return dateFormatterDateJava19.format(date);
    }

    /**
     * Convertis une structure Narration représentant la narration dans le
     * model d'affaire de l'application, en une structure NarrationHtmlForm,
     * représentant les informations du formulaire de la narration.
     *
     * @param narration Structure contenant les informations de la narration.
     * @param form Structure contenant les informations relative au formulaire
     * de la narration.
     */
    public static void convertNarration(Narration narration,
            NarrationHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(narration);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp et Dossier) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateApprobation");
            map.remove("dateChangement");
            map.remove("dossier");
            map.remove("montant");
            BeanUtils.populate(form, map);
            form.setDateCreation(TimestampFormat.format(
                    narration.getDateCreation(), locale, true));
            form.setDateModification(TimestampFormat.format(
                    narration.getDateModification(), locale, true));
            form.setDateApprobation(TimestampFormat.format(
                    narration.getDateApprobation(), locale, true));
            form.setDateChangement(TimestampFormat.format(
            		narration.getDateChangement(), locale, true));
            form.setMontant(CurrencyFormat.format(narration.getMontant(),locale));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Photo représentant la photo dans le
     * model d'affaire de l'application, en une structure PhotoHtmlForm,
     * représentant les informations du formulaire de la photo.
     *
     * @param photo Structure contenant les informations de la photo.
     * @param form Structure contenant les informations relative au formulaire
     * de la photo.
     */
    public static void convertPhoto(Photo photo, PhotoHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(photo);
            // L'attributs suivant doit être retiré du map, puisqu'il
            // est un type complexe (Timestamp) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("cardex01");
            map.remove("dossier");
            map.remove("sujetInteretDossiers");
            map.remove("sujet");
            map.remove("image");
            BeanUtils.populate(form, map);
            form.setDateCreation(TimestampFormat.format(
                    photo.getDateCreation()));
            form.setImage(photo.getImage());
            form.getSujetInteretDossiers().clear();
            
            if (photo.getSujetInteretDossiers() != null){
	            for (Dossier dossier:photo.getSujetInteretDossiers()){
	            	DossierForm dossierForm = new DossierForm();
	            	convertDossier(dossier, dossierForm, locale);
	            	form.getSujetInteretDossiers().add(dossierForm);
	            }
	            Collections.sort(form.getSujetInteretDossiers(), new NumeroDossierDossierFormComparator());
            }
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
/*
    public static void convert(SousCategoriesVO sourceSousCategoriesVO, SousCategoriesForm destinationSousCategoriesForm) throws ValueObjectMapperException {
    	destinationSousCategoriesForm.setLien(String.valueOf(sourceSousCategoriesVO.getLien()));
    	destinationSousCategoriesForm.setLienSite(String.valueOf(sourceSousCategoriesVO.getLienSite()));
        
    	for(SousCategorieVO sousCategorieVO: sourceSousCategoriesVO.getSousCategories()){
            SousCategorieForm sousCategorieForm = new SousCategorieForm(sousCategorieVO);
            destinationSousCategoriesForm.getSousCategories().add(sousCategorieForm);
		}
    }

    public static void convert(SousCategoriesForm sourceSousCategoriesForm, SousCategoriesVO destinationSousCategoriesVO) throws ValueObjectMapperException {
    	destinationSousCategoriesVO.setLien(Long.valueOf(sourceSousCategoriesForm.getLien()).longValue());
    	destinationSousCategoriesVO.setLienSite(Long.valueOf(sourceSousCategoriesForm.getLienSite()).longValue());

    	for(LabelValueBean labelValueBean:sourceSousCategoriesForm.getDoubleListe().getDroiteCols()){
    		boolean isTrouve = false;
    		long categorie = Long.valueOf(labelValueBean.getValue());
    		
        	for(SousCategorieForm sousCategorieForm: sourceSousCategoriesForm.getSousCategories()){
        		SousCategorieVO sousCategorieVO = new SousCategorieVO();
                
        		if (categorie == sousCategorieForm.getCle()){
	        		ValueObjectMapper.convert(sousCategorieForm, sousCategorieVO);
	                destinationSousCategoriesVO.getSousCategories().add(sousCategorieVO);
	                isTrouve = true;
	                break;
        		}
    		}
        	
        	if (isTrouve == false){
        		SousCategorieVO sousCategorieVO = new SousCategorieVO();
        		sousCategorieVO.setSousCategorie(categorie);
        		destinationSousCategoriesVO.getSousCategories().add(sousCategorieVO);
        	}
    	}
    }*/
    
    /**
     * Convertis une structure Societe représentant la société dans le
     * model d'affaire de l'application, en une structure SocieteHtmlForm,
     * représentant les informations du formulaire de la société.
     *
     * @param société Structure contenant les informations de la société.
     * @param form Structure contenant les informations relative au formulaire
     * de la société.
     */
    public static void convertSociete(Societe societe, SocieteHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(societe);
            // L'attributs suivant doit être retiré du map, puisqu'il
            // est un type complexe (Timestamp) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("dateDeFondation");
            map.remove("dateCreation");
            map.remove("dateChangement");
            map.remove("dateInactif");
            map.remove("lienDateCreation");
            BeanUtils.populate(form, map);
            form.setDateDeFondation(TimestampFormat.format(
                    societe.getDateDeFondation(), locale, false));
            form.setDateCreation(TimestampFormat.format(
                    societe.getDateCreation(), locale, true));
            form.setDateChangement(TimestampFormat.format(
                    societe.getDateChangement(), locale, true));
            form.setLienDateCreation(TimestampFormat.format(
                    societe.getLienDateCreation(), locale, true));
            form.setDateInactif(TimestampFormat.format(
                    societe.getDateInactif(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Suivi représentant le suivi dans le
     * model d'affaire de l'application, en une structure SuiviHtmlForm,
     * représentant les informations du formulaire du suivi.
     *
     * @param suivi Structure contenant les informations du suivi.
     * @param form Structure contenant les informations relative au formulaire
     * du suivi.
     */
    public static void convertSuivi(Suivi suivi, SuiviHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(suivi);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("datePrevue");
            map.remove("dateCompletee");
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateApprobation");
            map.remove("dateChangement");
            map.remove("dossier");
            BeanUtils.populate(form, map);
            form.setDatePrevue(TimestampFormat.format(
                    suivi.getDatePrevue(), locale, true));
            form.setDateCompletee(TimestampFormat.format(
                    suivi.getDateCompletee(), locale, true));
            form.setDateCreation(TimestampFormat.format(
                    suivi.getDateCreation(), locale, true));
            form.setDateModification(TimestampFormat.format(
                    suivi.getDateModification(), locale, true));
            form.setDateApprobation(TimestampFormat.format(
                    suivi.getDateApprobation(), locale, true));
            form.setDateChangement(TimestampFormat.format(
            		suivi.getDateChangement(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Acces représentant Acces dans le
     * model d'affaire de l'application, en une structure AccesHtmlForm,
     * représentant les informations sur les accès.
     *
     * @param acces Structure contenant les informations des accès.
     * @param form Structure contenant les informations relative à la liste
     * des accès.
     */
    public static void convertAcces(Acces acces, AccesHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(acces);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateAcces");
            map.remove("dateCreation");
            BeanUtils.populate(form, map);
            form.setDateCreation(TimestampFormat.format(
                    acces.getDateCreation(), locale, true));
            form.setDateAcces(TimestampFormat.format(
                    acces.getDateAcces(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
    
    /**
     * Convertis une structure Adresse représentant l'adresse dans le
     * model d'affaire de l'application, en une structure AdresseHtmlForm,
     * représentant les informations du formulaire de l'adresse.
     *
     * @param adresse Structure contenant les informations de l'adresse.
     * @param form Structure contenant les informations relative au formulaire
     * de l'adresse.
     */
    public static void convertAdresse(Adresse adresse, AdresseForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(adresse);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateChangement");
            BeanUtils.populate(form, map);
            form.assignerPPV(adresse.getPays(), adresse.getProvince(), adresse.getVille());
            form.setDateCreation(TimestampFormat.format(
                    adresse.getDateCreation(), locale, true));
            form.setDateModification(TimestampFormat.format(
                    adresse.getDateModification(), locale, true));
            form.setDateChangement(TimestampFormat.format(
            		adresse.getDateChangement(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Caracteristiques représentant les
     * caractéristiques dans le model d'affaire de l'application, en une
     * structure CaracteristiquesHtmlForm, représentant les informations du
     * formulaire des caractéristiques.
     *
     * @param caracteristiques Structure contenant les informations des
     * caractéristiques.
     * @param form Structure contenant les informations relative au formulaire
     * des caractéristiques.
     */
    public static void convertCaracteristiques(CardexAuthenticationSubject subject, Caracteristiques caracteristiques,
            CaracteristiquesHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(caracteristiques);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("caracteristiquesChoisis");
            BeanUtils.populate(form, map);

            form.initDoubleListe(subject, caracteristiques.getCaracteristiquesChoisis());
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (BusinessResourceException e) {
        	throw new ValueObjectMapperException(e);
		}
    }

    /**
     * Convertis une structure Partage dans le model d'affaire de l'application, en une
     * structure PartageHtmlForm, représentant les informations de l'onglet Partage.
     *
     * @param caracteristiques Structure contenant les informations des
     * caractéristiques.
     * @param form Structure contenant les informations relative au formulaire
     * des caractéristiques.
     */
    public static void convertPartage(Partage partage,
    		PartageHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(partage);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("intervenantsChoisis");
            BeanUtils.populate(form, map);
            Collection collection = partage.getIntervenantsChoisis();
            Iterator it = collection.iterator();
            String[] newPartage = new String[collection.size()];
            int i=0;
            while(it.hasNext()){
              String intervenant = (String)it.next();
              newPartage[i] = intervenant;
              i++;
            }
            form.setIntervenantsChoisis(newPartage);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Inscription représentant l'inscription dans le
     * model d'affaire de l'application, en une structure InscriptionHtmlForm,
     * représentant les informations du formulaire de l'inscription.
     *
     * @param inscription Structure contenant les informations de
     * l'inscription.
     * @param form Structure contenant les informations relative au formulaire
     * de l'inscription.
     */
    public static void convertInscription(Inscription inscription,
            InscriptionHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(inscription);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (ArrayList et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("sitesChoisis");
            map.remove("dateInscription");
            map.remove("dateDebut");
            map.remove("dateFin");
            map.remove("dateCourrielAide");
            map.remove("dateCourrielSuivi");
            map.remove("dateRencontreInitiale");
            map.remove("dateRencontreFinale");
            BeanUtils.populate(form, map);
            form.setDateInscription(TimestampFormat.format(
                    inscription.getDateInscription(), locale, false));
            form.setDateDebut(TimestampFormat.format(
                    inscription.getDateDebut(), locale, false));
            form.setDateFin(TimestampFormat.format(
                    inscription.getDateFin(), locale, false));
            form.setDateCourrielAide(TimestampFormat.format(
                    inscription.getDateCourrielAide(), locale, true));
            form.setDateCourrielSuivi(TimestampFormat.format(
                    inscription.getDateCourrielSuivi(), locale, true));
            form.setDateRencontreFinale(TimestampFormat.format(
                    inscription.getDateRencontreFinale(), locale, true));
            form.setDateRencontreInitiale(TimestampFormat.format(
                    inscription.getDateRencontreInitiale(), locale, true));
            Collection collection = inscription.getSitesChoisis();
            Iterator it = collection.iterator();
            String[] newSites = new String[collection.size()];
            int i=0;
            while(it.hasNext()){
              String site = (String)it.next();
              newSites[i] = site;
              i++;
            }
            form.setSitesChoisis(newSites);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure Jeux représentant les jeux dans le
     * model d'affaire de l'application, en une structure JeuxHtmlForm,
     * représentant les informations du formulaire de jeux.
     *
     * @param jeux Structure contenant les informations des jeux.
     * @param form Structure contenant les informations relative au formulaire
     * de jeux.
     */
    public static void convertJeux(CardexAuthenticationSubject subject, Jeux jeux, JeuxHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(jeux);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("jeuxChoisis");
            BeanUtils.populate(form, map);
            
            form.initDoubleListe(subject, jeux.getJeuxChoisis());

        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (BusinessResourceException e) {
			throw new ValueObjectMapperException(e);
		}
    }

    /**
     * Convertis une structure Particularites représentant les particularités
     * dans le model d'affaire de l'application, en une structure
     * ParticularitesHtmlForm, représentant les informations du formulaire des
     * particularités.
     *
     * @param particularites Structure contenant les informations des
     * particularités.
     * @param form Structure contenant les informations relative au formulaire
     * des particularités.
     */
    public static void convertParticularites(CardexAuthenticationSubject subject, Particularites particularites,
            ParticularitesHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(particularites);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("particularitesChoisis");
            BeanUtils.populate(form, map);
            
            form.initDoubleListe(subject, particularites.getParticularitesChoisis());
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (BusinessResourceException e) {
			throw new ValueObjectMapperException(e);
		}
    }

    /**
     * Convertis une structure CriteresRechercheDossier représentant le dossier
     * recherché dans le model d'affaire de l'application, en une structure
     * CriteresRechercheDossierHtmlForm, représentant les informations du
     * formulaire de recherche de dossier.
     *
     * @param criteresDossier Structure contenant les informations du dossier
     * recherché.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de dossier.
     */
    public static void convertCriteresRechercheDossier(
            CriteresRechercheDossier criteresDossier,
            CriteresRechercheDossierHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresDossier);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("numeroCardex");
            map.remove("dateFinDu");
            map.remove("dateDebutAu");
            map.remove("dateFinAu");
            map.remove("dateCreationDu");
            map.remove("dateCreationAu");
            BeanUtils.populate(form, map);
            form.setNumeroCardex(criteresDossier.getNumeroCardex());
            form.setDateDebutDu(TimestampFormat.format(
                    criteresDossier.getDateDebutDu(), locale, false));
            form.setDateFinDu(TimestampFormat.format(
                    criteresDossier.getDateFinDu(), locale, false));
            form.setDateDebutAu(TimestampFormat.format(
                    criteresDossier.getDateDebutAu(), locale, false));
            form.setDateFinAu(TimestampFormat.format(
                    criteresDossier.getDateFinAu(), locale, false));
            form.setDateCreationDu(TimestampFormat.format(
                    criteresDossier.getDateCreationDu(), locale, false));
            form.setDateCreationAu(TimestampFormat.format(
                    criteresDossier.getDateCreationAu(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheSujet représentant le sujet
     * recherché dans le model d'affaire de l'application, en une structure
     * CriteresRechercheSujetHtmlForm, représentant les informations du
     * formulaire de recherche de sujet.
     *
     * @param criteresSujet Structure contenant les informations du sujet
     * recherché.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de sujet.
     */
    public static void convertCriteresRechercheSujet(
            CriteresRechercheSujet criteresSujet,
            CriteresRechercheSujetHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresSujet);
            BeanUtils.populate(form, map);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheNarration représentant la
     * narration recherchée dans le model d'affaire de l'application, en une
     * structure CriteresRechercheNarrationHtmlForm, représentant les
     * informations du formulaire de recherche de la narration.
     *
     * @param criteresNarration Structure contenant les informations de la narration
     * recherchée.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de la narration.
     */
    public static void convertCriteresRechercheNarration(
            CriteresRechercheNarration criteresNarration,
            CriteresRechercheNarrationHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresNarration);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreationDebut");
            map.remove("dateApprobationDebut");
            map.remove("dateCreationFin");
            map.remove("dateApprobationFin");
            BeanUtils.populate(form, map);
            form.setDateCreationDebut(TimestampFormat.format(
                    criteresNarration.getDateCreationDebut(), locale, false));
            form.setDateApprobationDebut(TimestampFormat.format(
                    criteresNarration.getDateApprobationDebut(),
                    locale, false));
            form.setDateCreationFin(TimestampFormat.format(
                    criteresNarration.getDateCreationFin(), locale, false));
            form.setDateApprobationFin(TimestampFormat.format(
                    criteresNarration.getDateApprobationFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRecherchePhoto représentant la
     * photo recherchée dans le model d'affaire de l'application, en une
     * structure CriteresRecherchePhotoHtmlForm, représentant les
     * informations du formulaire de recherche de la photo.
     *
     * @param criteresPhoto Structure contenant les informations de la photo
     * recherchée.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de la photo.
     */
    public static void convertCriteresRecherchePhoto(
            CriteresRecherchePhoto criteresPhoto,
            CriteresRecherchePhotoHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresPhoto);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateAjoutDebut");
            map.remove("dateAjoutFin");
            map.remove("dateValideDebut");
            map.remove("dateValideFin");
            map.remove("dateTermineDebut");
            map.remove("dateTermineFin");
            BeanUtils.populate(form, map);
            form.setDateAjoutDebut(TimestampFormat.format(
                    criteresPhoto.getDateAjoutDebut(), locale, false));
            form.setDateAjoutFin(TimestampFormat.format(
                    criteresPhoto.getDateAjoutFin(), locale, false));
            form.setDateValideDebut(TimestampFormat.format(
                    criteresPhoto.getDateValideDebut(), locale, false));
            form.setDateValideFin(TimestampFormat.format(
                    criteresPhoto.getDateValideFin(), locale, false));
            form.setDateTermineDebut(TimestampFormat.format(
                    criteresPhoto.getDateTermineDebut(), locale, false));
            form.setDateTermineFin(TimestampFormat.format(
                    criteresPhoto.getDateTermineFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheSociete représentant la
     * société recherchée dans le model d'affaire de l'application, en une
     * structure CriteresRechercheSocieteHtmlForm, représentant les
     * informations du formulaire de recherche de la société.
     *
     * @param criteresSociete Structure contenant les informations de la société
     * recherchée.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de la société.
     */
    public static void convertCriteresRechercheSociete(
            CriteresRechercheSociete criteresSociete,
            CriteresRechercheSocieteHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresSociete);
            BeanUtils.populate(form, map);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheSuivi représentant le
     * suivi recherché dans le model d'affaire de l'application, en une
     * structure CriteresRechercheSuiviHtmlForm, représentant les
     * informations du formulaire de recherche du suivi.
     *
     * @param criteresSuivi Structure contenant les informations du suivi
     * recherché.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche du suivi.
     */
    public static void convertCriteresRechercheSuivi(
            CriteresRechercheSuivi criteresSuivi,
            CriteresRechercheSuiviHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresSuivi);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateEmisDebut");
            map.remove("datePrevueDebut");
            map.remove("dateCompleteeDebut");
            map.remove("dateEmisFin");
            map.remove("datePrevueFin");
            map.remove("dateCompleteeFin");
            BeanUtils.populate(form, map);
            form.setDateEmisDebut(TimestampFormat.format(
                    criteresSuivi.getDateEmisDebut(), locale, false));
            form.setDatePrevueDebut(TimestampFormat.format(
                    criteresSuivi.getDatePrevueDebut(), locale, false));
            form.setDateCompleteeDebut(TimestampFormat.format(
                    criteresSuivi.getDateCompleteeDebut(), locale, false));
            form.setDateEmisFin(TimestampFormat.format(
                    criteresSuivi.getDateEmisFin(), locale, false));
            form.setDatePrevueFin(TimestampFormat.format(
                    criteresSuivi.getDatePrevueFin(), locale, false));
            form.setDateCompleteeFin(TimestampFormat.format(
                    criteresSuivi.getDateCompleteeFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure convertCriteresRechercheConsignation représentant le
     * suivi recherché dans le model d'affaire de l'application, en une
     * structure CriteresRechercheConsignationHtmlForm, représentant les
     * informations du formulaire de recherche de Consignation.
     *
     * @param criteresConsignation Structure contenant les informations de Consignation
     * recherché.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de Consignation.
     */
    public static void convertCriteresRechercheConsignation(
            CriteresRechercheConsignation criteresConsignation,
            CriteresRechercheConsignationHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresConsignation);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateDebut");
            map.remove("dateFin");
            BeanUtils.populate(form, map);
            form.setDateDebut(TimestampFormat.format(
                    criteresConsignation.getDateDebut(), locale, false));
            form.setDateFin(TimestampFormat.format(
                    criteresConsignation.getDateFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

	/**
	 * Convertis une structure convertCriteresRechercheConsignation représentant le
	 * suivi recherché dans le model d'affaire de l'application, en une
	 * structure CriteresRechercheConsignationHtmlForm, représentant les
	 * informations du formulaire de recherche de Consignation.
	 *
	 * @param criteresConsignation Structure contenant les informations de Consignation
	 * recherché.
	 * @param form Structure contenant les informations relative au formulaire
	 * de recherche de Consignation.
	 */
	public static void convertCriteresRecherchePSUMandat(
			CriteresRecherchePSUMandat criteresPSUMandat,
			CriteresRecherchePSUMandatHtmlForm form, Locale locale)
			throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(criteresPSUMandat);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateDebut");
			map.remove("dateFin");
			BeanUtils.populate(form, map);
			form.setDateDebut(TimestampFormat.format(
					criteresPSUMandat.getDateDebut(), locale, false));
			form.setDateFin(TimestampFormat.format(
					criteresPSUMandat.getDateFin(), locale, false));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		}
	}

    /**
     * Convertis une structure DossierHtmlForm, représentant les informations
     * du formulaire de dossier, en une structure Dossier représentant le
     * dossier dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de dossier.
     * @param dossier Structure contenant les informations du dossier.
     */
    public static void convertDossierHtmlForm(DossierHtmlForm form,
            Dossier dossier, Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("numeroCardex");
            map.remove("dateDebut");
            map.remove("dateFin");
            map.remove("dateRapportee");
            map.remove("dateAssignation");
            map.remove("dateEvenement");
            map.remove("dateChangement");
            map.remove("dateCreation");
            map.remove("lienDateCreation");
            map.remove("jeux");
            map.remove("ajoutPhoto");
            map.remove("ajoutPieceJointe");
            BeanUtils.populate(dossier, map);
            dossier.setNumeroCardex((form.getNumeroCardex()).toString());
            dossier.setDateDebut(TimestampFormat.parse(form.getDateDebut(),
                    locale, true));
            dossier.setDateFin(TimestampFormat.parse(form.getDateFin(),
                    locale, true));
            dossier.setDateRapportee(TimestampFormat.parse(
                    form.getDateRapportee(), locale, true));
            dossier.setDateAssignation(TimestampFormat.parse(
                    form.getDateAssignation(), locale, true));
            dossier.setDateEvenement(TimestampFormat.parse(
                    form.getDateEvenement(), locale, true));
            dossier.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
            dossier.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            dossier.setLienDateCreation(TimestampFormat.parse(
                    form.getLienDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure FrequenceVisitesHtmlForm, représentant les informations
     * du formulaire de FrequenceVisites, en une structure FrequenceVisites représentant le
     * FrequenceVisites dans le modèle d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * d'évaluation.
     * @param FrequenceVisites Structure contenant les informations du FrequenceVisites.
     */
    public static void convertFrequenceVisitesHtmlForm(FrequenceVisitesHtmlForm form,
    		FrequenceVisites frequenceVisites, Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            BeanUtils.populate(frequenceVisites, map);
            frequenceVisites.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure JournalHtmlForm, représentant les informations
     * du formulaire de dossier, en une structure Journal représentant le
     * dossier dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de dossier.
     * @param dossier Structure contenant les informations du dossier.
     */
    public static void convertJournalHtmlForm(JournalHtmlForm form,
            Journal journal, Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("numeroDossier");
            map.remove("dateDebut");
            map.remove("dateFin");
            map.remove("dateCreation");
            BeanUtils.populate(journal, map);
            journal.setNumeroDossier((form.getNumeroDossier()).toString());
            journal.setDateDebut(TimestampFormat.parse(form.getDateDebut(),
                    locale, true));
            journal.setDateFin(TimestampFormat.parse(form.getDateFin(),
                    locale, true));
            journal.setDateCreation(TimestampFormat.parse(form.getDateCreation(),
                    locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure SujetHtmlForm, représentant les informations
     * du formulaire de sujet, en une structure Sujet représentant le
     * sujet dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de sujet.
     * @param sujet Structure contenant les informations du sujet.
     */
    public static void convertSujetHtmlForm(SujetHtmlForm form, Sujet sujet,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (Timestamp) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("dateNaissance");
            map.remove("dateCreation");
            map.remove("dateChangement");
            map.remove("lienDateCreation");
            map.remove("dateFinEnquete");
            map.remove("dateFinEmploi");
            map.remove("ajoutPhoto");
            BeanUtils.populate(sujet, map);
            sujet.setDateNaissance(TimestampFormat.parse(
                    form.getDateNaissance(), locale, false));
            sujet.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            sujet.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
            sujet.setLienDateCreation(TimestampFormat.parse(
                    form.getLienDateCreation(), locale, true));
            sujet.setDateFinEnquete(TimestampFormat.parse(
                    form.getDateFinEnquete(), locale, false));
            sujet.setDateFinEmploi(TimestampFormat.parse(
                    form.getDateFinEmploi(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure NarrationHtmlForm, représentant les informations
     * du formulaire de narration, en une structure Narration représentant la
     * narration dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de narration.
     * @param narration Structure contenant les informations de la narration.
     */
    public static void convertNarrationHtmlForm(NarrationHtmlForm form,
            Narration narration, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateApprobation");
            map.remove("dateChangement");
            map.remove("dossier");
            map.remove("montant");
            BeanUtils.populate(narration, map);
            narration.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            narration.setDateModification(TimestampFormat.parse(
                    form.getDateModification(), locale, true));
            narration.setDateApprobation(TimestampFormat.parse(
                    form.getDateApprobation(), locale, true));
            narration.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
            narration.setMontant(CurrencyFormat.parse(form.getMontant(),locale));

        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }
    
    /**
     * Convertis une structure PhotoHtmlForm, représentant les informations
     * du formulaire de photo, en une structure Photo représentant la
     * photo dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de photo.
     * @param photo Structure contenant les informations de la photo.
     */
    public static void convertPhotoHtmlForm(PhotoHtmlForm form, Photo photo,
            Locale locale) throws ValueObjectMapperException {
    	
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (Timestamp) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("cardex01");
            map.remove("dossier");
            map.remove("sujetInteretDossiers");
            map.remove("sujet");
            map.remove("image");
            map.remove("sourceFile");
            BeanUtils.populate(photo, map);
            photo.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, false));
            photo.setImage(form.getImage());
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure SocieteHtmlForm, représentant les informations
     * du formulaire de société, en une structure Societe représentant la
     * société dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de société.
     * @param societe Structure contenant les informations de la société.
     */
    public static void convertSocieteHtmlForm(SocieteHtmlForm form,
            Societe societe, Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (Timestamp) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("dateDeFondation");
            map.remove("dateCreation");
            map.remove("dateChangement");
            map.remove("dateInactif");
            map.remove("lienDateCreation");
            map.remove("ajoutPhoto");
            BeanUtils.populate(societe, map);
            societe.setDateDeFondation(TimestampFormat.parse(
                    form.getDateDeFondation(), locale, false));
            societe.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
            societe.setDateInactif(TimestampFormat.parse(
                    form.getDateInactif(), locale, false));
            societe.setLienDateCreation(TimestampFormat.parse(
                    form.getLienDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure SuiviHtmlForm, représentant les informations
     * du formulaire de suivi, en une structure Suivi représentant le
     * suivi dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de suivi.
     * @param suivi Structure contenant les informations du suivi.
     */
    public static void convertSuiviHtmlForm(SuiviHtmlForm form, Suivi suivi,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("datePrevue");
            map.remove("dateCompletee");
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateApprobation");
            map.remove("dateChangement");
            map.remove("dossier");
            BeanUtils.populate(suivi, map);
            suivi.setDatePrevue(TimestampFormat.parse(
                    form.getDatePrevue(), locale, true));
            suivi.setDateCompletee(TimestampFormat.parse(
                    form.getDateCompletee(), locale, true));
            suivi.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            suivi.setDateModification(TimestampFormat.parse(
                    form.getDateModification(), locale, true));
            suivi.setDateApprobation(TimestampFormat.parse(
                    form.getDateApprobation(), locale, true));
            suivi.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure AccesHtmlForm, représentant les informations
     * du formulaire des accès, en une structure Acces représentant
     * acces dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative à la liste
     * des accès
     * @param acces Structure contenant les informations des accèes.
     */
    public static void convertAccesHtmlForm(AccesHtmlForm form,
            Acces acces, Locale locale)throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dateAcces");
            BeanUtils.populate(acces, map);
            acces.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, false));
            acces.setDateAcces(TimestampFormat.parse(
                    form.getDateAcces(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }
    
    /**
     * Convertis une structure AdresseHtmlForm, représentant les informations
     * du formulaire de l'adresse, en une structure Adresse représentant
     * l'adresse dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de l'adresse.
     * @param adresse Structure contenant les informations de l'adresse.
     */
    public static void convertAdresseHtmlForm(AdresseHtmlForm form,
            Adresse adresse, Locale locale)throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateChangement");
            BeanUtils.populate(adresse, map);
            adresse.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, false));
            adresse.setDateModification(TimestampFormat.parse(
                    form.getDateModification(), locale, false));
            adresse.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure CaracteristiquesHtmlForm, représentant les
     * informations du formulaire des caratéristiques, en une structure
     * Caracteristiques représentant les caractéristiques dans le model
     * d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * des caractéristiques.
     * @param caracteristiques Structure contenant les informations des
     * caractéristiques.
     */
    public static void convertCaracteristiquesHtmlForm(
            CaracteristiquesHtmlForm form, Caracteristiques caracteristiques,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("caracteristiquesChoisis");
            BeanUtils.populate(caracteristiques, map);

            for(LabelValue labelValueBean:form.getDoubleListe().getDroiteCols()){
            	caracteristiques.addCaracteristique(labelValueBean.getValue());
            }
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure PartageHtmlForm, représentant les
     * informations de l'onglet Partage, en une structure
     * Partage.
     * 
     * @param form Structure contenant les informations relative au formulaire
     * des caractéristiques.
     * @param caracteristiques Structure contenant les informations des
     * caractéristiques.
     */
    public static void convertPartageHtmlForm(
            PartageHtmlForm form, Partage partage,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("intervenantsChoisis");
            BeanUtils.populate(partage, map);
            for(int i=0;form.getIntervenantsChoisis() != null && i<form.getIntervenantsChoisis().length;i++){
              partage.addIntervenant(form.getIntervenantsChoisis()[i]);
            }

        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure InscriptionHtmlForm, représentant les
     * informations du formulaire de l'inscription, en une structure
     * Inscription représentant l'inscription dans le model d'affaire de
     * l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de l'inscription.
     * @param inscription Structure contenant les informations de l'inscription.
     */
    public static void convertInscriptionHtmlForm(InscriptionHtmlForm form,
            Inscription inscription, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (ArrayList et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            //if(form.getSitesChoisis().length > 0){
	            map.remove("sitesChoisis");
            //}
            map.remove("dateInscription");
            map.remove("dateDebut");
            map.remove("dateFin");
            map.remove("dateCourrielAide");
            map.remove("dateCourrielSuivi");
            map.remove("dateRencontreInitiale");
            map.remove("dateRencontreFinale");
            BeanUtils.populate(inscription, map);
            inscription.setDateInscription(TimestampFormat.parse(
                    form.getDateInscription(), locale, false));
            inscription.setDateDebut(TimestampFormat.parse(
                    form.getDateDebut(), locale, false));
            inscription.setDateFin(TimestampFormat.parse(
                    form.getDateFin(), locale, false));
            inscription.setDateCourrielAide(TimestampFormat.parse(
                    form.getDateCourrielAide(), locale, true));
            inscription.setDateCourrielSuivi(TimestampFormat.parse(
                    form.getDateCourrielSuivi(), locale, true));
            inscription.setDateRencontreFinale(TimestampFormat.parse(
                    form.getDateRencontreFinale(), locale, true));
            inscription.setDateRencontreInitiale(TimestampFormat.parse(
                    form.getDateRencontreInitiale(), locale, true));
            if(form.getSitesChoisis().length > 0){
	            for(int i=0;i<form.getSitesChoisis().length;i++){
	              inscription.addSite(form.getSitesChoisis()[i]);
	            }
            }
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure JeuxHtmlForm, représentant les informations du
     * formulaire des jeux, en une structure Jeux représentant l'inscription
     * dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * des jeux.
     * @param jeux Structure contenant les informations des jeux.
     */
    public static void convertJeuxHtmlForm(JeuxHtmlForm form, Jeux jeux,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            //map.remove("jeuxChoisis");
            BeanUtils.populate(jeux, map);

            for(LabelValue labelValueBean:form.getDoubleListe().getDroiteCols()){
              jeux.addJeu(labelValueBean.getValue());
            }

        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure ParticularitesHtmlForm, représentant les
     * informations du formulaire des particulartiés, en une structure
     * Particularites représentant les particularités dans le model d'affaire
     * de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * des particularités.
     * @param particularites Structure contenant les informations des
     * particularités.
     */
    public static void convertParticularitesHtmlForm(
            ParticularitesHtmlForm form, Particularites particularites,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("particularitesChoisis");
            BeanUtils.populate(particularites, map);
            
            for(LabelValue labelValueBean:form.getDoubleListe().getDroiteCols()){
                particularites.addParticularite(labelValueBean.getValue());
            }
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheDossierHtmlForm, représentant
     * les informations du formulaire de recherche de dossier, en une structure
     * CriteresRechercheDossier représentant le dossier recherché dans le model
     * d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de dossier.
     * @param dossier Structure contenant les informations du dossier recherché.
     */
    public static void convertCriteresRechercheDossierHtmlForm(
            CriteresRechercheDossierHtmlForm form,
            CriteresRechercheDossier criteresDossier, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateDebutDu");
            map.remove("dateFinDu");
            map.remove("dateDebutAu");
            map.remove("dateFinAu");
            map.remove("dateCreationDu");
            map.remove("dateCreationAu");
            BeanUtils.populate(criteresDossier, map);
            criteresDossier.setNumeroCardex((
                    form.getNumeroCardex()).toString());
            criteresDossier.setDateDebutDu(TimestampFormat.parse(
                    form.getDateDebutDu(), locale, false));
            criteresDossier.setDateFinDu(TimestampFormat.parse(
                    form.getDateFinDu(), locale, false));
            criteresDossier.setDateDebutAu(TimestampFormat.parse(
                    form.getDateDebutAu(), locale, false));
            criteresDossier.setDateFinAu(TimestampFormat.parse(
                    form.getDateFinAu(), locale, false));
            criteresDossier.setDateCreationDu(TimestampFormat.parse(
                    form.getDateCreationDu(), locale, false));
            criteresDossier.setDateCreationAu(TimestampFormat.parse(
                    form.getDateCreationAu(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

	/**
	 * Convertis une structure CriteresRechercheRegroupementHtmlForm, représentant
	 * les informations du formulaire de recherche du journal, en une structure
	 * CriteresRechercheJournal représentant le dossier recherché dans le model
	 * d'affaire de l'application.
	 *
	 * @param form Structure contenant les informations relative au formulaire
	 * de recherche de dossier.
	 * @param dossier Structure contenant les informations du dossier recherché.
	 */
	public static void convertCriteresRechercheJournalHtmlForm(
	        CriteresRechercheJournalHtmlForm form,
	        CriteresRechercheJournal criteresJournal, Locale locale)
	        throws ValueObjectMapperException {
	    try {
	        Map map = BeanUtils.describe(form);
	        // Les attributs suivants doivent être retirés du map, puisqu'ils
	        // sont des types complexes (Timestamp) et qu'ils
	        // ne peuvent être convertis par la méthode "populate" de BeanUtils.
	        map.remove("dateCreationDu");
	        map.remove("dateCreationAu");
	        BeanUtils.populate(criteresJournal, map);
	        criteresJournal.setDateCreationDu(TimestampFormat.parse(
	                form.getDateCreationDu(), locale, true));
	        criteresJournal.setDateCreationAu(TimestampFormat.parse(
	                form.getDateCreationAu(), locale, true));
	    } catch (IllegalAccessException iae) {
	        throw new ValueObjectMapperException(iae);
	    } catch (InvocationTargetException ite) {
	        throw new ValueObjectMapperException(ite);
	    } catch (NoSuchMethodException nsme) {
	        throw new ValueObjectMapperException(nsme);
	    } catch (ParseException pe) {
	        throw new ValueObjectMapperException(pe);
	    }
	}

	/**
	 * Convertis une structure CriteresRechercheJournal représentant le dossier
	 * recherché dans le model d'affaire de l'application, en une structure
	 * CriteresRechercheJournalHtmlForm, représentant les informations du
	 * formulaire de recherche de dossier.
	 *
	 * @param criteresDossier Structure contenant les informations du dossier
	 * recherché.
	 * @param form Structure contenant les informations relative au formulaire
	 * de recherche de dossier.
	 */
	public static void convertCriteresRechercheJournal(
	        CriteresRechercheJournal criteresJournal,
	        CriteresRechercheJournalHtmlForm form, Locale locale)
	        throws ValueObjectMapperException {
	    try {
	        Map map = BeanUtils.describe(criteresJournal);
	        // Les attributs suivants doivent être retirés du map, puisqu'ils
	        // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
	        // ne peuvent être convertis par la méthode "populate" de BeanUtils.
	        map.remove("dateCreationDu");
	        map.remove("dateCreationAu");
	        BeanUtils.populate(form, map);
	        form.setDateCreationDu(TimestampFormat.format(
	                criteresJournal.getDateCreationDu(), locale, false));
	        form.setDateCreationAu(TimestampFormat.format(
	                criteresJournal.getDateCreationAu(), locale, false));
	    } catch (IllegalAccessException iae) {
	        throw new ValueObjectMapperException(iae);
	    } catch (InvocationTargetException ite) {
	        throw new ValueObjectMapperException(ite);
	    } catch (NoSuchMethodException nsme) {
	        throw new ValueObjectMapperException(nsme);
	    }
	}

    /**
     * Convertis une structure CriteresRechercheSujetHtmlForm, représentant
     * les informations du formulaire de recherche de sujet, en une structure
     * CriteresRechercheSujet représentant le sujet recherché dans le model
     * d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de sujet.
     * @param dossier Structure contenant les informations du sujet recherché.
     */
    public static void convertCriteresRechercheSujetHtmlForm(
            CriteresRechercheSujetHtmlForm form,
            CriteresRechercheSujet criteresSujet, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // L'attributs suivant doivt être retiré du map, puisqu'il
            // est un type complexe (ArrayList) et qu'il ne peut être converti
            // par la méthode "populate" de BeanUtils.
            map.remove("sujets");
            BeanUtils.populate(criteresSujet, map);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheNarrationHtmlForm, représentant
     * les informations du formulaire de recherche de narration, en une
     * structure CriteresRechercheNarration représentant la narration recherché
     * dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de la narration.
     * @param dossier Structure contenant les informations de la narration
     * recherchée.
     */
    public static void convertCriteresRechercheNarrationHtmlForm(
            CriteresRechercheNarrationHtmlForm form,
            CriteresRechercheNarration criteresNarration, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreationDebut");
            map.remove("dateApprobationDebut");
            map.remove("dateCreationFin");
            map.remove("dateApprobationFin");
            BeanUtils.populate(criteresNarration, map);
            criteresNarration.setDateCreationDebut(TimestampFormat.parse(
                    form.getDateCreationDebut(), locale, false));
            criteresNarration.setDateApprobationDebut(TimestampFormat.parse(
                    form.getDateApprobationDebut(), locale, false));
            criteresNarration.setDateCreationFin(TimestampFormat.parse(
                    form.getDateCreationFin(), locale, false));
            criteresNarration.setDateApprobationFin(TimestampFormat.parse(
                    form.getDateApprobationFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure CriteresRecherchePhotoHtmlForm, représentant
     * les informations du formulaire de recherche de photo, en une
     * structure CriteresRecherchePhoto représentant la photo recherché dans
     * le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de la photo.
     * @param criteresPhoto Structure contenant les informations de la photo
     * recherchée.
     */
    public static void convertCriteresRecherchePhotoHtmlForm(
            CriteresRecherchePhotoHtmlForm form,
            CriteresRecherchePhotoVO criteresPhoto, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateAjoutDebut");
            map.remove("dateAjoutFin");
            map.remove("dateValideDebut");
            map.remove("dateValideFin");
            map.remove("dateTermineDebut");
            map.remove("dateTermineFin");
            BeanUtils.populate(criteresPhoto, map);
            criteresPhoto.setDateAjoutDebut(TimestampFormat.parse(
                    form.getDateAjoutDebut(), locale, false));
            criteresPhoto.setDateAjoutFin(TimestampFormat.parse(
                    form.getDateAjoutFin(), locale, false));
            criteresPhoto.setDateValideDebut(TimestampFormat.parse(
                    form.getDateValideDebut(), locale, false));
            criteresPhoto.setDateValideFin(TimestampFormat.parse(
                    form.getDateValideFin(), locale, false));
            criteresPhoto.setDateTermineDebut(TimestampFormat.parse(
                    form.getDateTermineDebut(), locale, false));
            criteresPhoto.setDateTermineFin(TimestampFormat.parse(
                    form.getDateTermineFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure CriteresRechercheSocieteHtmlForm, représentant
     * les informations du formulaire de recherche de société, en une
     * structure CriteresRechercheSociete représentant la société recherchée
     * dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de la société.
     * @param criteresSociete Structure contenant les informations de la société
     * recherchée.
     */
    public static void convertCriteresRechercheSocieteHtmlForm(
            CriteresRechercheSocieteHtmlForm form,
            CriteresRechercheSociete criteresSociete, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            map.remove("entiteCardexLiaison");
            BeanUtils.populate(criteresSociete, map);
            EntiteCardexForm entiteCardexForm = form.getEntiteCardexLiaison();
            
            if (form.getEntiteCardexLiaison() instanceof DossierHtmlForm) {
                criteresSociete.setEntiteCardexLiaison(new DossierVO(LongUtils.valueOf(entiteCardexForm.getCle()), LongUtils.valueOf(entiteCardexForm.getSite())));
            }else if (form.getEntiteCardexLiaison() instanceof SujetHtmlForm) {
                criteresSociete.setEntiteCardexLiaison(new SujetVO(LongUtils.valueOf(entiteCardexForm.getCle()), LongUtils.valueOf(entiteCardexForm.getSite())));
            }else if (form.getEntiteCardexLiaison() instanceof VehiculeHtmlForm) {
                criteresSociete.setEntiteCardexLiaison(new VehiculeVO(LongUtils.valueOf(entiteCardexForm.getCle()), LongUtils.valueOf(entiteCardexForm.getSite())));
            }else if (form.getEntiteCardexLiaison() instanceof SocieteHtmlForm) {
                criteresSociete.setEntiteCardexLiaison(new SocieteVO(LongUtils.valueOf(entiteCardexForm.getCle()), LongUtils.valueOf(entiteCardexForm.getSite())));
            }
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheSuiviHtmlForm, représentant
     * les informations du formulaire de recherche de suivi, en une
     * structure CriteresRechercheSuivi représentant la suivi recherché
     * dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche du suivi.
     * @param criteresSuivi Structure contenant les informations du suivi
     * recherché.
     */
    public static void convertCriteresRechercheSuiviHtmlForm(
            CriteresRechercheSuiviHtmlForm form,
            CriteresRechercheSuivi criteresSuivi, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateEmisDebut");
            map.remove("datePrevueDebut");
            map.remove("dateCompleteeDebut");
            map.remove("dateEmisFin");
            map.remove("datePrevueFin");
            map.remove("dateCompleteeFin");
            BeanUtils.populate(criteresSuivi, map);
            criteresSuivi.setDateEmisDebut(TimestampFormat.parse(
                    form.getDateEmisDebut(), locale, false));
            criteresSuivi.setDatePrevueDebut(TimestampFormat.parse(
                    form.getDatePrevueDebut(), locale, false));
            criteresSuivi.setDateCompleteeDebut(TimestampFormat.parse(
                    form.getDateCompleteeDebut(), locale, false));
            criteresSuivi.setDateEmisFin(TimestampFormat.parse(
                    form.getDateEmisFin(), locale, false));
            criteresSuivi.setDatePrevueFin(TimestampFormat.parse(
                    form.getDatePrevueFin(), locale, false));
            criteresSuivi.setDateCompleteeFin(TimestampFormat.parse(
                    form.getDateCompleteeFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure CriteresRechercheConsignationHtmlForm, représentant
     * les informations du formulaire de recherche de Consignation, en une
     * structure CriteresRechercheConsignation représentant la Consignation recherchée
     * dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de Consignation.
     * @param criteresConsignation Structure contenant les informations de Consignation
     * recherché.
     */
    public static void convertCriteresRechercheConsignationHtmlForm(
            CriteresRechercheConsignationHtmlForm form,
            CriteresRechercheConsignation criteresConsignation, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateDebut");
            map.remove("dateFin");
            BeanUtils.populate(criteresConsignation, map);
            criteresConsignation.setDateDebut(TimestampFormat.parse(
                    form.getDateDebut(), locale, false));
            criteresConsignation.setDateFin(TimestampFormat.parse(
                    form.getDateFin(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure CriteresRechercheVehicule représentant le véhicule
     * recherché dans le model d'affaire de l'application, en une structure
     * CriteresRechercheVehiculeHtmlForm, représentant les informations du
     * formulaire de recherche de véhicule.
     *
     * @param criteresVehicule Structure contenant les informations du véhicule
     * recherché.
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de véhicule.
     */
    public static void convertCriteresRechercheVehicule(
            CriteresRechercheVehicule criteresVehicule,
            CriteresRechercheVehiculeHtmlForm form, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(criteresVehicule);
            BeanUtils.populate(form, map);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
    /**
     * Convertis une structure CriteresRechercheVehiculeHtmlForm, représentant
     * les informations du formulaire de recherche de véhicule, en une structure
     * CriteresRechercheVehicule représentant le véhicule recherché dans le modèle
     * d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche de sujet.
     * @param criteresVehicule Structure contenant les informations du sujet recherché.
     */
    public static void convertCriteresRechercheVehiculeHtmlForm(
            CriteresRechercheVehiculeHtmlForm form,
            CriteresRechercheVehicule criteresVehicule, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            map.remove("vehicules");
            BeanUtils.populate(criteresVehicule, map);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure CriteresRechercheUrgenceHtmlForm, représentant
     * les informations du formulaire de recherche du service d'urgence, en une structure
     * CriteresRechercheUrgence représentant le service d'urgence recherché dans le modèle
     * d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de recherche du service d'urgence.
     * @param criteresUrgence Structure contenant les informations du service d'urgence recherché.
     */
    public static void convertCriteresRechercheUrgenceHtmlForm(
            CriteresRechercheUrgenceHtmlForm form,
            CriteresRechercheUrgence criteresUrgence, Locale locale)
            throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            map.remove("urgence");
            BeanUtils.populate(criteresUrgence, map);
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
    
    /**
     * Convertis une structure Véhicule représentant le véhicule dans le model
     * d'affaire de l'application, en une structure VehiculeHtmlForm,
     * représentant les informations du formulaire de véhicule.
     *
     * @param vehicule Structure contenant les informations du véhicule.
     * @param form Structure contenant les informations relative au formulaire
     * de véhicule.
     */
    public static void convertVehicule(Vehicule vehicule, VehiculeHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(vehicule);
            map.remove("dateCreation");
            map.remove("dateExpirationVignette");
            map.remove("dateExpirationAssurance");
            map.remove("dateChangement");
            map.remove("lienDateCreation");
            BeanUtils.populate(form, map);
            form.setDateExpirationVignette(TimestampFormat.format(
                    vehicule.getDateExpirationVignette(), locale, false));
            form.setDateExpirationAssurance(TimestampFormat.format(
                    vehicule.getDateExpirationAssurance(), locale, false));
            form.setDateCreation(TimestampFormat.format(
                    vehicule.getDateCreation(), locale, true));
            form.setDateChangement(TimestampFormat.format(
                    vehicule.getDateChangement(), locale, true));
            form.setLienDateCreation(TimestampFormat.format(
                    vehicule.getLienDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
   /**
     * Convertis une structure VehiculeHtmlForm, représentant les informations
     * du formulaire de véhicule, en une structure Vehicule représentant le
     * véhicule dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de véhicule.
     * @param vehicule Structure contenant les informations du sujet.
     */
    public static void convertVehiculeHtmlForm(VehiculeHtmlForm form, Vehicule vehicule,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            map.remove("dateCreation");
            map.remove("dateExpirationVignette");
            map.remove("dateExpirationAssurance");
            map.remove("dateChangement");
            map.remove("lienDateCreation");
            map.remove("ajoutPhoto");
            BeanUtils.populate(vehicule, map);
            vehicule.setDateExpirationVignette(TimestampFormat.parse(
                    form.getDateExpirationVignette(), locale, false));
            vehicule.setDateExpirationAssurance(TimestampFormat.parse(
                    form.getDateExpirationAssurance(), locale, false));
            vehicule.setDateChangement(TimestampFormat.parse(
                    form.getDateChangement(), locale, true));
            vehicule.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            vehicule.setLienDateCreation(TimestampFormat.parse(
                    form.getLienDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

    /**
     * Convertis une structure SuiviHtmlForm, représentant les informations
     * du formulaire de suivi, en une structure Suivi représentant le
     * suivi dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * de suivi.
     * @param suivi Structure contenant les informations du suivi.
     */
    public static void convertConsignationHtmlForm(ConsignationHtmlForm form, Consignation consignation,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateApprobation");
            map.remove("dateCreation");
            map.remove("dateModification");
			map.remove("montant");
			map.remove("prix");
			map.remove("dossier");
			BeanUtils.populate(consignation, map);
            consignation.setDateApprobation(TimestampFormat.parse(
                    form.getDateApprobation(), locale, true));
            consignation.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            consignation.setDateModification(TimestampFormat.parse(
                    form.getDateModification(), locale, true));
			consignation.setMontant(CurrencyFormat.parse(form.getMontant(),locale));
			consignation.setPrix(CurrencyFormat.parse(form.getPrix(),locale));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

	/**
	 * Convertis une structure SuiviHtmlForm, représentant les informations
	 * du formulaire de suivi, en une structure Suivi représentant le
	 * suivi dans le model d'affaire de l'application.
	 *
	 * @param form Structure contenant les informations relative au formulaire
	 * de suivi.
	 * @param suivi Structure contenant les informations du suivi.
	 */
	public static void convertCriteresRecherchePSUMandatHtmlForm(CriteresRecherchePSUMandatHtmlForm form, CriteresRecherchePSUMandat criteresRecherchePSUMandat,
			Locale locale) throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(form);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateDebut");
			map.remove("dateFin");
			BeanUtils.populate(criteresRecherchePSUMandat, map);
			criteresRecherchePSUMandat.setDateDebut(TimestampFormat.parse(
					form.getDateDebut(), locale, true));
			criteresRecherchePSUMandat.setDateFin(TimestampFormat.parse(
					form.getDateFin(), locale, true));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		} catch (ParseException pe) {
			throw new ValueObjectMapperException(pe);
		}
	}

	/**
	 * Convertis une structure SuiviHtmlForm, représentant les informations
	 * du formulaire de suivi, en une structure Suivi représentant le
	 * suivi dans le model d'affaire de l'application.
	 *
	 * @param form Structure contenant les informations relative au formulaire
	 * de suivi.
	 * @param suivi Structure contenant les informations du suivi.
	 */
	public static void convertPSUMandatHtmlForm(PSUMandatHtmlForm form, PSUMandat psuMandat,
			Locale locale) throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(form);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateApprobation");
			map.remove("dateCreation");
			map.remove("dateModification");
			map.remove("dateDebut");
			map.remove("dateFin");
			//Pour une raison inxepliquée, "servlet" est associé à psuMandat, ce qui
			//provoque une erreur de conversion lors du "Populate". Le retirant la variable,
			//le problème est réglé.
			map.remove("servlet");
			map.remove("validatorResults");
			BeanUtils.populate(psuMandat, map);
			psuMandat.setDateApprobation(TimestampFormat.parse(
					form.getDateApprobation(), locale, true));
			psuMandat.setDateCreation(TimestampFormat.parse(
					form.getDateCreation(), locale, true));
			psuMandat.setDateModification(TimestampFormat.parse(
					form.getDateModification(), locale, true));
			psuMandat.setDateDebut(TimestampFormat.parse(
					form.getDateDebut(), locale, true));
			psuMandat.setDateFin(TimestampFormat.parse(
					form.getDateFin(), locale, true));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		} catch (ParseException pe) {
			throw new ValueObjectMapperException(pe);
		}
	}

	/**
	 * Convertis une structure Consignation représentant le dossier dans le model
	 * d'affaire de l'application, en une structure ConsignationHtmlForm,
	 * représentant les informations du formulaire de Consignation.
	 *
	 * @param dossier Structure contenant les informations de Consignation.
	 * @param form Structure contenant les informations relative au formulaire
	 * de Consignation.
	 */
	public static void convertPSUMandat(PSUMandat psuMandat, PSUMandatHtmlForm form,
			Locale locale) throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(psuMandat);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (NumeroCardex et Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateCreation");
			map.remove("dateModification");
			map.remove("dateApprobation");
			map.remove("dateDebut");
			map.remove("dateFin");
			BeanUtils.populate(form, map);
			form.setDateCreation(TimestampFormat.format(psuMandat.getDateCreation(), 
					locale, true));
			form.setDateModification(TimestampFormat.format(psuMandat.getDateModification(),
					locale, true));
			form.setDateApprobation(TimestampFormat.format(psuMandat.getDateApprobation(), 
					locale, true));
			form.setDateDebut(TimestampFormat.format(psuMandat.getDateDebut(), 
					locale, true));
			form.setDateFin(TimestampFormat.format(psuMandat.getDateFin(), 
					locale, true));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		}
	}

	/**
	 * Convertis une structure ConsignationAction représentant le dossier dans le model
	 * d'affaire de l'application, en une structure ConsignationActionHtmlForm,
	 * représentant les informations du formulaire de Consignation.
	 *
	 * @param dossier Structure contenant les informations de Consignation.
	 * @param form Structure contenant les informations relative au formulaire
	 * de Consignation.
	 */
	public static void convertConsignationActionPSU(ConsignationActionPSU consignationAction, ConsignationActionPSUHtmlForm form,
			Locale locale) throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(consignationAction);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (NumeroCardex et Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateConsignation");
			map.remove("dateCourriel");
			map.remove("dossier");
			BeanUtils.populate(form, map);
			form.setDateConsignation(TimestampFormat.format(consignationAction.getDateConsignation(), 
					locale, true));
			form.setDateCourriel(TimestampFormat.format(consignationAction.getDateCourriel(),
					locale, true));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		}
	}
	/**
	 * Convertis une structure ConsignationActionPSUHtmlForm, représentant les informations
	 * du formulaire de suivi, en une structure ConsignationActionPSU représentant le
	 * suivi dans le model d'affaire de l'application.
	 *
	 * @param form Structure contenant les informations relative au formulaire
	 * de suivi.
	 * @param suivi Structure contenant les informations du suivi.
	 */
	public static void convertConsignationActionPSUHtmlForm(ConsignationActionPSUHtmlForm form, ConsignationActionPSU consignationActionPSU,
			Locale locale) throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(form);
			// Les attributs suivants doivent être retirés du map, puisqu'ils
			// sont des types complexes (Timestamp) et qu'ils
			// ne peuvent être convertis par la méthode "populate" de BeanUtils.
			map.remove("dateConsignation");
			map.remove("dateCourriel");
			map.remove("dossier");
			//Pour une raison inxepliquée, "servlet" est associé à psuMandat, ce qui
			//provoque une erreur de conversion lors du "Populate". Le retirant la variable,
			//le problème est réglé.
			map.remove("servlet");
			BeanUtils.populate(consignationActionPSU, map);
			consignationActionPSU.setDateConsignation(TimestampFormat.parse(
					form.getDateConsignation(), locale, true));
			consignationActionPSU.setDateCourriel(TimestampFormat.parse(
					form.getDateCourriel(), locale, true));
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		} catch (ParseException pe) {
			throw new ValueObjectMapperException(pe);
		}
	}

    public static void convertDirect(Object source, Object dest)throws ValueObjectMapperException {
		try {
			Map mapOriginal = BeanUtils.describe(source);
			BeanUtils.populate(dest, mapOriginal);

		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		}
	}
	
    public static void convert(BilletForm source, BilletVO dest, Locale locale) throws ValueObjectMapperException, ParseException{
        convertBillet(source, dest);

    	if(StringUtils.isNotEmpty(source.getValeur())){
    		dest.setValeur(new BigDecimal(CurrencyFormat.parse(source.getValeur(), locale)));
    	}else{
    		dest.setValeur(null);
    	}
    	if(StringUtils.isNotEmpty(source.getMontantLot())){
    		dest.setMontantLot(new BigDecimal(CurrencyFormat.parse(source.getMontantLot(), locale)));
    	}else{
    		dest.setMontantLot(null);
    	}
    	if(StringUtils.isNotEmpty(source.getMontantExtra())){
    		dest.setMontantExtra(new BigDecimal(CurrencyFormat.parse(source.getMontantExtra(), locale)));
    	}else{
    		dest.setMontantExtra(null);
    	}
    }
    
    public static void convert(CriteresRechercheBilletForm source, CriteresRechercheBilletVO dest, Locale locale) throws ValueObjectMapperException, ParseException{
        convertBillet(source, dest);

    	if(StringUtils.isNotEmpty(source.getValeur())){
    		dest.setValeur(new BigDecimal(CurrencyFormat.parse(source.getValeur(), locale)));
    	}else{
    		dest.setValeur(null);
    	}
    	if(StringUtils.isNotEmpty(source.getMontantLot())){
    		dest.setMontantLot(new BigDecimal(CurrencyFormat.parse(source.getMontantLot(), locale)));
    	}else{
    		dest.setMontantLot(null);
    	}
    }

	private static void convertBillet(Object source, Object dest)
			throws ValueObjectMapperException {
		try {
            Map mapOriginal = BeanUtils.describe(source);
            List listMethodDate = obtenirListChaine(mapOriginal, "date");
            Map mapRetirer = retirerMap(mapOriginal, listMethodDate);
            mapRetirer.remove("valeur");
            mapRetirer.remove("montantLot");
            mapRetirer.remove("montantExtra");
            
            BeanUtils.populate(dest, mapRetirer);
            
            populateDate(source, dest, mapOriginal, listMethodDate);
            
            if (dest instanceof PostCopy){
            	PostCopy postCopy = (PostCopy) dest;
            	postCopy.postCopyFormat();
            }
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
	}

    public static void convert(BilletVO source, BilletForm dest, Locale locale) throws ValueObjectMapperException{
    	convertBillet(source, dest);
        
    	if(source.getValeur() != null){
    		dest.setValeur(CurrencyFormat.format(source.getValeur().doubleValue(),locale));
    	}else{
    		dest.setValeur(null);
    	}
    	if(source.getMontantLot() != null){
    		dest.setMontantLot(CurrencyFormat.format(source.getMontantLot().doubleValue(),locale));
    	}else{
    		dest.setMontantLot("");
    	}
    	if(source.getMontantExtra() != null){
    		dest.setMontantExtra(CurrencyFormat.format(source.getMontantExtra().doubleValue(),locale));
    	}else{
    		dest.setMontantExtra("");
    	}
    }

    
    public static void convert(Object source, Object dest)
            throws ValueObjectMapperException {
        try {
            Map mapOriginal = BeanUtils.describe(source);
            List listMethodDate = obtenirListChaine(mapOriginal, "date");
            Map mapRetirer = retirerMap(mapOriginal, listMethodDate);
            
            BeanUtils.populate(dest, mapRetirer);
            
            populateDate(source, dest, mapOriginal, listMethodDate);
            
            if (dest instanceof PostCopy){
            	PostCopy postCopy = (PostCopy) dest;
            	postCopy.postCopyFormat();
            }
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }
    
	public static Object construireParameterMap(Map parameterMap, Object o){
		
		try {
            List listMethodDate = obtenirListChaine(parameterMap, "date");
            Map mapRetirer = retirerMap(parameterMap, listMethodDate);
            
            BeanUtils.populate(o, mapRetirer);
            
            populateDateParameterMap(o, parameterMap, listMethodDate);			
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		
	    return o;
	}    

    /*private static void populateDate(Object dest, Map map, List listMethodDate) {
    	Iterator iter = listMethodDate.iterator();
    	
    	while (iter.hasNext()) {
			String key = (String) iter.next();
			
			try {
				Date date = TimestampFormat.parse((String) map.get(key));

				BeanUtils.copyProperty(dest, key, (Date) date);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    }*/
	
    private static void populateDate(Object source, Object dest, Map map, List listMethodDate) {
		Class[] classGet = new Class[0];
		Class[] classSetString = new Class[]{(new String()).getClass()};
		Class[] classSetDate = new Class[]{(new Date()).getClass()};    	
		SimpleDateFormat dateFormatterDateJava28 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		SimpleDateFormat dateFormatterDateJava19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		SimpleDateFormat dateFormatterDateJava10 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		//SimpleDateFormat dateFormatterEcran = new SimpleDateFormat(com.lotoquebec.cardex.text.DateFormat.DATE_FORMAT);
		Class destClass = dest.getClass();
		
		do {
			Field[] fields = destClass.getDeclaredFields();
			
			for (Field field:fields) {
				try { 
					if (listMethodDate.contains(field.getName())){
						Method methodSet = null;
						String valeur = (String) map.get(field.getName());
						Object valeurCible = null;
	
						if (String.class.isAssignableFrom(field.getType())){
							methodSet =  destClass.getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), classSetString);
							
							if (valeur == null)
								valeurCible = "";
							else{
								Method methodGet =  source.getClass().getDeclaredMethod("get"+StringUtils.capitalise(field.getName()), classGet);
								Date date = (Date) methodGet.invoke(source);
								valeurCible = dateFormatterDateJava19.format(date);
								//On n'affiche pas les heures nulles
								if(valeur.contains("00:00:00")){
									valeurCible = dateFormatterDateJava10.format(date);
								}
							}
						
						}else if (Date.class.isAssignableFrom(field.getType())){
							methodSet = destClass.getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), classSetDate);
							
							if (StringUtils.isEmpty(valeur))
								valeurCible = null;
							else{
								switch(valeur.length()){
									case 10:
										valeurCible = dateFormatterDateJava10.parse(valeur);
										break;
									case 19:
										valeurCible = dateFormatterDateJava19.parse(valeur);
										break;
									case 28:
										valeurCible = dateFormatterDateJava28.parse(valeur);
										break;									
								}
								//valeurCible = dateFormatterEcran.parse(valeur);
							}
						}
						
						Object[] setValeur = {valeurCible};
						methodSet.invoke(dest, setValeur);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
			// Aller chercher les champs des parents
			destClass = destClass.getSuperclass();
		} while (Object.class.equals(destClass) == false);			
	}
	
    private static void populateDateParameterMap(Object dest, Map parameterMap, List listMethodDate) {
    	Iterator iter = listMethodDate.iterator();
    	
    	while (iter.hasNext()) {
			String key = (String) iter.next();
			
			try {
				String strDate = obtenirPremiereString(parameterMap, key);
				Date date = null;
				
				if (strDate.length() == 10)
					date = TimestampFormat.parse(strDate);

				if (strDate.length() == 19)
					date = TimestampFormat.parseFrenchTemps(strDate);
				
				if (date != null)
					BeanUtils.copyProperty(dest, key, (Date) date);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}		
	}	    

	public static String obtenirPremiereString(Map parameterMap, String key){
		String[] arrayString = (String[]) parameterMap.get(key);
		return arrayString[0];
	}
    
	private static List obtenirListChaine(Map map, String chaine){
    	List list = new ArrayList();
    	Iterator iter = map.keySet().iterator();
    	
    	while (iter.hasNext()) {
			String key = (String) iter.next();
			
			if (chaine.equals(StringUtils.left(key, chaine.length())))
				list.add(key);
		}
    	return list;
    }

    private static Map retirerMap(Map map, List list){
    	Map retourMap = new HashMap();
    	retourMap.putAll(map);
    	Iterator iter = list.iterator();
    	
    	while (iter.hasNext()) {
			String key = (String) iter.next();
			retourMap.remove(key);
		}
    	return retourMap;
    }    
    
    public static void convertSujetNarration(SujetNarration sujetNarrationSource, Object dest)
			throws ValueObjectMapperException {
		try {
			Map map = BeanUtils.describe(sujetNarrationSource);
			map.remove("dateNaissanceDate");
			map.remove("dateNaissance");
			
			BeanUtils.populate(dest, map);
		} catch (IllegalAccessException iae) {
			throw new ValueObjectMapperException(iae);
		} catch (InvocationTargetException ite) {
			throw new ValueObjectMapperException(ite);
		} catch (NoSuchMethodException nsme) {
			throw new ValueObjectMapperException(nsme);
		}
	}

	public static void convertAdresseEntreeAForm(CardexAuthenticationSubject subject, AdresseSortie adresseSortie,
			AdresseForm adresseValideForm) throws BusinessResourceException {
		
		if (ConstantValidationAdresse.CAN.equals(adresseSortie.getPays())){
			adresseValideForm.setPays(obtenirLongValeurListe(subject, ConstantValidationAdresse.CANADA, "", new PaysCleListeCache(subject)));
		}
		
		adresseValideForm.setNumeroMunicipal( adresseSortie.getNumeroMunicipale() + adresseSortie.getSufixMunicipale() );
		adresseValideForm.setTypeRue(obtenirLongValeurListe(subject, adresseSortie.getTypeRue(), GlobalConstants.TypeRue.RUE, new TypeRueCleListeCache(subject)));
		adresseValideForm.setNomRue( adresseSortie.getNomRue() );
		adresseValideForm.setPointCardinal(obtenirLongValeurListe(subject, adresseSortie.getCardinaliteRue(), "", new CardinaliteAbreviationCle(subject)));
		adresseValideForm.setUnite(obtenirLongValeurListe(subject, adresseSortie.getTypeUnite(), "", new UniteAbreviationCle(subject)));
		adresseValideForm.setNumeroUnite(adresseSortie.getNumeroUnite());
		adresseValideForm.setProvince(obtenirLongValeurListe(subject, adresseSortie.getProvince(), "", new ProvinceAbreviationCle(subject, adresseValideForm.getPays())));
		adresseValideForm.setVille(obtenirLongValeurListe(subject, adresseSortie.obtenirVillePlusLong(), "", new VilleCleMultiExterneListeCache(subject, adresseValideForm.getProvince())));
		adresseValideForm.setCodePostal(adresseSortie.getCodePostal());
		
		adresseValideForm.setAdressePostal(adresseSortie.obtenirAdressePostal());
		
		adresseValideForm.setMessage(adresseSortie.obtenirMessageHTML());
	}
    
	public static void convertAdresseSortieRechercheAForm(CardexAuthenticationSubject subject, AdresseSortieRecherche adresseSortieRecherche,
			AdresseRechercheForm adresseRechercheForm) throws BusinessResourceException {
		
		adresseRechercheForm.setNumeroMunicipalMin(adresseSortieRecherche.getNumeroMunicipalMin());
		adresseRechercheForm.setNumeroMunicipalMax(adresseSortieRecherche.getNumeroMunicipalMax());
		
		adresseRechercheForm.setPrefixNumeroUnite(adresseSortieRecherche.getPrefixNumeroUnite());
		adresseRechercheForm.setSufixNumeroUnite(adresseSortieRecherche.getSufixNumeroUnite());
		
		adresseRechercheForm.setNumeroUniteMin(adresseSortieRecherche.getNumeroUniteMin());
		adresseRechercheForm.setNumeroUniteMax(adresseSortieRecherche.getNumeroUniteMax());
		
		convertAdresseEntreeAForm(subject, adresseSortieRecherche, adresseRechercheForm);
	}
	
	private static String obtenirLongValeurListe(CardexAuthenticationSubject subject, String valeur, String valeurDefault, CleListe cleListe) throws BusinessResourceException{
		ListeCache listeCache = ListeCache.getInstance();
		long l = listeCache.obtenirKeyDeLabel(subject, valeur, cleListe);
		
		if (l != 0)
			return String.valueOf(l);
		return valeurDefault;
	}
	
    /**
     * Convertit une structure EvaluationHtmlForm, représentant les informations
     * du formulaire d'évaluation, en une structure Evaluation représentant le
     * dossier dans le model d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * d'évaluaiton du Comité de vigilance.
     * @param dossier Structure contenant les informations du dossier.
     */
    public static void convertEvaluationHtmlForm(EvaluationHtmlForm form,
    		Evaluation evaluation, Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (NumeroCardex et Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateDebutEval");
            map.remove("dateFinEval");
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateEvaluation");
            map.remove("etats");
            map.remove("propos");
            map.remove("misesEvaluation");
            BeanUtils.populate(evaluation, map);
            evaluation.setDateDebutEval(TimestampFormat.parse(form.getDateDebutEval(),
                    locale, true));
            evaluation.setDateFinEval(TimestampFormat.parse(form.getDateFinEval(),
                    locale, true));
            evaluation.setDateModification(TimestampFormat.parse(
                    form.getDateModification(), locale, true));
            evaluation.setDateEvaluation(TimestampFormat.parse(
                    form.getDateEvaluation(), locale, true));
            evaluation.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, true));
            for(LabelValue labelValueBean:form.getDoubleListeEtat().getDroiteCols()){
                evaluation.addEtat(labelValueBean.getValue());
              }
            for(LabelValue labelValueBean:form.getDoubleListePropos().getDroiteCols()){
                evaluation.addPropos(labelValueBean.getValue());
              }
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }
    
    public static void convert(MiseEvaluationForm form, MiseEvaluationVO vo, Locale locale) throws ValueObjectMapperException {

    	try {
    		Map map = BeanUtils.describe(form);
    		BeanUtils.populate(vo, map);
    		
			vo.setMiseMinimum(CurrencyFormat.parse(form.getMiseMinimum(),locale));
	    	vo.setMiseMoyenne(CurrencyFormat.parse(form.getMiseMoyenne(),locale));
	    	vo.setMiseMaximum(CurrencyFormat.parse(form.getMiseMaximum(),locale));
	    	vo.setGainPerte(CurrencyFormat.parse(form.getGainPerte(),locale));
	    	vo.setMiseTotal(CurrencyFormat.parse(form.getMiseTotal(),locale));
	    	vo.setTempsJeuTotal(CurrencyFormat.parse(form.getTempsJeuTotal(),locale));
	    	vo.setTypeJeu( Long.valueOf(form.getJeuxForm().getTypeJeu()) );
	    	
	        for(LabelValue labelValueBean:form.getJeuxForm().getDoubleListe().getDroiteCols())
	        	vo.getJeux().add(Long.valueOf(labelValueBean.getValue()));

    	} catch (ParseException e) {
    		throw new ValueObjectMapperException(e);
		} catch (IllegalAccessException e) {
			throw new ValueObjectMapperException(e);
		} catch (InvocationTargetException e) {
			throw new ValueObjectMapperException(e);
		} catch (NoSuchMethodException e) {
			throw new ValueObjectMapperException(e);
		}
    }

    public static void convert(MiseEvaluationVO vo, MiseEvaluationForm form, Locale locale) throws ValueObjectMapperException {

    	try {
    		Map map = BeanUtils.describe(vo);
            BeanUtils.populate(form, map);
            form.setMiseMinimum(CurrencyFormat.format(vo.getMiseMinimum(),locale));
	    	form.setMiseMoyenne(CurrencyFormat.format(vo.getMiseMoyenne(),locale));
	    	form.setMiseMaximum(CurrencyFormat.format(vo.getMiseMaximum(),locale));
	    	form.setGainPerte(CurrencyFormat.format(vo.getGainPerte(),locale));
	    	form.setMiseTotal(CurrencyFormat.format(vo.getMiseTotal(),locale));
	    	form.setTempsJeuTotal(CurrencyFormat.format(vo.getTempsJeuTotal(),locale));
	    	form.getJeuxForm().setTypeJeu( String.valueOf(vo.getTypeJeu()) );

    	} catch (IllegalAccessException e) {
			throw new ValueObjectMapperException(e);
		} catch (InvocationTargetException e) {
			throw new ValueObjectMapperException(e);
		} catch (NoSuchMethodException e) {
			throw new ValueObjectMapperException(e);
		}
    }

    /**
     * Convertit une structure Evaluation représentant l'évaluation dans le
     * model d'affaire de l'application, en une structure EvaluationHtmlForm,
     * représentant les informations du formulaire d'évaluation.
     *
     * @param suivi Structure contenant les informations de l'évaluation.
     * @param form Structure contenant les informations relative au formulaire
     * du suivi.
     */
    public static void convertEvaluation(CardexAuthenticationSubject subject, Evaluation evaluation, EvaluationHtmlForm form, Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(evaluation);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateDebutEval");
            map.remove("dateFinEval");
            map.remove("dateCreation");
            map.remove("dateModification");
            map.remove("dateEvaluation");
            map.remove("etats");
            map.remove("propos");
            map.remove("misesEvaluation");
            BeanUtils.populate(form, map);
            form.setDateDebutEval(TimestampFormat.format(
            		evaluation.getDateDebutEval(), locale, true));
            form.setDateFinEval(TimestampFormat.format(
            		evaluation.getDateFinEval(), locale, true));
            form.setDateCreation(TimestampFormat.format(
            		evaluation.getDateCreation(), locale, true));
            form.setDateModification(TimestampFormat.format(
            		evaluation.getDateModification(), locale, true));
            form.setDateEvaluation(TimestampFormat.format(
            		evaluation.getDateEvaluation(), locale, true));
            form.initDoubleListeEtat(subject, evaluation.getEtats());
            form.initDoubleListePropos(subject, evaluation.getPropos());

        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (BusinessResourceException e) {
			throw new ValueObjectMapperException(e);
		}
    }
    
    public static void convert(CardexAuthenticationSubject subject, MiseEvaluationVO vo, MiseEvaluationForm form, Locale locale) throws ValueObjectMapperException {

        try {
            Map map = BeanUtils.describe(vo);
            BeanUtils.populate(form, map);

            form.getJeuxForm().initDoubleListe(subject, StringUtils.valueOf(vo.getJeux()));
            
            form.setMiseMinimum(CurrencyFormat.format(vo.getMiseMinimum(),locale));
            form.setMiseMoyenne(CurrencyFormat.format(vo.getMiseMoyenne(),locale));
            form.setMiseMaximum(CurrencyFormat.format(vo.getMiseMaximum(),locale));
            form.setGainPerte(CurrencyFormat.format(vo.getGainPerte(),locale));
            form.setMiseTotal(CurrencyFormat.format(vo.getMiseTotal(),locale));
            form.setTempsJeuTotal(CurrencyFormat.format(vo.getTempsJeuTotal(),locale));
            
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (BusinessResourceException e) {
			throw new ValueObjectMapperException(e);
		}
    }

    /**
     * Convertis une structure Urgence représentant le service d'urgence dans le
     * model d'affaire de l'application, en une structure UrgenceHtmlForm,
     * représentant les informations du formulaire de service d'urgence.
     *
     * @param adresse Structure contenant les informations de l'urgence.
     * @param form Structure contenant les informations relative au formulaire
     * de l'urgence.
     */
    public static void convertUrgence(Urgence urgence, UrgenceHtmlForm form,
            Locale locale) throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(urgence);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dossier");
            BeanUtils.populate(form, map);
            form.setDateCreation(TimestampFormat.format(
            		urgence.getDateCreation(), locale, true));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        }
    }

    /**
     * Convertis une structure UrgenceHtmlForm, représentant les informations
     * du formulaire de l'adresse, en une structure Urgence représentant
     * l'urgence dans le modèle d'affaire de l'application.
     *
     * @param form Structure contenant les informations relative au formulaire
     * du service d'urgence.
     * @param adresse Structure contenant les informations de l'Urgence.
     */
    public static void convertUrgenceHtmlForm(UrgenceHtmlForm form,
    		Urgence urgence, Locale locale)throws ValueObjectMapperException {
        try {
            Map map = BeanUtils.describe(form);
            // Les attributs suivants doivent être retirés du map, puisqu'ils
            // sont des types complexes (Timestamp) et qu'ils
            // ne peuvent être convertis par la méthode "populate" de BeanUtils.
            map.remove("dateCreation");
            map.remove("dossier");
            BeanUtils.populate(urgence, map);
            urgence.setDateCreation(TimestampFormat.parse(
                    form.getDateCreation(), locale, false));
        } catch (IllegalAccessException iae) {
            throw new ValueObjectMapperException(iae);
        } catch (InvocationTargetException ite) {
            throw new ValueObjectMapperException(ite);
        } catch (NoSuchMethodException nsme) {
            throw new ValueObjectMapperException(nsme);
        } catch (ParseException pe) {
            throw new ValueObjectMapperException(pe);
        }
    }

}
