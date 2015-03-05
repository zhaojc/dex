package com.lotoquebec.cardex.integration.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.vo.SujetInteretGalerieVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.DateUtils;

public class SujetInteretGalerieCache {

	private static Map<Sujet, SujetInteretGalerieVO> sujetInteretGalerieMap = new HashMap<Sujet, SujetInteretGalerieVO>();
	private static SujetInteretGalerieCache sujetInteretGalerie = null;
	private static Date derniereExecution = null;
	
	private SujetInteretGalerieCache() {
		super();
	}
	
	public static SujetInteretGalerieCache getInstanceOf(CardexAuthenticationSubject subject) throws DAOException {
		Date aujourdhui = new Date();
		if (sujetInteretGalerie == null)
			sujetInteretGalerie = new SujetInteretGalerieCache();
			
		if (sujetInteretGalerieMap.isEmpty() || DateUtils.isMemeJour(derniereExecution, aujourdhui) == false)
			FabriqueCardexDAO.getInstance().getPhotoDAO().chargerSujetInteretGalerie(subject, sujetInteretGalerieMap);
		derniereExecution = aujourdhui;
		
		return sujetInteretGalerie;
	}
	
	public Set<Dossier> getActifSujetInteretDossier(Sujet sujet){
		SujetInteretGalerieVO sujetInteretGalerieVO = sujetInteretGalerieMap.get(sujet);
		
		if (sujetInteretGalerieVO == null)
			return null;		
		return sujetInteretGalerieVO.getActifDossierSujetInteret();
	}
	
	public Set<Dossier> getInactifSujetInteretDossier(Sujet sujet){
		SujetInteretGalerieVO sujetInteretGalerieVO = sujetInteretGalerieMap.get(sujet);
		
		if (sujetInteretGalerieVO == null)
			return null;
		return sujetInteretGalerieVO.getInactifDossierSujetInteret();
	}
	
	public static void vider(){
		sujetInteretGalerieMap.clear();
	}
}
