package com.lotoquebec.cardex.generateurRapport.rapports;

import java.util.HashMap;
import java.util.Map;

public class RapportsConfiguration {

	public final static String REPERTOIRE_RAPPORT = "/rapports/";
	
	// Regroupement
	public final static String RAPPORT_GLOBAL_REGROUPEMENTS = REPERTOIRE_RAPPORT+"CDX_0080_RapportGlobalRegroupements.jrxml";
	public final static String RAPPORT_GLOBAL_REGROUPEMENTS_HEURES = REPERTOIRE_RAPPORT+"CDX_0087_RapportGlobalRegroupementsHeures.jrxml";
	public final static String RAPPORT_REGROUPEMENTS_ENDROIT = REPERTOIRE_RAPPORT+"CDX_0081_RapportEndroitRegroupements.jrxml";
	public final static String RAPPORT_REGROUPEMENTS_INTERVENANT_ENDROIT = REPERTOIRE_RAPPORT+"CDX_0085_RapportIntervenantRegroupementsEndroit.jrxml";
	public final static String RAPPORT_REGROUPEMENTS_INTERVENANT = REPERTOIRE_RAPPORT+"CDX_0086_RapportIntervenantRegroupements.jrxml";
	public final static String RAPPORT_REGROUPEMENTS_TENDANCE_MOIS = REPERTOIRE_RAPPORT+"CDX_0088_rapport_mois_tendance.jrxml";
	public final static String RAPPORT_MATRICE_REGROUPEMENTS = REPERTOIRE_RAPPORT+"CDX_0160_matrice_regroupements.jrxml";
	public final static String RAPPORT_GLOBAL_REGROUPEMENTS_CASINO = REPERTOIRE_RAPPORT+"CDX_0089_RapportGlobalRegroupementsCasino.jrxml";
	
	// RETIR�
	public final static String RAPPORT_REGROUPEMENTS_INTERVENANT_CATEGORIE = REPERTOIRE_RAPPORT+"CDX_0084_RapportRegroupementsIntervenantCategorie.jrxml";
	public final static String RAPPORT_REGROUPEMENTS_SECTEUR_CATEGORIE = REPERTOIRE_RAPPORT+"CDX_0082_RapportRegroupementsSecteurCategorie.jrxml";
	public final static String RAPPORT_REGROUPEMENTS_SECTEUR_INTERVENANT = REPERTOIRE_RAPPORT+"CDX_0083_RapportRegroupementsSecteurIntervenant.jrxml";
	
	// Validation d'adresse
	public final static String RAPPORT_VALIDATION_ADRESSES_SUJETS = REPERTOIRE_RAPPORT+"CDX_0130_sujet_adresse_invalide.jrxml";
	public final static String RAPPORT_VALIDATION_ADRESSES_SOCIETES = REPERTOIRE_RAPPORT+"CDX_0131_societe_adresse_invalide.jrxml";
	
	// RAQ
	public static final String RAPPORT_ACTIVITES_CDX_0070 = REPERTOIRE_RAPPORT+"CDX_0070_gabarit_narration.jrxml";
	
	// Rapport sur la coh�rence des donn�es
	public static final String COHERENCE = REPERTOIRE_RAPPORT+"CDX_0071_rapport_coherence_donnees.jrxml";
	public static final String VERIFICATION_ELEMENTS_MULTIMEDIA = REPERTOIRE_RAPPORT+"CDX_0076_verification_multimedia.jrxml";
	
	
	// Rapports diff�r�s pour les acheteurs myst�res, dans le cadre de la conformit� � la loi 84.
	public static final String RAPPORT_VERIFICATION_LIVRETS = REPERTOIRE_RAPPORT+"CDX_0073_verification_livrets.jrxml";
	public static final String RAPPORT_CHANGEMENTS_DETAILLANTS = REPERTOIRE_RAPPORT+"CDX_0074_changements_detaillants.jrxml";
	public static final String RAPPORT_RECONNAISSANCE_PLAQUES = REPERTOIRE_RAPPORT+"CDX_0075_reconnaissance_plaques.jrxml";
	public static final String RAPPORT_LAISSEZ_PASSER_SUJETS = REPERTOIRE_RAPPORT+"CDX_0077_laissez-passer_sujets.jrxml";
	public static final String RAPPORT_LAISSEZ_PASSER_SOCIETES = REPERTOIRE_RAPPORT+"CDX_0078_laissez-passer_societes.jrxml";

