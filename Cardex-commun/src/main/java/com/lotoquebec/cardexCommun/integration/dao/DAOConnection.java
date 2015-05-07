package com.lotoquebec.cardexCommun.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.CodeLangue;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lq.std.conf.impl.AppConfig;

public class DAOConnection {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final static Logger      log =
        LoggerFactory.getLogger(DAOConnection.class);

    /**
     * Property file containing DAOFactory specific properties
     */

    private static DAOConnection daoConnection;
    
    
    private static DataSource DATA_SOURCE = null;    

    /**
     * Cr�e le factory sp�cifi� par le param�tre wichFactory.
     * Le factory supporte deux types de DAO :
     * <ul>
     *   <li>Oracle</li>
     *   <li>Dummy</li>
     * <ul>
     *
     * @param whichFactory Le type de factory a cr�er.
     * @return DAOFactory Instance du factory.
     */
    public static DAOConnection getInstance() throws DAOException {
        
    	if (DAOConnection.daoConnection == null) {
            DAOConnection.daoConnection = new DAOConnection();
        }
        return DAOConnection.daoConnection;

    }

    /**
     * Cr�ation d'un connection � la bd sans variables globales Oracle.
     *
     * @return Connection � la bd
     *
     * @throws DAOException si une exception SQL est recue (si le driver de
     * base de donn�e ne peut �tre trouv�, etc.)
     */
    public Connection getConnection()
            throws DAOException {
        if (log.isDebugEnabled()) {
            log.debug("Création d'un connection anonyme à la bd ..");
            log.debug("   DATA_SOURCE_JNDI_NAME = '" + AppConfig.INSTANCE.get(GlobalConstants.Configuration.DATASOURCE) + "'");
        }

        try {
          if (DATA_SOURCE == null){
        	  
        	  if (StringUtils.isEmpty(AppConfig.INSTANCE.get(GlobalConstants.Configuration.DATASOURCE)))
        		  throw new RuntimeException("Aucune DataSource");
        	  else{
				Context ctx = new InitialContext();
				DATA_SOURCE = (DataSource)ctx.lookup(AppConfig.INSTANCE.get(GlobalConstants.Configuration.DATASOURCE));
        	  }
          }
          return DATA_SOURCE.getConnection();
        } catch (SQLException se) {
            throw new DAOException(se);
        } catch (NamingException ne) {
            throw new DAOException(ne);
        }
    }

    /**
     * Cr�ation d'un connection � la bd avec les variables globales Oracle
     * concernant un sujet  authentifi� sur l'application cardex.
     *
     * @param subject Sujet authentifi� sur l'application cardex
     *
     * @return Connection � la bd
     *
     * @throws DAOException si une exception SQL est re�u lors de
     * l'ex�cution de la store procedure d'initialisation des
     * variables globales Oracle et si le driver de base de donn�e
     * ne peut �tre trouv�.
     */
    public Connection getConnection(CardexAuthenticationSubject subject)
            throws DAOException {

            Connection connection = getConnection();
            initiliazeConnection(subject, connection);
            return connection;
    }

    /**
     * Initialisation des variables globales Oracle.
     *
     * @param subject Sujet authentifi� sur l'application cardex
     * @param connection Connection � la bd
     *
     * @throws DAOException si une exception SQL est recu lors de
     * l'ex�cution de la store procedure d'initialisation des
     * variables globales Oracle
     */
    protected void initiliazeConnection(CardexAuthenticationSubject subject,
            Connection connection) throws DAOException {
        CardexUser      user = (CardexUser) subject.getUser();
        Locale          locale = subject.getLocale();
        CardexPrivilege privilege =
            (CardexPrivilege) subject.getPrivilege();

        if (log.isDebugEnabled()) {
            log.debug("Initialisation des variables globales Oracle ..");
            log.debug("   Langue du sujet = '"
                      + CodeLangue.valueOf(locale).intValue() + "'");
            log.debug("   Code d'utilisateur = '" + user.getCode()
                      + "'");
            log.debug("   Niveau de confidentialit� = '"
                      + privilege.getNiveauConfidentialite() + "'");
            log.debug("   Site = '" + user.getSite() + "'");
        }
        CallableStatement callableStatement = null;

        try {
            callableStatement = connection.prepareCall(
                "begin CARDEX_SPECIAL.SP_INITIALISE (?,?,?,?); end;");

            callableStatement.setLong(1,
                    CodeLangue.valueOf(locale).intValue());// Langue du sujet
            callableStatement.setString(2,
                    user.getCode());                       // Code d'utilisateur
            callableStatement.setLong(3,
                    privilege.getNiveauConfidentialite()); // Niveau de
                                                           // confidentialit�.
            callableStatement.setLong(4, user.getSite());  // Site
            callableStatement.execute();
        } catch (SQLException se) {
            throw new DAOException(se);
        }finally{
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
        }
    }
}