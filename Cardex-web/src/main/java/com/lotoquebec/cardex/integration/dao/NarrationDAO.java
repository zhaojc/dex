package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.NarrationApprobationCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.NarrationApprobationCountSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.NarrationRechercheCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.NarrationRechercheCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.Intervenant;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.SecteurDeIntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Liste des appels � la base de donn�es pour diff�rents acc�s aux
 * narrations.  Impl�mente l'interface NarrationDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.28 $, $Date: 2002/05/02 14:12:34 $
 * @see com.lotoquebec.cardex.integration.NarrationDAO
 */

public class NarrationDAO {

   private final Logger      log =
       (Logger)LoggerCardex.getLogger(NarrationDAO.class);

/**
 * �criture d'une narration, appel�e par la m�thode "insert", "update",
 * "approbation" ou "delete".
 * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
 * d'une mise � jour ("U"), d'une approbation et modification ("M") ou
 * d'une suppression ("D").
 * Proc�dure appel�e : CARDEX_WEB_NARRATION.SPW_E_CO_COMMENTAIRE2
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param narration Narration : narration saisie � l'�cran.
 * @param action  java.lang.String : U ou I
 * @param genreFichier String : code � deux lettres de la table qui lie la narration
 * (Dossier (DO), Sujet (SU), Soci�t� (SO), V�hicule (VE)).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Narration
 */
   private Narration editNarration(CardexAuthenticationSubject subject, Narration narration,String action, String genreFichier) throws DAOException {
	   Connection connection = DAOConnection.getInstance().getConnection(subject);
	  
	   try {
		   Narration narrationRetour = editNarration(narration, action, genreFichier, connection);
		   assignerCleNarrationTemporaire(connection, narrationRetour);
		   //connection.commit();
		   connection.setAutoCommit(true);
		   return narrationRetour;
	   } catch (SQLException e) {
		   e.printStackTrace();
		   throw new DAOException(e);
	   }finally {
	         if (connection != null) {
				try {
					if (!connection.getAutoCommit()) {
						connection.rollback();
					}
					connection.close();
				} catch (SQLException se) {
					throw new DAOException(se);
				}
			}
	   }
   }
   
   private Narration editNarration(Narration narration,String action, String genreFichier, Connection connection) throws DAOException {
      
	  CallableStatement callableStatement = null;
	  Statement stmt = null;
	  ResultSet rs = null;
	  try {
           callableStatement = connection.prepareCall("begin CARDEX_WEB_NARRATION.SPW_E_CO_COMMENTAIRE2 (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
              callableStatement.setString(1,action);
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,2, narration.getCle());
              OracleDAOUtils.setLong(callableStatement,3, narration.getSite());
              OracleDAOUtils.setLong(callableStatement,4, narration.getLien()); //l_co_reference
              if(OracleDAOUtils.isEmpty(narration.getTexte())){ //Une valeur nulle est interdite
              	 callableStatement.setString(5, " "); //v_co_commentaire (compl�ment d'information)
              }else{
              	 callableStatement.setString(5, narration.getTexte()); //v_co_commentaire (compl�ment d'information)
              }
              callableStatement.setDouble(6, narration.getMontant()); //r_co_montant
              OracleDAOUtils.setLong(callableStatement,7, narration.getLienSite());  //l_co_ref_site
              callableStatement.setString(8, genreFichier);  //c_co_ref_genre
              OracleDAOUtils.setLong(callableStatement,9,narration.getConfidentialiteNarration()); //i_cc_commentaire
              OracleDAOUtils.setLong(callableStatement,10,narration.getAutoriteNarration());  //i_nh_commentaire
              OracleDAOUtils.setLong(callableStatement,11,narration.getConfidentialiteCreateur()); //i_cc_createur
              OracleDAOUtils.setLong(callableStatement,12,narration.getAutoriteCreateur()); //i_nh_createur
              callableStatement.setString(13, narration.getApprobateur()); //v_co_approbateur
              OracleDAOUtils.setLong(callableStatement,14,narration.getConfidentialiteApprobateur()); //i_cc_approbateur
              OracleDAOUtils.setLong(callableStatement,15,narration.getAutoriteApprobateur()); //i_nh_approbateur
              callableStatement.setTimestamp(16,(Timestamp)(narration.getDateApprobation())); //d_co_approbation
              callableStatement.setString(17,narration.getReference());  //v_co_reference
              callableStatement.setString(18,narration.getRapporteur()); //v_co_rapporte_par
              callableStatement.setString(19,narration.getTempsConsacre());  //v_co_temps
              //callableStatement.setClob(20,(java.sql.Clob)CLOB.getEmptyCLOB());
              //callableStatement.setClob(21,(java.sql.Clob)CLOB.getEmptyCLOB());
              OracleDAOUtils.setLong(callableStatement,20,narration.getGabaritUtilise());  //L_CO_GABARIT
              callableStatement.execute();
              //Narration newNarration = new NarrationVO();
              //newNarration.setCle(callableStatement.getLong(2));
              //newNarration.setSite(callableStatement.getLong(3));
              narration.setCle(callableStatement.getLong(2));
              narration.setSite(callableStatement.getLong(3));
              callableStatement.close();
              //Traitement sp�cifique au CLOB (Insertion ou Mise � jour seulement)
			  log.fine("apr�s close");
              if (action.equals("IA") || action.equals("I") || action.equals("U")){
                  connection.setAutoCommit(false);
                  log.fine("Traitement des CLOBs");
                  stmt = connection.createStatement();
                  Clob clobNarrationAvecFormat = null;
                  Clob clobNarrationSansFormat = null;
                  rs = stmt.executeQuery("SELECT * FROM CO_COMMENTAIRE2  WHERE L_CO_CLE="
                                          + narration.getCle() + " AND L_SI_CLE="
                                          + narration.getSite() +" FOR UPDATE");
                  if (rs.next()) {
      /* L'autre syntaxe :
           clobNarrationAvecFormat = ((OracleResultSet)rs).getCLOB("CLOB_CO_TEXTE_FORMAT");
         ne fonctionne pas avec WebSphere en mode Connection Pool.
      */ 
                    clobNarrationAvecFormat = (Clob)rs.getClob("CLOB_CO_TEXTE_FORMAT");
                    clobNarrationSansFormat = (Clob)rs.getClob("CLOB_CO_TEXTE_NORMAL");
                    clobNarrationAvecFormat.setString(1, NarrationBaliseUtil.nettoyerNarration( narration.getNarrationAvecFormat() ));
                    clobNarrationSansFormat.setString(1, narration.getNarrationSansFormat());
                  }
                  //connection.commit();
                  connection.setAutoCommit(true);
              }
              return narration;
      } catch (SQLException se) {
          throw new DAOException(se);
      } finally {
         if (callableStatement != null){
            try{
              callableStatement.close();
            }catch (SQLException se) {
             throw new DAOException(se);
            }
         }
         if (stmt != null){
            try{
              stmt.close();
            }catch (SQLException se) {
              throw new DAOException(se);
            }
         }
         if (rs != null){
            try{
              rs.close();
            }catch (SQLException se) {
              throw new DAOException(se);
            }
         }
      }
    }


	
/**
 * Appel de la m�thode editNarration pour la cr�ation d'une narration
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param narration Narration : narration saisie � l'�cran.
 * @param genreFichier String : code identifiant la table source qui lie une narration
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Narration
 */
    public Narration insert(CardexAuthenticationSubject subject, Narration narration, String genreFichier) throws DAOException {
      return editNarration(subject,narration,"I", genreFichier);
    }
    
