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

import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.vo.CaracteristiquesVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;

/**
 * Offre tout les services de r�cup�ration des informations d'une base de donn�e
 * Oracle, relatives aux caract�ristiques de sujets.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $, $Date: 2002/03/11 19:13:14 $
 * @see com.lotoquebec.cardex.integration.CaracteristiqueDAO
 */
public class CaracteristiqueDAO {

	private final Logger      log =
        LoggerFactory.getLogger((CaracteristiqueDAO.class));


    /**
     * Modification des nouvelles caract�ristiques associ�es � un sujet, appel�e
     * par la m�thode "update" afin de faire une action "clear" et "insert".
     * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LSC_CARACTERISTIQUE
     * Date de cr�ation : (2002-03-01)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param caracteristiques Caracteristiques : Caract�ristiques saisies �
     * l'�cran.
     * @param action String : "I" ou "C".
     * @param genreFichier String : Code � deux lettres de la table qui lie des
     * caract�ristiques � un Sujet (SU).  Pour l'instant, seuls les sujets
     * poss�dent des caract�ristiques.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    private void editCaracteristiques(CardexAuthenticationSubject subject,
            Caracteristiques caracteristiques, String action,
            String genreFichier) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            if ( action.equalsIgnoreCase("C") || action.equalsIgnoreCase("A")  ) {
                callableStatement = connection.prepareCall(
                        "begin CARDEX_LIEN.SP_E_LSC_CARACTERISTIQUE "
                        + "(?,?,?,?,?,?,?); end;");
                callableStatement.setString(1, action);
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                OracleDAOUtils.setLong(callableStatement, 4, 0);
                OracleDAOUtils.setLong(callableStatement, 5,
                        caracteristiques.getLien());
                OracleDAOUtils.setLong(callableStatement, 6,
                        caracteristiques.getLienSite());
                callableStatement.setString(7, genreFichier);
                callableStatement.execute();
            }
            else if ( action.equalsIgnoreCase("I") ) {
                Collection caracteristiquesChoisis = caracteristiques.getCaracteristiquesChoisis();
                Iterator it = caracteristiquesChoisis.iterator();
                callableStatement =
                        connection.prepareCall(
                        "begin CARDEX_LIEN.SP_E_LSC_CARACTERISTIQUE "
                        + "(?,?,?,?,?,?,?); end;");
                while (it.hasNext()) {
                    String caracteristiquesChoisi = (String)it.next();
                    callableStatement.setString(1, action);
                    callableStatement.registerOutParameter(2,
                            java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(3,
                            java.sql.Types.DECIMAL);
                    OracleDAOUtils.setLong(callableStatement, 4,
                            Long.parseLong((String)caracteristiquesChoisi));
                    OracleDAOUtils.setLong(callableStatement, 5,
                            caracteristiques.getLien());
                    OracleDAOUtils.setLong(callableStatement, 6,
                            caracteristiques.getLienSite());
                    callableStatement.setString(7, genreFichier);
                    callableStatement.execute();
                }
            }
            else {
                log.debug("Le code d'action '" + action
                        + "', est invalide pour la m�thode "
                        + "editCaracteristiques!");
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
     * Met � jour les caract�ristiques avec audit.
     * Cet audit est inscrit dans la table AUD_LSC_CARACTERISTIQUE.
     * Il sert � retrouver l'historique des caract�ristiques lors de l'impression d'un dossier.
     * Date de cr�ation : (2012-01-05)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param caracteristiques Caracteristiques : Caract�ristiques saisies �
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
            Caracteristiques caracteristiques, String genreFichier)
            throws DAOException {
    	//On supprime toutes les entr�es avant d'inscrire les choix. Les valeurs actuelles
    	//sont sauvegard�es dans l'audit des changements (action A).
        editCaracteristiques(subject, caracteristiques, "A", genreFichier);
        editCaracteristiques(subject, caracteristiques, "I", genreFichier);
    }

    /**
     * Lecture des caract�ristiques associ�es � un sujet.
     * Proc�dure appel�e : SP_L_LSC_CARACTERISTIQUE
     * Date de cr�ation : (2002-03-01)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : Code � deux lettres de la table qui lie une
     * caract�ristique � un Sujet (SU).  Pour l'instant, seuls les sujets
     * poss�dent des caract�ristiques.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Caracteristiques : Liste des caract�ristiques associ�es.
     */
    public Caracteristiques findLiensCaracteristique(
            CardexAuthenticationSubject subject, Sujet sujet) throws DAOException {
        log.debug("findLiensCaracteristiques()");
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        CaracteristiquesVO linkedCaracteristiques = null;

        try {
        	//On v�rifie d'abord si un historique des donn�es existe en fonction de la date de liaison (utilis� lors de l'impression du dossier).
        	if(sujet.getLienDateCreation() != null){
	        	callableStatement = connection.prepareCall(
	            	"begin cardex_audit.SP_L_AUD_LSC_CARACTERISTIQUE (?,?,?,?); end;");
	            callableStatement.setLong(1,sujet.getCle());
	            callableStatement.setLong(2,sujet.getSite());
	            callableStatement.setTimestamp(3,sujet.getLienDateCreation());
	            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet)callableStatement.getObject(4);
                while (resultSet.next()){
                    if ( linkedCaracteristiques == null ) {
                        linkedCaracteristiques = new CaracteristiquesVO();
                        linkedCaracteristiques.setSite(resultSet.getLong(
                                "L_SI_CLE"));
                        linkedCaracteristiques.setLien(resultSet.getLong(
                                "L_SU_CLE"));
                        linkedCaracteristiques.setLienSite(resultSet.getLong(
                                "L_SI_CLE"));
                    }
                    linkedCaracteristiques.addCaracteristique( Long.toString(
                            resultSet.getLong("L_CR_CLE")) );
                    log.debug("   [Caracteristiques id='"
                            + resultSet.getLong("L_CR_CLE")
                            + "' Site='" + linkedCaracteristiques.getSite() + "']");
                } // while
        	}
            if (linkedCaracteristiques == null) {
               //Pas de donn�es historiques ou pas de date de liaison. On retourne les donn�es actuelles.
   	        	 callableStatement = connection.prepareCall(
                    "begin SP_L_LSC_CARACTERISTIQUE (?,?,?,?); end;");
	            callableStatement.setLong(1,sujet.getCle());
	            callableStatement.setLong(2,sujet.getSite());
	            callableStatement.setString(3,GlobalConstants.GenreFichier.SUJET);
	            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet)callableStatement.getObject(4);
	            while (resultSet.next()){
	                if ( linkedCaracteristiques == null ) {
	                    linkedCaracteristiques = new CaracteristiquesVO();
	                    linkedCaracteristiques.setSite(resultSet.getLong(
	                            "L_SI_CLE"));
	                    linkedCaracteristiques.setLien(resultSet.getLong(
	                            "L_LSC_REFERENCE"));
	                    linkedCaracteristiques.setLienSite(resultSet.getLong(
	                            "L_LSC_REF_SITE"));
	                }
	                linkedCaracteristiques.addCaracteristique( Long.toString(
	                        resultSet.getLong("L_CR_CLE")) );
	                log.debug("   [Caracteristiques id='"
	                        + resultSet.getLong("L_CR_CLE")
	                        + "' Site='" + linkedCaracteristiques.getSite() + "']");
	            } // while
            }
            if (linkedCaracteristiques == null) {
              return new CaracteristiquesVO();
            }else {
              return linkedCaracteristiques;
            }
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