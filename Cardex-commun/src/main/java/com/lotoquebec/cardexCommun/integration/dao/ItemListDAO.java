package com.lotoquebec.cardexCommun.integration.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter.CleListeAutoCompleter;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.util.TableValeur;

/**
 * Obtiens de la base de donn�e Oracle, des collections de valeurs pour chaque
 * type de liste d�roulante contenues dans une page JSP.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.46 $, $Date: 2002/04/25 19:49:44 $
 * @see com.lotoquebec.cardex.integration.dao.ItemListDAO
 */
public class ItemListDAO {

    /**
     * L'instance du gestionnaire de journalisation.
     */
    private static Logger      log = LoggerFactory.getLogger((ItemListDAO.class));

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.ItemListDAO#getMapListValeur(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.integration.dao.cleListe.CleListe)
	 */
	public Map getMapListValeur(CardexAuthenticationSubject subject, CleListe cleListe) throws DAOException {
		log.debug("getMapListValeur(CardexAuthenticationSubject, CleMultiListeCache)");
    	final Map map = new LinkedHashMap();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = ListeCacheSQL.obtenirPreparerSQL( cleListe );
    	
    	map.put("", new LabelValueBean("", ""));
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
                String label = rs.getString(ListeCacheSQL.DESCRIPTION);
                String value = rs.getString(ListeCacheSQL.CLE);
                log.debug("   [LabelValueBean label='" + label
                          + "' value='" + value + "']");
                LabelValueBean valueBean = new LabelValueBean(label, value);
                map.put(value, valueBean);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	return map;
	}
	
	public Map getMapListTableValeur(CardexAuthenticationSubject subject, final TableValeurCleSQLListeCache cleListe) throws DAOException {
		log.debug("getMapListValeur(CardexAuthenticationSubject, CleMultiListeCache)");
    	final Map map = new LinkedHashMap();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = ListeCacheSQL.obtenirPreparerSQL( cleListe );
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String cle = rs.getString(ListeCacheSQL.CLE);
				boolean isActif = OracleDAOUtils.convertirStringABoolean(rs.getString(TableValeurCleSQLListeCache.ACTIF));
				String role = rs.getString(TableValeurCleSQLListeCache.ROLE);
                String description = rs.getString(ListeCacheSQL.DESCRIPTION);
                int action = rs.getInt(TableValeurCleSQLListeCache.ACTION);
                boolean administrer = OracleDAOUtils.convertirStringABoolean(rs.getString(TableValeurCleSQLListeCache.ADMINISTRER));
                boolean obligatoire = OracleDAOUtils.convertirStringABoolean(rs.getString(TableValeurCleSQLListeCache.OBLIGATOIRE));
                
                log.debug("   [TableValeur cle='" + cle + "' description='" + description + "']");
                
                map.put(cle, new TableValeur(cle, description, isActif, role, action, obligatoire, administrer));
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	return map;
	}	
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.ItemListDAO#getListeAutoCompleter(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.integration.dao.cleListeAutoCompleter.CleListeAutoCompleter)
	 */
	public List getListeAutoCompleter(CardexAuthenticationSubject subject, String valeurDepart, CleListeAutoCompleter cleListeAutoCompleter) throws DAOException {
		log.debug("getListeAutoCompleter(CardexAuthenticationSubject, CleListeAutoCompleter)");
    	final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = ListeAutoCompleterSQL.obtenirSQL( subject, valeurDepart, cleListeAutoCompleter );
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
                liste.add( rs.getString(ListeAutoCompleterSQL.VALEUR) );
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	return liste;
	}
    
	//Retourne une liste statique de mois ou d'ann�e. Utilis� dans le formulaire d'�valuation du comit� de vigilance.
	public Map getMapListValeurStatique(CardexAuthenticationSubject subject, String liste) throws DAOException {
		log.debug("getMapListValeurStatique");
    	final Map map = new LinkedHashMap();
    	map.put("", new LabelValueBean("", ""));
    	String label = "";
    	String value = "";
    	if(liste.equals(GlobalConstants.ListeCache.ANNEE)){
    		for(int i=2011;i<2025;i++){
    			label = String.valueOf(i);
    			value = String.valueOf(i);
    			log.debug("   [LabelValueBean label='" + label
	                  + "' value='" + value + "']");
    			LabelValueBean valueBean = new LabelValueBean(label, value);
    			map.put(value, valueBean);
    		}
    	}
    	if(liste.equals(GlobalConstants.ListeCache.MOIS)){
			label = "01";
			value = "01";
			LabelValueBean valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "02";
			value = "02";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "03";
			value = "03";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "04";
			value = "04";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "05";
			value = "05";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "06";
			value = "06";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "07";
			value = "07";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "08";
			value = "08";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "09";
			value = "09";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "10";
			value = "10";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "11";
			value = "11";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
			label = "12";
			value = "12";
			valueBean = new LabelValueBean(label, value);
			map.put(value, valueBean);
    	}
    	return map;
	}

}