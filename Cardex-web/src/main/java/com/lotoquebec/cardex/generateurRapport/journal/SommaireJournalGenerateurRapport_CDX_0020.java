package com.lotoquebec.cardex.generateurRapport.journal;

import java.io.InputStream;

import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.generateurRapport.rapports.sql.JournalGenererRapportSQL;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class SommaireJournalGenerateurRapport_CDX_0020 extends JournalGenererRapportSQL {

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/journalSommaire");
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.JOURNAL_SOMMAIRE);
	}

}
