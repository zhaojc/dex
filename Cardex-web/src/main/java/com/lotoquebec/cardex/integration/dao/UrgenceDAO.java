package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.CriteresRechercheUrgence;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.UrgenceCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.UrgenceCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.CallStatementHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.TransCallBackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Offre tout les services de récupération des informations d'une base de donnée
 * Oracle, relatives aux services d'urgence. 
 * @author levassc
 *
 */
public class UrgenceDAO {

    private final static Logger      log =
        (Logger)LoggerCardex.getLogger(UrgenceDAO.class);

	public void ajouter(CardexAuthenticationSubject subject, Urgence urgenceVO) throws DAOException{
		editUrgence(subject, GlobalConstants.Action.INSERER, urgenceVO);
	}

	public void supprimer(CardexAuthenticationSubject subject, Urgence urgenceVO) throws DAOException{
		editUrgence(subject, GlobalConstants.Action.SUPPRIMER, urgenceVO);
	}

	public void modifier(CardexAuthenticationSubject subject, Urgence urgenceVO) throws DAOException{
		editUrgence(subject, GlobalConstants.Action.MODIFIER, urgenceVO);
	}

    public static Integer nombreDeUrgenceRecherche(CardexAuthenticationSubject subject,CriteresRechercheUrgence criteria) throws DAOException {
        JDBCTemplate template = new JDBCTemplate(subject);
        PreparerSQL preparerSQL = (new UrgenceCountSQL()).construireSQL(subject, criteria);
       
        UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                object = rs.getInt(1);
            }
        };
       
        template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
       
        return (Integer) unEnregistrementPresent.getObject();
     }     
	
	private Urgence editUrgence(CardexAuthenticationSubject subject, final String action, final Urgence urgenceVO) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
  				callableStatement.setString(1,action); // action IN CHAR,
    			callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL); 
  				callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL); 
    			OracleDAOUtils.setLong(callableStatement, 2, urgenceVO.getCle()); 
    			OracleDAOUtils.setLong(callableStatement, 3, urgenceVO.getSite()); 
    			OracleDAOUtils.setLong(callableStatement, 4, urgenceVO.getClasse()); 
    			OracleDAOUtils.setLong(callableStatement, 5, urgenceVO.getLien()); 
    			OracleDAOUtils.setLong(callableStatement, 6, urgenceVO.getLienSite()); 
    			OracleDAOUtils.setLong(callableStatement, 7, urgenceVO.getLienSociete()); 
    			OracleDAOUtils.setLong(callableStatement, 8, urgenceVO.getLienSiteSociete()); 
    			if(StringUtils.isNotEmpty(urgenceVO.getUnite())){
        			callableStatement.setString(9, urgenceVO.getUnite().toUpperCase().trim()); 
    			}else{
    				callableStatement.setString(9, "");
    			}
    			if(StringUtils.isNotEmpty(urgenceVO.getDistrict())){
        			callableStatement.setString(10, urgenceVO.getDistrict().toUpperCase().trim()); 
    			}else{
    				callableStatement.setString(10, "");
    			}
    			if(StringUtils.isNotEmpty(urgenceVO.getContact())){
        			callableStatement.setString(11, urgenceVO.getContact().toUpperCase().trim()); 
    			}else{
    				callableStatement.setString(11, "");
    			}
                if(StringUtils.isNotEmpty(urgenceVO.getContactPrenom())){
                    callableStatement.setString(12, urgenceVO.getContactPrenom().toUpperCase().trim()); 
                }else{
                    callableStatement.setString(12, "");
                }
                if(StringUtils.isNotEmpty(urgenceVO.getFonctionGrade())){
                    callableStatement.setString(13, urgenceVO.getFonctionGrade().toUpperCase().trim()); 
                }else{
                    callableStatement.setString(13, "");
                }
                if(StringUtils.isNotEmpty(urgenceVO.getMatricule())){
                    callableStatement.setString(14, urgenceVO.getMatricule().toUpperCase().trim()); 
                }else{
                    callableStatement.setString(14, "");
                }
    			callableStatement.setString(15, urgenceVO.getTelephone());
    			OracleDAOUtils.setLong(callableStatement, 16, urgenceVO.getPoste()); 
    			callableStatement.setString(17, urgenceVO.getTelecopieur());
    			if(StringUtils.isNotEmpty(urgenceVO.getVille())){
        			callableStatement.setString(18, urgenceVO.getVille().toUpperCase().trim()); 
    			}else{
    				callableStatement.setString(18, "");
    			}
    			callableStatement.setString(19, urgenceVO.getCourriel());
    			callableStatement.setString(20, urgenceVO.getEvenement());
    			callableStatement.setString(21, String.valueOf(urgenceVO.isRepondant()));
    			OracleDAOUtils.setLong(callableStatement, 22, urgenceVO.getMotif()); 
    			OracleDAOUtils.setLong(callableStatement, 23, urgenceVO.getStatut()); 
    		}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_UR_URGENCE", 23, rch);
    	
    	CallStatementHandler callStatementHandler = new CallStatementHandler(){
			public void process(CallableStatement callableStatement) throws SQLException {
				if (GlobalConstants.Action.INSERER.equals(action)){
					urgenceVO.setCle(callableStatement.getLong(2));
					urgenceVO.setSite(callableStatement.getLong(3));
				}				
			}
    	};		
    	
    	storeProcTemplate.query( callStatementHandler, true );
    	return urgenceVO;
	}
	
	public List<UrgenceVO> findLiensUrgence(CardexAuthenticationSubject subject, final Dossier dossier) throws DAOException{
		final List<UrgenceVO> listeUrgenceVO = new ArrayList<UrgenceVO>();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.oracle.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossier.getCle()); 
    			OracleDAOUtils.setLong(callableStatement, 2, dossier.getSite()); 
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR); 
			}
    	};
    	
    	storeProcTemplate.prepareCall("cardex_lire_lien.SP_L3_UR_URGENCE", 3, 3, rch);
    	
    	RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				 listeUrgenceVO.add( traitementResultSet(rs) );
			}
    	};		
    	
    	storeProcTemplate.query( rowCallbackHandler, true );
    	return listeUrgenceVO;
	}
	
	public Urgence find(CardexAuthenticationSubject subject, final Urgence urgenceVO) throws DAOException{
		final UrgenceVO urgenceVOSortie = new UrgenceVO();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.oracle.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, urgenceVO.getCle()); 
    			OracleDAOUtils.setLong(callableStatement, 2, urgenceVO.getSite()); 
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR); 
			}
    	};
    	
    	storeProcTemplate.prepareCall("cardex_lire_lien.SP_L_UR_URGENCE", 3, 3, rch);
    	
    	TransCallBackHandler transCallBackHandler = new TransCallBackHandler(urgenceVO){
			public void processRow(ResultSet rs) throws SQLException {
				traitementResultSetLien(urgenceVOSortie, rs);
			}
    	};		
    	
    	storeProcTemplate.query( transCallBackHandler, true );    	
    	
    	return urgenceVOSortie;
	}	

    private static Urgence traitementResultSetDefault(ResultSet resultSet) throws SQLException  {
        UrgenceVO urgence = new UrgenceVO();
        DossierVO dossier = new DossierVO();
        urgence.setCle(resultSet.getLong("L_UR_CLE")); 
        urgence.setSite(resultSet.getLong("L_SI_CLE")); 
        urgence.setClasse(resultSet.getLong("I_CL_CLE"));
        urgence.setLien(resultSet.getLong("L_UR_REF_CLE"));
        urgence.setLienSite(resultSet.getLong("L_UR_REF_SITE")); 
        urgence.setLienSociete(resultSet.getLong("L_SO_REF_CLE")); 
        urgence.setLienSiteSociete(resultSet.getLong("L_SO_REF_SITE"));
        urgence.setSociete(resultSet.getString("V_SO_NOM"));
        urgence.setUnite(resultSet.getString("V_UR_UNITE")); 
        urgence.setContact(resultSet.getString("V_UR_CONTACT")); 
        urgence.setContactPrenom(resultSet.getString("V_UR_CONTACT_PRENOM"));
        urgence.setFonctionGrade(resultSet.getString("V_UR_FONCTION"));
        urgence.setMatricule(resultSet.getString("V_UR_MATRICULE"));
        urgence.setDistrict(resultSet.getString("V_UR_DISTRICT")); 
        urgence.setVille(resultSet.getString("V_UR_VILLE"));
        urgence.setEvenement(resultSet.getString("V_UR_EVENEMENT"));
        urgence.setStatut(resultSet.getLong("I_ST_CLE")); 
        urgence.setMotif(resultSet.getLong("L_UR_MOTIF")); 
        urgence.setDateCreation(resultSet.getTimestamp("D_UR_DATE_CREATION")); 
        urgence.setCreateur(resultSet.getString("V_UR_CREE_PAR"));
        urgence.setNumeroDossier(resultSet.getString("V_DO_NUMERO_DOSSIER"));
        //Inscription des valeurs sur les dossiers associés aux recherches de suivis
        dossier.setCle(resultSet.getLong("L_DO_CLE"));
        dossier.setSite(resultSet.getLong("L_SI_CLE"));
        dossier.setNumeroCardex(resultSet.getString("V_DO_NUMERO_DOSSIER"));
        urgence.setDossier(dossier);
        log.fine("   [URGENCE id='"+urgence.getCle()+"']");
       return urgence;
  }	
	
	private UrgenceVO traitementResultSet(ResultSet resultSet) throws SQLException {
		  UrgenceVO urgenceVO = new UrgenceVO();
		  traitementResultSetLien(urgenceVO, resultSet);
		  return urgenceVO;
	}
	
	private void traitementResultSetLien(UrgenceVO urgenceVO, ResultSet resultSet) throws SQLException {
			  urgenceVO.setCle(resultSet.getLong("L_UR_CLE")); 
			  urgenceVO.setSite(resultSet.getLong("L_SI_CLE")); 
			  urgenceVO.setLien(resultSet.getLong("L_UR_REF_CLE")); 
			  urgenceVO.setLienSite(resultSet.getLong("L_UR_REF_SITE")); 
			  urgenceVO.setLienSociete(resultSet.getLong("L_SO_REF_CLE")); 
			  urgenceVO.setLienSiteSociete(resultSet.getLong("L_SO_REF_SITE")); 
			  urgenceVO.setClasse(resultSet.getLong("I_CL_CLE")); 
			  urgenceVO.setUnite(resultSet.getString("V_UR_UNITE")); 
			  urgenceVO.setContact(resultSet.getString("V_UR_CONTACT")); 
			  urgenceVO.setContactPrenom(resultSet.getString("V_UR_CONTACT_PRENOM"));
			  urgenceVO.setFonctionGrade(resultSet.getString("V_UR_FONCTION"));
			  urgenceVO.setMatricule(resultSet.getString("V_UR_MATRICULE"));
			  urgenceVO.setDistrict(resultSet.getString("V_UR_DISTRICT")); 
			  urgenceVO.setTelephone(resultSet.getString("V_UR_TELEPHONE")); 
			  urgenceVO.setPoste(resultSet.getLong("L_UR_POSTE"));
			  urgenceVO.setTelecopieur(resultSet.getString("V_UR_TELECOPIEUR")); 
			  urgenceVO.setVille(resultSet.getString("V_UR_VILLE"));
			  urgenceVO.setCourriel(resultSet.getString("V_UR_COURRIEL"));
			  urgenceVO.setEvenement(resultSet.getString("V_UR_EVENEMENT"));
			  urgenceVO.setRepondant(Boolean.valueOf(resultSet.getString("V_UR_REPONDANT")));
			  urgenceVO.setStatut(resultSet.getLong("I_ST_CLE")); 
			  urgenceVO.setMotif(resultSet.getLong("L_UR_MOTIF")); 
			  urgenceVO.setDateCreation(resultSet.getTimestamp("D_UR_DATE_CREATION")); 
			  urgenceVO.setCreateur(resultSet.getString("V_UR_CREE_PAR"));
			  urgenceVO.setSociete(resultSet.getString("V_SO_NOM"));
	}
	
	/**
	 * Routine pour traiter les ResultSet retournés par les recherches de service d'urgence.
	 * Date de création : (2002-02-21)
	 * @author François Guérin
	 * @param resultSet  ResultSet : données retournées par une recherche
	 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
	 * rupture de connexion avec la base de données, ou que la table demandée est
	 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
	 * "stored procedure".
	 * @return ArrayList : liste des services d'urgence traités.
	 */
	    public static RowCallbackHandler constuireRowCallBackHandler(final List<Urgence> listUrgence){
	       return new RowCallbackHandler(){
	           public void processRow(ResultSet resultSet) throws SQLException {
	               Urgence urgence = traitementResultSetDefault(resultSet);
	               listUrgence.add(urgence);                  
	           }
	       };
	    }    
	
    /**
	 * Recherche des services d'urgence à l'aide de critères de recherche.
	 * La procédure appelée autrefois est de type DBMS (SQL dynamique)
	 * Le resultSet retourné par les recherches est traité dans la routine traitementResultSet.
	 * Procédure appelée : générée ici.
	 * @author mazzucr
	 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
	 * @param criteria CriteresRechercheVehicule : critères de recherche
	 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
	 * rupture de connexion avec la base de données, ou que la table demandée est
	 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
	 * "stored procedure".
	 * @return ValueListIterator : liste des servces d'urgence retournés par la recherche.
	 */
	    public static List<Urgence> select(CardexAuthenticationSubject subject, CriteresRechercheUrgence criteria) throws DAOException {
	        JDBCTemplate template = new JDBCTemplate(subject);
	        List<Urgence> urgenceList = new ArrayList<Urgence>();
	        PreparerSQL preparerSQL = (new UrgenceCompletSQL()).construireSQL(subject, criteria);
	        template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(urgenceList));
	       
	        return urgenceList;    
	    }
	    
	/**
	 * Appel de la méthode editUrgence pour la création d'un service d'urgence
	 * Date de création : (2002-02-27)
	 * @author Philippe Caron
	 * @param subject CardexAuthenticationSubject : Données nominatives sur
	 * l'utilisateur.
	 * @param urgence Urgence : Consignation saisie à l'écran.
	 * @param genreFichier String : Code identifiant la table source qui lie un
	 * consignation à un Dossier.
	 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
	 * rupture de connexion avec la base de données, ou que la table demandée est
	 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
	 * "stored procedure".
	 * @return Consignation
	 */
		public Urgence insert(CardexAuthenticationSubject subject, Urgence urgence) throws DAOException {
		  return editUrgence(subject, "I", urgence);
		}

		/**
		 * Appel de la méthode editUrgence pour la suppression d'un service d'urgence
		 * Date de création : (2002-02-27)
		 * @author Philippe Caron
		 * @param subject CardexAuthenticationSubject : Données nominatives sur
		 * l'utilisateur.
		 * @param consignation Consignation : Consignation saisie à l'écran.
		 * @param genreFichier String : Code identifiant la table source qui lie un
		 * consignation à un Dossier.
		 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
		 * rupture de connexion avec la base de données, ou que la table demandée est
		 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
		 * "stored procedure".
		 * @return Urgence
		 */
			public Urgence delete(CardexAuthenticationSubject subject, Urgence urgence) throws DAOException {
			  return editUrgence(subject, "D", urgence);
			}
		
	    /**
	     * Appel de la méthode editUrgence pour la mise à jour d'un service d'urgence
	     * Date de création : (2002-02-27)
	     * @author Philippe Caron
	     * @param subject CardexAuthenticationSubject : Données nominatives sur
	     * l'utilisateur.
	     * @param consignation Consignation : Consignation saisie à l'écran.
	     * @param genreFichier String : Code identifiant la table source qui lie un
	     * consignation à un Dossier.
	     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
	     * rupture de connexion avec la base de données, ou que la table demandée est
	     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
	     * "stored procedure".
	     */
	    	public void update(CardexAuthenticationSubject subject, Urgence urgence) throws DAOException {
	    	  editUrgence(subject, "U", urgence );
	    	}

    	/**
         * Recherche des services d'urgence créés dans les deux derniers jours lors de
         * l'affichage de l'écran de recherche des services d'urgence Procédure appelée :
         * CARDEX_WEB_LIRE_DOC_TRI.SPW_L_UR_URGENCE Date de création : (2014-03-17)
         * 
         * @author mazzucr
         * @param subject
         *            CardexAuthenticationSubject : données nominatives sur
         *            l'utilisateur
         * @param criteria
         *            CriteresRechercheUrgence : critères de recherche
         * @throws DAOException
         *             lancée lorsqu'une SQLException est reçue lors d'une rupture
         *             de connexion avec la base de données, ou que la table
         *             demandée est non disponible, ou qu'un problème est survenu
         *             lors de l'exécution d'une "stored procedure".
         * @return ValueListIterator : une liste de services d'urgence retournés par la
         *         recherche.
         */
    
        public static List<Urgence> selectDefault(CardexAuthenticationSubject subject, CriteresRechercheUrgence criteria) throws DAOException
        {
            Connection connection = DAOConnection.getInstance().getConnection(subject);
            CallableStatement callableStatement = null;
            ResultSet resultSet = null;
            ArrayList results = new ArrayList();
            try
            {
                callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_UR_URGENCE (?); end;");
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.execute();
                resultSet = (ResultSet) callableStatement.getObject(1);
                ValueListHandler listIterator = new ValueListHandler();
                // Traitement des données retournées
                while (resultSet.next())
                {
                    results.add(traitementResultSetDefault(resultSet));
                }
                return results;
            }
            catch (SQLException se)
            {
                throw new DAOException(se);
            }
            finally
            {
                try
                {
                    if (resultSet != null)
                    {
                        resultSet.close();
                    }
                    if (callableStatement != null)
                    {
                        callableStatement.close();
                    }
                    if (connection != null)
                    {
                        if (!connection.getAutoCommit())
                        {
                            connection.rollback();
                        }
                        connection.close();
                    }
                }
                catch (SQLException se)
                {
                    throw new DAOException(se);
                }
            } // finally
        }
}
