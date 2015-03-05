package com.lotoquebec.cardex.generateurRapport.rapports;

import java.util.HashMap;
import java.util.Map;

public class RapportsConfiguration {

	// Regroupement
	public final static String RAPPORT_GLOBAL_REGROUPEMENTS = "CDX_0080_RapportGlobalRegroupements.jasper";
	public final static String RAPPORT_GLOBAL_REGROUPEMENTS_HEURES = "CDX_0087_RapportGlobalRegroupementsHeures.jasper";
	public final static String RAPPORT_REGROUPEMENTS_ENDROIT = "CDX_0081_RapportEndroitRegroupements.jasper";
	public final static String RAPPORT_REGROUPEMENTS_INTERVENANT_ENDROIT = "CDX_0085_RapportIntervenantRegroupementsEndroit.jasper";
	public final static String RAPPORT_REGROUPEMENTS_INTERVENANT = "CDX_0086_RapportIntervenantRegroupements.jasper";
	public final static String RAPPORT_REGROUPEMENTS_TENDANCE_MOIS = "CDX_0088_rapport_mois_tendance.jasper";
	public final static String RAPPORT_MATRICE_REGROUPEMENTS = "CDX_0160_matrice_regroupements.jasper";
	public final static String RAPPORT_GLOBAL_REGROUPEMENTS_CASINO = "CDX_0089_RapportGlobalRegroupementsCasino.jasper";
	
	// RETIRÉ
	public final static String RAPPORT_REGROUPEMENTS_INTERVENANT_CATEGORIE = "CDX_0084_RapportRegroupementsIntervenantCategorie.jasper";
	public final static String RAPPORT_REGROUPEMENTS_SECTEUR_CATEGORIE = "CDX_0082_RapportRegroupementsSecteurCategorie.jasper";
	public final static String RAPPORT_REGROUPEMENTS_SECTEUR_INTERVENANT = "CDX_0083_RapportRegroupementsSecteurIntervenant.jasper";
	
	// Validation d'adresse
	public final static String RAPPORT_VALIDATION_ADRESSES_SUJETS = "CDX_0130_sujet_adresse_invalide.jasper";
	public final static String RAPPORT_VALIDATION_ADRESSES_SOCIETES = "CDX_0131_societe_adresse_invalide.jasper";
	
	// RAQ
	public static final String RAPPORT_ACTIVITES = "CDX_0070_gabarit_narration.jasper";
	
	// Rapport sur la cohérence des données
	public static final String COHERENCE = "CDX_0071_rapport_coherence_donnees.jasper";
	public static final String VERIFICATION_ELEMENTS_MULTIMEDIA = "CDX_0076_verification_multimedia.jasper";
	
	
	// Rapports différés pour les acheteurs mystères, dans le cadre de la conformité à la loi 84.
	public static final String RAPPORT_VERIFICATION_LIVRETS = "CDX_0073_verification_livrets.jasper";
	public static final String RAPPORT_CHANGEMENTS_DETAILLANTS = "CDX_0074_changements_detaillants.jasper";
	public static final String RAPPORT_RECONNAISSANCE_PLAQUES = "CDX_0075_reconnaissance_plaques.jasper";
	public static final String RAPPORT_LAISSEZ_PASSER_SUJETS = "CDX_0077_laissez-passer_sujets.jasper";
	public static final String RAPPORT_LAISSEZ_PASSER_SOCIETES = "CDX_0078_laissez-passer_societes.jasper";

	// Rapports statistiques
	public final static String RAPPORT_STATISTIQUE_DOSSIER_CATEGORIE = "CDX_0140_rapport_statistique.jasper";
	public final static String RAPPORT_STATISTIQUE_DOSSIER_MOIS = "CDX_0141_rapport_statistique_annuel.jasper";
	public final static String RAPPORT_STATISTIQUE_DOSSIER_TYPE = "CDX_0142_rapport_statistique_par_types.jasper";
	public static final String RAPPORT_STATISTIQUE_DOSSIER_TEMPS_CONSACRE = "CDX_0145_rapport_temps_consacre.jasper";
	
	// Rapports de consignation
	public final static String RAPPORT_SOMMAIRE_CONSIGNATION = "CDX_0150_rapport_sommaire_consignation.jasper";
	public final static String RAPPORT_DETAIL_CONSIGNATION = "CDX_0151_rapport_detail_consignation.jasper";
	public final static String RAPPORT_DENOMINATION = "CDX_0152_rapport_denomination.jasper";
	public final static String RAPPORT_NO_SERIE_DENOMINATION = "CDX_0153_rapport_numero_serie_denomination.jasper";
	public final static String RAPPORT_DENOMINATION_NO_SERIE = "CDX_0154_rapport_denomination_numero_serie.jasper";
	public final static String RAPPORT_ECHANGES = "CDX_0156_echanges_casino_client.jasper";

