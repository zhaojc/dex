/*
 * Created on 28-Apr-2008
 */
package com.lotoQuebec.correcteurAdresse.config;

/**
 * @author levassc
 */
public class ConstantValidationAdresse {

	public static final String CANADA = "Canada";
	public static final String CAN = "CAN";
	public static final String USA = "USA";
	public static final String AUTRE = "autre";
	
	public static final String USA_SEPARRER = "USA_AddressParse";
	public static final String USA_CORRIGER = "USA_AddressCorrection";
	
	public class CleConfiguration{
		public static final String PORT_SERVICE = "ServicePort";
		public static final String ADRESSE_SERVICE = "ServiceAddress";
		public static final String LANGAGE_USAGER = "UserLanguage";
		
		public static final String FORMAT_GUIDE = "OutputFormatGuide";
		public static final String OPTIMIZE_ADDRESS = "OptimizeAddress";
		
		public static final String DESIGNATOR_KEYWORD = "PreferredUnitDesignatorKeyword";
		public static final String DESIGNATOR_STYLE = "PreferredUnitDesignatorStyle";

		public static final String ERREUR_TOLERENCE = "ErrorTolerance";
	}

}
