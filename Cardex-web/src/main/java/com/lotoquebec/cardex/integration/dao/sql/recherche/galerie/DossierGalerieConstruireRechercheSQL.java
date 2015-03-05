package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;


public abstract class DossierGalerieConstruireRechercheSQL extends GalerieConstruireRechercheSQL{
	
	@Override
	protected String groupBy() {
		return "";
	}

	@Override
	protected void ajoutFromSujet(PreparerSQL preparerSQL, CriteresRecherchePhoto criteresRecherchePhoto){
		
		if (isExclureSujet(criteresRecherchePhoto) == false)
			super.ajoutFromSujet(preparerSQL, criteresRecherchePhoto);
	}
	@Override
	protected void whereLiaisonDossierSujet(PreparerSQL preparerSQL, CriteresRecherchePhoto criteresRecherchePhoto){

		if (isExclureSujet(criteresRecherchePhoto) == false)
			super.whereLiaisonDossierSujet(preparerSQL, criteresRecherchePhoto);
	}
	
	@Override
	protected void whereCritereSujet(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
		
		if (isExclureSujet(criteresRecherchePhoto) == false)
			super.whereCritereSujet(preparerSQL, subject, criteresRecherchePhoto);
	}
	
	@Override
	public String whereLiaisonPhoto(){
		StringBuilder query = new StringBuilder();
        
        query.append(" mm.l_mm_cle = lm.l_mm_cle and ");
        query.append(" mm.l_si_cle = lm.l_mm_ref_site  and ");
        query.append(" mm.I_TM_CLE < 18826 and ");
        query.append(" do.l_do_cle = lm.l_lmm_reference and ");
        query.append(" do.l_si_cle = lm.l_lmm_ref_site and ");
        query.append(" 'DO' = lm.c_lmm_ref_genre ");
        
        return query.toString();
	}

	private boolean isExclureSujet(CriteresRecherchePhoto criteresRecherchePhoto){
		        
        if (StringUtils.isEmpty(criteresRecherchePhoto.getNomOrdinaire())
        && StringUtils.isEmpty(criteresRecherchePhoto.getNom())
        && StringUtils.isEmpty(criteresRecherchePhoto.getPrenom())
        && StringUtils.isEmpty(criteresRecherchePhoto.getAlias())
        && criteresRecherchePhoto.getRace() == 0
        && criteresRecherchePhoto.getLangue() == 0
        && criteresRecherchePhoto.getSexe() == 0
        && criteresRecherchePhoto.getEthnie() == 0
        && criteresRecherchePhoto.getAge() == 0
        && criteresRecherchePhoto.getCaracteristique1() == 0
        && criteresRecherchePhoto.getCaracteristique2() == 0
        && criteresRecherchePhoto.getCaracteristique3() == 0
        && criteresRecherchePhoto.getCaracteristique4() == 0)
        	return true;
        return false;
	}
}
