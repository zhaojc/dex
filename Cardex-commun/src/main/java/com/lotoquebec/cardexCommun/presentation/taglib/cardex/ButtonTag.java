package com.lotoquebec.cardexCommun.presentation.taglib.cardex;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.Globals;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.BaseHandlerTag;
import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * Generate an IMG tag to the specified image URI.
 * <p>
 * TODO:
 * <ul>
 *   <li>make the <strong>alt</strong>, <strong>src</strong>, and
 *       <strong>lowsrc</strong> settable from properties (for i18n)</li>
 *   <li>handle <strong>onLoad</strong>, <strong>onAbort</strong>, and
 *       <strong>onError</strong> events (my JavaScript book is very old,
 *       there may be more unsupported events in the past couple of IE
 *       versions)
 * </ul>
 *
 * @author Michael Westbay
 * @author Craig McClanahan
 * @version $Revision: 1.6 $
 */

public class ButtonTag extends BaseHandlerTag {

    /**
     * L'instance du gestionnaire de journalisation.
     */
    //private final Logger      log =
    //    (Logger)Logger.getInstance(this.getClass());


    // ------------------------------------------------------------- Properties

	private String id = "";
	private String soumettre = "";
	private String post = "";
	private String urlSecurite = "";
	private String windowOpenLocation = "";
	
    public void setId(String id) {
		this.id = id;
	}

	/**
     * L'état par défaut si aucune règle de sécurité n'est applicable.
     */
    protected String defaultState= UIComponentState.ENABLED.toString();

    /**
     * Retourne l'état par dédauft si aucune règel de sécurité n'est applicable.
     */
    public String getDefaultState() {
        return (this.defaultState);
    }

    /**
     * Affecte l'état par dédauft si aucune règel de sécurité n'est applicable.
     *
     * @param securityConstraint La contrainte de sécurité applicable
     */
    public void setDefaultState(String state) {
        this.defaultState = state;
    }

    /**
     * La contrainte de sécurité applicable.
     */
    protected String securityConstraint= null;

    /**
     * Retourne la contrainte de sécurité
     */
    public String getSecurityConstraint() {
        return (this.securityConstraint);
    }

    /**
     * Affecte la contrainte de sécurité
     *
     * @param securityConstraint La contrainte de sécurité applicable
     */
    public void setSecurityConstraint(String securityConstraint) {
        this.securityConstraint = securityConstraint;
    }

    /**
     * The labelernate text to display for the image.  This is used for
     * text based browsers and/or as a "tool-tip" for the image.
     */
    protected String label = null;

    public String getLabel() {
        return (this.label);
    }

    public void setLabel(String label) {
        this.label = label;
    }


    /**
     * The message lookup key used to look up internationalized messages.
     */
    protected String labelKey = null;

    public String getLabelKey() {
        return (this.labelKey);
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }


    /**
     * The name of the servlet context attribute containing our message
     * resources.
     */
    protected String bundle = Globals.MESSAGES_KEY;
 
    public String getBundle() {
        return (this.bundle);
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }


    /**
     * The name of the servlet context attribute containing our message
     * resources.
     */
    protected String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The default Locale for our server.
     */
    protected static final Locale defaultLocale = Locale.getDefault();


    /**
     * The name of the attribute containing the Locale to be used for
     * looking up internationalized messages.
     */
    protected String locale = Globals.LOCALE_KEY;

