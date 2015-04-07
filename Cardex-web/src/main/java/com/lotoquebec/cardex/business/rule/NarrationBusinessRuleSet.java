/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.exception.NarrationBusinessRuleException;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.util.DateUtils;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable aux societes.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/04/11 19:20:27 $
 */
public class NarrationBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Valide les r�gles d'affaires applicable � une narration.
     *
     * @param businessObject La soci�t�
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet Societe ne sont pas respect�es.
     * @throws BusinessException 
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Societe
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.debug("checkRules()");

        if (businessObject instanceof Narration) {
			try {
				Narration narration = (Narration) businessObject;
				assignerDonneeValidation(subject, narration.getDossier());
	            checkTempsConsacre(narration);
	            validerRapportActiviteQuotidienne(narration);
			} catch (DAOException e) {
				e.printStackTrace();
				throw new AssertionError("Probl�me DAO dans NarrationBusinessRuleSet");	
			}
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + Narration.class.getName()
                                               + "'");
        }
    }

    /**
     * Un seul rapport d'activit� quotidienne par dossier par jour est permis 
     * @param narration
     * @throws BusinessException 
     */
    private void validerRapportActiviteQuotidienne(Narration narration) throws BusinessException {
		Dossier dossier = narration.getDossier();
		
		if (dossier != null && narration.isRapportActiviteQuotidienne() // Cette narration contient une gabarit RAQ
		&& narration.getCle() == 0){ // ajout d'une narration
			Iterator iter = dossier.getNarrations().iterator();
			
			while (iter.hasNext()) {
				Narration narrationIter = (Narration) iter.next();
				
				if (narrationIter.isRapportActiviteQuotidienne()
				&& DateUtils.isMemeJour(narration.getDateCreation(), narrationIter.getDateCreation())){
					BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
					businessRuleExceptionHandle.add("cardex_narration_rapport_activite_quotidienne" );
	            	throw businessRuleExceptionHandle.getBusinessException();
				}
			}
		}
	}

    private void assignerDonneeValidation(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException{
    	// NARRATION
    	Collection linkedNarration = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensNarration(subject, dossier);
    	Iterator it = linkedNarration.iterator();
    	
        while (it.hasNext()){
        	dossier.addNarration((Narration)it.next());
        }    	
    }    
    
	/**
     * Lors de l'approbation d'une narration, on s'assure qu'une valeur a �t�
     * inscrite dans le champ Temps consacr�.
     *
     * @param businessObject La soci�t�
     *
     * @throws BusinessRuleException si la date de fondation n'est pas
     * inf�rieure � la date courante.
     */
    private void checkTempsConsacre(Narration narration)
            throws BusinessRuleException {
        log.debug("checkTempsConsacre()");

        if (OracleDAOUtils.isEmpty(narration.getTempsConsacre())) {
            throw createException(NarrationBusinessRuleException.NARRATION_TEMPS_CONSACRE);
        }else{
        	log.debug("temps : " + narration.getTempsConsacre());
        }
    }

    /**
     * Retourne un NarrationBusinessRuleException initialis� avec
     * l'identificateur de r�gle.
     *
     *
     * @param ruleId
     *
     * @return
     *
     * @see
     */
    protected BusinessRuleException createException(int ruleId) {
        NarrationBusinessRuleException exc =
            new NarrationBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

}
