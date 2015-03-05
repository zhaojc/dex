package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class PrecedentDossierOrdreDossierClientMystereValidateurRV0004 extends OrdreDossierClientMystereValidateurRV0004 {
	
	public PrecedentDossierOrdreDossierClientMystereValidateurRV0004(CardexAuthenticationSubject subject, Dossier dossierOrigine) {
		super(subject, dossierOrigine);
	}

	@Override
	protected void assignerDossiers(List<Dossier> dossiers) throws DAOException {
		precedentDossier = obtenirPrecedentDossier(dossiers);
		
		if (isDossierAccepte(origineDossier))
			suivantDossier = origineDossier;
		else
			suivantDossier = obtenirSuivantDossier(dossiers);
	}


}
