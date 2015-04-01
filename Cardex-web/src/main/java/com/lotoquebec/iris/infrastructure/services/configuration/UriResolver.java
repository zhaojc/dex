package com.lotoquebec.iris.infrastructure.services.configuration;

import java.io.InputStream;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;


/** Utilis�e pour la conversion de XML vers un autre format.
 * Un instance de cette classe est *IMMUABLE*.
 *
 * @see javax.xml.transform.URIResolver
 *
 * @author     Charles Beauregard
 * @author     serge masse
 * @version    2004.11.19
 */
public class UriResolver implements URIResolver {

  /** Utilis� dans les messages d'erreur et d'essais. */
  private static final String CLASSE = UriResolver.class.getName();

  /** Ne devrait pas changer apr� son affectation dans le constructeur.
   */
  protected String CHEMIN_PARTIEL_AVEC_RACINE;
  protected String CHEMIN_PARTIEL_SANS_RACINE;

  /**
   * @param cheminPartielSansRacine Chemin de r�pertoires de fichiers,
   *             sans le prefix complet et sans le suffix complet,
   *             c.-�-d. sans la racine et sans le nom de fichier
   *             ni le(s) r�pertoire(s) pr�c�dant
   *             le nom de fichier.
   *             Exemple :
   *             <code>new UriResolver(IrisConfigPath.RAPPORTS.getPath())
   *             </code>
   */
  public UriResolver(String cheminPartielSansRacine){

	CHEMIN_PARTIEL_SANS_RACINE = cheminPartielSansRacine;
  }

  /** pour ses enfants */
  protected UriResolver(){}


  public String getCheminPartielAvecRacine(){
    return CHEMIN_PARTIEL_AVEC_RACINE;
  }

  /**
   * @see javax.xml.transform.URIResolver#resolve(java.lang.String,
   *                                              java.lang.String)
   * @see javax.xml.transform.stream.StreamSource
   *
   * @param cheminPartielAvecFichier le suffix du chemin d'un fichier,
   *             doit inclure le nom complet du fichier.
   * @param baseIgnoree ce param�tre est ignor�,
   *                    il n'est pas utilis�.
   *
   * @return Source un StreamSource pour le fichier
   *                CHEMIN_PARTIEL_AVEC_RACINE+cheminPartielAvecFichier.
   */
  public Source resolve(String cheminPartielAvecFichier,
                        String cheminDeBaseIgnore)
  throws TransformerException {
    try {
      InputStream stream = getInputStream(CHEMIN_PARTIEL_AVEC_RACINE,
                                          cheminPartielAvecFichier);
      return new StreamSource(stream);
    } catch (Exception iie) {
      throw new TransformerException(iie);
    }
  }

  /**
   * Retourne le flux de donn�es pour le fichier sp�cifi�
   * en le cherchant dans le <code>classpath</code>.
   *
   * @param cheminPartielAvecRacine Le chemin partiel avec la racine;
   *        ne doit pas �tre termin� par un s�parateur de fichiers;
   *        ce chemin est concat�n� avec un s�parateur de fichiers et l'autre
   *        param�tre pour obtenir un chemin complet,
   *        de la racine au nom de fichier.
   *
   * @param cheminPartielAvecFichier Le chemin partiel incluant un nom de
   *        fichier;
   *        ne doit pas commencer par un s�parateur de fichiers;
   *        ce chemin est concat�n� avec un s�parateur de fichiers et l'autre
   *        param�tre pour obtenir un chemin complet,
   *        de la racine au nom de fichier.
   *
   * @return InputStream Obtenu en utilisant
   *         <code>UriResolver.class.getResourceAsStream()</code>.
   *
   * @throws Exception Quand anomalie quelconque.
   */
  public InputStream getInputStream(String cheminPartielAvecRacine,
                                           String cheminPartielAvecFichier)
  throws Exception {
    InputStream stream = null;
    //TODO prochaine version : ajouter separateur seulement au besoin
    String cheminComplet = cheminPartielAvecRacine + IrisConfig.SEPARATOR
                           + cheminPartielAvecFichier;
    try{
      stream = PathConfiguration.class.getResourceAsStream(cheminComplet);

    }catch(Exception ex){
      throw new Exception(CLASSE+".getInputStream(String,String) : Le fichier "
            + cheminComplet + " est introuvable; "+ex );
    }
    if ( stream == null ){
      throw new Exception(CLASSE
          +".getInputStream(String,String) : Le fichier "
          + cheminComplet + " est introuvable");
    }
    return stream;
  }

  /** Cette m�thode utilise un chemin de r�pertoire incluant la racine.
   * <br>Un url de jar = <code>jar:&lt;url&gt;!/{entry}</code>
   * <br>Utilise <code>Class.getResource(chemin)</code>, donc ces
   * conventions sont utilis�es pour la syntaxe du chemin :
   *
	 * <p>The <code>Class.getResource(chemin) method delegates the call to
	 * its class loader, after making
	 * these changes to the resource name: if the resource name starts with
	 * "/", it is unchanged; otherwise, the package name is prepended to the
	 * resource name after converting "." to "/".  If this object was loaded by
	 * the bootstrap loader, the call is delegated to
	 * <code>ClassLoader.getSystemResourceAsStream</code>.
   *
   * @param classe La classe utilis�e pour obtenir un ClassLoader.
   *
   * @param chemin Le nom de fichier complet, incluant le chemin de r�pertoire
   *               et incluant la racine.
   *               Une exception est lev�e s'il est nul, vide, ou si le
   *               fichier n'existe pas.
   *               Devrait normalement commencer par le
   *               caract�re '/'.
   *
   * @return Un InputStream avec connexions ouvertes au fichier.
   */
  public static InputStream getXmlInputStream(String nomDeFichierAvecChemin)
  throws Exception {
    try{
			URL url = UriResolver.class.getResource(nomDeFichierAvecChemin);
			if(url.toString().startsWith("jar:")){
			  //le url est dans un jar
			  return PathConfiguration.class.getResourceAsStream(nomDeFichierAvecChemin);
			}
		  //url pas un jar
		  return url.openStream();
		}catch(Exception ex){
			throw new Exception(CLASSE
			            +".getXmlInputStreams(): anomalie" +
                  " ["+ex+"]"+                  " en essayant d'acc�der au "
			            +"fichier "+nomDeFichierAvecChemin
			            ,ex);
		}
  }


  public String toString(){
	  return CHEMIN_PARTIEL_AVEC_RACINE;
  }

}