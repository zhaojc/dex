/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Le AccesBusinessFacade offre les
 * services d'affaires concernant l'objet
 * acces.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 19:44:34 $
 */
public class AccesSessionFacade {

    /**
     * Recherche des acc�s.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> findAccesDossier(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	FabriqueFacade.getDossierSessionFacade().find(subject, criteria);
            return FabriqueCardexDAO.getInstance().getAccesDAO().select(subject, criteria, GlobalConstants.GenreFichier.DOSSIER);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des acc�s.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> findAccesSujet(CardexAuthenticationSubject subject,
    		EntiteCardex criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	FabriqueFacade.getSujetSessionFacade().find(subject, criteria);
    		return FabriqueCardexDAO.getInstance().getAccesDAO().select(subject, criteria, GlobalConstants.GenreFichier.SUJET);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche des acc�s.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> findAccesSociete(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	FabriqueFacade.getSocieteSessionFacade().find(subject, criteria);
        	return FabriqueCardexDAO.getInstance().getAccesDAO().select(subject, criteria, GlobalConstants.GenreFichier.SOCIETE);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche des acc�s.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> findAccesVehicule(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, criteria);
        	return FabriqueCardexDAO.getInstance().getAccesDAO().select(subject, criteria, GlobalConstants.GenreFichier.VEHICULE);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        } catch (BusinessException e) {
			throw new BusinessResourceException(e);
		}
    }
                

}

