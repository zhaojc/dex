package com.lotoquebec.cardex.generateurRapport.journal;

import java.io.InputStream;

import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.generateurRapport.rapports.sql.JournalGenererRapportSQL;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class DescriptifJournalGenerateurRapport_CDX_0023 extends JournalGenererRapportSQL {

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/journalDescriptif");
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.JOURNAL_DESCRIPTIF);
	}


}