	// Rapports statistiques
	public final static String RAPPORT_STATISTIQUE_DOSSIER_CATEGORIE = REPERTOIRE_RAPPORT+"CDX_0140_rapport_statistique.jrxml";
	public final static String RAPPORT_STATISTIQUE_DOSSIER_MOIS = REPERTOIRE_RAPPORT+"CDX_0141_rapport_statistique_annuel.jrxml";
	public final static String RAPPORT_STATISTIQUE_DOSSIER_TYPE = REPERTOIRE_RAPPORT+"CDX_0142_rapport_statistique_par_types.jrxml";
	public static final String RAPPORT_STATISTIQUE_DOSSIER_TEMPS_CONSACRE = REPERTOIRE_RAPPORT+"CDX_0145_rapport_temps_consacre.jrxml";
	
	// Rapports de consignation
	public final static String RAPPORT_SOMMAIRE_CONSIGNATION = REPERTOIRE_RAPPORT+"CDX_0150_rapport_sommaire_consignation.jrxml";
	public final static String RAPPORT_DETAIL_CONSIGNATION = REPERTOIRE_RAPPORT+"CDX_0151_rapport_detail_consignation.jrxml";
	public final static String RAPPORT_DENOMINATION = REPERTOIRE_RAPPORT+"CDX_0152_rapport_denomination.jrxml";
	public final static String RAPPORT_NO_SERIE_DENOMINATION = REPERTOIRE_RAPPORT+"CDX_0153_rapport_numero_serie_denomination.jrxml";
	public final static String RAPPORT_DENOMINATION_NO_SERIE = REPERTOIRE_RAPPORT+"CDX_0154_rapport_denomination_numero_serie.jrxml";
	public final static String RAPPORT_ECHANGES = REPERTOIRE_RAPPORT+"CDX_0156_echanges_casino_client.jrxml";

	public static Map<String, String> listeRapportsAdhoc = new HashMap<String, String>();

	// Rapports d'épuration
	public final static String RAPPORT_EPURATION_DOSSIERS = REPERTOIRE_RAPPORT+"CDX_0006_epuration_dossiers.jrxml";
	public final static String RAPPORT_EPURATION_SUJETS = REPERTOIRE_RAPPORT+"CDX_0007_epuration_sujets.jrxml";
	public final static String RAPPORT_EPURATION_SOCIETES = REPERTOIRE_RAPPORT+"CDX_0008_epuration_societes.jrxml";
	public final static String RAPPORT_EPURATION_VEHICULES = REPERTOIRE_RAPPORT+"CDX_0009_epuration_vehicules.jrxml";

