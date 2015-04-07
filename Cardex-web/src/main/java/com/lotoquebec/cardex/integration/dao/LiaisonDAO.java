package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.LiaisonEntiteCardex;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;

public class LiaisonDAO {

	private final Logger log = LoggerFactory.getLogger(LiaisonDAO.class);
	
    public void supprimerLiaison(CardexAuthenticationSubject subject, final LiaisonEntiteVO liaisonEntiteVO) throws DAOException {
    	StoreProcTemplate template = new StoreProcTemplate(subject);
    	
    	PreparerCallableStatement pcs = new PreparerCallableStatement(){

			public void preparer(CallableStatement callableStatement) throws SQLException {
                callableStatement.setLong(1, liaisonEntiteVO.getCle());
                callableStatement.setLong(2, liaisonEntiteVO.getSite());
			}
    		
    	};
    	template.prepareCall("CARDEX_LIEN.SP_D_LDD_LIEN_DOSSIER", 2, pcs);
    	template.query();
    }
    
    public <T extends LiaisonEntiteCardex> Map<T, T> obtenirEntiteEntreDeuxEntite(CardexAuthenticationSubject subject, final EntiteCardex entiteCardex1, final EntiteCardex entiteCardex2, final Class<T> cibleEntiteCardexClass) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		final Map<T, T> entiteRetourMap = new HashMap<T, T>();
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, entiteCardex1.getCle());
		        callableStatement.setLong(2, entiteCardex1.getSite());
		        callableStatement.setString(3, genreEntiteDO(entiteCardex1, cibleEntiteCardexClass));
		        callableStatement.setString(4, genreEntiteLDD(entiteCardex1, cibleEntiteCardexClass)); 
		        
				callableStatement.setLong(5, entiteCardex2.getCle());
		        callableStatement.setLong(6, entiteCardex2.getSite());
		        callableStatement.setString(7, genreEntiteDO(entiteCardex2, cibleEntiteCardexClass));
		        callableStatement.setString(8, genreEntiteLDD(entiteCardex2, cibleEntiteCardexClass));
		        
				callableStatement.registerOutParameter(9, OracleTypes.CURSOR);
			}
		};		
		
		storeProcTemplate.prepareCall("CARDEX_LIRE_DOC.SP_L_ENTITE_LIAISON", 9, 9, rch);
		
		RowCallbackHandler rcb = new RowCallbackHandler() {
			/**
			 *     L_DO_CLE                      LDD_LIEN_DOSSIER.L_DO_CLE%TYPE,
			 *     L_SI_CLE                      LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
			 *     -- Premiere liaison
			 *     LIAISON1_L_LDD_CLE            LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
			 *     LIAISON1_L_LDD_SI_CLE         LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
			 *     LIAISON1_C_DO_GENRE           LDD_LIEN_DOSSIER.C_DO_GENRE%TYPE,
			 *     LIAISON1_L_DO_CLE             LDD_LIEN_DOSSIER.L_DO_CLE%TYPE,
			 *     LIAISON1_L_SI_CLE             LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
			 *     -- Deuxi�me liaison
			 *     LIAISON2_L_LDD_CLE            LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
			 *     LIAISON2_L_LDD_SI_CLE         LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
			 *     LIAISON2_C_DO_GENRE           LDD_LIEN_DOSSIER.C_DO_GENRE%TYPE,
			 *     LIAISON2_L_DO_CLE             LDD_LIEN_DOSSIER.L_DO_CLE%TYPE,
			 *     LIAISON2_L_SI_CLE             LDD_LIEN_DOSSIER.L_SI_CLE%TYPE);
			 */
			public void processRow(ResultSet rs) throws SQLException {
				T t = nouvelleInstance(cibleEntiteCardexClass);
				t.setCle(rs.getLong(1));
				t.setSite(rs.getLong(2));
				
				if (entiteRetourMap.containsKey(t) == false)
					entiteRetourMap.put(t, t);
				
				LiaisonEntiteVO liaisonEntiteVO1 = new LiaisonEntiteVO();
				liaisonEntiteVO1.setCle(rs.getLong(3));
				liaisonEntiteVO1.setSite(rs.getLong(4));
				liaisonEntiteVO1.setSourceEntite(t);
				T destinationEntite1 = nouvelleInstance(rs.getString(5));
				destinationEntite1.setCle(rs.getLong(6));
				destinationEntite1.setSite(rs.getLong(7));
				liaisonEntiteVO1.setDestinationEntite(destinationEntite1);
				t.getLiaisonEntites().add( liaisonEntiteVO1 );

				LiaisonEntiteVO liaisonEntiteVO2 = new LiaisonEntiteVO();
				liaisonEntiteVO2.setCle(rs.getLong(8));
				liaisonEntiteVO2.setSite(rs.getLong(9));
				liaisonEntiteVO2.setSourceEntite(t);
				T destinationEntite2 = nouvelleInstance(rs.getString(10));
				destinationEntite2.setCle(rs.getLong(11));
				destinationEntite2.setSite(rs.getLong(12));
				liaisonEntiteVO2.setDestinationEntite(destinationEntite2);
				t.getLiaisonEntites().add( liaisonEntiteVO2 );				
			}
			
			@SuppressWarnings("unchecked")
			private T nouvelleInstance(String genre){
				genre = genre.trim();
				
		    	if (GlobalConstants.GenreFichier.DOSSIER.equals(genre))
		    		return (T) new DossierVO();
		    	
		    	if (GlobalConstants.GenreFichier.SUJET.equals(genre))
		    		return (T) new SujetVO();

		    	if (GlobalConstants.GenreFichier.VEHICULE.equals(genre))
		    		return (T) new VehiculeVO();
		    	
		    	if (GlobalConstants.GenreFichier.SOCIETE.equals(genre))
		    		return (T) new SocieteVO();
		    	
		    	return null;
			}
			
			@SuppressWarnings("unchecked")
			private T nouvelleInstance(Class<T> t){
				
		    	if (cibleEntiteCardexClass.equals(Dossier.class))
		    		return (T) new DossierVO();
		    	
		    	if (cibleEntiteCardexClass.equals(Sujet.class))
		    		return (T) new SujetVO();

		    	if (cibleEntiteCardexClass.equals(Vehicule.class))
		    		return (T) new VehiculeVO();
		    	
		    	if (cibleEntiteCardexClass.equals(Societe.class))
		    		return (T) new SocieteVO();
		    	
		    	return null;
			}
		};
		
		storeProcTemplate.query(rcb);
		return entiteRetourMap;
    }
    
    // Genre du haut de la relation LDD
    private <T> String genreEntiteDO(EntiteCardex sourceEntiteCardex, Class<T> cibleEntiteCardexClass){

    	if (sourceEntiteCardex instanceof DossierVO || cibleEntiteCardexClass.equals(Dossier.class))
    		return GlobalConstants.GenreFichier.DOSSIER;
    	
    	if (sourceEntiteCardex instanceof SujetVO || cibleEntiteCardexClass.equals(Sujet.class))
    		return GlobalConstants.GenreFichier.SUJET;

    	if (sourceEntiteCardex instanceof VehiculeVO || cibleEntiteCardexClass.equals(Vehicule.class))
    		return GlobalConstants.GenreFichier.VEHICULE;
    	
    	if (sourceEntiteCardex instanceof SocieteVO || cibleEntiteCardexClass.equals(Societe.class))
    		return GlobalConstants.GenreFichier.SOCIETE;
    	
    	return "";
    }
    
    // Genre du bas de la relation LDD
    private <T> String genreEntiteLDD(EntiteCardex sourceEntiteCardex, Class<T> cibleEntiteCardexClass){

    	if (sourceEntiteCardex instanceof SocieteVO || cibleEntiteCardexClass.equals(Societe.class))
    		return GlobalConstants.GenreFichier.SOCIETE;

    	if (sourceEntiteCardex instanceof VehiculeVO || cibleEntiteCardexClass.equals(Vehicule.class))
    		return GlobalConstants.GenreFichier.VEHICULE;

    	if (sourceEntiteCardex instanceof SujetVO || cibleEntiteCardexClass.equals(Sujet.class))
    		return GlobalConstants.GenreFichier.SUJET;

    	if (sourceEntiteCardex instanceof DossierVO || cibleEntiteCardexClass.equals(Dossier.class))
    		return GlobalConstants.GenreFichier.DOSSIER;
    	
    	return "";	
    }
    
    public LiaisonEntiteVO obtenirLiaison(CardexAuthenticationSubject subject, final EntiteCardex entiteCardex1, final EntiteCardex entiteCardex2) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		final LiaisonEntiteVO liaisonEntiteVO = new LiaisonEntiteVO();
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
		    	
				if (isEntite1AvantEntite2(entiteCardex1,entiteCardex2)){
					callableStatement.setLong(1, entiteCardex1.getCle());
			        callableStatement.setLong(2, entiteCardex1.getSite());
			        callableStatement.setString(3, genreEntite(entiteCardex1));
					callableStatement.setLong(4, entiteCardex2.getCle());
			        callableStatement.setLong(5, entiteCardex2.getSite());
			        callableStatement.setString(6, genreEntite(entiteCardex2));
			        // Assignation de la source dans l'ordre
			        liaisonEntiteVO.setSourceEntite(entiteCardex1);
			        liaisonEntiteVO.setDestinationEntite(entiteCardex2);
		    	}else{
					callableStatement.setLong(1, entiteCardex2.getCle());
			        callableStatement.setLong(2, entiteCardex2.getSite());
			        callableStatement.setString(3, genreEntite(entiteCardex2));
					callableStatement.setLong(4, entiteCardex1.getCle());
			        callableStatement.setLong(5, entiteCardex1.getSite());
			        callableStatement.setString(6, genreEntite(entiteCardex1));
			        // Assignation de la source dans l'ordre			        
			        liaisonEntiteVO.setSourceEntite(entiteCardex2);
			        liaisonEntiteVO.setDestinationEntite(entiteCardex1);
		    	}
		        
				callableStatement.registerOutParameter(7, OracleTypes.CURSOR);
			}
		    
			private boolean isEntite1AvantEntite2(EntiteCardex entiteCardex1, EntiteCardex entiteCardex2) {
				return pointEntite(entiteCardex1) >= pointEntite(entiteCardex2);
			}
		    
			private int pointEntite(EntiteCardex entiteCardex){

		    	if (entiteCardex instanceof DossierVO)
		    		return 4;

		    	if (entiteCardex instanceof SujetVO)
		    		return 3;
		    	
		    	if (entiteCardex instanceof VehiculeVO)
		    		return 2;

		    	if (entiteCardex instanceof SocieteVO)
		    		return 1;
		    	
		    	return 0;	
		    }
			
			private String genreEntite(EntiteCardex entiteCardex){

		    	if (entiteCardex instanceof DossierVO)
		    		return GlobalConstants.GenreFichier.DOSSIER;

		    	if (entiteCardex instanceof SujetVO)
		    		return GlobalConstants.GenreFichier.SUJET;
		    	
		    	if (entiteCardex instanceof VehiculeVO)
		    		return GlobalConstants.GenreFichier.VEHICULE;

		    	if (entiteCardex instanceof SocieteVO)
		    		return GlobalConstants.GenreFichier.SOCIETE;
		    	
		    	return "";	
		    }
		};		
		
		storeProcTemplate.prepareCall("CARDEX_LIRE_DOC.SP_L_LIAISON", 7, 7, rch);
		
		RowCallbackHandler rcb = new RowCallbackHandler() {
			/**
			  TYPE REC_LIAISON IS RECORD(
			    L_DO_CLE                      LDD_LIEN_DOSSIER.L_LDD_CLE%TYPE,
			    L_SI_CLE                      LDD_LIEN_DOSSIER.L_SI_CLE%TYPE,
			    I_RO_CLE                      LDD_LIEN_DOSSIER.I_RO_CLE%TYPE)
			 */
			public void processRow(ResultSet rs) throws SQLException {
				liaisonEntiteVO.setCle(rs.getLong(1));
				liaisonEntiteVO.setSite(rs.getLong(2));
				liaisonEntiteVO.setRole(rs.getLong(3));
			}
		};
		
		storeProcTemplate.query(rcb);
		return liaisonEntiteVO;
    }
    
}
