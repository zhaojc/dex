/*
 * Created on 13-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.CleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.CleMultiExterneListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class ListeCacheSQL {

	public final static String CLE = "cle";
	public final static String DESCRIPTION = "description";

	
	public static PreparerSQL obtenirPreparerSQL(CleListe cleListe){
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		
		if (cleListe instanceof CleListeCache){
			CleListeCache cleListeCache = (CleListeCache) cleListe;
			SQL.append("select T.L_TR_CLE as "+CLE+", ");
			SQL.append("T.V_TR_DESCRIPTION as "+DESCRIPTION+" ");
			SQL.append("from TR_TRADUCTION T ");
			SQL.append("where rtrim(T.C_TR_GENRE_FICHIER) = ? ");
			preparerSQL.addParametre(cleListeCache.getCleListe());
			SQL.append("AND T.I_LA_CLE = ? ");
			preparerSQL.addParametre(OracleDAOUtils.getLongLangue(cleListeCache.getLangue()));
			SQL.append(OracleDAOUtils.sqlOrderBy("T.V_TR_DESCRIPTION"));
			
		}else if (cleListe instanceof CleMultiExterneListeCache){
			CleMultiExterneListeCache cleMultiExterneListeCache = (CleMultiExterneListeCache) cleListe;
			SQL.append("select T."+cleMultiExterneListeCache.getColonneCle()+" as "+CLE+", ");
			SQL.append("T."+cleMultiExterneListeCache.getColonneDescription()+" as "+DESCRIPTION+" ");
			SQL.append("from "+cleMultiExterneListeCache.getNomTable()+" T ");
			
			if (StringUtils.isNotEmpty(cleMultiExterneListeCache.getColonneDiscriminantCle())
			&& StringUtils.isNotEmpty(cleMultiExterneListeCache.getDiscriminantValeur())){
				SQL.append("where rtrim(t."+cleMultiExterneListeCache.getColonneDiscriminantCle()+") = ? ");
				preparerSQL.addTransformerParametre(cleMultiExterneListeCache.getDiscriminantValeur());
			}
			SQL.append(OracleDAOUtils.sqlOrderBy(cleMultiExterneListeCache.getColonneDescription()));
			
		}else if (cleListe instanceof CleMultiListeCache){
			CleMultiListeCache cleMultiListeCache = (CleMultiListeCache) cleListe;
			SQL.append("select T.L_TR_CLE as "+CLE+", ");
			SQL.append("T.V_TR_DESCRIPTION as "+DESCRIPTION+" ");
			SQL.append("from TR_TRADUCTION T, "+cleMultiListeCache.getNomTable()+" p ");
			SQL.append("where T.L_TR_CLE = p."+cleMultiListeCache.getColonneCle()+" ");
			SQL.append("AND T.I_LA_CLE = ? ");
			preparerSQL.addParametre(OracleDAOUtils.getLongLangue(cleMultiListeCache.getLangue()));
			
			if (StringUtils.isNotEmpty( cleMultiListeCache.getColonneDiscriminantCle() )){
				SQL.append("AND rtrim(p."+cleMultiListeCache.getColonneDiscriminantCle()+") = ? ");
				preparerSQL.addTransformerParametre(cleMultiListeCache.getDiscriminantValeur());
			}
			SQL.append(OracleDAOUtils.sqlOrderBy("T.V_TR_DESCRIPTION"));
			
		}else if (cleListe instanceof CleSQLListeCache){
			CleSQLListeCache cleSQLListeCache = (CleSQLListeCache) cleListe;
			return cleSQLListeCache.getPreparerSQL();
		}
		preparerSQL.setSQL(SQL.toString());
		return preparerSQL;
	}

	
}
