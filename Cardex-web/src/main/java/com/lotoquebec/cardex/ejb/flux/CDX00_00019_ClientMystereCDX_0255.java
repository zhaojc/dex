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
 * 
 * @author 
 */
public class CDX00_00019_ClientMystereCDX_0255 implements Flux {

	private final static Logger log = LoggerFactory.getLogger(CDX00_00019_ClientMystereCDX_0255.class);
	
    public void execute() throws Exception {
        log.info("Entr�e flux CDX00_00019");
        CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
        
		produireRapportCDX_0255(subject);
		
        log.info("Fin flux CDX00_00019");
    }

	private void produireRapportCDX_0255(CardexAuthenticationSubject subject) throws BusinessResourceException, FileNotFoundException {
		log.info("produireRapportCDX_0255");
		String nomRapport = obtenirNomRapportCDX_0255();
		log.info("Choix nom rapport : '"+nomRapport+"'");
		
        JasperPrint print = FabriqueFacade.getRapportSessionFacade().clientMystereRapportCDX_0255(subject);
        (new ExcelImpressionRapport()).impression(nomRapport, print);
	}

    private String obtenirNomRapportCDX_0255(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_CLIENT_MYSTERE);
        String dateRapport = dateFormat.format(new Date());
        return rapportTemporaire+"Rapport sur la v�rification client myst�re ("+dateRapport+") CDX_0255.xls";
    }
    
}