    public Narration insert(Narration narration, String genreFichier, Connection connection) throws DAOException {
        return editNarration(narration,"I", genreFichier, connection);
    }
/**
 * Les narrations du journal sont pr�approuve
 * @param subject
 * @param narration
 * @param genreFichier
 * @return
 * @throws DAOException
 */
    public Narration insertPreApprouve(CardexAuthenticationSubject subject, Narration narration, String genreFichier) throws DAOException {
    	return editNarration(subject, narration,"IA", genreFichier);
    }   
    
/**
 * Appel de la m�thode editNarration pour la mise � jour d'une narration
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param narration Narration : narration saisie � l'�cran
 * @param genreFichier String : code identifiant la table source qui lie une narration
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 */
    public void update(CardexAuthenticationSubject subject, Narration narration, String genreFichier) throws DAOException {
      editNarration(subject, narration,"U", genreFichier);
    }

/**
 * Appel de la m�thode editNarration pour l'approbation ou la modification d'une narration.
 * L'approbation consiste � bloquer toute modification � une narration.
 * La modification consiste � permettre de nouveau les modifications � une
 * narration approuv�e.
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param narration Narration : narration saisie � l'�cran
 * @param genreFichier String : code identifiant la table source qui lie une narration
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Narration
 */
    public Narration approbation(CardexAuthenticationSubject subject, Narration narration, String genreFichier) throws DAOException {
      return editNarration(subject,narration,"M", genreFichier);
    }

/**
 * Appel de la m�thode editNarration pour la suppression d'une narration
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param narration Narration : narration saisie � l'�cran
 * @param genreFichier String : code identifiant la table source qui lie une narration
 * (Dossier, Sujet, Soci�t�, V�hicule).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Narration
 */
    public Narration delete(CardexAuthenticationSubject subject, Narration narration, String genreFichier) throws DAOException {
      return editNarration(subject,narration,"D", genreFichier);
    }

/**
 * Lecture des narrations associ�es � une entit� (Dossier, Sujet, Soci�t�, V�hicule).
 * Les narrations sont affich�es de la plus r�cente � la plus ancienne.
 * La table des narrations contient des champs de type CLOB.
 * Proc�dure appel�e : CARDEX_WEB_NARRATION.SPW_L_CO_COMMENTAIRE2
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param cle long : cl� de r�f�rence de l'entit�
 * @param site long : site de r�f�rence de l'entit�
 * @param genreFichier String : identification de l'entit�.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Collection : liste des narrations associ�es
 */
    public Collection findLiensNarration(CardexAuthenticationSubject subject, long cle, long site, String genreFichier) throws DAOException {
      log.fine("findLiensNarration()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
      ListeCache listeCache = ListeCache.getInstance();
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_NARRATION.SPW_L_CO_COMMENTAIRE2 (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         ArrayList results = new ArrayList();
         
         while (resultSet.next()){
              NarrationVO linkedNarration = new NarrationVO();
              linkedNarration.setCle(resultSet.getLong("L_CO_CLE"));
              linkedNarration.setSite(resultSet.getLong("L_SI_CLE"));
              linkedNarration.setLien(resultSet.getLong("L_CO_REFERENCE"));
              linkedNarration.setLienSite(resultSet.getLong("L_CO_REF_SITE"));
              linkedNarration.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));
              linkedNarration.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));
              linkedNarration.setConfidentialiteNarration(resultSet.getLong("I_CC_COMMENTAIRE"));
              linkedNarration.setAutoriteNarration(resultSet.getLong("I_NH_COMMENTAIRE"));
              linkedNarration.setAutoriteApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));
              linkedNarration.setAutoriteCreateur(resultSet.getLong("I_NH_CREATEUR"));
              linkedNarration.setCreateur(resultSet.getString("V_CO_CREE_PAR"));
              linkedNarration.setDateCreation(resultSet.getTimestamp("D_CO_DATE_CREATION"));
              linkedNarration.setModificateur(resultSet.getString("V_CO_MODIFIE_PAR"));
              linkedNarration.setDateModification(resultSet.getTimestamp("D_CO_MODIFICATION"));
              linkedNarration.setApprobateur(resultSet.getString("V_CO_APPROBATEUR"));
              linkedNarration.setDateApprobation(resultSet.getTimestamp("D_CO_APPROBATION"));
              linkedNarration.setMontant(resultSet.getDouble("R_CO_MONTANT"));
              linkedNarration.setTempsConsacre(resultSet.getString("V_CO_TEMPS"));
              linkedNarration.setRapporteur(resultSet.getString("V_CO_RAPPORTE_PAR"));
              linkedNarration.setReference(resultSet.getString("V_CO_REFERENCE"));
              //Conversion en String des valeurs CLOB retourn�es de la base de donn�es.
              linkedNarration.setNarrationAvecFormat(OracleDAOUtils.CLOBToString((java.sql.Clob)resultSet.getObject("CLOB_CO_TEXTE_FORMAT")));
              linkedNarration.setNarrationSansFormat(OracleDAOUtils.CLOBToString((java.sql.Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
              log.fine("   [Narration id='"+linkedNarration.getCle()+"' Site='"+linkedNarration.getSite()+"']");
              String secteur = listeCache.obtenirLabel(subject, linkedNarration.getCreateur(), new SecteurDeIntervenantCle());
              linkedNarration.setSecteur(secteur);
              linkedNarration.setGabaritUtilise(resultSet.getLong("L_CO_GABARIT"));

	    	  results.add(linkedNarration);
         }
         return results;
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
   }

/**
 * Lecture des narrations associ�es � un dossier pour l'impression d'un rapport.
 * Les narrations sont affich�es par date de cr�ation croissante.
 * La table des narrations contient des champs de type CLOB.
 * Proc�dure appel�e : CARDEX_WEB_NARRATION.SPW_RAP_CO_COMMENTAIRE2
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param cle long : cl� de r�f�rence de l'entit�
 * @param site long : site de r�f�rence de l'entit�
 * @param genreFichier String : identification de l'entit�.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Collection : liste des narrations associ�es
 */
    public Collection findLiensNarrationRapport(CardexAuthenticationSubject subject, long cle, long site, String genreFichier) throws DAOException {
      log.fine("findLiensNarrationRapport()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
      ListeCache listeCache = ListeCache.getInstance();
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_NARRATION.SPW_RAP_CO_COMMENTAIRE2 (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         ArrayList results = new ArrayList();
         while (resultSet.next()){
              NarrationVO linkedNarration = new NarrationVO();
              linkedNarration.setCle(resultSet.getLong("L_CO_CLE"));
              linkedNarration.setSite(resultSet.getLong("L_SI_CLE"));
              linkedNarration.setLien(resultSet.getLong("L_CO_REFERENCE"));
              linkedNarration.setLienSite(resultSet.getLong("L_CO_REF_SITE"));
              linkedNarration.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));
              linkedNarration.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));
              linkedNarration.setConfidentialiteNarration(resultSet.getLong("I_CC_COMMENTAIRE"));
              linkedNarration.setAutoriteNarration(resultSet.getLong("I_NH_COMMENTAIRE"));
              linkedNarration.setAutoriteApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));
              linkedNarration.setAutoriteCreateur(resultSet.getLong("I_NH_CREATEUR"));
			  linkedNarration.setSecteur(resultSet.getString("DESCRIPTION"));
              linkedNarration.setCreateur(resultSet.getString("V_CO_CREE_PAR"));
              linkedNarration.setDateCreation(resultSet.getTimestamp("D_CO_DATE_CREATION"));
              linkedNarration.setModificateur(resultSet.getString("V_CO_MODIFIE_PAR"));
              linkedNarration.setDateModification(resultSet.getTimestamp("D_CO_MODIFICATION"));
              linkedNarration.setApprobateur(resultSet.getString("V_CO_APPROBATEUR"));
              linkedNarration.setDateApprobation(resultSet.getTimestamp("D_CO_APPROBATION"));
              linkedNarration.setMontant(resultSet.getDouble("R_CO_MONTANT"));
              linkedNarration.setTempsConsacre(resultSet.getString("V_CO_TEMPS"));
              linkedNarration.setRapporteur(resultSet.getString("V_CO_RAPPORTE_PAR"));
              linkedNarration.setReference(resultSet.getString("V_CO_REFERENCE"));
              //Conversion en String des valeurs CLOB retourn�es de la base de donn�es.
              linkedNarration.setNarrationAvecFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_FORMAT")));
              linkedNarration.setNarrationSansFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
              log.fine("   [Narration id='"+linkedNarration.getCle()+"' Site='"+linkedNarration.getSite()+"']");
              String secteur = listeCache.obtenirLabel(subject, linkedNarration.getCreateur(), new SecteurDeIntervenantCle());
              linkedNarration.setSecteur(secteur);

        	  results.add(linkedNarration);
         }//while
         return results;
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
   }

