package com.lotoquebec.cardex.generateurRapport.adresse;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheAdressesVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.EntiteCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ValueObjectMapper;

public class AdresseInvalide extends GenererRapport {

	public final static String ENTITE = "entite";
	public final static String SITE_ORIGINE = "siteOrigine";
	public final static String ENTITE_RECHERCHE = "entiteRecherche";
	public final static String DATE_CREATION_DU = "dateCreationDu";
	public final static String DATE_CREATION_AU = "dateCreationAu";
	
		
	public JasperPrint executer(CardexAuthenticationSubject subject, HttpServletRequest request) throws BusinessException, JRException {
		InputStream gabarit = null;
		JRDataSource dataSource = null;
		Map parameterMap = request.getParameterMap();
		CriteresRechercheAdressesVO criteresRechercheAdressesVO = (CriteresRechercheAdressesVO) ValueObjectMapper.construireParameterMap(parameterMap, new CriteresRechercheAdressesVO());
		
		if (GlobalConstants.ChoixRechercheAdresse.SOCIETE.equals( obtenirPremiereString(parameterMap,ENTITE_RECHERCHE) )){
			gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_VALIDATION_ADRESSES_SOCIETES);
			dataSource = rechercheAdresseSociete(subject, criteresRechercheAdressesVO);				
			
		}else if (GlobalConstants.ChoixRechercheAdresse.SUJET.equals( obtenirPremiereString(parameterMap,ENTITE_RECHERCHE) )){
			gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_VALIDATION_ADRESSES_SUJETS);
			dataSource = rechercheAdresseSujet(subject, criteresRechercheAdressesVO);
		}
		Map parameters = contruireParametres(subject, parameterMap);
		
		return JasperFillManager.fillReport(gabarit, parameters, dataSource);
	}

	// numeroFiche / nom / adresse
	private JRDataSource rechercheAdresseSociete(CardexAuthenticationSubject subject, CriteresRechercheAdressesVO criteresRechercheAdressesVO) throws BusinessResourceException, BusinessException {
		List list = new ArrayList();
		SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
		Collection listeSociete = delegate.rechercheAdresseInvalideSociete(subject, criteresRechercheAdressesVO);
		Iterator iter = listeSociete.iterator();
		
        while (iter.hasNext()) {
            SocieteVO societe = (SocieteVO)iter.next();
            Iterator iterAdresse = societe.getAdresses().iterator();
            
            while (iterAdresse.hasNext()) {
				Adresse adresse = (Adresse) iterAdresse.next();
				Map mapRange = new HashMap();
				mapRange.put("nom", societe.getNom());
				mapRange.put("numeroFiche", societe.getNumeroFiche());
				mapRange.put("adresse", obtenirLigneAdresse(subject, adresse));
				String message = StringUtils.replace(adresse.getMessage(), "</br>", "<br>");
				mapRange.put("message", message);
	        	list.add(mapRange);				
			}
        }		

		return new JRMapCollectionDataSource(list);
	}
	// numeroFiche / nom / prenom / adresse
	private JRDataSource rechercheAdresseSujet(CardexAuthenticationSubject subject, CriteresRechercheAdressesVO criteresRechercheAdressesVO) throws BusinessResourceException, BusinessException {
		List list = new ArrayList();
		SujetBusinessDelegate delegate = new SujetBusinessDelegate();
		Collection listeSujet = delegate.rechercheAdresseInvalideSujet(subject, criteresRechercheAdressesVO);
		Iterator iter = listeSujet.iterator();
		
        while (iter.hasNext()) {
        	SujetVO sujet = (SujetVO)iter.next();
            Iterator iterAdresse = sujet.getAdresses().iterator();
            
            while (iterAdresse.hasNext()) {
				Adresse adresse = (Adresse) iterAdresse.next();
				Map mapRange = new HashMap();
				mapRange.put("nom", sujet.getNom());
				mapRange.put("prenom", sujet.getNom());
				mapRange.put("numeroFiche", sujet.getNumeroFiche());
				mapRange.put("adresse", obtenirLigneAdresse(subject, adresse));
				String message = StringUtils.replace(adresse.getMessage(), "</br>", "<br>");
				mapRange.put("message", message);				
	        	list.add(mapRange);				
			}
        }		

		return new JRMapCollectionDataSource(list);
	}

	private String obtenirLigneAdresse(CardexAuthenticationSubject subject, Adresse adresse) throws BusinessResourceException{
		adresse.assignerDescription(subject);
		return adresse.getLigneAdresseAE()+" "+adresse.getProvinceDescription()+" "+adresse.getPaysDescription()+" "+adresse.getCodePostal();
	}
	
	private Map contruireParametres(CardexAuthenticationSubject subject, Map parameterMap) {
		CardexUser cardexUser = (CardexUser) subject.getUser();
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		String entite = "";
		
		try {
			site = listeCache.obtenirLabel(subject, obtenirPremiereString(parameterMap,SITE_ORIGINE), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
			entite = listeCache.obtenirLabel(subject, obtenirPremiereString(parameterMap,ENTITE), new EntiteCleMultiListeCache(subject));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		
		Map parameters = new HashMap();
		parameters.put("SiteOrigine", site);
		parameters.put("DATE_DEBUT", obtenirPremiereString(parameterMap, DATE_CREATION_DU));
		parameters.put("DATE_FIN", obtenirPremiereString(parameterMap, DATE_CREATION_AU));
		parameters.put("Entite", entite);
		parameters.put("UTILISATEUR", cardexUser.getCode());
		return parameters;
	}
	
	private static String obtenirPremiereString(Map parameterMap, String key){
		String[] arrayString = (String[]) parameterMap.get(key);
		return arrayString[0];
	}

	public RapportVO construireNouveauRapportVO() {
		// TODO Auto-generated method stub
		return null;
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject,
			RapportVO rapportVO, Connection connection) throws BusinessResourceException,
			BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InputStream obtenirGabarit() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
