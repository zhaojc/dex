package com.lotoquebec.cardexCommun.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GererTacheUtilisateur {

	private static GererTacheUtilisateur gererTacheUtilisateur = null;
	private static Map<Integer,TacheUtilisateur> tacheUtilisateurMap = new HashMap<Integer,TacheUtilisateur>();
	private static IntegerRotatif integerRotatif = new IntegerRotatif();
	
	private GererTacheUtilisateur(){}
	
	public synchronized static GererTacheUtilisateur getInstanceOf(){
		
		if (gererTacheUtilisateur == null)
			gererTacheUtilisateur = new GererTacheUtilisateur();
		return gererTacheUtilisateur;
	}
	
	public int obtenirNumero(){
		return integerRotatif.genererNumero();
	}
	
	public synchronized void ajouterTacheUtilisateur(Integer sequence, TacheUtilisateur tacheUtilisateur) throws SQLException{
		
		if (sequence == null)
			return;
		verifierThreadCourrant();
		tacheUtilisateurMap.put(sequence, tacheUtilisateur);
	}
	
	public synchronized void retraitTacheUtilisateur(Integer sequence){
		tacheUtilisateurMap.remove(sequence);
	}

	/*
	 * Le cancel est appelé lorsque l'utilisateur quite la page 
	 */
	public synchronized void cancelTacheUtilisateur(Integer sequence) throws SQLException{
		
		if (tacheUtilisateurMap.containsKey(sequence)){
			tacheUtilisateurMap.get(sequence).cancel();
			retraitTacheUtilisateur(sequence);
 		}
	}
	
	public static void verifierThreadCourrant(){
		if (Thread.interrupted())
			throw new RuntimeException("La tâche de l'usager est suspendue");
	}
}
