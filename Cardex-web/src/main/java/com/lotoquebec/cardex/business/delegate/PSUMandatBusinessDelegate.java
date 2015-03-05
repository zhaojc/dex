/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.CriteresRecherchePSUMandat;
import com.lotoquebec.cardex.business.PSUMandat;
import com.lotoquebec.cardex.business.facade.PSUMandatSessionFacade;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le DossierBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:29 $
 */
public class PSUMandatBusinessDelegate extends BusinessDelegate {
    PSUMandatSessionFacade psuMandatSessionFacade;

    /**
     * Construit une instance de ConsignationBusinessDelegate
     */
    public PSUMandatBusinessDelegate() {
        this.psuMandatSessionFacade = new PSUMandatSessionFacade();
    }

    /**
     * Recherche d'un mandat PSU
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La consignation recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public PSUMandat find(CardexAuthenticationSubject subject,
	PSUMandat criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return psuMandatSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handlePSUMandatBusinessRuleException(e);
        }
    }

	/**
	 * Approbation d'un mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return void
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public void approbation(CardexAuthenticationSubject subject,
	PSUMandat criteria) throws BusinessException,
						BusinessResourceException {
		try {
			psuMandatSessionFacade.approbation(subject, criteria);
		} catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
	}

	/**
	 * Suppression ou d�sactivation d'un mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return void
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public void delete(CardexAuthenticationSubject subject,
	PSUMandat criteria) throws BusinessException,
						BusinessResourceException {
		try {
			psuMandatSessionFacade.delete(subject, criteria);
		} catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
	}

	/**
	 * Mise � jour d'un mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return void
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public void update(CardexAuthenticationSubject subject,
	PSUMandat criteria) throws BusinessException,
						BusinessResourceException {
		try {
			psuMandatSessionFacade.update(subject, criteria);
		} catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
	}

	/**
	 * V�rification d'un mandat PSU � la suite d'une action.
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return void
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public List<PSUMandat> verification(CardexAuthenticationSubject subject,
	PSUMandat criteria, String genreFichier, String action) throws BusinessException,
						BusinessResourceException {
		try {
			return psuMandatSessionFacade.verification(subject, criteria, genreFichier, action);
		} catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
	}

	/**
	 * Sauvegarde d'un nouveau mandat.
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return void
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public void insert(CardexAuthenticationSubject subject,
	PSUMandat criteria) throws BusinessException,
						BusinessResourceException {
		try {
			psuMandatSessionFacade.insert(subject, criteria);
		} catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
	}

    /**
     * Recherche de mandats PSU.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La consignation recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<PSUMandat> select(CardexAuthenticationSubject subject,
                        CriteresRecherchePSUMandat criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return psuMandatSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handlePSUMandatBusinessRuleException(e);
        }
    }

	/**
	 * Recherche des entr�es de la consignation pour les rapports.
	 *
	 * @param subject Le sujet qui effectue la recherche
	 * @param criteria Les crit�res de recherche
	 *
	 * @return Les dossiers recherch�s
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
/*	public ValueListIterator selectRapportConsignation(CardexAuthenticationSubject subject,
									CriteresRechercheConsignation criteria) throws BusinessException,
									BusinessResourceException {
		try {
			return consignationSessionFacade.selectRapportConsignation(subject, criteria);
		} catch (BusinessRuleException e) {
			throw handleConsignationBusinessRuleException(e);
		}
	}
*/
    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
     * correspondance entre les codes de r�gles d'affaires re�us d'une
     * BusinessRuleException et les messsages qui doivent �tre affich�
     * � un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de r�gles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
     */
    private BusinessException handlePSUMandatBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

	/**
	 * V�rification d'un mandat PSU � la suite d'une action.
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return void
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public void ecrireConsignationAction(CardexAuthenticationSubject subject,
	       ConsignationActionPSU consignationActionPSU) throws BusinessException,
						BusinessResourceException {
		try {
			psuMandatSessionFacade.ecrireConsignationAction(subject, consignationActionPSU);
		} catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
	}

	/**
	 * Lecture des consignations d'action reli�es � un mandat PSU.
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les crit�res de recherche
	 *
	 * @return La consignation recherch�
	 *
	 * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
	 * @throws BusinessResourceException si une erreur syst�me survient
	 */
	public ValueListIterator findLiensConsignationAction(CardexAuthenticationSubject subject,
						String mandat) throws BusinessException,
						BusinessResourceException {
		try {
			return psuMandatSessionFacade.findLiensConsignationAction(subject, mandat);
		}catch (BusinessRuleException e) {
			throw handlePSUMandatBusinessRuleException(e);
		}
   }
}