/**
 * Lecture des narrations associ�es � un dossier pour l'impression d'un rapport uniformis�.
 * Les narrations sont affich�es en fonction du contenu du champ V_CO_REFERENCE.
 * La table des narrations contient des champs de type CLOB.
 * Proc�dure appel�e : CARDEX_WEB_NARRATION.SPW_RAP_UNI_CO_COMMENTAIRE2
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param cle long : cl� de r�f�rence de l'entit�
 * @param site long : site de r�f�rence de l'entit�
 * @param genreFichier String : identification de l'entit�.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Collection : liste des narrations associ�es
 */
    public Collection findLiensNarrationRapportUniforme(CardexAuthenticationSubject subject, long cle, long site, String genreFichier, String section) throws DAOException {
      log.fine("findLiensNarrationRapportUniforme()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
      ListeCache listeCache = ListeCache.getInstance();
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_NARRATION.SPW_RAP_UNI_CO_COMMENTAIRE2 (?,?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.setString(4,section);
         callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(5);
         ArrayList results = new ArrayList();
         while (resultSet.next()){
              NarrationVO linkedNarration = new NarrationVO();
              linkedNarration.setCle(resultSet.getLong("L_CO_CLE"));
              linkedNarration.setSite(resultSet.getLong("L_SI_CLE"));
              linkedNarration.setLien(resultSet.getLong("L_CO_REFERENCE"));
              linkedNarration.setLienSite(resultSet.getLong("L_CO_REF_SITE"));
              linkedNarration.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));
              linkedNarration.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));
              linkedNarration.setConfidentialiteNarration(resultSet.getLong("I_CC_COMMENTAIRE"));
              linkedNarration.setAutoriteNarration(resultSet.getLong("I_NH_COMMENTAIRE"));
              linkedNarration.setAutoriteApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));
              linkedNarration.setAutoriteCreateur(resultSet.getLong("I_NH_CREATEUR"));
              linkedNarration.setCreateur(resultSet.getString("V_CO_CREE_PAR"));
              linkedNarration.setDateCreation(resultSet.getTimestamp("D_CO_DATE_CREATION"));
              linkedNarration.setModificateur(resultSet.getString("V_CO_MODIFIE_PAR"));
              linkedNarration.setDateModification(resultSet.getTimestamp("D_CO_MODIFICATION"));
              linkedNarration.setApprobateur(resultSet.getString("V_CO_APPROBATEUR"));
              linkedNarration.setDateApprobation(resultSet.getTimestamp("D_CO_APPROBATION"));
              linkedNarration.setMontant(resultSet.getDouble("R_CO_MONTANT"));
              linkedNarration.setTempsConsacre(resultSet.getString("V_CO_TEMPS"));
              linkedNarration.setRapporteur(resultSet.getString("V_CO_RAPPORTE_PAR"));
              linkedNarration.setReference(resultSet.getString("V_CO_REFERENCE"));
              //Conversion en String des valeurs CLOB retourn�es de la base de donn�es.
              linkedNarration.setNarrationAvecFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_FORMAT")));
              linkedNarration.setNarrationSansFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
              log.fine("   [Narration id='"+linkedNarration.getCle()+"' Site='"+linkedNarration.getSite()+"']");
              String secteur = listeCache.obtenirLabel(subject, linkedNarration.getCreateur(), new SecteurDeIntervenantCle());
              linkedNarration.setSecteur(secteur);

	    	  results.add(linkedNarration);
         }//while
         return results;
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
   }

