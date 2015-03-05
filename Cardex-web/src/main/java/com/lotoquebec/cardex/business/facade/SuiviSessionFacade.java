/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.List;

import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.SuiviDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * Le SuiviBusinessFacade offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:33 $
 */
public class SuiviSessionFacade {

    /**
     * Recherche d'une suivi
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La suivi recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Suivi find(CardexAuthenticationSubject subject,
                        Suivi criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            Suivi suivi = FabriqueCardexDAO.getInstance().getSuiviDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, suivi);
            return suivi;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de suivis
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La suivi recherché
     * @throws BusinessException 
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     */
    public List<Suivi> select(CardexAuthenticationSubject subject,
                        CriteresRechercheSuivi criteria) throws BusinessRuleException,
                        BusinessException {
        try {
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            checkNombreEnregistrementRechercheSuivi(subject, criteria);
            List<Suivi> suiviList = FabriqueCardexDAO.getInstance().getSuiviDAO().select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, suiviList);        	
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    private void checkNombreEnregistrementRechercheSuivi(CardexAuthenticationSubject subject, CriteresRechercheSuivi criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getSuiviDAO().nombreDeSuiviRecherche(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SUIVI){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SUIVI );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
    	}
    }
    
    /**
     * Recherche de l'audit des changements d'un suivi
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les suivis recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Suivi criteria) throws BusinessRuleException, BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getSuiviDAO().audit(subject, criteria);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    
}

