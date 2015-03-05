/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.business;

//import javax.ejb.*;
import java.util.HashMap;

import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * The BusinessDelegate use the required session facade
 * component to provide access to the underlying
 * business services.
 *
 * @author $Autor: $
 * @version $Revision: 1.3 $, $Date: 2002/04/25 15:43:17 $
 */
public abstract class BusinessDelegate {


    private static HashMap delegates = new HashMap();

    /**
     * Construct an instance of the specified BusinessDelegate type.
     */
    public static BusinessDelegate getInstance(String type) throws BusinessResourceException {
      try {
        Class delegateClass = (Class)delegates.get(type);
        if (delegateClass == null){
          delegateClass = Class.forName(type);
          delegates.put(type,delegateClass);
        }
        return (BusinessDelegate)delegateClass.newInstance();
      }catch (java.lang.ClassNotFoundException ce) {
        throw new BusinessResourceException(ce);
      }catch (java.lang.IllegalAccessException ie) {
        throw new BusinessResourceException(ie);
      }catch (java.lang.InstantiationException inste) {
        throw new BusinessResourceException(inste);
      }
    }

    /**
     * Construct an instance of BusinessDelegate.
     * Creating a new session facade.
     */
   public BusinessDelegate() {
        super();
    }

   /**
    * Construit une BusinessException contenant les messages d'erreurs qui
    * doivent être présenté à un utilisateur. Cette méthode fait la mise en
    * correspondance entre les codes de règles d'affaires reçus d'une
    * BusinessRuleException et les messsages qui doivent être affiché
    * à un utilisateur.
    *
    * @param bre BusinessRuleException BusinessRuleException contenant
    *            les codes de règles d'affaires
    *
    * @return BusinessException BusinessException contenant les messages
    * d'erreurs qui doivent être présenté à un utilisateur.
    */
   protected BusinessException handleBusinessRuleException(BusinessRuleException bre) throws BusinessException {
       BusinessException     be = new BusinessException();
       BusinessMessageResult messages = new BusinessMessageResult();
       BusinessMessage       message = new BusinessMessage();
       messages.addMessage(message);
       be.setBusinessMessageResult(messages);
       return be;
   }
}