	//Dossiers
	public static final String DOSSIER_PARTAGE_INTERVENANT = REPERTOIRE_RAPPORT+"CDX_0100_liste_dossiers_partages_intervenant.jrxml";
	public static final String DOSSIER_PARTAGE_RESPONSABLE = REPERTOIRE_RAPPORT+"CDX_0101_liste_dossiers_partages_responsable.jrxml";
	public static final String DOSSIER_ACTIF_INTERVENANT = REPERTOIRE_RAPPORT+"CDX_0102_liste_dossiers_actifs_intervenant.jrxml";
	public static final String DOSSIER_ACTIF_ENQUETEUR_LQ = REPERTOIRE_RAPPORT+"CDX_0103_liste_dossiers_actifs_enqueteurs_lq.jrxml";
	public final static String RAPPORT_CUMULATIF_ESPACEJEUX = REPERTOIRE_RAPPORT+"CDX_0143_rapport_cumulatif_dossiers_espacejeux.jrxml";
	public final static String RAPPORT_HEBDOMADAIRE_ESPACEJEUX = REPERTOIRE_RAPPORT+"CDX_0144_rapport_hebdomadaire_dossiers_espacejeux.jrxml";
	public final static String RAPPORT_CUMULATIF = REPERTOIRE_RAPPORT+"CDX_0147_rapport_cumulatif_dossiers.jrxml";
	public final static String RAPPORT_HEBDOMADAIRE = REPERTOIRE_RAPPORT+"CDX_0146_rapport_hebdomadaire_dossiers.jrxml";
	public final static String RAPPORT_INCIDENTS_DCSI = REPERTOIRE_RAPPORT+"CDX_0148_rapport_incidents_dcsi.jrxml";
	public final static String AUTOEXCLUSION_ESPACEJEUX = REPERTOIRE_RAPPORT+"CDX_0221_autoexclusion_espacejeux.jrxml";
	public final static String AUTOEXCLUSION_ACTIF_ESPACEJEUX = REPERTOIRE_RAPPORT+"CDX_0260_espacejeux_autoexclusion_actifs.jrxml";
	public final static String FRAUDE_FONDE_ESPACEJEUX = REPERTOIRE_RAPPORT+"CDX_0261_espacejeux_fraude_fondes.jrxml";
	public final static String TRICHERIE_FONDE_ESPACEJEUX = REPERTOIRE_RAPPORT+"CDX_0262_espacejeux_tricherie_fondes.jrxml";
	public final static String RAPPORT_SUIVIS = REPERTOIRE_RAPPORT+"CDX_0223_impression_avec_suivis.jrxml";
	public final static String RAPPORT_NOMBRE_DOSSIERS = REPERTOIRE_RAPPORT+"CDX_0229_rapport_nombre_dossiers.jrxml";
	public final static String READMISSION = REPERTOIRE_RAPPORT+"CDX_0234_formulaire_readmission.jrxml";
	public final static String RAPPORT_AMBULANCE_SOMMAIRE = REPERTOIRE_RAPPORT+"CDX_0270_ambulance_sommaire.jrxml";
	public final static String RAPPORT_AMBULANCE_DETAIL = REPERTOIRE_RAPPORT+"CDX_0271_ambulance_detail.jrxml";
	public final static String STATISTIQUE_ENDROITS_REGROUPES = REPERTOIRE_RAPPORT+"CDX_0149_rapport_endroits_regroupes.jrxml";
	public final static String TABLEAU_ENDROITS_REGROUPES = REPERTOIRE_RAPPORT+"CDX_0280_statistiques_endroits_regroupes.jrxml";
	
	// Dossiers - Repérage
	public static final String REPERAGES_ACCES_INTERDITS = REPERTOIRE_RAPPORT+"CDX_0012_reperages_acces_interdits.jrxml";
	public static final String REPERAGES_ACCES_INTERDITS_NOMBRE = REPERTOIRE_RAPPORT+"CDX_0014_reperages_acces_interdits_nombre.jrxml";
	public static final String REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE = REPERTOIRE_RAPPORT+"CDX_0011_reperages_AU_tri_nombre.jrxml";
	public static final String REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO = REPERTOIRE_RAPPORT+"CDX_0010_reperages_AU_tri_numero.jrxml";
	public static final String SOUS_RAPPORT_REPERAGES_AUTOEXCLUSIONS = REPERTOIRE_RAPPORT+"sous-rapport_reperages.jrxml";
	public static final String CINQ_REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE = REPERTOIRE_RAPPORT+"reperages_AU_tri_nombre.jrxml";
	public static final String CINQ_REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO = REPERTOIRE_RAPPORT+"reperages_AU_tri_numero.jrxml";

