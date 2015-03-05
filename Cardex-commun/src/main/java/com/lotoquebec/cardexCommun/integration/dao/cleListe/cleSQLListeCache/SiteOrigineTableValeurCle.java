package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


/**
 * Cette classe est requise � cause du s.b_si_origine = 1
 * @author levassc
 */
public class SiteOrigineTableValeurCle extends TableValeurCleSQLListeCache{

	public SiteOrigineTableValeurCle() {
		super();
	}

	
	public SiteOrigineTableValeurCle(String langue, long colonneDiscriminantValeur, String actionSecurite) {
		super(langue, GlobalConstants.TableValeur.SITE, String.valueOf(colonneDiscriminantValeur), actionSecurite);
	}
	
	public SiteOrigineTableValeurCle(String langue, String colonneDiscriminantValeur, String actionSecurite) {
		super(langue, GlobalConstants.TableValeur.SITE, colonneDiscriminantValeur, actionSecurite);
	}

	public SiteOrigineTableValeurCle(CardexAuthenticationSubject subject, long colonneDiscriminantValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), GlobalConstants.TableValeur.SITE, colonneDiscriminantValeur, actionSecurite);
	}
	
	public SiteOrigineTableValeurCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), GlobalConstants.TableValeur.SITE, colonneDiscriminantValeur, actionSecurite);
	}
	
	public SiteOrigineTableValeurCle(CardexAuthenticationSubject subject, String actionSecurite) {
		super(subject.getLocale().getLanguage(), GlobalConstants.TableValeur.SITE, "", actionSecurite);
	}
	

	public SiteOrigineTableValeurCle(String langue, String discriminantValeur, String actionSecurite, boolean abreviation) {
		super(langue, GlobalConstants.TableValeur.SITE, String.valueOf(discriminantValeur), actionSecurite);
		this.abreviation = abreviation;
	}

	public SiteOrigineTableValeurCle(String langue, long[] discriminantValeurs, String actionSecurite, boolean abreviation) {
		super(langue, GlobalConstants.TableValeur.SITE, discriminantValeurs, actionSecurite);
		this.abreviation = abreviation;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		
		preparerSQL.getSQL().append( "select s.l_si_cle as "+ListeCacheSQL.CLE+", tv.l_tv_action as "+SiteOrigineTableValeurCle.ACTION+", tv.b_tv_actif as "+SiteOrigineTableValeurCle.ACTIF+", tv.v_tv_role as "+SiteOrigineTableValeurCle.ROLE );
		preparerSQL.getSQL().append( ", tv.b_tv_obligatoire as "+SiteApplicableTableValeurCle.OBLIGATOIRE+" ");
		
		if (abreviation)
			preparerSQL.getSQL().append( ", tr.v_tr_abreviation as "+ListeCacheSQL.DESCRIPTION);
		else
			preparerSQL.getSQL().append( ", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION);
		
		preparerSQL.getSQL().append( ", tv.b_tv_administrer as "+SiteOrigineTableValeurCle.ADMINISTRER+" ");
		preparerSQL.getSQL().append( "from tv_table_valeur tv, si_site s, tr_traduction tr ");
		preparerSQL.getSQL().append( "where s.l_si_cle = tv.l_tv_valeur ");
		preparerSQL.getSQL().append( "and tv.l_tv_valeur = tr.l_tr_cle ");
		preparerSQL.getSQL().append( "and s.b_si_origine = 1 " ); // SITE D'ORIGINE

		assignerWhereCommun(preparerSQL);
		
		return preparerSQL;
	}

	public SiteOrigineTableValeurCle nouveauTableValeurCleSQLListeCache(String actionSecurite){
		return new SiteOrigineTableValeurCle(getLangue(), getDiscriminantValeurs(), actionSecurite, abreviation);
	}
}