	public static Map<String, String> listeRapportsAdhoc = new HashMap<String, String>();

	// Rapports d'épuration
	public final static String RAPPORT_EPURATION_DOSSIERS = "CDX_0006_epuration_dossiers.jasper";
	public final static String RAPPORT_EPURATION_SUJETS = "CDX_0007_epuration_sujets.jasper";
	public final static String RAPPORT_EPURATION_SOCIETES = "CDX_0008_epuration_societes.jasper";
	public final static String RAPPORT_EPURATION_VEHICULES = "CDX_0009_epuration_vehicules.jasper";

	//Dossiers
	public static final String DOSSIER_PARTAGE_INTERVENANT = "CDX_0100_liste_dossiers_partages_intervenant.jasper";
	public static final String DOSSIER_PARTAGE_RESPONSABLE = "CDX_0101_liste_dossiers_partages_responsable.jasper";
	public static final String DOSSIER_ACTIF_INTERVENANT = "CDX_0102_liste_dossiers_actifs_intervenant.jasper";
	public static final String DOSSIER_ACTIF_ENQUETEUR_LQ = "CDX_0103_liste_dossiers_actifs_enqueteurs_lq.jasper";
	public final static String RAPPORT_CUMULATIF_ESPACEJEUX = "CDX_0143_rapport_cumulatif_dossiers_espacejeux.jasper";
	public final static String RAPPORT_HEBDOMADAIRE_ESPACEJEUX = "CDX_0144_rapport_hebdomadaire_dossiers_espacejeux.jasper";
	public final static String RAPPORT_CUMULATIF = "CDX_0147_rapport_cumulatif_dossiers.jasper";
	public final static String RAPPORT_HEBDOMADAIRE = "CDX_0146_rapport_hebdomadaire_dossiers.jasper";
	public final static String RAPPORT_INCIDENTS_DCSI = "CDX_0148_rapport_incidents_dcsi.jasper";
	public final static String AUTOEXCLUSION_ESPACEJEUX = "CDX_0221_autoexclusion_espacejeux.jasper";
	public final static String AUTOEXCLUSION_ACTIF_ESPACEJEUX = "CDX_0260_espacejeux_autoexclusion_actifs.jasper";
	public final static String FRAUDE_FONDE_ESPACEJEUX = "CDX_0261_espacejeux_fraude_fondes.jasper";
	public final static String TRICHERIE_FONDE_ESPACEJEUX = "CDX_0262_espacejeux_tricherie_fondes.jasper";
	public final static String RAPPORT_SUIVIS = "CDX_0223_impression_avec_suivis.jasper";
	public final static String RAPPORT_NOMBRE_DOSSIERS = "CDX_0229_rapport_nombre_dossiers.jasper";
	public final static String READMISSION = "CDX_0234_formulaire_readmission.jasper";
	public final static String RAPPORT_AMBULANCE_SOMMAIRE = "CDX_0270_ambulance_sommaire.jasper";
	public final static String RAPPORT_AMBULANCE_DETAIL = "CDX_0271_ambulance_detail.jasper";
	public final static String STATISTIQUE_ENDROITS_REGROUPES = "CDX_0149_rapport_endroits_regroupes.jasper";
	public final static String TABLEAU_ENDROITS_REGROUPES = "CDX_0280_statistiques_endroits_regroupes.jasper";
	
	// Dossiers - Repérage
	public static final String REPERAGES_ACCES_INTERDITS = "CDX_0012_reperages_acces_interdits.jasper";
	public static final String REPERAGES_ACCES_INTERDITS_NOMBRE = "CDX_0014_reperages_acces_interdits_nombre.jasper";
	public static final String REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE = "CDX_0011_reperages_AU_tri_nombre.jasper";
	public static final String REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO = "CDX_0010_reperages_AU_tri_numero.jasper";
	public static final String SOUS_RAPPORT_REPERAGES_AUTOEXCLUSIONS = "sous-rapport_reperages.jasper";
	public static final String CINQ_REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE = "reperages_AU_tri_nombre.jasper";
	public static final String CINQ_REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO = "reperages_AU_tri_numero.jasper";