	// Suivi
	public static final String SUIVIS_21_JOURS = REPERTOIRE_RAPPORT+"CDX_0092_suivis_21_jours_intervenant.jrxml";
	public static final String SUIVIS_24_HEURES = REPERTOIRE_RAPPORT+"CDX_0091_suivis_24_heures_intervenant.jrxml";
	public static final String SUIVIS_30_JOURS = REPERTOIRE_RAPPORT+"CDX_0090_suivis_30_jours.jrxml";
	public static final String SUIVIS_30_JOURS_INTERVENANT = REPERTOIRE_RAPPORT+"CDX_0093_suivis_30_jours_intervenant.jrxml";
	public static final String SUIVIS_INVESTIGATION = REPERTOIRE_RAPPORT+"CDX_0094_suivis_investigation.jrxml";
	public static final String RAPPORT_SUIVIS_ENQUETES = REPERTOIRE_RAPPORT+"CDX_0098_rapport_suivis.jrxml";
	public static final String SUIVIS_RETARDS = REPERTOIRE_RAPPORT+"CDX_0095_suivis_retards.jrxml";
	public static final String SOUS_RAPPORT_SUIVIS_PLUS_30_JOURS = REPERTOIRE_RAPPORT+"sous_rapport_suivis_plus_30_jours.jrxml";
	public static final String SOUS_RAPPORT_SUIVIS_PLUS_60_JOURS = REPERTOIRE_RAPPORT+"sous_rapport_suivis_plus_60_jours.jrxml";
	public static final String SOUS_RAPPORT_SUIVIS_PLUS_90_JOURS = REPERTOIRE_RAPPORT+"sous_rapport_suivis_plus_90_jours.jrxml";
	public static final String SOUS_RAPPORT_SUJET_SUIVIS = REPERTOIRE_RAPPORT+"sous_rapport_sujet_suivis.jrxml";
	public static final String SOUS_RAPPORT_SUJET_SUIVIS_ANGLAIS = REPERTOIRE_RAPPORT+"sous_rapport_sujet_suivis_anglais.jrxml";
	public static final String SOUS_RAPPORT_DOSSIER_SUIVIS = REPERTOIRE_RAPPORT+"dossier_suivis.jrxml";
	public static final String SOUS_RAPPORT_DOSSIER_CATEGORIE = REPERTOIRE_RAPPORT+"dossier_categorie.jrxml";
	
	// Journal
	public static final String JOURNAL_SOMMAIRE = REPERTOIRE_RAPPORT+"CDX_0020_rapport_journal_sommaire.jrxml";
	public static final String JOURNAL_DETAIL = REPERTOIRE_RAPPORT+"CDX_0021_rapport_journal_detail.jrxml";
	public static final String JOURNAL_ORIGINE = REPERTOIRE_RAPPORT+"CDX_0022_rapport_journal_origine.jrxml";
	public static final String JOURNAL_DESCRIPTIF = REPERTOIRE_RAPPORT+"CDX_0023_rapport_journal_descriptif.jrxml";
	public static final String JOURNAL_ENQUETES = REPERTOIRE_RAPPORT+"CDX_0053_journal_enquetes.jrxml";
	
