package com.lotoquebec.cardex.generateurRapport.acces;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.entite.SujetRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class SujetAuditChangementGenerateurRapport_CDX_0185 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.audit");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		SujetBusinessDelegate delegate = new SujetBusinessDelegate();
		List<Sujet> sujets = delegate.audit(subject, (Sujet)vo);
		List<SujetRapportVO> rapportVOs = convertir(subject, (Sujet)vo, sujets);
		List mapList = new ArrayList();

		try{

			for(SujetRapportVO sujetRapportVO:rapportVOs){
				Map sujetMap = BeanUtils.describe(sujetRapportVO);
				mapList.add(sujetMap);
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
			
		
		return new JRMapCollectionDataSource(mapList);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUDIT_CHANGEMENTS_SUJETS_CDX_0185);
	}

	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
		ListeCache cache = ListeCache.getInstance();
		
		try {
			Sujet sujet = (Sujet) vo;
			String createurDescription = cache.obtenirLabel(subject, sujet.getCreateur(), new IntervenantCle(subject));
			parameters.put("NUMERO_FICHE", sujet.getNumeroFiche());
			parameters.put("CREATEUR", createurDescription);
			parameters.put("DATE_CREATION", DateFormat.format(sujet.getDateCreation()));
			parameters.put("SOUS_RAPPORT_AVANT_CHANGEMENT_AUDIT_CHANGEMENTS_SUJETS_CDX_0185", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_AVANT_CHANGEMENT_AUDIT_CHANGEMENTS_SUJETS_CDX_0185));
			parameters.put("SOUS_RAPPORT_CREATEUR_AUDIT_CHANGEMENT", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_CREATEUR_AUDIT_CHANGEMENT));
					
			return parameters;
		} catch (BusinessResourceException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	 private List<SujetRapportVO> convertir(CardexAuthenticationSubject subject, Sujet courant, List<Sujet> sujets) throws BusinessResourceException {
		List<SujetRapportVO> rapportVOs = new ArrayList<SujetRapportVO>();
		//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
		//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
		//le rapport soient identiques au "describe" des champs de la fonction.
	    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
		SujetRapportVO courantSujetRapportVO = new SujetRapportVO(courant);
		courantSujetRapportVO.assignerValeurDeListe(subject);
		rapportVOs.add(courantSujetRapportVO);
		
		for(int i=1;i<sujets.size();i++){
			Sujet sujet = sujets.get(i);
			SujetRapportVO precedentSujetRapportVO = rapportVOs.get(i-1);
			SujetRapportVO sujetRapportVO = new SujetRapportVO(sujet);
			sujetRapportVO.assignerValeurDeListe(subject);
			sujetRapportVO.setNumeroAssuranceSociale("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;********"); //On camoufle l'information du NAS
			sujetRapportVO.setSeveriteDescriptionAutres(RapportUtils.concateneFleche(sujetRapportVO.getSeveriteDescriptionAutres(), precedentSujetRapportVO.getSeveriteDescriptionAutres()));
			sujetRapportVO.setSexeDescription(RapportUtils.concateneFleche(sujetRapportVO.getSexeDescription(), precedentSujetRapportVO.getSexeDescription()));
			sujetRapportVO.setLangueDescription(RapportUtils.concateneFleche(sujetRapportVO.getLangueDescription(), precedentSujetRapportVO.getLangueDescription()));
			sujetRapportVO.setNom(RapportUtils.concateneFleche(sujetRapportVO.getNom(), precedentSujetRapportVO.getNom()));
			sujetRapportVO.setPrenom(RapportUtils.concateneFleche(sujetRapportVO.getPrenom(), precedentSujetRapportVO.getPrenom()));
		    sujetRapportVO.setAlias(RapportUtils.concateneFleche(sujetRapportVO.getAlias().toString(), precedentSujetRapportVO.getAlias().toString()));
		    sujetRapportVO.setStatutDescription(RapportUtils.concateneFleche(sujetRapportVO.getStatutDescription(), precedentSujetRapportVO.getStatutDescription()));
		    sujetRapportVO.setDateNaissance(RapportUtils.concateneFleche(sujetRapportVO.getDateNaissance(), precedentSujetRapportVO.getDateNaissance()));
		    sujetRapportVO.setNumeroPermisConduire(RapportUtils.concateneFleche(sujetRapportVO.getNumeroPermisConduire(), precedentSujetRapportVO.getNumeroPermisConduire()));
		    sujetRapportVO.setReference1(RapportUtils.concateneFleche(sujetRapportVO.getReference1(), precedentSujetRapportVO.getReference1()));
		    sujetRapportVO.setReference2(RapportUtils.concateneFleche(sujetRapportVO.getReference2(), precedentSujetRapportVO.getReference2()));
		    sujetRapportVO.setNumeroFiche(RapportUtils.concateneFleche(sujetRapportVO.getNumeroFiche(), precedentSujetRapportVO.getNumeroFiche()));
		    sujetRapportVO.setTypeAgeDescription(RapportUtils.concateneFleche(sujetRapportVO.getTypeAgeDescription(), precedentSujetRapportVO.getTypeAgeDescription()));
		    sujetRapportVO.setRaceDescription(RapportUtils.concateneFleche(sujetRapportVO.getRaceDescription(), precedentSujetRapportVO.getRaceDescription()));
		    sujetRapportVO.setPasseport(RapportUtils.concateneFleche(sujetRapportVO.getPasseport(), precedentSujetRapportVO.getPasseport()));
		    sujetRapportVO.setNumeroAssuranceMaladie(RapportUtils.concateneFleche(sujetRapportVO.getNumeroAssuranceMaladie(), precedentSujetRapportVO.getNumeroAssuranceMaladie()));
		    sujetRapportVO.setSeveriteDescription(RapportUtils.concateneFleche(sujetRapportVO.getSeveriteDescription(), precedentSujetRapportVO.getSeveriteDescription()));
		    sujetRapportVO.setSeveriteCasinoDescription(RapportUtils.concateneFleche(sujetRapportVO.getSeveriteCasinoDescription(), precedentSujetRapportVO.getSeveriteCasinoDescription()));
		    sujetRapportVO.setConfidentialiteDescription(RapportUtils.concateneFleche(sujetRapportVO.getConfidentialiteDescription(), precedentSujetRapportVO.getConfidentialiteDescription()));
		    sujetRapportVO.setNumeroClientEmploye(RapportUtils.concateneFleche(sujetRapportVO.getNumeroClientEmploye(), precedentSujetRapportVO.getNumeroClientEmploye()));
		    sujetRapportVO.setEthnieDescription(RapportUtils.concateneFleche(sujetRapportVO.getEthnieDescription(), precedentSujetRapportVO.getEthnieDescription()));
			rapportVOs.add(sujetRapportVO);
		}
		rapportVOs.remove(0);
		return rapportVOs;
	 }
		  
}
