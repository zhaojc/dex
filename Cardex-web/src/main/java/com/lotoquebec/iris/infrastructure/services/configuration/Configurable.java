package com.lotoquebec.iris.infrastructure.services.configuration;


/**
 * <p>Interface devant �tre implant�e par les classes qui veulent �tre notifi�es d'un changement de configuration.</p>
 * 
 * @author Daniel Marchese
 */
public interface Configurable {
	
	/**
	 * <p>M�thode appel�e lors d'un changement de configuration.</p>
	 * <p>
	 * Pour que la m�thode soit appel�e, il faut que les instances impl�mentant cette interface s'enregistrent
	 * aupr�s du service de configuration en appelant {@link IrisConfig#souscrireChangement(com.iris.infrastructure.services.configuration.IrisConfigPath,java.lang.String,com.iris.infrastructure.services.configuration.Configurable)}
	 * </p>
	 * 
	 * @param path			l'instance de IrisConfigPath d�signant le chemin du fichier dans IrisConfig
	 * @param nomFichier	le nom du fichier pour lequel on veut recevoir une notification
	 * 
	 * @throws Exception toute exception survenant lors du rafra�chissement de la configuration
	 */
	public void rafraichirConfiguration(IrisConfigPath path, String nomFichier)
		throws Exception;

}
