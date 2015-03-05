package com.lotoquebec.cardexCommun.presentation.taglib.cardex;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.taglib.html.OptionTag;
import org.apache.struts.util.IteratorAdapter;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListeUtil;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Affiche une valeur à l'écran basé sur le contenu d'une
 * collection contenant les beans qui possèdent des propriétés
 * pour identifier les valeurs et les étiquettes (identifié par les
 * attributs <code>valueProperty</code> et <code>labelProperty</code>)
 * , et un autre bean (identifié par les attributs <code>name</code> et
 * <code>property</code>)servant à sélectionné l'étiquette a afficher dans
 * cette collection.
 *
 * @see org.apache.struts.taglib.html.LinkTag
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $ $Date: 2002/03/11 16:48:51 $
 */
//public class OptionsRendererTag extends TagSupport {
public class OptionsRendererTag extends OptionTag {

    /**
     * Le nom de la collection contenant les beans qui possèdent des propriétés
     * pour identifier les valeurs et les étiquettes (identifié par les
     * attributs <code>valueProperty</code> et <code>labelProperty</code>)
     */
    protected String collection = null;
    private String classe = "";
    private String valeurTableValeur = "";
    private String valeurDiscriminant = "";
    private String actionSecurite = "";
    private final static String scope = "session";

