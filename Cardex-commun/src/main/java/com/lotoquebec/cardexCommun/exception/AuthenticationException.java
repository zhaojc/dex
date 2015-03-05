package com.lotoquebec.cardexCommun.exception;

/**
 * AuthenticationException is thrown  when any authentication
 * errors occurs while using or creating
 * an AuthenticationService
 *
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/02/08 17:37:13 $
 */
public class AuthenticationException extends ChainedException{
    /**
     * Constructor a AuthenticationException instance
     *
     * @see java.lang.Exception
     */
    public AuthenticationException() {
        super();
    }

    /**
     * Constructor a AuthenticationException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public AuthenticationException(String msg) {
        super(msg);
    }

    /**
     * Constructor a AuthenticationException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public AuthenticationException(Exception e) {
        super(e);
    }

    /**
     * Constructor a AuthenticationException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public AuthenticationException(Exception e, String msg) {
        super(e,msg);
    }

}