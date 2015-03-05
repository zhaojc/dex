/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardex.presentation.model.form.lienCascade;

import com.lotoquebec.cardexCommun.model.LienCascade;

/**
 * @author levassc
 */
public class HierarchiePPV extends LienCascade{

	public final static String PAYS = "pays";
	public final static String PROVINCE = "province";
	public final static String VILLE = "ville";

	public HierarchiePPV(String pays, String province, String ville) {
		assignerValeurCommun();
		set(PAYS, pays);
		set(PROVINCE, province);
		set(VILLE, ville);
	}	
	
	private void assignerValeurCommun() {
		ajout(PAYS);
		ajout(PROVINCE);
		ajout(VILLE);
	}

	public HierarchiePPV() {
		assignerValeurCommun();
	}
	
}
