package com.lotoquebec.cardex.generateurRapport.acces;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.AccesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;

public class AuditAccesNarrationGenerateurRapport_CDX_0126 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/auditAccesNarration");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new AccesRapportVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		AccesRapportVO rapportDossierVO =(AccesRapportVO) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.auditAccesNarration(rapportDossierVO,connection);

       	return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUDIT_ACCES_NARRATION);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		Map parameters = new HashMap();
		AccesRapportVO rapportDossierVO =(AccesRapportVO) rapportVO;
        parameters.put("DATE_DEBUT",DateFormat.format(rapportDossierVO.getDateHeureDebutDu(), DateFormat.DATE_FORMAT_AVEC_HEURE));
        parameters.put("DATE_FIN",DateFormat.format(rapportDossierVO.getDateHeureDebutAu(), DateFormat.DATE_FORMAT_AVEC_HEURE));
        parameters.put("UTILISATEUR",subject.getPrincipal().getName());
/*        //On met un titre pour le rapport CDX_0126
        parameters.put("TITRE","Rapport sur les acc�s aux narrations");
        
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		try {
			site = listeCache.obtenirLabel(subject, String.valueOf(rapportDossierVO.getSite()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		parameters.put("SITE_DESCRIPTION", site);
        parameters.put("GROUPE_DESCRIPTION", rapportDossierVO.getGroupe());
*/		
        return parameters;
	}
	
}
