/*
 * Created on 26-Sep-2008
 */
package com.lotoQuebec.correcteurAdresse.config;

import com.lotoQuebec.correcteurAdresse.util.CorrecteurAdresseHelper;

/**
 * @author levassc
 */
public class InitializationParameters {

	public static String construire(){
		String initialisation = "";
		Configuration configuration = Configuration.getInstance();
		
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.PORT_SERVICE);
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.ADRESSE_SERVICE);
		
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.LANGAGE_USAGER);
		
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.FORMAT_GUIDE);
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.OPTIMIZE_ADDRESS);

		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.DESIGNATOR_STYLE);
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.DESIGNATOR_KEYWORD);

		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.ERREUR_TOLERENCE);
		
		return initialisation;
	}
	
	public static String construire(String erreurTolerence){
		String initialisation = "";
		Configuration configuration = Configuration.getInstance();

		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.PORT_SERVICE);
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.ADRESSE_SERVICE);
		
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.LANGAGE_USAGER);
		
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.FORMAT_GUIDE);
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.OPTIMIZE_ADDRESS);

		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.DESIGNATOR_STYLE);
		initialisation += CorrecteurAdresseHelper.ajouterParametre(configuration, ConstantValidationAdresse.CleConfiguration.DESIGNATOR_KEYWORD);

		initialisation += CorrecteurAdresseHelper.ajouterParametre(ConstantValidationAdresse.CleConfiguration.ERREUR_TOLERENCE, erreurTolerence);
		
		return initialisation;
	}	
}
