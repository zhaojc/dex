/*
 * Created on 22-Feb-2008
 */
package com.lotoquebec.cardex.ejb.flux.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.ejb.flux.vo.SujetMouvementVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * @author levassc
 */
public class SocieteInterne {


	private static SocieteInterne societeInterne = null;
	private static Map mapSociete = new HashMap();
	private static CardexAuthenticationSubject subject = null;

	// il devrait y avoir une table pour �a.
	private static final long cleSocieteBINGO = 2405409;
	private static final long cleSocieteCC = 2405411;
	private static final long cleSocieteCASINO = 2405412;
	private static final long cleSocieteHLL = 2405413;
	private static final long cleSocieteING = 2208774;
	private static final long cleSocieteRCC = 2405415;
	private static final long cleSocieteRLL = 2405416;
	private static final long cleSocieteRCM = 2405417;
	private static final long cleSocieteSEJ = 2405420;
	private static final long cleSocieteSSJQ = 2405421;
	private static final long cleSocieteCLLAD = 2405425;
	private static final long cleSocieteCLLEH = 2405426;
	private static final long cleSocieteCLLIL = 2405429;
	private static final long cleSocieteCLLMP = 2405430;
	private static final long cleSocieteCLLQT = 2405431;
	private static final long cleSocieteCLLUZ = 2405432;
	private static final long cleSocieteCMAD = 2405434;
	private static final long cleSocieteCMEH = 2405435;
	private static final long cleSocieteCMIL = 2405436;
	private static final long cleSocieteCMMP = 2405437;
	private static final long cleSocieteCMQT = 2405439;
	private static final long cleSocieteCMUZ = 2405440;
	private static final long cleSocieteLQAD = 2405441;
	private static final long cleSocieteLQEH = 2405442;
	private static final long cleSocieteLQIL = 2405443;
	private static final long cleSocieteLQMP = 2405444;
	private static final long cleSocieteLQQT = 2405445;
	private static final long cleSocieteLQUZ = 2405446;
	private static final long cleSocieteCMTAD = 2596626;
	private static final long cleSocieteCMTIL = 2596641;
	private static final long cleSocieteCMTEH = 2596651;
	private static final long cleSocieteCMTUZ = 2596657;
	private static final long cleSocieteCMTQT = 2596659;
	private static final long cleSocieteCMTMP = 2596660;
	private static final long cleSocieteSJVQ = 3648330;
	private static final long cleSocieteNTER = 879195;
	

	private Predicate predicateAD = new Predicate(){
		public boolean evaluate(Object arg0) {
			final char[] AD = {'A', 'B', 'C', 'D'}; 
			if (arg0 instanceof Sujet == false)
				return false;
			
			Sujet sujet = (Sujet) arg0;
			
			return startWith(sujet.getNom(), AD);
		}
	};
	private Predicate predicateEH = new Predicate(){
		public boolean evaluate(Object arg0) {
			final char[] EH = {'E', 'F', 'G', 'H'}; 
			if (arg0 instanceof Sujet == false)
				return false;
			
			Sujet sujet = (Sujet) arg0;
			
			return startWith(sujet.getNom(), EH);
		}
	};
	private Predicate predicateIL = new Predicate(){
		public boolean evaluate(Object arg0) {
			final char[] IL = {'I', 'J', 'K', 'L'}; 
			if (arg0 instanceof Sujet == false)
				return false;
			
			Sujet sujet = (Sujet) arg0;
			
			return startWith(sujet.getNom(), IL);
		}
	};
	private Predicate predicateMP = new Predicate(){
		public boolean evaluate(Object arg0) {
			final char[] MP = {'M', 'N', 'O', 'P'}; 
			if (arg0 instanceof Sujet == false)
				return false;
			
			Sujet sujet = (Sujet) arg0;
			
			return startWith(sujet.getNom(), MP);
		}
	};
	private Predicate predicateQT = new Predicate(){
		public boolean evaluate(Object arg0) {
			final char[] QT = {'Q', 'R', 'S', 'T'}; 
			if (arg0 instanceof Sujet == false)
				return false;
			
			Sujet sujet = (Sujet) arg0;
			
			return startWith(sujet.getNom(), QT);
		}
	};
	private Predicate predicateUZ = new Predicate(){
		public boolean evaluate(Object arg0) {
			final char[] UZ = {'U', 'V', 'W', 'X', 'Y', 'Z'}; 
			if (arg0 instanceof Sujet == false)
				return false;
			
			Sujet sujet = (Sujet) arg0;
			
			return startWith(sujet.getNom(), UZ);
		}
	};    
	
	
	private SocieteInterne(){}
	
