package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à la narration.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.14 $, $Date: 2002/03/08 17:33:37 $
 */
public interface Narration extends Modifiable{


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

    /**
     * Retourne l'entité.
     *
     * @return long Valeur de entite.
     */
    public long getEntite();

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
     * Retourne le montant.
     *
     * @return double Valeur du montant.
     */
    public double getMontant();

    /**
     * Retourne la confidentialité de la narration.
     *
     * @return long Valeur de la confidentialité de la narration.
     */
    public long getConfidentialiteNarration();

    /**
     * Retourne la confidentialité de l'approbateur.
     *
     * @return long Valeur de la confidentialité de l'approbateur.
     */
    public long getConfidentialiteApprobateur();

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return long Valeur de la confidentialité du créateur.
     */
    public long getConfidentialiteCreateur();

    /**
     * Retourne l'autorité de la narration.
     *
     * @return long Valeur de l'autorité de la narration.
     */
    public long getAutoriteNarration();

    /**
     * Retourne l'autorité de l'approbateur.
     *
     * @return long Valeur de l'autorité de l'approbateur.
     */
    public long getAutoriteApprobateur();

    /**
     * Retourne l'autorité du créateur.
     *
     * @return long Valeur de l'autorité du créateur.
     */
    public long getAutoriteCreateur();

    /**
     * Retourne le temps consacré.
     *
     * @return String Valeur du temps consacré en caractère.
     */
    public String getTempsConsacre();

    /**
     * Retourne le rapporteur.
     *
     * @return String Valeur du rapporteur en caractère.
     */
    public String getRapporteur();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

	/**
	 * Retourne le secteur dont fait partie le créateur.
	 *
	 * @return String Valeur du secteur en caractère.
	 */
	public String getSecteur();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur();

    /**
     * Retourne le numéro de séquence.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference();

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne une deuxième référence de la fiche rattachée.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne une troisième référence de la fiche rattachée.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference3();

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification();

    /**
     * Retourne la date de l'approbation.
     *
     * @return Timestamp Valeur de la date de l'approbation (yyyy-MM-dd).
     */
    public Timestamp getDateApprobation();

    /**
     * Retourne le texte.
     *
     * @return String Valeur du texte en caractère.
     */
    public String getTexte();

    /**
     * Retourne le gabarit.
     *
     * @return String Valeur du gabarit en caractère.
     */
    public long getGabaritUtilise();

    /**
     * Retourne la narration avec format.
     *
     * @return String Valeur de la narration avec format en caractère.
     */
    public String getNarrationAvecFormat();

    /**
     * Retourne la narration sans format.
     *
     * @return String Valeur de la narration sans format en caractère.
     */
    public String getNarrationSansFormat();

    /**
     * Retourne certaines informations sur le dossier retourné
     * par une recherche de narrations.  Ces informations sont
     * utilisées pour appeler le dossier à l'écran à partir de la
     * liste de résultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier();

    /**
     * Test si une narration peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();

    /**
     * Test si une narration est nouvelle.
     *
     * @return boolean True si la narration est nouvelle.
     */
    public Boolean isNouveau();

    /**
     * Test si on peut permettre la modification d'une narration.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public Boolean isPermettreModification();

    /**
     * Test si une narration peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

    /**
     * Affecte entite.
     *
     * @param entite Valeur entite.
     */
    public void setEntite(long entite);

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
     * Affecte un montant.
     *
     * @param montant Valeur du montant.
     */
    public void setMontant(double montant);

    /**
     * Affecte une confidentialité de la narration.
     *
     * @param confidentialiteNarration Valeur de la confidentialité de la
     * narration.
     */
    public void setConfidentialiteNarration(long confidentialiteNarration);

    /**
     * Affecte une confidentialité de l'approbation.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialité de
     * l'approbation.
     */
    public void setConfidentialiteApprobateur(long confidentialiteApprobateur);

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur);

    /**
     * Affecte une autorité de la narration.
     *
     * @param autoriteNarration Valeur de l'autorité de la narration.
     */
    public void setAutoriteNarration(long autoriteNarration);

    /**
     * Affecte une autorité de l'approbateur.
     *
     * @param autoriteApprobateur Valeur de l'autorité de l'approbateur.
     */
    public void setAutoriteApprobateur(long autoriteApprobateur);

    /**
     * Affecte une autorité du créateur.
     *
     * @param autoriteCreateur Valeur de l'autorité du créateur.
     */
    public void setAutoriteCreateur(long autoriteCreateur);

    /**
     * Affecte un temps consacré.
     *
     * @param tempsConsacre Valeur du temps consacré en caractère.
     */
    public void setTempsConsacre(String tempsConsacre);

    /**
     * Affecte un rapporteur.
     *
     * @param rapporteur Valeur du rapporteur en caractère.
     */
    public void setRapporteur(String rapporteur);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

	/**
	 * Affecte un secteur.
	 *
	 * @param createur Valeur du secteur en caractère.
	 */
	public void setSecteur(String secteur);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte un numéro de séquence.
     *
     * @param reference Valeur de la référence en caractère.
     */
    public void setReference(String reference);

    /**
     * Affecte une référence.
     *
     * @param reference Valeur de la référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une référence.
     *
     * @param reference Valeur de la référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une référence.
     *
     * @param reference Valeur de la référence en caractère.
     */
    public void setReference3(String reference3);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte une date de l'approbation.
     *
     * @param dateApprobation Valeur de la date de l'approbation (yyyy-MM-dd).
     */
    public void setDateApprobation(Timestamp dateApprobation);

    /**
     * Affecte un texte.
     *
     * @param texte Valeur du texte en caractère.
     */
    public void setTexte(String texte);

    /**
     * Affecte une narration avec format.
     *
     * @param narrationAvecFormat Valeur de la narration avec format en
     * caractère.
     */
    public void setNarrationAvecFormat(String narrationAvecFormat);

    /**
     * Affecte une narration sans format.
     *
     * @param narrationSansFormat Valeur de la narration sans format en
     * caractère.
     */
    public void setNarrationSansFormat(String narrationSansFormat);

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourné par une recherche de narrations.
     */
    public void setDossier(Dossier dossier);

    /**
     * Determine si une narration est modifiable
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caractère.
     */
    public void setModifiable(Boolean modifiable);

    /**
     * Détermine si on peut permettre la modification d'une narration.
     *
     * @param isPermettreModification Est-ce qu'on peut permettre la modification d'une narration.
     * caractère.
     */
    public void setPermettreModification(Boolean permettreModification);

    /**
     * Determine si une narration est approuvanle
     *
     * @param isApprouvable Est-ce que la narration est approuvable
     * caractère.
     */
    public void setApprouvable(Boolean approuvable);

    /**
     * Determine si une narration est nouvelle
     *
     * @param isNouveau Est-ce que la narration est nouvelle
     * caractère.
     */
    public void setNouveau(Boolean nouveau);
    
    /**
     * Affecte le gabarit choisi dans la narration.
     *
     * @param texte Valeur du gabarit en caractère.
     */
    public void setGabaritUtilise(long gabaritUtilise);

   /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * La narration contient un gabarie de rapport d'activité quotidienne 
     * @return
     */
    public Boolean isRapportActiviteQuotidienne();

	public String getNarrationTemporaire();

	public void setNarrationTemporaire(String narrationTemporaire);

	public String getGenreLiaison();

	public void setGenreLiaison(String genreLiaison);
}