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
public abstract class AdresseSocieteSQL extends AdresseSQL{

	public final static String CLE_SOCIETE = "cleSociete";
	public final static String SITE_SOCIETE = "siteSociete";
	public final static String NUMERO_FICHE = "numeroFiche";
	public final static String SEVERITE = "severite";
	public final static String RAISON_SOCIALE = "raisonSociale";
	public final static String CLASSE = "classe";
	public final static String NOM_SOCIETE = "nomSociete";
	public final static String NOM_REFERENCE = "nomReference";
	public final static String PRENOM_REFERENCE = "prenomReference";
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheAdresses criteresRechercheAdresses = (CriteresRechercheAdresses) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();

        select.append("select ");
        select.append( selectArgument() );

		from.append("from ad_adresse ad, so_societe so, si_site si ");
		where.append("where ad.l_ad_reference = so.l_so_cle ");
		where.append("and ad.c_ad_ref_genre = 'SO' ");
		where.append("and si.l_si_cle = so.l_si_cle ");
		
		//On filtre la liste selon le niveau de confidentialité et l'entité
		where.append("AND so.i_cc_cle <= ? ");
        preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		
		super.assignerBaseSQL(preparerSQL, criteresRechercheAdresses);
		
        preparerSQL.setSQL(formerSQL());
        
        return preparerSQL;	
	}
}
