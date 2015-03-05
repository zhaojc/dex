/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;



/**
 * @author levassc
 */
public class IntervenantCle extends CleSQLListeCache{

	public IntervenantCle(String langue) {
		super(langue, "");
		discriminantValeurRequis = false;
	}

	public IntervenantCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		this.discriminantValeurRequis = false;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select i.name as "+ListeCacheSQL.CLE+", i.i_st_cle as statut, i.v_in_nom||', '||i.v_in_prenom||' ('||i.name||', '||tr.v_tr_description||') ' as "+ListeCacheSQL.DESCRIPTION+", ");
		SQL.append("replace(convert(i.v_in_nom || ', ' || i.v_in_prenom || ' (' || tr.v_tr_description || ') ', 'US7ASCII'), '-', 'a') as descriptionConvert ");
		SQL.append("from in_intervenant i, tr_traduction tr ");
		SQL.append("where i.i_st_cle = "+GlobalConstants.Statut.INTERVENANT_ACTIF+" ");
		SQL.append("and i.l_in_secteur = tr.l_tr_cle ");
		SQL.append("and tr.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append("union ");
		
		SQL.append("select i.name as "+ListeCacheSQL.CLE+", i.i_st_cle as statut, i.v_in_nom||', '||i.v_in_prenom||' (Inactif) ('||i.name||', '||tr.v_tr_description||') ' as "+ListeCacheSQL.DESCRIPTION+", ");
		SQL.append("replace(convert(i.v_in_nom || ', ' || i.v_in_prenom || ' (' || tr.v_tr_description || ') ', 'US7ASCII'), '-', 'a') as descriptionConvert ");
		SQL.append("from in_intervenant i, tr_traduction tr ");
		SQL.append("where i.i_st_cle = "+GlobalConstants.Statut.INTERVENANT_INACTIF+" ");
		SQL.append("and i.l_in_secteur = tr.l_tr_cle ");
		SQL.append("and tr.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append("order by statut, descriptionConvert");	
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
