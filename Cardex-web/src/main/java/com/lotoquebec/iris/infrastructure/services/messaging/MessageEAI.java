package com.lotoquebec.iris.infrastructure.services.messaging;

import java.io.Serializable;

import javax.jms.Destination;

import org.w3c.dom.Element;

import com.lotoquebec.iris.infrastructure.services.configuration.ConfigXML;


/**
 *
 * <p>Format d'un message �chang� entre IRIS et les syst�mes externes.
 * Repr�sente la structure de donn�es d�un message utilis� par le service 
 * de messagerie et compris du c�t� de WMQI. 
 * Il est envoy� sous format XML comme �tant le corps d�un message JMS de 
 * type � Texte �. Les propri�t�s du message EAI sont r�pliqu�es dans le message JMS.</p>
 * 
 * @author Jerome Caron
 * @author Marco Montestruque
*/
public class MessageEAI implements Serializable {
	private static final long serialVersionUID = -5256868278136235185L;
	public final static String TAG_JETON = "JETON";
    public final static String TAG_INITIATEUR = "INITIATEUR";
    public final static String TAG_ID_TRANS = "IDTRANS";
    public final static String TAG_ESTAMPILLE = "ESTAMPILLE";
    public final static String TAG_ID_EMETTEUR = "IDEMETTEUR";
    public final static String TAG_ENV_EMETTEUR = "ENVEMETTEUR";
    public final static String TAG_ENV_RECEPTEUR = "ENVRECEPTEUR";
    public final static String TAG_NOM_FLUX = "NOMFLUX";
    public final static String TAG_TYPE_FLUX = "TYPEFLUX";
    public final static String TAG_VERSION_FLUX = "VERSIONFLUX";
    public final static String TAG_TYPE_SERVICE = "TYPESERVICE";
    public final static String TAG_DESTINATAIRE = "DESTINATAIRE";
    public final static String TAG_OBJET = "OBJET";
    public final static String TAG_CLE = "CLE";
    public final static String TAG_CONTENU = "CONTENU";
    public final static String TAG_STATUT = "STATUT";
    private String initiateur = null;
    private String idTrans = null;
    private String estampille = null;
    private String idEmetteur = null;
    private String envEmetteur = null;
    private String envRecepteur = null;
    private String nomFlux = null;
    private String typeFlux = null;
    private String versionFlux = null;
    private String jeton = null;
    private String contenu = null;
    private String champLibre = null;
    private String typeDateLogique = null;
    private boolean isExternal = false;
    private boolean isLoggable = false;
    private String operation = null; // utilis� pour type DIFFUSION ET EXECUTION
    private String typeService = null; // utilis� pour type EXECUTION
    private String destinataire = null; // utilis� pour type COMMUNICATION
    private String cle = null;
    private String objet = null;
    private String statut = null;
    
    private transient Destination destinationStatut;

    /**
     * <p>Constructor for MessageEAI.</p>
     */
    public MessageEAI() {
    }

