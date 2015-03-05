package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.DoubleListe;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation des particularités.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.3 $, $Date: 2002/02/21 19:39:11 $
 */
public interface ParticularitesHtmlForm {


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
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caractère.
     */
    public String getSiteDossier();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque();

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caractère.
     */
    public String getModele();

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
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caractère.
     */
    public void setSiteDossier(String siteDossier);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caractère.
     */
    public void setModele(String modele);

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


    public DoubleListe getDoubleListe();

	public void setDoubleListe(DoubleListe doubleListe);
	
	public void initDoubleListe(CardexAuthenticationSubject subject, Collection<String> particularitesChoisis) throws BusinessResourceException;
	
}