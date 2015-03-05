/*
 * Created on 26-Sep-2008
 */
package com.lotoQuebec.correcteurAdresse;

/**
 * @author levassc
 */
public interface AdresseEntree {

	//public String getNomCompagnie();
	
	public String getLigneAdresseAE();
	
	public String getVilleAE();
	
	/*
	 * PS_CAN_STATE
	 * PS_USA_STATE
	 */
	public String getProvinceAE();
	
	/*
	 * Constante pour USA et CANADA
	 */
	public String getPaysAE();
	
	public String getCodePostalAE();
	
	/*
	 * PS_USA_URBANIZATION_NAME
	 */
	//public String informationPostalAC();
}
