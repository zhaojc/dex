package com.lotoquebec.cardexCommun.integration.dao.sql;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

public abstract class ConstruireRechercheSQL {

	protected abstract String selectArgument();
	protected abstract String groupBy();

	public abstract PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteria);

}
