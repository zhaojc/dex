	package com.lotoquebec.cardexCommun.presentation.taglib.html;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.IteratorAdapter;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ResponseUtils;


/**
 * Tag for creating multiple &lt;select&gt; options from a collection.  The
 * associated values displayed to the user may optionally be specified by a
 * second collection, or will be the same as the values themselves.  Each
 * collection may be an array of objects, a Collection, an Enumeration,
 * an Iterator, or a Map.
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 *
 * @author Florent Carpentier
 * @author Craig McClanahan
 */
public class SwitchBoxOptionsTag extends TagSupport {

    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");

    /**
     * The name of the collection containing beans that have properties to
     * provide both the values and the labels (identified by the
     * <code>property</code> and <code>labelProperty</code> attributes).
     */
    protected String collection = null;

    protected boolean selected = false;

    public boolean getSelected() {
        return (this.selected);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCollection() {
        return (this.collection);
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }


    /**
     * The name of the bean containing the labels collection.
     */
    protected String labelName = null;

    public String getLabelName() {
	return labelName;
    }

    public void setLabelName(String labelName) {
	this.labelName = labelName;
    }

    /**
     * The bean property containing the labels collection.
     */
    protected String labelProperty = null;

    public String getLabelProperty() {
	return labelProperty;
    }

    public void setLabelProperty(String labelProperty) {
	this.labelProperty = labelProperty;
    }

    /**
     * The name of the bean containing the values collection.
     */
    protected String name=null;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The name of the property to use to build the values collection.
     */
    protected String property=null;

    public String getProperty() {
	return property;
    }

    public void setProperty(String property) {
	this.property = property;
    }


    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */

    public int doStartTag() throws JspException {
	return SKIP_BODY;
    }

    /**
     * Process the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
	// Acquire the select tag we are associated with
	SelectCardexTag selectTag =
	  (SelectCardexTag) pageContext.getAttribute(Constants.SELECT_KEY);
	if (selectTag == null)
	    throw new JspException
	        (messages.getMessage("optionsTag.select"));
	StringBuffer sb = new StringBuffer();

        // If a collection was specified, use that mode to render options
        if (collection != null) {
            Iterator collIterator = getIterator(collection, null);
            while (collIterator.hasNext()) {

                Object bean = collIterator.next();
                Object value = null;
                Object label = null;;

                try {
                    value = PropertyUtils.getProperty(bean, property);
                    if (value == null)
                        value = "";
                } catch (IllegalAccessException e) {
                    throw new JspException
                        (messages.getMessage("getter.access",
                                             property, collection));
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    throw new JspException
                        (messages.getMessage("getter.result",
                                             property, t.toString()));
                } catch (NoSuchMethodException e) {
                    throw new JspException
                        (messages.getMessage("getter.method",
                                             property, collection));
                }

                try {
                    if (labelProperty != null)
                        label =
                            PropertyUtils.getProperty(bean, labelProperty);
                    else
                        label = value;
                    if (label == null)
                        label = "";
                } catch (IllegalAccessException e) {
                    throw new JspException
                        (messages.getMessage("getter.access",
                                             labelProperty, collection));
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    throw new JspException
                        (messages.getMessage("getter.result",
                                             labelProperty, t.toString()));
                } catch (NoSuchMethodException e) {
                    throw new JspException
                        (messages.getMessage("getter.method",
                                             labelProperty, collection));
                }


                String stringValue = value.toString();

                if (selected) {
                  if (selectTag.isMatched(stringValue)){
                    addOption(sb, stringValue, label.toString());
                  }
                }else{
                  if (!selectTag.isMatched(stringValue)){
                    addOption(sb, stringValue, label.toString());
                  }
                }


            }

        }

        // Otherwise, use the separate iterators mode to render options
        else {

              // Construct iterators for the values and labels collections
              Iterator valuesIterator = getIterator(name, property);
              Iterator labelsIterator = null;
              if ((labelName == null) && (labelProperty == null))
                  labelsIterator = getIterator(name, property); // Same coll.
              else
                  labelsIterator = getIterator(labelName, labelProperty);

              // Render the options tags for each element of the values coll.
              while (valuesIterator.hasNext()) {
                  String value = valuesIterator.next().toString();
                  String label = value;
                  if (labelsIterator.hasNext())
                      label = labelsIterator.next().toString();
                      if (selected) {
                        if (selectTag.isMatched(value)){
                          addOption(sb, value, label.toString());
                        }
                      }else{
                        if (!selectTag.isMatched(value)){
                          addOption(sb, value, label.toString());
                        }
                      }
              }
	}

	// Render this element to our writer
        tagUtils.write(pageContext, sb.toString());

        // Evaluate the remainder of this page
	return EVAL_PAGE;

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
        collection = null;
	labelName = null;
	labelProperty = null;
	name = null;
	property = null;
        selected = false;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Add an option element to the specified StringBuffer based on the
     * specified parameters.
     *
     * @param sb StringBuffer accumulating our results
     * @param value Value to be returned to the server for this option
     * @param label Value to be shown to the user for this option
     * @param matched Should this value be marked as selected?
     */
    protected void addOption(StringBuffer sb, String value, String label) {
        if ((value != null && value.trim().length() > 0) &&
            (label != null && label.trim().length() > 0)
           ){
          sb.append("<option value=\"");
          sb.append(value);
          sb.append("\"");
          sb.append(">");
          sb.append(ResponseUtils.filter(label));
          sb.append("</option>\r\n");
        }

    }


    /**
     * Return an iterator for the option labels or values, based on our
     * configured properties.
     *
     * @param name Name of the bean attribute (if any)
     * @param property Name of the bean property (if any)
     *
     * @exception JspException if an error occurs
     */
    protected Iterator getIterator(String name, String property)
        throws JspException {

	// Identify the bean containing our collection
	String beanName = name;
	if (beanName == null)
	    beanName = Constants.BEAN_KEY;
	Object bean = pageContext.findAttribute(beanName);
	if (bean == null)
	    throw new JspException
	        (messages.getMessage("getter.bean", beanName));

	// Identify the collection itself
	Object collection = bean;
	if (property != null) {
	    try {
		collection = PropertyUtils.getProperty(bean, property);
	    } catch (IllegalAccessException e) {
		throw new JspException
		    (messages.getMessage("getter.access", property, name));
	    } catch (InvocationTargetException e) {
		Throwable t = e.getTargetException();
		throw new JspException
		    (messages.getMessage("getter.result",
					 property, t.toString()));
	    } catch (NoSuchMethodException e) {
		throw new JspException
		    (messages.getMessage("getter.method", property, name));
	    }
	}

	// Construct and return an appropriate iterator
	if (collection.getClass().isArray())
	    collection = Arrays.asList((Object[]) collection);
	if (collection instanceof Collection)
	    return (((Collection) collection).iterator());
	else if (collection instanceof Iterator)
	    return ((Iterator) collection);
	else if (collection instanceof Map)
	    return (((Map) collection).entrySet().iterator());
    else if (collection instanceof Enumeration)
	    return( new IteratorAdapter((Enumeration)collection));
	else
	    throw new JspException
	        (messages.getMessage("optionsTag.iterator",
	                             collection.toString()));

    }
}