package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Ce servlet sert à rafraîchir des listes déroulantes sans qu'il soit nécessaire
 * de recharger la page, ce qui offre une meilleure performance et un meilleur confort visuel.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : janvier 2006
 */
public class RafraichirListe extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
	    //String action = request.getParameter("action");
        //String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();
        //Récupération des paramètres
        String liste  = (String)request.getParameter("LISTE");
        //String sLangue = (String)request.getParameter("LANGUE");
        String sCle   = (String)request.getParameter("CLE");
        long cle = Long.parseLong(sCle);
        long langue = 0;

        CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
        String sLangue = subject.getLocale().getLanguage();
        
        if(!liste.equals("groupesIntervenants")){
        	langue = GlobalConstants.Langues.FRANCAIS;
        }
        if(sLangue.equals("fr") == false){
        	langue = GlobalConstants.Langues.ANGLAIS;
        }
    	GestionnaireSecurite.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
    	
  		CallableStatement callableStatement = null;
  		ResultSet resultSet = null;
		Statement commandeSql = null;
		Connection connection = null;
		try {
			connection = DAOConnection.getInstance().getConnection();
		} catch (DAOException e) {
            e.printStackTrace();
		}
		try {
         commandeSql = connection.createStatement();
         //On interroge la base de données pour ramener le contenu de la liste déroulante filtrée.
		 //Rafraîchissement des catégories selon le type
		 if(liste.equals("categorie")){	 
			 resultSet = listeCategorie(commandeSql, cle, langue);
		 } 
		 //Rafraîchissement des types selon la nature
		 if(liste.equals("type")){	 
			 resultSet = listeType(commandeSql, cle, langue);
		 } 
		 //Rafraîchissement des types et des catégories selon la nature
		 if(liste.equals("typeCategorie")){	 
			 resultSet = listeTypeCategorie(commandeSql, cle, langue);
		 } 
		 //Rafraîchissement des natures selon le genre
		 if(liste.equals("nature")){	 
			 resultSet = listeNature(commandeSql, cle, langue);
		 } 
		 
		 //Rafraîchissement des provinces selon le pays
		 if(liste.equals("province")){	 
			 resultSet = listeProvince(commandeSql, cle, langue);
		 } 
		 //Rafraîchissement des villes selon la province
		 if(liste.equals("ville")){	 
			 resultSet = listeVille(commandeSql, cle, langue);
		 } 

		 //Rafraîchissement des modèles selon la marque
		 if(liste.equals("modele")){	 
			 resultSet = listeModele(commandeSql, cle, langue);
		 } 

		 //Rafraîchissement des groupes et des intervenants selon le site
		 //Pour cette liste, cle correspond au site et langue à statut.
		 if(liste.equals("groupesIntervenants")){	 
			 resultSet = listeGroupesIntervenants(commandeSql, cle, sLangue);
		 } 

         if(resultSet != null){
	         while (resultSet.next()) {
	            String texte = resultSet.getString("DESCRIPTION");
	            String valeur = resultSet.getString("CLE");
	        	sb.append("<liste>");
		        sb.append("<valeur>" + valeur + "</valeur>");
		        sb.append("<texte>" + texte + "</texte>");
		        sb.append("</liste>");
			}
		}
		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<listes>" + sb.toString() + "</listes>");
//System.out.println(sb.toString());
	    destroy();
	  } catch (IOException e) {
			e.printStackTrace();
      } catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
      } finally {
         if (callableStatement != null){
            try{
              callableStatement.close();
            }catch (SQLException se) {
			System.out.println(se.getMessage());
            }
         }
         if (resultSet != null){
            try{
              resultSet.close();
            }catch (SQLException se) {
			System.out.println(se.getMessage());
            }
         }
         if (connection != null){
            try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
                connection.close();
            }catch (SQLException se) {
			System.out.println(se.getMessage());
            }
         }
      }
         
