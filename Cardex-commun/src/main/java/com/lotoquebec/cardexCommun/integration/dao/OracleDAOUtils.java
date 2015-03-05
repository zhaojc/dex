package com.lotoquebec.cardexCommun.integration.dao;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.sql.rowset.serial.SerialArray;

import oracle.sql.ARRAY;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Offre tout les services d'utilités associés à la récupération de donnée d'une
 * base de donnée Oracle.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $, $Date: 2002/03/13 17:49:34 $
 */
public class OracleDAOUtils {
    
    private static final String CARACTERES_NON_NUMERIQUE = "/\\/-^¨:.,;=#+()$_&*!@%+ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzàÀâÂäÄéèÉÈëËîÎìÌïÏôÔöÖôÔòÒûùÛÙüÜçÇ";
	private static BigDecimal zeroBD = new BigDecimal("0.00");
    /**
     * Constructeur OracleDAOUtils par défaut.
     */
    private OracleDAOUtils() {
    }

    /**
     * Affecte une primitive de type "long".
     *
     * @param callableStatement
     * @param parameterIndex
     * @param value
     * @throws SQLException
     */
    public static void setLong(CallableStatement callableStatement,int parameterIndex, long value) throws SQLException {
        if (value == 0) {
            callableStatement.setNull(parameterIndex,Types.DECIMAL);
        }
        else {
            callableStatement.setLong(parameterIndex,value);
        }
    }

    public static void setStringEmptyNull(CallableStatement callableStatement,int parameterIndex, String value) throws SQLException {
        if (StringUtils.isEmpty(value)) {
            callableStatement.setNull(parameterIndex,Types.VARCHAR);
        }
        else {
            callableStatement.setString(parameterIndex,value);
        }
    }

    /**
    * Retourne une string sans les caractères numériques
    * @param s (string à manipuler)
    * @param parameterIndex
    * @throws SQLException
    */
    public static String retirerCaracteresNonNumeriques(String s){
        return s.replaceAll("[^0-9]", "");
    }
    
    public static String chaineNonNumeriques(){
        return CARACTERES_NON_NUMERIQUE;
    }
    /**
     *
     *
     * @param resultSet
     * @param parameterIndex
     * @throws SQLException
     */
    public static String getString(ResultSet resultSet,String columnName) throws SQLException {
        if (resultSet.getString(columnName) != null) {
          return resultSet.getString(columnName);
        }else {
          return "";
        }
    }

    /**
     * Affecte une date de recherche.
     *
     * @param callableStatement
     * @param parameterIndex
     * @param value
     * @throws SQLException
     */
    public static void setDateRecherche(CallableStatement callableStatement,
            int parameterIndex, Timestamp value) throws SQLException  {
        if (value != null) {
            callableStatement.setString(parameterIndex,
                    TimestampFormat.format(value));
        }
        else{
            callableStatement.setString(parameterIndex,"1800-01-01 00:00:00");
        }
    }

    public static void setDate(CallableStatement callableStatement, int parameterIndex, java.util.Date value) throws SQLException  {
        if (value != null) {
            callableStatement.setDate(parameterIndex, new Date(value.getTime()));
        }else{
        	callableStatement.setNull(parameterIndex,java.sql.Types.DATE);
        }
    }

    public static void setTimeStamp(CallableStatement callableStatement, int parameterIndex, java.util.Date value) throws SQLException  {
        if (value != null) {
            callableStatement.setTimestamp(parameterIndex, new Timestamp(value.getTime()));
        }else{
        	callableStatement.setNull(parameterIndex,java.sql.Types.TIMESTAMP);
        }
    }
    
    public static java.util.Date getDate(java.util.Date value) throws SQLException  {
        if (value != null) {
            return new java.util.Date(value.getTime());
        }else{
        	return null;
        }
    }
    
