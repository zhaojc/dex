/*
 * Created on 18-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet;

import java.util.Collection;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSujetVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.validation.Validation;

/**
 * @author levassc
 */
public class SujetNotPresent implements Validation {

	private CardexAuthenticationSubject subject;
	private String nom = "";
	private String prenom = "";
	private String dateNaissance = "";
	
	public SujetNotPresent(CardexAuthenticationSubject subject, String nom, String prenom, String dateNaissance) {
		super();
		this.subject = subject;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#valider(org.apache.struts.action.ActionErrors)
	 */
	public boolean valider(ActionMessages actionErrors) {
		
		try {
			SujetBusinessDelegate delegate = new SujetBusinessDelegate();
			CriteresRechercheSujetVO criteresRechercheSujet = new CriteresRechercheSujetVO();
			
			// Nom et Prénom obligatoire
			if (StringUtils.isNotEmpty( nom ) && StringUtils.isNotEmpty( prenom )){
				DateValidation dateValidation = new DateValidation(dateNaissance);
				
				if (dateValidation.valider()){
					criteresRechercheSujet.setNom( nom );
					criteresRechercheSujet.setPrenomPhonetique( prenom );
					criteresRechercheSujet.setDateNaissance( dateNaissance );
		
			        // Exécution de la recherche via le service d'affaire(BusinessDelegate)
			        Collection results = delegate.select(subject,criteresRechercheSujet);
		
			        if (results.size() == 0)
			        	return true;
			        
			        if (results.size() > 0){
			    		String param = prenom + " " + nom;
			    		actionErrors.add(Globals.ERROR_KEY, new ActionMessage("cardex_sujet_deja_existant", param));
			        }
				}
			}
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			throw new AssertionError("Problème (BusinessResourceException) dans SujetNotPresent");
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new AssertionError("Problème (BusinessException) dans SujetNotPresent");
		}
        
		return false;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.controller.util.validation.Validation#add(com.lotoquebec.cardex.presentation.controller.util.validation.Validation)
	 */
	public void add(Validation validation) {
	}

}
