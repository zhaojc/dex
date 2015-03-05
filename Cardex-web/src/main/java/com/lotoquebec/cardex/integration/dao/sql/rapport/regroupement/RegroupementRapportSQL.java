/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import java.sql.Timestamp;
import java.util.Calendar;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.HeureDebutSiteCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.CodeLangue;
import com.lotoquebec.cardexCommun.util.DateUtils;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * @author levassc
 */
public abstract class RegroupementRapportSQL {

	public final static String CLE_REGROUPEMENT = "CleRegroupement";
	public final static String NOM_REGROUPEMENT = "NomRegroupement";
	public final static String QUOTA = "Quota";
	public final static String QUOTA_MINIMUM = "QuotaMin";
	public final static String SOMME_HEURES = "SommeHeures";
	public final static String MOIS_LETTRES = "Mois_Lettres";
	public final static String MOIS_NOMBRE = "Mois_Nombre";
	public final static String GENRE = "Genre";
	public final static String NATURE = "Nature";
	public final static String TYPE = "Type";
	public final static String CATEGORIE = "Categorie";
	
	protected abstract String getExtraSelect();
	protected abstract String getExtraFrom();
	protected abstract String getExtraWhere(PreparerSQL preparerSQL, int codeLangue);
	

	/**
	 * Ici on déduit la date de début avec l'heure de début de quart di site.
	 * @param subject
	 * @param criteresRechercheRegroupement
	 * @return
	 */
	private Timestamp obtenirDateDu(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement){
		return assignerHeure(subject, criteresRechercheRegroupement.getSite(), criteresRechercheRegroupement.getDateDebutDu());
	}
	
	/**
	 * Avec la date Remphor, il faut ajouter 1 jours et mettre la date de fin du quart
	 * de travail.
	 * @param subject
	 * @param criteresRechercheRegroupement
	 * @return
	 */
	private Timestamp obtenirDateAu(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement){
		java.util.Date dateAu = criteresRechercheRegroupement.getDateDebutAu();
		dateAu = DateUtils.ajoutJour(dateAu, 1);
		return assignerHeure(subject, criteresRechercheRegroupement.getSite(), dateAu);
	}	

	private Timestamp assignerHeure(CardexAuthenticationSubject subject, long site, java.util.Date dateCreation){
		Calendar dateCreationCal = Calendar.getInstance();
		ListeCache listeCache = ListeCache.getInstance();
		dateCreationCal.setTime(dateCreation);
		dateCreationCal.set(Calendar.HOUR_OF_DAY, 0);
		dateCreationCal.set(Calendar.MINUTE, 0);
		
		try {
			String strHeureMin = listeCache.obtenirLabel(subject, site, new HeureDebutSiteCleMultiListeCache(subject));
			int iSeparateur = 0;
			if(StringUtils.isNotEmpty(strHeureMin)){
				iSeparateur = strHeureMin.indexOf(":");
			}
			
			if (iSeparateur > 0){
				dateCreationCal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(strHeureMin.substring(0, iSeparateur)));
				dateCreationCal.set(Calendar.MINUTE, Integer.valueOf(strHeureMin.substring(iSeparateur+1)));
			}
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		return new java.sql.Timestamp(dateCreationCal.getTimeInMillis());
	}	
	
