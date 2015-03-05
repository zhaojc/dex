package com.lotoQuebec.correcteurAdresse;

/*
 * Created on 26-Sep-2008
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import StreetPerfect64.SpaaSqaXpcJavaPkgApi64;

import com.lotoQuebec.correcteurAdresse.config.ConstantValidationAdresse;
import com.lotoQuebec.correcteurAdresse.config.InitializationParameters;

/**
 * @author levassc
 */
public class RechercherAdresse {

	public static List rechercher(AdresseEntree adresseEntree){
		String initializationParameters = InitializationParameters.construire();
		
		return rechercher(adresseEntree, initializationParameters);
	}
	
	public static List rechercher(AdresseEntree adresseEntree, String initializationParameters){
		String pays = adresseEntree.getPaysAE();
		
		if (ConstantValidationAdresse.CANADA.equals( pays )){
			return rechercherCanada(adresseEntree, initializationParameters);
			
		} else if (ConstantValidationAdresse.USA.equals( pays )){
			
		}
		
		return null;
	}
	
	private static List rechercherCanada(AdresseEntree adresseEntree, String initializationParameters) {
		List listeAdresse = new ArrayList();
		
		SpaaSqaXpcJavaPkgApi64 spJavaApi = new SpaaSqaXpcJavaPkgApi64 (  );
		
		String[] LS_input_array = new String[spJavaApi.DC_InputSearchAddress];
		int LS_array_index = 0;
		LS_input_array[LS_array_index++] = initializationParameters;
		LS_input_array[LS_array_index++] = adresseEntree.getLigneAdresseAE();
		LS_input_array[LS_array_index++] = adresseEntree.getVilleAE();
		LS_input_array[LS_array_index++] = adresseEntree.getProvinceAE();
		LS_input_array[LS_array_index++] = adresseEntree.getCodePostalAE();
		LS_input_array[LS_array_index++] = ConstantValidationAdresse.CANADA;
		
		String[]  LS_output_array = spJavaApi.StreetPerfectSearchAddress( LS_input_array );
		
	    LS_array_index = 0;
	    
		if ( LS_output_array != null ){
		
			if ( LS_output_array.length == spJavaApi.DC_OutputSearchAddress ){
		        LS_array_index = 0;
		        
	            String return_code = LS_output_array[LS_array_index++];
	            String response_address_list = LS_output_array[LS_array_index++];
	            String status_flag = LS_output_array[LS_array_index++];													  
	            String status_messages = LS_output_array[LS_array_index++];														    
		        
	            if ("N".equals(status_flag)){
	                StringTokenizer stringTokenizer = new StringTokenizer(response_address_list, "\r");
			        
		            while (stringTokenizer.hasMoreTokens()){
		            	String ligneAdresse = stringTokenizer.nextToken();
		            	AdresseSortieRecherche adresseSortie = traiterAdressePossible(ligneAdresse, return_code, status_flag, status_messages);
						
						if (adresseSortie != null)
							listeAdresse.add(adresseSortie);
		            }
	            }else{
	            	System.out.println("Erreur : '"+status_messages+"'");
	            }
			}
		}
		return listeAdresse;
	}

