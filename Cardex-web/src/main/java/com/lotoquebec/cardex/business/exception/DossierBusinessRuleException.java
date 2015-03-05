/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.exception;

import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette exception survient lorsque les r�gles d'affaire
 * d'un objet dossier ne sont pas respect�es.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.9 $, $Date: 2002/04/09 20:44:32 $
 */
public class DossierBusinessRuleException
    extends BusinessRuleException {

    /**
     * Un dossier ne peut pas �tre reli� � lui-m�me.
     */
    public static final int DOSSIER_RELIE_A_LUI_MEME = 0;

    /**
     * Un dossier ne peut pas �tre reli� plus d'une fois � un m�me dossier.
     */
    public static final int DOSSIER_RELIE_PLUS_UNE_FOIS = 1;

    /**
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     */
    public static final int DATE_DEBUT_INVALIDE = 2;

    /**
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     */
    public static final int DATE_DEBUT_SUPERIEUR_DATE_FIN = 3;

    /**
     * Le statut ne peut �tre mis inactif si des narrations
     * n'ont pas encore �t� approuv�s.
     */
    public static final int NARRATIONS_NON_APPROUVE_DOSSIER_INACTIF = 4;

    /**
     * Le statut ne peut �tre mis inactif si des suivis
     * n'ont pas encore �t� approuv�s.
     */
    public static final int SUIVIS_NON_APPROUVE_DOSSIER_INACTIF = 5;

    /**
     * Un dossier avec inscription doit �tre li�
     * � au moins une inscription.
     */
    public static final int DOSSIER_SANS_INSCRIPTIONS = 6;

    /**
     * Un dossier avec inscription doit �tre li�
     * � au moins une inscription.
     */
    public static final int MOT_DE_PASSE_INVALID = 7;

    /**
     * Un dossier avec inscription doit �tre li�
     * � au moins un sujet.
     */
    public static final int DOSSIER_SANS_SUJETS = 8;

    /**
     * Un dossier de rep�rage ne doit pas �tre li� � un sujet.
     * � au moins un sujet.
     */
    public static final int DOSSIER_REPERAGE = 9;

    /**
     * Le statut ne peut �tre mis inactif s'il n'y a pas de conclustion
     * dans un dossier du Comit� de vigilance
     */
    public static final int MANQUE_CONCLUSION_EVALUATION_DOSSIER_INACTIF = 10;

    /**
     * Le statut ne peut �tre mis inactif s'il n'y a pas de date de fin
     * dans un dossier du Comit� de vigilance
     */
    public static final int MANQUE_DATE_FIN_EVALUATION_DOSSIER_INACTIF = 11;
 
    /**
     * Un dossier d'autoexclusion d'Espacejeux doit �tre de la cat�gorie Jeux en ligne.
     * 
     */
    public static final int AUTOEXCLUSION_ESPACEJEUX = 12;

    /**
     * Un dossier d'autoexclusion d'Espacejeux doit �tre de la cat�gorie Jeux en ligne.
     * 
     */
    public static final int FONDE_DOSSIER_INACTIF = 13;

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @see java.lang.Exception
     */
    public DossierBusinessRuleException() {
        super();
    }

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public DossierBusinessRuleException(String msg) {
        super(msg);
    }

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     *
     * @see java.lang.Exception
     */
    public DossierBusinessRuleException(Exception e) {
        super(e);
    }

    /**
     * Constructor a DossierBusinessRuleExcpetion instance
     *
     * @param e Ancestor of this exception
     * @param msg Message
     *
     * @see java.lang.Exception
     */
    public DossierBusinessRuleException(Exception e, String msg) {
        super(e, msg);
    }

}
