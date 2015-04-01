package com.lotoquebec.iris.infrastructure.services.configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>Classe encapsulant les valeurs du fichier standalone.properties.</p>
 * <p>Le fichier standalone.properties est utilis� pour ex�cuter des tests unitaires dans
 * un environnement sans serveur.</p>
 * <p>Le fichier permet donc de sp�cifier certaines valeurs (ex: URL des bases de donn�es) n�cessaires
 * � l'ex�cution.</p>
 * 
 * @author Daniel Marchese
 */
public class IrisStandaloneProperties {
	
	/** Contient les propri�t�s d�finies dans le fichier standalone.properties */
	private Properties standaloneProperties = new Properties();	

	/**
	 * <p>Constructeur par d�faut. Charge les propri�t�s du fichier.</p>
	 */
	public IrisStandaloneProperties()
	{
		super();
		loadStandaloneProperties();
	}
	
	/**
	 * <p>Charge, s'il existe, le fichier standalone.properties.</p>
	 * <p>Si le fichier n'existe pas, on assume qu'on est dans un environnement avec un serveur d'applications.</p> 
	 */
	private void loadStandaloneProperties()
	{
		try
		{
			standaloneProperties.load(IrisConfig.getInstance().getClasspathBaseFile("standalone.properties"));
		}
		catch (IOException ex)
		{
			// Le fichier n'existe pas. On en d�duit qu'on n'est pas en mode standalone
			standaloneProperties.put("inApplicationServer", "true");
		}
	}

	/**
	 * <p>Retourne les propri�t�s d�finies dans le fichier.</p>
	 * 
	 * @return les propri�t�s du fichier standalone.properties
	 */
	public Properties getStandaloneProperties() {
		return standaloneProperties;
	}

	/**
	 * <p>Initialise les propri�t�s d�finies dans le fichier.</p>
	 * 
	 * @param standaloneProperties les propri�t�s � d�finir
	 */
	public void setStandaloneProperties(Properties standaloneProperties) {
		this.standaloneProperties = standaloneProperties;
	}


	/**
	 * <p>Retourne la valeur de la propri�t� <code>inApplicationServer</code> du fichier standalone.properties.</p>
	 * 
	 * @return <code>true</code> si on est dans un serveur et <code>false</code> si on est en standalone
	 */
	public boolean inApplicationServer()
	{
		return Boolean.valueOf(this.getStandaloneProperties().getProperty("inApplicationServer","false"));
	}
	
	/**
	 * <p>Retourne la valeur de la propri�t� <code>databaseDriverClass</code> du fichier standalone.properties.</p>
	 * 
	 * @return String le nom de la classe Java du driver de la base de donn�es
	 */
	public String getDatabaseDriverClass()
	{
		return this.getStandaloneProperties().getProperty("databaseDriverClass","oracle.jdbc.driver.OracleDriver");
	}

	/**
	 * <p>Retourne la valeur de la propri�t� <code>databaseDriverUrl</code> du fichier standalone.properties.</p>
	 * 
	 * @return String l'url pour le sch�ma de l'infrastructure (INF)
	 */
	public String getDatabaseDriverUrl()
	{
		return this.getStandaloneProperties().getProperty("databaseDriverUrl","");
	}


	/**
	 * <p>Retourne la valeur de la propri�t� <code>userSchema</code> du fichier standalone.properties.</p>
	 * <p>Permet de substituer le sch�ma d�fini dans les mappings Hibernate avec celui sp�cifi�.</p>
	 * 
	 * @return String le nom du sch�ma
	 */
	public String getUserSchema()
	{
		return this.getStandaloneProperties().getProperty("userSchema","");
	}

	/**
	 * <p>Retourne la valeur de la propri�t� <code>initiateurRapports</code> du fichier standalone.properties.</p>
	 * <p>Permet de d�finir l'initiateur pour les rapports lorsque ceux-ci sont ex�cut�s dans un environnement sans serveur.</p>
	 * 
	 * @return String le nom de l'initiateur pour les rapports
	 */
	public String getInitiateurRapports()
	{
		return this.getStandaloneProperties().getProperty("initiateurRapports","");
	}
	
	/**
	 * <p>Retourne la valeur de la propri�t� <code>databaseDriverUrlDdi</code> du fichier standalone.properties.</p>
	 * 
	 * @return String l'url pour le sch�ma DDI
	 */
	public String getDatabaseDriverUrlDdi()
	{
		return this.getStandaloneProperties().getProperty("databaseDriverUrlDdi","");
	}

	/**
	 * <p>Retourne la valeur de la propri�t� <code>databaseDriverUrlAdmin</code> du fichier standalone.properties.</p>
	 * 
	 * @return String l'url pour le sch�ma administratif (applicatif)
	 */
	public String getDatabaseDriverUrlAdmin()
	{
		return this.getStandaloneProperties().getProperty("databaseDriverUrlAdmin","");
	}		
}
