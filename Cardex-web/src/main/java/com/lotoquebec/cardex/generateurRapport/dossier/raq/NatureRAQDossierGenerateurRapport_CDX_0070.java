package com.lotoquebec.cardex.generateurRapport.dossier.raq;

import java.util.List;

import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.generateurRapport.entite.ActiviteQuotidienneRapportVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class NatureRAQDossierGenerateurRapport_CDX_0070 extends RAQDossierGenerateurRapport_CDX_0070 {

	@Override
	protected List<RapportVOCDX_00070> obtenirRapportVO_CDX_0070(
			CardexAuthenticationSubject subject,
			ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO)
			throws DAOException {
		return FabriqueCardexDAO.getInstance().getRapportDAO().natureRAQCDX_00070(subject, activiteQuotidienneRapportVO.getDateDebutDu(), activiteQuotidienneRapportVO.getDateDebutAu(), activiteQuotidienneRapportVO.getNature());
	}


	
}
