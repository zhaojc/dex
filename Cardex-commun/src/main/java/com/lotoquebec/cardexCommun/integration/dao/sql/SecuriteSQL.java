package com.lotoquebec.cardexCommun.integration.dao.sql;

import java.util.Set;

import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;

public class SecuriteSQL {

	public static String obtenirSQLSousGroupeSecurite(Set<Integer> setGroupes){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select ");  
		stringBuilder.append("sgr.L_SGR_ENFANT_CLE ");
		stringBuilder.append("from SGR_SOUS_GROUPE_SECURITE sgr ");
		stringBuilder.append("where sgr.L_SGR_PARENT_CLE in (");
		stringBuilder.append(OracleDAOUtils.obtenirChaineInteger(setGroupes, ", "));
		stringBuilder.append(") ");

		return stringBuilder.toString();
	}
	
	public static String obtenirSQLGroupeRoles(Set<Integer> setGroupes){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select ");
		stringBuilder.append("V_GR_ROLE ");
		stringBuilder.append("from GR_GROUPE_ROLE ");	
		stringBuilder.append("where L_GR_CLE in (");
		stringBuilder.append(OracleDAOUtils.obtenirChaineInteger(setGroupes, ", "));
		stringBuilder.append(") ");
		
		return stringBuilder.toString();
	}
	
}
