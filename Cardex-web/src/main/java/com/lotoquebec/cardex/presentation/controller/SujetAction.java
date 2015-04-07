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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.business.vo.CaracteristiquesVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSujetVO;
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
import com.lotoquebec.cardex.presentation.model.form.CaracteristiquesForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSujetForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.JeuxForm;
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
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetOngletTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.VehiculeOngletTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
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
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des sujets.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.27 $, $Date: 2002/04/30 17:47:53 $
 */
public class SujetAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));


    public ActionForward refreshSujet(
			CardexAuthenticationSubject subject, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		log.debug("Refresh ecran de recherche sujet");

		return mapping.findForward("success");
	}
    /**
	 * 
	 * @param mapping
	 *            L' ActionMapping utils� pour s�lectionner cette instance
	 * @param actionForm
	 *            L'ActionForm bean pour cette requ�te (optionnelle)
	 * @param request
	 *            La requ�te HTTP trait�e
	 * @param response
	 *            La r�ponse HTTP cr��e
	 * @param delegate
	 *            Le business delegate offrant les services d'affaires
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * 
	 * @exception IOException
	 *                si une erreur d'entr�e/sortieif an input/output survient
	 * @exception ServletException
	 *                si une exception servlet survient
	 */
    public ActionForward refreshRechercheSujet(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        log.debug("Refresh ecran de recherche sujet");

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
     * @throws CloneNotSupportedException 
     */
    public ActionForward showRechercheLiaisonSujet(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException, CloneNotSupportedException {
        log.debug("Acc�s � l'ecran de recherche sujet liaison.");

        ActionMessages errors = new ActionMessages();
        CriteresRechercheSujetForm rechercheSujetForm = new CriteresRechercheSujetForm();
        CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();

        // Valeurs par d�faut
        rechercheSujetForm.init(subject);
		CardexUser utilisateur = (CardexUser)subject.getUser();
		rechercheSujetForm.setLienSite(String.valueOf(utilisateur.getSite()));

        if (form instanceof DossierHtmlForm) {
            DossierHtmlForm dossierForm = (DossierHtmlForm)form;
	            rechercheSujetForm.setDossier(dossierForm);
	            rechercheSujetForm.setEntiteCardexLiaison((EntiteCardexForm) ((DossierForm) form).clone() );
        }else if (form instanceof SujetHtmlForm) {
            SujetHtmlForm sujetForm =  (SujetHtmlForm)form;
            rechercheSujetForm.setSujet(sujetForm);
            rechercheSujetForm.setEntiteCardexLiaison((EntiteCardexForm) ((SujetForm) form).clone() );
            //Dans le cas d'une relation entre sujets, on doit afficher une liste de r�les diff�rente. Pour la recherche, le champ roleLiaison sert de test dans le JSP.
            rechercheSujetForm.setTypeLien(GlobalConstants.LienRole.RELATION);
        }else if (form instanceof VehiculeHtmlForm) {
            VehiculeHtmlForm vehiculeForm = (VehiculeHtmlForm)form;
            rechercheSujetForm.setVehicule(vehiculeForm);
            rechercheSujetForm.setEntiteCardexLiaison((EntiteCardexForm) ((VehiculeForm) form).clone() );
        }else if (form instanceof SocieteHtmlForm) {
            SocieteHtmlForm societeForm = (SocieteHtmlForm)form;
            rechercheSujetForm.setSociete(societeForm);
            rechercheSujetForm.setEntiteCardexLiaison((EntiteCardexForm) ((SocieteForm) form).clone() );
        }else {
            log.error("L'objet source de la liaison sujet n'est pas de type valide(sujet,societe,dossier,vehicule)");
            return mapping.findForward("error");
        }

        request.getSession().setAttribute("rechercheSujet",rechercheSujetForm);

        try {
        	SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            List currentList = new ArrayList();
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSujetHtmlForm(rechercheSujetForm,criteresRechercheSujet,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            Collection results = delegate.selectDefault(subject,criteresRechercheSujet);

            // Ajout des soci�t�s dans le composant d'�tat (ActionForm)
            Iterator   it = results.iterator();

            while (it.hasNext()) {
                Sujet sujet = (Sujet)it.next();
                SujetForm sujetForm = new SujetForm();
                sujetForm.init(subject);
                //Dans le cas d'une relation entre sujets, on doit afficher une liste de r�les diff�rente. Le champ typeLien sert de test dans le JSP.
                sujetForm.setTypeLien(GlobalConstants.LienRole.RELATION);
                ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
                sujetForm.assignerValeurDeListe(subject);
                currentList.add(sujetForm);
                //Dans le cas d'une relation entre sujets, on doit afficher une liste de r�les diff�rente. Le champ typeLien sert de test dans le JSP.
    		    if (form instanceof SujetForm){
    		    	sujetForm.setTypeLien(GlobalConstants.LienRole.RELATION);
    		    }

            } 

            rechercheSujetForm.setListeResultat( currentList );
            rechercheSujetForm.getListeResultat().assignerTrierDefault(SujetTrieListe.CLE_NOM, false, new SujetTrieListe());
            
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
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * le panneau de recherche des sujets dans la liaison.  L'application affiche le panneau de mise � jour.
     * L'utilisateur a pr�alablement saisie les informations les donn�es relatives � l'identification
     * du sujet.
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

        log.debug("Cr�ation d'un nouveau sujet");

        SujetForm sujetForm = (SujetForm) form;
        sujetForm.init(subject);

		EntiteCardexForm entiteCardex = obtenirEntiteCardexLiaison(request, "rechercheSujet");
        sujetForm.setEntiteCardexLiaison( entiteCardex );
        
		//Valeurs par d�faut
		sujetForm.resetOnglets();
		sujetForm.setNew(true);
        sujetForm.setStatut(GlobalConstants.Statut.SUJET_ACTIF);
        sujetForm.setRole((String)request.getParameter("role"));
        
		return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * l'onglet Sujets d'un dossier. Ce bouton est r�serv� aux agents de s�curit� pour leur permettre 
     * d'ajouter des fiches sujets au lieu d'utiliser des gabarits. 
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
    public ActionForward createLiaison(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException {

		log.debug("Cr�ation d'un nouveau sujet par un agent de s�curit�");

		ActionMessages errors = new ActionMessages();
        SujetForm sujetForm = new SujetForm();
		sujetForm.init(subject);
        sujetForm.setEntiteCardexLiaison((DossierForm) form );

		//Valeurs par d�faut
		sujetForm.resetOnglets();
		sujetForm.setNew(true);
		//Lorsqu'un sujet est ajout� par un agent de s�curit�, on met le statut � "Provisoire".
		//Le sujet devra ensuite �tre approuv� par un superviseur qui mettra le code Actif si le sujet
		//doit �tre conserv�.
		sujetForm.setStatut(GlobalConstants.Statut.SUJET_PROVISOIRE);
        sujetForm.setRole((String)request.getParameter("role"));
		
		request.getSession().setAttribute("sujet",sujetForm);
		return mapping.findForward("success");
	}

	private EntiteCardexForm obtenirEntiteCardexLiaison(HttpServletRequest request, String formNameEntiteCardexLiaison) {
		EntiteCardexLiaison entiteCardexLiaison = (EntiteCardexLiaison) request.getSession().getAttribute( formNameEntiteCardexLiaison );
		return entiteCardexLiaison.getEntiteCardexLiaison();
	}

	/**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton sauvegarder dans
     * le panneau de cr�ation d'un sujet.  Le nouveau sujet est enregistr� dans le
     * cardex et l'�cran de mise a jour du sujet est affich�.
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
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward save(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {

        log.debug("Sauvegarde de la cr�ation d'un nouveau sujet");

        ActionMessages errors = new ActionMessages();
		Sujet newSujet = new SujetVO();
		SujetForm newSujetForm = (SujetForm) form;

		try {
			verifierToken(request);
			SujetBusinessDelegate sujetDelegate = new SujetBusinessDelegate();

			ValueObjectMapper.convertSujetHtmlForm(newSujetForm, newSujet,
					subject.getLocale());

			log.debug("Sujet � cr�er : " + newSujet);
			Sujet criteria = sujetDelegate.create(subject, newSujet);
			log.debug("# Cl� de sujet retourn� : " + newSujet.getCle());

			//V�rification d'un mandat PSU associ� � la cr�ation d'un sujet par
			// nom
			if (!StringUtils.isEmpty(newSujetForm.getNom())) {
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setSujetNom(newSujetForm.getNom().toUpperCase());
				if (!StringUtils.isEmpty(newSujetForm.getPrenom())) {
					psuMandat.setSujetPrenom(newSujetForm.getPrenom()
							.toUpperCase());
				}
				PSUMandatAction.verificationMandat(subject, psuMandat,
						GlobalConstants.GenreFichier.SUJET,
						GlobalConstants.TypeAction.AJOUT);
			}

			Sujet sujetCreated = sujetDelegate.find(subject, criteria);
			log.debug("Sujet cr�� : " + sujetCreated);

			if (newSujetForm.getEntiteCardexLiaison() != null){
				//On fait la liaison automatique avec le module reli�
            	if (newSujetForm.getEntiteCardexLiaison() instanceof SujetForm){
            		Sujet sujet = new SujetVO();
            		sujet.setCle(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getCle()));
            		sujet.setSite(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getSite()));
                    sujetCreated.setRole(Long.parseLong(newSujetForm.getRole()));
                    sujet.setRole(Long.parseLong(newSujetForm.getRole()));
                    sujetDelegate.addLienSujet(subject, sujetCreated, sujet);
            	}else if (newSujetForm.getEntiteCardexLiaison() instanceof DossierForm){
            		Dossier dossier = new DossierVO();
                    dossier.setCle(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getCle()));
                    dossier.setSite(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getSite()));
                    sujetCreated.setRole(Long.parseLong(newSujetForm.getRole()));
                    dossier.setRole(Long.parseLong(newSujetForm.getRole()));
                    sujetDelegate.addLienDossier(subject, sujetCreated, dossier);
            	}else if (newSujetForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		Societe societe = new SocieteVO();
            		societe.setCle(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getCle()));
            		societe.setSite(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getSite()));
                    sujetCreated.setRole(Long.parseLong(newSujetForm.getRole()));
                    societe.setRole(Long.parseLong(newSujetForm.getRole()));
                    sujetDelegate.addLienSociete(subject, sujetCreated, societe);
            	}else if (newSujetForm.getEntiteCardexLiaison() instanceof VehiculeForm){
            		Vehicule vehicule = new VehiculeVO();
            		vehicule.setCle(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getCle()));
            		vehicule.setSite(Long.parseLong(newSujetForm.getEntiteCardexLiaison().getSite()));
                    sujetCreated.setRole(Long.parseLong(newSujetForm.getRole()));
                    vehicule.setRole(Long.parseLong(newSujetForm.getRole()));
                    sujetDelegate.addLienVehicule(subject, sujetCreated, vehicule);
            	}
			}
				populateSujetForm(subject, criteria, newSujetForm);
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
	
    private void assignerLienRole(HttpServletRequest request, SujetForm sujetForm) {
		LienForm lienForm = new LienForm();
		lienForm.assignerSource(sujetForm.getEntiteCardexLiaison());
		lienForm.assignerDestination(sujetForm);
		request.getSession().setAttribute("lien", lienForm);
	}
	
    /**
     * <p>
     * Cet �v�nement est appel� au lieu de la m�thode showAcces afin d'�viter qu'un enregistrement
     * ne soit inscrit dans la table AC_ACCES.  En mode Web, chaque retour � un sujet (par
     * exemple, apr�s avoir inscrit une narration) se traduit par une relecture du sujet.
     * Cela produit chaque fois un nouvel enregistrement Select dans la table
     * AC_ACCES. 
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
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {

        log.debug("Acc�s � un sujet");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate sujetDelegate = new SujetBusinessDelegate();
            SujetForm  sujetForm = (SujetForm) form;
            Sujet      sujet     = new SujetVO();
            
            log.debug("Sujet recherch�: " + sujetForm.toString());

            if (AideController.isNullOrEquals(sujetForm.getMotPasse(),sujetForm.getConfirmationMotPasse()) ) {
              ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,subject.getLocale());
              sujet = sujetDelegate.find(subject, sujet);
              log.debug("Sujet trouv�: " + sujet.toString());
              ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
              //sujetForm.setNew(false);
              populateSujetFormShow(subject, sujet, sujetForm);
              assignerNouveauSujet(request, sujetForm);
              request.getSession().setAttribute(GlobalConstants.MotDePasse.SUJET_ATTEMPS,new Integer(0));
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.SUJET_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,subject.getLocale());
                sujet = sujetDelegate.findAcces(subject, sujet);
                log.debug("Sujet prot�g�: " + sujet.toString());
                ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
                populateSujetForm(subject, sujet, sujetForm);
                sujetForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr�mentation du nombre d'essaie
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.SUJET_ATTEMPS,nbOfAttempsInteger);

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

		log.debug("Acc�s � un sujet");

		ActionMessages errors = new ActionMessages();

		try {
			SujetBusinessDelegate sujetDelegate = new SujetBusinessDelegate();
			SujetForm sujetForm = (SujetForm) form;
			Sujet sujet = new SujetVO();

			log.debug("Sujet recherch�: " + sujetForm.toString());

			ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
					subject.getLocale());
			log.debug("Avant recherche");
			sujet = sujetDelegate.find(subject, sujet);
			log.debug("Sujet trouv�: " + sujet.toString());
			ValueObjectMapper.convertSujet(sujet, sujetForm, subject
					.getLocale());
			sujetForm.setNew(false);
			populateSujetFormShow(subject, sujet, sujetForm);

			request.getSession().setAttribute(
					GlobalConstants.MotDePasse.SUJET_ATTEMPS,
					new Integer(0));
			log.debug("Avant retour");
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
        log.debug("Liaison d'un sujet");

        return mapping.findForward("success");
    }    
    
    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur l'icone de visualisation(loupe)  dans
     * le panneau de recherche des sujets.  L'application affiche le panneau de mise � jour
     * du sujet s�lectionn� dans la liste de r�sultats de l'�cran de recherche.
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
    public ActionForward showAcces(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {

        log.debug("Acc�s � un sujet");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate sujetDelegate =
                new SujetBusinessDelegate();
            SujetForm   sujetForm 	= (SujetForm) form;
            Sujet       sujet 		= new SujetVO();
            
            log.debug("Sujet recherch�: " + sujetForm.toString());

            if (AideController.isNullOrEquals(sujetForm.getMotPasse(),sujetForm.getConfirmationMotPasse()) ) {
              ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,subject.getLocale());
              sujet = sujetDelegate.findAcces(subject, sujet);
              log.debug("Sujet trouv�: " + sujet.toString());
              sujetForm.init(subject);

              ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
              sujetForm.setNew(false);
              populateSujetFormShow(subject, sujet, sujetForm);
              request.getSession().setAttribute("sujet", sujetForm);
              request.getSession().setAttribute(GlobalConstants.MotDePasse.SUJET_ATTEMPS,new Integer(0));
              //V�rification d'un mandat PSU associ� � la consultation d'un sujet
              PSUMandatForm psuMandat = new PSUMandatForm();
              psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
              PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.CONSULTATION);
              return mapping.findForward("success");
            }else {
              Integer nbOfAttempsInteger = (Integer)request.getSession().getAttribute(GlobalConstants.MotDePasse.SUJET_ATTEMPS);
              int nbOfAttemps = 0;
              if (nbOfAttempsInteger != null) {
                nbOfAttemps = nbOfAttempsInteger.intValue();
              }

              //Est-ce que le maximum d'essaie de mot de passe est atteint
              if (nbOfAttemps < GlobalConstants.MotDePasse.MAX_ATTEMPS) {
                ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,subject.getLocale());
                sujet = sujetDelegate.findAcces(subject, sujet);
                log.debug("Sujet prot�g�: " + sujet.toString());
                sujetForm.init(subject);
                ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
                populateSujetForm(subject, sujet, sujetForm);
                sujetForm.setConfirmationMotPasse("");
                if (nbOfAttemps > 0) {
                  ActionMessage error = new ActionMessage("cardex_password");
                  errors.add(ActionMessages.GLOBAL_MESSAGE,error);
                  saveErrors(request, errors);
                }

                //Incr�mentation du nombre d'essaie
                nbOfAttemps++;
                nbOfAttempsInteger = new Integer(nbOfAttemps);
                request.getSession().setAttribute(GlobalConstants.MotDePasse.SUJET_ATTEMPS,nbOfAttempsInteger);

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
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton modifier dans
     * le panneau de mise � jour d'un sujet.  Les modifiactions du sujet est enregistr� dans le
     * cardex et l'�cran de mise a jour du sujet est affich�.
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


        log.debug("Mise � jour d'un sujet");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate sujetDelegate =
                new SujetBusinessDelegate();
            Sujet                 sujet = new SujetVO();
            SujetForm sujetForm = (SujetForm)form;
            ValueObjectMapper.convertSujetHtmlForm((SujetForm) form, sujet,
                    subject.getLocale());
            FormFile file = null;
            String nomFichier = "";
            //On v�rifie s'il s'agit de l'ajout d'une photo
            if(sujetForm.getAjoutPhoto().getSourceFile() != null 
                    && StringUtils.isNotEmpty(sujetForm.getAjoutPhoto().getSourceFile().getFileName())){
                file = sujetForm.getAjoutPhoto().getUploadImage();
                nomFichier = file.getFileName();
                sujetForm.getAjoutPhoto().setExtension(sujetForm.getAjoutPhoto().getExtensionDeFilePath());                
                //Est ce que la taille du fichier exc�de la taille maximale accept�e?
                if (sujetForm.getAjoutPhoto().isTailleAccepte() == false) {
                    log.error("La taille du fichier est sup�rieure � 7MB.");
                    throw (new BusinessRuleExceptionHandle("erreur_fichier")).getBusinessException();
                }else if(sujetForm.getAjoutPhoto().isPhoto() == false){
                    log.error("Ce fichier n'est pas une photo");
                    throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
                }else{
	            	sujetForm.getAjoutPhoto().setLien(sujetForm.getCle());
	            	sujetForm.getAjoutPhoto().setLienSite(sujetForm.getSite());
	            	sujetForm.getAjoutPhoto().setConfidentialite(sujetForm.getConfidentialite());
	            	addLienPhotoAjout(subject, mapping, sujetForm.getAjoutPhoto(), request, response);
                }
            }else{ //Sinon, on fait la mise � jour de la fiche sujet.
	            log.debug("Mise � jour du sujet: " + sujet.toString());
	            sujetDelegate.update(subject, sujet);
	            
				//V�rification d'un mandat PSU associ� � la mise � jour d'un sujet
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.MISE_A_JOUR);
            }
 
            populateSujetForm(subject, sujet, sujetForm);

            if (sujetForm.getEntiteCardexLiaison() != null){
            	assignerLienRole(request, sujetForm);

/*            	if(StringUtils.isNotEmpty(nomFichier)){
            		//Dans le cas d'ajout d'une photo, on revient � l'�cran de consultation et non 
            		//� celui du r�le.
            		return mapping.findForward("success");
            	}
            	if (sujetForm.getEntiteCardexLiaison() instanceof SujetForm){
					return mapping.findForward("successLiaisonSujet");
            	}else if (sujetForm.getEntiteCardexLiaison() instanceof DossierForm){
            		
            		return mapping.findForward("successLiaisonDossier");
            	}else if (sujetForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		return mapping.findForward("successLiaisonSociete");
            	}else if (sujetForm.getEntiteCardexLiaison() instanceof VehiculeForm){
            		return mapping.findForward("successLiaisonVehicule");
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
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton fermer dans
     * le panneau de consultation d'un sujet.  
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


        log.debug("Retour de la consultation d'un sujet");

        //ActionMessages errors = new ActionMessages();

            SujetForm sujetForm = (SujetForm)form;

            if (sujetForm.getEntiteCardexLiaison() != null){
            	assignerLienRole(request, sujetForm);

            	if (sujetForm.getEntiteCardexLiaison() instanceof SujetForm){
            		//On met le sujet source, sinon on boucle sur le nouveau sujet ajout�.
            		SujetForm sujetSource = (SujetForm)sujetForm.getEntiteCardexLiaison();
            		sujetSource.setEntiteCardexLiaison(null);
            		request.getSession().setAttribute("sujet", sujetSource);
					return mapping.findForward("successLiaisonSujet");
            	}else if (sujetForm.getEntiteCardexLiaison() instanceof DossierForm){
            		return mapping.findForward("successLiaisonDossier");
            	}else if (sujetForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		return mapping.findForward("successLiaisonSociete");
            	}else if (sujetForm.getEntiteCardexLiaison() instanceof VehiculeForm){
            		return mapping.findForward("successLiaisonVehicule");
            	}
            }
            
            return mapping.findForward("success");
    }

    public ActionForward updateLiaison(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {


    	log.debug("Mise � jour d'un sujet");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			SujetBusinessDelegate sujetDelegate = new SujetBusinessDelegate();
			Sujet sujet = new SujetVO();
			SujetForm sujetForm = (SujetForm) form;

			ValueObjectMapper.convertSujetHtmlForm((SujetForm) form, sujet,
					subject.getLocale());
			log.debug("Mise � jour du sujet: " + sujet.toString());
			sujetDelegate.update(subject, sujet);

			//V�rification d'un mandat PSU associ� � la mise � jour d'un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			PSUMandatAction.verificationMandat(subject, psuMandat,
					GlobalConstants.GenreFichier.SUJET,
					GlobalConstants.TypeAction.MISE_A_JOUR);

			sujet = sujetDelegate.find(subject, sujet);
			if (sujet != null) {
				ValueObjectMapper.convertSujet(sujet, sujetForm, subject
						.getLocale());
				log.debug("Modification effective du sujet: "
						+ sujet.toString());
			}

			populateSujetForm(subject, sujet, sujetForm);

			assignerLienRole(request, sujetForm);
			
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
        log.debug("Liaison d'un dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm           lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet                 sujet = new SujetVO();
            Dossier             dossier = new DossierVO();
            sujetForm.init(subject);
            
            sujet.setCle(lienForm.getCleSource());
            sujet.setSite(lienForm.getSiteSource());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));

            dossier.setCle(lienForm.getCleDestination());
            dossier.setSite(lienForm.getSiteDestination());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));

            log.debug(lienForm.toString());
            delegate.addLienDossier(subject, sujet,
                                    dossier);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);
            //R�cup�ration du num�ro de dossier qui vient d'�tre associ�
            Collection dossierListe = sujetForm.getDossiers();
			Iterator it = dossierListe.iterator();
			String numeroCardex = "";
			while (it.hasNext()) {
				DossierForm dossierAssocie= (DossierForm)it.next();
				if(dossierAssocie.getCle().equals(Long.toString(dossier.getCle()))){
					numeroCardex = dossierAssocie.getNumeroCardex().toString();
				}
			}
			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);

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
     * d'un �l�ment de r�sultats de recherche des societes en mode liaison.
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
        log.debug("Liaison d'un societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet                 sujet = new SujetVO();
            Societe               societe = new SocieteVO();
            sujetForm.init(subject);

            sujet.setCle(lienForm.getCleSource());
            sujet.setSite(lienForm.getSiteSource());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));

            societe.setCle(lienForm.getCleDestination());
            societe.setSite(lienForm.getSiteDestination());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));

            log.debug(lienForm.toString());
            delegate.addLienSociete(subject, sujet,
                                    societe);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//R�cup�ration du nom de la soci�t� qui vient d'�tre associ�e
			Collection societeListe = sujetForm.getSocietes();
			Iterator it = societeListe.iterator();
			String nom = "";
			while (it.hasNext()) {
				SocieteForm societeAssocie= (SocieteForm)it.next();
				if(societeAssocie.getCle().equals(Long.toString(societe.getCle()))){
					nom = societeAssocie.getNom();
				}
			}

			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);

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
	 * @param request
	 * @param sujetForm
	 */
	private void assignerNouveauSujet(HttpServletRequest request, SujetForm sujetForm) {
		SujetForm sujetFormOriginal = (SujetForm) request.getSession().getAttribute("sujet");
		sujetForm.setEntiteCardexLiaison( sujetFormOriginal.getEntiteCardexLiaison() );
		sujetForm.setNew(sujetFormOriginal.isNew());
		request.getSession().setAttribute("sujet", sujetForm);
	}
	
	/**
     * Sauvegarde des changements aux caract�ristiques en conservant un audit. 
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward updateLiensCaracteristique(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Mise � jour des liens caract�ristiques avec audit");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            Sujet sujet = new SujetVO();
            sujetForm.init(subject);
            CaracteristiquesForm caracteristiquesForm = (CaracteristiquesForm)form;
            Caracteristiques caracteristiques = new CaracteristiquesVO();
            sujetForm.setCle(caracteristiquesForm.getLien());
            sujetForm.setSite(caracteristiquesForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertCaracteristiquesHtmlForm(caracteristiquesForm,caracteristiques,subject.getLocale());
            delegate.updateLiensCaracteristique(subject,sujet,caracteristiques);
            sujet = delegate.find(subject,sujet);
            populateSujetForm(subject,sujet,sujetForm);
            assignerNouveauSujet(request, sujetForm);
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
     * d'un �l�ment de r�sultats de recherche des sujets en mode liaison.
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
        log.debug("Liaison d'un sujet");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet                 sujetOrigine = new SujetVO();
            Sujet                 sujetDestination = new SujetVO();
            sujetForm.init(subject);

            sujetOrigine.setCle(lienForm.getCleSource());
            sujetOrigine.setSite(lienForm.getSiteSource());
            sujetOrigine.setTypeLien(lienForm.getTypeLien());
            sujetOrigine.setRole(Long.parseLong(lienForm.getRole()));

            sujetDestination.setCle(lienForm.getCleDestination());
            sujetDestination.setSite(lienForm.getSiteDestination());
            sujetDestination.setTypeLien(lienForm.getTypeLien());
            sujetDestination.setRole(Long.parseLong(lienForm.getRole()));
            log.debug(lienForm.toString());
            delegate.addLienSujet(subject, sujetOrigine,
                                    sujetDestination);
            populateSujetForm(subject, sujetOrigine, sujetForm);
            assignerNouveauSujet(request, sujetForm);
            
			//R�cup�ration du nom du num�ro de fiche sujet qui vient d'�tre associ�
			Collection sujetListe = sujetForm.getSujets();
			Iterator it = sujetListe.iterator();
			String numeroFiche = "";
			while (it.hasNext()) {
				SujetForm sujetAssocie= (SujetForm)it.next();
				if(sujetAssocie.getCle().equals(Long.toString(sujetDestination.getCle()))){
					numeroFiche = sujetAssocie.getNumeroFiche();
				}
			}

			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);

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
     * d'un �l�ment de r�sultats de recherche des v�hicules en mode liaison.
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
        log.debug("Liaison d'un v�hicule");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm        lienForm = (LienForm) form;
            SujetForm       sujetForm = new SujetForm();
            Sujet           sujet = new SujetVO();
            Vehicule        vehicule = new VehiculeVO();

            sujetForm.init(subject);
            sujet.setCle(lienForm.getCleSource());
            sujet.setSite(lienForm.getSiteSource());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(GlobalConstants.Role.SANS_OBJET);

            vehicule.setCle(lienForm.getCleDestination());
            vehicule.setSite(lienForm.getSiteDestination());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(GlobalConstants.Role.SANS_OBJET);

            log.debug(lienForm.toString());
            delegate.addLienVehicule(subject, sujet,
                                    vehicule);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//R�cup�ration de l'immatriculation du v�hicule qui vient d'�tre associ�
			Collection vehiculeListe = sujetForm.getVehicules();
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
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			psuMandat.setReference1(immatriculation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);

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
     * de destruction de lien d'un �l�ment de l'onglets dossiers dans l'�cran
     * de consultation dossier.
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
        log.debug("Destruction d'un lien dossier");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet                 sujet = new SujetVO();
            Dossier                 dossier = new DossierVO();

            sujetForm.init(subject);
            sujet.setCle(lienForm.getCleSource());
            sujet.setSite(lienForm.getSiteSource());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));
            sujet.setLien(lienForm.getCle());
            sujet.setLienSite(lienForm.getSite());

            dossier.setCle(lienForm.getCleDestination());
            dossier.setSite(lienForm.getSiteDestination());
            dossier.setTypeLien(lienForm.getTypeLien());
            dossier.setRole(Long.parseLong(lienForm.getRole()));
            dossier.setLien(lienForm.getCle());
            dossier.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienDossier(subject,sujet,dossier);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			//psuMandat.setReference1(numeroCardex);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
        log.debug("Destruction d'un lien societe");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet                 sujet = new SujetVO();
            Societe                 societe =
                new SocieteVO();
            sujetForm.init(subject);

            sujet.setCle(lienForm.getCleSource());
            sujet.setSite(lienForm.getSiteSource());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));
            sujet.setLien(lienForm.getCle());
            sujet.setLienSite(lienForm.getSite());

            societe.setCle(lienForm.getCleDestination());
            societe.setSite(lienForm.getSiteDestination());
            societe.setTypeLien(lienForm.getTypeLien());
            societe.setRole(Long.parseLong(lienForm.getRole()));
            societe.setLien(lienForm.getCle());
            societe.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienSociete(subject,sujet,societe);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
			//psuMandat.setReference1(nom);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet             sujet = new SujetVO();
            Vehicule          vehicule = new VehiculeVO();
            sujetForm.init(subject);

            sujet.setCle(lienForm.getCleSource());
            sujet.setSite(lienForm.getSiteSource());
            sujet.setTypeLien(lienForm.getTypeLien());
            sujet.setRole(Long.parseLong(lienForm.getRole()));
            sujet.setLien(lienForm.getCle());
            sujet.setLienSite(lienForm.getSite());

            vehicule.setCle(lienForm.getCleDestination());
            vehicule.setSite(lienForm.getSiteDestination());
            vehicule.setTypeLien(lienForm.getTypeLien());
            vehicule.setRole(Long.parseLong(lienForm.getRole()));
            vehicule.setLien(lienForm.getCle());
            vehicule.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienVehicule(subject,sujet,vehicule);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);
			
			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
			//psuMandat.setReference1(immatriculation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
     * de destruction de lien d'un �l�ment de l'onglets dossiers dans l'�cran
     * de consultation dossier.
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
        log.debug("Destruction d'un lien sujet");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate =
                new SujetBusinessDelegate();
            LienForm                lienForm = (LienForm) form;
            SujetForm         sujetForm = new SujetForm();
            Sujet                 sujetOrigine = new SujetVO();
            Sujet                 sujetDestination =
                new SujetVO();
            sujetForm.init(subject);

            sujetOrigine.setCle(lienForm.getCleSource());
            sujetOrigine.setSite(lienForm.getSiteSource());
            sujetOrigine.setTypeLien(lienForm.getTypeLien());
            sujetOrigine.setRole(Long.parseLong(lienForm.getRole()));
            sujetOrigine.setLien(lienForm.getCle());
            sujetOrigine.setLienSite(lienForm.getSite());

            sujetDestination.setCle(lienForm.getCleDestination());
            sujetDestination.setSite(lienForm.getSiteDestination());
            sujetDestination.setTypeLien(lienForm.getTypeLien());
            sujetDestination.setRole(Long.parseLong(lienForm.getRole()));
            sujetDestination.setLien(lienForm.getCle());
            sujetDestination.setLienSite(lienForm.getSite());

            log.debug(lienForm.toString());
            delegate.deleteLienSujet(subject, sujetOrigine,
                                       sujetDestination);
            populateSujetForm(subject, sujetOrigine, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			//psuMandat.setReference1(numeroFiche);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
        log.debug("Liaison d'une narration � un sujet.");
        ActionMessages errors = new ActionMessages();
        ActionMessages messages = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            NarrationForm narrationForm = (NarrationForm)form;
            SujetForm sujetForm  = narrationForm.getSujet();
            Sujet sujet = new SujetVO();
            Narration narration = new NarrationVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Narration: " + narration);
            NarrationBaliseUtil.assignerMessageSiNarrationANettoyer(messages, narrationForm.getNarrationAvecFormat());
            delegate.addLienNarration(subject,sujet,narration);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//R�cup�ration de la date de cr�ation de la narration qui vient d'�tre associ�e
			Collection narrationListe = sujetForm.getNarrations();
			Iterator it = narrationListe.iterator();
			String dateCreation = "";
			while (it.hasNext()) {
				NarrationForm narrationAssocie= (NarrationForm)it.next();
				if(narrationAssocie.getCle().equals(Long.toString(narration.getCle()))){
					dateCreation = narrationAssocie.getDateCreation();
				}
			}

			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(dateCreation);
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);
			//Dans le cas des narratisons, on v�rifie �galement pour les mots-cl�s.
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
			psuMandat.setReference1(narrationForm.getNarrationSansFormat());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.NARRATION, GlobalConstants.TypeAction.AJOUT);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
			String ancestor = bre.getAncestor().toString();
			ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp�cial d'erreur. Durant la t�che qui reconstruit l'index des narrations
			//les sauvegardes �chouent. Dans ce cas, un message d'erreur est retourn� et la
			//narration est perdue. Le test suivant permet de d�tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration � l'�cran avec
			//un message plus appropri�, sans perte de donn�es. 
			if(ancestor.indexOf("ORA-29861") > -1){
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
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            SujetForm sujetForm = new SujetForm();
            Photo photo = new PhotoVO();
            Sujet sujet = new SujetVO();
            log.debug("PhotoForm a li�e : " + photoForm);

            sujetForm.init(subject);
            sujetForm.setCle(photoForm.getLien());
            sujetForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm,sujet,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());

            FormFile   file = photoForm.getUploadImage();
            
            //Est ce que la taille du fichier exc�de 4MB
            if (photoForm.isTailleAccepte() == false) {
                log.error("La taille du fichier est sup�rieure � 4MB.");
                return mapping.findForward("error");
            }else if(photoForm.isPhoto() == false){
                log.error("Ce fichier n'est pas une photo");
                throw (new BusinessRuleExceptionHandle("erreur.ajout.type.photo")).getBusinessException();
            }else{
            	byte[] data = file.getFileData();
            	photo.setImage( data );
            	
	            log.debug("Photo a li�e : " + photo);
	            photo = delegate.addLienPhoto(subject,sujet,photo);
	            
	            log.debug("Photo li�e : " + photo);
	            file.destroy();
				populateSujetForm(subject, sujet, sujetForm);
				assignerNouveauSujet(request, sujetForm);

				//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
				psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
				psuMandat.setReference1(Long.toString(photo.getLienElement()));
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);


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
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
            PhotoForm  photoForm = (PhotoForm) form;
            SujetForm sujetForm = new SujetForm();
            Photo photo = new PhotoVO();
            Sujet sujet = new SujetVO();
            log.debug("PhotoForm a li�e : " + photoForm);

            sujetForm.init(subject);
            sujetForm.setCle(photoForm.getLien());
            sujetForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm,sujet,subject.getLocale());
            photoForm.setExtension(photoForm.getExtensionDeFilePath());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm,photo,subject.getLocale());
            FormFile   file = photoForm.getUploadImage();
        	byte[] data = file.getFileData();
        	photo.setImage( data );
            log.debug("Photo a li�e : " + photo);
            photo = delegate.addLienPhoto(subject,sujet,photo);
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
        log.debug("Suppression d'un lien entre une adresse et un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            AdresseForm adresseForm = (AdresseForm)form;
            Sujet sujet = new SujetVO();
            Adresse adresse = new AdresseVO();
            sujetForm.init(subject);
            sujetForm.setCle(adresseForm.getLien());
            sujetForm.setSite(adresseForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Adresse: " + adresse);
            delegate.addLienAdresse(subject,sujet,adresse);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);
                                                 
			//V�rification d'un mandat PSU associ� � l'ajout d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.ADRESSE);
			psuMandat.setReference1(" ");
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.LIAISON);

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
        log.debug("Suppression d'un lien entre une photo et un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            PhotoForm photoForm = (PhotoForm)form;
            Sujet sujet = new SujetVO();
            Photo photo = new PhotoVO();
            sujetForm.init(subject);
            sujetForm.setCle(photoForm.getLien());
            sujetForm.setSite(photoForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertPhotoHtmlForm(photoForm, photo,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Photo: " + photo);
            delegate.deleteLienPhoto(subject,sujet,photo);
            //delegate.selectionnerDernierePhotoGalerie(subject, sujet);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);
			
			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.PHOTOS);
			psuMandat.setReference1(Long.toString(photo.getLienElement()));
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
    public ActionForward deleteLienNarration(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Suppression d'un lien entre une narration et un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Sujet sujet = new SujetVO();
            Narration narration = new NarrationVO();
            sujetForm.init(subject);
            sujetForm.setCle(narrationForm.getLien());
            sujetForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Narration: " + narration);
            delegate.deleteLienNarration(subject,sujet,narration);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
			psuMandat.setReference1(narrationForm.getDateCreation());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
        log.debug("Suppression d'un lien entre une adresse et un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            AdresseForm adresseForm = (AdresseForm)form;
            Sujet sujet = new SujetVO();
            Adresse adresse = new AdresseVO();
            sujetForm.init(subject);
            sujetForm.setCle(adresseForm.getLien());
            sujetForm.setSite(adresseForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Adresse: " + adresse);
            delegate.deleteLienAdresse(subject,sujet,adresse);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

			//V�rification d'un mandat PSU associ� � la suppression d'une liaison � un sujet
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setFicheSujet(sujetForm.getNumeroFiche());
			psuMandat.setGenreFichier(GlobalConstants.GenreFichier.ADRESSE);
			psuMandat.setReference1(" ");
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.SUPPRESSION);

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
     * le panneau de recherche des sujets.  L'�puration consiste � supprimer tous les
     * sujets donc le niveau de confidentialit� est 8.
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
        log.debug("�puration des sujets");

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
    		String nomRapport = chemin+"Sujets � �purer "+ siteDescription + " (" + dateRapport+").pdf";
    		InputStream gabarit = getClass().getClassLoader().getResourceAsStream("rapports/" + RapportsConfiguration.RAPPORT_EPURATION_SUJETS);
			log.debug("Sauvegarder sujets � �purer");
			long site = utilisateur.getSite();
			resultSet = rapportDelegate.rapportEpuration(site, connection, "CARDEX_RAPPORT.SP_RAP_SU_EPURATION");
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.debug(context.getRealPath("/rapports/"));
			ServletContext context = request.getSession().getServletContext();  
	        parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
            parameters.put("REPORT_CONNECTION",connection);
			parameters.put("UTILISATEUR", utilisateur.getCode());
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
			// Sauvegarde dans un fichier
			log.debug("�puration des sujets (Sauvegarde dans un fichier)");
			(new PDFImpressionRapport()).impression(nomRapport, print);
			//On proc�de ensuite � l'�puration
			SujetBusinessDelegate sujetDelegate =
                new SujetBusinessDelegate();
            sujetDelegate.delete(subject);
            //Apr�s la suppression, on vide la liste des r�sultats.
            CriteresRechercheSujetForm criteresRechercheSujetHtmlForm = new CriteresRechercheSujetForm();
            criteresRechercheSujetHtmlForm.init( subject );
            criteresRechercheSujetHtmlForm.getListeResultat().vider();
            request.getSession().setAttribute("rechercheSujet",criteresRechercheSujetHtmlForm);

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
        log.debug("Mise � jour d'un lien entre une narration et un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Sujet sujet = new SujetVO();
            Narration narration = new NarrationVO();
            sujetForm.init(subject);
            sujetForm.setCle(narrationForm.getLien());
            sujetForm.setSite(narrationForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Narration: " + narration);
            delegate.updateLienNarration(subject,narration);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
			String ancestor = bre.getAncestor().toString();
			ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp�cial d'erreur. Durant la t�che qui reconstruit l'index des narrations
			//les sauvegardes �chouent. Dans ce cas, un message d'erreur est retourn� et la
			//narration est perdue. Le test suivant permet de d�tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration � l'�cran avec
			//un message plus appropri�, sans perte de donn�es. 
			if(ancestor.indexOf("ORA-29861") > -1){
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
        log.debug("Approbation d'une narration li�e � un sujet.");
        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Sujet sujet = new SujetVO();
            Narration narration = new NarrationVO();
            sujetForm.init(subject);
            sujetForm.setCle(narrationForm.getLien());
            sujetForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur(user.getCode());
            narrationForm.setAutoriteApprobateur(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setAutoriteNarration(Long.toString(privilege.getNiveauAuthorite()));
            narrationForm.setConfidentialiteApprobateur(Long.toString(privilege.getNiveauConfidentialite()));
            narrationForm.setDateApprobation(currentDate);
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

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
        log.debug("Permettre la modification d'une narration li�e � un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            NarrationForm narrationForm = (NarrationForm)form;
            Sujet sujet = new SujetVO();
            Narration narration = new NarrationVO();
            sujetForm.init(subject);
            sujetForm.setCle(narrationForm.getLien());
            sujetForm.setSite(narrationForm.getLienSite());
            narrationForm.setApprobateur("");
            narrationForm.setAutoriteApprobateur("");
            narrationForm.setAutoriteNarration("");
            narrationForm.setConfidentialiteApprobateur("");
            narrationForm.setDateApprobation("");
            narrationForm.setAutoriteNarration(narrationForm.getAutoriteCreateur());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Narration: " + narration);
            delegate.approuveLienNarration(subject,narration);
            populateSujetForm(subject, sujet, sujetForm);
            request.getSession().setAttribute("sujet", sujetForm);

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
        log.debug("Mise � jour d'un lien entre une adresse et un sujet.");
        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = new SujetForm();
            AdresseForm adresseForm = (AdresseForm)form;
            Sujet sujet = new SujetVO();
            Adresse adresse = new AdresseVO();
            sujetForm.init(subject);
            sujetForm.setCle(adresseForm.getLien());
            sujetForm.setSite(adresseForm.getLienSite());
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            log.debug("Sujet: " + sujet);
            log.debug("Adresse: " + adresse);
            delegate.updateLienAdresse(subject,adresse);
            populateSujetForm(subject, sujet, sujetForm);
            assignerNouveauSujet(request, sujetForm);

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
     * Popule les informations d'un sujet obtenu dans la base de donn�e
     * dans le SujetForm sp�cifi�s.
     *
     * @param Sujet Les crit�res du sujet a obtenir
     * @param SujetForm L'ActionForm bean a popul� � partir du sujet obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populateSujetForm(CardexAuthenticationSubject subject,
                                     Sujet criteria,
                                     SujetForm sujetForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        SujetBusinessDelegate delegate =
            new SujetBusinessDelegate();
        Sujet sujet = delegate.find(subject, criteria);

        sujetForm.resetOnglets();
        //log.debug("Sujet trouv�: " + sujet.toString());
        ValueObjectMapper.convertSujet(sujet, sujetForm, subject.getLocale());
        sujetForm.setConfirmationMotPasse(sujetForm.getMotPasse());
        rechercheLiensSujet(subject, sujet, sujetForm, delegate);
    }
    
    /**
     * Effectue la recherche des enregistrements li�s � un sujet donn�, 
     * affich�s dans les onglets du sujet.
     *
     * @param subject Le sujet authentifi�
     * @param sujet Les crit�res du sujet a obtenir
     * @param sujetForm L'ActionForm bean a popul� � partir du sujet obtenu
     * @param delegate Lien avec la base de donn�es.
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void rechercheLiensSujet(CardexAuthenticationSubject subject,
                                     Sujet sujet,
                                     SujetForm sujetForm,
                                     SujetBusinessDelegate delegate) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {		
        // Recherche des liens sujet
        DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();
		Collection liensSujet = delegate.findLiensSujet(subject,sujet);
        Iterator   it = liensSujet.iterator();
        log.debug("Sujet li�s (" + liensSujet.size() + ") :");
        while (it.hasNext()) {
            Sujet     linkSujet = (Sujet) it.next();
            SujetForm linkSujetForm = new SujetForm();
            linkSujetForm.init(subject);
            
            ValueObjectMapper.convertSujet(linkSujet, linkSujetForm,
                    subject.getLocale());
            linkSujetForm.assignerValeurDeListe(subject);
            sujetForm.addSujet(linkSujetForm);
            log.debug(linkSujet.toString());
        }
        sujetForm.getListeSujets().assignerTrierDefault(SujetOngletTrieListe.CLE_NOM, false, new SujetOngletTrieListe());
        
        // Recherche des liens narration
        Collection liensNarration = delegate.findLiensNarration(subject,
                sujet);
        it = liensNarration.iterator();

        log.debug("Narration li�s (" + liensNarration.size() + ") :");

        while (it.hasNext()) {
            Narration     linkNarration = (Narration) it.next();
            NarrationForm linkNarrationForm = new NarrationForm();
			//On inscrit les valeurs de r�f�rence pour l'impression de la narration.
			linkNarration.setReference1(sujetForm.getNumeroFiche());
			linkNarration.setReference2(sujetForm.getNom());
			linkNarration.setReference3(sujetForm.getPrenom());
			
            ValueObjectMapper.convertNarration(linkNarration, linkNarrationForm,
                    subject.getLocale());
            linkNarrationForm.assignerValeurDeListe( subject );
            sujetForm.addNarration(linkNarrationForm);
            log.debug(linkNarration.toString());
        }
        sujetForm.getListeNarrations().assignerTrierDefault(NarrationOngletTrieListe.CLE_DATE_CREATION, true, new NarrationOngletTrieListe());

        // Recherche des liens dossier
        Collection liensDossier = findLiensDossier(subject, sujet, delegate);
        it = liensDossier.iterator();

        log.debug("Dossier li�s (" + liensDossier.size() + ") :");

        while (it.hasNext()) {
            Dossier     linkDossier = (Dossier) it.next();
            DossierForm linkDossierForm = new DossierForm();

            ValueObjectMapper.convertDossier(linkDossier, linkDossierForm,
                    subject.getLocale());
         // Recherche des liens jeux.  On n'effectue la recherche que pour les dossiers actifs
            if(linkDossierForm.getStatut().equals(GlobalConstants.Statut.DOSSIER_ACTIF)){
		        Jeux liensJeux = dossierDelegate.findLiensJeux(subject,linkDossier);
		        log.debug("Jeux li�s (" + liensJeux.getJeuxChoisis().size() + ") :");
		        JeuxForm linkJeuxForm = new JeuxForm();
		        ValueObjectMapper.convertJeux(subject, liensJeux,linkJeuxForm,subject.getLocale());
		        log.debug(linkJeuxForm.toString());
		        linkDossierForm.setJeux(linkJeuxForm);
            }
            linkDossierForm.assignerValeurDeListe(subject);
            sujetForm.addDossier(linkDossierForm);
            log.debug(linkDossier.toString());
        }
        sujetForm.getListeDossiers().assignerTrierDefault(DossierOngletTrieListe.CLE_DATE_DEBUT, true, new DossierOngletTrieListe());        

    // Recherche des liens photos
        Collection liensPhoto = delegate.findLiensPhoto(subject,
                sujet);
        it = liensPhoto.iterator();
        log.debug("Photos li�s (" + liensPhoto.size() + ") :");
        
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
            sujetForm.addPhoto(sublist);
        }//while

        // Recherche des liens adresses
        Collection liensAdresse = delegate.findLiensAdresse(subject,
                sujet);
        it = liensAdresse.iterator();
        log.debug("Adresses li�s (" + liensAdresse.size() + ") :");

        while (it.hasNext()) {
            Adresse     linkAdresse = (Adresse) it.next();
            AdresseForm linkAdresseForm = new AdresseForm();
            ValueObjectMapper.convertAdresse(linkAdresse, linkAdresseForm,
                    subject.getLocale());
            log.debug(linkAdresse.toString());
            linkAdresseForm.assignerValeurDeListe( subject );
            sujetForm.addAdresse(linkAdresseForm);
        }//while
        sujetForm.getListeAdresses().assignerTrierDefault(AdresseOngletTrieListe.CLE_DATE_CREATION, true, new AdresseOngletTrieListe());
        
        // Recherche des liens caracteristiques
        Caracteristiques liensCaracteristiques = delegate.findLiensCaracteristique(subject,
                sujet);
        log.debug("Caracteristiques li�s (" + liensCaracteristiques.getCaracteristiquesChoisis().size() + ") :");
        CaracteristiquesForm linkCaracteristiquesForm = new CaracteristiquesForm();
        ValueObjectMapper.convertCaracteristiques(subject, liensCaracteristiques,linkCaracteristiquesForm,subject.getLocale());
        sujetForm.setCaracteristiques(linkCaracteristiquesForm);

        // Recherche des liens societe
        Collection liensSociete = delegate.findLiensSociete(subject,
                sujet);
        it = liensSociete.iterator();

        log.debug("Societe li�s (" + liensSociete.size() + ") :");

        while (it.hasNext()) {
            Societe     linkSociete = (Societe) it.next();
            SocieteForm linkSocieteForm = new SocieteForm();

            ValueObjectMapper.convertSociete(linkSociete, linkSocieteForm,
                    subject.getLocale());
            linkSocieteForm.assignerValeurDeListe( subject );
            linkSocieteForm.assignerPermettreSuppressionLiaison(subject,sujetForm);
            sujetForm.addSociete(linkSocieteForm);
            log.debug(linkSociete.toString());
        }
        sujetForm.getListeSocietes().assignerTrierDefault(SocieteOngletTrieListe.CLE_NOM, false, new SocieteOngletTrieListe());

        // Recherche des liens vehicule
        Collection liensVehicule = delegate.findLiensVehicule(subject,
                sujet);
        it = liensVehicule.iterator();
        log.debug("Vehicule li�s (" + liensVehicule.size() + ") :");
        while (it.hasNext()) {
            Vehicule     linkVehicule = (Vehicule) it.next();
            VehiculeForm linkVehiculeForm = new VehiculeForm();
            ValueObjectMapper.convertVehicule(linkVehicule, linkVehiculeForm,
                    subject.getLocale());
            linkVehiculeForm.assignerValeurDeListe( subject );
            sujetForm.addVehicule(linkVehiculeForm);
            log.debug(linkVehicule.toString());
        }
        sujetForm.getListeVehicules().assignerTrierDefault(VehiculeOngletTrieListe.CLE_IMMATRICULATION, false, new VehiculeOngletTrieListe());

	    //Valeurs par d�faut d'une nouvelle photo
	    sujetForm.getAjoutPhoto().setTypeMultimedia(GlobalConstants.TypeMutliMedia.PHOTO);
	    sujetForm.getAjoutPhoto().setCle("-1");
	    sujetForm.getAjoutPhoto().setSite("-1");
	    sujetForm.getAjoutPhoto().setLienMultimedia("-1");
	    sujetForm.getAjoutPhoto().setLienSiteMultimedia("-1");
	    sujetForm.getAjoutPhoto().setLienElement("-1");
	    sujetForm.getAjoutPhoto().setLienSiteElement("-1");
        
    }
    
	protected Collection findLiensDossier(CardexAuthenticationSubject subject, Sujet sujet, SujetBusinessDelegate delegate)throws BusinessException, BusinessResourceException {
		return delegate.findLiensDossier(subject, sujet);
	}
    
    /**
     * Popule les informations d'un sujet obtenu dans la base de donn�e
     * dans le SujetForm sp�cifi�.  Cette m�thode est appel�e par show et
     * showAcces.  Le diff�rence avec populateSujetForm est que le dossier 
     * n'est plus appel� puisqu'il l'est d�j� dans show et showAcces.
     *
     * @param Sujet Les crit�res du sujet a obtenir
     * @param SujetForm L'ActionForm bean a popul� � partir du sujet obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populateSujetFormShow(CardexAuthenticationSubject subject,
                                     Sujet sujet,
                                     SujetForm sujetForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {
        SujetBusinessDelegate delegate = new SujetBusinessDelegate();
        sujetForm.resetOnglets();
        //log.debug("Sujet trouv�: " + sujet.toString());
        sujetForm.setConfirmationMotPasse(sujetForm.getMotPasse());
        rechercheLiensSujet(subject, sujet, sujetForm, delegate);
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
        log.debug("Param�tres de recherche par d�fault de sujet");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheSujetForm criteresRechercheSujetHtmlForm = (CriteresRechercheSujetForm) form;
            CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();

            // Valeurs par d�faut
            criteresRechercheSujetHtmlForm.init(subject);

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSujetHtmlForm(criteresRechercheSujetHtmlForm,criteresRechercheSujet,subject.getLocale());

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
        log.debug("Param�tres de recherche par d�fault de sujet en mode liaison");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheSujetForm criteresRechercheSujetHtmlForm = (CriteresRechercheSujetForm) form;
            EntiteCardexForm entiteCardexLiaison = null;
            if(criteresRechercheSujetHtmlForm.getEntiteCardexLiaison() != null ){
                entiteCardexLiaison = criteresRechercheSujetHtmlForm.getEntiteCardexLiaison();
            }
            CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();
            DossierHtmlForm dossierHtmlForm = criteresRechercheSujetHtmlForm.getDossier();
            SujetHtmlForm sujetHtmlForm = criteresRechercheSujetHtmlForm.getSujet();
            VehiculeHtmlForm vehiculeHtmlForm = criteresRechercheSujetHtmlForm.getVehicule();
            SocieteHtmlForm societeHtmlForm = criteresRechercheSujetHtmlForm.getSociete();

            criteresRechercheSujetHtmlForm.init(subject);
            criteresRechercheSujetHtmlForm.setDossier(dossierHtmlForm);
            criteresRechercheSujetHtmlForm.setSujet(sujetHtmlForm);
            criteresRechercheSujetHtmlForm.setVehicule(vehiculeHtmlForm);
            criteresRechercheSujetHtmlForm.setSociete(societeHtmlForm);

            // Valeurs par d�faut
            criteresRechercheSujetHtmlForm.init(subject);
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSujetHtmlForm(criteresRechercheSujetHtmlForm,criteresRechercheSujet,subject.getLocale());

            //On remet l'entit� s'il y a lieu, sinon c'est l'�cran de recherche normal qui est affich� � l'�cran.
            criteresRechercheSujetHtmlForm.setEntiteCardexLiaison(entiteCardexLiaison);

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans le menu principal, l'utilisateur a choisi de rechercher un sujet.
     * L'application affiche alors le panneau de recherche des sujets.
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
        log.debug("Recherche par d�fault de sujet");

        ActionMessages errors = new ActionMessages();

        try {
			Cookie[] cookies = request.getCookies();
			log.debug("cookies : " + cookies.length);
			for (Enumeration enumeration=request.getHeaderNames(); enumeration.hasMoreElements();) {
			    String headerName = (String)enumeration.nextElement();
			    log.debug("Name = " + headerName);
			}
			log.debug("Header cookie : " + request.getHeader("cookie"));
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            CriteresRechercheSujetForm criteresRechercheSujetHtmlForm = (CriteresRechercheSujetForm) form;
            CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();

            // Valeurs par d�faut
            criteresRechercheSujetHtmlForm.init(subject);
            //On v�rifie si la recherche doit �tre limit�e aux sujets provisoires
            if(!GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_RESULTAT_SUJETS_ONGLET)){
            	criteresRechercheSujetHtmlForm.setStatut(GlobalConstants.Statut.SUJET_PROVISOIRE);
            }

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSujetHtmlForm(criteresRechercheSujetHtmlForm,criteresRechercheSujet,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            Collection results = delegate.selectDefault(subject,criteresRechercheSujet);

            ajouterResultatSujet(subject, criteresRechercheSujetHtmlForm, results);

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
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de sujet, l'utilisateur a choisi
     * de rechercher un sujet selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des sujets avec les r�sultats de la nouvelle recherche.
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
        log.debug("Recherche de sujet");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate delegate =  new SujetBusinessDelegate();
            CriteresRechercheSujetForm criteresRechercheSujetHtmlForm = (CriteresRechercheSujetForm) form;
            CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();
            criteresRechercheSujetHtmlForm.getListeResultat().vider();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSujetHtmlForm(criteresRechercheSujetHtmlForm, criteresRechercheSujet,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            Collection results = delegate.select(subject,criteresRechercheSujet);
			//V�rification d'un mandat PSU associ� � la recherche d'un sujet par nom
			if(!OracleDAOUtils.isEmpty(criteresRechercheSujet.getNom())){			
				PSUMandatForm psuMandat = new PSUMandatForm();
				psuMandat.setSujetNom(criteresRechercheSujet.getNom().toUpperCase());
				if(!OracleDAOUtils.isEmpty(criteresRechercheSujet.getPrenomPhonetique())){			
					psuMandat.setSujetPrenom(criteresRechercheSujet.getPrenomPhonetique().toUpperCase());
				}
				PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.SUJET, GlobalConstants.TypeAction.RECHERCHE);
			}
            ajouterResultatSujet(subject, criteresRechercheSujetHtmlForm, results);

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

	private void ajouterResultatSujet(CardexAuthenticationSubject subject, CriteresRechercheSujetForm criteresRechercheSujetHtmlForm, Collection results) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des sujets dans le composant d'�tat (ActionForm)
		List currentList = new ArrayList();
		Iterator   it = results.iterator();

		while (it.hasNext()) {
		    Sujet sujet = (Sujet)it.next();
		    SujetForm sujetForm = new SujetForm();
		    sujetForm.init(subject);
		    ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
		    sujetForm.assignerValeurDeListe(subject);
            //Dans le cas d'une relation entre sujets, on doit afficher une liste de r�les diff�rente. Le champ typeLien sert de test dans le JSP.
		    if (criteresRechercheSujetHtmlForm.getEntiteCardexLiaison() instanceof SujetForm){
		    	sujetForm.setTypeLien(GlobalConstants.LienRole.RELATION);
		    }
		    currentList.add(sujetForm);
		}

		criteresRechercheSujetHtmlForm.setListeResultat( currentList );
		criteresRechercheSujetHtmlForm.getListeResultat().assignerTrierDefault(SujetTrieListe.CLE_NOM, false, new SujetTrieListe());
	}

	private void ajouterResultatSujetAudit(CardexAuthenticationSubject subject, CriteresRechercheSujetForm criteresRechercheSujetHtmlForm, ValueListIterator results) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des sujets dans le composant d'�tat (ActionForm)
		Collection list = results.getNextElements(results.getSize());
		List currentList = new ArrayList();
		Iterator   it = list.iterator();

		while (it.hasNext()) {
		    Sujet sujet = (Sujet)it.next();
		    SujetForm sujetForm = new SujetForm();
		    sujetForm.init(subject);
		    ValueObjectMapper.convertSujet(sujet, sujetForm,subject.getLocale());
		    sujetForm.assignerValeurDeListe(subject);
		    currentList.add(sujetForm);
		}

		criteresRechercheSujetHtmlForm.setListeResultatAudit( currentList );
	}

	/**
     * <p>
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de sujet, l'utilisateur a choisi
     * de rechercher un sujet selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des sujets avec les r�sultats de la nouvelle recherche.
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
        log.debug("Recherche de sujet");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate delegate =  new SujetBusinessDelegate();
            CriteresRechercheSujetForm criteresRechercheSujetHtmlForm = (CriteresRechercheSujetForm) form;
            CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();
            criteresRechercheSujetHtmlForm.getListeResultat().vider();
            
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSujetHtmlForm(criteresRechercheSujetHtmlForm, criteresRechercheSujet,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            Collection results = delegate.select(subject,criteresRechercheSujet);

            ajouterResultatSujet(subject, criteresRechercheSujetHtmlForm, results);

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

    public ActionForward selectionnerPhotoGalerie(CardexAuthenticationSubject subject,
	ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request,
	HttpServletResponse response) throws IOException,
	ServletException {
    	log.debug("S�lectionner photo galerie sujet");
		ActionMessages errors = new ActionMessages();

		try {
			SujetBusinessDelegate delegate = new SujetBusinessDelegate();
			
			String cle = (String) request.getParameter("param1");
			String site = (String) request.getParameter("param2");
			
			Sujet sujetVO = new SujetVO();
			PhotoVO photoVO = new PhotoVO();
			SujetForm sujetForm = (SujetForm) form;

			ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujetVO,
					subject.getLocale());
			photoVO.setCle(Long.valueOf(cle).longValue());
			photoVO.setSite(Long.valueOf(site).longValue());

			delegate.selectionnerPhotoGalerie(subject, sujetVO, photoVO);

			sujetForm.resetOnglets();
			rechercheLiensSujet(subject, sujetVO, sujetForm, delegate);
			
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
     * Mise � jour du r�le dans les onglets d'un sujet.
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
        log.debug("Mise � jour de la liaison dans un sujet");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm    sujetForm = (SujetForm) form;
            sujetForm.setLien((String)request.getParameter("lien"));
            sujetForm.setLienSite((String)request.getParameter("lienSite"));
            sujetForm.setRole((String)request.getParameter("role"));
            sujetForm.setCle((String)request.getParameter("cleSujet"));
            sujetForm.setSite((String)request.getParameter("siteSujet"));
            Sujet 	sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetForm,
                    sujet, subject.getLocale());
            delegate.updateLien(subject, sujet);
            populateSujetForm(subject, sujet, sujetForm);
            
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
     * Copie des donn�es d'un sujet � une autre.
     * Cette fonction est en principe temporaire dans le but de faciliter l'�limination des doublons de sujets
     * Avril 2013
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
        log.debug("Copie de donn�es de sujets");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm    sujetForm = (SujetForm) form;
            sujetForm.setCle((String)request.getParameter("cle"));
            sujetForm.setSite((String)request.getParameter("site"));
            String destinataire = (String)request.getParameter("sujetDestinataire").toUpperCase();
            Sujet sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet, subject.getLocale());
            Sujet sujet2 = delegate.rechercheDestinataire(subject, destinataire);
            delegate.copier(subject, Long.valueOf(sujetForm.getCle()), Long.valueOf(sujetForm.getSite()), sujet2.getCle(), sujet2.getSite());
            populateSujetForm(subject, sujet, sujetForm);
            
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
     * Recherche directe d'un sujet � partir du menu principal.
     * Ao�t 2013
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
    public ActionForward rechercheDirecteSujet(CardexAuthenticationSubject subject,
                                        ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws IOException,
                                        ServletException {
        log.debug("Recherche directe de sujets");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm    sujetForm = (SujetForm) form;
            sujetForm.setNumeroFiche((String)request.getParameter("SUJET").toUpperCase());
            Sujet sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet, subject.getLocale());
            sujet = delegate.rechercheDirecte(subject, sujet);
            if(sujet.isPossedeCle()){
            	//Un sujet a �t� trouv�. On inscrit donc une entr�e dans la table des acc�s.
            	delegate.ajoutAcces(subject, sujet);
                populateSujetForm(subject, sujet, sujetForm);
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


