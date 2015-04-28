/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.vo.rapport.AccesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ActifIntervenantDossierRapportVO_CDX_0102;
import com.lotoquebec.cardex.business.vo.rapport.ContratsAutoexclusionDossierRapportVO_CDX_0060;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041;
import com.lotoquebec.cardex.business.vo.rapport.DelaiTraitementEnqueteRapportVO_CDX_0246;
import com.lotoquebec.cardex.business.vo.rapport.EmployeDossierRapportVO_CDX_0042;
import com.lotoquebec.cardex.business.vo.rapport.EnqueteurJournalRapportVO_CDX_0053;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxAutoexclusionActifRapportVO_CDX_0260;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxFraudeFondeRapportVO_CDX_0261;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxTricherieFondeRapportVO_CDX_0262;
import com.lotoquebec.cardex.business.vo.rapport.ReperageAutoexclusionDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SeveriteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SiteIntervenantRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SocietesInactivesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatutDossierRapportVO_CDX_0055;
import com.lotoquebec.cardex.generateurRapport.clientMystere.ClientMystereGenerateurRapport_CDX_0255;
import com.lotoquebec.cardex.generateurRapport.clientMystere.ClientMystereGenerateurRapport_CDX_0257;
import com.lotoquebec.cardex.generateurRapport.dossier.raq.GlobalRAQDossierGenerateurRapport_CDX_0070;
import com.lotoquebec.cardex.generateurRapport.dossier.raq.NatureRAQDossierGenerateurRapport_CDX_0070;
import com.lotoquebec.cardex.generateurRapport.dossier.raq.SansNatureRAQDossierGenerateurRapport_CDX_0070;
import com.lotoquebec.cardex.generateurRapport.entite.ActiviteQuotidienneRapportVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * Le RapportBusinessFacade offre les
 * services d'affaires concernant l'objet
 * Rapports Jasper.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 19:44:34 $
 */
public class RapportSessionFacade {


