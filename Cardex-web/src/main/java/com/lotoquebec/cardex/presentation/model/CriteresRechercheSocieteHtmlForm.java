package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de société.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.11 $, $Date: 2002/02/25 18:15:26 $
 * @see
 */
public interface CriteresRechercheSocieteHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche();

    /**
     * Retourne la raison d'être.
     *
     * @return String Valeur de la raison d'être en caractère.
     */
    public String getRaisonEtre();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

    /**
     * Retourne le nom phonétique.
     *
     * @return String Valeur du nom phonétique en caractère.
     */
    public String getNomPhonetique();
    
    /**
     * Retourne le nom du sujet associé à la société.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNomSujet();

    /**
     * Retourne le prénom du sujet associé à la société.
     *
     * @return String Valeur du prénom en caractère.
     */
    public String getPrenomSujet();

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caractère.
     */
    public String getPays();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caractère.
     */
    public String getVille();

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caractère.
     */
    public String getClasse();

    /**
     * Retourne le rôle.
     *
     * @return String Valeur du rôle en caractère.
     */
    public String getRole();

    /**
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caractère.
     */
    public String getLangue();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite();

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeveriteCasino();

    /**
     * Retourne la région (référence1).
     *
     * @return String Valeur de la région en caractère.
     */
    public String getReference1();
    
    /**
     * Retourne la référence (référence2).
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference2();
    
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


    /**
     * Retourne une collection de sociétés.
     *
     * @return Collection Valeur de la collection de sociétés.
     */
    public Collection getSocietes();


    // Setters


    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche);

    /**
     * Affecte une raison d'être.
     *
     * @param raisonEtre Valeur de la raison d'être en caractère.
     */
    public void setRaisonEtre(String raisonEtre);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom);

    /**
     * Affecte un nom phonétique.
     *
     * @param nomPhonetique Valeur du nom phonétique en caractère.
     */
    public void setNomPhonetique(String nomPhonetique);

    /**
     * Affecte un nom du sujet.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNomSujet(String nomSujet);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setPrenomSujet(String prenomSujet);

    /**
     * Affecte un le pays.
     *
     * @param pays Valeur du pays en caractère.
     */
    public void setPays(String pays);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province);

    /**
     * Affecte une première référence (région).
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la référence.
     *
     * @param reference2 Valeur de la référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caractère.
     */
    public void setVille(String ville);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caractère.
     */
    public void setClasse(String classe);

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle en caractère.
     */
    public void setRole(String role);

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(String langue);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une sévérité casino.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeveriteCasino(String severiteCasino);

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
     * Ajoute une société.
     *
     * @param sujet Valeur de la société à ajouter.
     */
    public void addSociete(SocieteHtmlForm societe);

	/**
	 * Retourne si un rôle est requis pour la liaison
	 * @return
	 */
	public boolean isLienRoleRequis();

	/**
	 * @param string
	 */
	public void setLienRoleRequis(boolean lienRoleRequis);

	public EntiteCardexForm getEntiteCardexLiaison();
	
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardexLiaison);
	
}