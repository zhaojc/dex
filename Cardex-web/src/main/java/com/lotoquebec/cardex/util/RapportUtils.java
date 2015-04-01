package com.lotoquebec.cardex.util;

import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

public class RapportUtils {

	public static JasperReport compiler(String nomGabarit) throws JRException{
		return JasperCompileManager.compileReport(RapportsConfiguration.class.getResourceAsStream(nomGabarit));	
	}
}
