package com.lotoquebec.cardexCommun.rapport;

import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class PDFImpressionRapport extends ImpressionRapport{

	
	class ImprimerPDFThread extends ImprimerThread{

		public ImprimerPDFThread(String nomRapport, JasperPrint print) {
			super(nomRapport, print);
		}

		@Override
		public void runIT(FileOutputStream sortie) throws IOException, JRException {
			JasperExportManager.exportReportToPdfStream(print, sortie);			
		}
	}

	@Override
	protected ImprimerThread obtenirNewImprimerThread(String nomRapport,JasperPrint print) {
		return new ImprimerPDFThread(nomRapport, print);
	}
	
}
