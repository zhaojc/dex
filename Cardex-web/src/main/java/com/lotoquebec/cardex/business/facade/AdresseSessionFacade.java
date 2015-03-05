/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.util.List;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.integration.dao.AdresseDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * Le AdresseBusinessFacade offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 19:44:34 $
 */
public class AdresseSessionFacade {

    /**
     * Recherche d'une adresse
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La adresse recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Adresse find(CardexAuthenticationSubject subject,
                        Adresse criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            Adresse adresse = FabriqueCardexDAO.getInstance().getAdresseDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, criteria);
            return adresse;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de l'audit des changements d'une adresse
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les adresses recherchées
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Adresse criteria) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getAdresseDAO().audit(subject, criteria);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

}

