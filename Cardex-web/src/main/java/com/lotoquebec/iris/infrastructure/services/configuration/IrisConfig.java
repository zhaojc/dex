package com.lotoquebec.iris.infrastructure.services.configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <p>Cette classe repr�sente le coeur de la gestion des fichiers de configuration.</p>
 * <p>La principale fonctionnalit� est de retourner le contenu d'un fichier de configuration en fonction
 * de l'environnement dans lequel on se trouve.</p>
 * <p>Le contenu des fichiers demand�s est conserv� dans une cache afin d'�viter les relectures.</p>
 * <p>La classe permet de :
 * <ul>
 * <li>retourner le contenu d'un fichier</li>
 * <li>obtenir les propri�t�s g�n�rales d�finies dans le fichier iris.properties</li>
 * <li>obtenir les propri�t�s pour le fonctionnement en mode standalone</li>
 * <li>interroger et mettre � jour l'�tat du syst�me dans la base de donn�es</li>
 * <li>s'incrire et recevoir les notifications lors d'un changement d'un fichier</li>
 * </ul>
 * 
 * @author Daniel Marchese
 */
public class IrisConfig {

	/** Le singleton */
	private static IrisConfig instance;
	
	/** Constante contenant le caract�re de s�paration des �l�ments du chemin*/
	public static final String SEPARATOR = "/";
	/** Nom de la variable d'environnement qui contient la racine du chemin (ex: dev, prod etc.) */
	public static final String CONFIG_VAR = "IRIS_CONFIG";
	/** Nom de la variable d'environnement qui d�signe la source de la configuration (CLASSPATH ou DATABASE) */
	public static final String SOURCE_CONFIG_VAR = "IRIS_CONFIG_SOURCE";	
	/** Valeur pour la variable IRIS_CONFIG_SOURCE d�signant le classpath comme source des fichiers */
	public static final String SOURCE_CLASSPATH = "CLASSPATH";	
	/** Valeur pour la variable IRIS_CONFIG_SOURCE d�signant la base de donn�es comme source des fichiers */
	public static final String SOURCE_DATABASE = "DATABASE";	
	/** Nom de la variable d'environnement qui permet d'activer ou d�sactiver la cache */
	public static final String CACHE_CONFIG_VAR = "CACHE_CONFIG";
	
	/** Dictionnaire contenant le cache des fichiers de configuration */
	private Map cache = new HashMap();
	/** Les propri�t�s issues du fichier standalone.properties */
	private IrisStandaloneProperties standaloneProperties = null;
	/** Les propri�t�s issues du fichier iris.properties */
	private Properties irisProperties = null;
	/** Un dictionnaire des objets demandant une notification en cas de changement d'un fichier */
	private Map objetsConfigurables = Collections.synchronizedMap(new HashMap());
	
	private boolean utiliserCache = true;

	/**
	 * <p>Le constructeur par d�faut est priv�. Utiliser getInstance().</p>
	 * 
	 * @see #getInstance()
	 */
	private IrisConfig()
	{
		super();
		// V�rifie s'il faut utiliser ou non la cache des fichiers de configuration
		String propriete = System.getProperty(CACHE_CONFIG_VAR, "oui");
		this.utiliserCache = propriete.equalsIgnoreCase("oui") || propriete.equalsIgnoreCase("true");
		System.out.println("La cache des fichiers de configuration est " + (this.utiliserCache?"activ�e.":"d�sactiv�e."));
	}

	/**
	 * <p>Retourne le singleton.</p>
	 * 
	 * @return l'instance unique d'IrisConfig
	 */
	public static IrisConfig getInstance()
	{
		synchronized(IrisConfig.class)
		{
			if ( instance == null )
				instance = new IrisConfig();
		}
		
		return instance;
	}	
	
	
	/**
	 * <p>R�initialise le cache des configurations.</p>
	 */
	private void resetCache()
	{
		cache.clear();
	}

	
	/**
	 * <p>Retourne le flux de donn�es sp�cifi� en le cherchant dans le classpath.<p>
	 * <p>Si le flux a d�j� �t� demand� et que la cache est active, le contenu du cache est retourn�.</p>
	 * 
	 * @param fullName le nom complet pour la recherche dans le classpath
	 * 
	 * @return le flux de donn�es
	 * 
	 * @throws Exception toute exception survenant lors de la recherche et lecture du flux de donn�es
	 */
	private InputStream getClassPathResource(String fullName) throws Exception
	{
		if ( this.utiliserCache && cache.containsKey(fullName) )
		{
			return new ByteArrayInputStream((byte[])cache.get(fullName));
		}
		else
		{
			InputStream result = null;
			
			result = PathConfiguration.class.getResourceAsStream(fullName);
			
			if ( result == null )
			{
				return null;
			}			
			// Il faut transformer le stream en tableau de bytes pour pouvoir le conserver
			cache.put(fullName, getBytesFromInputStream(result));
			result = new ByteArrayInputStream((byte[])cache.get(fullName));

			return result;
		}
	}
	
	
	/**
	 * <p>Retourne le contenu d'un InputStream sous la forme d'un tableau de bytes.</p>
	 * 
	 * @param stream le stream dont vont �tre lus les bytes
	 * 
	 * @return le contenu sous forme de tableau de bytes
	 * 
	 * @throws IOException toute exception survenant lors de la lecture du stream
	 */
	private byte[] getBytesFromInputStream(InputStream stream) throws Exception
	{
		ByteArrayOutputStream bou = new ByteArrayOutputStream();
		
		int aByte;

		while ( (aByte = stream.read()) > -1)
		{
			bou.write(aByte);
		}

		return bou.toByteArray();
	}


