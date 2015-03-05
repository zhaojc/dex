package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.DoubleListe;


/**
 * Permet de transiter les informations relatives à la consultation d'une
 * évaluation du comité de vigilance de la couche présentation à la couche d'affaire.
 *
 * @author $Author: guerinf $
 * @version $Revision: 1.4 $, $Date: 2012/05/28 17:28:36 $
 */
public interface EvaluationHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne la date de début.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateDebutEval();

    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateFinEval();

    /**
     * Retourne le numéro de client BIJOU.
     *
     * @return String Valeur du numéro de client en caractère.
     */
    public String getNumeroClientBijou();

    /**
     * Retourne les faits connus.
     *
     * @return String Valeur des faits connus en caractère.
     */
    public String getFaitsConnus();

    /**
     * Retourne la proximité.
     *
     * @return String Valeur de la proximité en caractère.
     */
    public String getProximite();

    /**
     * Retourne la gradation.
     *
     * @return String Valeur de la gradation en caractère.
     */
    public String getGradation();

    /**
     * Retourne la transaction.
     *
     * @return String Valeur de la transaction en caractère.
     */
    public String getTransaction();

    /**
     * Retourne le commentaire sur les propos du client.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentairePropos();

    /**
     * Retourne le commentaire sur l'état du client.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaireEtat();

    /**
     * Retourne les autres commentaires du client.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaireAutre();

    /**
     * Retourne le commentaire sur les signe non verbaux du client.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaireSigne();

    /**
     * Retourne le signataire 1.
     *
     * @return String Valeur du signataire en caractère.
     */
    public String getSignataire1();

    /**
     * Retourne le signataire 2.
     *
     * @return String Valeur du signataire en caractère.
     */
    public String getSignataire2();

    /**
     * Retourne le signataire 3.
     *
     * @return String Valeur du signataire en caractère.
     */
    public String getSignataire3();

    /**
     * Retourne le signataire 4.
     *
     * @return String Valeur du signataire en caractère.
     */
    public String getSignataire4();

    /**
     * Retourne le signataire 5.
     *
     * @return String Valeur du signataire en caractère.
     */
    public String getSignataire5();

    /**
     * Retourne la date d'évaluation.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateEvaluation();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte un commentaire autre.
     *
     * @param commentaireAutre Valeur du commentaire en caractère.
     */
    public void setCommentaireAutre(String commentaireAutre);

    /**
     * Affecte un commentaire sur l'état.
     *
     * @param commentaireEtat Valeur du commentaire en caractère.
     */
    public void setCommentaireEtat(String commentaireEtat);

    /**
     * Affecte un commentaire sur les propos.
     *
     * @param commentairePropos Valeur du commentaire en caractère.
     */
    public void setCommentairePropos(String commentairePropos);

    /**
     * Affecte un commentaire sur les signes non verbaux.
     *
     * @param commentaireSigne Valeur du commentaire en caractère.
     */
    public void setCommentaireSigne(String commentaireSigne);

    /**
     * Affecte une date de début d'évaluation.
     *
     * @param dateDebutEval Valeur de la date en caractère.
     */
    public void setDateDebutEval(String dateDebutEval);

    /**
     * Affecte une date de fin d'évaluation.
     *
     * @param dateFinEval Valeur de la date en caractère.
     */
    public void setDateFinEval(String dateFinEval);

    /**
     * Affecte la date d'évaluation en caractère.
     *
     * @param dateEvaluation Valeur de la date d'évaluation. en caractère.
     */
    public void setDateEvaluation(String dateEvaluation);

    /**
     * Affecte les faits connus.
     *
     * @param faitsConnus Valeur des faits en caractère.
     */
    public void setFaitsConnus(String faitsConnus);

    /**
     * Affecte une gradation.
     *
     * @param gradation Valeur de la gradation en caractère.
     */
    public void setGradation(String gradation);

    /**
     * Affecte le numéro de client BIJOU.
     *
     * @param numeroClientBijou Valeur du numéro en caractère.
     */
    public void setNumeroClientBijou(String numeroClientBijou);

    /**
     * Affecte la proximité.
     *
     * @param proximite Valeur de la proximité en caractère.
     */
    public void setProximite(String proximite);

    /**
     * Affecte le signataire1.
     *
     * @param signataire1 Valeur du signataire en caractère.
     */
    public void setSignataire1(String signataire1);

    /**
     * Affecte le signataire2.
     *
     * @param signataire2 Valeur du signataire en caractère.
     */
    public void setSignataire2(String signataire2);

    /**
     * Affecte le signataire3.
     *
     * @param signataire3 Valeur du signataire en caractère.
     */
    public void setSignataire3(String signataire3);

    /**
     * Affecte le signataire4.
     *
     * @param signataire4 Valeur du signataire en caractère.
     */
    public void setSignataire4(String signataire4);

    /**
     * Affecte le signataire5.
     *
     * @param signataire5 Valeur du signataire en caractère.
     */
    public void setSignataire5(String signataire5);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);
    
    /**
     * Affecte la transaction.
     *
     * @param transaction Valeur de la transaction en caractère.
     */
    public void setTransaction(String transaction);

    public DoubleListe getDoubleListeEtat();

	public void setDoubleListeEtat(DoubleListe doubleListeEtat);

	public void initDoubleListeEtat(CardexAuthenticationSubject subject, Collection<String> etatsChoisis) throws BusinessResourceException;
    
    public DoubleListe getDoubleListePropos();

	public void setDoubleListePropos(DoubleListe doubleListePropos);

	public void initDoubleListePropos(CardexAuthenticationSubject subject, Collection<String> proposChoisis) throws BusinessResourceException;

}