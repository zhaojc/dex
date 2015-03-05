package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.FabriqueDAO;
import com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.CleListeAutoCompleter;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.ListeCacheString;

/**
 * Ce servlet sert à afficher les adresses d'un sujet à partir
 * d'un script ajax 
 */
public class AutoCompleterServlet extends HttpServlet {
	

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
    	String nomChamp = (String)request.getParameter("NOM_CHAMP");
    	String valeur = (String)request.getParameter("VALEUR");
    	String classeControl = (String)request.getParameter("CLASSE_CONTROL");

        CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
        
        GestionnaireSecurite.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        
        String html = "";
		try {
			html = construireTable(subject, request.getSession(), nomChamp, valeur, classeControl);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(html);    
    }
	
	private String construireTable(CardexAuthenticationSubject subject, HttpSession session, String nomChamp, String valeur, String classeControl) throws BusinessResourceException, ClassNotFoundException, InstantiationException, IllegalAccessException, DAOException {
    	StringBuffer html = new StringBuffer("");
    	List listValeur = obtenirListeValeur( subject, session, valeur, classeControl );     

    	if (listValeur.size() == 0)
    		return html.toString();
    	
    	html.append("<table cellPadding='0' cellSpacing='0' onmouseleave='auto();' onmouseover='hand();'>");
    	
    	Iterator iter = listValeur.iterator();
    	
    	while (iter.hasNext()) {
    		String str = (String) iter.next();
    		html.append(construireRanger(nomChamp, str));
		}
    	html.append("</TABLE>");
    	
    	return html.toString();
	}


	private List obtenirListeValeur(CardexAuthenticationSubject subject, HttpSession session, String valeurDepart, String classeControl) throws ClassNotFoundException, InstantiationException, IllegalAccessException, BusinessResourceException, DAOException {
		ListeCacheString listeCacheString = (ListeCacheString) session.getAttribute(GlobalConstants.AutoCompleterClass.LISTE_CACHE_STRING);
		
		if (listeCacheString == null || listeCacheString.isCaratereStartsWith(valeurDepart) == false){
			CleListeAutoCompleter cleListeAutoCompleter = creerCleListeAutoCompleter( classeControl );
			List<String> listeString = FabriqueDAO.getInstance().getItemListDAO().getListeAutoCompleter(subject, valeurDepart, cleListeAutoCompleter);			
			listeCacheString = new ListeCacheString(valeurDepart, listeString);
			session.setAttribute(GlobalConstants.AutoCompleterClass.LISTE_CACHE_STRING,listeCacheString);
			return listeString;
		}else
			return listeCacheString.obtenirListe(valeurDepart);
	}

	public CleListeAutoCompleter creerCleListeAutoCompleter(String classe) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class c = Class.forName( classe );
		return (CleListeAutoCompleter) c.newInstance();
	}	
	
	private StringBuffer construireRanger(String nomChamp, String str) {
		StringBuffer html = new StringBuffer("");
		
		html.append("<tr ");
		html.append("onMouseOver=");
			html.append('"');
			html.append("this.className='listDetailEven'");
			html.append('"');
			html.append(" ");
		html.append("onMouseOut=");
				html.append('"');
				html.append("this.className='listDetailOdd'");
				html.append('"');
				html.append(" ");
		html.append("><td style='white-space:pre' onclick=");
			html.append('"');
			html.append("assignerValeur('");
			html.append(nomChamp);
			html.append("', this);");
			html.append('"');
			html.append("><pre>");
			html.append(str);
			html.append("</pre></td>");
		html.append("</tr>");
		
		return html;
	}

}