package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class ComparatifAnnuelEnquetesDossierGenerateurRapport_CDX_0040 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersComparatifAnnuelEnquetes");
	}
	
	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportProcedure(rapportVO, "CARDEX_RAPPORT.SP_RAP_CUMUL_ANNUEL");
		List liste = remplirCumul(resultSet);
		return new JRMapCollectionDataSource(liste);
	}

    private List remplirCumul(ResultSet resultSet) {
		List list = new ArrayList();

		try {
			// Le nombre d'entrée retourné est fixe. On traite chacune des
			// entrées en fonction de la nature des enquêtes.
			// Nature Événements
			Map evenements = new HashMap();
			resultSet.next();
			evenements.put("NATURE", resultSet.getString(1));
			evenements.put("ENQUETES_OUVERTS_COURANT", resultSet
					.getBigDecimal(2));
			resultSet.next();
			evenements.put("NATURE", resultSet.getString(1));
			evenements.put("ENQUETES_OUVERTS_PASSES", resultSet
					.getBigDecimal(2));
			resultSet.next();
			evenements.put("NATURE", resultSet.getString(1));
			evenements.put("ENQUETES_FERMES_COURANT", resultSet
					.getBigDecimal(2));
			resultSet.next();
			evenements.put("NATURE", resultSet.getString(1));
			evenements
					.put("ENQUETES_FERMES_PASSES", resultSet.getBigDecimal(2));
			list.add(evenements);
			// Nature Plaintes
			Map plaintes = new HashMap();
			resultSet.next();
			plaintes.put("NATURE", resultSet.getString(1));
			plaintes
					.put("ENQUETES_OUVERTS_COURANT", resultSet.getBigDecimal(2));
			resultSet.next();
			plaintes.put("NATURE", resultSet.getString(1));
			plaintes.put("ENQUETES_OUVERTS_PASSES", resultSet.getBigDecimal(2));
			resultSet.next();
			plaintes.put("NATURE", resultSet.getString(1));
			plaintes.put("ENQUETES_FERMES_COURANT", resultSet.getBigDecimal(2));
			resultSet.next();
			plaintes.put("NATURE", resultSet.getString(1));
			plaintes.put("ENQUETES_FERMES_PASSES", resultSet.getBigDecimal(2));
			list.add(plaintes);
			// Nature Réclamations
			Map reclamations = new HashMap();
			resultSet.next();
			reclamations.put("NATURE", resultSet.getString(1));
			reclamations.put("ENQUETES_OUVERTS_COURANT", resultSet
					.getBigDecimal(2));
			resultSet.next();
			reclamations.put("NATURE", resultSet.getString(1));
			reclamations.put("ENQUETES_OUVERTS_PASSES", resultSet
					.getBigDecimal(2));
			resultSet.next();
			reclamations.put("NATURE", resultSet.getString(1));
			reclamations.put("ENQUETES_FERMES_COURANT", resultSet
					.getBigDecimal(2));
			resultSet.next();
			reclamations.put("NATURE", resultSet.getString(1));
			reclamations.put("ENQUETES_FERMES_PASSES", resultSet
					.getBigDecimal(2));
			list.add(reclamations);
			// Nature Journal
			Map journal = new HashMap();
			resultSet.next();
			journal.put("NATURE", resultSet.getString(1));
			journal.put("ENQUETES_OUVERTS_COURANT", resultSet
					.getBigDecimal(2));
			resultSet.next();
			journal.put("NATURE", resultSet.getString(1));
			journal.put("ENQUETES_OUVERTS_PASSES", resultSet
					.getBigDecimal(2));
			resultSet.next();
			journal.put("NATURE", resultSet.getString(1));
			journal.put("ENQUETES_FERMES_COURANT", resultSet
					.getBigDecimal(2));
			resultSet.next();
			journal.put("NATURE", resultSet.getString(1));
			journal.put("ENQUETES_FERMES_PASSES", resultSet
					.getBigDecimal(2));
			list.add(journal);

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}
	
	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.CUMUL_ANNUEL);
	}

}
