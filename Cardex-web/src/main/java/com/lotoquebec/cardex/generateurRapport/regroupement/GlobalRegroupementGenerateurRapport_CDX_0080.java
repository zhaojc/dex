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
import com.lotoquebec.cardex.business.vo.rapport.regroupement.GlobalRegroupementRapportVO_CDX_0080;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class GlobalRegroupementGenerateurRapport_CDX_0080 extends RegroupementGenerateurRapport {
 
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_GLOBAL_REGROUPEMENTS);
	}

	protected ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject,
			RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
		RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
		return delegate.produireRapportGlobal(subject, criteresRechercheRegroupement);
	}

	protected void trier(PresentationRegroupementRapport regroupementRapportVO) {
		regroupementRapportVO.trierNomRegroupement();
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new GlobalRegroupementRapportVO_CDX_0080();
	}

	protected JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO) {
		List list = new ArrayList();
		Iterator iter = regroupementRapportVO.getListeResultatPresentationRegroupement().iterator();
		
		while (iter.hasNext()) {
			ResultatPresentationRegroupement resultatPresentationRegroupement = (ResultatPresentationRegroupement) iter.next();
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("Regroupement", resultatPresentationRegroupement.getNomRegroupement());
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", resultatPresentationRegroupement.getPourcentage()/100);
			mapRegroupement.put("Quota", resultatPresentationRegroupement.getQuota()/100);
			mapRegroupement.put("Ecart", resultatPresentationRegroupement.getEcart()/100);
			list.add(mapRegroupement);
		}
		
		/*if (list.size() == 0){
			Map mapRegroupement = new HashMap();
			mapRegroupement.put("Regroupement", "");
			mapRegroupement.put("TotalHeures", "");
			mapRegroupement.put("Pourcentage", new Double(0));
			mapRegroupement.put("Quota", "");
			mapRegroupement.put("Ecart", "");
			list.add(mapRegroupement);
		}*/
		
		return new JRMapCollectionDataSource(list);
	}

	protected void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO) {
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

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/regroupementGlobal");
	}
	
}
