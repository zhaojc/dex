package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheVehicule;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class VehiculeSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheVehicule criteresRechercheVehicule = (CriteresRechercheVehicule) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        StringBuilder query = new StringBuilder();
  
        query.append("SELECT ");

        query.append( selectArgument() );
        
        query.append(" FROM VE_VEHICULE VE, MD_MODELE MD, si_site si ");
        if (criteresRechercheVehicule.getParticularite1() != 0 ){
            query.append(", LPV_LIEN_PARTICULARITE LPV1 ");
        }
        if (criteresRechercheVehicule.getParticularite2() != 0 ){
            query.append(", LPV_LIEN_PARTICULARITE LPV2 ");
        }
        if (criteresRechercheVehicule.getParticularite3() != 0 ){
            query.append(", LPV_LIEN_PARTICULARITE LPV3 ");
        }
        if (criteresRechercheVehicule.getParticularite4() != 0 ){
            query.append(", LPV_LIEN_PARTICULARITE LPV4 ");
        }
        if (criteresRechercheVehicule.getParticularite5() != 0 ){
            query.append(", LPV_LIEN_PARTICULARITE LPV5 ");
        }
        
        if (criteresRechercheVehicule.getConfidentialite() != 0 ) {
            query.append(" WHERE VE.I_CC_CLE = ? AND ? >= ? ");
			 preparerSQL.addParametre(criteresRechercheVehicule.getConfidentialite());
			 preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			 preparerSQL.addParametre(criteresRechercheVehicule.getConfidentialite());            
        }else{
        	query.append(" WHERE (VE.I_CC_CLE <= ? OR VE.V_VE_CREE_PAR = ?)" );
			preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			preparerSQL.addParametre(user.getCode());            
        }
        query.append(" AND ve.l_si_cle = si.l_si_cle ");
        query.append(" AND MD.I_MD_CLE (+) = VE.I_MD_CLE ");
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "VE.V_VE_IMMATRICULATION", criteresRechercheVehicule.getImmatriculation());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "VE.L_PR_CLE", criteresRechercheVehicule.getCleProvince());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "VE.I_MD_CLE", criteresRechercheVehicule.getModele());
        
        if (StringUtils.isNotEmpty(criteresRechercheVehicule.getNumeroFiche())){
            query.append("and VE.L_VE_CLE||VE.L_SI_CLE = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getNumeroFiche());
        }
        if (criteresRechercheVehicule.getParticularite1() != 0 ){
            query.append("and VE.L_VE_CLE = LPV1.L_VE_CLE and VE.l_si_cle = LPV1.L_LPV_REF_SITE and LPV1.I_PT_CLE = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getParticularite1());
        }
        if (criteresRechercheVehicule.getParticularite2() != 0 ){
            query.append("and VE.L_VE_CLE = LPV2.L_VE_CLE and VE.l_si_cle = LPV2.L_LPV_REF_SITE and LPV2.I_PT_CLE = ?" );
            preparerSQL.addParametre(criteresRechercheVehicule.getParticularite2());
        }
        if (criteresRechercheVehicule.getParticularite3() != 0 ){
            query.append("and VE.L_VE_CLE = LPV3.L_VE_CLE and VE.l_si_cle = LPV3.L_LPV_REF_SITE and LPV3.I_PT_CLE = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getParticularite3());
        }
        if (criteresRechercheVehicule.getParticularite4() != 0 ){
            query.append("and VE.L_VE_CLE = LPV4.L_VE_CLE and VE.l_si_cle = LPV4.L_LPV_REF_SITE and LPV4.I_PT_CLE = ?" );
            preparerSQL.addParametre(criteresRechercheVehicule.getParticularite4());
        }
        if (criteresRechercheVehicule.getParticularite5() != 0 ){
            query.append("and VE.L_VE_CLE = LPV5.L_VE_CLE and VE.l_si_cle = LPV5.L_LPV_REF_SITE and LPV5.I_PT_CLE = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getParticularite5());
        }
        if ( criteresRechercheVehicule.getMarque() != 0 && criteresRechercheVehicule.getModele() == 0){
            query.append(" AND MD.I_MA_CLE = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getMarque());
        }
        if (criteresRechercheVehicule.getEntite() != 0){
            query.append(" and ve.l_si_cle = si.l_si_cle and si.i_en_cle = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getEntite());
        }
        if (criteresRechercheVehicule.getSiteOrigine() != 0){
            query.append(" and si.l_si_cle = ?");
            preparerSQL.addParametre(criteresRechercheVehicule.getSiteOrigine());
        }
        //Ordre de tri
        if (StringUtils.isEmpty(criteresRechercheVehicule.getOrdreTriRecherche())){
             query.append(" order by ve.l_ve_cle desc, ve.l_si_cle asc");
         }else{
             query.append(" order by ?");
             preparerSQL.addParametre(criteresRechercheVehicule.getOrdreTriRecherche());
             //Ascendant ou descendant
             if (criteresRechercheVehicule.isOrdreCroissantRecherche()){
                 query.append(" asc");
             }else{
                 query.append(" desc");
             }
        }

		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}
		
}
