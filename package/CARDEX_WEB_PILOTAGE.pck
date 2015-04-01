CREATE OR REPLACE PACKAGE CARDEX_WEB_PILOTAGE is
  procedure SP_REMPLIR_TRADUCTION(action          IN char,
                                  new_cle         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                                  new_genre       IN char,
                                  new_description IN varchar,
                                  new_abreviation IN varchar);

  TYPE RECW_TR_TRADUCTION is record(
    V_TR_DESCRIPTION TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);
  TYPE DTSW_TR_TRADUCTION is ref cursor return RECW_TR_TRADUCTION;
  procedure SPW_L_TR_TRADUCTION(NEW_TR_CLE IN number,
                                rc1        IN OUT DTSW_TR_TRADUCTION);

  TYPE RECW_IN_INTERVENANT IS RECORD(
    L_IN_CLE               IN_INTERVENANT.L_IN_CLE%TYPE,
    L_SI_CLE               IN_INTERVENANT.L_SI_CLE%TYPE,
    NAME                   IN_INTERVENANT.NAME%TYPE,
    I_LA_CLE               IN_INTERVENANT.I_LA_CLE%TYPE,
    I_ST_CLE               IN_INTERVENANT.I_ST_CLE%TYPE,
    I_CC_CLE               IN_INTERVENANT.I_CC_CLE%TYPE,
    I_NH_CLE               IN_INTERVENANT.I_NH_CLE%TYPE,
    V_IN_NO_EMPLOYE        IN_INTERVENANT.V_IN_NO_EMPLOYE%TYPE,
    V_IN_NOM               IN_INTERVENANT.V_IN_NOM%TYPE,
    V_IN_PRENOM            IN_INTERVENANT.V_IN_PRENOM%TYPE,
    V_IN_COMMENTAIRE       IN_INTERVENANT.V_IN_COMMENTAIRE%TYPE,
    C_IN_MOT_PASSE         IN_INTERVENANT.C_IN_MOT_PASSE%TYPE,
    L_IN_TEMPS_ACTIF       IN_INTERVENANT.L_IN_TEMPS_ACTIF%TYPE,
    L_IN_TEMPS_CONNEXION   IN_INTERVENANT.L_IN_TEMPS_CONNEXION%TYPE,
    V_IN_CREE_PAR          IN_INTERVENANT.V_IN_CREE_PAR%TYPE,
    D_IN_DATE_CREATION     IN_INTERVENANT.D_IN_DATE_CREATION%TYPE,
    V_IN_MODIFIE_PAR       IN_INTERVENANT.V_IN_MODIFIE_PAR%TYPE,
    D_IN_DATE_MODIFICATION IN_INTERVENANT.D_IN_DATE_MODIFICATION%TYPE,
    I_EN_CLE               EN_ENTITE.I_EN_CLE%TYPE,
    V_IN_NAME_PARENT       IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
    L_IN_SECTEUR           IN_INTERVENANT.L_IN_SECTEUR%TYPE,
    L_IN_SOUS_SECTEUR      IN_INTERVENANT.L_IN_SOUS_SECTEUR%TYPE);
  TYPE DTSW_IN_INTERVENANT IS REF CURSOR RETURN RECW_IN_INTERVENANT;

  PROCEDURE SPW_L6_In_Intervenant(NEW_SECTEUR IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                                  NEW_ENTITE  IN SI_SITE.I_EN_CLE%TYPE,
                                  NEW_SITE    IN SI_SITE.L_SI_CLE%TYPE,
                                  rc1         IN OUT DTSW_IN_INTERVENANT);
  PROCEDURE SPW_L7_In_Intervenant(NEW_GROUPE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                  rc1        IN OUT DTSW_IN_INTERVENANT);

  PROCEDURE SPW_L_PROFILS_VIDES(rc1 IN OUT DTSW_IN_INTERVENANT);
  PROCEDURE SPW_L_PROFILS_NON_VIDES(rc1 IN OUT DTSW_IN_INTERVENANT);
  PROCEDURE SPW_RAP_SG_Groupes(NEW_GROUP_NAME IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                               rc1            IN OUT DTSW_IN_INTERVENANT,
                               NEW_ENTITE     IN EN_ENTITE.I_EN_CLE%TYPE);
  PROCEDURE SPW_RAP2_SG_Groupes(NEW_GROUP_NAME IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                                rc1            IN OUT DTSW_IN_INTERVENANT,
                                NEW_SITE       IN SI_SITE.L_SI_CLE%TYPE);
  TYPE RECW2_IN_INTERVENANT IS RECORD(
    L_IN_CLE               IN_INTERVENANT.L_IN_CLE%TYPE,
    L_SI_CLE               IN_INTERVENANT.L_SI_CLE%TYPE,
    NAME                   IN_INTERVENANT.NAME%TYPE,
    I_LA_CLE               IN_INTERVENANT.I_LA_CLE%TYPE,
    I_ST_CLE               IN_INTERVENANT.I_ST_CLE%TYPE,
    I_CC_CLE               IN_INTERVENANT.I_CC_CLE%TYPE,
    I_NH_CLE               IN_INTERVENANT.I_NH_CLE%TYPE,
    V_IN_NO_EMPLOYE        IN_INTERVENANT.V_IN_NO_EMPLOYE%TYPE,
    V_IN_NOM               IN_INTERVENANT.V_IN_NOM%TYPE,
    V_IN_PRENOM            IN_INTERVENANT.V_IN_PRENOM%TYPE,
    V_IN_COMMENTAIRE       IN_INTERVENANT.V_IN_COMMENTAIRE%TYPE,
    C_IN_MOT_PASSE         IN_INTERVENANT.C_IN_MOT_PASSE%TYPE,
    L_IN_TEMPS_ACTIF       IN_INTERVENANT.L_IN_TEMPS_ACTIF%TYPE,
    L_IN_TEMPS_CONNEXION   IN_INTERVENANT.L_IN_TEMPS_CONNEXION%TYPE,
    V_IN_CREE_PAR          IN_INTERVENANT.V_IN_CREE_PAR%TYPE,
    D_IN_DATE_CREATION     IN_INTERVENANT.D_IN_DATE_CREATION%TYPE,
    V_IN_MODIFIE_PAR       IN_INTERVENANT.V_IN_MODIFIE_PAR%TYPE,
    D_IN_DATE_MODIFICATION IN_INTERVENANT.D_IN_DATE_MODIFICATION%TYPE,
    I_EN_CLE               EN_ENTITE.I_EN_CLE%TYPE,
    V_IN_NAME_PARENT       IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
    L_IN_SECTEUR           IN_INTERVENANT.L_IN_SECTEUR%TYPE,
    L_IN_SOUS_SECTEUR      IN_INTERVENANT.L_IN_SOUS_SECTEUR%TYPE,
    V_IN_GESTIONNAIRE      IN_INTERVENANT.V_IN_GESTIONNAIRE%TYPE);
  TYPE DTSW2_IN_INTERVENANT IS REF CURSOR RETURN RECW2_IN_INTERVENANT;
  PROCEDURE SPW_RAP_IN_Intervenant(rc1        IN OUT DTSW2_IN_INTERVENANT,
                                   NEW_ENTITE IN EN_ENTITE.I_EN_CLE%TYPE);
  PROCEDURE SPW_RAP2_IN_Intervenant(rc1      IN OUT DTSW2_IN_INTERVENANT,
                                    NEW_SITE IN SI_SITE.L_SI_CLE%TYPE);

  procedure SP_E_GROUPE_SECURITE(action          IN CHAR,
                                 new_CLE         IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
                                 new_DESCRIPTION IN GS_GROUPE_SECURITE.V_GS_DESCRIPTION%TYPE,
                                 new_ABREVIATION IN GS_GROUPE_SECURITE.V_GS_ABREVIATION%TYPE,
                                 new_NOM         IN GS_GROUPE_SECURITE.V_GS_NOM%TYPE);

  PROCEDURE SPW_L_PROFIL_INTERVENANT(NEW_NAME IN IN_INTERVENANT.NAME%TYPE,
                                     rc1      IN OUT DTSW2_IN_INTERVENANT);
  procedure SPW_E_IN_INTERVENANT(action                   IN CHAR,
                                 NEW_L_IN_CLE             IN OUT IN_INTERVENANT.L_IN_CLE%TYPE,
                                 NEW_L_SI_CLE             IN OUT IN_INTERVENANT.L_SI_CLE%TYPE,
                                 NEW_NAME                 IN IN_INTERVENANT.NAME%TYPE,
                                 NEW_I_LA_CLE             IN IN_INTERVENANT.I_LA_CLE%TYPE,
                                 NEW_I_ST_CLE             IN IN_INTERVENANT.I_ST_CLE%TYPE,
                                 NEW_I_CC_CLE             IN IN_INTERVENANT.I_CC_CLE%TYPE,
                                 NEW_I_NH_CLE             IN IN_INTERVENANT.I_NH_CLE%TYPE,
                                 NEW_V_IN_NO_EMPLOYE      IN IN_INTERVENANT.V_IN_NO_EMPLOYE%TYPE,
                                 NEW_V_IN_NOM             IN IN_INTERVENANT.V_IN_NOM%TYPE,
                                 NEW_V_IN_PRENOM          IN IN_INTERVENANT.V_IN_PRENOM%TYPE,
                                 NEW_V_IN_COMMENTAIRE     IN IN_INTERVENANT.V_IN_COMMENTAIRE%TYPE,
                                 NEW_C_IN_MOT_PASSE       IN IN_INTERVENANT.C_IN_MOT_PASSE%TYPE,
                                 NEW_L_IN_TEMPS_ACTIF     IN IN_INTERVENANT.L_IN_TEMPS_ACTIF%TYPE,
                                 NEW_L_IN_TEMPS_CONNEXION IN IN_INTERVENANT.L_IN_TEMPS_CONNEXION%TYPE,
                                 NEW_V_IN_NAME_PARENT     IN IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
                                 NEW_L_IN_SECTEUR         IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                                 NEW_L_IN_SOUS_SECTEUR    IN_INTERVENANT.L_IN_SOUS_SECTEUR%TYPE,
                                 NEW_V_IN_GESTIONNAIRE    IN IN_INTERVENANT.V_IN_GESTIONNAIRE%TYPE);

  procedure SP_E_IV_INTERVENTION(action                 IN CHAR,
                                 new_V_TI_CODE          IN TI_TYPE_INTERVENTION.V_TI_CODE%TYPE,
                                 new_NAME               IN IV_INTERVENTION.NAME%TYPE,
                                 new_V_IV_COURRIEL      IN IV_INTERVENTION.V_IV_COURRIEL%TYPE,
                                 new_L_SI_CLE           IN IV_INTERVENTION.L_SI_CLE%TYPE,
                                 new_V_IV_TEXTE_MESSAGE IN IV_INTERVENTION.V_IV_TEXTE_MESSAGE%TYPE,
                                 new_V_IV_TEXTE_SUIVI   IN IV_INTERVENTION.V_IV_TEXTE_SUIVI%TYPE,
                                 new_C_IV_ENVOI_MESSAGE IN IV_INTERVENTION.C_IV_ENVOI_MESSAGE%TYPE,
                                 new_C_IV_ENVOI_SUIVI   IN IV_INTERVENTION.C_IV_ENVOI_SUIVI%TYPE,
                                 new_V_SN_SITE          IN SN_SITE_INTERVENTION.V_SN_CLE%TYPE);
  TYPE DTS_IV_INTERVENTION is ref cursor return IV_INTERVENTION%ROWTYPE;
  procedure SPW_L_IV_INTERVENTION(rc1 IN OUT DTS_IV_INTERVENTION);

  TYPE REC_GROUPE_INTERVENANT is record(
    CLE         varchar2(50),
    DESCRIPTION varchar2(300));
  TYPE DTS_GROUPE_INTERVENANT is ref cursor return REC_GROUPE_INTERVENANT;
  procedure SP_D_USER(NEW_NAME IN IN_INTERVENANT.NAME%TYPE);
  TYPE REC_REGROUPEMENT is record(
    I_RG_CLE         RG_REGROUPEMENT.I_RG_CLE%TYPE,
    I_LA_CLEe        TR_TRADUCTION.I_LA_CLE%TYPE,
    V_TR_DESCRIPTION TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    NAME             RG_REGROUPEMENT.NAME%TYPE,
    I_RG_QUOTA       RG_REGROUPEMENT.I_RG_QUOTA%TYPE,
    I_RG_QUOTA_MIN   RG_REGROUPEMENT.I_RG_QUOTA_MIN%TYPE,
    L_IN_SECTEUR     RG_REGROUPEMENT.L_IN_SECTEUR%TYPE);
  TYPE DTSW_REGROUPEMENT is ref cursor return REC_REGROUPEMENT;
  procedure SPW_L_RG_REGROUPEMENT(rc1 IN OUT DTSW_REGROUPEMENT);

  TYPE REC_LIEN_REGROUPEMENT is record(
    I_CA_CLE            LRG_LIEN_REGROUPEMENT.I_CA_CLE%TYPE,
    V_TR_CA_DESCRIPTION TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    I_RG_CLE            LRG_LIEN_REGROUPEMENT.I_RG_CLE%TYPE,
    V_TR_RG_DESCRIPTION TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);
  TYPE DTSW_LIEN_REGROUPEMENT is ref cursor return REC_LIEN_REGROUPEMENT;
  procedure SPW_L_LRG_REGROUPEMENT(NEW_SECTEUR RG_REGROUPEMENT.L_IN_SECTEUR%TYPE,
                                   rc1         IN OUT DTSW_LIEN_REGROUPEMENT);

  procedure SP_E_SECTEUR(action                 IN CHAR,
                         new_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                         new_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                         new_L_TV_PARENT_VALEUR IN TV_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE,
                         new_V_TV_PARENT_CODE   IN TV_TABLE_VALEUR.V_TV_PARENT_CODE%TYPE,
                         new_B_TV_ACTIF         IN TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
                         new_V_TV_ROLE          IN TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
                         new_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                         new_V_TV_DESCRIPTION   IN TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE,
                         new_B_TV_ADMINISTRER   IN TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
                         new_B_TV_OBLIGATOIRE   IN TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
                         new_V_TR_ABREVIATION   IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                         new_V_TR_DESCRIPTION   IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);

  TYPE REC_RS_ROLE_SECURITE is record(
    V_RS_ROLE        RS_ROLE_SECURITE.V_RS_ROLE%TYPE,
    V_RS_APPLICATION RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
    V_RS_CLASSE_FORM RS_ROLE_SECURITE.V_RS_CLASSE_FORM%TYPE,
    V_RS_CLASSE_VO   RS_ROLE_SECURITE.V_RS_CLASSE_VO%TYPE,
    V_RS_URL         RS_ROLE_SECURITE.V_RS_URL%TYPE,
    V_TV_CODE        RS_ROLE_SECURITE.V_TV_CODE%TYPE,
    V_RS_DESCRIPTION RS_ROLE_SECURITE.V_RS_DESCRIPTION%TYPE,
    B_RS_ADMINISTRER RS_ROLE_SECURITE.B_RS_ADMINISTRER%TYPE,
    L_RS_ECRAN       RS_ROLE_SECURITE.L_RS_ECRAN%TYPE,
    V_TR_DESCRIPTION TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);
  TYPE DTSW_RS_ROLE_SECURITE is ref cursor return REC_RS_ROLE_SECURITE;
  procedure SPW_L_RS_ROLE_SECURITE(NEW_APPLICATION IN RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
                                   NEW_ECRAN       IN RS_ROLE_SECURITE.L_RS_ECRAN%TYPE,
                                   NEW_ROLE        IN RS_ROLE_SECURITE.V_RS_ROLE%TYPE,
                                   NEW_ADMINISTRE  IN RS_ROLE_SECURITE.B_RS_ADMINISTRER%TYPE,
                                   rc1             IN OUT DTSW_RS_ROLE_SECURITE);

  TYPE REC_RS_ROLE_SECURITE_TOUS is record(
    ROLE       RS_ROLE_SECURITE.V_RS_ROLE%TYPE,
    ADMINISTRE RS_ROLE_SECURITE.B_RS_ADMINISTRER%TYPE);
  TYPE DTSW_RS_ROLE_SECURITE_TOUS is ref cursor return REC_RS_ROLE_SECURITE_TOUS;
  procedure SPW_L2_RS_ROLE_SECURITE(rc1 IN OUT DTSW_RS_ROLE_SECURITE_TOUS);
  procedure SPW_L3_RS_ROLE_SECURITE(rc1 IN OUT DTSW_RS_ROLE_SECURITE_TOUS);

  procedure SPW_E_RS_ROLE_SECURITE(action               IN CHAR,
                                   NEW_V_RS_ROLE        IN RS_ROLE_SECURITE.V_RS_ROLE%TYPE,
                                   NEW_V_RS_APPLICATION IN RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
                                   NEW_V_RS_CLASSE_FORM IN RS_ROLE_SECURITE.V_RS_CLASSE_FORM%TYPE,
                                   NEW_V_RS_CLASSE_VO   IN RS_ROLE_SECURITE.V_RS_CLASSE_VO%TYPE,
                                   NEW_V_RS_URL         IN RS_ROLE_SECURITE.V_RS_URL%TYPE,
                                   NEW_V_TV_CODE        IN RS_ROLE_SECURITE.V_TV_CODE%TYPE,
                                   NEW_V_RS_DESCRIPTION IN RS_ROLE_SECURITE.V_RS_DESCRIPTION%TYPE,
                                   NEW_B_RS_ADMINISTRER IN RS_ROLE_SECURITE.B_RS_ADMINISTRER%TYPE,
                                   NEW_L_RS_ECRAN       IN RS_ROLE_SECURITE.L_RS_ECRAN%TYPE);

  TYPE DTSW_GS_GROUPE_SECURITE IS REF CURSOR RETURN GS_GROUPE_SECURITE%ROWTYPE;
  procedure SPW_L_GS_GROUPE_SECURITE(NEW_GROUPE GS_GROUPE_SECURITE.V_GS_NOM%TYPE,
                                     rc1        IN OUT DTSW_GS_GROUPE_SECURITE);
  PROCEDURE SPW_L_GROUPES_VIDES(rc1 IN OUT DTSW_GS_GROUPE_SECURITE);
  PROCEDURE SPW_L_GROUPES_NON_VIDES(rc1 IN OUT DTSW_GS_GROUPE_SECURITE);

  procedure SP_I_TRADUCTION(NEW_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                            NEW_I_LA_CLE         IN TR_TRADUCTION.I_LA_CLE%TYPE,
                            NEW_C_TR_GENRE_FICHIER IN TR_TRADUCTION.C_TR_GENRE_FICHIER%TYPE,
                            NEW_V_TR_ABREVIATION IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                            NEW_V_TR_DESCRIPTION IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);
                            
  procedure SP_E_TRADUCTION(NEW_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                            NEW_I_LA_CLE         IN TR_TRADUCTION.I_LA_CLE%TYPE,
                            NEW_C_TR_GENRE_FICHIER IN TR_TRADUCTION.C_TR_GENRE_FICHIER%TYPE,                            
                            NEW_V_TR_ABREVIATION IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                            NEW_V_TR_DESCRIPTION IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);

  procedure SP_D_TRADUCTION(NEW_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE);
    
  TYPE REC_TV_TABLE_VALEUR is record(
    V_TV_CODE          TV_TABLE_VALEUR.V_TV_CODE%TYPE,
    L_TV_VALEUR        TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
    B_TV_ACTIF         TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
    V_TV_ROLE          TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
    L_TV_ACTION        TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
    V_TV_DESCRIPTION   TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE,
    B_TV_ADMINISTRER   TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
    B_TV_OBLIGATOIRE   TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
    I_LA_CLE           TR_TRADUCTION.I_LA_CLE%TYPE,
    C_TR_GENRE_FICHIER TR_TRADUCTION.C_TR_GENRE_FICHIER%TYPE,
    V_TR_ABREVIATION   TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
    V_TR_DESCRIPTION   TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    V_TV_PARENT_CODE   CTV_CRITERE_TABLE_VALEUR.V_TV_PARENT_CODE%TYPE,
    L_TV_PARENT_VALEUR CTV_CRITERE_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE);
  TYPE DTSW_TV_TABLE_VALEUR IS REF CURSOR RETURN REC_TV_TABLE_VALEUR;

  procedure SP_L_TV_TABLE_VALEUR(NEW_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                 NEW_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                                 rc1 OUT DTSW_TV_TABLE_VALEUR);

  procedure SP_I_TV_TABLE_VALEUR(NEW_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                 NEW_B_TV_ACTIF         IN TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
                                 NEW_V_TV_ROLE          IN TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
                                 NEW_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                                 NEW_B_TV_ADMINISTRER   IN TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
                                 NEW_B_TV_OBLIGATOIRE   IN TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
                                 NEW_V_TV_DESCRIPTION   IN TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE);

  procedure SP_E_TV_TABLE_VALEUR(NEW_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                 NEW_B_TV_ACTIF         IN TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
                                 NEW_V_TV_ROLE          IN TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
                                 NEW_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                                 NEW_B_TV_ADMINISTRER   IN TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
                                 NEW_B_TV_OBLIGATOIRE   IN TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
                                 NEW_V_TV_DESCRIPTION   IN TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE);

  procedure SP_D_TV_TABLE_VALEUR(NEW_V_TV_CODE   IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE);

  procedure SP_I_CTV_CRITERE_TABLE_VALEUR(NEW_V_TV_CODE IN CTV_CRITERE_TABLE_VALEUR.V_TV_CODE%TYPE,
                                          NEW_L_TV_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                          NEW_L_TV_PARENT_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE,
                                          NEW_V_TV_PARENT_CODE IN CTV_CRITERE_TABLE_VALEUR.V_TV_PARENT_CODE%TYPE);

  TYPE REC_CTV_CRITERE_TABLE_VALEUR is record(
    V_TV_CODE          CTV_CRITERE_TABLE_VALEUR.V_TV_CODE%TYPE,
    L_TV_VALEUR        CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
    L_TV_PARENT_VALEUR CTV_CRITERE_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE,
    V_TV_PARENT_CODE   CTV_CRITERE_TABLE_VALEUR.V_TV_PARENT_CODE%TYPE);
  TYPE DTSW_CTV_CRITERE_TABLE_VALEUR IS REF CURSOR RETURN REC_CTV_CRITERE_TABLE_VALEUR;
  
  procedure SP_L_CTV_CRITERE_TABLE_VALEUR(NEW_L_TV_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                          rc1 OUT DTSW_CTV_CRITERE_TABLE_VALEUR);
                                                                                    
  procedure SP_D_CTV_CRITERE_TABLE_VALEUR(NEW_L_TV_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                  NEW_L_TV_VALEUR_PARENT IN CTV_CRITERE_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE);

  TYPE REC_TV_ACTION is record(
    L_TV_VALEUR      TV_TABLE_VALEUR.L_TV_VALEUR%TYPE);
  TYPE DTSW_TV_ACTION IS REF CURSOR RETURN REC_TV_ACTION;
  
  procedure SP_L_TV_ACTION(NEW_V_TV_CODE IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                           rc1           OUT DTSW_TV_ACTION);
                                  
  TYPE REC_SUJET_HISTORIQUE IS RECORD(
    L_LI_CLE_PARENT     AUD_LI_LIAISON.L_LI_CLE_PARENT%TYPE,
    L_LI_SITE_PARENT    AUD_LI_LIAISON.L_LI_SITE_PARENT%TYPE,
    I_EN_CLE            SI_SITE.I_EN_CLE%TYPE,
    L_LI_CLE_ENFANT     AUD_LI_LIAISON.L_LI_CLE_ENFANT%TYPE,
    L_LI_SITE_ENFANT    AUD_LI_LIAISON.L_LI_SITE_ENFANT%TYPE,
    V_SU_REFERENCE_3    SU_SUJET.V_SU_REFERENCE_3%TYPE,
    I_SX_CLE            SU_SUJET.I_SX_CLE%TYPE,
    V_SU_NOM            SU_SUJET.V_SU_NOM%TYPE,
    V_SU_PRENOM         SU_SUJET.V_SU_PRENOM%TYPE,
    V_SU_SURNOM         SU_SUJET.V_SU_SURNOM%TYPE,
    D_SU_DATE_NAISSANCE SU_SUJET.D_SU_DATE_NAISSANCE%TYPE,
    V_SO_NOM            SO_SOCIETE.V_SO_NOM%TYPE);
  TYPE DTSW_SUJET_HISTORIQUE IS REF CURSOR RETURN REC_SUJET_HISTORIQUE;
  procedure SP_L_SUJET_HISTORIQUE(rc1 OUT DTSW_SUJET_HISTORIQUE);

  procedure SP_E_SUJET_HISTORIQUE(NEW_L_LI_CLE_PARENT  AUD_LI_LIAISON.L_LI_CLE_PARENT%TYPE,
                                  NEW_L_LI_SITE_PARENT AUD_LI_LIAISON.L_LI_SITE_PARENT%TYPE,
                                  NEW_L_LI_CLE_ENFANT  AUD_LI_LIAISON.L_LI_CLE_ENFANT%TYPE,
                                  NEW_L_LI_SITE_ENFANT AUD_LI_LIAISON.L_LI_SITE_ENFANT%TYPE);
