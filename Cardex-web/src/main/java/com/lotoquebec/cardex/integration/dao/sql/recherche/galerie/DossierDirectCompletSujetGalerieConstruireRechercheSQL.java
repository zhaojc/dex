package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

public class DossierDirectCompletSujetGalerieConstruireRechercheSQL extends CompletSujetGalerieConstruireRechercheSQL{

	@Override
	protected void whereCritereDossier(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
		OracleDAOUtils.ajouterChampConvertSQL(preparerSQL, "do.v_do_ancienne_reference", criteresRecherchePhoto.getNumeroDossier());
	}
	
	@Override
	protected void whereCritereSujet(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
		
	}

		
}
