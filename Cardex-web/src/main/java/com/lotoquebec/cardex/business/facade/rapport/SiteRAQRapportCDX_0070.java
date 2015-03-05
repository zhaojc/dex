package com.lotoquebec.cardex.business.facade.rapport;

import java.util.Date;
import java.util.List;

import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class SiteRAQRapportCDX_0070 extends RapportCDX_0070{

	private Long site;
	
	public SiteRAQRapportCDX_0070(CardexAuthenticationSubject subject, Date dateDebut, Date dateFin, Long site) {
		super(subject, dateDebut, dateFin);
		this.site = site;
	}

	@Override
	protected List<RapportVOCDX_00070> construireDonneeRapport(CardexAuthenticationSubject subject, Date dateDebut, Date dateFin) throws DAOException{
		return FabriqueCardexDAO.getInstance().getRapportDAO().globalRAQCDX_00070(subject, dateDebut, dateFin, site);
	}

	@Override
	protected String obtenirTitre() {
		return "Rapport d'activité quotidien";
	}

}
