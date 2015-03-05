/*
 * Created on 28-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.Resources;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.CleHardListe;

/**
 * @author levassc
 */
public abstract class CleRessourceHardListe extends CleHardListe {

	private MessageResources messageResources = null;
	private Locale locale = null;
	
	public CleRessourceHardListe(HttpServletRequest request, String langue) {
		super(langue);
		
		assignerMessageResources(request);
		
		this.locale = request.getLocale();
	}
	
	private void assignerMessageResources(HttpServletRequest request){

		if (messageResources == null){
			
			if (request == null)
				throw new RuntimeException("CleRessourceHardListe: Le champ request est null");
			
			if (request != null && request.getSession() == null)
				throw new RuntimeException("CleRessourceHardListe: La session est null");
			
			//MessageResources messages = MessageResources.getMessageResources( Constants.Package + ".LocalStrings" );
/*
 * Option 1: MessageResources.getMessageResources("MessageResources");
Option 2: MessageResourcesFactory.createFactory().createResources(“MessageResources”);
 */
			this.messageResources = (MessageResources) Resources.getMessageResources(request);

			if (messageResources == null)
				this.messageResources = (MessageResources) request.getSession().getAttribute(Globals.MESSAGES_KEY);
			
			if (request != null && request.getLocale() == null)
				throw new RuntimeException("CleRessourceHardListe: Le champ request.getLocale est null");
		}
	}

	protected void add(String key){

		if (messageResources == null)
			throw new RuntimeException("CleRessourceHardListe: Le champ messageResources est null");
		
		String valeur = messageResources.getMessage(locale, key);		
		add( key, valeur );
	}
	
	protected void addLabelKey(String key){
		
		if (messageResources == null)
			throw new RuntimeException("CleRessourceHardListe: Le champ messageResources est null");
		
		String valeur = messageResources.getMessage(locale, key);		
		add( valeur, valeur );
	}	
}