/**
 * Recherche de narrations � l'aide de crit�res de recherche.
 * Cette recherche sert principalement � identifier les narrations approuv�es ou non.
 * La requ�te SQL est g�n�r�e dans le code Java, avant d'�tre envoy�e � Oracle,
 * en fonction des crit�res de recherche.
 * Le resultSet retourn� par les recherches est trait� dans la routine traitementResultSet.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRechercheNarration : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des dossiers retourn�s par la recherche.
 */
    public List<NarrationVO> selectApprobation(CardexAuthenticationSubject subject,CriteresRechercheNarration criteria) throws DAOException {
 	   JDBCTemplate template = new JDBCTemplate(subject);
 	   List<NarrationVO> listNarration = new ArrayList<NarrationVO>();
 	   PreparerSQL preparerSQL = (new NarrationApprobationCompletSQL()).construireSQL(subject, criteria);
 	   template.query(preparerSQL, criteria.getSequence(), constuireRechercheNarrationRowCallBackHandler(subject, listNarration));
 	   
       return listNarration;
    }

    public Integer nombreDeNarrationRechercheApprobation(CardexAuthenticationSubject subject,CriteresRechercheNarration criteria) throws DAOException {
 	   JDBCTemplate template = new JDBCTemplate(subject);
 	  PreparerSQL preparerSQL = (new NarrationApprobationCountSQL()).construireSQL(subject, criteria);
 	   
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
 * Recherche de narrations � l'aide de crit�res de recherche.
 * Cette recherche sert principalement � chercher des narrations � l'aide de mots-cl�s.
 * La requ�te SQL est g�n�r�e dans le code Java, avant d'�tre envoy�e � Oracle,
 * en fonction des crit�res de recherche.
 * Le resultSet retourn� par les recherches est trait� dans la routine traitementResultSet.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-19)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRechercheNarration : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des dossiers retourn�s par la recherche.
 */
   public List<NarrationVO> selectNarration(CardexAuthenticationSubject subject,CriteresRechercheNarration criteria) throws DAOException {
	   JDBCTemplate template = new JDBCTemplate(subject);
	   List<NarrationVO> listNarration = new ArrayList<NarrationVO>();
	   PreparerSQL preparerSQL = (new NarrationRechercheCompletSQL()).construireSQL(subject, criteria);
	   template.query(preparerSQL, criteria.getSequence(), constuireRechercheNarrationRowCallBackHandler(subject, listNarration));
	   
       return listNarration;
   }

   public Integer nombreDeNarrationRechercheNarration(CardexAuthenticationSubject subject,CriteresRechercheNarration criteria) throws DAOException {
	   JDBCTemplate template = new JDBCTemplate(subject);
	   PreparerSQL preparerSQL = (new NarrationRechercheCountSQL()).construireSQL(subject, criteria);
	   
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
 * Routine pour traiter les ResultSet retourn�s par les recherches de narration.
 * Date de cr�ation : (2002-02-20)
 * @author Fran�ois Gu�rin
 * @param resultSet  ResultSet : donn�es retourn�es par une recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ArrayList : liste des r�sultats trait�s.
 */
   public RowCallbackHandler constuireRechercheNarrationRowCallBackHandler(final CardexAuthenticationSubject subject, final List<NarrationVO> listNarration){
	   return new RowCallbackHandler(){
		   public void processRow(ResultSet resultSet) throws SQLException {
			   
				try {
					ListeCache listeCache = ListeCache.getInstance();
					DossierVO dossier = new DossierVO();
					NarrationVO narration = new NarrationVO();
					narration.setCle(resultSet.getLong("L_CO_CLE"));
					narration.setSite(resultSet.getLong("L_SI_CLE"));
					narration.setLien(resultSet.getLong("L_CO_REFERENCE"));
					narration.setCreateur(resultSet.getString("V_CO_CREE_PAR"));
					narration.setDateCreation(resultSet.getTimestamp("D_CO_DATE_CREATION"));
					narration.setModificateur(resultSet.getString("V_CO_MODIFIE_PAR"));
					narration.setDateModification(resultSet.getTimestamp("D_CO_MODIFICATION"));
					narration.setMontant(resultSet.getDouble("R_CO_MONTANT"));
					narration.setLienSite(resultSet.getLong("L_CO_REF_SITE"));
					narration.setConfidentialiteNarration(resultSet.getLong("I_CC_COMMENTAIRE"));
					narration.setAutoriteNarration(resultSet.getLong("I_NH_COMMENTAIRE"));
					narration.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));
					narration.setAutoriteCreateur(resultSet.getLong("I_NH_CREATEUR"));
					narration.setApprobateur(resultSet.getString("V_CO_APPROBATEUR"));
					narration.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));
					narration.setAutoriteApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));
					narration.setDateApprobation(resultSet.getTimestamp("D_CO_APPROBATION"));
					narration.setReference(resultSet.getString("V_CO_REFERENCE"));
					narration.setRapporteur(resultSet.getString("V_CO_RAPPORTE_PAR"));
					narration.setTempsConsacre(resultSet.getString("V_CO_TEMPS"));
					narration.setNarrationAvecFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_FORMAT")));
					narration.setNarrationSansFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
					//Inscription des valeurs sur les dossiers associ�s aux recherches de narration
					dossier.setCle(resultSet.getLong("L_DO_CLE"));
					dossier.setSite(resultSet.getLong("L_CO_REF_SITE"));
					dossier.setNumeroCardex(resultSet.getString("V_DO_NUMERO_DOSSIER"));
					dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
					dossier.setMotPasse(resultSet.getString("V_DO_MOT_PASSE"));
					dossier.setEntite(resultSet.getLong("I_GE_ENTITE"));
					dossier.setGenre(resultSet.getLong("I_GE_CLE"));
					dossier.setNature(resultSet.getLong("I_NA_CLE"));
					dossier.setType(resultSet.getLong("I_TY_CLE"));
					dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
					dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));					
					
					narration.setDossier(dossier);					
		            String secteur = listeCache.obtenirLabel(subject, narration.getCreateur(), new SecteurDeIntervenantCle());
		            narration.setSecteur(secteur);

		            listNarration.add(narration);
					
				} catch (BusinessResourceException e) {
					e.printStackTrace();
				}
			}
	   };
   }