    /**
     * <p>Constructor MessageEAI.</p>
     */
    public MessageEAI(String xml) throws Exception {

        Element root = ConfigXML.loadXMLDocument(xml);

        if (root.getElementsByTagName(TAG_JETON).item(0).getFirstChild() != null) {
            this.setJeton(root.getElementsByTagName(TAG_JETON).item(0)
                              .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_INITIATEUR).item(0).getFirstChild() != null) {
            this.setInitiateur(root.getElementsByTagName(TAG_INITIATEUR).item(0)
                                   .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_ID_TRANS).item(0).getFirstChild() != null) {
            this.setIdTrans(root.getElementsByTagName(TAG_ID_TRANS).item(0)
                                .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_ESTAMPILLE).item(0).getFirstChild() != null) {
            this.setEstampille(root.getElementsByTagName(TAG_ESTAMPILLE).item(0)
                                   .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_ID_EMETTEUR).item(0).getFirstChild() != null) {
            this.setIdEmetteur(root.getElementsByTagName(TAG_ID_EMETTEUR).item(0)
                                   .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_ENV_EMETTEUR).item(0).getFirstChild() != null) {
            this.setEnvEmetteur(root.getElementsByTagName(TAG_ENV_EMETTEUR)
                                    .item(0).getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_ENV_RECEPTEUR).item(0).getFirstChild() != null) {
            this.setEnvRecepteur(root.getElementsByTagName(TAG_ENV_RECEPTEUR)
                                     .item(0).getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_NOM_FLUX).item(0).getFirstChild() != null) {
            this.setNomFlux(root.getElementsByTagName(TAG_NOM_FLUX).item(0)
                                .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_TYPE_FLUX).item(0).getFirstChild() != null) {
            this.setTypeFlux(root.getElementsByTagName(TAG_TYPE_FLUX).item(0)
                                 .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_VERSION_FLUX).item(0).getFirstChild() != null) {
            this.setVersionFlux(root.getElementsByTagName(TAG_VERSION_FLUX)
                                    .item(0).getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName("TYPEDATELOGIQUE").item(0).getFirstChild() != null) {
            this.setTypeDateLogique(root.getElementsByTagName("TYPEDATELOGIQUE")
                                        .item(0).getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName("CHAMPLIBRE").item(0).getFirstChild() != null) {
            this.setChampLibre(root.getElementsByTagName("CHAMPLIBRE").item(0)
                                   .getFirstChild().getNodeValue());
        }

        if (root.getElementsByTagName(TAG_CONTENU).item(0).getFirstChild() != null) {
            String contenu = root.getElementsByTagName(TAG_CONTENU).item(0)
                                 .getFirstChild().getNodeValue();
            this.setContenu(contenu);
        }

        if (this.getTypeFlux().equals(MessagingService.TYPE_FLUX_COMMUNICATION)) {
            if (root.getElementsByTagName(TAG_DESTINATAIRE).item(0)
                        .getFirstChild() != null) {
                this.setDestinataire(root.getElementsByTagName(TAG_DESTINATAIRE)
                                         .item(0).getFirstChild().getNodeValue());
            }

            if (root.getElementsByTagName(TAG_CLE).item(0).getFirstChild() != null) {
                this.setCle(root.getElementsByTagName(TAG_CLE).item(0)
                                .getFirstChild().getNodeValue());
            }

            if (root.getElementsByTagName(TAG_OBJET).item(0).getFirstChild() != null) {
                this.setObjet(root.getElementsByTagName(TAG_OBJET).item(0)
                                  .getFirstChild().getNodeValue());
            }
        } else if (this.getTypeFlux().equals(MessagingService.TYPE_FLUX_DIFFERE)) {
            if (root.getElementsByTagName("TYPESERVICE").item(0).getFirstChild() != null) {
                this.setTypeService(root.getElementsByTagName("TYPESERVICE")
                                        .item(0).getFirstChild().getNodeValue());
            }
        }

		if (root.getElementsByTagName(TAG_STATUT).item(0).getFirstChild() != null) {
			String statut = root.getElementsByTagName(TAG_STATUT).item(0)
								 .getFirstChild().getNodeValue();
			this.setStatut(statut);
		}        
    }

    /**
     * <p>Retourne la cl� du message</p> 
     * 
     * @return la cl� du message 
     */
    public String getCle() {
        return cle;
    }

    /**
     * 
     * @return l'environnement �metteur 
     */
    public String getEnvEmetteur() {
        return envEmetteur;
    }

    /**
     * 
     * @return l'environnement r�cepteur 
     */
    public String getEnvRecepteur() {
        return envRecepteur;
    }

    /**
     * <p>Retourne l'ID de l'emetteur.</p> 
     * 
     * @return l'identifiant de l'�metteur 
     */
    public String getIdEmetteur() {
        return idEmetteur;
    }

    /**
     * <p>Retourne le nom du flux.</p>
     *  
     * @return le nom du flux
     */
    public String getNomFlux() {
        return nomFlux;
    }

    /**
     * <p>Retourne la version du flux.</p>
     *  
     * @return la version du flux
     */
    public String getVersionFlux() {
        return versionFlux;
    }

    /**
     * 
     * @param cle The cle to set
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * @param envEmetteur 
     */
    public void setEnvEmetteur(String envEmetteur) {
        this.envEmetteur = envEmetteur;
    }

    /**
     * @param envRecepteur
     */
    public void setEnvRecepteur(String envRecepteur) {
        this.envRecepteur = envRecepteur;
    }

    /**
     * @param idEmetteur 
     */
    public void setIdEmetteur(String idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    /**
     * @param nomFlux 
     */
    public void setNomFlux(String nomFlux) {
        this.nomFlux = nomFlux;
    }

    /**
     * @param versionFlux 
     */
    public void setVersionFlux(String versionFlux) {
        this.versionFlux = versionFlux;
    }

    /**
    * <p>Retourne le contenu du message.</p>
    *  
	* @return le contenu du message
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * @param contenu 
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * @return l'estampille du message
     */
    public String getEstampille() {
        return estampille;
    }

    /**
     * <p>Retourne l'identifiant de la transmission.</p> 
     * 
     * @return l'dentifiant de la transmission
     */
    public String getIdTrans() {
        return idTrans;
    }

    /**
     * @return l'initiateur du message
     */
    public String getInitiateur() {
        return initiateur;
    }

    /**
     * @param estampille The estampille to set
     */
    public void setEstampille(String estampille) {
        this.estampille = estampille;
    }

    /**
     * @param idTrans 
     */
    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    /**
     * @param initiateur 
     */
    public void setInitiateur(String initiateur) {
        this.initiateur = initiateur;
    }

    /**
     * @return le jeton
     */
    public String getJeton() {
        return jeton;
    }

    /**
     * @param string
     */
    public void setJeton(String string) {
        jeton = string;
    }

    /**
     * <p>Gen�re le contenu dans un format XML</p>
     *
     * @return String the XML
     */
    public String toXML() {
        StringBuffer xml = new StringBuffer();

        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append(
            "<MEICORPO VERSION=\"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"MEICORPO1.00.xsd\">\n");

        if (getJeton() == null) {
            xml.append("<JETON/>\n");
        } else {
            xml.append("<JETON>" + getJeton() + "</JETON>\n");
        }

        if (getInitiateur() == null) {
            xml.append("<INITIATEUR/>\n");
        } else {
            xml.append("<INITIATEUR>" + getInitiateur() + "</INITIATEUR>\n");
        }

        if (getIdTrans() == null) {
            xml.append("<IDTRANS/>\n");
        } else {
            xml.append("<IDTRANS>" + getIdTrans() + "</IDTRANS>\n");
        }

        if (getEstampille() == null) {
            xml.append("<ESTAMPILLE/>\n");
        } else {
            xml.append("<ESTAMPILLE>" + getEstampille() + "</ESTAMPILLE>\n");
        }

        if (getIdEmetteur() == null) {
            xml.append("<IDEMETTEUR/>\n");
        } else {
            xml.append("<IDEMETTEUR>" + getIdEmetteur() + "</IDEMETTEUR>\n");
        }

        if (getEnvEmetteur() == null) {
            xml.append("<ENVEMETTEUR/>\n");
        } else {
            xml.append("<ENVEMETTEUR>" + getEnvEmetteur() + "</ENVEMETTEUR>\n");
        }

        if (getEnvRecepteur() == null) {
            xml.append("<ENVRECEPTEUR/>\n");
        } else {
            xml.append("<ENVRECEPTEUR>" + getEnvRecepteur() +
                "</ENVRECEPTEUR>\n");
        }

        if (getNomFlux() == null) {
            xml.append("<NOMFLUX/>\n");
        } else {
            xml.append("<NOMFLUX>" + getNomFlux() + "</NOMFLUX>\n");
        }

        if (getTypeFlux() == null) {
            xml.append("<TYPEFLUX/>\n");
        } else {
            xml.append("<TYPEFLUX>" + getTypeFlux() + "</TYPEFLUX>\n");
        }

        if (getVersionFlux() == null) {
            xml.append("<VERSIONFLUX/>\n");
        } else {
            xml.append("<VERSIONFLUX>" + getVersionFlux() + "</VERSIONFLUX>\n");
        }

        if (getTypeDateLogique() == null) {
            xml.append("<TYPEDATELOGIQUE/>\n");
        } else {
            xml.append("<TYPEDATELOGIQUE>" + getTypeDateLogique() +
                "</TYPEDATELOGIQUE>\n");
        }

        if (getChampLibre() == null) {
            xml.append("<CHAMPLIBRE/>\n");
        } else {
            xml.append("<CHAMPLIBRE>" + getChampLibre() + "</CHAMPLIBRE>\n");
        }

        //le CDATA est important pour ne pas valider le XML du contenu affaire
        if (getContenu() == null) {
            xml.append("<CONTENU/>\n");
        } else {
            xml.append("<CONTENU><![CDATA[" + getContenu() + "]]></CONTENU>\n");
        }

        if (this.getTypeFlux().equals(MessagingService.TYPE_FLUX_COMMUNICATION)) {
            if (getDestinataire() == null) {
                xml.append("<DESTINATAIRE/>\n");
            } else {
                xml.append("<DESTINATAIRE>" + getDestinataire() +
                    "</DESTINATAIRE>\n");
            }

            if (getObjet() == null) {
                xml.append("<OBJET/>\n");
            } else {
                xml.append("<OBJET>" + getObjet() + "</OBJET>\n");
            }

            if (getCle() == null) {
                xml.append("<CLE/>\n");
            } else {
                xml.append("<CLE>" + getCle() + "</CLE>\n");
            }
        } else if (this.getTypeFlux().equals(MessagingService.TYPE_FLUX_DIFFERE)) {
            if (getTypeService() == null) {
                xml.append("<TYPESERVICE/>\n");
            } else {
                xml.append("<TYPESERVICE>" + getTypeService() +
                    "</TYPESERVICE>\n");
            }
        }

		if (getStatut() == null) {
			 xml.append("<STATUT/>\n");
		 } else {
			 xml.append("<STATUT>" + getStatut() + "</STATUT>\n");
		 }
		 
        xml.append("</MEICORPO>\n");

        return xml.toString();
    }

    /**
     * @return le type de flux
     */
    public String getTypeFlux() {
        return typeFlux;
    }

    /**
     * @param string
     */
    public void setTypeFlux(String string) {
        typeFlux = string;
    }

    /**
     * @return <code>true</code> si le flux est externe
     */
    public boolean isExternal() {
        return isExternal;
    }

    /**
     * @return <code>true</code> si le flux est journalisable
     */
    public boolean isLoggable() {
        return isLoggable;
    }

    /**
     * @return le type de date logique
     */
    public String getTypeDateLogique() {
        return typeDateLogique;
    }

    /**
     * @param b
     */
    public void setExternal(boolean b) {
        isExternal = b;
    }

    /**
     * @param b
     */
    public void setLoggable(boolean b) {
        isLoggable = b;
    }

    /**
     * @param string
     */
    public void setTypeDateLogique(String string) {
        typeDateLogique = string;
    }

    /**
     * @return le destinataire du message
     */
    public String getDestinataire() {
        return destinataire;
    }

    /**
     * @return l'objet (sujet) du message
     */
    public String getObjet() {
        return objet;
    }

    /**
     * @return le type de service
     */
    public String getTypeService() {
        return typeService;
    }

    /**
     * @param string
     */
    public void setDestinataire(String string) {
        destinataire = string;
    }

    /**
     * @param string
     */
    public void setObjet(String string) {
        objet = string;
    }

    /**
     * @param string
     */
    public void setTypeService(String string) {
        typeService = string;
    }

    /**
     * @return la valeur du champ libre
     */
    public String getChampLibre() {
        return champLibre;
    }

    /**
     * @param string
     */
    public void setChampLibre(String string) {
        champLibre = string;
    }
    
	/**
	 * @return la valeur du statut
	 */
	public String getStatut() {
		return statut;
	}

	/**
	 * @param string
	 */
	public void setStatut(String string) {
		statut = string;
	}   
	 
	/**
	 * @return
	 */
	public Destination getDestinationStatut() {
		return destinationStatut;
	}

	/**
	 * @param destination
	 */
	public void setDestinationStatut(Destination destination) {
		destinationStatut = destination;
	}

}
