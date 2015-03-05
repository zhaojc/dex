/*
 * Created on 22-Sep-2008
 * 
 * Responsabilit� de NumeroSequenceDossier
 * 1-	�tre single thread
 * 2-	Permettre d�obtenir un num�ro de s�quence unique incr�mental par site
 * 	a.	Num�ro unique par date et par site
 * 3-	Conserver en m�moire le dernier num�ro de s�quence par site pour la journ�e courante
 * 4-	S�il n�a pas donn� de nouveau num�ro pour cette journ�e, le num�ro s�quentiel  red�marre � 1.
 * 5-	L�ajout du num�ro de s�quence dans la table site doit ce faire dans la m�me transaction que l�insertion du dossier.

 */
package com.lotoquebec.cardex.integration.dao.cache;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.lotoquebec.cardex.business.Site;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.SiteDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.EnregistrementPresent;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.util.DateUtils;
import com.lotoquebec.cardexCommun.util.DistributedMapUtil;

/**
 * @author levassc
 */
public class GenererNumeroSequenceDossier {

	private static GenererNumeroSequenceDossier numeroSequenceDossier = null;
	private SiteDAO siteDAO;
	
	private GenererNumeroSequenceDossier() {
		super();
		siteDAO = FabriqueCardexDAO.getInstance().getSiteDAO();
	}
	
	public synchronized static GenererNumeroSequenceDossier getInstance(){
		
		if (numeroSequenceDossier == null){
			numeroSequenceDossier = new GenererNumeroSequenceDossier();
		}
		return numeroSequenceDossier;
	}

	public String obtenirNumeroSequenceCardex(long cleSite, Connection connection) throws DAOException{
		Map siteSequenceMap = DistributedMapUtil.getInstance().obtenirCache(GlobalConstants.Configuration.SERVICES_CACHE_NUMERO_SEQUENCE_DOSSIER);
		
		synchronized (siteSequenceMap){
			try {
				connection.setAutoCommit(false);
				Site siteSequence = obtenirNumeroSequenceSite(Long.valueOf( cleSite ), connection);
				siteSequence.incrementerSequence();
				
				// V�rification en double parce qu'il existe certains cas rares
				// qui produise des num�ros en double.
				if (isNumeroDejaCreer(siteSequence.constuireNumeroCardex(), connection)){
					System.err.println("Probl�me de num�ro de dossier d�j� utilis� :"+siteSequence.constuireNumeroCardex());
					siteSequence = obtenirNumeroSequenceSite(Long.valueOf( cleSite ), connection);
					siteSequence.incrementerSequence();
					
					while(isNumeroDejaCreer(siteSequence.constuireNumeroCardex(), connection)){
						System.err.println("Probl�me de num�ro de dossier d�j� utilis� (it�ration) :"+siteSequence.constuireNumeroCardex());
						siteSequence = obtenirNumeroSequenceSite(Long.valueOf( cleSite ), connection);
						siteSequence.incrementerSequence();
					}
				}
				// fin de v�rification
				
				siteDAO.enregistrerSequence(siteSequence, connection);
				
				//connection.commit();
				connection.setAutoCommit(true);

				return siteSequence.constuireNumeroCardex();
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new DAOException(e1);
				}
				throw new DAOException(e);
			}		
		}
	}
	
	private boolean isNumeroDejaCreer(String numeroCardex, Connection connection){
		final EnregistrementPresent enregistrementPresent = new EnregistrementPresent();
		
		String SQL = "select * from do_dossier do where do.v_do_numero_dossier = '" + numeroCardex + "'";
		
		JDBCTemplate template;
		try {
			template = new JDBCTemplate(connection);
			template.query(SQL, enregistrementPresent, false);	
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return enregistrementPresent.isTrouve();
	}
	
	private Site obtenirNumeroSequenceSite(long numeroSite, Connection connection) throws DAOException{
		Site site = siteDAO.obtenirSite( numeroSite, connection );
		Timestamp date = new Timestamp((new Date()).getTime()); // maintenant
		
		if (DateUtils.isMemeJour(date, site.getDateSequence()) == false){
			//Nouvelle s�quence site
			site.initSequence(date);
		}
		return site;		
	}

	
}
