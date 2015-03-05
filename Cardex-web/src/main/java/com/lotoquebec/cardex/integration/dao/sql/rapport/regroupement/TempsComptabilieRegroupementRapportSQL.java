/*
 * Created on 10-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */
public class TempsComptabilieRegroupementRapportSQL {

	public final static String TOTAL_TEMPS_PRODUCTIF = "totalTempsProductif";
	private final static String CASINO_MONTEAL = "D";
	private final static String CASINO_CHARLEVOIX = "F";
	private final static String CASINO_LAC_LEAMY = "H";
	private final static String CASINO_MONT_TREMBLANT = "N";
	
	
	/*
	 * TEMP_CODE => Code du titre d'emploi de l'employé (ex. : 509, 2952, 4170, etc.)
	 * CODE_PAIE_DESC => Code de paie utilisé à l’horaire (ex. : REG01, VAC10, MAL30, FER15, etc.)
	 * QUART => Quart de travail attribué par les opérations (ex. : JOUR, SOIR, etc.)
	 * PRIME_1_CODE => Code de prime 1 (ex. : 25-Prime de disponibilité, 26-Prime de nuit, 80-Chef d’équipe, etc.)
	 */
	public PreparerSQL obtenirSQL(RegroupementRapportVO criteresRechercheRegroupement){
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("SELECT "+select()+" sum(RTS.HR_TOTAL_CENTIEME*65/100) as "+TOTAL_TEMPS_PRODUCTIF+" ");
		SQL.append("FROM CMPTR.RAPPORT_TECHNICIEN_SURV_HOR RTS ");
		SQL.append("WHERE ");

		/*
		469 	Technicien 
		612 	Tech. Junior
		4189	Technicien Lac-Leamy
		4190 	Tech. Junior Lac-Leamy
		4458	Salons de jeux
		4518    Tech. défibrilateur Charlevoix
		4532   Tech. Junior
		5158   Technicien		 */
		
		SQL.append("RTS.TEMP_CODE IN (469,4189,612,4190,4458,4518,4532,5158) "); 
		SQL.append("and RTS.CODE_PAIE_DESC IN ('REG01','TS1,0','TS1,5','COM87','COM01') AND (QUART <> 'E999' and QUART <> 'E888')");		
		
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "RTS.DATE_COUR", ">=", criteresRechercheRegroupement.getDateDebutDu());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "RTS.DATE_COUR", "<=", criteresRechercheRegroupement.getDateDebutAu());

        if (criteresRechercheRegroupement.isTousLesCasinos()){
			SQL.append("and RTS.DIRECODE in ('"+CASINO_MONTEAL+"','"+CASINO_CHARLEVOIX+"','"+CASINO_LAC_LEAMY+"','"+CASINO_MONT_TREMBLANT+"') ");				
		}else{
			String site = String.valueOf( criteresRechercheRegroupement.getSite() );
			
			if (GlobalConstants.SiteMaisonJeux.MONTREAL.equals( site ))
				SQL.append("and RTS.DIRECODE = '"+CASINO_MONTEAL+"'");
			
			else if (GlobalConstants.SiteMaisonJeux.CHARLEVOIX.equals( site ))
				SQL.append("and RTS.DIRECODE = '"+CASINO_CHARLEVOIX+"'");
			
			else if (GlobalConstants.SiteMaisonJeux.LAC_LEAMY.equals( site ))
				SQL.append("and RTS.DIRECODE = '"+CASINO_LAC_LEAMY+"'");
			
			else if (GlobalConstants.SiteMaisonJeux.MONT_TREMBLANT.equals( site ))
				SQL.append("and RTS.DIRECODE = '"+CASINO_MONT_TREMBLANT+"'");	
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "RTS.MATRI", criteresRechercheRegroupement.getMatricule());
		
		SQL.append(" ");
		SQL.append(groupBy());
		
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
	protected String select(){
		return "";
	}
	
	protected String groupBy(){
		return "";
	}

}
