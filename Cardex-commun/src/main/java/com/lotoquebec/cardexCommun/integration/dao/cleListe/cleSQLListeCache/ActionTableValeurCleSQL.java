/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;



/**
 * @author levassc
 */
public class ActionTableValeurCleSQL extends CleSQLListeCache{

	public ActionTableValeurCleSQL(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}
	
	public ActionTableValeurCleSQL(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
	}	

	/* Pour extraire la liste des Actions dans la table des valeurs et pour l'afficher dans les écrans sur la table des valeurs.
	 * @see com.lotoquebec.cardex.integration.dao.oracle.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select distinct t.l_tv_valeur as "+ListeCacheSQL.CLE+", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from tv_table_valeur t, tr_traduction tr ");
		SQL.append("where t.v_tv_code = ? ");
		preparerSQL.addParametre(GlobalConstants.TableValeur.ACTIONS);
		
		SQL.append(" and t.l_tv_valeur = tr.l_tr_cle ");
		SQL.append(" and tr.i_la_cle = ? ");
		
		if(langue.equals(GlobalConstants.Langue.ANGLAIS))
			preparerSQL.addParametre(GlobalConstants.Langues.ANGLAIS);
		else
			preparerSQL.addParametre(GlobalConstants.Langues.FRANCAIS);
		SQL.append(" order by "+ListeCacheSQL.DESCRIPTION+" ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
}
