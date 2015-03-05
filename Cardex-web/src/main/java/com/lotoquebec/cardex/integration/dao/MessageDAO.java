package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.CodeLangue;

/**
 * Retourne tous les messsages cardex.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.6 $, $Date: 2002/02/18 23:54:11 $
 * @see com.lotoquebec.cardex.integration.MessageDAO
 */
public class MessageDAO {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger(MessageDAO.class);

    /**
     * Ajoute les messages obtenus dans le HashMap.
     *
     * @param results Structure resultantes de toute les messages collectés
     * dans la table de la base de donnée Oracle.
     * @param locale Locale indiquant quelle est la langue des messages à
     * obtenir.
     * @param storeProcedureName Nom de la "store procedure" à utiliser pour
     * obtenir la bonne collection de valeurs.
     * @param labelColumnName Nom de la colonne de la valeur à afficher.
     * @param keyColumnName Nom de la colonne dans la table Oracle
     * correspondant à la clé.
     * @throws DAOException lancée lorsqu'une SQLException est reçu lors d'une
     * rupture de connexion avec la base de donnée, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "store procedure".
     */
    private void retrieveMessages(HashMap results, Locale locale,
                                  String storeProcedureName,
                                  String valueColumnName,
                                  String keyColumnName,
                                  int languageId) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall("begin " + storeProcedureName
                                                    + " (?); end;");

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);
            while (resultSet.next()) {
                String retrievedLanguageId = resultSet.getString("I_LA_CLE");
                if (retrievedLanguageId.equals(Integer.toString(languageId))) {
                    String key = resultSet.getString(keyColumnName);
                    String value = resultSet.getString(valueColumnName);
                    log.fine("  "  + storeProcedureName
                                    + ": [Message key='" + key
                                    + "' value='" + value + "']");
                    results.put(key, value);
                }
            }
        } catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }

    /**
     * Retourne un Map de tous les messsages cardex.  La clé est
     * l'identifiant du message et la valeur la description du message
     * dans la locale spécifiée.
     *
     * @param locale Locale indiquant quelle est la langue des messages à
     * obtenir.
     * @throws DAOException lancée lorsqu'une SQLException est reçu lors d'une
     * rupture de connexion avec la base de donnée, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "store procedure".
     */
    public Map getMessages(Locale locale) throws DAOException {
        log.fine("getMessages()");
        HashMap results = new HashMap();
        CodeLangue codeLangue = CodeLangue.valueOf(locale);
        retrieveMessages(   results, locale, "CARDEX_SPECIAL.SP_L_MESSAGES",
                            "MSGTEXT", "MSGID", codeLangue.intValue());
        log.fine("getMessages()");
        retrieveMessages(   results, locale, "SPW_L_DICTIONNAIRE", "V_TEXTE",
                            "V_NOM_OBJET", codeLangue.intValue());
        return (Map) results;
    }

}