package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;


public class CountSujetGalerieConstruireRechercheSQL extends SujetGalerieConstruireRechercheSQL{

	@Override
	protected String selectArgument(){
		return GalerieSQLUtils.selectCountArgument();
	}

	@Override
	protected String groupBy() {
		return "";
	}
	
		
}
