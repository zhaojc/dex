package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Permet de transiter les informations relatives � la recherche d'une
 * soci�t� de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/25 18:07:38 $
 */
public interface CriteresRechercheSociete extends CriteresRecherche{


    // Getters


    /**
     * Retourne le num�ro de fiche.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroFiche();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse();

    /**
     * Retourne la raison d'�tre.
     *
     * @return String Valeur de la raison d'�tre en caract�re.
     */
    public String getRaisonEtre();

    /**
     * Retourne le r�le.
     *
     * @return long Valeur du r�le.
     */
    public long getRole();

    /**
     * Retourne le nom.
     *
     * @return String Valeur le nom en caract�re.
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
     * Retourne l'entit�.
     *
     * @return long Valeur num�rique de l'entit�.
     */
    public long getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue();

    /**
     * Retourne le pays.
     *
     * @return long Valeur du pays.
     */
    public long getPays();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite();

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince();

    /**
     * Retourne la r�f�rence.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne la r�gion (reference1).
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1();

    /**
     * Retourne la ville.
     *
     * @return long Valeur de la ville.
     */
    public long getVille();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur de la s�v�rit�.
     */
    public long getSeverite();

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur de la s�v�rit� en caract�re.
     */
    public long getSeveriteCasino();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public long getMaximumResultatsRecherche();


    // Setters


    /**
     * Affecte un num�ro de fiche.
     *
     * @param numeroFiche Valeur du num�ro de fiche en caract�re.
     */
    public void setNumeroFiche(String numeroFiche);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte une raison d'�tre.
     *
     * @param raisonEtre Valeur de la raison d'�tre en caract�re.
     */
    public void setRaisonEtre(String raisonEtre);

    /**
     * Affecte un r�le.
     *
     * @param role Valeur du r�le.
     */
    public void setRole(long role);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur le nom en caract�re.
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
     * Affecte une entit�.
     *
     * @param entite Valeur num�rique de l'entit�.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long site);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue);

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays.
     */
    public void setPays(long pays);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province);

    /**
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une r�f�rence.
     *
     * @param reference1 Valeur de la r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville.
     */
    public void setVille(long ville);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit�.
     */
    public void setSeverite(long severite);

    /**
     * Affecte une s�v�rit� casino.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeveriteCasino(long severiteCasino);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximum);

	public EntiteCardex getEntiteCardexLiaison();

	public void setEntiteCardexLiaison(EntiteCardex entiteCardexLiaison);
	
}