    /**
     * Test d'une valeur nulle dans un critère de recherche de dossier.
     *
     * @param valeur
     * @return boolean
     * @deprecated utiliser StringUtils
     */
    public static boolean isEmpty(String valeur) {
        if (valeur == null || valeur.trim().length() == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Conversion d'un CLOB de la base de données en String.  Les champs de type
     * CLOB se retrouvent dans la table CO_COMMENTAIRE2.
     *
     * @param texteCLOB
     * @return String Texte converti.
     * @throws SQLException 
     * @throws DAOException
     */
    public static String CLOBToString(oracle.sql.CLOB texteCLOB) throws SQLException{
            int longueur = (int)texteCLOB.length();
            String texteConverti = texteCLOB.getSubString(1, longueur);
            return texteConverti;
    }

    public static boolean convertirStringABoolean(String aConvertir){
    	
    	if (GlobalConstants.SQL.TRUE.equals( aConvertir ))
    		return true;
    	
    	else if (GlobalConstants.SQL.FALSE.equals( aConvertir ))
    		return false;
    	
    	return false;
    }

    /*
     * Par defaut, si aConvertir est null c'est false
     */
    public static String convertirBooleanAString(Boolean aConvertir){
    	
    	if ( aConvertir != null && aConvertir )
    		return GlobalConstants.SQL.TRUE;
    	else
    		return GlobalConstants.SQL.FALSE;
    }
    
	public static long getLongLangue(String langue) {
		
		if (GlobalConstants.Langue.FRANCAIS.equals( langue ))
			return GlobalConstants.Langues.FRANCAIS;

		if (GlobalConstants.Langue.ANGLAIS.equals( langue ))
			return GlobalConstants.Langues.ANGLAIS;

		return 0;
	}    

	public static long getLongLangue(Locale locale) {
		
		if (locale.equals(Locale.CANADA_FRENCH))
			return GlobalConstants.Langues.FRANCAIS;
		else
			return GlobalConstants.Langues.ANGLAIS;
	}
	
	public static Locale getLocale(int langue) {
		
		if (GlobalConstants.Langues.FRANCAIS == langue )
			return Locale.CANADA_FRENCH;
		else
			return Locale.US;
	}	
	
	public static String sqlOrderBy(String champ){
		return "order by replace(convert("+champ+",'US7ASCII'),'-','a') ";
	}

	public static String champRecherche(PreparerSQL preparerSQL, String champ, String valeur){
		preparerSQL.addParametreWhiteCard(valeur.toUpperCase());
		return "convert(upper("+champ+"), 'US7ASCII') LIKE convert(?, 'US7ASCII') ";
	}
	
	@Deprecated
	public static void ajouterChampSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, long valeur){
	    if (valeur != 0){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" = ? ");
	        preparerSQL.addParametre(valeur);
	   }
	}
	
	public static void ajouterChampSQL(PreparerSQL preparerSQL, String champ, long valeur){
	    if (valeur != 0){
	    	preparerSQL.getSQL().append(" AND ");
	    	preparerSQL.getSQL().append(champ);
	    	preparerSQL.getSQL().append(" = ? ");
	        preparerSQL.addParametre(valeur);
	   }
	}
	
	// C'est laid, mais c'est la seule façon de passer un array que j'ai trouvé
	public static void ajouterChampSQL(PreparerSQL preparerSQL, String champ, long[] valeurs){
	    if (valeurs.length != 0){
	    	preparerSQL.getSQL().append(" AND ");
	    	preparerSQL.getSQL().append(champ);
	    	preparerSQL.getSQL().append(" in ("+construireParametre(valeurs.length)+") ");

	    	for(long l:valeurs)
	    		preparerSQL.addParametre(l);
	   }
	}	
	
	public static String construireParametre(int nbParametre){
		String param = StringUtils.repeat("?,", nbParametre);
		return StringUtils.left(param, param.length()-1 );
	}
	
