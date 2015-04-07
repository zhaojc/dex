package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Re�ois et traite les informations re�u via l'upload d'un fichier afin de le
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
        LoggerFactory.getLogger((this.getClass()));

    
    public ActionForward updateImage(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.debug("Mise � jour d'un dossier");
        
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
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Cr�ation d'une nouvelle photo");

        //Valeur par d�faut pour l'insertion
        PhotoForm photoForm = (PhotoForm)form;
        photoForm.setTypeMultimedia(GlobalConstants.TypeMutliMedia.PHOTO);

        log.debug("Photo : " + photoForm);
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
    public ActionForward createPieceJointe(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Cr�ation d'une nouvelle pi�ce jointe");

        //Valeur par d�faut pour l'insertion
        PhotoForm photoForm = (PhotoForm)form;
        photoForm.setTypeMultimedia(GlobalConstants.TypeMutliMedia.DOCUMENT_ANNEXE);

        log.debug("Photo : " + photoForm);
        return mapping.findForward("success");
    }

}