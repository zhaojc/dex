/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexUser;



/**
 * @author levassc
 */
public class SocieteParClasseCle extends CleSQLListeCache{

	private long site = 0; 
	public SocieteParClasseCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);

		this.discriminantValeurRequis = true;
	}

	public SocieteParClasseCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		CardexUser user = (CardexUser) subject.getUser();
		site = user.getSite();
		this.discriminantValeurRequis = true;
	}
	 
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select s.l_so_cle||'-'||s.l_si_cle as "+ListeCacheSQL.CLE+", s.v_so_nom as "+ListeCacheSQL.DESCRIPTION);
		SQL.append(" from so_societe s ");
		//SQL.append(" where s.l_si_cle = ? ");
		//preparerSQL.addParametre(Integer.valueOf(String.valueOf(site)));
		SQL.append(" where s.i_cl_cle = ? ");
		preparerSQL.addParametre(Integer.valueOf(discriminantValeur));
		SQL.append("and s.i_cc_cle is not null ");
		SQL.append("and s.i_cc_cle <> 14920 ");
		SQL.append("order by s.v_so_nom ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
}
