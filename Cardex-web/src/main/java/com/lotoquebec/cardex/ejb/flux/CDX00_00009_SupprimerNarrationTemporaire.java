package com.lotoquebec.cardex.ejb.flux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * Supprimer les narrations temporaires qui existent depuis plus de 7 jours.
 * @author levassc
 *
 */
public class CDX00_00009_SupprimerNarrationTemporaire implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00009_SupprimerNarrationTemporaire.class);
	
	public void execute() throws Exception {
		log.info("Entr�e flux CDX00_00009_SupprimerNarrationTemporaire");
		
		try{
			CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();

			FabriqueCardexDAO.getInstance().getNarrationDAO().supprimerNarrationTemporaireDePlusDe7Jours(subject);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erreur dans le diff�r� CDX00_00009_SupprimerNarrationTemporaire");
        	throw new Exception(e);
		} 
		log.info("Fin flux CDX00_00009_SupprimerNarrationTemporaire");
	}





}
