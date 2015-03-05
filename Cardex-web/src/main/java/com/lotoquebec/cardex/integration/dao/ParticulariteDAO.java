package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.CaracteristiquesVO;
import com.lotoquebec.cardex.business.vo.ParticularitesVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Liste des appels à la base de données pour différents accès aux
 * particularités.  Les particularités sont liées aux véhicules.
 * Implémente l'interface ParticulariteDAO.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.7 $, $Date: 2002/04/12 19:42:01 $
 * @see com.lotoquebec.cardex.integration.ParticulariteDAO
 */
public class ParticulariteDAO {

	private final Logger      log =
        (Logger)LoggerCardex.getLogger(ParticulariteDAO.class);

    /**
     * Mise à jour des nouvelles particularités associées à un véhicule, appelée
     * par update afin de faire une action "clear" et "insert".
     * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Procédure appelée : CARDEX_LIEN.SP_E_LPV_LIEN_PARTICULARITE
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param particularites Particularites : Particularités saisies à l'écran.
     * @param action String : "I" ou "C"
     * @param genreFichier String : Code à deux lettres de la table qui lie des
     * particularités à un Vehicule (VE).  Pour l'instant, seuls les véhicules
     * possèdent des particularités.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    private void editParticularites(CardexAuthenticationSubject subject, Particularites particularites, String action, String genreFichier) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        log.fine("Particularités : " +particularites.toString());
        try {
            if ( action.equalsIgnoreCase("C") || action.equalsIgnoreCase("A")) {
                callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPV_LIEN_PARTICULARITE (?,?,?,?,?,?,?); end;");
                callableStatement.setString(1, action);
                callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                OracleDAOUtils.setLong(callableStatement,4, particularites.getLien());
                OracleDAOUtils.setLong(callableStatement,5, 0);
                OracleDAOUtils.setLong(callableStatement,6, particularites.getLienSite());
                callableStatement.setString(7, genreFichier);
                callableStatement.execute();
            }
            else if ( action.equalsIgnoreCase("I") ) {
                Collection particularitesChoisis = particularites.getParticularitesChoisis();
                Iterator it = particularitesChoisis.iterator();
                callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPV_LIEN_PARTICULARITE (?,?,?,?,?,?,?); end;");
                while (it.hasNext()) {
                    String particularitesChoisi = (String)it.next();
                    callableStatement.setString(1, action);
                    callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                    OracleDAOUtils.setLong(callableStatement,4, particularites.getLien());
                    OracleDAOUtils.setLong(callableStatement,5, Long.parseLong((String)particularitesChoisi));
                    OracleDAOUtils.setLong(callableStatement,6, particularites.getLienSite());
                    callableStatement.setString(7, genreFichier);
                    callableStatement.execute();
                }
            }
            else {
                log.fine("Le code d'action '" + action + "', est invalide pour la méthode editParticularites!");
            }
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
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
     * Met à jour les particularités avec audit.
     * Cet audit est inscrit dans la table AUD_LSC_PARTICULARITE.
     * Il sert à retrouver l'historique des particularités lors de l'impression d'un dossier.
     * Date de création : (2012-01-09)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param caracteristiques Caracteristiques : Particularités saisies à
     * l'écran.
     * @param genreFichier String : Code à deux lettres de la table qui lie une
     * caractéristique à un Sujet (SU).  Pour l'instant, seuls les sujets
     * possèdent des caractéristiques.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject,
    		Particularites particularites, String genreFichier)
            throws DAOException {
    	//On supprime toutes les entrées avant d'inscrire les choix. Les valeurs actuelles
    	//sont sauvegardées dans l'audit des changements (action A).
    	editParticularites(subject, particularites, "A", genreFichier);
    	editParticularites(subject, particularites, "I", genreFichier);
    }
   
    /**
     * Lecture des particularités associées à un véhicule.
     * Procédure appelée : SP_L_LPV_LIEN_PARTICULARITE
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence du sujet.
     * @param site long : Site de référence du sujet.
     * @param genreFichier String : ("VE").
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Particularites : Liste des particularités associées.
     */
    public Particularites findLiensParticularite(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException {
      log.fine("findLiensParticularite()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      ParticularitesVO linkedParticularites = null;

      try {
      	//On vérifie d'abord si un historique des données existe en fonction de la date de liaison (utilisé lors de l'impression du dossier).
      	if(vehicule.getLienDateCreation() != null){
	        	callableStatement = connection.prepareCall(
	            	"begin cardex_audit.SP_L_AUD_LPV_PARTICULARITE (?,?,?,?); end;");
	            callableStatement.setLong(1,vehicule.getCle());
	            callableStatement.setLong(2,vehicule.getSite());
	            callableStatement.setTimestamp(3,vehicule.getLienDateCreation());
	            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet)callableStatement.getObject(4);
              while (resultSet.next()){
                  if ( linkedParticularites == null ) {
                	  linkedParticularites = new ParticularitesVO();
                	  linkedParticularites.setSite(resultSet.getLong(
                              "L_SI_CLE"));
                	  linkedParticularites.setLien(resultSet.getLong(
                              "L_VE_CLE"));
                	  linkedParticularites.setLienSite(resultSet.getLong(
                              "L_SI_CLE"));
                  }
                  linkedParticularites.addParticularite( Long.toString(
                          resultSet.getLong("L_PT_CLE")) );
                  log.fine("   [Caracteristiques id='"
                          + resultSet.getLong("L_PT_CLE")
                          + "' Site='" + linkedParticularites.getSite() + "']");
              } // while
      	}
          if (linkedParticularites == null) {
             //Pas de données historiques ou pas de date de liaison. On retourne les données actuelles.
	         callableStatement =
	            connection.prepareCall("begin SP_L_LPV_LIEN_PARTICULARITE (?,?,?,?); end;");
	         callableStatement.setLong(1,vehicule.getCle());
	         callableStatement.setLong(2,vehicule.getSite());
	         callableStatement.setString(3,GlobalConstants.GenreFichier.VEHICULE);
	         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
	         callableStatement.execute();
	         resultSet = (ResultSet)callableStatement.getObject(4);
	         while (resultSet.next()){
	              if ( linkedParticularites == null ) {
	                linkedParticularites = new ParticularitesVO();
	                linkedParticularites.setSite(resultSet.getLong("L_SI_CLE"));
	                linkedParticularites.setLien(resultSet.getLong("L_VE_CLE"));
	                linkedParticularites.setLienSite(resultSet.getLong("L_LPV_REF_SITE"));
	              }
	              linkedParticularites.addParticularite( Long.toString(resultSet.getLong("I_PT_CLE")) );
	              log.fine("   [Particularites id='" + resultSet.getLong("I_PT_CLE")+"' Site='" + linkedParticularites.getSite() + "']");
	         }//while
          }
        if (linkedParticularites == null) {
          return new ParticularitesVO();
        }else {
          return linkedParticularites;
        }
      }catch (SQLException se) {
          throw new DAOException(se);
      } finally {
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