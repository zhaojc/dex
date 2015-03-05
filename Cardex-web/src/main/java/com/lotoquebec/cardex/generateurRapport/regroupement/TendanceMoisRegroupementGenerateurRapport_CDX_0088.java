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
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.TendanceMoisRegroupementRapportVO_CDX_0088;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.rapport.TraiterCollectionUtils;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class TendanceMoisRegroupementGenerateurRapport_CDX_0088 extends RegroupementGenerateurRapport {

	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_REGROUPEMENTS_TENDANCE_MOIS);
	}

	protected ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject,
			RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
		RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
		return delegate.produireRapportTendanceMois(subject, criteresRechercheRegroupement);
	}

	protected void trier(PresentationRegroupementRapport regroupementRapportVO) {
		regroupementRapportVO.trierMoisNombreRegroupement();
	}

	protected JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO) {
		List list = new ArrayList();
		list.addAll(construireListeDataSource(regroupementRapportVO.getListeResultatPresentationRegroupement()));

		return new JRMapCollectionDataSource(list);
	}

	@Override
	public RapportVO construireNouveauRapportVO() {
		return new TendanceMoisRegroupementRapportVO_CDX_0088();
	}
	
	private List construireListeDataSource(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("Mois_Lettres",resultatPresentationRegroupement.getMoisLettres());
			mapRegroupement.put("Mois_Nombre", "");
			mapRegroupement.put("Cle_Regroupement", Long.valueOf(0));
			mapRegroupement.put("Regroupement", "");
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", Double.valueOf(0));
			mapRegroupement.put("Quota", Double.valueOf(0));
			mapRegroupement.put("Ecart", Double.valueOf(0));
			
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
			mapRegroupement.put("Mois_Lettres", "");
			mapRegroupement.put("Mois_Nombre",resultatPresentationRegroupement.getMoisNombre());
			mapRegroupement.put("Cle_Regroupement", resultatPresentationRegroupement.getCle());
			mapRegroupement.put("Regroupement", resultatPresentationRegroupement.getNomRegroupement());
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", resultatPresentationRegroupement.getPourcentage()*0.01);
			mapRegroupement.put("Quota", resultatPresentationRegroupement.getQuota());
			mapRegroupement.put("Ecart", resultatPresentationRegroupement.getEcart());
			
			list.add(mapRegroupement);
		}
		return list;
	}	
	
	protected void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO) {
		TraiterCollectionUtils.ajouterSousCollection(regroupementRapportVO.getListeResultatPresentationRegroupement(), discriminantAnneeMois);
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
	}

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/regroupementTendanceMois");
	}
	
}
