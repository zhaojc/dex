package com.lotoquebec.cardex.servlet;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import com.lotoquebec.cardexCommun.GlobalConstants;


/**
 * Cette classe est utilis� pour lancer une classe en r�flexion.
 * La classe lanc� est la classe qui pr�pare le rapport jasper.
 */
public class RapportAffichageExcel extends RapportAffichagePDF {

	private static final long serialVersionUID = 7411182369215382967L;
/*
	protected void assignerServletOutput(HttpServletRequest request, HttpServletResponse  response, JasperPrint print) throws IOException, JRException{
		JExcelApiExporter exporterXLS = new JExcelApiExporter ();

		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,Boolean.FALSE);

		// This'll allows users to directly download the XLS report without
		// having to save XLS report on server.
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());

		// This'll trigger the browser to use excel application to deal with
		// downloaded file.
		response.setContentType( GlobalConstants.TypeSortieServlet.EXCEL );

		exporterXLS.exportReport();
	}*/

	@Override
	protected void setContentType(HttpServletResponse response) {
		response.setContentType(GlobalConstants.TypeSortieServlet.EXCEL);
	}
	
	@Override
	protected JRAbstractExporter obtenirJRExporter() {
		JRXlsExporter exporter = new JRXlsExporter();
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(true);
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		exporter.setConfiguration(configuration);
		
		return exporter;
	}
	
}