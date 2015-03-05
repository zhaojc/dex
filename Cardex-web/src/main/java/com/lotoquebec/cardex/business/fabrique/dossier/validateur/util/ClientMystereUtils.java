package com.lotoquebec.cardex.business.fabrique.dossier.validateur.util;

import com.lotoquebec.cardexCommun.GlobalConstants;

public class ClientMystereUtils {

	public static boolean isConforme(long categorie){
		return GlobalConstants.CategorieClientMystere.CONFORME_VISITE_1 == categorie
		|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_2 == categorie
		|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_3 == categorie
		|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_4 == categorie
		|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_5 == categorie;
	}
	
	public static boolean isInfraction(long categorie){
		return GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 == categorie;
	}
	
}
