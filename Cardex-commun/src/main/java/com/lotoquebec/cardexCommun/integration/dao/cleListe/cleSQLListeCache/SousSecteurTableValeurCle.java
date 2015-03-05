package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


/**
 * Cette classe est requise � cause du s.b_si_origine = 1
 * @author levassc
 */
public class SousSecteurTableValeurCle extends TableValeurCleSQLListeCache{

	public SousSecteurTableValeurCle() {
		super();
	}

	
	public SousSecteurTableValeurCle(String langue, long colonneDiscriminantValeur, String actionSecurite) {
		super(langue, GlobalConstants.TableValeur.SOUS_SECTEUR, String.valueOf(colonneDiscriminantValeur), actionSecurite);
	}
	
	public SousSecteurTableValeurCle(String langue, String colonneDiscriminantValeur, String actionSecurite) {
		super(langue, GlobalConstants.TableValeur.SOUS_SECTEUR, colonneDiscriminantValeur, actionSecurite);
	}

	public SousSecteurTableValeurCle(CardexAuthenticationSubject subject, long colonneDiscriminantValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), GlobalConstants.TableValeur.SOUS_SECTEUR, colonneDiscriminantValeur, actionSecurite);
	}
	
	public SousSecteurTableValeurCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), GlobalConstants.TableValeur.SOUS_SECTEUR, colonneDiscriminantValeur, actionSecurite);
	}
	
	public SousSecteurTableValeurCle(CardexAuthenticationSubject subject, String actionSecurite) {
		super(subject.getLocale().getLanguage(), GlobalConstants.TableValeur.SOUS_SECTEUR, "", actionSecurite);
	}
	

	public SousSecteurTableValeurCle(String langue, String discriminantValeur, String actionSecurite, boolean abreviation) {
		super(langue, GlobalConstants.TableValeur.SOUS_SECTEUR, String.valueOf(discriminantValeur), actionSecurite);
		this.abreviation = abreviation;
	}

	public SousSecteurTableValeurCle(String langue, long[] discriminantValeurs, String actionSecurite, boolean abreviation) {
		super(langue, GlobalConstants.TableValeur.SOUS_SECTEUR, discriminantValeurs, actionSecurite);
		this.abreviation = abreviation;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		
		preparerSQL.getSQL().append( "select tv.l_tv_valeur as "+ListeCacheSQL.CLE+", tv.l_tv_action as "+SousSecteurTableValeurCle.ACTION+", tv.b_tv_actif as "+SousSecteurTableValeurCle.ACTIF+", tv.v_tv_role as "+SousSecteurTableValeurCle.ROLE );
		preparerSQL.getSQL().append( ", tv.b_tv_obligatoire as "+SiteApplicableTableValeurCle.OBLIGATOIRE+" ");
		preparerSQL.getSQL().append( ", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION);
		preparerSQL.getSQL().append( ", tv.b_tv_administrer as "+SousSecteurTableValeurCle.ADMINISTRER+" ");
		preparerSQL.getSQL().append( "from tv_table_valeur tv, tr_traduction tr ");
		preparerSQL.getSQL().append( "where tv.l_tv_valeur = tr.l_tr_cle ");

		assignerWhereCommun(preparerSQL);
		
		return preparerSQL;
	}

	public SousSecteurTableValeurCle nouveauTableValeurCleSQLListeCache(String actionSecurite){
		return new SousSecteurTableValeurCle(getLangue(), getDiscriminantValeurs(), actionSecurite, abreviation);
	}
}