	/*
0                                 35                            65    7173      81    87     94    100                                     140                                                                     212               
21                                                                                                                                                                                                                                      
21QCSAINT-ZEPHIRIN-DE-COURVAL     ANDRELLE                      RUE     3000149       000090       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     ANDRELLE                      RUE     2000150       000150       SAINT-ZEPHIRIN-DE-COURVAL                                                                                       J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     ANDRELLE                      RUE     3000195       000151       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     DE L'EGLISE                   RANG    1000401       000401       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     LOUISE-DUMOULIN               RUE     3000055       000050       SAINT-ZEPHIRIN-DE-COURVAL                                                                                       J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     PIOUI                         RANG    2000450       000002       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-ALEXANDRE               RANG    3002451       001300       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-FRANCOIS                RANG    3001550       000051       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-FRANCOIS-XAVIER         RUE     3000070       000029       SAINT-ZEPHIRIN-DE-COURVAL                                                                                       J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-JOSEPH                  RUE     3000991       000851       SAINT-ZEPHIRIN-DE-COURVAL                                                                                       J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-LOUIS                   RANG    3000800       000501       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-MICHEL                  RANG    3000440       000160       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-MICHEL                  RANG    3001032       000441       SAINT-ZEPHIRIN-DE-COURVAL                                                                                       J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-MICHEL                  RANG    3001801       001033       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-PIERRE                  RANG    3000800       000021       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-PIERRE                  RANG    3001480       000850       SAINT-ZEPHIRIN-DE-COURVAL                                                                                       J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINT-PIERRE                  RANG    1002801       001481       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
21QCSAINT-ZEPHIRIN-DE-COURVAL     SAINTE-GENEVIEVE              RANG    3001200       000701       SAINT-ZEPHIRIN-DE-COURVAL               RR0002                                                                  J0G1V0           N   
32QCSAINT-ZEPHIRIN-DE-COURVAL                                                                      SAINT-ZEPHIRIN-DE-COURVAL                     SAINT-ZEPHIRIN-DE-COURVAL     BDP                 0005800001      J0G1V0           N   
42QCSAINT-ZEPHIRIN-DE-COURVAL                                                                      SAINT-ZEPHIRIN-DE-COURVAL                     SAINT-ZEPHIRIN-DE-COURVAL     BDP                           RR0002J0G1V0           N   
52QCSAINT-ZEPHIRIN-DE-COURVAL                                                                      SAINT-ZEPHIRIN-DE-COURVAL                     SAINT-ZEPHIRIN-DE-COURVAL     BDP                                 J0G1V0           N

21                                                                                                                                                                                                                                      
21QCSAINT-BONAVENTURE             122                           RTE     2000464       000464       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             143                           RTE     3000792       000006       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             143                           RTE     3000865       000795       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             2E                            RANG    3001509       000252       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             4E                            RANG    3001419       000009       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             5E                            RANG    3001228       000080A      SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             6E                            RANG    3001260       000332       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             CYR                           RUE     3000110A      000100       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             DE LA MEUNERIE                RUE     3001124       001115       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             DIONNE                        RUE     3000366       000360       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             DU BASSIN                     RANG    3001446       000006       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             DU BASSIN                     RANG    2002070       002070       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             LABONTE                       RUE     1001689       001685       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             LALIME                        RUE     3001052       000791       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             LETENDRE                      RUE     3001052       001042       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             PAULHUS                       RUE     3001101       001091       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             PLANTE                        RUE     3000720       000693A      SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             PRINCIPALE                    RUE     3001681       000771       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
21QCSAINT-BONAVENTURE             SAINT-JEAN-BAPTISTE           RUE     3000790       000749       SAINT-BONAVENTURE                       RR0002                                                                  J0C1C0           N   
42QCSAINT-BONAVENTURE                                                                              SAINT-BONAVENTURE                             SAINT-BONAVENTURE             BDP                           RR0002J0C1C0           N   
52QCSAINT-BONAVENTURE                                                                              SAINT-BONAVENTURE                             SAINT-BONAVENTURE             BDP                                 J0C1C0           N   
   
4                                                                                                                                                                                                                                       
11QCSHERBROOKE                    KING                          RUE   E 1003331       003331       SHERBROOKE                                                                                                      J1E3Y8           N   
11QCSHERBROOKE                    KING                          RUE   E 1003333 3     003333 1     SHERBROOKE                                                                                                      J1E3Y8           L   
11QCSHERBROOKE                    PARROT                        RUE     1002921       002693       SHERBROOKE                                                                                                      J1E3Y8           N   
11QCSHERBROOKE                    PARROT                        RUE     2002856       002720       SHERBROOKE                                                                                                      J1E3Y8           N   

Cas qui n'ont pas marchés
11QCMONTREAL                      DE MAISONNEUVE                BOUL  E 2000800 23E   000800 11E   MONTREAL                                                                                                        H2L4M8           B   '
11QCMONTREAL                      DE MAISONNEUVE                BOUL  E 2000800 2E    000800 2E    MONTREAL
11QCMONTREAL                      DE LA GAUCHETIERE             RUE   O 2000700 C01   000700 C01   MONTREAL          
11QCDRUMMONDVILLE                 MARCHAND                      RUE     1000355 RC20  000355 RC1   DRUMMONDVILLE
11QCQUEBEC                        D'YOUVILLE                    PLACE   2000800 13E   000800 13E   QUEBEC                                                                                                          G1R3P4           A   '
11QCQUEBEC                        D'YOUVILLE                    PLACE   2000800 15E   000800 15E   QUEBEC                                                                                                          G1R3P4           A   '
11QCQUEBEC                        D'YOUVILLE                    PLACE   2000800 18E   000800 18E   QUEBEC                                                                                                          G1R3P4           A   '
11QCQUEBEC                        D'YOUVILLE                    PLACE   2000800 19E   000800 19E   QUEBEC                                                                                                          G1R3P4           A   '
11QCQUEBEC                        D'YOUVILLE                    PLACE   2000800 7E    000800 7E    QUEBEC                                                                                                          G1R3P4           A   '
11QCQUEBEC                        D'YOUVILLE                    PLACE   2000800 9E    000800 9E    QUEBEC 

3                                                                                                                                                                                                                                       
11QCMONTREAL                      SHERBROOKE                    RUE   E 2000600       000500       MONTREAL                                                                                                        H2L1K1           N   
11QCMONTREAL                      SHERBROOKE                    RUE   O 2000500       000500       MONTREAL                                                                                                        H3A3C6           A   
11QCMONTREAL                      SHERBROOKE                    RUE   O 2000500 2000  000500 2000  MONTREAL                                                                                                        H3A3G6           B   

	 */
	private static AdresseSortieRecherche traiterAdressePossible(String ligneAdresse, String return_code, String status_flag, String status_messages) {
	
		if (ligneAdresse.length() != 233)
			return null;
		try{
	    	AdresseSortieRecherche adresseSortie = new AdresseSortieRecherche();
	    	adresseSortie.setCodeRetour( return_code );
			adresseSortie.setStatut( status_flag );
			adresseSortie.setMessageStatut( status_messages );
			adresseSortie.setNomRue(ligneAdresse.substring(35, 65).trim());
			adresseSortie.setTypeRue(ligneAdresse.substring(65, 71).trim());
			adresseSortie.setCardinaliteRue(ligneAdresse.substring(71, 73).trim());
			adresseSortie.setNumeroMunicipalMax(obtenirLong(ligneAdresse, 74, 80));
			adresseSortie.setNumeroMunicipalMin(obtenirLong(ligneAdresse, 87, 93));
			
			if (adresseSortie.getNumeroMunicipalMin() == adresseSortie.getNumeroMunicipalMax())
				adresseSortie.setNumeroMunicipale(String.valueOf(adresseSortie.getNumeroMunicipalMin()));
			
			assignerNumeroUnite(ligneAdresse, adresseSortie);
			adresseSortie.setVille(ligneAdresse.substring(100, 140).trim());
			adresseSortie.setCodePostal(ligneAdresse.substring(212, 218).trim());
			
			adresseSortie.setTypeService(ligneAdresse.substring(140, 142).trim());
			adresseSortie.setNumeroService(ligneAdresse.substring(142, 146).trim());
			
			adresseSortie.setProvince(ligneAdresse.substring(3, 5).trim());
			adresseSortie.setPays(ConstantValidationAdresse.CANADA);
			
			// On corrige l'adresse pour avoir des accents dans le nom de la ville et de la rue
			AdresseSortie adresseSortieValide = CorrigerAdresse.corriger(adresseSortie);
			
			adresseSortie.setNomRue(adresseSortieValide.getNomRue());
			adresseSortie.setVille(adresseSortieValide.getVille());
			
			return adresseSortie;
		}catch (Exception e){
			System.err.println("Ligne en erreur : '"+ligneAdresse+"'");
		}
		return null;
	}

