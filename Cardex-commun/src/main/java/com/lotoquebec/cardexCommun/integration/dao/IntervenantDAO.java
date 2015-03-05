package com.lotoquebec.cardexCommun.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.Intervenant;
import com.lotoquebec.cardexCommun.business.vo.IntervenantVO;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.CodeLangue;

public class IntervenantDAO {

    public CardexAuthenticationSubject find(String userName) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        CardexAuthenticationSubject subject = new CardexAuthenticationSubject(userName,"","");
        CardexUser user = new CardexUser(userName);
        CardexPrivilege privilege = new CardexPrivilege();
        subject.setUser(user);
        subject.setPrivilege(privilege);
        subject.assignerActivite();

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_PILOTAGE.SPW_L_PROFIL_INTERVENANT "
                    + "(?,?);"
                    + "end;");
            callableStatement.setString(1,userName);
            callableStatement.registerOutParameter(2,OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            if ( resultSet.next()){
              if ( (resultSet.getString("NAME")       != null || resultSet.getString("NAME").trim().length() > 0) &&
                   resultSet.getLong("L_IN_SECTEUR") != 0 &&
                   resultSet.getInt("I_LA_CLE")  != 0 &&
                   resultSet.getLong("I_EN_CLE") != 0 &&
                   resultSet.getLong("L_SI_CLE") != 0&&
                   resultSet.getLong("I_NH_CLE") != 0&&
                   resultSet.getLong("I_CC_CLE") != 0&&
                   resultSet.getString("V_IN_NAME_PARENT") != null
                  ) {
                    subject.setLocale(CodeLangue.valueOf(resultSet.getInt("I_LA_CLE")));
                    user.setCode(resultSet.getString("NAME"));
                    user.setCodeParent(resultSet.getString("V_IN_NAME_PARENT"));
                    user.setEntite(resultSet.getLong("I_EN_CLE"));
                    user.setSecteur(resultSet.getLong("L_IN_SECTEUR"));
                    user.setSousSecteur(resultSet.getLong("L_IN_SOUS_SECTEUR"));
                    user.setSite(resultSet.getLong("L_SI_CLE"));
                    privilege.setNiveauAuthorite(resultSet.getLong("I_NH_CLE"));
                    privilege.setNiveauConfidentialite(resultSet.getLong("I_CC_CLE"));
                    
                    FabriqueDAO.getInstance().getSecuriteDAO().ajouterRoles(subject);
              }else {
                throw new DAOException("The user profile '"+userName+"' is not correctly defined in cardex ...");
              }
            }else {
              throw new DAOException("The user profile '"+userName+"' is not defined in cardex ...");
            }
            return subject;

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


    public Intervenant findIntervenant(String userName) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        IntervenantVO intervenant = new IntervenantVO();

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_PILOTAGE.SPW_L_PROFIL_INTERVENANT (?,?); end;");
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
                 intervenant.setCreateur(resultSet.getString("V_IN_CREE_PAR"));
                 intervenant.setDateCreation(resultSet.getTimestamp("D_IN_DATE_CREATION"));
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