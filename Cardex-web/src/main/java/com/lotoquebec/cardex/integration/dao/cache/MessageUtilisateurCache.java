package com.lotoquebec.cardex.integration.dao.cache;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.vo.MessageUtilisateurVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;

public class MessageUtilisateurCache {

	private static MessageUtilisateurCache messageUtilisateurCache = null;
	private static List listeMessageUtilisateur = new ArrayList();
	private static Date dateDerniereLecture = null;
	
	private MessageUtilisateurCache() {
		super();
	}

	public synchronized static MessageUtilisateurCache getInstance() {
		
		if (messageUtilisateurCache == null)
			messageUtilisateurCache = new MessageUtilisateurCache();
		
		return messageUtilisateurCache;
	}
	
	public static synchronized List obtenirListeMessageUtilisateur(CardexAuthenticationSubject subject){
		
		if (isRelancerLecture()){
			dateDerniereLecture = new Date();
			rafraichirListeMessageUtilisateur(subject);
		}
		return listeMessageUtilisateur;
	}
	
	private static void rafraichirListeMessageUtilisateur(CardexAuthenticationSubject subject) {
		listeMessageUtilisateur.clear();
		
		try {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

			PreparerCallableStatement rch = new PreparerCallableStatement(){

	    		public void preparer(CallableStatement callableStatement) throws SQLException {
	    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
				}
	    	};
	    	
	    	storeProcTemplate.prepareCall("CARDEX_SPECIAL.SP_L_ME_MESSAGE_COURANT", 1, 1, rch);
	    	
	    	RowCallbackHandler rcbh = new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException {
					MessageUtilisateurVO messageUtilisateurVO = new MessageUtilisateurVO();
					messageUtilisateurVO.setCle(rs.getLong(1));
					messageUtilisateurVO.setMessage(rs.getString(2));
					messageUtilisateurVO.setNiveau(rs.getInt(3));
					messageUtilisateurVO.setDateDebut(rs.getDate(4));
					messageUtilisateurVO.setDateFin(rs.getDate(5));
					listeMessageUtilisateur.add( messageUtilisateurVO );
				}
	    	};			    	
	    	
	    	storeProcTemplate.query( rcbh, true );		

		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	// la lecture est relancé à toutes les 5 minutes
	private static boolean isRelancerLecture(){
		
		if (dateDerniereLecture == null)
			return true;
		
		long differenceEnMinute = ((new Date()).getTime() - dateDerniereLecture.getTime())/ (60 * 1000);
		return differenceEnMinute >= 5;
	}
	
}
