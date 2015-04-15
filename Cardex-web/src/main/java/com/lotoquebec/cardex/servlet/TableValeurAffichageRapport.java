package com.lotoquebec.cardex.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.RequestUtils;

import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardex.presentation.model.form.rapport.TableValeurRapportAssociation;

/**
 * Cette classe est utilis� pour lancer une classe en r�flexion.
 * La classe lanc� est la classe qui pr�pare le rapport jasper.
 */
public class TableValeurAffichageRapport extends RapportAffichage {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    /**
     * Cette classe permet d'obtenir la classe de rapport et de la populer avec les arguements
     * @param request
     * @return
     */
    protected RapportForm obtenirRapportForm(HttpServletRequest request){
        String choixRapport = (String)request.getParameter("choixRapport");
        
		try {
			RapportForm rapportForm = TableValeurRapportAssociation.obtenirEntiteRapport(Integer.valueOf(choixRapport));
			RequestUtils.populate(rapportForm, request);
			return rapportForm;
			
		} catch (Exception e) {
			throw new RuntimeException("Impossible de cr�er la classe "+choixRapport);
		}
    }
    
}