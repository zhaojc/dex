package com.lotoquebec.cardexCommun.exception;

/**
 * DAOException is thrown  when any errors occurs
 * with a data source.
 *
 * @author $Autor: $
 * @version $Revision: 1.1 $, $Date: 2002/01/23 20:09:12 $
 */
public class DAOException extends ChainedException {

    /**
     * Constructor a DAOException instance
     *
     * @see java.lang.Exception
     */
    public DAOException() {
        super();
    }

    /**
     * Constructor a DAOException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public DAOException(String msg) {
        super(msg);
    }

    /**
     * Constructor a DAOException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public DAOException(Exception e) {
        super(e);
    }

    /**
     * Constructor a DAOException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public DAOException(Exception e, String msg) {
        super(e,msg);
    }

}

