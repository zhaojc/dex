package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.TypeConsignationStatutCle;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Ce servlet sert à vérifier si une consignation est approuvable (champ B_TN_APPROBATION
 * de la table TN_TYPE_CONSIGNATION) au moment où l'utilisateur choisit un type
 * dans la liste. Si la consignation est approuvable, on vérifie dans la table CN_CONSIGNAITON
 * si le numéro de série a déjà été saisi.
 * Si oui, on vérifie s'il a été approuvé.  Si oui, on reporte les informations 
 * d'approbation dans la consignation en cours.  Si non, on rend visible le message 
 * d'avertissement sur le formulaire à l'effet que la consignation doit être 
 * soumise aux responsables.
 * L'objectif implicite de cette vérification est relié aux faux-billets qui doivent
 * être acheminés au siège social lorsqu'il s'agit d'un nouveau numéro de série.  
 * La vérification permet d'envoyer un rappel à l'utilisateur.
 * Cette opération s'effectue en arrière-plan pendant la saisie de données.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : juin 2006
 */
public class VerificationConsignation extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response) throws IOException, ServletException {
        StringBuffer sb = new StringBuffer();
        sb.append("<liste>");
		String statut = "no";
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
        GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        String sType  = (String)request.getParameter("TYPE");
        long type     = Long.parseLong(sType);
        String sNumeroSerie = (String)request.getParameter("NUMERO_SERIE");
		
		ListeCache listeCache = ListeCache.getInstance(); 
		try {
			statut = listeCache.obtenirLabel(subject, type, new TypeConsignationStatutCle(subject));
		} catch (BusinessResourceException e) {
			ExceptionHandler.getInstance().traiterException(subject, e);
		}
		
		sb.append("<approuvable>" + OracleDAOUtils.convertirStringABoolean(statut) + "</approuvable>");
		
		if( GlobalConstants.SQL.TRUE.equals(statut) ){
			//Si le type de consignation est approuvable, on vérifie le 
			//numéro de série pour savoir s'il a déjà été saisi et approuvé.
			sb.append( constuireConsignationApprouve(subject, type, sNumeroSerie) );
			
		}else{
//System.out.println("approuvable à non");			
			sb.append("<approuve>false</approuve>");
			sb.append("<approbateur></approbateur>");
			sb.append("<date></date>");
		}
		sb.append("</liste>");

		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<listes>" + sb.toString() + "</listes>");
//System.out.println(sb.toString());
	    destroy();

    }

    private String constuireConsignationApprouve(CardexAuthenticationSubject subjet, final long type, final String sNumeroSerie){
	    //Recherche de l'aide pour un champ texte
	   final StringBuilder sortie = new StringBuilder();
	   try {
		   StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subjet);
		   
		   PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			   public void preparer(CallableStatement callableStatement) throws SQLException {
				   callableStatement.setLong(1, type);
				   callableStatement.setString(2, sNumeroSerie);
				   callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			   }
		   };
		   storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_CN_CONSIGNATION_APP", 3, 3, preparerCallableStatement);
		   
		   RowCallbackHandler rcbh = new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException {
					sortie.append("<approuve>" + OracleDAOUtils.convertirStringABoolean(rs.getString("C_CN_APPROUVE")) + "</approuve>");
					sortie.append("<approbateur>" + rs.getString("V_CN_APPROUVE_PAR") + "</approbateur>");
					sortie.append("<date>" + rs.getString("DATE_APPROBATION") + "</date>");
				}
	    	};		
			storeProcTemplate.query(rcbh);		   
		   
			if (sortie.length() == 0){
				sortie.append("<approuve>false</approuve>");
				sortie.append("<approbateur></approbateur>");
				sortie.append("<date></date>");
			}
			
	   } catch (DAOException e) {
		   ExceptionHandler.getInstance().handle(e);
	   } 
	   return sortie.toString();
   }  
    
}