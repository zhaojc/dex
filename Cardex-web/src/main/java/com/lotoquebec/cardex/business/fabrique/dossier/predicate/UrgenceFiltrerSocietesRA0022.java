package com.lotoquebec.cardex.business.fabrique.dossier.predicate;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardexCommun.GlobalConstants;

/**
 * RA0022 : Retirer les soci�t�s d'urgence de la liste des r�sultats.  
 * Ces soci�t�s ont une des classes "Ambulance", "Corps policiers"  ou 
 * "Service d'incendie".
 * 
 * De cette fa�on il ne sera pas possible de lier des soci�t�s d�urgence via la 
 * liaison de soci�t�.  Il faut le faire via l�onglet � Services d�urgence �
 * 
 * @author levassc
 *
 */
public class UrgenceFiltrerSocietesRA0022 implements Predicate{

	public boolean evaluate(Object o) {
		
		if (o instanceof Societe == false)
			return false;
		Societe societe = (Societe) o;
		
		if (isSocieteUrgence(societe.getClasse()))
			return false;
		return true;
	}

	public static boolean isSocieteUrgence(long classe){
		return (GlobalConstants.Classes.AMBULANCE == classe
				|| GlobalConstants.Classes.POLICE == classe
				|| GlobalConstants.Classes.POMPIER == classe);
	}
}
