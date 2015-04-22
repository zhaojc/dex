package com.lotoquebec.cardex.generateurRapport.clientMystere;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.collections.CollectionUtils;

import com.lotoquebec.cardex.business.facade.rapport.util.InfractionCDX_0257Predicate;
import com.lotoquebec.cardex.business.facade.rapport.util.VisiteCDX_0257Predicate;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

public class ClientMystereGenerateurRapport_CDX_0257 extends ClientMystereGenerateurRapport_CDX_0255 {

	//private final static Logger log = LoggerFactory.getLogger(ClientMystereGenerateurRapport_CDX_0257.class);
	private int numeroVisite;
	
	public ClientMystereGenerateurRapport_CDX_0257(int numeroVisite) {
		super();
		this.numeroVisite = numeroVisite;
	}

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
	}
	
	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		return new JRBeanCollectionDataSource(convertir(filtrer(subject,numeroVisite)));
	}
	
	
	private Collection<ClientMystereVO_CDX_0255> filtrer(CardexAuthenticationSubject subject, int numeroVisite) {
		
		if (societeClientMystereMap.isEmpty())
			chargerSocieteMystere(subject);
		try {
			Collection<ClientMystereVO_CDX_0255> clientMystereVOs = clone(societeClientMystereMap.values());
			CollectionUtils.filter(clientMystereVOs, new InfractionCDX_0257Predicate());
			CollectionUtils.filter(clientMystereVOs, new VisiteCDX_0257Predicate(numeroVisite));
			return clientMystereVOs;

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<ClientMystereVO_CDX_0255> clone(Collection<ClientMystereVO_CDX_0255> clientMystereVOs) throws CloneNotSupportedException{
		List<ClientMystereVO_CDX_0255> cloneClientMystereVO = new ArrayList<ClientMystereVO_CDX_0255>();
		
		for(ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255:clientMystereVOs)
			cloneClientMystereVO.add( clientMystereVO_CDX_0255.clone() );
		
		return cloneClientMystereVO;
	}
	
	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_DETAILLANTS_INFRACTION_CDX_0257);
	}

	
}
