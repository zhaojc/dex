package com.lotoquebec.cardexCommun.integration.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.util.IntegerRotatif;

public class TempsTraitement {

    private final Logger log = LoggerFactory.getLogger((this.getClass()));
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
		log.debug("D�but de l'ex�cution " + endroit + " id " + identifiant + " : "+dateFormat.format(dateDebut));
		log.debug("SQL de l'ex�cution " + endroit + " id " + identifiant + " : "+sql);
	}
	
	public void fin(){
		Date dateFin = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.debug("Fin de l'ex�cution " + endroit + " id " + identifiant + " : " + dateFormat.format(dateFin) + " Temps d'ex�cution : "+obtenirTempsTraitement(dateDebut, dateFin));
	}
	
	private long obtenirTempsTraitement(Date dateDepart, Date dateFin) {
		return (dateFin.getTime()-dateDepart.getTime()) / 1000;
	}	
	
}
