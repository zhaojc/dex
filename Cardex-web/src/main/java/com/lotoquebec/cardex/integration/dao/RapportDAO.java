package com.lotoquebec.cardex.integration.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.RapportDossierVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.rapport.AccesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ActifIntervenantDossierRapportVO_CDX_0102;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.ContratsAutoexclusionDossierRapportVO_CDX_0060;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.DelaiTraitementEnqueteRapportVO_CDX_0246;
import com.lotoquebec.cardex.business.vo.rapport.EmployeDossierRapportVO_CDX_0042;
import com.lotoquebec.cardex.business.vo.rapport.EnqueteurJournalRapportVO_CDX_0053;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxAutoexclusionActifRapportVO_CDX_0260;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxFraudeFondeRapportVO_CDX_0261;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxTricherieFondeRapportVO_CDX_0262;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVOCDX_00070;
import com.lotoquebec.cardex.business.vo.rapport.ReperageAutoexclusionDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.ResponsableSocieteVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.SeveriteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SiteIntervenantRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SocietesInactivesRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatutDossierRapportVO_CDX_0055;
import com.lotoquebec.cardex.integration.dao.clientMystere.SocieteAvecDossierClientMystereRowCallbackHandler;
import com.lotoquebec.cardex.integration.dao.clientMystere.SocieteSansDossierClientMystereRowCallbackHandler;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.TransCallStatementHandler;
import com.lotoquebec.cardexCommun.util.JourneeOuvrable;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class RapportDAO {

	
	private class CDX_00070RowCallbackHandler implements RowCallbackHandler{
		
		private List<RapportVOCDX_00070> rapportVOCDX_00070List;
		
		public CDX_00070RowCallbackHandler(List<RapportVOCDX_00070> rapportVOCDX_00070List) {
			super();
			this.rapportVOCDX_00070List = rapportVOCDX_00070List;
		}

		public void processRow(ResultSet rs) throws SQLException {
			RapportVOCDX_00070 rapportVOCDX_00070 = new RapportVOCDX_00070();
			rapportVOCDX_00070.setEntite(rs.getString(1));
			rapportVOCDX_00070.setSite(rs.getString(2));
			rapportVOCDX_00070.setDebutDate(rs.getTimestamp(3));
			rapportVOCDX_00070.setNumeroCardex(rs.getString(4));
			rapportVOCDX_00070.setTexteNarration(rs.getString(5));
			rapportVOCDX_00070.setIntervenant(rs.getString(6));
			rapportVOCDX_00070.setGroupe(rs.getString(7));
			rapportVOCDX_00070.setNature(rs.getString(8));
			rapportVOCDX_00070List.add(rapportVOCDX_00070);
		}

		public List<RapportVOCDX_00070> getRapportVOCDX_00070List() {
			return rapportVOCDX_00070List;
		}
		
	}
	
	/**
	 * Rapports de rep�rage.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public Collection rapportReperageAccesInterdit(CardexAuthenticationSubject subject, String procedure, CritereRapportVO rapportVO, long site) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin "+procedure+" (?,?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.setLong(3, site);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
    		   List liste = new ArrayList();
    	       while (resultSet.next()){
    				Map dossier = new HashMap();
    				dossier.put("V_DO_ANCIENNE_REFERENCE",OracleDAOUtils.getString(resultSet, "V_DO_ANCIENNE_REFERENCE"));
    				dossier.put("V_DO_NUMERO_DOSSIER",OracleDAOUtils.getString(resultSet, "V_DO_NUMERO_DOSSIER"));
    				dossier.put("D_DO_DATE_DEBUT",resultSet.getTimestamp("D_DO_DATE_DEBUT"));
    				dossier.put("D_DO_DATE_FIN",resultSet.getTimestamp("D_DO_DATE_FIN"));
    				dossier.put("V_SU_NOM",OracleDAOUtils.getString(resultSet, "V_SU_NOM"));
    				dossier.put("V_SU_PRENOM",OracleDAOUtils.getString(resultSet, "V_SU_PRENOM"));
    				dossier.put("V_SU_REFERENCE_3",OracleDAOUtils.getString(resultSet, "V_SU_REFERENCE_3"));
    				dossier.put("COUNT(D.L_DO_CLE)", resultSet.getBigDecimal("COUNT(D.L_DO_CLE)"));
    				DossierBusinessDelegate delegate = new DossierBusinessDelegate();
    				//On va d'abord chercher le sujet reli�
    		        Collection liensSujets;
    		        Iterator it;
    		        Sujet sujet = new SujetVO();
    		        sujet.setCle(resultSet.getLong("L_SU_CLE"));
    		        sujet.setSite(resultSet.getLong("L_SI_CLE"));
    				SujetBusinessDelegate delegateSujet = new SujetBusinessDelegate();
    		        // Recherche de la photo � afficher
    		        Collection liensPhoto = delegateSujet.findLiensPhoto(subject, sujet);
    		        it = liensPhoto.iterator();
    		        PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
    		        while (it.hasNext()) {
    	                Photo     linkPhoto = (Photo) it.next();
    	                if(linkPhoto.isSelectionner()){
    	                	FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, linkPhoto.getLienElement(), linkPhoto.getLienSiteElement(), false);
    	                	if(fichierMultimediaVO.getImageByte() != null){
    	                		InputStream photo = new ByteArrayInputStream(fichierMultimediaVO.getImageByte());
    	                		dossier.put("PHOTO", photo);
    	                	}
    	                }
    		        }//while
    				liste.add(dossier);
    		    }
    	       return liste;
            } catch (SQLException se) {
                throw new DAOException(se);
    		} catch (BusinessResourceException e) {
    			throw new DAOException(e);
    		} catch (BusinessException be) {
    			throw new DAOException(be);
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
	 * Rapports cumulatifs.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet procedureSite(String procedure, CritereRapportVO rapportVO, long site, Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		
        try {
            callableStatement = connection.prepareCall(
                    "begin "+procedure+" (?,?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.setLong(3, site);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Rapports sur les contrats.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportContrats(ContratsAutoexclusionDossierRapportVO_CDX_0060 contratsAutoexclusionDossierRapportVO_CDX_0060, Connection connection) throws DAOException {

		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_MENSUEL_AUTOEXCLUSIONS (?,?,?,?,?); end;");
            callableStatement.setDate(1,new Date(contratsAutoexclusionDossierRapportVO_CDX_0060.getDateDebutDu().getTime()));
            callableStatement.setDate(2,new Date(contratsAutoexclusionDossierRapportVO_CDX_0060.getDateDebutAu().getTime()));
            callableStatement.setLong(3,contratsAutoexclusionDossierRapportVO_CDX_0060.getSite());
            callableStatement.setLong(4,contratsAutoexclusionDossierRapportVO_CDX_0060.getCategorie());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(5);
            
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Rapport sur les employ�s li�s � des dossiers
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportEmployeDossier(EmployeDossierRapportVO_CDX_0042 employeDossierRapportVO_CDX_0042,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_EMPLOYES_DOSSIERS (?,?,?,?,?,?,?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1, new Timestamp(employeDossierRapportVO_CDX_0042.getDateDebutDu().getTime()));
            callableStatement.setTimestamp(2, new Timestamp(employeDossierRapportVO_CDX_0042.getDateDebutAu().getTime()));
            callableStatement.setLong(3,employeDossierRapportVO_CDX_0042.getEntite());
            callableStatement.setLong(4,employeDossierRapportVO_CDX_0042.getSite());
            callableStatement.setLong(5,employeDossierRapportVO_CDX_0042.getGenre());
            callableStatement.setLong(6,employeDossierRapportVO_CDX_0042.getNature());
            callableStatement.setLong(7,employeDossierRapportVO_CDX_0042.getType());
            callableStatement.setLong(8,employeDossierRapportVO_CDX_0042.getCategorie());
            callableStatement.setLong(9,employeDossierRapportVO_CDX_0042.getRole());
            callableStatement.setLong(10,employeDossierRapportVO_CDX_0042.getStatut());
            callableStatement.setLong(11,employeDossierRapportVO_CDX_0042.getFonde());
            callableStatement.registerOutParameter(12, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(12);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Rapport sur les rep�rages avec crit�re du nombre de rep�rages.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public Collection rapportReperageAutoexclusion(CardexAuthenticationSubject subject, ReperageAutoexclusionDossierRapportVO criteria, String procedure, long nombreReperages) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(criteria.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(criteria.getDateDebutAu().getTime()));
            callableStatement.setLong(3,criteria.getSite());
            callableStatement.setLong(4,nombreReperages);
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(5);
   		   List liste = new ArrayList();
	       while (resultSet.next()){
				Map dossier = new HashMap();
				dossier.put("V_DO_ANCIENNE_REFERENCE",OracleDAOUtils.getString(resultSet, "V_DO_ANCIENNE_REFERENCE"));
				dossier.put("V_DO_NUMERO_DOSSIER",OracleDAOUtils.getString(resultSet, "V_DO_NUMERO_DOSSIER"));
				dossier.put("D_DO_DATE_DEBUT",resultSet.getTimestamp("D_DO_DATE_DEBUT"));
				dossier.put("D_DO_DATE_FIN",resultSet.getTimestamp("D_DO_DATE_FIN"));
				dossier.put("V_SU_NOM",OracleDAOUtils.getString(resultSet, "V_SU_NOM"));
				dossier.put("V_SU_PRENOM",OracleDAOUtils.getString(resultSet, "V_SU_PRENOM"));
				dossier.put("V_SU_REFERENCE_3",OracleDAOUtils.getString(resultSet, "V_SU_REFERENCE_3"));
				dossier.put("COUNT(D.L_DO_CLE)", resultSet.getBigDecimal("COUNT(D.L_DO_CLE)"));
				DossierBusinessDelegate delegate = new DossierBusinessDelegate();
				//On va d'abord chercher le sujet reli�
		        Collection liensSujets;
		        Iterator it;
		        Sujet sujet = new SujetVO();
		        sujet.setCle(resultSet.getLong("L_SU_CLE"));
		        sujet.setSite(resultSet.getLong("L_SI_CLE"));
				SujetBusinessDelegate delegateSujet = new SujetBusinessDelegate();
		        // Recherche de la photo � afficher
		        Collection liensPhoto = delegateSujet.findLiensPhoto(subject, sujet);
		        it = liensPhoto.iterator();
		        PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
		        while (it.hasNext()) {
	                Photo     linkPhoto = (Photo) it.next();
	                if(linkPhoto.isSelectionner()){
	                	FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, linkPhoto.getLienElement(), linkPhoto.getLienSiteElement(), false);
	                	if(fichierMultimediaVO.getImageByte() != null){
	                		InputStream photo = new ByteArrayInputStream(fichierMultimediaVO.getImageByte());
	                		dossier.put("PHOTO", photo);
	                	}
	                }
		        }//while
				liste.add(dossier);
		    }
	       return liste;
        } catch (SQLException se) {
            throw new DAOException(se);
		} catch (BusinessResourceException e) {
			throw new DAOException(e);
		} catch (BusinessException be) {
			throw new DAOException(be);
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
	 * Rapports sur les statuts des dossiers.
	 * Rapport CDX_0055. Remplace les anciens rapports POL pour qu'ils soient plus g�n�riques (requ�te RSI R12-0078)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet statutDossiers(StatutDossierRapportVO_CDX_0055 statutDossierRapportVO_CDX_0055,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_STATUT_DOSSIERS (?,?); end;");
            callableStatement.setLong(1, statutDossierRapportVO_CDX_0055.getNature());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Rapport statistique sur le temps consacr�
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet tempsConsacre(StatistiqueDossierRapportVO statistiqueDossierRapportVO,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_TEMPS_CONSACRE (?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(statistiqueDossierRapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(statistiqueDossierRapportVO.getDateDebutAu().getTime()));
            
            if(statistiqueDossierRapportVO.getSite() == 0){
            	callableStatement.setString(3,"%");
            }else{
            	callableStatement.setString(3,String.valueOf(statistiqueDossierRapportVO.getSite()));
            }
            callableStatement.setLong(4,statistiqueDossierRapportVO.getGenre());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(5);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
     * Rapport sur les enqu�tes de Loto-Qu�bec sur les personnes au registre (PAR).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportEnqueteReclamation(String anneeMois,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_PAR (?,?); end;");
            callableStatement.setString(1,anneeMois);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
    
	}
	/**
     * Rapport sur les incidents de la DCSI.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportIncidentsDCSI(String annee,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_INCIDENTS_DCSI (?,?); end;");
            callableStatement.setString(1,annee);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }

	}

	/**
     * Rapport pour produire les listes de dossiers partag�s, par intervenant ou par responsable.
     * @author Fran�ois Gu�rin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet listesPartage(String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
    
	}
	/**
     * Rapport pour produire les listes de dossiers actifs par intervenant.
     * @author Fran�ois Gu�rin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet listesDossiersActifs(ActifIntervenantDossierRapportVO_CDX_0102 rapportDossierVO,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_DOSSIERS_ACTIFS (?,?,?,?); end;");
            
            callableStatement.setLong(1,rapportDossierVO.getGenre());
            
            if(StringUtils.isEmpty(rapportDossierVO.getIntervenant())){
            	callableStatement.setString(2,"%"); //Crit�re g�n�rique pour obtenir tous les intervenants.
            }else{
            	callableStatement.setString(2,rapportDossierVO.getIntervenant());
            }
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
     
	}
	/**
     * Rapport pour produire les listes de dossiers actifs par enqu�teur de Loto-Qu�bec.
     * @author Fran�ois Gu�rin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet listesDossiersActifsEnqueteurLQ(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_DOSSIERS_ACTIFS_ENQ_LQ (?); end;");
            
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }

	}

	/**
	 * Impression des fiches (sujet, soci�t� et dossier)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportImpressionFiche(long cle, long site, String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?); end;");
            callableStatement.setLong(1,cle);
            callableStatement.setLong(2,site);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
      
	}
	
	/**
	 * Impression du rapport sur les suivis 30 jours
     * @author Fran�ois Gu�rin
     * @param dateDebut String : date de d�but
     * @param dateFin String : date de fin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportSuivis(SiteIntervenantRapportVO rapportVO, String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.setLong(3,rapportVO.getSite());

            if(StringUtils.isEmpty(rapportVO.getIntervenant())){
            	callableStatement.setString(4,"%");
            }else{
            	callableStatement.setString(4,rapportVO.getIntervenant());
            }
            callableStatement.setLong(5,rapportVO.getActivite());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(6);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Impression du rapport sur les suivis d'enqu�tes CDX_0098
     * @author Fran�ois Gu�rin
     * @param dateDebut String : date de d�but
     * @param dateFin String : date de fin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public Collection rapportSuivisEnquetes(SiteIntervenantRapportVO rapportVO,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_SUIVIS (?,?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.setLong(3,rapportVO.getSite());
           	callableStatement.setLong(4,rapportVO.getNature());
            callableStatement.setLong(5,rapportVO.getActivite());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(6);
    		List liste = new ArrayList();
    		   while (resultSet.next()){
    				Map dossier = new HashMap();
					Date dateCreationDossier = new Date(resultSet.getTimestamp("Date_creation_dossier").getTime());
    				Date dateCreationSuivi = null; 
    				Date dateSuiviComplete = null; 
        			if(resultSet.getTimestamp("Date_creation_suivi") != null){
        				dateCreationSuivi = new Date(resultSet.getTimestamp("Date_creation_suivi").getTime());
        			}
        			if(resultSet.getTimestamp("Date_suivi_completee") != null){
        				dateSuiviComplete = new Date(resultSet.getTimestamp("Date_suivi_completee").getTime());
        			}
    				int nombreJours = 0;
    				//Pour les d�lais de cr�ation des suivis, on tient compte des jours ouvrables.
    				if(dateCreationSuivi != null){
		    			nombreJours = JourneeOuvrable.calculerDifference(dateCreationDossier, dateCreationSuivi);
	    				if (nombreJours >= 50){
	    					dossier.put("DELAI_DOSSIER_SUIVI", "> 50 jours");
	    				}else{
	    					dossier.put("DELAI_DOSSIER_SUIVI", String.valueOf(nombreJours));
	    				}
    				}
    				nombreJours = 0;
    				if(dateSuiviComplete != null){
    					Calendar dateSuiviCal = new GregorianCalendar();
						Calendar dateCompleteCal = new GregorianCalendar();
						dateSuiviCal.setTime(dateCreationSuivi);
						dateCompleteCal.setTime(dateSuiviComplete);
    					//Pour les d�lais de suivis compl�t�s, on calcule le nombre de jours calendrier �coul�s.
    					while (dateSuiviCal.before(dateCompleteCal)){
    						dateSuiviCal.add(Calendar.DATE, 1);
    						++nombreJours;
    						if(nombreJours >= 50) break;
    					}
	    				if (nombreJours >= 50){
	    					dossier.put("DELAI_SUIVI_CREE_COMPLETE", "> 50 jours");
	    				}else{
	    					dossier.put("DELAI_SUIVI_CREE_COMPLETE", String.valueOf(nombreJours));
	    				}

    				}    				
					dossier.put("NATURE",OracleDAOUtils.getString(resultSet, "Nature"));
					dossier.put("TYPE",OracleDAOUtils.getString(resultSet, "Type_dossier"));
					dossier.put("CATEGORIE",OracleDAOUtils.getString(resultSet, "Categorie"));
					dossier.put("NUMERO_CARDEX",OracleDAOUtils.getString(resultSet, "Numero_Cardex"));
					dossier.put("TYPE_SUIVI",OracleDAOUtils.getString(resultSet, "Type_suivi"));
					dossier.put("DATE_CREATION_DOSSIER",resultSet.getTimestamp("Date_creation_dossier"));
					dossier.put("DATE_CREATION_SUIVI",resultSet.getTimestamp("Date_creation_suivi"));
					dossier.put("DATE_SUIVI_COMPLETEE",resultSet.getTimestamp("Date_suivi_completee"));
					dossier.put("NOM",OracleDAOUtils.getString(resultSet, "Nom"));
					dossier.put("PRENOM",OracleDAOUtils.getString(resultSet, "Prenom"));
					dossier.put("STATUT",OracleDAOUtils.getString(resultSet, "Statut"));
					liste.add(dossier);
				}
    		   return liste;
    	}catch (SQLException se) {
            throw new DAOException(se);
        }
        
	}

	/**
	 * Impression du rapport sur les suivis par site et par intervenant
     * @author Fran�ois Gu�rin
     * @param dateDebut String : date de d�but
     * @param dateFin String : date de fin
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportSuivisIntervenant(SiteIntervenantRapportVO rapportVO, String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));

            callableStatement.setLong(3,rapportVO.getSite());
            if(StringUtils.isEmpty(rapportVO.getIntervenant())){
            	callableStatement.setString(4,"%");
            }else{
            	callableStatement.setString(4,rapportVO.getIntervenant());
            }
            callableStatement.setLong(5,rapportVO.getActivite());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(6);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	public List<RapportVOCDX_00070> globalRAQCDX_00070(CardexAuthenticationSubject subject, final java.util.Date dateDebut, final java.util.Date dateFin, final long site) throws DAOException {
		CDX_00070RowCallbackHandler rcbh = new CDX_00070RowCallbackHandler(new ArrayList<RapportVOCDX_00070>());
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				//callableStatement.setTimestamp(1, new Timestamp(DateUtils.ajoutJour(dateDebut,-1000).getTime()));
				callableStatement.setTimestamp(1, new Timestamp(dateDebut.getTime()));
	            callableStatement.setTimestamp(2, new Timestamp(dateFin.getTime()));
	            callableStatement.setLong(3, site);
	            callableStatement.setLong(4, 1336);
	            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			}
		};

		storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_GABARIT_NARRATION", 5, 5, preparerCallableStatement);
		
		storeProcTemplate.query(rcbh);
		return rcbh.getRapportVOCDX_00070List();
	}
	
	public List<RapportVOCDX_00070> natureRAQCDX_00070(CardexAuthenticationSubject subject, final java.util.Date dateDebut, final java.util.Date dateFin, final long nature) throws DAOException {
		CDX_00070RowCallbackHandler rcbh = new CDX_00070RowCallbackHandler(new ArrayList<RapportVOCDX_00070>());
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){

			public void preparer(CallableStatement callableStatement) throws SQLException {
	            callableStatement.setTimestamp(1, new Timestamp(dateDebut.getTime()));
	            callableStatement.setTimestamp(2, new Timestamp(dateFin.getTime()));
	            callableStatement.setLong(3, nature);
	            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			}
		};
		storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAQ_NATURE", 4, 4, preparerCallableStatement);
		
		storeProcTemplate.query(rcbh);
		return rcbh.getRapportVOCDX_00070List();
	}
	
	public List<RapportVOCDX_00070> sansNatureRAQCDX_00070(CardexAuthenticationSubject subject, final java.util.Date dateDebut, final java.util.Date dateFin, final long nature) throws DAOException {
		CDX_00070RowCallbackHandler rcbh = new CDX_00070RowCallbackHandler(new ArrayList<RapportVOCDX_00070>());
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
	            callableStatement.setTimestamp(1, new Timestamp(dateDebut.getTime()));
	            callableStatement.setTimestamp(2, new Timestamp(dateFin.getTime()));
	            callableStatement.setLong(3, nature);
	            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			}
		};
		
		storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAQ_SANS_NATURE", 4, 4, preparerCallableStatement);
		
		storeProcTemplate.query(rcbh);
		return rcbh.getRapportVOCDX_00070List();
	}
	
	
	/**
	 * Lancer une store proc avec les param�tres de base du rapportVO
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public ResultSet rapportProcedure(CritereRapportVO rapportVO, String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        
	}
	
	/**
	 * Lancer une store proc pour les rapports de vigilance
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public ResultSet rapportProcedureVigilance(EntiteRapportVO entiteRapportVO, String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?,?); end;");
            callableStatement.setDate(1, new Date(entiteRapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(entiteRapportVO.getDateDebutAu().getTime()));
            callableStatement.setLong(3, entiteRapportVO.getSite());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        
	}

	/**
	 * Rapport Journal des enqu�teurs.
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportJournalEnquetes(EnqueteurJournalRapportVO_CDX_0053 enqueteurJournalRapportVO_CDX_0053,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_JOURNAL_ENQUETES (?,?,?,?); end;");
            callableStatement.setDate(1, new Date(enqueteurJournalRapportVO_CDX_0053.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(enqueteurJournalRapportVO_CDX_0053.getDateDebutAu().getTime()));
            callableStatement.setLong(3,enqueteurJournalRapportVO_CDX_0053.getNature());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
       
	}

	/**
	 * Rapport Coh�rence des donn�es.
	 * Ce rapport est appel� par un diff�r�. Il indique les donn�es qui ne sont pas coh�rentes. Les cas suivants sont v�tif�s :
	 * Dossiers d'auto-exclusion sans Sujet
	 * Dossiers d'auto-exclusion sans Inscription
	 * Dossiers d'auto-exclusion sans Date de fin
	 * Sujets orphelins (li�s � rien)
	 * Soci�t�s orphelines
	 * V�hicules orphelins 
	 * @throws DAOException 
	 */

	public ResultSet rapportCoherence(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_COHERENCE (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}
	
	/**
	 * Rapport V�rification des �l�ments multim�dias.
	 * Ce rapport est appel� par un diff�r�. Il indique les �l�ments multim�dias qui ont �t� associ�s, mais 
	 * qui ne sont pas pr�sents sur le serveur de fichiers.
	 * @throws DAOException 
	 * 
	 */

	public ResultSet rapportVerificationMultimedia(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_VERIFICATION_MULTIMEDIA (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}
	
	/**
	 * Rapport pour rechercher des sujets en acc�s interdit.
	 * � chaque date anniversaire de l'acc�s, un avis est envoy�
	 * pour �valuer la r�admission du client, exclu par le Comit�
	 * de vigilance. 
	 * @throws DAOException 
	 */

	public String rapportReadmission(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		String listeSujet = "";

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_VIGILANCE_READMISSION (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            
			while(resultSet.next()){
				listeSujet =  listeSujet + resultSet.getString("numeroFiche") + ", ";
			}
            
            return listeSujet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }

	}

	/**
	 * Rapport sur les donn�es � �purer.
	 * Ce rapport est appel� par le bouton �puration dans l'�cran de recherche. Le r�sultat est
	 * sauvegard� dans un rapport et dans un r�pertoire � des fins de contr�le pour les v�rificateurs.
	 * La proc�dure � ex�cuter est pass�e en param�tre.
	 * Dossiers d'auto-exclusion sans Sujet
	 * Dossiers d'auto-exclusion sans Inscription
	 * Dossiers d'auto-exclusion sans Date de fin
	 * Sujets orphelins (li�s � rien)
	 * Soci�t�s orphelines
	 * V�hicules orphelins 
	 */

	public ResultSet rapportEpuration(long site, Connection connection, String procedure)throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?); end;");
            callableStatement.setLong(1, site);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(2);
      
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}
	
    //Rapport cumulatif et hebdomadaire (CDX_0146 et CDX_0147)	
    public RapportDossier produireListeTypeCategorie(final CumulatifDossierRapportVO cumulatifDossierRapportVO) throws DAOException{
		final List<RapportDossier> liste = new ArrayList<RapportDossier>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
    	RapportDossier rapportDossierRetour = new RapportDossierVO();
    	
    	PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, cumulatifDossierRapportVO.getSite());
				callableStatement.setLong(2, cumulatifDossierRapportVO.getGenre());
				OracleDAOUtils.setDate(callableStatement, 3, cumulatifDossierRapportVO.getDateDebutDu());
				OracleDAOUtils.setDate(callableStatement, 4, cumulatifDossierRapportVO.getDateDebutAu());
				callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
			}
    	};
    	storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAP_CUMMULATIF_HEBDOMADAIRE", 5, 5, preparerCallableStatement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
		    	RapportDossier rapportDossier = new RapportDossierVO();
		    	rapportDossier.setCleCategorie(rs.getLong("CLE_CATEGORIE"));
				rapportDossier.setType(rs.getString("TYPE"));
				rapportDossier.setCategorie(rs.getString("CATEGORIE"));
				rapportDossier.setNombreDossier(rs.getLong("NOMBRE_DOSSIER"));
				liste.add(rapportDossier);
			}
    	};
    	
    	storeProcTemplate.query(rch);
    	rapportDossierRetour.setListDossier(liste );
    	
		return rapportDossierRetour;
    }

    //Rapport cumulatif et hebdomadaire pour Espacejeux (CDX_0143 et CDX_0144)
    public RapportDossier produireListeTypeCategorieEspacejeux(final CritereRapportVO rapportVO) throws DAOException{
		final List liste = new ArrayList();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
    	RapportDossier rapportDossierRetour = new RapportDossierVO();
		
    	PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				OracleDAOUtils.setDate(callableStatement, 1, rapportVO.getDateDebutDu());
				OracleDAOUtils.setDate(callableStatement, 2, rapportVO.getDateDebutAu());
				callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			}
    	};
    	storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAP_CUMMULATIF_HEBDO_EJ", 3, 3, preparerCallableStatement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
		    	RapportDossier rapportDossier = new RapportDossierVO();
		    	rapportDossier.setCleCategorie(rs.getLong("CLE_CATEGORIE"));
				rapportDossier.setType(rs.getString("TYPE"));
				rapportDossier.setCategorie(rs.getString("CATEGORIE"));
				rapportDossier.setNombreDossier(rs.getLong("NOMBRE_DOSSIER"));
				liste.add(rapportDossier);
			}
    	};
    	
    	storeProcTemplate.query(rch);
    	rapportDossierRetour.setListDossier(liste );
    	
		return rapportDossierRetour;
    }
    
    //Recherche des dossiers fond�s, non fond�s ou ind�termin�s selon les crit�res et le type et la cat�gorie
    public long produireFonde(final RapportDossier rapportDossier, final CumulatifDossierRapportVO cumulatifDossierRapportVO, final String critereFonde) throws DAOException{
    	StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
    	PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, cumulatifDossierRapportVO.getSite());
				callableStatement.setLong(2, rapportDossier.getCleCategorie());
				callableStatement.setLong(3, Integer.valueOf(critereFonde));
				OracleDAOUtils.setDate(callableStatement, 4, cumulatifDossierRapportVO.getDateDebutDu());
				OracleDAOUtils.setDate(callableStatement, 5, cumulatifDossierRapportVO.getDateDebutAu());
				callableStatement.registerOutParameter(6, OracleTypes.NUMBER);
			}
    	};
    	storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAP_NOMBRE_FONDE", 6, 6, preparerCallableStatement);
    	
    	TransCallStatementHandler callStatementHandler = new TransCallStatementHandler(new Long(0)){
			@Override
			public void process(CallableStatement callableStatement)
					throws SQLException {
				contenu = callableStatement.getLong(6);
			}

		};    	
    	
    	storeProcTemplate.query(callStatementHandler, true);
  	   
        return (Long) callStatementHandler.getContenu();
    }
    
    
	//Recherche des dossiers avec l'inscription "ENQ' dans le champ R�f�rence 3 (V_DO_REFERENCE_5) selon les crit�res et le type et la cat�gorie
    public long produireAuxEnquetes(final RapportDossier rapportDossier, final CumulatifDossierRapportVO cumulatifDossierRapportVO) throws DAOException{
    	StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
    	PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, cumulatifDossierRapportVO.getSite());
				callableStatement.setLong(2, rapportDossier.getCleCategorie());
				OracleDAOUtils.setDate(callableStatement, 3, cumulatifDossierRapportVO.getDateDebutDu());
				OracleDAOUtils.setDate(callableStatement, 4, cumulatifDossierRapportVO.getDateDebutAu());
				callableStatement.registerOutParameter(5, OracleTypes.NUMBER);
			}
    	};
    	storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAP_NOMBRE_ENQUETES", 5, 5, preparerCallableStatement);
    	
    	TransCallStatementHandler callStatementHandler = new TransCallStatementHandler(new Long(0)){
			@Override
			public void process(CallableStatement callableStatement)
					throws SQLException {
				contenu = callableStatement.getLong(5);
			}

		};    	
    	
    	storeProcTemplate.query(callStatementHandler, true);
  	   
        return (Long) callStatementHandler.getContenu();
    }
    
    //Pour le rapport CDX_0144, rapport hebdomadaire
    public long produireDossierCumul(final RapportDossier rapportDossier, final CumulatifDossierRapportVO cumulatifDossierRapportVO) throws DAOException{
    	StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
    	PreparerCallableStatement preparerCallableStatement = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, cumulatifDossierRapportVO.getSite());
				callableStatement.setLong(2, rapportDossier.getCleCategorie());
				OracleDAOUtils.setDate(callableStatement, 3, cumulatifDossierRapportVO.getDateDebutDu());
				OracleDAOUtils.setDate(callableStatement, 4, cumulatifDossierRapportVO.getDateDebutAu());
				callableStatement.registerOutParameter(5, OracleTypes.NUMBER);
			}
    	};
    	storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_RAP_NOMBRE_DOSSIER_ANNUEL", 5, 5, preparerCallableStatement);
    	
    	TransCallStatementHandler callStatementHandler = new TransCallStatementHandler(new Long(0)){
			@Override
			public void process(CallableStatement callableStatement)
					throws SQLException {
				contenu = callableStatement.getLong(5);
			}

		};    	
    	
    	storeProcTemplate.query(callStatementHandler, true);
  	   
        return (Long) callStatementHandler.getContenu();
    }

	/**
	 * Rapport sur la v�rification des livrets
	 * Ce rapport est appel� par un diff�r�. Il permet de v�rifer que les livrets saisis par les
	 * acheteurs myst�res (conformit� � la loi 84 de la part des d�taillants) ont bien �t� vendus par
	 * le d�taillant indiqu�. La v�rification se fait par la comparaison avec les donn�es du syst�me
	 * ALEX, via une une vue Oracle. Les discordances sont signal�es sur le rapport produit. 
	 * @throws DAOException 
	 */

	public ResultSet rapportVerificationLivrets(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_L_VERIFICATION_LIVRET (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}

	public Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeAvecEtSansDossierClientMystereCDX_0255(CardexAuthenticationSubject subject, Set<Dossier> echantillonDossierClientMystereActif) throws DAOException {
		final Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap = new HashMap<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255>();
		societeAvecDossierClientMystereCDX_0255(subject, echantillonDossierClientMystereActif, societeClientMystereMap);
		societeSansDossierClientMystereCDX_0255(subject, societeClientMystereMap);
		return societeClientMystereMap;
	}
	
    private void societeAvecDossierClientMystereCDX_0255(CardexAuthenticationSubject subject, Set<Dossier> echantillonDossierClientMystereActif, final Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
		};		
		storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_SOCIETE_CLIENT_MYSTERE", 1, 1, rch);
		storeProcTemplate.query(new SocieteAvecDossierClientMystereRowCallbackHandler(societeClientMystereMap, echantillonDossierClientMystereActif));
    }
    
    private void societeSansDossierClientMystereCDX_0255(CardexAuthenticationSubject subject, final Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
		};		
		storeProcTemplate.prepareCall("CARDEX_RAPPORT.SP_SOCIETE_SANS_VISITE", 1, 1, rch);
		storeProcTemplate.query(new SocieteSansDossierClientMystereRowCallbackHandler(societeClientMystereMap));
    }    
    
    /**
     * RA0009	Responsable RDD : peut-�tre une soci�t� RDD ou un sujet RDD qui est li� avec le r�le "Responsable"
     * @param subject
     * @param societeClientMystereMap
     * @throws DAOException
     */
	public void assignerSujetResponsableSociete(CardexAuthenticationSubject subject, final Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		PreparerCallableStatement pcs = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
		};	
		storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_SUJET_RDD_RESP_SOCIETE_RDD", 1, 1, pcs);
		
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ClientMystereVO_CDX_0255 cleClientMystereVO_CDX_0255 = new ClientMystereVO_CDX_0255(rs.getLong(1), rs.getLong(2));
				ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255 = societeClientMystereMap.get(cleClientMystereVO_CDX_0255);
				
				if (clientMystereVO_CDX_0255 != null && (clientMystereVO_CDX_0255.getResponsableSociete() == null
				|| clientMystereVO_CDX_0255.getResponsableSociete().getLiaisonResponsableDate().before( rs.getDate(5) ))){
					clientMystereVO_CDX_0255.setResponsableSociete( new ResponsableSocieteVO_CDX_0255() );
					clientMystereVO_CDX_0255.getResponsableSociete().setNom(rs.getString(3));
					clientMystereVO_CDX_0255.getResponsableSociete().setPrenom(rs.getString(4));
					clientMystereVO_CDX_0255.getResponsableSociete().setLiaisonResponsableDate(rs.getDate(5));
				}
			}
    	};		
		
		storeProcTemplate.query(rch);
	}
	
	/**
	 * RA0009	Responsable RDD : peut-�tre une soci�t� RDD ou un sujet RDD qui est li� avec le r�le "Responsable"
	 * @param subject
	 * @param societeClientMystereMap
	 * @throws DAOException
	 */
	public void assignerSocieteResponsableSociete(CardexAuthenticationSubject subject, final Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		PreparerCallableStatement pcs = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
		};	
		storeProcTemplate.prepareCall("Cardex_Lire_Lien.SP_SOCIETERDD_RESP_SOCIETERDD", 1, 1, pcs);
		
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ClientMystereVO_CDX_0255 cleClientMystereVO_CDX_0255 = new ClientMystereVO_CDX_0255(rs.getLong(1), rs.getLong(2));
				ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255 = societeClientMystereMap.get(cleClientMystereVO_CDX_0255);
				
				if (clientMystereVO_CDX_0255 != null && (clientMystereVO_CDX_0255.getResponsableSociete() == null
				 || clientMystereVO_CDX_0255.getResponsableSociete().getLiaisonResponsableDate().before( rs.getDate(8) ))){
					clientMystereVO_CDX_0255.setResponsableSociete( new ResponsableSocieteVO_CDX_0255() );
					clientMystereVO_CDX_0255.getResponsableSociete().setNom(rs.getString(3));
					clientMystereVO_CDX_0255.getResponsableSociete().setPrenom(rs.getString(4));
					clientMystereVO_CDX_0255.getResponsableSociete().setNomSociete(rs.getString(7));
					clientMystereVO_CDX_0255.getResponsableSociete().setLiaisonResponsableDate(rs.getDate(8));
				}
			}
    	};			
		
		storeProcTemplate.query(rch);
	}
	
    /**
	 * Rapport sur les changements apport�s aux d�taillants qui font partie d'une
	 * vague de clients myst�res (conformit� � la loi 84 de la part des d�taillants).
	 * Ce rapport est appel� par un diff�r�. Les points suivants sont v�rifi�s :
	 * - Le nom
	 * - L'adresse
	 * - Le code du compte
	 * - Le responsable 
	 * - Inactif 
     * @throws DAOException 
	 */

	public ResultSet rapportChangementsDetaillants(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_L_CHANGEMENTS_DETAILLANTS (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);

            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }

	}

	/**
	 * Proc�dure pour l'ex�cution des rapports CDX_0244 et CDX_0245 sur les s�v�rit� des sujets et des soci�t�s. 
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public ResultSet rapportSeverite(SeveriteRapportVO rapportVO, String procedure,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin " + procedure + " (?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.setLong(3, rapportVO.getSite());
            callableStatement.setLong(4, rapportVO.getSeverite());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(5);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        
	}
	
	/**
	 * Proc�dure pour l'ex�cution du diff�r� CDX00_00015 pour produire les
	 * fichiers pour le syst�me de reconnaissance de plaques 
	 * (identification automatique des v�hicules reli�s � des sujets d'int�r�t)
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public ResultSet produireRapportReconnaissance(String site, Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_RECONNAISSANCE_PLAQUES(?,?); end;");
            callableStatement.setLong(1, Long.valueOf(site));
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(2);
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}

	/**
	 * Proc�dure pour l'ex�cution du rapport CDX_0075 pour produire 
	 * manuellement	les donn�es pour le syst�me de reconnaissance de plaques 
	 * (identification automatique des v�hicules reli�s � des sujets d'int�r�t)
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public ResultSet produireRapportReconnaissance(String titre, StatistiqueDossierRapportVO reconnaissancePlaqueVO, Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_RECONNAISSANCE_PLAQUES(?,?,?,?); end;");
            callableStatement.setString(1, titre);
            callableStatement.setLong(2, reconnaissancePlaqueVO.getSite());
            callableStatement.setLong(3, reconnaissancePlaqueVO.getNature());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(4);
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}
	
	/**
	 * Proc�dure pour l'ex�cution du diff�r� sur les demandes incompl�tes pour les dossiers
	 * d'habilitation s�curitaire. Le diff�r� a pour but d'envoyer des rappels afin que les
	 * demandes d'enqu�tes soient compl�t�es.
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public ResultSet listeDemandesIncompletes(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_DEMANDES_INCOMPLETES(?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}

	/**
	 * Proc�dure pour l'ex�cution du rapport CDX_0246 pour conna�tre le d�lai
	 * moyen de traitement des enqu�tes d'habilitation s�curitaire.
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public List rapportDelaiTraitementEnquetes(DelaiTraitementEnqueteRapportVO_CDX_0246 delaiTraitementEnqueteRapportVO_CDX_0246 ) throws DAOException {
		Connection connection = DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_DELAI_TRAITEMENT(?,?,?,?); end;");
			callableStatement.setLong(1, delaiTraitementEnqueteRapportVO_CDX_0246.getActivite());
			OracleDAOUtils.setDate(callableStatement, 2, delaiTraitementEnqueteRapportVO_CDX_0246.getDateDebutDu());
			OracleDAOUtils.setDate(callableStatement, 3, delaiTraitementEnqueteRapportVO_CDX_0246.getDateDebutAu());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(4);
    		List liste = new ArrayList();
    	    try{
    		   while (resultSet.next()){
    				Map dossier = new HashMap();
    				dossier.put("Site",OracleDAOUtils.getString(resultSet, "Site"));
    				dossier.put("Nature",OracleDAOUtils.getString(resultSet, "Nature"));
    				dossier.put("Dossier",OracleDAOUtils.getString(resultSet, "Dossier"));
    				Date dateCompletee = new Date(resultSet.getTimestamp("Date_completee").getTime());
    				Date dateCreation = new Date(resultSet.getTimestamp("Date_creation").getTime());
    				int nombreJours = JourneeOuvrable.calculerDifference(dateCreation, dateCompletee);
    				dossier.put("Total", BigDecimal.valueOf(nombreJours));
    				liste.add(dossier);
    		    }

        	} catch (SQLException se) {
            	throw new DAOException(se);
    		} 
            return liste;
        }
        catch (SQLException se) {
        	se.printStackTrace();
        }

        finally {
        	if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
			}
        } //finally
        return null;
	}
	
	/**
	 * Proc�dure pour l'ex�cution du rapport CDX_0247 pour conna�tre 
	 * les enqu�tes d'habilitation s�curitaire en retard.
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public List rapportEnqueteRetard(CritereRapportVO rapportVO) throws DAOException {
		Connection connection = DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ENQUETES_RETARD(?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
    		List liste = new ArrayList();
    	    try{
    		   while (resultSet.next()){
    				Map dossier = new HashMap();
    				//Pour conna�tre les enqu�tes en retard, on calcule le nombre de jours ouvrables entre
    				//la date de cr�ation du suivi et sa date compl�t�e.
    				//On v�rifie d'abord la SQ (5 jours de d�lai) si la date compl�t�e de la RACJ est nulle.
    				Date dateCompletee = new Date(resultSet.getTimestamp("Date_completee_sq").getTime());
    				Date dateCreation = new Date(resultSet.getTimestamp("Date_creation_sq").getTime());
    				int nombreJours = 0;
    				if(resultSet.getTimestamp("Date_completee_racj") == null){
	    				nombreJours = JourneeOuvrable.calculerDifference(dateCreation, dateCompletee);
	    				if(nombreJours > GlobalConstants.Suivi.DELAI_SQ){
		    				if (nombreJours >= 50){
		    					dossier.put("RETARD", "> 50 jours");
		    				}else{
		    					dossier.put("RETARD", String.valueOf(nombreJours));
		    				}
	    					dossier.put("SUIVI",OracleDAOUtils.getString(resultSet, "Suivi_sq"));
	    					dossier.put("DATE_CREATION",resultSet.getTimestamp("Date_creation_sq"));
	    					dossier.put("DATE_PREVUE",resultSet.getTimestamp("Date_prevue_sq"));
	    					dossier.put("DOSSIER",OracleDAOUtils.getString(resultSet, "Dossier"));
	    					liste.add(dossier);
	    				}
    				}else{
	    				//On v�rifie ensuite la RACJ (d�lai d'une journ�e apr�s la date compl�t�e de la SQ)
	    				//si la date compl�t�e de la RACJ n'est pas nulle
	    				dateCompletee = new Date(resultSet.getTimestamp("Date_completee_racj").getTime());
	    				dateCreation = new Date(resultSet.getTimestamp("Date_completee_sq").getTime());
	    				nombreJours = JourneeOuvrable.calculerDifference(dateCreation, dateCompletee);
	    				if(nombreJours > GlobalConstants.Suivi.DELAI_RACJ){
	    					dossier = new HashMap();
		    				if (nombreJours >= 50){
		    					dossier.put("RETARD", "> 50 jours");
		    				}else{
		    					dossier.put("RETARD", String.valueOf(nombreJours));
		    				}
	    					dossier.put("SUIVI",OracleDAOUtils.getString(resultSet, "Suivi_racj"));
	    					dossier.put("DATE_CREATION",resultSet.getTimestamp("Date_creation_racj"));
	    					dossier.put("DATE_PREVUE",resultSet.getTimestamp("Date_prevue_racj"));
	    					dossier.put("DOSSIER",OracleDAOUtils.getString(resultSet, "Dossier"));
	    					liste.add(dossier);
	    				}
    				}
    		    }

        	} catch (SQLException se) {
            	throw new DAOException(se);
    		} 
            return liste;
        }
        catch (SQLException se) {
        	se.printStackTrace();
        }

        finally {
        	if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
			}
        } //finally
        return null;
	}

	/**
	 * Proc�dure pour l'ex�cution du rapport CDX_0248 pour conna�tre 
	 * les enqu�tes d'habilitation s�curitaire qui ont �t� trait�es en retard.
	 * @param rapportVO
	 * @param procedure
	 * @return
	 * @throws DAOException
	 */
	public List rapportEnqueteTraitementRetard(CritereRapportVO rapportVO) throws DAOException {
		Connection connection = DAOConnection.getInstance().getConnection();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_RETARD_TRAITEMENT(?,?,?); end;");
            callableStatement.setDate(1, new Date(rapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(rapportVO.getDateDebutAu().getTime()));
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(3);
    		List liste = new ArrayList();
    	    try{
    		   while (resultSet.next()){
    				Map dossier = new HashMap();
    				//Pour conna�tre les enqu�tes en retard, on calcule le nombre de jours ouvrables entre
    				//la date de cr�ation du suivi et sa date compl�t�e.
    				Date dateCompletee = new Date(resultSet.getTimestamp("Date_completee_sq").getTime());
    				Date dateCreation = new Date(resultSet.getTimestamp("Date_creation_sq").getTime());
	    			int nombreJours = JourneeOuvrable.calculerDifference(dateCreation, dateCompletee);
    				if(nombreJours > GlobalConstants.Suivi.DELAI_SQ){
	    				if (nombreJours >= 50){
	    					dossier.put("RETARD", "> 50 jours");
	    				}else{
	    					dossier.put("RETARD", String.valueOf(nombreJours));
	    				}
    					dossier.put("SUIVI",OracleDAOUtils.getString(resultSet, "Suivi_sq"));
    					dossier.put("DATE_CREATION",resultSet.getTimestamp("Date_creation_sq"));
    					dossier.put("DATE_COMPLETEE",resultSet.getTimestamp("Date_completee_sq"));
    					dossier.put("DATE_PREVUE",resultSet.getTimestamp("Date_prevue_sq"));
    					dossier.put("DOSSIER",OracleDAOUtils.getString(resultSet, "Dossier"));
    					liste.add(dossier);
    				}
    				//On v�rifie ensuite la RACJ (d�lai d'une journ�e apr�s la date compl�t�e de la SQ)
    				//si la date compl�t�e de la RACJ n'est pas nulle
    				dateCompletee = new Date(resultSet.getTimestamp("Date_completee_racj").getTime());
    				dateCreation = new Date(resultSet.getTimestamp("Date_completee_sq").getTime());
    				nombreJours = JourneeOuvrable.calculerDifference(dateCreation, dateCompletee);
    				if(nombreJours > GlobalConstants.Suivi.DELAI_RACJ){
    					dossier = new HashMap();
	    				if (nombreJours >= 50){
	    					dossier.put("RETARD", "> 50 jours");
	    				}else{
	    					dossier.put("RETARD", String.valueOf(nombreJours));
	    				}
    					dossier.put("SUIVI",OracleDAOUtils.getString(resultSet, "Suivi_racj"));
    					dossier.put("DATE_CREATION",resultSet.getTimestamp("Date_creation_racj"));
    					dossier.put("DATE_COMPLETEE",resultSet.getTimestamp("Date_completee_racj"));
    					dossier.put("DATE_PREVUE",resultSet.getTimestamp("Date_prevue_racj"));
    					dossier.put("DOSSIER",OracleDAOUtils.getString(resultSet, "Dossier"));
    					liste.add(dossier);
    				}
    		    }

        	} catch (SQLException se) {
            	throw new DAOException(se);
    		} 
            return liste;
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
        } //finally
	}

	/**
	 * Rapport sur les sujets devant faire partie du syst�me de laissez-passer dans les casinos.
	 */

	public ResultSet produireRapportLaissezPasserSujet(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_LAISSEZ_PASSER_SUJETS (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}
       
	/**
	 * Rapport sur les soci�t�s devant faire partie du syst�me de laissez-passer dans les casinos.
	 */

	public ResultSet produireRapportLaissezPasserSociete(Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_LAISSEZ_PASSER_SOCIETES (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
        	throw new DAOException(se);
        }
	}

    /**
     * Rapport sur les acc�s (sommaire et d�taill�)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAcces(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ACCES(?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(4);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport sur les acc�s aux sujets (CDX_0072)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesSujets(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_ACCES_SUJETS(?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            if(StringUtils.isEmpty(String.valueOf(rapportDossierVO.getSite()))){
                callableStatement.setString(3,"%"); //Pour avoir tous les sites
            }else{
                callableStatement.setString(3,String.valueOf(rapportDossierVO.getSite()));
            }
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.setLong(5,rapportDossierVO.getNombreAcces());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(6);
        }catch (SQLException se) {
            throw new DAOException(se);
        }

    }

    /**
     * Rapport pour produire les rapports sur l'analyse des acc�s aux dossiers (CDX_0072).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAnalyseAccesDossiers(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_ACCES_DOSSIERS(?,?,?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            if(StringUtils.isEmpty(String.valueOf(rapportDossierVO.getSite())) || rapportDossierVO.getSite() == 0){
                callableStatement.setString(3,"%"); //Pour avoir tous les sites
            }else{
                callableStatement.setString(3,String.valueOf(rapportDossierVO.getSite()));
            }
            if(StringUtils.isEmpty(rapportDossierVO.getIntervenant())){
                callableStatement.setString(4,"%"); //Pour avoir tous les intervenants
            }else{
                callableStatement.setString(4,rapportDossierVO.getIntervenant());
            }
            callableStatement.setLong(5,rapportDossierVO.getNombreAcces());
            if(StringUtils.isEmpty(rapportDossierVO.getGenre())){
                callableStatement.setString(6,"%"); //Pour avoir tous les genres
            }else{
                callableStatement.setString(6,rapportDossierVO.getGenre());
            }
            if(StringUtils.isEmpty(rapportDossierVO.getNature())){
                callableStatement.setString(7,"%"); //Pour avoir toutes les natures
            }else{
                callableStatement.setString(7,rapportDossierVO.getNature());
            }
            callableStatement.registerOutParameter(8, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(8);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport sur les acc�s aux sujets par intervenant (CDX_0072)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesSujetsIntervenant(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_ACCES_SU_INTERVENANT(?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            if(StringUtils.isEmpty(String.valueOf(rapportDossierVO.getSite()))){
                callableStatement.setString(3,"%"); //Pour avoir tous les sites
            }else{
                callableStatement.setString(3,String.valueOf(rapportDossierVO.getSite()));
            }
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.setLong(5,rapportDossierVO.getNombreAcces());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(6);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport sur les acc�s par intervenant (CDX_0122).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesIntervenant(AccesRapportVO rapportDossierVO, Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ACCES_INTERVENANT(?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(5);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport d�taill� sur les acc�s par intervenant (CDX_0123).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesDetailIntervenant(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_DETAIL_ACCES(?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(5);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport sur les acc�s par nouvel intervenant (CDX_0122).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesNouveauxIntervenants(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_ACCES_INTERVENANT_CREES(?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(5);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport sur les acc�s des superutilisateurs (CDX_0122).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesSuperutilisateurs(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_ACCES_SUPERUTILISATEURS(?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(5);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }
    /**
     * Rapport sur les acc�s aux sujets (CDX_0124).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesEmploye(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ACCES_SUJETS (?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.setLong(5,rapportDossierVO.getGroupe());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(6);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }
    
    /**
     * Rapport sur les acc�s aux fournisseurs (CDX_0125).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesFournisseur(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ACCES_FOURNISSEURS (?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.setLong(5,rapportDossierVO.getGroupe());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(6);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }

    /**
     * Rapport sur les acc�s aux narrations (CDX_0126).
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
     * @see AccesDAO#select(long, long)
     */
    public ResultSet auditAccesNarration(AccesRapportVO rapportDossierVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ACCES_NARRATIONS(?,?,?,?,?,?); end;");
            callableStatement.setTimestamp(1,new Timestamp(rapportDossierVO.getDateHeureDebutDu().getTime()));
            callableStatement.setTimestamp(2,new Timestamp(rapportDossierVO.getDateHeureDebutAu().getTime()));
            callableStatement.setLong(3,rapportDossierVO.getSite());
            callableStatement.setString(4,rapportDossierVO.getIntervenant());
            callableStatement.setLong(5,rapportDossierVO.getGroupe());
            callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
            callableStatement.execute();
            return (ResultSet)callableStatement.getObject(6);
        }catch (SQLException se) {
            throw new DAOException(se);
        }
    }
    /**
     * Proc�dure pour l'ex�cution du rapport CDX_0256 sur les d�taillants devenus inactifs. 
     * @param rapportVO
     * @param procedure
     * @return
     * @throws DAOException
     */
    public ResultSet rapportSocietesInactives(SocietesInactivesRapportVO societesInactivesRapportVO,Connection connection) throws DAOException {
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_SOCIETES_INACTIVES (?,?); end;");
            if(StringUtils.isEmpty(societesInactivesRapportVO.getVague())){
                callableStatement.setString(1, "%");
            }else{
                callableStatement.setString(1, societesInactivesRapportVO.getVague().toUpperCase());
            }
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(2);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        
    }

	/**
	 * Rapport sur les contrats d'autoexclusion actifs d'Espacejeux (CDX_0260)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportEspaceJeuxAutoexclusionActif(EspaceJeuxAutoexclusionActifRapportVO_CDX_0260 espaceJeuxAutoexclusionActifRapportVO_CDX_0260,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_EJ_AU_ACTIF (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Rapport sur les dossiers de fraude fond�s d'Espacejeux (CDX_0261)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportEspaceJeuxFraudeFonde(EspaceJeuxFraudeFondeRapportVO_CDX_0261 espaceJeuxFraudeFondeRapportVO_CDX_0261,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_EJ_FRAUDE_FONDE (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        
	}

	/**
	 * Rapport sur les dossiers de tricherie fond�s d'Espacejeux (CDX_0262)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportEspaceJeuxTricherieFonde(EspaceJeuxTricherieFondeRapportVO_CDX_0262 espaceJeuxTricherieFondeRapportVO_CDX_0262,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAP_EJ_TRICHERIE_FONDE (?); end;");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(1);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        
	}

	/**
	 * Rapport sur les statistiques par endroits regroup�s (CDX_0149)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportEndroitsRegroupes(StatistiqueDossierRapportVO statistiqueDossierRapportVO,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_RAPPORT_ENDROITS_REGROUPES (?,?,?,?,?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(statistiqueDossierRapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(statistiqueDossierRapportVO.getDateDebutAu().getTime()));
          	callableStatement.setString(3,String.valueOf(statistiqueDossierRapportVO.getSite()));
            callableStatement.setLong(4,statistiqueDossierRapportVO.getGenre());
            callableStatement.setLong(5,statistiqueDossierRapportVO.getNature());
            callableStatement.setLong(6,statistiqueDossierRapportVO.getType());
            callableStatement.setLong(7,statistiqueDossierRapportVO.getCategorie());
            callableStatement.setLong(8,statistiqueDossierRapportVO.getRegroupement());
            callableStatement.registerOutParameter(9, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(9);
            //RA0020-Statistiques par endroits regroup�es. Afficher les sous-cat�gories seulement si la cat�gorie est renseign�e dans l'�cran des crit�res de recherche.
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

	/**
	 * Tableau sur les statistiques par endroits regroup�s (CDX_0280)
     * @author Fran�ois Gu�rin
     * @param cle  long : cl� unique du dossier
     * @param site long : site
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return ValueListIterator : Une liste de dossiers retourn�s par la
     * recherche.
	 * @see AccesDAO#select(long, long)
	 */
	public ResultSet rapportTableauEndroitsRegroupes(StatistiqueDossierRapportVO statistiqueDossierRapportVO,Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_RAPPORT.SP_TABLEAU_ENDROITS_REGROUPES (?,?,?,?,?,?,?,?,?); end;");
            callableStatement.setDate(1, new Date(statistiqueDossierRapportVO.getDateDebutDu().getTime()));
            callableStatement.setDate(2, new Date(statistiqueDossierRapportVO.getDateDebutAu().getTime()));
          	callableStatement.setString(3,String.valueOf(statistiqueDossierRapportVO.getSite()));
            callableStatement.setLong(4,statistiqueDossierRapportVO.getGenre());
            callableStatement.setLong(5,statistiqueDossierRapportVO.getNature());
            callableStatement.setLong(6,statistiqueDossierRapportVO.getType());
            callableStatement.setLong(7,statistiqueDossierRapportVO.getCategorie());
            callableStatement.setLong(8,statistiqueDossierRapportVO.getRegroupement());
            callableStatement.registerOutParameter(9, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(9);
            return resultSet;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
	}

}