END CARDEX_WEB_PILOTAGE;
/
CREATE OR REPLACE PACKAGE BODY CARDEX_WEB_PILOTAGE is
  procedure SP_REMPLIR_TRADUCTION(action          IN char,
                                  new_cle         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                                  new_genre       IN char,
                                  new_description IN varchar,
                                  new_abreviation IN varchar) is
  begin
    if action = 'I' then
      FOR rec IN (select I_LA_CLE from LA_LANGUE) LOOP
        insert into TR_TRADUCTION
        values
          (new_cle,
           rec.I_LA_CLE,
           new_genre,
           new_abreviation,
           new_description,
           CARDEX_SPECIAL.g_usager,
           sysdate,
           NULL,
           NULL);
      END LOOP;
    elsif action = 'U' then
      update TR_TRADUCTION
         set V_TR_DESCRIPTION = new_description
       where L_TR_CLE = new_cle;
    end if;
  END SP_REMPLIR_TRADUCTION;

  --Retourne la description correspondante à une clé.
  procedure SPW_L_TR_TRADUCTION(NEW_TR_CLE IN NUMBER,
                                rc1        IN OUT DTSW_TR_TRADUCTION) IS
  BEGIN
    open rc1 for
      select T.V_TR_DESCRIPTION
        FROM TR_TRADUCTION T
       Where T.L_TR_CLE = NEW_TR_CLE
         and T.I_LA_CLE = CARDEX_SPECIAL.g_langue;
  END SPW_L_TR_TRADUCTION;

  --Retourne les membres d'un secteur donné (menu principal pilotage)
  PROCEDURE SPW_L6_In_Intervenant(NEW_SECTEUR IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                                  NEW_ENTITE  IN SI_SITE.I_EN_CLE%TYPE,
                                  NEW_SITE    IN SI_SITE.L_SI_CLE%TYPE,
                                  rc1         IN OUT DTSW_IN_INTERVENANT) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE,
             P.I_NH_CLE,
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND P.L_IN_SECTEUR = NEW_SECTEUR
         AND (S.I_EN_CLE = NEW_ENTITE OR NEW_ENTITE = 0)
         AND (S.L_SI_CLE = NEW_SITE OR NEW_SITE = 0)
       ORDER BY P.V_IN_NOM, P.V_IN_PRENOM;
  END SPW_L6_In_Intervenant;
  --Retourne les intervenants d'un groupe de sécurité donné
  PROCEDURE SPW_L7_In_Intervenant(NEW_GROUPE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                  rc1        IN OUT DTSW_IN_INTERVENANT) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE,
             P.I_NH_CLE,
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR
        FROM IN_INTERVENANT             P,
             SI_SITE                    S,
             IGS_INTERVENANT_GROUPE_SEC I,
             GS_GROUPE_SECURITE         G
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND I.L_GS_CLE = NEW_GROUPE
         AND I.L_GS_CLE = G.L_GS_CLE
         AND I.V_NAME = P.NAME
       ORDER BY P.L_SI_CLE, P.V_IN_NOM, P.V_IN_PRENOM;
  END SPW_L7_In_Intervenant;

  --Lecture des informations du profil de l'intervenant.
  --Retourne les valeurs globales qui seront utilisées durant la session.
  PROCEDURE SPW_L_PROFIL_INTERVENANT(NEW_NAME IN IN_INTERVENANT.NAME%TYPE,
                                     rc1      IN OUT DTSW2_IN_INTERVENANT) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE "I_CC_CLE",
             P.I_NH_CLE "I_NH_CLE",
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             NVL(P.L_IN_SOUS_SECTEUR, 0) as "L_IN_SOUS_SECTEUR",
             P.V_IN_GESTIONNAIRE
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND P.NAME = NEW_NAME;
  END SPW_L_PROFIL_INTERVENANT;

  procedure SP_E_GROUPE_SECURITE(action          IN CHAR,
                                 new_CLE         IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
                                 new_DESCRIPTION IN GS_GROUPE_SECURITE.V_GS_DESCRIPTION%TYPE,
                                 new_ABREVIATION IN GS_GROUPE_SECURITE.V_GS_ABREVIATION%TYPE,
                                 new_NOM         IN GS_GROUPE_SECURITE.V_GS_NOM%TYPE) is
  begin
    if action = 'I' then
      insert into GS_GROUPE_SECURITE
        (L_GS_CLE, V_GS_DESCRIPTION, V_GS_ABREVIATION, V_GS_NOM)
      values
        (new_CLE, new_DESCRIPTION, new_ABREVIATION, new_NOM);
      commit;
    elsif action = 'U' then
      update GS_GROUPE_SECURITE
         set V_GS_DESCRIPTION = new_DESCRIPTION,
             V_GS_NOM         = new_NOM,
             V_GS_ABREVIATION = new_ABREVIATION
       where L_GS_CLE = NEW_CLE;
      commit;
    elsif action = 'D' then
      --Suppression des sous-groupes enfants
      delete from SGR_SOUS_GROUPE_SECURITE
       where L_SGR_ENFANT_CLE = NEW_CLE;
      commit;
      --Suppression des sous-groupes parents
      delete from SGR_SOUS_GROUPE_SECURITE
       where L_SGR_PARENT_CLE = NEW_CLE;
      commit;
      --Suppression des rôles
      delete from GR_GROUPE_ROLE where L_GR_CLE = NEW_CLE;
      commit;
      --Suppression des intervenants
      delete from IGS_INTERVENANT_GROUPE_SEC where L_GS_CLE = NEW_CLE;
      commit;
      delete from GS_GROUPE_SECURITE where L_GS_CLE = NEW_CLE;
      commit;
    end if;
  END SP_E_GROUPE_SECURITE;

  procedure SPW_E_IN_INTERVENANT(action                   IN CHAR,
                                 NEW_L_IN_CLE             IN OUT IN_INTERVENANT.L_IN_CLE%TYPE,
                                 NEW_L_SI_CLE             IN OUT IN_INTERVENANT.L_SI_CLE%TYPE,
                                 NEW_NAME                 IN IN_INTERVENANT.NAME%TYPE,
                                 NEW_I_LA_CLE             IN IN_INTERVENANT.I_LA_CLE%TYPE,
                                 NEW_I_ST_CLE             IN IN_INTERVENANT.I_ST_CLE%TYPE,
                                 NEW_I_CC_CLE             IN IN_INTERVENANT.I_CC_CLE%TYPE,
                                 NEW_I_NH_CLE             IN IN_INTERVENANT.I_NH_CLE%TYPE,
                                 NEW_V_IN_NO_EMPLOYE      IN IN_INTERVENANT.V_IN_NO_EMPLOYE%TYPE,
                                 NEW_V_IN_NOM             IN IN_INTERVENANT.V_IN_NOM%TYPE,
                                 NEW_V_IN_PRENOM          IN IN_INTERVENANT.V_IN_PRENOM%TYPE,
                                 NEW_V_IN_COMMENTAIRE     IN IN_INTERVENANT.V_IN_COMMENTAIRE%TYPE,
                                 NEW_C_IN_MOT_PASSE       IN IN_INTERVENANT.C_IN_MOT_PASSE%TYPE,
                                 NEW_L_IN_TEMPS_ACTIF     IN IN_INTERVENANT.L_IN_TEMPS_ACTIF%TYPE,
                                 NEW_L_IN_TEMPS_CONNEXION IN IN_INTERVENANT.L_IN_TEMPS_CONNEXION%TYPE,
                                 NEW_V_IN_NAME_PARENT     IN IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
                                 NEW_L_IN_SECTEUR         IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                                 NEW_L_IN_SOUS_SECTEUR    IN IN_INTERVENANT.L_IN_SOUS_SECTEUR%TYPE,
                                 NEW_V_IN_GESTIONNAIRE    IN IN_INTERVENANT.V_IN_GESTIONNAIRE%TYPE) IS
  begin
    if action = 'I' then
      select seq_dossier.nextval into NEW_L_IN_CLE from dual;
      insert into IN_INTERVENANT
        (L_IN_CLE,
         L_SI_CLE,
         NAME,
         I_LA_CLE,
         I_ST_CLE,
         I_CC_CLE,
         I_NH_CLE,
         V_IN_NO_EMPLOYE,
         V_IN_NOM,
         V_IN_PRENOM,
         V_IN_COMMENTAIRE,
         C_IN_MOT_PASSE,
         L_IN_TEMPS_ACTIF,
         L_IN_TEMPS_CONNEXION,
         V_IN_CREE_PAR,
         D_IN_DATE_CREATION,
         V_IN_NAME_PARENT,
         L_IN_SECTEUR,
         L_IN_SOUS_SECTEUR,
         V_IN_GESTIONNAIRE)
      values
        (NEW_L_IN_CLE,
         NEW_L_SI_CLE,
         NEW_NAME,
         NEW_I_LA_CLE,
         NEW_I_ST_CLE,
         NEW_I_CC_CLE,
         NEW_I_NH_CLE,
         NEW_V_IN_NO_EMPLOYE,
         NEW_V_IN_NOM,
         NEW_V_IN_PRENOM,
         NEW_V_IN_COMMENTAIRE,
         NEW_C_IN_MOT_PASSE,
         NEW_L_IN_TEMPS_ACTIF,
         NEW_L_IN_TEMPS_CONNEXION,
         CARDEX_SPECIAL.g_usager,
         sysdate,
         NEW_V_IN_NAME_PARENT,
         NEW_L_IN_SECTEUR,
         NEW_L_IN_SOUS_SECTEUR,
         NEW_V_IN_GESTIONNAIRE);
      commit;
    elsif action = 'U' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_INTERVENANT(NEW_L_IN_CLE, NEW_L_SI_CLE);
      --Mise à jour des changements
      update IN_INTERVENANT
         set L_SI_CLE               = NEW_L_SI_CLE,
             I_LA_CLE               = NEW_I_LA_CLE,
             I_ST_CLE               = NEW_I_ST_CLE,
             I_CC_CLE               = NEW_I_CC_CLE,
             I_NH_CLE               = NEW_I_NH_CLE,
             V_IN_NO_EMPLOYE        = NEW_V_IN_NO_EMPLOYE,
             V_IN_NOM               = NEW_V_IN_NOM,
             V_IN_PRENOM            = NEW_V_IN_PRENOM,
             V_IN_COMMENTAIRE       = NEW_V_IN_COMMENTAIRE,
             C_IN_MOT_PASSE         = NEW_C_IN_MOT_PASSE,
             L_IN_TEMPS_ACTIF       = NEW_L_IN_TEMPS_ACTIF,
             L_IN_TEMPS_CONNEXION   = NEW_L_IN_TEMPS_CONNEXION,
             V_IN_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_IN_DATE_MODIFICATION = sysdate,
             V_IN_NAME_PARENT       = NEW_V_IN_NAME_PARENT,
             L_IN_SECTEUR           = NEW_L_IN_SECTEUR,
             L_IN_SOUS_SECTEUR      = NEW_L_IN_SOUS_SECTEUR,
             V_IN_GESTIONNAIRE      = NEW_V_IN_GESTIONNAIRE
       where L_IN_CLE = NEW_L_IN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'A' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_INTERVENANT(NEW_L_IN_CLE, NEW_L_SI_CLE);
      --Activation.  Seul le statut est mis à jour
      update IN_INTERVENANT
         set I_ST_CLE               = NEW_I_ST_CLE,
             V_IN_COMMENTAIRE       = NEW_V_IN_COMMENTAIRE,
             V_IN_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_IN_DATE_MODIFICATION = sysdate
       where L_IN_CLE = NEW_L_IN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'D' then
      --Audit des changements
      cardex_audit.SP_E_AUDIT_INTERVENANT(NEW_L_IN_CLE, NEW_L_SI_CLE);
      --Désactivation
      update IN_INTERVENANT
         set I_ST_CLE               = NEW_I_ST_CLE,
             V_IN_COMMENTAIRE       = NEW_V_IN_COMMENTAIRE,
             V_IN_MODIFIE_PAR       = CARDEX_SPECIAL.g_usager,
             D_IN_DATE_MODIFICATION = sysdate
       where L_IN_CLE = NEW_L_IN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    elsif action = 'S' then
      --Suppression des groupes
      delete from IGS_INTERVENANT_GROUPE_SEC where V_NAME = NEW_NAME;
      commit;
      --Suppression des rôles
      delete from IR_INTERVENANT_ROLE where V_NAME = NEW_NAME;
      commit;
      --Suppression définitive
      delete from IN_INTERVENANT
       where L_IN_CLE = NEW_L_IN_CLE
         and L_SI_CLE = NEW_L_SI_CLE;
      commit;
    end if;
  exception
    when others then
      raise_application_error(-20000,
                              'Database : SP_E_IN_INTERVENANT',
                              TRUE);
      --Audit des accès
      cardex_special.sp_e_ac_acces(action,
                                   'IN',
                                   NEW_L_IN_CLE,
                                   NEW_L_SI_CLE,
                                   'IN',
                                   NEW_L_IN_CLE,
                                   NEW_L_SI_CLE,
                                   'IN',
                                   NEW_L_IN_CLE,
                                   NEW_L_SI_CLE);
  END SPW_E_IN_INTERVENANT;
  --Retourne les intervenants d'un groupe de sécurité par entité.
  PROCEDURE SPW_RAP_SG_Groupes(NEW_GROUP_NAME IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                               rc1            IN OUT DTSW_IN_INTERVENANT,
                               NEW_ENTITE     IN EN_ENTITE.I_EN_CLE%TYPE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE,
             P.I_NH_CLE,
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND P.L_IN_SECTEUR = NEW_GROUP_NAME
         AND S.I_EN_CLE = NEW_ENTITE
       ORDER BY P.V_IN_NOM, P.V_IN_PRENOM;
  END SPW_RAP_SG_Groupes;
  --Retourne les intervenants d'un groupe de sécurité par site.
  PROCEDURE SPW_RAP2_SG_Groupes(NEW_GROUP_NAME IN IN_INTERVENANT.L_IN_SECTEUR%TYPE,
                                rc1            IN OUT DTSW_IN_INTERVENANT,
                                NEW_SITE       IN SI_SITE.L_SI_CLE%TYPE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE,
             P.I_NH_CLE,
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND P.L_IN_SECTEUR = NEW_GROUP_NAME
         AND S.L_SI_CLE = NEW_SITE
       ORDER BY P.V_IN_NOM, P.V_IN_PRENOM;
  END SPW_RAP2_SG_Groupes;
  --Liste des intervenants avec leur groupe par entité
  PROCEDURE SPW_RAP_IN_INTERVENANT(rc1        IN OUT DTSW2_IN_INTERVENANT,
                                   NEW_ENTITE IN EN_ENTITE.I_EN_CLE%TYPE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE "I_CC_CLE",
             P.I_NH_CLE "I_NH_CLE",
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR,
             P.V_IN_GESTIONNAIRE
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND S.I_EN_CLE = NEW_ENTITE
       ORDER BY P.V_IN_NOM, P.V_IN_PRENOM;
  END SPW_RAP_IN_INTERVENANT;
  --Liste des intervenants avec leur groupe par site
  PROCEDURE SPW_RAP2_IN_INTERVENANT(rc1      IN OUT DTSW2_IN_INTERVENANT,
                                    NEW_SITE IN SI_SITE.L_SI_CLE%TYPE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE "I_CC_CLE",
             P.I_NH_CLE "I_NH_CLE",
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR,
             P.V_IN_GESTIONNAIRE
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE S.L_SI_CLE = P.L_SI_CLE
         AND S.L_SI_CLE = NEW_SITE
       ORDER BY P.V_IN_NOM, P.V_IN_PRENOM;
  END SPW_RAP2_IN_INTERVENANT;

  --Table des intervenants concernés lors d'un mouvement de personnel.
  procedure SP_E_IV_INTERVENTION(action                 IN CHAR,
                                 new_V_TI_CODE          IN TI_TYPE_INTERVENTION.V_TI_CODE%TYPE,
                                 new_NAME               IN IV_INTERVENTION.NAME%TYPE,
                                 new_V_IV_COURRIEL      IN IV_INTERVENTION.V_IV_COURRIEL%TYPE,
                                 new_L_SI_CLE           IN IV_INTERVENTION.L_SI_CLE%TYPE,
                                 new_V_IV_TEXTE_MESSAGE IN IV_INTERVENTION.V_IV_TEXTE_MESSAGE%TYPE,
                                 new_V_IV_TEXTE_SUIVI   IN IV_INTERVENTION.V_IV_TEXTE_SUIVI%TYPE,
                                 new_C_IV_ENVOI_MESSAGE IN IV_INTERVENTION.C_IV_ENVOI_MESSAGE%TYPE,
                                 new_C_IV_ENVOI_SUIVI   IN IV_INTERVENTION.C_IV_ENVOI_SUIVI%TYPE,
                                 new_V_SN_SITE          IN SN_SITE_INTERVENTION.V_SN_CLE%TYPE) IS
  begin
    if action = 'I' then
      insert into IV_INTERVENTION
        (V_TI_CODE,
         NAME,
         V_IV_COURRIEL,
         L_SI_CLE,
         V_IV_TEXTE_MESSAGE,
         V_IV_TEXTE_SUIVI,
         C_IV_ENVOI_MESSAGE,
         C_IV_ENVOI_SUIVI,
         D_IV_DATE_CREATION,
         V_IV_CREE_PAR,
         V_SN_SITE)
      values
        (new_V_TI_CODE,
         new_NAME,
         new_V_IV_COURRIEL,
         new_L_SI_CLE,
         new_V_IV_TEXTE_MESSAGE,
         new_V_IV_TEXTE_SUIVI,
         new_C_IV_ENVOI_MESSAGE,
         new_C_IV_ENVOI_SUIVI,
         SYSDATE,
         CARDEX_SPECIAL.g_usager,
         new_V_SN_SITE);
      commit;
    elsif action = 'U' then
      update IV_INTERVENTION
         set V_IV_COURRIEL      = NEW_V_IV_COURRIEL,
             L_SI_CLE           = NEW_L_SI_CLE,
             V_IV_TEXTE_MESSAGE = NEW_V_IV_TEXTE_MESSAGE,
             V_IV_TEXTE_SUIVI   = NEW_V_IV_TEXTE_SUIVI,
             C_IV_ENVOI_MESSAGE = NEW_C_IV_ENVOI_MESSAGE,
             C_IV_ENVOI_SUIVI   = NEW_C_IV_ENVOI_SUIVI,
             V_SN_SITE          = NEW_V_SN_SITE
       where V_TI_CODE = NEW_V_TI_CODE
         and NAME = new_NAME;
      commit;
    elsif action = 'D' then
      delete from IV_INTERVENTION
       where V_TI_CODE = new_V_TI_CODE
         and NAME = new_NAME;
      commit;
    end if;
  END SP_E_IV_INTERVENTION;
  procedure SPW_L_IV_INTERVENTION(rc1 IN OUT DTS_IV_INTERVENTION) is
  begin
    open rc1 for
      select P.V_TI_CODE,
             P.NAME,
             P.V_IV_COURRIEL,
             P.L_SI_CLE,
             P.V_IV_TEXTE_MESSAGE,
             P.V_IV_TEXTE_SUIVI,
             P.C_IV_ENVOI_MESSAGE,
             P.C_IV_ENVOI_SUIVI,
             P.D_IV_DATE_CREATION,
             P.V_IV_CREE_PAR,
             P.V_SN_SITE
        from IV_INTERVENTION P
       order by V_TI_CODE, NAME;
  END SPW_L_IV_INTERVENTION;

  procedure SP_D_USER(NEW_NAME IN in_intervenant.NAME%TYPE) is
  begin
    --Suppression des groupes
    delete from IGS_INTERVENANT_GROUPE_SEC where V_NAME = NEW_NAME;
    commit;
    --Suppression des rôles
    delete from IR_INTERVENANT_ROLE where V_NAME = NEW_NAME;
    commit;
    --Suppression définitive
    delete from IN_INTERVENANT where NAME = NEW_NAME;
    commit;
  END SP_D_USER;
  procedure SPW_L_RG_REGROUPEMENT(rc1 IN OUT DTSW_REGROUPEMENT) IS
  BEGIN
    open rc1 for
      select r.i_rg_cle,
             t.i_la_cle,
             t.v_tr_description,
             r.name,
             r.i_rg_quota,
             r.i_rg_quota_min,
             r.l_in_secteur
        FROM RG_REGROUPEMENT R, TR_TRADUCTION T
       WHERE R.I_RG_CLE = T.L_TR_CLE
       ORDER BY t.v_tr_description;
  END SPW_L_RG_REGROUPEMENT;

  procedure SPW_L_LRG_REGROUPEMENT(NEW_SECTEUR IN RG_REGROUPEMENT.L_IN_SECTEUR%TYPE,
                                   rc1         IN OUT DTSW_LIEN_REGROUPEMENT) IS
  BEGIN
    open rc1 for
      select LRG.I_CA_CLE,
             TCA.v_tr_description,
             LRG.I_RG_CLE,
             TRG.v_tr_description
        FROM LRG_LIEN_REGROUPEMENT LRG,
             RG_REGROUPEMENT       R,
             TR_TRADUCTION         TRG,
             CA_CATEGORIE          CA,
             TR_TRADUCTION         TCA
       WHERE R.I_RG_CLE = LRG.I_RG_CLE
         AND CA.I_CA_CLE = LRG.I_CA_CLE
         AND R.I_RG_CLE = TRG.L_TR_CLE
         AND TRG.I_LA_CLE = CARDEX_SPECIAL.g_langue
         AND CA.I_CA_CLE = TCA.L_TR_CLE
         AND TCA.I_LA_CLE = CARDEX_SPECIAL.g_langue
         AND R.L_IN_SECTEUR = NEW_SECTEUR
       ORDER BY TCA.v_tr_description, TRG.v_tr_description;
  END SPW_L_LRG_REGROUPEMENT;

  procedure SP_E_SECTEUR(action                 IN CHAR,
                         new_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                         new_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                         new_L_TV_PARENT_VALEUR IN TV_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE,
                         new_V_TV_PARENT_CODE   IN TV_TABLE_VALEUR.V_TV_PARENT_CODE%TYPE,
                         new_B_TV_ACTIF         IN TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
                         new_V_TV_ROLE          IN TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
                         new_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                         new_V_TV_DESCRIPTION   IN TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE,
                         new_B_TV_ADMINISTRER   IN TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
                         new_B_TV_OBLIGATOIRE   IN TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
                         new_V_TR_ABREVIATION   IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                         new_V_TR_DESCRIPTION   IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE) is
  begin
    if action = 'I' then
      insert into TV_TABLE_VALEUR
        (L_TV_VALEUR,
         V_TV_CODE,
         L_TV_PARENT_VALEUR,
         V_TV_PARENT_CODE,
         B_TV_ACTIF,
         V_TV_ROLE,
         L_TV_ACTION,
         V_TV_DESCRIPTION,
         B_TV_ADMINISTRER,
         B_TV_OBLIGATOIRE)
      values
        (new_L_TV_VALEUR,
         new_V_TV_CODE,
         new_L_TV_PARENT_VALEUR,
         new_V_TV_PARENT_CODE,
         new_B_TV_ACTIF,
         new_V_TV_ROLE,
         new_L_TV_ACTION,
         new_V_TV_DESCRIPTION,
         new_B_TV_ADMINISTRER,
         new_B_TV_OBLIGATOIRE);
      SP_REMPLIR_TRADUCTION(action,
                            new_L_TV_VALEUR,
                            '',
                            new_V_TR_DESCRIPTION,
                            new_V_TR_ABREVIATION);
      commit;
    elsif action = 'U' then
      update TV_TABLE_VALEUR
         set V_TV_DESCRIPTION = new_V_TV_DESCRIPTION,
             B_TV_ACTIF       = new_B_TV_ACTIF,
             B_TV_ADMINISTRER = new_B_TV_ADMINISTRER,
             B_TV_OBLIGATOIRE = new_B_TV_OBLIGATOIRE
       where L_TV_VALEUR = new_L_TV_VALEUR;
      SP_REMPLIR_TRADUCTION(action,
                            new_L_TV_VALEUR,
                            '',
                            new_V_TR_DESCRIPTION,
                            new_V_TR_ABREVIATION);
      commit;
    elsif action = 'D' then
      delete from TR_TRADUCTION where L_TR_CLE = new_L_TV_VALEUR;
      delete from TV_TABLE_VALEUR where L_TV_VALEUR = new_L_TV_VALEUR;
      commit;
    end if;
  END SP_E_SECTEUR;

  procedure SPW_L_RS_ROLE_SECURITE(NEW_APPLICATION IN RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
                                   NEW_ECRAN       IN RS_ROLE_SECURITE.L_RS_ECRAN%TYPE,
                                   NEW_ROLE        IN RS_ROLE_SECURITE.V_RS_ROLE%TYPE,
                                   NEW_ADMINISTRE  IN RS_ROLE_SECURITE.B_RS_ADMINISTRER%TYPE,
                                   rc1             IN OUT DTSW_RS_ROLE_SECURITE) IS
  BEGIN
    open rc1 for
      select R.V_RS_ROLE,
             R.V_RS_APPLICATION,
             R.V_RS_CLASSE_FORM,
             R.V_RS_CLASSE_VO,
             R.V_RS_URL,
             R.V_TV_CODE,
             R.V_RS_DESCRIPTION,
             R.B_RS_ADMINISTRER,
             R.L_RS_ECRAN,
             TR.V_TR_DESCRIPTION
        FROM RS_ROLE_SECURITE R, TR_TRADUCTION TR
       WHERE R.V_RS_APPLICATION LIKE NEW_APPLICATION
         and upper(R.V_RS_ROLE) LIKE NEW_ROLE
         and (R.L_RS_ECRAN = NEW_ECRAN or NEW_ECRAN = 0)
         and R.B_RS_ADMINISTRER LIKE NEW_ADMINISTRE
         and R.L_RS_ECRAN = TR.L_TR_CLE
         AND TR.I_LA_CLE = cardex_special.g_langue
       ORDER BY convert(upper(R.V_RS_ROLE), 'US7ASCII');
  END SPW_L_RS_ROLE_SECURITE;

  --Retourne la liste de tous les rôles
  procedure SPW_L2_RS_ROLE_SECURITE(rc1 IN OUT DTSW_RS_ROLE_SECURITE_TOUS) IS
  BEGIN
    open rc1 for
      select R.V_RS_ROLE as "ROLE", R.B_RS_ADMINISTRER as "ADMINISTRE"
        FROM RS_ROLE_SECURITE R
      union
      select t.v_tv_role as "ROLE", T.B_TV_ADMINISTRER as "ADMINISTRE"
        from tv_table_valeur t
       where t.v_tv_role is not null;
  END SPW_L2_RS_ROLE_SECURITE;

  --Retourne la liste de tous les rôles "Autres" et de la table de valeurs
  procedure SPW_L3_RS_ROLE_SECURITE(rc1 IN OUT DTSW_RS_ROLE_SECURITE_TOUS) IS
  BEGIN
    open rc1 for
      select R.V_RS_ROLE as "ROLE", R.B_RS_ADMINISTRER as "ADMINISTRE"
        FROM RS_ROLE_SECURITE R
       where r.l_rs_ecran = 423
      union
      select t.v_tv_role as "ROLE", T.B_TV_ADMINISTRER as "ADMINISTRE"
        from tv_table_valeur t
       where t.v_tv_role is not null;
  END SPW_L3_RS_ROLE_SECURITE;

  procedure SPW_E_RS_ROLE_SECURITE(action               IN CHAR,
                                   NEW_V_RS_ROLE        IN RS_ROLE_SECURITE.V_RS_ROLE%TYPE,
                                   NEW_V_RS_APPLICATION IN RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
                                   NEW_V_RS_CLASSE_FORM IN RS_ROLE_SECURITE.V_RS_CLASSE_FORM%TYPE,
                                   NEW_V_RS_CLASSE_VO   IN RS_ROLE_SECURITE.V_RS_CLASSE_VO%TYPE,
                                   NEW_V_RS_URL         IN RS_ROLE_SECURITE.V_RS_URL%TYPE,
                                   NEW_V_TV_CODE        IN RS_ROLE_SECURITE.V_TV_CODE%TYPE,
                                   NEW_V_RS_DESCRIPTION IN RS_ROLE_SECURITE.V_RS_DESCRIPTION%TYPE,
                                   NEW_B_RS_ADMINISTRER IN RS_ROLE_SECURITE.B_RS_ADMINISTRER%TYPE,
                                   NEW_L_RS_ECRAN       IN RS_ROLE_SECURITE.L_RS_ECRAN%TYPE) IS
  BEGIN
    if action = 'I' then
      insert into RS_ROLE_SECURITE
        (V_RS_ROLE,
         V_RS_APPLICATION,
         V_RS_CLASSE_FORM,
         V_RS_CLASSE_VO,
         V_RS_URL,
         V_TV_CODE,
         V_RS_DESCRIPTION,
         B_RS_ADMINISTRER,
         L_RS_ECRAN)
      values
        (NEW_V_RS_ROLE,
         NEW_V_RS_APPLICATION,
         NEW_V_RS_CLASSE_FORM,
         NEW_V_RS_CLASSE_VO,
         NEW_V_RS_URL,
         NEW_V_TV_CODE,
         NEW_V_RS_DESCRIPTION,
         NEW_B_RS_ADMINISTRER,
         NEW_L_RS_ECRAN);
      commit;
    elsif action = 'U' then
      update RS_ROLE_SECURITE
         set V_RS_DESCRIPTION = new_V_RS_DESCRIPTION,
             B_RS_ADMINISTRER = new_B_RS_ADMINISTRER,
             L_RS_ECRAN       = NEW_L_RS_ECRAN
       where V_RS_ROLE = new_V_RS_ROLE;
      commit;
    elsif action = 'D' then
      delete from RS_ROLE_SECURITE where V_RS_ROLE = new_V_RS_ROLE;
      commit;
    end if;
  END SPW_E_RS_ROLE_SECURITE;

  procedure SPW_L_GS_GROUPE_SECURITE(NEW_GROUPE GS_GROUPE_SECURITE.V_GS_NOM%TYPE,
                                     rc1        IN OUT DTSW_GS_GROUPE_SECURITE) is
  begin
    open rc1 for
      select *
        from GS_GROUPE_SECURITE G
       where upper(G.V_GS_NOM) like NEW_GROUPE
       order by replace(convert(upper(G.V_GS_NOM), 'US7ASCII'), '-', 'a');
  END SPW_L_GS_GROUPE_SECURITE;

  procedure SPW_L_GROUPES_VIDES(rc1 IN OUT DTSW_GS_GROUPE_SECURITE) is
  begin
    open rc1 for
      SELECT *
        FROM GS_GROUPE_SECURITE GS
       WHERE NOT EXISTS (SELECT *
                FROM GR_GROUPE_ROLE GR
               WHERE GR.L_GR_CLE = GS.L_GS_CLE)
         AND NOT EXISTS
       (SELECT *
                FROM SGR_SOUS_GROUPE_SECURITE SGR
               WHERE SGR.L_SGR_PARENT_CLE = GS.L_GS_CLE);
  END SPW_L_GROUPES_VIDES;

  procedure SPW_L_GROUPES_NON_VIDES(rc1 IN OUT DTSW_GS_GROUPE_SECURITE) is
  begin
    open rc1 for
      SELECT *
        FROM GS_GROUPE_SECURITE GS
       WHERE EXISTS (SELECT *
                FROM GR_GROUPE_ROLE GR
               WHERE GR.L_GR_CLE = GS.L_GS_CLE)
          OR EXISTS (SELECT *
                FROM SGR_SOUS_GROUPE_SECURITE SGR
               WHERE SGR.L_SGR_PARENT_CLE = GS.L_GS_CLE);
  END SPW_L_GROUPES_NON_VIDES;

  --Retourne les profils sans groupes et sans rôles
  PROCEDURE SPW_L_PROFILS_VIDES(rc1 IN OUT DTSW_IN_INTERVENANT) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE,
             P.I_NH_CLE,
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE (NOT EXISTS
              (SELECT * FROM IR_INTERVENANT_ROLE IR WHERE IR.V_NAME = P.NAME) AND
              NOT EXISTS (SELECT *
                            FROM IGS_INTERVENANT_GROUPE_SEC IGS
                           WHERE IGS.V_NAME = P.NAME))
         AND P.I_ST_CLE = 532 --Intervenants actifs seulement
         AND P.L_SI_CLE = S.L_SI_CLE;
  END SPW_L_PROFILS_VIDES;

  --Retourne les profils avec groupes ou rôles
  PROCEDURE SPW_L_PROFILS_NON_VIDES(rc1 IN OUT DTSW_IN_INTERVENANT) IS
  BEGIN
    OPEN rc1 FOR
      SELECT P.L_IN_CLE,
             P.L_SI_CLE,
             P.NAME,
             P.I_LA_CLE,
             P.I_ST_CLE,
             P.I_CC_CLE,
             P.I_NH_CLE,
             P.V_IN_NO_EMPLOYE,
             P.V_IN_NOM,
             P.V_IN_PRENOM,
             P.V_IN_COMMENTAIRE,
             P.C_IN_MOT_PASSE,
             P.L_IN_TEMPS_ACTIF,
             P.L_IN_TEMPS_CONNEXION,
             P.V_IN_CREE_PAR,
             P.D_IN_DATE_CREATION,
             P.V_IN_MODIFIE_PAR,
             P.D_IN_DATE_MODIFICATION,
             S.I_EN_CLE,
             P.V_IN_NAME_PARENT,
             P.L_IN_SECTEUR,
             P.L_IN_SOUS_SECTEUR
        FROM IN_INTERVENANT P, SI_SITE S
       WHERE (EXISTS
              (SELECT * FROM IR_INTERVENANT_ROLE IR WHERE IR.V_NAME = P.NAME) OR
              EXISTS (SELECT *
                        FROM IGS_INTERVENANT_GROUPE_SEC IGS
                       WHERE IGS.V_NAME = P.NAME))
         AND P.I_ST_CLE = 532 --Intervenants actifs seulement
         AND P.L_SI_CLE = S.L_SI_CLE;
  END SPW_L_PROFILS_NON_VIDES;

  procedure SP_I_TRADUCTION(NEW_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                            NEW_I_LA_CLE         IN TR_TRADUCTION.I_LA_CLE%TYPE,
                            NEW_C_TR_GENRE_FICHIER IN TR_TRADUCTION.C_TR_GENRE_FICHIER%TYPE,
                            NEW_V_TR_ABREVIATION IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                            NEW_V_TR_DESCRIPTION IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE) IS
  begin
       
    insert into TR_TRADUCTION
      (L_TR_CLE,
       I_LA_CLE,
       C_TR_GENRE_FICHIER,
       V_TR_ABREVIATION,
       V_TR_DESCRIPTION)
    VALUES
      (NEW_L_TR_CLE,
       NEW_I_LA_CLE,
       NEW_C_TR_GENRE_FICHIER,
       NEW_V_TR_ABREVIATION,
       NEW_V_TR_DESCRIPTION);       
       
  END SP_I_TRADUCTION;
  
  procedure SP_E_TRADUCTION(NEW_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE,
                            NEW_I_LA_CLE         IN TR_TRADUCTION.I_LA_CLE%TYPE,
                            NEW_C_TR_GENRE_FICHIER IN TR_TRADUCTION.C_TR_GENRE_FICHIER%TYPE,                            
                            NEW_V_TR_ABREVIATION IN TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
                            NEW_V_TR_DESCRIPTION IN TR_TRADUCTION.V_TR_DESCRIPTION%TYPE) IS
  begin
    update TR_TRADUCTION
       set C_TR_GENRE_FICHIER = NEW_C_TR_GENRE_FICHIER,
           V_TR_DESCRIPTION = NEW_V_TR_DESCRIPTION,
           V_TR_ABREVIATION = NEW_V_TR_ABREVIATION
     where L_TR_CLE = NEW_L_TR_CLE
       AND I_LA_CLE = NEW_I_LA_CLE;
  END SP_E_TRADUCTION;
  
  procedure SP_D_TRADUCTION(NEW_L_TR_CLE         IN TR_TRADUCTION.L_TR_CLE%TYPE) IS
  begin
    delete from TR_TRADUCTION
     where L_TR_CLE = NEW_L_TR_CLE;
  END SP_D_TRADUCTION;  

  procedure SP_I_TV_TABLE_VALEUR(NEW_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                 NEW_B_TV_ACTIF         IN TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
                                 NEW_V_TV_ROLE          IN TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
                                 NEW_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                                 NEW_B_TV_ADMINISTRER   IN TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
                                 NEW_B_TV_OBLIGATOIRE   IN TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
                                 NEW_V_TV_DESCRIPTION   IN TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE) IS
  BEGIN
    insert into TV_TABLE_VALEUR
      (V_TV_CODE,
       L_TV_VALEUR,
       B_TV_ACTIF,
       V_TV_ROLE,
       L_TV_ACTION,
       V_TV_DESCRIPTION,
       B_TV_ADMINISTRER,
       B_TV_OBLIGATOIRE)
    VALUES
      (NEW_V_TV_CODE,
       NEW_L_TV_VALEUR,
       NEW_B_TV_ACTIF,
       NEW_V_TV_ROLE,
       NEW_L_TV_ACTION,
       NEW_V_TV_DESCRIPTION,
       NEW_B_TV_ADMINISTRER,
       NEW_B_TV_OBLIGATOIRE);
  
  END SP_I_TV_TABLE_VALEUR;

  procedure SP_L_TV_TABLE_VALEUR(NEW_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                 NEW_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                                 rc1 OUT DTSW_TV_TABLE_VALEUR) IS
  BEGIN
    OPEN rc1 FOR
    select tv.V_TV_CODE,
           tv.L_TV_VALEUR,
           tv.B_TV_ACTIF,
           tv.V_TV_ROLE,
           tv.L_TV_ACTION,
           tv.V_TV_DESCRIPTION,
           tv.B_TV_ADMINISTRER,
           tv.B_TV_OBLIGATOIRE,
           tr.i_la_cle,
           tr.c_tr_genre_fichier,
           tr.v_tr_abreviation,
           tr.v_tr_description,
           ctv.V_TV_PARENT_CODE,
           ctv.L_TV_PARENT_VALEUR
      from tv_table_valeur tv, tr_traduction tr, ctv_critere_table_valeur ctv
     where tv.l_tv_valeur = tr.l_tr_cle
       and tv.l_tv_valeur = ctv.l_tv_valeur(+)
       AND tv.v_tv_code = NEW_V_TV_CODE
       and tv.l_tv_valeur = NEW_L_TV_VALEUR
       and tv.l_tv_action = NEW_L_TV_ACTION;
  END SP_L_TV_TABLE_VALEUR;


  procedure SP_E_TV_TABLE_VALEUR(NEW_V_TV_CODE          IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR        IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                 NEW_B_TV_ACTIF         IN TV_TABLE_VALEUR.B_TV_ACTIF%TYPE,
                                 NEW_V_TV_ROLE          IN TV_TABLE_VALEUR.V_TV_ROLE%TYPE,
                                 NEW_L_TV_ACTION        IN TV_TABLE_VALEUR.L_TV_ACTION%TYPE,
                                 NEW_B_TV_ADMINISTRER   IN TV_TABLE_VALEUR.B_TV_ADMINISTRER%TYPE,
                                 NEW_B_TV_OBLIGATOIRE   IN TV_TABLE_VALEUR.B_TV_OBLIGATOIRE%TYPE,
                                 NEW_V_TV_DESCRIPTION   IN TV_TABLE_VALEUR.V_TV_DESCRIPTION%TYPE) IS
  BEGIN
    update TV_TABLE_VALEUR tv
       set tv.V_TV_CODE          = NEW_V_TV_CODE,
           tv.B_TV_ACTIF         = NEW_B_TV_ACTIF,
           tv.v_TV_ROLE          = NEW_V_TV_ROLE,
           tv.L_TV_ACTION        = NEW_L_TV_ACTION,
           tv.v_Tv_Description   = NEW_V_TV_DESCRIPTION,
           tv.B_TV_ADMINISTRER   = NEW_B_TV_ADMINISTRER,
           tv.B_TV_OBLIGATOIRE   = NEW_B_TV_OBLIGATOIRE
     WHERE TV.L_TV_VALEUR = NEW_L_TV_VALEUR
       and tv.L_TV_ACTION = NEW_L_TV_ACTION;
  END SP_E_TV_TABLE_VALEUR;

  procedure SP_D_TV_TABLE_VALEUR(NEW_V_TV_CODE   IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                                 NEW_L_TV_VALEUR IN TV_TABLE_VALEUR.L_TV_VALEUR%TYPE) IS
  BEGIN
    delete from TV_TABLE_VALEUR
     where V_TV_CODE = NEW_V_TV_CODE
       and L_TV_VALEUR = NEW_L_TV_VALEUR;
  END SP_D_TV_TABLE_VALEUR;
  
  procedure SP_I_CTV_CRITERE_TABLE_VALEUR(NEW_V_TV_CODE IN CTV_CRITERE_TABLE_VALEUR.V_TV_CODE%TYPE,
                                          NEW_L_TV_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                          NEW_L_TV_PARENT_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE,
                                          NEW_V_TV_PARENT_CODE IN CTV_CRITERE_TABLE_VALEUR.V_TV_PARENT_CODE%TYPE) IS
  BEGIN
    insert into CTV_CRITERE_TABLE_VALEUR
      (V_TV_CODE,
       L_TV_VALEUR,
       L_TV_PARENT_VALEUR,
       V_TV_PARENT_CODE)
    VALUES
      (NEW_V_TV_CODE,
       NEW_L_TV_VALEUR,
       NEW_L_TV_PARENT_VALEUR,
       NEW_V_TV_PARENT_CODE);
  END SP_I_CTV_CRITERE_TABLE_VALEUR;
  
  procedure SP_L_CTV_CRITERE_TABLE_VALEUR(NEW_L_TV_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                          rc1 OUT DTSW_CTV_CRITERE_TABLE_VALEUR) IS
  BEGIN
    OPEN rc1 FOR
      SELECT CTV.V_TV_CODE,
             CTV.L_TV_VALEUR,
             CTV.L_TV_PARENT_VALEUR,
             CTV.V_TV_PARENT_CODE
        FROM CTV_CRITERE_TABLE_VALEUR CTV
       WHERE CTV.L_TV_VALEUR = NEW_L_TV_VALEUR;
  END SP_L_CTV_CRITERE_TABLE_VALEUR;  
  
  procedure SP_D_CTV_CRITERE_TABLE_VALEUR(NEW_L_TV_VALEUR IN CTV_CRITERE_TABLE_VALEUR.L_TV_VALEUR%TYPE,
                                  NEW_L_TV_VALEUR_PARENT IN CTV_CRITERE_TABLE_VALEUR.L_TV_PARENT_VALEUR%TYPE) IS
  BEGIN
    delete from CTV_CRITERE_TABLE_VALEUR
     where L_TV_VALEUR = NEW_L_TV_VALEUR
       and L_TV_PARENT_VALEUR = NEW_L_TV_VALEUR_PARENT;
  END SP_D_CTV_CRITERE_TABLE_VALEUR;  

  procedure SP_L_TV_ACTION(NEW_V_TV_CODE IN TV_TABLE_VALEUR.V_TV_CODE%TYPE,
                           rc1           OUT DTSW_TV_ACTION) IS
  BEGIN
   OPEN rc1 FOR
    select distinct tv.l_tv_action from cardex.tv_table_valeur tv where tv.v_Tv_Code = NEW_V_TV_CODE;
  END SP_L_TV_ACTION;  

  procedure SP_L_SUJET_HISTORIQUE(rc1 OUT DTSW_SUJET_HISTORIQUE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT L.L_LI_CLE_PARENT,
             L.L_LI_SITE_PARENT,
             SI.I_EN_CLE,
             L.L_LI_CLE_ENFANT,
             L.L_LI_SITE_ENFANT,
             P.V_SU_REFERENCE_3,
             P.I_SX_CLE,
             P.V_SU_NOM,
             P.V_SU_PRENOM,
             P.V_SU_SURNOM,
             P.D_SU_DATE_NAISSANCE,
             SO.V_SO_NOM
        FROM AUD_LI_LIAISON L, SU_SUJET P, SO_SOCIETE SO, SI_SITE SI
       WHERE L.L_LI_CLE_PARENT = P.L_SU_CLE
         AND L.L_LI_SITE_PARENT = P.L_SI_CLE
         AND L.C_LI_GENRE_PARENT = 'SU '
         AND L.C_LI_GENRE_ENFANT = 'SO '
         AND L.L_LI_CLE_ENFANT = SO.L_SO_CLE
         AND L.L_LI_SITE_ENFANT = SO.L_SI_CLE
         AND L.L_LI_SITE_PARENT = SI.L_SI_CLE
       ORDER BY P.V_SU_NOM, P.V_SU_PRENOM;
  END SP_L_SUJET_HISTORIQUE;

  --Suppression d'un sujet dans l'historique de liaisons.
  procedure SP_E_SUJET_HISTORIQUE(NEW_L_LI_CLE_PARENT  AUD_LI_LIAISON.L_LI_CLE_PARENT%TYPE,
                                  NEW_L_LI_SITE_PARENT AUD_LI_LIAISON.L_LI_SITE_PARENT%TYPE,
                                  NEW_L_LI_CLE_ENFANT  AUD_LI_LIAISON.L_LI_CLE_ENFANT%TYPE,
                                  NEW_L_LI_SITE_ENFANT AUD_LI_LIAISON.L_LI_SITE_ENFANT%TYPE) IS
  BEGIN
    DELETE FROM AUD_LI_LIAISON L
     WHERE L.L_LI_CLE_PARENT = NEW_L_LI_CLE_PARENT
       AND L.L_LI_SITE_PARENT = NEW_L_LI_SITE_PARENT
       AND L.L_LI_CLE_ENFANT = NEW_L_LI_CLE_ENFANT
       AND L.L_LI_SITE_ENFANT = NEW_L_LI_SITE_ENFANT;
  END SP_E_SUJET_HISTORIQUE;

END CARDEX_WEB_PILOTAGE;
/
