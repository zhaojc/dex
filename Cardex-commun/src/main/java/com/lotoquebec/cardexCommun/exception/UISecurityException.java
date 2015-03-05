package com.lotoquebec.cardexCommun.exception;


/**
 * UISecurityException is thrown  when any
 * errors occurs while using or creating
 * an UIComponentSecurityManager
 *
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/02/08 17:37:13 $
 */
public class UISecurityException extends RuntimeException {

    /**
     * Constructor a AuthenticationException instance
     *
     * @see java.lang.Exception
     */
    public UISecurityException() {
        super();
    }

    /**
     * Constructor a AuthenticationException instance
     *
     * @see java.lang.Exception
     */
    public UISecurityException(String msg) {
        super(msg);
    }

}