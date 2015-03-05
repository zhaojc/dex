/*
 * Created on 13-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.CleListeAutoCompleter;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class ListeAutoCompleterSQL {

	public final static String VALEUR = "valeur";
	
	public static PreparerSQL obtenirSQL(CardexAuthenticationSubject subject, String valeurDepart, CleListeAutoCompleter cleListeAutoCompleter){
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        String confidentialite = String.valueOf( privilege.getNiveauConfidentialite() );
        CardexUser user = (CardexUser)subject.getUser();
               
		SQL.append("select DISTINCT T."+cleListeAutoCompleter.getChamp()+" as "+VALEUR+" ");
		SQL.append("from "+cleListeAutoCompleter.getTable()+" T ");
		//Si la table est Sujet ou Société, on doit convertir en majuscules. Pour les autres tables, ce n'est pas nécessaire, car les 
		//valeurs sont déjà en majuscules. En n'utilisant pas la conversion UPPER dans le SQL, la liste s'affiche plus rapidement.
		if(cleListeAutoCompleter.getTable().equals(GlobalConstants.Tables.SU_SUJET) || cleListeAutoCompleter.getTable().equals(GlobalConstants.Tables.SO_SOCIETE)){
			SQL.append("where upper(T."+cleListeAutoCompleter.getChamp()+") like ? ");
		}else{
			SQL.append("where T."+cleListeAutoCompleter.getChamp()+" like ? ");
		}
		preparerSQL.addParametre(valeurDepart.toUpperCase()+"%");
		//On filtre la liste selon le niveau de confidentialité et l'entité
		if (StringUtils.isNotEmpty(cleListeAutoCompleter.getChampConfidentialite())){
			SQL.append("AND T."+cleListeAutoCompleter.getChampConfidentialite()+" <= ? ");
			preparerSQL.addParametre(confidentialite);
		}
		
		SQL.append("AND T.L_SI_CLE = ? ");
		preparerSQL.addParametre(user.getSite());
		SQL.append(OracleDAOUtils.sqlOrderBy("T."+cleListeAutoCompleter.getChamp()));
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

	
}
