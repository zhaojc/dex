package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet.DateValidation;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert à retourner le sigle du site applicable d'un dossier d'investigation.
 * Avec ce sigle, une comparaison est effectuée pour s'assurer que le sigle inscrit
 * dans le numéro de dossier correspond au sigle du site applicable.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : août 2012
 *
 */
public class RechercheSigle extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//Récupération des paramètres
		String cle = (String) request.getParameter("CLE");
		String site = (String) request.getParameter("SITE");
		Connection connection = null;

		try {
			connection = DAOConnection.getInstance().getConnection();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
			
			List liste = obtenirSigle(cle, site, subject);
			Iterator iter = liste.iterator();
			String sigle = "";
			if(iter.hasNext()) { //On ne prend que la première valeur retournée. En principe, il ne devrait y en avoir qu'une seule.
				sigle = (String) iter.next();
			}
			//On encode la réponse pour supporter les charactères français
			ecrireReponse(response, sigle);

			destroy();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					if (!connection.getAutoCommit()) {
						connection.rollback();
					}
					connection.close();
				} catch (SQLException se) {
					System.out.println(se.getMessage());
				}
			}
		}
	}

	private List obtenirSigle(final String cle, final String site, final CardexAuthenticationSubject subject) throws DAOException {
	    final List liste = new ArrayList();
		
		try {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
			PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			   public void preparer(CallableStatement callableStatement) throws SQLException {
				   callableStatement.setLong(1, Long.valueOf(cle));
				   callableStatement.setLong(2, Long.valueOf(site));
				   callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			   }
			};
			storeProcTemplate.prepareCall("Cardex_Special.SP_L_RECHERCHE_SIGLE", 3, 3, preparerCallableStatement);
		   
			RowCallbackHandler rcbh = new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException {
					liste.add(rs.getString("V_SI_ABREVIATION"));
				}
	    	};		
			storeProcTemplate.query(rcbh);		   
		   
	   } catch (DAOException e) {
		   ExceptionHandler.getInstance().handle(e);
	   } 		
		
		
	   return liste;
	}



	private void ecrireReponse(HttpServletResponse response, String sigle) throws IOException {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write( "<Sigle>"+sigle+"</Sigle>" );
	}
	
}