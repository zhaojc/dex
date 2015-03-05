package com.lotoquebec.cardexCommun.util.validation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.FieldChecks;
import org.apache.struts.validator.Resources;

public class CardexValidator extends FieldChecks {

    /**
     * <p>Checks if the field matches the decimal format in the field's pattern attribute.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
	public static boolean validateDecimal(Object bean,
            ValidatorAction va, 
			Field field,
            ActionMessages errors,
            Validator validator,
            HttpServletRequest request) {
		 boolean bValid = true;
         String pattern = field.getVarValue("pattern");
         Locale locale = RequestUtils.getUserLocale(request, Globals.LOCALE_KEY);

	 if (field.getProperty() != null && field.getProperty().length() > 0 && pattern != null && pattern.length() > 0) {
	   try {
              String value = ValidatorUtils.getValueAsString(bean, field.getProperty());

              if (value !=null && value.trim().length() > 0){
                  if (locale.getLanguage().equalsIgnoreCase("fr") && value.indexOf('.') != -1 ){
                  	//{@link org.apache.struts.taglib.TagUtils#getActionErrors(PageContext,String)} instead.
                    errors.add(field.getProperty(), Resources.getActionMessage(validator, request, va, field));
                    bValid = false;
                  }else {
                    DecimalFormat formatter = (DecimalFormat)DecimalFormat.getInstance(locale);
                    formatter.applyPattern(pattern);
                    Number number = formatter.parse(value);
                  }
              }
    	   } catch (ParseException e) {
              errors.add(field.getProperty(), Resources.getActionMessage(validator, request, va, field));
    	      bValid = false;
           }
        } else {
           errors.add(field.getProperty(), Resources.getActionMessage(validator, request, va, field));
           bValid = false;
        }

        return bValid;
    }

}