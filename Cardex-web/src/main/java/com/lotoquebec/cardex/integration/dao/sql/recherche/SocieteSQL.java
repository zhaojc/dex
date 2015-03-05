package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheSociete;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class SocieteSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheSociete criteresRechercheSociete = (CriteresRechercheSociete) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        StringBuilder query = new StringBuilder();
  
        query.append("SELECT ");

        query.append( selectArgument() );
        
        query.append("FROM SO_SOCIETE S, SI_SITE SI, VEXI_CDX_DDS_CENTRE_REGION CR, VEXI_CDX_DDS_CODE_COMPTE CC, VEXI_CDX_DDS_DISTRICT D ");
        
        if (criteresRechercheSociete.getVille() != 0 || criteresRechercheSociete.getProvince() != 0 || criteresRechercheSociete.getPays() != 0){
             query.append(", AD_ADRESSE A ");
        }
        if (criteresRechercheSociete.getVille() == 0 && (criteresRechercheSociete.getProvince() != 0 || criteresRechercheSociete.getPays() != 0)){
        	query.append(", PR_PROVINCE P, VI_VILLE V ");
        }
        if ((!StringUtils.isEmpty(criteresRechercheSociete.getNomSujet())) || (!StringUtils.isEmpty(criteresRechercheSociete.getPrenomSujet())) || (criteresRechercheSociete.getRole() != 0)){
         	query.append(", LDD_LIEN_DOSSIER D ");
         }
        if ((!StringUtils.isEmpty(criteresRechercheSociete.getNomSujet())) || (!StringUtils.isEmpty(criteresRechercheSociete.getPrenomSujet()))){
       	 query.append(", SU_SUJET SU ");
        }
        if (criteresRechercheSociete.getConfidentialite() != 0){
            query.append(" WHERE S.I_CC_CLE = ? AND ? >= ? ");
			 preparerSQL.addParametre(criteresRechercheSociete.getConfidentialite());
			 preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			 preparerSQL.addParametre(criteresRechercheSociete.getConfidentialite());            
            
        }else{
        	query.append(" WHERE (S.I_CC_CLE <= ? OR S.V_SO_CREE_PAR = ?)" );
			preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			preparerSQL.addParametre(user.getCode());            
        }
        query.append("and s.l_si_cle = si.l_si_cle ");
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "S.V_SO_REFERENCE_1", criteresRechercheSociete.getReference1());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "upper(S.V_SO_REFERENCE_2)", criteresRechercheSociete.getReference2().toUpperCase());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "S.V_SO_REFERENCE_3", criteresRechercheSociete.getNumeroFiche());
        
        if (StringUtils.isNotEmpty(criteresRechercheSociete.getNom())){
             OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "UPPER(S.V_SO_NOM)", criteresRechercheSociete.getNom());
        }else{
            if (StringUtils.isNotEmpty(criteresRechercheSociete.getNomPhonetique())){
                query.append(" AND (S.C_SO_SNDX_NOM = soundex(?)" );
                preparerSQL.addParametre(criteresRechercheSociete.getNomPhonetique());
                query.append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.V_SO_NOM", criteresRechercheSociete.getNomPhonetique().toUpperCase()) + " ) " );
            }
        }
 
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "upper(S.V_SO_RAISON_SOCIALE)", criteresRechercheSociete.getRaisonEtre());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "S.I_CL_CLE", criteresRechercheSociete.getClasse());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "S.I_ST_CLE", criteresRechercheSociete.getStatut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "S.I_SE_CLE", criteresRechercheSociete.getSeverite());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "S.I_SE_CLE_CASINO", criteresRechercheSociete.getSeveriteCasino());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "S.I_LS_CLE", criteresRechercheSociete.getLangue());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "si.i_en_cle", criteresRechercheSociete.getEntite());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "s.l_si_cle", criteresRechercheSociete.getSiteOrigine());
        
        if (criteresRechercheSociete.getRole() != 0){
            query.append(" AND (((D.L_LDD_DOSSIER_ASSOCIE = S.L_SO_CLE AND D.L_LDD_SITE = S.L_SI_CLE) " );
            query.append(" OR (D.L_DO_CLE = S.L_SO_CLE AND D.L_DO_SITE = S.L_SI_CLE)) AND D.I_RO_CLE = ?) " );
            preparerSQL.addParametre(criteresRechercheSociete.getRole());
        }
        if (StringUtils.isNotEmpty(criteresRechercheSociete.getNomSujet()) || !StringUtils.isEmpty(criteresRechercheSociete.getPrenomSujet())){
          	query.append(" AND ((D.L_LDD_DOSSIER_ASSOCIE = S.L_SO_CLE AND D.L_LDD_SITE = S.L_SI_CLE ");
          	query.append(" AND D.L_DO_CLE = SU.L_SU_CLE AND D.L_DO_SITE = SU.L_SI_CLE) ");
          	query.append(" OR (D.L_LDD_DOSSIER_ASSOCIE = SU.L_SU_CLE AND D.L_LDD_SITE = SU.L_SI_CLE ");
          	query.append(" AND D.L_DO_CLE = S.L_SO_CLE AND D.L_DO_SITE = S.L_SI_CLE)) ");
          	
          	if (StringUtils.isNotEmpty(criteresRechercheSociete.getNomSujet())){
          		query.append(" AND ");
          		query.append(OracleDAOUtils.champRecherche(preparerSQL, "upper(SU.V_SU_NOM)", criteresRechercheSociete.getNomSujet()));
          	}
          	
          	if (StringUtils.isNotEmpty(criteresRechercheSociete.getPrenomSujet())){
          		query.append(" AND ");
          		query.append(OracleDAOUtils.champRecherche(preparerSQL, "upper(SU.V_SU_PRENOM)", criteresRechercheSociete.getPrenomSujet()));
          	}
        }
        if (criteresRechercheSociete.getVille() != 0 || criteresRechercheSociete.getProvince() != 0 || criteresRechercheSociete.getPays() != 0){
            query.append(" AND A.L_AD_REFERENCE = S.L_SO_CLE AND A.L_AD_REF_SITE = S.L_SI_CLE " );
            query.append(" AND A.C_AD_REF_GENRE = 'SO' ");
        }
        if (criteresRechercheSociete.getVille() != 0){
            query.append(" AND A.L_VI_CLE = ?" );
            preparerSQL.addParametre(criteresRechercheSociete.getVille());
        }else{
	         if (criteresRechercheSociete.getProvince() != 0){
	            query.append(" AND P.L_PR_CLE = ? ");
	            query.append(" AND P.L_PR_CLE = V.L_PR_CLE " );
	            query.append(" AND V.L_VI_CLE = A.L_VI_CLE " );
	            preparerSQL.addParametre(criteresRechercheSociete.getProvince());
 	         }else{
		         if (criteresRechercheSociete.getPays() != 0){
 		           query.append(" AND P.I_PA_CLE = ? ");
	               query.append(" AND P.L_PR_CLE = V.L_PR_CLE " );
	               query.append(" AND V.L_VI_CLE = A.L_VI_CLE " );
	               preparerSQL.addParametre(criteresRechercheSociete.getPays());
		         }
	         }
        }
        query.append(" AND S.L_SO_CENTRE_REGIONAL = CR.NO_CENTREREG(+) " );
        query.append(" AND S.V_SO_CODE_COMPTE = CC.CODE_CPTE_COC(+) " );
        query.append(" AND S.V_SO_DISTRICT = D.NO_DISTRICT(+) " );
        //Ordre de tri
        if (StringUtils.isEmpty(criteresRechercheSociete.getOrdreTriRecherche())){
             query.append(" order by convert(upper(V_SO_NOM), 'US7ASCII') asc");
         }else{
             query.append(" order by ?");
             preparerSQL.addParametre(criteresRechercheSociete.getOrdreTriRecherche());
             //Ascendant ou descendant
             if (criteresRechercheSociete.isOrdreCroissantRecherche()){
                 query.append(" asc");
             }else{
                 query.append(" desc");
             }
         }

		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}
		
}
