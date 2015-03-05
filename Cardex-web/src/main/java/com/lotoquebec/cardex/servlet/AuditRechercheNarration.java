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
 * Ce servlet sert à effectuer une saugevarde automatique dans la table AUD_RECHERCHE_NARRATION.
 * Lorsqu'un utilisateur effectue une recherche de narrations, il a la possibilité de consulter
 * les narrations dans les résultats de recherche sans ouvrir le dossier correspondant. Ce faisant, 
 * aucune trace de cette consultation n'est enregistrée, contrairement à l'audit des accès qui
 * est effectué lors de l'ouverture du dossier. 
 * Ce servlet comble cette lacune en inscrivant une trace dans l'audit des recherches de narrations. 
 * L'enregistrement est effectué automatiquement chaque fois que l'utilisateur clique sur le champ 
 * de la narration dans les résultats de recherche. 
 * @date : juillet 2013
 */
public class AuditRechercheNarration extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        StringBuffer sb = new StringBuffer();
	try {
		AuthenticationSubject sujet = (AuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
		
		CardexAuthenticationSubject subject = (CardexAuthenticationSubject)sujet;
        NarrationDAO delegate = new NarrationDAO();
		//Récupération des paramètres
        long cle = Long.valueOf((String)request.getParameter("CLE"));
        long site = Long.valueOf((String)request.getParameter("SITE"));
        String dossier = (String)request.getParameter("DOSSIER");
        delegate.ajoutAuditRechercheNarration(subject, cle, site, dossier);

	    destroy();
        } catch (DAOException se) {
            System.out.println(se);
        }
         
    }

}