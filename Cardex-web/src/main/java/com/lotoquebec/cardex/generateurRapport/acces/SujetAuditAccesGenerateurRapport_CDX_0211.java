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
import com.lotoquebec.cardex.business.delegate.AccesBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.entite.AccesRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SujetAuditAccesGenerateurRapport_CDX_0211 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.acces.selectAccesSujet");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		AccesBusinessDelegate delegate = new AccesBusinessDelegate();
		List<AccesVO> accesVOs = delegate.selectAccesSujet(subject, (EntiteCardex)vo);
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
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUDIT_ACCES_SUJETS_CDX_0211);
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
