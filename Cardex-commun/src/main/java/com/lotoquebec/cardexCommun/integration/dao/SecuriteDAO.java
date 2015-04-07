package com.lotoquebec.cardexCommun.integration.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.IntervenantVO;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.sql.SecuriteSQL;
import com.lotoquebec.cardexCommun.securite.RoleCache;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;

public class SecuriteDAO {

    /**
     * L'instance du gestionnaire de journalisation.
     */
    private final Logger log = LoggerFactory.getLogger((this.getClass()));
    
	/**
	 * Obtenir la liste des r�les autoris�s pour un code d'usager
	 * d'un intervenant
	 * @param nomUsager
	 * @return
	 * @throws DAOException 
	 */
	public Set<String> obtenirListeRoles(String codeUsager) throws DAOException{
		final Set<String> setRoles = new HashSet<String>();
		Set<Integer> setGroupes = obtenirListeGroupeSecurite(codeUsager);
		setRoles.addAll( obtenirListeGroupeRoles(setGroupes) );
		setRoles.addAll( obtenirListeIntevenantRoles(codeUsager ));

		return setRoles;
	}
	
	public void ajouterRoles(AuthenticationSubject subject) throws DAOException {
    	CardexPrivilege cardexPrivilege = (CardexPrivilege) subject.getPrivilege();
		cardexPrivilege.viderRole();
		CardexUser cardexUser = (CardexUser)subject.getUser();

		cardexPrivilege.setRoles(obtenirListeRoles(cardexUser.getCode()));
	}
	
