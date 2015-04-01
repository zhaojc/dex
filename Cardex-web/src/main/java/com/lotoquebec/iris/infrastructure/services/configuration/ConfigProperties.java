package com.lotoquebec.iris.infrastructure.services.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * <p>Cette classe �tend les fonctionnalit�s de la classe {@link java.util.Properties}
 * pour standardiser le chargement des propri�t�s des services.</p>
 * 
 * @author Gilles Rondeau.
 * @author J�r�me Caron
 */
public class ConfigProperties extends Properties
{
	/** Indique que le fichier a �t� charg� avec succ�s */
    public final static int STATUS_OK = 0;
    /** Indique que le fichier n'a pas �t� trouv� */
    public final static int STATUS_NF = 1;
    /** Indique une erreur de lecture sur le fichier */
    public final static int STATUS_IO = 2;

    private boolean onErrorRaise = true;
    private boolean loadError = false;
    private String applicationId = "appId";
    private String requesterId = "reqId";
    private String configFile = "";
    private int loadStatus = 0;
    private boolean loaded;

    /**
	 * <p>Constructeur par d�faut.</p>
     */
    public ConfigProperties()
    {
        super();

    }

    /**
	 * <p>Retourne une instance initialis�e avec le fichier sp�cifi�.</p>
	 * 
	 * @param cfgFileName le nom du fichier de configuration
	 * 
	 * @throws Exception toute exception survenant lors de l'initialisation
     */
    public ConfigProperties(String cfgFileName) throws Exception
    {
        super();
        load(cfgFileName);
    }
    
	/**
	 * <p>Retourne une instance initialis�e avec le flux sp�cifi�.</p>
	 * 
	 * @param cfg le flux de configuration
	 * 
	 * @throws Exception toute exception survenant lors de l'initialisation
	 */
    public ConfigProperties(InputStream cfg) throws Exception
    {
        super();
        loadStream(cfg);
    }    

    /**
	 * <p>Retourne le nom du fichier utilis� pour charger les propri�t�s de configuration.</p>
	 * 
	 * @return le nom du fichier de configuration
     */
    public String getFileName()
    {
        return (configFile);
    }

    /**
	 * <p>Retourne le statut de chargement.</p>
	 * 
	 * @return le statut
	 * 
	 * @see #STATUS_OK
	 * @see #STATUS_NF
	 * @see #STATUS_IO
     */
    public int getLoadStatus()
    {
        return (loadStatus);
    }

    /**
	 * <p>Retourne la valeur du param�tre convertie en <code>int</code>.</p>
	 * 
	 * @param paramName le nom du param�tre
	 * 
	 * @return la valeur du param�tre convertie en <code>int</code>
	 * 
	 * @throws Exception toute exception survenant lors de la conversion
     */
    public int getValueAsInt(String paramName)
        throws Exception
    {
		Integer value = new Integer(getProperty(paramName));
		return value.intValue();
    }

	/**
	 * <p>Retourne la valeur du param�tre convertie en <code>int</code>.</p>
	 * 
	 * @param paramName		le nom du param�tre
	 * @param defaultValue	la valeur par d�faut si le param�tre n'est pas d�fini
	 * 
	 * @return la valeur du param�tre convertie en <code>int</code>
	 * 
	 * @throws Exception toute exception survenant lors de la conversion
	 */
    public int getValueAsInt(String paramName, int defaultValue)
        throws Exception
    {
		Integer value = new Integer(getProperty(paramName, Integer.toString(defaultValue)));
		return value.intValue();
    }

	/**
	 * <p>Retourne la valeur du param�tre convertie en <code>long</code>.</p>
	 * 
	 * @param paramName le nom du param�tre
	 * 
	 * @return la valeur du param�tre convertie en <code>long</code>
	 */    
    public long getValueAsLong(String paramName)
    {
    	return getValueAsLong(paramName, 0);
    }
    
