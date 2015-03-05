package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet est la base des liste dynamique à base du sujet
 */
public abstract class TableDynamiqueServlet extends HttpServlet {

	protected CardexAuthenticationSubject subject = null;
	
    protected abstract String construireRanger(ListeCache cache, Object o) throws BusinessResourceException;

    protected abstract String construireEntete(MessageResources messageResources);

	protected abstract List obtenirListeForm(String sCle, String sSite) throws BusinessResourceException, BusinessException, ValueObjectMapperException;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        String sCle   = (String)request.getParameter("CLE");
        String sSite = (String)request.getParameter("SITE");
        
        subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
        
        GestionnaireSecurite.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        
        String html = "";
		try {
			html = construireTable(request, sCle, sSite);
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
		}
		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(html);    
    }

    private String construireTable(HttpServletRequest request, String sCle, String sSite) throws BusinessResourceException, BusinessException, ValueObjectMapperException{
    	StringBuffer html = new StringBuffer("");
    	MessageResources messageResources = (MessageResources) request.getSession().getAttribute(Globals.MESSAGES_KEY);
    	ListeCache cache = ListeCache.getInstance();
    	List listForm = obtenirListeForm( sCle, sSite );     
    	
    	html.append("<TABLE width='100%' cellPadding='2' cellSpacing='0' border='1' BGCOLOR='#ffffff' CLASS='tableOutline'>");
    	html.append(construireEntete( messageResources ));
    	
    	Iterator iter = listForm.iterator();
    	
    	while (iter.hasNext()) {
    		Object o = iter.next();
			html.append("<TR>");
			html.append(construireRanger(cache, o));
			html.append("</TR>");
		}
    	html.append("</TABLE>");
    	
    	return html.toString();
    }

	protected String construireTD(String element){
    	String html = "";
		html += "<TD class='listDetailOdd'>";
		
		if (StringUtils.isNotEmpty( element ))
			html += element ;
		html += "</td>";
    	return html;
    }

	protected String construireTDEntete(MessageResources messageResources, String element){
    	String html = "";
    	html += "<TD class='listTableHeader'>";
		
		if (StringUtils.isNotEmpty( element ))
			html += messageResources.getMessage(subject.getLocale(), element) ;
		html += "</td>";
    	return html;
    }


	
}