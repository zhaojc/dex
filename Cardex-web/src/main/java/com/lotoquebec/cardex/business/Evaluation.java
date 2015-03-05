package com.lotoquebec.cardex.business;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardexCommun.business.Business;


/**
 * Permet de transiter les informations relatives � la consultation d'une
 * �valuation du comit� de vigilance de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: guerinf $
 * @version $Revision: 1.4 $, $Date: 2012/05/28 17:28:36 $
 */
public interface Evaluation extends Modifiable, Business {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSite();

    /**
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date en caract�re.
     */
    public Timestamp getDateDebutEval();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date en caract�re.
     */
    public Timestamp getDateFinEval();

    /**
     * Retourne le num�ro de client BIJOU.
     *
     * @return long Valeur du num�ro de client.
     */
    public long getNumeroClientBijou();

    /**
     * Retourne les faits connus.
     *
     * @return String Valeur des faits connus en caract�re.
     */
    public String getFaitsConnus();

    /**
     * Retourne la proximit�.
     *
     * @return String Valeur de la proximit� en caract�re.
     */
    public String getProximite();

    /**
     * Retourne la gradation.
     *
     * @return String Valeur de la gradation en caract�re.
     */
    public String getGradation();

    /**
     * Retourne la transaction.
     *
     * @return String Valeur de la transaction en caract�re.
     */
    public String getTransaction();

    /**
     * Retourne le commentaire sur les propos du client.
     *
     * @return String Valeur du commentaire en caract�re.
     */
    public String getCommentairePropos();

    /**
     * Retourne le commentaire sur l'�tat du client.
     *
     * @return String Valeur du commentaire en caract�re.
     */
    public String getCommentaireEtat();

    /**
     * Retourne les autres commentaires du client.
     *
     * @return String Valeur du commentaire en caract�re.
     */
    public String getCommentaireAutre();

    /**
     * Retourne le commentaire sur les signe non verbaux du client.
     *
     * @return String Valeur du commentaire en caract�re.
     */
    public String getCommentaireSigne();

    /**
     * Retourne le signataire 1.
     *
     * @return String Valeur du signataire en caract�re.
     */
    public String getSignataire1();

    /**
     * Retourne le signataire 2.
     *
     * @return String Valeur du signataire en caract�re.
     */
    public String getSignataire2();

    /**
     * Retourne le signataire 3.
     *
     * @return String Valeur du signataire en caract�re.
     */
    public String getSignataire3();

    /**
     * Retourne le signataire 4.
     *
     * @return String Valeur du signataire en caract�re.
     */
    public String getSignataire4();

    /**
     * Retourne le signataire 5.
     *
     * @return String Valeur du signataire en caract�re.
     */
    public String getSignataire5();

    /**
     * Retourne la date d'�valuation.
     *
     * @return Timestamp Valeur de la date en caract�re.
     */
    public Timestamp getDateEvaluation();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date de cr�ation en caract�re.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caract�re.
     */
    public Timestamp getDateModification();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caract�re.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caract�re.
     */
    public long getLienSite();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte un commentaire autre.
     *
     * @param commentaireAutre Valeur du commentaire en caract�re.
     */
    public void setCommentaireAutre(String commentaireAutre);

    /**
     * Affecte un commentaire sur l'�tat.
     *
     * @param commentaireEtat Valeur du commentaire en caract�re.
     */
    public void setCommentaireEtat(String commentaireEtat);

    /**
     * Affecte un commentaire sur les propos.
     *
     * @param commentairePropos Valeur du commentaire en caract�re.
     */
    public void setCommentairePropos(String commentairePropos);

    /**
     * Affecte un commentaire sur les signes non verbaux.
     *
     * @param commentaireSigne Valeur du commentaire en caract�re.
     */
    public void setCommentaireSigne(String commentaireSigne);

    /**
     * Affecte une date de d�but d'�valuation.
     *
     * @param dateDebutEval Valeur de la date en caract�re.
     */
    public void setDateDebutEval(Timestamp dateDebutEval);

    /**
     * Affecte une date de fin d'�valuation.
     *
     * @param dateFinEval Valeur de la date en caract�re.
     */
    public void setDateFinEval(Timestamp dateFinEval);

    /**
     * Affecte la date d'�valuation en caract�re.
     *
     * @param dateEvaluation Valeur de la date d'�valuation. en caract�re.
     */
    public void setDateEvaluation(Timestamp dateEvaluation);

    /**
     * Affecte les faits connus.
     *
     * @param faitsConnus Valeur des faits en caract�re.
     */
    public void setFaitsConnus(String faitsConnus);

    /**
     * Affecte une gradation.
     *
     * @param gradation Valeur de la gradation en caract�re.
     */
    public void setGradation(String gradation);

    /**
     * Affecte le num�ro de client BIJOU.
     *
     * @param numeroClientBijou Valeur du num�ro en caract�re.
     */
    public void setNumeroClientBijou(long numeroClientBijou);

    /**
     * Affecte la proximit�.
     *
     * @param proximite Valeur de la proximit� en caract�re.
     */
    public void setProximite(String proximite);

    /**
     * Affecte le signataire1.
     *
     * @param signataire1 Valeur du signataire en caract�re.
     */
    public void setSignataire1(String signataire1);

    /**
     * Affecte le signataire2.
     *
     * @param signataire2 Valeur du signataire en caract�re.
     */
    public void setSignataire2(String signataire2);

    /**
     * Affecte le signataire3.
     *
     * @param signataire3 Valeur du signataire en caract�re.
     */
    public void setSignataire3(String signataire3);

    /**
     * Affecte le signataire4.
     *
     * @param signataire4 Valeur du signataire en caract�re.
     */
    public void setSignataire4(String signataire4);

    /**
     * Affecte le signataire5.
     *
     * @param signataire5 Valeur du signataire en caract�re.
     */
    public void setSignataire5(String signataire5);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(long lienSite);
	
    /**
     * Affecte la transaction.
     *
     * @param transaction Valeur de la transaction en caract�re.
     */
    public void setTransaction(String transaction);

    /**
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();
   
    /**
     * Ajoute un �tat d'esprit.
     *
     * @param etat Valeur de l'�tat.
     */
    public void addEtat(String etat);

    /**
     * Retourne la liste des �tats choisis.
     *
     * @return Collection Valeur de la liste des �tats choisis.
     */
    public Collection getEtats();

    /**
     * Ajoute un propos du client.
     *
     * @param etat Valeur du propos.
     */
    public void addPropos(String propos);

    /**
     * Retourne la liste des propos choisis.
     *
     * @return Collection Valeur de la liste des propos choisis.
     */
    public Collection getPropos();

    public List<MiseEvaluationVO> getMisesEvaluation();

	public void setMisesEvaluation(List<MiseEvaluationVO> misesEvaluation);
	
}