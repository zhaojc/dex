/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.exception.BilletBusinessRuleException;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe valide l'ensemble des règles d'affaire applicable aux billets.
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
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Construit une instance de BilletBusinessRuleSet
     */
    public BilletBusinessRuleSet() {}

    /**
     * Valide les règles d'affaires applicable à un billet.
     *
     * @param businessObject Le billet
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet Billet ne sont pas respectées.
     * @throws BusinessException 
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.vo.BilletVO
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.fine("checkRules()");

        if (businessObject instanceof BilletVO) {
            BilletVO billet = (BilletVO) businessObject;
            //Pour les dossiers de Client mystère, on valide la saisie du numéro de contrôle du billet
            if(billet.getLienSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)){
                checkNumeroDeControleRule(billet);
            }
       } else {
            throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"
                                               + BilletVO.class.getName()
                                               + "'");
        }
    }

    /**
     * Les 2 premiers chiffres seront toujours 07 et le 
     * reste sans caractères autres que de chiffres, mais 
     * toujours la même longueur de 14, les traits d'union   
     * sont à la positionne 3 et 8 du numéro de contrôle
     * 
     * Exemple : 07-9999-999999
     *
     * @param billet Le billet
     *
     * @throws BusinessRuleException si le numéro de contrôle n'est pas valide.
     *
     */
    private void checkNumeroDeControleRule(BilletVO billet)
            throws BusinessRuleException {
        log.fine("checkNumeroDeControleRule()");

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
                && (StringUtils.mid(sNumCtrlAvecTrait, 2, 1).compareTo("-")==0) //troisième caractère doit être un trait d'union
                && (StringUtils.mid(sNumCtrlAvecTrait, 7, 1).compareTo("-")==0) //septième caractère doit être un trait d'union
                ){
        }else {
            throw createException(BilletBusinessRuleException.NUMERO_DE_CONTROLE_INVALID);
        }
    }

    /**
     * Retourne un DossierBusinessRuleException initialisé avec
     * l'identificateur de règle.
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
