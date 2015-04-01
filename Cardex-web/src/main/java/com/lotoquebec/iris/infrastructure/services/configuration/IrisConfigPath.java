package com.lotoquebec.iris.infrastructure.services.configuration;

/**
 * <p>Cette classe contient les diff�rents chemins possibles pour les
 * fichiers de configuration.</p>
 * <p>Le chemin complet d'un fichier de configuration est compos� de :
 * "iris" + environnement + image + IrisConfigPath + nom de fichier</p>
 * <p>
 * L'environnement d�signe soit le d�veloppement, soit les tests,
 * soit la production.
 * L'image d�signe une version de la configuration.
 * <p>
 *
 * @author Daniel Marchese
 *
 * @version 2004.10.08
 */
public class IrisConfigPath {

  /** Le chemin repr�sent� par cette instance */
  private String path;

  /** Constante d�crivant le chemin pour les DTD */
  public static final IrisConfigPath DTD = new IrisConfigPath("dtd");
  /** Constante d�crivant le chemin pour la configuration du service de journalisation */
  public static final IrisConfigPath LOGGING = new IrisConfigPath("logging");
  /** Constante d�crivant le chemin pour la configuration du service de persistence */
  public static final IrisConfigPath PERSISTENCE = new IrisConfigPath("persistence");
  /** Constante d�crivant le chemin pour la configuration de la s�curit� */
  public static final IrisConfigPath SECURITY = new IrisConfigPath("security");
  /** Constante d�crivant le chemin pour la configuration du service de localisation des services */
  public static final IrisConfigPath SERVICES = new IrisConfigPath("services");
  /** � EFFACER Constante d�crivant le chemin pour la configuration du service de notification */
  public static final IrisConfigPath NOTIFICATION = new IrisConfigPath("notification");
  /** Constante d�crivant le chemin pour la configuration du service des rapports */
  public static final IrisConfigPath RAPPORTS = new IrisConfigPath("rapports");
  /** Constante d�crivant le chemin pour la configuration des DTD de contenu pour le service des rapports */
  public static final IrisConfigPath RAPPORTS_DTD_CONTENU = new IrisConfigPath("rapports" + IrisConfig.SEPARATOR + "dtdContenu");
  /** Constante d�crivant le chemin pour la configuration du service de diffusion */
  public static final IrisConfigPath DIFFUSION = new IrisConfigPath("diffusion");
  /** Constante d�crivant le chemin pour la configuration du service de date logique */
  public static final IrisConfigPath DATE_LOGIQUE = new IrisConfigPath("datelogique");
  /** Constante d�crivant le chemin pour la configuration des messages d'erreur */
  public static final IrisConfigPath MSGERREUR = new IrisConfigPath("messagesErreurs");
  /** Constante d�crivant le chemin pour la configuration des services de l'infrastructure */
  public static final IrisConfigPath MANAGEMENT = new IrisConfigPath("management");
  /** Constante d�crivant le chemin pour la configuration du service de communication */
  public static final IrisConfigPath COMMUNICATION = new IrisConfigPath("communication");
  /** Constante d�crivant le chemin pour la configuration du service de messagerie */
  public static final IrisConfigPath MESSAGING = new IrisConfigPath("messaging");
  /** Constante d�crivant le chemin pour la configuration du service de conversion */
  public static final IrisConfigPath CONVERSION = new IrisConfigPath("conversion");
  /** Constante d&eacute;crivant le r&eacute;pertoire contenant les fichiers de mappings java-xml utilis&eacute;s par le service de conversion;
  ce r&eacute;pertoire ne doit contenir que des fichiers xml de format castor.exolab.org */
  public static final IrisConfigPath MAPPINGS_JAVA_XML = new IrisConfigPath("mappingsjavaxml");
  /** Constante d�crivant le chemin pour la configuration du service d'expedition */
  public static final IrisConfigPath EXPEDITION = new IrisConfigPath("expedition");
  /** Constante d�crivant le chemin pour la configuration du service int�gration */
  public static final IrisConfigPath INTEGRATION = new IrisConfigPath("integration");

  /**
   * <p>Le constructeur par d�faut est priv�.</p>
   */
  private IrisConfigPath()
  {
    super();
  }


  /**
   * <p>Constructeur avec un chemin qui ne fait pas partie de ceux r�f�renc�s
   * dans les constantes de la classe.</p>
   * <p>Il est pr�f�rable de d�finir une variable statique avec le chemin en
   * question.</p>
   *
   * @param newPath le chemin du projet IrisConfig
   */
  public IrisConfigPath(String newPath)
  {
    super();
    this.setPath(newPath);
  }

  /**
   * <p>Retourne le chemin.</p>
   *
   * @return le chemin du projet IrisConfig
   */
  public String getPath() {
    return path;
  }

  /**
   * <p>Initialise le chemin.</p>
   *
   * @param path le chemin du projet IrisConfig
   */
  private void setPath(String path) {
    this.path = path;
  }

}