	// Suivi
	public static final String SUIVIS_21_JOURS = "CDX_0092_suivis_21_jours_intervenant.jasper";
	public static final String SUIVIS_24_HEURES = "CDX_0091_suivis_24_heures_intervenant.jasper";
	public static final String SUIVIS_30_JOURS = "CDX_0090_suivis_30_jours.jasper";
	public static final String SUIVIS_30_JOURS_INTERVENANT = "CDX_0093_suivis_30_jours_intervenant.jasper";
	public static final String SUIVIS_INVESTIGATION = "CDX_0094_suivis_investigation.jasper";
	public static final String RAPPORT_SUIVIS_ENQUETES = "CDX_0098_rapport_suivis.jasper";
	public static final String SUIVIS_RETARDS = "CDX_0095_suivis_retards.jasper";
	public static final String SOUS_RAPPORT_SUIVIS_PLUS_30_JOURS = "sous_rapport_suivis_plus_30_jours.jasper";
	public static final String SOUS_RAPPORT_SUIVIS_PLUS_60_JOURS = "sous_rapport_suivis_plus_60_jours.jasper";
	public static final String SOUS_RAPPORT_SUIVIS_PLUS_90_JOURS = "sous_rapport_suivis_plus_90_jours.jasper";
	public static final String SOUS_RAPPORT_SUJET_SUIVIS = "sous_rapport_sujet_suivis.jasper";
	public static final String SOUS_RAPPORT_SUJET_SUIVIS_ANGLAIS = "sous_rapport_sujet_suivis_anglais.jasper";
	public static final String SOUS_RAPPORT_DOSSIER_SUIVIS = "dossier_suivis.jasper";
	public static final String SOUS_RAPPORT_DOSSIER_CATEGORIE = "dossier_categorie.jasper";
	
	// Journal
	public static final String JOURNAL_SOMMAIRE = "CDX_0020_rapport_journal_sommaire.jasper";
	public static final String JOURNAL_DETAIL = "CDX_0021_rapport_journal_detail.jasper";
	public static final String JOURNAL_ORIGINE = "CDX_0022_rapport_journal_origine.jasper";
	public static final String JOURNAL_DESCRIPTIF = "CDX_0023_rapport_journal_descriptif.jasper";
	public static final String JOURNAL_ENQUETES = "CDX_0053_journal_enquetes.jasper";
	
	public static final String MENSUEL_RENCONTRES_DETAIL = "CDX_0062_rapport_rencontres_detail.jasper";
	public static final String MENSUEL_RENCONTRES = "CDX_0061_rapport_rencontres.jasper";
	public static final String CUMUL_ANNUEL = "CDX_0040_cumul_annuel_enquetes.jasper";
	public static final String CUMUL_HEBDOMADAIRE = "CDX_0041_cumul_hebdo_enquetes.jasper";
	public static final String MENSUEL_AUTOEXCLUSIONS = "CDX_0060_rapport_autoexclusions.jasper";
	public static final String MENSUEL_RENCONTRES_FINALES = "CDX_0063_rapport_rencontres_finales.jasper";
	public static final String MENSUEL_RENCONTRES_FINALES_DETAIL = "CDX_0064_rapport_rencontres_finales_detail.jasper";
	public static final String NOMBRE_ENQUETES = "CDX_0240_nombre_enquetes.jasper";
	public static final String FACTURATION_SQ = "CDX_0241_facturation_sq.jasper";
	public static final String FACTURATION_ECHO = "CDX_0242_facturation_echo.jasper";
	public static final String FACTURATION_SOQUIJ = "CDX_0243_facturation_soquij.jasper";
	public static final String DELAI_TRAITEMENT_ENQUETES = "CDX_0246_delai_traitement_enquetes.jasper";
	public static final String EMPLOYE_DOSSIER = "CDX_0042_employes_dossiers.jasper";
	public static final String ENQUETE_EN_RETARD = "CDX_0247_enquetes_retard.jasper";
	public static final String ENQUETE_TRAITEMENT_EN_RETARD = "CDX_0248_enquetes_traitees_retard.jasper";
	public static final String RAPPORT_PAR = "CDX_0030_rapport_PAR.jasper";
	public static final String REGISTRE_RFC_MAITRE = "CDX_0054_registre_RFC.jasper";
	public static final String REGISTRE_RFC_MAITRE_SOUS_RAPPORT_TOTAL = "sous_rapport_registre_RFC_total.jasper";
	public static final String REGISTRE_RFC_MAITRE_SOUS_RAPPORT = "sous_rapport_registre_RFC.jasper";
	public static final String STATUT_DOSSIERS = "CDX_0055_Statut_dossiers.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_ACTIVES = "sous_rapport_enquetes_actives.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_EN_COURS = "sous_rapport_enquetes_en_cours.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_NOUVELLES = "sous_rapport_enquetes_nouvelles.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_ENQUETES_TERMINEES = "sous_rapport_enquetes_terminees.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_ACTIVES = "sous_rapport_total_enquetes_actives.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_EN_COURS = "sous_rapport_total_enquetes_en_cours.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_NOUVELLES = "sous_rapport_total_enquetes_nouvelles.jasper";
	public static final String STATUT_DOSSIERS_SOUS_RAPPORT_TOTAL_ENQUETES_TERMINEES = "sous_rapport_total_enquetes_terminees.jasper";	
	public static final String SOUS_RAPPORT_DELAI_TRAITEMENT_ENQUETES = "sous-rapport_delai_traitement_enquetes.jasper";	
	public static final String SOUS_RAPPORT_TOTAL_ENQUETES = "sous-rapport_total_enquetes_investigation.jasper";	
	public static final String SUJETS_SEVERITE_4 = "CDX_0244_severite_4_sujets.jasper";	
	public static final String SOCIETES_SEVERITE_4 = "CDX_0245_severite_4_societes.jasper";	

