/*
 * Created on 18-Nov-2008
 */
package com.lotoquebec.cardexCommun.presentation.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.struts.action.ActionForm;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class AideController {

	public static boolean isNullOrEquals(String str1, String str2){
		
        if ((str1 == null || str2 == null) || str1.equals(str2) )
        	return true;
        return false;
	}
	
	/**
	 * Retrait du caractère '<' pour prévenir les (XSS)
	 * Cross-Site Scripting réfléchi
	 * @param actionForm
	 */
	public static void retraitXSS(ActionForm actionForm){
		Class[] classGet = new Class[0];
		Class[] classSet = new Class[]{("").getClass()};
		
		if (actionForm == null)
			return;
		Field[] fields = actionForm.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			// Le champ narration avec format contient des balises que nous devons conserver.
			if ("narrationAvecFormat".equals(field.getName()) == false && "narrationTemporaire".equals(field.getName()) == false  
			&& String.class.isAssignableFrom(field.getType())){

				try {
					Method methodGet =  actionForm.getClass().getDeclaredMethod("get"+StringUtils.capitalise(field.getName()), classGet);
					Method methodSet =  actionForm.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), classSet);
					String string = (String) methodGet.invoke(actionForm, new Object[0]);
					
					if (string.indexOf("<") > -1){
						string = StringUtils.replace(string, "<", "");
						
						Object[] setValeur = {string};
						methodSet.invoke(actionForm, setValeur);
					}
				} catch (Exception e) {
					// Il peut y avoir certaines méthodes qui n'ont pas de setter. c'est ok.
				} 
			}
		}
	}
	
}
