/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.StringHelper;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */ 
public class RoleSecuriteAdministrerCle extends CleSQLListeCache {

	private String application = "";
	
	/**
	 * Le discriminant est l'écran
	 * @param langue
	 * @param colonneDiscriminantValeur
	 */
	public RoleSecuriteAdministrerCle(String langue, String discriminantValeur) {
		super(langue, discriminantValeur);
		this.discriminantValeurRequis = false;
	}

	public RoleSecuriteAdministrerCle(CardexAuthenticationSubject subject, String application, String ecran) {
		super(subject.getLocale().getLanguage(), ecran);
		this.application = application;
		this.discriminantValeurRequis = false;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		
		// Avec autre nous affichons seulement les rôles de la table de valeur
		if (GlobalConstants.Ecran.AUTRES.equals( discriminantValeur )){
			SQL.append("select t.v_tv_role as "+ListeCacheSQL.CLE+", t.v_tv_role as "+ListeCacheSQL.DESCRIPTION+" ");
			SQL.append("from tv_table_valeur t ");
			SQL.append("where t.v_tv_role is not null ");
			SQL.append("and t.b_tv_administrer = 'yes' ");
		}else{
			
			if (StringUtils.isNotEmpty( discriminantValeur ) || StringUtils.isNotEmpty(application)){
				SQL.append("select R.V_RS_ROLE as "+ListeCacheSQL.CLE+", R.V_RS_ROLE as "+ListeCacheSQL.DESCRIPTION+" ");
				SQL.append("FROM RS_ROLE_SECURITE R ");
				SQL.append("where r.b_rs_administrer = 'yes' ");
				OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "r.l_rs_ecran", StringHelper.convertirStringEnLong( discriminantValeur ));
				OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "r.v_rs_application", application);
				
				SQL.append("union ");
				SQL.append("select R.V_RS_ROLE||'.inactif' as "+ListeCacheSQL.CLE+", R.V_RS_ROLE||'.inactif' as "+ListeCacheSQL.DESCRIPTION+" ");
				SQL.append("FROM RS_ROLE_SECURITE R ");
				SQL.append("where r.b_rs_administrer = 'yes' ");
				SQL.append("and r.v_rs_classe_form is not null ");
				SQL.append("and r.v_rs_url is null ");
				OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "r.l_rs_ecran", StringHelper.convertirStringEnLong( discriminantValeur ));
				OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "r.v_rs_application", application);				
				
			}else{
				SQL.append("select R.V_RS_ROLE as "+ListeCacheSQL.CLE+", R.V_RS_ROLE as "+ListeCacheSQL.DESCRIPTION+" ");
				SQL.append("FROM RS_ROLE_SECURITE R ");
				SQL.append("where r.b_rs_administrer = 'yes' ");
				SQL.append("union ");
				SQL.append("select R.V_RS_ROLE||'.inactif' as "+ListeCacheSQL.CLE+", R.V_RS_ROLE||'.inactif' as "+ListeCacheSQL.DESCRIPTION+" ");
				SQL.append("FROM RS_ROLE_SECURITE R ");
				SQL.append("where r.b_rs_administrer = 'yes' ");
				SQL.append("and r.v_rs_classe_form is not null ");
				SQL.append("and r.v_rs_url is null ");
				SQL.append("union ");
				SQL.append("select t.v_tv_role as "+ListeCacheSQL.CLE+", t.v_tv_role as "+ListeCacheSQL.DESCRIPTION+" ");
				SQL.append("from tv_table_valeur t ");
				SQL.append("where t.v_tv_role is not null ");
				SQL.append("and t.b_tv_administrer = 'yes' ");
			}
		}
		SQL.append("order by "+ListeCacheSQL.CLE+" ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
	public String getApplication() {
		return application;
	}

	public boolean equals(Object obj) {
		
		if (super.equals(obj) == false)
			return false;
		
		RoleSecuriteAdministrerCle roleSecuriteAdministrerCle = (RoleSecuriteAdministrerCle) obj;
		
		if (StringUtils.equals( application, roleSecuriteAdministrerCle.getApplication() ) == false){
			return false;
		}
		
		return true;
	}
}