/**
 * Recherche directe d'une narration par sa cl� unique.
 * Proc�dure appel�e : CARDEX_WEB_NARRATION.SPW_L2_CO_COMMENTAIRE2
 * Date de cr�ation : (2002-02-20)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Narration : narration � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Narration : donn�es du dossier trouv�.
 */
    public Narration find(CardexAuthenticationSubject subject, Narration criteria) throws DAOException {
      Connection connection = DAOConnection.getInstance().getConnection(subject);
      NarrationVO narration = new NarrationVO();
      Dossier dossier = new DossierVO();
      CardexUser user = (CardexUser)subject.getUser();
      CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
      boolean isInactif = false;
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  
      try {
         callableStatement = connection.prepareCall("begin CARDEX_WEB_NARRATION.SPW_L_CO_COMMENTAIRE2 (?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         String typeFichierAssocie = null;

         //Traitement du r�sultat retourn�
         if (resultSet.next()) {
              narration.setCle(resultSet.getLong("L_CO_CLE"));
              narration.setSite(resultSet.getLong("L_SI_CLE"));
              narration.setLien(resultSet.getLong("L_CO_REFERENCE"));
              narration.setCreateur(resultSet.getString("V_CO_CREE_PAR"));
              narration.setDateCreation(resultSet.getTimestamp("D_CO_DATE_CREATION"));
              narration.setModificateur(resultSet.getString("V_CO_MODIFIE_PAR"));
              narration.setDateModification(resultSet.getTimestamp("D_CO_MODIFICATION"));
              narration.setMontant(resultSet.getDouble("R_CO_MONTANT"));
              narration.setLienSite(resultSet.getLong("L_CO_REF_SITE"));
              narration.setConfidentialiteNarration(resultSet.getLong("I_CC_COMMENTAIRE"));
              narration.setAutoriteNarration(resultSet.getLong("I_NH_COMMENTAIRE"));
              narration.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));
              narration.setAutoriteCreateur(resultSet.getLong("I_NH_CREATEUR"));
              narration.setApprobateur(resultSet.getString("V_CO_APPROBATEUR"));
              narration.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));
              narration.setAutoriteApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));
              narration.setDateApprobation(resultSet.getTimestamp("D_CO_APPROBATION"));
              narration.setReference(resultSet.getString("V_CO_REFERENCE"));
              narration.setTexte(resultSet.getString("V_CO_COMMENTAIRE"));
              narration.setRapporteur(resultSet.getString("V_CO_RAPPORTE_PAR"));
              narration.setTempsConsacre(resultSet.getString("V_CO_TEMPS"));
              narration.setNarrationAvecFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_FORMAT")));
              narration.setNarrationSansFormat(OracleDAOUtils.CLOBToString((Clob)resultSet.getObject("CLOB_CO_TEXTE_NORMAL")));
              narration.setGabaritUtilise(resultSet.getLong("L_CO_GABARIT"));
              dossier.setCle(narration.getLien());
              dossier.setSite(narration.getLienSite());

              typeFichierAssocie = resultSet.getString("C_CO_REF_GENRE");
              if (typeFichierAssocie != null) {
                typeFichierAssocie = typeFichierAssocie.trim();
                narration.setGenreLiaison(typeFichierAssocie);
              }
              log.fine("C_CO_REF_GENRE is '"+typeFichierAssocie+"'");
              if (GlobalConstants.GenreFichier.DOSSIER.equals(typeFichierAssocie)) {
                //R�cup�ration du dossier associ� � la narration
                dossier = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject,dossier);
                narration.setDossier(dossier);
                isInactif = Long.toString(dossier.getStatut()).equals(GlobalConstants.Statut.DOSSIER_INACTIF);
              }else if (GlobalConstants.GenreFichier.SUJET.equals(typeFichierAssocie)) {
                //R�cup�ration du sujet associ� � la narration
                //SujetDAO sujetDAO = OracleDAOFactory.getInstance().getSujetDAO();
                //sujet = sujetDAO.find(subject,dossier);
                //narration.setSujet(sujet);
              }else if (GlobalConstants.GenreFichier.VEHICULE.equals(typeFichierAssocie)) {
                //R�cup�ration du vehicule associ� � la narration
                //VehiculeDAO vehiculeDAO = OracleDAOFactory.getInstance().getVehiculeDAO();
                //vehicule = vehiculeDAO.find(subject,dossier);
                //narration.setVehicule(vehicule);
              }else if (GlobalConstants.GenreFichier.SOCIETE.equals(typeFichierAssocie)) {
                //R�cup�ration du societe associ� � la narration
                //SocieteDAO societeDAO = OracleDAOFactory.getInstance().getSocieteDAO();
                //societe = societeDAO.find(subject,dossier);
                //narration.setSociete(societe);
              }

         }

         // D�termine si la narration est modifiable
         //  - Code cr�ateur �gal � code de la narration
         //  - Les sites sont les m�me
         //� - Narration pas approuv�
         //  - Dossier associ� actif
         boolean isMemeIntervenant = isMemeIntervenant(subject, narration.getCreateur());
         
         if (user.getCode().equals(narration.getCreateur()) &&
                 user.getSite() == narration.getSite() &&
                 narration.getDateApprobation() == null &&
                 !isInactif
                ){
                narration.setModifiable(true);
             }
             
         // D�termine si la narration est approuvable
         //  - Niveau d'authorite de l'utilisateur sup�rieur ou �gal � celui de la narration ou � celui du cr�ateur
         //  - Les sites sont les m�me
         //  - Un m�me intervenant n'a pas le droit de s'approuver lui-m�me.
         //  - La narration ne doit pas �tre approuv�
         if (user.getSite() == narration.getSite() &&
        	isMemeIntervenant == false &&
        	privilege.getNiveauAuthorite() >= narration.getAutoriteNarration()  &&
            narration.getDateApprobation() == null
            ){
            narration.setApprouvable(true);
         }

         // D�termine si on peut permettre la modification d'une narration
         //  - Niveau d'authorite de l'utilisateur sup�rieur ou �gal � celui de la narration
         //  - Les sites sont les m�me
         //  - La narration ne doit pas �tre approuv�
         if (user.getSite() == narration.getSite() &&
        	 privilege.getNiveauAuthorite() >= narration.getAutoriteNarration()  &&
             narration.getDateApprobation() != null &&
             !isInactif
            ){
            narration.setPermettreModification(true);
         }

         if (log.isLoggable(Level.FINE)){
           log.fine("D�termine si la narration est modifiable");
           log.fine("  user.code = '"+user.getCode()+"'");
           log.fine("  narration.createur = '"+narration.getCreateur()+"'");
           log.fine("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
           log.fine("  narration.niveauAuthorite = '"+narration.getAutoriteNarration()+"'");
           log.fine("  user.site = '"+user.getSite()+"'");
           log.fine("  narration.site = '"+narration.getSite()+"'");
           log.fine("  narration.dateApprobation = '"+narration.getDateApprobation()+"'");
           if (GlobalConstants.GenreFichier.DOSSIER.equals(typeFichierAssocie)) {
              log.fine("  dossier.statut = '"+isInactif+"'");
           }
           log.fine("  narration.isModifiable = '"+narration.isModifiable()+"'");
           log.fine("D�termine si la narration est approuvable");
           log.fine("  user.site = '"+user.getSite()+"'");
           log.fine("  narration.site = '"+narration.getSite()+"'");
           log.fine("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
           log.fine("  narration.niveauAuthorite = '"+narration.getAutoriteNarration()+"'");
           log.fine("  createur.niveauAuthorite = '"+narration.getAutoriteCreateur()+"'");
           log.fine("  narration.isApprouvable = '"+narration.isApprouvable()+"'");
           log.fine("D�termine si on peut permettre la modification d'une narration");
           log.fine("  user.site = '"+user.getSite()+"'");
           log.fine("  narration.site = '"+narration.getSite()+"'");
           log.fine("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
           log.fine("  narration.niveauAuthorite = '"+narration.getAutoriteNarration()+"'");
           log.fine("  narration.isPermettreModification = '"+narration.isPermettreModification()+"'");
           if (GlobalConstants.GenreFichier.DOSSIER.equals(typeFichierAssocie)) {
              log.fine("  dossier.statut = '"+isInactif+"'");
           }

         }


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
      return narration;
    }

    /**
     * Un m�me intervenant n'a pas le droit de s'approuver lui-m�me.
     * @param subject
     * @param code
     * @param createur
     * @return
     * @throws DAOException 
     */
    private boolean isMemeIntervenant(CardexAuthenticationSubject subject, String createurNarration) throws DAOException {
    	CardexUser user = (CardexUser)subject.getUser();
    	
    	if (user.getCode().equals(createurNarration))
    		return true;
    	
    	Intervenant createurIntervenantNarration = (new IntervenantDAO()).findIntervenant(createurNarration);
    	
    	return user.getCodeParent().equals( createurIntervenantNarration.getCodeParent() );
    }

    /**
     * Recherche de l'audit des changements d'un sujet.
     * 
     * Proc�dure appel�e : CARDEX_AUDIT.SP_L_AUDIT_NARRATION
     * Date de cr�ation : (2011-03-08)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Sujet : Sujet � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public List audit(CardexAuthenticationSubject subject, Narration criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		List  resultats = new ArrayList();
          try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_AUDIT.SP_L_AUDIT_NARRATION(?,?,?); end;");
        	callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du r�sultat retourn�
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
     * Routine pour traiter les ResultSet retourn�s par les recherches de narration.
     * Date de cr�ation : (2002-02-20)
     * @author Fran�ois Gu�rin
     * @param resultSet  ResultSet : donn�es retourn�es par une recherche
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     * @return ArrayList : liste des r�sultats trait�s.
     */
      private ArrayList traitementResultSetAudit(ResultSet resultSet) throws DAOException {
          ArrayList results = new ArrayList();
          try { 
              while (resultSet.next()){
                  NarrationVO narration = new NarrationVO();
                  narration.setCle(resultSet.getLong("L_CO_CLE"));
                  narration.setSite(resultSet.getLong("L_SI_CLE"));
                  narration.setEntite(resultSet.getLong("I_EN_CLE"));
                  narration.setLien(resultSet.getLong("L_CO_REFERENCE"));
                  narration.setCreateur(OracleDAOUtils.getString(resultSet,"V_CO_CREE_PAR"));
                  narration.setDateCreation(resultSet.getTimestamp("D_CO_DATE_CREATION"));
                  narration.setModificateur(OracleDAOUtils.getString(resultSet,"V_CO_MODIFIE_PAR"));
                  narration.setDateModification(resultSet.getTimestamp("D_CO_MODIFICATION"));
                  narration.setMontant(resultSet.getDouble("R_CO_MONTANT"));
                  narration.setLienSite(resultSet.getLong("L_CO_REF_SITE"));
                  narration.setConfidentialiteNarration(resultSet.getLong("I_CC_COMMENTAIRE"));
                  narration.setAutoriteNarration(resultSet.getLong("I_NH_COMMENTAIRE"));
                  narration.setConfidentialiteCreateur(resultSet.getLong("I_CC_CREATEUR"));
                  narration.setAutoriteCreateur(resultSet.getLong("I_NH_CREATEUR"));
                  narration.setApprobateur(OracleDAOUtils.getString(resultSet,"V_CO_APPROBATEUR"));
                  narration.setConfidentialiteApprobateur(resultSet.getLong("I_CC_APPROBATEUR"));
                  narration.setAutoriteApprobateur(resultSet.getLong("I_NH_APPROBATEUR"));
                  narration.setDateApprobation(resultSet.getTimestamp("D_CO_APPROBATION"));
                  narration.setReference(OracleDAOUtils.getString(resultSet,"V_CO_REFERENCE"));
                  narration.setRapporteur(OracleDAOUtils.getString(resultSet,"V_CO_RAPPORTE_PAR"));
                  narration.setTempsConsacre(OracleDAOUtils.getString(resultSet,"V_CO_TEMPS"));
                  narration.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
                  narration.setDateChangement(resultSet.getTimestamp("D_CO_DATE_CHANGEMENT"));
    			  results.add(narration);
             }
             return results;
          } catch (SQLException se) {
              throw new DAOException(se);
          }
      }

      /**
		  procedure SPW_I_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE               IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
		                                           NEW_L_SI_CLE               IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
		                                           NEW_L_NR_REFERENCE         IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
		                                           NEW_L_NR_REF_SITE          IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
		                                           NEW_C_NR_REF_GENRE         IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
		                                           NEW_CLOB_NR_TEXTE_FORMAT   IN NR_NARRATION_TRANSITOIRE.CLOB_NR_TEXTE_FORMAT%TYPE 
       * @param subject
       * @param narration
       * @throws DAOException
       */
    public void insertionNarrationTemporaire(CardexAuthenticationSubject subject, final Narration narration) throws DAOException {
		log.fine("obtenirListeProfils");
		final Connection connection = DAOConnection.getInstance().getConnection(subject);
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
		
			public void preparer(CallableStatement callableStatement) throws SQLException {
		        callableStatement.setLong(1, narration.getCle());
		        callableStatement.setLong(2, narration.getSite());
		        callableStatement.setLong(3, narration.getLien());
		        callableStatement.setLong(4, narration.getLienSite());
		        callableStatement.setString(5, narration.getGenreLiaison());
				Clob narrationTemporaire = construireClob(connection);
		        narrationTemporaire.setString(1, narration.getNarrationTemporaire()); 		        
		        callableStatement.setClob(6, narrationTemporaire);
			}
		};		
		
		storeProcTemplate.prepareCall("CARDEX_WEB_NARRATION.SPW_I_NR_NARRATION_TRANSITOIRE", 6, rch);
		
		storeProcTemplate.query(true);
    }

    /**
  procedure SPW_E_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE               IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE               IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE         IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE          IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE         IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           NEW_CLOB_NR_TEXTE_FORMAT   IN NR_NARRATION_TRANSITOIRE.CLOB_NR_TEXTE_FORMAT%TYPE)
	 * @param subject
	 * @param narration
	 * @throws DAOException
	 */
	public void sauvegarderNarrationTemporaire(CardexAuthenticationSubject subject, final Narration narration) throws DAOException {
		log.fine("obtenirListeProfils");
		final Connection connection = DAOConnection.getInstance().getConnection(subject);
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
		
			public void preparer(CallableStatement callableStatement) throws SQLException {
		        callableStatement.setLong(1, narration.getCle());
		        callableStatement.setLong(2, narration.getSite());
		        callableStatement.setLong(3, narration.getLien());
		        callableStatement.setLong(4, narration.getLienSite());
		        callableStatement.setString(5, narration.getGenreLiaison());
				Clob narrationTemporaire = construireClob(connection);
		        narrationTemporaire.setString(1, narration.getNarrationTemporaire()); 		        
		        callableStatement.setClob(6, narrationTemporaire);
			}
		};		
		
		storeProcTemplate.prepareCall("CARDEX_WEB_NARRATION.SPW_E_NR_NARRATION_TRANSITOIRE", 6, rch);
		
		storeProcTemplate.query(true);
	}    
    
	/**
	 * Pour qu'une nouvelle narration dans l'entit� n'ai pas le boutton recharger
	 * @param subject
	 * @param narration
	 * @throws DAOException
	 */
	public void assignerCleNarrationTemporaire(Connection connection, final Narration narration) throws DAOException {
		log.fine("assignerCleNarrationTemporaire");
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
		
			public void preparer(CallableStatement callableStatement) throws SQLException {
		        callableStatement.setLong(1, narration.getCle());
		        callableStatement.setLong(2, narration.getSite());
		        callableStatement.setLong(3, narration.getLien());
		        callableStatement.setLong(4, narration.getLienSite());
		        callableStatement.setString(5, narration.getGenreLiaison());
			}
		};		
		
		storeProcTemplate.prepareCall("CARDEX_WEB_NARRATION.SPW_E_NR_ASSIGNER_CLE", 5, rch);
		
		storeProcTemplate.query(false);
	}    
	
  	private Clob construireClob(Connection connection) throws SQLException{
		CallableStatement callableStatement = null;
		try{
			callableStatement = connection.prepareCall("{ call DBMS_LOB.CREATETEMPORARY(?, TRUE)}");
			callableStatement.registerOutParameter(1, OracleTypes.CLOB);
			callableStatement.execute();
			return (Clob)callableStatement.getObject(1);
		} finally {
			if ( callableStatement != null ) {
				try {callableStatement.close();} catch (Throwable e) {}
			}
		}
	}
      
      /**
       *   procedure SPW_L_NR_NARRATION_TRANSITOIRE(NEW_L_NR_CLE               IN NR_NARRATION_TRANSITOIRE.L_NR_CLE%TYPE,
                                           NEW_L_SI_CLE               IN NR_NARRATION_TRANSITOIRE.L_SI_CLE%TYPE,
                                           NEW_L_NR_REFERENCE         IN NR_NARRATION_TRANSITOIRE.L_NR_REFERENCE%TYPE,
                                           NEW_L_NR_REF_SITE          IN NR_NARRATION_TRANSITOIRE.L_NR_REF_SITE%TYPE,
                                           NEW_C_NR_REF_GENRE         IN NR_NARRATION_TRANSITOIRE.C_NR_REF_GENRE%TYPE,
                                           rc1                        IN OUT DTS_NR_NARRATION_TRANSITOIRE)
       * @param subject
       * @param narration
       * @return
       * @throws DAOException
       */
      public Narration obtenirNarrationTemporaire(CardexAuthenticationSubject subject, final Narration narration) throws DAOException {
  		log.fine("obtenirNarrationTemporaire");
		final Narration narrationTemporaire = new NarrationVO();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
		
			public void preparer(CallableStatement callableStatement) throws SQLException {
		        callableStatement.setLong(1, narration.getCle());
		        callableStatement.setLong(2, narration.getSite());
		        callableStatement.setLong(3, narration.getLien());
		        callableStatement.setLong(4, narration.getLienSite());
		        callableStatement.setString(5, narration.getGenreLiaison());
		        callableStatement.registerOutParameter(6, OracleTypes.CURSOR);
			}
		};		
		
		storeProcTemplate.prepareCall("CARDEX_WEB_NARRATION.SPW_L_NR_NARRATION_TRANSITOIRE", 6, 6, rch);
		
    	UnEnregistrementPresent enregistrementPresent = new UnEnregistrementPresent(){
			public void processRow(ResultSet rs) throws SQLException {
				narrationTemporaire.setDateChangement(rs.getTimestamp(8));
				narrationTemporaire.setNarrationTemporaire(rs.getClob(7).getSubString(1, (int) rs.getClob(7).length()));
			}
    	};		
		
		storeProcTemplate.query(enregistrementPresent, true);
		
		return narrationTemporaire;
      }      
      
      public void supprimerNarrationTemporaireDePlusDe7Jours(CardexAuthenticationSubject subject) throws DAOException {
  		log.fine("supprimerNarrationTemporaireDePlusDe7Jours");
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		storeProcTemplate.prepareCall("CARDEX_WEB_NARRATION.SPW_S_NR_NARRATION_TRANSITOIRE", 0, 0, null);
		
		storeProcTemplate.query(true);
      }
      
      /**
       * �criture d'une entr�e dans la table AUD_RECHERCHE_NARRATION.
	   * Cette table sert � conserver l'audit de la consultation des narrations
	   * dans la rechercher des narrations. Chaque fois que l'utilisateur clique
	   * sur le champ narration dans les r�sultats, le servlet AuditRechercheNarration
	   * d�clenche l'action d'enregistrement.
       * Proc�dure appel�e : CARDEX_WEB_NARRATION.SPW_E_AUD_RECHERCHE_NARRATION
       * Date de cr�ation : (2002-02-19)
       * @author Fran�ois Gu�rin
       * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
       * @param narration Narration : narration saisie � l'�cran.
       * @param action  java.lang.String : U ou I
       * @param genreFichier String : code � deux lettres de la table qui lie la narration
       * (Dossier (DO), Sujet (SU), Soci�t� (SO), V�hicule (VE)).
       * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
       * rupture de connexion avec la base de donn�es, ou que la table demand�e est
       * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
       * "stored procedure".
       * @return Narration
       */
         public void ajoutAuditRechercheNarration(CardexAuthenticationSubject subject, long cle, long site, String dossier) throws DAOException {
      	   Connection connection = DAOConnection.getInstance().getConnection(subject);
     	   CallableStatement callableStatement = null;

      	   try {
               callableStatement = connection.prepareCall("begin CARDEX_AUDIT.SP_E_AUD_RECHERCHE_NARRATION (?,?,?); end;");
               callableStatement.setLong(1,cle);
               callableStatement.setLong(2,site);
               callableStatement.setString(3,dossier);
               callableStatement.execute();
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    		   throw new DAOException(e);
    	   }finally {
    	         if (connection != null) {
    				try {
    					if (!connection.getAutoCommit()) {
    						connection.rollback();
    					}
    					connection.close();
    				} catch (SQLException se) {
    					throw new DAOException(se);
    				}
    			}
				if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
	                    throw new DAOException(e);
			        }
				}
    	   }
       }

         public void approbationAutomatique(final java.util.Date dateDebut, final java.util.Date dateFin, Connection connection) throws DAOException {
       		log.fine("approbationAutomatique");
      	   CallableStatement callableStatement = null;

      	   try {
               callableStatement = connection.prepareCall("begin CARDEX_WEB_NARRATION.SPW_E_APPROBATION_AUTO (?,?); end;");
               callableStatement.setTimestamp(1, new Timestamp(dateDebut.getTime()));
               callableStatement.setTimestamp(2, new Timestamp(dateFin.getTime()));
               callableStatement.execute();
    	   } catch (SQLException e) {
    		   e.printStackTrace();
    		   throw new DAOException(e);
    	   }finally {
				if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
	                    throw new DAOException(e);
			        }
				}
    	   }
        }
           
         
}
