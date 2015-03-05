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
 * Ce servlet sert à vérifier si le véhicule en ajout existe déjà
 * (champs V_VE_IMMATRICULATION et I_MD_CLE de la table VE_VEHICULE)
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : août 2013
 */
public class VerificationVehiculeExistant extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//Récupération des paramètres
		String immatriculation = (String) request.getParameter("IMMATRICULATION");
		String modele = (String) request.getParameter("MODELE");
		Connection connection = null;

		
		try {
			connection = DAOConnection.getInstance().getConnection();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
			
	    	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
			
			List liste = obtenirListeVehicule(immatriculation, modele, subject);
	    	
			//On encode la réponse pour supporter les charactères français
			ecrireReponse(response, liste);

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

	private List obtenirListeVehicule(final String immatriculation, final String modele, final CardexAuthenticationSubject subject) throws DAOException {
		final List liste = new ArrayList();
		
		
		try {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		   
			PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			   public void preparer(CallableStatement callableStatement) throws SQLException {
				   callableStatement.setString(1, immatriculation);
				   callableStatement.setLong(2, Long.valueOf(modele));
				   callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			   }
			};
			storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_VE_VEHICULE_EXISTANT", 3, 3, preparerCallableStatement);
		   
			RowCallbackHandler rcbh = new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException {
					liste.add(rs.getString("V_VE_IMMATRICULATION") + " et " + rs.getString("V_MD_MODELE") + " (créé le : "+ StringUtils.substring(rs.getString("D_VE_DATE_CREATION"),0,19) + ") ");
				}
	    	};		
			storeProcTemplate.query(rcbh);		   
		   
	   } catch (DAOException e) {
		   ExceptionHandler.getInstance().handle(e);
	   } 		
		
		
	   return liste;
	}



	private void ecrireReponse(HttpServletResponse response, Collection listeVehicule) throws IOException {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer vehicule = new StringBuffer("");
		Iterator iter = listeVehicule.iterator();
		
		while (iter.hasNext()) {
			String numeroVehiculeIter = (String) iter.next();
			
			if (StringUtils.isNotEmpty(vehicule.toString()))
				vehicule.append(",\r\n"); //Nouvelle ligne
			vehicule.append(numeroVehiculeIter);
		}
		
		response.getWriter().write( "<Vehicule>"+vehicule+"</Vehicule>" );
	}
	
}