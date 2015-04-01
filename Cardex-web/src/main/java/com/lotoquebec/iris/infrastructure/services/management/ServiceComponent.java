package com.lotoquebec.iris.infrastructure.services.management;


import com.lotoquebec.iris.infrastructure.services.configuration.ConfigProperties;

/**
 * <p>Cette classe sert de base aux services de l'infrastructure.</p>
 *
 * @author Humphrey Sheil
 * @author Christian Corneau
 */
public abstract class ServiceComponent
{
	/** Indique si le service charge une configuration d�pendante du vendeur */
	private boolean isVendorAware = false;
	/** Nom du vendeur */
	//private String vendor = null;
	/** Indique sur le service (composant) est acvtiv� */
	protected boolean isEnabled;
	/** Contient les propri�t�s de configuration du service */
	protected ConfigProperties cfg = null;

	/**
	 * <p>Constructeur par d�faut.</p>
	 */
	protected ServiceComponent()
	{
	}

	/**
	 * <p>Constructeur avec des propri�t�s de configuration en argument.</p>
	 * 
	 * @param cfg les propri�t�s de configuration
	 * 
	 * @throws Exception toute exception survenant lors de la cr�ation du service
	 */
	protected ServiceComponent(ConfigProperties cfg) throws Exception
	{
		super();
		this.cfg = cfg;
	}

	/**
	 * <p>Initialise l'�tat du service.</p>
	 * 
	 * @param isEnabled <code>true</code> si le service est activ�
	 *
	 */
	public void setEnabledStatus(boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}

	/**
	 * <p>Retourne l'�tat du service.</p>
	 * 
	 * @return <code>true</code> si le service est activ�
	 */
	public boolean isEnabled()
	{
		return this.isEnabled;
	}


	/**
	 * <p>M�thode pouvant �tre sous-class�e pour retourner une information sp�cifique sur le statut.</p>
	 * 
	 * @return le statut du service
	 */
	public String getStatus()
	{
		return "";
	}

	/**
	 * <p>Retourne la valeur de la propri�t� de configuration demand�e.</p>
	 *
	 * @param propertyName le nom de la propri�t� de configuration
	 *   
	 * @return la valeur de la propri�t� de configuration
	 */
	protected String getProperty(String propertyName)
	{
		return cfg.getProperty(propertyName);
	}

	/**
	 * <p>Indique si le service charge une configuration d�pendante du vendeur</p>
	 * 
	 * @return <code>true</code> si le service charge une configuration d�pendante du vendeur
	 */
	public boolean isVendorAware()
	{
		return this.isVendorAware;
	}

	/**
	 * <p>Indique si le service charge une configuration d�pendante du vendeur</p>
	 * 
	 * @param inVendorAwareFlagValue <code>true</code> si le service charge une configuration d�pendante du vendeur
	 */
	public void setVendorAware(boolean inVendorAwareFlagValue)
	{
		this.isVendorAware = inVendorAwareFlagValue;
	}
}