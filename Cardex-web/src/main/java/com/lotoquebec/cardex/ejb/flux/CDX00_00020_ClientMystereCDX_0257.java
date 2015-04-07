package com.lotoquebec.cardex.ejb.flux;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.rapport.ExcelImpressionRapport;
import com.lq.std.conf.impl.AppConfig;

/**
 * @author 
 */
public class CDX00_00020_ClientMystereCDX_0257 implements Flux {

	private final static Logger log = LoggerFactory.getLogger(CDX00_00020_ClientMystereCDX_0257.class);
	
    public void execute() throws Exception {
        log.info("Entr�e flux CDX00_00020");
        CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
        
		produireRapportCDX_0257(subject);
		
        log.info("Fin flux CDX00_00020");
    }
    
	private void produireRapportCDX_0257(CardexAuthenticationSubject subject) throws BusinessResourceException, FileNotFoundException {
		log.info("produireRapportCDX_0257");
		JasperPrint[] prints = FabriqueFacade.getRapportSessionFacade().clientMystereRapportCDX_0257(subject);
		
		for (int i=0;i<5;i++){
			String nomRapport = obtenirNomRapportCDX_0257(i);
			log.info("Choix nom rapport : '"+nomRapport+"'");
	        (new ExcelImpressionRapport()).impression(nomRapport, prints[i]);
		}
	}

    private String obtenirNomRapportCDX_0257(int numeroVisite){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_CLIENT_MYSTERE);
        String dateRapport = dateFormat.format(new Date());
        return rapportTemporaire+"Rapport soci�t� en infraction visite "+(numeroVisite+1)+" ("+dateRapport+") CDX_0257.xls";
    }    
    
}
