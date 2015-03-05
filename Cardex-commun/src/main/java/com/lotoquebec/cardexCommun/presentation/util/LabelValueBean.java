package com.lotoquebec.cardexCommun.presentation.util;

import java.io.Serializable;

/**
 * Simple JavaBean to represent label-value pairs for use in collections
 * that are utilized by the <code>&lt;form:options&gt;</code> tag.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $ $Date: 2002/02/08 17:37:15 $
 */
public class LabelValueBean implements Serializable, LabelValue {


    /**
     * Construct a new LabelValueBean with the specified values.
     *
     * @param label The label to be displayed to the user
     * @param value The value to be returned to the server
     */
    public LabelValueBean(String label, String value) {
        this.label = label;
        this.value = value;
    }

    /**
     * The label to be displayed to the user.
     */
    protected String label = null;

    /* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.presentation.util.LabelValue#getLabel()
	 */
    public String getLabel() {
        return (this.label);
    }

    protected String value = null;

    /* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.presentation.util.LabelValue#getValue()
	 */
    public String getValue() {
        return (this.value);
    }

    /**
     * Return a string representation of this object.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("LabelValueBean[");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.value);
        sb.append("]");
        return (sb.toString());
    }

    public boolean equals (Object o) {
    	
    	if (o instanceof LabelValueBean == false)
    		return false;
    	
    	LabelValueBean oLabelValue = (LabelValueBean) o;
    	
    	// retrait de la comparaison avec le label à cause du problème de 
    	// doublon dans la double liste des sous catégorie
    	boolean isValueEqual = this.getValue().equals( oLabelValue.getValue() );
    	
    	return /*isLabelEqual &&*/ isValueEqual;
    }
    
    public int hashCode(){
    	return 1;
    }
}
