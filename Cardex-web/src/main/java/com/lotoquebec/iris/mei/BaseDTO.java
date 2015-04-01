package com.lotoquebec.iris.mei;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import com.lotoquebec.iris.utilities.xml.XmlUtil;

/**
 * @author desclas
 *
 */
public class BaseDTO implements Serializable
{

	private String id;
	private Timestamp timestamp;
	private Collection codes = new ArrayList();
	private Set sectionsModifiees;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Returns the timestamp used in the Hybernator persistance framerwork.
	 * @return Timestamp
	 */
	public Timestamp getTimestamp()
	{
		return timestamp;
	}

	/**
	 * Sets the timestamp used in the Hybernator persistance framerwork.
	 * @param timestamp The timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp)
	{
		this.timestamp = timestamp;
	}

	/**
	 * Methode utilise pour la transformation XML des MEI
	 */
	public String toXML() throws Exception
	{
		return new XmlUtil().toXml(this);
	}
	/**
	 * @return
	 */
	public Collection getCodes()
	{
		return codes;
	}

	/**
	 * @param collection
	 */
	public void setCodes(Collection collection)
	{
		codes = collection;
	}

	/**
	 * 
	 * @param code
	 */
	public void addCode(Exception code)
	{
		codes.add(code);
	}

	/**
	 * Initialise la liste des sections modifi�es
	 */
	public void setSectionsModifiees(Set liste)
	{
		this.sectionsModifiees = liste;
	}
	
	/**
	 * Retourne la liste des sections modifi�es
	 */
	public Set getSectionsModifiees()
	{
		return this.sectionsModifiees;
	}
	
	public boolean equals(Object o) {
		return (o instanceof BaseDTO) && (getId() != null) && (getId()).equals(((BaseDTO)o).getId());
	}
	
	public int hashCode() {
		return (getId() == null) ? super.hashCode() : (getId()).hashCode();
	}	
}
