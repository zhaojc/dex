package com.lotoquebec.cardexCommun.util;

public class IntegerRotatif {

	private static int valeur = 0;
	
	public synchronized String genereStringNumero(){
		int valeur = genererNumero();
		return String.valueOf((int)valeur);
	}

	public synchronized int genererNumero() {
		
		if (valeur == Integer.MAX_VALUE)
			valeur = 1;
		else
			valeur++;
		return valeur;
	}
	
}