	public PreparerSQL obtenirSQL(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement){
		PreparerSQL preparerSQL = new PreparerSQL();
		int codeLangue = CodeLangue.valueOf(subject.getLocale()).intValue();
		
		StringBuilder SQL = new StringBuilder();
		SQL.append("select reg.i_rg_cle as "+CLE_REGROUPEMENT+", tr.v_tr_description as "+NOM_REGROUPEMENT+", reg.i_rg_quota as "+QUOTA+", reg.i_rg_quota_min as "+QUOTA_MINIMUM+" "+getExtraSelect()+", sum(com.v_co_temps) as "+SOMME_HEURES+" ");
		SQL.append("from co_commentaire2 com, do_dossier do, lrg_lien_regroupement lien, rg_regroupement reg, si_site si, "); 
		SQL.append("ca_categorie ca, ty_type ty, na_nature na, tr_traduction tr, in_intervenant int "+getExtraFrom());
		SQL.append("where com.l_co_reference = do.l_do_cle ");
		SQL.append("and com.l_co_ref_site = do.l_si_cle ");
		SQL.append("and com.c_co_ref_genre = 'DO' ");
		SQL.append("and do.i_ca_cle = lien.i_ca_cle ");
		SQL.append("and lien.i_rg_cle = reg.i_rg_cle ");
		SQL.append("and do.l_si_cle = si.l_si_cle ");
		SQL.append("and do.i_ca_cle = ca.i_ca_cle ");
		SQL.append("and ca.i_ty_cle = ty.i_ty_cle ");
		SQL.append("and ty.i_na_cle = na.i_na_cle ");
		SQL.append("and com.v_co_temps is not null ");
		SQL.append("and reg.i_rg_cle = tr.l_tr_cle ");
		SQL.append("and do.i_cc_cle != 14920 "); // Confidentialité 8 => retrait
		SQL.append("and com.v_co_cree_par = int.name ");
		SQL.append("and int.i_st_cle = 532 "); // Utilisateur Actif
		
		// Langue
		SQL.append("and tr.i_la_cle = ? ");
		preparerSQL.addParametre(codeLangue);
		
		 // Secteur
		if (criteresRechercheRegroupement.getSecteur() != 0){
			SQL.append("and int.L_IN_SECTEUR = ? ");
			SQL.append("and reg.L_IN_SECTEUR = ? ");
			preparerSQL.addParametre(criteresRechercheRegroupement.getSecteur());
			preparerSQL.addParametre(criteresRechercheRegroupement.getSecteur());
		}
		if (criteresRechercheRegroupement.isTousLesCasinos()){
			SQL.append("and com.l_si_cle in ("+GlobalConstants.SiteMaisonJeux.CHARLEVOIX+","+GlobalConstants.SiteMaisonJeux.LAC_LEAMY+","+GlobalConstants.SiteMaisonJeux.MONT_TREMBLANT+","+GlobalConstants.SiteMaisonJeux.MONTREAL+") ");
		}else{
			OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "com.l_si_cle", criteresRechercheRegroupement.getSite());
		}	
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "si.i_en_cle", criteresRechercheRegroupement.getEntite());
		// Genre
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "na.i_ge_cle", criteresRechercheRegroupement.getGenre());
		// Nature
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "na.i_na_cle", criteresRechercheRegroupement.getNature());
		// Type
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "ty.i_ty_cle", criteresRechercheRegroupement.getType());
		// Categorie
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "ca.i_ca_cle", criteresRechercheRegroupement.getCategorie());
		// Regroupement
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "reg.i_rg_cle", criteresRechercheRegroupement.getRegroupement());
		// Intervenant
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "com.v_co_cree_par", criteresRechercheRegroupement.getIntervenant());
		// Reference1
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "do.i_or_cle", criteresRechercheRegroupement.getEndroit());

		OracleDAOUtils.ajouterChampSQLHeure(preparerSQL, SQL, "com.d_co_date_creation", ">=", obtenirDateDu(subject, criteresRechercheRegroupement));
		
		OracleDAOUtils.ajouterChampSQLHeure(preparerSQL, SQL, "com.d_co_date_creation", "<", obtenirDateAu(subject, criteresRechercheRegroupement));
        
		SQL.append(getExtraWhere(preparerSQL, codeLangue));
		
		SQL.append("group by "+getGroupeBy());

		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
	protected String getGroupeBy(){
		return "reg.i_rg_cle, tr.v_tr_description, reg.i_rg_quota, reg.i_rg_quota_min ";
	}

	public PreparerSQL obtenirSQLMatrice(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement){
		PreparerSQL preparerSQL = new PreparerSQL();
		int codeLangue = CodeLangue.valueOf(subject.getLocale()).intValue();
		
		StringBuilder SQL = new StringBuilder();
		SQL.append("select TR1.V_TR_DESCRIPTION as "+GENRE+", TR2.V_TR_DESCRIPTION as "+NATURE+", TR3.V_TR_DESCRIPTION as "+TYPE+", TR4.V_TR_DESCRIPTION as "+CATEGORIE+", trrg.v_tr_description as "+NOM_REGROUPEMENT+" ");
		SQL.append("FROM GE_GENRE GE, NA_NATURE NA, TY_TYPE TY, CA_CATEGORIE CA, TR_TRADUCTION TR1, TR_TRADUCTION TR2, "); 
		SQL.append("TR_TRADUCTION TR3, TR_TRADUCTION TR4, tr_traduction trrg, lrg_lien_regroupement lrg, rg_regroupement rg ");
		SQL.append("where ( GE.I_GE_CLE=NA.I_GE_CLE ) AND ( GE.I_GE_CLE=TR1.L_TR_CLE ) ");
		SQL.append("AND ( NA.I_NA_CLE=TY.I_NA_CLE ) AND ( NA.I_NA_CLE=TR2.L_TR_CLE ) AND ( TY.I_TY_CLE=CA.I_TY_CLE ) ");
		SQL.append("AND ( TY.I_TY_CLE=TR3.L_TR_CLE ) AND ( CA.I_CA_CLE=TR4.L_TR_CLE ) ");
		SQL.append("and ca.i_ca_cle = lrg.i_ca_cle ");
		SQL.append("and lrg.i_rg_cle = trrg.l_tr_cle ");
		SQL.append("and lrg.i_rg_cle = rg.i_rg_cle ");
		
		// Langue
		SQL.append(" AND TR1.I_LA_CLE=? ");
		SQL.append(" AND TR2.I_LA_CLE=? ");
		SQL.append(" AND TR3.I_LA_CLE=? ");
		SQL.append(" AND TR4.I_LA_CLE=? ");
		SQL.append(" AND TRRG.I_LA_CLE=? ");
		preparerSQL.addParametre(codeLangue);
		preparerSQL.addParametre(codeLangue);
		preparerSQL.addParametre(codeLangue);
		preparerSQL.addParametre(codeLangue);
		preparerSQL.addParametre(codeLangue);
		
		 // Secteur
		OracleDAOUtils.ajouterChampSQL(preparerSQL, SQL, "rg.L_IN_SECTEUR", criteresRechercheRegroupement.getSecteur());
		
		//Order by
		SQL.append("order by convert(TR1.V_TR_DESCRIPTION,'US7ASCII'), TR2.V_TR_DESCRIPTION, TR3.V_TR_DESCRIPTION, TR4.V_TR_DESCRIPTION, trrg.v_tr_description");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
}
