package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


public class SujetDirectCompletSujetGalerieConstruireRechercheSQL extends CompletSujetGalerieConstruireRechercheSQL{

	@Override
	protected void whereCritereDossier(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
		
	}
	
	@Override
	protected void whereCritereSujet(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
		
		OracleDAOUtils.ajouterChampConvertSQL(preparerSQL, "s.v_su_reference_3", criteresRecherchePhoto.getNumeroSujet());
	}
	
}
