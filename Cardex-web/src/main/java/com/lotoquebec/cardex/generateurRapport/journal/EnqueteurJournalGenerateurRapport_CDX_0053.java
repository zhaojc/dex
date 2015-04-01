package com.lotoquebec.cardex.generateurRapport.journal;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.EnqueteurJournalRapportVO_CDX_0053;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class EnqueteurJournalGenerateurRapport_CDX_0053 extends GenererRapport {

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/journalEnqueteur");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new EnqueteurJournalRapportVO_CDX_0053();
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		EnqueteurJournalRapportVO_CDX_0053 enqueteurJournalRapportVO_CDX_0053 = (EnqueteurJournalRapportVO_CDX_0053) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	
        //Si le crit�re Nature est vide, on inscrit par d�faut celui du journal des enqu�tes.
		if(enqueteurJournalRapportVO_CDX_0053.getNature() == 0)
			enqueteurJournalRapportVO_CDX_0053.setNature( GlobalConstants.Nature.JOURNAL_ENQUETES );

		ResultSet resultSet = delegate.rapportJournalEnquetes(enqueteurJournalRapportVO_CDX_0053,connection);
       	return new JRResultSetDataSource(resultSet);        
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.JOURNAL_ENQUETES);
	}


}