    public String getLocale() {
        return (this.locale);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * La touche de raccourci utilisée pour activer le bouton (en combinaison avec ALT)
     */
    protected String accessKey = null;

    public String getAccessKey() {
        return (this.accessKey);
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of the IMG tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

    	// 	Evaluate the body of this tag
    	return (EVAL_BODY_INCLUDE);
    }


    /**
     * Render the end of the IMG tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {
	  	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
	  	UIComponentState state = null;
	  	
	  	if (StringUtils.isNotEmpty(getSecurityConstraint()))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, getSecurityConstraint());
	  	
	  	else if (StringUtils.isNotEmpty(soumettre))
	  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, soumettre);

	  	else if (StringUtils.isNotEmpty(post))
	  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, post);	  	
	  	
	  	else if (StringUtils.isNotEmpty(urlSecurite))
	  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, urlSecurite);

	  	else if (StringUtils.isNotEmpty(windowOpenLocation))
	  		state = GestionnaireSecurite.obtenirURLUIComponentState(subject, windowOpenLocation);
	  	
	  	else
	  		state = UIComponentState.ENABLED;
      
      if (UIComponentState.HIDDEN.equals(state)){
	return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        setDisabled(true);
      }

	// Generate the name definition or image element
	StringBuffer results = new StringBuffer("<input type=\"button\"");
		
		if (StringUtils.isNotEmpty(id)) {
	        results.append(" id=\"");
	        results.append(id);
	        results.append("\"");
	    }
        String tmp = label();
        if (tmp != null) {
            results.append(" value=\"");
            results.append(tmp);
            results.append("\"");
        }
        if (this.accessKey != null){
            results.append(" accesskey=\"");
            results.append(this.accessKey);
            results.append("\"");
        }
        	
        if (this.name != null){
            results.append(" name=\"");
            results.append(this.name);
            results.append("\"");
        }
        	
        results.append(prepareStyles());
        results.append(prepareEventHandlers());
	results.append(">");

	// Print this element to our output writer
		TagUtils tagUtils = TagUtils.getInstance();
		tagUtils.write(pageContext, results.toString());

        // Evaluate the reaminder of this page
	return (EVAL_PAGE);

    }

    protected void prepareMouseEvents(StringBuffer handlers) {
    	StringBuffer onclick = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty(getOnclick()))
    		onclick.append(getOnclick());
    	
    	if (StringUtils.isNotEmpty(soumettre)){
	    	onclick.append("; soumettre('");
	    	onclick.append(((HttpServletRequest)pageContext.getRequest()).getContextPath());
	    	onclick.append(soumettre);
	    	onclick.append("');");
    	}
    	if (StringUtils.isNotEmpty(post)){
	    	onclick.append("; post('");
	    	onclick.append(((HttpServletRequest)pageContext.getRequest()).getContextPath());
	    	onclick.append(post);
	    	onclick.append("');");
    	}
    	if (StringUtils.isNotEmpty(windowOpenLocation)){
	    	onclick.append("; windowOpenLocation('");
	    	onclick.append(((HttpServletRequest)pageContext.getRequest()).getContextPath());
	    	onclick.append(windowOpenLocation);
	    	onclick.append("');");
    	}    	
        prepareAttribute(handlers, "onclick", onclick);
        prepareAttribute(handlers, "ondblclick", getOndblclick());
        prepareAttribute(handlers, "onmouseover", getOnmouseover());
        prepareAttribute(handlers, "onmouseout", getOnmouseout());
        prepareAttribute(handlers, "onmousemove", getOnmousemove());
        prepareAttribute(handlers, "onmousedown", getOnmousedown());
        prepareAttribute(handlers, "onmouseup", getOnmouseup());
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
        label = null;
        labelKey = null;
        accessKey = null;
        bundle = Globals.MESSAGES_KEY;
        locale = Globals.LOCALE_KEY;
    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the labelernate text to be included on this generated element,
     * or <code>null</code> if there is no such text.
     *
     * @exception JspException if an error occurs
     */
    protected String label() throws JspException {

        if (this.label != null) {
            if (this.labelKey != null) {
                JspException e = new JspException
                    ("You may specify either label or labelKey but not both");
                TagUtils tagUtils = TagUtils.getInstance();
                tagUtils.saveException(pageContext, e);
                throw e;
            } else {
                return (this.label);
            }
        } else if (this.labelKey != null) {
            MessageResources resources = (MessageResources)
                pageContext.getAttribute(this.bundle,
                                         PageContext.APPLICATION_SCOPE);
            if (resources == null) {
                JspException e = new JspException
                    ("Cannot find message resources bundle under key '" + this.bundle + "'");
                throw e;
            }
            Locale locale = null;
            try {
                locale = (Locale)
                    pageContext.getAttribute(this.locale,
                                             PageContext.SESSION_SCOPE);
            } catch (IllegalStateException e) {
                locale = null; // Invalidated session
            }
            if (locale == null)
                locale = defaultLocale;
            return (resources.getMessage(locale, this.labelKey));
        } else {
            return (null);
        }
    }

	public void setSoumettre(String soumettre) {
		this.soumettre = soumettre;
	}

	public void setPost(String post) {
		this.post = post;
	}
	
	public void setUrlSecurite(String urlSecurite) {
		this.urlSecurite = urlSecurite;
	}
    
	public void setWindowOpenLocation(String windowOpenLocation) {
		this.windowOpenLocation = windowOpenLocation;
	}
	
}
