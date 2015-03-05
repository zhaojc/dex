package com.lotoquebec.cardex.business.facade.rapport;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.collections.CollectionUtils;

import com.lotoquebec.cardex.business.facade.rapport.util.InfractionCDX_0257Predicate;
import com.lotoquebec.cardex.business.facade.rapport.util.VisiteCDX_0257Predicate;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

public class ClientMystereCDX_0257RapportCardex extends ClientMystereCDX_0255RapportCardex{

	private final static Logger log = (Logger)LoggerCardex.getLogger(ClientMystereCDX_0257RapportCardex.class);
	
	public ClientMystereCDX_0257RapportCardex(CardexAuthenticationSubject subject) {
		super(subject);
	}
	
	@Override
	public JasperPrint executer() throws BusinessResourceException{
		throw new IllegalArgumentException("Le numeroVisite est requis");
	}
	
	private Collection<ClientMystereVO_CDX_0255> filtrer(int numeroVisite) throws DAOException, CloneNotSupportedException{
		
		if (societeClientMystereMap.isEmpty())
			chargerSocieteMystere();
		Collection<ClientMystereVO_CDX_0255> clientMystereVOs = clone(societeClientMystereMap.values());
		CollectionUtils.filter(clientMystereVOs, new InfractionCDX_0257Predicate());
		CollectionUtils.filter(clientMystereVOs, new VisiteCDX_0257Predicate(numeroVisite));
		return clientMystereVOs;
	}
	
	public List<ClientMystereVO_CDX_0255> clone(Collection<ClientMystereVO_CDX_0255> clientMystereVOs) throws CloneNotSupportedException{
		List<ClientMystereVO_CDX_0255> cloneClientMystereVO = new ArrayList<ClientMystereVO_CDX_0255>();
		
		for(ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255:clientMystereVOs)
			cloneClientMystereVO.add( clientMystereVO_CDX_0255.clone() );
		
		return cloneClientMystereVO;
	}
	
	public JasperPrint executer(int numeroVisite) throws BusinessResourceException{
		
		try {
			InputStream jasperReport = obtenirJasperReport();
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(convertir(filtrer(numeroVisite)));
			return JasperFillManager.fillReport(jasperReport, construireParametres(), beanCollectionDataSource);
		} catch (JRException e) {
			throw (new BusinessResourceException(e));
		} catch (DAOException e) {
			throw (new BusinessResourceException(e));
		} catch (CloneNotSupportedException e) {
			throw (new BusinessResourceException(e));
		}
	}

	@Override
	protected InputStream obtenirJasperReport() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_DETAILLANTS_INFRACTION);
	}

}