	//Contrat autoexclusion
	public final static String AUTOEXCLUSION = "CDX_0220_autoexclusion.jasper";
	public static final String SOUS_RAPPORT_AUTOEXCLUSION_SUJET_PHOTO = "sujet_photo.jasper";
	public static final String SOUS_RAPPORT_AUTOEXCLUSION_SUJET_FRANCAIS = "sous_rapport_sujet_autoexclusion.jasper";
	public static final String SOUS_RAPPORT_AUTOEXCLUSION_SUJET_ANGALAIS = "sous_rapport_sujet_autoexclusion_anglais.jasper";
	
	//Rapports du comité de vigilance
	public static final String RAPPORT_STATISTIQUE_VIGILANCE_SOMMAIRE = "CDX_0235_rapport_statistique_vigilance_sommaire.jasper";
	public static final String RAPPORT_STATISTIQUE_VIGILANCE_DETAILLE = "CDX_0236_rapport_statistique_vigilance_detaille.jasper";
	public static final String RAPPORT_SUJETS_ANALYSES_VIGILANCE = "CDX_0237_clients_analyses_vigilance.jasper";
	
	//Rapports clients mystères
	public static final String RAPPORT_DETAIL_VISITES = "CDX_0250_detail_visites_clients_mysteres.jasper";
	public static final String RAPPORT_DETAIL_VISITES_A_VENIR = "CDX_0251_detail_visites_a_venir_clients_mysteres.jasper";
	public static final String RAPPORT_DETAILLANTS_FAUTIFS = "CDX_0252_detaillants_en_infraction_clients_mysteres.jasper";
	public static final String RAPPORT_VISITES_REGION = "CDX_0253_visites_par_region_administrative.jasper";
	public static final String RAPPORT_VISITES_CENTRE = "CDX_0254_visites_par_centre_regional.jasper";
	public static final String RAPPORT_FICHIER_MAITRE = "CDX_0255_clients_mysteres_fichier_maitre.jasper";
    public static final String RAPPORT_SOCIETES_INACTIVES = "CDX_0256_clients_mysteres_detaillants_inactifs.jasper";
    public static final String RAPPORT_DETAILLANTS_INFRACTION = "CDX_0257_detaillants_en_infraction.jasper";

    //Rapports sur les accès
    public static final String AUDIT_SOMMAIRE_ACCES = "CDX_0120_audit_sommaire_acces.jasper";
    public static final String AUDIT_DETAIL_ACCES = "CDX_0121_audit_detail_acces.jasper";
    public static final String AUDIT_INTERVENANT_ACCES = "CDX_0122_audit_acces_intervenant.jasper";
    public static final String AUDIT_DETAIL_INTERVENANT_ACCES ="CDX_0123_audit_detail_acces_intervenant.jasper";
    public static final String AUDIT_ACCES_EMPLOYE ="CDX_0124_audit_acces_sujets.jasper";
    public static final String AUDIT_ACCES_FOURNISSEUR ="CDX_0125_audit_acces_sujets_fournisseurs.jasper";
    public static final String AUDIT_ACCES_NARRATION ="CDX_0126_audit_acces_narrations.jasper";
    public static final String ANALYSE_ACCES = "CDX_0072_rapport_analyse_acces.jasper";
	
}
