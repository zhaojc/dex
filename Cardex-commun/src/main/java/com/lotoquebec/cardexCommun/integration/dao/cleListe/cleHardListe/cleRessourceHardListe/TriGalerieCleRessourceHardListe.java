/*
 * Created on 28-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe;

import javax.servlet.http.HttpServletRequest;

/**
 * @author levassc
 */
public class TriGalerieCleRessourceHardListe extends CleRessourceHardListe {

	public final static String TRI_DATE_CREATION_PHOTO = "tri.galerie.date.creation.photo";
	public final static String TRI_DATE_DEBUT_DOSSIER = "tri.galerie.date.debut.dossier";
	public final static String TRI_DATE_FIN_DOSSIER = "tri.galerie.date.fin.dossier";
	
	public TriGalerieCleRessourceHardListe(HttpServletRequest request, String langue) {
		super(request, langue);
	}

	protected void genererListe(){
		add(TRI_DATE_CREATION_PHOTO);
		add(TRI_DATE_DEBUT_DOSSIER);
		add(TRI_DATE_FIN_DOSSIER);		
	}
}