    public String getCollection() {
        return (this.collection);
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * La contrainte de sécurité applicable.
     */
    protected String securityConstraint= null;

    /**
     * Retourne la contrainte de sécurité
     */
    public String getSecurityConstraint() {
        return securityConstraint;
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
     * Le nom de la propriété contenant l'étiquette pour un bean de la collection.
     */
    protected String labelProperty = null;

    public String getLabelProperty() {
	return labelProperty;
    }

    public void setLabelProperty(String labelProperty) {
	this.labelProperty = labelProperty;
    }

    /**
     * Le nom de la bean contenant la valeur a sélectionné dans la collection.
     */
    protected String name=null;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * Le nom de la propriété de la bean contenant la valeur a sélectionné dans la collection.
     */
    protected String property=null;

    public String getProperty() {
	return property;
    }

    public void setProperty(String property) {
	this.property = property;
    }


    /**
     * Le nom de la propriété contenant la valeur pour un bean de la collection.
     */
    protected String valueProperty=null;

    public String getValueProperty() {
	return valueProperty;
    }

    public void setValueProperty(String property) {
	this.valueProperty = property;
    }

    /**
     * Traitement de début de tag .
     *
     * @exception JspException Si exception  JSP  survient
     */

    public int doStartTag() throws JspException {
	return SKIP_BODY;
    }

    /**
     * Traitement de fin de tag .
     *
     * @exception JspException Si exception  JSP  survient
     */
    public int doEndTag() throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)pageContext.findAttribute(AuthenticationSubject.class.getName());
    	Object formulaire = tagUtils.lookup(pageContext, getName(), null);
		UIComponentState state = null;
    	
	  	if (StringUtils.isNotEmpty(securityConstraint))
	  		state = GestionnaireSecurite.obtenirAdhocUIComponentState(subject, securityConstraint);
	  	else
	  		state = GestionnaireSecurite.obtenirFormulaireUIComponentState(subject, formulaire.getClass(), getProperty());

      if (UIComponentState.HIDDEN.equals(state)){
	      return (SKIP_BODY);
      }else if (UIComponentState.DISABLED.equals(state)) {
        setDisabled(true);
      }

	StringBuffer sb = new StringBuffer();

        // Si une collection est spécifé, la collection est utilisé pour
        // recherché la valeur a affiché
        if (collection != null) {
            Iterator collIterator = getIterator(collection, null);
            while (collIterator.hasNext()) {

                Object bean = collIterator.next();
                Object value = null;
                Object label = null;;

                try {
                    value = PropertyUtils.getProperty(bean, valueProperty);
                    if (value == null)
                        value = "";
                } catch (IllegalAccessException e) {
                    throw new JspException("IllegalAccess in the OptionsRendererTag for the valueProperty");
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    throw new JspException("NoSuchMethodException in the OptionsRendererTag for the valueProperty:" + t.toString());
                } catch (NoSuchMethodException e) {
                    throw new JspException("NoSuchMethodException in the OptionsRendererTag for the valueProperty");
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
                    throw new JspException("IllegalAccess in the OptionsRendererTag for the labelProperty");
                } catch (InvocationTargetException e) {
                    Throwable t = e.getTargetException();
                    throw new JspException("InvocationTargetException in the OptionsRendererTag for the valueProperty:" + t.toString());
                } catch (NoSuchMethodException e) {
                    throw new JspException("NoSuchMethodException in the OptionsRendererTag for the labelProperty");
                }


                String stringValue = value.toString();
                if (isMatched(stringValue)){
                  sb.append(label);
                }
            }

        }else if (StringUtils.isNotEmpty( classe )){
        	ListeCache listeCache = ListeCache.getInstance();
	        String value = (String) tagUtils.lookup(pageContext, name, property, scope);
	        
        	try {
        		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        		CleListe cleListe = CleListeUtil.creerCleListe((CardexAuthenticationSubject) subject, request, classe, valeurTableValeur, valeurDiscriminant, actionSecurite);	
				sb.append( listeCache.obtenirLabel((CardexAuthenticationSubject) subject, value, cleListe) );
			} catch (BusinessResourceException e) {
				e.printStackTrace();
                throw new JspException("Problème de disponibilité de base de donnée");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new JspException("Problème de nom de classe");
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new JspException("Problème de nom de classe");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new JspException("Problème de nom de classe");
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				throw new JspException("Problème de nom de classe");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				throw new JspException("Problème de nom de classe");
			}
        }

	// Affiche L'élément dans le writer
        tagUtils.write(pageContext, sb.toString());

        // Evaluation du reste de la page
	return EVAL_PAGE;

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
        collection = null;
	valueProperty = null;
	labelProperty = null;
	name = null;
	property = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Does the specified value match one of those we are looking for?
     *
     * @param value Value to be compared
     */
    public boolean isMatched(String value) throws JspException {
    	TagUtils tagUtils = TagUtils.getInstance();
        Object bean = pageContext.findAttribute(name);
        String match = null;
        if (bean == null) {
            JspException e = new JspException("Unable to find bean name '"+name+"' in OptionsRendererTag.");
            tagUtils.saveException(pageContext, e);
            throw e;
        }
        if (property != null) {
          try {
              match = BeanUtils.getProperty(bean, property);
              if (match == null)
                  match = "";
          } catch (IllegalAccessException e) {
          	tagUtils.saveException(pageContext, e);
              throw new JspException("Unable to access property '"+property+"' for bean name '"+name+"' in OptionsRendererTag.");
          } catch (InvocationTargetException e) {
              Throwable t = e.getTargetException();
              tagUtils.saveException(pageContext, t);
              throw new JspException("Unable to access property '"+property+"' for bean name '"+name+"' in OptionsRendererTag.");
          } catch (NoSuchMethodException e) {
          	tagUtils.saveException(pageContext, e);
              throw new JspException("Unable to access property '"+property+"' for bean name '"+name+"' in OptionsRendererTag.");
          }
        }else {
          match = bean.toString();
        }

        if ((match == null) || (value == null)){
            return (false);
        }
        if (value.equals(match)) {
            return (true);
        }
        return (false);

    }


    /**
     * Retourne un iterator pour les étiquettes et valeurs, basé sur
     * les paramètres spécifiés.
     *
     * @param name Nom de l'attribut de la bean
     * @param property Nom de la propriété de la bean
     *
     * @exception JspException if an error occurs
     */
    protected Iterator getIterator(String name, String property)
        throws JspException {

	// Identification de la bean contenant la collection
	String beanName = name;
	if (beanName == null)
	    beanName = Constants.BEAN_KEY;
	Object bean = pageContext.findAttribute(beanName);
	if (bean == null)
            throw new JspException("Unable to find bean name '"+beanName+"' in OptionsRendererTag.");
	Object collection = bean;

	// Construit et retourrn l'iterator approprié
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
            throw new JspException("Invalid collection type pass to OptionsRendererTag.");
    }
    
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}

	public void setValeurTableValeur(String valeurTableValeur) {
		this.valeurTableValeur = valeurTableValeur;
	}

	public void setActionSecurite(String actionSecurite) {
		this.actionSecurite = actionSecurite;
	}

	public void setValeurDiscriminant(String valeurDiscriminant) {
		this.valeurDiscriminant = valeurDiscriminant;
	}
	
	
}