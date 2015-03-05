/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;

import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.facade.RapportSessionFacade;
import com.lotoquebec.cardex.business.facade.rapport.SiteRAQRapportCDX_0070;
import com.lotoquebec.cardex.business.vo.rapport.AccesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ActifIntervenantDossierRapportVO_CDX_0102;
import com.lotoquebec.cardex.business.vo.rapport.ContratsAutoexclusionDossierRapportVO_CDX_0060;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041;
import com.lotoquebec.cardex.business.vo.rapport.DelaiTraitementEnqueteRapportVO_CDX_0246;
import com.lotoquebec.cardex.business.vo.rapport.EmployeDossierRapportVO_CDX_0042;
import com.lotoquebec.cardex.business.vo.rapport.EnqueteurJournalRapportVO_CDX_0053;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxAutoexclusionActifRapportVO_CDX_0260;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxFraudeFondeRapportVO_CDX_0261;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxTricherieFondeRapportVO_CDX_0262;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ReperageAutoexclusionDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SeveriteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SiteIntervenantRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SocietesInactivesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatutDossierRapportVO_CDX_0055;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le AccesBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * acces.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 19:44:32 $
 */
public class RapportBusinessDelegate extends BusinessDelegate {
    RapportSessionFacade rapportSessionFacade;

    /**
     * Construit une instance de AccesBusinessDelegate
     */
    public RapportBusinessDelegate() {
        this.rapportSessionFacade = new RapportSessionFacade();
    }