	/**
	 * <p>Retourne la valeur du param�tre convertie en <code>long</code>.</p>
	 * 
	 * @param paramName		le nom du param�tre
	 * @param defaultValue	la valeur par d�faut si le param�tre n'est pas d�fini
	 * 
	 * @return la valeur du param�tre convertie en <code>long</code>
	 */    
    public long getValueAsLong(String paramName, long defaultValue)
    {
        try
        {
        	String str = getProperty(paramName);
        	if (str == null)
        		return defaultValue;
            return Long.parseLong(str);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }
    }
    
    /**
	 * <p>Retourne les valeurs du param�tre sp�cifi� en un tableau de <code>int</code>.</p>
	 * <p>Les valeurs sont s�par�es par des virgules. Par exemple : MyApp.MyParam=34,23,45</p>
	 * 
	 * @param paramName le nom du param�tre
	 * 
	 * @return les valeurs du param�tre converties en tableau de <code>int</code>
	 * 
	 * @throws Exception toute exception survenant lors de la conversion
     */
    public int[] getValuesAsInt(String paramName)
        throws Exception
    {
        int[] values;
        StringTokenizer strVal = new StringTokenizer(getProperty(paramName, ""), ",");
        int cnt = strVal.countTokens();
        values = new int[cnt];
        for (int ndx = 0; ndx < cnt; ++ndx)
        {
			Integer intTok = new Integer(strVal.nextToken());
			values[ndx] = intTok.intValue();
        }
        return values;
    }

	/**
	 * <p>Retourne les valeurs du param�tre sp�cifi� en un tableau de <code>String</code>.</p>
	 * <p>Les valeurs sont s�par�es par des virgules. Par exemple : MyApp.MyParam=val1,val2,val3</p>
	 * 
	 * @param paramName le nom du param�tre
	 * 
	 * @return les valeurs du param�tre converties en tableau de <code>String</code>
	 * 
	 * @throws Exception toute exception survenant lors de la conversion
     */
    public String[] getValuesAsString(String paramName)
        throws Exception
    {
        String[] values;
        StringTokenizer strVal = new StringTokenizer(getProperty(paramName, ""), ",");
        int cnt = strVal.countTokens();
        values = new String[cnt];
        for (int ndx = 0; ndx < cnt; ++ndx)
        {
			values[ndx] = strVal.nextToken().trim();
        }
        return values;
    }

   /**
    * <p>Retourne si le fichier est charg�.</p>
    * 
    * @return <code>true</code> si le fichier est charg�
    */
    public boolean isLoaded()
    {
        return loaded;
    }

	/**
	 * <p>Retourne s'il y a eu une erreur au chargement du fichier.</p>
	 * 
	 * @return <code>true</code> si une erreur s'est produite au chargement
	 */
    public boolean isLoadError()
    {
        return (loadStatus == 0) ? false : true;
    }


    /**
	 * <p>Charge toutes les propri�t�s � partir d'un fichier de configuration.</p>
	 * 
	 * @param cfgFileName le nom du fichier de configuration qui doit �tre charg�
	 * 
	 * @throws Exception toute exception survenant lors du chargement
     */
    public void load(String cfgFileName) throws Exception 
    {
        // Load properties from main configuration file
        loadFromFile(cfgFileName);

        // Load included configuration files
        String[] cfgFiles = getValuesAsString("Include.Files");
        for (int ndx = 0; ndx < cfgFiles.length; ++ndx)
        {
            loadFromFile(cfgFiles[ndx]);
        }
    }
    
	/**
	 * <p>Charge toutes les propri�t�s � partir d'un flux de configuration.</p>
	 * 
	 * @param cfg le flux de configuration qui doit �tre charg�
	 * 
	 * @throws Exception toute exception survenant lors du chargement
	 */
    public void loadStream(InputStream cfg) throws Exception 
    {
        // Load properties from main configuration file
        loadFromStream(cfg);

        // Load included configuration files
        String[] cfgFiles = getValuesAsString("Include.Files");
        for (int ndx = 0; ndx < cfgFiles.length; ++ndx)
        {
            loadFromFile(cfgFiles[ndx]);
        }
    }    

