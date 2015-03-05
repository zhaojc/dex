package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de soci�t�.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.11 $, $Date: 2002/02/25 18:15:26 $
 * @see
 */
public interface CriteresRechercheSocieteHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroFiche();

    /**
     * Retourne la raison d'�tre.
     *
     * @return String Valeur de la raison d'�tre en caract�re.
     */
    public String getRaisonEtre();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getNom();

    /**
     * Retourne le nom phon�tique.
     *
     * @return String Valeur du nom phon�tique en caract�re.
     */
    public String getNomPhonetique();
    
    /**
     * Retourne le nom du sujet associ� � la soci�t�.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getNomSujet();

    /**
     * Retourne le pr�nom du sujet associ� � la soci�t�.
     *
     * @return String Valeur du pr�nom en caract�re.
     */
    public String getPrenomSujet();

    /**
     * Retourne le pays.
     *
     * @return String Valeur du pays en caract�re.
     */
    public String getPays();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince();

    /**
     * Retourne la ville.
     *
     * @return String Valeur de la ville en caract�re.
     */
    public String getVille();

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caract�re.
     */
    public String getClasse();

    /**
     * Retourne le r�le.
     *
     * @return String Valeur du r�le en caract�re.
     */
    public String getRole();

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caract�re.
     */
    public String getLangue();

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite();

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeveriteCasino();

    /**
     * Retourne la r�gion (r�f�rence1).
     *
     * @return String Valeur de la r�gion en caract�re.
     */
    public String getReference1();
    
    /**
     * Retourne la r�f�rence (r�f�rence2).
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference2();
    
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
     * Retourne une collection de soci�t�s.
     *
     * @return Collection Valeur de la collection de soci�t�s.
     */
    public Collection getSocietes();


    // Setters


    /**
     * Affecte un num�ro de fiche.
     *
     * @param numeroFiche Valeur du num�ro de fiche en caract�re.
     */
    public void setNumeroFiche(String numeroFiche);

    /**
     * Affecte une raison d'�tre.
     *
     * @param raisonEtre Valeur de la raison d'�tre en caract�re.
     */
    public void setRaisonEtre(String raisonEtre);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setNom(String nom);

    /**
     * Affecte un nom phon�tique.
     *
     * @param nomPhonetique Valeur du nom phon�tique en caract�re.
     */
    public void setNomPhonetique(String nomPhonetique);

    /**
     * Affecte un nom du sujet.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setNomSujet(String nomSujet);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setPrenomSujet(String prenomSujet);

    /**
     * Affecte un le pays.
     *
     * @param pays Valeur du pays en caract�re.
     */
    public void setPays(String pays);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province);

    /**
     * Affecte une premi�re r�f�rence (r�gion).
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la r�f�rence.
     *
     * @param reference2 Valeur de la r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville en caract�re.
     */
    public void setVille(String ville);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caract�re.
     */
    public void setClasse(String classe);

    /**
     * Affecte un r�le.
     *
     * @param role Valeur du r�le en caract�re.
     */
    public void setRole(String role);

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caract�re.
     */
    public void setLangue(String langue);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une s�v�rit� casino.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeveriteCasino(String severiteCasino);

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
     * Ajoute une soci�t�.
     *
     * @param sujet Valeur de la soci�t� � ajouter.
     */
    public void addSociete(SocieteHtmlForm societe);

	/**
	 * Retourne si un r�le est requis pour la liaison
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