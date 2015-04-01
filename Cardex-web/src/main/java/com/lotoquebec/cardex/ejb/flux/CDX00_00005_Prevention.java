package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.integration.dao.DossierDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.InscriptionDAO;
import com.lotoquebec.cardex.integration.dao.NarrationDAO;
import com.lotoquebec.cardex.integration.dao.SujetDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.CourrielAction;

/**
 * Pr�vention
 * @author levassc
 *
 */
public class CDX00_00005_Prevention implements Flux{
	
	private final Logger log = (Logger)LoggerCardex.getLogger(CDX00_00005_Prevention.class);
	private boolean courrielEnvoye = false;	
	private CardexAuthenticationSubject subject = null;
	private DossierDAO dossierDAO = null;
	private InscriptionDAO inscriptionDAO = null;
	private NarrationDAO narrationDAO = null;
	private SujetDAO sujetDAO = null;
	
	private void init(){
		dossierDAO = FabriqueCardexDAO.getInstance().getDossierDAO();
		inscriptionDAO = FabriqueCardexDAO.getInstance().getInscriptionDAO();
		narrationDAO = FabriqueCardexDAO.getInstance().getNarrationDAO();
		sujetDAO = FabriqueCardexDAO.getInstance().getSujetDAO();
	}
	
	public void execute() throws Exception{
		Connection connection = null;
		log.fine("Entr�e flux CDX00_00005");
		
		init();
		
		try{
			subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			
			log.fine("Ouverture de connection");
			connection = DAOConnection.getInstance().getConnection(subject);
			
			traiterDossierExpiration(subject, connection);
			traiterDossierAideInitiale(subject, connection);
			traiterDossierBonifies(subject, connection);
			
			if (courrielEnvoye == false){
		    	log.fine("Fin pr�vention sans courriel");
		    	String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Fin_Prevention_Sans_Courriel);
		 	    CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, "", GlobalConstants.TypesIntervention.Fin_Prevention_Sans_Courriel, "A");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.severe("Erreur dans le diff�r� CDX00_00005 Pr�vention");
        	try {
				connection.rollback();
			    connection.close();
			    connection = null;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	throw new Exception(e);
		} finally {
 		    if (connection != null) {
                try{
			         connection.commit();
			         connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
 		    }
		}
		log.fine("Fin flux CDX00_00005");
	}

	private void traiterDossierAideInitiale(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException {
        String message = "<br>";
        List<DossierVO> listeDossiers = dossierDAO.rechercheDossierAutoexeclusionAideInitiale(connection);
        Iterator<DossierVO> iter = listeDossiers.iterator();
        SimpleDateFormat simpleDateFormat = TimestampFormat.getFormatter(true);
		String dateAujourdhui = simpleDateFormat.format(new Date());
        String narration = "Aide demand�e - Courriel envoy� le "+dateAujourdhui;
        
        while (iter.hasNext()) {
			Connection connectionIter = null; 
			try {
				connectionIter = DAOConnection.getInstance().getConnection(subject);
        	
				DossierVO dossierVO = iter.next();
				SujetVO sujetVO = (SujetVO) dossierVO.getSujets().iterator().next(); // Un seul sujet
				message +=dossierVO.getNumeroCardex()+", "+sujetVO.getNumeroFiche()+"<br>";
				log.fine("Traitement du sujet: "+sujetVO.getNumeroFiche());
				assignerSujetConfidentialiteC(subject, connectionIter, sujetVO);
				log.fine("Traitement du dossier: "+dossierVO.getNumeroDossier());
		     	assignerDossierConfidentialiteC(subject, connectionIter, dossierVO);
		     	ecrireNarration(subject, dossierVO, narration, connectionIter);
		     	log.fine("Enregistrement de la date d'aide initiale");
		     	inscriptionDAO.enregisterDateAideInitiale(connectionIter, dossierVO);
		     	connectionIter.commit();
		     	
			} finally{
				connectionIter.close();
				connectionIter = null;
			}		     	
		}
        
        if (listeDossiers.size() > 0){
	    	log.fine("Recherche de destinataires (Aide)");
	    	String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Aide);
	    	CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.Aide, "A");
	 	    courrielEnvoye = true;
        }
	}

