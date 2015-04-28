package com.lotoquebec.cardex.generateurRapport.dossier.vigilance;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class DetailleVigilanceDossierGenerateurRapport_CDX_0233 extends VigilanceDossierGenerateurRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		//GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.base.imprimer");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		EntiteCardex dossier = (EntiteCardex) vo;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportImpressionFiche(dossier.getCle(), dossier.getSite(), "CARDEX_RAPPORT.SP_RAP_DO_VIGILANCE_DETAILLE", connection);
        return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.DETAILLE_VIGILANCE_DOSSIER_CDX_0233);
	}

	@Override
	protected Map<String, Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters =  super.construireParametres(subject, vo, connection);
		
		parameters.put("SOUS_RAPPORT_SUJET_CDX_0233", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_CDX_0233));
			parameters.put("SOUS_RAPPORT_SUJET_PHOTO", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_PHOTO));
			parameters.put("SOUS_RAPPORT_SUJET_ADRESSE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_ADRESSE));
			parameters.put("SOUS_RAPPORT_SUJET_CARACTERISTIQUE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_CARACTERISTIQUE));
			parameters.put("SOUS_RAPPORT_SUJET_DOSSIER", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_DOSSIER));

		parameters.put("SOUS_RAPPORT_DOSSIER_NARRATION", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_DOSSIER_NARRATION));

		return parameters;
	}

	
}
