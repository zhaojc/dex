package com.lotoquebec.cardex.integration.dao;

import java.util.Arrays;
import java.util.List;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class NarrationBaliseUtil {

   private final static List<String> baliseAccepte = Arrays.asList("P","BR","TABLE","B","U","LI","UL","OL","I","FONT","CENTER","HR","TR","TD","TH","STRONG","EM","TBODY");
   
   public static String nettoyerNarration(String narrationAvecFormat){
		StringBuilder sortie = new StringBuilder();
		String[] narrationList = narrationAvecFormat.split("<");
		
		for (String token:narrationList){
			String balise = extraireNomBalise(token);
			
			if (balise == null){
				sortie.append(token);
				continue;
			}
			
			if ( baliseAccepte.contains(balise) ){
				sortie.append('<');
				sortie.append(token);
			}else{
				int finBaliseInterdite = token.indexOf('>')+1;
				sortie.append(token.substring(finBaliseInterdite));
			}
		}
		
		return sortie.toString();
   }

   public static void assignerMessageSiNarrationANettoyer(ActionMessages actionMessages, String narrationAvecFormat){
		String[] narrationList = narrationAvecFormat.split("<");

		for (String token:narrationList){
			String balise = extraireNomBalise(token);
			
			if (balise == null)
				continue;
			
			if ( baliseAccepte.contains(balise) == false){
				actionMessages.add(Globals.MESSAGE_KEY, new ActionMessage("narration.avec.balise"));
				break;
			}
		}
   }
   
	private static String extraireNomBalise(String token){
		int debutNomBalise = 0;
		int finNomBalise = Integer.MAX_VALUE;

		// Ce n'est pas une balise, il faut avoir une fin a cette balise.
		if (token.indexOf('>') == -1)
			return null;
		
		if (token.indexOf('/') == 0){
			debutNomBalise = 1;
			finNomBalise = token.indexOf('>');		
		}
		if (token.indexOf('/') > 0 && finNomBalise > token.indexOf('/')){
			debutNomBalise = 0;
			finNomBalise = token.indexOf('/');
		}
		if (token.indexOf('>') > -1 && finNomBalise > token.indexOf('>')){
			debutNomBalise = 0;
			finNomBalise = token.indexOf('>');
		}
		if (token.indexOf(' ') > -1 && finNomBalise > token.indexOf(' ')){
			debutNomBalise = 0;
			finNomBalise = token.indexOf(' ');
		}
		
		if (finNomBalise == Integer.MAX_VALUE){
			return null;
		}
		
		return token.substring(debutNomBalise, finNomBalise);
	}
	
}
