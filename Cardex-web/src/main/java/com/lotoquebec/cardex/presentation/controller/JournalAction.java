/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Journal;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheJournalVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.JournalVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheJournalForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.JournalForm;
import com.lotoquebec.cardex.presentation.model.form.LienForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.JournalTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des journaux.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.27 $, $Date: 2002/04/30 17:47:53 $
 */
public class JournalAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));


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
    public ActionForward refreshRechercheJournal(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh ecran de recherche journal");

        // On vide la liste des journal car elle est plein de RapportJournalForm
        request.getSession().setAttribute(GlobalConstants.RechercheList.JOURNAL_CURRENT_LIST,new ArrayList());
        
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
     */
    public ActionForward refreshJournal(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException {
        log.fine("Refresh �cran de consultation du journal");
        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * le panneau de recherche des journaux.  L'application affiche le panneau de mise � jour.
     * L'utilisateur a pr�alablement saisie les informations  les donn�es relatives � l'identification
     * du journal.
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul> site d'origine (site de l'utilisateur)
     * <ul> utilisateur (Code de l'utilisateur)
     * <ul> Date de d�but (date du jour)
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

        log.fine("Cr�ation d'un nouveau journal");

        ActionMessages errors = new ActionMessages();
        JournalForm journalForm = (JournalForm)form;
        CardexUser user = (CardexUser)subject.getUser();
        
        //Valeurs par d�faut
        journalForm.init();
        journalForm.setNew(true);
        journalForm.setModifiable(true);
        journalForm.setIntervenant(user.getCode());
        journalForm.setEntite(String.valueOf(user.getEntite()));
        journalForm.setSite(String.valueOf(user.getSite()));
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
        journalForm.setDateDebut(currentDate);
        journalForm.setDateCreation(currentDate);
        if(journalForm.getEntite().equals(GlobalConstants.Entite.LOTO_QUEBEC)){
        	journalForm.setNature(String.valueOf(GlobalConstants.Nature.JOURNAL_SECURITE));
        }else{
            if((user.getSite() == Long.valueOf(GlobalConstants.SiteMaisonJeux.LUDOPLEX_QUEBEC) || user.getSite() == Long.valueOf(GlobalConstants.SiteMaisonJeux.LUDOPLEX_TROIS_RIVIERE))
            		&& (user.getSecteur() == Long.valueOf(GlobalConstants.Secteur.AGENT_SECURITE) || 
            			user.getSecteur() == Long.valueOf(GlobalConstants.Secteur.GESTIONNAIRE_SECURITE) ||
            			user.getSecteur() == Long.valueOf(GlobalConstants.Secteur.SUPERVISEUR_SECURITE)))  {
            	journalForm.setNature(String.valueOf(GlobalConstants.Nature.JOURNAL_SECURITE_CASINOS));
            }else{
        	    journalForm.setNature(String.valueOf(GlobalConstants.Nature.JOURNAL));
            }
        }

        return mapping.findForward("success");

    }

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton sauvegarder dans
     * le panneau de cr�ation d'un journal.  Le nouveau journal est enregistr� dans le
     * cardex et l'�cran de mise a jour du journal est affich�.
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

        log.fine("Sauvegarde de la cr�ation d'un nouveau journal");

        ActionMessages errors = new ActionMessages();
        Journal newJournal = new JournalVO();
        JournalForm newJournalForm = (JournalForm) form;
        Dossier     dossier = new DossierVO();
        DossierForm dossierForm = new DossierForm();
        Narration     narration = new NarrationVO();
        NarrationForm narrationForm = new NarrationForm();
        try {
        	verifierToken(request);
            DossierBusinessDelegate journalDelegate =
                new DossierBusinessDelegate();
			//On transf�re les donn�es du journal � un dossier pour sauvegarder les changements.
			dossierForm = transfertJournalDossier(subject, newJournalForm, dossierForm);

            ValueObjectMapper.convertDossierHtmlForm(dossierForm,dossier, subject.getLocale());
            //Num�ro par d�faut pour forcer la cr�ation d'un num�ro unique dans Oracle
            dossier.setNumeroCardex(GlobalConstants.NumeroCardex.DEFAULT);

            log.fine("Journal � cr�er : " + newJournal);
            Dossier newDossier = journalDelegate.create(subject, dossier);
            log.fine("# Cl� de journal retourn� : " + newDossier.getCle());
			//Transfert des donn�es du journal pour la cr�ation de la narration associ�e
			//au dossier nouvellement cr��.
			narrationForm = transfertJournalNarration(subject, newJournalForm, narrationForm);

            ValueObjectMapper.convertNarrationHtmlForm(narrationForm,narration, subject.getLocale());
            narration.setDossier(newDossier);
            journalDelegate.addLienNarrationApprouve(subject,newDossier,narration);

            //Journal cr�� � partir du sujet ou de la soci�t� (onglet Dossier). On fait un lien automatique avec le sujet ou la soci�t�.
            if (newJournalForm.getEntiteCardexLiaison() != null){
            	newJournalForm.setCle(Long.toString(newDossier.getCle()));
            	newJournalForm.setSite(Long.toString(newDossier.getSite()));
            	assignerLienRole(request, newJournalForm);
            	if(newJournalForm.getEntiteCardexLiaison() instanceof SujetForm){
            		return mapping.findForward("successLiaisonSujetJournal");
            	}
            	if(newJournalForm.getEntiteCardexLiaison() instanceof SocieteForm){
            		return mapping.findForward("successLiaisonSocieteJournal");
            	}
            }
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
			String ancestor = bre.getAncestor().toString();
			ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp�cial d'erreur. Durant la t�che qui reconstruit l'index des narrations
			//les sauvegardes �chouent. Dans ce cas, un message d'erreur est retourn� et la
			//narration est perdue. Le test suivant permet de d�tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration � l'�cran avec
			//un message plus appropri�, sans perte de donn�es. 
			if((ancestor.indexOf("ORA-29861") > -1) || (ancestor.indexOf("ORA-29875") > -1) || (ancestor.indexOf("ORA-29877") > -1) || (ancestor.indexOf("DRG-10599") > -1)){
				errors.add(Globals.ERROR_KEY, new ActionMessage("cardex_erreur_narration"));
				saveErrors(request, errors);
				return mapping.findForward("erreur");
			}else{
				handleBusinessResourceException(bre, errors, request);
				return mapping.findForward("error");
			}
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }

    }

    /**
     * <p>
     * Cet �v�nement est appel� au lieu de la m�thode showAcces afin d'�viter qu'un enregistrement
     * ne soit inscrit dans la table AC_ACCES.  En mode Web, chaque retour � un journal (par
     * exemple, apr�s avoir inscrit une narration) se traduit par une relecture du journal.
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

        log.fine("Acc�s � un journal");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate journalDelegate = new DossierBusinessDelegate();
            JournalForm  journalForm = (JournalForm) form;
            Journal      journal     = new JournalVO();
            CardexUser user = (CardexUser)subject.getUser();

            log.fine("Journal recherch�: " + journalForm.toString());

			  ValueObjectMapper.convertJournalHtmlForm(journalForm, journal,subject.getLocale());
			
			  journal = journalDelegate.findJournal(subject, journal);
			  log.fine("Journal trouv�: " + journal.toString());
			  ValueObjectMapper.convertJournal(journal, journalForm,subject.getLocale());
			  //La ligne suivante corrige un bogue pour le champ Origine lors de la consultation d'un journal des enqu�teurs de Loto-Qu�bec.
			  //La vraie mani�re de corriger serait de retourner l'entit� avec le journal, ce qui n'a pas �t� fait, car il s'agit d'une
			  //correction de derni�re minute avec trop peu de temps pour des tests. � revoir, bien que cette ligne soit sans cons�quences, 
			  //le seul risque �tant que le champ Origine soit vide lors d'une consultation. Pour une modification, il sera toujours pr�sent. 
	          journalForm.setEntite(String.valueOf(user.getEntite()));
			
			  return mapping.findForward("success");


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
     * le panneau de mise � jour d'un journal.  Les modifiactions du journal est enregistr� dans le
     * cardex et l'�cran de mise a jour du journal est affich�.
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

        log.fine("Mise � jour d'un journal");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
            DossierBusinessDelegate journalDelegate =
                new DossierBusinessDelegate();
            JournalForm journalForm = (JournalForm)form;
            Dossier     dossier = new DossierVO();
            DossierForm dossierForm = new DossierForm();
	        Narration     narration = new NarrationVO();
    	    NarrationForm narrationForm = new NarrationForm();
			//On transf�re les donn�es du journal � un dossier pour sauvegarder les changements.
			dossierForm = transfertJournalDossier(subject, journalForm, dossierForm);
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            log.fine("Mise � jour du journal: " + dossier.toString());
            journalDelegate.update(subject, dossier);
			//Transfert des donn�es du journal pour la mise � jour de la narration associ�e
			//au dossier.
			narrationForm = transfertJournalNarration(subject, journalForm, narrationForm);

            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration, subject.getLocale());
            journalDelegate.updateLienJournal(subject,narration);
            
            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
			String ancestor = bre.getAncestor().toString();
			ExceptionHandler.getInstance().handle( bre.getAncestor() );
			//Cas sp�cial d'erreur. Durant la t�che qui reconstruit l'index des narrations
			//les sauvegardes �chouent. Dans ce cas, un message d'erreur est retourn� et la
			//narration est perdue. Le test suivant permet de d�tecter si l'erreur survient
			//lors de la reconstruction et, si oui, de retourner la narration � l'�cran avec
			//un message plus appropri�, sans perte de donn�es. 
			if((ancestor.indexOf("ORA-29861") > -1) || (ancestor.indexOf("ORA-29875") > -1) || (ancestor.indexOf("ORA-29877") > -1) || (ancestor.indexOf("DRG-10599") > -1)){
				errors.add(Globals.ERROR_KEY, new ActionMessage("cardex_erreur_narration"));
				saveErrors(request, errors);
				return mapping.findForward("erreur");
			}else{
				handleBusinessResourceException(bre, errors, request);
				return mapping.findForward("error");
			}
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }

    }

    /**
     * <p>
     * Transf�re les informations du journal � un dossier pour la sauvegarde ou
     * la mise � jour.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private DossierForm transfertJournalDossier(CardexAuthenticationSubject subject,
                                     JournalForm journalForm,
                                     DossierForm dossierForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {

        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
                                     	
                dossierForm.setCle(journalForm.getCle());
                dossierForm.setSite(journalForm.getSite());
                dossierForm.setNumeroCardex(journalForm.getNumeroDossier());
                dossierForm.setType(journalForm.getType());
                dossierForm.setCategorie(journalForm.getCategorie());
                dossierForm.setNumeroDossier(journalForm.getNumeroIncident());
                dossierForm.setSiteOrigine(journalForm.getSite());
                dossierForm.setStatut(GlobalConstants.Statut.DOSSIER_INACTIF);
                //R10-0134 : les entr�es de journal sont d�sormais consid�r�es comme non-fond�es.
                dossierForm.setFonde(GlobalConstants.Fonde.NON);
                dossierForm.setDateDebut(journalForm.getDateDebut());
                dossierForm.setDateFin(journalForm.getDateFin());
                dossierForm.setReference1(journalForm.getNumeroEmploye());
                dossierForm.setReference2(journalForm.getReference2());
                dossierForm.setReference3(journalForm.getReference3());
                dossierForm.setTypeVideo("");
                dossierForm.setReferenceVideo(journalForm.getReferenceVideo());
                dossierForm.setIntervenant(journalForm.getIntervenant());
                dossierForm.setOrigine(journalForm.getOrigine());
                dossierForm.setSeverite("");
                dossierForm.setHierarchie(String.valueOf(privilege.getNiveauAuthorite()));
                dossierForm.setConfidentialite(String.valueOf(privilege.getNiveauConfidentialite()));
                dossierForm.setEndroit(journalForm.getEndroit());
                dossierForm.setLocalisation(journalForm.getLocalisation());
                dossierForm.setDescriptif(journalForm.getDescriptif());
                dossierForm.setPeriode("");
                dossierForm.setMotPasse("");
                dossierForm.setDuree("");
                dossierForm.setDateRapportee(journalForm.getDateDebut());
                dossierForm.setClasse("");
                dossierForm.setRace("");
                dossierForm.setDateAssignation(journalForm.getDateDebut());
                dossierForm.setDateEvenement(journalForm.getDateDebut());
                dossierForm.setReference4("");
                dossierForm.setReference5("");
                dossierForm.setReference6("");
                dossierForm.setReference7("");
                dossierForm.setFondeDescription("");
            return dossierForm;
	}

    /**
     * <p>
     * Transf�re les informations du journal � une narration pour la sauvegarde ou
     * la mise � jour.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private NarrationForm transfertJournalNarration(CardexAuthenticationSubject subject,
                                     JournalForm journalForm,
                                     NarrationForm narrationForm) throws BusinessResourceException,
                                     BusinessException,
                                     ValueObjectMapperException {

        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
                                     	
                narrationForm.setCle(journalForm.getCleNarration());
                narrationForm.setSite(journalForm.getSiteNarration());
                narrationForm.setApprobateur(journalForm.getIntervenant());
                narrationForm.setAutoriteApprobateur(String.valueOf(privilege.getNiveauAuthorite()));
                narrationForm.setAutoriteCreateur(String.valueOf(privilege.getNiveauAuthorite()));
                narrationForm.setAutoriteNarration(String.valueOf(privilege.getNiveauAuthorite()));
                narrationForm.setConfidentialiteApprobateur(String.valueOf(privilege.getNiveauConfidentialite()));
                narrationForm.setConfidentialiteCreateur(String.valueOf(privilege.getNiveauConfidentialite()));
                narrationForm.setConfidentialiteNarration(String.valueOf(privilege.getNiveauConfidentialite()));
                narrationForm.setCreateur(String.valueOf(journalForm.getIntervenant()));
                narrationForm.setDateApprobation(journalForm.getDateCreation());
                narrationForm.setDateCreation(journalForm.getDateCreation());
                narrationForm.setLien(journalForm.getCle());
                narrationForm.setLienSite(journalForm.getSite());
                narrationForm.setMontant("");
                String narrationFormat = "<html><head> </head> <body><p>" +
                	journalForm.getDescription() + "</p></body></html>";
                narrationForm.setNarrationAvecFormat(narrationFormat);
                narrationForm.setNarrationSansFormat(journalForm.getDescription());
                narrationForm.setRapporteur(journalForm.getIntervenant());
                narrationForm.setTempsConsacre(journalForm.getDuree());
            return narrationForm;
	}

    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Groupe (selon la s�lection de l'�cran principal)
     * <ul>Intervenant (selon la s�lection de l'�cran principal)
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
        log.fine("Param�tres de recherche par d�fault de journal");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheJournalForm criteresRechercheJournalHtmlForm = (CriteresRechercheJournalForm) form;
            CriteresRechercheJournalVO criteresRechercheJournal = new CriteresRechercheJournalVO();
            
            criteresRechercheJournalHtmlForm.init(subject);
            
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheJournalHtmlForm(criteresRechercheJournalHtmlForm,criteresRechercheJournal,subject.getLocale());

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans le menu principal, l'utilisateur a choisi de rechercher un journal
     * pour une cat�gorie et une nature donn�e. L'application affiche alors le panneau de
     * recherche des journaux.
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
        log.fine("Recherche par d�fault de journal");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            CriteresRechercheJournalForm criteresRechercheJournalHtmlForm = (CriteresRechercheJournalForm) form;
            //CardexUser user = (CardexUser) subject.getUser();
            CriteresRechercheJournalVO criteresRechercheJournal = new CriteresRechercheJournalVO();

            // Valeurs par d�faut
            criteresRechercheJournalHtmlForm.init(subject);

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheJournalHtmlForm(criteresRechercheJournalHtmlForm,criteresRechercheJournal,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Journal> results = delegate.selectDefaultJournal(subject,criteresRechercheJournal);

            assignerResultatJournal(subject, criteresRechercheJournalHtmlForm, results);

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
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de journal, l'utilisateur a choisi
     * de rechercher un journal selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des journaux avec les r�sultats de la nouvelle recherche.
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
        log.fine("Recherche de journal");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate =  new DossierBusinessDelegate();
            CriteresRechercheJournalForm criteresRechercheJournalHtmlForm = (CriteresRechercheJournalForm) form;
            CriteresRechercheJournalVO criteresRechercheJournal = new CriteresRechercheJournalVO();
            criteresRechercheJournalHtmlForm.getListeResultat().vider();
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheJournalHtmlForm(criteresRechercheJournalHtmlForm, criteresRechercheJournal,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Journal> results = delegate.selectJournal(subject,criteresRechercheJournal);

            assignerResultatJournal(subject, criteresRechercheJournalHtmlForm, results);

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
    
	private void assignerResultatJournal(CardexAuthenticationSubject subject, CriteresRechercheJournalForm criteresRechercheJournalHtmlForm, List list) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
		// Ajout des journaux dans le composant d'�tat (ActionForm)
		List currentList = new ArrayList();
		Iterator   it = list.iterator();

		while (it.hasNext()) {
		    Journal journal = (Journal)it.next();
		    JournalForm journalForm = new JournalForm();
		    ValueObjectMapper.convertJournal(journal, journalForm,subject.getLocale());
		    journalForm.assignerValeurDeListe( subject );
		    currentList.add(journalForm);
		}

		criteresRechercheJournalHtmlForm.setListeResultat( currentList );
		criteresRechercheJournalHtmlForm.getListeResultat().assignerTrierDefault(JournalTrieListe.CLE_NUMERO_CARDEX, true, new JournalTrieListe());
	}

    /**
     * <p>
     * Cet �v�nement survient lorsque l'utilisateur clique sur le bouton ajouter dans
     * l'onglet Dossiers d'un sujet ou d'une soci�t�. L'action sert � relier directement une entr�e de journal
     * au sujet ou � la soci�t� affich� � l'�cran. Le bouton sert aux enqu�teurs de Loto-Qu�bec qui utilisent le journal. Ce bouton
     * a pour but de leur faciliter la t�che puisque leurs dossiers, y compris le journal, sont
     * pratiquement toujours reli�s � un sujet ou � une soci�t�. 
     * Pour les Maisons de jeux, le journal est r�serv� aux techniciens de surveillance. Il est disponible � partir du menu principal.
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

		log.fine("Cr�ation d'un lien entre une entr�e de journal et un sujet ou une societe");

        JournalForm journalForm = new JournalForm();
        CardexUser user = (CardexUser)subject.getUser();

		//Valeurs par d�faut
        journalForm.reset(mapping, request);
        if (form instanceof SujetForm) {
        	journalForm.setEntiteCardexLiaison((SujetForm) form );
        }
        if (form instanceof SocieteForm) {
        	journalForm.setEntiteCardexLiaison((SocieteForm) form );
        }
        journalForm.setNew(true);
        journalForm.setModifiable(true);
        journalForm.setIntervenant(user.getCode());
        journalForm.setEntite(GlobalConstants.Entite.LOTO_QUEBEC);
        journalForm.setSite(String.valueOf(user.getSite()));
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
        journalForm.setDateDebut(currentDate);
        journalForm.setDateCreation(currentDate);
        journalForm.setNature(String.valueOf(GlobalConstants.Nature.JOURNAL_ENQUETES));
        request.getSession().setAttribute("journal",journalForm);

        return mapping.findForward("success");
	}

    private void assignerLienRole(HttpServletRequest request, JournalForm journalForm) {
		LienForm lienForm = new LienForm();
		lienForm.assignerSource(journalForm);
		lienForm.assignerDestination(journalForm.getEntiteCardexLiaison());
		request.getSession().setAttribute("lien", lienForm);
	}

}


