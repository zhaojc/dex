/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.exception;

import java.lang.Exception;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * The ChainedException class is the super class use
 * to represent exceptional conditions.  The
 * ChainedException keep a reference to an ancestor
 * that is the initiator of the exception.
 *
 * @see java.lang.Exception
 * @author $Autor: $
 * @version $Revision: 1.2 $, $Date: 2002/04/24 12:32:36 $
 */
public class ChainedException extends Exception {
    Exception ancestor = null;

    /**
     * Constructor a chained exception instance
     *
     * @see java.lang.Exception
     */
    public ChainedException() {
        super();
    }

    /**
     * Constructor a chained exception instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public ChainedException(String msg) {
        super(msg);
    }

    /**
     * Constructor a chained exception instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public ChainedException(Exception e) {
        this(e.getMessage());

        this.ancestor = e;
    }

    /**
     * Constructor a chained exception instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public ChainedException(Exception e, String msg) {
        this(msg);

        this.ancestor = e;
    }

    /**
     *
     */
    public Exception getAncestor() {
      return this.ancestor;
    }

    /**
     * Print this exception and all ancestor exception
     * stack trace to System.err PrintStream.
     *
     * @see java.lang.Exception
     */
    public void printStackTrace() {
      printStackTrace(System.err);
    }

    /**
     * Print this exception and all ancestor exception
     * stack trace to the specified PrintStream.
     *
     * @see java.lang.Exception
     */
    public void printStackTrace(PrintStream stream) {
        super.printStackTrace(stream);
        if (this.ancestor != null) {
            this.ancestor.printStackTrace(stream);
        }
    }

    /**
     * Print this exception and all ancestor exception
     * stack trace to the specified PrintWriter.
     *
     * @see java.lang.Exception
     */
    public void printStackTrace(PrintWriter writer) {
        super.printStackTrace(writer);
        if (this.ancestor != null) {
            this.ancestor.printStackTrace(writer);
        }
    }

}

