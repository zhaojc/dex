package com.lotoquebec.cardex.generateurRapport.dossier.vigilance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import javax.imageio.ImageIO;

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
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

public abstract class VigilanceDossierGenerateurRapport extends GenererRapport {

	@Override
	protected Map<String, Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters =  super.construireParametres(subject, vo, connection);
		EntiteCardex dossier = (EntiteCardex) vo;
		
		parameters.put("DOSSIER_CLE", dossier.getCle());
		parameters.put("DOSSIER_SITE", dossier.getSite());
		parameters.put("CONFIDENTIALITE", Long.toString(((CardexPrivilege)subject.getPrivilege()).getNiveauConfidentialite()));
		
		try {
			parameters.put("LQ-LOGO", ImageIO.read(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.LQ_LOGO)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		parameters.put("SOUS_RAPPORT_EVALUATION", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION));
			parameters.put("SOUS_RAPPORT_EVALUATION_MISE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_MISE));
				parameters.put("SOUS_RAPPORT_EVALUATION_MISE_JEU", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_MISE_JEU));
				parameters.put("SOUS_RAPPORT_EVALUATION_MISE_VISITE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_MISE_VISITE));
			parameters.put("SOUS_RAPPORT_EVALUATION_FAITS_CONNUS", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_FAITS_CONNUS));
			parameters.put("SOUS_RAPPORT_EVALUATION_ETAT", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_ETAT));
			parameters.put("SOUS_RAPPORT_EVALUATION_SIGNATURE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_SIGNATURE));
			parameters.put("SOUS_RAPPORT_EVALUATION_SIGNATURE_PROPOS", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_EVALUATION_SIGNATURE_PROPOS));

		
		return parameters;
	}

}
