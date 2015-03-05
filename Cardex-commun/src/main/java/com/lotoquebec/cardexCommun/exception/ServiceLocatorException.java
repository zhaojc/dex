package com.lotoquebec.cardexCommun.exception;

/**
 * ServiceLocatorException is thrown  when any errors occurs
 * in lookup and serialization of EJBHandle.
 *
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/01/25 15:03:27 $
 */
public class ServiceLocatorException extends ChainedException {

    /**
     * Constructor a ServiceLocatorException instance
     *
     * @see java.lang.Exception
     */
    public ServiceLocatorException() {
        super();
    }

    /**
     * Constructor a ServiceLocatorException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public ServiceLocatorException(String msg) {
        super(msg);
    }

    /**
     * Constructor a ServiceLocatorException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public ServiceLocatorException(Exception e) {
        super(e);
    }

    /**
     * Constructor a ServiceLocatorException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public ServiceLocatorException(Exception e, String msg) {
        super(e,msg);
    }

}

