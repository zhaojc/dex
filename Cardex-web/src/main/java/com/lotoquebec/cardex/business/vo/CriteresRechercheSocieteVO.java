package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresRechercheSociete;
import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Permet de transiter les informations relatives à la recherche d'une
 * société de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.5 $, $Date: 2002/02/25 18:07:42 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSociete
 */
public class CriteresRechercheSocieteVO implements CriteresRechercheSociete {

    private String numeroFiche = "";
    private long classe = 0;
    private String raisonEtre = "";
    private long role = 0;
    private String nom = "";
    private String nomPhonetique = "";
    private String nomSujet = "";
    private String prenomSujet = "";
    private long entite = 0;
    private long siteOrigine = 0;
    private long langue = 0;
    private long pays = 0;
    private long confidentialite = 0;
    private long province = 0;
    private String reference1 = "";
    private String reference2 = "";
    private long ville = 0;
    private long statut = 0;
    private long severite = 0;
    private long severiteCasino = 0;
    private long lien = 0;
    private long lienSite = 0;
    private String ordreTriRecherche = "";
    private Boolean ordreCroissantRecherche = true;
    private long  maximumResultatsRecherche = 0;
    private int sequence = 0;
    private EntiteCardex entiteCardexLiaison = null;
    
    /**
     * Constructeur de CriteresRechercheSocieteVO par défaut.
     */
    public CriteresRechercheSocieteVO() {}


    // Getters


    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche() {
        return this.numeroFiche;
    }

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse() {
        return this.classe;
    }

    /**
     * Retourne la raison d'être.
     *
     * @return String Valeur de la raison d'être en caractère.
     */
    public String getRaisonEtre() {
        return this.raisonEtre;
    }

    /**
     * Retourne le rôle.
     *
     * @return long Valeur du rôle.
     */
    public long getRole() {
        return this.role;
    }

    /**
     * Retourne le nom.
     *
     * @return String Valeur le nom en caractère.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * @return the nomPhonetique
     */
    public String getNomPhonetique()
    {
        return nomPhonetique;
    }


    /**
     * Retourne l'entité.
     *
     * @return long Valeur numérique de l'entité.
     */
    public long getEntite() {
        return this.entite;
    }

    /**
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue() {
        return this.langue;
    }

    /**
     * Retourne le pays.
     *
     * @return long Valeur du pays.
     */
    public long getPays() {
        return this.pays;
    }

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne la province.
     *
     * @return long Valeur de la province.
     */
    public long getProvince() {
        return this.province;
    }

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractère.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la ville.
     *
     * @return long Valeur de la ville.
     */
    public long getVille() {
        return this.ville;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeverite() {
        return this.severite;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }


    // Setters


    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche) {
        this.numeroFiche = numeroFiche;
    }

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe.
     */
    public void setClasse(long classe) {
        this.classe = classe;
    }

    /**
     * Affecte une raison d'être.
     *
     * @param raisonEtre Valeur de la raison d'être en caractère.
     */
    public void setRaisonEtre(String raisonEtre) {
        this.raisonEtre = raisonEtre;
    }

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle.
     */
    public void setRole(long role) {
        this.role = role;
    }

    /**
     * Affecte un nom.
     *
     * @param nom Valeur le nom en caractère.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param nomPhonetique the nomPhonetique to set
     */
    public void setNomPhonetique(String nomPhonetique)
    {
        this.nomPhonetique = nomPhonetique;
    }

    /**
     * Affecte une entité.
     *
     * @param entite Valeur numérique de l'entité.
     */
    public void setEntite(long entite) {
        this.entite = entite;
    }
 
    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue) {
        this.langue = langue;
    }

    /**
     * Affecte un pays.
     *
     * @param pays Valeur du pays.
     */
    public void setPays(long pays) {
        this.pays = pays;
    }

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(long province) {
        this.province = province;
    }

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    /**
     * Affecte une ville.
     *
     * @param ville Valeur de la ville.
     */
    public void setVille(long ville) {
        this.ville = ville;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite) {
        this.severite = severite;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSocieteVO.
     *
     * @return String Valeur de tout les attributs du
     * CriteresRechercheSocieteVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheSocieteVO : ");
        stringBuffer.append("numeroFiche = '" + numeroFiche);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', raisonEtre = '" + raisonEtre);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', nomPhonetique = '" + nomPhonetique);
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', pays = '" + pays);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', ville = '" + ville);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }


	/**
	 * @return nomSujet
	 */
	public String getNomSujet() {
		return nomSujet;
	}


	/**
	 * @param nomSujet nomSujet à définir
	 */
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}


	/**
	 * @return prenomSujet
	 */
	public String getPrenomSujet() {
		return prenomSujet;
	}


	/**
	 * @param prenomSujet prenomSujet à définir
	 */
	public void setPrenomSujet(String prenomSujet) {
		this.prenomSujet = prenomSujet;
	}


	public int getSequence() {
		return sequence;
	}


	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	public Boolean getOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}


	/**
	 * @return siteOrigine
	 */
	public long getSiteOrigine() {
		return siteOrigine;
	}


	/**
	 * @param siteOrigine siteOrigine à définir
	 */
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}


	public long getSeveriteCasino() {
		return severiteCasino;
	}


	public void setSeveriteCasino(long severiteCasino) {
		this.severiteCasino = severiteCasino;
	}


	public String getReference2() {
		return reference2;
	}


	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}


	public EntiteCardex getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}


	public void setEntiteCardexLiaison(EntiteCardex entiteCardexLiaison) {
		this.entiteCardexLiaison = entiteCardexLiaison;
	}

}