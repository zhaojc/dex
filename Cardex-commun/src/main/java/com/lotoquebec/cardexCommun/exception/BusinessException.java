package com.lotoquebec.cardexCommun.exception;

import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;

/**
 * BusinessException is thrown  when any business
 * errors occurs while using or creating
 * a session facade
 *
 * @author $Autor: $
 * @version $Revision: 1.1 $, $Date: 2002/01/23 20:09:00 $
 */
public class BusinessException extends ChainedException {

    BusinessMessageResult messageResult ;

    /**
     * Constructor a BusinessException instance
     *
     * @see java.lang.Exception
     */
    public BusinessException() {
        super();
    }
    
    public BusinessException(BusinessMessage businessMessage) {
        super();
        messageResult = new BusinessMessageResult();
        messageResult.addMessage(businessMessage);
    }

    /**
     * Constructor a BusinessException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BusinessException(String msg) {
        super(msg);
    }

    /**
     * Constructor a BusinessException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public BusinessException(Exception e) {
        super(e);
    }

    /**
     * Constructor a BusinessException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BusinessException(Exception e, String msg) {
        super(e,msg);
    }

    public BusinessMessageResult getBusinessMessageResult() {
      return this.messageResult;
    }

    public void setBusinessMessageResult(BusinessMessageResult result) {
      this.messageResult = result;
    }
}
