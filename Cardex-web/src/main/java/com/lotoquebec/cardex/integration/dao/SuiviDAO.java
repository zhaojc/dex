package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SuiviVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SuiviCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SuiviCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Liste des appels à la base de données pour différents accès aux
 * narrations.  Implémente l'interface SuiviDAO.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/05/02 13:06:09 $
 * @see com.lotoquebec.cardex.integration.SuiviDAO
 */

public class SuiviDAO{

   private final Logger      log =
       (Logger)LoggerCardex.getLogger(SuiviDAO.class);

/**
 * Écriture d'un suivi, appelée par la méthode "insert", "update",
 * "approbation" ou "delete".
 * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
 * d'une mise à jour ("U"), d'une approbation et modification ("M") ou
 * d'une suppression ("D").
 * Procédure appelée : CARDEX_LIEN.SP_E_SV_SUIVI
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param suivi Suivi : suivi saisie à l'écran.
 * @param action  java.lang.String : U ou I
 * @param genreFichier String : code à deux lettres de la table qui lie le suivi
 * (Dossier (DO).
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Suivi
 */
   private Suivi editSuivi(CardexAuthenticationSubject subject, Suivi suivi,
        String action, String genreFichier) throws DAOException {
        Connection connection
            = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
      try {
          callableStatement =
              connection.prepareCall("begin CARDEX_LIEN.SP_E_SV_SUIVI "
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
                    + "end;");
              callableStatement.setString(1,action);
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,2, suivi.getCle());
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,3, suivi.getSite());
              OracleDAOUtils.setLong(callableStatement,4, suivi.getLien());
              OracleDAOUtils.setLong(callableStatement,5, suivi.getLienSite());
              callableStatement.setString(6,genreFichier);
              callableStatement.setString(7, suivi.getSuivi());

              OracleDAOUtils.setLong(callableStatement,8, suivi.getActivite());
              OracleDAOUtils.setLong(callableStatement,9, suivi.getStatut());
              callableStatement.setTimestamp(10,(Timestamp)(suivi.getDatePrevue()));
              callableStatement.setTimestamp(11,(Timestamp)(suivi.getDateCompletee()));
              OracleDAOUtils.setLong(callableStatement,12,suivi.getSecteurOrigine());
              callableStatement.setString(13, suivi.getDemandeur());
              callableStatement.setString(14, suivi.getIntervenant());
              OracleDAOUtils.setLong(callableStatement,15,suivi.getSecteurAssigne());
              callableStatement.setString(16, suivi.getCreateur());
              OracleDAOUtils.setLong(callableStatement, 17,suivi.getConfidentialiteSuivi());
              OracleDAOUtils.setLong(callableStatement, 18,suivi.getNiveauHierarchiqueSuivi());
              OracleDAOUtils.setLong(callableStatement, 19,suivi.getConfidentialiteCreateur());
              OracleDAOUtils.setLong(callableStatement, 20,suivi.getNiveauHierarchiqueCreateur());
              callableStatement.setTimestamp(21,(Timestamp)(suivi.getDateCreation()));
              callableStatement.setString(22,suivi.getModificateur());
              callableStatement.setTimestamp(23,(Timestamp)(suivi.getDateModification()));
              callableStatement.setString(24,suivi.getApprobateur());
              OracleDAOUtils.setLong(callableStatement,25,suivi.getConfidentialiteApprobateur());
              OracleDAOUtils.setLong(callableStatement,26,suivi.getNiveauHierarchiqueApprobateur());
              callableStatement.setTimestamp(27,(Timestamp)(suivi.getDateApprobation()));
              callableStatement.setString(28, suivi.getReference1());
              callableStatement.setString(29, suivi.getReference2());
              callableStatement.setString(30, suivi.getReference3());

