package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.vo.JeuxVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;

/**
 * Liste des appels � la base de donn�es pour diff�rents acc�s aux dossiers. Les
 * jeux sont li�s aux dossiers.
 * Impl�mente l'interface JeuDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/13 17:49:34 $
 * @see com.lotoquebec.cardex.integration.JeuDAO
 */
public class JeuDAO {

	private final Logger      log =
        LoggerFactory.getLogger(JeuDAO.class);

    /**
     * Mise � jour des nouveaux jeux associ�s � un dossier, appel�e
     * par update afin de faire une action "clear" et "insert".
     * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LJD_LIEN_JEU_DOSSIER
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param jeux Jeux : Jeux saisies � l'�cran.
     * @param action String : "I" ou "C"
     * @param genreFichier String : Code � deux lettres de la table qui lie des
     * jeux � un Dossier (DO).  Pour l'instant, seuls les dossiers poss�dent des
     * jeux.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
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
                log.debug("Le code d'action '" + action + "', est invalide pour la m�thode editJeux!");
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
     * Met � jour les jeux.
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param jeux Jeux : Jeux saisis � l'�cran.
     * @param genreFichier String : Code � deux lettres de la table qui lie un
     * jeu � un Dossier (DO).  Pour l'instant, seuls les sujets poss�dent des
     * jeux.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject, Jeux jeux, String genreFichier) throws DAOException {
        editJeux(subject, jeux, "C", genreFichier);
        editJeux(subject, jeux, "I", genreFichier);
    }

    /**
     * Lecture des jeux associ�s � un dossier.
     * Proc�dure appel�e : SP_L_LJD_LIEN_JEU_DOSSIER
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Jeux : Liste des jeux associ�s.
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject, long cle, long site, String genreFichier) throws DAOException {
      log.debug("findLiensJeux()");
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
              log.debug("   [Jeu cle='" + resultSet.getLong("I_JE_CLE")+"', site='" + linkedJeux.getSite() + "']");
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
     * Lecture des jeux associ�s � une �valuation.
     * Proc�dure appel�e : SPW_L_LJV_JEU_EVAL
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Jeux : Liste des jeux associ�s.
     */
    public Jeux findLiensJeuxMiseEvaluation(CardexAuthenticationSubject subject, long cle, long site) throws DAOException {
      log.debug("findLiensJeuxEvaluation()");
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
              log.debug("   [Jeu cle='" + resultSet.getLong("L_JE_CLE")+"', site='" + linkedJeux.getSite() + "']");
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