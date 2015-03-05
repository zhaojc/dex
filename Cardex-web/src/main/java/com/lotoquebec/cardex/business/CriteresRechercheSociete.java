package com.lotoquebec.cardex.business;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Permet de transiter les informations relatives à la recherche d'une
 * société de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/25 18:07:38 $
 */
public interface CriteresRechercheSociete extends CriteresRecherche{


    // Getters


    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse();

    /**
     * Retourne la raison d'être.
     *
     * @return String Valeur de la raison d'être en caractère.
     */
    public String getRaisonEtre();

    /**
     * Retourne le rôle.
     *
     * @return long Valeur du rôle.
     */
    public long getRole();

    /**
     * Retourne le nom.
     *
     * @return String Valeur le nom en caractère.
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
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur numérique du site d'origine.
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
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite();

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince();

    /**
     * Retourne la référence.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne la région (reference1).
     *
     * @return String Valeur de la première référence en caractère.
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
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeverite();

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité en caractère.
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
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();


    // Setters


    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte une raison d'être.
     *
     * @param raisonEtre Valeur de la raison d'être en caractère.
     */
    public void setRaisonEtre(String raisonEtre);

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle.
     */
    public void setRole(long role);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur le nom en caractère.
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
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
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
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province);

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une référence.
     *
     * @param reference1 Valeur de la référence en caractère.
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
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite);

    /**
     * Affecte une sévérité casino.
     *
     * @param severite Valeur de la sévérité en caractère.
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
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximum);

	public EntiteCardex getEntiteCardexLiaison();

	public void setEntiteCardexLiaison(EntiteCardex entiteCardexLiaison);
	
}