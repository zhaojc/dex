package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.RapportDossier;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.RapportDossierVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.CumulatifDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class HebdomadaireDossierGenerateurRapport_CDX_0146 extends CritereGenererRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_HEBDOMADAIRE);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersHebdomadaire");
	}
	
	public CritereRapportVO construireNouveauRapportVO() {
		return new CumulatifDossierRapportVO();
	}

	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException,BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		CumulatifDossierRapportVO cumulatifDossierRapportVO = (CumulatifDossierRapportVO) rapportVO;
		//On conserve la date initiale pour la retourner en param�tre au rapport
		Date dateDebut = cumulatifDossierRapportVO.getDateDebutDu();
		//On commence d'abord par construire la liste des types et des cat�gories avec le total de dossiers.
		RapportDossier rapportDossier = delegate.produireListeTypeCategorie(cumulatifDossierRapportVO);
		List list = new ArrayList();
		Iterator iter = rapportDossier.getListDossier().iterator();
		try{
			while (iter.hasNext()) {
				RapportDossierVO rapportDossierVO = (RapportDossierVO) iter.next();
				Map mapRapportDossier = new HashMap();
				mapRapportDossier.put("Type", rapportDossierVO.getType());
				mapRapportDossier.put("Categorie", rapportDossierVO.getCategorie());
				mapRapportDossier.put("nombreDossier", rapportDossierVO.getNombreDossier());
				//Pour chaque entr�e, on calcule les valeurs cumulatives � partir du d�but de l'ann�e
				//et qui seront report�es sur le rapport.
			    SimpleDateFormat formater = null;
			    formater = new SimpleDateFormat("yyyy");
			    String date = formater.format(cumulatifDossierRapportVO.getDateDebutDu());
			    cumulatifDossierRapportVO.setDateDebutDu(DateFormat.parse(date + "-01-01"));
				mapRapportDossier.put("nombreDossierCumul", delegate.produireDossierCumul(rapportDossierVO, cumulatifDossierRapportVO));
				list.add(mapRapportDossier);
			}
			cumulatifDossierRapportVO.setDateDebutDu(dateDebut);
		}catch(ParseException e) {
	    	e.printStackTrace();
		}
		return new JRMapCollectionDataSource(list);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		CumulatifDossierRapportVO cumulatifDossierRapportVO = (CumulatifDossierRapportVO) rapportVO;
		CardexUser cardexUser = (CardexUser) subject.getUser();
		ListeCache listeCache = ListeCache.getInstance();
		String site = "";
		String genre = "";
		try {
			site = listeCache.obtenirLabel(subject, String.valueOf(cumulatifDossierRapportVO.getSite()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
			genre = listeCache.obtenirLabel(subject, String.valueOf(cumulatifDossierRapportVO.getGenre()), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER));
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
		parameters.put("DateDebut", StringUtils.substring(cumulatifDossierRapportVO.getDateDebutDu().toString(),0,10));
		parameters.put("DateFin", StringUtils.substring(cumulatifDossierRapportVO.getDateDebutAu().toString(),0,10));
		parameters.put("Genre", genre);
		parameters.put("UTILISATEUR", cardexUser.getCode());
		parameters.put("SiteOrigine", site);
		return parameters;
	}
	
}
