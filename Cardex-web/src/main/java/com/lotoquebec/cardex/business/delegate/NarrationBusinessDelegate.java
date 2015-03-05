/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.exception.CriteresRechercheNarrationBusinessRuleException;
import com.lotoquebec.cardex.business.exception.NarrationBusinessRuleException;
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.NarrationSessionFacade;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.GlobalConstants.GabaritNarration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le DossierBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/04/11 15:06:09 $
 */
public class NarrationBusinessDelegate extends BusinessDelegate {
    NarrationSessionFacade narrationSessionFacade;

    /**
     * Construit une instance de NarrationBusinessDelegate
     */
    public NarrationBusinessDelegate() {
        this.narrationSessionFacade = new NarrationSessionFacade();
    }

    /**
     * Recherche d'une narration
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La narration recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Narration find(CardexAuthenticationSubject subject,
                        Narration criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return narrationSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleNarrationBusinessRuleException(e);
        }
    }

    /**
     * Recherche de narrations
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La narration recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection selectNarration(CardexAuthenticationSubject subject,
                        CriteresRechercheNarration criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return narrationSessionFacade.selectNarration(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherNarrationBusinessRuleException(e);
        }
    }

    /* Recherche de narrations approuv�
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La narration recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection selectApprobation(CardexAuthenticationSubject subject,
                        CriteresRechercheNarration criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return narrationSessionFacade.selectApprobation(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherNarrationBusinessRuleException(e);
        }
    }

    public void insertionNarrationTemporaire(CardexAuthenticationSubject subject, Narration narration) throws BusinessException, BusinessResourceException {
		try {
			narrationSessionFacade.insertionNarrationTemporaire(subject, narration);
		} catch (BusinessRuleException e) {
			throw handleCriteresRecherNarrationBusinessRuleException(e);
		}
	}

    
    public void sauvegarderNarrationTemporaire(CardexAuthenticationSubject subject, Narration narration) throws BusinessException, BusinessResourceException {
		try {
			narrationSessionFacade.sauvegarderNarrationTemporaire(subject, narration);
		} catch (BusinessRuleException e) {
			throw handleCriteresRecherNarrationBusinessRuleException(e);
		}
	}
    
    public Narration obtenirNarrationTemporaire(CardexAuthenticationSubject subject, Narration narration) throws BusinessException, BusinessResourceException {
		try {
			return narrationSessionFacade.obtenirNarrationTemporaire(subject, narration);
		} catch (BusinessRuleException e) {
			throw handleCriteresRecherNarrationBusinessRuleException(e);
		}
	}    
    
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
    private BusinessException handleNarrationBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

		switch (businessRule) {

		case NarrationBusinessRuleException.NARRATION_TEMPS_CONSACRE:
			message.setKey("cardex_temps_consacre");
			break;

		default:
			throw new IllegalArgumentException("La r�gle d'affaire '"
											   + businessRule
											   + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
											   + Dossier.class.getName()
											   + "'");
		}
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

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
    private BusinessException handleCriteresRecherNarrationBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case CriteresRechercheNarrationBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN:
            message.setKey("cardex_required_validate_date");
            //message.addArgument();
            break;

        default:
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
                                               + CriteresRechercheNarration.class.getName()
                                               + "'");
        }

        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Recherche des changements d'une narration.
     *
     * @param subject Le sujet qui recherche une narration
     * @param criteria Les crit�res de recherche
     *
     * @return La narration recherch�e
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List audit(CardexAuthenticationSubject subject,
    		Narration criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return narrationSessionFacade.audit(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleNarrationBusinessRuleException(e);
        }
    }    

	public List<Long> creerExclureGabaritNarration(CardexAuthenticationSubject subject, EntiteCardex entiteCardex) throws BusinessException{
		
		try {
			DossierSessionFacade dossierSessionFacade = new DossierSessionFacade();
			List<Long> exclureValeur = new ArrayList<Long>(GlobalConstants.GabaritNarration.ExclureGabarit);
			
			if (entiteCardex instanceof Dossier){
				Dossier dossier = dossierSessionFacade.find(subject, (Dossier) entiteCardex);
				
				if (GlobalConstants.Categorie.COMITE_VIGILANCE == dossier.getCategorie()){
					List<Narration> liensNarration = (List<Narration>) dossierSessionFacade.findLiensNarration(subject, dossier);
					
					exclureValeur.remove(obtenirGabaritVigilanceAutorise(liensNarration));
					exclureValeur.remove(GabaritNarration.VIGILANCE_DEMANDE_REINTEGRATION_CLIENT);
					exclureValeur.remove(GabaritNarration.VIGILANCE_SUIVI);
				}				
			}
			return exclureValeur;
		} catch (BusinessRuleException e) {
			throw handleCriteresRecherNarrationBusinessRuleException(e);
		}
	}
	
	public List<Long> modifierExclureGabaritNarration(CardexAuthenticationSubject subject, Narration narration) throws BusinessException{
		
		//try {
			//narration = narrationSessionFacade.find(subject, narration);
			List<Long> exclureValeur = new ArrayList<Long>(GlobalConstants.GabaritNarration.ExclureGabarit);
			
			if (narration.getGabaritUtilise() != 0)
				exclureValeur.remove(narration.getGabaritUtilise());
			return exclureValeur;
		/*} catch (BusinessRuleException e) {
			throw handleCriteresRecherNarrationBusinessRuleException(e);
		}*/
	}
	
	/**
	 * On prend la liste trier en ordre de gabarit de vigilance.
	 * On retirer tous les gabarits pr�sent dans les narrations de vigilance.
	 * On retourne le prochain gabarit de vigilance permis d'ajout�
	 * 
	 * @param dossierForm
	 * @return
	 */
	private long obtenirGabaritVigilanceAutorise(List<Narration> narrations){
		List<Long> gabaritsVigilance = new ArrayList<Long>(GlobalConstants.GabaritNarration.ExclureGabarit);
			
		for (Narration narration:narrations)
			gabaritsVigilance.remove((Long)narration.getGabaritUtilise());
		
		if (gabaritsVigilance.size() > 0)
			return gabaritsVigilance.get(0);
		return 0;
	}
	
}

