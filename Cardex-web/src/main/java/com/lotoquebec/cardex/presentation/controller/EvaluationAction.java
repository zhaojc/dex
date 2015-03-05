/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.FrequenceVisites;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.delegate.EvaluationBusinessDelegate;
import com.lotoquebec.cardex.business.vo.EvaluationVO;
import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.EvaluationForm;
import com.lotoquebec.cardex.presentation.model.form.FrequenceVisitesForm;
import com.lotoquebec.cardex.presentation.model.form.MiseEvaluationForm;
import com.lotoquebec.cardex.presentation.model.util.MiseEvaluationComparator;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation gestion des évaluations du comité de vigilance.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:08 $
 */
public class EvaluationAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * <p>
     * <p>
     * Par défaut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Création d'une nouvelle évaluation");
        ActionErrors errors = new ActionErrors();
        CardexUser user = (CardexUser)subject.getUser();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
        EvaluationForm evaluationForm = new EvaluationForm();
        try{
	        if (form instanceof DossierForm) {
	          log.fine("Création d'une évaluation liée au dossier: " + form);
	          DossierForm dossierForm = (DossierForm)form;
	          evaluationForm.setLien(dossierForm.getCle());
	          evaluationForm.setLienSite(dossierForm.getSite());
	          evaluationForm.setEntite(dossierForm.getEntite());
	          Evaluation evaluation = new EvaluationVO();
	          ValueObjectMapper.convertEvaluationHtmlForm(evaluationForm, evaluation, subject.getLocale());
	          evaluationForm.initDoubleListeEtat(subject, null);
	          evaluationForm.initDoubleListePropos(subject, null);
	        }
	
	        //Valeur par défaut
	        evaluationForm.setCreateur(user.getCode());
	        evaluationForm.setDateCreation(currentDate);
	        evaluationForm.setModifiable(true);
	
	        log.fine("Évaluation : " + evaluationForm);
	        request.getSession().setAttribute("evaluation", evaluationForm);
	
	        return mapping.findForward("success");
	    } catch (BusinessResourceException bre) {
	        handleBusinessResourceException(bre, errors, request);
	        return mapping.findForward("error");
	    } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }


    /**
     * <p>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Accès à une évaluation");
        ActionMessages errors = new ActionMessages();

        try {
            EvaluationBusinessDelegate delegate = new EvaluationBusinessDelegate();
            EvaluationForm evaluationForm = (EvaluationForm)form;
            DossierForm dossierForm = (DossierForm) request.getSession().getAttribute("dossier");
            Evaluation evaluation = new EvaluationVO();
            ValueObjectMapper.convertEvaluationHtmlForm(evaluationForm, evaluation, subject.getLocale());
            evaluation = delegate.find(subject,evaluation);
            
            evaluationForm.init();
            ValueObjectMapper.convertEvaluation(subject, evaluation, evaluationForm, subject.getLocale());
            evaluationForm.setEntite(dossierForm.getEntite());
            List<MiseEvaluationVO> misesEvaluation = delegate.findLiensMisesEvaluation(subject, evaluation);
            
            for(MiseEvaluationVO miseEvaluationVO:misesEvaluation){
            	MiseEvaluationForm miseEvaluationForm = new MiseEvaluationForm();
            	ValueObjectMapper.convert(miseEvaluationVO, miseEvaluationForm, request.getLocale());
            	evaluationForm.getMisesEvaluation().add(miseEvaluationForm);
            	miseEvaluationForm.getJeuxForm().setEntite( dossierForm.getEntite() );
            	
    	        Jeux jeux = delegate.findLiensJeux(subject, miseEvaluationVO);
    	        miseEvaluationForm.getJeuxForm().initDoubleListe(subject, jeux.getJeuxChoisis());
            	
    	        List<FrequenceVisites> liensFrequenceVisites = delegate.findLiensFrequenceVisites(subject, miseEvaluationVO);

    	        for (FrequenceVisites frequenceVisites:liensFrequenceVisites){
    	        	FrequenceVisitesForm frequenceVisitesForm = new FrequenceVisitesForm();
    	        	ValueObjectMapper.convert(frequenceVisites, frequenceVisitesForm);
                    frequenceVisitesForm.setAnnee(StringUtils.substring(frequenceVisitesForm.getPeriode(),0,4));
                    frequenceVisitesForm.setMois(StringUtils.substring(frequenceVisitesForm.getPeriode(),5,8));
                    miseEvaluationForm.getFrequencesVisites().add(frequenceVisitesForm);
    	        }
            }
            Collections.sort(evaluationForm.getMisesEvaluation(), new MiseEvaluationComparator());
	        List etats = delegate.findLiensEtats(subject,evaluation);
            evaluationForm.initDoubleListeEtat(subject, etats);
	        List propos = delegate.findLiensPropos(subject,evaluation);
            evaluationForm.initDoubleListePropos(subject, propos);

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
     * Ajout d'une fréquence de visites dans l'écran d'évaluation
     * 
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward ajouterFrequence(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.fine("Ajout d'une nouvelle fréquence de visite");
        EvaluationForm evaluationForm = (EvaluationForm)form;
        FrequenceVisitesForm frequenceVisites = new FrequenceVisitesForm();
        int indexMise = Integer.valueOf(evaluationForm.getIndexMise());
        evaluationForm.getMisesEvaluation().get( indexMise ).getFrequencesVisites().add(frequenceVisites);

        return mapping.findForward("success");
    }
    /**
     * Retrait d'une fréquence de visites dans l'écran d'évaluation
     * 
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward retirerFrequence(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.fine("Retrait d'une nouvelle fréquence de visite");
        EvaluationForm evaluationForm = (EvaluationForm)form;
        int indexMise = Integer.valueOf(evaluationForm.getIndexMise());
        int indexFrequenceVisites =  Integer.valueOf(evaluationForm.getIndexFrequenceVisites());
        evaluationForm.getMisesEvaluation().get( indexMise ).getFrequencesVisites().remove( indexFrequenceVisites );
        
        return mapping.findForward("success");
    }

    public ActionForward ajouterMiseEvaluation(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException, BusinessResourceException {
		log.fine("Ajout d'une nouvelle mise");
		EvaluationForm evaluationForm = (EvaluationForm)form;
		MiseEvaluationForm miseEvaluationForm = new MiseEvaluationForm();
		miseEvaluationForm.getJeuxForm().setEntite(evaluationForm.getEntite());
		miseEvaluationForm.getJeuxForm().setTypeJeu( evaluationForm.getTypeJeu() );
		miseEvaluationForm.getJeuxForm().initDoubleListe(subject, new HashSet<String>());
        //Création d'une liste vide pour les fréquences de visite
        miseEvaluationForm.getFrequencesVisites().add( new FrequenceVisitesForm() );
		evaluationForm.getMisesEvaluation().add(miseEvaluationForm);
		Collections.sort(evaluationForm.getMisesEvaluation(), new MiseEvaluationComparator());
		
		return mapping.findForward("success");
	}
			    
    public ActionForward retirerMiseEvaluation(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Retrait d'une nouvelle mise");
		EvaluationForm evaluationForm = (EvaluationForm) form;
		int indexMise = Integer.valueOf(evaluationForm.getIndexMise());
		evaluationForm.getMisesEvaluation().remove( indexMise );

		return mapping.findForward("success");
	}
}

