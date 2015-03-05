/*
 * Created on 23-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.vo.ResultatLigneRegroupementVO;
import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.EndroitRegroupementRapportSQL_CDX_0081;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.GlobalRegroupementRapportSQL;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.IntervenantCategorieRegroupementRapportSQL_CDX_0084;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.IntervenantEndroitRegroupementRapportSQL_CDX_0085;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.IntervenantRegroupementRapportSQL_CDX_0086;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.MatriceRegroupementRapportSQL_CDX_0160;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.RegroupementRapportSQL;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.SecteurRegroupementRapportSQL;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.TempsComptabilieParMatriculeRegroupementRapportSQL;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.TempsComptabilieRegroupementRapportSQL;
import com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement.TendanceMoisRegroupementRapportSQL_CDX_0088;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnectionRemphor;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class RegroupementDAO {

	private static SimpleDateFormat dateFormatYYYYMM = new SimpleDateFormat("yyyy-MM");
	
    public ResultatRegroupementVO produireRapportGlobal(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new GlobalRegroupementRapportSQL()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong( RegroupementRapportSQL.CLE_REGROUPEMENT ) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getDouble(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );

				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	RegroupementVO.setListRegroupement( liste );
    	RegroupementVO.setNombreHeuresProductive( obtenirTempsNonComptabilise(criteresRechercheRegroupement) );
    	RegroupementVO.setHeuresProductiveValide( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) );
    	
		return RegroupementVO;
    }

	public ResultatRegroupementVO produireRapportRegroupementEndroit(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new EndroitRegroupementRapportSQL_CDX_0081()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong(RegroupementRapportSQL.CLE_REGROUPEMENT) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getInt(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );
				RegroupementVO.setEndroit( rs.getString(EndroitRegroupementRapportSQL_CDX_0081.ENDROIT) );
				
				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	RegroupementVO.setListRegroupement( liste );
    	RegroupementVO.setNombreHeuresProductive( obtenirTempsNonComptabilise(criteresRechercheRegroupement) );
    	RegroupementVO.setHeuresProductiveValide( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) );
    	
		return RegroupementVO;
    }
    
    public ResultatRegroupementVO produireRapportSecteur(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	
    	PreparerSQL preparerSQL = (new SecteurRegroupementRapportSQL()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong(RegroupementRapportSQL.CLE_REGROUPEMENT) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getInt(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );
				RegroupementVO.setCategorie( rs.getString(SecteurRegroupementRapportSQL.CATEGORIE) );
				RegroupementVO.setType( rs.getString(SecteurRegroupementRapportSQL.TYPE) );
				
				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	RegroupementVO.setListRegroupement( liste );
    	RegroupementVO.setNombreHeuresProductive( obtenirTempsNonComptabilise(criteresRechercheRegroupement) );
    	RegroupementVO.setHeuresProductiveValide( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) );
    	
		return RegroupementVO;
    }
    
    public ResultatRegroupementVO produireRapportIntervenant(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new IntervenantRegroupementRapportSQL_CDX_0086()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong(RegroupementRapportSQL.CLE_REGROUPEMENT) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getInt(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );
				RegroupementVO.setCleIntervenant( rs.getLong(IntervenantRegroupementRapportSQL_CDX_0086.CLE_INTERVENANT) );
				RegroupementVO.setNomIntervenant( rs.getString(IntervenantRegroupementRapportSQL_CDX_0086.NOM_INTERVENANT) );
				RegroupementVO.setPrenomIntervenant( rs.getString(IntervenantRegroupementRapportSQL_CDX_0086.PRENOM_INTERVENANT) );
				RegroupementVO.setMatricule( rs.getString(IntervenantRegroupementRapportSQL_CDX_0086.MATRICULE) );
				
				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	liste.addAll( RegroupementVOAAjouter(subject, liste, criteresRechercheRegroupement) );
    	RegroupementVO.setListRegroupement( liste );
    	RegroupementVO.setMapMatriculeHeuresProductive( obtenirMapMatriculeTempsNonComptabilise(criteresRechercheRegroupement) );
    	RegroupementVO.setNombreHeuresProductive( obtenirTempsNonComptabilise(RegroupementVO.getMapMatriculeHeuresProductive()) );
    	RegroupementVO.setHeuresProductiveValide( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) );
    	
		return RegroupementVO;
    }

	private double obtenirTempsNonComptabilise(Map mapMatriculeHeuresProductive) {
		double totalHeures = 0;
		Iterator iter = mapMatriculeHeuresProductive.values().iterator();
		
		while (iter.hasNext()) {
			Double heureProductive = (Double) iter.next();
			totalHeures += heureProductive.doubleValue();
		}
		return totalHeures;
	}

	/*
     * Obtenir une liste des RegroupementVOs supplémentaires à ajouter
     */
	private Collection RegroupementVOAAjouter(CardexAuthenticationSubject subject, List liste, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException {
				
		if (criteresRechercheRegroupement.getRegroupement() == 0){
			Map mapRegroupementVOAAjouter = new HashMap();
			Map mapRegroupementVO = obtenirRegroupementVO(subject, criteresRechercheRegroupement);
			Iterator iter = liste.iterator();
			
			while (iter.hasNext()) {
				ResultatLigneRegroupementVO RegroupementVO = (ResultatLigneRegroupementVO) iter.next();
				
				// On construit un clone du map mapRegroupementVO
				if (mapRegroupementVOAAjouter.containsKey( String.valueOf(RegroupementVO.getCleIntervenant()) ) == false){
					mapRegroupementVOAAjouter.put( String.valueOf( RegroupementVO.getCleIntervenant() ), construireMapRegroupementVOIntervenant(RegroupementVO, mapRegroupementVO) );
				}
				Map mapRegroupementVOIntervenant = (Map) mapRegroupementVOAAjouter.get( String.valueOf( RegroupementVO.getCleIntervenant() ) );
				
				// On delete le RegroupementVO qui est déjà contenu dans la liste
				if (mapRegroupementVOIntervenant.containsKey( String.valueOf( RegroupementVO.getCle() ) )){
					mapRegroupementVOIntervenant.remove( String.valueOf( RegroupementVO.getCle() ) );
				}
			}
			return construireListeRegroupementVOAAjouter(mapRegroupementVOAAjouter);
		}
		
		return new ArrayList();
	}
	
	/*
	 * Construire la liste de sortie qui est tous les RegroupementVOs à vide
	 */
	private Collection construireListeRegroupementVOAAjouter(Map mapRegroupementVOAAjouter) {
		Collection list = new ArrayList();
		Iterator iter = mapRegroupementVOAAjouter.values().iterator();
		
		while (iter.hasNext()) {
			Map mapRegroupementVOIntervenant = (Map) iter.next();
			list.addAll(mapRegroupementVOIntervenant.values());
		}
		return list;
	}

	/*
	 * Cloner la liste de regroupeemnt passé en paramètre
	 */
	private Map construireMapRegroupementVOIntervenant(ResultatLigneRegroupementVO RegroupementVO, Map mapRegroupementVO){
		Map mapRegroupementVOIntervenant = new HashMap();
		Iterator iter = mapRegroupementVO.values().iterator();
		
		while (iter.hasNext()) {
			ResultatLigneRegroupementVO resultatRegroupementVO = (ResultatLigneRegroupementVO) iter.next();
			ResultatLigneRegroupementVO resultatRegroupementVOIter;
			try {
				resultatRegroupementVOIter = (ResultatLigneRegroupementVO) resultatRegroupementVO.clone();
				resultatRegroupementVOIter.setCleIntervenant( RegroupementVO.getCleIntervenant() );
				resultatRegroupementVOIter.setPrenomIntervenant( RegroupementVO.getPrenomIntervenant() );
				resultatRegroupementVOIter.setNomIntervenant( RegroupementVO.getNomIntervenant() );
				mapRegroupementVOIntervenant.put(String.valueOf( resultatRegroupementVOIter.getCle() ), resultatRegroupementVOIter);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			} 
		}
		return mapRegroupementVOIntervenant;
	}
    
    public ResultatRegroupementVO produireRapportIntervenantCategorie(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new IntervenantCategorieRegroupementRapportSQL_CDX_0084()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong(RegroupementRapportSQL.CLE_REGROUPEMENT) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getInt(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );
				RegroupementVO.setCleIntervenant( rs.getLong(IntervenantCategorieRegroupementRapportSQL_CDX_0084.CLE_INTERVENANT) );
				RegroupementVO.setNomIntervenant( rs.getString(IntervenantCategorieRegroupementRapportSQL_CDX_0084.NOM_INTERVENANT) );
				RegroupementVO.setPrenomIntervenant( rs.getString(IntervenantCategorieRegroupementRapportSQL_CDX_0084.PRENOM_INTERVENANT) );
				RegroupementVO.setCategorie( rs.getString(IntervenantCategorieRegroupementRapportSQL_CDX_0084.CATEGORIE) );
				RegroupementVO.setType( rs.getString(IntervenantCategorieRegroupementRapportSQL_CDX_0084.TYPE) );
				
				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	RegroupementVO.setListRegroupement( liste );
    	RegroupementVO.setNombreHeuresProductive( obtenirTempsNonComptabilise(criteresRechercheRegroupement) );
    	RegroupementVO.setHeuresProductiveValide( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) );
    	
		return RegroupementVO;
    }
    
    public ResultatRegroupementVO produireRapportIntervenantEndroit(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new IntervenantEndroitRegroupementRapportSQL_CDX_0085()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong(RegroupementRapportSQL.CLE_REGROUPEMENT) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getInt(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );
				RegroupementVO.setCleIntervenant( rs.getLong(IntervenantEndroitRegroupementRapportSQL_CDX_0085.CLE_INTERVENANT) );
				RegroupementVO.setNomIntervenant( rs.getString(IntervenantEndroitRegroupementRapportSQL_CDX_0085.NOM_INTERVENANT) );
				RegroupementVO.setPrenomIntervenant( rs.getString(IntervenantEndroitRegroupementRapportSQL_CDX_0085.PRENOM_INTERVENANT) );
				RegroupementVO.setEndroit( rs.getString(IntervenantEndroitRegroupementRapportSQL_CDX_0085.ENDROIT) );
				
				liste.add(RegroupementVO);
			}
    	};
    	template.query(preparerSQL, rch);
    	RegroupementVO.setListRegroupement( liste );
    	RegroupementVO.setNombreHeuresProductive( obtenirTempsNonComptabilise(criteresRechercheRegroupement) );
    	RegroupementVO.setHeuresProductiveValide( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) );
    	
		return RegroupementVO;
    }
	
    public ResultatRegroupementVO produireRapportTendanceMois(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
    	final SimpleDateFormat dateFormatYYYYMMMMM = new SimpleDateFormat("yyyy-MMMMM", subject.getLocale());
		final List<ResultatLigneRegroupementVO> liste = new ArrayList<ResultatLigneRegroupementVO>();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new TendanceMoisRegroupementRapportSQL_CDX_0088()).obtenirSQL(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				
				RegroupementVO.setCle( rs.getLong( RegroupementRapportSQL.CLE_REGROUPEMENT ) );
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setQuota( rs.getDouble(RegroupementRapportSQL.QUOTA) );
				RegroupementVO.setQuotaMin( rs.getInt(RegroupementRapportSQL.QUOTA_MINIMUM) );
				RegroupementVO.setNombreMinutes( rs.getLong(RegroupementRapportSQL.SOMME_HEURES) );
				RegroupementVO.setMoisNombre( rs.getString(RegroupementRapportSQL.MOIS_NOMBRE) );
				
				try {
					Calendar anneeMoisCal = Calendar.getInstance();
					Date anneeMoisLettres = dateFormatYYYYMM.parse( RegroupementVO.getMoisNombre() );
					anneeMoisCal.setTime(anneeMoisLettres);
					RegroupementVO.setMoisLettres( dateFormatYYYYMMMMM.format( anneeMoisCal.getTime() ).toUpperCase() );	
				} catch (ParseException e) {
					e.printStackTrace();
				}

				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	modifierChangementMois(subject, criteresRechercheRegroupement.getDateDebutAu(), liste);
    	RegroupementVO.setListRegroupement( liste );
    	
		return RegroupementVO;
    }

    /*
     * Remphor gère deux jours de travail dans la même journée.  Par exemple,
     * travailler de 20h à 06h (le lendemain) ce verra dans Remphor comme 
     * étant dans la même journée de 20h à 06h.
     * 
     * Pour ça, lorsque la date "Au" passé en paramètre est la dernière du mois
     * Cardex retourne une journée de plus.  Par exemple, 2001-01-31 va prendre
     * le temps dans Cardex jusqu'au 2001-02-01 à 06h (heure de début/fin de quart
     * de travail).
     */
    private void modifierChangementMois(CardexAuthenticationSubject subject, Date dateCreationAu, List<ResultatLigneRegroupementVO> liste) {
    	SimpleDateFormat dateFormatYYYYMMMMM = new SimpleDateFormat("yyyy-MMMMM", subject.getLocale());
		Calendar calendarCreationAu = Calendar.getInstance();
		calendarCreationAu.setTimeInMillis(dateCreationAu.getTime());

    	if (isDerniereJourneeDuMois(calendarCreationAu)){
    		String anneeMoisAAjouter = dateFormatYYYYMM.format(calendarCreationAu.getTime());
    		String anneeMoisLongAAjouter = dateFormatYYYYMMMMM.format(calendarCreationAu.getTime()).toUpperCase();
    		List<ResultatLigneRegroupementVO> RegroupementVOsEnTrop = construireListRegroupementVOEnTrop(calendarCreationAu, liste);
    		
    		for (ResultatLigneRegroupementVO RegroupementVOEnTrop:RegroupementVOsEnTrop){
        		Iterator<ResultatLigneRegroupementVO> iter = liste.iterator();
        		boolean RegroupementVOEnTropTrouve = false;
        		
        		while (iter.hasNext()) {
    				ResultatLigneRegroupementVO resultatRegroupementVO = iter.next();

        			if (anneeMoisAAjouter.equals(resultatRegroupementVO.getMoisNombre())
        			&& RegroupementVOEnTrop.getNomRegroupement().equals(resultatRegroupementVO.getNomRegroupement())){
        				resultatRegroupementVO.setNombreMinutes( resultatRegroupementVO.getNombreMinutes() + RegroupementVOEnTrop.getNombreMinutes() );
        				RegroupementVOEnTropTrouve = true;
        				break;
        				
        			} else if (RegroupementVOEnTrop == resultatRegroupementVO){
        				// la valeur dans le mois suivant doit être retiré
        				iter.remove();
        			}
    			}
        		
       		 	// Cas particulier, le RegroupementVO n'existait pas dans
				// le mois précédent
				if (RegroupementVOEnTropTrouve == false){
					RegroupementVOEnTrop.setMoisLettres( anneeMoisLongAAjouter );
					RegroupementVOEnTrop.setMoisNombre( anneeMoisAAjouter );   
					liste.add( RegroupementVOEnTrop );
				}
    		}
    	}
	}
    
    private List<ResultatLigneRegroupementVO> construireListRegroupementVOEnTrop(Calendar calendarCreationAu, List<ResultatLigneRegroupementVO> liste){
    	List<ResultatLigneRegroupementVO> RegroupementVOsEnTrop = new ArrayList<ResultatLigneRegroupementVO>();
    	calendarCreationAu.add(Calendar.DATE, 1);// +1 pour avoir le prochain mois
    	
		String anneeMoisEnTrop = dateFormatYYYYMM.format(calendarCreationAu.getTime()); 
		
		for(ResultatLigneRegroupementVO resultatRegroupementVO:liste){

			if (anneeMoisEnTrop.equals(resultatRegroupementVO.getMoisNombre()))
				RegroupementVOsEnTrop.add(resultatRegroupementVO);
		}
		return RegroupementVOsEnTrop;
    }
    
    private boolean isDerniereJourneeDuMois(Calendar calendarCreationAu){
		int derniereJournee = calendarCreationAu.getActualMaximum(Calendar.DAY_OF_MONTH);
		return calendarCreationAu.get(Calendar.DAY_OF_MONTH) == derniereJournee;
    }

	public ResultatRegroupementVO produireRapportMatriceRegroupement(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	ResultatRegroupementVO RegroupementVO = new ResultatRegroupementVO();
		final List liste = new ArrayList();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new MatriceRegroupementRapportSQL_CDX_0160()).obtenirSQLMatrice(subject, criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				String classement = rs.getString(RegroupementRapportSQL.GENRE) + " - " + rs.getString(RegroupementRapportSQL.NATURE) + " - " + rs.getString(RegroupementRapportSQL.TYPE) + " - " + rs.getString(RegroupementRapportSQL.CATEGORIE);
				RegroupementVO.setNomRegroupement( rs.getString(RegroupementRapportSQL.NOM_REGROUPEMENT) );
				RegroupementVO.setCategorie( classement );
				liste.add(RegroupementVO);
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	RegroupementVO.setListRegroupement( liste );
		return RegroupementVO;
    }

    private double obtenirTempsNonComptabilise(RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
		final List liste = new ArrayList();
		JDBCTemplate template = new JDBCTemplate( DAOConnectionRemphor.getInstance().getConnection() );
    	
    	if ( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) == false )
    		return 0;
    	
    	PreparerSQL preparerSQL = (new TempsComptabilieRegroupementRapportSQL()).obtenirSQL(criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				liste.add( new Double( rs.getDouble(TempsComptabilieRegroupementRapportSQL.TOTAL_TEMPS_PRODUCTIF ) ) );
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	
		return ((Double)liste.get(0)).doubleValue();
    }
    
    private Map obtenirMapMatriculeTempsNonComptabilise(RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
		final Map map = new HashMap();
    	JDBCTemplate template = new JDBCTemplate( DAOConnectionRemphor.getInstance().getConnection() );
    	
    	if ( isCriteresValidePourCalculTempsComptabilise(criteresRechercheRegroupement) == false )
    		return new HashMap();
    	
    	PreparerSQL preparerSQL = (new TempsComptabilieParMatriculeRegroupementRapportSQL()).obtenirSQL(criteresRechercheRegroupement);
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				String key = rs.getString(TempsComptabilieParMatriculeRegroupementRapportSQL.MATRICULE);
				double nbHeuresProductive = rs.getDouble(TempsComptabilieParMatriculeRegroupementRapportSQL.TOTAL_TEMPS_PRODUCTIF);
				map.put(key, new Double( nbHeuresProductive ) );
			}
    	};
    	
    	template.query(preparerSQL, rch);
    	
		return map;
    }    
    
    /*
     * Pour certaines raisons le temps comptabilisé ne va représenter qu'une 
     * partie non significative par rapport aux heures remphor.
     */
    private boolean isCriteresValidePourCalculTempsComptabilise(RegroupementRapportVO criteresRechercheRegroupement){
    	boolean isSiteMontreal = criteresRechercheRegroupement.getSite() != 0
		&& Long.valueOf( GlobalConstants.SiteMaisonJeux.MONTREAL).doubleValue() == criteresRechercheRegroupement.getSite();
    	boolean isSiteCharlevoix = criteresRechercheRegroupement.getSite() != 0
		&& Long.valueOf( GlobalConstants.SiteMaisonJeux.CHARLEVOIX).doubleValue() == criteresRechercheRegroupement.getSite();
    	boolean isSiteLacLeamy = criteresRechercheRegroupement.getSite() != 0
		&& Long.valueOf( GlobalConstants.SiteMaisonJeux.LAC_LEAMY).doubleValue() == criteresRechercheRegroupement.getSite();
    	boolean isSiteMontTremblant = criteresRechercheRegroupement.getSite() != 0
		&& Long.valueOf( GlobalConstants.SiteMaisonJeux.MONT_TREMBLANT).doubleValue() == criteresRechercheRegroupement.getSite();    	
    	boolean isEntiteMaisonJeux = criteresRechercheRegroupement.getEntite() != 0
    	&& Long.valueOf( GlobalConstants.Entite.MAISON_JEUX).doubleValue() == criteresRechercheRegroupement.getEntite();
    	boolean isGenreVide = criteresRechercheRegroupement.getGenre() == 0;
    	boolean isNatureVide = criteresRechercheRegroupement.getNature() == 0;
    	boolean isTypeVide = criteresRechercheRegroupement.getType() == 0;
    	boolean isCategorieVide = criteresRechercheRegroupement.getCategorie() == 0;
    	boolean isRegroupementVOVide = criteresRechercheRegroupement.getRegroupement() == 0;    	
    	boolean isEndroitVide = StringUtils.isEmpty( criteresRechercheRegroupement.getEndroit() );
    	
    	return (isSiteMontreal || isSiteCharlevoix || isSiteLacLeamy || isSiteMontTremblant) 
		&& isEntiteMaisonJeux 
		&& isGenreVide && isNatureVide && isTypeVide && isCategorieVide
		&& isRegroupementVOVide && isEndroitVide;
    }
    
    private Map obtenirRegroupementVO(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	final Map map = new HashMap();
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection
					.prepareCall("begin CARDEX_LIRE_DOC.SP_RG_Regroupement (?,?); end;");
			callableStatement.setLong(1, criteresRechercheRegroupement.getSecteur());
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet resultSet = (ResultSet)callableStatement.getObject(2);
	        
			//Traitement des données retournées
			while (resultSet.next()) {
				ResultatLigneRegroupementVO RegroupementVO = new ResultatLigneRegroupementVO();
				RegroupementVO.setCle(resultSet.getLong("I_RG_CLE"));
				RegroupementVO.setNomRegroupement(resultSet.getString("NOM_Regroupement"));
				RegroupementVO.setGroupe(resultSet.getString("NAME"));
				RegroupementVO.setQuota(resultSet.getInt("I_RG_QUOTA"));
				RegroupementVO.setQuotaMin(resultSet.getInt("I_RG_QUOTA_MIN"));
				
				map.put(String.valueOf(RegroupementVO.getCle()), RegroupementVO);
			}

		} catch (SQLException se) {
			throw new DAOException(se);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (java.sql.SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					if (!connection.getAutoCommit()) {
						connection.rollback();
					}
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}    	
    	return map;
    }

}
