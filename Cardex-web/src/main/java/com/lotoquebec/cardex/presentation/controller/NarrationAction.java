/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.delegate.FabriqueBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.NarrationBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheNarrationVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheNarrationForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.ApprobationTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.NarrationTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringHelper;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.11 $, $Date: 2002/03/25 20:42:33 $
 */
public class NarrationAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

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
     * @throws ValueObjectMapperException 
     * @throws BusinessException 
     * @throws BusinessResourceException 
     */
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException, ValueObjectMapperException, BusinessResourceException, BusinessException {
        log.debug("Cr�ation d'une nouvelle narration");
        NarrationBusinessDelegate narrationBusinessDelegate = new NarrationBusinessDelegate();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        NarrationForm narrationForm = new NarrationForm();
        List<Long> exclureGabarit = narrationBusinessDelegate.creerExclureGabaritNarration(subject, null);
        narrationForm.init(exclureGabarit);
        narrationForm.setModifiable(true);
        narrationForm.setNouveau(true);
        narrationForm.setDateCreation(currentDate);
        
        if (form instanceof DossierForm) {
			log.debug("Cr�ation d'une narration li�e au dossier: " + form);
			DossierForm dossierForm = (DossierForm)form;
			exclureGabarit = narrationBusinessDelegate.creerExclureGabaritNarration(subject, new DossierVO(Long.valueOf(dossierForm.getCle()), Long.valueOf(dossierForm.getSite())));
			narrationForm.assignerFiltreGabarit(exclureGabarit);
			narrationForm.setDossier((DossierForm)form);
			narrationForm.setGenreLiaison( GlobalConstants.GenreFichier.DOSSIER );
        }else if (form instanceof SujetForm) {
          log.debug("Cr�ation d'une narration li�e au sujet: " + form);
          narrationForm.setSujet((SujetForm)form);
          narrationForm.setGenreLiaison( GlobalConstants.GenreFichier.SUJET );
        }else if (form instanceof VehiculeForm) {
          log.debug("Cr�ation d'une narration li�e au vehicule: " + form);
          narrationForm.setVehicule((VehiculeForm)form);
          narrationForm.setGenreLiaison( GlobalConstants.GenreFichier.VEHICULE );
        }else if (form instanceof SocieteForm) {
          log.debug("Cr�ation d'une narration li�e a la societe: " + form);
          narrationForm.setSociete((SocieteForm)form);
          narrationForm.setGenreLiaison( GlobalConstants.GenreFichier.SOCIETE );
        }
        
        if (form instanceof EntiteCardexForm){
        	narrationForm.setLien( ((EntiteCardexForm)form).getCle() );
        	narrationForm.setLienSite( ((EntiteCardexForm)form).getSite() );
        }
        
    	NarrationVO narrationVO = new NarrationVO();
    	ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narrationVO, subject.getLocale());        
    	Narration narrationTemporaire = FabriqueBusinessDelegate.getNarrationBusinessDelegate().obtenirNarrationTemporaire(subject, narrationVO);
        
    	if (StringUtils.isNotEmpty( narrationTemporaire.getNarrationTemporaire() )){
    		narrationForm.setNarrationTemporaire( narrationTemporaire.getNarrationTemporaire() );
    		narrationForm.setPremiereNarrationTemporaire(false);
    	}
        
        //Valeur par d�faut
        narrationForm.setAutoriteCreateur(Long.toString(privilege.getNiveauAuthorite()));
        narrationForm.setAutoriteNarration(Long.toString(privilege.getNiveauAuthorite()));
        narrationForm.setConfidentialiteCreateur(Long.toString(privilege.getNiveauConfidentialite()));
        narrationForm.setConfidentialiteNarration(Long.toString(privilege.getNiveauConfidentialite()));
        narrationForm.setRapporteur(user.getCode());
        narrationForm.setCreateur(user.getCode());
        log.debug("Narration : " + narrationForm);
        request.getSession().setAttribute("narration",narrationForm);
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
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Acc�s � une narration");

        ActionMessages errors = new ActionMessages();

        try {
            NarrationBusinessDelegate delegate = new NarrationBusinessDelegate();
            NarrationForm narrationForm = (NarrationForm)form;
            Narration narration = new NarrationVO();
            ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
            narration = delegate.find(subject,narration);
            List<Long> exclureGabarit = delegate.modifierExclureGabaritNarration(subject, narration);
            narrationForm.init(exclureGabarit);
            
            //On remet les valeurs de r�f�rence
            narration.setReference1(narrationForm.getReference1());
            narration.setReference2(narrationForm.getReference2());
            narration.setReference3(narrationForm.getReference3());
            
            ValueObjectMapper.convertNarration(narration, narrationForm,
                    subject.getLocale());
            Narration narrationTemporaire = FabriqueBusinessDelegate.getNarrationBusinessDelegate().obtenirNarrationTemporaire(subject, narration);
            
        	if (StringUtils.isNotEmpty( narrationTemporaire.getNarrationTemporaire() )){
        		narrationForm.setPremiereNarrationTemporaire(false);
        		
        		if (isAssignerNarrationTemporaire(narration, narrationTemporaire))
        			narrationForm.setNarrationTemporaire( narrationTemporaire.getNarrationTemporaire() );
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

    private boolean isAssignerNarrationTemporaire(Narration narration, Narration narrationTemporaire){
		
		// C'est une nouvelle narration
		if (narration.getCle() == 0)
			return true;
		
		// Si la narration temporaire est plus vieille que la narration sauvegarder, il ne faut pas afficher la narration temporaire.		
		if (narration.getDateCreation() != null && narrationTemporaire.getDateChangement() != null 
		&& narration.getDateCreation().after( narrationTemporaire.getDateChangement() ))
			return false;
		 
		if (narration.getDateModification() != null && narrationTemporaire.getDateChangement() != null 
		&& narration.getDateModification().after( narrationTemporaire.getDateChangement() ))
			return false;
		
		return true;
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
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward refreshRechercheNarration(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.debug("Refresh de recherche narration");

        return mapping.findForward("success");
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
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Recherche par d�fault de narration");

        CriteresRechercheNarrationForm criteresRechercheNarrationHtmlForm = (CriteresRechercheNarrationForm) form;
        criteresRechercheNarrationHtmlForm.init(subject);

        return mapping.findForward("success");
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
    public ActionForward searchDefaultApprobation(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Recherche par d�fault de narration en mode liaison");

    	CriteresRechercheNarrationForm criteresRechercheNarrationHtmlForm = (CriteresRechercheNarrationForm) form;
        criteresRechercheNarrationHtmlForm.init(subject);

        return mapping.findForward("success");
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
        log.debug("Param�tres de recherche par d�fault de narration");

        CriteresRechercheNarrationForm criteresRechercheNarrationHtmlForm = (CriteresRechercheNarrationForm) form;

        criteresRechercheNarrationHtmlForm.init(subject);

        return mapping.findForward("success");
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
    public ActionForward resetSearchDefaultApprobation(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Param�tres de recherche par d�fault de narration en mode liaison");
        CriteresRechercheNarrationForm criteresRechercheNarrationHtmlForm = (CriteresRechercheNarrationForm) form;

        criteresRechercheNarrationHtmlForm.init(subject);
        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de narration, l'utilisateur a choisi
     * de rechercher un narration selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des narrations avec les r�sultats de la nouvelle recherche.
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
        log.debug("Recherche de narration");

        ActionMessages errors = new ActionMessages();

        try {
            NarrationBusinessDelegate delegate =  new NarrationBusinessDelegate();
            CriteresRechercheNarrationForm criteresRechercheNarrationHtmlForm = (CriteresRechercheNarrationForm) form;
            criteresRechercheNarrationHtmlForm.getListeResultat().vider();
            CriteresRechercheNarrationVO criteresRechercheNarration = new CriteresRechercheNarrationVO();
            criteresRechercheNarrationHtmlForm.getListeResultat().vider();
            
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheNarrationHtmlForm(criteresRechercheNarrationHtmlForm, criteresRechercheNarration,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            Collection list = delegate.selectNarration(subject,criteresRechercheNarration);

            // Ajout des narrations dans le composant d'�tat (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = list.iterator();
//Marquer les mots cl�s : <b style="color:black;background-color:#ff9999">mot-cl�</b>
            while (it.hasNext()) {
                Narration narration = (Narration)it.next();
                NarrationForm narrationForm = new NarrationForm();
                DossierForm dossierForm = new DossierForm();
                ValueObjectMapper.convertNarration(narration, narrationForm,subject.getLocale());
                ValueObjectMapper.convertDossier(narration.getDossier(),dossierForm,subject.getLocale());
                dossierForm.assignerValeurDeListe(subject);
                narrationForm.setDossier(dossierForm);
                narrationForm.assignerValeurDeListe(subject);
                //On marque les mots cl�s dans le texte pour aider l'utilisateur dans sa recherche.
                //Le 1er mot-c� est marqu� en rouge, le 2e en bleu et le 3e en jaune.
                if(!criteresRechercheNarrationHtmlForm.getMotCle1().equals("")){
                	narrationForm.setNarrationSansFormat(marquerMotCle(narrationForm.getNarrationSansFormat(), criteresRechercheNarrationHtmlForm.getMotCle1(), "#ff9999" ));
                }
                if(!criteresRechercheNarrationHtmlForm.getMotCle2().equals("")){
                	narrationForm.setNarrationSansFormat(marquerMotCle(narrationForm.getNarrationSansFormat(), criteresRechercheNarrationHtmlForm.getMotCle2(), "#3399ff" ));
                }
                if(!criteresRechercheNarrationHtmlForm.getMotCle3().equals("")){
                	narrationForm.setNarrationSansFormat(marquerMotCle(narrationForm.getNarrationSansFormat(), criteresRechercheNarrationHtmlForm.getMotCle3(), "#ffff00" ));
                }
                currentList.add(narrationForm);
            }

            criteresRechercheNarrationHtmlForm.setListeResultat( currentList );
            criteresRechercheNarrationHtmlForm.getListeResultat().assignerTrierDefault(NarrationTrieListe.CLE_DATE_CREATION, true, new NarrationTrieListe());


            // Stockage des donn�es de r�f�rence concernant le contenu des liste d�roulante

			//V�rification d'un mandat PSU associ� � une recherche de narrations
			PSUMandatForm psuMandat = new PSUMandatForm();
			psuMandat.setMotCle1(criteresRechercheNarrationHtmlForm.getMotCle1());
			psuMandat.setMotCle2(criteresRechercheNarrationHtmlForm.getMotCle2());
			psuMandat.setMotCle3(criteresRechercheNarrationHtmlForm.getMotCle3());
			PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.NARRATION, GlobalConstants.TypeAction.RECHERCHE);
			
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
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de narration, l'utilisateur a choisi
     * de rechercher un narration selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des narrations avec les r�sultats de la nouvelle recherche.
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
    public ActionForward searchApprobation(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Recherche des approbations");

        ActionMessages errors = new ActionMessages();

        try {
            NarrationBusinessDelegate delegate =  new NarrationBusinessDelegate();
            CriteresRechercheNarrationForm criteresRechercheNarrationHtmlForm = (CriteresRechercheNarrationForm) form;
            CriteresRechercheNarrationVO criteresRechercheNarration = new CriteresRechercheNarrationVO();
            ValueListIterator results;
            criteresRechercheNarrationHtmlForm.getListeResultat().vider();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheNarrationHtmlForm(criteresRechercheNarrationHtmlForm, criteresRechercheNarration,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            Collection listNarration = delegate.selectApprobation(subject,criteresRechercheNarration);

            // Ajout des narrations dans le composant d'�tat (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = listNarration.iterator();

            while (it.hasNext()) {
                Narration narration = (Narration)it.next();
                NarrationForm narrationForm = new NarrationForm();
                DossierForm dossierForm = new DossierForm();
                ValueObjectMapper.convertNarration(narration, narrationForm,subject.getLocale());
                ValueObjectMapper.convertDossier(narration.getDossier(),dossierForm,subject.getLocale());
                dossierForm.assignerValeurDeListe( subject );
                narrationForm.setDossier(dossierForm);
                narrationForm.assignerValeurDeListe( subject );
                currentList.add(narrationForm);
            }

            criteresRechercheNarrationHtmlForm.setListeResultat( currentList );
            criteresRechercheNarrationHtmlForm.getListeResultat().assignerTrierDefault(ApprobationTrieListe.CLE_DATE_CREATION, true, new ApprobationTrieListe());
            
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
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * Routine pour les mots-cl�s dans le texte pour faciliter le rep�rage de dossiers aux utilisateurs
     * Date de cr�ation : (2007-12-13)
     * @author Fran�ois Gu�rin
     * @return String : la narration modifi�e.
     */
    private String marquerMotCle(String texte, String motCle, String couleur){
    	
    	return StringHelper.encadrer(texte, motCle, "<b style='color:black;background-color:" + couleur + "' >", "</b>");
    }

    public ActionForward sauvegardeNarrationTemporaire(CardexAuthenticationSubject subject,
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            ServletException, ValueObjectMapperException, BusinessResourceException, BusinessException {
    	log.debug("Sauvegarde narration temporaire");
    	NarrationForm narrationForm = (NarrationForm) form;
    	NarrationVO narrationVO = new NarrationVO();
    	verifierToken(request);
    	
		ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narrationVO, subject.getLocale());
		
		if (narrationForm.isPremiereNarrationTemporaire())
			FabriqueBusinessDelegate.getNarrationBusinessDelegate().insertionNarrationTemporaire(subject, narrationVO);
		else
			FabriqueBusinessDelegate.getNarrationBusinessDelegate().sauvegarderNarrationTemporaire(subject, narrationVO);

    	return mapping.findForward("success");
    }

}

