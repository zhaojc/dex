package com.lotoquebec.cardexCommun.rapport;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;


public class CSVImpressionRapport extends ImpressionRapport{

	class ImprimerCSVThread extends ImprimerThread{

		public ImprimerCSVThread(String nomRapport, JasperPrint print) {
			super(nomRapport, print);
		}

		@Override
		public void runIT(FileOutputStream sortie) throws JRException, IOException {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JRCsvExporter exporterCSV = new JRCsvExporter();
			exporterCSV.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterCSV.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
			exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
			exporterCSV.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
			exporterCSV.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, true);
			exporterCSV.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
			exporterCSV.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ";");		


			exporterCSV.exportReport();
			sortie.write(output.toByteArray());			
		}
	}
	
	@Override
	protected ImprimerThread obtenirNewImprimerThread(String nomRapport,JasperPrint print) {
		return new ImprimerCSVThread(nomRapport, print);
	}
	
}