	public static final String MENSUEL_RENCONTRES_DETAIL = REPERTOIRE_RAPPORT+"CDX_0062_rapport_rencontres_detail.jrxml";
	public static final String MENSUEL_RENCONTRES = REPERTOIRE_RAPPORT+"CDX_0061_rapport_rencontres.jrxml";
	public static final String CUMUL_ANNUEL = REPERTOIRE_RAPPORT+"CDX_0040_cumul_annuel_enquetes.jrxml";
	public static final String CUMUL_HEBDOMADAIRE = REPERTOIRE_RAPPORT+"CDX_0041_cumul_hebdo_enquetes.jrxml";
	public static final String MENSUEL_AUTOEXCLUSIONS = REPERTOIRE_RAPPORT+"CDX_0060_rapport_autoexclusions.jrxml";
	public static final String MENSUEL_RENCONTRES_FINALES = REPERTOIRE_RAPPORT+"CDX_0063_rapport_rencontres_finales.jrxml";
	public static final String MENSUEL_RENCONTRES_FINALES_DETAIL = REPERTOIRE_RAPPORT+"CDX_0064_rapport_rencontres_finales_detail.jrxml";
	public static final String NOMBRE_ENQUETES = REPERTOIRE_RAPPORT+"CDX_0240_nombre_enquetes.jrxml";
	public static final String FACTURATION_SQ = REPERTOIRE_RAPPORT+"CDX_0241_facturation_sq.jrxml";
	public static final String FACTURATION_ECHO = REPERTOIRE_RAPPORT+"CDX_0242_facturation_echo.jrxml";
	public static final String FACTURATION_SOQUIJ = REPERTOIRE_RAPPORT+"CDX_0243_facturation_soquij.jrxml";
	public static final String DELAI_TRAITEMENT_ENQUETES = REPERTOIRE_RAPPORT+"CDX_0246_delai_traitement_enquetes.jrxml";
	public static final String EMPLOYE_DOSSIER = REPERTOIRE_RAPPORT+"CDX_0042_employes_dossiers.jrxml";
	public static final String ENQUETE_EN_RETARD = REPERTOIRE_RAPPORT+"CDX_0247_enquetes_retard.jrxml";
	public static final String ENQUETE_TRAITEMENT_EN_RETARD = REPERTOIRE_RAPPORT+"CDX_0248_enquetes_traitees_retard.jrxml";
	public static final String RAPPORT_PAR = REPERTOIRE_RAPPORT+"CDX_0030_rapport_PAR.jrxml";
	public static final String REGISTRE_RFC_MAITRE = REPERTOIRE_RAPPORT+"CDX_0054_registre_RFC.jrxml";
	public static final String REGISTRE_RFC_MAITRE_SOUS_RAPPORT_TOTAL = REPERTOIRE_RAPPORT+"sous_rapport_registre_RFC_total.jrxml";
	public static final String REGISTRE_RFC_MAITRE_SOUS_RAPPORT = REPERTOIRE_RAPPORT+"sous_rapport_registre_RFC.jrxml";
	public static final String STATUT_DOSSIERS = REPERTOIRE_RAPPORT+"CDX_0055_Statut_dossiers.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_ACTIVES = REPERTOIRE_RAPPORT+"sous_rapport_enquetes_actives.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_EN_COURS = REPERTOIRE_RAPPORT+"sous_rapport_enquetes_en_cours.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_NOUVELLES = REPERTOIRE_RAPPORT+"sous_rapport_enquetes_nouvelles.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_TERMINEES = REPERTOIRE_RAPPORT+"sous_rapport_enquetes_terminees.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_ACTIVES = REPERTOIRE_RAPPORT+"sous_rapport_total_enquetes_actives.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_EN_COURS = REPERTOIRE_RAPPORT+"sous_rapport_total_enquetes_en_cours.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_NOUVELLES = REPERTOIRE_RAPPORT+"sous_rapport_total_enquetes_nouvelles.jrxml";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_TERMINEES = REPERTOIRE_RAPPORT+"sous_rapport_total_enquetes_terminees.jrxml";	
	public static final String SOUS_RAPPORT_DELAI_TRAITEMENT_ENQUETES = REPERTOIRE_RAPPORT+"sous-rapport_delai_traitement_enquetes.jrxml";	
	public static final String SOUS_RAPPORT_TOTAL_ENQUETES = REPERTOIRE_RAPPORT+"sous-rapport_total_enquetes_investigation.jrxml";	
	public static final String SUJETS_SEVERITE_4 = REPERTOIRE_RAPPORT+"CDX_0244_severite_4_sujets.jrxml";	
	public static final String SOCIETES_SEVERITE_4 = REPERTOIRE_RAPPORT+"CDX_0245_severite_4_societes.jrxml";	

