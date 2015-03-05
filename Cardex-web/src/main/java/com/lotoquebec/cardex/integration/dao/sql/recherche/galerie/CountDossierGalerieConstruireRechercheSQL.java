package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;


public class CountDossierGalerieConstruireRechercheSQL extends DossierGalerieConstruireRechercheSQL{

	protected String selectArgument(){
		return "count(mm.l_em_cle) ";
	}
	
	
}
