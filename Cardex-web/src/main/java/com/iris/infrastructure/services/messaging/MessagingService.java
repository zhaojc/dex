package com.iris.infrastructure.services.messaging;

import javax.jms.Destination;


/**
 * L'interface du service de Message externe JMS
 *
 * @author Jerome Caron
 *@author Marco Montestruque
 */
public interface MessagingService {
    public final static String INITIAL_CONTEXT_FACTORY = "INITIAL_CONTEXT_FACTORY";
    public final static String FLUX_TAG = "FLUX";
    public final static String TYPE_FLUX_DIFFUSION = "DIFFUSION";
    public final static String TYPE_FLUX_COMMUNICATION = "COMMUNICATION";
    public final static String TYPE_FLUX_DIFFERE = "DIFFERE";
	public final static String TYPE_FLUX_STATUT = "STATUT";
    public final static String SPECIFIC_FLUX_TAG = "Specific-flux";
    public final static String FLUX_TYPE = "type";
    public final static String FLUX_NAME = "name";
    public final static String FLUX_EXTERNE = "externe";
    public final static String FLUX_LOGGING = "journalisable";
    public final static String TRIGGER_PATH = "path";
    public final static String OPERATION_SIMULATION = "SIMULATION";
	public final static String OPERATION_RESTRUCTURATION = "RDD_36010";
    public final static String OPERATION_FERMETURE = "GFC_00101";
    public final static String OPERATION_OUVERTURE = "GFC_00102";
    public final static String OPERATION_AV_DATE_ADMIN = "GFC_00039";
    public final static String OPERATION_AV_DATE_DIFF = "GFC_00040";
    public final static String OPERATION_MIS_A_JOUR_PERMISSION = "MISE_A_JOUR_PERMISSIONS";

	public final static String STATUT_ERREUR_WAS = "ME010050";
	public final static String STATUT_RECU_WAS = "MI010042";
	public final static String STATUT_COMPLETE_WAS = "FIN";
	
    /**
     *  <p>Méthode générique qui encapsule la couche de communication
     *  pour l'envoie d'un message JMS.</p>
     *
     * @param MessageEAI
     * @throws Exception
     */
    public void envoyerMessage(MessageEAI msg) throws Exception;

	/**
	 * <p>Envoie un message EAI en tant que message de statut à la destination spécifiée.</p>
	 * 
	 * @param msg le message EAI qui va servir de message de statut
	 * @param destination la destination JMS vers laquelle va être envoyé le statut
	 * 
	 * @throws Exception toute exception pouvant survenir lors de l'envoi du statut
	 */
	public void envoyerStatut(MessageEAI msg, Destination destination)
		throws Exception;        
}
