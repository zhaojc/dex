package com.lotoquebec.iris.infrastructure.services.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <p>Cette classe permet de faire le parsing d'un fichier de configuration XML.</p>
 */
public class ConfigXML
{
	/** Un EntityResolver optionnel pour la localisation des DTD */
	private EntityResolver entityResolver;
	private boolean validating = false;

	/**
	 * <p>Constructeur par d�faut.</p>
	 */
	private ConfigXML()
	{
		super();
	}

	/**
	 * <p>Retourne une nouvelle instance de la classe.</p>
	 * 
	 * @return une instance de la classe
	 */
	public static ConfigXML getInstance()
	{
		return new ConfigXML();
	}

	/**
	 * <p>Retourne une nouvelle instance de la classe en lui assignant un EntityResolver
	 * pour la localization des DTD.</p>
	 * 
	 * @return une instance de la classe � laquelle est associ� l'EntityResolver
	 */
	public static ConfigXML getInstance(EntityResolver resolver)
	{
		ConfigXML instance = new ConfigXML();
		instance.setEntityResolver(resolver);
		return instance;
	}

	/**
	 * Permet de charger un document XML et de retourner son �l�ment racine. Effectue la validation
	 * du contenue du document XML avec sa DTD si la m�thode {@link #isValidating() isValidating} 
	 * retourne <code>true</code>.
	 * 
	 * @param fileName le nom du fichier qui doit �tre charg�
	 * 
	 * @exception IrisInfraException toute exception survenant lors du chargement du fichier
	 */
	public Element loadDocument(String fileName) throws Exception
	{
		InputStream is = getClass().getResourceAsStream(fileName);

		if (is == null)
		{
		    throw new Exception("Unable to load " + fileName);	
		}

		return loadDocument(is);
	}

	/**
	 * Permet de charger un document XML et de retourner son �l�ment racine. Effectue la validation
	 * du contenue du document XML avec sa DTD si la m�thode {@link #isValidating() isValidating} 
	 * retourne <code>true</code>.
	 * 
	 * @param stream le flux � partir duquel est charg� le fichier
	 * 
	 * @exception Exception toute exception survenant lors du chargement du fichier
	 */
	public Element loadDocument(InputStream stream) throws Exception
	{
		Document doc = null;
		Element root = null;
		String location = null;

		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setValidating(validating);
			
			DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
			parser.setErrorHandler(new SaxErrorHandler());
			
			// Si on poss�de un EntityResolver, on le passe au parser
			if ( this.getEntityResolver() != null )
				parser.setEntityResolver(this.getEntityResolver());
			
			doc = parser.parse(stream);
			root = doc.getDocumentElement();
			root.normalize();
			return root;
		}
        catch (SAXParseException err)
        {
            
            throw new Exception("SAX Parse Exception: " + err.getMessage(),err);
        }
        catch (SAXException se)
        {
            throw new Exception("SAX Exception: " + se.getMessage(),se);
        }
        catch (IOException ioe)
        {
            throw new Exception("IO Exception: " + ioe.getMessage(),ioe);
        }
        catch (Exception pce)
        {
            throw new Exception("Exception: " + pce.getMessage(),pce);
        }
	}


	/**
	 * Permet de charger un document XML et de retourner son �l�ment racine. N'effectue aucune 
	 * validation sur le contenue du document XML m�me si la m�thode {@link #isValidating() isValidating} 
	 * retourne <code>true</code>.
	 * 
	 * @param aReader une instance de Reader
	 * 
	 * @exception Exception toute exception survenant lors du chargement du fichier
	 */
	public static Element loadDocument(Reader aReader) throws Exception
	{
		return loadDocument(aReader, null);
	}


	/**
	 * Permet de charger un document XML et de retourner son �l�ment racine. N'effectue aucune
	 * validation sur le contenue du document XML m�me si la m�thode {@link #isValidating() isValidating} 
	 * retourne <code>true</code>.
	 * 
	 * @param aReader une instance de Reader
	 * @param resolver un EntityResolver permettant de localiser les DTD
	 * 
	 * @exception Exception toute exception survenant lors du chargement du fichier
	 */
	public static Element loadDocument(Reader aReader, EntityResolver resolver) throws Exception
	{
		Document doc = null;
		Element root = null;
		String location = null;
		InputSource anInputSource = new InputSource(aReader);

		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();

			// Si on nous a fourni un EntityResolver, on le passe au parser
			if ( resolver != null )
				parser.setEntityResolver(resolver);

			doc = parser.parse(anInputSource);
			root = doc.getDocumentElement();
			root.normalize();
			return root;
		}
        catch (SAXParseException err)
        {
            throw new Exception("SAX Parse Exception: " + err.getMessage(),err);
        }
        catch (SAXException se)
        {
            throw new Exception("SAX Exception: " + se.getMessage(),se);
        }
        catch (IOException ioe)
        {
            throw new Exception("IO Exception: " + ioe.getMessage(),ioe);
        }
        catch (Exception pce)
        {
            throw new Exception("Exception: " + pce.getMessage(),pce);
        }
	}

	/**
	 * Permet de charger un document XML et de retourner son �l�ment racine. N'effectue aucune 
	 * validation sur le contenue du document XML m�me si la m�thode {@link #isValidating() isValidating} 
	 * retourne <code>true</code>.
	 * 
	 * @param data le contenu XML
	 * 
	 * @exception Exception toute exception survenant lors du chargement du fichier
	 */
	public static Element loadXMLDocument(String data) throws Exception
	{
		StringReader aReader = new StringReader(data);
		return loadDocument(aReader);

	}
	/**
	 * <p>Retourne l'instance de EntityResolver utilis�e.</p>
	 * 
	 * @return une instance de EntityResolver
	 */
	public EntityResolver getEntityResolver() {
		return entityResolver;
	}

	/**
	 * <p>Initialise l'instance de EntityResolver � utiliser.</p>
	 * 
	 * @param entityResolver l'instance de EntityResolver
	 */
	public void setEntityResolver(EntityResolver entityResolver) {
		this.entityResolver = entityResolver;
	}

	/**
	 * Retourne <code>true</code> si le document XML � analyser doit �tre valid� avec sa DTD,
	 * <code>false</code> sinon. La validation est d�sactiv� par d�faut.
	 * 
	 * @return 	<code>True</code> si le document XML � analyser doit �tre valid� avec sa DTD,
	 * 			<code>false</code> sinon.
	 */
	public boolean isValidating() {
		return validating;
	}

	/**
	 * Active la validation du document avec sa DTD si <code>validate</code> est � 
	 * <code>true</code>, et la d�sactive si <code>validate</code> est � <code>false</code>
	 * 
	 * @param validate 	Indique si la validation du document XML avec sa DTD doit �tre 
	 * 					activ� (<code>true</code>) ou d�sactiv� (<code>false</code>).
	 */
	public void setValidating(boolean validate) {
		validating = validate;
	}

}