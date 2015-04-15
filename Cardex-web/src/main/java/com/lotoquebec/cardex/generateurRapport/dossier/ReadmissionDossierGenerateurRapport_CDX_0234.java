package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EntiteRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class ReadmissionDossierGenerateurRapport_CDX_0234 extends CritereGenererRapport {
 
	private boolean tousCasinoEtLudoplex = false;
	
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.READMISSION);
	}

	//Construction de la liste qui sera soumise au rapport. Les champs du map correspondent � ceux du rapport.
	private List construireListeDataSource(CardexAuthenticationSubject subject, Dossier dossier, Map mapRapportDossier)
	 			throws BusinessException{
		List list = new ArrayList();
    	ListeCache cache = ListeCache.getInstance();

    	//On ajoute les informations du dossier
        mapRapportDossier.put("numeroDossier", dossier.getNumeroDossier());
        mapRapportDossier.put("numeroCardex", dossier.getNumeroCardex());
        mapRapportDossier.put("duree", dossier.getDuree());
        mapRapportDossier.put("dateDebut", StringUtils.substring(dossier.getDateDebut().toString(),0,10));

		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		//On va chercher le sujet reli�
        Collection liensSujets;
        Iterator it;
	        liensSujets = delegate.findLiensSujet(subject, dossier);
	        it = liensSujets.iterator();
	        if(it.hasNext()) {
	            Sujet linkSujet = (Sujet) it.next();
	            //On passe la cl� et le site du sujet pour le sous-rapport
				mapRapportDossier.put("nom", linkSujet.getNom());
				mapRapportDossier.put("prenom", linkSujet.getPrenom());
	        }
	        list.add(mapRapportDossier);
		return list;
	}
	
	public CritereRapportVO construireNouveauRapportVO() {
		return new EntiteRapportVO();
	}
	
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.DOSSIER, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), "550");
	}
	
	@Override
	protected JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		List list = new ArrayList();
		EntiteRapportVO entiteRapportVO = (EntiteRapportVO) rapportVO;
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
       	Dossier dossierVO = new DossierVO();
       	dossierVO.setCle(entiteRapportVO.getCle());
       	dossierVO.setSite(entiteRapportVO.getSite());
		Dossier dossier = delegate.find(subject, dossierVO);

		Map mapRapportDossier = new HashMap();
		//On ajoute les champs qui seront imprim�s sur le contrat
		list.addAll(construireListeDataSource(subject, dossier, mapRapportDossier));

		return new JRMapCollectionDataSource(list);
	}



}
