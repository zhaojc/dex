package com.lotoquebec.iris.infrastructure.services.messaging;

import java.io.Serializable;
import java.util.Set;

import com.lotoquebec.iris.mei.BaseDTO;


/**
 *
 * <p>Format d'un message <b>asynchrone</b> utilis� � l'interne de Iris.
 * Repr�sente la structure de donn�es d�un message <b>asynchrone</b> envoy� par Iris et destin� � Iris.</p>
 *
 * @author Jerome Caron
 * @author Marco Montestruque
 */
public class MessageAsync implements Serializable {

    /** L'initiateur */
    protected String initiateur = null;
	/** L'identifiant de la transaction */
    protected String idTrans = null;
	/** Le type de message asynchrone*/
    protected String type = null;
	/** Le type de date logique */
    protected String typeDateLogique = null;
	/** Le DTO associ� au message asynchrone */
	protected BaseDTO dto = null;
	
	/** Les trois attributs suivants servent � transmettre le contexte de s�curit� */
	protected Set principals = null;
	protected Set pubCredentials = null;

	/** Indentifiant unique pour la version de la classe. G�n�r� avec serialver.exe */
	static final long serialVersionUID = 3751947405580370222L;
	
    /**
     * <p>Constructeur public par d�faut</p>
     */
    public MessageAsync() {
    	super();
    }

    /**
     * <p>Retourne l'identifiant de la transaction</p>
     *  
     * @return l'identifiant de la transaction
     */
    public String getIdTrans() {
        return idTrans;
    }

    /**
     * <p>Retourne l'initiateur</p>
     * 
     * @return l'initiateur
     */
    public String getInitiateur() {
        return initiateur;
    }

    /**
     * <p>Initialise l'identifiant de la transaction</p>
     * 
     * @param string l'identifiant de la transaction
     */
    public void setIdTrans(String string) {
        idTrans = string;
    }

    /**
     * <p>Initialise l'initiateur</p>
     * 
     * @param string l'initiateur
     */
    public void setInitiateur(String string) {
        initiateur = string;
    }

    /**
     * <p>Retourne le DTO sous la forme d'une cha�ne XML</p>
     * 
     * @return le DTO en XML
     */
    public String getParam()
    {
    	String xml = null;
    	try
    	{
        	xml = dto.toXML();
    	}
    	catch (Exception e)
    	{
    		// Se produit si on n'arrive pas � s�rialiser en XML
			throw new RuntimeException(e.getMessage());
		}
    	return xml;
    }

    /**
     * <p>Initialise le DTO</p>
     * 
     * @param dto le DTO
     */
    public void setParam(BaseDTO dto) {
		this.dto = dto;
    }

    /**
     * <p>Retourne le type de message asynchrone</p>
     * 
     * @return le type de message
     */
    public String getType() {
        return type;
    }

    /**
     * <p>Initialise le type de message asynchrone</p>
     * 
     * @param string le type de message
     */
    public void setType(String string) {
        type = string;
    }

    /**
     * <p>Retourne le type de date logique</p>
     * 
     * @return le type de date logique
     */
    public String getTypeDateLogique() {
        return typeDateLogique;
    }

    /**
     * <p>Initialise le type de date logique</p>
     * 
     * @param string le type de date logique
     */
    public void setTypeDateLogique(String string) {
        typeDateLogique = string;
    }
    
    
	/**
	 * <p>Retourne le DTO</p>
	 * 
	 * @return le DTO
	 */
	public BaseDTO getDto() {
		return dto;
	}
	

	public Set getPrincipals() {
		return principals;
	}
	
	public void setPrincipals(Set principals) {
		this.principals = principals;
	}
	
	public Set getPubCredentials() {
		return pubCredentials;
	}
	
	public void setPubCredentials(Set pubCredentials) {
		this.pubCredentials = pubCredentials;
	}
}
