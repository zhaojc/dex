package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.StringHelper;
import com.lotoquebec.cardexCommun.util.StringUtils;


public class AdresseBusinessRuleSet implements BusinessRuleSet {

  /**
   * L'instance du gestionnaire de journalisation.
   */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

  private Pattern codePostalCanadaPattern = null;
  
  /**
   * Construit une instance de DossierBusinessRuleSet
   */
  public AdresseBusinessRuleSet() {
  	codePostalCanadaPattern = Pattern.compile("[A-Z][0-9][A-Z][0-9][A-Z][0-9]");
  }

    /**
     * Valide les règles d'affaires applicable
     * aux adresses.
     *
     * @param businessObject Les critères de recherche
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet critère de recherche dossier ne sont pas respectées.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
  	public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
	  	log.fine("checkRules()");
	        
		if (businessObject instanceof Adresse) {
			Adresse adresse = (Adresse) businessObject;
			if(!adresse.isIndicateurRdd()){ //On convertit sauf s'il s'agit d'une adresse de RDD (réseau des détaillants).
				conversionCodePostal( adresse );
			}
		}else {
		  throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"+CriteresRechercheDossier.class.getName()+"'");
		}
  	}

	/**
	 * Conversion automatique du code postal en caractères majuscules avec 
	 * élimination des tous les caractères autres que chiffres, lettres et 
	 * espace blanc au milieu (fonction trim() pour les autres espaces au début ou à la fin).
	 * @param adresse
	 */
	private void conversionCodePostal(Adresse adresse) {
		String codePostal = adresse.getCodePostal();
		
		if (StringUtils.isNotEmpty( codePostal )){
			codePostal = codePostal.toUpperCase(); // majuscules
			codePostal = StringUtils.replace(codePostal, " ", ""); // retirer les espaces
			codePostal = StringHelper.stripNonAlphanumeric( codePostal ); // plus de caractère de contrôle.
			
			// Est-ce que c'est un codePostal Canadien
			Matcher m = codePostalCanadaPattern.matcher(codePostal);

			if (m.matches()){
				codePostal = StringUtils.left(codePostal, 3) + " " + StringUtils.right(codePostal, 3);
			}
			
			adresse.setCodePostal( codePostal );
		}
	}





}
