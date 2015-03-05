package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;



/**
 * Cette classe est requise a cause du s.b_si_applicable = 1
 * @author levassc
 */
public class SiteApplicableTableValeurCle extends TableValeurCleSQLListeCache{

	public SiteApplicableTableValeurCle() {
		super();
	}

	public SiteApplicableTableValeurCle(String langue, long[] ls, String actionSecurite) {
		super(langue, GlobalConstants.TableValeur.SITE, ls, actionSecurite);
	}
	
	public SiteApplicableTableValeurCle(String langue, String colonneDiscriminantValeur, String actionSecurite) {
		super(langue, GlobalConstants.TableValeur.SITE, colonneDiscriminantValeur, actionSecurite);
	}

	public SiteApplicableTableValeurCle(CardexAuthenticationSubject subject, long colonneDiscriminantValeur, String actionSecurite) {
		super(subject, GlobalConstants.TableValeur.SITE, String.valueOf(colonneDiscriminantValeur), actionSecurite);
	}
	
	public SiteApplicableTableValeurCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur, String actionSecurite) {
		super(subject, GlobalConstants.TableValeur.SITE, colonneDiscriminantValeur, actionSecurite);
	}
	
	public SiteApplicableTableValeurCle(CardexAuthenticationSubject subject, String actionSecurite) {
		super(subject, GlobalConstants.TableValeur.SITE, actionSecurite);
	}
	

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		
		preparerSQL.getSQL().append( "select s.l_si_cle as "+ListeCacheSQL.CLE+", tv.l_tv_action as "+SiteApplicableTableValeurCle.ACTION+", tv.b_tv_actif as "+SiteApplicableTableValeurCle.ACTIF+", tv.v_tv_role as "+SiteApplicableTableValeurCle.ROLE );
		preparerSQL.getSQL().append( ", tv.b_tv_obligatoire as "+SiteApplicableTableValeurCle.OBLIGATOIRE+" ");
		preparerSQL.getSQL().append( ", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION);
		
		preparerSQL.getSQL().append( ", tv.b_tv_administrer as "+SiteApplicableTableValeurCle.ADMINISTRER+" ");
		preparerSQL.getSQL().append( "from tv_table_valeur tv, si_site s, tr_traduction tr ");
		preparerSQL.getSQL().append( "where s.l_si_cle = tv.l_tv_valeur ");
		preparerSQL.getSQL().append( "and tv.l_tv_valeur = tr.l_tr_cle ");
		preparerSQL.getSQL().append( "and s.b_si_applicable = 1 " ); // SITE APPLICABLE

		assignerWhereCommun(preparerSQL);
		
		return preparerSQL;
	}

	public TableValeurCleSQLListeCache nouveauTableValeurCleSQLListeCache(String actionSecurite){
		return new SiteApplicableTableValeurCle(getLangue(), getDiscriminantValeurs(), actionSecurite);
	}

}
