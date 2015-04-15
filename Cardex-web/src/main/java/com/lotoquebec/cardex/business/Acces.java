package com.lotoquebec.cardex.business;

import java.sql.Timestamp;
import java.util.Collection;

import com.lotoquebec.cardexCommun.business.vo.VO;

/**
 * Les acc�s aux donn�es du syst�me sont compil�s dans la table AC_ACCES.
 * Les donn�es compil�es peuvent �tre consult�s avec le bouton Audit sur 
 * les �crans de consultation (Dossiers, Sujets, Soci�t�s et V�hicules).
 *
 * @author $Author: Fran�ois Gu�rin $
 * @version $Revison: $, $Date: 2002/06/11 00:25:27 $
 */
public interface Acces extends VO{


    // Getters

    /**
     * Retourne la cl� (L_AC_CLE).
     *
     * @return long Valeur num�rique de la cl�
     */
    public long getCle();

    /**
     * Retourne le site (L_SI_SITE).
     *
     * @return long Valeur num�rique du site d'o� provient l'acc�s
     */

    public long getSite();

    /**
     * Retourne le site d'origine (L_ORI_SITE).
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne le genre (C_GF_REFERENCE).
     *
     * @return String Valeur du genre.
     */
    public String getGenreRef();

    /**
     * Retourne la cl� d'origine (L_ORI_CLE).
     *
     * @return long Valeur num�rique de la cl�.
     */
    public long getCleOrigine();

    /**
     * Retourne la cl� de r�f�rence (L_REF_CLE).
     *
     * @return long Valeur num�rique dela cl�.
     */
    public long getCleRef();

    /**
     * Retourne la r�f�rence du fichier (C_GF_ORIGINE).
     * Correspond au fichier sur lequel on conserve un audit.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenreOrigine();

    /**
     * Retourne le site de r�f�rence (L_REF_SITE).
     *
     * @return long Valeur num�rique du site.
     */
    public long getSiteRef();

    /**
     * Retourne le code de l'utilisateur qui fait l'acc�s (V_AC_NAME).
     *
     * @return String Valeur du code de l'utilisateur.
     */
    public String getUtilisateur();

    /**
     * Retourne la date de l'acc�s (D_AC_DATE_ACCES).
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public Timestamp getDateAcces();

     /**
     * Retourne la cl� de r�f�rence 2 (L_REF2_CLE).
     *
     * @return long Valeur de la cl�.
     */
    public long getCleRef2();

    /**
     * Retourne le type d'action (C_AC_ACTION).
     * S=S�lection, I=Insertion, D=Suppression, U=Modification
     *
     * @return String Valeur de l'action.
     */
    public String getAction();

    /**
     * Retourne le genre de la r�f�rence 2 (C_GF_REF2).
     *
     * @return String Valeur du genre
     */
    public String getGenreRef2();

    /**
     * Retourne le site de la r�f�rence 2 (L_REF2_SITE ).
     *
     * @return long Valeur du site.
     */
    public long getSiteRef2();

    /**
     * Retourne le cr�ateur du dossier principal pour lequel on consulte
     * les acc�s.
     *
     * @return String Valeur du code de l'intervenant.
     */
    public String getCreateur();


    /**
     * Retourne la hi�rarchie (applicable dans le cas des Dossiers).
     *
     * @return long Valeur num�rique de la hi�rarchie.
     */
    public long getHierarchie();


    /**
     * Retourne la date de cr�ation du dossier principal.
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

   /**
     * Retourne les dossiers.
     *
     * @return Collection Valeur des dossiers.
     */
    public Collection getDossiers();


    // Setters

    /**
     * Affecte l'action.
     *
     * @param action Valeur de l'action.
     */
    public void setAction(String action);

    /**
     * Affecte la cle.
     *
     * @param cle Valeur num�rique de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(long site);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine);



    /**
     * Affecte la cl� de r�f�rence.
     *
     * @param cleRef Valeur num�rique de la cl�.
     */
    public void setCleRef(long cleRef);

    /**
     * Affecte la cl� de r�f�rence 2.
     *
     * @param cleRef2 Valeur num�rique de la cl�.
     */
    public void setCleRef2(long cleRef2);

    /**
     * Affecte une cl� d'origine.
     *
     * @param cleOrigine Valeur num�rique de la cl�.
     */
    public void setCleOrigine(long cleOrigine);

    /**
     * Affecte un code de cr�ateur.
     *
     * @param createur Code du cr�ateur du dossier.
     */
    public void setCreateur(String createur);

    /**
     * Affecte la date d'acc�s.
     *
     * @param dateAcces Valeur de la date d'acc�s (yyyy-MM-dd).
     */
    public void setDateAcces(Timestamp dateAcces);

    /**
     * Affecte la date de cr�ation.
     *
     * @param dateCreation Valeur de la date (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un genre .
     *
     * @param genreOrigine Valeur du genre du dossier d'origine.
     */
    public void setGenreOrigine(String genreOrigine);

    /**
     * Affecte le genre de la premi�re r�f�rence.
     *
     * @param genreRef Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setGenreRef(String genreRef);

    /**
     * Affecte le genre de la deuxi�me r�f�rence.
     *
     * @param genreRef2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setGenreRef2(String genreRef2);

     /**
     * Affecte l'utilisateur.
     *
     * @param utilisateur Valeur de l'utilisateur en caract�re.
     */
    public void setUtilisateur(String utilisateur);

 

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(long hierarchie);

    /**
     * Affecte le site de r�f�rence.
     *
     * @param siteRef Valeur num�rique du site.
     */
    public void setSiteRef(long siteRef);

    /**
     * Affecte le site de r�f�rence 2.
     *
     * @param siteRef2 Valeur num�rique du site.
     */
    public void setSiteRef2(long siteRef2);


}