    /**
	 * <p>Charge les propri�t�s � partir du fichier sp�cifi�. Si le r�pertoire n'est pas sp�cifi�,
	 * l'emplacement du fichier est d�sign� par la propri�t� syst�me <code>com.iris.config.path</code> sp�cifi�e
	 * au d�marrage de l'application en utilisant l'option -D de la JVM.</p>
	 * 
	 * @param cfgFileName le nom du fichier de configuration
	 * 
	 * @throws Exception toute exception survenant lors du chargement du fichier
     */
    private void loadFromFile(String cfgFileName) throws Exception
    {

        // Load properties file
        try
        {
      		InputStream is = getCfgFileStream(cfgFileName);
            load(is);
            loaded = true;
        }
        catch (FileNotFoundException fnfe)
        {
        	loadStatus = STATUS_NF;

			Exception ex = new Exception("Le fichier " + cfgFileName + " est introuvable",fnfe);
			throw ex;  
        }
        catch (IOException ioe)
        {
        	loadStatus = STATUS_IO;
        	
			Exception ex = new Exception("Erreur lors de la lecture du fichier " + cfgFileName,ioe);
			throw ex; 
        }
    }

	/**
	 * <p>Charge toutes les propri�t�s � partir d'un flux de configuration.</p>
	 * 
	 * @param cfg le flux de configuration qui doit �tre charg�
	 * 
	 * @throws Exception toute exception survenant lors du chargement
	 */
    private void loadFromStream(InputStream cfg) throws Exception
    {

        // Load properties file
        try
        {
      		load(cfg);
            loaded = true;
        }
        catch (FileNotFoundException fnfe)
        {
        	loadStatus = STATUS_NF;
			Exception ex = new Exception("Le fichier " + cfg + " est introuvable",fnfe);
			throw ex;  
        }
        catch (IOException ioe)
        {
        	loadStatus = STATUS_IO;
			Exception ex = new Exception("Erreur lors de la lecture du fichier " + cfg,ioe);        	
			throw ex; 
        }
    }

    /**
	 * <p>Initialise l'indicateur d'erreur si une exception survient lors de l'initialisation des propri�t�s.</p>
	 * 
	 * @param flag la valeur de l'indicateur
     */
    public void raiseExceptionOnError(boolean flag)
    {
        onErrorRaise = flag;
    }


   /**
     * <p>M�thode entreposant le fichier de configuration en m�moire.</p>
     * 
     * @throws Exception toute exception survenant lors de l'entreposage
     */
    public void store() throws Exception
    {
        OutputStream out = null;

        try
        {
            out = new FileOutputStream("." + File.separator + configFile);
            store(out, null);
        }
        finally
		{
			out.close();
		}
    }

	/**
	 * <p>Retourne le fichier de configuration comme flux en se basant sur le nom sp�cifi�.</p>
	 * <p>Si un chemin est sp�cifi� dans l'environnement, le fichier sera charg� � partir de ce chemin.
	 * Si le chemin n'est pas sp�cifi�, le fichier sera charg� � partir du m�me r�pertoire que l'objet de configuration.</p
	 * 
	 * @param cfgFileName le nom du fichier de configuration
	 * 
	 * @throws Exception toute exception survenant lors du chargement du fichier
	 */
    private InputStream getCfgFileStream(String cfgFileName) throws Exception
    {
    	InputStream cfgFileStream = null;
        String cfgPath = System.getProperty("com.iris.config.path");

      	if (cfgPath != null)
       	{
           	configFile = cfgPath + File.separator + cfgFileName;
           	cfgFileStream = new FileInputStream(configFile);
       	}
       	else
       	{
       		cfgFileStream = getClass().getResourceAsStream(cfgFileName);

       		if (cfgFileStream == null)
       		{
       			throw new FileNotFoundException();
       		}
       	}
        return cfgFileStream;
    }
    
    public boolean equals (Object o) {
    	return super.equals(o);
    }
    
    public int hashCode(){
    	return 1;
    }
    
}