              callableStatement.execute();
              Suivi newSuivi = new SuiviVO();
              newSuivi.setCle(callableStatement.getLong(2));
              newSuivi.setSite(callableStatement.getLong(3));
              return newSuivi;
      } catch (SQLException se) {
          throw new DAOException(se);
      } finally {
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
 * Appel de la méthode editSuivi pour la création d'un suivi
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param suivi Suivi : Suivi saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * suivi à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Suivi
 */
    public Suivi insert(CardexAuthenticationSubject subject, Suivi suivi,
            String genreFichier) throws DAOException {
      return editSuivi(subject, suivi, "I", genreFichier);
    }

/**
 * Appel de la méthode editSuivi pour la mise à jour d'un suivi
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param suivi Suivi : Suivi saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * suivi à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 */
    public void update(CardexAuthenticationSubject subject, Suivi suivi,
            String genreFichier) throws DAOException {
      editSuivi(subject, suivi, "U", genreFichier);
    }

/**
 * Appel de la méthode editSuivi pour l'approbation ou la modification d'un
 * suivi.
 * L'approbation consiste à bloquer toute modification à un suivi.
 * La modification consiste à permettre de nouveau les modifications à un suivi
 * approuvé.
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param suivi Suivi : Suivi saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * suivi à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Suivi
 */
    public Suivi approbation(CardexAuthenticationSubject subject, Suivi suivi,
            String genreFichier) throws DAOException {
      return editSuivi(subject, suivi, "M", genreFichier);
    }

/**
 * Appel de la méthode editSuivi pour la suppression d'un suivi
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param suivi Suivi : Suivi saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * suivi à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Suivi
 */
    public Suivi delete(CardexAuthenticationSubject subject, Suivi suivi,
            String genreFichier) throws DAOException {
      return editSuivi(subject, suivi, "D", genreFichier);
    }

/**
 * Lecture des suivis associés à une entité Dossier.
 * Procédure appelée : CARDEX_LIEN.SP_E_SV_SUIVI
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject  CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param cle long : clé de référence de l'entité
 * @param site long : site de référence de l'entité
 * @param genreFichier String : identification de l'entité.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Collection : liste des suivis associés
 */
    public Collection findLiensSuivi(CardexAuthenticationSubject subject,
            long cle, long site, String genreFichier) throws DAOException {
      log.fine("findLiensSuivi()");
      Connection connection
            = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

      try {
         callableStatement =
            connection.prepareCall("begin SPW_L_SV_SUIVI (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         ArrayList results = new ArrayList();
         while (resultSet.next()){
              SuiviVO linkedSuivi = new SuiviVO();
              linkedSuivi.setCle(resultSet.getLong("L_SV_CLE"));
              linkedSuivi.setSite(resultSet.getLong("L_SI_CLE"));
              linkedSuivi.setLien(resultSet.getLong("L_SV_REFERENCE"));
              linkedSuivi.setLienSite(resultSet.getLong("L_SV_REF_SITE"));
              linkedSuivi.setSuivi(resultSet.getString("V_SV_SUIVI"));
              linkedSuivi.setActivite(resultSet.getLong("I_TC_CLE"));
              linkedSuivi.setStatut(resultSet.getLong("I_ST_CLE"));
              linkedSuivi.setDatePrevue(resultSet.getTimestamp("D_SV_DATE_PREVUE"));
              linkedSuivi.setDateCompletee(resultSet.getTimestamp("D_SV_DATE_COMPLETEE"));
              linkedSuivi.setSecteurOrigine(resultSet.getLong("L_SV_PO_ORIGINE"));  // ?
              linkedSuivi.setDemandeur(resultSet.getString("V_SV_DEMANDEUR"));  // ?
              linkedSuivi.setIntervenant(resultSet.getString("V_SV_INTERVENANT"));// ?
              linkedSuivi.setSecteurAssigne(resultSet.getLong("L_SV_PO_ASSIGNE"));  // ?
              linkedSuivi.setCreateur(resultSet.getString("V_SV_CREE_PAR"));    // ?
              linkedSuivi.setConfidentialiteSuivi(resultSet.getLong("I_CC_SUIVI"));// ?
              linkedSuivi.setNiveauHierarchiqueSuivi(resultSet.getLong("I_NH_SUIVI"));// ?
              linkedSuivi.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));// ?
              linkedSuivi.setNiveauHierarchiqueCreateur(resultSet.getLong("I_NH_CREATEUR"));
              linkedSuivi.setDateCreation(resultSet.getTimestamp("D_SV_DATE_CREATION"));
              linkedSuivi.setModificateur(resultSet.getString("V_SV_MODIFIE_PAR"));    // ?
              linkedSuivi.setDateModification(resultSet.getTimestamp("D_SV_MODIFICATION"));
              linkedSuivi.setApprobateur(resultSet.getString("V_SV_APPROBATEUR"));    // ?
              linkedSuivi.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));// ?
              linkedSuivi.setNiveauHierarchiqueApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));// ?
              linkedSuivi.setDateApprobation(resultSet.getTimestamp("D_SV_APPROBATION"));
              linkedSuivi.setReference1(resultSet.getString("V_SV_REFERENCE_1"));
              linkedSuivi.setReference2(resultSet.getString("V_SV_REFERENCE_2"));
              linkedSuivi.setReference3(resultSet.getString("V_SV_REFERENCE_3"));
              results.add(linkedSuivi);
         }//while
         return results;
      }catch (SQLException se) {
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

/**
 * Routine pour traiter les ResultSet retournés par les recherches de suivi.
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param resultSet  ResultSet : Données retournées par une recherche.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ArrayList : liste des résultats traités.
 */

    public RowCallbackHandler constuireRowCallBackHandler(final List<Suivi> listDossier){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			 Suivi suivi = construireSuivi(resultSet);
                listDossier.add(suivi);
  		   }
  	   };
     }

  private ArrayList traitementResultSet(ResultSet resultSet)
        throws DAOException {
       ArrayList results = new ArrayList();
       try { //On limite le nombre d'enregistrements retournés à 3000.
            while (resultSet.next() && results.size() < GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SUIVI){
              SuiviVO suiviVo = construireSuivi(resultSet);
              results.add(suiviVo);
         }
         return results;
      } catch (SQLException se) {
          throw new DAOException(se);
      }
    }

