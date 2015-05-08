package com.lotoquebec.cardex.integration.dao.clientMystere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.SectionSocieteVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.VisiteVO_CDX_0255;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.util.StringHelper;

public class SocieteAvecDossierClientMystereRowCallbackHandler extends SocieteClientMystereRowCallbackHandler{

	private Set<Dossier> echantillonDossierClientMystereActif;
	
	public SocieteAvecDossierClientMystereRowCallbackHandler(Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap, Set<Dossier> echantillonDossierClientMystereActif) {
		super(societeClientMystereMap);
		this.echantillonDossierClientMystereActif = echantillonDossierClientMystereActif;
	}

	/*
	 * 	
  TYPE REC_SOCIETE_CLIENT_MYSTERE IS RECORD(CLE_SOCIETE SO_SOCIETE.L_SO_CLE%TYPE,
                            SITE_SOCIETE          SO_SOCIETE.L_SI_CLE%TYPE,
                            CLE_ECHANTILLON       DO_DOSSIER.L_DO_CLE%TYPE,
                            SITE_ECHANTILLON      DO_DOSSIER.L_SI_CLE%TYPE,
                            VAGUE                 DO_DOSSIER.V_DO_ANCIENNE_REFERENCE%TYPE,
                            DISTRICT              SO_SOCIETE.V_SO_DISTRICT%TYPE,
                            REGION_ADMINISTRATIVE SO_SOCIETE.V_SO_REFERENCE_1%TYPE,
                            NUMERO_DETAILLANT     SO_SOCIETE.V_SO_REFERENCE_3%TYPE,
                            NOM_SOCIETE           SO_SOCIETE.V_SO_NOM%TYPE,
                            COMPTE_SOCIETE        SO_SOCIETE.V_SO_CODE_COMPTE%TYPE,
                            NOM_COMPTE            VEXI_CDX_DDS_CODE_COMPTE.NOM_COC%TYPE,
                            LANGUE                TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
                            NUM_MUNICIPAL         AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE,
                            TYPE_RUE              TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
                            NOM_RUE               AD_ADRESSE.V_AD_NOM_RUE%TYPE,
                            VILLE                 VI_VILLE.V_VI_VILLE%TYPE,
                            CODE_POSTAL           AD_ADRESSE.V_AD_CODE_POSTAL%TYPE,
                            TELEPHONE_1           AD_ADRESSE.V_AD_TELEPHONE_1%TYPE,
                            CATEGORIE             DO_DOSSIER.I_CA_CLE%TYPE,
                            DEBUT_DATE_DOSSIER    DO_DOSSIER.D_DO_DATE_DEBUT%TYPE,
                            FIN_DATE_DOSSIER      DO_DOSSIER.D_DO_DATE_FIN%TYPE,
                            AVIS_DATE             DO_DOSSIER.V_DO_REFERENCE3%TYPE,
                            TEXTE_NARRATION       CO_COMMENTAIRE2.CLOB_CO_TEXTE_NORMAL%TYPE,
                            NUMERO_BILLET         BI_BILLET.V_BI_NUMERO_CONTROLE%TYPE);
	 */
	@Override
	protected void assignerInformationDossier(ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255, Dossier echantillonDossier, ResultSet rs) throws SQLException {
		SectionSocieteVO_CDX_0255 sectionSocieteCDX0255VO = clientMystereVO_CDX_0255.obtenirSectionSocieteCDX0255VO(echantillonDossier);
		long categorie = rs.getLong("CATEGORIE");
		Timestamp debutDossierDate = rs.getTimestamp("DEBUT_DATE_DOSSIER");
		sectionSocieteCDX0255VO.assignerDernierDossier(debutDossierDate);
		/*
		 *  RA0012	Client mystère CDX_0255. Une ligne du rapporté possède 
		 *  l'attribut "Annulation" à "oui" si la société possède un dossier 
		 *  de catégorie "Retrait processus" pour cette vague
		 */
		if (GlobalConstants.CategorieClientMystere.RETRAIT_PROCESSUS == categorie){
			sectionSocieteCDX0255VO.setAnnulation(true);
			sectionSocieteCDX0255VO.ajouterAnnulationCommentaire(extraireNarration(rs));
			return;
		}
		
		/*
		 * RA0013	Client mystère CDX_0255. Une nouvelle ligne commence pour 
		 * cette société RDD si un dossier de catégorie "Gestion détaillant" est 
		 * lié à cette société pour cette vague.  Cela signifie un changement de 
		 * propriétaire de la société.
		 */
		if (GlobalConstants.CategorieClientMystere.GESTION_DETAILLANT == categorie){
			sectionSocieteCDX0255VO.ajouterAutreCommentaire(extraireNarration(rs));
			clientMystereVO_CDX_0255.doublerSectionSociete(debutDossierDate);
			return;
		}
		VisiteVO_CDX_0255 visiteVOCDX0255VO = sectionSocieteCDX0255VO.getVisites()[retournerVague(categorie)];
		visiteVOCDX0255VO.setCategorie(categorie);
		visiteVOCDX0255VO.setVisiteDate(debutDossierDate);
		visiteVOCDX0255VO.setCarte(isCarte(categorie));
		visiteVOCDX0255VO.setNumeroBillet(rs.getString("NUMERO_BILLET"));
		visiteVOCDX0255VO.setEnvoieAvisDate(rs.getString("AVIS_DATE"));
		visiteVOCDX0255VO.setReceptionAvisDate(rs.getDate("FIN_DATE_DOSSIER"));
		visiteVOCDX0255VO.setVisiteConfirme(isConfirme(visiteVOCDX0255VO, sectionSocieteCDX0255VO.getEchantillonDossier()));
		
		sectionSocieteCDX0255VO.ajouterAutreCommentaire(extraireNarration(rs));
	}
	