	@Deprecated
	public static void ajouterChampSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, BigDecimal valeur){
	    if (valeur != null && valeur != zeroBD){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" = ?");
	        preparerSQL.addParametre(valeur);
	   }
	}	

	@Deprecated
	public static void ajouterChampConvertSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, String valeur){
	    if (StringUtils.isNotEmpty(valeur)){
	        query.append(" AND ");
	        query.append(champRecherche(preparerSQL, champ, valeur));
	   }
	}
	
	public static void ajouterChampConvertSQL(PreparerSQL preparerSQL, String champ, String valeur){
	    
		if (StringUtils.isNotEmpty(valeur)){
	    	preparerSQL.getSQL().append(" AND ");
	    	preparerSQL.getSQL().append(champRecherche(preparerSQL, champ, valeur));
	   }
	}
		
	
	@Deprecated
	public static void ajouterChampSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, String valeur){
	    if (StringUtils.isNotEmpty(valeur)){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" = ?");
	        preparerSQL.addParametre(valeur);
	   }
	}

	/**
	    * Ajoute un champ dans un SQL qui sera comparé à une valeur formatée sans aucun caractère alphabétique
	    * Les 4 premiers translate enlèvent les caractères espéciaux (espace, /, \ et '
	    * Le dernier tranlate enlève les caractères indentifiés dans CARACTERES_NON_NUMERIQUE du champ de recherche.
	    * En plus, la valeur persistée dans la BD sera aussi numérique (appel de retirerCaracteresNonNumeriques)
	    * 
	    * @param preparerSQL (PreparerSQL à manipuler)
	    * @param champ (String qui représente la colonne de la BD)
	    * @param valeur (String qui représente la valeur à formater)
	    */
	public static void ajouterChampSQLConvertChaineAsChaineNumerique(PreparerSQL preparerSQL, String champ, String valeur){
	    if (StringUtils.isNotEmpty(valeur)){
	        preparerSQL.getSQL().append("translate(translate(translate(translate(translate(");
            preparerSQL.getSQL().append(champ);	        
	        preparerSQL.getSQL().append(", ' ', '*'),'/','*'),'\"','*'),'''','*'),'"); 
	        preparerSQL.getSQL().append(OracleDAOUtils.chaineNonNumeriques()); 
	        preparerSQL.getSQL().append("',' ')=");
	        preparerSQL.getSQL().append(OracleDAOUtils.retirerCaracteresNonNumeriques(valeur)); 
	    }
	}
	
	public static void ajouterChampSQL(PreparerSQL preparerSQL, String champ, String valeur){
	    if (StringUtils.isNotEmpty(valeur)){
	    	preparerSQL.getSQL().append(" AND ");
	    	preparerSQL.getSQL().append(champ);
	    	preparerSQL.getSQL().append(" = ?");
	        preparerSQL.addParametre(valeur);
	   }
	}
	
	@Deprecated
	public static void ajouterChampLikeSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, String valeur){
	    if (StringUtils.isNotEmpty(valeur)){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" like ?");
	        preparerSQL.addParametreWhiteCard(valeur.toUpperCase().trim());
	   }
	}
	
	public static void ajouterChampLikeSQL(PreparerSQL preparerSQL, String champ, String valeur){
	    if (StringUtils.isNotEmpty(valeur)){
	    	preparerSQL.getSQL().append(" AND ");
	    	preparerSQL.getSQL().append(champ);
	    	preparerSQL.getSQL().append(" like ? ");
	        preparerSQL.addParametreWhiteCard(valeur.toUpperCase().trim());
	   }
	}	
	
	@Deprecated
	public static void ajouterChampSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, String separateur, Timestamp valeur){
	    if (valeur != null){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" ");
	        query.append(separateur);
	        query.append(" ? ");
	        preparerSQL.addParametre(valeur);
	   }
	}	
	
	@Deprecated
	public static void ajouterChampSQL(PreparerSQL preparerSQL, StringBuilder query, String champ, String separateur, java.util.Date valeur){
	    if (valeur != null){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" ");
	        query.append(separateur);
	        query.append(" ? ");
	        preparerSQL.addParametre(new Date(valeur.getTime()));
	   }
	}
	
	public static void ajouterChampSQL(PreparerSQL preparerSQL, String champ, String separateur, java.util.Date valeur){
	    if (valeur != null){
	        preparerSQL.getSQL().append(" AND ");
	        preparerSQL.getSQL().append(champ);
	        preparerSQL.getSQL().append(" ");
	        preparerSQL.getSQL().append(separateur);
	        preparerSQL.getSQL().append(" ? ");
	        preparerSQL.addParametre(new Date(valeur.getTime()));
	   }
	}
	
	@Deprecated
	public static void ajouterChampSQLHeure(PreparerSQL preparerSQL, StringBuilder query, String champ, String separateur, Timestamp valeur){
	    if (valeur != null){
	        query.append(" AND ");
	        query.append(champ);
	        query.append(" ");
	        query.append(separateur);
	        query.append(" ? ");
	        preparerSQL.addParametreHeure(valeur);
	   }
	}
	
	public static String obtenirChaineInteger(Set<Integer> setGroupes, String separateur) {
		StringBuilder retour = new StringBuilder("");
		
		for(Integer valeur:setGroupes){
			
			if (retour.length() > 0){
				retour.append(" ");
				retour.append(separateur);
				retour.append(" ");
			}
			retour.append(valeur);
		}
		return retour.toString();
	}
	
}