	//Contrat autoexclusion
	public final static String AUTOEXCLUSION = REPERTOIRE_RAPPORT+"CDX_0220_autoexclusion.jrxml";
	public static final String SOUS_RAPPORT_AUTOEXCLUSION_SUJET_PHOTO = REPERTOIRE_RAPPORT+"sujet_photo.jrxml";
	public static final String SOUS_RAPPORT_AUTOEXCLUSION_SUJET_FRANCAIS = REPERTOIRE_RAPPORT+"sous_rapport_sujet_autoexclusion.jrxml";
	public static final String SOUS_RAPPORT_AUTOEXCLUSION_SUJET_ANGALAIS = REPERTOIRE_RAPPORT+"sous_rapport_sujet_autoexclusion_anglais.jrxml";
	
	//Rapports du comit� de vigilance
	public static final String RAPPORT_STATISTIQUE_VIGILANCE_SOMMAIRE = REPERTOIRE_RAPPORT+"CDX_0235_rapport_statistique_vigilance_sommaire.jrxml";
	public static final String RAPPORT_STATISTIQUE_VIGILANCE_DETAILLE = REPERTOIRE_RAPPORT+"CDX_0236_rapport_statistique_vigilance_detaille.jrxml";
	public static final String RAPPORT_SUJETS_ANALYSES_VIGILANCE = REPERTOIRE_RAPPORT+"CDX_0237_clients_analyses_vigilance.jrxml";
	
	//Rapports clients myst�res
	public static final String RAPPORT_DETAIL_VISITES = REPERTOIRE_RAPPORT+"CDX_0250_detail_visites_clients_mysteres.jrxml";
	public static final String RAPPORT_DETAIL_VISITES_A_VENIR = REPERTOIRE_RAPPORT+"CDX_0251_detail_visites_a_venir_clients_mysteres.jrxml";
	public static final String RAPPORT_DETAILLANTS_FAUTIFS = REPERTOIRE_RAPPORT+"CDX_0252_detaillants_en_infraction_clients_mysteres.jrxml";
	public static final String RAPPORT_VISITES_REGION = REPERTOIRE_RAPPORT+"CDX_0253_visites_par_region_administrative.jrxml";
	public static final String RAPPORT_VISITES_CENTRE = REPERTOIRE_RAPPORT+"CDX_0254_visites_par_centre_regional.jrxml";
	public static final String RAPPORT_FICHIER_MAITRE_CDX_0255 = REPERTOIRE_RAPPORT+"CDX_0255_clients_mysteres_fichier_maitre.jrxml";
    public static final String RAPPORT_SOCIETES_INACTIVES = REPERTOIRE_RAPPORT+"CDX_0256_clients_mysteres_detaillants_inactifs.jrxml";
    public static final String RAPPORT_DETAILLANTS_INFRACTION_CDX_0257 = REPERTOIRE_RAPPORT+"CDX_0257_detaillants_en_infraction.jrxml";