private SuiviVO construireSuivi(ResultSet resultSet) throws SQLException {
	SuiviVO suiviVo = new SuiviVO();
	  DossierVO dossier = new DossierVO();
	  suiviVo.setCle(resultSet.getLong("L_SV_CLE"));
	  suiviVo.setSite(resultSet.getLong("L_SI_CLE"));
	  suiviVo.setSuivi(resultSet.getString("V_SV_SUIVI"));
	  suiviVo.setActivite(resultSet.getLong("I_TC_CLE"));
	  suiviVo.setStatut(resultSet.getLong("I_ST_CLE"));
	  suiviVo.setDatePrevue(resultSet.getTimestamp("D_SV_DATE_PREVUE"));
	  suiviVo.setDateCompletee(resultSet.getTimestamp("D_SV_DATE_COMPLETEE"));
	  suiviVo.setSecteurOrigine(resultSet.getLong("L_SV_PO_ORIGINE"));  // ?
	  suiviVo.setDemandeur(resultSet.getString("V_SV_DEMANDEUR"));  // ?
	  suiviVo.setIntervenant(resultSet.getString("V_SV_INTERVENANT"));// ?
	  suiviVo.setSecteurAssigne(resultSet.getLong("L_SV_PO_ASSIGNE"));  // ?
	  suiviVo.setCreateur(resultSet.getString("V_SV_CREE_PAR"));    // ?
	  suiviVo.setConfidentialiteSuivi(resultSet.getLong("I_CC_SUIVI"));// ?
	  suiviVo.setNiveauHierarchiqueSuivi(resultSet.getLong("I_NH_SUIVI"));// ?
	  suiviVo.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));// ?
	  suiviVo.setNiveauHierarchiqueCreateur(resultSet.getLong("I_NH_CREATEUR"));
	  suiviVo.setDateCreation(resultSet.getTimestamp("D_SV_DATE_CREATION"));
	  suiviVo.setModificateur(resultSet.getString("V_SV_MODIFIE_PAR"));    // ?
	  suiviVo.setDateModification(resultSet.getTimestamp("D_SV_MODIFICATION"));
	  suiviVo.setApprobateur(resultSet.getString("V_SV_APPROBATEUR"));    // ?
	  suiviVo.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));// ?
	  suiviVo.setNiveauHierarchiqueApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));// ?
	  suiviVo.setDateApprobation(resultSet.getTimestamp("D_SV_APPROBATION"));
	  suiviVo.setReference1(resultSet.getString("V_SV_REFERENCE_1"));
	  suiviVo.setReference2(resultSet.getString("V_SV_REFERENCE_2"));
	  suiviVo.setReference3(resultSet.getString("V_SV_REFERENCE_3"));
	  suiviVo.setEntite(resultSet.getLong("I_GE_ENTITE"));
	  //Inscription des valeurs sur les dossiers associés aux recherches de suivis
	  dossier.setCle(resultSet.getLong("L_DO_CLE"));
	  dossier.setEntite(resultSet.getLong("I_GE_ENTITE"));
	  dossier.setSite(resultSet.getLong("L_SI_CLE"));
	  dossier.setNumeroCardex(resultSet.getString("V_DO_NUMERO_DOSSIER"));
	  dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
	  dossier.setMotPasse(resultSet.getString("V_DO_MOT_PASSE"));
	  dossier.setGenre(resultSet.getLong("I_GE_CLE"));
	  dossier.setNature(resultSet.getLong("I_NA_CLE"));
	  dossier.setType(resultSet.getLong("I_TY_CLE"));
	  dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
	  dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));

	  suiviVo.setDossier(dossier);
	return suiviVo;
}

  /**
   * Routine pour traiter les ResultSet retournés par l'audit des changements.
   * Date de création : (2011-03-08)
   * @author Philippe Caron
   * @param resultSet  ResultSet : Données retournées par une recherche.
   * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
   * rupture de connexion avec la base de données, ou que la table demandée est
   * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
   * "stored procedure".
   * @return ArrayList : liste des résultats traités.
   */
    private ArrayList traitementResultSetAudit(ResultSet resultSet)
          throws DAOException {
        ArrayList results = new ArrayList();
        try {
            while (resultSet.next()){
                SuiviVO suiviVo = new SuiviVO();
                suiviVo.setCle(resultSet.getLong("L_SV_CLE"));
                suiviVo.setSite(resultSet.getLong("L_SI_CLE"));
                suiviVo.setSuivi(resultSet.getString("V_SV_SUIVI"));
                suiviVo.setActivite(resultSet.getLong("I_TC_CLE"));
                suiviVo.setStatut(resultSet.getLong("I_ST_CLE"));
                suiviVo.setDatePrevue(resultSet.getTimestamp("D_SV_DATE_PREVUE"));
                suiviVo.setDateCompletee(resultSet.getTimestamp("D_SV_DATE_COMPLETEE"));
                suiviVo.setSecteurOrigine(resultSet.getLong("L_SV_PO_ORIGINE"));  // ?
                suiviVo.setDemandeur(resultSet.getString("V_SV_DEMANDEUR"));  // ?
                suiviVo.setIntervenant(resultSet.getString("V_SV_INTERVENANT"));// ?
                suiviVo.setSecteurAssigne(resultSet.getLong("L_SV_PO_ASSIGNE"));  // ?
                suiviVo.setCreateur(resultSet.getString("V_SV_CREE_PAR"));    // ?
                suiviVo.setConfidentialiteSuivi(resultSet.getLong("I_CC_SUIVI"));// ?
                suiviVo.setNiveauHierarchiqueSuivi(resultSet.getLong("I_NH_SUIVI"));// ?
                suiviVo.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));// ?
                suiviVo.setNiveauHierarchiqueCreateur(resultSet.getLong("I_NH_CREATEUR"));
                suiviVo.setDateCreation(resultSet.getTimestamp("D_SV_DATE_CREATION"));
                suiviVo.setModificateur(resultSet.getString("V_SV_MODIFIE_PAR"));    // ?
                suiviVo.setDateModification(resultSet.getTimestamp("D_SV_MODIFICATION"));
                suiviVo.setApprobateur(resultSet.getString("V_SV_APPROBATEUR"));    // ?
                suiviVo.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));// ?
                suiviVo.setNiveauHierarchiqueApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));// ?
                suiviVo.setDateApprobation(resultSet.getTimestamp("D_SV_APPROBATION"));
                suiviVo.setReference1(resultSet.getString("V_SV_REFERENCE_1"));
                suiviVo.setReference2(resultSet.getString("V_SV_REFERENCE_2"));
                suiviVo.setReference3(resultSet.getString("V_SV_REFERENCE_3"));
                suiviVo.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
                suiviVo.setDateChangement(resultSet.getTimestamp("D_SV_DATE_CHANGEMENT"));
                results.add(suiviVo);
           }
           return results;
        } catch (SQLException se) {
            throw new DAOException(se);
        }
      }

