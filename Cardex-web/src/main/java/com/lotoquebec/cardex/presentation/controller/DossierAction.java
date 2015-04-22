/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.BilletBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.ConsignationVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.EvaluationVO;
import com.lotoquebec.cardex.business.vo.FrequenceVisitesVO;
import com.lotoquebec.cardex.business.vo.InscriptionVO;
import com.lotoquebec.cardex.business.vo.JeuxVO;
import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.business.vo.PartageVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.business.vo.SuiviVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.NarrationBaliseUtil;
import com.lotoquebec.cardex.integration.dao.SujetInteretGalerieCache;
import com.lotoquebec.cardex.presentation.controller.util.tri.SousCategorieComparator;
import com.lotoquebec.cardex.presentation.model.CriteresRechercheDossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.BilletForm;
import com.lotoquebec.cardex.presentation.model.form.ConsignationForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.EvaluationForm;
import com.lotoquebec.cardex.presentation.model.form.FrequenceVisitesForm;
import com.lotoquebec.cardex.presentation.model.form.InscriptionForm;
import com.lotoquebec.cardex.presentation.model.form.JeuxForm;
import com.lotoquebec.cardex.presentation.model.form.LienForm;
import com.lotoquebec.cardex.presentation.model.form.MiseEvaluationForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.PartageForm;
import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SousCategorieForm;
import com.lotoquebec.cardex.presentation.model.form.SousCategoriesForm;
import com.lotoquebec.cardex.presentation.model.form.SuiviForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.UrgenceForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.BilletOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.ConsignationOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierLiaisonTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.EvaluationOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.InscriptionOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.PartageOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SuiviOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.UrgenceOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeOngletTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleAbreviationSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.presentation.util.AideController;
import com.lotoquebec.cardexCommun.rapport.PDFImpressionRapport;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ViderCacheUtils;
import com.lq.std.conf.impl.AppConfig;

/**
 * Cette classe g?re les ?v?nements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.74 $, $Date: 2002/04/30 17:47:53 $
 */
