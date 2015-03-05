/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.List;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.CriteresRechercheConsignation;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.VerificationSyntaxe;


/**
 * Le SuiviBusinessFacade offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:33 $
 */
public class ConsignationSessionFacade {


    /**
     * Recherche d'une consignation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La consignation recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Consignation find(CardexAuthenticationSubject subject,
                        Consignation criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            Consignation consignation = FabriqueCardexDAO.getInstance().getConsignationDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, consignation);
            return consignation;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de consignations
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La consignation recherché
     * @throws BusinessException 
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     */
    public List<Consignation> select(CardexAuthenticationSubject subject,
                        CriteresRechercheConsignation criteria) throws BusinessRuleException,
                        BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            checkNombreEnregistrementRechercheConsignation(subject, criteria);
            List<Consignation> consignationList = FabriqueCardexDAO.getInstance().getConsignationDAO().select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, consignationList);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    private void checkNombreEnregistrementRechercheConsignation(CardexAuthenticationSubject subject, CriteresRechercheConsignation criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getConsignationDAO().nombreDeConsignationRecherche(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_CONSIGNATION){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_CONSIGNATION );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
    	}
    }


}

