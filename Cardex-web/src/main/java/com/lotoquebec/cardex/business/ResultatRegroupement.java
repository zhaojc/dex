/*
 * Created on 24-Jan-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.lotoquebec.cardex.business;

/**
 * @author levassc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ResultatRegroupement extends Cloneable{
	public abstract String getCategorie();

	public abstract void setCategorie(String categorie);

	public abstract String getNomIntervenant();
	
	public abstract String getPrenomIntervenant();

	public abstract void setNomIntervenant(String nomIntervenant);
	
	public abstract void setPrenomIntervenant(String prenomIntervenant);

	public abstract long getNombreMinutes();

	public abstract void setNombreMinutes(long nombreMinutes);

	public abstract String getNomRegroupement();

	public abstract void setNomRegroupement(String nomRegroupement);

	public abstract double getQuota();

	public abstract void setQuota(double quota);

	public abstract int getQuotaMin();

	public abstract void setQuotaMin(int quotaMin);

	public abstract String getType();

	public abstract void setType(String type);
	
	public abstract String getEndroit();

	public abstract void setEndroit(String endroit);
	
	public abstract String getMatricule();
	
	public abstract void setMatricule(String matricule);
	
	public abstract long getCle();
	
	public abstract void setCle(long cle);
	
	public abstract String getGroupe();
	
	public abstract void setGroupe(String groupe);
	
	public abstract long getCleIntervenant();

	public abstract void setCleIntervenant(long cleIntervenant);
	
	public abstract String getMoisLettres();
	
	public abstract void setMoisLettres(String moisLettres);

	public abstract String getMoisNombre();
	
	public abstract void setMoisNombre(String moisNombre);

	public abstract Object clone() throws CloneNotSupportedException;
}