    /**
     * Recherche des rep�rages.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportReperageAccesInterdit(CardexAuthenticationSubject subject, ReperageAutoexclusionDossierRapportVO rapportDossierVO, String procedure) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportReperageAccesInterdit(subject, procedure, rapportDossierVO, rapportDossierVO.getSite());
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    public ResultSet cumulatifHebdomadaireEnquetesDossier(CumulatifHebdomadaireEnquetesDossierRapportVO_CDX_0041 rapportDossierVO,Connection connection) throws BusinessRuleException,
    BusinessResourceException {
		try {
			return FabriqueCardexDAO.getInstance().getRapportDAO().procedureSite("CARDEX_RAPPORT.SP_RAP_CUMUL_HEBDO", rapportDossierVO, rapportDossierVO.getSite(), connection);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
    
    /**
     * Recherche des contrats.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportContrats(ContratsAutoexclusionDossierRapportVO_CDX_0060 contratsAutoexclusionDossierRapportVO_CDX_0060, Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportContrats(contratsAutoexclusionDossierRapportVO_CDX_0060, connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur les employ�s li�s � des dossiers
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les employ�s li�s � des dossiers
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEmployeDossier(EmployeDossierRapportVO_CDX_0042 employeDossierRapportVO_CDX_0042,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEmployeDossier(employeDossierRapportVO_CDX_0042,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur le journal des enqu�teurs.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur le journal des enqu�teurs..
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportJournalEnquetes(EnqueteurJournalRapportVO_CDX_0053 enqueteurJournalRapportVO_CDX_0053,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportJournalEnquetes(enqueteurJournalRapportVO_CDX_0053,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
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
    public ResultSet rapportEpuration(long site, Connection connection, String procedure) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEpuration(site, connection, procedure);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur les rep�rages avec crit�re du nombre de rep�rages.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportReperageAutoexclusion(CardexAuthenticationSubject subject, ReperageAutoexclusionDossierRapportVO criteria, String procedure, long nombreReperages) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportReperageAutoexclusion(subject, criteria, procedure, nombreReperages);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    public ResultSet rapportProcedure(CritereRapportVO rapportVO, String procedure,Connection connection) throws BusinessRuleException,
    BusinessResourceException {
    	try {
    		return FabriqueCardexDAO.getInstance().getRapportDAO().rapportProcedure(rapportVO, procedure, connection);
    	} catch (DAOException dae) {
    		throw new BusinessResourceException(dae);
    	}
    }

    public ResultSet rapportSocietesInactives(SocietesInactivesRapportVO societesInactivesRapportVO,Connection connection) throws BusinessRuleException,
    BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportSocietesInactives(societesInactivesRapportVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    public Collection rapportEnqueteRetard(CritereRapportVO rapportVO) throws BusinessRuleException,
    BusinessResourceException {
    	try {
    		return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEnqueteRetard(rapportVO);
    	} catch (DAOException dae) {
    		throw new BusinessResourceException(dae);
    	}
    }

    public Collection rapportEnqueteTraitementRetard(CritereRapportVO rapportVO) throws BusinessRuleException,
    BusinessResourceException {
    	try {
    		return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEnqueteTraitementRetard(rapportVO);
    	} catch (DAOException dae) {
    		throw new BusinessResourceException(dae);
    	}
    }

    public ResultSet rapportProcedureVigilance(EntiteRapportVO entiteRapportVO, String procedure,Connection connection) throws BusinessRuleException,
    BusinessResourceException {
		try {
			return FabriqueCardexDAO.getInstance().getRapportDAO().rapportProcedureVigilance(entiteRapportVO, procedure,connection);
		} catch (DAOException dae) {
		throw new BusinessResourceException(dae);
		}
    }
    
    //Rapport sur les s�v�rit�s
    public ResultSet rapportSeverite(SeveriteRapportVO rapportVO, String procedure,Connection connection) throws BusinessRuleException,
    BusinessResourceException {
		try {
			return FabriqueCardexDAO.getInstance().getRapportDAO().rapportSeverite(rapportVO, procedure,connection);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
    }

    /**
     * Rapport sur les statuts des dossiers
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet statutDossiers(StatutDossierRapportVO_CDX_0055 statutDossierRapportVO_CDX_0055,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().statutDossiers(statutDossierRapportVO_CDX_0055,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport statistique sur le temps consacr�
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet tempsConsacre(StatistiqueDossierRapportVO statistiqueDossierRapportVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().tempsConsacre(statistiqueDossierRapportVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Rapport sur les enqu�tes de Loto-Qu�bec sur les personnes au registre (PAR).
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportEnqueteReclamation(String anneeMois,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEnqueteReclamation(anneeMois,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur les incidents de la DCSI.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportIncidentsDCSI(String annee,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportIncidentsDCSI(annee,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    
    /**
     * Rapport pour produire les listes de dossiers partag�s, par intervenant ou par responsable.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet listesPartage(String procedure,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().listesPartage(procedure,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les listes de dossiers actifs par intervenant.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet listesDossiersActifs(ActifIntervenantDossierRapportVO_CDX_0102 rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().listesDossiersActifs(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAcces(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAcces(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s aux sujets.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesSujets(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesSujets(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur l'analyse des acc�s aux dossiers (CDX_0072).
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAnalyseAccesDossiers(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAnalyseAccesDossiers(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s aux sujets par intervenant.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesSujetsIntervenant(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesSujetsIntervenant(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des intervenants.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesIntervenant(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesIntervenant(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des intervenants.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesDetailIntervenant(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesDetailIntervenant(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des superutilisateurs.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesSuperutilisateurs(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesSuperutilisateurs(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Rapport pour produire les rapports sur les acc�s des sujets.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesEmploye(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesEmploye(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s aux fournisseurs.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesFournisseur(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesFournisseur(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }    
    /**
     * Rapport pour produire les rapports sur les acc�s aux narrations.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesNarration(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesNarration(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport pour produire les rapports sur les acc�s des nouveaux intervenants.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet auditAccesNouveauxIntervenants(AccesRapportVO rapportDossierVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().auditAccesNouveauxIntervenants(rapportDossierVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Rapport pour produire les listes de dossiers actifs par enqu�teur de Loto-Qu�bec.
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet listesDossiersActifsEnqueteurLQ(Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().listesDossiersActifsEnqueteurLQ(connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }


    /**
     * Impression des fiches (sujet, soci�t� et dossier)
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportImpressionFiche(long cle, long site, String procedure,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportImpressionFiche(cle, site, procedure,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Impression du rapport sur les suivis 30 jours
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportSuivis(SiteIntervenantRapportVO rapportVO, String procedure,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportSuivis(rapportVO, procedure,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Impression du rapport sur les suivis CDX_0098
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection rapportSuivisEnquetes(SiteIntervenantRapportVO rapportVO,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportSuivisEnquetes(rapportVO,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Impression du rapport sur les suivis par intervenant et par site
     * @param subject Le sujet qui consulte les acc�s
     * @param criteria Les crit�res de recherche
     *
     * @return Rapport sur les rep�rages.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public ResultSet rapportSuivisIntervenant(SiteIntervenantRapportVO rapportVO, String procedure,Connection connection) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportSuivisIntervenant(rapportVO, procedure,connection);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des dossiers pour le rapport cumulatif (CDX_0146) et hebdomadaire (CDX_0147)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public RapportDossier produireListeTypeCategorie(CumulatifDossierRapportVO cumulatifDossierRapportVO) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().produireListeTypeCategorie(cumulatifDossierRapportVO);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des dossiers pour le rapport cumulatif (CDX_0143) et hebdomadaire (CDX_0144) pour Espacejeux
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public RapportDossier produireListeTypeCategorieEspacejeux(CritereRapportVO rapportVO) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().produireListeTypeCategorieEspacejeux(rapportVO);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche des dossiers fond�s pour le rapport cumulatif (CDX_0143)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public long produireFonde(RapportDossier rapportDossier, CumulatifDossierRapportVO cumulatifDossierRapportVO, String critereFonde) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().produireFonde(rapportDossier, cumulatifDossierRapportVO, critereFonde);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche des dossiers aux enqu�tes pour le rapport cumulatif (CDX_0143)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public long produireAuxEnquetes(RapportDossier rapportDossier, final CumulatifDossierRapportVO cumulatifDossierRapportVO) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().produireAuxEnquetes(rapportDossier, cumulatifDossierRapportVO);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Calcul du cumul des dossiers depuis le 1er janvier de l'ann�e en cours pour le rapport cumulatif (CDX_0143)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public long produireDossierCumul(RapportDossier rapportDossier, CumulatifDossierRapportVO cumulatifDossierRapportVO) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().produireDossierCumul(rapportDossier, cumulatifDossierRapportVO);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Pour la production manuelle du rapport sur la reconnaissance de plaques (pour le syst�me Autovue) CDX_0075
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public ResultSet produireRapportReconnaissance(String titre, StatistiqueDossierRapportVO reconnaissancePlaqueVO,Connection connection) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().produireRapportReconnaissance(titre, reconnaissancePlaqueVO,connection);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /**
     * Calcul du d�lai de traitement des dossiers d'habilitation s�curitaire (CDX_0246)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Collection rapportDelaiTraitementEnquetes(DelaiTraitementEnqueteRapportVO_CDX_0246 delaiTraitementEnqueteRapportVO_CDX_0246) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportDelaiTraitementEnquetes(delaiTraitementEnqueteRapportVO_CDX_0246);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Rapport sur les contrats d'autoexclusion actifs d'Espacejeux (CDX_0260)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public ResultSet rapportEspaceJeuxAutoexclusionActif(EspaceJeuxAutoexclusionActifRapportVO_CDX_0260 espaceJeuxAutoexclusionActifRapportVO_CDX_0260,Connection connection) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEspaceJeuxAutoexclusionActif(espaceJeuxAutoexclusionActifRapportVO_CDX_0260,connection);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur les dossiers de fraude fond� d'Espacejeux (CDX_0261)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public ResultSet rapportEspaceJeuxFraudeFonde(EspaceJeuxFraudeFondeRapportVO_CDX_0261 espaceJeuxFraudeFondeRapportVO_CDX_0261,Connection connection) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEspaceJeuxFraudeFonde(espaceJeuxFraudeFondeRapportVO_CDX_0261,connection);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur les dossiers de tricherie fond� d'Espacejeux (CDX_0262)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public ResultSet rapportEspaceJeuxTricherieFonde(EspaceJeuxTricherieFondeRapportVO_CDX_0262 espaceJeuxTricherieFondeRapportVO_CDX_0262,Connection connection) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEspaceJeuxTricherieFonde(espaceJeuxTricherieFondeRapportVO_CDX_0262,connection);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /**
     * Rapport sur les statistiques par endroits regroup�s (CDX_0149)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public ResultSet rapportEndroitsRegroupes(StatistiqueDossierRapportVO statistiqueDossierRapportVO,Connection connection) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportEndroitsRegroupes(statistiqueDossierRapportVO,connection);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /**
     * Tableau sur les statistiques par endroits regroup�s (CDX_0149)
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public ResultSet rapportTableauEndroitsRegroupes(StatistiqueDossierRapportVO statistiqueDossierRapportVO,Connection connection) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getRapportDAO().rapportTableauEndroitsRegroupes(statistiqueDossierRapportVO,connection);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    public JasperPrint globalRAQCDX_0070(CardexAuthenticationSubject subject, ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO) throws BusinessException{
		try {
			return (new GlobalRAQDossierGenerateurRapport_CDX_0070()).executer(subject, activiteQuotidienneRapportVO, null);
		} catch (JRException e) {
			throw new BusinessResourceException(e);
		}
    }
    
    public JasperPrint natureRAQRapportCDX_0070(CardexAuthenticationSubject subject, ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO) throws BusinessException{
		try {    	
	    	return (new NatureRAQDossierGenerateurRapport_CDX_0070()).executer(subject, activiteQuotidienneRapportVO, null);
		} catch (JRException e) {
			throw new BusinessResourceException(e);
		}
    }
    
    public JasperPrint sansNatureRAQRapportCDX_0070(CardexAuthenticationSubject subject, ActiviteQuotidienneRapportVO activiteQuotidienneRapportVO) throws BusinessException{
		try {    	
	    	return (new SansNatureRAQDossierGenerateurRapport_CDX_0070()).executer(subject, activiteQuotidienneRapportVO, null);
		} catch (JRException e) {
			throw new BusinessResourceException(e);
		}
    }
    
    public JasperPrint clientMystereRapportCDX_0255(CardexAuthenticationSubject subject) throws BusinessResourceException{
		try {
			return (new ClientMystereGenerateurRapport_CDX_0255()).executer(subject, null, null);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessResourceException(e);
		} catch (JRException e) {
			e.printStackTrace();
			throw new BusinessResourceException(e);
		}
    }
 
    public JasperPrint[] clientMystereRapportCDX_0257(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	JasperPrint[] prints = new JasperPrint[5];
    	
    	
		try {
			for (int i = 0; i < 5; i++)
				prints[i] = new ClientMystereGenerateurRapport_CDX_0257(i).executer(subject, null, null);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw new BusinessResourceException(e);
		} catch (JRException e) {
			e.printStackTrace();
			throw new BusinessResourceException(e);
		}

    	return prints;
    }
    
}

