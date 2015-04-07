package com.lotoquebec.cardex.business.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.util.StringHelper;
import com.lotoquebec.cardexCommun.util.StringUtils;


public class AdresseBusinessRuleSet implements BusinessRuleSet {

  /**
   * L'instance du gestionnaire de journalisation.
   */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

  private Pattern codePostalCanadaPattern = null;
  
  /**
   * Construit une instance de DossierBusinessRuleSet
   */
  public AdresseBusinessRuleSet() {
  	codePostalCanadaPattern = Pattern.compile("[A-Z][0-9][A-Z][0-9][A-Z][0-9]");
  }

    /**
     * Valide les r�gles d'affaires applicable
     * aux adresses.
     *
     * @param businessObject Les crit�res de recherche
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet crit�re de recherche dossier ne sont pas respect�es.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
  	public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
	  	log.debug("checkRules()");
	        
		if (businessObject instanceof Adresse) {
			Adresse adresse = (Adresse) businessObject;
			if(!adresse.isIndicateurRdd()){ //On convertit sauf s'il s'agit d'une adresse de RDD (r�seau des d�taillants).
				conversionCodePostal( adresse );
			}
		}else {
		  throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"+CriteresRechercheDossier.class.getName()+"'");
		}
  	}

	/**
	 * Conversion automatique du code postal en caract�res majuscules avec 
	 * �limination des tous les caract�res autres que chiffres, lettres et 
	 * espace blanc au milieu (fonction trim() pour les autres espaces au d�but ou � la fin).
	 * @param adresse
	 */
	private void conversionCodePostal(Adresse adresse) {
		String codePostal = adresse.getCodePostal();
		
		if (StringUtils.isNotEmpty( codePostal )){
			codePostal = codePostal.toUpperCase(); // majuscules
			codePostal = StringUtils.replace(codePostal, " ", ""); // retirer les espaces
			codePostal = StringHelper.stripNonAlphanumeric( codePostal ); // plus de caract�re de contr�le.
			
			// Est-ce que c'est un codePostal Canadien
			Matcher m = codePostalCanadaPattern.matcher(codePostal);

			if (m.matches()){
				codePostal = StringUtils.left(codePostal, 3) + " " + StringUtils.right(codePostal, 3);
			}
			
			adresse.setCodePostal( codePostal );
		}
	}





}
