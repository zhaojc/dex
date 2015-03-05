/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

/**
 * @author levassc
 */
public abstract class AdresseSujetSQL extends AdresseSQL{

	public final static String CLE_SUJET = "cleSociete";
	public final static String SITE_SUJET = "siteSociete";
	public final static String NUMERO_FICHE = "numeroFiche";
	public final static String SEVERITE = "severite";
	public final static String NOM = "nom";
	public final static String PRENOM = "prenom";
	public final static String ALIAS = "alias";
	public final static String SEXE = "sexe";
	public final static String ETHNIE = "ethnie";
	public final static String RACE = "race";
	public final static String LANGUE = "langue";
	public final static String DATE_NAISSANCE = "dateNaissance";
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheAdresses criteresRechercheAdresses = (CriteresRechercheAdresses) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        
        select.append("select ");
        select.append(selectArgument());
        
		from.append("from ad_adresse ad, su_sujet su, si_site si ");
		where.append("where ad.l_ad_reference = su.l_su_cle ");
		where.append("and ad.c_ad_ref_genre = 'SU' ");
		where.append("and si.l_si_cle = su.l_si_cle ");
		where.append("and ad.l_ad_ref_site = su.l_si_cle ");
		
		//On filtre la liste selon le niveau de confidentialité et l'entité
		where.append("AND su.i_cc_cle <= ? ");
		preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		
		super.assignerBaseSQL(preparerSQL, criteresRechercheAdresses);
		
        preparerSQL.setSQL(formerSQL());
        
        return preparerSQL;		
	}
}
