package com.lotoquebec.cardexCommun.util;


 
/*
 * INSCRIRE le NAS sur une feuille de papier, comme suit: 440-968-592;
 * COCHER le deuxième, quatrième, sixième et huitième chiffre, tel qu'indiqué ci-dessus;
 * INSCRIRE le NAS de nouveau, mais cette fois en multipliant par deux les chiffres cochés, comme suit: 480-18616-5182.
 * LORSQUE CETTE MULTIPLICATION DONNE UN NOMBRE À DEUX CHIFFRES, ALORS :
 * ADDITIONNER ces deux chiffres pour n'en faire qu'un seul,
 */
public class ValiderNAS {

	private int[] NAS = new int[9];
	private boolean peutValier = true;

	/**
	 * @param nas
	 */
	public ValiderNAS(String nas) {
		super();
		nas = StringUtils.replace(nas, " ", "");
		nas = StringUtils.replace(nas, "-", "");
		
		if (StringUtils.isEmpty(nas)
		|| StringUtils.isNumeric(nas) == false
		|| nas.length() != 9){
			peutValier = false;
			return;
		}
		
		for (int i = 0; i < nas.length(); i++) {
			NAS[i] = Integer.valueOf(nas.substring(i, i+1)).intValue();
		}
	}
	
	public boolean isValide(){
		
		if (peutValier == false)
			return false;
		
		NAS[1] = doubler(NAS[1]);
		NAS[3] = doubler(NAS[3]);
		NAS[5] = doubler(NAS[5]);
		NAS[7] = doubler(NAS[7]);
	
		int somme = sommerChiffre();
		
		return (somme % 10) == 0;
	}
	
	private int doubler(int i){
		int retour = i * 2;
		
		while (retour >= 10){
			retour = 1 + (retour - 10);
		}
		
		return retour;
	}
	
	private int sommerChiffre(){
		int somme = 0;
		
		for (int i = 0; i < NAS.length; i++) {
			somme += NAS[i];
		}
		
		return somme;
	}
}
