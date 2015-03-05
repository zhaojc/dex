package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Offre tout les services de récupération des informations d'une base de donnée
 * Oracle, relatives aux adresses de sujets ou de sociétés.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/16 20:57:31 $
 * @see com.lotoquebec.cardex.integration.AdresseDAO
 */
public class AdresseDAO {


    private final Logger log = (Logger)LoggerCardex.getLogger((AdresseDAO.class));

    /**
     * Effectue le travail relatif à une adresse, appelée par la méthode
     * "insert", "update", "approbation" ou "delete".
     * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
     * d'une mise à jour ("U"), d'une approbation et modification ("M") ou
     * d'une suppression ("D").
     * Procédure appelée : CARDEX_LIEN.SP_E_AD_ADRESSE
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param adresse Adresse : Adresse saisie à l'écran.
     * @param action  java.lang.String : "U" ou "I".
     * @param genreFichier String : Code à deux lettres de la table qui lie
     * l'adresse Sujet (SU) ou Societe (SO).
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Adresse Une adresse correspondant à celle éditée.
     */
    private Adresse editAdresse(CardexAuthenticationSubject subject,
            Adresse adresse, String action, String genreFichier)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_AD_ADRESSE "
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                    + "end;");
            callableStatement.setString(1,action);
            callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
            callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
            
            OracleDAOUtils.setLong(callableStatement,2, adresse.getCle());
            OracleDAOUtils.setLong(callableStatement,3, adresse.getSite());
                        
            OracleDAOUtils.setLong(callableStatement,4, adresse.getLien());
            callableStatement.setString(5, adresse.getAdresse());
            callableStatement.setString(6, adresse.getAdresse2());
            
            callableStatement.setString(7, adresse.getNumeroMunicipal());
            OracleDAOUtils.setLong(callableStatement,8, adresse.getTypeRue());
            callableStatement.setString(9, adresse.getNomRue());
            OracleDAOUtils.setLong(callableStatement,10, adresse.getPointCardinal());
            OracleDAOUtils.setLong(callableStatement,11, adresse.getUnite());
            callableStatement.setString(12, adresse.getNumeroUnite());
            callableStatement.setString(13, adresse.getAdressePostal());
            callableStatement.setString(14, adresse.getAdresseElectronique1());
            callableStatement.setString(15, adresse.getAdresseElectronique2());
            
            OracleDAOUtils.setLong(callableStatement,16, adresse.getPays());
            OracleDAOUtils.setLong(callableStatement,17, adresse.getProvince());
            OracleDAOUtils.setLong(callableStatement,18, adresse.getVille());
            if(StringUtils.isNotEmpty(adresse.getCodePostal())){
            	//On convertit le code postal en majuscules.
            	adresse.setCodePostal(adresse.getCodePostal().toUpperCase());
            }
            callableStatement.setString(19, adresse.getCodePostal());
            OracleDAOUtils.setLong(callableStatement, 20, adresse.getTypeUtilTelephone1());
            OracleDAOUtils.setLong(callableStatement, 21, adresse.getPeriodeTelephone1());
            callableStatement.setString(22, adresse.getTelephone1());
            OracleDAOUtils.setLong(callableStatement, 23, adresse.getTypeUtilTelephone2());
            OracleDAOUtils.setLong(callableStatement, 24, adresse.getPeriodeTelephone2());
            callableStatement.setString(25, adresse.getTelephone2());
            OracleDAOUtils.setLong(callableStatement, 26, adresse.getTypeUtilTelephone3());
            OracleDAOUtils.setLong(callableStatement, 27, adresse.getPeriodeTelephone3());
            callableStatement.setString(28, adresse.getTelephone3());
            
            OracleDAOUtils.setLong(callableStatement,29, adresse.getStatut());
            callableStatement.setString(30, adresse.getCommentaire());

            OracleDAOUtils.setLong(callableStatement,31, adresse.getLienSite());
            callableStatement.setString(32, genreFichier);
		    callableStatement.setString(33,OracleDAOUtils.convertirBooleanAString(adresse.isIndicateurRdd())); //B_AD_IND_RDD
            
            callableStatement.execute();
            Adresse newAdresse = new AdresseVO();
            newAdresse.setCle(callableStatement.getLong(2));
            newAdresse.setSite(callableStatement.getLong(3));
            return newAdresse;
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
     * Appel la méthode editAdresse pour la création d'une adresse.
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param adresse Adresse : Adresse saisie à l'écran.
     * @param genreFichier String : Code identifiant la table source qui lie une
     * adresse à un Sujet (SU) ou Societe (SO).
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Adresse Une adresse correspondant à celle insérée.
     */
    public Adresse insert(CardexAuthenticationSubject subject, Adresse adresse,
            String genreFichier) throws DAOException {
        return editAdresse(subject, adresse, "I", genreFichier);
    }

    /**
     * Appel de la méthode editAdresse pour la mise à jour d'une adresse.
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param adresse Adresse : Adresse saisie à l'écran.
     * @param genreFichier String : Code identifiant la table source qui lie une
     * adresse à un Sujet (SU) ou Societe (SO).
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject, Adresse adresse,
            String genreFichier) throws DAOException {
        editAdresse(subject, adresse, "U", genreFichier);
    }

    /**
     * Appel de la méthode editAdresse pour l'approbation ou la modification
     * d'une adresse.
     * L'approbation consiste à bloquer toute modification à une adresse.
     * La modification consiste à permettre de nouveau les modifications à une
     * adresse approuvée.
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param adresse Adresse : Adresse saisie à l'écran.
     * @param genreFichier String : Code identifiant la table source qui lie une
     * adresse à un Dossier.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Adresse Une adresse correspondant à celle modifiée.
     */
    public Adresse approbation(CardexAuthenticationSubject subject,
            Adresse adresse, String genreFichier) throws DAOException {
        return editAdresse(subject, adresse, "M", genreFichier);
    }

    /**
     * Appel de la méthode editAdresse pour la suppression d'une adresse.
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param adresse Adresse : Adresse saisie à l'écran.
     * @param genreFichier String : Code identifiant la table source qui lie une
     * adresse à un Sujet (SU) ou Societe (SO).
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void delete(CardexAuthenticationSubject subject, Adresse adresse,
            String genreFichier) throws DAOException {
        editAdresse(subject, adresse, "D", genreFichier);
    }

    /**
     * Retourne les adresses historiques associées à une entité Sujet ou Societe.
     * Procédure appelée : CARDEX_LIEN.SPW_L3_AD_ADRESSE
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence de l'entité
     * @param site long : Site de référence de l'entité
     * @param genreFichier String : Code identifiant la table source qui lie une
     * adresse à un Sujet (SU) ou Societe (SO).
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Collection : Liste des adresses associées.
     */
    public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject,
            long cle, long site, Timestamp dateLiaison, String genreFichier) throws DAOException {
        log.fine("findLiensAdresse()");
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIRE_LIEN.SP_L3_AD_ADRESSE (?,?,?,?,?); end;");
            callableStatement.setLong(1,cle);
            callableStatement.setLong(2,site);
            callableStatement.setString(3,genreFichier);
            if(dateLiaison == null){
            	callableStatement.setNull(4, java.sql.Types.TIMESTAMP);
            }else{
            	callableStatement.setTimestamp(4,dateLiaison);
            }
            callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(5);
            ArrayList results = new ArrayList();
            while (resultSet.next()) {
                AdresseVO linkedAdresse = obtenirAdresseVOde(resultSet);
                results.add(linkedAdresse);
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
     * Retourne les adresses associées à une entité Societe.
     * Procédure appelée : CARDEX_LIEN.SPW_L3_AD_ADRESSE
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence de l'entité
     * @param site long : Site de référence de l'entité
     * @param genreFichier String : Code identifiant la table source qui lie une
     * adresse à une Societe (SO).
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Collection : Liste des adresses associées.
     */
    public Collection findLiensAdresse(CardexAuthenticationSubject subject,
            long cle, long site, Timestamp dateLiaison, String genreFichier) throws DAOException {
        log.fine("findLiensAdresse()");
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIRE_LIEN.SP_L3_AD_ADRESSE (?,?,?,?); end;");
            callableStatement.setLong(1,cle);
            callableStatement.setLong(2,site);
            callableStatement.setString(3,genreFichier);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(4);
            ArrayList results = new ArrayList();
            while (resultSet.next()) {
                AdresseVO linkedAdresse = obtenirAdresseVOde(resultSet);
                //Avant d'ajouter l'adresse, on vérifie les points suivants :
                //Il s'agit d'une adresse de sujet
                //Il s'agit d'une adresse d'employé (site 11 ou 30)
                //Le rôle ad-hoc est accordé à l'utilisateur
                if (genreFichier == GlobalConstants.GenreFichier.SUJET){
                	if(linkedAdresse.getSite() == Long.valueOf(GlobalConstants.Sites.INVESTIGATION) || linkedAdresse.getSite() == Long.valueOf(GlobalConstants.Sites.INVESTIGATION_FACTUREE)){
                		if(GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_ADRESSE_EMPLOYE)){
                			//Rôle accordé, on ajoute l'adresse de l'employé.
                			results.add(linkedAdresse);
                		}
                	}else{//Pas un employé, on ajoute
                		results.add(linkedAdresse);
                	}
                }else{//Adresse de société, on ajoute
                	results.add(linkedAdresse);
                }
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
     * Retourne directement une adresse par sa clé unique.
     * Procédure appelée : SP_L2_AD_ADRESSE
     * Date de création : (2002-02-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param criteria Adresse : Adresse à rechercher.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Adresse : Adresse associée.
     */
    public Adresse find(CardexAuthenticationSubject subject, Adresse criteria)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		AdresseVO adresseVo = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIRE_LIEN.SP_L_AD_ADRESSE (?,?,?); end;");
            callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du résultat retourné
            if (resultSet.next()) {
            	adresseVo = obtenirAdresseVOde(resultSet);
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
        return adresseVo;
    }

	/**
	 * @param adresseVo
	 * @param resultSet
	 * @throws SQLException
	 */
	public AdresseVO obtenirAdresseVOde(ResultSet resultSet) throws SQLException {
		AdresseVO adresseVo = new AdresseVO();
		adresseVo.setCle(resultSet.getLong("L_AD_CLE"));
		adresseVo.setSite(resultSet.getLong("L_SI_CLE"));
		adresseVo.setLien(resultSet.getLong("L_AD_REFERENCE"));
		adresseVo.setAdresse(resultSet.getString("V_AD_ADRESSE"));
		adresseVo.setAdresse2(resultSet.getString("V_AD_ADRESSE2"));
		adresseVo.setNumeroMunicipal( resultSet.getString("V_AD_NUM_MUNICIPAL") );
		adresseVo.setTypeRue( resultSet.getLong("L_AD_TYPE_RUE") );
		adresseVo.setNomRue( resultSet.getString("V_AD_NOM_RUE") );
		adresseVo.setPointCardinal( resultSet.getLong("L_AD_POINT_CARDINAL") );
		adresseVo.setUnite( resultSet.getLong("L_AD_TYPE_UNITE") );
		adresseVo.setNumeroUnite( resultSet.getString("V_AD_NO_UNITE") );
		adresseVo.setAdressePostal( resultSet.getString("V_AD_ADRESSE_POST") );
		adresseVo.setAdresseElectronique1( resultSet.getString("V_AD_ELECTRONIQUE_1") );
		adresseVo.setAdresseElectronique2( resultSet.getString("V_AD_ELECTRONIQUE_2") );
		adresseVo.setPays(resultSet.getLong("I_PA_CLE"));
		adresseVo.setProvince(resultSet.getLong("L_PR_CLE"));
		adresseVo.setVille(resultSet.getLong("L_VI_CLE"));
		adresseVo.setCodePostal(resultSet.getString(
		        "V_AD_CODE_POSTAL"));
		adresseVo.setTypeUtilTelephone1( resultSet.getLong("I_TE_CLE_1") );
		adresseVo.setTelephone1(resultSet.getString(
		        "V_AD_TELEPHONE_1"));
		adresseVo.setTypeUtilTelephone2( resultSet.getLong("I_TE_CLE_2") );
		adresseVo.setTelephone2(resultSet.getString(
		        "V_AD_TELEPHONE_2"));
		adresseVo.setTypeUtilTelephone3( resultSet.getLong("I_TE_CLE_3") );
		adresseVo.setTelephone3(resultSet.getString(
		        "V_AD_TELEPHONE_3"));
		adresseVo.setStatut(resultSet.getLong("I_ST_CLE"));
		adresseVo.setCommentaire(resultSet.getString(
		        "V_AD_COMMENTAIRE"));
		adresseVo.setLienSite(resultSet.getLong("L_AD_REF_SITE"));
		adresseVo.setCreateur(resultSet.getString("V_AD_CREE_PAR"));
		adresseVo.setDateCreation(resultSet.getTimestamp("D_AD_DATE_CREATION"));
		adresseVo.setModificateur(resultSet.getString("V_AD_MODIFIE_PAR"));
		adresseVo.setDateModification(resultSet.getTimestamp("D_AD_DATE_MODIFICATION"));
		adresseVo.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_AD_IND_RDD")));
		adresseVo.setPeriodeTelephone1( resultSet.getLong("I_AD_TEL_PERIODE_1"));
		adresseVo.setPeriodeTelephone2( resultSet.getLong("I_AD_TEL_PERIODE_2"));
		adresseVo.setPeriodeTelephone3( resultSet.getLong("I_AD_TEL_PERIODE_3"));

		return adresseVo;
	}

	/**
	 * @param adresseVo
	 * @param resultSet
	 * @throws SQLException
	 */
	public AdresseVO obtenirAdresseVOSujet(ResultSet resultSet) throws SQLException {
		AdresseVO adresseVo = new AdresseVO();
		adresseVo.setCle(resultSet.getLong("L_AD_CLE"));
		adresseVo.setSite(resultSet.getLong("L_SI_CLE"));
		adresseVo.setLien(resultSet.getLong("L_AD_REFERENCE"));
		adresseVo.setAdresse(resultSet.getString("V_AD_ADRESSE"));
		adresseVo.setAdresse2(resultSet.getString("V_AD_ADRESSE2"));
		adresseVo.setNumeroMunicipal( resultSet.getString("V_AD_NUM_MUNICIPAL") );
		adresseVo.setTypeRue( resultSet.getLong("L_AD_TYPE_RUE") );
		adresseVo.setNomRue( resultSet.getString("V_AD_NOM_RUE") );
		adresseVo.setPointCardinal( resultSet.getLong("L_AD_POINT_CARDINAL") );
		adresseVo.setUnite( resultSet.getLong("L_AD_TYPE_UNITE") );
		adresseVo.setNumeroUnite( resultSet.getString("V_AD_NO_UNITE") );
		adresseVo.setAdressePostal( resultSet.getString("V_AD_ADRESSE_POST") );
		adresseVo.setAdresseElectronique1( resultSet.getString("V_AD_ELECTRONIQUE_1") );
		adresseVo.setAdresseElectronique2( resultSet.getString("V_AD_ELECTRONIQUE_2") );
		adresseVo.setPays(resultSet.getLong("I_PA_CLE"));
		adresseVo.setProvince(resultSet.getLong("L_PR_CLE"));
		adresseVo.setVille(resultSet.getLong("L_VI_CLE"));
		adresseVo.setCodePostal(resultSet.getString(
		        "V_AD_CODE_POSTAL"));
		adresseVo.setTypeUtilTelephone1( resultSet.getLong("I_TE_CLE_1") );
		adresseVo.setTelephone1(resultSet.getString(
		        "V_AD_TELEPHONE_1"));
		adresseVo.setTypeUtilTelephone2( resultSet.getLong("I_TE_CLE_2") );
		adresseVo.setTelephone2(resultSet.getString(
		        "V_AD_TELEPHONE_2"));
		adresseVo.setTypeUtilTelephone3( resultSet.getLong("I_TE_CLE_3") );
		adresseVo.setTelephone3(resultSet.getString(
		        "V_AD_TELEPHONE_3"));
		adresseVo.setStatut(resultSet.getLong("I_ST_CLE"));
		adresseVo.setCommentaire(resultSet.getString(
		        "V_AD_COMMENTAIRE"));
		adresseVo.setLienSite(resultSet.getLong("L_AD_REF_SITE"));
		adresseVo.setCreateur(resultSet.getString("V_AD_CREE_PAR"));
		adresseVo.setDateCreation(resultSet.getTimestamp("D_AD_DATE_CREATION"));
		adresseVo.setModificateur(resultSet.getString("V_AD_MODIFIE_PAR"));
		adresseVo.setDateModification(resultSet.getTimestamp("D_AD_DATE_MODIFICATION"));
		adresseVo.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_AD_IND_RDD")));
		adresseVo.setClasse(resultSet.getLong("I_CL_CLE"));
		return adresseVo;
	}

	/**
     * Recherche de l'audit des changements d'une adresse.
     * 
     * Procédure appelée : CARDEX_AUDIT.SP_L_AUDIT_ADRESSE
     * Date de création : (2011-03-08)
     * @author François Guérin
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param criteria Adresse : Adresse à rechercher.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associée.
     */
    public List audit(CardexAuthenticationSubject subject,Adresse criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		List  resultats = new ArrayList();
          try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_AUDIT.SP_L_AUDIT_ADRESSE(?,?,?); end;");
        	callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du résultat retourné
            resultats = traitementResultSetAudit(resultSet);

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
     * Routine pour traiter les ResultSet retournés par l'audit des changements.
     * Date de création : (2011-03-08)
     * @author François Guérin
     * @param resultSet  ResultSet : données retournées par une recherche
     * @throws SQLException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "stored procedure".
     * @return ArrayList : liste des sujets traités.
     */
      private ArrayList traitementResultSetAudit(ResultSet resultSet) throws DAOException {
          ArrayList results = new ArrayList();
          try { 
              while (resultSet.next()){
					AdresseVO adresseVo = new AdresseVO();
					adresseVo.setCle(resultSet.getLong("L_AD_CLE"));
					adresseVo.setSite(resultSet.getLong("L_SI_CLE"));
					adresseVo.setLien(resultSet.getLong("L_AD_REFERENCE"));
					adresseVo.setAdresse(OracleDAOUtils.getString(resultSet,"V_AD_ADRESSE"));
					adresseVo.setAdresse2(OracleDAOUtils.getString(resultSet,"V_AD_ADRESSE2"));
					adresseVo.setNumeroMunicipal(OracleDAOUtils.getString(resultSet,"V_AD_NUM_MUNICIPAL") );
					adresseVo.setTypeRue( resultSet.getLong("L_AD_TYPE_RUE") );
					adresseVo.setNomRue( resultSet.getString("V_AD_NOM_RUE") );
					adresseVo.setPointCardinal(resultSet.getLong("L_AD_POINT_CARDINAL") );
					adresseVo.setUnite( resultSet.getLong("L_AD_TYPE_UNITE") );
					adresseVo.setNumeroUnite(OracleDAOUtils.getString(resultSet,"V_AD_NO_UNITE") );
					adresseVo.setAdressePostal(OracleDAOUtils.getString(resultSet,"V_AD_ADRESSE_POST") );
					adresseVo.setAdresseElectronique1(OracleDAOUtils.getString(resultSet,"V_AD_ELECTRONIQUE_1") );
					adresseVo.setAdresseElectronique2(OracleDAOUtils.getString(resultSet,"V_AD_ELECTRONIQUE_2") );
					adresseVo.setPays(resultSet.getLong("I_PA_CLE"));
					adresseVo.setProvince(resultSet.getLong("L_PR_CLE"));
					adresseVo.setVille(resultSet.getLong("L_VI_CLE"));
					adresseVo.setCodePostal(OracleDAOUtils.getString(resultSet,"V_AD_CODE_POSTAL"));
					adresseVo.setTypeUtilTelephone1( resultSet.getLong("I_TE_CLE_1") );
					adresseVo.setTelephone1(OracleDAOUtils.getString(resultSet,"V_AD_TELEPHONE_1"));
					adresseVo.setTypeUtilTelephone2( resultSet.getLong("I_TE_CLE_2") );
					adresseVo.setTelephone2(OracleDAOUtils.getString(resultSet,"V_AD_TELEPHONE_2"));
					adresseVo.setTypeUtilTelephone3( resultSet.getLong("I_TE_CLE_3") );
					adresseVo.setTelephone3(OracleDAOUtils.getString(resultSet,"V_AD_TELEPHONE_3"));
					adresseVo.setStatut(resultSet.getLong("I_ST_CLE"));
					adresseVo.setCommentaire(OracleDAOUtils.getString(resultSet,"V_AD_COMMENTAIRE"));
					adresseVo.setLienSite(resultSet.getLong("L_AD_REF_SITE"));
					adresseVo.setCreateur(OracleDAOUtils.getString(resultSet,"V_AD_CREE_PAR"));
					adresseVo.setDateCreation(resultSet.getTimestamp("D_AD_DATE_CREATION"));
					adresseVo.setModificateur(OracleDAOUtils.getString(resultSet,"V_AD_MODIFIE_PAR"));
					adresseVo.setDateModification(resultSet.getTimestamp("D_AD_DATE_MODIFICATION"));
					adresseVo.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
					adresseVo.setDateChangement(resultSet.getTimestamp("D_AD_DATE_CHANGEMENT"));
					adresseVo.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_AD_IND_RDD")));
				    results.add(adresseVo);
		            }
		        }catch (SQLException se) {
			       throw new DAOException(se);
				}
					return results;
		  	}
 
      /**
       * Insertion d'une adresse du système RDD.
       * Cette procédure séparée de la méthode d'ajout standard (edit) est
       * nécessaire, car elle a lieu en différé avec un code associé au 
       * Casino de Montréal. Le site Loto-Québec est requis ici.
       * Procédure appelée : CARDEX_LIEN.SP_E_AD_ADRESSE_RDD
       * Date de création : (2012-11-16)
       * @author guerinf
       * @param subject CardexAuthenticationSubject : Données nominatives sur
       * l'utilisateur.
       * @param adresse Adresse : Adresse saisie à l'écran.
       * @param genreFichier String : Code à deux lettres de la table qui lie
       * l'adresse Sujet (SU) ou Societe (SO).
       * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
       * rupture de connexion avec la base de données, ou que la table demandée
       * est non disponible, ou qu'un problème est survenu lors de l'exécution
       * d'une "stored procedure".
       * @return Adresse Une adresse correspondant à celle éditée.
       */
      public Adresse insertAdresseRDD(CardexAuthenticationSubject subject,
              Adresse adresse, String genreFichier)
              throws DAOException {
          Connection connection =
          	DAOConnection.getInstance().getConnection(subject);
  		CallableStatement callableStatement = null;
          try {
              callableStatement = connection.prepareCall(
                      "begin CARDEX_LIEN.SP_E_AD_ADRESSE_RDD "
                      + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                      + "end;");
              callableStatement.setString(1,"I");
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              
              OracleDAOUtils.setLong(callableStatement,2, adresse.getCle());
              OracleDAOUtils.setLong(callableStatement,3, Long.valueOf(GlobalConstants.Sites.LOTO_QUEBEC));
                          
              OracleDAOUtils.setLong(callableStatement,4, adresse.getLien());
              callableStatement.setString(5, adresse.getAdresse());
              callableStatement.setString(6, adresse.getAdresse2());
              
              callableStatement.setString(7, adresse.getNumeroMunicipal());
              OracleDAOUtils.setLong(callableStatement,8, adresse.getTypeRue());
              callableStatement.setString(9, adresse.getNomRue());
              OracleDAOUtils.setLong(callableStatement,10, adresse.getPointCardinal());
              OracleDAOUtils.setLong(callableStatement,11, adresse.getUnite());
              callableStatement.setString(12, adresse.getNumeroUnite());
              callableStatement.setString(13, adresse.getAdressePostal());
              callableStatement.setString(14, adresse.getAdresseElectronique1());
              callableStatement.setString(15, adresse.getAdresseElectronique2());
              
              OracleDAOUtils.setLong(callableStatement,16, adresse.getPays());
              OracleDAOUtils.setLong(callableStatement,17, adresse.getProvince());
              OracleDAOUtils.setLong(callableStatement,18, adresse.getVille());
              if(StringUtils.isNotEmpty(adresse.getCodePostal())){
              	//On convertit le code postal en majuscules.
              	adresse.setCodePostal(adresse.getCodePostal().toUpperCase());
              }
              callableStatement.setString(19, adresse.getCodePostal());
              OracleDAOUtils.setLong(callableStatement, 20, adresse.getTypeUtilTelephone1());
              callableStatement.setString(21, adresse.getTelephone1());
              OracleDAOUtils.setLong(callableStatement, 22, adresse.getTypeUtilTelephone2());
              callableStatement.setString(23, adresse.getTelephone2());
              OracleDAOUtils.setLong(callableStatement, 24, adresse.getTypeUtilTelephone3());
              callableStatement.setString(25, adresse.getTelephone3());
              
              OracleDAOUtils.setLong(callableStatement,26, adresse.getStatut());
              callableStatement.setString(27, adresse.getCommentaire());

              OracleDAOUtils.setLong(callableStatement,28, adresse.getLienSite());
              callableStatement.setString(29, genreFichier);
  		    callableStatement.setString(30,OracleDAOUtils.convertirBooleanAString(adresse.isIndicateurRdd())); //B_AD_IND_RDD
              
              callableStatement.execute();
              Adresse newAdresse = new AdresseVO();
              newAdresse.setCle(callableStatement.getLong(2));
              newAdresse.setSite(callableStatement.getLong(3));
              return newAdresse;
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
       * Retourne les adresses associées à une entité Sujet.
       * Procédure appelée : CARDEX_LIEN.SPW_L4_AD_ADRESSE
       * Date de création : (2002-02-28)
       * @author Philippe Caron
       * @param subject  CardexAuthenticationSubject : Données nominatives sur
       * l'utilisateur.
       * @param cle long : Clé de référence de l'entité
       * @param site long : Site de référence de l'entité
       * @param genreFichier String : Code identifiant la table source qui lie une
       * adresse à un Sujet (SU).
       * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
       * rupture de connexion avec la base de données, ou que la table demandée
       * est non disponible, ou qu'un problème est survenu lors de l'exécution
       * d'une "stored procedure".
       * @return Collection : Liste des adresses associées.
       */
      public Collection findLiensAdresseSujet(CardexAuthenticationSubject subject,
              long cle, long site, Timestamp dateLiaison, String genreFichier) throws DAOException {
          log.fine("findLiensAdresseSujet()");
          Connection connection =
          	DAOConnection.getInstance().getConnection(subject);
  		CallableStatement callableStatement = null;
  		ResultSet resultSet = null;
          try {
              callableStatement = connection.prepareCall(
                      "begin CARDEX_LIRE_LIEN.SP_L4_AD_ADRESSE (?,?,?,?); end;");
              callableStatement.setLong(1,cle);
              callableStatement.setLong(2,site);
              callableStatement.setString(3,genreFichier);
              callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
              callableStatement.execute();
              resultSet = (ResultSet)callableStatement.getObject(4);
              ArrayList results = new ArrayList();
              long adresseLue = 0; //Comme la même adresse sera retournée autant de fois qu'il y a de sociétés reliées,
              					   //cette variable sert à évaluer si l'adresse a déjà été ajoutée.
              while (resultSet.next()) {
                  AdresseVO linkedAdresse = obtenirAdresseVOSujet(resultSet);
                  //Avant d'ajouter l'adresse, on vérifie les points suivants :
                  //Il s'agit d'une adresse de sujet
                  //Il s'agit d'une adresse d'employé (site 11 ou 30 et relié à une société avec la classe "LQ et ses filiales")
                  //Le rôle ad-hoc est accordé à l'utilisateur
                  if (genreFichier == GlobalConstants.GenreFichier.SUJET){
                  	if(linkedAdresse.getClasse() == GlobalConstants.Classes.EMPLOYE){
                  		if(GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_ADRESSE_EMPLOYE)){
                  			//Rôle accordé, on ajoute l'adresse de l'employé si ce n'est pas déjà fait.
                  			if(adresseLue != linkedAdresse.getCle()){
                  				results.add(linkedAdresse);
                  			}
                  		}
                  	}else{//Pas un employé, on ajoute
              			if(adresseLue != linkedAdresse.getCle()){
              				results.add(linkedAdresse);
              			}
                  	}
                  }else{//Adresse de société, on ajoute
                  	results.add(linkedAdresse);
                  }
                  adresseLue = linkedAdresse.getCle();
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

}