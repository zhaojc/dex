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
import com.lotoquebec.cardex.business.vo.rapport.regroupement.MatriceRegroupementRapportVO_CDX_0160;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class MatriceRegroupementGenerateurRapport_CDX_0160 extends RegroupementGenerateurRapport {

	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_MATRICE_REGROUPEMENTS);
	}

	protected ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject,
			RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
		RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
		return delegate.produireRapportMatriceRegroupement(subject, criteresRechercheRegroupement);
	}

	protected void trier(PresentationRegroupementRapport regroupementRapportVO) {
		//regroupementRapportVO.trierCategorie();
	}

	protected JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO) {
		List list = new ArrayList();
		list.addAll(construireListeDataSource(regroupementRapportVO.getListeResultatPresentationRegroupement()));

		return new JRMapCollectionDataSource(list);
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new MatriceRegroupementRapportVO_CDX_0160();
	}
	
	private List construireListeDataSource(List listeResultatPresentationRegroupement) {
		List list = new ArrayList();
		Iterator iter = listeResultatPresentationRegroupement.iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("CATEGORIE", resultatPresentationRegroupement.getCategorie());
			mapRegroupement.put("REGROUPEMENT", resultatPresentationRegroupement.getNomRegroupement());
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
			mapRegroupement.put("Endroit", "");
			mapRegroupement.put("Regroupement", resultatPresentationRegroupement.getNomRegroupement());
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", new Double(resultatPresentationRegroupement.getPourcentage()*0.01));
			list.add(mapRegroupement);
		}
		return list;
	}	

	protected void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO) {
		//TraiterCollectionUtils.ajouterSousCollection(regroupementRapportVO.getListeResultatPresentationRegroupement(), discriminantEndroit);		
	}

	protected void assignerPremiereColonne(ResultatPresentationRegroupement resultatRegroupement, String strTempsNonComptabilise) {
		resultatRegroupement.setEndroit(strTempsNonComptabilise);
	}
	
	@Override
	protected void convertirResultat(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement) {
		RapportConvertisseur.convertirResultat(regroupementRapportVO, regroupement);
	}

	@Override
	protected void ajouterTempsNonComptabilise(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO) {
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/regroupementMatrice");
	}

	
}
