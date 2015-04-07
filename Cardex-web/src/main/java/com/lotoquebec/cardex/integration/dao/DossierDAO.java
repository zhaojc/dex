package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.CriteresRechercheJournal;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Journal;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.JournalVO;
import com.lotoquebec.cardex.business.vo.LiaisonBilletSocieteVO;
import com.lotoquebec.cardex.business.vo.RapportJournalVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.business.vo.rapport.AmbulanceDossierRapportVO;
import com.lotoquebec.cardex.integration.dao.cache.GenererNumeroSequenceDossier;
import com.lotoquebec.cardex.integration.dao.sql.rapport.AmbulanceDossierRapportSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.DossierCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.DossierCountSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.DossierPartageSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.JournalCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.JournalCountSQL;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.NatureInscriptionCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.securite.UIComponentState;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;
import com.lotoquebec.cardexCommun.util.ListeCache;


/**
 * Offre tout les services de r�cup�ration des informations d'une base de donn�e
 * Oracle, relatives aux dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.72 $, $Date: 2002/04/25 15:42:36 $
 * @see com.lotoquebec.cardex.integration.DossierDAO
 */
public class DossierDAO {
 
	private final Logger      log =
        LoggerFactory.getLogger((DossierDAO.class));

     /**
     * Modification d'un dossier, appel� par la m�thode "insert" ou "update".
     * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
     * ou d'une mise � jour ("U").
     * Proc�dure appel�e : CARDEX_DOC.SP_E_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier saisi � l'�cran.
     * @param action  java.lang.String : U ou I.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier Une instance du dossier associ�e.
     */
     private Dossier editDossier(CardexAuthenticationSubject subject, Dossier dossier,String action, Connection connection) throws DAOException {
         CallableStatement callableStatement = null;
         try {
        	 
         	if ("I".equals(action)){
         		CardexUser  cardexUser = (CardexUser)subject.getUser();
             	GenererNumeroSequenceDossier genererNumeroSequenceDossier = GenererNumeroSequenceDossier.getInstance();
				String sequence = genererNumeroSequenceDossier.obtenirNumeroSequenceCardex(cardexUser.getSite(), connection);
				dossier.setNumeroCardex(sequence);
         	}
         	
            callableStatement = connection.prepareCall(
                     "begin CARDEX_DOC.SP_E_DO_DOSSIER "
                     + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                     + ",?,?,?,?,?,?,?,?,?,?); end;");
            callableStatement.setString(1,action);
            callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
            callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
 			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            OracleDAOUtils.setLong(callableStatement, 2, dossier.getCle());
            OracleDAOUtils.setLong(callableStatement, 3, dossier.getSite());
             //v_do_numero_dossier
             callableStatement.setString(4, dossier.getNumeroCardex());
             //i_ca_cle
             OracleDAOUtils.setLong(callableStatement, 5,
                     dossier.getCategorie());
             //i_se_cle
             OracleDAOUtils.setLong(callableStatement, 6, dossier.getSeverite());
             //i_cc_cle
             OracleDAOUtils.setLong(callableStatement, 7,
                     dossier.getConfidentialite());
             //Niveau d'autorit� (i_nh_cle)
             OracleDAOUtils.setLong(callableStatement, 8,
                     dossier.getHierarchie());
             //i_pe_cle
             OracleDAOUtils.setLong(callableStatement, 9, dossier.getPeriode());
             //i_st_cle
             OracleDAOUtils.setLong(callableStatement, 10, dossier.getStatut());
             //v_do_mot_passe
             callableStatement.setString(11, dossier.getMotPasse());
             //d_do_date_debut
             callableStatement.setTimestamp(12,
                     (Timestamp)(dossier.getDateDebut()));
             //d_do_date_fin
             callableStatement.setTimestamp(13,
                     (Timestamp)(dossier.getDateFin()));
             //v_do_duree
             callableStatement.setString(14, dossier.getDuree());
             //v_do_ancienne_reference
             callableStatement.setString(15,
                     dossier.getNumeroDossier().trim().toUpperCase());
             //v_do_reference_video
             callableStatement.setString(16, dossier.getReferenceVideo().trim().toUpperCase());
             //d_do_date_rapportee
             callableStatement.setTimestamp(17,
                     (Timestamp)(dossier.getDateRapportee()));
             //i_do_classe
             OracleDAOUtils.setLong(callableStatement, 18, dossier.getClasse());
             //i_do_race
             OracleDAOUtils.setLong(callableStatement, 19, dossier.getRace());
             //v_do_lieu
             callableStatement.setString(20, dossier.getDescriptif().trim().toUpperCase());
             //i_or_cle
             OracleDAOUtils.setLong(callableStatement, 21, dossier.getEndroit());
             //i_cr_cle
             OracleDAOUtils.setLong(callableStatement, 22,
                     dossier.getLocalisation());
             //v_do_reference1 : r�f�rence 1
             callableStatement.setString(23, dossier.getReference1().trim().toUpperCase());
             //v_do_reference2
             callableStatement.setString(24, "");
             //v_do_assigne_a
             callableStatement.setString(25, dossier.getIntervenant().trim());
             //d_do_date_assignation
             callableStatement.setTimestamp(26,
                     (Timestamp)(dossier.getDateAssignation()));
             //d_do_date_evenement
             callableStatement.setTimestamp(27,
                     (Timestamp)(dossier.getDateEvenement()));
             //v_do_reference3 : r�f�rence 2
             callableStatement.setString(28, dossier.getReference2().trim().toUpperCase());
             //v_do_reference4
             callableStatement.setString(29, dossier.getReference4().trim());
             //v_do_reference5 : r�f�rence 3
             callableStatement.setString(30, dossier.getReference3().trim().toUpperCase());
             //I_do_fonde
             callableStatement.setString(31, dossier.getFondeDescription());
             //v_do_reference6
             callableStatement.setString(32, dossier.getReference6());
             //v_do_reference7
             callableStatement.setString(33, dossier.getReference7());
             //i_do_fonde
             OracleDAOUtils.setLong(callableStatement, 34, dossier.getFonde());
             //i_rf_cle : r�f�rence vid�o
             OracleDAOUtils.setLong(callableStatement, 35,
                     dossier.getTypeVideo());
             if (!OracleDAOUtils.isEmpty(dossier.getEnregistrementConserve())) {
             	callableStatement.setString(36, dossier.getEnregistrementConserve());
             }else{
             	callableStatement.setString(36,GlobalConstants.SQL.FALSE_FIXE);
             }
             if (!OracleDAOUtils.isEmpty(dossier.getEnregistrementNumerique())) {
             	callableStatement.setString(37, dossier.getEnregistrementNumerique());
             }else{
             	callableStatement.setString(37,GlobalConstants.SQL.FALSE_FIXE);
             }
             //L_DO_ORIGINE
             callableStatement.setLong(38, dossier.getOrigine());
             callableStatement.execute();
             //connection.commit();
             connection.setAutoCommit(true);
             Dossier newDossier = new DossierVO();
             newDossier.setCle(callableStatement.getLong(2));
             newDossier.setSite(callableStatement.getLong(3));
 			 newDossier.setNumeroCardex(callableStatement.getString(4));
             return newDossier;
         }
         catch (SQLException se) {
           throw new DAOException(se);
         } finally {
 			if(callableStatement != null) {
 				try {
 						callableStatement.close();
 			    } catch (java.sql.SQLException e) {
                     throw new DAOException(e);
 		        }
 			}
         }
     }     
    private Dossier editDossier(CardexAuthenticationSubject subject,
            Dossier dossier,String action) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        try {

            return editDossier(subject, dossier, action, connection);
        } finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }

    /**
     * Appel de la m�thode editDossier pour la cr�ation d'un dossier.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier saisi � l'�cran.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier Une dossier correspondant � celui ins�r�.
     */
    public Dossier insert(CardexAuthenticationSubject subject, Dossier dossier, Connection connection)
			throws DAOException {
    	return editDossier(subject, dossier,"I", connection);
	}    
    public Dossier insert(CardexAuthenticationSubject subject,Dossier dossier)
            throws DAOException {
    	return editDossier(subject,dossier,"I");
    }


    /**
     * D�termine si un dossier est avec inscription.
     *
     * @param subject Le sujet qui cr�� le dossier
     * @param info Le dossier
     *
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     */
    public boolean isAvecInscription(CardexAuthenticationSubject subject,
                       Dossier dossier) throws DAOException {

        log.debug("isAvecInscription()");
      	ListeCache listeCache = ListeCache.getInstance();
		
		try {
			String inscriptionFlag = listeCache.obtenirLabel(subject, dossier.getNature(), new NatureInscriptionCle());
			return (inscriptionFlag != null && "1".equals(inscriptionFlag.trim()));
		} catch (BusinessResourceException e) {
			throw new DAOException(e);
		}
   }


    /**
     * Appel de la m�thode editDossier pour la mise � jour d'un dossier.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier saisi � l'�cran.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject,Dossier dossier)
            throws DAOException {
      editDossier(subject,dossier,"U");
    }

    /**
     * Recherche directe d'un dossier par sa cl� unique avec �criture d'un audit.
     * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L2_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Dossier : Dossier � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public Dossier findAcces(CardexAuthenticationSubject subject,Dossier criteria)
            throws DAOException {
        Connection connection = null;
 		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        DossierVO dossier = new DossierVO();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        ListeCache listeCache = ListeCache.getInstance();
        boolean retry = false;
		int numOfRetries = 0;

           try {
            connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIRE_DOC.SP_L_DO_DOSSIER (?,?,?); end;");
            callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du r�sultat retourn�
            if (resultSet.next()) {
                //log.debug("   [LabelValueBean label='" + label
                //      + "' value='"+value+"']");
				dossier = traitementResultSetFind(resultSet);
            }

             // D�termine si le dossier est modifiable
             //  - Niveau d'authorite de l'utilisateur sup�rieur � celui du dossier
             //          ou
             //� - L'utilisateur est l'intervenant assign�
             //  - L'utilisateur est du m�me site que le dossier.
			 //D�cembre 2005 : un dossier inactif ne peut plus �tre modifi� par l'intervenant assign�
			 //et le dossier doit �tre du m�me site que celui de l'utilisateur, sauf si une r�gle ClearTrust 
			 //autorise l'acc�s au champ Statut.
            dossier.setModifiable(isModifiable(subject, dossier));
	             
             // D�termine si le dossier est avec inscription
             dossier.setInscription(isAvecInscription(subject,dossier));

             if (log.isDebugEnabled()){
               log.debug("Détermine si le dossier est modifiable");
               log.debug("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
               log.debug("  dossier.hierarchie = '"+dossier.getHierarchie()+"'");
               log.debug("  user.code = '"+user.getCode()+"'");
               log.debug("  dossier.intervenant = '"+dossier.getIntervenant()+"'");
               log.debug("  user.site = '"+user.getSite()+"'");
               log.debug("  dossier.site = '"+dossier.getSite()+"'");
               log.debug("  dossier.statut = '"+dossier.getStatut()+"'");
               log.debug("  GlobalConstants.Statut.DOSSIER_ACTIF = '"+GlobalConstants.Statut.DOSSIER_ACTIF+"'");
               log.debug("  dossier.isModifiable = '"+dossier.isModifiable()+"'");
               log.debug("D�termine si le dossier est avec inscription");
               log.debug("  dossier.isInscription = '"+dossier.isInscription()+"'");
             }

        }catch (SQLException se) {
            throw new DAOException(se);
        } catch (BusinessResourceException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
        return dossier;
    }
    
    /**
     * Recherche directe d'un dossier par sa cl� unique sans �criture d'un audit.
     * Cette proc�dure est n�cessaire en raison du mode web de l'application.  Dans ce 
     * mode, chaque fois qu'on revient au dossier (par exemple, apr�s une nouvelle narration),
     * une relecture du dossier est effectu�e, ce qui g�n�re une entr�e inutile dans la
     * table AC_ACCES et fausse les donn�es d'historique du dossier.
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Dossier : Dossier � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public Dossier find(CardexAuthenticationSubject subject,Dossier criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        DossierVO dossier = new DossierVO();
        boolean retry = false;
		int numOfRetries = 0;
		ListeCache listeCache = ListeCache.getInstance();
		CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();

        try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_DO_DOSSIER (?,?,?); end;");
            callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du r�sultat retourn�
            if (resultSet.next()) {
                //log.debug("   [LabelValueBean label='" + label
                //      + "' value='"+value+"']");
				dossier = traitementResultSetFind(resultSet);
            }

             // D�termine si le dossier est modifiable
             //  - Niveau d'authorite de l'utilisateur sup�rieur � celui du dossier
             //          ou
             //� - L'utilisateur est l'intervenant assign�
             //  - L'utilisateur est du m�me site que le dossier.
			 //D�cembre 2005 : un dossier inactif ne peut plus �tre modifi� par l'intervenant assign�
			 //et le dossier doit �tre du m�me site que celui de l'utilisateur, sauf si une r�gle ClearTrust 
			 //autorise la r�activation du dossier.
            dossier.setModifiable(isModifiable(subject, dossier));

             // D�termine si le dossier est avec inscription
             dossier.setInscription(isAvecInscription(subject,dossier));

             if (log.isDebugEnabled()){
               log.debug("D�termine si le dossier est modifiable");
               log.debug("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
               log.debug("  dossier.hierarchie = '"+dossier.getHierarchie()+"'");
               log.debug("  user.code = '"+user.getCode()+"'");
               log.debug("  dossier.intervenant = '"+dossier.getIntervenant()+"'");
               log.debug("  user.site = '"+user.getSite()+"'");
               log.debug("  dossier.site = '"+dossier.getSite()+"'");
               log.debug("  dossier.statut = '"+dossier.getStatut()+"'");
               log.debug("  GlobalConstants.Statut.DOSSIER_ACTIF = '"+GlobalConstants.Statut.DOSSIER_ACTIF+"'");
               log.debug("  dossier.isModifiable = '"+dossier.isModifiable()+"'");
               log.debug("D�termine si le dossier est avec inscription");
               log.debug("  dossier.isInscription = '"+dossier.isInscription()+"'");
             }

          }catch (SQLException se) {
            throw new DAOException(se);
        } catch (BusinessResourceException e) {
			e.printStackTrace();
		}
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
        
        return dossier;
    }   
    
    // D�termine si le dossier est modifiable
    //  - Niveau d'authorite de l'utilisateur sup�rieur � celui du dossier
    //          ou
    //� - L'utilisateur est l'intervenant assign�
    //  - L'utilisateur est du m�me site que le dossier.
	 //D�cembre 2005 : un dossier inactif ne peut plus �tre modifi� par l'intervenant assign�
	 //et le dossier doit �tre du m�me site que celui de l'utilisateur, sauf si une r�gle ClearTrust 
	 //autorise la r�activation du dossier.    
    public boolean isModifiable(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessResourceException{
		ListeCache listeCache = ListeCache.getInstance();
		CardexUser user = (CardexUser)subject.getUser();
    	
        boolean isHierachieAccessible = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NIVEAU_HIERARCHIQUE, GlobalConstants.ActionSecurite.SELECTION), String.valueOf(dossier.getHierarchie()));
        
        boolean isGenreAccessible = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, GlobalConstants.ActionSecurite.MODIFICATION), String.valueOf(dossier.getGenre()));
        boolean isNatureAccessible = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, GlobalConstants.ActionSecurite.MODIFICATION), String.valueOf(dossier.getNature()));
        
        //Si l'utilisateur n'a pas le droit de voir le bouton Enregistrer, on enmp�che la modification.
	    UIComponentState boutonEnregistrerVisible = GestionnaireSecurite.obtenirURLUIComponentState(subject, "/dossier/update.do");
	    if(!String.valueOf(boutonEnregistrerVisible).equals(String.valueOf(UIComponentState.ENABLED))){
	    	return false;
	    }

        if (isGenreAccessible == false || isNatureAccessible == false)
        	return false;
        
		if(!GlobalConstants.Statut.DOSSIER_INACTIF.equals(Long.toString(dossier.getStatut()))){
            if ( (isHierachieAccessible && user.getSite() == dossier.getSite())
            || (user.getCode().equals(dossier.getIntervenant()) && user.getSite() == dossier.getSite()) ){
                   return true;
               }
        }else{
           //On v�rifie la r�gle d'acc�s pour r�activer un dossier.
       	 UIComponentState state = GestionnaireSecuriteCardex.obtenirAdhocUIComponentState(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_RETIRER);
			 String enabled   = String.valueOf(UIComponentState.ENABLED);
			 String etat = String.valueOf(state);
            if ( (isHierachieAccessible && user.getSite() == dossier.getSite())
            || (etat.trim().equals(enabled.trim()) && user.getSite() == dossier.getSite()) ){
					return true;
               }
        }
		
		return false;
    }
    
    /**
     * Recherche de l'audit des changements d'un dossier.
     * 
     * Proc�dure appel�e : CARDEX_AUDIT.SP_L_AUDIT_DOSSIER
     * Date de cr�ation : (2011-03-03)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Dossier : Dossier � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public List audit(CardexAuthenticationSubject subject,Dossier criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        DossierVO dossier = new DossierVO();
		//ListeCache listeCache = ListeCache.getInstance();
		CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        List  resultats = new ArrayList();
          try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_AUDIT.SP_L_AUDIT_DOSSIER(?,?,?); end;");
        	callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du r�sultat retourn�
			resultats = traitementResultSetAudit(resultSet);
             if (log.isDebugEnabled()){
               log.debug("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
               log.debug("  dossier.hierarchie = '"+dossier.getHierarchie()+"'");
               log.debug("  user.code = '"+user.getCode()+"'");
               log.debug("  dossier.intervenant = '"+dossier.getIntervenant()+"'");
               log.debug("  user.site = '"+user.getSite()+"'");
               log.debug("  dossier.site = '"+dossier.getSite()+"'");
               log.debug("  dossier.statut = '"+dossier.getStatut()+"'");
               log.debug("  GlobalConstants.Statut.DOSSIER_ACTIF = '"+GlobalConstants.Statut.DOSSIER_ACTIF+"'");
               log.debug("  dossier.isModifiable = '"+dossier.isModifiable()+"'");
               log.debug("D�termine si le dossier est avec inscription");
               log.debug("  dossier.isInscription = '"+dossier.isInscription()+"'");
             }

		}catch (SQLException se) {
            throw new DAOException(se);
        }

        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
        
        return resultats;
    }   
    
    /**
     * Recherche directe d'une entr�e de journal par sa cl� unique 
     * sans �criture d'un audit.
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_DO_DOSSIER_JOURNAL
     * Date de cr�ation : (2004-04-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Journal : Dossier � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public Journal findJournal(CardexAuthenticationSubject subject,Journal criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        JournalVO journal = new JournalVO();
        int numOfRetries = 0;
        boolean retry = false;
		CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
          try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_DO_DOSSIER_JOURNAL (?,?,?); end;");
            callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du r�sultat retourn�
            if (resultSet.next()) {
                //log.debug("   [LabelValueBean label='" + label
                //      + "' value='"+value+"']");
                journal.setCle(resultSet.getLong("L_DO_CLE"));
                journal.setSite(resultSet.getLong("L_SI_CLE"));
                journal.setCleNarration(resultSet.getLong("L_CO_CLE"));
                journal.setSiteNarration(resultSet.getLong("L_SI_CLE"));
                journal.setCategorie(resultSet.getLong("I_CA_CLE"));
                journal.setType(resultSet.getLong("I_TY_CLE"));
                journal.setNature(resultSet.getLong("I_NA_CLE"));
                journal.setNumeroIncident(OracleDAOUtils.getString(resultSet,
                        "V_DO_ANCIENNE_REFERENCE"));
                journal.setNumeroDossier(OracleDAOUtils.getString(resultSet,
                        "V_DO_NUMERO_DOSSIER"));
                journal.setNumeroEmploye(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE1"));
                journal.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE3"));
                journal.setReference3(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
                journal.setDescription(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
                journal.setDuree(OracleDAOUtils.getString(resultSet,"V_CO_TEMPS"));
                journal.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
                journal.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN"));
                journal.setDateCreation(resultSet.getTimestamp("D_DO_DATE_CREATION"));
                journal.setReferenceVideo(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE_VIDEO"));
                journal.setIntervenant(OracleDAOUtils.getString(resultSet,"V_DO_ASSIGNE_A"));
                //journal.setOrigine(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE2"));
                journal.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
                journal.setEndroit(resultSet.getLong("I_OR_CLE"));
                journal.setLocalisation(resultSet.getLong("I_CR_CLE"));
                journal.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
            }

             // D�termine si le dossier est modifiable
             //� - L'Utilisateur est l'intervenant assign�
             //  - L'intervenant n'a pas chang� de site.
             //  - La date de d�but correspond � celle du jour
			Date jour = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strJour = formatter.format(jour);
			String dateDebut = DateFormat.format(journal.getDateDebut());
			log.debug("strJour = " + strJour + "  dateDebut = " + dateDebut + " dossier : " + journal.getDateDebut());
            if (user.getCode().equals(journal.getIntervenant()) && 
			   (user.getSite() == journal.getSite()) && 
			   (dateDebut.equals(strJour))){
                journal.setModifiable(true);
             }
             if (log.isDebugEnabled()){
               log.debug("Détermine si le dossier est modifiable");
               log.debug("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
               log.debug("  user.code = '"+user.getCode()+"'");
               log.debug("  dossier.intervenant = '"+journal.getIntervenant()+"'");
               log.debug("  user.site = '"+user.getSite()+"'");
               log.debug("  dossier.site = '"+journal.getSite()+"'");
               log.debug("  GlobalConstants.Statut.DOSSIER_ACTIF = '"+GlobalConstants.Statut.DOSSIER_ACTIF+"'");
               log.debug("  dossier.isModifiable = '"+journal.isModifiable()+"'");
               log.debug("Détermine si le dossier est avec inscription");
             }

          }catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
        
        return journal;
    }   

    DossierVO traitementResultSetFind(ResultSet resultSet)
            throws DAOException {
     
  		    DossierVO dossier = new DossierVO();
			try{
                dossier.setCle(resultSet.getLong("L_DO_CLE"));
                dossier.setSite(resultSet.getLong("L_SI_CLE"));
                dossier.setNumeroCardex(OracleDAOUtils.getString(resultSet,
                        "V_DO_NUMERO_DOSSIER"));
                dossier.setEntite(resultSet.getLong("I_GE_ENTITE"));
                dossier.setGenre(resultSet.getLong("I_GE_CLE"));
                dossier.setNature(resultSet.getLong("I_NA_CLE"));
                dossier.setType(resultSet.getLong("I_TY_CLE"));
                dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
                dossier.setNumeroDossier(OracleDAOUtils.getString(resultSet,
                        "V_DO_ANCIENNE_REFERENCE"));
                dossier.setSiteOrigine(resultSet.getLong("L_SI_CLE"));
                dossier.setStatut(resultSet.getLong("I_ST_CLE"));
                dossier.setFonde(resultSet.getLong("I_DO_FONDE"));
                dossier.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
                dossier.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN"));
                dossier.setReference1(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE1"));
                dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE3"));
                dossier.setReference3(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
                dossier.setTypeVideo(resultSet.getLong("I_RF_CLE"));
                dossier.setReferenceVideo(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE_VIDEO"));
                dossier.setIntervenant(OracleDAOUtils.getString(resultSet,"V_DO_ASSIGNE_A"));
                //dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE2"));
                dossier.setSeverite(resultSet.getLong("I_SE_CLE"));
                dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
                dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                dossier.setEndroit(resultSet.getLong("I_OR_CLE"));
                dossier.setLocalisation(resultSet.getLong("I_CR_CLE"));
                dossier.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
                dossier.setPeriode(resultSet.getLong("I_PE_CLE"));
                dossier.setMotPasse(OracleDAOUtils.getString(resultSet,"V_DO_MOT_PASSE"));
                dossier.setMotPasseCourant(OracleDAOUtils.getString(resultSet,"V_DO_MOT_PASSE")); //Pour pouvoir v�rifier si le mot de passe a chang�.
                dossier.setDuree(OracleDAOUtils.getString(resultSet,"V_DO_DUREE"));
                dossier.setDateRapportee(resultSet.getTimestamp("D_DO_DATE_RAPPORTEE"));
                dossier.setClasse(resultSet.getLong("I_DO_CLASSE"));
                dossier.setRace(resultSet.getLong("I_DO_RACE"));
                dossier.setDateAssignation(resultSet.getTimestamp("D_DO_DATE_ASSIGNATION"));
                dossier.setDateEvenement(resultSet.getTimestamp("D_DO_DATE_EVENEMENT"));
                dossier.setReference4(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE4"));
                dossier.setReference5(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
                dossier.setReference6(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE6"));
                dossier.setReference7(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE7"));
                dossier.setFondeDescription(OracleDAOUtils.getString(resultSet,"C_DO_FONDE"));
                dossier.setCreateur(OracleDAOUtils.getString(resultSet,"V_DO_CREE_PAR"));
                dossier.setDateCreation(resultSet.getTimestamp("D_DO_DATE_CREATION"));
                dossier.setEnregistrementConserve(OracleDAOUtils.getString(resultSet,"B_DO_ENREGISTREMENT_CONSERVE"));
                dossier.setEnregistrementNumerique(OracleDAOUtils.getString(resultSet,"B_DO_ENREGISTREMENT_NUMERIQUE"));
                dossier.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
			}catch (SQLException se) {
               throw new DAOException(se);
			}
				return dossier;
        }
    /**
     * Recherche directe d'un dossier par sa cl� unique et prot�g� par mot de
     * passe.
     * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Dossier : Dossier � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier Une instance de dossier associ�e.
     */
    public Dossier findMotPasse(CardexAuthenticationSubject subject,
            Dossier criteria) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        DossierVO dossier = new DossierVO();
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIRE_DOC.SP_L_DO_DOSSIER (?,?,?,?); end;");
            callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.setString(3,criteria.getMotPasse());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            //Traitement des donn�es retourn�es.
            if (resultSet.next()) {
                //log.debug("   [LabelValueBean label='" + label
                //      + "' value='"+value+"']");
                dossier.setCle(resultSet.getLong("L_DO_CLE"));
                dossier.setSite(resultSet.getLong("L_SI_CLE"));
                dossier.setNumeroCardex(OracleDAOUtils.getString(resultSet,
                        "V_DO_NUMERO_DOSSIER"));
                dossier.setGenre(resultSet.getLong("I_GE_CLE"));
                dossier.setNature(resultSet.getLong("I_NA_CLE"));
                dossier.setType(resultSet.getLong("I_TY_CLE"));
                dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
                dossier.setNumeroDossier(OracleDAOUtils.getString(resultSet,
                        "V_DO_ANCIENNE_REFERENCE"));
                dossier.setSiteOrigine(resultSet.getLong("L_SI_CLE"));
                dossier.setStatut(resultSet.getLong("I_ST_CLE"));
                dossier.setFonde(resultSet.getLong("I_DO_FONDE"));
                dossier.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
                dossier.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN"));
                dossier.setReference1(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE1"));
                //dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE2"));
                dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE3"));
                dossier.setReference3(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
                dossier.setTypeVideo(resultSet.getLong("I_RF_CLE"));
                dossier.setReferenceVideo(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE_VIDEO"));
                dossier.setIntervenant(OracleDAOUtils.getString(resultSet,"V_DO_ASSIGNE_A"));
                dossier.setSeverite(resultSet.getLong("I_SE_CLE"));
                dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
                dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                dossier.setEndroit(resultSet.getLong("I_OR_CLE"));
                dossier.setLocalisation(resultSet.getLong("I_CR_CLE"));
                dossier.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
                dossier.setPeriode(resultSet.getLong("I_PE_CLE"));
                dossier.setMotPasse(OracleDAOUtils.getString(resultSet,"V_DO_MOT_PASSE"));
                dossier.setDuree(OracleDAOUtils.getString(resultSet,"V_DO_DUREE"));
                dossier.setDateRapportee(resultSet.getTimestamp(
                        "D_DO_DATE_RAPPORTEE"));
                dossier.setClasse(resultSet.getLong("I_DO_CLASSE"));
                dossier.setRace(resultSet.getLong("I_DO_RACE"));
                dossier.setDateAssignation(resultSet.getTimestamp(
                        "D_DO_DATE_ASSIGNATION"));
                dossier.setDateEvenement(resultSet.getTimestamp(
                        "D_DO_DATE_EVENEMENT"));
                dossier.setReference4(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE4"));
                dossier.setReference5(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
                dossier.setReference6(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE6"));
                dossier.setReference7(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE7"));
                dossier.setFondeDescription(OracleDAOUtils.getString(resultSet,"C_DO_FONDE"));
                dossier.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
            }
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
        return dossier;
    }

    /**
     * Recherche des dossiers cr��s dans les deux derniers jours lors de
     * l'affichage de l'�cran de recherche de dossiers.
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria CriteresRechercheDossier : Crit�res de recherche.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     */
    public List<Dossier> selectDefault(CardexAuthenticationSubject subject,
            CriteresRechercheDossier criteria) throws DAOException{
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
            	"begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_DO_DOSSIER (?,?); end;");
            callableStatement.setLong(1,criteria.getNature());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            //System.out.print("R�sultat : " + resultSet.isBeforeFirst());
            List<Dossier> dossierList = traitementResultSet(resultSet);
            if(dossierList.size() == 0){//S'il n'y a aucun dossier dans les derni�res 48 heures, on va chercher le plus r�cent
            	resultSet.close();
            	callableStatement.close();
            	callableStatement = connection.prepareCall(
                "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L3_DO_DOSSIER (?,?); end;");
		        callableStatement.setLong(1,criteria.getNature());
		        callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		        callableStatement.execute();
		        resultSet = (ResultSet)callableStatement.getObject(2);
	            dossierList = traitementResultSet(resultSet);
            }
            return dossierList;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
    }

    /**
     * Recherche des entr�es de la journ�e pour afficher par d�faut dans l'�cran 
     * de recherche des entr�es du journal de surveillance.
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_DO_DOSSIER_JOURNAL
     * Date de cr�ation : (2004-04-21)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria CriteresRechercheJournal : Crit�res de recherche.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     */
    public List<Journal> selectDefaultJournal(CardexAuthenticationSubject subject,
            CriteresRechercheJournal criteria) throws DAOException{
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		CardexUser user = (CardexUser) subject.getUser();

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_DO_DOSSIER_JOURNAL (?,?); end;");
            /*if(criteria.getEntite() == Long.parseLong(GlobalConstants.Entite.LOTO_QUEBEC)){
                if(String.valueOf(user.getSecteur()).equals(Long.parseLong(GlobalConstants.Secteur.AGENT_SECURITE)))
                    callableStatement.setLong(1,Integer.parseInt(GlobalConstants.Nature.JOURNAL_SECURITE));
                else
                    callableStatement.setLong(1,Integer.parseInt(GlobalConstants.Nature.JOURNAL_ENQUETES));
            }else{
                if(String.valueOf(user.getSecteur()).equals(Long.parseLong(GlobalConstants.Secteur.AGENT_SECURITE)))
                    callableStatement.setLong(1,Integer.parseInt(GlobalConstants.Nature.JOURNAL_SECURITE));
                else
                    callableStatement.setLong(1,Integer.parseInt(GlobalConstants.Nature.JOURNAL));
            }*/
            callableStatement.setLong(1, criteria.getNature());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            //Traitement des donn�es retourn�es
            return traitementResultSetJournal(resultSet);
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
    }

    /**
     * Recherche de dossiers partag�s.
     * Proc�dure appel�e : G�n�r�e ici.
     * Date de cr�ation : (2009-11-06)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria CriteresRechercheDossier : Crit�res de recherche.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator Liste des dossiers retourn�s par la recherche.
     */
    public List<Dossier> recherchePartage(CardexAuthenticationSubject subject,
            CriteresRechercheDossier criteria) throws DAOException {
        log.debug(criteria.toString());
        final List<Dossier> dossierList = new ArrayList<Dossier>();
        
        retraitDuSiteApplicablePourAccesInterditEtAvisDeGuet(criteria);
        JDBCTemplate template = new JDBCTemplate(subject);
        PreparerSQL preparerSQL = (new DossierPartageSQL()).construireSQL(subject, criteria);
        
        RowCallbackHandler callbackHandler = new RowCallbackHandler(){

			public void processRow(ResultSet rs) throws SQLException {
				dossierList.add( construireDossier(rs) );
			}
        	
        };
        
        template.query(preparerSQL, callbackHandler);
        return dossierList;
    }

    /**
     * Pour les acc�s interdit il ne faut pas tenir compte du site applicable.
     * Un acc�s interdit et un avis de guet c'est pour tous les sites applicables.
     * @param criteria
     */    
    private void retraitDuSiteApplicablePourAccesInterditEtAvisDeGuet(CriteresRechercheDossier criteria) {
    	
		if (GlobalConstants.Nature.ACCES_INTERDIT == criteria.getNature()
		|| GlobalConstants.Nature.AVIS_DE_GUET == criteria.getNature()){
			criteria.setSiteApplicable(0);
		}
	}

	/**
     * Recherche des entr�es du journal de surveillance
     * � l'aide de crit�res de recherche.
     * Proc�dure appel�e : G�n�r�e ici.
     * Date de cr�ation : (2004-04-26)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria CriteresRechercheDossier : Crit�res de recherche.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator Liste des dossiers retourn�s par la recherche.
     */
    public List<Journal> selectJournal(CardexAuthenticationSubject subject, CriteresRechercheJournal criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Journal> listJournal = new ArrayList<Journal>();
    	PreparerSQL preparerSQL = (new JournalCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireJournalRowCallBackHandler(listJournal));
 	   
    	return listJournal;	
    }

    
    public Integer nombreDeJournalRecherche(CardexAuthenticationSubject subject,CriteresRechercheJournal criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new JournalCountSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }    
    
    /**
     * Recherche directe de dossiers par le num�ro de fiche sujet (dossiers
     * associ�s � sujet).  La recherche est appel�e par la m�thode "select".
     * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L7_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param ficheSujet String : Valeur � rechercher dans le champ
     * v_do_ancienne_reference.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Une instance du dossier associ�e.
     */
    public List<Dossier> rechercheFicheSujet(CardexAuthenticationSubject subject,
            String ficheSujet) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIRE_DOC.SP_LY_DO_DOSSIER (?,?); end;");
            callableStatement.setString(1,ficheSujet);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            return traitementResultSet(resultSet);
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
    }

    /**
     * Routine pour traiter les ResultSet retourn�s par les recherches de
     * l'audit des changements d'un dossier.
     * Date de cr�ation : (2011-03-03)
     * @author Fran�ois Gu�rin
     * @param resultSet  ResultSet : donn�es retourn�es par une recherche
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return DossierVO : liste des dossiers trait�s.
     */
    private ArrayList traitementResultSetAudit(ResultSet resultSet)
    throws DAOException {

        ArrayList results = new ArrayList();
        try { 
            while (resultSet.next()){
        	    DossierVO dossier = new DossierVO();
		        dossier.setCle(resultSet.getLong("L_DO_CLE"));
		        dossier.setSite(resultSet.getLong("L_SI_CLE"));
		        dossier.setNumeroCardex(OracleDAOUtils.getString(resultSet,"V_DO_NUMERO_DOSSIER"));
		        dossier.setEntite(resultSet.getLong("I_EN_CLE"));
		        dossier.setGenre(resultSet.getLong("I_GE_CLE"));
		        dossier.setNature(resultSet.getLong("I_NA_CLE"));
		        dossier.setType(resultSet.getLong("I_TY_CLE"));
		        dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
		        dossier.setNumeroDossier(OracleDAOUtils.getString(resultSet,"V_DO_ANCIENNE_REFERENCE"));
		        dossier.setSiteOrigine(resultSet.getLong("L_SI_CLE"));
		        dossier.setStatut(resultSet.getLong("I_ST_CLE"));
		        dossier.setFonde(resultSet.getLong("I_DO_FONDE"));
		        dossier.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
		        dossier.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN"));
		        dossier.setReference1(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE1"));
		        dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE3"));
		        dossier.setReference3(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
		        dossier.setTypeVideo(resultSet.getLong("I_RF_CLE"));
		        dossier.setReferenceVideo(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE_VIDEO"));
		        dossier.setIntervenant(OracleDAOUtils.getString(resultSet,"INTERVENANT"));
                dossier.setIntervenantDescription(OracleDAOUtils.getString(resultSet,"INTERVENANT_DETAIL"));
		        //dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE2"));
		        dossier.setSeverite(resultSet.getLong("I_SE_CLE"));
		        dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
		        dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
		        dossier.setEndroit(resultSet.getLong("I_OR_CLE"));
		        dossier.setLocalisation(resultSet.getLong("I_CR_CLE"));
		        dossier.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
		        dossier.setPeriode(resultSet.getLong("I_PE_CLE"));
		        dossier.setMotPasse(OracleDAOUtils.getString(resultSet,"V_DO_MOT_PASSE"));
		        dossier.setDateAssignation(resultSet.getTimestamp("D_DO_DATE_ASSIGNATION"));
		        dossier.setReference4(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE4"));
		        dossier.setReference5(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
		        dossier.setReference6(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE6"));
		        dossier.setReference7(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE7"));
		        dossier.setEnregistrementConserve(OracleDAOUtils.getString(resultSet,"ENTREGISTREMENT_CONSERVE"));
		        dossier.setEnregistrementNumerique(OracleDAOUtils.getString(resultSet,"ENTREGISTREMENT_NUMERIQUE"));
		        dossier.setCreateur(OracleDAOUtils.getString(resultSet,"V_DO_CREE_PAR"));
		        dossier.setDateCreation(resultSet.getTimestamp("D_DO_DATE_CREATION"));
		        dossier.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
		        dossier.setDateChangement(resultSet.getTimestamp("D_DO_DATE_CHANGEMENT"));
		        dossier.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
		        results.add(dossier);
            }
	}catch (SQLException se) {
       throw new DAOException(se);
	}
		return results;
}
    /**
     * Routine pour traiter les ResultSet retourn�s par les recherches de
     * dossier.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param resultSet  ResultSet : donn�es retourn�es par une recherche
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ArrayList : liste des dossiers trait�s.
     */
    public RowCallbackHandler constuireDossierRowCallBackHandler(final List<Dossier> listDossier){
 	   return new RowCallbackHandler(){
 		   public void processRow(ResultSet resultSet) throws SQLException {
 			   DossierVO dossier = construireDossier(resultSet);
               listDossier.add(dossier); 					
 		   }
 	   };
    }
    
    private List<Dossier> traitementResultSet(ResultSet resultSet)
            throws DAOException {
    	List<Dossier> results = new ArrayList<Dossier>();
        try { //On limite le nombre d'enregistrements retourn�s � 10000.
        	while (resultSet.next() && results.size() < GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_DOSSIER){
        		DossierVO dossier = construireDossier(resultSet);
                results.add(dossier);
            }
            return results;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
    }
    
	private DossierVO construireDossier(ResultSet resultSet)
			throws SQLException {
		DossierVO dossier = new DossierVO();
		dossier.setCle(resultSet.getLong("L_DO_CLE"));
		dossier.setSite(resultSet.getLong("L_SI_CLE"));
		dossier.setNumeroCardex(OracleDAOUtils.getString(resultSet,
		        "V_DO_NUMERO_DOSSIER"));
		dossier.setEntite(resultSet.getLong("I_GE_ENTITE"));
		dossier.setGenre(resultSet.getLong("I_GE_CLE"));
		dossier.setNature(resultSet.getLong("I_NA_CLE"));
		dossier.setType(resultSet.getLong("I_TY_CLE"));
		dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
		dossier.setNumeroDossier(OracleDAOUtils.getString(resultSet,
		        "V_DO_ANCIENNE_REFERENCE"));
		dossier.setSiteOrigine(resultSet.getLong("L_SI_CLE"));
		dossier.setStatut(resultSet.getLong("I_ST_CLE"));
		dossier.setFonde(resultSet.getLong("I_DO_FONDE"));
		dossier.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
		dossier.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN")); 
		dossier.setReference1(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE1"));
		dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE3"));
		dossier.setReference3(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
		dossier.setTypeVideo(resultSet.getLong("I_RF_CLE"));
		dossier.setReferenceVideo(OracleDAOUtils.getString(resultSet,
		        "V_DO_REFERENCE_VIDEO"));
		dossier.setIntervenant(OracleDAOUtils.getString(resultSet,"V_DO_ASSIGNE_A"));
		//dossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE2"));
		dossier.setSeverite(resultSet.getLong("I_SE_CLE"));
		dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
		dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
		dossier.setEndroit(resultSet.getLong("I_OR_CLE"));
		dossier.setLocalisation(resultSet.getLong("I_CR_CLE"));
		dossier.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
		dossier.setPeriode(resultSet.getLong("I_PE_CLE"));
		dossier.setMotPasse(OracleDAOUtils.getString(resultSet,"V_DO_MOT_PASSE"));
		dossier.setMotPasseCourant(OracleDAOUtils.getString(resultSet,"V_DO_MOT_PASSE")); //Pour pouvoir v�rifier si le mot de passe a chang�.
		dossier.setDuree(OracleDAOUtils.getString(resultSet,"V_DO_DUREE"));
		dossier.setDateRapportee(resultSet.getTimestamp("D_DO_DATE_RAPPORTEE"));
		dossier.setClasse(resultSet.getLong("I_DO_CLASSE"));
		dossier.setRace(resultSet.getLong("I_DO_RACE"));
		dossier.setDateAssignation(resultSet.getTimestamp("D_DO_DATE_ASSIGNATION"));
		dossier.setDateEvenement(resultSet.getTimestamp("D_DO_DATE_EVENEMENT"));
		dossier.setCreateur(OracleDAOUtils.getString(resultSet, "V_DO_CREE_PAR"));
		dossier.setReference4(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE4"));
		dossier.setReference5(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
		dossier.setReference6(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE6"));
		dossier.setReference7(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE7"));
		dossier.setCreateur(OracleDAOUtils.getString(resultSet,"V_DO_CREE_PAR"));
		dossier.setDateCreation(resultSet.getTimestamp("D_DO_DATE_CREATION"));
		dossier.setFondeDescription(OracleDAOUtils.getString(resultSet,"C_DO_FONDE"));
		dossier.setEnregistrementConserve(OracleDAOUtils.getString(resultSet,"B_DO_ENREGISTREMENT_CONSERVE"));
		dossier.setEnregistrementNumerique(OracleDAOUtils.getString(resultSet,"B_DO_ENREGISTREMENT_NUMERIQUE"));
		dossier.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
		return dossier;
	}

    /**
     * Routine pour traiter les ResultSet retourn�s par les recherches dans
     * le journal de surveillance.
     * Date de cr�ation : (2004-04-21)
     * @author Fran�ois Gu�rin
     * @param resultSet  ResultSet : donn�es retourn�es par une recherche
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ArrayList : liste des dossiers trait�s.
     */
    public RowCallbackHandler constuireJournalRowCallBackHandler(final List<Journal> listJournal){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			 JournalVO journal = construireJournal(resultSet);
                listJournal.add(journal); 					
  		   }
  	   };
     }
	
	
    private List<Journal> traitementResultSetJournal(ResultSet resultSet)
            throws DAOException {
    	List<Journal> results = new ArrayList<Journal>();
        try { //On limite le nombre d'enregistrements retourn�s � 10000.
            while (resultSet.next() && results.size() < GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_JOURNAL){
            	GererTacheUtilisateur.verifierThreadCourrant();
            	
                JournalVO journal = construireJournal(resultSet);
                results.add(journal);
            }
            return results;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
    }
	private JournalVO construireJournal(ResultSet resultSet)
			throws SQLException {
		JournalVO journal = new JournalVO();
		journal.setCle(resultSet.getLong("L_DO_CLE"));
		journal.setSite(resultSet.getLong("L_SI_CLE"));
		journal.setCleNarration(resultSet.getLong("L_CO_CLE"));
		journal.setSiteNarration(resultSet.getLong("L_SI_CLE"));
		journal.setCategorie(resultSet.getLong("I_CA_CLE"));
		journal.setType(resultSet.getLong("I_TY_CLE"));
		journal.setNature(resultSet.getLong("I_NA_CLE"));
		journal.setNumeroIncident(OracleDAOUtils.getString(resultSet,
		        "V_DO_ANCIENNE_REFERENCE"));
		journal.setNumeroDossier(OracleDAOUtils.getString(resultSet,
		        "V_DO_NUMERO_DOSSIER"));
		journal.setNumeroEmploye(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE1"));
		journal.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE3"));
		journal.setReference3(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE5"));
		journal.setDescription(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
		journal.setDuree(OracleDAOUtils.getString(resultSet,"V_CO_TEMPS"));
		journal.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
		journal.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN"));
		journal.setDateCreation(resultSet.getTimestamp("D_DO_DATE_CREATION"));
		journal.setReferenceVideo(OracleDAOUtils.getString(resultSet,
		        "V_DO_REFERENCE_VIDEO"));
		journal.setIntervenant(OracleDAOUtils.getString(resultSet,"V_DO_ASSIGNE_A"));
		journal.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
		journal.setEndroit(resultSet.getLong("I_OR_CLE"));
		journal.setLocalisation(resultSet.getLong("I_CR_CLE"));
		journal.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
		journal.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
		
		return journal;
	}

    /**
     * Routine pour traiter les ResultSet retourn�s par les recherches dans
     * le journal de surveillance pour l'impression des rapports.
     * Date de cr�ation : (2004-04-21)
     * @author Fran�ois Gu�rin
     * @param resultSet  ResultSet : donn�es retourn�es par une recherche
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ArrayList : liste des dossiers trait�s.
     */
    private ArrayList traitementResultSetRapportJournal(ResultSet resultSet)
            throws DAOException {
        ArrayList results = new ArrayList();
        try { //On limite le nombre d'enregistrements retourn�s � 10000.
            while (resultSet.next() && results.size() < 10000){
                RapportJournalVO journal = new RapportJournalVO();
                journal.setSite(resultSet.getLong("L_SI_CLE"));
                journal.setCleType(resultSet.getLong("I_TY_CLE"));
                journal.setDescription(OracleDAOUtils.getString(resultSet,"DESCRIPTION"));
                journal.setSecteur(OracleDAOUtils.getString(resultSet,"GROUP_NAME"));
                journal.setDuree(resultSet.getLong("TOTAL"));
                journal.setNombre(resultSet.getLong("NOMBRE"));
                //journal.setDateDebut(resultSet.getTimestamp("D_DO_DATE_DEBUT"));
                //journal.setDateFin(resultSet.getTimestamp("D_DO_DATE_FIN"));
                journal.setIntervenant(OracleDAOUtils.getString(resultSet,"V_CO_CREE_PAR"));
                results.add(journal);
            }
            return results;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Modification d'un dossier par insertion ou suppression d'un lien
     * (association � un autre dossier).
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier source.
     * @param addedDossier Dossier : Dossier associ�.
     * @param action String : "I" (insert) ou "D" (delete).
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void editLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Dossier addedDossier, String action)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER"
                    + " (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                callableStatement.setLong(2, addedDossier.getLien());
                callableStatement.setLong(3, addedDossier.getLienSite());
                callableStatement.setLong(4, dossier.getCle());
                callableStatement.setLong(5, addedDossier.getCle());
                callableStatement.setLong(6, addedDossier.getRole());
                callableStatement.setLong(7, addedDossier.getTypeLien());
                callableStatement.setLong(8, dossier.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.setLong(10, addedDossier.getSite());
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }
    

    
    /**
     * Suppression de tous les dossiers plac�s en confidentialit� 8.
     * Cette m�thode est appel�e � partir de la recherche de dossiers.
     * Proc�dure appel�e : SP_EPURATION_DOSSIERS_SITE
     * Date de cr�ation : (2002-10-17)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void delete(CardexAuthenticationSubject subject)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        CardexUser user = (CardexUser)subject.getUser();

        try {
            callableStatement = connection.prepareCall(
                    "begin SP_EPURATION_DOSSIERS_SITE(?); end;");
                callableStatement.setLong(1, user.getSite());
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }    

    /**
     * Inscription de la date de paiement dans les billets associ�s au dossier
     * Cette m�thode est appel�e � partir de l'onglet billets de dossiers.
     * Proc�dure appel�e : SP_E_BI_BILLET_PAIEMENT
     * Date de cr�ation : (2012-01-17)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void inscrireDatePaiement(CardexAuthenticationSubject subject,
			 long cle,
			 long site,
			 String datePaiement)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        CardexUser user = (CardexUser)subject.getUser();

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_BI_BILLET_PAIEMENT(?,?,?); end;");
                callableStatement.setLong(1, cle);
                callableStatement.setLong(2, site);
                callableStatement.setString(3, datePaiement);
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }    

    /**
     * �criture ou suppression d'un lien (association d'un dossier � un sujet).
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier source.
     * @param sujet Sujet : Sujet associ�.
     * @param action String : "I" (insert) "D" (delete).
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void editLienDossier(Dossier dossier, Sujet sujet, String action, Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER"
                    + " (?,?,?,?,?,?,?,?,?,?,?); end;");
                log.debug("begin CARDEX_LIEN.SP_E_");
                log.debug("  action '" + action + "'");
                log.debug("  L_LDD_CLE '" + sujet.getLien() + "'");
                log.debug("  L_SI_CLE '" + sujet.getLienSite() + "'");
                log.debug("  L_DO_CLE '" + dossier.getCle() + "'");
                log.debug("  L_LDD_DOSSIER_ASSOCIE '" + sujet.getCle() + "'");
                log.debug("  I_RO_CLE '" + sujet.getRole() + "'");
                log.debug("  I_TL_CLE '" + sujet.getTypeLien() + "'");
                log.debug("  L_DO_SITE '" + dossier.getSite() + "'");
                log.debug("  C_DO_GENRE '" + GlobalConstants.GenreFichier.DOSSIER + "'");
                log.debug("  L_LDD_SITE '" + sujet.getSite());
                log.debug("  C_LDD_GENRE '" + GlobalConstants.GenreFichier.SUJET + "'");

                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                callableStatement.setLong(2, sujet.getLien());
                callableStatement.setLong(3, sujet.getLienSite());
                callableStatement.setLong(4, dossier.getCle());
                callableStatement.setLong(5, sujet.getCle());
                callableStatement.setLong(6, sujet.getRole());
                callableStatement.setLong(7, sujet.getTypeLien());
                callableStatement.setLong(8, dossier.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.setLong(10, sujet.getSite());
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.SUJET);
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
        }
    }    
    public void editLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Sujet sujet, String action) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        try {
        	editLienDossier(dossier, sujet, action, connection);
        } finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }

    /**
     * Modification d'un dossier par insertion ou suppression d'un lien
     * (association � un v�hicule).
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier source.
     * @param vehicule Vehicule : V�hicule associ�.
     * @param action String : "I" (insert) "D" (delete).
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void editLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Vehicule vehicule, String action)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER"
                    + " (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                callableStatement.setLong(2, vehicule.getLien());
                callableStatement.setLong(3, vehicule.getLienSite());
                callableStatement.setLong(4, dossier.getCle());
                callableStatement.setLong(5, vehicule.getCle());
                callableStatement.setLong(6, vehicule.getRole());
                callableStatement.setLong(7, vehicule.getTypeLien());
                callableStatement.setLong(8, dossier.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.setLong(10, vehicule.getSite());
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.VEHICULE);
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }

    /**
     * �criture ou suppression d'un lien (association d'un dossier � une
     * soci�t�).
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier source.
     * @param societe Societe : Soci�t� associ�.
     * @param action String : "I" (insert) "D" (delete).
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void editLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Societe societe, String action)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER"
                    + " (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                callableStatement.setLong(2, societe.getLien());
                callableStatement.setLong(3, societe.getLienSite());
                callableStatement.setLong(4, dossier.getCle());
                callableStatement.setLong(5, societe.getCle());
                callableStatement.setLong(6, societe.getRole());
                callableStatement.setLong(7, societe.getTypeLien());
                callableStatement.setLong(8, dossier.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.setLong(10, societe.getSite());
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.SOCIETE);
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }


    // M�thodes addLien


    /**
     * Insert un lien (association d'un dossier � une narration).  Utilisant la
     * stored procedure SP_E_CO_COMMENTAIRE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param narration Narration : Narration associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Narration Une instance de la narration associ�e.
     */
    public Narration addLienNarration(CardexAuthenticationSubject subject,
            Dossier dossier, Narration narration) throws DAOException {
        narration.setLien(dossier.getCle());
        narration.setLienSite(dossier.getSite());
        return FabriqueCardexDAO.getInstance().getNarrationDAO().insert(subject, narration,
            GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Ajout de la narration du journal
     * @param subject
     * @param dossier
     * @param narration
     * @return
     * @throws DAOException
     */
    public Narration addLienNarrationApprouve(CardexAuthenticationSubject subject,
            Dossier dossier, Narration narration) throws DAOException {
        narration.setLien(dossier.getCle());
        narration.setLienSite(dossier.getSite());
        return FabriqueCardexDAO.getInstance().getNarrationDAO().insertPreApprouve(subject, narration,
            GlobalConstants.GenreFichier.DOSSIER);
    }    
    
    /**
     * Insert un lien (association d'un dossier � un autre dossier).
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param addedDossier Dossier : Dossier associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Dossier addedDossier) throws DAOException {
        editLienDossier(subject, dossier, addedDossier, "I");
    }

    /**
     * Insert un lien (association d'un dossier � un v�hicule).  Utilisant la
     * stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
            Dossier dossier, Vehicule vehicule) throws DAOException {
        editLienDossier(subject, dossier, vehicule, "I");
    }

    /**
     * Insert un lien (association d'un dossier � une inscription).  Utilisant la
     * stored procedure SP_E_IS_INSCRIPTION PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void addLienInscription(CardexAuthenticationSubject subject,
            Dossier dossier, Inscription inscription) throws DAOException {
    	FabriqueCardexDAO.getInstance().getInscriptionDAO().insert(subject, inscription, GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * D�truit un lien (association d'un dossier � une inscription).  Utilisant la
     * stored procedure SP_E_IS_INSCRIPTION PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienInscription(CardexAuthenticationSubject subject,
            Dossier dossier, Inscription inscription) throws DAOException {
    	FabriqueCardexDAO.getInstance().getInscriptionDAO().delete(subject, inscription, GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Recherche des intervenants du partage
     * Date de cr�ation : (2009-11-05)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
/*    public Partage findPartage(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPartageDAO().findPartage(subject, dossier);
    }
*/
    /**
     * Ouverture du partage � partir de l'onglet Partage
     * Date de cr�ation : (2009-11-05)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public Partage ouvrirPartage(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPartageDAO().ouvrirPartage(subject, dossier);
    }

    /**
     * Recherche une inscription
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public Inscription findInscription(CardexAuthenticationSubject subject,
            Inscription inscription) throws DAOException {
        return FabriqueCardexDAO.getInstance().getInscriptionDAO().findInscription(subject, inscription);
    }

    /**
     * Mise � jour d'une inscription
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateInscription(CardexAuthenticationSubject subject,
            Inscription inscription) throws DAOException {
    	FabriqueCardexDAO.getInstance().getInscriptionDAO().updateInscription(subject, inscription);
    }

    /**
     * Insert un lien (association d'un dossier � une soci�t�).  Utilisant la
     * stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param societe Societe : Soci�t� associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
            Dossier dossier, Societe societe) throws DAOException {
        editLienDossier(subject, dossier, societe, "I");
    }

    /**
     * Insert un lien (association d'un dossier � un sujet).  Utilisant la
     * stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param sujet Sujet : Sujet associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
            Dossier dossier, Sujet sujet) throws DAOException {
        editLienDossier(subject, dossier, sujet, "I");
    }

    /**
     * Insert un lien (association d'un dossier � une photo).  Utilisant la
     * stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param photo Photo : Photo associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Photo Une instance de photo associ�e.
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
            Dossier dossier, Photo photo) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPhotoDAO().insert(subject, photo,
            GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Insert un lien (association d'un dossier � un suivi).  Utilisant la
     * stored procedure SP_E_SV_SUIVI PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param suivi Suivi : Suivi associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Suivi Une instance de suivi associ�.
     */
    public Suivi addLienSuivi(CardexAuthenticationSubject subject,
            Dossier dossier, Suivi suivi) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSuiviDAO().insert(subject, suivi,
                GlobalConstants.GenreFichier.DOSSIER);
    }

    public void updateLiensJeu(CardexAuthenticationSubject subject, Dossier dossier, Jeux jeux) throws DAOException {
    	FabriqueCardexDAO.getInstance().getJeuDAO().update(subject, jeux, GlobalConstants.GenreFichier.DOSSIER);
    }

    public void updateLiensPartage(CardexAuthenticationSubject subject, Dossier dossier, Partage partage) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPartageDAO().update(subject, partage);
    }

   /**
     * Insert un lien (association d'un dossier � un service d'urgence).  Utilisant la
     * stored procedure SP_E_UR_URGENCE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Consignation : Suivi associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Urgence Une instance de Urgence associ�.
     */
    public Urgence addLienUrgence(CardexAuthenticationSubject subject,
            Dossier dossier, Urgence urgence) throws DAOException {
        return FabriqueCardexDAO.getInstance().getUrgenceDAO().insert(subject, urgence);
    }

    /**
     * Insert un lien (association d'un dossier � une consignation).  Utilisant la
     * stored procedure SP_E_CN_CONSIGNATION PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Consignation : Suivi associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Consignation Une instance de consignation associ�.
     */
    public Consignation addLienConsignation(CardexAuthenticationSubject subject,
            Dossier dossier, Consignation consignation) throws DAOException {
        return FabriqueCardexDAO.getInstance().getConsignationDAO().insert(subject, consignation,
                GlobalConstants.GenreFichier.DOSSIER);
    }

    // M�thodes deleteLien


    /**
     * Suppression d'un lien (association d'un dossier � une narration).
     * Utilisant la stored procedure SP_E_CO_COMMENTAIRE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param narration Narration : Narration associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
            Dossier dossier, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().delete(subject, narration,
            GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Suppression d'un lien (association d'un dossier � un autre dossier).
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param addedDossier Dossier : Dossier associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Dossier addedDossier) throws DAOException {
        editLienDossier(subject, dossier, addedDossier, "D");
    }

    /**
     * Suppression d'un lien (association d'un dossier � un v�hicule).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
            Dossier dossier, Vehicule vehicule) throws DAOException {
        editLienDossier(subject, dossier, vehicule, "D");
    }

    /**
     * Suppression d'un lien (association d'un dossier � une soci�t�).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param societe Societe : Soci�t� associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
            Dossier dossier, Societe societe) throws DAOException {
        editLienDossier(subject, dossier, societe, "D");
    }

    /**
     * Suppression d'un lien (association d'un dossier � un sujet).  Utilisant
     * la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param sujet Sujet : Sujet associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
            Dossier dossier, Sujet sujet) throws DAOException {
        editLienDossier(subject, dossier, sujet, "D");
    }

    /**
     * Suppression d'un lien (association d'un dossier � une photo).  Utilisant
     * la stored procedure SP_E_LMM_LIEN_MULTIMEDIA PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param photo Photo : Photo associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
            Dossier dossier, Photo photo) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPhotoDAO().delete(subject,photo,GlobalConstants.GenreFichier.DOSSIER);
    }
    

    /**
     * Mise � jour d'un lien (association d'un dossier � une pi�ce jointe).  Utilisant
     * la stored procedure SP_U_LMM_LIEN_MULTIMEDIA PACKAGE CARDEX_LIEN.
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param photo Photo : lien photo associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienMultimedia(CardexAuthenticationSubject subject,
            Photo photo) throws DAOException {
        FabriqueCardexDAO.getInstance().getPhotoDAO().updateLienMultimedia(photo, subject);
    }

    /**
     * Suppression d'un lien (association d'un dossier � un suivi).  Utilisant
     * la stored procedure SP_E_SV_SUIVI PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param suivi Suivi : Suivi associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienSuivi(CardexAuthenticationSubject subject,
            Dossier dossier, Suivi suivi) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSuiviDAO().delete(subject, suivi, GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Suppression d'un lien (association d'un dossier � un service d'urgence).  Utilisant
     * la stored procedure SP_E_UR_URGENCE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Consignation : Consignation associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienUrgence(CardexAuthenticationSubject subject,
            Dossier dossier, Urgence urgence) throws DAOException {
    	FabriqueCardexDAO.getInstance().getUrgenceDAO().delete(subject, urgence);
    }

    /**
     * Suppression d'un lien (association d'un dossier � une consignation).  Utilisant
     * la stored procedure SP_E_CN_CONSIGNATION PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Consignation : Consignation associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void deleteLienConsignation(CardexAuthenticationSubject subject,
            Dossier dossier, Consignation consignation) throws DAOException {
    	FabriqueCardexDAO.getInstance().getConsignationDAO().delete(subject, consignation, GlobalConstants.GenreFichier.DOSSIER);
    }

    // M�thodes updateLien


    /**
     * Mise � jour d'un lien (association d'un dossier � une narration).
     * Utilisant la stored procedure SP_E_CO_COMMENTAIRE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param narration Narration : Narration associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienNarration(CardexAuthenticationSubject subject,
            Dossier dossier, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().update(subject, narration,
                GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Approbation d'une narration (association d'un dossier � une narration).
     * Utilisant la stored procedure SP_E_CO_COMMENTAIRE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param narration Narration : Narration associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
            Dossier dossier, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().approbation(subject, narration,
                GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Approbation d'un suivi (association d'un dossier � un suivi).
     * Utilisant la stored procedure SP_E_CO_COMMENTAIRE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param suivi Suivi : Suivi associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void approuveLienSuivi(CardexAuthenticationSubject subject,
            Dossier dossier, Suivi suivi) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSuiviDAO().approbation(subject, suivi,
                GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Approbation d'un suivi (association d'un dossier � une consignation).
     * Utilisant la stored procedure SP_E_CN_CONSIGNATION PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Consignation : Suivi associ�e.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void approuveLienConsignation(CardexAuthenticationSubject subject,
            Dossier dossier, Consignation consignation) throws DAOException {
    	FabriqueCardexDAO.getInstance().getConsignationDAO().approbation(subject, consignation,
                GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � un autre dossier).
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param addedDossier Dossier : Dossier associ�.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienDossier(CardexAuthenticationSubject subject,
            Dossier dossier, Dossier addedDossier) throws DAOException {
      editLienDossier(subject, dossier, addedDossier, "U");
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � aucun, un ou plusieurs
     * jeux).  Utilisant la stored procedure
     * SP_E_LJD_LIEN_JEU_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param jeux Jeux : Jeux associ�es.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienJeu(CardexAuthenticationSubject subject,
            Dossier dossier, Jeux jeux) throws DAOException {
    	FabriqueCardexDAO.getInstance().getJeuDAO().update(subject, jeux, GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � un vehicule).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param vehicule Vehicule : V�hicule associ�.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienVehicule(CardexAuthenticationSubject subject,
            Dossier dossier, Vehicule vehicule) throws DAOException {
        editLienDossier(subject, dossier, vehicule, "U");
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � un sujet ou � une soci�t�).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param societe Societe : Soci�t� associ�e.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLien(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER"
                    + " (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,"U"); //action
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                callableStatement.setLong(2, dossier.getLien());
                callableStatement.setLong(3, dossier.getLienSite());
                callableStatement.setLong(4, dossier.getCle());
                callableStatement.setLong(5, 0);
                callableStatement.setLong(6, dossier.getRole());
                callableStatement.setLong(7, dossier.getTypeLien());
                callableStatement.setLong(8, dossier.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.setLong(10, 0);
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.SOCIETE);
                callableStatement.execute();
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � un sujet).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param sujet Sujet : Sujet associ�.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienSujet(CardexAuthenticationSubject subject,
            Dossier dossier, Sujet sujet) throws DAOException {
        editLienDossier(subject, dossier, sujet, "U");
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � une photo).  Utilisant
     * la stored procedure SP_E_LMM_LIEN_MULTIMEDIA PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param photo Photo : Photo associ�e.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienPhoto(CardexAuthenticationSubject subject,
            Dossier dossier, Photo photo) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPhotoDAO().update(subject,photo,GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � un suivi).  Utilisant
     * la stored procedure SP_E_SV_SUIVI PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param suivi Suivi : Suivi associ�.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienSuivi(CardexAuthenticationSubject subject,
            Dossier dossier, Suivi suivi) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSuiviDAO().update(subject, suivi, GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � un service d'urgence).  Utilisant
     * la stored procedure SP_E_UR_URGENCE PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Suivi : Suivi associ�.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienUrgence(CardexAuthenticationSubject subject,
            Dossier dossier, Urgence urgence) throws DAOException {
    	FabriqueCardexDAO.getInstance().getUrgenceDAO().update(subject, urgence);
    }

    /**
     * Mise � jour d'un lien (association d'un dossier � une consignation).  Utilisant
     * la stored procedure SP_E_CN_CONSIGNATION PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param consignation Suivi : Suivi associ�.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLienConsignation(CardexAuthenticationSubject subject,
            Dossier dossier, Consignation consignation) throws DAOException {
    	FabriqueCardexDAO.getInstance().getConsignationDAO().update(subject, consignation, GlobalConstants.GenreFichier.DOSSIER);
    }


    // M�thodes findLiens


    /**
     * Retourne les dossiers associ�s � un certain dossier.
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_DO_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle : cle de l'object avec lequel on cherche l'association.
     * @param site : site de l'object avec lequel on cherche l'association.
     * @param genreFichier : genre de l'object avec lequel on cherche
     * l'association.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des dossiers associ�s.
     */
    public Collection findLiensDossier(long cle, long site, String genreFichier, Connection connection) throws DAOException {
        log.debug("findLiensDossier()");
		
        CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_DO_DOSSIER (?,?,?,?); end;"); // cette proc�dure contien un merge cart�sien
            callableStatement.setLong(1, cle);
            callableStatement.setLong(2, site);
            callableStatement.setString(3, genreFichier);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            
            resultSet = (ResultSet)callableStatement.getObject(4);
            ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                DossierVO linkedDossier = new DossierVO();
                linkedDossier.setCle(resultSet.getLong("L_DO_CLE"));
                linkedDossier.setSite(resultSet.getLong("L_SI_CLE"));
                linkedDossier.setNumeroCardex(OracleDAOUtils.getString(resultSet,
                        "V_DO_NUMERO_DOSSIER"));
                linkedDossier.setGenre(resultSet.getLong("I_GE_CLE"));
                linkedDossier.setNature(resultSet.getLong("I_NA_CLE"));
                linkedDossier.setType(resultSet.getLong("I_TY_CLE"));
                linkedDossier.setCategorie(resultSet.getLong("I_CA_CLE"));
                linkedDossier.setNumeroDossier(OracleDAOUtils.getString(resultSet,
                        "V_DO_ANCIENNE_REFERENCE"));
                linkedDossier.setSiteOrigine(resultSet.getLong("L_SI_CLE"));
                linkedDossier.setStatut(resultSet.getLong("I_ST_CLE"));
                linkedDossier.setFonde(resultSet.getLong("I_DO_FONDE"));
                linkedDossier.setDateDebut(resultSet.getTimestamp(
                        "D_DO_DATE_DEBUT"));
                linkedDossier.setDateFin(resultSet.getTimestamp(
                        "D_DO_DATE_FIN"));
                linkedDossier.setReference1(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE1"));
                //linkedDossier.setReference2(OracleDAOUtils.getString(resultSet, "V_DO_REFERENCE2"));
                linkedDossier.setReference3(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE3"));
                linkedDossier.setTypeVideo(resultSet.getLong("I_RF_CLE"));
                linkedDossier.setReferenceVideo(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE_VIDEO"));
                linkedDossier.setIntervenant(OracleDAOUtils.getString(resultSet,
                        "V_DO_ASSIGNE_A"));
                //linkedDossier.setReference2(OracleDAOUtils.getString(resultSet,"V_DO_REFERENCE2"));
                linkedDossier.setSeverite(resultSet.getLong("I_SE_CLE"));
                linkedDossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
                linkedDossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                linkedDossier.setEndroit(resultSet.getLong("I_OR_CLE"));
                linkedDossier.setLocalisation(resultSet.getLong("I_CR_CLE"));
                linkedDossier.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
                linkedDossier.setPeriode(resultSet.getLong("I_PE_CLE"));
                linkedDossier.setMotPasse(OracleDAOUtils.getString(resultSet,
                        "V_DO_MOT_PASSE"));
                linkedDossier.setDuree(OracleDAOUtils.getString(resultSet,"V_DO_DUREE"));
                linkedDossier.setDateRapportee(resultSet.getTimestamp(
                        "D_DO_DATE_RAPPORTEE"));
                linkedDossier.setClasse(resultSet.getLong("I_DO_CLASSE"));
                linkedDossier.setRace(resultSet.getLong("I_DO_RACE"));
                linkedDossier.setDateAssignation(resultSet.getTimestamp(
                        "D_DO_DATE_ASSIGNATION"));
                linkedDossier.setDateEvenement(resultSet.getTimestamp(
                        "D_DO_DATE_EVENEMENT"));
                linkedDossier.setReference4(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE4"));
                linkedDossier.setReference5(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE5"));
                linkedDossier.setReference6(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE6"));
                linkedDossier.setReference7(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE7"));
                linkedDossier.setFondeDescription(OracleDAOUtils.getString(resultSet,
                        "C_DO_FONDE"));
                linkedDossier.setLien(resultSet.getLong("L_LDD_CLE"));
                linkedDossier.setLienSite(resultSet.getLong("L_LDD_SI_CLE"));
                linkedDossier.setRole(resultSet.getLong("I_LDD_RO_CLE"));
				linkedDossier.setLienCreateur(resultSet.getString("V_LDD_CREE_PAR"));
                linkedDossier.setLienDateCreation(resultSet.getTimestamp("D_LDD_DATE_CREATION"));
                linkedDossier.setEntite(resultSet.getLong("I_EN_CLE"));
                linkedDossier.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
                results.add(linkedDossier);
                log.debug("   [DOSSIER id='" + linkedDossier.getNumeroCardex()
                        + "' date='" + linkedDossier.getDateDebut() + "']");
            }//while
            return results;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
        } //finally
    }    
    
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
            long cle, long site, String genreFichier) throws DAOException {
        log.debug("findLiensDossier()");
        Connection connection = null;
        
        try {
            connection = DAOConnection.getInstance().getConnection(subject);

        	return findLiensDossier(cle, site, genreFichier, connection);
        }
        finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
    }

    /**
     * Retourne les dossiers associ�es � un dossier.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des narrations associ�es.
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        log.debug("findLiensDossier()");
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_DO_DOSSIER (?,?,?,?); end;"); // cette proc�dure contien un merge cart�sien
            callableStatement.setLong(1, dossier.getCle());
            callableStatement.setLong(2, dossier.getSite());
            callableStatement.setString(3,
                    GlobalConstants.GenreFichier.DOSSIER);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            
            resultSet = (ResultSet)callableStatement.getObject(4);
            ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                DossierVO linkedDossier = new DossierVO();
                linkedDossier.setCle(resultSet.getLong("L_DO_CLE"));
                linkedDossier.setSite(resultSet.getLong("L_SI_CLE"));
                linkedDossier.setNumeroCardex(OracleDAOUtils.getString(resultSet,
                        "V_DO_NUMERO_DOSSIER"));
                linkedDossier.setGenre(resultSet.getLong("I_GE_CLE"));
                linkedDossier.setNature(resultSet.getLong("I_NA_CLE"));
                linkedDossier.setType(resultSet.getLong("I_TY_CLE"));
                linkedDossier.setCategorie(resultSet.getLong("I_CA_CLE"));
                linkedDossier.setNumeroDossier(OracleDAOUtils.getString(resultSet,
                        "V_DO_ANCIENNE_REFERENCE"));
                linkedDossier.setSiteOrigine(resultSet.getLong("L_SI_CLE"));
                linkedDossier.setStatut(resultSet.getLong("I_ST_CLE"));
                linkedDossier.setFonde(resultSet.getLong("I_DO_FONDE"));
                linkedDossier.setDateDebut(resultSet.getTimestamp(
                        "D_DO_DATE_DEBUT"));
                linkedDossier.setDateFin(resultSet.getTimestamp(
                        "D_DO_DATE_FIN"));
                linkedDossier.setReference1(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE1"));
                //linkedDossier.setReference2(OracleDAOUtils.getString(resultSet, "V_DO_REFERENCE2"));
                linkedDossier.setReference3(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE3"));
                linkedDossier.setTypeVideo(resultSet.getLong("I_RF_CLE"));
                linkedDossier.setReferenceVideo(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE_VIDEO"));
                linkedDossier.setIntervenant(OracleDAOUtils.getString(resultSet,
                        "V_DO_ASSIGNE_A"));
                //linkedDossier.setReference2(OracleDAOUtils.getString(resultSet, "V_DO_REFERENCE2"));
                linkedDossier.setSeverite(resultSet.getLong("I_SE_CLE"));
                linkedDossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
                linkedDossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                linkedDossier.setEndroit(resultSet.getLong("I_OR_CLE"));
                linkedDossier.setLocalisation(resultSet.getLong("I_CR_CLE"));
                linkedDossier.setDescriptif(OracleDAOUtils.getString(resultSet,"V_DO_LIEU"));
                linkedDossier.setPeriode(resultSet.getLong("I_PE_CLE"));
                linkedDossier.setMotPasse(OracleDAOUtils.getString(resultSet,
                        "V_DO_MOT_PASSE"));
                linkedDossier.setDuree(OracleDAOUtils.getString(resultSet,"V_DO_DUREE"));
                linkedDossier.setDateRapportee(resultSet.getTimestamp(
                        "D_DO_DATE_RAPPORTEE"));
                linkedDossier.setClasse(resultSet.getLong("I_DO_CLASSE"));
                linkedDossier.setRace(resultSet.getLong("I_DO_RACE"));
                linkedDossier.setDateAssignation(resultSet.getTimestamp(
                        "D_DO_DATE_ASSIGNATION"));
                linkedDossier.setDateEvenement(resultSet.getTimestamp(
                        "D_DO_DATE_EVENEMENT"));
                linkedDossier.setReference4(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE4"));
                linkedDossier.setReference5(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE5"));
                linkedDossier.setReference6(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE6"));
                linkedDossier.setReference7(OracleDAOUtils.getString(resultSet,
                        "V_DO_REFERENCE7"));
                linkedDossier.setFondeDescription(OracleDAOUtils.getString(resultSet,
                        "C_DO_FONDE"));
                linkedDossier.setLien(resultSet.getLong("L_LDD_CLE"));
                linkedDossier.setLienSite(resultSet.getLong("L_LDD_SI_CLE"));
                linkedDossier.setRole(resultSet.getLong("I_LDD_RO_CLE"));
				linkedDossier.setLienCreateur(resultSet.getString("V_LDD_CREE_PAR"));
                linkedDossier.setLienDateCreation(resultSet.getTimestamp("D_LDD_DATE_CREATION"));
                linkedDossier.setEntite(resultSet.getLong("I_EN_CLE"));
                linkedDossier.setOrigine(resultSet.getLong("L_DO_ORIGINE"));
                results.add(linkedDossier);
                log.debug("   [DOSSIER id='" + linkedDossier.getNumeroCardex()
                        + "' date='" + linkedDossier.getDateDebut() + "']");
            }//while
            return results;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        } //finally
    }

    /**
     * Retourne les narrations associ�es � un dossier.  La proc�dure appel�e est
     * dans OracleNarrationDAO.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des narrations associ�es.
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getNarrationDAO().findLiensNarration(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les narrations associ�es � un dossier pour l'impression d'un rapport.  
     * La proc�dure appel�e est dans OracleNarrationDAO.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des narrations associ�es.
     */
    public Collection findLiensNarrationRapport(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getNarrationDAO().findLiensNarrationRapport(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les narrations associ�es � un dossier pour l'impression d'un rapport uniformis�.  
     * La proc�dure appel�e est dans OracleNarrationDAO.
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des narrations associ�es.
     */
    public Collection findLiensNarrationRapportUniforme(CardexAuthenticationSubject subject,
            Dossier dossier, String section) throws DAOException {
        return FabriqueCardexDAO.getInstance().getNarrationDAO().findLiensNarrationRapportUniforme(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER, section);
    }

    /**
     * Retourne les jeux associ�s � un dossier.  La proc�dure appel�e est dans
     * OracleJeuDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des jeux associ�s.
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getJeuDAO().findLiensJeux(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les intervenants associ�s � un partage de dossier.  La proc�dure appel�e est dans
     * OraclePartageDAO.
     * Date de cr�ation : (2009-11-05)
     * @author guerinf
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des jeux associ�s.
     */
    public Collection findLiensPartage(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPartageDAO().findLiensPartage(subject, dossier.getCle(),
                dossier.getSite());
    }

    public SousCategoriesVO findLiensSousCategories(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSousCategorieDAO().findLiensSousCategorie(subject, dossier.getCle(),
                dossier.getSite());
    }    
    
    /**
     * Retourne les v�hicule associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleVehiculeDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des v�hicules associ�s.
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return VehiculeDAO.findLiensVehicule(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les soci�t�s associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleSocieteDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des v�hicules associ�s.
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensSociete(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les sujets associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleSujetDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des sujets associ�s.
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSujet(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les sujets provisoires associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleSujetDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des sujets associ�s.
     */
    public Collection findLiensSujetProvisoire(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSujetProvisoire(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les inscriptions associ�es � un dossier.  La proc�dure appel�e
     * est dans OracleInscriptionDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Inscription : Inscription contenant la liste des site associ�s.
     */
    public Collection findLiensInscription(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {

        return FabriqueCardexDAO.getInstance().getInscriptionDAO().findLiensInscription(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les photos associ�es � un dossier.  La proc�dure appel�e est
     * dans OraclePhotoDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des photos associ�es.
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPhotoDAO().findLiensPhoto(subject, dossier.getCle(),
                dossier.getSite(), null, GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les photos associ�es � un dossier.  La proc�dure appel�e est
     * dans OraclePhotoDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des photos associ�es.
     */
    public Collection findLiensPieceJointe(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException{
        return FabriqueCardexDAO.getInstance().getPhotoDAO().findLiensPieceJointe(subject, dossier.getCle(),
                dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les suivis associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleSuiviDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des suivis associ�s.
     */
    public Collection findLiensSuivi(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSuiviDAO().findLiensSuivi(subject, dossier.getCle(),
                dossier.getSite(),GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Retourne les services d'urgence associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleUrgenceDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des Urgence associ�s.
     */
    public List<UrgenceVO> findLiensUrgence(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getUrgenceDAO().findLiensUrgence(subject, dossier);
    }

    /**
     * Retourne les suivis associ�s � un dossier.  La proc�dure appel�e est
     * dans OracleConsignationDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des suivis associ�s.
     */
    public Collection findLiensConsignation(CardexAuthenticationSubject subject,
            Dossier dossier) throws DAOException {
        return FabriqueCardexDAO.getInstance().getConsignationDAO().findLiensConsignation(subject, dossier.getCle(),
                dossier.getSite(),GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Recherche de dossiers � l'aide de crit�res de recherche.
     * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique).
     * (SP_L4_do_dossier PACKAGE CARDEX_LIRE_DOC).  Avec Java, il ne semble pas
     * possible de r�cup�rer les donn�es retourn�es par ce genre de proc�dure.
     * C'est pourquoi la requ�te SQL est g�n�r�e plut�t dans le code Java avant
     * d'�tre envoy�e � Oracle.  Le resultSet retourn� par les recherches est
     * trait� dans la routine traitementResultSet.
     * Proc�dure appel�e : G�n�r�e ici.
     * Date de cr�ation : (2002-02-06)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria CriteresRechercheDossier : Crit�res de recherche.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator Liste des dossiers retourn�s par la recherche.
     */
    public List<Dossier> select(CardexAuthenticationSubject subject, CriteresRechercheDossier criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Dossier> listDossier = new ArrayList<Dossier>();
        retraitDuSiteApplicablePourAccesInterditEtAvisDeGuet(criteria);
        PreparerSQL preparerSQL = (new DossierCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireDossierRowCallBackHandler(listDossier));
 	   
    	return listDossier;	
    }
    
    public Integer nombreDeDossierRecherche(CardexAuthenticationSubject subject,CriteresRechercheDossier criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
        retraitDuSiteApplicablePourAccesInterditEtAvisDeGuet(criteria);

        PreparerSQL preparerSQL = (new DossierCountSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }    
    
    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.integration.dao.DossierDAO#rechercheDossierAutoexeclusionExpiration(java.sql.Connection)
     */
    public List<DossierVO> rechercheDossierAutoexeclusionExpiration(Connection connection) throws DAOException{
		final List<DossierVO> resultat = new ArrayList<DossierVO>();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_DOSSIER_PreventFinContrat", 1, 1, rch);
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				DossierVO dossierVO = new DossierVO();
				SujetVO sujetVO = new SujetVO();
				dossierVO.setCle(rs.getLong(1));
				dossierVO.setSite(rs.getLong(2));
				dossierVO.setNumeroCardex(rs.getString(4));
				sujetVO.setCle(rs.getLong(5));
				sujetVO.setSite(rs.getLong(6));
				sujetVO.setNumeroFiche(rs.getString(7));
				dossierVO.addSujet(sujetVO);
				resultat.add(dossierVO);
			}
    	};		
		
		storeProcTemplate.query(rcbh, false);
		
		return resultat;	
    }
    
    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.integration.dao.DossierDAO#rechercheDossierAutoexeclusionAideInitiale(java.sql.Connection)
     */
    public List<DossierVO> rechercheDossierAutoexeclusionAideInitiale(Connection connection) throws DAOException{
		final List<DossierVO> resultat = new ArrayList<DossierVO>();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		 
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_DOSSIER_PreveAideInitiale", 1, 1, rch);
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				DossierVO dossierVO = new DossierVO();
				SujetVO sujetVO = new SujetVO();
				dossierVO.setCle(rs.getLong(1));
				dossierVO.setSite(rs.getLong(2));
				dossierVO.setNumeroCardex(rs.getString(4));
				sujetVO.setCle(rs.getLong(5));
				sujetVO.setSite(rs.getLong(6));
				sujetVO.setNumeroFiche(rs.getString(7));
				dossierVO.addSujet(sujetVO);
				resultat.add(dossierVO);
			}
    	};		
		
		storeProcTemplate.query(rcbh, false);
		
		return resultat;	
    }
    
    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.integration.dao.DossierDAO#rechercheDossierAutoexeclusionExpiration(java.sql.Connection)
     */
    public List<DossierVO> rechercheDossierAutoexeclusionBonifies(Connection connection) throws DAOException{
		final List<DossierVO> resultat = new ArrayList<DossierVO>();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_DOSSIER_Bonifies", 1, 1, rch);
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				DossierVO dossierVO = new DossierVO();
				SujetVO sujetVO = new SujetVO();
				dossierVO.setCle(rs.getLong(1));
				dossierVO.setSite(rs.getLong(2));
				dossierVO.setNumeroCardex(rs.getString(4));
				sujetVO.setCle(rs.getLong(5));
				sujetVO.setSite(rs.getLong(6));
				sujetVO.setNumeroFiche(rs.getString(7));
				dossierVO.addSujet(sujetVO);
				resultat.add(dossierVO);
			}
    	};		
		
		storeProcTemplate.query(rcbh, false);
		
		return resultat;	
    }

    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.integration.dao.DossierDAO#assignerDossierConfidentialiteC(java.sql.Connection, com.lotoquebec.cardex.business.vo.DossierVO)
     */
	public void assignerDossierConfidentialiteC(Connection connection, final DossierVO dossierVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossierVO.getCle());
    			OracleDAOUtils.setLong(callableStatement, 2, dossierVO.getSite());
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_DO_DOSSIER_ASSIGNER_CC_C", 2, rch);

    	storeProcTemplate.query( false );		
	}

	/*
	 * (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.DossierDAO#obtenirLienDossierSocietePresentsBillet(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.business.vo.DossierVO)
	 */
	public List<LiaisonBilletSocieteVO> obtenirLienDossierSocietePresentsBillet(CardexAuthenticationSubject subject, final Dossier dossierVO) throws DAOException{
		final List<LiaisonBilletSocieteVO> listeLienPresents = new ArrayList<LiaisonBilletSocieteVO>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossierVO.getCle()); // NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 2, dossierVO.getSite()); // NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE,
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR); // rc1          OUT DTS_LIEN_BILLET
			}
    	};
    	
    	storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_LIEN_BILLET_PRESENTE", 3, 3, rch);
    	
    	RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				LiaisonBilletSocieteVO liaisonBilletSocieteVO = new LiaisonBilletSocieteVO();
				SocieteVO societeVO = new SocieteVO();
				
				liaisonBilletSocieteVO.setCleLDD(rs.getLong(1));
				liaisonBilletSocieteVO.setSiteLDD(rs.getLong(2));
				
				liaisonBilletSocieteVO.setRole(rs.getLong(3));
				
				societeVO.setCle(rs.getLong(4));
				societeVO.setSite(rs.getLong(5));
				
				liaisonBilletSocieteVO.setDossier(dossierVO);
				liaisonBilletSocieteVO.setSociete(societeVO);
				
				listeLienPresents.add(liaisonBilletSocieteVO);
			}
    	};		
    	
    	storeProcTemplate.query( rowCallbackHandler, true );    	
    	
    	return listeLienPresents;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.DossierDAO#obtenirLienDossierSocieteRequisBillet(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.business.vo.DossierVO)
	 */
	public List<LiaisonBilletSocieteVO> obtenirLienDossierSocieteRequisBillet(CardexAuthenticationSubject subject, final Dossier dossierVO) throws DAOException{
		final List<LiaisonBilletSocieteVO> listeLienPresents = new ArrayList<LiaisonBilletSocieteVO>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossierVO.getCle()); // NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 2, dossierVO.getSite()); // NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE,
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR); // rc1          OUT DTS_LIEN_BILLET
			}
    	};
    	
    	storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_L_LIEN_BILLET_REQUIS", 3, 3, rch);
    	
    	RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				LiaisonBilletSocieteVO liaisonBilletSocieteVO = new LiaisonBilletSocieteVO();
				SocieteVO societeVO = new SocieteVO();
				
				liaisonBilletSocieteVO.setCleLDD(rs.getLong(1));
				liaisonBilletSocieteVO.setSiteLDD(rs.getLong(2));
				
				liaisonBilletSocieteVO.setRole(rs.getLong(3));
				
				societeVO.setCle(rs.getLong(4));
				societeVO.setSite(rs.getLong(5));
				
				liaisonBilletSocieteVO.setDossier(dossierVO);
				liaisonBilletSocieteVO.setSociete(societeVO);
				
				listeLienPresents.add(liaisonBilletSocieteVO);
			}
    	};		
    	
    	storeProcTemplate.query( rowCallbackHandler, true );    	
    	
    	return listeLienPresents;
	}	

    public List<Dossier> rapportAmbulance(CardexAuthenticationSubject subject, AmbulanceDossierRapportVO ambulanceDossierRapportVO) throws DAOException{
    	final List<Dossier> liste = new ArrayList<Dossier>();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new AmbulanceDossierRapportSQL()).obtenirPreparerSQLRapportAmbulance(ambulanceDossierRapportVO);
    	
    	RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
    		public void processRow(ResultSet rs) throws SQLException{
                DossierVO dossier = new DossierVO();
                dossier.setCle(rs.getLong("L_CO_REFERENCE"));
                dossier.setSite(rs.getLong("L_CO_REF_SITE"));
                liste.add(dossier);
  			}
    	};
  	   
    	template.query(preparerSQL, rowCallbackHandler);
    	return liste;
    }
			
    //Mise-�-jour de la s�v�rit� � 4 pour les dossiers d'invesigation qui arrivent � expiration dans 90 jours.
    public void severite4DossierInvestigationExpiration90Jours(Connection connection) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_DO_SEVERITE_4", 0, null);

    	storeProcTemplate.query( false );		
   }

    //Mise-�-jour de la s�v�rit� des dossiers d'invesigation actifs et dont la s�v�rit� n'est pas � 3
	  public void severite3DossierInvestigationDossierActif(Connection connection) throws DAOException {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
	
			PreparerCallableStatement rch = new PreparerCallableStatement(){
	    		public void preparer(CallableStatement callableStatement) throws SQLException {
				}
	    	};
	    	
	    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_DO_SEVERITE_3", 0, null);
	
	    	storeProcTemplate.query( false );		
	   }
	
    //Mise-�-jour de la s�v�rit� � 2 pour les dossiers d'invesigation arriv� � expiration et dont la s�v�rit� n'est pas � 2.
    public void severite2DossierInvestigationExpiration(Connection connection) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_DO_SEVERITE_2", 0, null);

    	storeProcTemplate.query( false );		
   }

	    /**
	     * V�rification des dossiers d'autoexclusion. Les dossiers expir�s, selon les r�gles
	     * document�es dans la proc�dure, sont mis inactifs, ainsi que les inscriptions associ�es.
	     * Cette proc�dure �tait autrefois appel�e dans Oracle � partir d'un diff�r�; elle l'est maintenant � partir d'un EJB.
	     * Proc�dure appel�e : CARDEX_SPECIAL.SP_ACTIVE_INSCRIPTION()
	     * Date de cr�ation : (2012-05-23)
	     * @author Fran�ois Gu�rin
	     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	     * rupture de connexion avec la base de donn�es, ou que la table demand�e
	     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
	     * d'une "stored procedure".
	     */
	    public void desactiveDossiersAutoexclusion(Connection connection)
	            throws DAOException {
	        CallableStatement callableStatement = null;
	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_SPECIAL.SP_ACTIVE_INSCRIPTION(); end;");
	                callableStatement.execute();
	        }
	        catch (SQLException se) {
	            throw new DAOException(se);
	        }
	        finally {
				if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
	                    throw new DAOException(e);
			        }
				}
	        }
	    }    

	    /**
	     * �puration de la table AC_ACCES. L'�puration est faite une fois par ann�e et supprime
	     * les donn�es ant�rieures � 3 ans, sauf les insertions.
	     * En date du 24-05-2012, seuls les acc�s aux dossiers inactifs sont trait�s.
	     * Proc�dure appel�e : CARDEX_SPECIAL.sp_epuration_acces()
	     * Date de cr�ation : (2012-05-23)
	     * @author Fran�ois Gu�rin
	     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	     * rupture de connexion avec la base de donn�es, ou que la table demand�e
	     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
	     * d'une "stored procedure".
	     */
	    public void epurationAcces(Connection connection)
	            throws DAOException {
	        CallableStatement callableStatement = null;
	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_SPECIAL.sp_epuration_acces(); end;");
	                callableStatement.execute();
	        }
	        catch (SQLException se) {
	            throw new DAOException(se);
	        }
	        finally {
				if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
	                    throw new DAOException(e);
			        }
				}
	        }
	    }    
	    
		public void updateLienEvaluation(CardexAuthenticationSubject subject, Dossier dossier, Evaluation evaluation) throws DAOException {
			FabriqueCardexDAO.getInstance().getEvaluationDAO().update(subject, evaluation);
		}

		public Collection findLiensEvaluation(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException{
			  return FabriqueCardexDAO.getInstance().getEvaluationDAO().findLiensEvaluation(subject, dossier.getCle(), dossier.getSite());
		}
		
	    /**
	     * Association d'une �valuation � un sujet). Utilisant la
	     * stored procedure SP_E_EV_EVALUATION PACKAGE CARDEX_LIEN.
	     * Date de cr�ation : (2012-06-01)
	     * @author guerinf
	     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
	     * l'utilisateur.
	     * @param sujet Sujet : sujet de base.
	     * @param Evaluation evaluation : �valuation associ�s.
	     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	     * rupture de connexion avec la base de donn�es, ou que la table demand�e
	     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
	     * d'une "stored procedure".
	     * @return Evaluation Une instance de evaluation associ�.
	     */
	    public Evaluation addLienEvaluation(CardexAuthenticationSubject subject,
	    		Dossier dossier, Evaluation evaluation) throws DAOException {
	        return FabriqueCardexDAO.getInstance().getEvaluationDAO().insert(subject, evaluation);
	    }

	    /**
	     * Supprime un lien (association d'une �valuation � un sujet).  Utilisant la
	     * stored procedure SP_E_EV_EVALUATION PACKAGE CARDEX_LIEN.
	     * Date de cr�ation : (2012-06-01)
	     * @author guerinf
	     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
	     * l'utilisateur.
	     * @param sujet Sujet : sujet de base.
	     * @param Evaluation evaluation : �valuation associ�s.
	     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	     * rupture de connexion avec la base de donn�es, ou que la table demand�e
	     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
	     * d'une "stored procedure".
	     * @return Evaluation Une instance de evaluation associ�.
	     */
	    public Evaluation deleteLienEvaluation(CardexAuthenticationSubject subject,
	    		Dossier dossier, Evaluation evaluation) throws DAOException {
	        return FabriqueCardexDAO.getInstance().getEvaluationDAO().delete(subject, evaluation);
	    }

	    
	    /**
	     * Sert � v�rifier s'il existe des dossiers actifs de d�taillants visit�s par des clients myst�res (conformit� � la loi 84).
	     * Cette proc�dure est appel�e par le diff�r� CDX00_00013. S'il y a des dossiers actifs, le traitement continue pour
	     * v�rifier si des changements ont �t� apport�s aux d�taillants rattach�s � ces dossiers.
	     * 
	     */
	    public Integer verifierNombreDossierVisite(CardexAuthenticationSubject subject) throws DAOException {
	    	JDBCTemplate template = new JDBCTemplate(subject);

	        PreparerSQL preparerSQL = (new DossierCountSQL()).construireSQLDossierVisite(subject);
	  	   
	    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
	  			@Override
	  			public void processRow(ResultSet rs) throws SQLException {
	  				object = rs.getInt(1);
	  			}
	    	};
	  	   
	    	template.query(preparerSQL, unEnregistrementPresent);
	  	   
	        return (Integer) unEnregistrementPresent.getObject();
	     }    

	     /**
	      * Recherche directe d'un dossier avec le num�ro de dossier.
	      * Appel� � partir de la recherche directe dans le menu principal.
	      * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L_DO_DOSSIER_DIRECT
	      * Date de cr�ation : (2013-08-07)
	      * @author Fran�ois Gu�rin
	      * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
	      * @param criteria Sujet : sujet � rechercher
	      * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	      * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	      * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	      * "stored procedure".
	      * @return dossier : donn�es du dossier trouv�.
	      */
	     	public Dossier rechercheDirecte(CardexAuthenticationSubject subject, Dossier criteria) throws DAOException{
	     	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	     	  CallableStatement callableStatement = null;
	     	  ResultSet resultSet = null;
	     	  Dossier dossier = new DossierVO();
	     	  try {
	     		 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_DO_DOSSIER_DIRECT (?,?); end;");
	     		 callableStatement.setString(1,criteria.getNumeroCardex());
	     		 callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
	     		 callableStatement.execute();
	     		 resultSet = (ResultSet)callableStatement.getObject(2);
	     		 //Traitement du dossier retourn� (s'il y a lieu)
	     		 if (resultSet.next()){
	     			dossier = traitementResultSetFind(resultSet);
	     		 }
	     		 return dossier;
	     	   } catch (SQLException se) {
	     		 throw new DAOException(se);
	     	   } finally {
	     			if(resultSet != null) {
	     				try {
	     						resultSet.close();
	     				} catch (java.sql.SQLException e) {
	     					throw new DAOException(e);
	     				}
	     			}
	     			if(callableStatement != null) {
	     				try {
	     						callableStatement.close();
	     				} catch (java.sql.SQLException e) {
	     					throw new DAOException(e);
	     				}
	     			}
	     			if (connection != null) {
	     				try{
	     					 if(!connection.getAutoCommit())
	     					 {
	     						connection.rollback();
	     					 }
	     					   connection.close();
	     				} catch (SQLException e) {
	     					throw new DAOException(e);
	     				}
	     			}
	     	   } //finally
	     	}    

	//Ajout d'une entr�e dans la table des acc�s
	public void ajoutAcces(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException {
		FabriqueCardexDAO.getInstance().getAccesDAO().ajoutAcces(subject, dossier.getCle(), dossier.getSite(), GlobalConstants.GenreFichier.DOSSIER);
	}

    /**
     * Recherche du dernier dossier cr�� pour un d�taillant dans le cadre d'une visite de client-myst�re.
     * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_DERNIER_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Dossier : Dossier � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public Dossier dernierDossierClientMystere(CardexAuthenticationSubject subject, final Societe societe) throws DAOException {
    	StoreProcTemplate template = new StoreProcTemplate(subject);
    	
		PreparerCallableStatement pcs = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
	            callableStatement.setLong(1, societe.getCle());
	            callableStatement.setLong(2, societe.getSite());
	            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);

			}
		};	
		template.prepareCall("CARDEX_LIRE_DOC.SP_DERNIER_DOSSIER", 3, 3, pcs);
    	
    	UnEnregistrementPresent<Dossier> unEnregistrementPresent = new UnEnregistrementPresent<Dossier>(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				object = new DossierVO();
                object.setCategorie(rs.getLong("I_CA_CLE"));
                object.setReference1(rs.getString("V_DO_REFERENCE1"));
                object.setNumeroDossier(rs.getString("V_DO_ANCIENNE_REFERENCE"));
			}
    	};
    	template.query(unEnregistrementPresent);
    	
        return unEnregistrementPresent.getObject();
    }   

    public Dossier dernierDossierEchantillionClientMystere(CardexAuthenticationSubject subject, final Societe societe) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, societe.getCle());
		        callableStatement.setLong(2, societe.getSite());
				callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			}
		};		
		storeProcTemplate.prepareCall("CARDEX_LIRE_DOC.SP_DERNIER_ECHANTILLION", 3, 3, rch);
		
		UnEnregistrementPresent<Dossier> unEnregistrementPresent = new UnEnregistrementPresent<Dossier>() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				long cle = rs.getLong(1);
				long site = rs.getLong(2);
				object = new DossierVO(cle,site);
				object.setNumeroDossier(rs.getString(3));
			}
		};
		
		storeProcTemplate.query(unEnregistrementPresent);
		
		return unEnregistrementPresent.getObject();
    }
    
    /**
     * RA0008 Dossier d'�chantillon actif: 
     * Dossier de statut actif de cat�gorie "�chantillon" qui poss�de au moins 
     * une soci�t� qui n'a pas encore �t� visit�e.  Cette soci�t� n'aura pas 
     * d'autre dossier de type "vente aux mineurs" que le dossier d'�chantillon 
     * lui-m�me pour cette m�me vague.
     * 
     * @param subject
     * @return
     * @throws DAOException
     */
    public Set<Dossier> echantillonDossierClientMystereActif(CardexAuthenticationSubject subject) throws DAOException {
    	final Set<Dossier> echantillonDossiers = new HashSet<Dossier>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
		};		
		storeProcTemplate.prepareCall("CARDEX_LIRE_DOC.SP_DOSSIER_ECHANTILLON_ACTIF", 1, 1, rch);
		
		RowCallbackHandler rowCallbackHandler = new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				long cle = rs.getLong(1);
				long site = rs.getLong(2);
				echantillonDossiers.add( new DossierVO(cle,site) );
			}
		};
		
		storeProcTemplate.query(rowCallbackHandler);
		
		return echantillonDossiers;    	
    }

    
    
}
