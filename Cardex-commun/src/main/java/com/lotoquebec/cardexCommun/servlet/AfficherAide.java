package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ExceptionHandler;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert à retrouver l'aide associée à un élément sélectionné à l'écran. 
 * Les éléments considérés sont les champs texte, les listes déroulantes et les valeurs 
 * d'une liste déroulante. 
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : novembre 2008
 */
public class AfficherAide extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        StringBuffer sb = new StringBuffer();
		CardexAuthenticationSubject subjet = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
		
		GestionnaireSecurite.validerSecuriteURL(subjet, request.getServletPath());
        
		//Récupération des paramètres
        String valeur  = (String)request.getParameter("VALEUR");
        String sCle   = (String)request.getParameter("CLE");
    	long cle = 0;
    	//S'il s'agit d'une liste déroulante, on vérifie s'il s'agit d'une valeur 
    	//numérique ou texte. 
        if(StringUtils.isNumeric(sCle)){
        	cle = Long.parseLong(sCle);
        }else{
        	if( StringUtils.isNotEmpty(sCle) ){
        		valeur = sCle;
        	}
        }
        int langue = GlobalConstants.Langues.FRANCAIS;;
        /*if(!sujet.getLocale().getLanguage().equals("fr")){
        	langue = GlobalConstants.Langues.ANGLAIS;
        }*/
         //On interroge la base de données pour ramener le contenu l'aide.
         //La recherche diffère si la clé n'égale pas 0.
         if(cle != 0){
			 sb.append( rechercheAideCle(subjet, cle, langue) );
		 }else{
			 sb.append( rechercheAideChamp(subjet, valeur, langue) );
		 }

		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(sb.toString());

	    destroy();

      }
   
   private String rechercheAideCle(CardexAuthenticationSubject subjet, final long cle, final int langue){
	    //Recherche de l'aide pour un champ texte
		   final StringBuilder sortie = new StringBuilder();
		   try {
			   StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subjet);
			   
			   PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
				   public void preparer(CallableStatement callableStatement) throws SQLException {
					   callableStatement.setLong(1, cle);
					   callableStatement.setInt(2, langue);
					   callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
				   }
			   };
			   storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_AIDE_VALEUR", 3, 3, preparerCallableStatement);
			   
			   RowCallbackHandler rcbh = new RowCallbackHandler(){
					public void processRow(ResultSet rs) throws SQLException {
			            String texte = rs.getString("V_AI_AIDE");
			            sortie.append("<P style='background-color:#ffcc99;'><b>Aide en ligne (" + rs.getString("V_AI_CHAMP") + ")</b></P>");
			            sortie.append(texte);
					}
		    	};		
				storeProcTemplate.query(rcbh);		   
			   
		   } catch (DAOException e) {
			   ExceptionHandler.getInstance().handle(e);
		   } 
		   return sortie.toString();
	   }   
  
   private String rechercheAideChamp(CardexAuthenticationSubject subjet, final String valeur, final int langue){
    //Recherche de l'aide pour un champ texte
	   final StringBuilder sortie = new StringBuilder();
	   try {
		   StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subjet);
		   
		   PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			   public void preparer(CallableStatement callableStatement) throws SQLException {
				   callableStatement.setString(1, valeur);
				   callableStatement.setInt(2, langue);
				   callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			   }
		   };
		   storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_AIDE_CHAMP", 3, 3, preparerCallableStatement);
		   
		   RowCallbackHandler rcbh = new RowCallbackHandler(){
				public void processRow(ResultSet rs) throws SQLException {
		            String texte = rs.getString("V_AI_AIDE");
		            sortie.append("<P style='background-color:#ffcc99;'><b>Aide en ligne (" + rs.getString("V_AI_CHAMP") + ")</b></P>");
		            sortie.append(texte);
				}
	    	};		
			storeProcTemplate.query(rcbh);		   
		   
	   } catch (DAOException e) {
		   ExceptionHandler.getInstance().handle(e);
	   } 
	   return sortie.toString();
   }



}