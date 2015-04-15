package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;

public class AccesDAO {

	/**
	 * Lecture des acc�s associ�s � un dossier.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public List<AccesVO> select(CardexAuthenticationSubject subject, final EntiteCardex entiteCardex, final String origine) throws DAOException {
        StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
        final List<AccesVO> results = new ArrayList<AccesVO>();
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
                callableStatement.setLong(1, entiteCardex.getCle());
                callableStatement.setLong(2, entiteCardex.getSite());
                callableStatement.setString(3, origine);
                callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			}
    	};
    	storeProcTemplate.prepareCall("CARDEX_SPECIAL.SP_L_AC_ACCES", 4, 4, rch);

    	RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
                AccesVO acces = new AccesVO();
                acces.setCle(rs.getLong("L_AC_CLE"));
                acces.setSite(rs.getLong("L_SI_CLE"));
                acces.setGenreOrigine(OracleDAOUtils.getString(rs,"C_GF_ORIGINE"));
                acces.setCleOrigine(rs.getLong("L_ORI_CLE"));
                acces.setSiteOrigine(rs.getLong("L_ORI_SITE"));
                acces.setCleRef(rs.getLong("L_REF_CLE"));
                acces.setCleRef2(rs.getLong("L_REF2_CLE"));
                acces.setSiteRef(rs.getLong("L_REF_SITE"));
                acces.setSiteRef2(rs.getLong("L_REF2_SITE"));
                acces.setGenreRef(OracleDAOUtils.getString(rs,"C_GF_REFERENCE"));
                acces.setGenreRef2(OracleDAOUtils.getString(rs,"C_GF_REF2"));
                acces.setAction(OracleDAOUtils.getString(rs,"C_AC_ACTION"));
                acces.setUtilisateur(OracleDAOUtils.getString(rs,"V_AC_NAME"));
                acces.setDateAcces(rs.getTimestamp("D_AC_DATE_ACCES"));
                results.add(acces);
			}
    	};		
    	storeProcTemplate.query( rowCallbackHandler);
    	
    	return results;
	}

	/**
	 * Ajout dans la table des acc�s.
     * @author Fran�ois Gu�rin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
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
