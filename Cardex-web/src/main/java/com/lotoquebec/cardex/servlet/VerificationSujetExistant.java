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
 * Ce servlet sert à vérifier si le sujet est déjà créer
 * (champs V_SU_NOM, V_SU_PRENOM, D_SU_DATE_NAISSANCE de la table SU_SUJET)
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : juin 2008
 * 2012 : les améliorations suivantes sont demandées : 
 * 	- Recherche dans toutes les entités
 *  - Nettoyer les critères de recherche (retrait des caractères accentués, des espaces blancs, etc.)
 *  - Afficher le site et la date de création dans le message d'avertissement
 */
public class VerificationSujetExistant extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		//Récupération des paramètres
		String nom = (String) request.getParameter("NOM");
		String prenom = (String) request.getParameter("PRENOM");
		String dateNaissance = (String) request.getParameter("DATE_NAISSANCE");
		Connection connection = null;

		DateValidation dateValidation = new DateValidation( dateNaissance );
		// La date n'est pas une date valide
		if (dateValidation.valider() == false){
			ecrireReponse(response, new ArrayList());
			return;
		}
		
		try {
			connection = DAOConnection.getInstance().getConnection();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		try {
			CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
			
	    	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
			
			List liste = obtenirListeNumeroFiche(nom, prenom, dateNaissance, subject);
	    	
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

	private List obtenirListeNumeroFiche(final String nom, final String prenom, final String dateNaissanceStr, final CardexAuthenticationSubject subject) throws DAOException {
		final List liste = new ArrayList();
		
		
		try {
			final Date dateNaissance = new Date(DateFormat.parse(dateNaissanceStr).getTime());
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		   
			PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			   public void preparer(CallableStatement callableStatement) throws SQLException {
				   callableStatement.setString(1, prenom.toUpperCase());
				   callableStatement.setString(2, nom.toUpperCase());
				   callableStatement.setDate(3, dateNaissance);
				   callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			   }
			};
			storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_SU_SUJET_EXISTANT", 4, 4, preparerCallableStatement);
		   
			RowCallbackHandler rcbh = new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException {
					liste.add(rs.getString("V_SU_REFERENCE_3") + " (créé le : "+ StringUtils.substring(rs.getString("D_SU_DATE_CREATION"),0,19) + ") ");
				}
	    	};		
			storeProcTemplate.query(rcbh);		   
		   
	   } catch (DAOException e) {
		   ExceptionHandler.getInstance().handle(e);
	   } catch (ParseException e) {
		   ExceptionHandler.getInstance().handle(e);
	   } 		
		
		
	   return liste;
	}



	private void ecrireReponse(HttpServletResponse response, Collection listeNumeroFiche) throws IOException {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer numeroFiche = new StringBuffer("");
		Iterator iter = listeNumeroFiche.iterator();
		
		while (iter.hasNext()) {
			String numeroFicheIter = (String) iter.next();
			
			if (StringUtils.isNotEmpty(numeroFiche.toString()))
				numeroFiche.append(",\r\n"); //Nouvelle ligne
			numeroFiche.append(numeroFicheIter);
		}
		
		response.getWriter().write( "<NumeroFiche>"+numeroFiche+"</NumeroFiche>" );
	}
	
}