package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.generateurRapport.GenererRapportForm;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class ResultatRechercheAvecSujetDossierGenerateurRapport_CDX_0224 extends GenererRapportForm {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		//GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.base.imprimer");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, ValidatorForm validatorForm, Connection connection) throws BusinessResourceException, BusinessException {
		CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) validatorForm;
        return new JRMapCollectionDataSource(remplirResultatDossiers(criteresRechercheDossierForm.getListeResultat().getResultatComplet()));
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RESULTATS_RECHERCHE_DOSSIERS_AVEC_SUJETS_CDX_0224);
	}

	@Override
	protected Map<String, Object> construireParametres(CardexAuthenticationSubject subject, ValidatorForm validatorForm, Connection connection) throws JRException {
		Map<String,Object> parameters =  super.construireParametres(subject, validatorForm, connection);
		
		parameters.put("SOUS_RAPPORT_CATEGORIE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_CATEGORIE));
		parameters.put("SOUS_RAPPORT_SUJET", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET));
			parameters.put("SOUS_RAPPORT_SUJET_ADRESSE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_ADRESSE));
			parameters.put("SOUS_RAPPORT_SUJET_CARACTERISTIQUE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SUJET_CARACTERISTIQUE));
		parameters.put("SOUS_RAPPORT_SOCIETE", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_SOCIETE));
		parameters.put("SOUS_RAPPORT_PIECES_JOINTES", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_PIECES_JOINTES));
		parameters.put("SOUS_RAPPORT_NARRATION", RapportUtils.compiler(RapportsConfiguration.SOUS_RAPPORT_DOSSIER_NARRATION));

		return parameters;
	}

	 private List remplirResultatDossiers(List<DossierForm> dossierForms){
		List list = new ArrayList();
		
		for(DossierForm dossierForm:dossierForms){
			Map dossier = new HashMap();
			dossier.put("NatureDescription", dossierForm.getNatureDescription());
			dossier.put("TypeDescription", dossierForm.getTypeDescription());
			dossier.put("CategorieDescription", dossierForm.getCategorieDescription());
			//dossier.put("Numero_Cardex",dossierForm.getNumeroCardex().toString());
			dossier.put("Numero_Cardex",dossierForm.getNumeroCardexTexte());
			dossier.put("Numero_dossier",dossierForm.getNumeroDossier());
			dossier.put("Reference_1", dossierForm.getReference1());
			dossier.put("Reference_2", dossierForm.getReference2());
			dossier.put("Reference_3", dossierForm.getReference3());
			dossier.put("Date_debut", dossierForm.getDateDebut());
			dossier.put("Date_fin", dossierForm.getDateFin());
			dossier.put("Periode", dossierForm.getPeriodeDescription());
			dossier.put("Statut", dossierForm.getStatutDescription());
			dossier.put("Intervenant", dossierForm.getIntervenant());
			dossier.put("IntervenantDescription", dossierForm.getIntervenantDescription());
			dossier.put("Severite", dossierForm.getSeveriteDescription());
			dossier.put("Confidentialite", dossierForm.getConfidentialiteDescription());
			dossier.put("FondeDescription", dossierForm.getFondeDescription());
			dossier.put("Role", dossierForm.getRoleDescription());
			dossier.put("OrigineDescription", dossierForm.getOrigineDescription());
			dossier.put("EndroitDescription", dossierForm.getEndroitDescription());
			dossier.put("LocalisationDescription", dossierForm.getLocalisationDescription());
			dossier.put("Descriptif", dossierForm.getDescriptif());
			dossier.put("Numero_sequentiel", dossierForm.getReferenceVideo());
			dossier.put("SiteDescription", dossierForm.getSiteDescription());
			dossier.put("DOSSIERCLE", new BigDecimal(dossierForm.getCle()));
			dossier.put("SITECLE", new BigDecimal(dossierForm.getSite()));
			
			if(StringUtils.isNotEmpty(dossierForm.getLienCreateur()))
				dossier.put("LiePar", dossierForm.getLienCreateur());
			list.add(dossier);
		}
		
		return list;
	 }
}