    /**
     * Rapport sur les rep�rages
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportReperageAccesInterdit(CardexAuthenticationSubject subject, ReperageAutoexclusionDossierRapportVO rapportDossierVO, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportReperageAccesInterdit(subject, rapportDossierVO, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    public ResultSet cumulatifHebdomadaireEnquetesDossier(CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041 rapportDossierVO) throws BusinessException,
    BusinessResourceException {
    	try {
    		return rapportSessionFacade.cumulatifHebdomadaireEnquetesDossier(rapportDossierVO);
    	} catch (BusinessRuleException e) {
    		throw handleAccesBusinessRuleException(e);
    	}
    }    

    /**
     * Rapport sur les contrats
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportContrats(ContratsAutoexclusionDossierRapportVO_CDX_0060 contratsAutoexclusionDossierRapportVO_CDX_0060) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportContrats(contratsAutoexclusionDossierRapportVO_CDX_0060);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les employ�s li�s � des dossiers
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des employ�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEmployeDossier(EmployeDossierRapportVO_CDX_0042 employeDossierRapportVO_CDX_0042) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEmployeDossier(employeDossierRapportVO_CDX_0042);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les donn�es � �purer.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEpuration(long site, Connection connection, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEpuration(site, connection, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur le journal des enqu�teurs
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des dossiers.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportJournalEnquetes(EnqueteurJournalRapportVO_CDX_0053 enqueteurJournalRapportVO_CDX_0053) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportJournalEnquetes(enqueteurJournalRapportVO_CDX_0053);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les rep�rages avec crit�re du nombre de rep�rages � partir du menu des rapports.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportReperageAutoexclusion(CardexAuthenticationSubject subject, ReperageAutoexclusionDossierRapportVO criteria, String procedure, long nombreReperages) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportReperageAutoexclusion(subject, criteria, procedure, nombreReperages);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les rencontres
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportProcedure(RapportVO rapportVO, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportProcedure(rapportVO, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les d�taillants devenus inactifs
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des soci�t�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportSocietesInactives(SocietesInactivesRapportVO societesInactivesRapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportSocietesInactives(societesInactivesRapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les enqu�tes en retard (CDX_0247)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportEnqueteRetard(RapportVO rapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEnqueteRetard(rapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les enqu�tes qui ont �t� trait�es en retard (CDX_0248)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportEnqueteTraitementRetard(RapportVO rapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEnqueteTraitementRetard(rapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les dossiers de vigilance
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportProcedureVigilance(EntiteRapportVO entiteRapportVO, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportProcedureVigilance(entiteRapportVO, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les s�v�rit�s
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportSeverite(SeveriteRapportVO rapportVO, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportSeverite(rapportVO, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les statuts des dossiers
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet statutDossiers(StatutDossierRapportVO_CDX_0055 statutDossierRapportVO_CDX_0055) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.statutDossiers(statutDossierRapportVO_CDX_0055);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport statistique sur le temps consacr�
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet tempsConsacre(StatistiqueDossierRapportVO statistiqueDossierRapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.tempsConsacre(statistiqueDossierRapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
   
    /**
     * Rapport sur les enqu�tes de Loto-Qu�bec sur les personnes au registre (PAR).
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEnqueteReclamation(String anneeMois) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEnqueteReclamation(anneeMois);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les incidents de la DCSI.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportIncidentsDCSI(String annee) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportIncidentsDCSI(annee);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les listes de dossiers partag�s, par intervenant ou par responsable.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet listesPartage(String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.listesPartage(procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les listes de dossiers actifs par intervenant.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet listesDossiersActifs(ActifIntervenantDossierRapportVO_CDX_0102 rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.listesDossiersActifs(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAcces(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAcces(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s aux sujets.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesSujets(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesSujets(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur l'analyse des acc�s aux dossiers (CDX_0072).
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAnalyseAccesDossiers(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAnalyseAccesDossiers(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s aux sujets par intervenant.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesSujetsIntervenant(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesSujetsIntervenant(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des intervenants.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesIntervenant(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesIntervenant(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports d�taill�s sur les acc�s des intervenants.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesDetailIntervenant(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesDetailIntervenant(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des superutilisateurs.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesSuperutilisateurs(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesSuperutilisateurs(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des sujets.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesEmploye(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesEmploye(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Rapport pour produire les rapports sur les acc�s des sujets.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesFournisseur(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesFournisseur(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    /**
     * Rapport pour produire les rapports sur les acc�s des narrations.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesNarration(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesNarration(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des nouveaux intervenants.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesNouveauxIntervenants(AccesRapportVO rapportDossierVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.auditAccesNouveauxIntervenants(rapportDossierVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Rapport pour produire les listes de dossiers actifs par enqu�teur de Loto-Qu�bec.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet listesDossiersActifsEnqueteurLQ() throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.listesDossiersActifsEnqueteurLQ();
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Impression des fiches (sujet, soci�t� et dossier)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportImpressionFiche(long cle, long site, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportImpressionFiche(cle, site, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Impression du rapport sur les suivis par intervenant et par site
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportSuivis(SiteIntervenantRapportVO rapportVO, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportSuivis(rapportVO, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Impression du rapport sur les suivis CDX_0098
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportSuivisEnquetes(SiteIntervenantRapportVO rapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportSuivisEnquetes(rapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

     /* Impression du rapport sur les suivis 30 jours
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportSuivisIntervenant(SiteIntervenantRapportVO rapportVO, String procedure) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportSuivisIntervenant(rapportVO, procedure);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche de dossiers pour les rapports cumulatif (CDX_0146) et hebdomadaire (CDX_0147)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public RapportDossier produireListeTypeCategorie(CumulatifDossierRapportVO cumulatifDossierRapportVO) throws BusinessException,
                                    BusinessResourceException {
        try {
            return rapportSessionFacade.produireListeTypeCategorie(cumulatifDossierRapportVO);
        } catch (BusinessRuleException e) {
        	throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Recherche de dossiers pour les rapports cumulatif (CDX_0143) et hebdomadaire (CDX_0144) pour Espacejeux
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public RapportDossier produireListeTypeCategorieEspacejeux(RapportVO rapportVO) throws BusinessException,
                                    BusinessResourceException {
        try {
            return rapportSessionFacade.produireListeTypeCategorieEspacejeux(rapportVO);
        } catch (BusinessRuleException e) {
        	throw handleAccesBusinessRuleException(e);
        }
    }

    public long produireFonde(RapportDossier rapportDossier,
    		CumulatifDossierRapportVO cumulatifDossierRapportVO,
            String critereFonde) throws BusinessException,
            BusinessResourceException {
    	try {
			return rapportSessionFacade.produireFonde(rapportDossier, cumulatifDossierRapportVO, critereFonde);
		} catch (BusinessRuleException e) {
			throw handleAccesBusinessRuleException(e);
		}
    }

    public long produireAuxEnquetes(RapportDossier rapportDossier, final CumulatifDossierRapportVO cumulatifDossierRapportVO) throws BusinessException,
            BusinessResourceException {
    	try {
			return rapportSessionFacade.produireAuxEnquetes(rapportDossier, cumulatifDossierRapportVO);
		} catch (BusinessRuleException e) {
			throw handleAccesBusinessRuleException(e);
		}
    }

    //Calcule le nombre de dossiers cr��s depuis le 1er janvier
    public long produireDossierCumul(RapportDossier rapportDossier, CumulatifDossierRapportVO cumulatifDossierRapportVO) throws BusinessException,
            BusinessResourceException {
    	try {
			return rapportSessionFacade.produireDossierCumul(rapportDossier, cumulatifDossierRapportVO);
		} catch (BusinessRuleException e) {
			throw handleAccesBusinessRuleException(e);
		}
    }
    
    public Collection rapportDelaiTraitementEnquetes(DelaiTraitementEnqueteRapportVO_CDX_0246 rapportDossierVO) throws BusinessException,
    BusinessResourceException {
    	try {
    		return rapportSessionFacade.rapportDelaiTraitementEnquetes(rapportDossierVO);
    	} catch (BusinessRuleException e) {
    		throw handleAccesBusinessRuleException(e);
    	}
    }    

    //Pour la production manuelle du rapport sur la reconnaissance de plaques (pour le syst�me Autovue)
    public ResultSet produireRapportReconnaissance(String titre, StatistiqueDossierRapportVO reconnaissancePlaqueVO ) throws BusinessException,
            BusinessResourceException {
    	try {
			return rapportSessionFacade.produireRapportReconnaissance(titre, reconnaissancePlaqueVO);
		} catch (BusinessRuleException e) {
			throw handleAccesBusinessRuleException(e);
		}
    }
    
    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
     * correspondance entre les codes de r�gles d'affaires re�us d'une
     * BusinessRuleException et les messsages qui doivent �tre affich�
     * � un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de r�gles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
     */
    private BusinessException handleAccesBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Rapport sur les contrats d'autoexclusion actifs d'Espacejeux (CDX_0260)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEspaceJeuxAutoexclusionActif(EspaceJeuxAutoexclusionActifRapportVO_CDX_0260 espaceJeuxAutoexclusionActifRapportVO_CDX_0260) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEspaceJeuxAutoexclusionActif(espaceJeuxAutoexclusionActifRapportVO_CDX_0260);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les dossiers de fraude fond� d'Espacejeux (CDX_0261)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEspaceJeuxFraudeFonde(EspaceJeuxFraudeFondeRapportVO_CDX_0261 espaceJeuxFraudeFondeRapportVO_CDX_0261) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEspaceJeuxFraudeFonde(espaceJeuxFraudeFondeRapportVO_CDX_0261);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les dossiers de tricherie fond� d'Espacejeux (CDX_0262)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEspaceJeuxTricherieFonde(EspaceJeuxTricherieFondeRapportVO_CDX_0262 espaceJeuxTricherieFondeRapportVO_CDX_0262) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEspaceJeuxTricherieFonde(espaceJeuxTricherieFondeRapportVO_CDX_0262);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Rapport sur les statistiques par endroits regroup�s (CDX_0149)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des totaux.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEndroitsRegroupes(StatistiqueDossierRapportVO statistiqueDossierRapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportEndroitsRegroupes(statistiqueDossierRapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Tableau sur les statistiques par endroits regroup�s (CDX_0280)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des totaux.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportTableauEndroitsRegroupes(StatistiqueDossierRapportVO statistiqueDossierRapportVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return rapportSessionFacade.rapportTableauEndroitsRegroupes(statistiqueDossierRapportVO);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    public JasperPrint siteRAQCDX_0070(CardexAuthenticationSubject subject, Date debutDate, Date finDate, Long site) throws BusinessResourceException{
		return rapportSessionFacade.siteRAQCDX_0070(subject, debutDate, finDate, site);
    }
    
}

