package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Reçois et traite les informations reçu via l'upload d'un fichier afin de le
 * persister sur disque.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/18 21:47:35 $
 * @see com.lotoquebec.cardex.presentation.controller.AbstractAction
 */
public class PhotoAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    
    public ActionForward updateImage(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.fine("Mise à jour d'un dossier");
        
    	return mapping.findForward("success");
    }
    
    
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
        log.fine("Création d'une nouvelle photo");

        //Valeur par défaut pour l'insertion
        PhotoForm photoForm = (PhotoForm)form;
        photoForm.setTypeMultimedia(GlobalConstants.TypeMutliMedia.PHOTO);

        log.fine("Photo : " + photoForm);
        return mapping.findForward("success");
    }

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
    public ActionForward createPieceJointe(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Création d'une nouvelle pièce jointe");

        //Valeur par défaut pour l'insertion
        PhotoForm photoForm = (PhotoForm)form;
        photoForm.setTypeMultimedia(GlobalConstants.TypeMutliMedia.DOCUMENT_ANNEXE);

        log.fine("Photo : " + photoForm);
        return mapping.findForward("success");
    }

}