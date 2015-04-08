/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

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
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSocieteVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
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
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSocieteForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.LienForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.AdresseOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.DossierOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteLiaisonTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeOngletTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
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
import com.lotoquebec.cardexCommun.log.LoggerCardex;
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
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des soci�t�s.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.20 $, $Date: 2002/04/30 17:47:53 $
 */
public class SocieteAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * le panneau de recherche des soci�t�s.  L'application affiche le panneau de mise � jour.
     * L'utilisateur a pr�alablement saisie les informations  les donn�es relatives � l'identification
     * de la soci�t�.
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> site d'origine (site de l'utilisateur)
     * <ul> statut (actif)
     * <ul> Fond� (ind�termin�)
     * <ul> Date de l'assignation (date du jour)
     * </li>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Cr�ation d'une nouvelle societe");

        SocieteForm societeForm = (SocieteForm) form;
    	societeForm.init(subject);
		EntiteCardexForm entiteCardex = obtenirEntiteCardexLiaison(request, "rechercheSociete");
		societeForm.setEntiteCardexLiaison( entiteCardex );
        societeForm.setRole((String)request.getParameter("role"));
        //Dans le cas d'un lien entre 2 soci�t�s, on met le r�le Sans objet par d�faut
        if (societeForm.getEntiteCardexLiaison() instanceof SocieteForm){
        	societeForm.setRole(String.valueOf(GlobalConstants.Role.SANS_OBJET));
        }
        // Valeurs par d�faut
        societeForm.resetOnglets();
        societeForm.setStatut(GlobalConstants.Statut.SOCIETE_REGULIER);
        societeForm.setNew(true);

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton
     * sauvegarder dans le panneau de cr�ation d'une soci�t�.  La nouvelle
     * soci�t� est enregistr�e dans le cardex et l'�cran de mise a jour de
     * la soci�t� est affich�.
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> Hi�archie (Niveau d'authorit� de l'utilisateur)
     * <ul> Num�ro de cardex ("                  *")
     * </li>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output
     * survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward save(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Sauvegarde d'une nouvelle soci�t�");

        ActionErrors    errors = new ActionErrors();
        Societe         newSociete = new SocieteVO();
        SocieteForm     newSocieteForm = (SocieteForm) form;

        try {
        	verifierToken(request);
            SocieteBusinessDelegate societeDelegate =
                new SocieteBusinessDelegate();

            ValueObjectMapper.convertSocieteHtmlForm(newSocieteForm, newSociete,
                    subject.getLocale());

            // Pas de valeurs par d�faut.

            log.fine("Societe � cr�er : " + newSociete);

            Societe criteria = societeDelegate.create(subject, newSociete);

			//V�rification d'un mandat PSU associ� � la cr�ation d'une soci�t� par nom
			if(!OracleDAOUtils.isEmpty(newSocieteForm.getNom())){			
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setSocieteNom(newSocieteForm.getNom().toUpperCase());
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.AJOUT);
			}

			Societe societeCreated = societeDelegate.find(subject, criteria);
			log.fine("Societe cr�� : " + societeCreated);

			if (newSocieteForm.getEntiteCardexLiaison() != null){
				//On fait la liaison automatique avec le module reli�
            	if (newSocieteForm.getEntiteCardexLiaison() instanceof SujetForm){
            		Sujet sujet = new SujetVO();
            		sujet.setCle(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getCle()));
            		sujet.setSite(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getSite()));
            		societeCreated.setRole(Long.parseLong(newSocieteForm.getRole()));
                    sujet.setRole(Long.parseLong(newSocieteForm.getRole()));
                    societeDelegate.addLienSujet(subject, societeCreated, sujet);
            	}else if (newSocieteForm.getEntiteCardexLiaison() instanceof DossierForm){
            		Dossier dossier = new DossierVO();
                    dossier.setCle(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getCle()));
                    dossier.setSite(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getSite()));
                    societeCreated.setRole(Long.parseLong(newSocieteForm.getRole()));
                    dossier.setRole(Long.parseLong(newSocieteForm.getRole()));
                    societeDelegate.addLienDossier(subject, societeCreated, dossier);
            	}else if (newSocieteForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		Societe societe = new SocieteVO();
            		societe.setCle(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getCle()));
            		societe.setSite(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getSite()));
            		societeCreated.setRole(Long.parseLong(newSocieteForm.getRole()));
                    societe.setRole(Long.parseLong(newSocieteForm.getRole()));
                    societeDelegate.addLienSociete(subject, societeCreated, societe);
            	}else if (newSocieteForm.getEntiteCardexLiaison() instanceof VehiculeForm){
            		Vehicule vehicule = new VehiculeVO();
            		vehicule.setCle(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getCle()));
            		vehicule.setSite(Long.parseLong(newSocieteForm.getEntiteCardexLiaison().getSite()));
            		societeCreated.setRole(Long.parseLong(newSocieteForm.getRole()));
                    vehicule.setRole(Long.parseLong(newSocieteForm.getRole()));
                    societeDelegate.addLienVehicule(subject, societeCreated, vehicule);
            	}
			}
            populateSocieteForm(subject, criteria, newSocieteForm);
            //Apr�s l'ajout d'une soci�t�, on rafra�chit les listes pour que la nouvelle soci�t� soit pr�sente.
           	ListeCache listeCache = ListeCache.getInstance();
        	listeCache.vider(null);

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
     * Cet �v�nement survient lorsque l'utilisateur clique sur l'icone de
     * visualisation(loupe) dans le panneau de recherche des soci�t�s.
     * L'application affiche le panneau de mise � jour de la soci�t�
     * s�lectionn� dans la liste de r�sultats de l'�cran de recherche.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output
     * survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Acc�s � une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate societeDelegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = (SocieteForm) form;
            Societe     societe     = new SocieteVO();

            log.fine("Societe recherch�: " + societeForm.toString());

            if (AideController.isNullOrEquals(societeForm.getMotPasse(), societeForm.getConfirmationMotPasse()) ) {
              ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,subject.getLocale());
              societe = societeDelegate.find(subject, societe);
              log.fine("Societe trouv�: " + societe.toString());
              ValueObjectMapper.convertSociete(societe, societeForm,subject.getLocale());
              //societeForm.setNew(false);
              populateSocieteFormShow(subject, societe, societeForm);

              societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
              societeForm.setNew(obtenirStatutNouveau(request));
              request.getSession().setAttribute(GlobalConstants.MotDePasse.SOCIETE_ATTEMPS,new Integer(0));
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.SOCIETE_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,subject.getLocale());
                societe = societeDelegate.find(subject, societe);
                log.fine("Societe prot�g�: " + societe.toString());
                ValueObjectMapper.convertSociete(societe, societeForm,subject.getLocale());
                populateSocieteForm(subject, societe, societeForm);
                societeForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr�mentation du nombre d'essaie
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.SOCIETE_ATTEMPS,nbOfAttempsInteger);

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
    
    public ActionForward rafraichirConsultation(
			CardexAuthenticationSubject subject, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Acc�s � une soci�t�");

		ActionMessages errors = new ActionMessages();

		try {
			SocieteBusinessDelegate societeDelegate = new SocieteBusinessDelegate();
			SocieteForm societeForm = (SocieteForm) form;
			Societe societe = new SocieteVO();

			log.fine("Societe recherch�: " + societeForm.toString());

			ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
					subject.getLocale());
			societe = societeDelegate.find(subject, societe);
			log.fine("Societe trouv�: " + societe.toString());
			ValueObjectMapper.convertSociete(societe, societeForm, subject
					.getLocale());
			societeForm.setNew(false);
			populateSocieteFormShow(subject, societe, societeForm);

			request.getSession().setAttribute(
					GlobalConstants.MotDePasse.SOCIETE_ATTEMPS,
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
     * Cet �v�nement survient lorsque l'utilisateur clique sur l'icone de
     * visualisation(loupe) dans le panneau de recherche des soci�t�s.
     * L'application affiche le panneau de mise � jour de la soci�t�
     * s�lectionn� dans la liste de r�sultats de l'�cran de recherche.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output
     * survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showAcces(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Acc�s � une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate societeDelegate = new SocieteBusinessDelegate();
            SocieteForm         societeForm = (SocieteForm) form;
            Societe                 societe = new SocieteVO();

            log.fine("Societe recherch�: " + societeForm.toString());

            if (AideController.isNullOrEquals(societeForm.getMotPasse(), societeForm.getConfirmationMotPasse()) ) {
              ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,subject.getLocale());
              societe = societeDelegate.findAcces(subject, societe);
              log.fine("Societe trouv�: " + societe.toString());
              societeForm.init(subject);
              ValueObjectMapper.convert(societe, societeForm);
              societeForm.setNew(false);
              populateSocieteFormShow(subject, societe, societeForm);

              request.getSession().setAttribute(GlobalConstants.MotDePasse.SOCIETE_ATTEMPS,new Integer(0));
			  //V�rification d'un mandat PSU associ� � la consultation d'une soci�t� (par nom ou par fiche)
			  PSUMandatForm psuMandat = new PSUMandatForm();
			  if(!OracleDAOUtils.isEmpty(societeForm.getNom())){
			  	  psuMandat.setSocieteNom(societeForm.getNom().toUpperCase());
			  }
			  if(!OracleDAOUtils.isEmpty(societeForm.getNumeroFiche())){
				  psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			  }
			  PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.CONSULTATION);
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.SOCIETE_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,subject.getLocale());
                societe = societeDelegate.findAcces(subject, societe);
                log.fine("Societe prot�g�: " + societe.toString());
                ValueObjectMapper.convertSociete(societe, societeForm,subject.getLocale());
                populateSocieteForm(subject, societe, societeForm);
                societeForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr�mentation du nombre d'essaie
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.SOCIETE_ATTEMPS,nbOfAttempsInteger);

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
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output
     * survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showRechercheLiaisonSociete(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException, CloneNotSupportedException {
        log.fine("Acc�s � l'ecran de recherche societe");

        ActionErrors errors = new ActionErrors();
        CriteresRechercheSocieteForm rechercheSocieteForm = new CriteresRechercheSocieteForm();
        CriteresRechercheSocieteVO criteresRechercheSociete = new CriteresRechercheSocieteVO();

        // Valeurs par d�faut
        rechercheSocieteForm.init(subject);
		CardexUser utilisateur = (CardexUser)subject.getUser();
		rechercheSocieteForm.setLienSite(String.valueOf(utilisateur.getSite()));
        if (form instanceof DossierHtmlForm) {
            DossierHtmlForm dossierForm = (DossierHtmlForm)form;
            rechercheSocieteForm.setDossier(dossierForm);
            rechercheSocieteForm.setEntiteCardexLiaison((EntiteCardexForm) ((DossierForm) form).clone() );
        }else if (form instanceof SujetHtmlForm) {
            SujetHtmlForm sujetForm = (SujetHtmlForm)form;
            rechercheSocieteForm.setSujet(sujetForm);
            rechercheSocieteForm.setEntiteCardexLiaison((EntiteCardexForm) ((SujetForm) form).clone() );
        }else if (form instanceof VehiculeHtmlForm) {
            VehiculeHtmlForm vehiculeForm = (VehiculeHtmlForm)form;
            rechercheSocieteForm.setVehicule(vehiculeForm);
            rechercheSocieteForm.setEntiteCardexLiaison((EntiteCardexForm) ((VehiculeForm) form).clone() );
        }else if (form instanceof SocieteHtmlForm) {
            SocieteHtmlForm societeForm = (SocieteHtmlForm)form;
            rechercheSocieteForm.setSociete(societeForm);
            rechercheSocieteForm.setEntiteCardexLiaison((EntiteCardexForm) ((SocieteForm) form).clone() );
            //Le r�le n'est pas n�cessaire entre 2 soci�t�s
            rechercheSocieteForm.setLienRoleRequis(false);
        }else {
            log.severe("L'objet source de la liaison dossier n'est pas de type valide(sujet,societe,dossier,vehicule)");
            return mapping.findForward("error");
        }

        request.getSession().setAttribute("rechercheSociete",rechercheSocieteForm);

        try {
        	SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            List currentList = new ArrayList();
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSocieteHtmlForm(rechercheSocieteForm,criteresRechercheSociete,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Societe> list = delegate.selectDefault(subject,criteresRechercheSociete);

            // Ajout des soci�t�s dans le composant d'�tat (ActionForm)
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Societe societe = (Societe)it.next();
                SocieteForm societeForm = new SocieteForm();
                societeForm.init(subject);
                ValueObjectMapper.convertSociete(societe, societeForm,subject.getLocale());
                societeForm.assignerValeurDeListe(subject);
                currentList.add(societeForm);
            }

            rechercheSocieteForm.setListeResultat( currentList );
            rechercheSocieteForm.getListeResultat().assignerTrierDefault(SocieteLiaisonTrieListe.CLE_NOM, false, new SocieteLiaisonTrieListe());

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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward refreshRechercheSociete(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh ecran de recherche societe");

        return mapping.findForward("success");
    }

    /**
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward refreshSociete(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh ecran de recherche societe");

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton modifier dans
     * le panneau de mise � jour d'une soci�t�.  Les modifiactions de la soci�t� est enregistr� dans le
     * cardex et l'�cran de mise a jour de la soci�t� est affich�.
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward update(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Mise � jour d'une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate societeDelegate =
                new SocieteBusinessDelegate();
            Societe                 societe = new SocieteVO();
            SocieteForm             societeForm = (SocieteForm) form;

            ValueObjectMapper.convertSocieteHtmlForm((SocieteForm) form,
                    societe, subject.getLocale());
            log.fine("Mise � jour de la soci�t�: " + societe.toString());
            FormFile file = null;
            String nomFichier = "";
            //On v�rifie s'il s'agit de l'ajout d'une photo
            if(societeForm.getAjoutPhoto().getSourceFile() != null 
                && StringUtils.isNotEmpty(societeForm.getAjoutPhoto().getSourceFile().getFileName())){
                file = societeForm.getAjoutPhoto().getUploadImage();
                nomFichier = file.getFileName();
                societeForm.getAjoutPhoto().setExtension(societeForm.getAjoutPhoto().getExtensionDeFilePath());                
                //Est ce que la taille du fichier exc�de 4MB
                if (societeForm.getAjoutPhoto().isTailleAccepte() == false) {
                    log.severe("La taille du fichier est sup�rieure � 7MB.");
                    throw (new BusinessRuleExceptionHandle("erreur_fichier")).getBusinessException();
                }else if(societeForm.getAjoutPhoto().isPhoto() == false){
                    log.severe("Ce fichier n'est pas une photo");
                    throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
                }else{
                	societeForm.getAjoutPhoto().setLien(societeForm.getCle());
                	societeForm.getAjoutPhoto().setLienSite(societeForm.getSite());
                	societeForm.getAjoutPhoto().setConfidentialite(societeForm.getConfidentialite());
	            	addLienPhotoAjout(subject, mapping, societeForm.getAjoutPhoto(), request, response);
                }
            }else{ //Sinon, on fait la mise � jour de la fiche soci�t�.
	            societeDelegate.update(subject, societe);
	            
				//V�rification d'un mandat PSU associ� � la mise � jour d'une soci�t�
				PSUMandatForm psuMandat = new PSUMandatForm();
				if(StringUtils.isNotEmpty(societeForm.getNumeroFiche())){
					psuMandat.setFicheSociete(societeForm.getNumeroFiche());
				}
				if(StringUtils.isNotEmpty(societeForm.getNom())){
					psuMandat.setSocieteNom(societeForm.getNom().toUpperCase());
				}
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.MISE_A_JOUR);
            }
            populateSocieteForm(subject, societe, societeForm);

            if (societeForm.getEntiteCardexLiaison() != null){
            	assignerLienRole(request, societeForm);

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
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un �l�ment de r�sultats de recherche des soci�t�s en mode liaison.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward showRole(CardexAuthenticationSubject subject,
                                  ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException,
                                  ServletException {
        log.fine("Liaison d'une soci�t�");

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un �l�ment de r�sultats de recherche des dossiers en mode liaison.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienDossier(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Liaison d'un dossier � une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate =
                new SocieteBusinessDelegate();
            log.fine("Liaison d'un dossier: form: " + form);
            LienForm                lienForm = (LienForm) form;
            log.fine("Liaison d'un dossier: lienForm: " + lienForm);
            SocieteForm             societeForm = new SocieteForm();
            Societe                 societeOrigine = new SocieteVO();
            Dossier                 dossierDestination = new DossierVO();

            societeForm.init(subject);
            societeOrigine.setCle(lienForm.getCleSource());
            societeOrigine.setSite(lienForm.getSiteSource());
            societeOrigine.setTypeLien(lienForm.getTypeLien());
            societeOrigine.setRole(Long.parseLong(lienForm.getRole()));

            dossierDestination.setCle(lienForm.getCleDestination());
            dossierDestination.setSite(lienForm.getSiteDestination());
            dossierDestination.setTypeLien(lienForm.getTypeLien());
            dossierDestination.setRole(Long.parseLong(lienForm.getRole()));

            log.fine(lienForm.toString());
            delegate.addLienDossier(subject, societeOrigine,
                                    dossierDestination);
            populateSocieteForm(subject, societeOrigine, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);
            
			//R�cup�ration du num�ro de dossier qui vient d'�tre associ�
			Collection dossierListe = societeForm.getDossiers();
			Iterator it = dossierListe.iterator();
			String numeroCardex = "";
			while (it.hasNext()) {
				DossierForm dossierAssocie= (DossierForm)it.next();
				if(dossierAssocie.getCle().equals(Long.toString(dossierDestination.getCle()))){
					numeroCardex = dossierAssocie.getNumeroCardex().toString();
				}
			}
			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);

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
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un �l�ment de r�sultats de recherche des soci�t�s en mode liaison.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienSujet(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Liaison d'une societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SocieteForm         societeForm = new SocieteForm();
            Societe             societe = new SocieteVO();
            Sujet               sujet = new SujetVO();

            societeForm.init(subject);
            societe.setCle(lienForm.getCleSource());
            societe.setSite(lienForm.getSiteSource());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));

            sujet.setCle(lienForm.getCleDestination());
            sujet.setSite(lienForm.getSiteDestination());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));

            log.fine(lienForm.toString());
            delegate.addLienSujet(subject, societe, sujet);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);
			
			//R�cup�ration du nom du num�ro de fiche sujet qui vient d'�tre associ�
			Collection sujetListe = societeForm.getSujets();
			Iterator it = sujetListe.iterator();
			String numeroFiche = "";
			while (it.hasNext()) {
				SujetForm sujetAssocie= (SujetForm)it.next();
				if(sujetAssocie.getCle().equals(Long.toString(sujet.getCle()))){
					numeroFiche = sujetAssocie.getNumeroFiche();
				}
			}

			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);

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
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un �l�ment de r�sultats de recherche des soci�t�s en mode liaison.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienVehicule(CardexAuthenticationSubject subject,
                                         ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws IOException,
                                         ServletException {
        log.fine("Liaison d'un vehicule � une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SocieteForm             societeForm = new SocieteForm();
            Societe                 societe = new SocieteVO();
            Vehicule                vehicule = new VehiculeVO();

            societeForm.init(subject);
            societe.setCle(lienForm.getCleSource());
            societe.setSite(lienForm.getSiteSource());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));

            vehicule.setCle(lienForm.getCleDestination());
            vehicule.setSite(lienForm.getSiteDestination());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));

            log.fine(lienForm.toString());
            delegate.addLienVehicule(subject, societe, vehicule);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//R�cup�ration de l'immatriculation du v�hicule qui vient d'�tre associ�
			Collection vehiculeListe = societeForm.getVehicules();
			Iterator it = vehiculeListe.iterator();
			String immatriculation = "";
			while (it.hasNext()) {
				VehiculeForm vehiculeAssocie= (VehiculeForm)it.next();
				if(vehiculeAssocie.getCle().equals(Long.toString(vehicule.getCle()))){
					immatriculation = vehiculeAssocie.getImmatriculation();
				}
			}

			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			psuMandat.setReference1(immatriculation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);
            
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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienSujet(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Destruction d'un lien societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            LienForm            lienForm = (LienForm) form;
            SocieteForm         societeForm = new SocieteForm();
            Societe             societe = new SocieteVO();
            Sujet               sujet = new SujetVO();

            societeForm.init(subject);
            societe.setCle(lienForm.getCleSource());
            societe.setSite(lienForm.getSiteSource());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));
            societe.setLien(lienForm.getCle());
            societe.setLienSite(lienForm.getSite());

            sujet.setCle(lienForm.getCleDestination());
            sujet.setSite(lienForm.getSiteDestination());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));
            sujet.setLien(lienForm.getCle());
            sujet.setLienSite(lienForm.getSite());

            log.fine(lienForm.toString());
            delegate.deleteLienSujet(subject, societe, sujet);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);
            
			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			//psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienVehicule(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Destruction d'un lien Vehicule");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            LienForm            lienForm = (LienForm) form;
            SocieteForm         societeForm = new SocieteForm();
            Societe             societe = new SocieteVO();
            Vehicule            vehicule = new VehiculeVO();

            societeForm.init(subject);
            societe.setCle(lienForm.getCleSource());
            societe.setSite(lienForm.getSiteSource());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));
            societe.setLien(lienForm.getCle());
            societe.setLienSite(lienForm.getSite());

            vehicule.setCle(lienForm.getCleDestination());
            vehicule.setSite(lienForm.getSiteDestination());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));
            vehicule.setLien(lienForm.getCle());
            vehicule.setLienSite(lienForm.getSite());

            log.fine(lienForm.toString());
            delegate.deleteLienVehicule(subject, societe, vehicule);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			//psuMandat.setReference1(immatriculation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de liaison
     * d'un �l�ment de r�sultats de recherche des soci�t�s en mode liaison.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienSociete(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Liaison d'une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate =
                new SocieteBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SocieteForm         societeForm = new SocieteForm();
            Societe                 societeOrigine = new SocieteVO();
            Societe                 societeDestination =
                new SocieteVO();

            societeForm.init(subject);
            societeOrigine.setCle(lienForm.getCleSource());
            societeOrigine.setSite(lienForm.getSiteSource());
            societeOrigine.setTypeLien(lienForm.getTypeLien());
            //R�le par d�faut pour ce lien
            societeOrigine.setRole(GlobalConstants.Role.SANS_OBJET);

            societeDestination.setCle(lienForm.getCleDestination());
            societeDestination.setSite(lienForm.getSiteDestination());
            societeDestination.setTypeLien(lienForm.getTypeLien());
            //R�le par d�faut pour ce lien
            societeDestination.setRole(GlobalConstants.Role.SANS_OBJET);

            log.fine(lienForm.toString());
            delegate.addLienSociete(subject, societeOrigine,
                                    societeDestination);
            populateSocieteForm(subject, societeOrigine, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//R�cup�ration du nom de la soci�t� qui vient d'�tre associ�e
			Collection societeListe = societeForm.getSocietes();
			Iterator it = societeListe.iterator();
			String nom = "";
			while (it.hasNext()) {
				SocieteForm societeAssocie= (SocieteForm)it.next();
				if(societeAssocie.getCle().equals(Long.toString(societeDestination.getCle()))){
					nom = societeAssocie.getNom();
				}
			}

			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);

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
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Liaison d'une narration � une soci�t�.");
        ActionMessages errors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        
        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            NarrationForm narrationForm = (NarrationForm)form;
            SocieteForm societeForm  = narrationForm.getSociete();
            Societe societe = new SocieteVO();
            Narration narration = new NarrationVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Narration: " + narration);
            NarrationBaliseUtil.assignerMessageSiNarrationANettoyer(messages, narrationForm.getNarrationAvecFormat());
            delegate.addLienNarration(subject,societe,narration);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//R�cup�ration de la date de cr�ation de la narration qui vient d'�tre associ�e
			Collection narrationListe = societeForm.getNarrations();
			Iterator it = narrationListe.iterator();
			String dateCreation = "";
			while (it.hasNext()) {
				NarrationForm narrationAssocie= (NarrationForm)it.next();
				if(narrationAssocie.getCle().equals(Long.toString(narration.getCle()))){
					dateCreation = narrationAssocie.getDateCreation();
				}
			}
			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(dateCreation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);
			//Dans le cas des narratisons, on v�rifie �galement pour les mots-cl�s.
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
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
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            SocieteForm societeForm = new SocieteForm();
            Photo photo = new PhotoVO();
            Societe societe = new SocieteVO();
            log.fine("PhotoForm a li�e : " + photoForm);
            
            societeForm.init(subject);
            societeForm.setCle(photoForm.getLien());
            societeForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm,societe,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());

            FormFile   file = photoForm.getUploadImage();

            //Est ce que la taille du fichier exc�de 4MB
            if (photoForm.isTailleAccepte() == false) {
            	log.severe("La taille du fichier est sup�rieure � 4MB.");
                return mapping.findForward("error");
            }else if(photoForm.isPhoto() == false){
                log.severe("Ce fichier n'est pas une photo");
                throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
            }else{
            	byte[] data = file.getFileData();
            	photo.setImage( data );
            	
	            log.fine("Photo a li�e : " + photo);
    	        photo= delegate.addLienPhoto(subject,societe,photo);
        	    log.fine("Photo li�e : " + photo);
        	    file.destroy();
        	    
				populateSocieteForm(subject, societe, societeForm);
	    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
	            societeForm.setNew(obtenirStatutNouveau(request));
				request.getSession().setAttribute("societe", societeForm);

				//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � une soci�t�
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setFicheSociete(societeForm.getNumeroFiche());
				psuMandat.setSocieteNom(societeForm.getNom());
				psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
				psuMandat.setReference1(Long.toString(photo.getLienElement()));
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);

        		return mapping.findForward("success");
            }

            //file.destroy();
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
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward addLienAdresse(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Ajout d'un lien entre une adresse et une soci�t�.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            AdresseForm adresseForm = (AdresseForm)form;
            Societe societe = new SocieteVO();
            Adresse adresse = new AdresseVO();
            societeForm.init(subject);
            societeForm.setCle(adresseForm.getLien());
            societeForm.setSite(adresseForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Adresse: " + adresse);
            delegate.addLienAdresse(subject,societe,adresse);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);
			
			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.ADRESSE);
			psuMandat.setReference1(" ");
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.LIAISON);

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
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un �l�ment de l'onglets societes dans l'�cran
     * de consultation societe.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienDossier(CardexAuthenticationSubject subject,
                                           ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException,
                                           ServletException {
        log.fine("Destruction d'un lien dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate =
                new SocieteBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SocieteForm             societeForm = new SocieteForm();
            Societe                 societe = new SocieteVO();
            Dossier                 dossier =
                new DossierVO();

            societeForm.init(subject);
            societe.setCle(lienForm.getCleSource());
            societe.setSite(lienForm.getSiteSource());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));
            societe.setLien(lienForm.getCle());
            societe.setLienSite(lienForm.getSite());

            dossier.setCle(lienForm.getCleDestination());
            dossier.setSite(lienForm.getSiteDestination());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));
            dossier.setLien(lienForm.getCle());
            dossier.setLienSite(lienForm.getSite());
            log.fine(lienForm.toString());
            delegate.deleteLienDossier(subject, societe, dossier);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			//psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * Cet �v�nement surivient lorsque l'utilisateur clique sur le bouton de
     * de destruction de lien d'un �l�ment de l'onglets societes dans l'�cran
     * de consultation societe.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienSociete(CardexAuthenticationSubject subject,
                                           ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException,
                                           ServletException {
        log.fine("Destruction d'un lien societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate =
                new SocieteBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SocieteForm         societeForm = new SocieteForm();
            Societe                 societeOrigine = new SocieteVO();
            Societe                 societeDestination =
                new SocieteVO();

            societeForm.init(subject);
            societeOrigine.setCle(lienForm.getCleSource());
            societeOrigine.setSite(lienForm.getSiteSource());
            societeOrigine.setTypeLien(lienForm.getTypeLien());
            societeOrigine.setRole(Long.parseLong(lienForm.getRole()));
            societeOrigine.setLien(lienForm.getCle());
            societeOrigine.setLienSite(lienForm.getSite());

            societeDestination.setCle(lienForm.getCleDestination());
            societeDestination.setSite(lienForm.getSiteDestination());
            societeDestination.setTypeLien(lienForm.getTypeLien());
            societeDestination.setRole(Long.parseLong(lienForm.getRole()));
            societeDestination.setLien(lienForm.getCle());
            societeDestination.setLienSite(lienForm.getSite());
            log.fine(lienForm.toString());
            delegate.deleteLienSociete(subject, societeOrigine,
                                       societeDestination);
            populateSocieteForm(subject, societeOrigine, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			//psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Suppression d'un lien entre une narration et une soci�t�.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Societe societe = new SocieteVO();
            Narration narration = new NarrationVO();
            societeForm.init(subject);
            societeForm.setCle(narrationForm.getLien());
            societeForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Narration: " + narration);
            delegate.deleteLienNarration(subject,societe,narration);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(narrationForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienPhoto(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Suppression d'un lien entre une photo et une soci�t�.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            PhotoForm photoForm = (PhotoForm)form;
            Societe societe = new SocieteVO();
            Photo photo = new PhotoVO();
            societeForm.init(subject);
            societeForm.setCle(photoForm.getLien());
            societeForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm, photo,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Photo: " + photo);
            delegate.deleteLienPhoto(subject,societe,photo);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
			psuMandat.setReference1(Long.toString(photo.getLienElement()));
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward deleteLienAdresse(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Suppression d'un lien entre une adresse et une soci�t�");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            AdresseForm adresseForm = (AdresseForm)form;
            Societe societe = new SocieteVO();
            Adresse adresse = new AdresseVO();
            societeForm.init(subject);
            societeForm.setCle(adresseForm.getLien());
            societeForm.setSite(adresseForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Adresse: " + adresse);
            delegate.deleteLienAdresse(subject,societe,adresse);
            populateSocieteForm(subject, societe, societeForm);
            societeForm.setNew(obtenirStatutNouveau(request));
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            request.getSession().setAttribute("societe", societeForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � une soci�t�
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSociete(societeForm.getNumeroFiche());
			psuMandat.setSocieteNom(societeForm.getNom());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.ADRESSE);
			psuMandat.setReference1(" ");
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.SUPPRESSION);

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
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton �puration dans
     * le panneau de recherche des soci�t�s.  L'�puration consiste � supprimer toutes les
     * soci�t�s donc le niveau de confidentialit� est 8.
     * <p>
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward delete(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException, DAOException,
                                ServletException {
        log.fine("�puration des soci�t�s");

        ActionMessages errors = new ActionMessages();

        ResultSet resultSet = null;
        Connection connection = null;

        try {
        	verifierToken(request);
        	//Avant de proc�der � l'�puration, on sauvegardes les donn�es dans un rapport � la demande des v�rificateurs.
        	RapportBusinessDelegate rapportDelegate = new RapportBusinessDelegate();
        	Map parameters = new HashMap();
        	connection = DAOConnection.getInstance().getConnection(subject);
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk_mm");
    		String chemin = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_EPURATION);
    		String dateRapport = dateFormat.format(new Date());
			CardexUser utilisateur = (CardexUser)subject.getUser();
    		ListeCache cache = ListeCache.getInstance();
			String siteDescription = cache.obtenirLabel(subject, String.valueOf(utilisateur.getSite()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, utilisateur.getEntite(), GlobalConstants.ActionSecurite.SELECTION));
    		String nomRapport = chemin+"Soci�t�s � �purer "+ siteDescription + " (" + dateRapport+").pdf";
    		InputStream gabarit = getClass().getClassLoader().getResourceAsStream(RapportsConfiguration.RAPPORT_EPURATION_SOCIETES);
			log.fine("Sauvegarder soci�t�s � �purer");
			long site = utilisateur.getSite();
			resultSet = rapportDelegate.rapportEpuration(site, connection, "CARDEX_RAPPORT.SP_RAP_SO_EPURATION");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.fine(context.getRealPath("/rapports/"));
			ServletContext context = request.getSession().getServletContext();  
	        parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
            parameters.put("REPORT_CONNECTION",connection);
			parameters.put("UTILISATEUR", utilisateur.getCode());
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
			// Sauvegarde dans un fichier
			log.fine("�puration des sujets (Sauvegarde dans un fichier)");
			(new PDFImpressionRapport()).impression(nomRapport, print);
			//On proc�de ensuite � l'�puration
            SocieteBusinessDelegate societeDelegate =
                new SocieteBusinessDelegate();
            societeDelegate.delete(subject);
            //Apr�s la suppression, on vide la liste des r�sultats.
            CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm = new CriteresRechercheSocieteForm();
            criteresRechercheSocieteHtmlForm.init( subject );
            criteresRechercheSocieteHtmlForm.getListeResultat().vider();
            request.getSession().setAttribute("rechercheSociete",criteresRechercheSocieteHtmlForm);
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
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Mise � jour d'un lien entre une narration et une soci�t�.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Societe societe = new SocieteVO();
            Narration narration = new NarrationVO();
            societeForm.init(subject);
            societeForm.setCle(narrationForm.getLien());
            societeForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Narration: " + narration);
            delegate.updateLienNarration(subject,narration);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward approuveLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Approbation d'une narration li�e � un societe.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Societe societe = new SocieteVO();
            Narration narration = new NarrationVO();
            societeForm.init(subject);
            societeForm.setCle(narrationForm.getLien());
            societeForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur(user.getCode());
            narrationForm.setAutoriteApprobateur(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setAutoriteNarration(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setConfidentialiteApprobateur(Long.toString(privilege.getNiveauConfidentialite()));
            narrationForm.setDateApprobation(currentDate);
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateSocieteForm(subject, societe, societeForm);
            request.getSession().setAttribute("societe", societeForm);

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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward permettreModificationLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Permettre la modification d'une narration li�e � un societe.");
        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Societe societe = new SocieteVO();
            Narration narration = new NarrationVO();
            societeForm.init(subject);
            societeForm.setCle(narrationForm.getLien());
            societeForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur("");
            narrationForm.setAutoriteApprobateur("");
            narrationForm.setAutoriteNarration("");
            narrationForm.setConfidentialiteApprobateur("");
            narrationForm.setDateApprobation("");
            narrationForm.setAutoriteNarration(narrationForm.getAutoriteCreateur());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateSocieteForm(subject, societe, societeForm);
            request.getSession().setAttribute("societe", societeForm);

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
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLienAdresse(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Mise � jour d'un lien entre une adresse et une soci�t�.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm societeForm = new SocieteForm();
            AdresseForm adresseForm = (AdresseForm)form;
            Societe societe = new SocieteVO();
            Adresse adresse = new AdresseVO();
            societeForm.init(subject);
            societeForm.setCle(adresseForm.getLien());
            societeForm.setSite(adresseForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,
                    subject.getLocale());
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            log.fine("Societe: " + societe);
            log.fine("Adresse: " + adresse);
            delegate.updateLienAdresse(subject,adresse);
            populateSocieteForm(subject, societe, societeForm);
    		societeForm.setEntiteCardexLiaison( obtenirEntiteCardexFiche(request));
            societeForm.setNew(obtenirStatutNouveau(request));
            request.getSession().setAttribute("societe", societeForm);

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
     * Popule les informations d'une soci�t� obtenu dans la base de donn�e
     * dans le SocieteForm sp�cifi�s.
     *
     * @param Societe Les crit�res de la soci�t� a obtenir
     * @param SocieteForm L'ActionForm bean a popul� � partir de la soci�t� obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populateSocieteForm(CardexAuthenticationSubject subject,
                                     Societe criteria,
                                     SocieteForm societeForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        log.fine("populateSocieteForm");
        SocieteBusinessDelegate delegate =
            new SocieteBusinessDelegate();
        Societe societe = delegate.find(subject, criteria);

        societeForm.resetOnglets();
        //log.fine("Societe trouv�e: " + societe.toString());
        ValueObjectMapper.convert(societe, societeForm);
        societeForm.setConfirmationMotPasse(societeForm.getMotPasse());
        //Recherche de tous les enregistrements li�s dans les onglets
		rechercheLiensSociete(subject, societe, societeForm, delegate);
    }
    

    /**
     * Effectue la recherche des enregistrements li�s � un dossier donn�, 
     * affich�s dans les onglets du dossier.
     *
     * @param subject Le sujet authentifi�
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     * @param delegate Lien avec la base de donn�es.
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void rechercheLiensSociete(CardexAuthenticationSubject subject,
                                     Societe societe,
                                     SocieteForm societeForm,
                                     SocieteBusinessDelegate delegate) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {		
        // Recherche des liens dossier
        Collection liensDossier = delegate.findLiensDossier(subject, societe);
        Iterator   it = liensDossier.iterator();
        log.fine("Dossier li�s (" + liensDossier.size() + ") :");
        while (it.hasNext()) {
            Dossier     linkDossier = (Dossier) it.next();
            DossierForm linkDossierForm = new DossierForm();
            ValueObjectMapper.convertDossier(linkDossier, linkDossierForm,
                    subject.getLocale());
            linkDossierForm.assignerValeurDeListe( subject );
            societeForm.addDossier(linkDossierForm);
            log.fine(linkDossier.toString());
        }
        societeForm.getListeDossiers().assignerTrierDefault(DossierOngletTrieListe.CLE_DATE_DEBUT, true, new DossierOngletTrieListe());

        // Recherche des liens narration
        Collection liensNarration = delegate.findLiensNarration(subject,
                societe);
        it = liensNarration.iterator();

        log.fine("Narration li�s (" + liensNarration.size() + ") :");

        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();
			//On inscrit les valeurs de r�f�rence pour l'impression de la narration.
			linkNarration.setReference1(societeForm.getNom());

            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    subject.getLocale());
            linkNarrationForm.assignerValeurDeListe( subject );
            societeForm.addNarration(linkNarrationForm);
            log.fine(linkNarration.toString());
        }
        societeForm.getListeNarrations().assignerTrierDefault(NarrationOngletTrieListe.CLE_DATE_CREATION, true, new NarrationOngletTrieListe());

        // Recherche des liens sujets
        Collection liensSujet = delegate.findLiensSujet(subject, societe);
        it = liensSujet.iterator();

        log.fine("Sujets li�s (" + liensSujet.size() + ") :");

        while (it.hasNext()) {
            Sujet     linkSujet = (Sujet) it.next();
            SujetForm linkSujetForm = new SujetForm();

            ValueObjectMapper.convertSujet(linkSujet, linkSujetForm,
                    subject.getLocale());
            linkSujetForm.assignerValeurDeListe(subject);
            linkSujetForm.assignerPermettreSuppressionLiaison(societeForm);
            societeForm.addSujet(linkSujetForm);
            log.fine(linkSujet.toString());
        }
        societeForm.getListeSujets().assignerTrierDefault(SujetOngletTrieListe.CLE_NOM, false, new SujetOngletTrieListe());

        // Recherche des liens propri�taires
        Collection liensProprietaires = delegate.findLiensProprietaires(subject,
                societe);
        it = liensProprietaires.iterator();

        log.fine("Proprietaires li�s (" + liensProprietaires.size() + ") :");

        while (it.hasNext()) {
            Sujet     linkSujet = (Sujet) it.next();
            SujetForm linkSujetForm = new SujetForm();

            ValueObjectMapper.convertSujet(linkSujet, linkSujetForm,
                    subject.getLocale());
            linkSujetForm.assignerValeurDeListe(subject);
            linkSujetForm.assignerPermettreSuppressionLiaison(societeForm);
            societeForm.addProprietaires(linkSujetForm);
            log.fine(linkSujet.toString());
        }
        societeForm.getListeProprietaires().assignerTrierDefault(SujetOngletTrieListe.CLE_DATE_CREATION, false, new SujetOngletTrieListe());

        // Recherche des liens photos
        Collection liensPhoto = delegate.findLiensPhoto(subject,
                societe);
        it = liensPhoto.iterator();

        log.fine("Photos li�s (" + liensPhoto.size() + ") :");

        while (it.hasNext()) {
            Collection sublist = new ArrayList();
            for (int i=0; i<3;i++) {
              if (it.hasNext()) {
                Photo     linkPhoto = (Photo) it.next();
                PhotoForm linkPhotoForm = new PhotoForm();
                ValueObjectMapper.convertPhoto(linkPhoto, linkPhotoForm,
                        subject.getLocale());
                sublist.add(linkPhotoForm);
                // log.fine(linkPhoto.toString());
              }
            }
            societeForm.addPhoto(sublist);
        }

        // Recherche des liens adresses
        Collection liensAdresse = delegate.findLiensAdresse(subject,
                societe);
        it = liensAdresse.iterator();
        log.fine("Adresses li�s (" + liensAdresse.size() + ") :");

        while (it.hasNext()) {
            Adresse     linkAdresse = (Adresse) it.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    subject.getLocale());
            log.fine(linkAdresse.toString());
            linkAdresseForm.assignerValeurDeListe( subject );
            societeForm.addAdresse(linkAdresseForm);
        }//while
        societeForm.getListeAdresses().assignerTrierDefault(AdresseOngletTrieListe.CLE_DATE_CREATION, true, new AdresseOngletTrieListe());

        // Recherche des liens societe
        Collection liensSociete = delegate.findLiensSociete(subject, societe);
        it = liensSociete.iterator();

        log.fine("Soci�t�s li�es (" + liensSociete.size() + ") :");

        while (it.hasNext()) {
            Societe     linkSociete = (Societe) it.next();
            SocieteForm linkSocieteForm = new SocieteForm();
            linkSocieteForm.init(subject);
            
            ValueObjectMapper.convertSociete(linkSociete, linkSocieteForm,
                    subject.getLocale());
            linkSocieteForm.assignerValeurDeListe( subject );
            societeForm.addSociete(linkSocieteForm);
            log.fine(linkSociete.toString());
        }
        societeForm.getListeSocietes().assignerTrierDefault(SocieteOngletTrieListe.CLE_NOM, false, new SocieteOngletTrieListe());

        // Recherche des liens v�hicules
        Collection liensVehicule = delegate.findLiensVehicule(subject,
                societe);
        it = liensVehicule.iterator();

        log.fine("Vehicules li�s (" + liensVehicule.size() + ") :");

        while (it.hasNext()) {
            Vehicule     linkVehicule = (Vehicule) it.next();
            VehiculeForm linkVehiculeForm = new VehiculeForm();

            ValueObjectMapper.convertVehicule(linkVehicule, linkVehiculeForm,
                    subject.getLocale());
            linkVehiculeForm.assignerValeurDeListe( subject );
            societeForm.addVehicule(linkVehiculeForm);
            log.fine(linkVehicule.toString());
        }
        societeForm.getListeVehicules().assignerTrierDefault(VehiculeOngletTrieListe.CLE_IMMATRICULATION, false, new VehiculeOngletTrieListe());

	    //Valeurs par d�faut d'une nouvelle photo
        societeForm.getAjoutPhoto().setTypeMultimedia(GlobalConstants.TypeMutliMedia.PHOTO);
        societeForm.getAjoutPhoto().setCle("-1");
        societeForm.getAjoutPhoto().setSite("-1");
        societeForm.getAjoutPhoto().setLienMultimedia("-1");
        societeForm.getAjoutPhoto().setLienSiteMultimedia("-1");
        societeForm.getAjoutPhoto().setLienElement("-1");
        societeForm.getAjoutPhoto().setLienSiteElement("-1");
        
    }    
    
    /**
     * Popule les informations d'un dossier obtenu dans la base de donn�e
     * dans le SocieteForm sp�cifi�s.  Cette m�thode est appel�e par show et
     * showAcces.  Le diff�rence avec populateSocieteForm est que la fiche societe 
     * n'est plus appel�e puisqu'elle l'est d�j� dans show et showAcces.
     *
     * @param Societe Les crit�res de la soci�t� a obtenir
     * @param SocieteForm L'ActionForm bean a popul� � partir de la soci�t� obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populateSocieteFormShow(CardexAuthenticationSubject subject,
                                     Societe societe,
                                     SocieteForm societeForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        log.fine("populateSocieteFormShow");
        SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
        societeForm.resetOnglets();
        societeForm.setConfirmationMotPasse(societeForm.getMotPasse());
        //Recherche de tous les enregistrements li�s dans les onglets
		rechercheLiensSociete(subject, societe, societeForm, delegate);
    }    

    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s�lection de l'�cran principal)
     * <ul>Nature (selon la s�lection de l'�cran principal)
     * </li>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Param�tres de recherche par d�fault de societe");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm = (CriteresRechercheSocieteForm) form;
            CardexUser user = (CardexUser) subject.getUser();
            CriteresRechercheSocieteVO criteresRechercheSociete = new CriteresRechercheSocieteVO();

            criteresRechercheSocieteHtmlForm.init( subject );

            // Valeurs par d�faut
            criteresRechercheSocieteHtmlForm.setEntite(String.valueOf(user.getEntite()));

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSocieteHtmlForm(criteresRechercheSocieteHtmlForm,criteresRechercheSociete,subject.getLocale());

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * </li>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward resetSearchDefaultLiaison(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Param�tres de recherche par d�fault de societe en mode liaison");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm = (CriteresRechercheSocieteForm) form;
            CriteresRechercheSocieteVO criteresRechercheSociete = new CriteresRechercheSocieteVO();
            DossierHtmlForm dossierHtmlForm = criteresRechercheSocieteHtmlForm.getDossier();
            SujetHtmlForm sujetHtmlForm = criteresRechercheSocieteHtmlForm.getSujet();
            VehiculeHtmlForm vehiculeHtmlForm = criteresRechercheSocieteHtmlForm.getVehicule();
            SocieteHtmlForm societeHtmlForm = criteresRechercheSocieteHtmlForm.getSociete();

            criteresRechercheSocieteHtmlForm.init( subject );
            criteresRechercheSocieteHtmlForm.setDossier(dossierHtmlForm);
            criteresRechercheSocieteHtmlForm.setSujet(sujetHtmlForm);
            criteresRechercheSocieteHtmlForm.setVehicule(vehiculeHtmlForm);
            criteresRechercheSocieteHtmlForm.setSociete(societeHtmlForm);

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSocieteHtmlForm(criteresRechercheSocieteHtmlForm,criteresRechercheSociete,subject.getLocale());

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans le menu principal, l'utilisateur a
     * choisi de rechercher une soci�t� pour une cat�gorie et une nature donn�e.
     * L'application affiche alors le panneau de recherche des soci�t�s.
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s�lection de l'�cran principal)
     * <ul>Nature (selon la s�lection de l'�cran principal)
     * </li>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Recherche par d�fault de societe");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm = (CriteresRechercheSocieteForm) form;
            CriteresRechercheSocieteVO criteresRechercheSociete = new CriteresRechercheSocieteVO();

            // Valeurs par d�faut
            criteresRechercheSocieteHtmlForm.init( subject );

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSocieteHtmlForm(criteresRechercheSocieteHtmlForm,criteresRechercheSociete,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Societe> results = delegate.selectDefault(subject,criteresRechercheSociete);

            assignerResultatSociete(subject, criteresRechercheSocieteHtmlForm, results);
    		criteresRechercheSocieteHtmlForm.getListeResultat().assignerTrierDefault(SocieteTrieListe.CLE_NOM, false, new SocieteTrieListe());

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
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de societe, l'utilisateur a choisi
     * de rechercher une soci�t� selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des soci�t�s avec les r�sultats de la nouvelle recherche.
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward search(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Recherche de soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate delegate =  new SocieteBusinessDelegate();
            CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm = (CriteresRechercheSocieteForm) form;
            CriteresRechercheSocieteVO criteresRechercheSociete = new CriteresRechercheSocieteVO();
            criteresRechercheSocieteHtmlForm.getListeResultat().vider();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSocieteHtmlForm(criteresRechercheSocieteHtmlForm, criteresRechercheSociete,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Societe> results = delegate.select(subject,criteresRechercheSociete);

			//V�rification d'un mandat PSU associ� � la recherche d'une soci�t� par nom
			if(!OracleDAOUtils.isEmpty(criteresRechercheSociete.getNom())){			
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setSocieteNom(criteresRechercheSociete.getNom().toUpperCase());
				if(!OracleDAOUtils.isEmpty(criteresRechercheSociete.getNumeroFiche())){			
					psuMandat.setFicheSociete(criteresRechercheSociete.getNumeroFiche().toUpperCase());
				}				
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SOCIETE, GlobalConstants.TypeAction.RECHERCHE);
			}

            assignerResultatSociete(subject, criteresRechercheSocieteHtmlForm, results);
    		criteresRechercheSocieteHtmlForm.getListeResultat().assignerTrierDefault(SocieteTrieListe.CLE_NOM, false, new SocieteTrieListe());

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
	 * @param criteresRechercheSocieteHtmlForm
	 * @param results
	 * @throws IteratorException
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
	private void assignerResultatSociete(CardexAuthenticationSubject subject, CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm, List<Societe> list) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des soci�t�s dans le composant d'�tat (ActionForm)
		List currentList = new ArrayList();
		Iterator   it = list.iterator();

		while (it.hasNext()) {
		    Societe societe = (Societe)it.next();
		    SocieteForm societeForm = new SocieteForm();
		    societeForm.init(subject);
		    ValueObjectMapper.convertSociete(societe, societeForm,subject.getLocale());
		    societeForm.assignerValeurDeListe( subject );
		    currentList.add(societeForm);
		}

		criteresRechercheSocieteHtmlForm.setListeResultat( currentList );
	}

    /**
	 * @param subject
	 * @param criteresRechercheSocieteHtmlForm
	 * @param results
	 * @throws IteratorException
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
	private void assignerResultatSocieteAudit(CardexAuthenticationSubject subject, CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm, ValueListIterator results) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des soci�t�s dans le composant d'�tat (ActionForm)
		Collection list = results.getNextElements(results.getSize());
		List currentList = new ArrayList();
		Iterator   it = list.iterator();

		while (it.hasNext()) {
		    Societe societe = (Societe)it.next();
		    SocieteForm societeForm = new SocieteForm();
		    societeForm.init(subject);
		    ValueObjectMapper.convertSociete(societe, societeForm,subject.getLocale());
		    societeForm.assignerValeurDeListe( subject );
		    currentList.add(societeForm);
		}

		criteresRechercheSocieteHtmlForm.setListeResultatAudit( currentList );
	}

	/**
     * <p>
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de soci�t�, l'utilisateur a choisi
     * de rechercher une soci�t� selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des soci�t�s avec les r�sultats de la nouvelle recherche.
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchLiaison(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Recherche de soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate delegate =  new SocieteBusinessDelegate();
            CriteresRechercheSocieteForm criteresRechercheSocieteHtmlForm = (CriteresRechercheSocieteForm) form;
            CriteresRechercheSocieteVO criteresRechercheSociete = new CriteresRechercheSocieteVO();
            criteresRechercheSocieteHtmlForm.getListeResultat().vider();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSocieteHtmlForm(criteresRechercheSocieteHtmlForm, criteresRechercheSociete,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Societe> results = delegate.liaisonSelect(subject,criteresRechercheSociete);

            assignerResultatSociete(subject, criteresRechercheSocieteHtmlForm, results);
    		criteresRechercheSocieteHtmlForm.getListeResultat().assignerTrierDefault(SocieteLiaisonTrieListe.CLE_NOM, false, new SocieteLiaisonTrieListe());
            
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

	private EntiteCardexForm obtenirEntiteCardexLiaison(HttpServletRequest request, String formNameEntiteCardexLiaison) {
		//Sert � conna�tre le module auquel la nouvelle fiche doit �tre reli�e.
		EntiteCardexLiaison entiteCardexLiaison = (EntiteCardexLiaison) request.getSession().getAttribute( formNameEntiteCardexLiaison );
		return entiteCardexLiaison.getEntiteCardexLiaison();
	}

	private EntiteCardexForm obtenirEntiteCardexFiche(HttpServletRequest request) {
		//On r�cup�re l'entit� de liaison dans le cas d'un ajout. Sinon, l'information se perd en mode ajout si
		//on ajoute une information dans un onglet. La liaison ne peut donc plus se faire ensuite.
		SocieteForm societeForm = (SocieteForm) request.getSession().getAttribute( "societe" );
		if(societeForm.getEntiteCardexLiaison() != null){
			//Cas d'ajout
			return societeForm.getEntiteCardexLiaison();
		}else{
			//Cas de consultation
			return null;
		}
	}

	private Boolean obtenirStatutNouveau(HttpServletRequest request) {
		//On r�cup�re le statut nouveau. S'il s'agit d'un ajout, le bouton fermer sera interdit � l'affichage de la fiche.
		SocieteForm societeForm = (SocieteForm) request.getSession().getAttribute( "societe" );
		return societeForm.isNew();
	}

	private void assignerLienRole(HttpServletRequest request, SocieteForm societeForm) {
		LienForm lienForm = new LienForm();
		lienForm.assignerSource(societeForm.getEntiteCardexLiaison());
		lienForm.assignerDestination(societeForm);
		request.getSession().setAttribute("lien", lienForm);
	}

    /**
     * <p>
     * Mise � jour du r�le dans les onglets d'une soci�t�.
     * Les liens concernent les Sujets, les soci�t�s, les dossiers et les v�hicules.
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLien(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Mise � jour de la liaison dans une soci�t�");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm    societeForm = (SocieteForm) form;
            societeForm.setLien((String)request.getParameter("lien"));
            societeForm.setLienSite((String)request.getParameter("lienSite"));
            societeForm.setRole((String)request.getParameter("role"));
            societeForm.setCle((String)request.getParameter("cleSociete"));
            societeForm.setSite((String)request.getParameter("siteSociete"));
            Societe 	societe = new SocieteVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeForm,
                    societe, subject.getLocale());
            delegate.updateLien(subject, societe);
            populateSocieteForm(subject, societe, societeForm);
            
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
     * Copie des donn�es d'une soci�t� � une autre.
     * Cette fonction est en principe temporaire dans le but de faciliter l'�limination des doublons de soci�t�s dans l'entit� Loto-Qu�bec
     * Janvier 2012
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward copier(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.fine("Copie de donn�es de soci�t�s");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            SocieteForm    societeForm = (SocieteForm) form;
            societeForm.setCle((String)request.getParameter("cle"));
            societeForm.setSite((String)request.getParameter("site"));
            String destinataire = (String)request.getParameter("detaillant");
            Societe societe = new SocieteVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,subject.getLocale());
            Societe societe2 = delegate.rechercheDestinataire(subject, destinataire);
            delegate.copier(subject, Long.valueOf(societeForm.getCle()), Long.valueOf(societeForm.getSite()), societe2.getCle(), societe2.getSite());
            populateSocieteForm(subject, societe, societeForm);
            
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
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
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
            SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
            PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            SocieteForm societeForm = new SocieteForm();
            Photo photo = new PhotoVO();
            Societe societe = new SocieteVO();
            log.fine("PhotoForm a li�e : " + photoForm);

            societeForm.init(subject);
            societeForm.setCle(photoForm.getLien());
            societeForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertSocieteHtmlForm(societeForm,societe,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());
            FormFile   file = photoForm.getUploadImage();
        	byte[] data = file.getFileData();
        	photo.setImage( data );
            log.fine("Photo a li�e : " + photo);
            photo = delegate.addLienPhoto(subject,societe,photo);
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
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton fermer dans
     * le panneau de consultation d'une soci�t�.  
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward retour(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {


        log.fine("Retour de la consultation d'une soci�t�");

        //ActionMessages errors = new ActionMessages();

            SocieteForm societeForm = (SocieteForm)form;

            if (societeForm.getEntiteCardexLiaison() != null){
            	assignerLienRole(request, societeForm);

            	if (societeForm.getEntiteCardexLiaison() instanceof SujetForm){
					return mapping.findForward("successLiaisonSujet");
            	}else if (societeForm.getEntiteCardexLiaison() instanceof DossierForm){
            		return mapping.findForward("successLiaisonDossier");
            	}else if (societeForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		//On met la soci�t� source, sinon on boucle sur la nouvelle soci�t� ajout�e.
            		SocieteForm societeSource = (SocieteForm)societeForm.getEntiteCardexLiaison();
            		societeSource.setEntiteCardexLiaison(null);
            		request.getSession().setAttribute("societe", societeSource);
            		return mapping.findForward("successLiaisonSociete");
            	}else if (societeForm.getEntiteCardexLiaison() instanceof VehiculeForm){
            		return mapping.findForward("successLiaisonVehicule");
            	}
            }
            
            return mapping.findForward("success");
    }

}