public class DossierAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * le panneau de recherche des dossiers.  L'application affiche le panneau de mise ? jour.
     * L'utilisateur a pr?alablement saisie les informations  les donn?es relatives ? l'identification
     * du dossier.
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> site d'origine (site de l'utilisateur)
     * <ul> statut (actif)
     * <ul> Fond? (ind?termin?)
     * <ul> Date de l'assignation (date du jour)
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Cr?ation d'un nouveau dossier");

        ActionErrors    errors = new ActionErrors();
        DossierForm dossierForm = (DossierForm) form;
        Dossier         dossier     = new DossierVO();
        CardexUser      user = (CardexUser) subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();

        try {
            DossierBusinessDelegate dossierDelegate =
                new DossierBusinessDelegate();

            String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

            // Valeurs par d?faut
            dossierForm.init(subject);
            dossierForm.resetOnglets();
            dossierForm.setSiteOrigine(String.valueOf(user.getSite()));
            dossierForm.setStatut(String.valueOf(GlobalConstants.Statut.DOSSIER_ACTIF));
            dossierForm.setFonde(GlobalConstants.Fonde.INDETERMINE);
            dossierForm.setDateAssignation(currentDate);
            dossierForm.setConfidentialite(Long.toString(privilege.getNiveauConfidentialite()));
            dossierForm.setIntervenant(user.getCode());
            String defaultGenre = (String)request.getSession().getAttribute(GlobalConstants.RechercheList.DOSSIER_GENRE_DEFAULT);
            String defaultNature = (String)request.getSession().getAttribute(GlobalConstants.RechercheList.DOSSIER_NATURE_DEFAULT);
            dossierForm.setEntite(String.valueOf(user.getEntite()));
           	dossierForm.setGenre(defaultGenre);
           	dossierForm.setNature(defaultNature);
            dossierForm.setDateDebut(currentDate);
            dossierForm.assignerConfidentialiteDefaut(subject);
            //Pour les dossiers Sujet d'int?r?t, on met les valeurs Fond? ? Oui,
            //Confidentialit? ? 1 et Origine ? Enqu?tes pour ?viter les oublis.
            if(defaultGenre.equals(String.valueOf(GlobalConstants.Genre.SUJETS_INTERET ))){
            	dossierForm.setFonde(GlobalConstants.Fonde.OUI);
            	dossierForm.setOrigine(GlobalConstants.Origine.ENQUETE);
            }
            // Dans la cr?ation des dossiers, tous les dossiers cr??s sous le
            // genre S?curit? doivent ?tre mis en confidentialit? 1 mais il
            // sera toujours modifiable.  Pour les technicien de surveillance.
            if (Long.valueOf(GlobalConstants.Secteur.TECHNICIEN_SURVEILLANCE) == user.getSecteur()
            && defaultGenre.equals(GlobalConstants.Genre.SECURITE )){
            	dossierForm.setConfidentialite(Long.toString(GlobalConstants.Confidentialite.UN));
            }

            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            dossierForm.setInscription(dossierDelegate.isAvecInscription(subject,dossier));
            dossierForm.setModifiable(true);
            dossierForm.setNew(true);
            if (!dossierForm.isInscription()){
              dossierForm.setDateDebut(currentDate);
            }

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton OK dans
     * le panneau de cr?ation d'un dossier.  Le nouveau dossier est enregistr? dans le
     * cardex et l'?cran de mise a jour du dossier est affich?.
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> Hi?archie (Niveau d'authorit? de l'utilisateur)
     * <ul> Num?ro de cardex ("                  *")
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward save(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Sauvegarde de la cr?ation d'un nouveau dossier");

        ActionErrors    errors = new ActionErrors();
        DossierHtmlForm dossierForm = (DossierHtmlForm) form;
        CardexPrivilege privilege =
            (CardexPrivilege) subject.getPrivilege();
        Dossier         newDossier = new DossierVO();
        DossierForm newDossierForm = (DossierForm) form;

        try {
        	verifierToken(request);
            DossierBusinessDelegate dossierDelegate =
                new DossierBusinessDelegate();

            ValueObjectMapper.convertDossierHtmlForm(newDossierForm, newDossier,
                    subject.getLocale());

            // Valeurs par d?faut
            newDossier.setHierarchie(privilege.getNiveauAuthorite());
            newDossier.setNumeroCardex(GlobalConstants.NumeroCardex.DEFAULT);
            log.debug("Dossier ? cr?er : " + newDossier);
            newDossier.setNouveau(true);
            Dossier criteria = dossierDelegate.create(subject,
                                                      newDossier);

            log.debug("# Cl? de dossier retourn? : "
                      + newDossier.getCle());

			//V?rification d'un mandat PSU associ? ? la recherche d'un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(criteria.getNumeroCardex());
			if(StringUtils.isNotEmpty(dossierForm.getNumeroDossier())){
				psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			}
			psuMandat.setCategorie(dossierForm.getCategorie());
			psuMandat.setFonde(dossierForm.getFonde());
			psuMandat.setSiteCible(dossierForm.getSiteOrigine());
			psuMandat.setIntervenant(dossierForm.getIntervenant());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.AJOUT);

            populateDossierForm(subject, criteria, newDossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement est appel? au lieu de la m?thode showAcces afin d'?viter qu'un enregistrement
     * ne soit inscrit dans la table AC_ACCES.  En mode Web, chaque retour ? un dossier (par
     * exemple, apr?s avoir inscrit une narration) se traduit par une relecture du dossier.
     * Cela se traduit chaque fois par un nouvel enregistrement Select dans la table
     * AC_ACCES.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Retour ? un dossier");

        ActionMessages errors = new ActionMessages();


        try {
            DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();
            DossierForm dossierForm = (DossierForm) form;
            Dossier     dossier     = new DossierVO();
            log.debug("Dossier lu: " + dossierForm.toString());

            if (dossierForm.getMotPasse().equals(dossierForm.getConfirmationMotPasse()) ) {
              ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());
              dossier = dossierDelegate.find(subject, dossier);
              ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
              populateDossierFormShow(subject, dossier, dossierForm);
              log.debug("Dossier trouv?: " + dossier.toString());
              log.debug("Onglet : " + dossierForm.getReference7());
              request.getSession().setAttribute(GlobalConstants.MotDePasse.DOSSIER_ATTEMPS,new Integer(0));
              request.getSession().setAttribute("dossier",dossierForm);
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.DOSSIER_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());
                dossier = dossierDelegate.find(subject, dossier);
                log.debug("Dossier prot?g?: " + dossier.toString());
                ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
                populateDossierForm(subject, dossier, dossierForm);
                dossierForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr?mentation du nombre d'essaie
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.DOSSIER_ATTEMPS,nbOfAttempsInteger);

                return mapping.findForward("motpasse");
              }else {
                //On invalide la session utilisateur si le maximum d'essaie
                //de mot de passe est atteint
                request.getSession().invalidate();
                return mapping.findForward("session");
              }
            }


        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }


    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur l'icone de visualisation(loupe)  dans
     * le panneau de recherche des dossiers.  L'application affiche le panneau de mise ? jour
     * du dossier s?lectionn? dans la liste de r?sultats de l'?cran de recherche.
     * Une inscription est ?galement g?n?r?e dans la table AC_ACCES, s'il y a lieu.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showAcces(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Acc?s ? un dossier");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate dossierDelegate =
                new DossierBusinessDelegate();
            DossierForm dossierForm = (DossierForm) form;
            Dossier dossier = new DossierVO();

            log.debug("Dossier recherch?: " + dossierForm.toString());

            if (AideController.isNullOrEquals(dossierForm.getMotPasse(),dossierForm.getConfirmationMotPasse()) ) {
              ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());
              dossier = dossierDelegate.findAcces(subject, dossier);
              log.debug("Dossier trouv?: " + dossier.toString());
              dossierForm.init(subject);
              ValueObjectMapper.convertDossier(dossier, dossierForm, subject.getLocale());
              populateDossierFormShow(subject, dossier, dossierForm);

              request.getSession().setAttribute(GlobalConstants.MotDePasse.DOSSIER_ATTEMPS,new Integer(0));
			  //V?rification d'un mandat PSU associ? ? la consultation d'un dossier
			  PSUMandatForm psuMandat = new PSUMandatForm();
			  psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			  psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			  //System.out.println("dossier : " + dossierForm.getNumeroCardex().toString());
			  PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.CONSULTATION);
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.DOSSIER_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());
                dossier = dossierDelegate.findAcces(subject, dossier);
                log.debug("Dossier prot?g?: " + dossier.toString());
                ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
                populateDossierForm(subject, dossier, dossierForm);
                dossierForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE, error);
                  saveErrors(request, errors);
                }

                //Incr?mentation du nombre d'essaie
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.DOSSIER_ATTEMPS,nbOfAttempsInteger);

                return mapping.findForward("motpasse");
              }else {
                //On invalide la session utilisateur si le maximum d'essaie
                //de mot de passe est atteint
                request.getSession().invalidate();
                return mapping.findForward("session");
              }
            }


        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
        	vome.printStackTrace();
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur l'icone d'ajout de lien  dans
     * l'onglet dossier de l'?cran de consultation d'un dossier.  L'application affiche l'?cran
     * de recherche de dossier en mode liaison .
     *
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> Genre (Genre s?lectionn? dans le menu principal)
     * <ul> Nature (Nature s?lectionn? dans le menu principal)
     * <ul> Entit? (Entit? de l'utilisateur)
     * <ul> Site Origine  (Site de l'utilisateur)
     * </li>
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showRechercheLiaisonDossier(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Acc?s ? l'ecran de recherche dossier liaison.");

        ActionMessages errors = new ActionMessages();
        CriteresRechercheDossierForm rechercheDossierForm = new CriteresRechercheDossierForm();
        CardexUser user = (CardexUser) subject.getUser();
        try {

	        // Valeurs par d?faut
	        rechercheDossierForm.init(subject);
	        rechercheDossierForm.setEntite(String.valueOf(user.getEntite()));
	        rechercheDossierForm.setSiteOrigine(String.valueOf(user.getSite()));
	        request.getSession().setAttribute(GlobalConstants.RechercheList.LIAISON_DOSSIER_GENRE_DEFAULT,"");
	        request.getSession().setAttribute(GlobalConstants.RechercheList.LIAISON_DOSSIER_NATURE_DEFAULT,"");

	        if (form instanceof DossierHtmlForm) {
	            DossierHtmlForm dossierForm = (DossierHtmlForm)form;
	            rechercheDossierForm.setDossier(dossierForm);
	            //Le r?le n'est pas n?cessaire entre 2 dossiers
	            rechercheDossierForm.setLienRoleRequis(false);
	        }else if (form instanceof SujetHtmlForm) {
	            SujetHtmlForm sujetForm = (SujetHtmlForm)form;
	            rechercheDossierForm.setSujet(sujetForm);
	        }else if (form instanceof VehiculeHtmlForm) {
	            VehiculeHtmlForm vehiculeForm = (VehiculeHtmlForm)form;
	            rechercheDossierForm.setVehicule(vehiculeForm);
	        }else if (form instanceof SocieteHtmlForm) {
	            SocieteHtmlForm societeForm = (SocieteHtmlForm)form;
	            rechercheDossierForm.setSociete(societeForm);
	        }else {
	            log.error("L'objet source de la liaison dossier n'est pas de type valide(sujet,societe,dossier,vehicule)");
	            return mapping.findForward("error");
	        }

	        request.getSession().setAttribute("rechercheDossier",rechercheDossierForm);

            ValueListHandler completeList = new ValueListHandler();
            completeList.setList(new ArrayList());

            rechercheDossierForm.setListeResultat( new ArrayList() );
            rechercheDossierForm.getListeResultat().assignerTrierDefault(DossierLiaisonTrieListe.CLE_DATE_DEBUT, true, new DossierLiaisonTrieListe());

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur s?lectionne une valeur dans une liste
     * d?roulante qui est utilis? comme crit?re de filtrage pour d'autre liste d?roulante
     * dans l'?cran de recherche de dossier .
     * <p>
     * Les listes d?roulantes concern?es sont:
     * <li>
     * <ul> Site d'origine (selon l'Entit?)
     * <ul> Site applicable (selon l'Entit?)
     * <ul> Genre (selon l'Entit?)
     * <ul> Nature (selon le Genre)
     * <ul> Type (selon la Nature)
     * <ul> Cat?gorie (selon le Type)
     * <ul> Intervenant (selon le site)
     * <ul> Statut (des dossiers)
     * <ul> Sites (selon l'Entit?)
     * <ul> Origine (selon l'Entit?)
     * <ul> Abr?viation des sites (selon l'Entit?)
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public ActionForward refreshRechercheDossier(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.debug("Refresh ecran de recherche dossier");

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur s?lectionne une valeur dans une liste
     * d?roulante qui est utilis? comme crit?re de filtrage pour d'autre liste d?roulante
     * dans l'?cran de consultation de dossier .
     * <p>
     * Les listes d?roulantes concern?es sont:
     * <li>
     * <ul> Site d'origine (selon l'Entit?)
     * <ul> Site applicable (selon l'Entit?)
     * <ul> Genre (selon l'Entit?)
     * <ul> Nature (selon le Genre)
     * <ul> Type (selon la Nature)
     * <ul> Cat?gorie (selon le Type)
     * <ul> Intervenant (selon le site)
     * <ul> Statut (des dossiers)
     * <ul> Sites (selon l'Entit?)
     * <ul> Origine (selon l'Entit?)
     * <ul> Abr?viation des sites (selon l'Entit?)
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward rafraichirConsultation(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.debug("Refresh ecran de recherche dossier");
		ActionMessages errors = new ActionMessages();

        try{
        	DossierForm         dossierForm = (DossierForm) form;
			Dossier                 dossier = new DossierVO();
			ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
					subject.getLocale());
			log.debug("Dossier trouv?: " + dossier.toString());
			populateDossierFormShow(subject, dossier, dossierForm);
	        request.getSession().setAttribute("dossier", dossierForm);
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
    }

    public ActionForward resetDossier(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		log.debug("Refresh ecran de recherche dossier");

		ActionMessages errors = new ActionMessages();

		try {
			DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();
			DossierForm         dossierForm = (DossierForm) form;
			Dossier                 dossier = new DossierVO();
			ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
					subject.getLocale());
			dossier = dossierDelegate.findAcces(subject, dossier);
			log.debug("Dossier trouv?: " + dossier.toString());
			ValueObjectMapper.convertDossier(dossier, dossierForm, subject
					.getLocale());
			populateDossierFormShow(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);
			request.getSession().setAttribute(
					GlobalConstants.MotDePasse.DOSSIER_ATTEMPS, new Integer(0));
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton OK dans
     * le panneau de mise ? jour d'un dossier.  Les modifiactions du dossier est enregistr? dans le
     * cardex et l'?cran de mise a jour du dossier est affich?.
     * <p>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward update(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Mise ? jour d'un dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate dossierDelegate =
                new DossierBusinessDelegate();
            Dossier                 dossier = new DossierVO();
            DossierForm         dossierForm = (DossierForm) form;
            ValueObjectMapper.convertDossierHtmlForm(dossierForm,
                    dossier, subject.getLocale());

    	    log.debug("Mise ? jour du dossier: "
                      + dossier.toString());
    	    List<BusinessMessage> businessMessages = dossierDelegate.preSauvegardeMessage(subject, dossier);
    	    ajouterActionMessage(businessMessages, errors, request);
    	    dossierDelegate.update(subject, dossier);

			//V?rification d'un mandat PSU associ? ? la recherche d'un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossier.getNumeroCardex());
			if(StringUtils.isNotEmpty(dossierForm.getNumeroDossier())){
				psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			}
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.MISE_A_JOUR);

            populateDossierForm(subject, dossier, dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton AJOUT dans
     * l'onglet pi?ce jointe d'un dossier.  L'ajout de la pi?ce jointe est enregistr?
     * dans le cardex et l'?cran du dossier est affich?.
     * <p>
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward ajouterPieceJointe(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {

        log.debug("Ajour d'une pi?ce jointe");

        ActionMessages errors = new ActionMessages();

        try
        {
            verifierToken(request);
            DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();
            Dossier dossier = new DossierVO();
            DossierForm dossierForm = (DossierForm) form;
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier, subject.getLocale());
            PhotoForm photoForm = dossierForm.getAjoutPieceJointe();

            // Est ce que la taille du fichier exc?de 10 Mo pour les pi?ces jointes
            if (photoForm.isTaillePieceAccepte() == false) {
                log.error("La taille du fichier est sup?rieure ? 10 Mo.");
                throw (new BusinessRuleExceptionHandle("erreur_fichier_document")).getBusinessException();
            }
            Photo photo = obtenirPhoto(subject, dossierForm, photoForm);

            dossierDelegate.addLienPieceJointe(subject, dossier, photo);

            populateDossierForm(subject, dossier, dossierForm);

            return mapping.findForward("success");
        }
        catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        }
        catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        }
        catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton ?puration dans
     * le panneau de recherche des dossiers.  L'?puration consiste ? supprimer tous les
     * dossiers donc le niveau de confidentialit? est 8.
     * <p>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward delete(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException, DAOException,
                                ServletException {
        log.debug("?puration des dossiers");

        ActionMessages errors = new ActionMessages();
        ResultSet resultSet = null;
        Connection connection = null;
        try {
        	verifierToken(request);
        	//Avant de proc?der ? l'?puration, on sauvegardes les donn?es dans un rapport ? la demande des v?rificateurs.
        	RapportBusinessDelegate rapportDelegate = new RapportBusinessDelegate();
        	Map parameters = new HashMap();
        	connection = DAOConnection.getInstance().getConnection(subject);
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk_mm");
    		String chemin = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_EPURATION);
    		String dateRapport = dateFormat.format(new Date());
			CardexUser utilisateur = (CardexUser)subject.getUser();
    		ListeCache cache = ListeCache.getInstance();
			String siteDescription = cache.obtenirLabel(subject, String.valueOf(utilisateur.getSite()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, utilisateur.getEntite(), GlobalConstants.ActionSecurite.SELECTION));
    		String nomRapport = chemin+"Dossiers à épurer "+ siteDescription + " (" + dateRapport+").pdf";
    		InputStream gabarit = getClass().getClassLoader().getResourceAsStream(RapportsConfiguration.RAPPORT_EPURATION_DOSSIERS);
			log.debug("Sauvegarder dossiers à épurer");

			long site = utilisateur.getSite();
			resultSet = rapportDelegate.rapportEpuration(site, connection, "CARDEX_RAPPORT.SP_RAP_DO_EPURATION");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.debug(context.getRealPath("/rapports/"));
			ServletContext context = request.getSession().getServletContext();
            parameters.put("REPORT_CONNECTION",connection);
	        parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
			parameters.put("UTILISATEUR", utilisateur.getCode());
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
			// Sauvegarde dans un fichier
			log.debug("Épuration des dossiers (Sauvegarde dans un fichier)");
			(new PDFImpressionRapport()).impression(nomRapport, print);
			//On proc?de ensuite ? l'?puration
        	DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();
            //dossierDelegate.delete(subject);
            //Apr?s la suppression, on vide la liste des r?sultats.
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = new CriteresRechercheDossierForm();
            criteresRechercheDossierHtmlForm.init(subject);
            CardexUser user = (CardexUser) subject.getUser();
            criteresRechercheDossierHtmlForm.setEntite(String.valueOf(user.getEntite()));
            criteresRechercheDossierHtmlForm.setSiteOrigine(String.valueOf(user.getSite()));
            criteresRechercheDossierHtmlForm.setGenre(request.getParameter(GlobalConstants.RechercheDossier.GENRE));
            criteresRechercheDossierHtmlForm.setNature(request.getParameter(GlobalConstants.RechercheDossier.NATURE));
            request.getSession().setAttribute("rechercheDossier",criteresRechercheDossierHtmlForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
		} catch (JRException e) {
			e.printStackTrace();
            return mapping.findForward("error");
		}
        finally {
				if(resultSet != null) {
					try {
							resultSet.close();
				    } catch (java.sql.SQLException e) {
	                    throw new DAOException(e);
			        }
		        }
	 		    if (connection != null) {
	                try{
				         if(!connection.getAutoCommit())
				         {
				            connection.rollback();
				         }
	 		           	   connection.close();
	                } catch (SQLException e) {
	                    throw new DAOException(e);
	                }
	 		    }
	        } //finally
}

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton effacer
     * crit?res dans l'?cran de recherche de dossier.  L'application affiche l'?cran
     * de recherche de dossier avec les valeurs par d?faut.
     * Les valeurs par d?faut sont :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s?lection de l'?cran principal)
     * <ul>Nature (selon la s?lection de l'?cran principal)
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Param?tres de recherche par d?fault de dossier");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = (CriteresRechercheDossierForm) form;
            CardexUser user = (CardexUser) subject.getUser();
            CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();

            criteresRechercheDossierHtmlForm.init(subject);
            // Valeurs par d?faut
            criteresRechercheDossierHtmlForm.setEntite(String.valueOf(user.getEntite()));
            criteresRechercheDossierHtmlForm.setSiteOrigine(String.valueOf(user.getSite()));

            String defaultGenre = (String)request.getSession().getAttribute(GlobalConstants.RechercheList.DOSSIER_GENRE_DEFAULT);
            String defaultNature = (String)request.getSession().getAttribute(GlobalConstants.RechercheList.DOSSIER_NATURE_DEFAULT);
            criteresRechercheDossierHtmlForm.setGenre(defaultGenre);
            criteresRechercheDossierHtmlForm.setNature(defaultNature);

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierHtmlForm,criteresRechercheDossier,subject.getLocale());

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton effacer
     * crit?res dans l'?cran de recherche de dossier.  L'application affiche l'?cran
     * de recherche de dossier avec les valeurs par d?faut.
     * Les valeurs par d?faut sont :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward resetSearchDefaultLiaison(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Param?tres de recherche par d?fault de dossier en mode liaison");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = (CriteresRechercheDossierForm) form;
            CardexUser user = (CardexUser) subject.getUser();
            CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
            DossierHtmlForm dossierHtmlForm = criteresRechercheDossierHtmlForm.getDossier();
            SujetHtmlForm sujetHtmlForm = criteresRechercheDossierHtmlForm.getSujet();
            VehiculeHtmlForm vehiculeHtmlForm = criteresRechercheDossierHtmlForm.getVehicule();
            SocieteHtmlForm societeHtmlForm = criteresRechercheDossierHtmlForm.getSociete();


            criteresRechercheDossierHtmlForm.init(subject);
            criteresRechercheDossierHtmlForm.getListeResultat().vider();
            criteresRechercheDossierHtmlForm.setDossier(dossierHtmlForm);
            criteresRechercheDossierHtmlForm.setSujet(sujetHtmlForm);
            criteresRechercheDossierHtmlForm.setVehicule(vehiculeHtmlForm);
            criteresRechercheDossierHtmlForm.setSociete(societeHtmlForm);

            // Valeurs par d?faut
            criteresRechercheDossierHtmlForm.setEntite(String.valueOf(user.getEntite()));
            criteresRechercheDossierHtmlForm.setSiteOrigine(String.valueOf(user.getSite()));

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierHtmlForm,criteresRechercheDossier,subject.getLocale());

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque dans le menu principal, l'utilisateur a choisi de rechercher un dossier
     * pour une cat?gorie et une nature donn?e. L'application affiche alors le panneau de
     * recherche des dossiers.
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s?lection de l'?cran principal)
     * <ul>Nature (selon la s?lection de l'?cran principal)
     * </li>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Recherche par d?fault de dossier");

        ActionMessages errors = new ActionMessages();
        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = (CriteresRechercheDossierForm) form;
            CardexUser user = (CardexUser) subject.getUser();
            CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
            // Valeurs par d?faut
            criteresRechercheDossierHtmlForm.init(subject);
            criteresRechercheDossierHtmlForm.setEntite(String.valueOf(user.getEntite()));
            criteresRechercheDossierHtmlForm.setSiteOrigine(String.valueOf(user.getSite()));
            criteresRechercheDossierHtmlForm.setGenre(request.getParameter(GlobalConstants.RechercheDossier.GENRE));
            criteresRechercheDossierHtmlForm.setNature(request.getParameter(GlobalConstants.RechercheDossier.NATURE));
            request.getSession().setAttribute(GlobalConstants.RechercheList.DOSSIER_GENRE_DEFAULT,criteresRechercheDossierHtmlForm.getGenre());
            request.getSession().setAttribute(GlobalConstants.RechercheList.DOSSIER_NATURE_DEFAULT,criteresRechercheDossierHtmlForm.getNature());

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierHtmlForm,criteresRechercheDossier,subject.getLocale());

            // Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Dossier> results = delegate.selectDefault(subject,criteresRechercheDossier);

            assignerResultatDossier(subject, criteresRechercheDossierHtmlForm, results);
    		criteresRechercheDossierHtmlForm.getListeResultat().assignerTrierDefault(DossierTrieListe.CLE_NUMERO_CARDEX, true, new DossierTrieListe());

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);

            return mapping.findForward("error");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque dans l'?cran de recherche de dossier, l'utilisateur a choisi
     * de rechercher un dossier selon des crit?res diff?rents. L'application affiche alors le panneau de
     * recherche des dossiers avec les r?sultats de la nouvelle recherche.
     * <p>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward search(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Recherche de dossier");
        ListeCache listeCache = ListeCache.getInstance();
        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate =  new DossierBusinessDelegate();
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = (CriteresRechercheDossierForm) form;
            CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
            criteresRechercheDossierHtmlForm.getListeResultat().vider();
            //
            // Cas sp?cial pour la recherche par num?ro de cardex
            //
            String codeSite = criteresRechercheDossierHtmlForm.getNumeroCardex().getCodeSite();
            String siteAbreviation = listeCache.obtenirLabel(subject, codeSite, new TableValeurCleAbreviationSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.RECHERCHE));
            criteresRechercheDossierHtmlForm.getNumeroCardex().setSite(siteAbreviation);

            if (criteresRechercheDossierHtmlForm.getNumeroCardex().getSite().trim().length() != 0 && criteresRechercheDossierHtmlForm.getNumeroCardex().getSite() != null) {
            	if ((StringUtils.isEmpty(criteresRechercheDossierHtmlForm.getNumeroCardex().getDate().trim()))
            	&& (StringUtils.isEmpty(criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence()))) {
            		criteresRechercheDossierHtmlForm.getNumeroCardex().setSite(""); //On vide pour la recherche.
            	}else{
		              if (criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence().trim().length() < 4
		              && criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence() != null) {
		            	  criteresRechercheDossierHtmlForm.getNumeroCardex().setSequence("%"+criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence().trim());
		              }else if (criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence() == null) {
		            	  criteresRechercheDossierHtmlForm.getNumeroCardex().setSequence("%");
		              }
            	}
            }
			//V?rification d'un mandat PSU associ? ? la recherche d'un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			//On v?rifie si la recherche inclut un nom d'intervenant
			if((!OracleDAOUtils.isEmpty(criteresRechercheDossierHtmlForm.getCategorie())) && (!OracleDAOUtils.isEmpty(criteresRechercheDossierHtmlForm.getFonde()))){
				psuMandat.setCategorie(criteresRechercheDossierHtmlForm.getCategorie());
				psuMandat.setFonde(criteresRechercheDossierHtmlForm.getFonde());
				if(!OracleDAOUtils.isEmpty(criteresRechercheDossierHtmlForm.getSiteOrigine())){
					psuMandat.setSiteCible(criteresRechercheDossierHtmlForm.getSiteOrigine());
				}
				if(!OracleDAOUtils.isEmpty(criteresRechercheDossierHtmlForm.getIntervenant())){
					psuMandat.setIntervenant(criteresRechercheDossierHtmlForm.getIntervenant());
				}
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.RECHERCHE);
			}else{
				if(!OracleDAOUtils.isEmpty(criteresRechercheDossierHtmlForm.getIntervenant())){
					psuMandat.setIntervenant(criteresRechercheDossierHtmlForm.getIntervenant());
					PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.RECHERCHE);
				}
			}

			// Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
			ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierHtmlForm, criteresRechercheDossier,subject.getLocale());
			log.debug(criteresRechercheDossier.toString());

			// Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
			List<Dossier> results = delegate.select(subject,criteresRechercheDossier);

            assignerResultatDossier(subject, criteresRechercheDossierHtmlForm, results);
    		criteresRechercheDossierHtmlForm.getListeResultat().assignerTrierDefault(DossierTrieListe.CLE_NUMERO_CARDEX, true, new DossierTrieListe());
    		//On remet la valeur par d?faut du sigle
    		criteresRechercheDossierHtmlForm.getNumeroCardex().setCodeSite(codeSite);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            return mapping.findForward("error");
        }
    }

    /**
	 * @param subject
	 * @param criteresRechercheDossierHtmlForm
	 * @param results
	 * @throws IteratorException
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
    private void assignerResultatDossier(CardexAuthenticationSubject subject, CriteresRechercheDossierForm criteresRechercheDossierHtmlForm, ValueListIterator valueListIterator) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		List list = valueListIterator.getNextElements( valueListIterator.getSize() );
    	assignerResultatDossier(subject, criteresRechercheDossierHtmlForm, list);
    }

	private void assignerResultatDossier(CardexAuthenticationSubject subject, CriteresRechercheDossierForm criteresRechercheDossierHtmlForm, List<Dossier> list) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des dossiers dans le composant d'?tat (ActionForm)
		List currentList = new ArrayList();
		Iterator   it = list.iterator();

		while (it.hasNext()) {
		    Dossier dossier = (Dossier)it.next();
		    DossierForm dossierForm = new DossierForm();
		    dossierForm.init(subject);
		    ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
		    dossierForm.assignerValeurDeListe(subject);
		    currentList.add(dossierForm);
		}

		criteresRechercheDossierHtmlForm.setListeResultat( currentList );
	}

    /**
	 * @param subject
	 * @param criteresRechercheDossierHtmlForm
	 * @param results
	 * @throws IteratorException
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
	private void assignerResultatDossierAudit(CardexAuthenticationSubject subject, CriteresRechercheDossierForm criteresRechercheDossierHtmlForm, ValueListIterator results) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des dossiers dans le composant d'?tat (ActionForm)
		Collection list = results.getNextElements( results.getSize() );
		List currentList = new ArrayList();
		Iterator   it = list.iterator();

		while (it.hasNext()) {
		    Dossier dossier = (Dossier)it.next();
		    DossierForm dossierForm = new DossierForm();
		    dossierForm.init(subject);
		    ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
		    dossierForm.assignerValeurDeListe(subject);
		    currentList.add(dossierForm);
		}

		criteresRechercheDossierHtmlForm.setListeResultatAudit( currentList );
	}

	/**
     * <p>
     * Cet ?v?nement surivient lorsque dans l'?cran de recherche de dossier en mode liaison, l'utilisateur a choisi
     * de rechercher un dossier selon des crit?res diff?rents. L'application affiche alors le panneau de
     * recherche des dossiers avec les r?sultats de la nouvelle recherche.
     * <p>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchLiaison(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Recherche de dossier en mode liaison");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate =  new DossierBusinessDelegate();
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = (CriteresRechercheDossierForm) form;
            CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
            ListeCache listeCache = ListeCache.getInstance();
            criteresRechercheDossierHtmlForm.getListeResultat().vider();
            //
            // Cas sp?cial pour la recherche par num?ro de cardex
            //
            String codeSite = criteresRechercheDossierHtmlForm.getNumeroCardex().getCodeSite();
            String siteAbreviation = listeCache.obtenirLabel(subject, codeSite, new TableValeurCleAbreviationSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.RECHERCHE));;
            criteresRechercheDossierHtmlForm.getNumeroCardex().setSite(siteAbreviation);

            if (criteresRechercheDossierHtmlForm.getNumeroCardex().getSite().trim().length() != 0 && criteresRechercheDossierHtmlForm.getNumeroCardex().getSite() != null) {
            	if ((StringUtils.isEmpty(criteresRechercheDossierHtmlForm.getNumeroCardex().getDate().trim()))
            	&& (StringUtils.isEmpty(criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence()))) {
            		criteresRechercheDossierHtmlForm.getNumeroCardex().setSite(""); //On vide pour la recherche.
            	}else{
		              if (criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence().trim().length() < 4
		              && criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence() != null) {
		            	  criteresRechercheDossierHtmlForm.getNumeroCardex().setSequence("%"+criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence().trim());
		              }else if (criteresRechercheDossierHtmlForm.getNumeroCardex().getSequence() == null) {
		            	  criteresRechercheDossierHtmlForm.getNumeroCardex().setSequence("%");
		              }
            	}
            }

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierHtmlForm, criteresRechercheDossier,subject.getLocale());

            // Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Dossier> results = delegate.select(subject,criteresRechercheDossier);

            assignerResultatDossier(subject, criteresRechercheDossierHtmlForm, results);
    		criteresRechercheDossierHtmlForm.getListeResultat().assignerTrierDefault(DossierLiaisonTrieListe.CLE_DATE_DEBUT, true, new DossierLiaisonTrieListe());

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);

            return mapping.findForward("error");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un ?l?ment de r?sultats de recherche des dossiers en mode liaison.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showRole(CardexAuthenticationSubject subject,
                                  ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException,
                                  ServletException {
        log.debug("Choix du type de lien avec un dossier");

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un ?l?ment de r?sultats de recherche des dossiers en mode liaison.
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienDossier(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Liaison d'un dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier                 dossierOrigine = new DossierVO();
            Dossier                 dossierDestination =
                new DossierVO();
            dossierForm.init(subject);

            dossierOrigine.setCle(lienForm.getCleSource());
            dossierOrigine.setSite(lienForm.getSiteSource());
            dossierOrigine.setTypeLien(lienForm.getTypeLien());
            //R?le par d?faut pour ce lien
            dossierOrigine.setRole(GlobalConstants.Role.SANS_OBJET);

            dossierDestination.setCle(lienForm.getCleDestination());
            dossierDestination.setSite(lienForm.getSiteDestination());
            dossierDestination.setTypeLien(lienForm.getTypeLien());
            //R?le par d?faut pour ce lien
            dossierDestination.setRole(GlobalConstants.Role.SANS_OBJET);

            log.debug(lienForm.toString());
            BusinessRuleExceptionHandle businessRuleExceptionHandle = delegate.addLienDossier(subject, dossierOrigine,
                                    dossierDestination);
            populateDossierForm(subject, dossierOrigine, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//R?cup?ration du num?ro de dossier qui vient d'?tre associ?
			Collection dossierListe = dossierForm.getDossiers();
			Iterator it = dossierListe.iterator();
			String numeroCardex = "";
			while (it.hasNext()) {
				DossierForm dossierAssocie= (DossierForm)it.next();
				if(dossierAssocie.getCle().equals(Long.toString(dossierDestination.getCle()))){
					numeroCardex = dossierAssocie.getNumeroCardex().toString();
				}
			}
			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);
            //On v?rifie le message d'avertissement retourn? s'il y a lieu pour pouvoir l'afficher ? l'utilisateur
            if(StringUtils.isNotEmpty(businessRuleExceptionHandle.getBusinessException().getBusinessMessageResult().toString())){
                handleBusinessException(businessRuleExceptionHandle.getBusinessException(), errors, request);
             }

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    public ActionForward addLienDossierNumeroDossier(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.debug("Liaison d'un dossier par le num?ro de dossier");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			DossierBusinessDelegate delegate = new DossierBusinessDelegate();
			DossierForm dossierHtmlForm = (DossierForm) form;
			Dossier dossierOrigine = new DossierVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossierOrigine, subject.getLocale());
			List listdossier = delegate.obtenirDossierNumeroDossier(subject, dossierOrigine, dossierOrigine.getNumeroDossier());

			if (listdossier != null && listdossier.size() > 0){

				if (dossierHtmlForm.isModifiable()
				&& dossierOrigine.getNumeroDossier().equals( dossierHtmlForm.getNumeroDossier() ) == false){
	            	dossierOrigine.setNumeroDossier( dossierHtmlForm.getNumeroDossier() );
	            	delegate.update(subject, dossierOrigine);
	            }
				Iterator iter = listdossier.iterator();

				if (listdossier.size() == 1){
					Dossier dossier = lierDossierEnsemble(subject, dossierOrigine, (Dossier) iter.next());

    				showAcces(subject, mapping, dossierHtmlForm, request, response);

    				verificationMandatPSU(subject, dossierHtmlForm, dossier);
    			}else{
					// Plusieurs choix possibles, il faudrait faire un choix
					dossierHtmlForm.viderListeDossierALier();

					while (iter.hasNext()) {
						Dossier dossier = (Dossier) iter.next();
						DossierForm dossierForm = new DossierForm();
						dossierForm.init(subject);
						ValueObjectMapper.convertDossier(dossier, dossierForm, subject.getLocale());
						dossierForm.assignerValeurDeListe(subject);
						dossierHtmlForm.addDossierALier( dossierForm );
					}
					dossierHtmlForm.trierDossierALierNumeroCardex();

					return mapping.findForward("choisirResultat");
				}
			}else{
				showAcces(subject, mapping, dossierHtmlForm, request, response);
			}

		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return (new ActionForward(mapping.getInput()));
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		}
		catch (ValueObjectMapperException vome) {
			handleValueObjectMapperException(vome, errors, request);
			return (new ActionForward(mapping.getInput()));
		}

		return mapping.findForward("success");
	}

    public ActionForward addLienDossierNumeroCardex(CardexAuthenticationSubject subject,
	ActionMapping mapping, ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws IOException, ServletException {
		log.debug("Liaison d'un dossier par le num?ro de cardex");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			String numeroUnique = request.getParameter(GlobalConstants.Dossier.NUMERO_UNIQUE);
			DossierForm dossierHtmlForm = (DossierForm) form;
			Dossier dossierALier = new DossierVO();
			assignerNumeroUnique(numeroUnique, dossierALier);

			if (dossierALier.getCle() != 0){
				Dossier dossierOrigine = new DossierVO();
	            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossierOrigine, subject.getLocale());

				Dossier dossierLier = lierDossierEnsemble(subject, dossierOrigine, dossierALier);

				verificationMandatPSU(subject, dossierHtmlForm, dossierLier);
			}

			showAcces(subject, mapping, dossierHtmlForm, request, response);

		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return (new ActionForward(mapping.getInput()));
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		}
		catch (ValueObjectMapperException vome) {
			handleValueObjectMapperException(vome, errors, request);
			return (new ActionForward(mapping.getInput()));
		}

		return mapping.findForward("success");
    }

	private void assignerNumeroUnique(String numeroUnique, Dossier dossierCritere) {
		StringTokenizer tokenizer = new StringTokenizer(numeroUnique, "-");
		dossierCritere.setCle( Long.parseLong( tokenizer.nextToken() ) );
		dossierCritere.setSite( Long.parseLong( tokenizer.nextToken() ) );
	}

	private Dossier lierDossierEnsemble(CardexAuthenticationSubject subject, Dossier dossierOrigine, Dossier dossierALier) throws BusinessException, BusinessResourceException {
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		Dossier dossierDestination = new DossierVO();

		dossierDestination.setCle(dossierALier.getCle());
		dossierDestination.setSite(dossierALier.getSite());
		dossierDestination.setTypeLien(0);
		dossierDestination.setRole(GlobalConstants.LienRole.N_A);
		//dossierDestination.setLien();
		//dossierDestination.setLienSite();

		log.debug(dossierALier.toString());
		delegate.addLienDossier(subject, dossierOrigine, dossierDestination);
		return dossierALier;
	}

	/**
	 * @param subject
	 * @param dossierHtmlForm
	 * @param dossier
	 * @throws IOException
	 * @throws ServletException
	 */
	private void verificationMandatPSU(CardexAuthenticationSubject subject, DossierForm dossierHtmlForm, Dossier dossier) throws IOException, ServletException {
		//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un
		// dossier
		PSUMandatForm psuMandat = new PSUMandatForm();
		psuMandat.setNumeroCardex(dossierHtmlForm.getNumeroCardex().toString());
		psuMandat.setNumeroDossier(dossierHtmlForm.getNumeroDossier());
		psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
		psuMandat.setReference1(dossier.getNumeroCardex());
		PSUMandatAction.verificationMandat(subject, psuMandat,
				GlobalConstants.GenreFichier.DOSSIER,
				GlobalConstants.TypeAction.LIAISON);
	}

    /**
	 * <p>
	 * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
	 * liaison d'un ?l?ment de r?sultats de recherche des sujets en mode
	 * liaison.
	 *
	 * @param subject
	 *            Le sujet authentifi?
	 * @param mapping
	 *            L' ActionMapping utils? pour s?lectionner cette instance
	 * @param actionForm
	 *            L'ActionForm bean pour cette requ?te (optionnelle)
	 * @param request
	 *            La requ?te HTTP trait?e
	 * @param response
	 *            La r?ponse HTTP cr??e
	 * @param delegate
	 *            Le business delegate offrant les services d'affaires
	 *
	 * @exception IOException
	 *                si une erreur d'entr?e/sortie survient
	 * @exception ServletException
	 *                si une exception servlet survient
	 */
    public ActionForward addLienSujet(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Liaison d'un sujet");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier             dossier = new DossierVO();
            Sujet               sujet = new SujetVO();
            dossierForm.init(subject);

            dossier.setCle(lienForm.getCleSource());
            dossier.setSite(lienForm.getSiteSource());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));

            sujet.setCle(lienForm.getCleDestination());
            sujet.setSite(lienForm.getSiteDestination());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));

            log.debug(lienForm.toString());
            delegate.addLienSujet(subject, dossier, sujet);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//R?cup?ration du nom du num?ro de fiche sujet qui vient d'?tre associ?
			Collection sujetListe = dossierForm.getSujets();
			Iterator it = sujetListe.iterator();
			String numeroFiche = "";
			while (it.hasNext()) {
				SujetForm sujetAssocie= (SujetForm)it.next();
				if(sujetAssocie.getCle().equals(Long.toString(sujet.getCle()))){
					numeroFiche = sujetAssocie.getNumeroFiche();
				}
			}

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un ?l?ment de r?sultats de recherche des v?hicules en mode liaison.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienVehicule(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Liaison d'un v?hicule");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm  		lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier             dossier = new DossierVO();
            Vehicule            vehicule = new VehiculeVO();
            dossierForm.init(subject);

            dossier.setCle(Long.valueOf(lienForm.getCleSource()));
            dossier.setSite(Long.valueOf(lienForm.getSiteSource()));
            //R?le par d?faut pour ce lien
            dossier.setRole(GlobalConstants.Role.SANS_OBJET);
            vehicule.setCle(Long.valueOf(lienForm.getCleDestination()));
            vehicule.setSite(Long.valueOf(lienForm.getSiteDestination()));
            vehicule.setRole(GlobalConstants.Role.SANS_OBJET);

            log.debug(lienForm.toString());
            delegate.addLienVehicule(subject, dossier,
                                    vehicule);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//R?cup?ration de l'immatriculation du v?hicule qui vient d'?tre associ?
			Collection vehiculeListe = dossierForm.getVehicules();
			Iterator it = vehiculeListe.iterator();
			String immatriculation = "";
			while (it.hasNext()) {
				VehiculeForm vehiculeAssocie= (VehiculeForm)it.next();
				if(vehiculeAssocie.getCle().equals(Long.toString(vehicule.getCle()))){
					immatriculation = vehiculeAssocie.getImmatriculation();
				}
			}

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			psuMandat.setReference1(immatriculation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un ?l?ment de r?sultats de recherche des societes en mode liaison.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienSociete(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Liaison d'un societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier             dossier = new DossierVO();
            Societe               societe = new SocieteVO();
            dossierForm.init(subject);

            dossier.setCle(lienForm.getCleSource());
            dossier.setSite(lienForm.getSiteSource());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));

            societe.setCle(lienForm.getCleDestination());
            societe.setSite(lienForm.getSiteDestination());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));

            log.debug(lienForm.toString());
            BusinessRuleExceptionHandle businessRuleExceptionHandle = delegate.addLienSociete(subject, dossier,
                                    societe);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//R?cup?ration du nom de la soci?t? qui vient d'?tre associ?e
			Collection societeListe = dossierForm.getSocietes();
			Iterator it = societeListe.iterator();
			String nom = "";
			while (it.hasNext()) {
				SocieteForm societeAssocie= (SocieteForm)it.next();
				if(societeAssocie.getCle().equals(Long.toString(societe.getCle()))){
					nom = societeAssocie.getNom();
				}
			}

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);
			//On v?rifie le message d'avertissement retourn? s'il y a lieu pour pouvoir l'afficher ? l'utilisateur
	        if(StringUtils.isNotEmpty(businessRuleExceptionHandle.getBusinessException().getBusinessMessageResult().toString())){
	            handleBusinessException(businessRuleExceptionHandle.getBusinessException(), errors, request);
	         }

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
        return mapping.findForward("success");
    }

    /**
     * <p>
     * Mise ? jour du r?le dans les onglets d'un dossier.
     * Les liens concernent les Sujets, les soci?t?s, les dossiers et les v?hicules.
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLien(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Mise ? jour de la liaison dans un dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm    dossierForm = (DossierForm) form;
            dossierForm.setLien((String)request.getParameter("lien"));
            dossierForm.setLienSite((String)request.getParameter("lienSite"));
            dossierForm.setRole((String)request.getParameter("role"));
            dossierForm.setCle((String)request.getParameter("cleDossier"));
            dossierForm.setSite((String)request.getParameter("siteDossier"));
            Dossier 	dossier = new DossierVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierForm,
                    dossier, subject.getLocale());
            delegate.updateLien(subject, dossier);
            populateDossierForm(subject, dossier, dossierForm);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
        }
        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de cr?ation (liaison) d'une inscription.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienInscription(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Liaison d'un inscription");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            InscriptionForm         inscriptionForm = (InscriptionForm) form;
            Inscription               inscription = new InscriptionVO();
            Dossier             dossier = new DossierVO();
            DossierForm         dossierForm = new DossierForm();

            dossierForm.init(subject);
            dossierForm.setCle(inscriptionForm.getLien());
            dossierForm.setSite(inscriptionForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm,dossier,subject.getLocale());
            ValueObjectMapper.convertInscriptionHtmlForm(inscriptionForm,inscription,subject.getLocale());
            log.debug(inscription.toString());
            delegate.addLienInscription(subject, dossier,inscription);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.INSCRIPTION);
			psuMandat.setReference1(inscriptionForm.getDateInscription());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de suppression de
     * lien dans l'onglet inscription.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienInscription(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Suppression d'un lien inscription");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            InscriptionForm         inscriptionForm = (InscriptionForm) form;
            Inscription               inscription = new InscriptionVO();
            Dossier             dossier = new DossierVO();
            DossierForm         dossierForm = new DossierForm();

            dossierForm.init(subject);
            dossierForm.setCle(inscriptionForm.getLien());
            dossierForm.setSite(inscriptionForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm,dossier,subject.getLocale());
            //ValueObjectMapper.convertInscriptionHtmlForm(inscriptionForm,inscription,subject.getLocale());
            inscription.setCle(Integer.parseInt(inscriptionForm.getCle()));
            inscription.setSite(Integer.parseInt(inscriptionForm.getSite()));

            log.debug(inscription.toString());
            delegate.deleteLienInscription(subject, dossier,
                                    inscription);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.INSCRIPTION);
			psuMandat.setReference1(inscriptionForm.getDateInscription());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

   /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification des jeux.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLiensJeu(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour des liens jeux");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            Dossier dossier = new DossierVO();
            JeuxForm jeuxForm = (JeuxForm)form;
            Jeux jeux = new JeuxVO();
            dossierForm.init(subject);
            dossierForm.setCle(jeuxForm.getLien());
            dossierForm.setSite(jeuxForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertJeuxHtmlForm(jeuxForm,jeux,subject.getLocale());
            delegate.updateLiensJeu(subject,dossier,jeux);
            populateDossierForm(subject,dossier,dossierForm);
            request.getSession().setAttribute("dossier",dossierForm);
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification du partage.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLiensPartage(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour des liens partage");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            Dossier dossier = new DossierVO();
            PartageForm partageForm = (PartageForm)form;
            Partage partage = new PartageVO();
            dossierForm.init(subject);
            dossierForm.setCle(partageForm.getLien());
            dossierForm.setSite(partageForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertPartageHtmlForm(partageForm,partage,subject.getLocale());
            delegate.updateLiensPartage(subject,dossier,partage);
            populateDossierForm(subject,dossier,dossierForm);
            request.getSession().setAttribute("dossier",dossierForm);
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification des sous-cat?gories.
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward updateLiensSousCategories(CardexAuthenticationSubject subject,
    ActionMapping mapping, ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.debug("Mise ? jour des liens sous-cat?gories");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			DossierBusinessDelegate delegate = new DossierBusinessDelegate();
			DossierForm dossierForm = new DossierForm();
			Dossier dossier = new DossierVO();
			SousCategoriesForm sousCategoriesForm = (SousCategoriesForm) form;
			sousCategoriesForm.assignerSousCategorieVO();
			delegate.updateLiensSousCategorie(subject, sousCategoriesForm.getSousCategoriesVO());
			dossierForm.init(subject);
            dossierForm.setCle(String.valueOf(sousCategoriesForm.getSousCategoriesVO().getLien()));
            dossierForm.setSite(String.valueOf(sousCategoriesForm.getSousCategoriesVO().getLienSite()));
            dossier.setCle(sousCategoriesForm.getSousCategoriesVO().getLien());
            dossier.setSite(sousCategoriesForm.getSousCategoriesVO().getLienSite());
			populateDossierForm(subject,dossier,dossierForm);
			request.getSession().setAttribute("dossier",dossierForm);

			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
		return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de suppression de
     * lien dans l'onglet sujet.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienSujet(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Destruction d'un lien sujet");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier             dossier = new DossierVO();
            Sujet               sujet = new SujetVO();

            dossierForm.init(subject);
            dossier.setCle(lienForm.getCleSource());
            dossier.setSite(lienForm.getSiteSource());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));
            dossier.setLien(lienForm.getCle());
            dossier.setLienSite(lienForm.getSite());

            sujet.setCle(lienForm.getCleDestination());
            sujet.setSite(lienForm.getSiteDestination());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));
            sujet.setLien(lienForm.getCle());
            sujet.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienSujet(subject, dossier,
                                    sujet);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			//psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de suppression de
     * lien dans l'onglet v?hicule.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienVehicule(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Destruction d'un lien vehicule");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier             dossier = new DossierVO();
            Vehicule            vehicule = new VehiculeVO();

            dossierForm.init(subject);
            dossier.setCle(lienForm.getCleSource());
            dossier.setSite(lienForm.getSiteSource());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));
            dossier.setLien(lienForm.getCle());
            dossier.setLienSite(lienForm.getSite());

            vehicule.setCle(lienForm.getCleDestination());
            vehicule.setSite(lienForm.getSiteDestination());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));
            vehicule.setLien(lienForm.getCle());
            vehicule.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienVehicule(subject, dossier,
                                    vehicule);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			//psuMandat.setReference1(immatriculation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de suppression de
     * lien dans l'onglet soci?t?.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienSociete(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Destruction d'un lien societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier             dossier = new DossierVO();
            Societe               societe = new SocieteVO();

            dossierForm.init(subject);
            dossier.setCle(lienForm.getCleSource());
            dossier.setSite(lienForm.getSiteSource());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));
            dossier.setLien(lienForm.getCle());
            dossier.setLienSite(lienForm.getSite());

            societe.setCle(lienForm.getCleDestination());
            societe.setSite(lienForm.getSiteDestination());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));
            societe.setLien(lienForm.getCle());
            societe.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienSociete(subject, dossier,
                                    societe);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			//psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de cr?ation (liaison) d'une narration.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Liasion d'une narration ? un dossier.");
        ActionMessages errors = new ActionMessages();
        ActionMessages messages = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            NarrationForm narrationForm = (NarrationForm)form;
            DossierForm dossierForm  = narrationForm.getDossier();
            Dossier dossier = new DossierVO();
            Narration narration = new NarrationVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            narration.setDossier(dossier);
            log.debug("Dossier: " + dossier);
            log.debug("Narration: " + narration);

            NarrationBaliseUtil.assignerMessageSiNarrationANettoyer(messages, narrationForm.getNarrationAvecFormat());
           	delegate.addLienNarration(subject,dossier,narration);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//R?cup?ration de la date de cr?ation de la narration qui vient d'?tre associ?e
			Collection narrationListe = dossierForm.getNarrations();
			Iterator it = narrationListe.iterator();
			String dateCreation = "";
			while (it.hasNext()) {
				NarrationForm narrationAssocie= (NarrationForm)it.next();
				if(narrationAssocie.getCle().equals(Long.toString(narration.getCle()))){
					dateCreation = narrationAssocie.getDateCreation();
				}
			}
			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(dateCreation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);
			//Dans le cas des narratisons, on v?rifie ?galement pour les mots-cl?s.
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			psuMandat.setReference1(narrationForm.getNarrationSansFormat());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.NARRATION, GlobalConstants.TypeAction.AJOUT);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
        	bre.printStackTrace();
			String ancestor = bre.getAncestor().toString();
			ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp?cial d'erreur. Durant la t?che qui reconstruit l'index des narrations
			//les sauvegardes ?chouent. Dans ce cas, un message d'erreur est retourn? et la
			//narration est perdue. Le test suivant permet de d?tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration ? l'?cran avec
			//un message plus appropri?, sans perte de donn?es.
			if((ancestor.indexOf("ORA-29861") > -1) || (ancestor.indexOf("ORA-29875") > -1) || (ancestor.indexOf("ORA-29877") > -1) || (ancestor.indexOf("DRG-10599") > -1)){
				errors.add(Globals.ERROR_KEY, new ActionMessage("cardex_erreur_narration"));

				return mapping.findForward("erreur");
			}else{
				handleBusinessResourceException(bre, errors, request);
				return mapping.findForward("error");
			}
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        } finally{
        	saveMessages(request, messages);
        }

    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de cr?ation (liaison) d'un suivi.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienSuivi(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Liaison d'une suivi ? un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            SuiviForm suiviForm = (SuiviForm)form;
            DossierForm dossierForm  = new DossierForm();
            Dossier dossier = new DossierVO();
            Suivi suivi = new SuiviVO();
            dossierForm.init(subject);
            dossierForm.setCle(suiviForm.getLien());
            dossierForm.setSite(suiviForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            // I10-0153 - Cardex - Validation de dates dans les Suivis
            // Assigner la date de cr?ation de nouveau pour ne pas avoir une
            // date pr?vue avant la date de cr?ation.
            suivi.setDateCreation(new Timestamp(System.currentTimeMillis()));
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + suivi);
            delegate.addLienSuivi(subject,dossier,suivi);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUIVI);
			psuMandat.setReference1(suiviForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

   /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de cr?ation (liaison) d'une consignation.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienConsignation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Liaison d'une consignation ? un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            ConsignationForm consignationForm = (ConsignationForm)form;
            DossierForm dossierForm  = new DossierForm();
            Dossier dossier = new DossierVO();
            Consignation consignation = new ConsignationVO();
            dossierForm.init(subject);
            dossierForm.setCle(consignationForm.getLien());
            dossierForm.setSite(consignationForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertConsignationHtmlForm(consignationForm, consignation,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Consignation: " + consignation);
            delegate.addLienConsignation(subject,dossier,consignation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.CONSIGNATION);
			psuMandat.setReference1(consignationForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de cr?ation (liaison) d'un service d'urgence.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienUrgence(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Liaison d'un service d'urgence ? un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            UrgenceForm urgenceForm = (UrgenceForm)form;
            DossierForm dossierForm  = new DossierForm();
            Dossier dossier = new DossierVO();
            Urgence urgence = new UrgenceVO();
            String cle = "";
            String site = "";
            if(StringUtils.isNotEmpty(urgenceForm.getLienSociete())){
            	//On extrait la cl? et le site de la soci?t? pour l'insertion
	            int pos = urgenceForm.getLienSociete().indexOf("-");
	            cle = urgenceForm.getLienSociete().substring(0,pos);
	            site = urgenceForm.getLienSociete().substring(pos+1, urgenceForm.getLienSociete().length());
	            urgenceForm.setLienSociete(cle);
	            urgenceForm.setLienSiteSociete(site);
            }
            dossierForm.init(subject);
            dossierForm.setCle(urgenceForm.getLien());
            dossierForm.setSite(urgenceForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertUrgenceHtmlForm(urgenceForm, urgence,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Urgence: " + urgence);
            delegate.addLienUrgence(subject,dossier,urgence);

            //Rafra?chissement du dossier
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.URGENCE);
			psuMandat.setReference1(urgenceForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton Ajouter dans l'?cran
     * de cr?ation d'un suivi. Cette fonction acc?l?re la cr?ation de suivis successifs
     * comme ceux cr??s par Investigation.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward ajouterSuivi(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Sauvegarde et ajout d'un suivi ? un dossier.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            SuiviForm suiviForm = (SuiviForm)form;
            DossierForm dossierForm  = new DossierForm();
            Dossier dossier = new DossierVO();
            Suivi suivi = new SuiviVO();
            dossierForm.init(subject);
            dossierForm.setCle(suiviForm.getLien());
            dossierForm.setSite(suiviForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + suivi);
            delegate.addLienSuivi(subject,dossier,suivi);
            //On conserve les valeurs pour les r?afficher, sauf le secteur et l'intervenant assign?
            suiviForm.setSecteurAssigne(user.getSecteur()+"");
			suiviForm.setIntervenant("");
            suiviForm.setDemandeur(user.getCode());
            suiviForm.setDateCreation(currentDate);
            suiviForm.setModifiable(true);
            suiviForm.setApprouvable(false);
            suiviForm.setPermettreComplete(false);
            suiviForm.setNiveauHierarchiqueCreateur(Long.toString(privilege.getNiveauAuthorite()));
            suiviForm.setConfidentialiteCreateur(Long.toString(privilege.getNiveauConfidentialite()));

            log.debug("Suivi : " + suiviForm);
            request.getSession().setAttribute("suivi",suiviForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton t?l?charger dans l'?cran
     * de t?l?chargement (liaison) d'une photo.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienPhoto(CardexAuthenticationSubject subject,
                                  ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                 throws IOException, ServletException {

        ActionMessages errors = new ActionMessages();
        String success = "success";
        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            DossierForm dossierForm = new DossierForm();
            Photo photo = new PhotoVO();
            Dossier dossier = new DossierVO();
            log.debug("PhotoForm a li?e : " + photoForm);

            dossierForm.init(subject);
            dossierForm.setCle(photoForm.getLien());
            dossierForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm,dossier,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());

            FormFile   file = photoForm.getUploadImage();

            if (photoForm.isTailleAccepte() == false) {
                log.error("La taille du fichier est sup?rieure ? 4MB.");
                return mapping.getInputForward();
            }else if(GlobalConstants.TypeMutliMedia.PHOTO.equals(photoForm.getTypeMultimedia())
            && photoForm.isPhoto() == false){
                log.error("Ce fichier n'est pas une photo");
                throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
            }else{
            	byte[] data = file.getFileData();
            	photo.setImage( data );
            	photo.setExtension(photoForm.getExtensionDeFilePath());

	            log.debug("Photo a li?e : " + photo);
	            photo= delegate.addLienPhoto(subject,dossier,photo);
	            file.destroy();
	            log.debug("Photo li?e : " + photo);
				populateDossierForm(subject, dossier, dossierForm);
				request.getSession().setAttribute("dossier", dossierForm);

				//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
				psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
				psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
				psuMandat.setReference1(Long.toString(photo.getLienElement()));
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);

       	        return mapping.findForward(success);
            }

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }

    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets dossiers dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienDossier(CardexAuthenticationSubject subject,
                                           ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException,
                                           ServletException {
        log.debug("Destruction d'un lien dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate =
                new DossierBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            DossierForm         dossierForm = new DossierForm();
            Dossier                 dossierOrigine = new DossierVO();
            Dossier                 dossierDestination =
                new DossierVO();

            dossierForm.init(subject);
            dossierOrigine.setCle(lienForm.getCleSource());
            dossierOrigine.setSite(lienForm.getSiteSource());
            dossierOrigine.setTypeLien(lienForm.getTypeLien());
            dossierOrigine.setRole(Long.parseLong(lienForm.getRole()));
            dossierOrigine.setLien(lienForm.getCle());
            dossierOrigine.setLienSite(lienForm.getSite());

            dossierDestination.setCle(lienForm.getCleDestination());
            dossierDestination.setSite(lienForm.getSiteDestination());
            dossierDestination.setTypeLien(lienForm.getTypeLien());
            dossierDestination.setRole(Long.parseLong(lienForm.getRole()));
            dossierDestination.setLien(lienForm.getCle());
            dossierDestination.setLienSite(lienForm.getSite());
            log.debug(lienForm.toString());
            delegate.deleteLienDossier(subject, dossierOrigine,
                                       dossierDestination);
            populateDossierForm(subject, dossierOrigine, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			//psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

	        assignerTypeCriteresRechercheDossier(request, dossierForm);

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }

        return mapping.findForward("success");
    }

	private void assignerTypeCriteresRechercheDossier(HttpServletRequest request, DossierForm dossierForm) {
		CriteresRechercheDossierHtmlForm criteresRechercheDossierForm =
		    (CriteresRechercheDossierHtmlForm) request.getSession().getAttribute("rechercheDossier");

		if (criteresRechercheDossierForm != null)
			criteresRechercheDossierForm.setType( dossierForm.getType() );
	}

	/**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets narrations dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une narration et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Dossier dossier = new DossierVO();
            Narration narration = new NarrationVO();
            dossierForm.init(subject);
            dossierForm.setCle(narrationForm.getLien());
            dossierForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            narration.setDossier(dossier);
            log.debug("Dossier: " + dossier);
            log.debug("Narration: " + narration);
            delegate.deleteLienNarration(subject,dossier,narration);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(narrationForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    public ActionForward deleteLienBillet(CardexAuthenticationSubject subject,
            ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {
		log.debug("Suppression d'un lien entre une narration et un dossier.");
		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			BilletBusinessDelegate delegate = new BilletBusinessDelegate();
			DossierForm dossierForm = new DossierForm();
			BilletForm billetForm = (BilletForm)form;
			BilletVO billetVO = new BilletVO();
        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
        	delegate.supprimer(subject, billetVO);
			Dossier dossier = new DossierVO();
			dossierForm.init(subject);
			dossierForm.setCle(String.valueOf(billetForm.getLien()));
			dossierForm.setSite(String.valueOf(billetForm.getLienSite()));
			ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier, subject.getLocale());
			populateDossierForm(subject, dossier, dossierForm);
			request.getSession().setAttribute("dossier", dossierForm);

			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
	}
    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets suivis dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienSuivi(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une suivi et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            SuiviForm suiviForm = (SuiviForm)form;
            Dossier dossier = new DossierVO();
            Suivi suivi = new SuiviVO();
            dossierForm.init(subject);
            dossierForm.setCle(suiviForm.getLien());
            dossierForm.setSite(suiviForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + suivi);
            delegate.deleteLienSuivi(subject,dossier,suivi);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUIVI);
			psuMandat.setReference1(suiviForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets consignation dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienConsignation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une consignation et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            ConsignationForm consignationForm = (ConsignationForm)form;
            Dossier dossier = new DossierVO();
            Consignation consignation = new ConsignationVO();
            dossierForm.init(subject);
            dossierForm.setCle(consignationForm.getLien());
            dossierForm.setSite(consignationForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertConsignationHtmlForm(consignationForm, consignation,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Consignation: " + consignation);
            delegate.deleteLienConsignation(subject,dossier,consignation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.CONSIGNATION);
			psuMandat.setReference1(consignationForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets service d'urgence dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienUrgence(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre un service d'urgence et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            UrgenceForm urgenceForm = (UrgenceForm)form;
            Dossier dossier = new DossierVO();
            Urgence urgence = new UrgenceVO();
            dossierForm.init(subject);
            dossierForm.setCle(urgenceForm.getLien());
            dossierForm.setSite(urgenceForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertUrgenceHtmlForm(urgenceForm, urgence,
                    subject.getLocale());
            log.debug("Dossier : " + dossier);
            log.debug("Urgence : " + urgence);
            delegate.deleteLienUrgence(subject,dossier,urgence);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.URGENCE);
			psuMandat.setReference1(urgenceForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets photos dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienPhoto(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une photo et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            PhotoForm photoForm = (PhotoForm)form;
            Dossier dossier = new DossierVO();
            Photo photo = new PhotoVO();
            dossierForm.init(subject);
            dossierForm.setCle(photoForm.getLien());
            dossierForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm, photo,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Photo: " + photo);
            delegate.deleteLienPhoto(subject,dossier,photo);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setNumeroDossier(dossierForm.getNumeroDossier());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
			psuMandat.setReference1(Long.toString(photo.getLienElement()));
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

            request.getParameterMap().put("cle", new String[]{photoForm.getLien()});
            request.getParameterMap().put("site", new String[]{photoForm.getLienSite()});
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification de narration.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour d'un lien entre une narration et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Dossier dossier = new DossierVO();
            Narration narration = new NarrationVO();
            dossierForm.init(subject);
            dossierForm.setCle(narrationForm.getLien());
            dossierForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            narration.setDossier(dossier);
            log.debug("Dossier: " + dossier);
            log.debug("Narration: " + narration);
            delegate.updateLienNarration(subject,narration);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
        	String ancestor = bre.getAncestor().toString();
        	ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp?cial d'erreur. Durant la t?che qui reconstruit l'index des narrations
			//les sauvegardes ?chouent. Dans ce cas, un message d'erreur est retourn? et la
			//narration est perdue. Le test suivant permet de d?tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration ? l'?cran avec
			//un message plus appropri?, sans perte de donn?es.
			if((ancestor.indexOf("ORA-29861") > -1) || (ancestor.indexOf("ORA-29875") > -1) || (ancestor.indexOf("ORA-29877") > -1)){
				errors.add(Globals.ERROR_KEY, new ActionMessage("cardex_erreur_narration"));
				saveErrors(request, errors);
				return mapping.findForward("erreur");
            }else{
                handleBusinessResourceException(bre, errors, request);
                return mapping.findForward("error");
            }
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification de suivi.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienSuivi(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour d'un lien entre un suivi et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            SuiviForm suiviForm = (SuiviForm)form;
            Dossier dossier = new DossierVO();
            Suivi suivi = new SuiviVO();
            dossierForm.init(subject);
            dossierForm.setCle(suiviForm.getLien());
            dossierForm.setSite(suiviForm.getLienSite());
            //On met le suivi automatiquement ? compl?t? si une date Compl?t?e a ?t? saisie.
            if(StringUtils.isNotEmpty(suiviForm.getDateCompletee())){
            	suiviForm.setStatut(GlobalConstants.StatutSuivi.COMPLETE);
            }
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + suivi);
            delegate.updateLienSuivi(subject,suivi);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification de consignation.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienConsignation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour d'un lien entre une consignation et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            ConsignationForm consignationForm = (ConsignationForm)form;
            Dossier dossier = new DossierVO();
            Consignation consignation = new ConsignationVO();
            dossierForm.init(subject);
            dossierForm.setCle(consignationForm.getLien());
            dossierForm.setSite(consignationForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertConsignationHtmlForm(consignationForm, consignation,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Consignation: " + consignation);
            delegate.updateLienConsignation(subject,consignation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de modification de services d'urgence.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienUrgence(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour d'un lien entre un service d'urgence et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            UrgenceForm urgenceForm = (UrgenceForm)form;
            String cle = "";
            String site = "";
            if(StringUtils.isNotEmpty(urgenceForm.getLienSociete())){
            	//On extrait la cl? et le site de la soci?t? pour l'insertion
	            int pos = urgenceForm.getLienSociete().indexOf("-");
	            cle = urgenceForm.getLienSociete().substring(0,pos);
	            site = urgenceForm.getLienSociete().substring(pos+1, urgenceForm.getLienSociete().length());
	            urgenceForm.setLienSociete(cle);
	            urgenceForm.setLienSiteSociete(site);
            }
            Dossier dossier = new DossierVO();
            Urgence urgence = new UrgenceVO();
            dossierForm.init(subject);
            dossierForm.setCle(urgenceForm.getLien());
            dossierForm.setSite(urgenceForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertUrgenceHtmlForm(urgenceForm, urgence,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Urgence: " + urgence);
            delegate.updateLienUrgence(subject,dossier,urgence);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton Approuver dans l'?cran
     * de modification de narration.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward approuveLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Approbation d'une narration li?e ? un dossier.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
log.debug("Date du jour : " + currentDate);
        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Dossier dossier = new DossierVO();
            Narration narration = new NarrationVO();
            dossierForm.init(subject);
            dossierForm.setCle(narrationForm.getLien());
            dossierForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur(user.getCode());
            narrationForm.setAutoriteApprobateur(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setAutoriteNarration(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setConfidentialiteApprobateur(Long.toString(privilege.getNiveauConfidentialite()));
            narrationForm.setDateApprobation(currentDate);
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            narration.setDossier(dossier);
            log.debug("Dossier: " + dossier);
            log.debug("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
			String ancestor = bre.getAncestor().toString();
			ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp?cial d'erreur. Durant la t?che qui reconstruit l'index des narrations
			//les sauvegardes ?chouent. Dans ce cas, un message d'erreur est retourn? et la
			//narration est perdue. Le test suivant permet de d?tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration ? l'?cran avec
			//un message plus appropri?, sans perte de donn?es.
			if((ancestor.indexOf("ORA-29861") > -1) || (ancestor.indexOf("ORA-29875") > -1) || (ancestor.indexOf("ORA-29877") > -1)){
				errors.add(Globals.ERROR_KEY, new ActionMessage("cardex_erreur_narration"));
				saveErrors(request, errors);
				return mapping.findForward("erreur");
			}else{
				handleBusinessResourceException(bre, errors, request);
				return mapping.findForward("error");
			}
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton Approuver dans l'?cran
     * de modification de suivi.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward approuveLienSuivi(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Approbation d'une suivi li?e ? un dossier.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            SuiviForm suiviForm = (SuiviForm)form;
            Dossier dossier = new DossierVO();
            Suivi suivi = new SuiviVO();
            dossierForm.init(subject);
            dossierForm.setCle(suiviForm.getLien());
            dossierForm.setSite(suiviForm.getLienSite());
            suiviForm.setApprobateur(user.getCode());
            suiviForm.setNiveauHierarchiqueApprobateur(Long.toString(privilege.getNiveauAuthorite()));
            suiviForm.setNiveauHierarchiqueSuivi(Long.toString(privilege.getNiveauAuthorite()));
            suiviForm.setConfidentialiteApprobateur(Long.toString(privilege.getNiveauConfidentialite()));
            suiviForm.setDateApprobation(currentDate);
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + suivi);
            delegate.approuveLienSuivi(subject,suivi);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton Approuver dans l'?cran
     * de modification de consignation.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward approuveLienConsignation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Approbation d'une consignation li?e ? un dossier.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            ConsignationForm consignationForm = (ConsignationForm)form;
            Dossier dossier = new DossierVO();
            Consignation consignation = new ConsignationVO();
            dossierForm.init(subject);
            dossierForm.setCle(consignationForm.getLien());
            dossierForm.setSite(consignationForm.getLienSite());
            consignationForm.setApprobateur(user.getCode());
            consignationForm.setDateApprobation(currentDate);
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertConsignationHtmlForm(consignationForm, consignation,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + consignation);
            delegate.approuveLienConsignation(subject,consignation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

/**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton Permettre Modifcation
     * dans l'?cran de modification de narration.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward permettreModificationLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Permettre la modification d'une narration li?e ? un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Dossier dossier = new DossierVO();
            Narration narration = new NarrationVO();
            dossierForm.init(subject);
            dossierForm.setCle(narrationForm.getLien());
            dossierForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur("");
            narrationForm.setAutoriteApprobateur("");
            narrationForm.setAutoriteNarration("");
            narrationForm.setConfidentialiteApprobateur("");
            narrationForm.setDateApprobation("");
            narrationForm.setAutoriteNarration(narrationForm.getAutoriteCreateur());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            narration.setDossier(dossier);
            log.debug("Dossier: " + dossier);
            log.debug("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
        	String ancestor = bre.getAncestor().toString();
        	ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp?cial d'erreur. Durant la t?che qui reconstruit l'index des narrations
			//les sauvegardes ?chouent. Dans ce cas, un message d'erreur est retourn? et la
			//narration est perdue. Le test suivant permet de d?tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration ? l'?cran avec
			//un message plus appropri?, sans perte de donn?es.
			if((ancestor.indexOf("ORA-29861") > -1) || (ancestor.indexOf("ORA-29875") > -1) || (ancestor.indexOf("ORA-29877") > -1)){
				errors.add(Globals.ERROR_KEY, new ActionMessage("cardex_erreur_narration"));
				saveErrors(request, errors);
				return mapping.findForward("erreur");
            }else{
                handleBusinessResourceException(bre, errors, request);
                return mapping.findForward("error");
            }
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton Compl?ter
     * dans l'?cran de modification de suivi.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward completeLienSuivi(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Permettre la modification d'une suivi li?e ? un dossier.");
        ActionMessages errors = new ActionMessages();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            SuiviForm suiviForm = (SuiviForm)form;
            Dossier dossier = new DossierVO();
            Suivi suivi = new SuiviVO();
            dossierForm.init(subject);
            dossierForm.setCle(suiviForm.getLien());
            dossierForm.setSite(suiviForm.getLienSite());
            //On met le suivi automatiquement ? compl?t? quand l'utilisateur clique sur le bouton Compl?t?.
            suiviForm.setStatut(GlobalConstants.StatutSuivi.COMPLETE);
            suiviForm.setDateCompletee(currentDate);
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Suivi: " + suivi);
            delegate.updateLienSuivi(subject,suivi);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Popule les informations d'un dossier obtenu dans la base de donn?e
     * dans le DossierForm sp?cifi?s.
     *
     * @param Dossier Les crit?res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul? ? partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst?me survient
     * @exception BusinessException si une r?gle d'affaire n'est pas respect?e
     */
    private void populateDossierForm(CardexAuthenticationSubject subject,
                                     Dossier criteria,
                                     DossierForm dossierForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        DossierBusinessDelegate delegate =
            new DossierBusinessDelegate();
        Dossier  dossier = delegate.find(subject, criteria);

        dossierForm.resetOnglets();
        //log.debug("Dossier trouv?: " + dossier.toString());
        ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
        dossierForm.setConfirmationMotPasse(dossierForm.getMotPasse());
		rechercheLiensDossier(subject, dossier, dossierForm, delegate);
    }

    /**
     * <p>
     * Popule les informations d'un dossier obtenu dans la base de donn?e
     * dans le DossierForm sp?cifi?s.  Cette m?thode est appel?e par show et
     * showAcces.  Le diff?rence avec populateDossierForm est que le dossier
     * n'est plus appel? puisqu'il l'est d?j? dans show et showAcces.
     *
     * @param subject Le sujet authentifi?
     * @param Dossier Les crit?res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul? ? partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst?me survient
     * @exception BusinessException si une r?gle d'affaire n'est pas respect?e
     */
    protected void populateDossierFormShow(CardexAuthenticationSubject subject,
                                     Dossier dossier,
                                     DossierForm dossierForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        DossierBusinessDelegate delegate = new DossierBusinessDelegate();
//        Dossier  dossier = delegate.find(subject, criteria);

        dossierForm.resetOnglets();
        //log.debug("Dossier trouv?: " + dossier.toString());
//        ValueObjectMapper.convertDossier(dossier, dossierForm,subject.getLocale());
        dossierForm.setConfirmationMotPasse(dossierForm.getMotPasse());
        //Recherche de tous les enregistrements li?s dans les onglets
		rechercheLiensDossier(subject, dossier, dossierForm, delegate);
    }

    /**
     * Effectue la recherche des enregistrements li?s ? un dossier donn?,
     * affich?s dans les onglets du dossier.
     *
     * @param subject Le sujet authentifi?
     * @param Dossier Les crit?res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul? ? partir du dossier obtenu
     * @param delegate Lien avec la base de donn?es.
     *
     * @exception BusinessResourceException si une erreur syst?me survient
     * @exception BusinessException si une r?gle d'affaire n'est pas respect?e
     */
    public void rechercheLiensDossier(CardexAuthenticationSubject subject,
                                     Dossier dossier,
                                     DossierForm dossierForm,
                                     DossierBusinessDelegate delegate) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        // Recherche des liens dossier
        Collection liensDossier = delegate.findLiensDossier(subject,
                dossier);
        Iterator   it = liensDossier.iterator();

        log.debug("Dossier li?s (" + liensDossier.size() + ") :");

        while (it.hasNext()) {
            Dossier     linkDossier = (Dossier) it.next();
            DossierForm linkDossierForm = new DossierForm();
            linkDossierForm.init(subject);

            ValueObjectMapper.convertDossier(linkDossier, linkDossierForm,
                    subject.getLocale());
            linkDossierForm.assignerValeurDeListe( subject );
            dossierForm.addDossier(linkDossierForm);
            log.debug(linkDossier.toString());
        }
        dossierForm.getListeDossiers().assignerTrierDefault(DossierOngletTrieListe.CLE_DATE_DEBUT, true, new DossierOngletTrieListe());

        // Recherche des liens narration
        Collection liensNarration = delegate.findLiensNarration(subject, dossier);
        it = liensNarration.iterator();

        log.debug("Narration li?s (" + liensNarration.size() + ") :");

        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();
			//On inscrit les valeurs de r?f?rence pour l'impression de la narration.
			linkNarration.setReference1(dossierForm.getNumeroDossier());
			String numeroCardex = dossierForm.getNumeroCardex().getSite() + "-" +
								  dossierForm.getNumeroCardex().getDate() + "-" +
								  dossierForm.getNumeroCardex().getSequence();
			linkNarration.setReference2(numeroCardex);
            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    subject.getLocale());
            linkNarrationForm.assignerValeurDeListe( subject );
            dossierForm.addNarration(linkNarrationForm);
            log.debug(linkNarration.toString());
        }
        dossierForm.getListeNarrations().assignerTrierDefault(NarrationOngletTrieListe.CLE_DATE_CREATION, true, new NarrationOngletTrieListe());

        // Recherche des liens billets
        Collection<BilletVO> liensBillet = delegate.trouverLiensBillet(subject, dossier);
        log.debug("Billets li?s (" + liensBillet.size() + ") :");
        //DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(subject.getLocale()));
        for (BilletVO billetVO:liensBillet){
        	BilletForm billetForm = new BilletForm();
            ValueObjectMapper.convert(billetVO, billetForm, subject.getLocale());
            dossierForm.addBillet(billetForm);
            log.debug(billetForm.toString());
        }
        dossierForm.getListeBillets().assignerTrierDefault(BilletOngletTrieListe.CLE_NOM_BILLET, true, new BilletOngletTrieListe());

        // Recherche des liens sujets
        Collection liensSujet = delegate.findLiensSujet(subject,
                dossier);
        it = liensSujet.iterator();

        log.debug("Sujets li?s (" + liensSujet.size() + ") :");

        while (it.hasNext()) {
            Sujet     linkSujet = (Sujet) it.next();
            SujetForm linkSujetForm = new SujetForm();

            ValueObjectMapper.convertSujet(linkSujet, linkSujetForm,
                    subject.getLocale());
            linkSujetForm.assignerValeurDeListe(subject);
            dossierForm.addSujet(linkSujetForm);
            log.debug(linkSujet.toString());
        }
        dossierForm.getListeSujets().assignerTrierDefault(SujetOngletTrieListe.CLE_NOM, false, new SujetOngletTrieListe());

        // Recherche des liens photos
        Collection liensPhoto = delegate.findLiensPhoto(subject,
                dossier);
        it = liensPhoto.iterator();

        log.debug("Photos li?s (" + liensPhoto.size() + ") :");

        while (it.hasNext()) {
            Collection sublist = new ArrayList();
            for (int i=0; i<3;i++) {
              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm,
                        subject.getLocale());
                sublist.add(linkPhotoForm);
                log.debug(linkPhoto.toString());
              }//if
            }//for
            dossierForm.addPhoto(sublist);
        }//while

        // Recherche des liens pieces jointes
        Collection liensPieceJointe = delegate.findLiensPieceJointe(subject,
                dossier);
        it = liensPieceJointe.iterator();

        log.debug("PieceJointes li?s (" + liensPieceJointe.size() + ") :");

        while (it.hasNext()) {
            Photo     linkPieceJointe = (Photo) it.next();
            PhotoForm linkPieceJointeForm = new PhotoForm();
            ValueObjectMapper.convertPhoto(linkPieceJointe, linkPieceJointeForm,
                    subject.getLocale());
            dossierForm.addPieceJointe(linkPieceJointeForm);
        }//while

        // Recherche des liens jeux
        Jeux liensJeux = delegate.findLiensJeux(subject,
                dossier);
        log.debug("Jeux li?s (" + liensJeux.getJeuxChoisis().size() + ") :");
        JeuxForm linkJeuxForm = new JeuxForm();
        ValueObjectMapper.convertJeux(subject, liensJeux,linkJeuxForm,subject.getLocale());
        log.debug(linkJeuxForm.toString());
        dossierForm.setJeux(linkJeuxForm);

        // Recherche des liens inscriptions
        Collection liensInscription = delegate.findLiensInscription(subject,
                dossier);
        it = liensInscription.iterator();

        log.debug("Inscriptions li?s (" + liensInscription.size() + ") :");

        while (it.hasNext()) {
            Inscription     linkInscription = (Inscription) it.next();
            InscriptionForm linkInscriptionForm = new InscriptionForm();
            ValueObjectMapper.convertInscription(linkInscription, linkInscriptionForm,
                    subject.getLocale());
            log.debug(linkInscription.toString());
            linkInscriptionForm.assignerValeurDeListe( subject );
            dossierForm.addInscription(linkInscriptionForm);
        }//while
        dossierForm.getListeInscriptions().assignerTrierDefault(InscriptionOngletTrieListe.CLE_DATE_INSCRIPTION, true, new InscriptionOngletTrieListe());

        // Recherche des liens suivis
        Collection liensSuivi = delegate.findLiensSuivi(subject,
                dossier);
        it = liensSuivi.iterator();

        log.debug("Suivis li?s (" + liensSuivi.size() + ") :");

        while (it.hasNext()) {
            Suivi     linkSuivi = (Suivi) it.next();
            SuiviForm linkSuiviForm = new SuiviForm();
            ValueObjectMapper.convertSuivi(linkSuivi, linkSuiviForm,
                    subject.getLocale());
            log.debug(linkSuivi.toString());
            linkSuiviForm.assignerValeurDeListe( subject );
            dossierForm.addSuivi(linkSuiviForm);
        }//while
        dossierForm.getListeSuivis().assignerTrierDefault(SuiviOngletTrieListe.CLE_DATE_PREVUE, true, new SuiviOngletTrieListe());

        // Recherche des liens consignation
        Collection liensConsignation = delegate.findLiensConsignation(subject,
                dossier);
        it = liensConsignation.iterator();

        log.debug("Consignations li?es (" + liensConsignation.size() + ") :");

        while (it.hasNext()) {
            Consignation     linkConsignation = (Consignation) it.next();
            ConsignationForm linkConsignationForm = new ConsignationForm();
            ValueObjectMapper.convertConsignation(linkConsignation, linkConsignationForm,
                    subject.getLocale());
            log.debug(linkConsignation.toString());
            linkConsignationForm.assignerValeurDeListe( subject );
            dossierForm.addConsignation(linkConsignationForm);
        }//while
        dossierForm.getListeConsignations().assignerTrierDefault(ConsignationOngletTrieListe.CLE_DATE_CREATION, true, new ConsignationOngletTrieListe());

        // Recherche des liens societes
        Collection liensSociete = delegate.findLiensSociete(subject,
                dossier);
        it = liensSociete.iterator();

        log.debug("Societes li?s (" + liensSociete.size() + ") :");

        while (it.hasNext()) {
            Societe     linkSociete = (Societe) it.next();
            SocieteForm linkSocieteForm = new SocieteForm();

            ValueObjectMapper.convertSociete(linkSociete, linkSocieteForm,
                    subject.getLocale());
            linkSocieteForm.assignerValeurDeListe( subject );
            linkSocieteForm.assignerPermettreSuppressionLiaison(subject,dossierForm);
            dossierForm.addSociete(linkSocieteForm);
            log.debug(linkSociete.toString());
        }
        dossierForm.getListeSocietes().assignerTrierDefault(SocieteOngletTrieListe.CLE_NOM, false, new SocieteOngletTrieListe());

        // Recherche des liens vehicules
        Collection liensVehicule = delegate.findLiensVehicule(subject,
                dossier);
        it = liensVehicule.iterator();

        log.debug("Vehicule li?s (" + liensVehicule.size() + ") :");

        while (it.hasNext()) {
            Vehicule     linkVehicule = (Vehicule) it.next();
            VehiculeForm linkVehiculeForm = new VehiculeForm();

            ValueObjectMapper.convertVehicule(linkVehicule, linkVehiculeForm,
                    subject.getLocale());
            linkVehiculeForm.assignerValeurDeListe( subject );
            dossierForm.addVehicule(linkVehiculeForm);
            log.debug(linkVehicule.toString());
        }
        dossierForm.getListeVehicules().assignerTrierDefault(VehiculeOngletTrieListe.CLE_IMMATRICULATION, false, new VehiculeOngletTrieListe());

        // Recherche des liens SousCat?gories
        SousCategoriesVO sousCategoriesVO = delegate.findLiensSousCategories(subject, dossier);
        it = sousCategoriesVO.getSousCategories().iterator();
        log.debug("SousCat?gories li?s (" + sousCategoriesVO.getSousCategories().size() + ") :");
        List listeSousCategorie = new ArrayList();

        while (it.hasNext()) {
            SousCategorieVO sousCategorieVO = (SousCategorieVO) it.next();
            SousCategorieForm sousCategorie = new SousCategorieForm(sousCategorieVO);
            sousCategorie.assignerValeurDeListe( subject );
            listeSousCategorie.add(sousCategorie);
        }
        Collections.sort(listeSousCategorie, new SousCategorieComparator());
        dossierForm.setListeSousCategories( listeSousCategorie );

        // Recherche des intervenants du partage
        Collection liensPartage = delegate.findLiensPartage(subject, dossier);
        it = liensPartage.iterator();
        log.debug("Intervenants li?s (" + liensPartage.size() + ") :");
        while (it.hasNext()) {
        	Partage partage = (Partage) it.next();
        	PartageForm partageForm = new PartageForm();
        	ValueObjectMapper.convertPartage(partage,partageForm,subject.getLocale());
        	log.debug(partageForm.toString());
        	partageForm.assignerValeurDeListe(subject);
        	dossierForm.addPartage(partageForm);
        }
        dossierForm.getListePartage().assignerTrierDefault(PartageOngletTrieListe.CLE_INTERVENANT, false, new PartageOngletTrieListe());

        // Recherche des liens ?valuation
        Collection liensEvaluation = delegate.findLiensEvaluation(subject, dossier);
        it = liensEvaluation.iterator();

        log.debug("?valuations li?es (" + liensEvaluation.size() + ") :");

        while (it.hasNext()) {
        	Evaluation     linkEvaluation = (Evaluation) it.next();
        	EvaluationForm linkEvaluationForm = new EvaluationForm();
        	ValueObjectMapper.convertEvaluation(subject, linkEvaluation, linkEvaluationForm, subject.getLocale());
            log.debug(linkEvaluation.toString());
            linkEvaluationForm.assignerValeurDeListe( subject );
            dossierForm.addEvaluation(linkEvaluationForm);
        }//while
        dossierForm.getListeEvaluations().assignerTrierDefault(EvaluationOngletTrieListe.CLE_DATE_CREATION, true, new EvaluationOngletTrieListe());

        // Recherche des liens des services d'urgence
        Collection liensUrgence = delegate.findLiensUrgence(subject, dossier);
        it = liensUrgence.iterator();

        log.debug("Service d'urgence li?s (" + liensUrgence.size() + ") :");

        while (it.hasNext()) {
        	Urgence     linkUrgence = (Urgence) it.next();
        	UrgenceForm linkUrgenceForm = new UrgenceForm();
            ValueObjectMapper.convertUrgence(linkUrgence, linkUrgenceForm,
                    subject.getLocale());
            log.debug(linkUrgence.toString());
            linkUrgenceForm.assignerValeurDeListe( subject );
            dossierForm.addUrgence(linkUrgenceForm);
        }//while
        dossierForm.getListeUrgence().assignerTrierDefault(UrgenceOngletTrieListe.CLE_DATE_CREATION, true, new UrgenceOngletTrieListe());

        //Valeurs par d?faut d'une nouvelle photo
        if(dossierForm.getAjoutPhoto() != null){
	        dossierForm.getAjoutPhoto().setTypeMultimedia(GlobalConstants.TypeMutliMedia.PHOTO);
	        dossierForm.getAjoutPhoto().setCle("-1");
	        dossierForm.getAjoutPhoto().setSite("-1");
	        dossierForm.getAjoutPhoto().setLienMultimedia("-1");
	        dossierForm.getAjoutPhoto().setLienSiteMultimedia("-1");
	        dossierForm.getAjoutPhoto().setLienElement("-1");
	        dossierForm.getAjoutPhoto().setLienSiteElement("-1");
        }
	    //Valeurs par d?faut d'une nouvelle pi?ce jointe
        if(dossierForm.getAjoutPieceJointe() != null){
        	dossierForm.getAjoutPieceJointe().setTypeMultimedia(GlobalConstants.TypeMutliMedia.DOCUMENT_ANNEXE);
	        dossierForm.getAjoutPieceJointe().setCle("-1");
	        dossierForm.getAjoutPieceJointe().setSite("-1");
	        dossierForm.getAjoutPieceJointe().setLienMultimedia("-1");
	        dossierForm.getAjoutPieceJointe().setLienSiteMultimedia("-1");
	        dossierForm.getAjoutPieceJointe().setLienElement("-1");
	        dossierForm.getAjoutPieceJointe().setLienSiteElement("-1");
        }

    }

    /**
     * <p>
     * Cette fonction sert ? forcer le rechargement des donn?es en m?moire cache.
     * Elle est appel?e par un bouton (acc?s restreint par ClearTrust) au niveau
     * du menu principal.  Elle est n?cessaire dans le cas d'un changement apport?
     * dans un fichier ma?tre d?j? charg? en m?moire.  Sans cette fonction, il faudrait
     * repartir le Cardex au niveau de WebSphere, ce qui d?connecterait les utilisateurs.
     * <p>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws InterruptedException
     */
    public ActionForward refreshCache(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        log.debug("Rafra?chissement des informations en m?moire cache");
        //ViderCacheUtils.getInstance().assignerViderCaches();
        (new ViderCacheUtils()).assignerViderCaches();
        SujetInteretGalerieCache.vider(); // vider la liste de sujet d'int?r?t actif/inactif

        return mapping.findForward("success");

    }

    /**
     * <p>
     * Affichage de la pi?ce jointe avec dimensionnement automatique de l'image pour son
     * impression sur une page 8,5x11.  Autrement, si la pi?ce jointe exc?de le format
     * d'une page, l'utilisateur doit ajuster manuellement le facteur zoom avant de lancer
     * l'impression, ce qui est laborieux et souvent compliqu? pour des utilisateurs
     * inexp?riment?s.
     * Sert ?galement ? afficher une photo pour permettre de zoomer pleine grandeur.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showPieceJointe(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException, DAOException {
        log.debug("Affichage d'une pi?ce jointe.");
        PhotoForm photoForm = (PhotoForm)form;
		photoForm.setUrl("");
        request.getSession().setAttribute("photo", photoForm);
        return mapping.findForward("success");
    }

    /**
     * <p>
     * Modification de la pi?ce jointe
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienPieceJointe(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException, DAOException {
        log.debug("Modification d'un lien entre une pi?ce jointe et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
            verifierToken(request);
            PhotoBusinessDelegate delegate = new PhotoBusinessDelegate();

            PhotoForm photoForm = (PhotoForm) form;
            Photo photo = new PhotoVO();
            ValueObjectMapper.convertPhotoHtmlForm(photoForm, photo, subject.getLocale());
            log.debug("Pi?ce jointe: " + photo);

            delegate.updateLienMultimedia(subject, photo);

            request.getParameterMap().put("cle", new String[]{photoForm.getLien()});
            request.getParameterMap().put("site", new String[]{photoForm.getLienSite()});
            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * Fin de la session
     * <p>
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward logout(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Logout du Cardex");
       return mapping.findForward("success");
   }

    /**
     * Approuver les sous cat?gories du dossier
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward approuverSousCategories(CardexAuthenticationSubject subject,
	ActionMapping mapping, ActionForm form,
	HttpServletRequest request,
	HttpServletResponse response) throws IOException,
	ServletException {
		log.debug("Sauvegarde de la cr?ation d'un nouveau dossier");

		ActionErrors errors = new ActionErrors();
		DossierForm dossierForm = (DossierForm) form;
		Dossier dossier = new DossierVO();

		try {
			verifierToken(request);
			DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();

			ValueObjectMapper.convertDossierHtmlForm(dossierForm,
					dossier, subject.getLocale());

			dossierDelegate.modifierApprouveLienSousCategorie(subject, dossier, true);

			log.debug("# Cl? de dossier retourn? : " + dossier.getCle());

			populateDossierForm(subject, dossier, dossierForm);

			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);

			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}

    /**
     * "D?saprouver" les approbations des sous-cat?gories
     * Pour permetre de les modifiers ? nouveau
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward modifierApprouverSousCategories(CardexAuthenticationSubject subject,
	ActionMapping mapping, ActionForm form,
	HttpServletRequest request,
	HttpServletResponse response) throws IOException,
	ServletException {
		log.debug("Sauvegarde de la cr?ation d'un nouveau dossier");

		ActionErrors errors = new ActionErrors();
		DossierForm dossierForm = (DossierForm) form;
		Dossier dossier = new DossierVO();

		try {
			verifierToken(request);
			DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();

			ValueObjectMapper.convertDossierHtmlForm(dossierForm,
					dossier, subject.getLocale());

			dossierDelegate.modifierApprouveLienSousCategorie(subject, dossier, false);

			log.debug("# Cl? de dossier retourn? : " + dossier.getCle());

			populateDossierForm(subject, dossier, dossierForm);

			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);

			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}

    /**
     * <p>
     * Cet ?v?nement surivient lorsque dans l'?cran de recherche de dossier, l'utilisateur a choisi
     * de rechercher les dossiers partag?s auxquels il a droit. Les dossiers partag?s sont ind?pendants
     * du niveau de confidentialit? et de l'entit?. Le bouton Partage dans l'?cran de recherche permet donc
     * de contourner ces r?gles d'acc?s pour retrouver les dossiers partag?s de l'utilisteur.
     * <p>
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward recherchePartage(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Recherche des dossiers partag?s");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate =  new DossierBusinessDelegate();
            CriteresRechercheDossierForm criteresRechercheDossierHtmlForm = (CriteresRechercheDossierForm) form;
            CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
            String sigle = criteresRechercheDossierHtmlForm.getNumeroCardex().getSite();

			// Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
			ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierHtmlForm, criteresRechercheDossier,subject.getLocale());
			log.debug(criteresRechercheDossier.toString());

			// Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
			List<Dossier> dossierList = delegate.recherchePartage(subject,criteresRechercheDossier);

            assignerResultatDossier(subject, criteresRechercheDossierHtmlForm, dossierList);
    		criteresRechercheDossierHtmlForm.getListeResultat().assignerTrierDefault(DossierTrieListe.CLE_NUMERO_CARDEX, true, new DossierTrieListe());
    		//On remet la valeur par d?faut du sigle
    		criteresRechercheDossierHtmlForm.getNumeroCardex().setSite(sigle);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            return mapping.findForward("error");
        }
    }

	//Sert ? inscrire une date de paiement ? tous les billets affich?s dans l'onglet Billets d'un dossier.
	//?vite ainsi d'inscrire manuellement une date ? chacun des billets.
	public ActionForward inscrireDatePaiement(CardexAuthenticationSubject subject,
		    ActionMapping mapping,
		    ActionForm form,
		    HttpServletRequest request,
		    HttpServletResponse response) throws IOException,
		    ServletException {
		    	log.debug("Inscrire une date de paiement aux billets");
		        ActionMessages errors = new ActionMessages();

		        try {
		        	verifierToken(request);
		            DossierBusinessDelegate delegate =  new DossierBusinessDelegate();
			        DossierForm dossierForm = (DossierForm)form;
			        Dossier dossierVO = new DossierVO();
			        dossierForm.setCle((String)request.getParameter("cle"));
			        dossierForm.setSite((String)request.getParameter("site"));
		            String datePaiement = (String)request.getParameter("datePaiement");
		            delegate.inscrireDatePaiement(subject, Long.valueOf(dossierForm.getCle()), Long.valueOf(dossierForm.getSite()), datePaiement);
		            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossierVO ,subject.getLocale());
		            dossierVO = delegate.find(subject, dossierVO);
		            ValueObjectMapper.convertDossier(dossierVO, dossierForm,subject.getLocale());
		            populateDossierForm(subject, dossierVO, dossierForm);

		        } catch (BusinessResourceException e) {
		            handleBusinessResourceException(e, errors, request);
		            return mapping.getInputForward();
				} catch (BusinessException e) {
					handleBusinessException(e, errors, request);
					return mapping.getInputForward();
				} catch (ValueObjectMapperException e) {
					e.printStackTrace();
					return mapping.getInputForward();
				}

		    	return mapping.findForward("success");
		    }

    /**
     * <p>
     * Validation des crit?res saisis avant l'ex?cution des rapports
     * <p>
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward valider(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Validation de la recherche de dossiers pour les rapports");

        ActionMessages errors = new ActionMessages();

        try {
        	CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) form;
        	CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierForm,criteresRechercheDossier,subject.getLocale());

    		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
    		delegate.validerRapport(subject, criteresRechercheDossier);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            bre.printStackTrace();
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            vome.printStackTrace();
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton AJOUT dans
     * l'onglet photo d'un dossier.  L'ajout de la photo est enregistr? dans le
     * cardex et l'?cran du dossier est affich?.
     * <p>
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward ajouterPhoto(CardexAuthenticationSubject subject,
                                    ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
                                 throws IOException, ServletException {

        ActionMessages errors = new ActionMessages();

        try {
            verifierToken(request);
            DossierBusinessDelegate dossierDelegate =
                new DossierBusinessDelegate();
            Dossier                 dossier = new DossierVO();
            DossierForm         dossierForm = (DossierForm) form;
            ValueObjectMapper.convertDossierHtmlForm(dossierForm,
                    dossier, subject.getLocale());
            PhotoForm photoForm = dossierForm.getAjoutPhoto();
            //Est ce que la taille du fichier exc?de 7 Mo pour les images
            if (photoForm.isTailleAccepte() == false) {
                  log.error("La taille du fichier est sup?rieure ? 7 Mo.");
                  throw (new BusinessRuleExceptionHandle("erreur_fichier")).getBusinessException();
            }
            if(photoForm.isPhoto() == false) {
               log.error("Ce fichier n'est pas une photo");
               throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
            }

            Photo photo = obtenirPhoto(subject, dossierForm, photoForm);

            dossierDelegate.addLienPhoto(subject,dossier,photo);

            populateDossierForm(subject, dossier, dossierForm);
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    private Photo obtenirPhoto(CardexAuthenticationSubject subject, DossierForm dossierForm, PhotoForm photoForm) throws BusinessException, ValueObjectMapperException, FileNotFoundException, IOException {
        photoForm.setLien(dossierForm.getCle());
        photoForm.setLienSite(dossierForm.getSite());

        photoForm.setConfidentialite(dossierForm.getConfidentialite());
        photoForm.setExtension(photoForm.getExtensionDeFilePath());

        Photo photo = new PhotoVO();
        log.debug("PhotoForm a li?e : " + photoForm);

        ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());

        FormFile   file = photoForm.getUploadImage();
        byte[] data = file.getFileData();
        photo.setImage( data );
        log.debug("Photo a li?e : " + photo);

        return photo;
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton OK dans l'?cran
     * de cr?ation (liaison) d'une ?valuation.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienEvaluation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Liaison d'une ?valuation ? un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            EvaluationForm evaluationForm = (EvaluationForm)form;
            DossierForm dossierForm  = new DossierForm();
            Dossier dossier = new DossierVO();
            Evaluation evaluation = convert(subject, evaluationForm, dossier);

            delegate.addLienEvaluation(subject,dossier,evaluation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.EVALUATION);
			psuMandat.setReference1(" ");
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.LIAISON);

			return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * On converti le formulaire en object vo
     * @param subject
     * @param evaluationForm
     * @param dossierForm
     * @param dossier
     * @return
     * @throws ValueObjectMapperException
     */
	private Evaluation convert(CardexAuthenticationSubject subject,
			EvaluationForm evaluationForm, Dossier dossier) throws ValueObjectMapperException {
		Evaluation evaluation = new EvaluationVO();
		dossier.setCle(Long.valueOf(evaluationForm.getLien()));
		dossier.setSite(Long.valueOf(evaluationForm.getLienSite()));
		ValueObjectMapper.convertEvaluationHtmlForm(evaluationForm, evaluation,
		        subject.getLocale());
		log.debug("Dossier: " + dossier);
		log.debug("Evaluation: " + evaluation);

		for(MiseEvaluationForm miseEvaluationForm:evaluationForm.getMisesEvaluation()){
			MiseEvaluationVO miseEvaluationVO = new MiseEvaluationVO();
			ValueObjectMapper.convert(miseEvaluationForm, miseEvaluationVO, subject.getLocale());
			evaluation.getMisesEvaluation().add(miseEvaluationVO);

			for(FrequenceVisitesForm frequenceVisitesForm: miseEvaluationForm.getFrequencesVisites()){

				//On ne traite pas les lignes sans p?riode
				if(StringUtils.isNotEmpty(frequenceVisitesForm.getAnnee())){
					frequenceVisitesForm.setPeriode(frequenceVisitesForm.getAnnee() + '-' + frequenceVisitesForm.getMois());
					FrequenceVisitesVO frequenceVisites = new FrequenceVisitesVO();
					ValueObjectMapper.convertFrequenceVisitesHtmlForm(frequenceVisitesForm, frequenceVisites,
					subject.getLocale());
					miseEvaluationVO.getFrequenceVisites().add(frequenceVisites);
				}
			}
		}
		return evaluation;
	}

    /**
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton de
     * de suppression de lien d'un ?l?ment de l'onglets ?valuations dans l'?cran
     * de consultation dossier.
     *
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienEvaluation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une ?valuation et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = new DossierForm();
            EvaluationForm evaluationForm = (EvaluationForm)form;
            Dossier dossier = new DossierVO();
            Evaluation evaluation = new EvaluationVO();
            dossierForm.init(subject);
            dossierForm.setCle(evaluationForm.getLien());
            dossierForm.setSite(evaluationForm.getLienSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            ValueObjectMapper.convertEvaluationHtmlForm(evaluationForm, evaluation,
                    subject.getLocale());
            log.debug("Dossier: " + dossier);
            log.debug("Evaluation : " + evaluation);
            delegate.deleteLienEvaluation(subject, dossier, evaluation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un dossier
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setNumeroCardex(dossierForm.getNumeroCardex().toString());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.EVALUATION);
			psuMandat.setReference1(" ");
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.SUPPRESSION);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienEvaluation(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour d'un lien entre une ?valuation et un dossier.");
        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            Dossier dossier = new DossierVO();
            DossierForm dossierForm = new DossierForm();
            EvaluationForm evaluationForm = (EvaluationForm)form;
            Evaluation evaluation = convert(subject, evaluationForm,
            		dossier);
            delegate.updateLienEvaluation(subject,evaluation);
            populateDossierForm(subject, dossier, dossierForm);
            request.getSession().setAttribute("dossier", dossierForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * Sert ? sauvegarder l'?valuation ? l'?cran tout en demeurant dans l'?cran de saisie.
     * Cette sauvegarde manuelle est offerte au lieu d'une sauvegarde qui serait automatique,
     * mais tr?s complexe.
     *
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienEvaluationMaintenant(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Sauvegarde imm?diate d'une ?valuation");
        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            Dossier dossier = new DossierVO();
            EvaluationForm evaluationForm = (EvaluationForm)form;
            Evaluation evaluation = convert(subject, evaluationForm,
            		dossier);
            if (StringUtils.isEmpty(evaluationForm.getCle())){
            	Evaluation evaluationRetour = delegate.addLienEvaluation(subject,dossier,evaluation);
            	evaluationForm.setCle(String.valueOf(evaluationRetour.getCle()));
            	evaluationForm.setSite(String.valueOf(evaluationRetour.getSite()));
            }else{
            	delegate.updateLienEvaluation(subject,evaluation);
            }
            request.getSession().setAttribute("evaluation", evaluationForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Recherche directe d'un dossier ? partir du menu principal.
     * Ao?t 2013
     * @param subject Le sujet authentifi?
     * @param mapping L' ActionMapping utils? pour s?lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ?te (optionnelle)
     * @param request La requ?te HTTP trait?e
     * @param response La r?ponse HTTP cr??e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr?e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward rechercheDirecteDossier(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Recherche directe de dossiers");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = (DossierForm) form;
            //On r?cup?re le num?ro de dossier saisi ? l'?cran.
            String numero = (String)request.getParameter("DOSSIER").toUpperCase();
        	Dossier dossier = new DossierVO();
        	if(numero.length() == 15){ //Sans les traits d'union
            	dossierForm.setNumeroCardex(numero);
            	ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier, subject.getLocale());
            	dossier = delegate.rechercheDirecte(subject, dossier);
            }
        	if(numero.length() == 17){ //Avec les traits d'union.
        		//On accepte la saisie avec les traits d'union, mais pour la recherche, on doit les retirer.
        		numero = numero.replace("-","");
            	dossierForm.setNumeroCardex(numero);
            	ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier, subject.getLocale());
            	dossier = delegate.rechercheDirecte(subject, dossier);
            }
            if(dossier.getCle() != 0){
            	//Un dossier a ?t? trouv?. On inscrit donc une entr?e dans la table des acc?s.
            	delegate.ajoutAcces(subject, dossier);
                populateDossierForm(subject, dossier, dossierForm);
            }else{
            	return mapping.findForward("erreurRecherche");
            }

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
        }
        return mapping.findForward("success");
    }

}

