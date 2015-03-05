package com.lotoquebec.cardex.generateurRapport.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

public abstract class GenererRapportSQL extends GenererRapport {

	// Construire le contenu du rapport

	protected abstract PreparerSQL construirePreparerSQL(RapportVO rapportVO);

	protected JRDataSource construireDataSource(
			CardexAuthenticationSubject subject, RapportVO rapportVO,
			Connection connection) throws BusinessResourceException,
			BusinessException {

		try {
			PreparerSQL preparerSQL = construirePreparerSQL(rapportVO);
			PreparedStatement ps = connection.prepareStatement(preparerSQL
					.getSQL().toString());
			preparerSQL.assignerPreparedStatement(ps);
			return new JRResultSetDataSource(ps.executeQuery());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
