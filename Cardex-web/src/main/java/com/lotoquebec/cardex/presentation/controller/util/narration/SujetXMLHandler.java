/*
 * Created on 13-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.narration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class SujetXMLHandler implements HtmlHandler{

	private SujetNarration sujetNarration = new SujetNarration();

	public SujetNarration getSujetNarration() {
		return sujetNarration;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.narration.HtmlHandler#startElement(java.util.Map, java.lang.String)
	 */
	public void startElement(Map attributMap, String value) {

		if (attributMap.containsKey("id")){
			String strMethod = (String) attributMap.get("id");
			strMethod = StringUtils.capitalise( strMethod );
			
			Class cls = sujetNarration.getClass();

			Class partypes[] = new Class[1];
			partypes[0] = String.class;
			
			Object arglist[] = new Object[1];
			arglist[0] = StringUtils.replace(value, "&nbsp;", "");
			
			Method meth;
			
			try {
				meth = cls.getMethod("set"+strMethod, partypes);
				meth.invoke(sujetNarration, arglist);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
