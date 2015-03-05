/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * Obtient la collection de valeurs à afficher dans la liste déroulante
 * associée à l'attribut Groupe source (pour la copie des groupes). Les groupes 
 * source sont associés à des sous-groupes ou à des rôles.
 * @author levassc
 */ 
public class GroupeSecuriteSourceCle extends CleSQLListeCache {

	/**
	 * @param langue
	 * @param colonneDiscriminantValeur
	 */
	public GroupeSecuriteSourceCle(String langue) {
		super(langue, "");
		this.discriminantValeurRequis = false;
	}

	public GroupeSecuriteSourceCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		this.discriminantValeurRequis = false;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("SELECT GS.L_GS_CLE as "+ListeCacheSQL.CLE+", GS.V_GS_NOM as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("FROM GS_GROUPE_SECURITE GS ");
		SQL.append("WHERE EXISTS (SELECT * ");
		SQL.append("FROM GR_GROUPE_ROLE GR ");
		SQL.append("WHERE GR.L_GR_CLE = GS.L_GS_CLE) ");
		SQL.append("OR EXISTS (SELECT * ");
		SQL.append("FROM SGR_SOUS_GROUPE_SECURITE SGR ");
		SQL.append("WHERE SGR.L_SGR_PARENT_CLE = GS.L_GS_CLE) ");
		SQL.append("order by "+ListeCacheSQL.DESCRIPTION+" ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
}
