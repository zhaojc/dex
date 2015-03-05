package com.lotoquebec.cardex.presentation.model;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulaire de l'onglet Partage.
 *
 * @author $Author: guerinf $
 * @version $Revision: 1.4 $, $Date: 2009/11/02 19:10:18 $
 */
public interface PartageHtmlForm {


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
     * Retourne le nom de l'intervenant qui cr�e le partage.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getIntervenant();

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

    /**
     * Retourne le profil de l'intervenant.
     *
     * @return String Valeur du profil en caract�re.
     */
    public String getProfil();

    /**
     * Retourne le site de l'intervenant.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSiteIntervenant();

    /**
     * Retourne une collection d'intervenants.
     *
     * @return String [] Valeur de la liste de cha�ne de caract�re.
     */
    public String [] getIntervenantsChoisis();



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
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setIntervenant(String nom);

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

    /**
     * Affecte un profil de l'intervenant.
     *
     * @param profil Valeur du profil en caract�re.
     */
    public void setProfil(String profil);

    /**
     * Affecte un site.
     *
     * @param siteIntervenant Valeur du site de l'intervenant en caract�re.
     */
    public void setSiteIntervenant(String siteIntervenant);

	/**
	 * @return numeroDossier
	 */
	public String getNumeroDossier();
	


	/**
	 * @param numeroDossier numeroDossier � d�finir
	 */
	public void setNumeroDossier(String numeroDossier);


	/**
	 * @return categorie
	 */
	public String getCategorie();
	


	/**
	 * @param categorie categorie � d�finir
	 */
	public void setCategorie(String categorie);
	


	/**
	 * @return entite
	 */
	public String getEntite();
	


	/**
	 * @param entite entite � d�finir
	 */
	public void setEntite(String entite);
	

	/**
	 * @return genrePartage
	 */
	public String getGenrePartage();
	


	/**
	 * @param entite entite � d�finir
	 */
	public void setGenrePartage(String genrePartage);
	

	/**
	 * @return siteOrigine
	 */
	public String getSiteOrigine();
	

	/**
	 * @param siteOrigine siteOrigine � d�finir
	 */
	public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une collection d'intervenants.
     *
     * @param intervenantsChoisis Valeur de la liste de cha�ne de caract�re
     * des intervenants.
     */
    public void setIntervenantsChoisis(String [] intervenantsChoisis);
	
}