package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import java.util.Arrays;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.LongUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;



/**
 * @author levassc
 */
public class TableValeurCleSQLListeCache extends CleSQLListeCache{

	protected String valeurTableValeur = "";
	private String actionSecurite = "";
	protected boolean abreviation = false;
	public final static String ACTION = "action";
	public final static String ADMINISTRER = "administrer";
	public final static String ACTIF = "actif";
	public final static String ROLE = "role";	
	public final static String OBLIGATOIRE = "obligatoire";
	protected long[] discriminantValeurs = new long[0];

	public TableValeurCleSQLListeCache() {
		super(null, null);
	}

	public TableValeurCleSQLListeCache(String langue, String discriminantValeur) {
		super(langue, discriminantValeur);
	}

	public TableValeurCleSQLListeCache(String langue, String valeurTableValeur, long[] discriminantValeurs, String actionSecurite) {
		super(langue, "");
		this.valeurTableValeur = valeurTableValeur;
		this.discriminantValeurs = discriminantValeurs;
		this.actionSecurite = actionSecurite;
	}
	
	public TableValeurCleSQLListeCache(String langue, String valeurTableValeur, long discriminantValeur, String actionSecurite) {
		super(langue, "");
		this.valeurTableValeur = valeurTableValeur;
		this.discriminantValeurs = LongUtils.listValueOf(discriminantValeur);
		this.actionSecurite = actionSecurite;
	}
	
	public TableValeurCleSQLListeCache(String langue, String valeurTableValeur, String discriminantValeur, String actionSecurite) {
		super(langue, "");
		this.valeurTableValeur = valeurTableValeur;
		
		if (discriminantValeur.indexOf(",") > -1){
			// Pour qu'une valeur vide soit
			this.discriminantValeurs = LongUtils.valueOf(discriminantValeur.split(",",-1));
		}else
			this.discriminantValeurs = LongUtils.listValueOf(discriminantValeur);
		this.actionSecurite = actionSecurite;
	}
	
