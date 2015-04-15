package com.lotoquebec.cardex.generateurRapport.acces;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.entite.SujetRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SujetAuditChangementGenerateurRapport_CDX_0185 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.audit");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		SujetBusinessDelegate delegate = new SujetBusinessDelegate();
		List<Sujet> sujets = delegate.audit(subject, (Sujet)vo);
		List<SujetRapportVO> sujetRapportVOs = new ArrayList<SujetRapportVO>();
		
		for(Sujet sujetVO:sujets){
			SujetRapportVO sujetRapportVO = new SujetRapportVO(sujetVO);
			sujetRapportVO.assignerValeurDeListe(subject);
			sujetRapportVOs.add( sujetRapportVO );
		}
		
       	return new JRBeanCollectionDataSource(sujetRapportVOs);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUDIT_CHANGEMENTS_SUJETS_CDX_0185);
	}

	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
		ListeCache cache = ListeCache.getInstance();
		
		try {
			Sujet sujet = (Sujet) vo;
			String createurDescription = cache.obtenirLabel(subject, sujet.getCreateur(), new IntervenantCle(subject));
			parameters.put("NUMERO_DOSSIER", sujet.getNumeroFiche());
			parameters.put("CREATEUR", createurDescription);
			parameters.put("DATE_CREATION", DateFormat.format(sujet.getDateCreation()));
			return parameters;
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
