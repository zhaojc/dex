package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.ChangementMotPasseHtmlForm;

/**
 * Permet le changement d'un mot de passe dans ClearTrust. Le changement
 * est effectué à partir de la page ct-change.jsp qui apparaît après
 * l'authentification lorsque le mot de passe est expiré.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/20 22:06:56 $
 * @see com.lotoquebec.cardex.presentation.model.AdresseHtmlForm
 */
public class ChangementMotPasseForm extends ValidatorForm implements ChangementMotPasseHtmlForm, Serializable {

    private String       code = "";
    private String       ancienMotPasse = "";
    private String       nouveauMotPasse = "";
    private String       confirmation = "";
	private String       message = "";

	/**
	 * Constructeur de ChangementMotPasseForm par défaut.
	 */
	public ChangementMotPasseForm() {}

	/**
	 * @return
	 */
	public String getAncienMotPasse() {
		return ancienMotPasse;
	}

	/**
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getConfirmation() {
		return confirmation;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return
	 */
	public String getNouveauMotPasse() {
		return nouveauMotPasse;
	}

	/**
	 * @param string
	 */
	public void setAncienMotPasse(String ancienMotPasse) {
		this.ancienMotPasse = ancienMotPasse;
	}

	/**
	 * @param string
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param string
	 */
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	/**
	 * @param string
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param string
	 */
	public void setNouveauMotPasse(String nouveauMotPasse) {
		this.nouveauMotPasse = nouveauMotPasse;
	}

}