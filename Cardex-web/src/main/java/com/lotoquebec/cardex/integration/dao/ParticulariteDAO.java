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

import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.ParticularitesVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;

/**
 * Liste des appels � la base de donn�es pour diff�rents acc�s aux
 * particularit�s.  Les particularit�s sont li�es aux v�hicules.
 * Impl�mente l'interface ParticulariteDAO.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.7 $, $Date: 2002/04/12 19:42:01 $
 * @see com.lotoquebec.cardex.integration.ParticulariteDAO
 */
public class ParticulariteDAO {

	private final Logger      log =
        LoggerFactory.getLogger(ParticulariteDAO.class);

    /**
     * Mise � jour des nouvelles particularit�s associ�es � un v�hicule, appel�e
     * par update afin de faire une action "clear" et "insert".
     * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LPV_LIEN_PARTICULARITE
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param particularites Particularites : Particularit�s saisies � l'�cran.
     * @param action String : "I" ou "C"
     * @param genreFichier String : Code � deux lettres de la table qui lie des
     * particularit�s � un Vehicule (VE).  Pour l'instant, seuls les v�hicules
     * poss�dent des particularit�s.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    private void editParticularites(CardexAuthenticationSubject subject, Particularites particularites, String action, String genreFichier) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        log.debug("Particularit�s : " +particularites.toString());
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
                log.debug("Le code d'action '" + action + "', est invalide pour la m�thode editParticularites!");
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
     * Met � jour les particularit�s avec audit.
     * Cet audit est inscrit dans la table AUD_LSC_PARTICULARITE.
     * Il sert � retrouver l'historique des particularit�s lors de l'impression d'un dossier.
     * Date de cr�ation : (2012-01-09)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param caracteristiques Caracteristiques : Particularit�s saisies �
     * l'�cran.
     * @param genreFichier String : Code � deux lettres de la table qui lie une
     * caract�ristique � un Sujet (SU).  Pour l'instant, seuls les sujets
     * poss�dent des caract�ristiques.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject,
    		Particularites particularites, String genreFichier)
            throws DAOException {
    	//On supprime toutes les entr�es avant d'inscrire les choix. Les valeurs actuelles
    	//sont sauvegard�es dans l'audit des changements (action A).
    	editParticularites(subject, particularites, "A", genreFichier);
    	editParticularites(subject, particularites, "I", genreFichier);
    }
   
    /**
     * Lecture des particularit�s associ�es � un v�hicule.
     * Proc�dure appel�e : SP_L_LPV_LIEN_PARTICULARITE
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("VE").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Particularites : Liste des particularit�s associ�es.
     */
    public Particularites findLiensParticularite(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException {
      log.debug("findLiensParticularite()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      ParticularitesVO linkedParticularites = null;

      try {
      	//On v�rifie d'abord si un historique des donn�es existe en fonction de la date de liaison (utilis� lors de l'impression du dossier).
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
                  log.debug("   [Caracteristiques id='"
                          + resultSet.getLong("L_PT_CLE")
                          + "' Site='" + linkedParticularites.getSite() + "']");
              } // while
      	}
          if (linkedParticularites == null) {
             //Pas de donn�es historiques ou pas de date de liaison. On retourne les donn�es actuelles.
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
	              log.debug("   [Particularites id='" + resultSet.getLong("I_PT_CLE")+"' Site='" + linkedParticularites.getSite() + "']");
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