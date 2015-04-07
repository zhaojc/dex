/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.exception.BilletBusinessRuleException;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable aux billets.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author Author: mazzucr 
 * @version 
 */
public class BilletBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Construit une instance de BilletBusinessRuleSet
     */
    public BilletBusinessRuleSet() {}

    /**
     * Valide les r�gles d'affaires applicable � un billet.
     *
     * @param businessObject Le billet
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet Billet ne sont pas respect�es.
     * @throws BusinessException 
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.vo.BilletVO
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.debug("checkRules()");

        if (businessObject instanceof BilletVO) {
            BilletVO billet = (BilletVO) businessObject;
            //Pour les dossiers de Client myst�re, on valide la saisie du num�ro de contr�le du billet
            if(billet.getLienSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)){
                checkNumeroDeControleRule(billet);
            }
       } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + BilletVO.class.getName()
                                               + "'");
        }
    }

    /**
     * Les 2 premiers chiffres seront toujours 07 et le 
     * reste sans caract�res autres que de chiffres, mais 
     * toujours la m�me longueur de 14, les traits d'union   
     * sont � la positionne 3 et 8 du num�ro de contr�le
     * 
     * Exemple : 07-9999-999999
     *
     * @param billet Le billet
     *
     * @throws BusinessRuleException si le num�ro de contr�le n'est pas valide.
     *
     */
    private void checkNumeroDeControleRule(BilletVO billet)
            throws BusinessRuleException {
        log.debug("checkNumeroDeControleRule()");

        boolean contientLettres = false;
        
        String sNumCtrlAvecTrait = billet.getNumeroControl();
        String sNumCtrl = sNumCtrlAvecTrait.replaceAll("-", "");
                
        int sz = sNumCtrl.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(sNumCtrl.charAt(i)) == true)  
                contientLettres=true;
        }
        
        if ((contientLettres == false)  
                && (sNumCtrlAvecTrait.length() == 14) 
                && (StringUtils.left(sNumCtrl,2).compareTo("07")==0) //commence par 07
                && (StringUtils.mid(sNumCtrlAvecTrait, 2, 1).compareTo("-")==0) //troisi�me caract�re doit �tre un trait d'union
                && (StringUtils.mid(sNumCtrlAvecTrait, 7, 1).compareTo("-")==0) //septi�me caract�re doit �tre un trait d'union
                ){
        }else {
            throw createException(BilletBusinessRuleException.NUMERO_DE_CONTROLE_INVALID);
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
        BilletBusinessRuleException exc =
            new BilletBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

}