	public static SocieteInterne getInstance(CardexAuthenticationSubject subject){
		
		 if (societeInterne == null){
		 	societeInterne = new SocieteInterne();
		 	SocieteInterne.subject = subject;
		 }
		 return societeInterne;
	}
	
    private boolean startWith(String string, char[] start){
    	
    	for (int i = 0; i < start.length; i++) {
			char c = start[i];
			
			if (Character.toUpperCase(string.charAt(0)) == Character.toUpperCase(c))
				return true;
		}
    	return false;
    }	
    
	/*
     * A	LQ
     * B	Soci�t� �tablissement de jeux
     * C	CASINO SIEGE SOCIAL
     * D	CASINO MONTREAL
     * E	RESTO MONTREAL
     * F	CASINO CHARLEVOIX
     * G	RESTO CHARLEVOIX
     * H	CASINO DU LAC LEAMY
     * I	RESTO LAC LEAMY
     * J	BINGO
     * K	INGENIO
     * L	HOTEL HILTON LEAMY
     * M	SSJQ
     * N	MONT-TREMBLANT
     * O	ESPACE JEUX
     * P	NTER
     */
	private void populerMapSociete() throws DAOException {
		
		if (mapSociete.size() == 0){
			mapSociete = new HashMap();
			
			mapSociete.put("A", creerSocietePredicateLQ()); // LQ
			mapSociete.put("B", new SocietePredicate(obtenirSociete(cleSocieteSEJ, 30))); // Soci�t� �tablissement de jeux
			mapSociete.put("C", new SocietePredicate(obtenirSociete(cleSocieteCASINO, 30))); // CASINO SIEGE SOCIAL
			mapSociete.put("D", creerSocietePredicateCasinoMontreal()); // CASINO MONTREAL
			mapSociete.put("E", new SocietePredicate(obtenirSociete(cleSocieteRCM, 30))); // RESTO MONTREAL
			mapSociete.put("F", new SocietePredicate(obtenirSociete(cleSocieteCC, 30))); // CASINO CHARLEVOIX
			mapSociete.put("G", new SocietePredicate(obtenirSociete(cleSocieteRCC, 30))); // RESTO CHARLEVOIX
			mapSociete.put("H", creerSocietePredicateCasinoLacLeamy()); // CASINO DU LAC LEAMY
			mapSociete.put("I", new SocietePredicate(obtenirSociete(cleSocieteRLL, 30))); // RESTO LAC LEAMY
			mapSociete.put("J", new SocietePredicate(obtenirSociete(cleSocieteBINGO, 30))); // BINGO
			mapSociete.put("K", new SocietePredicate(obtenirSociete(cleSocieteING, 30))); // INGENIO
			mapSociete.put("L", new SocietePredicate(obtenirSociete(cleSocieteHLL, 30))); // HOTEL HILTON LEAMY
			mapSociete.put("M", new SocietePredicate(obtenirSociete(cleSocieteSSJQ, 30))); // SSJQ
			mapSociete.put("N", creerSocietePredicateCasinoMontTremblant()); // CASINO MONT-TREMBLANT
			mapSociete.put("O", new SocietePredicate(obtenirSociete(cleSocieteSJVQ, 30))); // SJVQ
			mapSociete.put("P", new SocietePredicate(obtenirSociete(cleSocieteNTER, 30))); // NTER
		}
	}
	

    private SocietePredicate creerSocietePredicateLQ() throws DAOException {
    	SocietePredicate societePredicate = new SocietePredicate();
    	
    	societePredicate.add(predicateAD, obtenirSociete(cleSocieteLQAD, 30));
    	societePredicate.add(predicateEH, obtenirSociete(cleSocieteLQEH, 30));
    	societePredicate.add(predicateIL, obtenirSociete(cleSocieteLQIL, 30));
    	societePredicate.add(predicateMP, obtenirSociete(cleSocieteLQMP, 30));
    	societePredicate.add(predicateQT, obtenirSociete(cleSocieteLQQT, 30));
    	societePredicate.add(predicateUZ, obtenirSociete(cleSocieteLQUZ, 30));
    	
    	return societePredicate;
    }
    
