package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.DoubleListe;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulatire de consultation des particularit�s.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.3 $, $Date: 2002/02/21 19:39:11 $
 */
public interface ParticularitesHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caract�re.
     */
    public String getSiteDossier();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque();

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caract�re.
     */
    public String getModele();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caract�re.
     */
    public void setSiteDossier(String siteDossier);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caract�re.
     */
    public void setModele(String modele);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);


    public DoubleListe getDoubleListe();

	public void setDoubleListe(DoubleListe doubleListe);
	
	public void initDoubleListe(CardexAuthenticationSubject subject, Collection<String> particularitesChoisis) throws BusinessResourceException;
	
}