package com.lotoquebec.cardexCommun.exception;

/**
 * IteratorException is thrown  when no list data
 * are available when an operation
 * is perform on a ValueListIterator.
 *
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/01/25 15:03:27 $
 */
public class IteratorException extends ChainedException {

    /**
     * Constructor a IteratorException instance
     *
     * @see java.lang.Exception
     */
    public IteratorException() {
        super();
    }

    /**
     * Constructor a IteratorException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public IteratorException(String msg) {
        super(msg);
    }

    /**
     * Constructor a IteratorException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public IteratorException(Exception e) {
        super(e);
    }

    /**
     * Constructor a IteratorException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public IteratorException(Exception e, String msg) {
        super(e,msg);
    }

}