    private SocietePredicate creerSocietePredicateCasinoMontreal() throws DAOException {
    	SocietePredicate societePredicate = new SocietePredicate();
    	
    	societePredicate.add(predicateAD, obtenirSociete(cleSocieteCMAD, 30));
    	societePredicate.add(predicateEH, obtenirSociete(cleSocieteCMEH, 30));
    	societePredicate.add(predicateIL, obtenirSociete(cleSocieteCMIL, 30));
    	societePredicate.add(predicateMP, obtenirSociete(cleSocieteCMMP, 30));
    	societePredicate.add(predicateQT, obtenirSociete(cleSocieteCMQT, 30));
    	societePredicate.add(predicateUZ, obtenirSociete(cleSocieteCMUZ, 30));
    	
    	return societePredicate;
    }
    
    private SocietePredicate creerSocietePredicateCasinoLacLeamy() throws DAOException {
    	SocietePredicate societePredicate = new SocietePredicate();
    	
    	societePredicate.add(predicateAD, obtenirSociete(cleSocieteCLLAD, 30));
    	societePredicate.add(predicateEH, obtenirSociete(cleSocieteCLLEH, 30));
    	societePredicate.add(predicateIL, obtenirSociete(cleSocieteCLLIL, 30));
    	societePredicate.add(predicateMP, obtenirSociete(cleSocieteCLLMP, 30));
    	societePredicate.add(predicateQT, obtenirSociete(cleSocieteCLLQT, 30));
    	societePredicate.add(predicateUZ, obtenirSociete(cleSocieteCLLUZ, 30));
    	
    	return societePredicate;
    }    	

    private SocietePredicate creerSocietePredicateCasinoMontTremblant() throws DAOException {
    	SocietePredicate societePredicate = new SocietePredicate();

    	societePredicate.add(predicateAD, obtenirSociete(cleSocieteCMTAD, 30));
    	societePredicate.add(predicateIL, obtenirSociete(cleSocieteCMTIL, 30));
    	societePredicate.add(predicateEH, obtenirSociete(cleSocieteCMTEH, 30));
    	societePredicate.add(predicateUZ, obtenirSociete(cleSocieteCMTUZ, 30));
    	societePredicate.add(predicateQT, obtenirSociete(cleSocieteCMTQT, 30));
    	societePredicate.add(predicateMP, obtenirSociete(cleSocieteCMTMP, 30));
	
	return societePredicate;
}    	
    
	private Societe createSociete(long cle, long site){
		Societe societe = new SocieteVO();
		societe.setCle( cle );
		societe.setSite( site );
		return societe;
	}
	
	private Societe obtenirSociete(long cle, long site) throws DAOException {
		Societe cSociete = createSociete(cle, site);
		return FabriqueCardexDAO.getInstance().getSocieteDAO().find(subject, cSociete);
	}
	
	public void lierSociete(SujetMouvementVO sujetMouvementVO) throws DAOException{
		populerMapSociete();
		Sujet sujet = sujetMouvementVO.getSujetConcerne();
		Societe societe = obtenirSociete(mapSociete, sujetMouvementVO);
		
		if (societe != null && sujet.isPossedeCle()){
			
			if (isDejaDansSociete(sujet, societe) == false){
				societe.setRole(GlobalConstants.Role.ENQUETE);
				FabriqueCardexDAO.getInstance().getSujetDAO().addLienSociete(subject, sujet, societe);
			}
		}
	}

	private boolean isDejaDansSociete(Sujet sujet, Societe societe) throws DAOException {
		Collection listeSociete = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSociete(subject, sujet);
		Iterator iter = listeSociete.iterator();
		
		while (iter.hasNext()) {
			Societe societeIter = (Societe) iter.next();
			
			if(societe.getCle() == societeIter.getCle()
			&& societe.getSite() == societeIter.getSite())
				return true;
		}
		
		return false;
	}
	
	private Societe obtenirSociete(Map mapSociete, SujetMouvementVO sujetMouvementVO) {
		SocietePredicate societePredicate = (SocietePredicate) mapSociete.get( sujetMouvementVO.getMouvementVO().getCodeSite() );
		
		if (societePredicate == null){
			System.err.println("Probl�me avec le site " + sujetMouvementVO.getMouvementVO().getCodeSite());
			return null;
		}
		return societePredicate.obtenirSocieteDe( sujetMouvementVO.getSujetConcerne() );
	}    
		
}