//System.out.print("Sujet = " + sujet.getLocale());
    }

   private ResultSet listeCategorie(Statement commandeSql, long cle, long langue){
      //Affichage des catégories filtrées par type
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT P.I_CA_CLE as \"CLE\", T.V_TR_DESCRIPTION as \"DESCRIPTION\" from CA_CATEGORIE P, TR_TRADUCTION T ");
	         query.append(" where P.I_TY_CLE = " + cle );
	         query.append(" and P.I_CA_CLE = T.L_TR_CLE " );
	         query.append(" and T.I_LA_CLE = " + langue );
	         query.append(" ORDER BY T.V_TR_DESCRIPTION " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }

   private ResultSet listeProvince(Statement commandeSql, long cle, long langue){
      //Affichage des provinces filtrées par pays
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT P.L_PR_CLE as \"CLE\", T.V_TR_DESCRIPTION as \"DESCRIPTION\" from PR_PROVINCE P, TR_TRADUCTION T ");
	         query.append(" where P.I_PA_CLE = " + cle );
	         query.append(" and P.L_PR_CLE = T.L_TR_CLE " );
	         query.append(" and T.I_LA_CLE = " + langue );
	         query.append(" ORDER BY T.V_TR_DESCRIPTION " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }

   private ResultSet listeVille(Statement commandeSql, long cle, long langue){
      //Affichage des villes filtrées par province
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT P.L_VI_CLE as \"CLE\", P.V_VI_VILLE as \"DESCRIPTION\" from VI_VILLE P ");
	         query.append(" where P.L_PR_CLE = " + cle );
	         //L'ordre de tri ne tient pas compte des accents et des traits d'union
	         query.append(" ORDER BY replace(convert(upper(v_vi_ville),'US7ASCII'),'-','a') " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }
   private ResultSet listeType(Statement commandeSql, long cle, long langue){
   	//Affichage des types filtrés par nature
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT P.I_TY_CLE as \"CLE\", T.V_TR_DESCRIPTION as \"DESCRIPTION\" from TY_TYPE P, TR_TRADUCTION T ");
	         query.append(" where P.I_NA_CLE = " + cle );
	         query.append(" and P.I_TY_CLE = T.L_TR_CLE " );
	         query.append(" and T.I_LA_CLE = " + langue );
	         query.append(" ORDER BY T.V_TR_DESCRIPTION " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }

   private ResultSet listeTypeCategorie(Statement commandeSql, long cle, long langue){
   	//Affichage des types filtrés par nature
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT C.I_CA_CLE||'/'||C.I_TY_CLE as \"CLE\", TC.V_TR_DESCRIPTION||'-'||TT.V_TR_DESCRIPTION as \"DESCRIPTION\" ");
		     query.append(" from TY_TYPE P, CA_CATEGORIE C, TR_TRADUCTION TT, TR_TRADUCTION TC ");
	         query.append(" where P.I_NA_CLE = " + cle );
	         query.append(" and P.I_TY_CLE = TT.L_TR_CLE " );
	         query.append(" and TT.I_LA_CLE = " + langue );
	         query.append(" and C.I_TY_CLE = P.I_TY_CLE " );
	         query.append(" and C.I_CA_CLE = TC.L_TR_CLE " );
	         query.append(" and TC.I_LA_CLE = " + langue );
	         query.append(" order by TC.V_TR_DESCRIPTION, TT.V_TR_DESCRIPTION " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }
   private ResultSet listeNature(Statement commandeSql, long cle, long langue){
   	//Affichage des natures filtrées par genre
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT P.I_NA_CLE as \"CLE\", T.V_TR_DESCRIPTION as \"DESCRIPTION\" from NA_NATURE P, TR_TRADUCTION T ");
	         query.append(" where P.I_GE_CLE = " + cle );
	         query.append(" and P.I_NA_CLE = T.L_TR_CLE " );
	         query.append(" and T.I_LA_CLE = " + langue );
	         query.append(" ORDER BY T.V_TR_DESCRIPTION " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }

   private ResultSet listeModele(Statement commandeSql, long cle, long langue){
   	//Affichage des modèles de véhicule filtrés par marque
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("SELECT P.I_MD_CLE as \"CLE\", P.V_MD_MODELE as \"DESCRIPTION\" from MD_MODELE P ");
	         query.append(" where P.I_MA_CLE = " + cle );
	         query.append(" ORDER BY P.V_MD_MODELE " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }

   private ResultSet listeGroupesIntervenants(Statement commandeSql, long site, String statut){
   	//Affichage des goupes de sécurité et des intervenants filtrés par site et par statut
   	//On n'affiche pas les membres du groupe Admin.
   	//Dans la recherche, on tient compte de tous les intervenants; dans les dossiers, seulement les actifs.
	         StringBuffer query = new StringBuffer(10000);
	 		 ResultSet resultSet = null;
	  try{
		     query.append("select sg.user_name as \"CLE\", s.description||' - '||i.v_in_nom||', '||i.v_in_prenom as \"DESCRIPTION\" ");
		     query.append(" from in_intervenant i ");
	         query.append(" where I.l_in_secteur <> "+GlobalConstants.Secteur.Admin );
	         query.append(" and I.L_SI_CLE = " + site );
	         if(statut.equals("OUI")){
	         	query.append(" and i.i_st_cle = " + GlobalConstants.Statut.INTERVENANT_ACTIF );
	         }
	         query.append(" order by s.description, i.v_in_nom, i.v_in_prenom " );
	         resultSet = commandeSql.executeQuery(query.toString());
	    }catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
	  }
	 return resultSet;
  
   }
   
}