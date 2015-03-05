/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public abstract class AdresseSQL extends ConstruireRechercheSQL{

	protected StringBuilder select = new StringBuilder("");
	protected StringBuilder from = new StringBuilder("");
	protected StringBuilder where = new StringBuilder("");
	
	public void assignerBaseSQL(PreparerSQL preparerSQL, CriteresRechercheAdresses criteresRechercheAdresses){
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "si.i_en_cle", criteresRechercheAdresses.getEntite());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "si.l_si_cle", criteresRechercheAdresses.getSiteOrigine());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "ad.v_ad_num_municipal", criteresRechercheAdresses.getNumeroMunicipal());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "ad.l_ad_type_rue", criteresRechercheAdresses.getTypeRue());
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, where, "upper(ad.v_ad_nom_rue)", criteresRechercheAdresses.getNomRue());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "ad.l_ad_point_cardinal", criteresRechercheAdresses.getPointCardinal());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "ad.l_ad_type_unite", criteresRechercheAdresses.getUnite());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "ad.v_ad_no_unite", criteresRechercheAdresses.getNumeroUnite());
		OracleDAOUtils.ajouterChampConvertSQL(preparerSQL, where, "ad.v_ad_adresse_post", criteresRechercheAdresses.getAdressePostal());
		
		if (StringUtils.isNotEmpty( criteresRechercheAdresses.getTelephone() )){
		    preparerSQL.getSQL().append(" and ( ");
            OracleDAOUtils.ajouterChampSQLConvertChaineAsChaineNumerique(preparerSQL,"ad.v_ad_telephone_1",criteresRechercheAdresses.getTelephone());
            preparerSQL.getSQL().append(" or ");
            OracleDAOUtils.ajouterChampSQLConvertChaineAsChaineNumerique(preparerSQL,"ad.v_ad_telephone_2",criteresRechercheAdresses.getTelephone());
            preparerSQL.getSQL().append(" or ");
            OracleDAOUtils.ajouterChampSQLConvertChaineAsChaineNumerique(preparerSQL,"ad.v_ad_telephone_3",criteresRechercheAdresses.getTelephone());
            preparerSQL.getSQL().append(" ) ");
            where.append(preparerSQL);
		}

		if (criteresRechercheAdresses.getVille() != 0){
			OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "ad.l_vi_cle", criteresRechercheAdresses.getVille());
			
		}else{
			if (criteresRechercheAdresses.getProvince() != 0){
				from.append(", vi_ville vi ");
				where.append("and vi.l_vi_cle(+) = ad.l_vi_cle ");
				OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "vi.l_pr_cle(+)", criteresRechercheAdresses.getProvince());
				
			}else if (criteresRechercheAdresses.getPays() != 0){
				from.append(", vi_ville vi, pr_province pr ");
				where.append("and vi.l_vi_cle = ad.l_vi_cle ");
				where.append("and pr.l_pr_cle = vi.l_pr_cle ");
				OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "pr.i_pa_cle", criteresRechercheAdresses.getPays());
			}
		}

        OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "AD.D_AD_DATE_CREATION", ">=", criteresRechercheAdresses.getDateCreationDu());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, where, "AD.D_AD_DATE_CREATION", "<", criteresRechercheAdresses.getDateCreationAu());
        if(criteresRechercheAdresses.getDateCreationAu() != null){
            where.append(" + 1 ");
        }

        if (StringUtils.isNotEmpty( criteresRechercheAdresses.getCodePostal() )){
			String codePostalSansEspace = StringUtils.deleteWhitespace(criteresRechercheAdresses.getCodePostal());
			where.append("and ( "+OracleDAOUtils.champRecherche(preparerSQL, "ad.v_ad_code_postal", criteresRechercheAdresses.getCodePostal()));
			
			if (codePostalSansEspace.length() == 6)
				where.append("or "+OracleDAOUtils.champRecherche(preparerSQL, "ad.v_ad_code_postal", codePostalFormat(codePostalSansEspace)));
			where.append("or "+OracleDAOUtils.champRecherche(preparerSQL, "ad.v_ad_code_postal", codePostalSansEspace)+" ) ");
		}
		
		if (StringUtils.isNotEmpty( criteresRechercheAdresses.getAdresseElectronique() )){
			where.append("and ("+OracleDAOUtils.champRecherche(preparerSQL, "ad.v_ad_electronique_1", criteresRechercheAdresses.getAdresseElectronique()));
			where.append(" or "+OracleDAOUtils.champRecherche(preparerSQL, "ad.v_ad_electronique_2", criteresRechercheAdresses.getAdresseElectronique()) + " ) ");
		}
	}
	
	
	private String codePostalFormat(String codePostal){
		
		if (StringUtils.isNotEmpty( codePostal ) && codePostal.length() == 6){
			return StringUtils.left(codePostal, 3)+" "+StringUtils.right(codePostal, 3);
		}
		return "";
	}
	
	protected String formerSQL(){
		return select + " " + from + " " + where;
	}
}
