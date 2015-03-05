/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;



/**
 * Sert à retrouver le type d'une catégorie
 * @author guerinf
 */
public class CategorieTypeCle extends CleSQLListeCache{

	public CategorieTypeCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}

	public CategorieTypeCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
	}
	
	public CategorieTypeCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), null);
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select C.I_CA_CLE as "+ListeCacheSQL.CLE+", ");
		SQL.append("C.I_TY_CLE as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from CA_CATEGORIE C");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