	/*
	 * RA0010	Client mystère CDX_0255. Visite carté si la société possède un dossier de visite conforme (1-2-3-4-5) pour cette vague.
	 */
	private boolean isCarte(long categorie){
		return GlobalConstants.CategorieClientMystere.CONFORME_VISITE_1 == categorie 
			|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_2 == categorie 
			|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_3 == categorie 
			|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_4 == categorie 
			|| GlobalConstants.CategorieClientMystere.CONFORME_VISITE_5 == categorie;
	}
	
	/*
	 * RA0011	Client mystère CDX_0255. Une visite est confirmée si le dossier 
	 * d'échantillon n'est pas actif (RA0008) et la date d'envoie de l'avis et 
	 * la date de réception de l'avis ne sont pas vide et si la date de suspension 
	 * est passée.
	 */
	private boolean isConfirme(VisiteVO_CDX_0255 visiteCDX0255VO, Dossier echantillonDossier){
		return echantillonDossierClientMystereActif.contains(echantillonDossier) == false 
		&& visiteCDX0255VO.getEnvoieAvisDate() != null
		&& visiteCDX0255VO.getReceptionAvisDate() != null
		&& visiteCDX0255VO.isSuspensionPasse();
	}	
	
	private int retournerVague(long categorie){
		
		if (GlobalConstants.CategorieClientMystere.CONFORME_VISITE_1 == categorie || GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 == categorie)
			return 0;
		if (GlobalConstants.CategorieClientMystere.CONFORME_VISITE_2 == categorie || GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 == categorie)
			return 1;
		if (GlobalConstants.CategorieClientMystere.CONFORME_VISITE_3 == categorie || GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 == categorie)
			return 2;
		if (GlobalConstants.CategorieClientMystere.CONFORME_VISITE_4 == categorie || GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 == categorie)
			return 3;
		if (GlobalConstants.CategorieClientMystere.CONFORME_VISITE_5 == categorie || GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 == categorie)
			return 4;
		return -1;
	}
	
	private String extraireNarration(ResultSet rs) throws SQLException {
		return StringHelper.removeCaractere(rs.getString("TEXTE_NARRATION"),"\t\r\n");
	}
	
}
