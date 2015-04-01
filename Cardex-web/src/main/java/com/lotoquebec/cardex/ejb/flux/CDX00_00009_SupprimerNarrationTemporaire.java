package com.lotoquebec.cardex.ejb.flux;

import java.util.logging.Logger;

import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Supprimer les narrations temporaires qui existent depuis plus de 7 jours.
 * @author levassc
 *
 */
public class CDX00_00009_SupprimerNarrationTemporaire implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00009_SupprimerNarrationTemporaire.class);
	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00009_SupprimerNarrationTemporaire");
		
		try{
			CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();

			FabriqueCardexDAO.getInstance().getNarrationDAO().supprimerNarrationTemporaireDePlusDe7Jours(subject);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.severe("Erreur dans le diff�r� CDX00_00009_SupprimerNarrationTemporaire");
        	throw new Exception(e);
		} 
		log.fine("Fin flux CDX00_00009_SupprimerNarrationTemporaire");
	}





}
