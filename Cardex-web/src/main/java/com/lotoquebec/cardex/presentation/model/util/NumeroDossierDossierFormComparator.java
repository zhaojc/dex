package com.lotoquebec.cardex.presentation.model.util;

import java.util.Comparator;

import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class NumeroDossierDossierFormComparator implements Comparator<DossierForm>{

	public int compare(DossierForm dossierForm1, DossierForm dossierForm2) {
		
		if (StringUtils.isEmpty(dossierForm1.getNumeroDossier()) || StringUtils.isEmpty( dossierForm2.getNumeroDossier() ))
			return 0;
		
		return dossierForm1.getNumeroDossier().compareTo( dossierForm2.getNumeroDossier() );
	}
	

}
