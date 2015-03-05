package com.lotoquebec.cardex.generateurRapport.regroupement;

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

import com.lotoquebec.cardex.business.delegate.RegroupementBusinessDelegate;
import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.IntervenantRegroupementRapportVO_CDX_0086;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.rapport.TraiterCollectionUtils;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class IntervenantRegroupementGenerateurRapport_CDX_0086 extends RegroupementGenerateurRapport {

	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_REGROUPEMENTS_INTERVENANT);
	}

	protected ResultatRegroupementVO produireRapport(CardexAuthenticationSubject subject,
			RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
		RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
		return delegate.produireRapportIntervenant(subject, criteresRechercheRegroupement);
	}

	protected void trier(PresentationRegroupementRapport regroupementRapportVO) {
		regroupementRapportVO.trierIntervenantNomRegroupement();
	}

	protected JRDataSource construireDataSource(PresentationRegroupementRapport regroupementRapportVO) {
		List list = new ArrayList();
		list.addAll(construireListeDataSource(regroupementRapportVO.getListeResultatPresentationRegroupement()));

		return new JRMapCollectionDataSource(list);
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new IntervenantRegroupementRapportVO_CDX_0086();
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
			mapRegroupement.put("Pourcentage", resultatPresentationRegroupement.getPourcentage()/100);
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
			mapRegroupement.put("TotalHeures", resultatPresentationRegroupement.getHeureFormate());
			mapRegroupement.put("Pourcentage", resultatPresentationRegroupement.getPourcentage()/100);
			mapRegroupement.put("Quota", resultatPresentationRegroupement.getQuota()/100);
			mapRegroupement.put("Ecart", resultatPresentationRegroupement.getEcart()/100);
			list.add(mapRegroupement);
		}
		return list;
	}	

	protected void ajouterSousCollection(PresentationRegroupementRapport regroupementRapportVO) {
		TraiterCollectionUtils.ajouterSousCollection(regroupementRapportVO.getListeResultatPresentationRegroupement(), discriminantIntervenant);		
	}

	protected void assignerPremiereColonne(ResultatPresentationRegroupement resultatRegroupement, String strTempsNonComptabilise) {
		resultatRegroupement.setNomIntervenant(strTempsNonComptabilise);
	}
	
	@Override
	protected void convertirResultat(CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO, ResultatRegroupementVO regroupement) {
		RapportConvertisseur.convertirResultatAjoutTempsNonComptabiliseParIntervenant(subject, bundle, regroupementRapportVO, regroupement);
	}
	
	@Override
	protected void ajouterTempsNonComptabilise( CardexAuthenticationSubject subject, PresentationRegroupementRapport regroupementRapportVO) {
	}

	@Override
	protected void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/regroupementIntervenant");
	}
	
}
