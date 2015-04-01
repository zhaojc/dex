package com.lotoquebec.iris.utilities.xml;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;

/** 
 * <p>Default string &lt;-&gt; object conversion strategy.</p>
 * <p>
 * This delegates to ConvertUtils except when the type 
 * is assignable from <code>java.util.Date</code>
 * but not from <code>java.sql.Date</code>.
 * In this case, the format used is (in ISO8601 terms) 
 * <code>CCYY-MM-DDThh:mm:ss.sss</code>.
 * This is the same as the output of the toString method on java.util.Date.
 * </p>
 * <p>
 * This should preserve the existing symantic behaviour whilst allowing round tripping of dates
 * (given the default settings).
 * </p>
 * 
 * @author <a href="henry.voyer@loto-quebec.com">Henry Voyer</a>
 * @version $Revision: 1.5 $ 30/10/2003
 * 
 */
public class CustomObjectStringConverter extends ConvertUtilsObjectStringConverter
{

	private static final long serialVersionUID = 3216097023860696448L;
	/** Formats Dates to Strings and Strings to Dates */
	transient ISO8601DateFormatter formatter = new ISO8601DateFormatter();

	/**
	  * Converts an object to a string representation using ConvertUtils.
	  * If the object is a java.util.Date and the type is java.util.Date 
	  * but not java.sql.Date
	  * then SimpleDateFormat formatting to 
	  * <code>EEE MMM dd HH:mm:ss zzz yyyy</code>
	  * will be used. 
	  * (This is the same as java.util.Date toString would return.)
	  *
	  * @param object the object to be converted, possibly null
	  * @param type the property class of the object, not null
	  * @param flavour a string allow symantic differences in formatting 
	  * to be communicated (ignored)
	  * @param context convert against this context not null
	  * @return a String representation, not null
	  */
	public String objectToString(
		Object object,
		Class type,
		String flavour,
		Context context)
	{
		if (object != null)
		{

			if (isTimestamp(type))
			{
				Timestamp timestamp = (Timestamp) object;
				return timestamp.toString();
			}

			if (isDate(type))
			{

				return formatter.format((Date) object);
			}

			// use ConvertUtils implementation
			return super.objectToString(object, type, flavour, context);

		}
		return "";
	}

	/**
	  * Converts an object to a string representation using ConvertUtils.
	  * 
	  * @param value the String to be converted, not null
	  * @param type the property class to be returned (if possible), not null
	  * @param flavour a string allow symantic differences 
	  * in formatting to be communicated (ignored)
	  * @param context not null
	  * @return an Object converted from the String, not null
	  */
	public Object stringToObject(
		String value,
		Class type,
		String flavour,
		Context context)
	{

		try
		{

			if (isTimestamp(type))
			{
				return Timestamp.valueOf(value);
			}

			if (isDate(type))
			{

				long val = formatter.parse(value);

				return new Date(val);
			}

		}
		catch (ParseException ex)
		{
			handleException(ex);
			// this supports any subclasses that do not which to throw exceptions
			// probably will result in a problem when the method will be invoked
			// but never mind
			return value;
		}

		// use ConvertUtils implementation
		return super.stringToObject(value, type, flavour, context);

	}

	private boolean isCollection(Class type)
	{
		return Collection.class.isAssignableFrom(type);
	}

	/** 
	  * Allow subclasses to use a different exception handling strategy.
	  * This class throws a <code>org.apache.commons.beanutils.ConversionException</code>
	  * when conversion fails.
	  * @param e the Exception to be handled
	  * @throws org.apache.commons.beanutils.ConversionException when conversion fails
	  */
	protected void handleException(Exception e)
	{
		throw new ConversionException(
			"String to object conversion failed: " + e.getMessage(),
			e);
	}

	/**
	  * Is the given type a java.util.Date but not a java.sql.Date?
	  * @param type test this class type
	  * @return true is this is a until date but not a sql one
	  */
	private boolean isDate(Class type)
	{

		return java.util.Date.class.isAssignableFrom(type);
	}

	/**
	  * Is the given type a java.util.Date but not a java.sql.Date?
	  * @param type test this class type
	  * @return true is this is a until date but not a sql one
	  */
	private boolean isTimestamp(Class type)
	{

		return java.sql.Timestamp.class.isAssignableFrom(type);
	}
}
