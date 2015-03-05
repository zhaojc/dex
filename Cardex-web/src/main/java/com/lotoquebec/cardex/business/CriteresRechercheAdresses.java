/*
 * Created on 18-Apr-2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.lotoquebec.cardex.business;

import java.util.Date;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;


/**
 * @author levassc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface CriteresRechercheAdresses extends CriteresRecherche{
	public abstract String getAdressePostal();

	public abstract void setAdressePostal(String adressePostal);

	public abstract String getCodePostal();

	public abstract void setCodePostal(String codePostal);

	public abstract long getEntite();

	public abstract void setEntite(long entite);

	public abstract String getNomRue();

	public abstract void setNomRue(String nomRue);

	public abstract String getNumeroMunicipal();

	public abstract void setNumeroMunicipal(String numeroMunicipal);

	public abstract String getNumeroUnite();

	public abstract void setNumeroUnite(String numeroUnite);

	public abstract long getPays();

	public abstract void setPays(long pays);

	public abstract long getPointCardinal();

	public abstract void setPointCardinal(long pointCardinal);

	public abstract long getProvince();

	public abstract void setProvince(long province);

	public abstract long getSiteOrigine();

	public abstract void setSiteOrigine(long siteOrigine);

	public abstract String getTelephone();

	public abstract void setTelephone(String telephone);

	public abstract long getTypeRue();

	public abstract void setTypeRue(long typeRue);

	public abstract long getUnite();

	public abstract void setUnite(long unite);

	public abstract long getVille();

	public abstract void setVille(long ville);
	
	public String getAdresseElectronique();
	
	public void setAdresseElectronique(String adresseElectronique);
	
	public Date getDateCreationDu();
	
	public void setDateCreationDu(Date dateCreationDu);
	
	public Date getDateCreationAu();
	
	public void setDateCreationAu(Date dateCreationAu);	
	
}