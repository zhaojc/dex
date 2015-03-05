package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;


public class CountReperageSujetGalerieConstruireRechercheSQL extends ReperageSujetGalerieConstruireRechercheSQL{

	@Override
	protected String selectArgument(){
		return GalerieSQLUtils.selectCountArgument();
	}

	@Override
	protected String groupBy() {
		return "";
	}
		
}