	private void traiterDossierExpiration(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
		String message = "<br>";
        List<DossierVO> listeDossiers = dossierDAO.rechercheDossierAutoexeclusionExpiration(connection);
        Iterator<DossierVO> iter = listeDossiers.iterator();
        SimpleDateFormat simpleDateFormat = TimestampFormat.getFormatter(true);
		String dateAujourdhui = simpleDateFormat.format(new Date());
        String narration = "Suivi demand� - Courriel envoy� le "+dateAujourdhui;
        
        while (iter.hasNext()) {
			Connection connectionIter = null; 
			try {
				connectionIter = DAOConnection.getInstance().getConnection(subject);
				
				DossierVO dossierVO = iter.next();
				SujetVO sujetVO = (SujetVO) dossierVO.getSujets().iterator().next(); // Un seul sujet
				message +=dossierVO.getNumeroCardex()+", "+sujetVO.getNumeroFiche()+"<br>";
				log.fine("Traitement du sujet: "+sujetVO.getNumeroFiche());
				assignerSujetConfidentialiteC(subject, connectionIter, sujetVO);
				log.fine("Traitement du dossier: "+dossierVO.getNumeroDossier());
		     	assignerDossierConfidentialiteC(subject, connectionIter, dossierVO);
		     	ecrireNarration(subject, dossierVO, narration, connectionIter);
		     	log.fine("Enregistrement de la date de suivi");
		     	inscriptionDAO.enregisterDateSuiviInscription(connectionIter, dossierVO);
		     	
		     	connectionIter.commit();
		     	
			} finally{
				connectionIter.close();
				connectionIter = null;
			}  	     	
		}
        
        if (listeDossiers.size() > 0){
	    	log.fine("Recherche de destinataires (Suivi)");
	    	String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Suivi);
	 	    CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.Suivi, "A");
	 	    courrielEnvoye = true;
        }
	}

	private void ecrireNarration(CardexAuthenticationSubject subject, DossierVO dossierVO, String texte, Connection connection) throws DAOException {
		log.fine("Enregistrement la narration");
		Timestamp maintenant = new Timestamp((new Date()).getTime());
		Narration narration = new NarrationVO();
		narration.setLien(dossierVO.getCle());
		narration.setLienSite(dossierVO.getSite());
		narration.setConfidentialiteApprobateur(GlobalConstants.Confidentialite.C);
		narration.setConfidentialiteCreateur(GlobalConstants.Confidentialite.C);
		narration.setConfidentialiteNarration(GlobalConstants.Confidentialite.C);
		narration.setAutoriteApprobateur(GlobalConstants.NiveauHierachique.UN);
		narration.setAutoriteCreateur(GlobalConstants.NiveauHierachique.UN);
		narration.setAutoriteNarration(GlobalConstants.NiveauHierachique.UN);
		narration.setDateApprobation(maintenant);
		narration.setApprobateur("CARDEX");
		narration.setNarrationAvecFormat(texte);
		narration.setNarrationSansFormat(texte);
		narration.setDateCreation(maintenant);
		narrationDAO.insert(narration, GlobalConstants.GenreFichier.DOSSIER, connection);
	}

	//Cette m�thode recherche les contrats bonifi�s afin d'ajuster la confidentialit� � C des dossiers, des sujets et des photos. 
	//La confidentialit� C permet aux intervenants externes de consulter ces informations.
	private void traiterDossierBonifies(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
        List<DossierVO> listeDossiers = dossierDAO.rechercheDossierAutoexeclusionBonifies(connection);
        Iterator<DossierVO> iter = listeDossiers.iterator();
        log.fine("Traitement des contrats bonifi�s");
        
        while (iter.hasNext()) {
			Connection connectionIter = null; 
			try {
				connectionIter = DAOConnection.getInstance().getConnection(subject);        	
				DossierVO dossierVO = iter.next();
				SujetVO sujetVO = (SujetVO) dossierVO.getSujets().iterator().next(); 
				log.fine("Traitement du sujet: "+sujetVO.getNumeroFiche());
				assignerSujetConfidentialiteC(subject, connectionIter, sujetVO);
				log.fine("Traitement du dossier: "+dossierVO.getNumeroDossier());
				assignerDossierConfidentialiteC(subject, connectionIter, dossierVO);
				
		     	connectionIter.commit();
	     	
			} finally{
				connectionIter.close();
				connectionIter = null;
			}  	     	
		}
        
	}
	
	/**
	 * Avant d'assigner la confidentialit� � C il faut v�rifier si le 
	 * dossier n'y est pas d�j�
	 * @param subject
	 * @param dossierVO
	 * @param connection
	 * @throws DAOException 
	 */
	private void assignerDossierConfidentialiteC(CardexAuthenticationSubject subject, Connection connection, DossierVO dossierVO) throws DAOException{
		dossierVO = (DossierVO) dossierDAO.find(subject, dossierVO);
		
		if (GlobalConstants.Confidentialite.C != dossierVO.getConfidentialite())
			dossierDAO.assignerDossierConfidentialiteC(connection, dossierVO);
	}
	
	/**
	 * Avant d'assigner la confidentialit� � C il faut v�rifier si
	 * le sujet n'y est pas d�j�
	 * @param subject
	 * @param sujetVO
	 * @param connection
	 * @throws DAOException
	 */
	private void assignerSujetConfidentialiteC(CardexAuthenticationSubject subject, Connection connection, SujetVO sujetVO) throws DAOException{
		sujetVO = (SujetVO) sujetDAO.find(subject, sujetVO);
		
		if (GlobalConstants.Confidentialite.C != sujetVO.getConfidentialite())
			sujetDAO.assignerSujetConfidentialiteC(connection, sujetVO);
	}
}
