package com.lotoquebec.cardex.business.facade.rapport;

import java.util.Date;
import java.util.List;

import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class NatureRAQRapportCDX_0070 extends RapportCDX_0070{

	private long nature;
	
	public NatureRAQRapportCDX_0070(CardexAuthenticationSubject subject, Date dateDebut, Date dateFin, long nature) {
		super(subject, dateDebut, dateFin);
		this.nature = nature;
	}

	@Override
	protected List<RapportVOCDX_00070> construireDonneeRapport(CardexAuthenticationSubject subject, Date dateDebut, Date dateFin) throws DAOException{
		return FabriqueCardexDAO.getInstance().getRapportDAO().natureRAQCDX_00070(subject, dateDebut, dateFin, nature);
	}

	@Override
	protected String obtenirTitre() {
		return "Rapport d'activité quotidien";
	}

}
