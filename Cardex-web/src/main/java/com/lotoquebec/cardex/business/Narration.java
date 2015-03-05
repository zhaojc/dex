package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � la narration.
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
     * Retourne l'entit�.
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
     * Retourne la confidentialit� de la narration.
     *
     * @return long Valeur de la confidentialit� de la narration.
     */
    public long getConfidentialiteNarration();

    /**
     * Retourne la confidentialit� de l'approbateur.
     *
     * @return long Valeur de la confidentialit� de l'approbateur.
     */
    public long getConfidentialiteApprobateur();

    /**
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return long Valeur de la confidentialit� du cr�ateur.
     */
    public long getConfidentialiteCreateur();

    /**
     * Retourne l'autorit� de la narration.
     *
     * @return long Valeur de l'autorit� de la narration.
     */
    public long getAutoriteNarration();

    /**
     * Retourne l'autorit� de l'approbateur.
     *
     * @return long Valeur de l'autorit� de l'approbateur.
     */
    public long getAutoriteApprobateur();

    /**
     * Retourne l'autorit� du cr�ateur.
     *
     * @return long Valeur de l'autorit� du cr�ateur.
     */
    public long getAutoriteCreateur();

    /**
     * Retourne le temps consacr�.
     *
     * @return String Valeur du temps consacr� en caract�re.
     */
    public String getTempsConsacre();

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
     * Retourne le num�ro de s�quence.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference();

    /**
     * Retourne la premi�re r�f�rence.
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
     * @return Timestamp Valeur de la date de cr�ation (yyyy-MM-dd).
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
     * @return String Valeur du texte en caract�re.
     */
    public String getTexte();

    /**
     * Retourne le gabarit.
     *
     * @return String Valeur du gabarit en caract�re.
     */
    public long getGabaritUtilise();

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
     * Retourne certaines informations sur le dossier retourn�
     * par une recherche de narrations.  Ces informations sont
     * utilis�es pour appeler le dossier � l'�cran � partir de la
     * liste de r�sultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier();

    /**
     * Test si une narration peut �tre modifi�.
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
     * Test si une narration peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
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
     * Affecte une confidentialit� de la narration.
     *
     * @param confidentialiteNarration Valeur de la confidentialit� de la
     * narration.
     */
    public void setConfidentialiteNarration(long confidentialiteNarration);

    /**
     * Affecte une confidentialit� de l'approbation.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialit� de
     * l'approbation.
     */
    public void setConfidentialiteApprobateur(long confidentialiteApprobateur);

    /**
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur);

    /**
     * Affecte une autorit� de la narration.
     *
     * @param autoriteNarration Valeur de l'autorit� de la narration.
     */
    public void setAutoriteNarration(long autoriteNarration);

    /**
     * Affecte une autorit� de l'approbateur.
     *
     * @param autoriteApprobateur Valeur de l'autorit� de l'approbateur.
     */
    public void setAutoriteApprobateur(long autoriteApprobateur);

    /**
     * Affecte une autorit� du cr�ateur.
     *
     * @param autoriteCreateur Valeur de l'autorit� du cr�ateur.
     */
    public void setAutoriteCreateur(long autoriteCreateur);

    /**
     * Affecte un temps consacr�.
     *
     * @param tempsConsacre Valeur du temps consacr� en caract�re.
     */
    public void setTempsConsacre(String tempsConsacre);

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
     * @param dateCreation Valeur de la date de cr�ation (yyyy-MM-dd).
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
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourn� par une recherche de narrations.
     */
    public void setDossier(Dossier dossier);

    /**
     * Determine si une narration est modifiable
     *
     * @param isModifiable Est-ce que la narration est modifiable
     * caract�re.
     */
    public void setModifiable(Boolean modifiable);

    /**
     * D�termine si on peut permettre la modification d'une narration.
     *
     * @param isPermettreModification Est-ce qu'on peut permettre la modification d'une narration.
     * caract�re.
     */
    public void setPermettreModification(Boolean permettreModification);

    /**
     * Determine si une narration est approuvanle
     *
     * @param isApprouvable Est-ce que la narration est approuvable
     * caract�re.
     */
    public void setApprouvable(Boolean approuvable);

    /**
     * Determine si une narration est nouvelle
     *
     * @param isNouveau Est-ce que la narration est nouvelle
     * caract�re.
     */
    public void setNouveau(Boolean nouveau);
    
    /**
     * Affecte le gabarit choisi dans la narration.
     *
     * @param texte Valeur du gabarit en caract�re.
     */
    public void setGabaritUtilise(long gabaritUtilise);

   /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * La narration contient un gabarie de rapport d'activit� quotidienne 
     * @return
     */
    public Boolean isRapportActiviteQuotidienne();

	public String getNarrationTemporaire();

	public void setNarrationTemporaire(String narrationTemporaire);

	public String getGenreLiaison();

	public void setGenreLiaison(String genreLiaison);
}