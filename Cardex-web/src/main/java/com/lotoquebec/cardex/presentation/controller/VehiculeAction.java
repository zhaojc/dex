package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.VehiculeBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheVehiculeVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.business.vo.ParticularitesVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.NarrationBaliseUtil;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EntiteCardexLiaison;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheVehiculeForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.LienForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.ParticularitesForm;
import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.presentation.util.AideController;
import com.lotoquebec.cardexCommun.rapport.PDFImpressionRapport;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lq.std.conf.impl.AppConfig;

/**
 * Cette classe g?re les ?v?nements en rapport
 * avec le cas d'utilisation gestion des v?hicules.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.20 $, $Date: 2002/05/01 19:34:01 $
 */
public class VehiculeAction extends AbstractAction {
    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));


    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * le panneau de recherche des v?hicules.  L'application affiche le panneau de mise ? jour.
     * L'utilisateur a pr?alablement saisie les informations  les donn?es relatives ? l'identification
     * du v?hicule.
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
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {

        log.debug("Cr?ation d'un nouveau v?hicule");

        VehiculeForm vehiculeForm = (VehiculeForm)form;

        //Valeurs par d?faut
    	vehiculeForm.init(subject);
		EntiteCardexForm entiteCardex = obtenirEntiteCardexLiaison(request, "rechercheVehicule");
		vehiculeForm.setEntiteCardexLiaison( entiteCardex );
        vehiculeForm.resetOnglets();
        vehiculeForm.setNew(true);
        //Sans objet, par d?faut
        vehiculeForm.setRole(String.valueOf(GlobalConstants.Role.SANS_OBJET));

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * l'onglet V?hicules d'un dossier. Ce bouton est r?serv? aux agents de s?curit? pour leur permettre
     * d'ajouter des fiches v?hicules au lieu d'utiliser des gabarits.
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> site d'origine (site de l'utilisateur)
     * <ul> statut (actif)
     * <ul> Fond? (ind?termin?)
     * <ul> Date de l'assignation (date du jour)
     * </li>
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
    public ActionForward createLiaisonDossier(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {

		log.debug("Cr?ation d'un nouveau v?hicule par un agent de s?curit?");

        VehiculeForm vehiculeForm = new VehiculeForm();
        vehiculeForm.init(subject);
		vehiculeForm.setEntiteCardexLiaison((DossierForm) form );

		//Valeurs par d?faut
        vehiculeForm.resetOnglets();
        vehiculeForm.setNew(true);
        //Sans objet, par d?faut
        vehiculeForm.setRole(String.valueOf(GlobalConstants.Role.SANS_OBJET));
		request.getSession().setAttribute("vehicule",vehiculeForm);
		return mapping.findForward("success");
	}
    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * l'onglet V?hicules d'un sujet. Ce bouton est r?serv? aux agents de s?curit? pour leur permettre
     * d'ajouter des fiches v?hicules au lieu d'utiliser des gabarits.
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> site d'origine (site de l'utilisateur)
     * <ul> statut (actif)
     * <ul> Fond? (ind?termin?)
     * <ul> Date de l'assignation (date du jour)
     * </li>
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
    public ActionForward createLiaisonSujet(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {

		log.debug("Cr?ation d'un nouveau v?hicule par un agent de s?curit?");

        VehiculeForm vehiculeForm = new VehiculeForm();
        vehiculeForm.init(subject);
		vehiculeForm.setEntiteCardexLiaison((SujetForm) form );

		//Valeurs par d?faut
        vehiculeForm.resetOnglets();
        vehiculeForm.setNew(true);
        //Sans objet, par d?faut
        vehiculeForm.setRole(String.valueOf(GlobalConstants.Role.SANS_OBJET));
		request.getSession().setAttribute("vehicule",vehiculeForm);
		return mapping.findForward("success");
	}

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton sauvegarder dans
     * le panneau de cr?ation d'un v?hicule.  Le nouveau v?hicule est enregistr? dans le
     * cardex et l'?cran de mise ? jour du v?hicule est affich?.
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
    public ActionForward save(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {

        log.debug("Sauvegarde de la cr?ation d'un nouveau v?hicule");

        ActionMessages errors = new ActionMessages();
        VehiculeHtmlForm vehiculeForm = (VehiculeHtmlForm)form;
        Vehicule newVehicule = new VehiculeVO();
        VehiculeForm newVehiculeForm = (VehiculeForm) form;

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate vehiculeDelegate =
                new VehiculeBusinessDelegate();

            ValueObjectMapper.convertVehiculeHtmlForm(newVehiculeForm, newVehicule, subject.getLocale());

            log.debug("V?hicule ? cr?er : " + newVehicule);
            Vehicule criteria = vehiculeDelegate.create(subject, newVehicule);
            log.debug("# Cl? de v?hicule retourn? : " + newVehicule.getCle());
            Vehicule vehiculeCreated = vehiculeDelegate.find(subject, criteria);
            log.debug("V?hicule cr?? : " + vehiculeCreated );

			//V?rification d'un mandat PSU associ? ? l'ajout d'un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			if(!OracleDAOUtils.isEmpty(vehiculeForm.getImmatriculation())){
				psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.AJOUT);
			}

			if (newVehiculeForm.getEntiteCardexLiaison() != null){
				//On fait la liaison automatique avec le module reli?
            	if (newVehiculeForm.getEntiteCardexLiaison() instanceof SujetForm){
            		Sujet sujet = new SujetVO();
            		sujet.setCle(Long.parseLong(newVehiculeForm.getEntiteCardexLiaison().getCle()));
            		sujet.setSite(Long.parseLong(newVehiculeForm.getEntiteCardexLiaison().getSite()));
                    vehiculeCreated.setRole(Long.parseLong(newVehiculeForm.getRole()));
                    sujet.setRole(Long.parseLong(newVehiculeForm.getRole()));
                    vehiculeDelegate.addLienSujet(subject,vehiculeCreated, sujet);
            	}else if (newVehiculeForm.getEntiteCardexLiaison() instanceof DossierForm){
            		Dossier dossier = new DossierVO();
                    dossier.setCle(Long.parseLong(newVehiculeForm.getEntiteCardexLiaison().getCle()));
                    dossier.setSite(Long.parseLong(newVehiculeForm.getEntiteCardexLiaison().getSite()));
                    vehiculeCreated.setRole(Long.parseLong(newVehiculeForm.getRole()));
                    dossier.setRole(Long.parseLong(newVehiculeForm.getRole()));
                    vehiculeDelegate.addLienDossier(subject, vehiculeCreated, dossier);
            	}else if (newVehiculeForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		Societe societe = new SocieteVO();
            		societe.setCle(Long.parseLong(newVehiculeForm.getEntiteCardexLiaison().getCle()));
            		societe.setSite(Long.parseLong(newVehiculeForm.getEntiteCardexLiaison().getSite()));
                    vehiculeCreated.setRole(Long.parseLong(newVehiculeForm.getRole()));
                    societe.setRole(Long.parseLong(newVehiculeForm.getRole()));
                    vehiculeDelegate.addLienSociete(subject, vehiculeCreated, societe);
            	}
			}

			populateVehiculeForm(subject, criteria, newVehiculeForm);

			return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }

    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur l'icone de visualisation(loupe)  dans
     * le panneau de recherche des v?hicules.  L'application affiche le panneau de mise ? jour
     * du v?hicule s?lectionn? dans la liste de r?sultats de l'?cran de recherche.
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
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {

        log.debug("Acc?s ? un v?hicule");
        ActionMessages errors = new ActionMessages();
        try {
            VehiculeBusinessDelegate vehiculeDelegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = (VehiculeForm) form;
            Vehicule vehicule = new VehiculeVO();

            if (AideController.isNullOrEquals(vehiculeForm.getMotPasse(), vehiculeForm.getConfirmationMotPasse())) {
              ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,subject.getLocale());
              vehicule = vehiculeDelegate.find(subject, vehicule);
              log.debug("V?hicule trouv?: " + vehicule.toString());
              ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,subject.getLocale());
              //vehiculeForm.setNew(false);
              populateVehiculeForm(subject, vehicule, vehiculeForm);
              assignerNouveauVehicule(request, vehiculeForm);
              request.getSession().setAttribute(GlobalConstants.MotDePasse.VEHICULE_ATTEMPS,new Integer(0));
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.VEHICULE_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,subject.getLocale());
                vehicule = vehiculeDelegate.find(subject, vehicule);
                log.debug("V?hicule prot?g?: " + vehicule.toString());
                ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,subject.getLocale());
                populateVehiculeForm(subject, vehicule, vehiculeForm);
                vehiculeForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr?mentation du nombre d'essais
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.VEHICULE_ATTEMPS,nbOfAttempsInteger);

                return mapping.findForward("motpasse");
              }else {
                //On invalide la session utilisateur si le maximum d'essaie
                //de mot de passe est atteint
                request.getSession().invalidate();
                return mapping.findForward("session");
              }
            }

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        }
        catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    public ActionForward rafraichirConsultation(
			CardexAuthenticationSubject subject, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		log.debug("Acc?s ? un v?hicule");
		ActionMessages errors = new ActionMessages();
		try {
			VehiculeBusinessDelegate vehiculeDelegate = new VehiculeBusinessDelegate();
			VehiculeForm vehiculeForm = (VehiculeForm) form;
			Vehicule vehicule = new VehiculeVO();

			ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm,
					vehicule, subject.getLocale());
			vehicule = vehiculeDelegate.find(subject, vehicule);
			log.debug("V?hicule trouv?: " + vehicule.toString());
			ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,
					subject.getLocale());
			vehiculeForm.setNew(false);
			populateVehiculeForm(subject, vehicule, vehiculeForm);
			assignerNouveauVehicule(request, vehiculeForm);

			request.getSession().setAttribute(
					GlobalConstants.MotDePasse.VEHICULE_ATTEMPS,
					new Integer(0));
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
     * Cet ?v?nement survient lorsque l'utilisateur clique sur l'icone de visualisation(loupe)  dans
     * le panneau de recherche des v?hicules.  L'application affiche le panneau de mise ? jour
     * du v?hicule s?lectionn? dans la liste de r?sultats de l'?cran de recherche.
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
    public ActionForward showAcces(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {

        log.debug("Acc?s ? un v?hicule");
        ActionMessages errors = new ActionMessages();
        try {
            VehiculeBusinessDelegate vehiculeDelegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = (VehiculeForm) form;
            Vehicule vehicule = new VehiculeVO();
            if (AideController.isNullOrEquals(vehiculeForm.getMotPasse(), vehiculeForm.getConfirmationMotPasse())) {
              ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,subject.getLocale());
              vehicule = vehiculeDelegate.findAcces(subject, vehicule);
              log.debug("V?hicule trouv?: " + vehicule.toString());
              vehiculeForm.init(subject);
              ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,subject.getLocale());
              vehiculeForm.setNew(false);
              populateVehiculeForm(subject, vehicule, vehiculeForm);
              request.getSession().setAttribute(GlobalConstants.MotDePasse.VEHICULE_ATTEMPS,new Integer(0));
			  //V?rification d'un mandat PSU associ? ? la consultation d'un v?hicule (par immatriculation)
			  PSUMandatForm psuMandat = new PSUMandatForm();
			  if(!OracleDAOUtils.isEmpty(vehiculeForm.getImmatriculation())){
				  psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
				  PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.CONSULTATION);
			  }
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.VEHICULE_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,subject.getLocale());
                vehicule = vehiculeDelegate.findAcces(subject, vehicule);
                log.debug("V?hicule prot?g?: " + vehicule.toString());
                vehiculeForm.init(subject);
                ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,subject.getLocale());
                populateVehiculeForm(subject, vehicule, vehiculeForm);
                vehiculeForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr?mentation du nombre d'essais
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.VEHICULE_ATTEMPS,nbOfAttempsInteger);

                return mapping.findForward("motpasse");
              }else {
                //On invalide la session utilisateur si le maximum d'essaie
                //de mot de passe est atteint
                request.getSession().invalidate();
                return mapping.findForward("session");
              }
            }

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        }
        catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }


    /**
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
    public ActionForward showRechercheLiaisonVehicule(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException, CloneNotSupportedException {
        log.debug("Acc?s ? l'ecran de recherche vehicule liaison.");

        ActionMessages errors = new ActionMessages();
        CriteresRechercheVehiculeForm rechercheVehiculeForm = new CriteresRechercheVehiculeForm();
        CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();

        // Valeurs par d?faut
        rechercheVehiculeForm.init(subject);
		CardexUser utilisateur = (CardexUser)subject.getUser();
		rechercheVehiculeForm.setLienSite(String.valueOf(utilisateur.getSite()));

        if (form instanceof DossierHtmlForm) {
            DossierHtmlForm dossierForm = (DossierHtmlForm)form;
            rechercheVehiculeForm.setDossier(dossierForm);
            rechercheVehiculeForm.setEntiteCardexLiaison((EntiteCardexForm) ((DossierForm) form).clone() );
        }else if (form instanceof SujetHtmlForm) {
            SujetHtmlForm sujetForm = (SujetHtmlForm)form;
            rechercheVehiculeForm.setSujet(sujetForm);
            rechercheVehiculeForm.setEntiteCardexLiaison((EntiteCardexForm) ((SujetForm) form).clone() );
        }else if (form instanceof VehiculeHtmlForm) {
            VehiculeHtmlForm vehiculeForm = (VehiculeHtmlForm)form;
            rechercheVehiculeForm.setVehicule(vehiculeForm);
            rechercheVehiculeForm.setEntiteCardexLiaison((EntiteCardexForm) ((VehiculeForm) form).clone() );
        }else if (form instanceof SocieteHtmlForm) {
            SocieteHtmlForm societeForm = (SocieteHtmlForm)form;
            rechercheVehiculeForm.setSociete(societeForm);
            rechercheVehiculeForm.setEntiteCardexLiaison((EntiteCardexForm) ((SocieteForm) form).clone() );
        }else {
            log.error("L'objet source de la liaison dossier n'est pas de type valide(sujet,societe,dossier,vehicule)");
            return mapping.findForward("error");
        }

        request.getSession().setAttribute("rechercheVehicule",rechercheVehiculeForm);

        try {
        	VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();

            ValueListHandler completeList = new ValueListHandler();
            completeList.setList(new ArrayList());
            rechercheVehiculeForm.getListeResultat().vider();

            //Conversion du composant d'?tat(ActionForm) en composant
            //d'affaire(Value Object).
            ValueObjectMapper.convertCriteresRechercheVehiculeHtmlForm(
            		rechercheVehiculeForm, criteresRechercheVehicule,
                    subject.getLocale());

            //Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Vehicule> results = delegate.selectDefault(subject,criteresRechercheVehicule);

            ajouterResultatVehicule(subject, rechercheVehiculeForm, results);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton modifier dans
     * le panneau de mise ? jour d'un v?hicule.  Les modifications aux donn?es
     * sont enregistr?es dans le cardex, et l'?cran de mise ? jour
     * du v?hicule est affich?.
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
    public ActionForward update(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {

        log.debug("Mise ? jour d'un v?hicule");
        ActionMessages errors = new ActionMessages();
        try {
        	verifierToken(request);
            VehiculeBusinessDelegate vehiculeDelegate =
                new VehiculeBusinessDelegate();
            Vehicule vehicule = new VehiculeVO();
            VehiculeForm vehiculeForm = (VehiculeForm)form;

            ValueObjectMapper.convertVehiculeHtmlForm((VehiculeHtmlForm) form, vehicule,
                    subject.getLocale());
            FormFile file = null;
            String nomFichier = "";
            //On v?rifie s'il s'agit de l'ajout d'une photo
            if(vehiculeForm.getAjoutPhoto().getSourceFile() != null
                && StringUtils.isNotEmpty(vehiculeForm.getAjoutPhoto().getSourceFile().getFileName())){
                file = vehiculeForm.getAjoutPhoto().getUploadImage();
                nomFichier = file.getFileName();
                vehiculeForm.getAjoutPhoto().setExtension(vehiculeForm.getAjoutPhoto().getExtensionDeFilePath());
                //Est ce que la taille du fichier exc?de 4MB
                if (vehiculeForm.getAjoutPhoto().isTailleAccepte() == false) {
                    log.error("La taille du fichier est sup?rieure ? 7MB.");
                    throw (new BusinessRuleExceptionHandle("erreur_fichier")).getBusinessException();
                }else if(vehiculeForm.getAjoutPhoto().isPhoto() == false){
                    log.error("Ce fichier n'est pas une photo");
                    throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
                }else{
	            	vehiculeForm.getAjoutPhoto().setLien(vehiculeForm.getCle());
	            	vehiculeForm.getAjoutPhoto().setLienSite(vehiculeForm.getSite());
	            	vehiculeForm.getAjoutPhoto().setConfidentialite(vehiculeForm.getConfidentialite());
	            	addLienPhotoAjout(subject, mapping, vehiculeForm.getAjoutPhoto(), request, response);
                }
            }else{ //Sinon, on fait la mise ? jour de la fiche v?hicule.
	            log.debug("Mise ? jour du v?hicule: " + vehicule.toString());
	            vehiculeDelegate.update(subject, vehicule);
	            vehicule = vehiculeDelegate.find(subject, vehicule);
	            if (vehicule != null) {
	              ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,
	                    subject.getLocale());
	              log.debug("Modification effective du v?hicule: " + vehicule.toString());
	            }

				//V?rification d'un mandat PSU associ? ? la mise ? jour d'un v?hicule
				PSUMandatForm psuMandat = new PSUMandatForm();
				if(StringUtils.isNotEmpty(vehiculeForm.getImmatriculation())){
					psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
				}
				if(StringUtils.isNotEmpty(vehiculeForm.getProvince())){
					psuMandat.setProvince(vehiculeForm.getProvince());
				}
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.MISE_A_JOUR);
            }
            populateVehiculeForm(subject, vehicule, vehiculeForm);

            if (vehiculeForm.getEntiteCardexLiaison() != null){
            	assignerLienRole(request, vehiculeForm);

/*            	if(StringUtils.isNotEmpty(nomFichier)){
            		//Dans le cas d'ajout d'une photo, on revient ? l'?cran de consultation et non
            		//? celui du r?le.
            		return mapping.findForward("success");
            	}
            	if (vehiculeForm.getEntiteCardexLiaison() instanceof SujetForm){
					return mapping.findForward("successLiaisonSujet");
            	}else if (vehiculeForm.getEntiteCardexLiaison() instanceof DossierForm){
            		return mapping.findForward("successLiaisonDossier");
        		}else if (vehiculeForm.getEntiteCardexLiaison() instanceof SocieteForm){
        			return mapping.findForward("successLiaisonSociete");
        		}
*/            }

            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }

    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque dans le menu principal, l'utilisateur
     * a choisi de rechercher un v?hicule. L'application affiche alors le panneau de
     * recherche des v?hicules.
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
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Recherche par d?fault de v?hicules");
        ActionMessages errors = new ActionMessages();
        try {

            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm = (CriteresRechercheVehiculeForm) form;
            CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();

            //Valeur par d?faut
            criteresRechercheVehiculeHtmlForm.init(subject);

            //Conversion du composant d'?tat(ActionForm) en composant
            //d'affaire(Value Object).
            ValueObjectMapper.convertCriteresRechercheVehiculeHtmlForm(
                    criteresRechercheVehiculeHtmlForm, criteresRechercheVehicule,
                    subject.getLocale());


            //Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Vehicule> results = delegate.selectDefault(subject,criteresRechercheVehicule);

            ajouterResultatVehicule(subject, criteresRechercheVehiculeHtmlForm, results);

            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        }catch (IteratorException ie) {
            handleIteratorException(ie,errors,request);
            return mapping.findForward("error");
        }catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome,errors,request);
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque dans l'?cran de recherche de v?hicules,
     * l'utilisateur a choisi de rechercher un v?hicule selon des crit?res
     * diff?rents. L'application affiche alors le panneau de
     * recherche des v?hicules avec les r?sultats de la nouvelle recherche.
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
    public ActionForward search(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {

        log.debug("Recherche de v?hicules");
        ActionMessages errors = new ActionMessages();
        try {
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm = (CriteresRechercheVehiculeForm) form;
            CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();
            criteresRechercheVehiculeHtmlForm.getListeResultat().vider();
            //Conversion du composant d'?tat(ActionForm) en composant
            //d'affaire(Value Object).
            ValueObjectMapper.convertCriteresRechercheVehiculeHtmlForm(
                    criteresRechercheVehiculeHtmlForm, criteresRechercheVehicule,
                    subject.getLocale());
            //Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Vehicule> results = delegate.select(subject,criteresRechercheVehicule);

			//V?rification d'un mandat PSU associ? ? la recherche d'un v?hicule (par province)
			PSUMandatForm psuMandat = new PSUMandatForm();
			if(!OracleDAOUtils.isEmpty(criteresRechercheVehicule.getProvince())){
				psuMandat.setProvince(criteresRechercheVehicule.getProvince());
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.RECHERCHE);
			}

            ajouterResultatVehicule(subject, criteresRechercheVehiculeHtmlForm, results);

            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        }catch (IteratorException ie) {
            handleIteratorException(ie,errors,request);
            return mapping.findForward("error");
        }catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome,errors,request);
            return mapping.findForward("error");
        }
    }

    /**
	 * @param subject
	 * @param criteresRechercheVehiculeHtmlForm
	 * @param results
	 * @throws IteratorException
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
	private void ajouterResultatVehicule(CardexAuthenticationSubject subject, CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm, List<Vehicule> list) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		//Ajout des v?hicules dans le composant d'?tat (ActionForm)
		List currentList = new ArrayList();
		Iterator it = list.iterator();
		while (it.hasNext()){
		  Vehicule vehicule = (Vehicule)it.next();
		  log.debug(vehicule.toString());
		  VehiculeHtmlForm vehiculeForm = new  VehiculeForm();
		  ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,
		        subject.getLocale());
		  vehiculeForm.assignerValeurDeListe( subject );
		  currentList.add(vehiculeForm);
		}

		criteresRechercheVehiculeHtmlForm.setListeResultat( currentList );
		criteresRechercheVehiculeHtmlForm.getListeResultat().assignerTrierDefault(VehiculeTrieListe.CLE_IMMATRICULATION, false, new VehiculeTrieListe());
	}

    /**
	 * @param subject
	 * @param criteresRechercheVehiculeHtmlForm
	 * @param results
	 * @throws IteratorException
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
	private void ajouterResultatVehiculeAudit(CardexAuthenticationSubject subject, CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm, ValueListIterator results) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		//Ajout des v?hicules dans le composant d'?tat (ActionForm)
		Collection list = results.getNextElements(results.getSize());
		List currentList = new ArrayList();
		Iterator it = list.iterator();
		while (it.hasNext()){
		  Vehicule vehicule = (Vehicule)it.next();
		  log.debug(vehicule.toString());
		  VehiculeHtmlForm vehiculeForm = new  VehiculeForm();
		  ValueObjectMapper.convertVehicule(vehicule, vehiculeForm,
		        subject.getLocale());
		  vehiculeForm.assignerValeurDeListe( subject );
		  currentList.add(vehiculeForm);
		}

		criteresRechercheVehiculeHtmlForm.setListeResultatAudit( currentList );
	}

	/**
     * <p>
     * Cet ?v?nement surivient lorsque dans l'?cran de recherche de v?hicule, l'utilisateur a choisi
     * de rechercher un vehicule selon des crit?res diff?rents. L'application affiche alors le panneau de
     * recherche des vehicules avec les r?sultats de la nouvelle recherche.
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
    public ActionForward searchLiaison(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Recherche de vehicule en mode liaison");

        ActionMessages errors = new ActionMessages();

        try {
            VehiculeBusinessDelegate delegate =  new VehiculeBusinessDelegate();
            CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm = (CriteresRechercheVehiculeForm) form;
            CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();
            criteresRechercheVehiculeHtmlForm.getListeResultat().vider();

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheVehiculeHtmlForm(criteresRechercheVehiculeHtmlForm, criteresRechercheVehicule,subject.getLocale());

            // Ex?cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Vehicule> results = delegate.select(subject,criteresRechercheVehicule);

            ajouterResultatVehicule(subject, criteresRechercheVehiculeHtmlForm, results);

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
     * d'un ?l?ment de r?sultats de recherche des v?hicules en mode liaison.
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
    public ActionForward showRole(CardexAuthenticationSubject subject,
                                  ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException,
                                  ServletException {
        log.debug("Choix du type de lien avec un vehicule");

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Popule les informations d'un v?hicule obtenu dans la base de donn?es
     * dans le VehiculeForm sp?cifi?.
     *
     * @param Vehicule Les crit?res du v?hicule ? obtenir
     * @param VehiculeForm L'ActionForm bean ? populer ? partir du v?hicule obtenu
     *
     * @exception BusinessResourceException si une erreur syst?me survient
     * @exception BusinessException si une r?gle d'affaire n'est pas respect?e
     */
    private void populateVehiculeForm(CardexAuthenticationSubject subject,
                                     Vehicule criteria,
                                     VehiculeForm vehiculeForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        VehiculeBusinessDelegate delegate =
            new VehiculeBusinessDelegate();
        Vehicule vehicule = delegate.find(subject,criteria);
        vehiculeForm.resetOnglets();
        //log.debug("V?hicule trouv?: " + vehicule.toString());
        ValueObjectMapper.convertVehicule(vehicule, vehiculeForm, subject.getLocale());
        vehiculeForm.setConfirmationMotPasse(vehiculeForm.getMotPasse());
		rechercheLiensVehicule(subject, vehicule, vehiculeForm, delegate);
  }

    /**
     * Effectue la recherche des enregistrements li?s ? un v?hicule donn?,
     * affich?s dans les onglets du v?hicule.
     *
     * @param subject Le sujet authentifi?
     * @param Vehicule Les crit?res du vehicule a obtenir
     * @param VehiculeForm L'ActionForm bean a popul? ? partir du vehicule obtenu
     * @param delegate Lien avec la base de donn?es.
     *
     * @exception BusinessResourceException si une erreur syst?me survient
     * @exception BusinessException si une r?gle d'affaire n'est pas respect?e
     */
    private void rechercheLiensVehicule(CardexAuthenticationSubject subject,
                                     Vehicule vehicule,
                                     VehiculeForm vehiculeForm,
                                     VehiculeBusinessDelegate delegate) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        // Recherche des liens dossier
        Collection liensDossier = delegate.findLiensDossier(subject,vehicule);
        Iterator it = liensDossier.iterator();

        log.debug("Dossiers li?s (" + liensDossier.size() + ") :");

        while (it.hasNext()) {
            Dossier     linkDossier = (Dossier) it.next();
            DossierForm linkDossierForm = new DossierForm();

            ValueObjectMapper.convertDossier(linkDossier, linkDossierForm,
                    subject.getLocale());
            linkDossierForm.assignerValeurDeListe( subject );
            vehiculeForm.addDossier(linkDossierForm);
            log.debug(linkDossier.toString());
        }
        vehiculeForm.getListeDossiers().assignerTrierDefault(DossierOngletTrieListe.CLE_DATE_DEBUT, true, new DossierOngletTrieListe());

        // Recherche des liens narration
        Collection liensNarration = delegate.findLiensNarration(subject,
                vehicule);
        it = liensNarration.iterator();

        log.debug("Narrations li?es (" + liensNarration.size() + ") :");

        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();
			//On inscrit les valeurs de r?f?rence pour l'impression de la narration.
			linkNarration.setReference1(vehiculeForm.getImmatriculation());

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    subject.getLocale());
            linkNarrationForm.assignerValeurDeListe( subject );
            vehiculeForm.addNarration(linkNarrationForm);
            log.debug(linkNarration.toString());
        }
        vehiculeForm.getListeNarrations().assignerTrierDefault(NarrationOngletTrieListe.CLE_DATE_CREATION, true, new NarrationOngletTrieListe());

        // Recherche des liens photos
        Collection liensPhoto = delegate.findLiensPhoto(subject,
                vehicule);
        it = liensPhoto.iterator();
        log.debug("Photos li?es (" + liensPhoto.size() + ") :");

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
            vehiculeForm.addPhoto(sublist);
        }//while

        // Recherche des liens particularit?s
        Particularites liensParticularites = delegate.findLiensParticularite(subject,
                vehicule);
        log.debug("Particularites li?s (" + liensParticularites.getParticularitesChoisis().size() + ") :");
        ParticularitesForm linkParticularitesForm = new ParticularitesForm();
        ValueObjectMapper.convertParticularites(subject, liensParticularites,linkParticularitesForm,subject.getLocale());
        vehiculeForm.setParticularites(linkParticularitesForm);

        // Recherche des liens sujets
        Collection liensSujet = delegate.findLiensSujet(subject,
                vehicule);
        it = liensSujet.iterator();

        log.debug("Sujets li?s (" + liensSujet.size() + ") :");

        while (it.hasNext()) {
            Sujet     linkSujet = (Sujet) it.next();
            SujetForm linkSujetForm = new SujetForm();

            ValueObjectMapper.convertSujet(linkSujet, linkSujetForm,
                    subject.getLocale());
            linkSujetForm.assignerValeurDeListe( subject );
            vehiculeForm.addSujet(linkSujetForm);
            log.debug(linkSujet.toString());
        }
        vehiculeForm.getListeSujets().assignerTrierDefault(SujetOngletTrieListe.CLE_NOM, false, new SujetOngletTrieListe());

        // Recherche des liens soci?t?s
        Collection liensSociete = delegate.findLiensSociete(subject,
                vehicule);
        it = liensSociete.iterator();

        log.debug("Soci?t?s li?es (" + liensSociete.size() + ") :");

        while (it.hasNext()) {
            Societe     linkSociete = (Societe) it.next();
            SocieteForm linkSocieteForm = new SocieteForm();

            ValueObjectMapper.convertSociete(linkSociete, linkSocieteForm,
                    subject.getLocale());
            linkSocieteForm.assignerValeurDeListe( subject );
            vehiculeForm.addSociete(linkSocieteForm);
            log.debug(linkSociete.toString());
        }
        vehiculeForm.getListeSocietes().assignerTrierDefault(SocieteOngletTrieListe.CLE_NOM, false, new SocieteOngletTrieListe());

        //Valeurs par d?faut d'une nouvelle photo
        vehiculeForm.getAjoutPhoto().setTypeMultimedia(GlobalConstants.TypeMutliMedia.PHOTO);
        vehiculeForm.getAjoutPhoto().setCle("-1");
        vehiculeForm.getAjoutPhoto().setSite("-1");
        vehiculeForm.getAjoutPhoto().setLienMultimedia("-1");
        vehiculeForm.getAjoutPhoto().setLienSiteMultimedia("-1");
        vehiculeForm.getAjoutPhoto().setLienElement("-1");
        vehiculeForm.getAjoutPhoto().setLienSiteElement("-1");

    }

    /**
     * Popule les informations d'un dossier obtenu dans la base de donn?e
     * dans le VehiculeForm sp?cifi?.  Cette m?thode est appel?e par show et
     * showAcces.  Le diff?rence avec populateVehiculeForm est que le dossier
     * n'est plus appel? puisqu'il l'est d?j? dans show et showAcces.
     *
     * @param Vehicule Les crit?res du v?hicule ? obtenir
     * @param VehiculeForm L'ActionForm bean ? populer ? partir du v?hicule obtenu
     *
     * @exception BusinessResourceException si une erreur syst?me survient
     * @exception BusinessException si une r?gle d'affaire n'est pas respect?e
     */
    private void populateVehiculeFormShow(CardexAuthenticationSubject subject,
                                     Vehicule vehicule,
                                     VehiculeForm vehiculeForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        VehiculeBusinessDelegate delegate =new VehiculeBusinessDelegate();
        vehiculeForm.resetOnglets();
        vehiculeForm.setConfirmationMotPasse(vehiculeForm.getMotPasse());
		rechercheLiensVehicule(subject, vehicule, vehiculeForm, delegate);
  }


    /**
     * Rafraichissement de l'?cran de recherche lorsqu'une marque est s?lectionn?e
     * de mani?re ? filtrer la liste des mod?les.
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
    public ActionForward refreshRechercheVehicule(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.debug("Refresh ?cran de recherche de v?hicule");

        return mapping.findForward("success");
    }

    /**
     * Rafraichissement de l'?cran de v?hicule lorsqu'une marque est s?lectionn?e
     * de mani?re ? filtrer la liste des mod?les.
     *
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
    public ActionForward refreshVehicule(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.debug("Refresh ?cran du v?hicule");

        return mapping.findForward("success");
    }

    /**
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
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Param?tres de recherche par d?fault de v?hicules");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm = (CriteresRechercheVehiculeForm) form;
            CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();

            criteresRechercheVehiculeHtmlForm.init( subject );

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheVehiculeHtmlForm(criteresRechercheVehiculeHtmlForm,criteresRechercheVehicule,subject.getLocale());

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
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
        log.debug("Param?tres de recherche par d?fault de v?hicules en mode liaison");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm = (CriteresRechercheVehiculeForm) form;
            CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();
            DossierHtmlForm dossierHtmlForm = criteresRechercheVehiculeHtmlForm.getDossier();
            SujetHtmlForm sujetHtmlForm = criteresRechercheVehiculeHtmlForm.getSujet();
            VehiculeHtmlForm vehiculeHtmlForm = criteresRechercheVehiculeHtmlForm.getVehicule();
            SocieteHtmlForm societeHtmlForm = criteresRechercheVehiculeHtmlForm.getSociete();

            criteresRechercheVehiculeHtmlForm.init( subject );
            criteresRechercheVehiculeHtmlForm.setDossier(dossierHtmlForm);
            criteresRechercheVehiculeHtmlForm.setSujet(sujetHtmlForm);
            criteresRechercheVehiculeHtmlForm.setVehicule(vehiculeHtmlForm);
            criteresRechercheVehiculeHtmlForm.setSociete(societeHtmlForm);

            // Conversion du composant d'?tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheVehiculeHtmlForm(criteresRechercheVehiculeHtmlForm,criteresRechercheVehicule,subject.getLocale());

            return mapping.findForward("success");
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
            VehiculeBusinessDelegate delegate =
                new VehiculeBusinessDelegate();
            LienForm             lienForm = (LienForm) form;
            VehiculeForm         vehiculeForm = new VehiculeForm();
            Vehicule             vehicule = new VehiculeVO();
            Dossier              dossier = new DossierVO();
            vehiculeForm.init(subject);
            vehicule.setCle(lienForm.getCleSource());
            vehicule.setSite(lienForm.getSiteSource());
            vehicule.setTypeLien(lienForm.getTypeLien());
            //R?le par d?faut pour ce lien
            vehicule.setRole(GlobalConstants.Role.SANS_OBJET);

            dossier.setCle(lienForm.getCleDestination());
            dossier.setSite(lienForm.getSiteDestination());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(GlobalConstants.Role.SANS_OBJET);

            log.debug(lienForm.toString());
            delegate.addLienDossier(subject, vehicule,
                                    dossier);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//R?cup?ration du num?ro de dossier qui vient d'?tre associ?
			Collection dossierListe = vehiculeForm.getDossiers();
			Iterator it = dossierListe.iterator();
			String numeroCardex = "";
			while (it.hasNext()) {
				DossierForm dossierAssocie= (DossierForm)it.next();
				if(dossierAssocie.getCle().equals(Long.toString(dossier.getCle()))){
					numeroCardex = dossierAssocie.getNumeroCardex().toString();
				}
			}
			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.LIAISON);

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
     * d'un ?l?ment de r?sultats de recherche des sujets en mode liaison.
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
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            LienForm          lienForm = (LienForm) form;
            VehiculeForm      vehiculeForm = new VehiculeForm();
            Vehicule          vehicule = new VehiculeVO();
            Sujet             sujet = new SujetVO();
            vehiculeForm.init(subject);
            vehicule.setCle(lienForm.getCleSource());
            vehicule.setSite(lienForm.getSiteSource());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(GlobalConstants.Role.SANS_OBJET);

            sujet.setCle(lienForm.getCleDestination());
            sujet.setSite(lienForm.getSiteDestination());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(GlobalConstants.Role.SANS_OBJET);

            delegate.addLienSujet(subject, vehicule, sujet);
            log.debug(lienForm.toString());
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//R?cup?ration du nom du num?ro de fiche sujet qui vient d'?tre associ?
			Collection sujetListe = vehiculeForm.getSujets();
			Iterator it = sujetListe.iterator();
			String numeroFiche = "";
			while (it.hasNext()) {
				SujetForm sujetAssocie= (SujetForm)it.next();
				if(sujetAssocie.getCle().equals(Long.toString(sujet.getCle()))){
					numeroFiche = sujetAssocie.getNumeroFiche();
				}
			}

			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.LIAISON);

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
     * d'un ?l?ment de r?sultats de recherche des soci?t?s en mode liaison.
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
    public ActionForward addLienSociete(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Liaison d'une soci?t?");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            LienForm          lienForm = (LienForm) form;
            VehiculeForm      vehiculeForm = new VehiculeForm();
            Vehicule          vehicule = new VehiculeVO();
            Societe           societe = new SocieteVO();
            vehiculeForm.init(subject);

            vehicule.setCle(lienForm.getCleSource());
            vehicule.setSite(lienForm.getSiteSource());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(GlobalConstants.Role.SANS_OBJET);

            societe.setCle(lienForm.getCleDestination());
            societe.setSite(lienForm.getSiteDestination());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(GlobalConstants.Role.SANS_OBJET);

            delegate.addLienSociete(subject, vehicule, societe);
            log.debug(lienForm.toString());
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//R?cup?ration du nom de la soci?t? qui vient d'?tre associ?e
			Collection dossierListe = vehiculeForm.getSocietes();
			Iterator it = dossierListe.iterator();
			String nom = "";
			while (it.hasNext()) {
				SocieteForm societeAssocie= (SocieteForm)it.next();
				if(societeAssocie.getCle().equals(Long.toString(societe.getCle()))){
					nom = societeAssocie.getNom();
				}
			}
			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.LIAISON);

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
     * Cet ?v?nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un ?l?ment de l'onglets dossiers dans l'?cran
     * de consultation v?hicule.
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
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            LienForm          lienForm = (LienForm) form;
            VehiculeForm      vehiculeForm = new VehiculeForm();
            Vehicule          vehicule = new VehiculeVO();
            Dossier           dossier = new DossierVO();
            vehiculeForm.init(subject);

            vehicule.setCle(lienForm.getCleSource());
            vehicule.setSite(lienForm.getSiteSource());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));
            vehicule.setLien(lienForm.getCle());
            vehicule.setLienSite(lienForm.getSite());

            dossier.setCle(lienForm.getCleDestination());
            dossier.setSite(lienForm.getSiteDestination());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));
            dossier.setLien(lienForm.getCle());
            dossier.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienDossier(subject,vehicule,dossier);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			//psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton ?puration dans
     * le panneau de recherche des v?hicules.  L'?puration consiste ? supprimer tous les
     * v?hicules donc le niveau de confidentialit? est 8.
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
        log.debug("?puration des v?hicules");

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
    		String nomRapport = chemin+"V?hicules ? ?purer "+ siteDescription + " (" + dateRapport+").pdf";
    		InputStream gabarit = getClass().getClassLoader().getResourceAsStream(RapportsConfiguration.RAPPORT_EPURATION_VEHICULES);
			log.debug("Sauvegarder v?hicules ? ?purer");

			long site = utilisateur.getSite();
			resultSet = rapportDelegate.rapportEpuration(site, connection, "CARDEX_RAPPORT.SP_RAP_VE_EPURATION");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.debug(context.getRealPath("/rapports/"));
			ServletContext context = request.getSession().getServletContext();
	        parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
            parameters.put("REPORT_CONNECTION",connection);
			parameters.put("UTILISATEUR", utilisateur.getCode());
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
			// Sauvegarde dans un fichier
			log.debug("?puration des v?hicules (Sauvegarde dans un fichier)");
			(new PDFImpressionRapport()).impression(nomRapport, print);
			//On proc?de ensuite ? l'?puration
            VehiculeBusinessDelegate vehiculeDelegate =
                new VehiculeBusinessDelegate();
            vehiculeDelegate.delete(subject);
            //Apr?s la suppression, on vide la liste des r?sultats.
            CriteresRechercheVehiculeForm criteresRechercheVehiculeForm = new CriteresRechercheVehiculeForm();
            criteresRechercheVehiculeForm.init( subject );
            criteresRechercheVehiculeForm.getListeResultat().vider();
            request.getSession().setAttribute("rechercheVehicule",criteresRechercheVehiculeForm);

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
    }

    /**
     * <p>
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
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
    public ActionForward deleteLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une narration et un v?hicule.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = new VehiculeForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Vehicule vehicule = new VehiculeVO();
            Narration narration = new NarrationVO();
            vehiculeForm.init(subject);
            vehiculeForm.setCle(narrationForm.getLien());
            vehiculeForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Vehicule: " + vehicule);
            log.debug("Narration: " + narration);
            delegate.deleteLienNarration(subject,vehicule,narration);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(narrationForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
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
    public ActionForward deleteLienPhoto(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une photo et un v?hicule.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = new VehiculeForm();
            PhotoForm photoForm = (PhotoForm)form;
            Vehicule vehicule = new VehiculeVO();
            Photo photo = new PhotoVO();
            vehiculeForm.init(subject);
            vehiculeForm.setCle(photoForm.getLien());
            vehiculeForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm, photo,
                    subject.getLocale());
            log.debug("Vehicule: " + vehicule);
            log.debug("Photo: " + photo);
            delegate.deleteLienPhoto(subject,vehicule,photo);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
			psuMandat.setReference1(Long.toString(photo.getLienElement()));
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.SUPPRESSION);

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
            VehiculeBusinessDelegate delegate =
                new VehiculeBusinessDelegate();
            LienForm            lienForm = (LienForm) form;
            VehiculeForm        vehiculeForm = new VehiculeForm();
            Vehicule            vehicule = new VehiculeVO();
            Sujet               sujet = new SujetVO();
            vehiculeForm.init(subject);

            vehicule.setCle(lienForm.getCleSource());
            vehicule.setSite(lienForm.getSiteSource());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));
            vehicule.setLien(lienForm.getCle());
            vehicule.setLienSite(lienForm.getSite());

            sujet.setCle(lienForm.getCleDestination());
            sujet.setSite(lienForm.getSiteDestination());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));
            sujet.setLien(lienForm.getCle());
            sujet.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienSujet(subject, vehicule,
                                    sujet);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			//psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.SUPPRESSION);

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
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            LienForm            lienForm = (LienForm) form;
            VehiculeForm        vehiculeForm = new VehiculeForm();
            Vehicule            vehicule = new VehiculeVO();
            Societe             societe = new SocieteVO();
            vehiculeForm.init(subject);

            vehicule.setCle(lienForm.getCleSource());
            vehicule.setSite(lienForm.getSiteSource());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));
            vehicule.setLien(lienForm.getCle());
            vehicule.setLienSite(lienForm.getSite());

            societe.setCle(lienForm.getCleDestination());
            societe.setSite(lienForm.getSiteDestination());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));
            societe.setLien(lienForm.getCle());
            societe.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienSociete(subject, vehicule,
                                    societe);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//V?rification d'un mandat PSU associ? ? la suppression d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			//psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.SUPPRESSION);

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
    public ActionForward permettreModificationLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Permettre la modification d'une narration li?e ? un vehicule.");
        ActionMessages errors = new ActionMessages();

        try {
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = new VehiculeForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Vehicule vehicule = new VehiculeVO();
            Narration narration = new NarrationVO();
            vehiculeForm.init(subject);
            vehiculeForm.setCle(narrationForm.getLien());
            vehiculeForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur("");
            narrationForm.setAutoriteApprobateur("");
            narrationForm.setAutoriteNarration("");
            narrationForm.setConfidentialiteApprobateur("");
            narrationForm.setDateApprobation("");
            narrationForm.setAutoriteNarration(narrationForm.getAutoriteCreateur());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Vehicule: " + vehicule);
            log.debug("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

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
     * <p>
     * Par d?faut, l'application remplit automatiquement les champs suivants :
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
    public ActionForward addLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Liasion d'une narration ? un v?hicule.");
        ActionMessages errors = new ActionMessages();
        ActionMessages messages = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            NarrationForm narrationForm = (NarrationForm)form;
            VehiculeForm vehiculeForm  = narrationForm.getVehicule();
            Vehicule vehicule = new VehiculeVO();
            Narration narration = new NarrationVO();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Vehicule : " + vehicule);
            log.debug("Narration: " + narration);
            NarrationBaliseUtil.assignerMessageSiNarrationANettoyer(messages, narrationForm.getNarrationAvecFormat());
            delegate.addLienNarration(subject,vehicule,narration);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

			//R?cup?ration de la date de cr?ation de la narration qui vient d'?tre associ?e
			Collection narrationListe = vehiculeForm.getNarrations();
			Iterator it = narrationListe.iterator();
			String dateCreation = "";
			while (it.hasNext()) {
				NarrationForm narrationAssocie= (NarrationForm)it.next();
				if(narrationAssocie.getCle().equals(Long.toString(narration.getCle()))){
					dateCreation = narrationAssocie.getDateCreation();
				}
			}
			//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un v?hicule
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(dateCreation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.LIAISON);
			//Dans le cas des narratisons, on v?rifie ?galement pour les mots-cl?s.
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			psuMandat.setReference1(narrationForm.getNarrationSansFormat());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.NARRATION, GlobalConstants.TypeAction.AJOUT);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
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
    public ActionForward approuveLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Approbation d'une narration li?e ? un v?hicule.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = new VehiculeForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Vehicule vehicule = new VehiculeVO();
            Narration narration = new NarrationVO();
            vehiculeForm.init(subject);
            vehiculeForm.setCle(narrationForm.getLien());
            vehiculeForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur(user.getCode());
            narrationForm.setAutoriteApprobateur(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setAutoriteNarration(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setConfidentialiteApprobateur(Long.toString(privilege.getNiveauConfidentialite()));
            narrationForm.setDateApprobation(currentDate);
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("V?hicule : " + vehicule);
            log.debug("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

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
    public ActionForward addLienPhoto(CardexAuthenticationSubject subject,
                                  ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                 throws IOException, ServletException {

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            VehiculeForm vehiculeForm = new VehiculeForm();
            Photo photo = new PhotoVO();
            Vehicule vehicule = new VehiculeVO();
            log.debug("PhotoForm a li?e : " + photoForm);
            vehiculeForm.init(subject);

            vehiculeForm.setCle(photoForm.getLien());
            vehiculeForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm,vehicule,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());

            FormFile   file = photoForm.getUploadImage();

            //Est ce que la taille du fichier exc?de 4MB
            if (photoForm.isTailleAccepte() == false) {
                log.error("La taille du fichier est sup?rieure ? 4MB.");
                return mapping.findForward("error");
            }else if(photoForm.isPhoto() == false){
                log.error("Ce fichier n'est pas une photo");
                throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
            }else{
            	byte[] data = file.getFileData();
            	photo.setImage( data );

	            log.debug("Photo a li?e : " + photo);
	            photo= delegate.addLienPhoto(subject,vehicule,photo);
	            log.debug("Photo li?e : " + photo);
	            file.destroy();

				populateVehiculeForm(subject, vehicule, vehiculeForm);
				assignerNouveauVehicule(request, vehiculeForm);

				//V?rification d'un mandat PSU associ? ? l'ajout d'une liaison ? un v?hicule
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setImmatriculation(vehiculeForm.getImmatriculation());
				psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
				psuMandat.setReference1(Long.toString(photo.getLienElement()));
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.VEHICULE, GlobalConstants.TypeAction.LIAISON);

		        return mapping.findForward("success");
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
    public ActionForward updateLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour d'un lien entre une narration et un vehicule.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = new VehiculeForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Vehicule vehicule = new VehiculeVO();
            Narration narration = new NarrationVO();
            vehiculeForm.init(subject);
            vehiculeForm.setCle(narrationForm.getLien());
            vehiculeForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Vehicule: " + vehicule);
            log.debug("Narration: " + narration);
            delegate.updateLienNarration(subject,narration);
            populateVehiculeForm(subject, vehicule, vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

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
     * Mise ? jour des liens particularit?s en conservant un audit.
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
    public ActionForward updateLienParticularite(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise ? jour des liens particularit?s en conservant un audit.");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = new VehiculeForm();
            Vehicule vehicule = new VehiculeVO();
            ParticularitesForm particularitesForm = (ParticularitesForm)form;
            Particularites particularites = new ParticularitesVO();
            vehiculeForm.init(subject);
            vehiculeForm.setCle(particularitesForm.getLien());
            vehiculeForm.setSite(particularitesForm.getLienSite());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            ValueObjectMapper.convertParticularitesHtmlForm(particularitesForm,particularites,subject.getLocale());
            delegate.updateLienParticularite(subject,vehicule,particularites);
            vehicule = delegate.find(subject,vehicule);
            populateVehiculeForm(subject,vehicule,vehiculeForm);
            assignerNouveauVehicule(request, vehiculeForm);

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
     * Mise ? jour du r?le dans les onglets d'un v?hicule.
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
        log.debug("Mise ? jour de la liaison dans un v?hicule");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm    vehiculeForm = (VehiculeForm) form;
            vehiculeForm.setLien((String)request.getParameter("lien"));
            vehiculeForm.setLienSite((String)request.getParameter("lienSite"));
            vehiculeForm.setRole((String)request.getParameter("role"));
            vehiculeForm.setCle((String)request.getParameter("cleVehicule"));
            vehiculeForm.setSite((String)request.getParameter("siteVehicule"));
            Vehicule 	vehicule = new VehiculeVO();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm,
                    vehicule, subject.getLocale());
            delegate.updateLien(subject, vehicule);
            populateVehiculeForm(subject, vehicule, vehiculeForm);

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
	 * @param request
	 * @param vehiculeForm
	 */
	private void assignerNouveauVehicule(HttpServletRequest request, VehiculeForm vehiculeForm) {
		VehiculeForm vehiculeFormOriginal = (VehiculeForm) request.getSession().getAttribute("vehicule");
		vehiculeForm.setEntiteCardexLiaison( vehiculeFormOriginal.getEntiteCardexLiaison() );
		vehiculeForm.setNew(vehiculeFormOriginal.isNew());
		request.getSession().setAttribute("vehicule", vehiculeForm);
	}

    private void assignerLienRole(HttpServletRequest request, VehiculeForm vehiculeForm) {
		LienForm lienForm = new LienForm();
		lienForm.assignerSource(vehiculeForm.getEntiteCardexLiaison());
		lienForm.assignerDestination(vehiculeForm);
		request.getSession().setAttribute("lien", lienForm);
	}

	private EntiteCardexForm obtenirEntiteCardexLiaison(HttpServletRequest request, String formNameEntiteCardexLiaison) {
		EntiteCardexLiaison entiteCardexLiaison = (EntiteCardexLiaison) request.getSession().getAttribute( formNameEntiteCardexLiaison );
		return entiteCardexLiaison.getEntiteCardexLiaison();
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
    private void addLienPhotoAjout(CardexAuthenticationSubject subject,
                                  ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                 throws IOException, ServletException {

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            VehiculeForm vehiculeForm = new VehiculeForm();
            Photo photo = new PhotoVO();
            Vehicule vehicule = new VehiculeVO();
            log.debug("PhotoForm a li?e : " + photoForm);

            vehiculeForm.init(subject);
            vehiculeForm.setCle(photoForm.getLien());
            vehiculeForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm,vehicule,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());
            FormFile   file = photoForm.getUploadImage();
        	byte[] data = file.getFileData();
        	photo.setImage( data );
            log.debug("Photo a li?e : " + photo);
            photo = delegate.addLienPhoto(subject,vehicule,photo);
            //file.destroy();

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            bre.printStackTrace();
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            be.printStackTrace();
        } catch (ValueObjectMapperException vome) {
        	vome.printStackTrace();
        }

    }

    /**
     * <p>
     * Cet ?v?nement survient lorsque l'utilisateur clique sur le bouton fermer dans
     * le panneau de consultation d'un v?hicule.
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
    public ActionForward retour(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {


        log.debug("Retour de la consultation d'un v?hicule");

        //ActionMessages errors = new ActionMessages();

            VehiculeForm vehiculeForm = (VehiculeForm)form;

            if (vehiculeForm.getEntiteCardexLiaison() != null){
            	assignerLienRole(request, vehiculeForm);

            	if (vehiculeForm.getEntiteCardexLiaison() instanceof SujetForm){
					return mapping.findForward("successLiaisonSujet");
            	}else if (vehiculeForm.getEntiteCardexLiaison() instanceof DossierForm){
            		return mapping.findForward("successLiaisonDossier");
            	}else if (vehiculeForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		return mapping.findForward("successLiaisonSociete");
            	}
            }

            return mapping.findForward("success");
    }

    /**
     * <p>
     * Recherche directe d'un v?hicule ? partir du menu principal.
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
    public ActionForward rechercheDirecteVehicule(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Recherche directe de v?hicules");

        ActionMessages errors = new ActionMessages();

        try {
        	VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
        	CriteresRechercheVehiculeVO criteresRechercheVehicule = new CriteresRechercheVehiculeVO();
        	criteresRechercheVehicule.setImmatriculation(((String)request.getParameter("VEHICULE").toUpperCase()));
            //vehicule = delegate.rechercheDirecte(subject, vehicule);
            List resultats = delegate.select(subject,criteresRechercheVehicule);
            if (resultats.size() == 0){
            	//Aucun v?hicule trouv?
            	return mapping.findForward("erreurRecherche");
            }
            Iterator iter = resultats.iterator();
            if (resultats.size() == 1){
				Vehicule vehiculeTrouve = (Vehicule) iter.next();
            	//Un vehicule a ?t? trouv?. On inscrit donc une entr?e dans la table des acc?s.
            	delegate.ajoutAcces(subject, vehiculeTrouve);
            	VehiculeForm vehiculeForm = new VehiculeForm();
            	populateVehiculeForm(subject, vehiculeTrouve, vehiculeForm);
            	request.getSession().setAttribute("vehicule",vehiculeForm);
                return mapping.findForward("success");
            }
            if (resultats.size() > 1){
            	//Plusieurs vehicules ont ?t? trouv?s avec le m?me num?ro d'immatriculation.
				//On pr?pare la liste pour que l'utilisateur choisisse le bon v?hicule
            	CriteresRechercheVehiculeForm criteresRechercheVehiculeHtmlForm = new CriteresRechercheVehiculeForm();
            	criteresRechercheVehiculeHtmlForm.getListeResultat().init();
            	ajouterResultatVehicule(subject, criteresRechercheVehiculeHtmlForm, resultats);
            	request.getSession().setAttribute("rechercheVehicule",criteresRechercheVehiculeHtmlForm);
            	return mapping.findForward("choisirResultat");
            }
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        }
        return mapping.findForward("success");
    }

}