/**
 * Recherche directe d'un suivi par sa clé unique.
 * Procédure appelée : SP_L2_SV_SUIVI
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject  CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param criteria Suivi : Suivi à rechercher.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Suivi : Données du dossier trouvé.
 */
    public Suivi find(CardexAuthenticationSubject subject, Suivi criteria)
            throws DAOException {
      Connection connection
            = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      SuiviVO suiviVo = new SuiviVO();
      CardexUser user = (CardexUser)subject.getUser();
      CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();

      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L_SV_SUIVI (?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         //Traitement du résultat retourné
         if (resultSet.next()) {
              suiviVo.setCle(resultSet.getLong("L_SV_CLE"));
              suiviVo.setSite(resultSet.getLong("L_SI_CLE"));
              suiviVo.setLien(resultSet.getLong("L_SV_REFERENCE"));
              suiviVo.setLienSite(resultSet.getLong("L_SV_REF_SITE"));
              suiviVo.setSuivi(resultSet.getString("V_SV_SUIVI"));
              suiviVo.setActivite(resultSet.getLong("I_TC_CLE"));
              suiviVo.setStatut(resultSet.getLong("I_ST_CLE"));
              suiviVo.setDatePrevue(resultSet.getTimestamp("D_SV_DATE_PREVUE"));
              suiviVo.setDateCompletee(resultSet.getTimestamp("D_SV_DATE_COMPLETEE"));
              suiviVo.setSecteurOrigine(resultSet.getLong("L_SV_PO_ORIGINE"));  // ?
              suiviVo.setDemandeur(resultSet.getString("V_SV_DEMANDEUR"));  // ?
              suiviVo.setIntervenant(resultSet.getString("V_SV_INTERVENANT"));// ?
              suiviVo.setSecteurAssigne(resultSet.getLong("L_SV_PO_ASSIGNE"));  // ?
              suiviVo.setCreateur(resultSet.getString("V_SV_CREE_PAR"));    // ?
              suiviVo.setConfidentialiteSuivi(resultSet.getLong("I_CC_SUIVI"));// ?
              suiviVo.setNiveauHierarchiqueSuivi(resultSet.getLong("I_NH_SUIVI"));// ?
              suiviVo.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));// ?
              suiviVo.setNiveauHierarchiqueCreateur(resultSet.getLong("I_NH_CREATEUR"));
              suiviVo.setDateCreation(resultSet.getTimestamp("D_SV_DATE_CREATION"));
              suiviVo.setModificateur(resultSet.getString("V_SV_MODIFIE_PAR"));    // ?
              suiviVo.setDateModification(resultSet.getTimestamp("D_SV_MODIFICATION"));
              suiviVo.setApprobateur(resultSet.getString("V_SV_APPROBATEUR"));    // ?
              suiviVo.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));// ?
              suiviVo.setNiveauHierarchiqueApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));// ?
              suiviVo.setDateApprobation(resultSet.getTimestamp("D_SV_APPROBATION"));
              suiviVo.setReference1(resultSet.getString("V_SV_REFERENCE_1"));
              suiviVo.setReference2(resultSet.getString("V_SV_REFERENCE_2"));
              suiviVo.setReference3(resultSet.getString("V_SV_REFERENCE_3"));
              suiviVo.setEntite(resultSet.getLong("I_EN_CLE"));
         }

         //suiviVo.setModifiable(false);
         //suiviVo.setPermettreComplete(false);
         //suiviVo.setApprouvable(false);

         //Un suivi peut être approuvé ou complété seulement si un intervenant a été désigné
         //et ne peut être modifié que par le demandeur ou par un intervenant du secteur assigné.

         // la table de suivi

         if ((user.getCode().equals(suiviVo.getDemandeur()) ||
         		(user.getSecteur() == suiviVo.getSecteurOrigine())) &&
         		suiviVo.getIntervenant() == null){
              suiviVo.setModifiable(true);
              suiviVo.setPermettreComplete(false);
              suiviVo.setApprouvable(false);
           }

         //Un suivi peut être modifié seulement par le demandeur ou par l'intervenant
         //et si seulement le suivi n'a pas été approuvé
         if ((user.getCode().equals(suiviVo.getDemandeur()) ||
                (user.getCode().equals(suiviVo.getIntervenant()))) &&
                (suiviVo.getDateApprobation() == null && suiviVo.getIntervenant() != null)
               ){
              suiviVo.setModifiable(true);
              suiviVo.setPermettreComplete(false);
           }

         //Un suivi ne peut être complétée que par l'intervenant assigné
         if ( user.getCode().equals(suiviVo.getIntervenant()) && suiviVo.getDateCompletee() == null) {
            suiviVo.setPermettreComplete(true);
         }

         // Un suivi peut être approuvé si le niveau d'autorité de l'utilisateur
         // est supérieur à celui du suivi et si le site est le même ou si
         // l'utilisateur est le demandeur du suivi ou si le niveau d'autorité,
         // le site et le secteur de l'utilisateur sont les mêmes que ceux du demandeur
         ListeCache listeCache = ListeCache.getInstance();
         boolean isHierachieSuiviAccessible = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject,GlobalConstants.TableValeur.NIVEAU_HIERARCHIQUE, GlobalConstants.ActionSecurite.SELECTION), String.valueOf(suiviVo.getNiveauHierarchiqueSuivi()));
         boolean isHierachieSuiviCreateurAccessible = listeCache.isValeurAccessible(subject, new TableValeurCleSQLListeCache(subject,GlobalConstants.TableValeur.NIVEAU_HIERARCHIQUE, GlobalConstants.ActionSecurite.SELECTION), String.valueOf(suiviVo.getNiveauHierarchiqueCreateur()));

         if (((privilege.getNiveauAuthorite() > suiviVo.getNiveauHierarchiqueSuivi() && isHierachieSuiviAccessible &&
             user.getSite() == suiviVo.getSite() && !user.getCode().equals(suiviVo.getIntervenant())) ||
             (user.getCode().equals(suiviVo.getDemandeur())) ||
             (privilege.getNiveauAuthorite() == suiviVo.getNiveauHierarchiqueCreateur() && isHierachieSuiviCreateurAccessible &&
              user.getSite() ==  suiviVo.getSite() &&
              user.getSecteur() == suiviVo.getSecteurOrigine() && !user.getCode().equals(suiviVo.getIntervenant()))) &&
			  (suiviVo.getIntervenant() != null &&
              suiviVo.getDateApprobation() == null && suiviVo.getDateCompletee() != null)) {
              suiviVo.setApprouvable(true);
         }

         if (log.isLoggable(Level.FINE)){
           log.fine("  user.code = '"+user.getCode()+"'");
           log.fine("  user.site = '"+user.getSite()+"'");
           log.fine("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
           log.fine("  suivi.demandeur = '"+suiviVo.getDemandeur()+"'");
           log.fine("  suivi.demandeur.niveauAuthorite = '"+suiviVo.getNiveauHierarchiqueCreateur()+"'");
           log.fine("  suivi.demandeur.secteur = '"+suiviVo.getSecteurOrigine()+"'");
           log.fine("  suivi.intervenant = '"+suiviVo.getIntervenant()+"'");
           log.fine("  suivi.niveauAuthorite = '"+suiviVo.getNiveauHierarchiqueSuivi()+"'");
           log.fine("  suivi.site = '"+suiviVo.getSite()+"'");
           log.fine("  suivi.approbateur = '"+suiviVo.getApprobateur()+"'");
           log.fine("Détermine si le suivi est modifiable");
           log.fine("  suivi.isModifiable = '"+suiviVo.isModifiable()+"'");
           log.fine("Détermine si le suivi est approuvable");
           log.fine("  suivi.isApprouvable = '"+suiviVo.isApprouvable()+"'");
           log.fine("Détermine si on peut compléter le suivi");
           log.fine("  suivi.isPermettreComplete = '"+suiviVo.isPermettreComplete()+"'");

         }



      }catch (SQLException se) {
          throw new DAOException(se);
      } catch (BusinessResourceException e) {
		throw new DAOException(e);
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
      return suiviVo;
    }

