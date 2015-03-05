package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;

public class AccesDAO {

	/**
	 * Lecture des accès associés à un dossier.
     * @author François Guérin
     * @param cle  long : clé unique du dossier
     * @param site long : site
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retournés par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ValueListIterator select(CardexAuthenticationSubject subject, long cle, long site, String origine) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        ArrayList results = new ArrayList();
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_SPECIAL.SP_L_AC_ACCES (?,?,?,?); end;");
            callableStatement.setLong(1,cle);
            callableStatement.setLong(2,site);
            callableStatement.setString(3,origine);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            ValueListHandler  listIterator = new ValueListHandler();
            //Traitement des données retournées
            while (resultSet.next()){
                AccesVO acces = new AccesVO();
                acces.setCle(resultSet.getLong("L_AC_CLE"));
                acces.setSite(resultSet.getLong("L_SI_CLE"));
                acces.setGenreOrigine(OracleDAOUtils.getString(resultSet,"C_GF_ORIGINE"));
                acces.setCleOrigine(resultSet.getLong("L_ORI_CLE"));
                acces.setSiteOrigine(resultSet.getLong("L_ORI_SITE"));
                acces.setCleRef(resultSet.getLong("L_REF_CLE"));
                acces.setCleRef2(resultSet.getLong("L_REF2_CLE"));
                acces.setSiteRef(resultSet.getLong("L_REF_SITE"));
                acces.setSiteRef2(resultSet.getLong("L_REF2_SITE"));
                acces.setGenreRef(OracleDAOUtils.getString(resultSet,"C_GF_REFERENCE"));
                acces.setGenreRef2(OracleDAOUtils.getString(resultSet,"C_GF_REF2"));
                acces.setAction(OracleDAOUtils.getString(resultSet,"C_AC_ACTION"));
                acces.setUtilisateur(OracleDAOUtils.getString(resultSet,"V_AC_NAME"));
                acces.setDateAcces(resultSet.getTimestamp("D_AC_DATE_ACCES"));
                results.add(acces);
			}
            listIterator.setList(results);
            return listIterator;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        catch (IteratorException ie) {
            throw new DAOException(ie);
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
        } //finally
	}

	/**
	 * Ajout dans la table des accès.
     * @author François Guérin
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
	 * @see AccesDAO#select(long, long)
	 */
	public void ajoutAcces(CardexAuthenticationSubject subject, long cle, long site, String origine) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        ArrayList results = new ArrayList();
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_SPECIAL.SP_E_AC_ACCES (?,?,?,?,?,?,?,?,?,?); end;");
            callableStatement.setString(1,"S");
            callableStatement.setString(2,origine);
            callableStatement.setLong(3,cle);
            callableStatement.setLong(4,site);
            callableStatement.setString(5,origine);
            callableStatement.setLong(6,cle);
            callableStatement.setLong(7,site);
            callableStatement.setString(8,origine);
            callableStatement.setLong(9,cle);
            callableStatement.setLong(10,site);
            callableStatement.execute();
        }
        catch (SQLException se) {
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
        } //finally
	}
	
}
