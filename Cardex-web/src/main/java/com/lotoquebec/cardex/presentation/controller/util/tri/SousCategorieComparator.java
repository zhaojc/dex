package com.lotoquebec.cardex.presentation.controller.util.tri;

import java.io.Serializable;
import java.util.Comparator;

import com.lotoquebec.cardex.presentation.model.form.SousCategorieForm;
import com.lotoquebec.cardexCommun.util.StringComparator;

public class SousCategorieComparator implements Comparator, Serializable{

	public int compare(Object arg0, Object arg1) {

		if (arg0 instanceof SousCategorieForm == false
		|| arg1 instanceof SousCategorieForm == false)
			return 0;
		
		SousCategorieForm sousCategorie1 = (SousCategorieForm) arg0;
		SousCategorieForm sousCategorie2 = (SousCategorieForm) arg1;
		
		return (new StringComparator()).compare(sousCategorie1.getDescription(), sousCategorie2.getDescription());
	}

}
