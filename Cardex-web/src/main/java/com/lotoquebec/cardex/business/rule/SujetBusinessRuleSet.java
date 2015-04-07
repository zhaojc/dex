/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.exception.SujetBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ValiderNAS;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable aux sujets.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/04/11 19:20:27 $
 */
public class SujetBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Construit une instance de SujetBusinessRuleSet
     */
    public SujetBusinessRuleSet() {}

    /**
     * Valide les r�gles d'affaires applicable � un sujet.
     *
     * @param businessObject Le sujet
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet Sujet ne sont pas respect�es.
     * @throws BusinessException 
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Sujet
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.debug("checkRules()");

        if (businessObject instanceof Sujet) {
            Sujet sujet = (Sujet) businessObject;

            checkDateNaissanceRule(sujet);
            checkMotDePasseRule(sujet);
            checkNumeroAssuranceSocial(sujet);
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + Sujet.class.getName()
                                               + "'");
        }
    }

    /**
     * Dates de d�but sup�rieure ou �gale � 1993-01-01.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkMotDePasseRule(Sujet sujet)
            throws BusinessRuleException {
        log.debug("checkMotDePasseRule()");

        if (sujet.getMotPasse().trim().equals(sujet.getConfirmationMotPasse().trim())) {
        }else {
            throw createException(SujetBusinessRuleException.MOT_DE_PASSE_INVALID);
        }
    }

    /**
     * Retourne un DossierBusinessRuleException initialis� avec
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
        SujetBusinessRuleException exc =
            new SujetBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

    /**
     * Validation de la date de naissance.
     *
     * @param businessObject Le sujet
     *
     * @throws BusinessRuleException si les dates de d�but sont
     * inf�rieures ou �gales aux dates de fin.
     */
    private void checkDateNaissanceRule(Sujet businessObject)
            throws BusinessRuleException {
        log.debug("checkDateDebutSuperieurDateFinRule()");

        Timestamp dateNaissance = businessObject.getDateNaissance();
        long typeAge = businessObject.getTypeAge();
        
        if (dateNaissance != null && typeAge != 1353) {

            Date now = new Date(System.currentTimeMillis());

            GregorianCalendar gc = new GregorianCalendar(1900, 0, 1);
            Date old = gc.getTime();

            if (dateNaissance.before(old) || dateNaissance.after(now)) {
                throw createException(SujetBusinessRuleException.DATE_NAISSANCE_INVALIDE);
            }
        }
    }

    private void checkNumeroAssuranceSocial(Sujet sujet) throws BusinessException {
		log.debug("checkNumeroAssuranceSocial()");
		String numeroAssuranceSociale = sujet.getNumeroAssuranceSociale();
		
		if (StringUtils.isNotEmpty(numeroAssuranceSociale)
		&& sujet.isNASCanadien()) {
			ValiderNAS validerNAS = new ValiderNAS( numeroAssuranceSociale);
			
			if (validerNAS.isValide() == false){
				throw (new BusinessRuleExceptionHandle("cardex_NumeroAssuranceSocial")).getBusinessException();			
			}
		}
    }
}
