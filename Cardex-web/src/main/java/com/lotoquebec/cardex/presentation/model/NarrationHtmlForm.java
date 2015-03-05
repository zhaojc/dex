package com.lotoquebec.cardex.presentation.model;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation d'une narration.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $, $Date: 2002/03/07 20:03:28 $
 */
public interface NarrationHtmlForm {


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
     * Retourne entite.
     *
     * @return String Valeur de entite en caractère.
     */
    public String getEntite();

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
     * Retourne le montant.
     *
     * @return String Valeur du montant en caractère.
     */
    public String getMontant();

    /**
     * Retourne la confidentialité de la narration.
     *
     * @return String Valeur de la confidentialité de la narration en caractère.
     */
    public String getConfidentialiteNarration();

    /**
     * Retourne la confidentialité de l'approbateur.
     *
     * @return String Valeur de la confidentialité de l'approbateur en
     * caractère.
     */
    public String getConfidentialiteApprobateur();

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return String Valeur de la confidentialité du créateur en caractère.
     */
    public String getConfidentialiteCreateur();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne l'autorité de la narration.
     *
     * @return String Valeur de l'autorité de la narration en caractère.
     */
    public String getAutoriteNarration();

    /**
     * Retourne l'autorité de l'approbateur.
     *
     * @return String Valeur de l'autorité de l'approbateur en caractère.
     */
    public String getAutoriteApprobateur();

    /**
     * Retourne l'autorité du créateur.
     *
     * @return String Valeur de l'autorité du créateur en caractère.
     */
    public String getAutoriteCreateur();

    /**
     * Retourne le temps consacré.
     *
     * @return String Valeur du temps consacré en caractère.
     */
    public String getTempsConsacre();

    /**
     * Retourne le nombre d'heure de temps consacré.
     *
     * @return String Valeur du nombre d'heure de temps consacré en caractère.
     */
    public String getTempsConsacreHeure();

    /**
     * Retourne le nombre de minute de temps consacré.
     *
     * @return String Valeur du nombre de minute de temps consacré en caractère.
     */
    public String getTempsConsacreMinute();

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
     * Retourne le numéro de séquence de la narration.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference();

    /**
     * Retourne une première référence de la fiche rattachée.
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
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification();

    /**
     * Retourne la date de l'approbation.
     *
     * @return String Valeur de la date de l'approbation en caractère
     */
    public String getDateApprobation();

    /**
     * Retourne le gabarit.
     *
     * @return String Valeur du gabarit en caractère.
     */
    public String getGabaritUtilise();

    /**
     * Retourne le texte.
     *
     * @return String Valeur du texte en caractère.
     */
    public String getTexte();

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
     * Test si une narration peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isModifiable();

    /**
     * Test s'il s'agit d'une nouvelle narration.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isNouveau();

    /**
     * Test si on peut permettre la modification d'une narration.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public boolean isPermettreModification();

    /**
     * Test si une narration peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable();

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
     * Affecte un montant.
     *
     * @param montant Valeur du montant en caractère.
     */
    public void setMontant(String montant);

    /**
     * Affecte une confidentialité de la narration.
     *
     * @param confidentialiteNarration Valeur de la confidentialité de la
     * narration en caractère.
     */
    public void setConfidentialiteNarration(String confidentialiteNarration);

    /**
     * Affecte une confidentialité de l'approbation.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialité de
     * l'approbateur en caractère.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur);

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur
     * en caractère.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur);

    /**
     * Affecte une autorité de la narration.
     *
     * @param autoriteNarration Valeur de l'autorité de la narration en
     * caractère.
     */
    public void setAutoriteNarration(String autoriteNarration);

    /**
     * Affecte une autorité de l'approbateur.
     *
     * @param autoriteApprobateur Valeur de l'autorité de l'approbateur en
     * caractère.
     */
    public void setAutoriteApprobateur(String autoriteApprobateur);

    /**
     * Affecte une autorité du créateur.
     *
     * @param autoriteCreateur Valeur de l'autorité du créateur en caractère.
     */
    public void setAutoriteCreateur(String autoriteCreateur);

    /**
     * Affecte un temps consacré.
     *
     * @param tempsConsacre Valeur du temps consacré en caractère.
     */
    public void setTempsConsacre(String tempsConsacre);

    /**
     * Affecte un nombre d'heure de temps consacré.
     *
     * @param tempsConsacre Valeur du nombre d'heure de temps consacré en
     * caractère.
     */
    public void setTempsConsacreHeure(String tempsConsacreHeure);

    /**
     * Affecte un nombre de minute de temps consacré.
     *
     * @param tempsConsacre Valeur du nombre de minute de temps consacré en
     * caractère.
     */
    public void setTempsConsacreMinute(String tempsConsacreMinute);

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
     * Détermine une nouvelle narration
     *
     * @param isNouveau 
     */
    public void setNouveau(boolean isNouveau);

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
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte une date de l'approbation.
     *
     * @param dateApprobation Valeur de la date de l'approbation en caractère.
     */
    public void setDateApprobation(String dateApprobation);

    /**
     * Affecte le gabarit choisi dans la narration.
     *
     * @param texte Valeur du gabarit en caractère.
     */
    public void setGabaritUtilise(String gabaritUtilise);

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
     * Determine si une narration est modifiable
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caractère.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Détermine si on peut permettre la modification d'une narration.
     *
     * @param isPermettreModification Est-ce qu'on peut permettre la modification d'une narration.
     * caractère.
     */
    public void setPermettreModification(boolean isPermettreModification);

    /**
     *
     * Determine si une narration est approuvanle
     *
     * @param isApprouvable Est-ce que la narration est approuvable
     * caractère.
     */
    public void setApprouvable(boolean isApprouvable);
    
    /**
     * Affecte la date du changement (pour l'audit).
     *
     * @param dateChangement Valeur dateChangement.
     */
    public void setDateChangement(String dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * Affecte entite.
     *
     * @param site Valeur entite en caractère.
     */
    public void setEntite(String entite);

    /**
     * La narration contien un gabarie de rapport d'activité quotidienne 
     * @return
     */
    public boolean isRapportActiviteQuotidienne();

	public void setRapportActiviteQuotidienne(boolean rapportActiviteQuotidienne);    
}