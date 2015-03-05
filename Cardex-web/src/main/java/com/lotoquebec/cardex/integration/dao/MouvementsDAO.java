/*
 * Created on 23-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lotoquebec.cardex.business.vo.MouvementVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnectionRemphor;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.EnregistrementPresent;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class MouvementsDAO {
	
	// On ramène d'abord les données des trente dernier jours. Comme on
	// ne connaît pas
	// la date de saisie, on remonte jusqu'à 20 jours pour être certain
	// d'attraper
	// les nouveaux enregistrements.
	public List<MouvementVO> obtenirListeMouvement60joursNonTraite(final CardexAuthenticationSubject subject) throws DAOException{
		final List<MouvementVO> listeMouvement = new ArrayList<MouvementVO>();
		final Set<String> mouvementsExistantSet = obtenirMouvements61DerniersJours(subject);
		Connection connection = DAOConnectionRemphor.getInstance().getConnection();
		
		String SQL = "SELECT * FROM CMPTR.EMPLOYE_CARDEX where (dtemploi between sysdate -60 and sysdate + 1) or (dtdepart between sysdate - 60 and sysdate +1) or (dtdebconge between sysdate -60 and sysdate + 1)";
		JDBCTemplate template = new JDBCTemplate( connection );
		
		RowCallbackHandler callbackHandler = new RowCallbackHandler(){

			public void processRow(ResultSet rs) throws SQLException {
				MouvementVO mouvementVO = new MouvementVO();
				mouvementVO.setDateNaissance(rs.getTimestamp("DTNAIS"));
				mouvementVO.setNom(StringUtils.defaultString(rs.getString("nom")));
				mouvementVO.setPrenom(StringUtils.defaultString(rs.getString("prenom")));
				mouvementVO.setNumeroClientEmploye(rs.getString("MATRI"));
				mouvementVO.setCodeSite(rs.getString("SITE"));
				mouvementVO.setSiteConcerne(rs.getString("SOCIETE"));
				mouvementVO.setDateDepart(rs.getTimestamp("DTDEPART"));
				mouvementVO.setDateEmbauche(rs.getTimestamp("DTEMPLOI"));
				mouvementVO.setDateConge(rs.getTimestamp("DTDEBCONGE"));
				mouvementVO.setDateFinConge(rs.getTimestamp("DTFINCONGE"));
				mouvementVO.setDescriptionConge(StringUtils.defaultString(rs.getString("CONGE_DESC")));
				mouvementVO.setStatut(StringUtils.defaultString(rs.getString("STATUT")));
				mouvementVO.setDescriptionEmploi(StringUtils.defaultString(rs.getString("CORPS_DESCRIPTION")));
				mouvementVO.setAdministrateur(StringUtils.defaultString(rs.getString("SUPERIEUR")));
				mouvementVO.setDescriptionSecteur(StringUtils.defaultString(rs.getString("UNITE_DESCRIPTION")));
				
				//Le mouvement n'a pas été traité.
        	  	//On l'ajoute d'abord à la table des mouvements, puis on cumule
        	  	//les résultats avant de les retourner.
				if (mouvementsExistantSet.contains(mouvementVO.getCle()) == false){
					
					if (isMouvementDejaFait(subject, mouvementVO.getCle()) ){
						System.err.println("Problème avec le mouvement "+mouvementVO.getCle());
						System.err.println("No Employe: "+mouvementVO.getNumeroClientEmploye());
						System.err.println("Prenom: "+mouvementVO.getPrenom()+" Nom: "+mouvementVO.getNom());
					}else
						listeMouvement.add(mouvementVO);
				}
			}
		};
		
		template.query(SQL, callbackHandler);
		
		return listeMouvement;
	}
	
	public Set<String> obtenirMouvements61DerniersJours(CardexAuthenticationSubject subject) throws DAOException{
		final Set<String> mouvementsSet = new HashSet<String>();
		String SQL = "select m.cle from mouvements m where m.date_insertion > sysdate - 61";
		
		JDBCTemplate template = new JDBCTemplate(subject);
		
		RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				mouvementsSet.add(rs.getString(1));
			}
		};
		template.query(SQL, callbackHandler, true);
		
		return mouvementsSet;
	}

	
	public boolean isMouvementDejaFait(CardexAuthenticationSubject subject, String cle) {
		final EnregistrementPresent enregistrementPresent = new EnregistrementPresent();
		
		String SQL = "SELECT * from MOUVEMENTS where CLE = '" + cle + "'";
		
		JDBCTemplate template;
		try {
			template = new JDBCTemplate(subject);
			template.query(SQL, enregistrementPresent, true);	
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return enregistrementPresent.isTrouve();
	}
	
	public void insert(final MouvementVO mouvementVO, Connection connection) throws DAOException{
		final StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * NOM	VARCHAR2(20)
			 * PRENOM	VARCHAR2(20)
			 * NAS	VARCHAR2(12)
			 * TYPE	VARCHAR2(10)
			 * DATE_EMBAUCHE	DATE
			 * DATE_DEPART	DATE
			 * SITE	VARCHAR2(40)
			 * NO_EMPLOYE	VARCHAR2(10)
			 * DATE_NAISSANCE	DATE
			 * CLE	VARCHAR2(50)
			 * DESCRIPTION_CONGE	VARCHAR2(30)
			 * DATE_DEBUT_CONGE	DATE
			 * STATUT	VARCHAR2(10)
			 * DATE_FIN_CONGE	DATE
			 * DESCRIPTION_EMPLOI	VARCHAR2(50)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1, mouvementVO.getNom());
    			callableStatement.setString(2, mouvementVO.getPrenom());
    			callableStatement.setString(3, null);
    			callableStatement.setString(4, mouvementVO.getTypeMouvement());
    			callableStatement.setTimestamp(5, mouvementVO.getDateEmbauche());
    			callableStatement.setTimestamp(6, mouvementVO.getDateDepart());
    			callableStatement.setString(7, mouvementVO.getSiteConcerne()); //Pas le code
    			callableStatement.setString(8, mouvementVO.getNumeroClientEmploye());
    			callableStatement.setTimestamp(9, mouvementVO.getDateNaissance());
    			callableStatement.setString(10, mouvementVO.getCle());
    			callableStatement.setString(11, mouvementVO.getDescriptionConge());
    			callableStatement.setTimestamp(12, mouvementVO.getDateConge());
    			callableStatement.setString(13, mouvementVO.getStatut());
    			callableStatement.setTimestamp(14, mouvementVO.getDateFinConge());
    			callableStatement.setString(15, mouvementVO.getDescriptionEmploi());
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_DOC.SP_I_MOUVEMENTS", 15, rch);
		
		try {
			storeProcTemplate.query(false);
		} catch (DAOException e) {
			e.printStackTrace();
			System.err.println("Erreur dans la store 'CARDEX_DOC.SP_I_MOUVEMENTS'");
			System.err.println(mouvementVO.toString());
			throw e;
		}
	}
	
}
