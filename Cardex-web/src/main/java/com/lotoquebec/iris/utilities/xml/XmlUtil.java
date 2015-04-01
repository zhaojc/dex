package com.lotoquebec.iris.utilities.xml;

import java.io.StringWriter;

/**
 * 
 * XmlUtils permet de prendre un objet et le transformer en XML utilisant
 * le standard tel que decrit dans le document de format d'�change EAI Version 1.0
 * </p>
 * <p> 
 * L'outils utilis� pour le marshaling xml est 
 * <a href="http://jakarta.apache.org/commons/betwixt/index.html">betwixt</a> 
 * de jakarta.
 * </p> 
 * 
 * Voici un exemple de son utilisation <br>
 *  <br>
 * Pour la transformation xml <br>
 *  <br>
 * DTO_Exemple exemple = getExemple(); // chercher un DTO exemple <br>
 *  <br>
 * XmlUtil xmlUtils = new XmlUtil(); <br>
 *  <br>
 * String xml_value = xmlUtils.toXml(exemple);  // xml_value contient le String xml <br>
 *  <br>
 * Pour la transformation objet <br>
 *  <br>
 * DTO_Exemple exemple2 = (DTO_Exemple) xmlUtils.toObject(xml_value,DTO_Exemple.class); <br>
 *  <br>
 * <r><b>Warning</b></r></br>
 * Il faut que le DTO utilis� respecte le standard Java Bean. <br>
 * CAD que l'objet � transformer en XML contienne les getter, setter et les m�thodes add<Element> pour les
 * attributs de type Collection. <br>
 * ex. <br>
 *  <br>
 * 
 * public class DTO_Exemple <br>
 * { <br>
 *  private String valeur = null; <br>
 *  private Collection ensemble = new ArrayList(); <br>
 *  <br>
 *  public String getValeur() <br>
 *  { return valeur;} <br>
 *  <br>
 *  public void setValeur(String valeur) <br>
 *  { this.valeur = valeur;} <br>
 *  <br>
 *  public void addEnsemble(String valeur) <br>
 *  { ensemble.add(valeur);  } <br>
 *   <br>
 *  public Collection getEnsemble() <br>
 *  { return ensemble; } <br>
 *  <br>
 *  public void setEnsemble(Collection ensemble) <br>
 *  { <br>
 *    this.ensemble = ensemble; <br>
 *  }  <br>
 * } <br>
 * 
 * @author <a href="henry.voyer@loto-quebec.com">Henry Voyer</a>
 * @version $Revision: 1.0 $ 30/10/2003
 * 
 */
public class XmlUtil
{

	private static BindingConfiguration configuration =
		new BindingConfiguration(new CustomObjectStringConverter(), true);

	/**
	 * WriteToXml transforme le contenu de l'objet en xml
	 * @param object
	 * @return String
	 * @throws Exception
	 */
	public String toXml(Object object) throws Exception
	{
		return toXml(object, false);
	}

	/**
	 * WriteToXml transforme le contenu de l'objet en xml
	 * @param object object to marshal
	 * @param boolean isWriteEmptyElements
	 * @return String
	 * @throws Exception
	 */
	public String toXml(Object object, boolean isWriteEmptyElements)
		throws Exception
	{
		if (object == null)
		{
			return "";
		}

		StringWriter outputWriter = new StringWriter();

		BeanWriter writer = new BeanWriter(outputWriter);

		writer.setBindingConfiguration(configuration);
		writer.getBindingConfiguration().setMapIDs(false);

		writer.setWriteEmptyElements(isWriteEmptyElements);
		writer.getXMLIntrospector().setAttributesForPrimitives(false);
		writer.enablePrettyPrint();
		
		// set a custom name mapper for attributes
		writer.getXMLIntrospector().setAttributeNameMapper(
			new HyphenatedNameMapper());

		writer.getXMLIntrospector().setAttributeNameMapper(
			new DecapitalizeNameMapper());

		// set a custom name mapper for elements
		writer.getXMLIntrospector().setElementNameMapper(
			new HyphenatedNameMapper());

		writer.getXMLIntrospector().setElementNameMapper(
			new DecapitalizeNameMapper());

		String name = nameStrategy(object.getClass().getName());

		writer.write(name, object);

		return outputWriter.toString();
	}

	/**
	 * ReadFromXml transforme un text xml en objet
	 * @param xml
	 * @param classType
	 * @return Object
	 * @throws Exception
	 */
	public Object toObject(String xml, Class classType) throws Exception
	{
		if (xml == null || xml.equals("") || classType == null)
		{
			return null;
		}
		BeanReader beanReader = new BeanReader();

		beanReader.getXMLIntrospector().setWrapCollectionsInElement(true);

		beanReader.setBindingConfiguration(configuration);

		return null;
	}

	/**
	 * Method nameStrategy continent les strategies utilis�s pour 
	 * obtenir le nom de l'objet
	 * @param name
	 * @return String
	 */
	private String nameStrategy(String name)
	{
		// Remove DTO reference         
		//name = StringUtils.prechomp(name, "DTO_");

		// Remove the package reference
		int index = name.lastIndexOf(".");
		int size = name.length();

		if (index != -1 && size > 1)
		{
			name = name.substring(index + 1, size);
		}

		// uncapitalise
		//name = StringUtils.uncapitalise(name);

		return name;
	}

}
