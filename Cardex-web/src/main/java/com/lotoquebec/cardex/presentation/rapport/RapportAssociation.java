package com.lotoquebec.cardex.presentation.rapport;

import java.util.HashMap;
import java.util.Map;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.CumulatifDossierGenerateurRapport_CDX_0147;
import com.lotoquebec.cardex.generateurRapport.dossier.CumulatifEspacejeuxDossierGenerateurRapport_CDX_0143;
import com.lotoquebec.cardex.generateurRapport.dossier.HebdomadaireDossierGenerateurRapport_CDX_0146;
import com.lotoquebec.cardex.generateurRapport.dossier.HebdomadaireEspacejeuxDossierGenerateurRapport_CDX_0144;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.generateurRapport.regroupement.EndroitRegroupementGenerateurRapport_CDX_0081;
import com.lotoquebec.cardex.generateurRapport.regroupement.GlobalRegroupementGenerateurRapport_CDX_0080;
import com.lotoquebec.cardex.generateurRapport.regroupement.GlobalTotalHeuresRegroupementGenerateurRapport_CDX_0087;
import com.lotoquebec.cardex.generateurRapport.regroupement.IntervenantEndroitRegroupementGenerateurRapport_CDX_0085;
import com.lotoquebec.cardex.generateurRapport.regroupement.IntervenantRegroupementGenerateurRapport_CDX_0086;
import com.lotoquebec.cardex.generateurRapport.regroupement.MatriceRegroupementGenerateurRapport_CDX_0160;
import com.lotoquebec.cardex.generateurRapport.regroupement.TendanceMoisRegroupementGenerateurRapport_CDX_0088;
import com.lotoquebec.cardex.presentation.rapport.dossier.DossierAutoexclusion;
import com.lotoquebec.cardex.presentation.rapport.dossier.DossierAutoexclusionEspacejeux;
import com.lotoquebec.cardex.presentation.rapport.dossier.DossierReadmission;
import com.lotoquebec.cardex.presentation.rapport.dossier.DossierSuivis;
import com.lotoquebec.cardex.presentation.rapport.dossier.NombreDossiers;
import com.lotoquebec.cardexCommun.GlobalConstants;

public class RapportAssociation {


	public static Map<String, String> listeRapportsAdhoc = new HashMap<String, String>();

	
	static{
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_DOSSIERS, "cardex.recherche.dossiers.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_DOSSIERS_COMPLET, "cardex.recherche.dossiers.imprimer");

		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES, "cardex.recherche.societes.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES_COMPLET, "cardex.recherche.societes.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_ADRESSE_SOCIETES, "cardex.recherche.societes.imprimer");
		
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS, "cardex.recherche.sujets.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS_COMPLET, "cardex.recherche.sujets.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_ADRESSE_SUJETS, "cardex.recherche.sujet.imprimer");
		
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_VEHICULES, "cardex.recherche.vehicules.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_VEHICULES_COMPLET, "cardex.recherche.vehicules.imprimer");
		
        listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE, "cardex.recherche.urgences.imprimer");
        listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE_COMPLET, "cardex.recherche.urgences.imprimer");
		
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.IMPRESSION_SUJET, "cardex.sujet.base.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.IMPRESSION_SOCIETE, "cardex.societe.base.imprimer");				
		//listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.IMPRESSION_DOSSIER, "cardex.dossier.base.imprimer");
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.IMPRESSION_GALERIE, "cardex.sujet.base.imprimer");			

		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_DOSSIERS, "cardex.dossier.audit");			
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_SUJETS, "cardex.sujet.audit");			
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_SOCIETES, "cardex.societe.audit");			
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_VEHICULES, "cardex.vehicule.audit");			
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_NARRATIONS, "cardex.narration.audit");			
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_SUIVIS, "cardex.suivi.audit");			
		listeRapportsAdhoc.put(GlobalConstants.ChoixRapport.AUDIT_CHANGEMENTS_ADRESSES, "cardex.adresse.audit");			

		listeRapportsAdhoc.put("com.lotoquebec.cardex.generateurRapport.adresse.AdresseInvalide", "cardex.cb_rapport.adresse.invalide");
	}
	
	public static String getRoleAdhoc(String key){
		return listeRapportsAdhoc.get(key);
	}
	
	public static GenererRapport obtenirGenererRapport(int choixRapport){

		switch(choixRapport){
			/*
			 * Section rapports de regroupement et dossiers
			 */
			case 169: //Global des regroupements
				return new GlobalRegroupementGenerateurRapport_CDX_0080();
			case 170: //Global des regroupements (heures travaillées)
				return new GlobalTotalHeuresRegroupementGenerateurRapport_CDX_0087();
			case 171: //Regroupement par endroit
				return new EndroitRegroupementGenerateurRapport_CDX_0081();
			case 172: //Regroupement par intervenant trié par endroit
				return new IntervenantEndroitRegroupementGenerateurRapport_CDX_0085();
			case 173: //Regroupement par intervenant
				return new IntervenantRegroupementGenerateurRapport_CDX_0086();
			case 174: //Rapport sur les tendances par mois
				return new TendanceMoisRegroupementGenerateurRapport_CDX_0088();
			case 281: //Matrice des regroupements
				return new MatriceRegroupementGenerateurRapport_CDX_0160();	
			case 479: //Rapport cumulatif des dossiers Espacejeux
				return new CumulatifEspacejeuxDossierGenerateurRapport_CDX_0143();	
			case 480: //Rapport hebdomadaire des dossiers Espacejeux
				return new HebdomadaireEspacejeuxDossierGenerateurRapport_CDX_0144();	
			case 482: //Rapport cumulatif des dossiers
				return new CumulatifDossierGenerateurRapport_CDX_0147();	
			case 483: //Rapport hebdomadaire des dossiers
				return new HebdomadaireDossierGenerateurRapport_CDX_0146();
			case 484: //Contrat d'autoexclusion
				return new DossierAutoexclusion();	
			case 486: //Contrat d'autoexclusion Espacejeux
				return new DossierAutoexclusionEspacejeux();	
			case 487: //Rapport avec suivis
				return new DossierSuivis();	
			case 493: //Rapport sur le nombre de dossiers retournés par une recherche
				return new NombreDossiers();
			case 550: //Formulaire de réadmission
				return new DossierReadmission();
				
			default:
				throw new RuntimeException("Aucun rapport trouvé pour la valeur "+choixRapport);		
		}
	}

	public static String obtenirFichierJasper(int choixRapport){

		switch(choixRapport){
		
			case 127: // Repérages des avis de guet
				return GlobalConstants.ChoixRapport.REPERAGES_AVIS_DE_GUET;
			case 128: // Statistique des dossiers et catégories
				return RapportsConfiguration.RAPPORT_STATISTIQUE_DOSSIER_CATEGORIE;
			case 129: // Rapport statistique annuel
				return RapportsConfiguration.RAPPORT_STATISTIQUE_DOSSIER_MOIS;

			case 469: // Rencontres initiales d'autoexclusion
				return GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_FINALES;			
			case 470: // Rencontres finales d'autoexclusion
				return GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_FINALES_DETAIL;			

				
			default:
				throw new RuntimeException("Aucun rapport trouvé pour la valeur "+choixRapport);		
		}
	}
	
}
