package com.lotoquebec.cardexCommun.exception;

/**
 * BusinessRuleException is thrown  when any business
 * rules violation occurs while using a business services
 *
 * @author $Autor: $
 * @version $Revision: 1.1 $, $Date: 2002/01/23 20:09:08 $
 */
public class BusinessRuleException extends ChainedException {

    private int rule;

    /**
     * Constructor a BusinessRuleException instance
     *
     * @see java.lang.Exception
     */
    public BusinessRuleException() {
        super();
    }

    /**
     * Constructor a BusinessRuleException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a BusinessRuleException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public BusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a BusinessRuleException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BusinessRuleException(Exception e, String msg) {
        super(e,msg);
    }

    public int getBusinessRule() {
      return this.rule;
    }

    public void setBusinessRule(int rule) {
      this.rule = rule;
    }
}