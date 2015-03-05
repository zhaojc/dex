package com.lotoQuebec.correcteurAdresse;

/*
 * Created on 26-Sep-2008
 */
import StreetPerfect64.SpaaSqaXpcJavaPkgApi64;

import com.lotoQuebec.correcteurAdresse.config.ConstantValidationAdresse;
import com.lotoQuebec.correcteurAdresse.config.InitializationParameters;

/**
 * @author levassc
 */
public class CorrigerAdresse {

	public static AdresseSortie corriger(AdresseEntree adresseCorrection){
		String initializationParameters = InitializationParameters.construire();
		
		return corriger(adresseCorrection, initializationParameters);
	}
	
	public static AdresseSortie corriger(AdresseEntree adresseCorrection, String initializationParameters){
		String pays = adresseCorrection.getPaysAE();
		
		if (ConstantValidationAdresse.CANADA.equals( pays )){
			return corrigerCanada(adresseCorrection, initializationParameters);
			
		} else if (ConstantValidationAdresse.USA.equals( pays )){
			
		}
		
		return null;
	}
	
	private static AdresseSortie corrigerCanada(AdresseEntree adresseCorrection, String initializationParameters) {
		AdresseSortie adresseCorrige = new AdresseSortie();
		SpaaSqaXpcJavaPkgApi64 spJavaApi = new SpaaSqaXpcJavaPkgApi64(  );
		
		String[] LS_input_array = new String[spJavaApi.DC_InputCorrectParseAddress];
		int LS_array_index = 0;
		LS_input_array[LS_array_index++] = initializationParameters;
		LS_input_array[LS_array_index++] = adresseCorrection.getLigneAdresseAE();
		LS_input_array[LS_array_index++] = adresseCorrection.getVilleAE();
		LS_input_array[LS_array_index++] = adresseCorrection.getProvinceAE();
		LS_input_array[LS_array_index++] = adresseCorrection.getCodePostalAE();
		LS_input_array[LS_array_index++] = ConstantValidationAdresse.CANADA;
		
		String[]  LS_output_array = spJavaApi.StreetPerfectCorrectParseAddress ( LS_input_array );
		
	    LS_array_index = 0;
	    
		if ( LS_output_array != null ){
		
			if ( LS_output_array.length == spJavaApi.DC_OutputCorrectParseAddress ){
		        LS_array_index = 0;
		        adresseCorrige.setCodeRetour( LS_output_array[LS_array_index++] );

		        adresseCorrige.setType( LS_output_array[LS_array_index++] );
		        adresseCorrige.setAdresseligne( LS_output_array[LS_array_index++] );
		        adresseCorrige.setNumeroMunicipale( LS_output_array[LS_array_index++] );
		        adresseCorrige.setSufixMunicipale( LS_output_array[LS_array_index++] );
		        adresseCorrige.setNomRue( LS_output_array[LS_array_index++] );
		        adresseCorrige.setTypeRue( LS_output_array[LS_array_index++] );
		        adresseCorrige.setCardinaliteRue( LS_output_array[LS_array_index++] );
		        adresseCorrige.setTypeUnite( LS_output_array[LS_array_index++] );
		        adresseCorrige.setNumeroUnite( LS_output_array[LS_array_index++] );
		        adresseCorrige.setTypeService( LS_output_array[LS_array_index++] );
		        adresseCorrige.setNumeroService( LS_output_array[LS_array_index++] );
		        adresseCorrige.setNomRegionService( LS_output_array[LS_array_index++] );
		        adresseCorrige.setTypeRegionService( LS_output_array[LS_array_index++] );
		        adresseCorrige.setQualificatifRegionService( LS_output_array[LS_array_index++] );
		        adresseCorrige.setVille( LS_output_array[LS_array_index++] );
		        adresseCorrige.setVilleLong( LS_output_array[LS_array_index++] );
		        adresseCorrige.setVilleAbreviation( LS_output_array[LS_array_index++] );
		        adresseCorrige.setProvince( LS_output_array[LS_array_index++] );
		        adresseCorrige.setCodePostal( LS_output_array[LS_array_index++] );
		        adresseCorrige.setPays( LS_output_array[LS_array_index++] );
		        adresseCorrige.setInformationAdditionnel1( LS_output_array[LS_array_index++] );
		        adresseCorrige.setInformationAdditionnel2( LS_output_array[LS_array_index++] );
		        adresseCorrige.setInformationNonIdentifier( LS_output_array[LS_array_index++] );
		        adresseCorrige.setMessages( LS_output_array[LS_array_index++] );
				
				adresseCorrige.setStatut( LS_output_array[LS_array_index++] );
				adresseCorrige.setMessageStatut( LS_output_array[LS_array_index++] );
				
			}else{
				if ( LS_output_array.length == 3 ){
					LS_array_index = 0;
					adresseCorrige.setCodeRetour( LS_output_array[LS_array_index++] );
					adresseCorrige.setStatut( LS_output_array[LS_array_index++] );
					adresseCorrige.setMessageStatut( LS_output_array[LS_array_index++] );
				}else{
					for ( LS_array_index = 0; LS_array_index < LS_output_array.length; LS_array_index ++ ){
						System.out.println ( LS_output_array[LS_array_index] );
					} 
				}
			}
		}
		return adresseCorrige;
	}
}
