/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringHelper;



/**
 * @author levassc
 */
public class TypeCategorieCle extends CleSQLListeCache{

	public TypeCategorieCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}

	public TypeCategorieCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select C.I_CA_CLE || '/' || C.I_TY_CLE as "+ListeCacheSQL.CLE+", ");
		SQL.append("TC.V_TR_DESCRIPTION || ' - ' || TT.V_TR_DESCRIPTION as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from TY_TYPE P, CA_CATEGORIE C, TR_TRADUCTION TT, TR_TRADUCTION TC ");
		SQL.append("where P.I_TY_CLE = TT.L_TR_CLE ");
		SQL.append("and TT.I_LA_CLE = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append("AND C.I_TY_CLE = P.I_TY_CLE ");
		SQL.append("AND C.I_CA_CLE = TC.L_TR_CLE ");
		SQL.append("AND TC.I_LA_CLE = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "p.i_na_cle", StringHelper.convertirStringEnLong( discriminantValeur ));
		SQL.append("ORDER BY TC.V_TR_DESCRIPTION, TT.V_TR_DESCRIPTION ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

	public static int obtenirNombreSiteApplicableEntiteMaisonJeux(CardexAuthenticationSubject subject) throws BusinessResourceException{
		ListeCache listeCache = ListeCache.getInstance();
		// -1 pour retirer le vide...
		return listeCache.obtenirMap(subject, new TypeCategorieCle(subject, GlobalConstants.Entite.MAISON_JEUX)).size() -1;
	}
}
