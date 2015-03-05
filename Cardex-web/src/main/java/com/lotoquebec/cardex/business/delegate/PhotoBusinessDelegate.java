/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.exception.CriteresRecherchePhotoBusinessRuleException;
import com.lotoquebec.cardex.business.facade.PhotoSessionFacade;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le DossierBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/04/04 20:41:08 $
 */
public class PhotoBusinessDelegate extends BusinessDelegate {
    PhotoSessionFacade photoSessionFacade;

    /**
     * Construit une instance de PhotoBusinessDelegate
     */
    public PhotoBusinessDelegate() {
        this.photoSessionFacade = new PhotoSessionFacade();
    }

    /**
     * Recherche de photos
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La photo recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Photo> select(CardexAuthenticationSubject subject,
                        CriteresRecherchePhoto criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return photoSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherPhotoBusinessRuleException(e);
        }
    }

    /**
     * Obtenir le fichier demandé par la clé et le site
     * @param cle
     * @param site
     * @return
     * @throws BusinessException
     * @throws BusinessResourceException
     */
    public FichierMultimediaVO obtenirFichier(CardexAuthenticationSubject subject, long cle, long site, boolean grandeImage) throws BusinessException, BusinessResourceException{
        try {
            return photoSessionFacade.obtenirFichier(subject, cle, site, grandeImage);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherPhotoBusinessRuleException(e);
        }    	
    }
    
    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handlePhotoBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }


    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handleCriteresRecherPhotoBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case CriteresRecherchePhotoBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN:
            message.setKey("cardex_required_validate_date");
            //message.addArgument();
            break;

        case CriteresRecherchePhotoBusinessRuleException.PAS_AU_MOINS_UNE_CASE_COCHEE:
            message.setKey("cardex_required_inclus");
            //message.addArgument();
            break;
        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + CriteresRecherchePhoto.class.getName()
                                               + "'");
        }

        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Mise à jour d'un lien multiméria d'un dossier
     *
     * @param subject Le sujet qui modifie le lien multimédia d'un dossier
     * @param photo le lien photo modifié
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienMultimedia(CardexAuthenticationSubject subject,
                       Photo photo) throws BusinessException,
                                            BusinessResourceException {
        try {
            photoSessionFacade.updateLienMultimedia(subject, photo);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherPhotoBusinessRuleException(e);
        }
    }

}

