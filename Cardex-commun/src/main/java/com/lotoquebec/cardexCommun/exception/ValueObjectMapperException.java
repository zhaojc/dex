package com.lotoquebec.cardexCommun.exception;



/**
 * ValueObjectMapperException is thrown  when any errors occurs
 * with a data source.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.3 $, $Date: 2002/02/18 23:56:12 $
 */
public class ValueObjectMapperException extends ChainedException {

    /**
     * Constructor a ValueObjectMapperException instance
     *
     * @see java.lang.Exception
     */
    public ValueObjectMapperException() {
        super();
    }

    /**
     * Constructor a ValueObjectMapperException instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public ValueObjectMapperException(String msg) {
        super(msg);
    }

    /**
     * Constructor a ValueObjectMapperException instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public ValueObjectMapperException(Exception e) {
        super(e);
    }

    /**
     * Constructor a ValueObjectMapperException instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public ValueObjectMapperException(Exception e, String msg) {
        super(e,msg);
    }

}

