package com.lotoquebec.cardex.generateurRapport.regroupement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import com.lotoquebec.cardex.business.delegate.RegroupementBusinessDelegate;
import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.IntervenantEndroitRegroupementRapportVO_CDX_0085;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.rapport.TraiterCollectionUtils;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class IntervenantEndroitRegroupementGenerateurRapport_CDX_0085 extends RegroupementGenerateurRapport {
	
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_REGROUPEMENTS_INTERVENANT_ENDROIT);
	}

	protected ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject,
			RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
		RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
		return delegate.produireRapportIntervenantEndroit(subject, criteresRechercheRegroupement);
	}

	protected void trier(PresentationRegroupementRapport regroupementRapportVO) {
		regroupementRapportVO.trierIntervenantNomRegroupementEndroit();
	}

	protected JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO) {
		List list = new ArrayList();
		list.addAll(construireListeDataSource(regroupementRapportVO.getListeResultatPresentationRegroupement()));

		return new JRMapCollectionDataSource(list);
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new IntervenantEndroitRegroupementRapportVO_CDX_0085();
	}
	
	private List construireListeDataSource(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
            ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
            Map mapRegroupement = new HashMap();
            mapRegroupement.put("Intervenant", resultatPresentationRegroupement.getIntervenant());
            mapRegroupement.put("Regroupement", "");
            mapRegroupement.put("Endroit", "");
            mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
            mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
            list.add(mapRegroupement);
            list.addAll(construireListeDataSourceRegroupement(resultatPresentationRegroupement.getListeSubResultatPresentationRegroupement()));
		}
		return list;
	}

	private List construireListeDataSourceRegroupement(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
            ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
            Map mapRegroupement = new HashMap();
            mapRegroupement.put("Intervenant", "");
            mapRegroupement.put("Regroupement", resultatPresentationRegroupement.getNomRegroupement());
            mapRegroupement.put("Endroit", "");
            mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
            mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
            list.add(mapRegroupement);
            list.addAll(construireListeDataSourceSousEndroit(resultatPresentationRegroupement.getListeSubResultatPresentationRegroupement()));
		}
		return list;
	}	
	
	private List construireListeDataSourceSousEndroit(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
            ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
            Map mapRegroupement = new HashMap();
            mapRegroupement.put("Regroupement", "");
            mapRegroupement.put("Intervenant", "");
            mapRegroupement.put("Endroit", resultatPresentationRegroupement.getEndroit());
            mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
            mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
            list.add(mapRegroupement);
		}
		return list;
	}	

	protected void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO) {
		List listDiscriminant = new ArrayList();
		listDiscriminant.add( discriminantIntervenant );
		listDiscriminant.add( discriminantRegroupement );
		TraiterCollectionUtils.ajouterSousCollection(regroupementRapportVO.getListeResultatPresentationRegroupement(), listDiscriminant);		
	}

	protected void assignerPremiereColonne(ResultatPresentationRegroupement resultatRegroupement, String strTempsNonComptabilise) {
	    resultatRegroupement.setNomIntervenant(strTempsNonComptabilise);
	}
	
	@Override
	protected void convertirResultat(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement) {
		RapportConvertisseur.convertirResultat(regroupementRapportVO, regroupement);
	}
	
	@Override
	protected void ajouterTempsNonComptabilise( CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO) {
		RapportConvertisseur.ajouterTempsNonComptabilise(subject, bundle, regroupementRapportVO);
		
		// TODO : retirer cette patch faite pour la V5.7
		// Faire une section somaire
		if (regroupementRapportVO.getListeResultatPresentationRegroupement().size() > 0){
    		ResultatPresentationRegroupement tempsNonComptabiliseRPR = (ResultatPresentationRegroupement) regroupementRapportVO.getListeResultatPresentationRegroupement().get(regroupementRapportVO.getListeResultatPresentationRegroupement().size()-1);
    		tempsNonComptabiliseRPR.setNomIntervenant( tempsNonComptabiliseRPR.getNomRegroupement() );
		}
	}

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/regroupementIntervenantEndroit");
	}

	
}