/**
 * Recherche de suivis à l'aide de critères de recherche.
 * Cette recherche sert principalement à chercher des suivis.
 * La requête SQL est générée dans le code Java, avant d'être envoyée à Oracle,
 * en fonction des critères de recherche.
 * Le resultSet retourné par les recherches est traité dans la routine traitementResultSet.
 * Procédure appelée : générée ici.
 * Date de création : (2002-02-19)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria CriteresRechercheSuivi : critères de recherche
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des dossiers retournés par la recherche.
 */

    public List<Suivi> select(CardexAuthenticationSubject subject, CriteresRechercheSuivi criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Suivi> suiviList = new ArrayList<Suivi>();
    	PreparerSQL preparerSQL = (new SuiviCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(suiviList));

    	return suiviList;
    }

    public Integer nombreDeSuiviRecherche(CardexAuthenticationSubject subject, CriteresRechercheSuivi criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new SuiviCountSQL()).construireSQL(subject, criteria);

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
    * Recherche de l'audit des changements d'un suivi.
    *
    * Procédure appelée : CARDEX_AUDIT.SP_L_AUDIT_SUIVI
    * Date de création : (2011-03-08)
    * @author François Guérin
    * @param subject  CardexAuthenticationSubject : Données nominatives sur
    * l'utilisateur.
    * @param criteria Sujet : Sujet à rechercher.
    * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
    * rupture de connexion avec la base de données, ou que la table demandée
    * est non disponible, ou qu'un problème est survenu lors de l'exécution
    * d'une "stored procedure".
    * @return Dossier : Instance de dossier associée.
    */
   public List audit(CardexAuthenticationSubject subject,Suivi criteria)
           throws DAOException {
        Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		List  resultats = new ArrayList();
         try {
       	connection = DAOConnection.getInstance().getConnection(subject);
           callableStatement = connection.prepareCall(
                   "begin CARDEX_AUDIT.SP_L_AUDIT_SUIVI(?,?,?); end;");
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

}