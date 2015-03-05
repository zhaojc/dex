package com.lotoquebec.cardex.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.GlobalConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;


/**
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 */
public class RapportAffichageExcel extends RapportAffichagePDF {

	@Override
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
	}

}