package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;



public class CompletReperageSujetGalerieConstruireRechercheSQL extends ReperageSujetGalerieConstruireRechercheSQL{

	@Override
	protected String selectArgument(){
		return GalerieSQLUtils.selectSujetArgument();
	}

	@Override
	protected String groupBy() {
		return GalerieSQLUtils.groupBySujet();
	}


}
