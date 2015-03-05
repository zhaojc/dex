/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.StringHelper;



/**
 * @author levassc
 */
public class IntervenantActifParSiteCle extends CleSQLListeCache{

	public IntervenantActifParSiteCle(String langue, String discriminantValeur) {
		super(langue, discriminantValeur);
	}
	
	public IntervenantActifParSiteCle(CardexAuthenticationSubject subject,
			String discriminantValeur) {
		super(subject.getLocale().getLanguage(), discriminantValeur);
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select i.name as "+ListeCacheSQL.CLE+", i.i_st_cle as statut, i.v_in_nom||', '||i.v_in_prenom||' ('||i.name||', '||tr.v_tr_description||') ' as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from in_intervenant i, SI_SITE S, tr_traduction tr ");
		SQL.append("where S.L_SI_CLE = i.L_SI_CLE ");
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "S.L_SI_CLE", StringHelper.convertirStringEnLong( discriminantValeur ));
		
		SQL.append("and i.i_st_cle = "+GlobalConstants.Statut.INTERVENANT_ACTIF+" ");
		SQL.append("and i.l_in_secteur = tr.l_tr_cle ");
		SQL.append("and tr.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append(OracleDAOUtils.sqlOrderBy(ListeCacheSQL.DESCRIPTION));
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
