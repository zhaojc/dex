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
import com.lotoquebec.cardex.business.vo.rapport.regroupement.IntervenantCategorieRegroupementRapportVO_CDX_0084;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.rapport.TraiterCollectionUtils;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class IntervenantCategorieRegroupementGenerateurRapport_CDX_0084 extends RegroupementGenerateurRapport {

	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_REGROUPEMENTS_INTERVENANT_CATEGORIE);
	}

	protected ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject,
			RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
		RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
		return delegate.produireRapportIntervenantCategorie(subject, criteresRechercheRegroupement);
	}

	protected void trier(PresentationRegroupementRapport regroupementRapportVO) {
		regroupementRapportVO.trierNomRegroupementIntervenantTypeCategorie();
	}

	protected JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO) {
		List list = new ArrayList();
		list.addAll(construireListeDataSource(regroupementRapportVO.getListeResultatPresentationRegroupement()));

		return new JRMapCollectionDataSource(list);
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new IntervenantCategorieRegroupementRapportVO_CDX_0084();
	}
	
	private List construireListeDataSource(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("Regroupement", resultatPresentationRegroupement.getNomRegroupement());
			mapRegroupement.put("Intervenant", "");
			mapRegroupement.put("Categorie", "");
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
			list.add(mapRegroupement);
			list.addAll(construireListeDataSourceIntervenant(resultatPresentationRegroupement.getListeSubResultatPresentationRegroupement()));
		}
		return list;
	}

	private List construireListeDataSourceIntervenant(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("Regroupement", "");
			mapRegroupement.put("Intervenant", resultatPresentationRegroupement.getIntervenant());
			mapRegroupement.put("Categorie", "");
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
			list.add(mapRegroupement);
			list.addAll(construireListeDataSourceSousCategorie(resultatPresentationRegroupement.getListeSubResultatPresentationRegroupement()));
		}
		return list;
	}	
	
	private List construireListeDataSourceSousCategorie(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("Regroupement", "");
			mapRegroupement.put("Intervenant", "");
			mapRegroupement.put("Categorie", resultatPresentationRegroupement.getType()+" - "+resultatPresentationRegroupement.getCategorie());
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
			list.add(mapRegroupement);
		}
		return list;
	}	

	protected void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO) {
		List listDiscriminant = new ArrayList();
		listDiscriminant.add( discriminantRegroupement );
		listDiscriminant.add( discriminantIntervenant );
		TraiterCollectionUtils.ajouterSousCollection(regroupementRapportVO.getListeResultatPresentationRegroupement(), listDiscriminant);		
	}

	protected void assignerPremiereColonne(ResultatPresentationRegroupement resultatRegroupement, String strTempsNonComptabilise) {
		resultatRegroupement.setNomRegroupement(strTempsNonComptabilise);
	}

	@Override
	protected void convertirResultat(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement) {
		RapportConvertisseur.convertirResultat(regroupementRapportVO, regroupement);
	}
	
	@Override
	protected void ajouterTempsNonComptabilise( CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO) {
		RapportConvertisseur.ajouterTempsNonComptabilise(subject, bundle, regroupementRapportVO);
	}

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/regroupementIntervenantCategorie");
	}

}
