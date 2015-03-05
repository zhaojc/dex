/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.business;

import java.util.ArrayList;
import java.util.List;

import com.lotoquebec.cardexCommun.business.BusinessMessage;

import java.util.Collection;

/**
 * Convenient wrapper for collections of messages returned
 * from the business layer.
 *
 * @author $Autor: $
 * @version $Revision: 1.3 $, $Date: 2002/02/08 17:37:19 $
 */
public class BusinessMessageResult {

    /**
     * Message list.
     */
    protected ArrayList messages = null;

    /**
     * Construct new instance of  BusinessMessageResult
     */
    public BusinessMessageResult() {
        this.messages = new ArrayList();
    }

    /**
     * Add message to the list.
     */
    public boolean addMessage(BusinessMessage message) {
        return this.messages.add(message);
    }

    /**
     * Return list of messages.
     */
    public Collection getMessages() {
        return (List) this.messages;
    }

}

