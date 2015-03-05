package com.lotoquebec.cardexCommun.rapport;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;


public class ExcelImpressionRapport extends ImpressionRapport{

	class ImprimerExcelThread extends ImprimerThread{

		public ImprimerExcelThread(String nomRapport, JasperPrint print) {
			super(nomRapport, print);
		}

		@Override
		public void runIT(FileOutputStream sortie) throws JRException, IOException {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JExcelApiExporter exporterXLS = new JExcelApiExporter();
			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,output);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporterXLS.exportReport();
			sortie.write(output.toByteArray());			
		}
	}
	
	@Override
	protected ImprimerThread obtenirNewImprimerThread(String nomRapport,JasperPrint print) {
		return new ImprimerExcelThread(nomRapport, print);
	}
	
}