	public TableValeurCleSQLListeCache(CardexAuthenticationSubject subject, String valeurTableValeur, long discriminantValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), "");
		this.valeurTableValeur = valeurTableValeur;
		this.discriminantValeurs = LongUtils.listValueOf(discriminantValeur);
		this.actionSecurite = actionSecurite;
	}

	public TableValeurCleSQLListeCache(CardexAuthenticationSubject subject, String valeurTableValeur, String[] discriminantValeurs, String actionSecurite) {
		super(subject.getLocale().getLanguage(), "");
		this.valeurTableValeur = valeurTableValeur;
		this.discriminantValeurs = LongUtils.valueOf(discriminantValeurs);
		this.actionSecurite = actionSecurite;
	}
	
	public TableValeurCleSQLListeCache(CardexAuthenticationSubject subject, String valeurTableValeur, String discriminantValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), "");
		this.valeurTableValeur = valeurTableValeur;
		this.discriminantValeurs = LongUtils.listValueOf(discriminantValeur);
		this.actionSecurite = actionSecurite;
	}
	
	public TableValeurCleSQLListeCache(CardexAuthenticationSubject subject, String valeurTableValeur, String actionSecurite) {
		super(subject.getLocale().getLanguage(), "");
		this.valeurTableValeur = valeurTableValeur;
		this.actionSecurite = actionSecurite;
	}
	
	//Sert pour aller chercher les �quivalents anglais dans les contrats d'autoexclusion
	public TableValeurCleSQLListeCache(String langue, String valeurTableValeur, String actionSecurite) {
		super(langue, "");
		this.valeurTableValeur = valeurTableValeur;
		this.actionSecurite = actionSecurite;
	}
	

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		preparerSQL.getSQL().append( "select distinct tv.l_tv_valeur as "+ListeCacheSQL.CLE+", tv.l_tv_action as "+TableValeurCleSQLListeCache.ACTION+", tv.b_tv_actif as "+TableValeurCleSQLListeCache.ACTIF+", tv.v_tv_role as "+TableValeurCleSQLListeCache.ROLE +", tv.b_tv_obligatoire as "+TableValeurCleSQLListeCache.OBLIGATOIRE);
		
		if (abreviation)
			preparerSQL.getSQL().append( ", tr.v_tr_abreviation as "+ListeCacheSQL.DESCRIPTION);
		else
			preparerSQL.getSQL().append( ", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION);
		
		preparerSQL.getSQL().append( ", tv.b_tv_administrer as "+TableValeurCleSQLListeCache.ADMINISTRER+" " );
		preparerSQL.getSQL().append( "from tv_table_valeur tv, tr_traduction tr " );
		
		preparerSQL.getSQL().append( "where tv.l_tv_valeur = tr.l_tr_cle " );
		assignerWhereCommun(preparerSQL);
		
		return preparerSQL;
	}

	/**
	 * Assigner Code, crit�re de table de valeur, action de s�curit� et la langue
	 * order by description
	 * @param preparerSQL
	 */
	protected void assignerWhereCommun(PreparerSQL preparerSQL) {
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "tv.v_tv_code", valeurTableValeur);
		
		if (discriminantValeurs.length > 0){
			preparerSQL.getSQL().append( "AND ? = (select count(*) from ctv_critere_table_valeur ctv where ctv.l_tv_valeur = tv.l_tv_valeur ");
			preparerSQL.addParametre(discriminantValeurs.length);
			OracleDAOUtils.ajouterChampSQL(preparerSQL, "ctv.l_tv_parent_valeur", discriminantValeurs);
			preparerSQL.getSQL().append(") ");
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "tv.L_TV_ACTION", Integer.valueOf(getActionSecurite()));
		OracleDAOUtils.ajouterChampSQL(preparerSQL, "tr.i_la_cle", OracleDAOUtils.getLongLangue(langue));
		
		preparerSQL.getSQL().append(OracleDAOUtils.sqlOrderBy(ListeCacheSQL.DESCRIPTION));
	}

	
	public String getValeurTableValeur() {
		return valeurTableValeur;
	}

	/*
	 * Il devrait toujours avoir une action de s�curit�.  Sinon, nous utilisons toutes les actions
	 */
	public String getActionSecurite() {
		
		if (StringUtils.isEmpty(actionSecurite))
			actionSecurite = GlobalConstants.ActionSecurite.TOUTES_ACTIONS;
		return actionSecurite;
	}

	public TableValeurCleSQLListeCache nouveauTableValeurCleSQLListeCache(String actionSecurite){
		return new TableValeurCleSQLListeCache(getLangue(), getValeurTableValeur(), getDiscriminantValeurs(), actionSecurite);
	}
	
	public boolean isAbreviation() {
		return abreviation;
	}

	public boolean equals(Object obj) {
		
		if (super.equals(obj) == false)
			return false;
		
		TableValeurCleSQLListeCache tableValeurCleSQLListeCache = (TableValeurCleSQLListeCache) obj;
		
		if (StringUtils.equals( valeurTableValeur, tableValeurCleSQLListeCache.getValeurTableValeur() )  == false){
			return false;
		}
		
		if (StringUtils.equals( getActionSecurite(), tableValeurCleSQLListeCache.getActionSecurite() ) == false){
			return false;
		}
		
		if (abreviation !=  tableValeurCleSQLListeCache.isAbreviation()){
			return false;
		}
		
		if (Arrays.equals(discriminantValeurs, tableValeurCleSQLListeCache.getDiscriminantValeurs()) == false)
			return false;
		
		return true;
	}

	public long[] getDiscriminantValeurs() {
		return discriminantValeurs;
	}
	
	@Override
	public boolean isDiscreminantVide(){
		return discriminantValeurs.length == 0;
	}

	public void setValeurTableValeur(String valeurTableValeur) {
		this.valeurTableValeur = valeurTableValeur;
	}

	public void setActionSecurite(String actionSecurite) {
		this.actionSecurite = actionSecurite;
	}

	public void setDiscriminantValeur(String discriminantValeur) {
		this.discriminantValeurs = LongUtils.listValueOf(discriminantValeur);
	}
	
	
	
}
