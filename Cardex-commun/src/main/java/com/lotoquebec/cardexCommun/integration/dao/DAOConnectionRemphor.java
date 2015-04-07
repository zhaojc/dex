package com.lotoquebec.cardexCommun.integration.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lq.std.conf.impl.AppConfig;

/**
 * Factory qui produit des instances de DAO telle que:
 * <ul>
 *   <li>IntervenantDAO</li>
 *   <li>GroupeDAO</li>
 *   <li>ItemListDAO</li>
 * <ul>
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.15 $, $Date: 2002/03/27 22:45:35 $
 */
public class DAOConnectionRemphor {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final static Logger      log =
        LoggerFactory.getLogger(DAOConnectionRemphor.class);

    private static DAOConnectionRemphor daoConnectionRemphor;

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
    public static DAOConnectionRemphor getInstance() throws DAOException {
    	if (DAOConnectionRemphor.daoConnectionRemphor == null) {
            DAOConnectionRemphor.daoConnectionRemphor = new DAOConnectionRemphor();
        }
        return DAOConnectionRemphor.daoConnectionRemphor;
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
    	DAOException exception  = null;
    	
        if (log.isDebugEnabled()) {
            log.debug("Cr�ation d'un connection anonyme � la bd ..");
            log.debug("   DATA_SOURCE_JNDI_NAME = '" + AppConfig.INSTANCE.get(GlobalConstants.Configuration.DATASOURCE_REMPHOR) + "'");
        }

        // Trois tentative d'ouvrir la connexion remphor
        for (int i = 0; i < 3; i++) {
        	
            try {
                if (DATA_SOURCE == null){
                  //Create the Initial Naming Context
                	Context ctx = new InitialContext();
                  DATA_SOURCE = (DataSource)ctx.lookup(AppConfig.INSTANCE.get(GlobalConstants.Configuration.DATASOURCE_REMPHOR));
                }
                Connection connection = DATA_SOURCE.getConnection();
                // pour des raisons de performance
                connection.setAutoCommit(false);
                return connection;
              } catch (SQLException se) {
            	  log.error("Probl�me de connexion Remphor");
            	  exception = new DAOException(se);
              } catch (NamingException ne) {
            	  log.error("Probl�me de connexion Remphor");
            	  exception = new DAOException(ne);
              }
		}
        
        if (exception != null)
        	throw exception;
        
        return null;
    }
}