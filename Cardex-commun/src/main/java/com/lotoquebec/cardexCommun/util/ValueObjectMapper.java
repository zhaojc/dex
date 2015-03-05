package com.lotoquebec.cardexCommun.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

public class ValueObjectMapper {

    public static void convert(Object source, Object dest)
    throws ValueObjectMapperException {
	try {
	    Map mapOriginal = BeanUtils.describe(source);
	    List listMethodDate = obtenirListChaine(mapOriginal, "date");
	    Map mapRetirer = retirerMap(mapOriginal, listMethodDate);
	    
	    BeanUtils.populate(dest, mapRetirer);
	    
	    populateDate(dest, mapOriginal, listMethodDate);
	    
	} catch (IllegalAccessException iae) {
	    throw new ValueObjectMapperException(iae);
	} catch (InvocationTargetException ite) {
	    throw new ValueObjectMapperException(ite);
	} catch (NoSuchMethodException nsme) {
	    throw new ValueObjectMapperException(nsme);
	}
}

public static Object construireParameterMap(Map parameterMap, Object o){

try {
    List listMethodDate = obtenirListChaine(parameterMap, "date");
    Map mapRetirer = retirerMap(parameterMap, listMethodDate);
    
    BeanUtils.populate(o, mapRetirer);
    
    populateDateParameterMap(o, parameterMap, listMethodDate);			
} catch (IllegalAccessException e1) {
	e1.printStackTrace();
} catch (InvocationTargetException e1) {
	e1.printStackTrace();
}

return o;
}    

private static void populateDate(Object dest, Map map, List listMethodDate) {
Iterator iter = listMethodDate.iterator();

while (iter.hasNext()) {
    String key = (String) iter.next();
    String dateHeureDu = "dateHeureDebutDu";
    String dateHeureAu = "dateHeureDebutAu";
    Date date = null;
    try {
        if(key.equals(dateHeureDu) || key.equals(dateHeureAu)){
             date = TimestampFormat.parse((String) map.get(key), Locale.CANADA_FRENCH, true);
        }else{
             date = TimestampFormat.parse((String) map.get(key));
        }
        
        if (date != null)
            BeanUtils.copyProperty(dest, key, (Date) date);

    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
}       
}


private static void populateDateParameterMap(Object dest, Map parameterMap, List listMethodDate) {
Iterator iter = listMethodDate.iterator();

while (iter.hasNext()) {
	String key = (String) iter.next();
	
	try {
		String strDate = obtenirPremiereString(parameterMap, key);
		Date date = null;
		
		if (strDate.length() == 10)
			date = TimestampFormat.parse(strDate);

		if (strDate.length() == 19)
			date = TimestampFormat.parseFrenchTemps(strDate);
		
		if (date != null)
			BeanUtils.copyProperty(dest, key, (Date) date);
		
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
}		
}	    

public static String obtenirPremiereString(Map parameterMap, String key){
	String[] arrayString = (String[]) parameterMap.get(key);
	return arrayString[0];
}

private static List obtenirListChaine(Map map, String chaine){
	List list = new ArrayList();
	Iterator iter = map.keySet().iterator();
	
	while (iter.hasNext()) {
		String key = (String) iter.next();
		
		if (chaine.equals(StringUtils.left(key, chaine.length())))
			list.add(key);
	}
	return list;
}

private static Map retirerMap(Map map, List list){
	Map retourMap = new HashMap();
	retourMap.putAll(map);
	Iterator iter = list.iterator();
	
	while (iter.hasNext()) {
		String key = (String) iter.next();
		retourMap.remove(key);
	}
	return retourMap;
}    

private static String obtenirLongValeurListe(CardexAuthenticationSubject subject, String valeur, String valeurDefault, CleListe cleListe) throws BusinessResourceException{
	ListeCache listeCache = ListeCache.getInstance();
	long l = listeCache.obtenirKeyDeLabel(subject, valeur, cleListe);
	
	if (l != 0)
		return String.valueOf(l);
	return valeurDefault;
	}

}
