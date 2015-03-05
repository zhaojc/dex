package com.lotoquebec.cardexCommun.integration.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.IntegerRotatif;

public class TempsTraitement {

    private final Logger log = (Logger)LoggerCardex.getLogger((this.getClass()));
	private Date dateDebut = null;
	private String identifiant = "";
	private String endroit = "";
	private static IntegerRotatif integerRotatif = new IntegerRotatif(); 
	
	public TempsTraitement(String endroit, String sql) {
		super();
		this.endroit = endroit;
		dateDebut = new Date();
		identifiant = integerRotatif.genereStringNumero();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.fine("Début de l'exécution " + endroit + " id " + identifiant + " : "+dateFormat.format(dateDebut));
		log.fine("SQL de l'exécution " + endroit + " id " + identifiant + " : "+sql);
	}
	
	public void fin(){
		Date dateFin = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.fine("Fin de l'exécution " + endroit + " id " + identifiant + " : " + dateFormat.format(dateFin) + " Temps d'exécution : "+obtenirTempsTraitement(dateDebut, dateFin));
	}
	
	private long obtenirTempsTraitement(Date dateDepart, Date dateFin) {
		return (dateFin.getTime()-dateDepart.getTime()) / 1000;
	}	
	
}
