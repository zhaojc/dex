/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardexCommun.securite;

import java.io.Serializable;

/**
 * Class declaration
 *
 * @see
 * @author $Autor: $
 * @version $Revision: 1.3 $, $Date: 2002/03/11 20:38:39 $
 */
public class UIComponentState implements Serializable {
    private String state;

    /**
     * Constructor declaration
     *
     *
     * @param state
     *
     * @see
     */
    private UIComponentState(String state) {
        this.state = state;
    }

    public static final UIComponentState ENABLED =
        new UIComponentState("enabled");
    public static final UIComponentState DISABLED =
        new UIComponentState("disabled");
    public static final UIComponentState HIDDEN =
        new UIComponentState("hidden");

    /**
     * Method declaration
     *
     *
     * @param state
     *
     * @return
     *
     * @see
     */
    public static UIComponentState valueOf(String state) {
        if ("enabled".equalsIgnoreCase(state)) {
            return ENABLED;
        } else if ("disabled".equalsIgnoreCase(state)) {
            return DISABLED;
        } else if ("hidden".equalsIgnoreCase(state)) {
            return HIDDEN;
        } else {
            return ENABLED;
        }
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @see
     */
    public String toString() {
        return state;
    }

}

