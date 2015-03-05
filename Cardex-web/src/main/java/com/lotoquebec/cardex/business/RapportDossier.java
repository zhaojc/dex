/*
 * Created on 10-Apr-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.lotoquebec.cardex.business;

import java.util.List;
import java.util.Map;

/**
 * Structure pour recevoir et traiter les donn�es pour la production du rapport CDX_0143 (Rapport cumulatif des dossiers)
 * Ce rapport sert au d�part � Jeu en ligne, mais il est con�u pour pouvoir �ventuellement servir � des fins statistiques g�n�rales
 * pour les autres sites aussi.
 * 
 * @author guerinf
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface RapportDossier {

	public abstract String getType();

	public abstract void setType(String type);

	public abstract String getCategorie();

	public abstract void setCategorie(String categorie);

	public abstract long getCleCategorie();

	public abstract void setCleCategorie(
			long cleCategorie);
	
	public abstract List getListDossier();

	public abstract void setListDossier(List listDossier);

	public abstract long getNombreDossier();

	public abstract void setNombreDossier(
			long nombreDossier);
	
	public abstract long getNombreFonde();

	public abstract void setNombreFonde(
			long nombreFonde);

	public abstract long getNombreNonFonde();

	public abstract void setNombreNonFonde(
			long nombreNonFonde);

	public abstract long getNombreIndetermine();

	public abstract void setNombreIndetermine(
			long nombreIndetermine);

	public abstract long getNombreAuxEnquetes();

	public abstract void setNombreAuxEnquetes(
			long nombreAuxEnquetes);

	public abstract long getNombreDossierCumul();

	public abstract void setNombreDossierCumul(
			long nombreDossierCumul);
}