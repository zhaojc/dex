package com.lotoquebec.cardexCommun.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @deprecated Utiliser le preparer statement avec des "?"
 */
public class VerificationSyntaxe {


	public static void verification(Object o){
		Class[] classGet = new Class[0];
		Class[] classSet = new Class[]{("").getClass()};
		Field[] fields = o.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			if (String.class.isAssignableFrom(field.getType())){

				try {
					Method methodGet =  o.getClass().getDeclaredMethod("get"+StringUtils.capitalise(field.getName()), classGet);
					Method methodSet =  o.getClass().getDeclaredMethod("set"+StringUtils.capitalise(field.getName()), classSet);
					String string = (String) methodGet.invoke(o, new Object[0]);
					
					string = StringHelper.removeCaractereRecherche(string);
					
					Object[] setValeur = {string};
					methodSet.invoke(o, setValeur);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}
