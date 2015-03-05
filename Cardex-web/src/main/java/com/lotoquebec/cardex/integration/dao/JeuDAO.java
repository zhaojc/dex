package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.vo.JeuxVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Liste des appels à la base de données pour différents accès aux dossiers. Les
 * jeux sont liés aux dossiers.
 * Implémente l'interface JeuDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/13 17:49:34 $
 * @see com.lotoquebec.cardex.integration.JeuDAO
 */
public class JeuDAO {

	private final Logger      log =
        (Logger)LoggerCardex.getLogger(JeuDAO.class);

    /**
     * Mise à jour des nouveaux jeux associés à un dossier, appelée
     * par update afin de faire une action "clear" et "insert".
     * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Procédure appelée : CARDEX_LIEN.SP_E_LJD_LIEN_JEU_DOSSIER
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param jeux Jeux : Jeux saisies à l'écran.
     * @param action String : "I" ou "C"
     * @param genreFichier String : Code à deux lettres de la table qui lie des
     * jeux à un Dossier (DO).  Pour l'instant, seuls les dossiers possèdent des
     * jeux.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    private void editJeux(CardexAuthenticationSubject subject, Jeux jeux, String action, String genreFichier) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            if ( action.equalsIgnoreCase("C") ) {
                callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LJD_LIEN_JEU_DOSSIER (?,?,?,?,?,?,?); end;");
                callableStatement.setString(1, action);
                callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                OracleDAOUtils.setLong(callableStatement,4, jeux.getLien());
                OracleDAOUtils.setLong(callableStatement,5, 0);
                OracleDAOUtils.setLong(callableStatement,6, jeux.getLienSite());
                callableStatement.setString(7, genreFichier);
                callableStatement.execute();
            }
            else if ( action.equalsIgnoreCase("I") ) {
                Collection jeuxChoisis = jeux.getJeuxChoisis();
                Iterator it = jeuxChoisis.iterator();
                while (it.hasNext()){
                    String jeu = (String)it.next();
                    callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LJD_LIEN_JEU_DOSSIER (?,?,?,?,?,?,?); end;");
                    callableStatement.setString(1, action);
                    callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                    OracleDAOUtils.setLong(callableStatement,4, jeux.getLien());
                    OracleDAOUtils.setLong(callableStatement,5, Long.parseLong(jeu));
                    OracleDAOUtils.setLong(callableStatement,6, jeux.getLienSite());
                    callableStatement.setString(7, genreFichier);
                    callableStatement.execute();
                }
            }
            else {
                log.fine("Le code d'action '" + action + "', est invalide pour la méthode editJeux!");
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
     * Met à jour les jeux.
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param jeux Jeux : Jeux saisis à l'écran.
     * @param genreFichier String : Code à deux lettres de la table qui lie un
     * jeu à un Dossier (DO).  Pour l'instant, seuls les sujets possèdent des
     * jeux.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject, Jeux jeux, String genreFichier) throws DAOException {
        editJeux(subject, jeux, "C", genreFichier);
        editJeux(subject, jeux, "I", genreFichier);
    }

    /**
     * Lecture des jeux associés à un dossier.
     * Procédure appelée : SP_L_LJD_LIEN_JEU_DOSSIER
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence du sujet.
     * @param site long : Site de référence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Jeux : Liste des jeux associés.
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject, long cle, long site, String genreFichier) throws DAOException {
      log.fine("findLiensJeux()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_LJD_LIEN_JEU_DOSSIER (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         JeuxVO linkedJeux = null;
         while (resultSet.next()){
              if ( linkedJeux == null ) {
                linkedJeux = new JeuxVO();
                linkedJeux.setSite(resultSet.getLong("L_SI_CLE"));
                linkedJeux.setLien(resultSet.getLong("L_DO_CLE"));
                linkedJeux.setLienSite(resultSet.getLong("L_LJD_REF_SITE"));
              }
              linkedJeux.addJeu( Long.toString(resultSet.getLong("I_JE_CLE")) );
              log.fine("   [Jeu cle='" + resultSet.getLong("I_JE_CLE")+"', site='" + linkedJeux.getSite() + "']");
         }//while
         if (linkedJeux == null){
          return new JeuxVO();
         }else{
           return linkedJeux;
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
    /**
     * Lecture des jeux associés à une évaluation.
     * Procédure appelée : SPW_L_LJV_JEU_EVAL
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence du sujet.
     * @param site long : Site de référence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Jeux : Liste des jeux associés.
     */
    public Jeux findLiensJeuxMiseEvaluation(CardexAuthenticationSubject subject, long cle, long site) throws DAOException {
      log.fine("findLiensJeuxEvaluation()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_LJV_JEU_EVAL (?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         JeuxVO linkedJeux = null;
         while (resultSet.next()){
              if ( linkedJeux == null ) {
                linkedJeux = new JeuxVO();
                linkedJeux.setSite(resultSet.getLong("L_SI_CLE"));
                linkedJeux.setLien(resultSet.getLong("L_LME_CLE"));
                linkedJeux.setLienSite(resultSet.getLong("L_LME_REF_SITE"));
              }
              linkedJeux.addJeu( Long.toString(resultSet.getLong("L_JE_CLE")) );
              log.fine("   [Jeu cle='" + resultSet.getLong("L_JE_CLE")+"', site='" + linkedJeux.getSite() + "']");
         }//while
         if (linkedJeux == null){
          return new JeuxVO();
         }else{
           return linkedJeux;
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