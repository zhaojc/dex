package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class AutoexclusionEspaceJeuxDossierGenerateurRapport_CDX_0221 extends GenererRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUTOEXCLUSION_ESPACEJEUX);
	}
	
	public RapportVO construireNouveauRapportVO() {
		return new EntiteRapportVO();
	}
	
	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent à ceux du rapport.
	private List construireListeDataSource(CardexAuthenticationSubject subject, Dossier dossier, Map mapRapportDossier)
	 			throws BusinessException{
		List list = new ArrayList();
    	ListeCache cache = ListeCache.getInstance();

    	//On ajoute les informations du dossier
        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
        mapRapportDossier.put("dateDebut", StringUtils.substring(dossier.getDateDebut().toString(),0,10));
        mapRapportDossier.put("dateFin", StringUtils.substring(dossier.getDateFin().toString(),0,10));
        mapRapportDossier.put("intervenantDescription", dossier.getIntervenantDescription());

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va chercher le sujet relié
        Collection liensSujets;
        Iterator it;
	        liensSujets = delegate.findLiensSujet(subject, dossier);
	        it = liensSujets.iterator();
	        if(it.hasNext()) {
	            Sujet linkSujet = (Sujet) it.next();
	            //On passe la clé et le site du sujet pour le sous-rapport
				mapRapportDossier.put("sujetCle", BigDecimal.valueOf(linkSujet.getCle()));
				mapRapportDossier.put("sujetSite", BigDecimal.valueOf(linkSujet.getSite()));
			}

	        list.add(mapRapportDossier);
	        
		return list;
	}

	private Map construireListeLibelles(Dossier dossier)
		throws BusinessException{
		List list = new ArrayList();
		//On remplit d'abord les libellés
		Map mapLibelles = new HashMap(); 
		
		mapLibelles.put("titre", bundle.getString("titreAE"));
		mapLibelles.put("confidentiel", bundle.getString("confidentiel"));
		mapLibelles.put("societe", bundle.getString("societeEJ"));
		mapLibelles.put("enonce", bundle.getString("enonceEJ"));
		mapLibelles.put("demande", bundle.getString("demandeEJ"));
		mapLibelles.put("libelleDemandeDebut", bundle.getString("libelle.demandeEJ")); 
		mapLibelles.put("libellePeriode", bundle.getString("libelle.periode"));
		mapLibelles.put("au", bundle.getString("au_t"));
		mapLibelles.put("libelleCondition1", bundle.getString("libelle.conditionEJ"));
		mapLibelles.put("libelleCondition2", bundle.getString("acceptationEJ"));
		mapLibelles.put("remarque", bundle.getString("cocherEJ"));
		mapLibelles.put("signatures", bundle.getString("signatures"));
	
		return mapLibelles;
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("sous_rapport_sujet_autoexclusion", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_AUTOEXCLUSION_SUJET_FRANCAIS)));
		parameters.put("sous_rapport_sujet_autoexclusion_anglais", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_AUTOEXCLUSION_SUJET_ANGALAIS)));
		parameters.put("REPORT_CONNECTION", connection);
		parameters.put("langue", locale.toString());
		
		return parameters;
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		List list = new ArrayList();
		EntiteRapportVO entiteRapportVO = (EntiteRapportVO) rapportVO;
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
       	Dossier dossierVO = new DossierVO();
       	dossierVO.setCle(entiteRapportVO.getCle());
       	dossierVO.setSite(entiteRapportVO.getSite());
		Dossier dossier = delegate.find(subject, dossierVO);
		
		//On commence par construire le contenu des libellés, selon la langue demandée pour le contrat.
		Map mapRapportDossier = construireListeLibelles(dossier);
		//On ajout ensuite les champs qui seront imprimés sur le contrat
		list.addAll(construireListeDataSource(subject, dossier, mapRapportDossier));
	
		return new JRMapCollectionDataSource(list);

	}

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.DOSSIER, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), "486");
	}



}