	private static void assignerNumeroUnite(String ligneAdresse, AdresseSortieRecherche adresseSortie){
		char[] chiffre = {'1','2','3','4','5','6','7','8','9','0'};
		String prefix = "";
		String sufix = "";
		String minUnite = ligneAdresse.substring(94, 100).trim();
		String chiffreMin = "";
		String maxUnite = ligneAdresse.substring(81, 87).trim();
		String chiffreMax = "";
		
		if (minUnite.length() == 0 || maxUnite.length() == 0)
			return;
		
		boolean trouveChiffre = false;
		for (int i = 0; i < minUnite.length(); i++) {
			char c = minUnite.charAt(i);
		
			if (Arrays.binarySearch(chiffre, c) == -11 && trouveChiffre == false)
				prefix += c;
			
			if (Arrays.binarySearch(chiffre, c) == -11 && trouveChiffre == true)
				sufix += c;
			
			if (Arrays.binarySearch(chiffre, c) != -11){
				trouveChiffre = true;
				chiffreMin += c;
			}
		}
		adresseSortie.setNumeroUniteMin(Long.valueOf(chiffreMin).longValue());
		adresseSortie.setPrefixNumeroUnite(prefix);
		adresseSortie.setSufixNumeroUnite(sufix);
		
		for (int i = 0; i < maxUnite.length(); i++) {
			char c = maxUnite.charAt(i);
			
			if (Arrays.binarySearch(chiffre, c) != -11)
				chiffreMax += c;
		}
		adresseSortie.setNumeroUniteMax(Long.valueOf(chiffreMax).longValue());
		
		if (adresseSortie.getNumeroUniteMin() == adresseSortie.getNumeroUniteMax()){
			String numeroUnite = prefix+String.valueOf(adresseSortie.getNumeroUniteMin())+sufix;
			adresseSortie.setNumeroUnite(numeroUnite);
		}
	}
	
	private static long obtenirLong(String ligneAdresse, int debut, int fin){
		String strLong = ligneAdresse.substring(debut, fin).trim();
		
		if (strLong.length() == 0)
			return 0;
		
		return Long.valueOf(strLong).longValue();
	}
	
}
