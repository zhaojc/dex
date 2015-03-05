package com.lotoquebec.cardex.integration.dao.clientMystere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class SocieteClientMystereRowCallbackHandler implements RowCallbackHandler{
	
	protected Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap = new HashMap<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255>();
	
	protected abstract void assignerInformationDossier(ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255, Dossier echantillonDossier, ResultSet rs) throws SQLException;
	
	public SocieteClientMystereRowCallbackHandler(Map<ClientMystereVO_CDX_0255, ClientMystereVO_CDX_0255> societeClientMystereMap) {
		super();
		this.societeClientMystereMap = societeClientMystereMap;
	}

	/*
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
           					SO.L_SO_CENTRE_REGIONAL as CENTRE_REGIONAL,                            
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
	public void processRow(ResultSet rs) throws SQLException {
		ClientMystereVO_CDX_0255 nouveauClientMystereCDX_0255 = new ClientMystereVO_CDX_0255(rs.getLong("CLE_SOCIETE"), rs.getLong("SITE_SOCIETE"));
		ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255 = societeClientMystereMap.get(nouveauClientMystereCDX_0255);
		
		if (clientMystereVO_CDX_0255 == null){
			clientMystereVO_CDX_0255 = nouveauClientMystereCDX_0255;
			societeClientMystereMap.put(nouveauClientMystereCDX_0255, nouveauClientMystereCDX_0255);
			clientMystereVO_CDX_0255.setDistrict(rs.getString("DISTRICT"));
			clientMystereVO_CDX_0255.setNumero(rs.getString("NUMERO_DETAILLANT"));
			clientMystereVO_CDX_0255.setNom(rs.getString("NOM_SOCIETE"));
			clientMystereVO_CDX_0255.setCodeCommerce(rs.getString("COMPTE_SOCIETE"));
			clientMystereVO_CDX_0255.setLangue(rs.getString("LANGUE"));
			clientMystereVO_CDX_0255.setRegionAdministrative(rs.getString("REGION_ADMINISTRATIVE"));
			clientMystereVO_CDX_0255.setCentreRegional(rs.getString("CENTRE_REGIONAL"));
			clientMystereVO_CDX_0255.setNomCompte(rs.getString("NOM_COMPTE"));
		}
		clientMystereVO_CDX_0255.setAdresse(construireAdresse(rs.getString("NUM_MUNICIPAL"), rs.getString("TYPE_RUE"), rs.getString("NOM_RUE")).toString());
		clientMystereVO_CDX_0255.setVille(rs.getString("VILLE"));
		clientMystereVO_CDX_0255.setCodePostal(rs.getString("CODE_POSTAL"));
		clientMystereVO_CDX_0255.setTelephone(rs.getString("TELEPHONE_1"));
		
		Dossier echantillonDossier = new DossierVO(rs.getLong("CLE_ECHANTILLON"), rs.getLong("SITE_ECHANTILLON"));
		echantillonDossier.setNumeroDossier(rs.getString("VAGUE"));
		assignerInformationDossier(clientMystereVO_CDX_0255, echantillonDossier, rs);
	}

	private StringBuilder construireAdresse(String numeroMunicipal, String typeRue, String nomRue)
			throws SQLException {
		StringBuilder adresse = new StringBuilder();
		
		if (StringUtils.isNotEmpty(numeroMunicipal))
			adresse.append(numeroMunicipal+", ");
		
		if (StringUtils.isNotEmpty(typeRue))
			adresse.append(typeRue+" ");
		adresse.append(StringUtils.defaultString(nomRue));
		return adresse;
	}

}
