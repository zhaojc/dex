package com.lotoquebec.cardexCommun.exception;

/**
 * BusinessResourceException is thrown  when any resource or
 * system errors occurs at the resource layer level.
 *
 * @author $Autor: $
 * @version $Revision: 1.3 $, $Date: 2002/02/08 17:37:13 $
 */
public class BusinessResourceException extends BusinessException {

    /**
     * Constructor a BusinessResourceException instance
     *
     * @see java.lang.Exception
     */
    public BusinessResourceException() {
        super();
    }

    /**
     * Constructor a BusinessResourceException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BusinessResourceException(String msg) {
        super(msg);
    }

    /**
     * Constructor a BusinessResourceException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public BusinessResourceException(Exception e) {
        super(e);
    }

    /**
     * Constructor a BusinessResourceException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public BusinessResourceException(Exception e, String msg) {
        super(e,msg);
    }

}