package com.lotoquebec.cardex.presentation.model;

import java.sql.Timestamp;

/**
 * Permet le changement d'un mot de passe dans ClearTrust. Le changement
 * est effectué à partir de la page ct-change.jsp qui apparaît après
 * l'authentification lorsque le mot de passe est expiré.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/28 17:28:36 $
 */
public interface ChangementMotPasseHtmlForm {


    // Getters

    /**
     * Retourne le code d'utilisateur
     *
     * @return String Valeur du code d'utilisateur
     */

    public String getCode();

    /**
     * Retourne l'ancien mot de passe
     *
     * @return String Valeur de l'ancien mot de passe.
     */
    public String getAncienMotPasse();

    /**
     * Retourne le nouveau mot de passe.
     *
     * @return String Valeur du nouveau mot de passe.
     */
    public String getNouveauMotPasse();

    /**
     * Retourne la confirmation du mot de passe
     *
     * @return String Valeur confirmation du mot de passe.
     */
    public String getConfirmation();

    /**
     * Retourne le message d'erreur si le changement échoue
     *
     * @return String Valeur message.
     */
    public String getMessage();


    // Setters

	/**
	 * Affecte le code d'utilisateur
	 *
	 * @param String Valeur du code d'utilisateur
	 */

	public void setCode(String code);

	/**
	 * Affecte l'ancien mot de passe
	 *
	 * @param String Valeur de l'ancien mot de passe.
	 */
	public void setAncienMotPasse(String ancienMotPasse);

	/**
	 * Affecte le nouveau mot de passe.
	 *
	 * @param String Valeur du nouveau mot de passe.
	 */
	public void setNouveauMotPasse(String nouveauMotPasse);

	/**
	 * Affecte la confirmation du mot de passe
	 *
	 * @param String Valeur confirmation du mot de passe.
	 */
	public void setConfirmation(String confirmation);

	/**
	 * Affecte le message d'erreur si le changement échoue
	 *
	 * @param String Valeur message.
	 */
	public void setMessage(String mesage);

}