	/**
	 * <p>Retourne un fichier dans le chemin de base de la structure de configuration (ex: /iris/dev/).</p>
	 * <p>Cette m�thode est utilis�e uniquement pour retrouver le fichier standalone.properties.
	 * C'est la raison pour laquelle elle g�n�re une IOException pour qu'elle ne soit pas 
	 * journalis�e.</p>
	 * 
	 * @return le contenu du fichier demand�
	 * 
	 * @throws IOException si le fichier demand� n'existe pas
	 */
	public InputStream getClasspathBaseFile(String fileName) throws IOException
	{
		InputStream result = null;
		
		result = PathConfiguration.class.getResourceAsStream(fileName);
		
		if ( result == null )
			throw new IOException("Fichier " + fileName + " introuvable");
		
		return result;
	}

	/**
	 * <p>Retourne les propri�t�s d�finies dans standalone.properties.</p>
	 * 
	 *  @return une instance de IrisStandaloneProperties
	 */
	public IrisStandaloneProperties getStandaloneProperties()
	{
		if ( standaloneProperties == null )
			standaloneProperties = new IrisStandaloneProperties();
		return standaloneProperties;
	}

	/**
	 * <p>M�thode pouvant �tre appel�e par les classes impl�mentant l'interface Configurable
	 * afin d'�tre avis�es en cas de changement de configuration.</p>
	 * 
	 * @param path			l'instance d'IrisConfigPath d�signant le chemin du fichier
	 * @param nomFichier	le nom du fichier dont on veut �tre avis�s du changement
	 * @param service		le service souscrivant � la notification de changement
	 */
	public void souscrireChangement(IrisConfigPath path, String nomFichier, Configurable service)
	{
		Map mapFichiers = null;
		Set listeServices = null;
		
		if ( objetsConfigurables.containsKey(path.getPath()) )
		{
			// Une entr�e existe d�j� pour ce path
			mapFichiers = (Map)objetsConfigurables.get(path.getPath());
			if ( mapFichiers.containsKey(nomFichier) )
			{
				// Une entr�e existe d�j� pour ce nom de fichier
				listeServices = (Set)mapFichiers.get(nomFichier);
				listeServices.add(service); 
			}
			else
			{
				// Pas d'entr�e pour ce nom de fichier, il faut cr�er la liste
				listeServices = new HashSet();
				listeServices.add(service);
				mapFichiers.put(nomFichier, listeServices);
			}
		}
		else
		{
			// Aucune entr�e pour ce path, cr�� la structure au complet
			mapFichiers = new HashMap();
			listeServices = new HashSet();
			listeServices.add(service);
			mapFichiers.put(nomFichier, listeServices);
			objetsConfigurables.put(path.getPath(), mapFichiers);
		}
	}
	

	/*
	 *  (non-Javadoc)
	 * @see com.iris.infrastructure.services.configuration.AgentConfiguration#notifierChangement(com.iris.infrastructure.services.configuration.IrisConfigPath, java.lang.String)
	 */
 	public void notifierChangement(IrisConfigPath path, String nomFichier) throws Exception
	{
		if ( objetsConfigurables.containsKey(path.getPath()) )
		{
			Map mapFichiers = (Map)objetsConfigurables.get(path.getPath());
			
			if ( mapFichiers.containsKey(nomFichier) )
			{
				Set listeFichiers = (Set)mapFichiers.get(nomFichier);
				Iterator iterateur = listeFichiers.iterator();

				// S'il y a des souscripteurs, il faut vider la cache avant de les notifier
				if ( iterateur.hasNext() )
					this.resetCache();
					
				// Avise tous les souscripteurs pour qu'ils rafraichissent leur configuration
				while ( iterateur.hasNext() )
				{
					Configurable service = (Configurable)iterateur.next();
					service.rafraichirConfiguration(path, nomFichier);
				}
			}
		}
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.iris.infrastructure.services.configuration.AgentConfiguration#notifierChangement()
	 */
	public void notifierChangement() throws Exception
	{
		// On vide la cache m�moire
		this.resetCache();
		
		Iterator itPath = objetsConfigurables.keySet().iterator();
		
		// Boucle sur tous les chemins
		while ( itPath.hasNext() )
		{
			String path = (String)itPath.next();
			Map mapFichiers = (Map)objetsConfigurables.get(path);
			Iterator itFichiers = mapFichiers.keySet().iterator();
			
			// Boucle sur tous les noms de fichiers
			while ( itFichiers.hasNext() )
			{
				String nomFichier = (String)itFichiers.next();
				Iterator itServices = mapFichiers.entrySet().iterator();
				
				// Boucle sur tous les souscripteurs
				while ( itServices.hasNext() )
				{
					Configurable service = (Configurable)itServices.next();
					service.rafraichirConfiguration(new IrisConfigPath(path), nomFichier);
				}
			}
		}
	}
	
}