	/**
	 * 	TYPE DTS_IN_INTERVENANT IS REF CURSOR RETURN IN_INTERVENANT%ROWTYPE;
	 * 	PROCEDURE SP_L_INTERVENANT(
	 * 		V_IN_NAME_PARENT in IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
	 * 		rc1 OUT DTS_IN_INTERVENANT);
	 */
	public List<IntervenantVO> obtenirListeProfils(final String codeUsager) throws DAOException{
		final List<IntervenantVO> listeIntervenant = new ArrayList<IntervenantVO>();
		log.debug("obtenirListeProfils");
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    	        callableStatement.setString(1, codeUsager);
    			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_SPECIAL.SP_L2_INTERVENANT_PARENT", 2, 2, rch);
		
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				listeIntervenant.add( obtenirIntervenant( rs ) );
			}
    	};		
		
		storeProcTemplate.query(rcbh, true);

    	return listeIntervenant;
	}

	/**
	 * 	TYPE DTS_IGS_INTERVENANT_GROUPE_SEC IS REF CURSOR RETURN IGS_INTERVENANT_GROUPE_SEC%ROWTYPE;
	 *  PROCEDURE SP_L_INTERVENANT_GROUPE(
	 *  	v_name in IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE,
	 *  	rc1 OUT DTS_IGS_INTERVENANT_GROUPE_SEC);
	 *  
	 * @param codeUsager
	 * @return
	 * @throws DAOException
	 */
	public Set<Integer> obtenirListeGroupeSecurite(final String codeUsager) throws DAOException{
		final Set<Integer> setGroupes = new HashSet<Integer>();
		log.debug("obtenirListeProfils");
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    	        callableStatement.setString(1, codeUsager);
    			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_SPECIAL.SP_L_INTERVENANT_GROUPE", 2, 2, rch);
		
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				setGroupes.add( rs.getInt("L_GS_CLE") );
			}
    	};		
		
		storeProcTemplate.query(rcbh, true);
		
		return obtenirListeGroupeEnfantSecurite(setGroupes);		
	}

	private Set<Integer> obtenirListeGroupeEnfantSecurite(Set<Integer> setGroupes) throws DAOException{
		final Set<Integer> listeGroupesEnfants = new HashSet<Integer>();

		if (setGroupes.isEmpty())
			return listeGroupesEnfants;
		
		log.debug("obtenirListeGroupeEnfantSecurite");
		JDBCTemplate template = new JDBCTemplate();
    	
    	String sql = SecuriteSQL.obtenirSQLSousGroupeSecurite(setGroupes);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				listeGroupesEnfants.add( rs.getInt(1) );
			}
    	};
    	
    	template.query(sql, rch);
    	
    	setGroupes.addAll( obtenirListeGroupeEnfantSecurite(listeGroupesEnfants) );
    	
		return setGroupes;
	}
	
	private Set<String> obtenirListeGroupeRoles(Set<Integer> setGroupes) throws DAOException{
		final Set<String> listeRoles = new HashSet<String>();
		
		if (setGroupes.isEmpty())
			return listeRoles;
		
		log.debug("obtenirListeGroupeRoles");
    	JDBCTemplate template = new JDBCTemplate();
    	
    	String sql = SecuriteSQL.obtenirSQLGroupeRoles(setGroupes);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				listeRoles.add( rs.getString(1) );
			}
    	};
    	template.query(sql, rch);
    	
		return listeRoles;
	}	
	
	/**
	 * 	TYPE DTS_IR_INTERVENANT_ROLE IS REF CURSOR RETURN IR_INTERVENANT_ROLE%ROWTYPE;
	 * 	PROCEDURE SP_L_INTERVENANT_ROLE(
	 * 		v_name in IR_INTERVENANT_ROLE.V_NAME%TYPE,
	 * 		rc1 OUT DTS_IR_INTERVENANT_ROLE);
	 */
	private Set<String> obtenirListeIntevenantRoles(final String codeUsager) throws DAOException{
		log.debug("obtenirListeIntevenantRoles");
		final Set<String> listeRoles = new HashSet<String>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    	        callableStatement.setString(1, codeUsager);
    			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall("CARDEX_SPECIAL.SP_L_INTERVENANT_ROLE", 2, 2, rch);
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				listeRoles.add( rs.getString(2) );
			}
    	};		
		
		storeProcTemplate.query(rcbh, true);		

		return listeRoles;
	}		
	
	private IntervenantVO obtenirIntervenant(ResultSet resultSet) throws SQLException{
		IntervenantVO intervenant = new IntervenantVO();
	    intervenant.setCode(resultSet.getString("PROFIL"));
	    intervenant.setCodeParent(resultSet.getString("PARENT"));
	    intervenant.setNom(resultSet.getString("NOM"));
	    intervenant.setPrenom(resultSet.getString("PRENOM"));
	    intervenant.setSecteurDescription(resultSet.getString("SECTEUR"));
	    intervenant.setSousSecteurDescription(resultSet.getString("SOUS_SECTEUR"));
	    intervenant.setStatutDescription(resultSet.getString("STATUT"));
	    intervenant.setAutoriteDescription(resultSet.getString("AUTORITE"));
	    intervenant.setConfidentialiteDescription(resultSet.getString("CONFIDENTIALITE"));
	    intervenant.setSiteDescription(resultSet.getString("SITE"));
	    intervenant.setSite(resultSet.getLong("SITE_CLE"));
	    intervenant.setEntite(resultSet.getLong("ENTITE"));
	    return intervenant;
	}
	
	/**
	 * Obtenir la liste des r�les admissibles (qui sont g�r� par la s�curit�)
	 * pour l'application Cardex
	 * 
	 * PROCEDURE SP_L_ROLE_SECURITE_ADMINISTRER(
	 * 		rc1 OUT DTS_RS_ROLE_SECURITE);
	 * @return
	 * @throws DAOException
	 */
	public Set<RoleCache> obtenirListeRoleCache(final String nomApplication) throws DAOException{
		final Set<RoleCache> setRoles = new HashSet<RoleCache>();
		log.debug("obtenirListeRoleCache");
    	
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate();
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1, nomApplication);
    			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall("CARDEX_SPECIAL.SP_L_ROLE_SECURITE", 2, 2, rch);
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String role = rs.getString(1);
				String classeForm = rs.getString(3);
				String classeVO = rs.getString(4);
				String url = rs.getString(5);
				String liste = rs.getString(6);
				boolean administrer = OracleDAOUtils.convertirStringABoolean(rs.getString(8));
				
				setRoles.add(new RoleCache(role, classeForm, classeVO, liste, url, administrer));
			}
    	};		
		
		storeProcTemplate.query(rcbh, true);		
		
		return setRoles;
	}
}
