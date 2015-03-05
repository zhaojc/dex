/*
 * Created on 22-Sep-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.lotoquebec.cardex.business;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author levassc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Site extends Serializable{
	public abstract String getAbreviation();

	public abstract void setAbreviation(String abreviation);

	public abstract long getCle();

	public abstract void setCle(long cle);

	public abstract String getCreateur();

	public abstract void setCreateur(String createur);

	public abstract Timestamp getDateCreation();

	public abstract void setDateCreation(Timestamp dateCreation);

	public abstract Timestamp getDateModification();

	public abstract void setDateModification(Timestamp dateModification);

	public abstract Timestamp getDateSequence();

	public abstract void setDateSequence(Timestamp dateSequence);

	public abstract int getEntite();

	public abstract void setEntite(int entite);

	public abstract String getModifierPar();

	public abstract void setModifierPar(String modifierPar);

	public abstract String getNom();

	public abstract void setNom(String nom);

	public abstract long getNumeroSequence();

	public abstract void setNumeroSequence(long numeroSequence);

	public abstract Boolean isSiteApplicable();

	public abstract void setSiteApplicable(Boolean siteApplicable);

	public abstract Boolean isSiteOrigine();

	public abstract void setSiteOrigine(Boolean siteOrigine);

	public abstract void incrementerSequence();

	public abstract String constuireNumeroCardex();
	
	public abstract void initSequence(Timestamp date);
}