package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheBilletVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.BilletCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.BilletCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.CallStatementHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.TransCallBackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;

/**
 * Offre tout les services de récupération des informations d'une base de donnée
 * Oracle, relatives aux billets. 
 * @author levassc
 *
 */
public class BilletDAO {

	public void ajouter(CardexAuthenticationSubject subject, BilletVO billetVO) throws DAOException{
		editBillet(subject, GlobalConstants.Action.INSERER, billetVO);
	}

	public void supprimer(CardexAuthenticationSubject subject, BilletVO billetVO) throws DAOException{
		editBillet(subject, GlobalConstants.Action.SUPPRIMER, billetVO);
	}

	public void modifier(CardexAuthenticationSubject subject, BilletVO billetVO) throws DAOException{
		editBillet(subject, GlobalConstants.Action.MODIFIER, billetVO);
	}

	private void editBillet(CardexAuthenticationSubject subject, final String action, final BilletVO billetVO) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
  				callableStatement.setString(1,action); // action IN CHAR,
    			callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL); // NEW_L_BI_CLE IN OUT BI_BILLET.L_BI_CLE%TYPE,
  				callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL); // NEW_L_SI_CLE IN OUT BI_BILLET.L_SI_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 2, billetVO.getCle()); // NEW_L_BI_CLE IN OUT BI_BILLET.L_BI_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 3, billetVO.getSite()); // NEW_L_SI_CLE IN OUT BI_BILLET.L_SI_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 4, billetVO.getLien()); // NEW_L_BI_REF_CLE IN BI_BILLET.L_BI_REF_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 5, billetVO.getLienSite()); // NEW_L_BI_REF_SITE IN BI_BILLET.L_BI_REF_SITE%TYPE,
    			callableStatement.setString(6, billetVO.getLienGenre()); // NEW_C_BI_REF_GENRE IN BI_BILLET.C_BI_REF_GENRE%TYPE,
    			callableStatement.setString(7, billetVO.getNom().toUpperCase().trim()); // NEW_V_BI_NOM IN BI_BILLET.V_BI_NOM%TYPE,
    			callableStatement.setString(8, billetVO.getNumeroControl()); // NEW_V_BI_NUMERO_CONTROLE IN BI_BILLET.V_BI_NUMERO_CONTROLE%TYPE,
    			
    			if(billetVO.getValeur() != null){
    				callableStatement.setBigDecimal(9, billetVO.getValeur()); // NEW_L_BI_VALEUR IN BI_BILLET.L_BI_VALEUR%TYPE,
    			}else{
    				callableStatement.setNull(9,java.sql.Types.DOUBLE);
				}
    			callableStatement.setString(10, OracleDAOUtils.convertirBooleanAString(billetVO.isExtra())); // NEW_B_BI_EXTRA IN BI_BILLET.B_BI_EXTRA%TYPE,
    			callableStatement.setString(11, OracleDAOUtils.convertirBooleanAString(billetVO.isParticipationTirage())); // NEW_B_BI_PARTICIPATION     IN BI_BILLET.B_BI_PARTICIPATION%TYPE,
    			callableStatement.setString(12, OracleDAOUtils.convertirBooleanAString(billetVO.isFormuleGroupe())); // NEW_B_BI_FORMULE_GROUPE    IN BI_BILLET.B_BI_FORMULE_GROUPE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 13, billetVO.getTypeMise()); // NEW_L_TYPE_MISE IN BI_BILLET.L_TYPE_MISE%TYPE,
    			
    			if(billetVO.getMontantLot() != null){
    				callableStatement.setBigDecimal(14, billetVO.getMontantLot()); // NEW_L_BI_MONTANT_LOT IN BI_BILLET.L_BI_MONTANT_LOT%TYPE,
    			}else{
    				callableStatement.setNull(14,java.sql.Types.DOUBLE);
    			}
    			OracleDAOUtils.setLong(callableStatement, 15, billetVO.getCleSocieteProvenance()); // NEW_L_SO_CLE_PROVENANCE IN BI_BILLET.L_SO_CLE_PROVENANCE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 16, billetVO.getSiteSocieteProvenance()); // NEW_L_SO_SITE_PROVENANCE IN BI_BILLET.L_SO_SITE_PROVENANCE%TYPE,
    			OracleDAOUtils.setTimeStamp(callableStatement, 17, billetVO.getDateAchat()); // NEW_D_BI_DATE_ACHAT IN BI_BILLET.D_BI_DATE_ACHAT%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 18, billetVO.getCleSocieteValidation()); // NEW_L_SO_CLE_VALIDATION IN BI_BILLET.L_SO_CLE_VALIDATION%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 19, billetVO.getSiteSocieteValidation()); // NEW_L_SO_SITE_VALIDATION IN BI_BILLET.L_SO_SITE_VALIDATION%TYPE,
    			OracleDAOUtils.setTimeStamp(callableStatement, 20, billetVO.getDateValidation()); // NEW_D_BI_DATE_VALIDATION IN BI_BILLET.D_BI_DATE_VALIDATION%TYPE) IS
    			OracleDAOUtils.setLong(callableStatement, 21, billetVO.getTypeLoterie()); // NEW_L_JE_CLE IN BI_BILLET.L_JE_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 22, billetVO.getCleSocieteVerification()); // NEW_L_SO_CLE_VERIFICATION IN BI_BILLET.L_SO_CLE_VERIFICATION%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 23, billetVO.getSiteSocieteVerification()); // NEW_L_SO_SITE_VERIFICATION IN BI_BILLET.L_SO_SITE_VERIFICATION%TYPE,
    			OracleDAOUtils.setTimeStamp(callableStatement, 24, billetVO.getDateVerification()); // NEW_D_BI_DATE_VERIFICATION IN BI_BILLET.D_BI_DATE_VERIFICATION%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 25, billetVO.getCleSocieteFautif()); // NEW_L_SO_CLE_FAUTIF IN BI_BILLET.L_SO_CLE_FAUTIF%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 26, billetVO.getSiteSocieteFautif()); // NEW_L_SO_SITE_FAUTIF IN BI_BILLET.L_SO_SITE_FAUTIF%TYPE
    			OracleDAOUtils.setTimeStamp(callableStatement, 27, billetVO.getDatePaiement()); // NEW_D_BI_DATE_PAIEMENT IN BI_BILLET.D_BI_DATE_PAIEMENT%TYPE,
    			callableStatement.setString(28, OracleDAOUtils.convertirBooleanAString(billetVO.isExtraGagnant())); // NEW_B_BI_EXTRA_GAGNANT IN BI_BILLET.B_BI_EXTRA_GAGNANT%TYPE,
    			if(billetVO.getMontantExtra() != null){
    				callableStatement.setBigDecimal(29, billetVO.getMontantExtra()); // NEW_L_BI_MONTANT_EXTRA IN BI_BILLET.L_BI_MONTANT_EXTRA%TYPE,
    			}else{
    				callableStatement.setNull(29,java.sql.Types.DOUBLE);
    			}
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_BI_BILLET", 29, rch);
    	
    	CallStatementHandler callStatementHandler = new CallStatementHandler(){
			public void process(CallableStatement callableStatement) throws SQLException {
				if (GlobalConstants.Action.INSERER.equals(action)){
					billetVO.setCle(callableStatement.getLong(2));
					billetVO.setSite(callableStatement.getLong(3));
				}				
			}
    	};		
    	
    	storeProcTemplate.query( callStatementHandler, true );
	}
	public List<BilletVO> trouverLiensBillet(CardexAuthenticationSubject subject, final Dossier dossier) throws DAOException{
		final List<BilletVO> listeBilletVO = new ArrayList<BilletVO>();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.oracle.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossier.getCle()); // NEW_L_BI_REF_CLE IN BI_BILLET.L_BI_REF_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 2, dossier.getSite()); // NEW_L_BI_REF_SITE IN BI_BILLET.L_BI_REF_SITE%TYPE,
    			callableStatement.setString(3, GlobalConstants.GenreFichier.DOSSIER); // NEW_C_BI_REF_GENRE IN BI_BILLET.C_BI_REF_GENRE%TYPE,
    			callableStatement.registerOutParameter(4, OracleTypes.CURSOR); // rc1 OUT DTSW_BI_BILLET
			}
    	};
    	
    	storeProcTemplate.prepareCall("Cardex_Web_Lire_Doc_Tri.SPW_L_LIEN_BILLET", 4, 4, rch);
    	
    	RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				listeBilletVO.add( traitementResultSet(rs) );
			}
    	};		
    	
    	storeProcTemplate.query( rowCallbackHandler, true );
    	return listeBilletVO;
	}
	
	public List<BilletVO> recherche(CardexAuthenticationSubject subject, final CriteresRechercheBilletVO criteresRechercheBilletVO) throws DAOException{
		final List<BilletVO> listeBilletVO = new ArrayList<BilletVO>();
		JDBCTemplate template = new JDBCTemplate(subject);
		PreparerSQL preparerSQL = new BilletCompletSQL().construireSQL(subject, criteresRechercheBilletVO);
    	
		RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				BilletVO billetVO = new BilletVO();
				DossierVO dossierVO = new DossierVO();
				dossierVO.setCle(rs.getLong("L_DO_CLE"));
				dossierVO.setSite(rs.getLong("L_DO_SI_CLE"));
				dossierVO.setNumeroCardex(OracleDAOUtils.getString(rs, "V_DO_NUMERO_DOSSIER"));
				dossierVO.setCategorie(rs.getLong("I_CA_CLE"));
				dossierVO.setSeverite(rs.getLong("I_SE_CLE"));
				dossierVO.setConfidentialite(rs.getLong("I_CC_CLE"));
				dossierVO.setPeriode(rs.getLong("I_PE_CLE"));
				dossierVO.setStatut(rs.getLong("I_ST_CLE"));
				dossierVO.setDateDebut(rs.getTimestamp("D_DO_DATE_DEBUT"));
				dossierVO.setDateFin(rs.getTimestamp("D_DO_DATE_FIN"));
				dossierVO.setNumeroDossier(rs.getString("V_DO_ANCIENNE_REFERENCE"));
				dossierVO.setReference1(OracleDAOUtils.getString(rs, "V_DO_REFERENCE1"));
				dossierVO.setReference2(OracleDAOUtils.getString(rs, "V_DO_REFERENCE2"));
				dossierVO.setIntervenant(OracleDAOUtils.getString(rs, "V_DO_ASSIGNE_A"));
				dossierVO.setType(rs.getLong("I_TY_CLE"));
				dossierVO.setNature(rs.getLong("I_NA_CLE"));
				dossierVO.setGenre(rs.getLong("I_GE_CLE"));
				dossierVO.setEntite(rs.getLong("I_GE_ENTITE"));
				dossierVO.setReference3(OracleDAOUtils.getString(rs, "V_DO_REFERENCE3"));
				billetVO.setDossierVO(dossierVO);
				billetVO.setLien(dossierVO.getCle());
				billetVO.setSite(dossierVO.getSite());
				billetVO.setLienGenre(GlobalConstants.GenreFichier.DOSSIER);
				billetVO.setCle(rs.getLong("L_BI_CLE"));
				billetVO.setSite(rs.getLong("L_BI_SI_CLE"));
				billetVO.setNom(rs.getString("V_BI_NOM"));
				billetVO.setNumeroControl(rs.getString("V_BI_NUMERO_CONTROLE"));
				billetVO.setMontantLot(rs.getBigDecimal("L_BI_MONTANT_LOT"));
				billetVO.setDateCreation(rs.getTimestamp("D_BI_DATE_CREATION"));
				billetVO.setSite(rs.getLong("L_BI_SI_CLE"));
				billetVO.setDatePaiement(rs.getTimestamp("D_BI_DATE_PAIEMENT"));
				listeBilletVO.add(billetVO);
			}
		};
    	
    	template.query( preparerSQL, criteresRechercheBilletVO.getSequence(), rowCallbackHandler );
    	
    	return listeBilletVO;
	}
	
    public Integer nombreDeBilletRecherche(CardexAuthenticationSubject subject, CriteresRechercheBilletVO criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new BilletCountSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }    	
 
	public BilletVO trouverBillet(CardexAuthenticationSubject subject, final BilletVO billetVO) throws DAOException{
		final BilletVO billetVOSortie = new BilletVO();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
			/*
			 * @see com.lotoquebec.cardex.integration.dao.oracle.jdbc.RowCallHandler#processRow(java.sql.CallableStatement)
			 */
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, billetVO.getCle()); // NEW_L_BI_CLE IN BI_BILLET.L_BI_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 2, billetVO.getSite()); // NEW_L_SI_CLE IN BI_BILLET.L_SI_CLE%TYPE,
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR); // rc1 OUT DTSW_BI_BILLET
			}
    	};
    	
    	storeProcTemplate.prepareCall("Cardex_Web_Lire_Doc_Tri.SPW_L_BILLET", 3, 3, rch);
    	
    	TransCallBackHandler transCallBackHandler = new TransCallBackHandler(billetVO){
			public void processRow(ResultSet rs) throws SQLException {
				traitementResultSet(billetVOSortie, rs);
			}
    	};		
    	
    	storeProcTemplate.query( transCallBackHandler, true );    	
    	
    	return billetVOSortie;
	}	
	
	private BilletVO traitementResultSet(ResultSet resultSet) throws SQLException {
		  BilletVO billetVO = new BilletVO();
		  traitementResultSet(billetVO, resultSet);
		  return billetVO;
	}
	
	private void traitementResultSet(BilletVO billetVO, ResultSet resultSet) throws SQLException {
		  billetVO.setCle(resultSet.getLong("L_BI_CLE")); // L_BI_CLE	NUMBER(8)
		  billetVO.setSite(resultSet.getLong("L_SI_CLE")); // L_SI_CLE	NUMBER(8)
		  billetVO.setLien(resultSet.getLong("L_BI_REF_CLE")); // L_BI_REF_CLE	NUMBER(8)
		  billetVO.setLienSite(resultSet.getLong("L_BI_REF_SITE")); // L_BI_REF_SITE	NUMBER(8)
		  billetVO.setLienGenre(resultSet.getString("C_BI_REF_GENRE")); // C_BI_REF_GENRE	CHAR(3)
		  billetVO.setNom(resultSet.getString("V_BI_NOM")); // V_BI_NOM	VARCHAR2(50)
		  billetVO.setNumeroControl(resultSet.getString("V_BI_NUMERO_CONTROLE")); // V_BI_NUMERO_CONTROLE	VARCHAR2(30)
		  billetVO.setValeur(resultSet.getBigDecimal("L_BI_VALEUR")); // L_BI_VALEUR	NUMBER(8)
		  billetVO.setExtra(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_BI_EXTRA"))); // B_BI_EXTRA	VARCHAR2(3)
		  billetVO.setExtraGagnant(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_BI_EXTRA_GAGNANT"))); // B_BI_EXTRA_GAGNANT	VARCHAR2(3)
		  billetVO.setParticipationTirage(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_BI_PARTICIPATION"))); // B_BI_PARTICIPATION
		  billetVO.setFormuleGroupe(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_BI_FORMULE_GROUPE"))); // B_BI_FORMULE_GROUPE
		  billetVO.setTypeMise(resultSet.getLong("L_TYPE_MISE")); // L_TYPE_MISE	NUMBER(8)
		  billetVO.setMontantLot(resultSet.getBigDecimal("L_BI_MONTANT_LOT")); // L_BI_MONTANT_LOT	NUMBER(9)
		  billetVO.setCleSocieteProvenance(resultSet.getLong("L_SO_CLE_PROVENANCE")); // L_SO_CLE_PROVENANCE	NUMBER(8)
		  billetVO.setSiteSocieteProvenance(resultSet.getLong("L_SO_SITE_PROVENANCE")); // L_SO_SITE_PROVENANCE	NUMBER(8)
		  billetVO.setDateAchat(OracleDAOUtils.getDate(resultSet.getTimestamp("D_BI_DATE_ACHAT"))); // D_BI_DATE_ACHAT	DATE
		  billetVO.setCleSocieteValidation(resultSet.getLong("L_SO_CLE_VALIDATION")); // L_SO_CLE_VALIDATION	NUMBER(8)
		  billetVO.setSiteSocieteValidation(resultSet.getLong("L_SO_SITE_VALIDATION")); // L_SO_SITE_VALIDATION	NUMBER(8)
		  billetVO.setDateValidation(OracleDAOUtils.getDate(resultSet.getTimestamp("D_BI_DATE_VALIDATION"))); // D_BI_DATE_VALIDATION	DATE
		  billetVO.setCreateur(resultSet.getString("V_BI_CREE_PAR")); // V_BI_CREE_PAR	VARCHAR2(16)
		  billetVO.setDateCreation(new Date(resultSet.getTimestamp("D_BI_DATE_CREATION").getTime())); // D_BI_DATE_CREATION	DATE
		  billetVO.setTypeLoterie(resultSet.getLong("L_JE_CLE")); // L_JE_CLE	NUMBER(8)
		  billetVO.setCleSocieteVerification(resultSet.getLong("L_SO_CLE_VERIFICATION")); // L_SO_CLE_VERIFICATION	NUMBER(8)
		  billetVO.setSiteSocieteVerification(resultSet.getLong("L_SO_SITE_VERIFICATION")); // L_SO_SITE_VERIFICATION	NUMBER(8)
		  billetVO.setDateVerification(OracleDAOUtils.getDate(resultSet.getTimestamp("D_BI_DATE_VERIFICATION"))); // D_BI_DATE_VERIFICATION	DATE
		  billetVO.setCleSocieteFautif(resultSet.getLong("L_SO_CLE_FAUTIF")); // L_SO_CLE_FAUTIF	NUMBER(8)
		  billetVO.setSiteSocieteFautif(resultSet.getLong("L_SO_SITE_FAUTIF")); // L_SO_SITE_FAUTIF	NUMBER(8)
		  billetVO.setDatePaiement(OracleDAOUtils.getDate(resultSet.getTimestamp("D_BI_DATE_PAIEMENT"))); // D_BI_DATE_PAIEMENT	DATE
		  billetVO.setMontantExtra(resultSet.getBigDecimal("L_BI_MONTANT_EXTRA")); // L_BI_MONTANT_EXTRA	NUMBER(9)
	}
}
