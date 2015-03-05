package com.lotoquebec.cardex.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.integration.dao.NarrationDAO;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;

/**
 * Ce servlet sert à effectuer une saugevarde automatique des narrations des dossiers.
 * Cela évite de recharger la page et d'interrompre l'utilisateur dans son travail de rédaction.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML).
 * @date : janvier 2006
 */
public class SauvegardeAuto extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        StringBuffer sb = new StringBuffer();
        NarrationForm narrationForm = new NarrationForm();
		Narration narration = new NarrationVO();
	try {
		AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
		
		GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) sujet, request.getServletPath());
		
		CardexAuthenticationSubject subject = (CardexAuthenticationSubject)sujet;
        NarrationDAO delegate = new NarrationDAO();
		//Récupération des paramètres
        narrationForm.setCle((String)request.getParameter("CLE"));
        narrationForm.setSite((String)request.getParameter("SITE"));
        narrationForm.setLien((String)request.getParameter("LIEN"));
        narrationForm.setLienSite((String)request.getParameter("LIEN_SITE"));
        narrationForm.setApprobateur((String)request.getParameter("APPROBATEUR"));
        narrationForm.setAutoriteApprobateur((String)request.getParameter("AUTORITE_APPROBATEUR"));
        narrationForm.setAutoriteCreateur((String)request.getParameter("AUTORITE_CREATEUR"));
        narrationForm.setAutoriteNarration((String)request.getParameter("AUTORITE_NARRATION"));
        narrationForm.setConfidentialiteApprobateur((String)request.getParameter("CONFIDENTIALITE_APPROBATEUR"));
        narrationForm.setConfidentialiteCreateur((String)request.getParameter("CONFIDENTIALITE_CREATEUR"));
        narrationForm.setConfidentialiteNarration((String)request.getParameter("CONFIDENTIALITE_NARRATION"));
        narrationForm.setMontant((String)request.getParameter("MONTANT"));
        narrationForm.setNarrationAvecFormat((String)request.getParameter("AVEC_FORMAT"));
        narrationForm.setNarrationSansFormat((String)request.getParameter("SANS_FORMAT"));
        narrationForm.setTempsConsacre((String)request.getParameter("TEMPS"));
		String action = (String)request.getParameter("ACTION");
        ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
                    subject.getLocale());
System.out.println(narrationForm.getNarrationAvecFormat());
		String cle  = "";
		String site = "";
		if(action.equals("I")){
			narration = delegate.insert(subject,narration,"DO");
			//Lecture de la clé de la nouvelle narration.
			cle  = Long.toString(narration.getCle());
			site = Long.toString(narration.getSite());
		}else{
			delegate.update(subject,narration,"DO");
			cle  = narrationForm.getCle();
			site = narrationForm.getSite();
		}
    	sb.append("<valeurs>");
        sb.append("<cle>" + cle + "</cle>");
        sb.append("<site>" + site + "</site>");
        sb.append("</valeurs>");

		//On retourne la clé et le site pour les prochaines mises à jour.
        response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("<narration>" + sb.toString() + "</narration>");
	    destroy();
        } catch (DAOException se) {
            System.out.println(se);
        } catch (ValueObjectMapperException vome) {
            System.out.println(vome);
        }
         
    }

}