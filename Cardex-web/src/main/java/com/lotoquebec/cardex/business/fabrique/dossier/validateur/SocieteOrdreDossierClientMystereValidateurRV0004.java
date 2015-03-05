package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import java.util.Collections;
import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.util.comparator.DateDebutDossierComparator;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class SocieteOrdreDossierClientMystereValidateurRV0004 extends OrdreDossierClientMystereValidateurRV0004 {

	private Societe societe;
	
	public SocieteOrdreDossierClientMystereValidateurRV0004(CardexAuthenticationSubject subject, Dossier dossierOrigine, Societe societe) {
		super(subject, dossierOrigine);
		this.societe = societe;
	}
	@Override
	protected Societe obtenirSocieteRDD() throws DAOException{
		return societe;
	}
	
	@Override
	protected void assignerDossiers(List<Dossier> dossiers) throws DAOException {
		precedentDossier = obtenirDernierDossier(dossiers);
		suivantDossier = origineDossier;
	}

	protected Dossier obtenirDernierDossier(List<Dossier> dossiers) throws DAOException {
		Collections.sort(dossiers, new DateDebutDossierComparator());
		
		for (int i=dossiers.size()-1;i>0;i--) {
			
			if (isDossierAccepte(dossiers.get(i)))
				return dossiers.get(i);
		}
		return null;
	}
}