    //Rapports sur les accès
    public static final String AUDIT_SOMMAIRE_ACCES = REPERTOIRE_RAPPORT+"CDX_0120_audit_sommaire_acces.jrxml";
    public static final String AUDIT_DETAIL_ACCES = REPERTOIRE_RAPPORT+"CDX_0121_audit_detail_acces.jrxml";
    public static final String AUDIT_INTERVENANT_ACCES = REPERTOIRE_RAPPORT+"CDX_0122_audit_acces_intervenant.jrxml";
    public static final String AUDIT_DETAIL_INTERVENANT_ACCES = REPERTOIRE_RAPPORT+"CDX_0123_audit_detail_acces_intervenant.jrxml";
    public static final String AUDIT_ACCES_EMPLOYE = REPERTOIRE_RAPPORT+"CDX_0124_audit_acces_sujets.jrxml";
    public static final String AUDIT_ACCES_FOURNISSEUR = REPERTOIRE_RAPPORT+"CDX_0125_audit_acces_sujets_fournisseurs.jrxml";
    public static final String AUDIT_ACCES_NARRATION = REPERTOIRE_RAPPORT+"CDX_0126_audit_acces_narrations.jrxml";
    public static final String ANALYSE_ACCES = REPERTOIRE_RAPPORT+"CDX_0072_rapport_analyse_acces.jrxml";
    
    //Audit
	public static final String AUDIT_ACCES_DOSSIERS = REPERTOIRE_RAPPORT+"CDX_0210_impression_audit_acces_dossiers.jrxml";
	public static final String AUDIT_ACCES_SUJETS_CDX_0211 = REPERTOIRE_RAPPORT+"CDX_0211_impression_audit_acces_sujets.jrxml";
	public static final String AUDIT_ACCES_SOCIETES_CDX_212 = REPERTOIRE_RAPPORT+"CDX_0212_impression_audit_acces_societes.jrxml";
	public static final String AUDIT_ACCES_VEHICULES = REPERTOIRE_RAPPORT+"CDX_0213_impression_audit_acces_vehicules.jrxml";
	public static final String DETAIL_ACCES_DOSSIERS = REPERTOIRE_RAPPORT+"CDX_0121_audit_detail_acces.jrxml";
	public static final String DETAIL_ACCES_INTERVENANT = REPERTOIRE_RAPPORT+"CDX_0123_audit_detail_acces_intervenant.jrxml";

	// Audit de changement
	public static final String AUDIT_CHANGEMENTS_ADRESSES = REPERTOIRE_RAPPORT+"CDX_0180_audit_changements_adresses.jrxml";
	public static final String AUDIT_CHANGEMENTS_DOSSIERS = REPERTOIRE_RAPPORT+"CDX_0182_audit_changements_dossiers.jrxml";
	public static final String AUDIT_CHANGEMENTS_INTERVENANTS = REPERTOIRE_RAPPORT+"CDX_0183_audit_changements_intervenants.jrxml";
	public static final String AUDIT_CHANGEMENTS_NARRATIONS = REPERTOIRE_RAPPORT+"CDX_0181_audit_changements_narrations.jrxml";
	public static final String AUDIT_CHANGEMENTS_SOCIETES = REPERTOIRE_RAPPORT+"CDX_0184_audit_changements_societes.jrxml";
	public static final String AUDIT_CHANGEMENTS_SUJETS_CDX_0185 = REPERTOIRE_RAPPORT+"CDX_0185_audit_changements_sujets.jrxml";
	public static final String AVANT_CHANGEMENT_AUDIT_CHANGEMENTS_SUJETS = REPERTOIRE_RAPPORT+"CDX_0185_sous-rapport_audit_sujets.jrxml";
	public static final String CREATEUR_AUDIT_CHANGEMENTS_SUJETS = REPERTOIRE_RAPPORT+"sous-rapport_audit_createur.jrxml";
	
	public static final String AUDIT_CHANGEMENTS_SUIVIS = REPERTOIRE_RAPPORT+"CDX_0186_audit_changements_suivis.jrxml";
	public static final String AUDIT_CHANGEMENTS_VEHICULES = REPERTOIRE_RAPPORT+"CDX_0187_audit_changements_vehicules.jrxml";
	
	// Sujet
	public static final String SUJET_CDX_0002 = REPERTOIRE_RAPPORT+"CDX_0002_sujet.jrxml";
	public static final String SUJET_GALERIE_CDX_0004 = REPERTOIRE_RAPPORT+"CDX_0004_sujet-galerie.jrxml";

}
