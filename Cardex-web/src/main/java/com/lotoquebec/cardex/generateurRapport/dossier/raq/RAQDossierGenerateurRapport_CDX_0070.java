package com.lotoquebec.cardex.generateurRapport.dossier.raq;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.entite.ActiviteQuotidienneRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public abstract class RAQDossierGenerateurRapport_CDX_0070 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.rapport.activite.quotidienne");
	}
	
	protected abstract List<RapportVOCDX_00070> obtenirRapportVO_CDX_0070(CardexAuthenticationSubject subject, ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO) throws DAOException;
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new ActiviteQuotidienneRapportVO();
	}
	
	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO = (ActiviteQuotidienneRapportVO) rapportVO;

        //Si les dates n'ont pas été sélectionnées, on met par défaut la date de la veille à 6 h 
        //jusqu'à la date du jour à 6 h.
    	if (activiteQuotidienneRapportVO.getDateDebutDu() == null)
    		activiteQuotidienneRapportVO.setDateDebutDu( RapportUtils.dateHier7h(null) );
    	
    	if (activiteQuotidienneRapportVO.getDateDebutAu() == null)
    		activiteQuotidienneRapportVO.setDateDebutAu( RapportUtils.dateAujourdHuiFin6h59(null) );
		
		try {
			List<RapportVOCDX_00070> rapportVOCDX_00070s = obtenirRapportVO_CDX_0070(subject, activiteQuotidienneRapportVO);
	       	return new JRBeanCollectionDataSource(rapportVOCDX_00070s);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_ACTIVITES_CDX_0070);
	}

	@Override
	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
		parameters.put("TITRE", "Rapport d'activité quotidien");
		return parameters;
	}

	
}
