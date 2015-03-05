package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.integration.dao.SujetInteretGalerieCache;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.exception.DAOException;


public class ViderSujetInteretDossierSujetInteret implements RegleAffaire<Dossier>{

	/**
	 * @param subject
	 */
	public ViderSujetInteretDossierSujetInteret() {
		super();
	}

	public void executer(Dossier dossier) throws DAOException {
        
        if(GlobalConstants.Genre.SUJETS_INTERET == dossier.getGenre())
        	SujetInteretGalerieCache.vider();
	}

}
