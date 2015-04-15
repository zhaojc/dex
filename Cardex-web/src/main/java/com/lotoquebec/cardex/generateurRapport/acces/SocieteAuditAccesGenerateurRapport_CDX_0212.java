package com.lotoquebec.cardex.generateurRapport.acces;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.AccesBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.entite.AccesRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SocieteAuditAccesGenerateurRapport_CDX_0212 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.acces.selectAccesSociete");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		AccesBusinessDelegate delegate = new AccesBusinessDelegate();
		List<AccesVO> accesVOs = delegate.selectAccesSociete(subject, (Societe)vo);
		List<AccesRapportVO> accesRapportVOs = new ArrayList<AccesRapportVO>();
		
		for(AccesVO accesVO:accesVOs){
			AccesRapportVO accesRapportVO = new AccesRapportVO(accesVO);
			accesRapportVO.assignerValeurDeListe(subject);
			accesRapportVOs.add( accesRapportVO );
		}
		
       	return new JRBeanCollectionDataSource(accesRapportVOs);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUDIT_ACCES_SOCIETES_CDX_212);
	}

	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
		ListeCache cache = ListeCache.getInstance();
		
		try {
			Societe societe = (Societe) vo;
			String createurDescription = cache.obtenirLabel(subject, societe.getCreateur(), new IntervenantCle(subject));
			parameters.put("NUMERO_DOSSIER", societe.getNumeroFiche());
			parameters.put("CREATEUR", createurDescription);
			parameters.put("DATE_CREATION", DateFormat.format(societe.getDateCreation()));
			return parameters;
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
