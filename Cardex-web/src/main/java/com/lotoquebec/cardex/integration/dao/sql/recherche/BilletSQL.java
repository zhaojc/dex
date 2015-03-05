/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.vo.CriteresRechercheBilletVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public abstract class BilletSQL extends ConstruireRechercheSQL{

	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheBilletVO criteresRechercheBilletVO = (CriteresRechercheBilletVO) criteresRecherche;
		String numeroDetaillantProvenance = criteresRechercheBilletVO.getNumeroDetaillantProvenance();
		String numeroDetaillantValidation = criteresRechercheBilletVO.getNumeroDetaillantValidation();
		String numeroDetaillantVerification = criteresRechercheBilletVO.getNumeroDetaillantVerification();
		String numeroDetaillantFautif = criteresRechercheBilletVO.getNumeroDetaillantFautif();
		
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();
        
        query.append("SELECT ");
        
        query.append( selectArgument() );
		
        query.append(" FROM BI_BILLET BI, V_DO_DOSSIER_CA_TY DO ");
        
    	if (StringUtils.isNotEmpty(numeroDetaillantProvenance))
    		query.append(", SO_SOCIETE SOProvenance ");
        
    	if (StringUtils.isNotEmpty(numeroDetaillantValidation))
    		query.append(", SO_SOCIETE SOValidation ");

    	if (StringUtils.isNotEmpty(numeroDetaillantVerification))
    		query.append(", SO_SOCIETE SOVerification ");

    	if (StringUtils.isNotEmpty(numeroDetaillantFautif))
    		query.append(", SO_SOCIETE SOFautif ");

        query.append(" WHERE BI.L_BI_REF_CLE = DO.L_DO_CLE ");
        query.append(" AND BI.L_BI_REF_SITE = DO.L_SI_CLE ");
        query.append(" AND BI.C_BI_REF_GENRE = 'DO' ");
        
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "BI.V_BI_NOM", criteresRechercheBilletVO.getNom());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "BI.V_BI_NUMERO_CONTROLE", criteresRechercheBilletVO.getNumeroControl());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.L_BI_VALEUR", criteresRechercheBilletVO.getValeur());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.L_TYPE_MISE", criteresRechercheBilletVO.getTypeMise());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.L_BI_MONTANT_LOT", criteresRechercheBilletVO.getMontantLot());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.L_JE_CLE", criteresRechercheBilletVO.getTypeLoterie());
		
    	if (StringUtils.isNotEmpty(numeroDetaillantProvenance)){
    		query.append(" AND BI.L_SO_CLE_PROVENANCE = SOProvenance.l_So_Cle ");
    		query.append(" AND BI.L_SO_SITE_PROVENANCE = SOProvenance.l_Si_Cle ");
    		query.append(" AND SOProvenance.v_So_Reference_3 = ? ");
    		preparerSQL.addParametre(numeroDetaillantProvenance);
    	}
    	
    	if (StringUtils.isNotEmpty(numeroDetaillantValidation)){
    		query.append(" AND BI.L_SO_CLE_VALIDATION = SOValidation.l_So_Cle ");
    		query.append(" AND BI.L_SO_SITE_VALIDATION = SOValidation.l_Si_Cle ");
    		query.append(" AND SOValidation.v_So_Reference_3 = ? ");
    		preparerSQL.addParametre(numeroDetaillantValidation);
    	}
    	
    	if (StringUtils.isNotEmpty(numeroDetaillantVerification)){
    		query.append(" AND BI.L_SO_CLE_VERIFICATION = SOVerification.l_So_Cle ");
    		query.append(" AND BI.L_SO_SITE_VALIDATION = SOVerification.l_Si_Cle ");
    		query.append(" AND SOVerification.v_So_Reference_3 = ? ");
    		preparerSQL.addParametre(numeroDetaillantVerification);
    	}
    	
    	if (StringUtils.isNotEmpty(numeroDetaillantFautif)){
    		query.append(" AND BI.L_SO_CLE_FAUTIF = SOFautif.l_So_Cle ");
    		query.append(" AND BI.L_SO_SITE_FAUTIF = SOFautif.l_Si_Cle ");
    		query.append(" AND SOFautif.v_So_Reference_3 = ? ");
    		preparerSQL.addParametre(numeroDetaillantFautif);
    	}
    	OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.D_BI_DATE_PAIEMENT", "=", criteresRechercheBilletVO.getDatePaiement());
    	OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.D_BI_DATE_CREATION", ">=", criteresRechercheBilletVO.getDateDebutCreation());
    	OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "BI.D_BI_DATE_CREATION", "<", criteresRechercheBilletVO.getDateFinCreation());
        if(criteresRechercheBilletVO.getDateFinCreation()!= null){
        	query.append(" + 1 ");
        }
    	
		//On vérifie si le dossier est partagé et si l'utilisateur a le droit d'accéder au dossier.
		query.append(" AND (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD WHERE LPD.L_LPD_REFERENCE = DO.L_DO_CLE AND LPD.L_LPD_REF_SITE = DO.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");
		query.append(" AND DO.V_DO_MOT_PASSE IS NULL) ");
        
        preparerSQL.setSQL(query.toString());
        
        return preparerSQL;        
	}
	

}
