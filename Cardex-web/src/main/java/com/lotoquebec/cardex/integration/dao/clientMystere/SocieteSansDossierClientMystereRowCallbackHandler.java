package com.lotoquebec.cardex.integration.dao.clientMystere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;

public class SocieteSansDossierClientMystereRowCallbackHandler extends SocieteAvecDossierClientMystereRowCallbackHandler{

	public SocieteSansDossierClientMystereRowCallbackHandler(Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap) {
		super(societeClientMystereMap, null);
	}

	@Override
	protected void assignerInformationDossier(ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255, Dossier echantillonDossier, ResultSet rs) throws SQLException {
		clientMystereVO_CDX_0255.obtenirSectionSocieteCDX0255VO(echantillonDossier);
	}
	
}
