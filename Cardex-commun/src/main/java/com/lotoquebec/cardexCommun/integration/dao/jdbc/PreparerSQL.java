package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette est utilisé pour lancer des requêtes SQL avec le PrepareStatement
 * qui permet de bloquer les SQL injection.
 * Cas spécial pour les Char Oracle.  Les char utilisent tous les espaces donc
 * "DO" sera "DO " si c'est un char de 3.
 * Utiliser "rtrim" pour l'opérateur de gauche.
 * @author levassc
 *
 */
public class PreparerSQL {

	private StringBuilder SQL = new StringBuilder();
	private List<Object> parametre = new ArrayList<Object>();
	
	public void addParametre(Object o){
		
		if (o instanceof java.util.Date)
			parametre.add(new java.sql.Date(((java.util.Date) o).getTime()));
		else
			parametre.add(o);
	}
	
	public void addParametreHeure(Timestamp timestamp){
		parametre.add(timestamp);
	}

	public void addTransformerParametre(String s){
		
		if (StringUtils.isNumeric(s))
			parametre.add(Integer.valueOf(s));
		else
			parametre.add(s);
	}
	
	public void addParametreWhiteCard(String s){
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		sb.append(s);
		sb.append("%");
		parametre.add(sb.toString());
	}

	@Deprecated
	public void setSQL(String sql) {
		SQL = new StringBuilder(sql);
	}
	
	public void setSQL(StringBuilder sql) {
		SQL = sql;
	}

	public StringBuilder getSQL() {
		return SQL;
	}

	public void assignerPreparedStatement(PreparedStatement preparedStatement) throws SQLException{
		
		for(int i=0;i<parametre.size();i++){
			preparedStatement.setObject(i+1, parametre.get(i));
		}
	}
	
	public void ajouter(PreparerSQL preparerSQL){
		this.SQL.append( preparerSQL.SQL );
		this.parametre.addAll( preparerSQL.parametre );
	}

	@Override
	public String toString() {
		StringBuilder SQLSortie = new StringBuilder(SQL);
		int positionPoint = SQLSortie.toString().indexOf("?", 0);
		int i = 0;
	
		while(positionPoint != -1){
			SQLSortie.replace(positionPoint, positionPoint+1, toString(parametre.get(i)));
			positionPoint = SQLSortie.toString().indexOf("?", positionPoint+1);
			i++;
		}
		return SQLSortie.toString();
	}

	// cette méthode est utiliser seulement en debug
	// ne jamais jamais jamais l'utilisez directement dans une connexion bd
	// il est possible d'avoir de l'SQL insertion
	@Deprecated
	private String toString(Object parametre){
		if (parametre instanceof Integer || parametre instanceof Long)
			return String.valueOf( parametre );
		else if (parametre instanceof String)
			return "'"+(String) parametre+"'";
		else if (parametre instanceof Date)
			return "TO_DATE('"+DateFormat.format((Date) parametre)+"', 'YYYY-MM-DD')";
		return "";
	}

	
}
