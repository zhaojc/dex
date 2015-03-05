package com.lotoquebec.cardex.presentation.model;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
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
     * Retourne entite.
     *
     * @return String Valeur de entite en caract�re.
     */
    public String getEntite();

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
     * Retourne le montant.
     *
     * @return String Valeur du montant en caract�re.
     */
    public String getMontant();

    /**
     * Retourne la confidentialit� de la narration.
     *
     * @return String Valeur de la confidentialit� de la narration en caract�re.
     */
    public String getConfidentialiteNarration();

    /**
     * Retourne la confidentialit� de l'approbateur.
     *
     * @return String Valeur de la confidentialit� de l'approbateur en
     * caract�re.
     */
    public String getConfidentialiteApprobateur();

    /**
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return String Valeur de la confidentialit� du cr�ateur en caract�re.
     */
    public String getConfidentialiteCreateur();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne l'autorit� de la narration.
     *
     * @return String Valeur de l'autorit� de la narration en caract�re.
     */
    public String getAutoriteNarration();

    /**
     * Retourne l'autorit� de l'approbateur.
     *
     * @return String Valeur de l'autorit� de l'approbateur en caract�re.
     */
    public String getAutoriteApprobateur();

    /**
     * Retourne l'autorit� du cr�ateur.
     *
     * @return String Valeur de l'autorit� du cr�ateur en caract�re.
     */
    public String getAutoriteCreateur();

    /**
     * Retourne le temps consacr�.
     *
     * @return String Valeur du temps consacr� en caract�re.
     */
    public String getTempsConsacre();

    /**
     * Retourne le nombre d'heure de temps consacr�.
     *
     * @return String Valeur du nombre d'heure de temps consacr� en caract�re.
     */
    public String getTempsConsacreHeure();

    /**
     * Retourne le nombre de minute de temps consacr�.
     *
     * @return String Valeur du nombre de minute de temps consacr� en caract�re.
     */
    public String getTempsConsacreMinute();

    /**
     * Retourne le rapporteur.
     *
     * @return String Valeur du rapporteur en caract�re.
     */
    public String getRapporteur();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

	/**
	 * Retourne le secteur dont fait partie le cr�ateur.
	 *
	 * @return String Valeur du secteur en caract�re.
	 */
	public String getSecteur();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur();

    /**
     * Retourne le num�ro de s�quence de la narration.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference();

    /**
     * Retourne une premi�re r�f�rence de la fiche rattach�e.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference1();

    /**
     * Retourne une deuxi�me r�f�rence de la fiche rattach�e.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne une troisi�me r�f�rence de la fiche rattach�e.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference3();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caract�re.
     */
    public String getDateModification();

    /**
     * Retourne la date de l'approbation.
     *
     * @return String Valeur de la date de l'approbation en caract�re
     */
    public String getDateApprobation();

    /**
     * Retourne le gabarit.
     *
     * @return String Valeur du gabarit en caract�re.
     */
    public String getGabaritUtilise();

    /**
     * Retourne le texte.
     *
     * @return String Valeur du texte en caract�re.
     */
    public String getTexte();

    /**
     * Retourne la narration avec format.
     *
     * @return String Valeur de la narration avec format en caract�re.
     */
    public String getNarrationAvecFormat();

    /**
     * Retourne la narration sans format.
     *
     * @return String Valeur de la narration sans format en caract�re.
     */
    public String getNarrationSansFormat();

    /**
     * Test si une narration peut �tre modifi�.
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
     * Test si une narration peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable();

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
     * Affecte un montant.
     *
     * @param montant Valeur du montant en caract�re.
     */
    public void setMontant(String montant);

    /**
     * Affecte une confidentialit� de la narration.
     *
     * @param confidentialiteNarration Valeur de la confidentialit� de la
     * narration en caract�re.
     */
    public void setConfidentialiteNarration(String confidentialiteNarration);

    /**
     * Affecte une confidentialit� de l'approbation.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialit� de
     * l'approbateur en caract�re.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur);

    /**
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur
     * en caract�re.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur);

    /**
     * Affecte une autorit� de la narration.
     *
     * @param autoriteNarration Valeur de l'autorit� de la narration en
     * caract�re.
     */
    public void setAutoriteNarration(String autoriteNarration);

    /**
     * Affecte une autorit� de l'approbateur.
     *
     * @param autoriteApprobateur Valeur de l'autorit� de l'approbateur en
     * caract�re.
     */
    public void setAutoriteApprobateur(String autoriteApprobateur);

    /**
     * Affecte une autorit� du cr�ateur.
     *
     * @param autoriteCreateur Valeur de l'autorit� du cr�ateur en caract�re.
     */
    public void setAutoriteCreateur(String autoriteCreateur);

    /**
     * Affecte un temps consacr�.
     *
     * @param tempsConsacre Valeur du temps consacr� en caract�re.
     */
    public void setTempsConsacre(String tempsConsacre);

    /**
     * Affecte un nombre d'heure de temps consacr�.
     *
     * @param tempsConsacre Valeur du nombre d'heure de temps consacr� en
     * caract�re.
     */
    public void setTempsConsacreHeure(String tempsConsacreHeure);

    /**
     * Affecte un nombre de minute de temps consacr�.
     *
     * @param tempsConsacre Valeur du nombre de minute de temps consacr� en
     * caract�re.
     */
    public void setTempsConsacreMinute(String tempsConsacreMinute);

    /**
     * Affecte un rapporteur.
     *
     * @param rapporteur Valeur du rapporteur en caract�re.
     */
    public void setRapporteur(String rapporteur);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

	/**
	 * Affecte un secteur.
	 *
	 * @param createur Valeur du secteur en caract�re.
	 */
	public void setSecteur(String secteur);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur);

    /**
     * D�termine une nouvelle narration
     *
     * @param isNouveau 
     */
    public void setNouveau(boolean isNouveau);

    /**
     * Affecte un num�ro de s�quence.
     *
     * @param reference Valeur de la r�f�rence en caract�re.
     */
    public void setReference(String reference);

    /**
     * Affecte une r�f�rence.
     *
     * @param reference Valeur de la r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une r�f�rence.
     *
     * @param reference Valeur de la r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une r�f�rence.
     *
     * @param reference Valeur de la r�f�rence en caract�re.
     */
    public void setReference3(String reference3);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte une date de l'approbation.
     *
     * @param dateApprobation Valeur de la date de l'approbation en caract�re.
     */
    public void setDateApprobation(String dateApprobation);

    /**
     * Affecte le gabarit choisi dans la narration.
     *
     * @param texte Valeur du gabarit en caract�re.
     */
    public void setGabaritUtilise(String gabaritUtilise);

    /**
     * Affecte un texte.
     *
     * @param texte Valeur du texte en caract�re.
     */
    public void setTexte(String texte);

    /**
     * Affecte une narration avec format.
     *
     * @param narrationAvecFormat Valeur de la narration avec format en
     * caract�re.
     */
    public void setNarrationAvecFormat(String narrationAvecFormat);

    /**
     * Affecte une narration sans format.
     *
     * @param narrationSansFormat Valeur de la narration sans format en
     * caract�re.
     */
    public void setNarrationSansFormat(String narrationSansFormat);

    /**
     * Determine si une narration est modifiable
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caract�re.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * D�termine si on peut permettre la modification d'une narration.
     *
     * @param isPermettreModification Est-ce qu'on peut permettre la modification d'une narration.
     * caract�re.
     */
    public void setPermettreModification(boolean isPermettreModification);

    /**
     *
     * Determine si une narration est approuvanle
     *
     * @param isApprouvable Est-ce que la narration est approuvable
     * caract�re.
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
     * @param site Valeur entite en caract�re.
     */
    public void setEntite(String entite);

    /**
     * La narration contien un gabarie de rapport d'activit� quotidienne 
     * @return
     */
    public boolean isRapportActiviteQuotidienne();

	public void setRapportActiviteQuotidienne(boolean rapportActiviteQuotidienne);    
}