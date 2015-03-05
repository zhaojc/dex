package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
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
import com.lotoquebec.cardex.business.vo.rapport.CumulatifDossierRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ValueObjectMapper;

public class CumulatifDossierGenerateurRapport_CDX_0147 extends GenererRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_CUMULATIF);
	}

	public RapportVO construireNouveauRapportVO() {
		return new CumulatifDossierRapportVO();
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersCumulatif");
	}

	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		CumulatifDossierRapportVO cumulatifDossierRapportVO = (CumulatifDossierRapportVO) rapportVO;
		//On commence d'abord par construire la liste des types et des catégories avec le total de dossiers.
		RapportDossier rapportDossier = delegate.produireListeTypeCategorie(cumulatifDossierRapportVO);
		
		List list = new ArrayList();
		Iterator iter = rapportDossier.getListDossier().iterator();
		
		while (iter.hasNext()) {
			RapportDossierVO rapportDossierVO = (RapportDossierVO) iter.next();
			Map mapRapportDossier = new HashMap();
			mapRapportDossier.put("Type", rapportDossierVO.getType());
			mapRapportDossier.put("Categorie", rapportDossierVO.getCategorie());
			mapRapportDossier.put("nombreDossier", rapportDossierVO.getNombreDossier());
			//Pour chaque entrée, on calcule les autres valeurs qui seront reportées sur le rapport.
			mapRapportDossier.put("nombreFonde", delegate.produireFonde(rapportDossierVO, cumulatifDossierRapportVO, GlobalConstants.Fonde.OUI));
			mapRapportDossier.put("nombreNonFonde", delegate.produireFonde(rapportDossierVO, cumulatifDossierRapportVO, GlobalConstants.Fonde.NON));
			mapRapportDossier.put("nombreIndetermine", delegate.produireFonde(rapportDossierVO, cumulatifDossierRapportVO, GlobalConstants.Fonde.INDETERMINE));
			//Le nombre de dossiers aux enquêtes n'est valable que pour le site Espacejeux.
			if(cumulatifDossierRapportVO.getSite() == Long.valueOf(GlobalConstants.SiteMaisonJeux.ESPACEJEUX)){
				mapRapportDossier.put("nombreAuxEnquetes", delegate.produireAuxEnquetes(rapportDossierVO, cumulatifDossierRapportVO));
			}
			list.add(mapRapportDossier);
		}

		return new JRMapCollectionDataSource(list);
	}
	
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
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
