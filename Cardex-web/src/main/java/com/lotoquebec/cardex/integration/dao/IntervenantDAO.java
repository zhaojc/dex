package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardexCommun.business.Intervenant;
import com.lotoquebec.cardexCommun.business.vo.IntervenantVO;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;

public class IntervenantDAO {

    public Intervenant findIntervenant(String userName) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        IntervenantVO intervenant = new IntervenantVO();

        try {
            callableStatement = connection.prepareCall(
                    "begin SPW_L_PROFIL_INTERVENANT (?,?); end;");
            callableStatement.setString(1,userName);
            callableStatement.registerOutParameter(2,OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            if ( resultSet.next()){
                 intervenant.setCle(resultSet.getLong("L_IN_CLE"));
                 intervenant.setCode(resultSet.getString("NAME"));
                 intervenant.setCodeParent(resultSet.getString("V_IN_NAME_PARENT"));
                 intervenant.setSecteur(resultSet.getLong("L_IN_SECTEUR"));
                 intervenant.setSousSecteur(resultSet.getLong("L_IN_SOUS_SECTEUR"));
                 intervenant.setLangue(resultSet.getLong("I_LA_CLE"));
                 intervenant.setEntite(resultSet.getLong("I_EN_CLE"));
                 intervenant.setStatut(resultSet.getLong("I_ST_CLE"));
                 intervenant.setSite(resultSet.getLong("L_SI_CLE"));
                 intervenant.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                 intervenant.setHierarchie(resultSet.getLong("I_NH_CLE"));
                 intervenant.setCommentaire(resultSet.getString("V_IN_COMMENTAIRE"));
                 intervenant.setNom(resultSet.getString("V_IN_NOM"));
                 intervenant.setPrenom(resultSet.getString("V_IN_PRENOM"));
                 intervenant.setNumero(resultSet.getString("V_IN_NO_EMPLOYE"));
                 intervenant.setNumero(resultSet.getString("V_IN_NO_EMPLOYE"));
                 intervenant.setGestionnaire(resultSet.getString("V_IN_GESTIONNAIRE"));
	            //On complète la fiche de l'intervenant avec les informations enregistrées
	            //dans ClearTrust
	            //ClearTrustDAO cleartrust = new ClearTrustDAO();
	            //intervenant = (IntervenantVO)cleartrust.lireClearTrust(intervenant);
              }
            return intervenant;
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
        }
    }    
}