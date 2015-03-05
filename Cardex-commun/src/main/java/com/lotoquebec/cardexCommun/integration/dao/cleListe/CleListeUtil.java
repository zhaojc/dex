/*
 * Created on 14-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;

/**
 * @author levassc
 */
public class CleListeUtil {

	public static CleListe creerCleListe(CardexAuthenticationSubject subject, HttpServletRequest request, String classe, String valeurTableValeur, String valeurDiscriminant, String actionSecurite) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		CleListe cleListe = null;
		Class c = Class.forName( classe );
		Constructor[] arrayCT = c.getConstructors();
		
		for (int i = 0; i < arrayCT.length; i++) {
			Constructor ct = arrayCT[i];
			
			try{
				if ( TableValeurCleSQLListeCache.class.isAssignableFrom( c ) ){
					return CleListeFabrique.obtenir(classe, subject.getLocale().getLanguage(), valeurTableValeur, valeurDiscriminant, actionSecurite);
					/*
					if (ct.getParameterTypes().length == 4
					&& String.class.isAssignableFrom(ct.getParameterTypes()[0])
					&& String.class.isAssignableFrom(ct.getParameterTypes()[1])
					&& String.class.isAssignableFrom(ct.getParameterTypes()[2])
					&& String.class.isAssignableFrom(ct.getParameterTypes()[3])){
						Object arglist[] = new Object[4];
						arglist[0] = subject.getLocale().getLanguage();
						arglist[1] = valeurTableValeur;
						arglist[2] = valeurDiscriminant;
						arglist[3] = actionSecurite;
						cleListe = (CleListe) ct.newInstance(arglist);
						break;
					}
					if (ct.getParameterTypes().length == 3
					&& String.class.isAssignableFrom(ct.getParameterTypes()[0])
					&& String.class.isAssignableFrom(ct.getParameterTypes()[1])
					&& String.class.isAssignableFrom(ct.getParameterTypes()[2])){
						Object arglist[] = new Object[3];
						arglist[0] = subject.getLocale().getLanguage();
						arglist[1] = valeurDiscriminant;
						arglist[2] = actionSecurite;
						cleListe = (CleListe) ct.newInstance(arglist);
						break;
					}			*/		
				}else if ( ct.getParameterTypes().length == 1
				&& String.class.isAssignableFrom(ct.getParameterTypes()[0])){
					Object arglist[] = new Object[1];
					arglist[0] = subject.getLocale().getLanguage();
					cleListe = (CleListe) ct.newInstance(arglist);
					break;
				}else if ( ct.getParameterTypes().length == 2
				&& String.class.isAssignableFrom(ct.getParameterTypes()[0])
				&& String.class.isAssignableFrom(ct.getParameterTypes()[1])){
					Object arglist[] = new Object[2];
					arglist[0] = subject.getLocale().getLanguage();
					arglist[1] = valeurDiscriminant;
					cleListe = (CleListe) ct.newInstance(arglist);
					break;
					
				}else if ( ct.getParameterTypes().length == 2
				&& ct.getParameterTypes()[0].isInstance( request )
				&& String.class.isAssignableFrom(ct.getParameterTypes()[1])){
					Object arglist[] = new Object[2];
					arglist[0] = request;
					arglist[1] = subject.getLocale().getLanguage();
					cleListe = (CleListe) ct.newInstance(arglist);
					break;
					
				}
				// Dans l'�cran de la galerie, il y a une erreur que j'essaye de r�soudre.
			}catch(Exception e){
				System.err.println("Subjet = '"+subject+"'");
				System.err.println("Request = '"+request+"'");
				System.err.println("Classe = '"+classe+"'");
				System.err.println("ValeurDiscriminant = '"+valeurDiscriminant+"'");
				e.printStackTrace();
				throw new AssertionError(e.getMessage());
			}
		}
		
		return cleListe;
	}
	
}
