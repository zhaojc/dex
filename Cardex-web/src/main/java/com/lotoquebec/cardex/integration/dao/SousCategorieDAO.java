package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;

/**
 * Liste des appels à la base de données pour différents accès aux dossiers. Les
 * sous-catégorie sont liés aux dossiers.
 */
public class SousCategorieDAO {

    /**
     * PROCEDURE Cardex_Web_Lire_Doc_Tri.SPW_L_LDC_LIEN_DOSSIER_CAT(
     * NEW_L_DO_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.L_DO_CLE%TYPE,
     * NEW_L_SI_CLE IN LDC_LIEN_DOSSIER_CATEGORIE.L_SI_CLE%TYPE,
     * rc1 OUT DTSW_LDC_LIEN_DOSSIER_CAT)
     */
	public SousCategoriesVO findLiensSousCategorie(CardexAuthenticationSubject subject, final long cle, final long site) throws DAOException {
		final SousCategoriesVO sousCategoriesVO = new SousCategoriesVO();
		
		sousCategoriesVO.setLien(cle);
		sousCategoriesVO.setLienSite(site);
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    	        callableStatement.setLong(1,cle);
    	        callableStatement.setLong(2,site);
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("Cardex_Web_Lire_Doc_Tri.SPW_L_LDC_LIEN_DOSSIER_CAT", 3, 3, rch);
		
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				SousCategorieVO sousCategorieVO = new SousCategorieVO();
				sousCategorieVO.setCleDossier(rs.getLong(1));
				sousCategorieVO.setSiteDossier(rs.getLong(2));
				sousCategorieVO.setCle(rs.getLong(3));
				sousCategorieVO.setCreerPar(rs.getString(4));
				sousCategorieVO.setDateCreation(rs.getTimestamp(5));
				sousCategorieVO.setDateApprobation(rs.getTimestamp(6));
				sousCategorieVO.setApprouvePar(rs.getString(7));
				sousCategoriesVO.addSousCategorie( sousCategorieVO );
			}
    	};		
		
		storeProcTemplate.query(rcbh, true);
		
		return sousCategoriesVO;
	}

	/**
	 * Efface 1 catégorie/sous-catégories du dossier.
	 * @param subject
	 * @param sousCategoriesVO
	 * @throws DAOException
	 */
	public void supprimer(CardexAuthenticationSubject subject, final SousCategorieVO sousCategorieVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1,"D");
                callableStatement.setLong(2, sousCategorieVO.getCleDossier());
                callableStatement.setLong(3, sousCategorieVO.getSiteDossier());
                callableStatement.setLong(4, sousCategorieVO.getCle());
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_LDC_LIEN_DOSSIER_CAT", 4, rch);
		storeProcTemplate.query(true);		
	}
	
	/**
	 * Ajouter 1 catégorie/sous-catégorie pour un dossier.
	 * @param subject
	 * @param cle
	 * @param site
	 * @param categorie
	 * @throws DAOException
	 */
	public void ajouter(CardexAuthenticationSubject subject, final SousCategorieVO sousCategorieVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1,"I");
    	        callableStatement.setLong(2, sousCategorieVO.getCleDossier());
    	        callableStatement.setLong(3, sousCategorieVO.getSiteDossier());
    	        callableStatement.setLong(4, sousCategorieVO.getCle());
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_LDC_LIEN_DOSSIER_CAT", 4, rch);
		storeProcTemplate.query(true);		
	}


	public void modifierApprouveLienSousCategorie(CardexAuthenticationSubject subject, final Dossier dossier, final boolean approuver) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    	        callableStatement.setLong(1, dossier.getCle());
    	        callableStatement.setLong(2, dossier.getSite());
    	        
    	        if (approuver)
    	        	callableStatement.setString(3, "o");
    	        else
    	        	callableStatement.setString(3, "n");
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_APPROUVE_LIEN_DOSSIER_CAT", 3, rch);
		storeProcTemplate.query(true);
	}	
	
}