package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.business.vo.RapportDossierVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.ValueObjectMapper;

/**
 * Production des rapports CDX_0143 et CDX_0144
 * @author guerinf
 * 2012-01-26
 */
public abstract class RechercheDossierRapport extends GenererRapport {

	public final static String SITE = "siteOrigine";
	public final static String TYPE = "type";
	public final static String CATEGORIE = "categorie";
	public final static String DATE_DEBUT = "dateDebutDu";
	public final static String DATE_FIN = "dateDebutAu";
	
	protected CardexAuthenticationSubject subject = null;

	// Appel des composants concept pour la production du rapport
	protected abstract RapportDossier produireRapport(CardexAuthenticationSubject subject, CriteresRechercheDossier criteresRechercheDossier) throws BusinessException; 
	
	// Construire le contenu du rapport
	protected abstract JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportDossier rapportDossierVO, CriteresRechercheDossier criteresRechercheDossier) throws BusinessException;
	
	public JasperPrint executer(CardexAuthenticationSubject subject, HttpServletRequest request) throws BusinessException, JRException {
		InputStream gabarit = obtenirGabarit();
		Map parameterMap = request.getParameterMap();
		this.subject = subject;
		
		CriteresRechercheDossier criteresRechercheDossier = (CriteresRechercheDossier) ValueObjectMapper.construireParameterMap(parameterMap, new CriteresRechercheDossierVO());
     	//On met le site de l'utilisateur si la variable est vide
		if (criteresRechercheDossier.getSiteOrigine() == 0){
            CardexUser user = (CardexUser)subject.getUser();
     		criteresRechercheDossier.setSiteOrigine(user.getSite());
     	}
		//On s'assure qu'il y a des dates pour les rapports. Sinon, on prend la dernière semaine.
        String DATE_FORMAT_NOW = "yyyy-MM-dd";
        Calendar dateDebutCal = Calendar.getInstance();
        Calendar dateFinCal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        dateDebutCal.add(Calendar.DATE,-7);
        try{
	        if(criteresRechercheDossier.getDateDebutDu() == null){
				criteresRechercheDossier.setDateDebutDu(TimestampFormat.parse(
						sdf.format(dateDebutCal.getTime()), subject.getLocale(), false));
			}
			if(criteresRechercheDossier.getDateDebutAu() == null){
				criteresRechercheDossier.setDateDebutAu(TimestampFormat.parse(
						sdf.format(dateFinCal.getTime()), subject.getLocale(), false));
			}
        } catch (ParseException pe) {
        	pe.printStackTrace();
        }
		
		RapportDossier rapportDossier = new RapportDossierVO();
			rapportDossier = produireRapport(subject, criteresRechercheDossier);
			
		JRDataSource dataSource = construireDataSource(subject, rapportDossier, criteresRechercheDossier);
		
		//Map parameters = construireParametres(subject, parameterMap);
		Map parameters = construireParametresCriteres(subject, criteresRechercheDossier);
			
		return JasperFillManager.fillReport(gabarit, parameters, dataSource);
	}

	private Map construireParametres(CardexAuthenticationSubject subject, Map parameterMap) {
		CardexUser cardexUser = (CardexUser) subject.getUser();
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		try {
			site = listeCache.obtenirLabel(subject, ValueObjectMapper.obtenirPremiereString(parameterMap,SITE), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		
		Map parameters = new HashMap();
		//On ajoute les paramètres qui seront affichés dans l'en-tête du rapport.
		parameters.put("SiteOrigine", site);
		parameters.put("DateDebut", ValueObjectMapper.obtenirPremiereString(parameterMap, DATE_DEBUT));
		parameters.put("DateFin", ValueObjectMapper.obtenirPremiereString(parameterMap, DATE_FIN));
		parameters.put("UTILISATEUR", cardexUser.getCode());

		return parameters;
	}
	
	private Map construireParametresCriteres(CardexAuthenticationSubject subject, CriteresRechercheDossier criteresRechercheDossier) {
		CardexUser cardexUser = (CardexUser) subject.getUser();
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		String genre = "";
		try {
			site = listeCache.obtenirLabel(subject, String.valueOf(criteresRechercheDossier.getSiteOrigine()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
			genre = listeCache.obtenirLabel(subject, String.valueOf(criteresRechercheDossier.getGenre()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		
		Map parameters = new HashMap();
		//On ajoute les paramètres qui seront affichés dans l'en-tête du rapport.
		parameters.put("SiteOrigine", site);
		parameters.put("DateDebut", TimestampFormat.format(
				criteresRechercheDossier.getDateDebutDu(), subject.getLocale(), false));
		parameters.put("DateFin", TimestampFormat.format(
				criteresRechercheDossier.getDateDebutAu(), subject.getLocale(), false));
		parameters.put("Genre", genre);
		parameters.put("UTILISATEUR", cardexUser.getCode());

		return parameters;
	}
}
