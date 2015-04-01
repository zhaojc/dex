CREATE OR REPLACE PACKAGE Cardex_Special IS
  g_langue               LA_LANGUE.I_LA_CLE%TYPE := 1;
  g_usager               IN_INTERVENANT.NAME%TYPE := 'PELOQUD';
  g_code_confidentialite CC_CONFIDENTIALITE.I_CC_CLE%TYPE := 1;
  g_site                 SI_SITE.L_SI_CLE%TYPE := 11;
  PROCEDURE SP_INITIALISE(langue               IN LA_LANGUE.I_LA_CLE%TYPE,
                          usager               IN IN_INTERVENANT.NAME%TYPE,
                          code_confidentialite IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          site                 IN SI_SITE.L_SI_CLE%TYPE);
  TYPE REC_GENRE_NATURE IS RECORD(
    I_GE_CLE         GE_GENRE.I_GE_CLE%TYPE,
    V_GE_ABREVIATION TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
    V_GE_GENRE       TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    I_GE_ENTITE      GE_GENRE.I_GE_ENTITE%TYPE,
    I_NA_CLE         NA_NATURE.I_NA_CLE%TYPE,
    C_NA_ABREVIATION TR_TRADUCTION.V_TR_ABREVIATION%TYPE,
    V_NA_NATURE      TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    B_NA_INSCRIPTION NA_NATURE.B_NA_INSCRIPTION%TYPE);
  TYPE REC_DTS_GENRE_NATURE IS REF CURSOR RETURN REC_GENRE_NATURE;
  PROCEDURE Sp_L2_Dts_Genre_Nature(rc1 IN OUT Cardex_Special.REC_DTS_GENRE_NATURE);
  PROCEDURE SP_COMPTE_IS_INSCRIPTION(rc           OUT NUMBER,
                                     NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                     NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE);
  TYPE DTS_AC_ACCES IS REF CURSOR RETURN AC_ACCES%ROWTYPE;
  PROCEDURE Sp_L_Ac_Acces(DOSSIER IN DO_DOSSIER.L_DO_CLE%TYPE,
                          SITE    IN SI_SITE.L_SI_CLE%TYPE,
                          ORIGINE IN AC_ACCES.C_GF_ORIGINE%TYPE,
                          rc1     IN OUT Cardex_Special.DTS_AC_ACCES);
  PROCEDURE SP_E_AC_ACCES(ACTION             IN CHAR,
                          NEW_C_GF_ORIGINE   IN GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                          NEW_L_ORI_CLE      IN DO_DOSSIER.L_DO_CLE%TYPE,
                          NEW_L_ORI_SITE     IN SI_SITE.L_SI_CLE%TYPE,
                          NEW_C_GF_REFERENCE IN GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                          NEW_L_REF_CLE      IN DO_DOSSIER.L_DO_CLE%TYPE,
                          NEW_L_REF_SITE     IN SI_SITE.L_SI_CLE%TYPE,
                          NEW_C_GF_REF2      IN GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                          NEW_L_REF2_CLE     IN DO_DOSSIER.L_DO_CLE%TYPE,
                          NEW_L_REF2_SITE    IN SI_SITE.L_SI_CLE%TYPE);
  PROCEDURE SP_E_SC_STATUT_ACCES(ACTION          IN CHAR,
                                 NEW_C_GF_CLE    IN SC_STATUT_ACCES.C_GF_CLE%TYPE,
                                 NEW_B_SC_SELECT IN SC_STATUT_ACCES.B_SC_SELECT%TYPE,
                                 NEW_B_SC_INSERT IN SC_STATUT_ACCES.B_SC_INSERT%TYPE,
                                 NEW_B_SC_UPDATE IN SC_STATUT_ACCES.B_SC_UPDATE%TYPE,
                                 NEW_B_SC_DELETE IN SC_STATUT_ACCES.B_SC_DELETE%TYPE);
  PROCEDURE SP_E_AG_ACCES_GENRE(ACTION                     IN CHAR,
                                NEW_L_AG_CLE               IN AG_ACCES_GENRE.L_AG_CLE%TYPE,
                                NEW_V_AG_NAME              IN AG_ACCES_GENRE.V_AG_NAME%TYPE,
                                NEW_L_GE_CLE               IN AG_ACCES_GENRE.L_GE_CLE%TYPE,
                                NEW_V_AG_TYPE_TAB          IN AG_ACCES_GENRE.V_AG_TYPE_TAB%TYPE,
                                NEW_C_AG_NOUVEAU           IN AG_ACCES_GENRE.C_AG_NOUVEAU%TYPE,
                                NEW_C_AG_CONSULTATION      IN AG_ACCES_GENRE.C_AG_CONSULTATION%TYPE,
                                NEW_D_AG_DATE_MODIFICATION IN AG_ACCES_GENRE.D_AG_DATE_MODIFICATION%TYPE);
  TYPE REC_ACCES_GENRE IS RECORD(
    L_AG_CLE               AG_ACCES_GENRE.L_AG_CLE%TYPE,
    V_AG_NAME              AG_ACCES_GENRE.V_AG_NAME%TYPE,
    L_GE_CLE               AG_ACCES_GENRE.L_GE_CLE%TYPE,
    V_AG_TYPE_TAB          AG_ACCES_GENRE.V_AG_TYPE_TAB%TYPE,
    C_AG_NOUVEAU           AG_ACCES_GENRE.C_AG_NOUVEAU%TYPE,
    C_AG_CONSULTATION      AG_ACCES_GENRE.C_AG_CONSULTATION%TYPE,
    D_AG_DATE_MODIFICATION AG_ACCES_GENRE.D_AG_DATE_MODIFICATION%TYPE);
  TYPE REC_DTS_ACCES_GENRE IS REF CURSOR RETURN REC_ACCES_GENRE;
  PROCEDURE Sp_L_Ag_Acces_Genre(NEW_NAME  IN AG_ACCES_GENRE.V_AG_NAME%TYPE,
                                NEW_GROUP IN AG_ACCES_GENRE.V_AG_NAME%TYPE,
                                rc1       IN OUT Cardex_Special.REC_DTS_ACCES_GENRE);
  FUNCTION F_TRAITE_INSCRIPTION(NEW_I_CA_CLE IN CA_CATEGORIE.I_CA_CLE%TYPE)
    RETURN NUMBER;

  FUNCTION f_no_sujet(dossier       IN SU_SUJET.l_su_cle%TYPE,
                      site          IN SI_SITE.l_si_cle%TYPE,
                      date_creation IN SU_SUJET.d_su_date_creation%TYPE)
    RETURN VARCHAR2;
  PRAGMA RESTRICT_REFERENCES(F_NO_SUJET, WNDS, WNPS, RNPS);
  PROCEDURE sp_active_inscription;
  PROCEDURE sp_desactive_mandats;
  PROCEDURE sp_epuration_acces;

  /*TYPE DTS_ME_MESSAGE IS REF CURSOR RETURN ME_MESSAGE%ROWTYPE;
  PROCEDURE SP_L_ME_MESSAGE_COURANT(
     rc1      IN OUT Cardex_Special.DTS_ME_MESSAGE);*/

  --PROCEDURE SP_RAFRAICHIR_AE;

  TYPE DTS_RS_ROLE_SECURITE IS REF CURSOR RETURN RS_ROLE_SECURITE%ROWTYPE;
  PROCEDURE SP_L_ROLE_SECURITE(NEW_RS_APPLICATION IN RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
                               rc1                OUT DTS_RS_ROLE_SECURITE);

  TYPE DTS_IGS_INTERVENANT_GROUPE_SEC IS REF CURSOR RETURN IGS_INTERVENANT_GROUPE_SEC%ROWTYPE;
  PROCEDURE SP_L_INTERVENANT_GROUPE(NEW_V_NAME in IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE,
                                    rc1        OUT DTS_IGS_INTERVENANT_GROUPE_SEC);

  TYPE DTS_IR_INTERVENANT_ROLE IS REF CURSOR RETURN IR_INTERVENANT_ROLE%ROWTYPE;
  PROCEDURE SP_L_INTERVENANT_ROLE(NEW_V_NAME in IR_INTERVENANT_ROLE.V_NAME%TYPE,
                                  rc1        OUT DTS_IR_INTERVENANT_ROLE);

  TYPE DTS_GR_GROUPE_ROLE IS REF CURSOR RETURN GR_GROUPE_ROLE%ROWTYPE;
  PROCEDURE SP_L_GROUPE_ROLE(NEW_L_GR_CLE in GR_GROUPE_ROLE.L_GR_CLE%TYPE,
                             rc1          OUT DTS_GR_GROUPE_ROLE);
  PROCEDURE SP_L2_GROUPE_ROLE(NEW_V_GR_ROLE in GR_GROUPE_ROLE.V_GR_ROLE%TYPE,
                              rc1           OUT DTS_GR_GROUPE_ROLE);

  TYPE REC_GR_GROUPE_ROLE2 IS RECORD(
    L_GS_CLE GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
    V_GS_NOM GS_GROUPE_SECURITE.V_GS_NOM%TYPE);
  TYPE DTS_GR_GROUPE_ROLE2 IS REF CURSOR RETURN REC_GR_GROUPE_ROLE2;
  PROCEDURE SP_L3_GROUPE_ROLE(NEW_V_GR_ROLE in GR_GROUPE_ROLE.V_GR_ROLE%TYPE,
                              rc1           OUT DTS_GR_GROUPE_ROLE2);
  PROCEDURE SP_L_GROUPE_INTERVENANT(NEW_NAME in IN_INTERVENANT.NAME%TYPE,
                                    rc1      OUT DTS_GR_GROUPE_ROLE2);

  TYPE DTS_IN_INTERVENANT IS REF CURSOR RETURN IN_INTERVENANT%ROWTYPE;
  PROCEDURE SP_L_INTERVENANT_PARENT(NEW_V_IN_NAME_PARENT in IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
                                    rc1                  OUT DTS_IN_INTERVENANT);

  TYPE REC_INTERVENANT_PARENT IS RECORD(
    PROFIL          IN_INTERVENANT.NAME%TYPE,
    PARENT          IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
    NOM             IN_INTERVENANT.V_IN_NOM%TYPE,
    PRENOM          IN_INTERVENANT.V_IN_PRENOM%TYPE,
    SECTEUR         TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    SOUS_SECTEUR    TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    STATUT          TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    AUTORITE        TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    CONFIDENTIALITE TR_TRADUCTION.V_TR_DESCRIPTION%TYPE,
    SITE            SI_SITE.V_SI_SITE%TYPE,
    SITE_CLE        SI_SITE.L_SI_CLE%TYPE,
    ENTITE          SI_SITE.I_EN_CLE%TYPE);
  TYPE DTS_INTERVENANT_PARENT2 IS REF CURSOR RETURN REC_INTERVENANT_PARENT;
  PROCEDURE SP_L2_INTERVENANT_PARENT(NEW_NAME in IN_INTERVENANT.NAME%TYPE,
                                     rc1      OUT DTS_INTERVENANT_PARENT2);

  PROCEDURE SP_E_INTERVENANT_GROUPE(action       IN CHAR,
                                    NEW_L_GS_CLE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                    NEW_V_NAME   IN IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE);
  PROCEDURE SP_E_GROUPE_INTERVENANT(action       IN CHAR,
                                    NEW_L_GS_CLE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                    NEW_V_NAME   IN IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE);

  TYPE REC_INTERVENANT_GROUPE IS RECORD(
    NAME             IN_INTERVENANT.NAME%TYPE,
    V_IN_NOM         IN_INTERVENANT.V_IN_NOM%TYPE,
    V_IN_PRENOM      IN_INTERVENANT.V_IN_PRENOM%TYPE,
    L_SI_CLE         IN_INTERVENANT.L_SI_CLE%TYPE,
    L_IN_SECTEUR     IN_INTERVENANT.L_IN_SECTEUR%TYPE,
    V_TR_DESCRIPTION TR_TRADUCTION.V_TR_DESCRIPTION%TYPE);
  TYPE DTS_INTERVENANT_GROUPE IS REF CURSOR RETURN REC_INTERVENANT_GROUPE;
  PROCEDURE SP_L2_INTERVENANT_GROUPE(NEW_L_GS_CLE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                     rc1          OUT DTS_INTERVENANT_GROUPE);
  PROCEDURE SP_L2_INTERVENANT_ROLE(NEW_ROLE in IR_INTERVENANT_ROLE.V_IR_ROLE%TYPE,
                                   rc1      OUT DTS_INTERVENANT_GROUPE);

  PROCEDURE SP_E_ROLE_GROUPE(action       IN CHAR,
                             NEW_L_GR_CLE IN GR_GROUPE_ROLE.L_GR_CLE%TYPE,
                             NEW_V_ROLE   IN GR_GROUPE_ROLE.V_GR_ROLE%TYPE);

  PROCEDURE SP_E_ROLE_INTERVENANT(action        IN CHAR,
                                  NEW_V_NAME    IN IR_INTERVENANT_ROLE.V_NAME%TYPE,
                                  NEW_V_IR_ROLE IN IR_INTERVENANT_ROLE.V_IR_ROLE%TYPE);

  PROCEDURE SP_E_SOUS_GROUPE(action     IN CHAR,
                             NEW_PARENT IN SGR_SOUS_GROUPE_SECURITE.L_SGR_PARENT_CLE%TYPE,
                             NEW_ENFANT IN SGR_SOUS_GROUPE_SECURITE.L_SGR_ENFANT_CLE%TYPE);

  TYPE REC_SOUS_GROUPE IS RECORD(
    L_SGR_PARENT_CLE SGR_SOUS_GROUPE_SECURITE.L_SGR_PARENT_CLE%TYPE,
    L_SGR_ENFANT_CLE SGR_SOUS_GROUPE_SECURITE.L_SGR_ENFANT_CLE%TYPE,
    PARENT           GS_GROUPE_SECURITE.V_GS_NOM%TYPE,
    ENFANT           GS_GROUPE_SECURITE.V_GS_NOM%TYPE);
  TYPE DTS_SOUS_GROUPE IS REF CURSOR RETURN REC_SOUS_GROUPE;
  PROCEDURE SP_L_SOUS_GROUPE(NEW_L_GS_CLE IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
                             rc1          OUT DTS_SOUS_GROUPE);

  PROCEDURE SP_E_PROFIL(NEW_PROFIL_SOURCE      IN IN_INTERVENANT.NAME%TYPE,
                        NEW_PROFIL_DESTINATION IN IN_INTERVENANT.NAME%TYPE);
  PROCEDURE SP_E_GROUPE(NEW_GROUPE_SOURCE      IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
                        NEW_GROUPE_DESTINATION IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE);

  TYPE DTS_ME_MESSAGE IS REF CURSOR RETURN ME_MESSAGE%ROWTYPE;
  PROCEDURE SP_L_ME_MESSAGE_COURANT(rc1 IN OUT Cardex_Special.DTS_ME_MESSAGE);

  PROCEDURE SP_E_COPIER_SOCIETE(NEW_L_SO_CLE_SOURCE IN SO_SOCIETE.L_SO_CLE%TYPE,
                                NEW_L_SI_CLE_SOURCE IN SO_SOCIETE.L_SI_CLE%TYPE,
                                NEW_L_SO_CLE_DEST   IN SO_SOCIETE.L_SO_CLE%TYPE,
                                NEW_L_SI_CLE_DEST   IN OUT SO_SOCIETE.L_SI_CLE%TYPE);

  PROCEDURE SP_E_COPIER_SUJET(NEW_L_SU_CLE_SOURCE IN SU_SUJET.L_SU_CLE%TYPE,
                              NEW_L_SI_CLE_SOURCE IN SU_SUJET.L_SI_CLE%TYPE,
                              NEW_L_SU_CLE_DEST   IN SU_SUJET.L_SU_CLE%TYPE,
                              NEW_L_SI_CLE_DEST   IN OUT SU_SUJET.L_SI_CLE%TYPE);

  TYPE DTS_RECHERCHE_SIGLE IS REF CURSOR RETURN SI_SITE%ROWTYPE;
  PROCEDURE SP_L_RECHERCHE_SIGLE(NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                 NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE,
                                 rc1          IN OUT DTS_RECHERCHE_SIGLE);

  TYPE REC_RECHERCHE_CODE_PARENT IS RECORD(
    V_IN_NAME_PARENT IN_INTERVENANT.V_IN_NAME_PARENT%TYPE);
  TYPE DTS_RECHERCHE_CODE_PARENT IS REF CURSOR RETURN REC_RECHERCHE_CODE_PARENT;
  PROCEDURE SP_L_RECHERCHE_CODE_PARENT(NEW_CODE_TAM IN AUT_AUTHENTIFICATION.V_AUT_AUTHENTIFICATION%TYPE,
                                       rc1          IN OUT DTS_RECHERCHE_CODE_PARENT);
END Cardex_Special;
/
CREATE OR REPLACE PACKAGE BODY CARDEX_SPECIAL IS
  PROCEDURE SP_INITIALISE(langue               IN LA_LANGUE.I_LA_CLE%TYPE,
                          usager               IN IN_INTERVENANT.NAME%TYPE,
                          code_confidentialite IN CC_CONFIDENTIALITE.I_CC_CLE%TYPE,
                          site                 IN SI_SITE.L_SI_CLE%TYPE) IS
  BEGIN
    g_langue               := langue;
    g_usager               := usager;
    g_code_confidentialite := code_confidentialite;
    g_site                 := site;
  END SP_INITIALISE;
  PROCEDURE Sp_L2_Dts_Genre_Nature(rc1 IN OUT Cardex_Special.REC_DTS_GENRE_NATURE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT G.I_GE_CLE,
             TG.V_TR_ABREVIATION,
             TG.V_TR_DESCRIPTION,
             G.I_GE_ENTITE,
             N.I_NA_CLE,
             TN.V_TR_ABREVIATION,
             TN.V_TR_DESCRIPTION,
             N.B_NA_INSCRIPTION
        FROM GE_GENRE G, NA_NATURE N, TR_TRADUCTION TG, TR_TRADUCTION TN
       WHERE N.I_GE_CLE(+) = G.I_GE_CLE
         AND TG.L_TR_CLE = G.I_GE_CLE
         AND N.I_NA_CLE = TN.L_TR_CLE
         AND TG.I_LA_CLE = Cardex_Special.g_langue
         AND TN.I_LA_CLE = Cardex_Special.g_langue
       ORDER BY G.I_GE_CLE;
  END Sp_L2_Dts_Genre_Nature;
  PROCEDURE SP_COMPTE_IS_INSCRIPTION(rc           OUT NUMBER,
                                     NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                     NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE) IS
  BEGIN
    rc := 0;
    SELECT COUNT(*)
      INTO rc
      FROM IS_INSCRIPTION
     WHERE L_IS_REF_SITE = NEW_L_SI_CLE
       AND L_IS_REFERENCE = NEW_L_DO_CLE;
  END SP_COMPTE_IS_INSCRIPTION;
  PROCEDURE SP_E_AC_ACCES(ACTION             IN CHAR,
                          NEW_C_GF_ORIGINE   IN GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                          NEW_L_ORI_CLE      IN DO_DOSSIER.L_DO_CLE%TYPE,
                          NEW_L_ORI_SITE     IN SI_SITE.L_SI_CLE%TYPE,
                          NEW_C_GF_REFERENCE IN GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                          NEW_L_REF_CLE      IN DO_DOSSIER.L_DO_CLE%TYPE,
                          NEW_L_REF_SITE     IN SI_SITE.L_SI_CLE%TYPE,
                          NEW_C_GF_REF2      IN GF_GENRE_FICHIER.C_GF_CLE%TYPE,
                          NEW_L_REF2_CLE     IN DO_DOSSIER.L_DO_CLE%TYPE,
                          NEW_L_REF2_SITE    IN SI_SITE.L_SI_CLE%TYPE) IS
    NEW_L_AC_CLE DO_DOSSIER.L_DO_CLE%TYPE;
    OK           NUMBER(1, 0) := 0;
  BEGIN
    SELECT COUNT(*)
      INTO OK
      FROM SC_STATUT_ACCES
     WHERE C_GF_CLE = NEW_C_GF_ORIGINE;
    IF OK != 0 THEN
      SELECT DECODE(ACTION,
                    'S',
                    B_SC_SELECT,
                    'I',
                    B_SC_INSERT,
                    'U',
                    B_SC_UPDATE,
                    'D',
                    B_SC_DELETE,
                    0)
        INTO OK
        FROM SC_STATUT_ACCES
       WHERE C_GF_CLE = NEW_C_GF_ORIGINE;
      IF NVL(OK, 0) = 1 THEN
        SELECT SEQ_ACCES.NEXTVAL INTO NEW_L_AC_CLE FROM DUAL;
        INSERT INTO AC_ACCES
          (L_AC_CLE,
           L_SI_CLE,
           C_GF_ORIGINE,
           L_ORI_CLE,
           L_ORI_SITE,
           C_GF_REFERENCE,
           L_REF_CLE,
           L_REF_SITE,
           V_AC_NAME,
           D_AC_DATE_ACCES,
           C_AC_ACTION,
           C_GF_REF2,
           L_REF2_CLE,
           L_REF2_SITE)
        VALUES
          (NEW_L_AC_CLE,
           Cardex_Special.G_SITE,
           NEW_C_GF_ORIGINE,
           NEW_L_ORI_CLE,
           NEW_L_ORI_SITE,
           NEW_C_GF_REFERENCE,
           NEW_L_REF_CLE,
           NEW_L_REF_SITE,
           Cardex_Special.G_USAGER,
           SYSDATE,
           ACTION,
           NEW_C_GF_REF2,
           NEW_L_REF2_CLE,
           NEW_L_REF2_SITE);
        COMMIT;
      END IF;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000, 'Database : SP_E_AC_ACCES', TRUE);
  END SP_E_AC_ACCES;
  PROCEDURE Sp_L_Ac_Acces(DOSSIER IN DO_DOSSIER.L_DO_CLE%TYPE,
                          SITE    IN SI_SITE.L_SI_CLE%TYPE,
                          ORIGINE IN AC_ACCES.C_GF_ORIGINE%TYPE,
                          rc1     IN OUT Cardex_Special.DTS_AC_ACCES) IS
  BEGIN
    OPEN rc1 FOR
      SELECT L_AC_CLE,
             L_SI_CLE,
             C_GF_ORIGINE,
             L_ORI_CLE,
             L_ORI_SITE,
             C_GF_REFERENCE,
             L_REF_CLE,
             L_REF_SITE,
             V_AC_NAME,
             D_AC_DATE_ACCES,
             C_AC_ACTION,
             C_GF_REF2,
             L_REF2_CLE,
             L_REF2_SITE
        FROM AC_ACCES
       WHERE L_ORI_CLE = DOSSIER
         AND L_ORI_SITE = SITE
         AND C_GF_ORIGINE = ORIGINE
       ORDER BY D_AC_DATE_ACCES DESC;
  END Sp_L_Ac_Acces;
  PROCEDURE SP_E_SC_STATUT_ACCES(action          IN CHAR,
                                 NEW_C_GF_CLE    IN SC_STATUT_ACCES.C_GF_CLE%TYPE,
                                 NEW_B_SC_SELECT IN SC_STATUT_ACCES.B_SC_SELECT%TYPE,
                                 NEW_B_SC_INSERT IN SC_STATUT_ACCES.B_SC_INSERT%TYPE,
                                 NEW_B_SC_UPDATE IN SC_STATUT_ACCES.B_SC_UPDATE%TYPE,
                                 NEW_B_SC_DELETE IN SC_STATUT_ACCES.B_SC_DELETE%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO SC_STATUT_ACCES
        (C_GF_CLE, B_SC_SELECT, B_SC_INSERT, B_SC_UPDATE, B_SC_DELETE)
      VALUES
        (NEW_C_GF_CLE,
         NEW_B_SC_SELECT,
         NEW_B_SC_INSERT,
         NEW_B_SC_UPDATE,
         NEW_B_SC_DELETE);
      COMMIT;
    ELSIF action = 'U' THEN
      UPDATE SC_STATUT_ACCES
         SET B_SC_SELECT = NEW_B_SC_SELECT,
             B_SC_INSERT = NEW_B_SC_INSERT,
             B_SC_UPDATE = NEW_B_SC_UPDATE,
             B_SC_DELETE = NEW_B_SC_DELETE
       WHERE C_GF_CLE = NEW_C_GF_CLE;
      COMMIT;
    ELSIF action = 'D' THEN
      DELETE FROM SC_STATUT_ACCES WHERE C_GF_CLE = NEW_C_GF_CLE;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : SP_E_SC_STATUT_ACCES',
                              TRUE);
  END SP_E_SC_STATUT_ACCES;
  FUNCTION F_TRAITE_INSCRIPTION(NEW_I_CA_CLE IN CA_CATEGORIE.I_CA_CLE%TYPE)
    RETURN NUMBER IS
    OK NUMBER(1, 0) := 0;
  BEGIN
    SELECT NVL(NA.B_NA_INSCRIPTION, 0)
      INTO OK
      FROM NA_NATURE NA, CA_CATEGORIE CA, TY_TYPE TY
     WHERE (TY.I_TY_CLE = CA.I_TY_CLE)
       AND (TY.I_NA_CLE = NA.I_NA_CLE)
       AND (CA.I_CA_CLE = NEW_I_CA_CLE);
    RETURN OK;
  END F_TRAITE_INSCRIPTION;

  FUNCTION f_no_sujet(dossier       IN SU_SUJET.l_su_cle%TYPE,
                      site          IN SI_SITE.l_si_cle%TYPE,
                      date_creation IN SU_SUJET.d_su_date_creation%TYPE)
    RETURN VARCHAR2 IS
    graine NUMBER;
    m      NUMBER;
    titre  VARCHAR2(9) := '';
  BEGIN
    graine := TO_NUMBER(TO_CHAR(site, 'FM99') ||
                        TO_CHAR(date_creation, 'YY') ||
                        SUBSTR(TO_CHAR(dossier, 'FM09999999'), 4, 1));
    LOOP
      m      := MOD(graine, 26);
      titre  := CHR(m + 65) || titre;
      graine := (graine - m) / 26;
      EXIT WHEN graine = 0;
    END LOOP;
    titre := UPPER(titre || '-' ||
                   SUBSTR(TO_CHAR(dossier, 'FM09999999'), 5));
    RETURN titre;
  END f_no_sujet;
  PROCEDURE sp_active_inscription IS
    fini      ST_STATUT.i_st_cle%TYPE := 0;
    actif     ST_STATUT.i_st_cle%TYPE := 0;
    avenir    ST_STATUT.i_st_cle%TYPE := 0;
    averifier ST_STATUT.i_st_cle%TYPE := 0;
  BEGIN
    SELECT COUNT(*)
      INTO fini
      FROM ST_STATUT
     WHERE c_st_genre_fichier = 'DO'
       AND c_st_type = 'I';
    IF fini = 1 THEN
      SELECT i_st_cle
        INTO fini
        FROM ST_STATUT
       WHERE c_st_genre_fichier = 'DO'
         AND c_st_type = 'I';
    ELSE
      RAISE_APPLICATION_ERROR(-20005,
                              fini ||
                              ' statut(s) de type ''I'' de defini il n''en faut exactement qu''un seul');
      fini := 0;
    END IF;
    SELECT COUNT(*)
      INTO actif
      FROM ST_STATUT
     WHERE c_st_genre_fichier = 'DO'
       AND c_st_type = 'A';
    IF actif = 1 THEN
      SELECT i_st_cle
        INTO actif
        FROM ST_STATUT
       WHERE c_st_genre_fichier = 'DO'
         AND c_st_type = 'A';
    ELSE
      RAISE_APPLICATION_ERROR(-20005,
                              actif ||
                              ' statut(s) de type ''A'' de defini il n''en faut exactement qu''un seul');
      actif := 0;
    END IF;
    SELECT COUNT(*)
      INTO avenir
      FROM ST_STATUT
     WHERE c_st_genre_fichier = 'DO'
       AND c_st_type = 'R';
    IF avenir = 1 THEN
      SELECT i_st_cle
        INTO avenir
        FROM ST_STATUT
       WHERE c_st_genre_fichier = 'DO'
         AND c_st_type = 'R';
    ELSE
      RAISE_APPLICATION_ERROR(-20005,
                              avenir ||
                              ' statut(s) de type ''R'' de defini il n''en faut exactement qu''un seul');
      avenir := 0;
    END IF;
    SELECT COUNT(*)
      INTO averifier
      FROM ST_STATUT
     WHERE c_st_genre_fichier = 'DO'
       AND c_st_type = 'N';
    IF averifier = 1 THEN
      SELECT i_st_cle
        INTO averifier
        FROM ST_STATUT
       WHERE c_st_genre_fichier = 'DO'
         AND c_st_type = 'N';
    ELSE
      RAISE_APPLICATION_ERROR(-20005,
                              averifier ||
                              ' statut(s) de type ''N'' de defini il n''en faut exactement qu''un seul');
      averifier := 0;
    END IF;
    --Procédure modifiée en octobre 2005 pour répondre aux demandes du service de prévention du jeu.
    --Une entrée dans I_IS_PERIODE a été ajoutée pour indiquer si le suivi auquel le joueur autoexclu est contraint a été
    --effectué. Si ce champ indique toujours "À suivre" (22950), on laisse le dossier actif, même si le contrat
    --d'autoexclusion est expiré. Le champ est directement réinitialité par l'intervenant lorsque le client
    --a été rencontré. Son contrat devient alors inactif après la date de fin.
    UPDATE IS_INSCRIPTION
       SET i_st_cle = fini
     WHERE (i_st_cle = actif)
       AND (TRUNC(NVL(d_is_date_fin, SYSDATE)) < TRUNC(SYSDATE))
       AND (i_is_periode <> 22950 or i_is_periode is null);
    COMMIT;
    -- Avril 2008 : la correction demandée ci-dessus ne doit s'appliquer qu'au Casino de Montréal (site 7)
    -- ainsi que Jean-Lapointe (52), Centre d'évaluation (10) et (Juillet 2010) sécurité corporative (29) .
    -- Janvier 2012 : le site 29 est retiré des critères.
    UPDATE IS_INSCRIPTION
       SET i_st_cle = fini
     WHERE (i_st_cle = actif)
       AND (TRUNC(NVL(d_is_date_fin, SYSDATE)) < TRUNC(SYSDATE))
       AND (i_is_periode = 22950 and
           (l_si_cle <> 7 AND l_si_cle <> 52 AND l_si_cle <> 10));
    COMMIT;
    -- Octobre 2008 : pour les sites 7, 10 et 52, le contrat bonofié est mis inactif si la rencontre finale
    -- a eu lieu. et (Juillet 2010) sécurité corporative (29) .
    -- Janvier 2012 : le site 29 est retiré des critères.
    UPDATE IS_INSCRIPTION
       SET i_st_cle = fini
     WHERE (i_st_cle = actif)
       AND (TRUNC(NVL(d_is_date_fin, SYSDATE)) < TRUNC(SYSDATE))
       AND (i_is_periode = 22950 and
           (l_si_cle = 7 OR l_si_cle = 52 OR l_si_cle = 10) and
           d_is_rencontre_finale is not null);
    COMMIT;
    UPDATE IS_INSCRIPTION is1
       SET is1.i_st_cle = actif
     WHERE (is1. i_st_cle = avenir)
       AND (TRUNC(NVL(is1.d_is_date_debut, SYSDATE)) >= TRUNC(SYSDATE))
       AND NOT EXISTS
     (SELECT *
              FROM IS_INSCRIPTION is2
             WHERE is2.i_st_cle = actif
               AND is1.l_is_reference = is2.l_is_reference
               AND is1.l_is_ref_site = is2.l_is_ref_site);
    COMMIT;
    UPDATE IS_INSCRIPTION is1
       SET is1.i_st_cle = actif
     WHERE (is1.i_st_cle = averifier)
       AND (TRUNC(NVL(is1.d_is_date_creation, SYSDATE)) + 2 >=
           TRUNC(SYSDATE))
       AND (TRUNC(NVL(is1.d_is_date_debut, SYSDATE)) <= TRUNC(SYSDATE))
       AND NOT EXISTS
     (SELECT *
              FROM IS_INSCRIPTION is2
             WHERE is2.i_st_cle = actif
               AND is1.l_is_reference = is2.l_is_reference
               AND is1.l_is_ref_site = is2.l_is_ref_site);
    COMMIT;
    UPDATE IS_INSCRIPTION
       SET i_st_cle = avenir
     WHERE (i_st_cle = averifier)
       AND (TRUNC(NVL(d_is_date_creation, SYSDATE)) + 2 >= TRUNC(SYSDATE))
       AND (TRUNC(NVL(d_is_date_debut, SYSDATE)) > TRUNC(SYSDATE));
    COMMIT;
    UPDATE IS_INSCRIPTION
       SET i_st_cle = fini
     WHERE (i_st_cle = averifier)
       AND (TRUNC(NVL(d_is_date_creation, SYSDATE)) + 2 >= TRUNC(SYSDATE))
       AND (TRUNC(NVL(d_is_date_fin, SYSDATE)) < TRUNC(SYSDATE));
    COMMIT;
    --Ajout le 3 octobre 2008. L'émission de contrats dans le futur est maintenant
    --permise. On s'assure que le statut soit "À venir" pour éviter l'affichage
    --de deux contrats actifs.
    UPDATE IS_INSCRIPTION
       SET i_st_cle = avenir
     WHERE (i_st_cle = actif)
       AND (TRUNC(d_is_date_debut) > TRUNC(SYSDATE));
    COMMIT;
    UPDATE DO_DOSSIER d
       SET d.i_st_cle        = (SELECT i.i_st_cle
                                  FROM IS_INSCRIPTION i
                                 WHERE i.d_is_date_creation =
                                       (SELECT MAX(i2.d_is_date_creation)
                                          FROM IS_INSCRIPTION i2
                                         WHERE i2.L_IS_REFERENCE = d.l_do_cle
                                           AND i2.l_IS_ref_SITE = d.l_si_cle
                                           AND (i2.i_st_cle = actif OR
                                               i2.i_st_cle = fini OR
                                               i2.i_st_cle = avenir))
                                   AND i.L_IS_REFERENCE = d.l_do_cle
                                   AND i.l_IS_ref_SITE = d.l_si_cle),
           d.d_do_date_debut = (SELECT i3.d_is_date_debut
                                  FROM IS_INSCRIPTION i3
                                 WHERE i3.d_is_date_creation =
                                       (SELECT MAX(i4.d_is_date_creation)
                                          FROM IS_INSCRIPTION i4
                                         WHERE i4.L_IS_REFERENCE = d.l_do_cle
                                           AND i4.l_IS_ref_SITE = d.l_si_cle
                                           AND (i4.i_st_cle = actif OR
                                               i4.i_st_cle = fini OR
                                               i4.i_st_cle = avenir))
                                   AND i3.L_IS_REFERENCE = d.l_do_cle
                                   AND i3.l_IS_ref_SITE = d.l_si_cle),
           d.d_do_date_fin   = (SELECT i5.d_is_date_fin
                                  FROM IS_INSCRIPTION i5
                                 WHERE i5.d_is_date_creation =
                                       (SELECT MAX(i6.d_is_date_creation)
                                          FROM IS_INSCRIPTION i6
                                         WHERE i6.L_IS_REFERENCE = d.l_do_cle
                                           AND i6.l_IS_ref_SITE = d.l_si_cle
                                           AND (i6. i_st_cle = actif OR
                                                i6.i_st_cle = fini OR
                                                i6.i_st_cle = avenir))
                                   AND i5.L_IS_REFERENCE = d.l_do_cle
                                   AND i5.l_IS_ref_SITE = d.l_si_cle)
     WHERE d.l_do_cle =
           (SELECT i7.l_is_reference
              FROM IS_INSCRIPTION i7
             WHERE i7.d_is_date_creation =
                   (SELECT MAX(i8.d_is_date_creation)
                      FROM IS_INSCRIPTION i8
                     WHERE i8.L_IS_REFERENCE = d.l_do_cle
                       AND i8.l_IS_ref_SITE = d.l_si_cle
                       AND (i8.i_st_cle = actif OR i8.i_st_cle = fini))
               AND i7.L_IS_REFERENCE = d.l_do_cle
               AND i7.l_IS_ref_SITE = d.l_si_cle)
       AND d.l_si_cle =
           (SELECT i9.l_is_ref_site
              FROM IS_INSCRIPTION i9
             WHERE i9.d_is_date_creation =
                   (SELECT MAX(i10.d_is_date_creation)
                      FROM IS_INSCRIPTION i10
                     WHERE i10.L_IS_REFERENCE = d.l_do_cle
                       AND i10.l_IS_ref_SITE = d.l_si_cle
                       AND (i10.i_st_cle = actif OR i10.i_st_cle = fini))
               AND i9.L_IS_REFERENCE = d.l_do_cle
               AND i9.l_IS_ref_SITE = d.l_si_cle);
    COMMIT;
    --Mise à jour de la durée de validité (en mois) du dossier
    UPDATE DO_DOSSIER d
       SET d.v_do_duree = TO_CHAR(ROUND(MONTHS_BETWEEN(d.d_do_date_fin,
                                                       d.d_do_date_debut)))
     WHERE d.l_do_cle =
           (SELECT is1.l_is_reference
              FROM IS_INSCRIPTION is1
             WHERE is1.d_is_date_creation =
                   (SELECT MAX(d_is_date_creation)
                      FROM IS_INSCRIPTION
                     WHERE L_IS_REFERENCE = d.l_do_cle
                       AND l_IS_ref_SITE = d.l_si_cle
                       AND (i_st_cle = actif OR i_st_cle = fini))
               AND is1.L_IS_REFERENCE = d.l_do_cle
               AND is1.l_IS_ref_SITE = d.l_si_cle)
       AND d.l_si_cle =
           (SELECT is2.l_is_ref_site
              FROM IS_INSCRIPTION is2
             WHERE is2.d_is_date_creation =
                   (SELECT MAX(d_is_date_creation)
                      FROM IS_INSCRIPTION
                     WHERE L_IS_REFERENCE = d.l_do_cle
                       AND l_IS_ref_SITE = d.l_si_cle
                       AND (i_st_cle = actif OR i_st_cle = fini))
               AND is2.L_IS_REFERENCE = d.l_do_cle
               AND is2.l_IS_ref_SITE = d.l_si_cle);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : sp_active_inscription',
                              TRUE);
  END sp_active_inscription;

  PROCEDURE SP_E_AG_ACCES_GENRE(action                     IN CHAR,
                                NEW_L_AG_CLE               IN AG_ACCES_GENRE.L_AG_CLE%TYPE,
                                NEW_V_AG_NAME              IN AG_ACCES_GENRE.V_AG_NAME%TYPE,
                                NEW_L_GE_CLE               IN AG_ACCES_GENRE.L_GE_CLE%TYPE,
                                NEW_V_AG_TYPE_TAB          IN AG_ACCES_GENRE.V_AG_TYPE_TAB%TYPE,
                                NEW_C_AG_NOUVEAU           IN AG_ACCES_GENRE.C_AG_NOUVEAU%TYPE,
                                NEW_C_AG_CONSULTATION      IN AG_ACCES_GENRE.C_AG_CONSULTATION%TYPE,
                                NEW_D_AG_DATE_MODIFICATION IN AG_ACCES_GENRE.D_AG_DATE_MODIFICATION%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO AG_ACCES_GENRE
        (L_AG_CLE,
         V_AG_NAME,
         L_GE_CLE,
         V_AG_TYPE_TAB,
         C_AG_NOUVEAU,
         C_AG_CONSULTATION,
         D_AG_DATE_MODIFICATION)
      VALUES
        (NEW_L_AG_CLE,
         NEW_V_AG_NAME,
         NEW_L_GE_CLE,
         NEW_V_AG_TYPE_TAB,
         NEW_C_AG_NOUVEAU,
         NEW_C_AG_CONSULTATION,
         NEW_D_AG_DATE_MODIFICATION);
      COMMIT;
    ELSIF action = 'U' THEN
      UPDATE AG_ACCES_GENRE
         SET V_AG_NAME              = NEW_V_AG_NAME,
             L_GE_CLE               = NEW_L_GE_CLE,
             V_AG_TYPE_TAB          = NEW_V_AG_TYPE_TAB,
             C_AG_NOUVEAU           = NEW_C_AG_NOUVEAU,
             C_AG_CONSULTATION      = NEW_C_AG_CONSULTATION,
             D_AG_DATE_MODIFICATION = NEW_D_AG_DATE_MODIFICATION
       WHERE L_AG_CLE = NEW_L_AG_CLE;
      COMMIT;
    ELSIF action = 'D' THEN
      DELETE FROM AG_ACCES_GENRE WHERE L_AG_CLE = NEW_L_AG_CLE;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : SP_E_AG_ACCES_GENRE',
                              TRUE);
  END SP_E_AG_ACCES_GENRE;
  PROCEDURE Sp_L_Ag_Acces_Genre(NEW_NAME  IN AG_ACCES_GENRE.V_AG_NAME%TYPE,
                                NEW_GROUP IN AG_ACCES_GENRE.V_AG_NAME%TYPE,
                                rc1       IN OUT Cardex_Special.REC_DTS_ACCES_GENRE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT distinct L_AG_CLE,
                      V_AG_NAME,
                      L_GE_CLE,
                      V_AG_TYPE_TAB,
                      C_AG_NOUVEAU,
                      C_AG_CONSULTATION,
                      D_AG_DATE_MODIFICATION
        FROM AG_ACCES_GENRE, IN_INTERVENANT I
       WHERE (V_AG_NAME = NEW_NAME OR V_AG_NAME = NEW_GROUP)
         AND V_AG_NAME = I.NAME;
  END Sp_L_Ag_Acces_Genre;
  --Désactivation des mandats PSU (programme de suivi des utilisateurs)
  PROCEDURE sp_desactive_mandats IS
    inactif ST_STATUT.i_st_cle%TYPE := 0;
    actif   ST_STATUT.i_st_cle%TYPE := 0;
  BEGIN
    SELECT COUNT(*)
      INTO inactif
      FROM ST_STATUT
     WHERE c_st_genre_fichier = 'DO'
       AND c_st_type = 'I';
    IF inactif = 1 THEN
      SELECT i_st_cle
        INTO inactif
        FROM ST_STATUT
       WHERE c_st_genre_fichier = 'DO'
         AND c_st_type = 'I';
    ELSE
      RAISE_APPLICATION_ERROR(-20005,
                              inactif ||
                              ' statut(s) de type ''I'' de defini il n''en faut exactement qu''un seul');
      inactif := 0;
    END IF;
    SELECT COUNT(*)
      INTO actif
      FROM ST_STATUT
     WHERE c_st_genre_fichier = 'DO'
       AND c_st_type = 'A';
    IF actif = 1 THEN
      SELECT i_st_cle
        INTO actif
        FROM ST_STATUT
       WHERE c_st_genre_fichier = 'DO'
         AND c_st_type = 'A';
    ELSE
      RAISE_APPLICATION_ERROR(-20005,
                              actif ||
                              ' statut(s) de type ''A'' de defini il n''en faut exactement qu''un seul');
      actif := 0;
    END IF;
    UPDATE PSU_MANDAT P
       set P.i_St_Cle = inactif
     where P.i_St_Cle = actif
       and P.d_Psu_Date_Fin < sysdate;
    commit;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : Sp_desactive_mandats',
                              TRUE);
  END Sp_desactive_mandats;

  /*-PROCEDURE SP_L_ME_MESSAGE_COURANT(
     rc1      IN OUT Cardex_Special.DTS_ME_MESSAGE) IS
  BEGIN
  OPEN rc1 FOR
    SELECT ME.*
    FROM ME_MESSAGE ME
    WHERE (ME.D_ME_DATE_DEBUT IS NULL
      OR ME.D_ME_DATE_DEBUT < SYSDATE)
     AND (ME.D_ME_DATE_FIN IS NULL
      OR ME.D_ME_DATE_FIN > SYSDATE)
     ORDER BY ME.I_ME_NIVEAU, ME.D_ME_DATE_DEBUT;
  END SP_L_ME_MESSAGE_COURANT;*/

  /*PROCEDURE SP_RAFRAICHIR_AE IS
  VAR_L_SU_CLE SU_SUJET.L_SU_CLE%TYPE;
  VAR_L_SI_CLE SU_SUJET.L_SI_CLE%TYPE;
  VAR_V_SU_NOM SU_SUJET.V_SU_NOM%TYPE;
  VAR_V_SU_PRENOM SU_SUJET.V_SU_PRENOM%TYPE;
  VAR_D_SU_DATE_NAISSANCE SU_SUJET.D_SU_DATE_NAISSANCE%TYPE;
  VAR_V_SU_NO_FICHE SU_SUJET.V_SU_REFERENCE_3%TYPE;
  VAR_V_AD_NUM_MUNICIPAL AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE;
  VAR_V_AD_CODE_POSTAL AD_ADRESSE.V_AD_CODE_POSTAL%TYPE;
  VAR_I_PE_CLE DO_DOSSIER.I_PE_CLE%TYPE;
  VAR_D_DO_DATE_FIN DO_DOSSIER.D_DO_DATE_FIN%TYPE;
  
  VAR_V_AE_NOM AE_AUTO_EXCLUSION.V_AE_NOM%TYPE;
  VAR_V_AE_PRENOM AE_AUTO_EXCLUSION.V_AE_PRENOM%TYPE;
  VAR_D_AE_DATE_NAISSANCE AE_AUTO_EXCLUSION.D_AE_DATE_NAISSANCE%TYPE;
  VAR_V_AE_NO_FICHE AE_AUTO_EXCLUSION.V_AE_NO_FICHE%TYPE;
  VAR_V_AE_NUM_MUNICIPAL AE_AUTO_EXCLUSION.V_AE_NUM_MUNICIPAL%TYPE;
  VAR_V_AE_CODE_POSTAL AE_AUTO_EXCLUSION.V_AE_CODE_POSTAL%TYPE;
  VAR_I_AE_PE_CLE AE_AUTO_EXCLUSION.I_PE_CLE%TYPE;
  VAR_D_AE_DATE_FIN AE_AUTO_EXCLUSION.D_AE_DATE_FIN%TYPE;
  
  CURSOR sujet_autoexclue IS
    select distinct su.l_su_cle, su.l_si_cle, su.v_su_nom, su.v_su_prenom, su.d_su_date_naissance, su.v_su_reference_3, ad.v_ad_num_municipal, ad.v_ad_code_postal, do.i_pe_cle, do.d_do_date_fin
    from su_sujet su, do_dossier do, ldd_lien_dossier ldd, ad_adresse ad
    where do.i_ca_cle = 112
    and ldd.l_do_cle = do.l_do_cle
    and ldd.l_do_site = do.l_si_cle
    and ldd.c_do_genre = 'DO'
    and ldd.l_ldd_dossier_associe = su.l_su_cle
    and ldd.l_ldd_site = su.l_si_cle
    and ldd.c_ldd_genre = 'SU'
    and do.i_st_cle = 359
    and ad.l_ad_reference (+) = su.l_su_cle
    and ad.l_ad_ref_site (+) = su.l_si_cle
    and ad.c_ad_ref_genre (+) = 'SU'
    and ad.i_st_cle = 621;
  CURSOR sujet_table_autoexclue IS
    select ae.v_ae_nom, ae.v_ae_prenom, ae.d_ae_date_naissance, ae.v_ae_no_fiche, ae.v_ae_num_municipal, ae.v_ae_code_postal, ae.i_pe_cle, ae.d_ae_date_fin
    from ae_auto_exclusion ae
    where ae.l_ae_cle = VAR_L_SU_CLE
    and ae.l_si_cle = VAR_L_SI_CLE;
  CURSOR table_autoexclue IS
    select ae.v_ae_nom, ae.v_ae_prenom, ae.d_ae_date_naissance, ae.v_ae_no_fiche, ae.v_ae_num_municipal, ae.v_ae_code_postal, ae.i_pe_cle, ae.d_ae_date_fin
    from ae_auto_exclusion ae;
  BEGIN
       OPEN sujet_autoexclue;
       LOOP
          FETCH sujet_autoexclue INTO VAR_L_SU_CLE, VAR_L_SI_CLE, VAR_V_SU_NOM, VAR_V_SU_PRENOM, VAR_D_SU_DATE_NAISSANCE, VAR_V_SU_NO_FICHE, VAR_V_AD_NUM_MUNICIPAL, VAR_V_AD_CODE_POSTAL, VAR_I_PE_CLE, VAR_D_DO_DATE_FIN;
          OPEN sujet_table_autoexclue;
          FETCH sujet_table_autoexclue INTO VAR_V_AE_NOM, VAR_V_AE_PRENOM, VAR_D_AE_DATE_NAISSANCE, VAR_V_AE_NO_FICHE, VAR_V_AE_NUM_MUNICIPAL, VAR_V_AE_CODE_POSTAL, VAR_I_AE_PE_CLE, VAR_D_AE_DATE_FIN;
  
          IF sujet_table_autoexclue%NOTFOUND THEN
               DBMS_OUTPUT.put_line('insert');
               insert into ae_auto_exclusion values
                      (VAR_L_SU_CLE, VAR_L_SI_CLE, VAR_V_SU_NOM, VAR_V_SU_PRENOM, VAR_D_SU_DATE_NAISSANCE, VAR_V_SU_NO_FICHE, VAR_V_AD_CODE_POSTAL, VAR_V_AD_NUM_MUNICIPAL, VAR_I_PE_CLE, VAR_D_DO_DATE_FIN);
          ELSIF VAR_V_SU_NOM != VAR_V_AE_NOM or VAR_V_SU_PRENOM != VAR_V_AE_PRENOM
          or VAR_D_SU_DATE_NAISSANCE != VAR_D_AE_DATE_NAISSANCE or VAR_V_SU_NO_FICHE != VAR_V_AE_NO_FICHE
          or VAR_V_AD_CODE_POSTAL != VAR_V_AE_CODE_POSTAL or VAR_I_PE_CLE != VAR_I_AE_PE_CLE
          or (VAR_D_DO_DATE_FIN is null and VAR_D_AE_DATE_FIN is not null) or VAR_D_DO_DATE_FIN > VAR_D_AE_DATE_FIN THEN
               DBMS_OUTPUT.put_line('update');
               update ae_auto_exclusion ae
                      set ae.v_ae_nom=VAR_V_SU_NOM,
                      ae.v_ae_prenom=VAR_V_SU_PRENOM,
                      ae.d_ae_date_naissance=VAR_D_SU_DATE_NAISSANCE,
                      ae.v_ae_no_fiche=VAR_V_SU_NO_FICHE,
                      ae.v_ae_code_postal=VAR_V_AD_CODE_POSTAL,
                      ae.i_pe_cle=VAR_I_PE_CLE,
                      ae.d_ae_date_fin=VAR_D_DO_DATE_FIN
               where ae.l_ae_cle = VAR_L_SU_CLE
               and ae.l_si_cle = VAR_L_SI_CLE;
          END IF;
          CLOSE sujet_table_autoexclue;
          EXIT WHEN sujet_autoexclue%NOTFOUND;
       END LOOP;
       CLOSE sujet_autoexclue;
       commit;
  END SP_RAFRAICHIR_AE;*/

  PROCEDURE SP_L_ROLE_SECURITE(NEW_RS_APPLICATION IN RS_ROLE_SECURITE.V_RS_APPLICATION%TYPE,
                               rc1                OUT DTS_RS_ROLE_SECURITE) IS
  BEGIN
    OPEN rc1 FOR
      select *
        from RS_ROLE_SECURITE rs
       where rs.v_rs_application = NEW_RS_APPLICATION;
  END SP_L_ROLE_SECURITE;

  PROCEDURE SP_L_INTERVENANT_GROUPE(NEW_V_NAME in IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE,
                                    rc1        OUT DTS_IGS_INTERVENANT_GROUPE_SEC) IS
  BEGIN
    OPEN rc1 FOR
      select * from IGS_INTERVENANT_GROUPE_SEC where V_NAME = NEW_V_NAME;
  END SP_L_INTERVENANT_GROUPE;

  --Liste des intervenants reliés à un groupe de sécurité
  PROCEDURE SP_L2_INTERVENANT_GROUPE(NEW_L_GS_CLE in IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                     rc1          OUT DTS_INTERVENANT_GROUPE) IS
  BEGIN
    OPEN rc1 FOR
      select I.NAME,
             I.V_IN_NOM,
             I.V_IN_PRENOM,
             T.V_TR_DESCRIPTION,
             I.L_SI_CLE,
             I.L_IN_SECTEUR
        from IGS_INTERVENANT_GROUPE_SEC, IN_INTERVENANT I, TR_TRADUCTION T
       where L_GS_CLE = NEW_L_GS_CLE
         AND V_NAME = I.NAME
         AND I.L_IN_SECTEUR = T.L_TR_CLE
         AND T.I_LA_CLE = CARDEX_SPECIAL.g_langue;
  END SP_L2_INTERVENANT_GROUPE;

  PROCEDURE SP_L_INTERVENANT_ROLE(NEW_V_NAME in IR_INTERVENANT_ROLE.V_NAME%TYPE,
                                  rc1        OUT DTS_IR_INTERVENANT_ROLE) IS
  BEGIN
    OPEN rc1 FOR
      select * from IR_INTERVENANT_ROLE where V_NAME = NEW_V_NAME;
  END SP_L_INTERVENANT_ROLE;

  PROCEDURE SP_L2_INTERVENANT_ROLE(NEW_ROLE in IR_INTERVENANT_ROLE.V_IR_ROLE%TYPE,
                                   rc1      OUT DTS_INTERVENANT_GROUPE) IS
  BEGIN
    OPEN rc1 FOR
      select I.NAME,
             I.V_IN_NOM,
             I.V_IN_PRENOM,
             T.V_TR_DESCRIPTION,
             I.L_SI_CLE,
             I.L_IN_SECTEUR
        from IR_INTERVENANT_ROLE IR, IN_INTERVENANT I, TR_TRADUCTION T
       where I.NAME = IR.V_NAME
         AND IR.V_IR_ROLE = NEW_ROLE
         AND I.L_IN_SECTEUR = T.L_TR_CLE
         AND T.I_LA_CLE = CARDEX_SPECIAL.g_langue;
  END SP_L2_INTERVENANT_ROLE;

  PROCEDURE SP_L_GROUPE_ROLE(NEW_L_GR_CLE in GR_GROUPE_ROLE.L_GR_CLE%TYPE,
                             rc1          OUT DTS_GR_GROUPE_ROLE) IS
  BEGIN
    OPEN rc1 FOR
      select * from GR_GROUPE_ROLE where L_GR_CLE = NEW_L_GR_CLE;
  END SP_L_GROUPE_ROLE;

  PROCEDURE SP_L2_GROUPE_ROLE(NEW_V_GR_ROLE in GR_GROUPE_ROLE.V_GR_ROLE%TYPE,
                              rc1           OUT DTS_GR_GROUPE_ROLE) IS
  BEGIN
    OPEN rc1 FOR
      select * from GR_GROUPE_ROLE where V_GR_ROLE = NEW_V_GR_ROLE;
  END SP_L2_GROUPE_ROLE;

  PROCEDURE SP_L3_GROUPE_ROLE(NEW_V_GR_ROLE in GR_GROUPE_ROLE.V_GR_ROLE%TYPE,
                              rc1           OUT DTS_GR_GROUPE_ROLE2) IS
  BEGIN
    OPEN rc1 FOR
      SELECT GS.L_GS_CLE, GS.V_GS_NOM
        FROM GS_GROUPE_SECURITE GS, GR_GROUPE_ROLE GR
       WHERE GS.L_GS_CLE = GR.L_GR_CLE
         AND GR.V_GR_ROLE like NEW_V_GR_ROLE
       ORDER BY GS.V_GS_NOM;
  END SP_L3_GROUPE_ROLE;

  --Liste des groupes d'un intervenant
  PROCEDURE SP_L_GROUPE_INTERVENANT(NEW_NAME in IN_INTERVENANT.NAME%TYPE,
                                    rc1      OUT DTS_GR_GROUPE_ROLE2) IS
  BEGIN
    OPEN rc1 FOR
      SELECT GS.L_GS_CLE, GS.V_GS_NOM
        FROM IGS_INTERVENANT_GROUPE_SEC IGS, GS_GROUPE_SECURITE GS
       WHERE IGS.V_NAME = NEW_NAME
         AND IGS.L_GS_CLE = GS.L_GS_CLE
       ORDER BY GS.V_GS_NOM;
  END SP_L_GROUPE_INTERVENANT;

  PROCEDURE SP_L_INTERVENANT_PARENT(NEW_V_IN_NAME_PARENT in IN_INTERVENANT.V_IN_NAME_PARENT%TYPE,
                                    rc1                  OUT DTS_IN_INTERVENANT) IS
  BEGIN
    OPEN rc1 FOR
      select *
        from IN_INTERVENANT
       where v_in_name_parent = NEW_V_IN_NAME_PARENT;
  END SP_L_INTERVENANT_PARENT;

  --Lecture des profils d'un intervenant pour le choix au lancement de l'application
  PROCEDURE SP_L2_INTERVENANT_PARENT(NEW_NAME in IN_INTERVENANT.NAME%TYPE,
                                     rc1      OUT DTS_INTERVENANT_PARENT2) IS
  BEGIN
    OPEN rc1 FOR
      select I.NAME                AS "PROFIL",
             I.V_IN_NAME_PARENT    AS "PARENT",
             I.V_IN_NOM            AS "NOM",
             I.V_IN_PRENOM         AS "PRENOM",
             TRS.V_TR_DESCRIPTION  AS "SECTEUR",
             TRSS.V_TR_DESCRIPTION AS "SOUS_SECTEUR",
             TRST.V_TR_DESCRIPTION AS "STATUT",
             TRNH.V_TR_DESCRIPTION AS "AUTORITE",
             TRCC.V_TR_DESCRIPTION AS "CONFIDENTIALITE",
             S.V_SI_SITE           as "SITE",
             S.L_SI_CLE            as "SITE_CLE",
             S.I_EN_CLE            as "ENTITE"
        from IN_INTERVENANT I,
             TR_TRADUCTION  TRS,
             TR_TRADUCTION  TRSS,
             TR_TRADUCTION  TRST,
             TR_TRADUCTION  TRNH,
             TR_TRADUCTION  TRCC,
             SI_SITE        S
       where v_in_name_parent =
             (select I2.V_IN_NAME_PARENT
                from IN_INTERVENANT I2
               where I2.NAME = NEW_NAME)
         AND I.L_IN_SECTEUR = TRS.L_TR_CLE
         AND TRS.I_LA_CLE = 1
         AND I.L_IN_SOUS_SECTEUR = TRSS.L_TR_CLE(+)
         AND TRSS.I_LA_CLE(+) = 1
         AND I.I_ST_CLE = TRST.L_TR_CLE
         AND TRST.I_LA_CLE = 1
         AND I.I_NH_CLE = TRNH.L_TR_CLE
         AND TRNH.I_LA_CLE = 1
         AND I.I_CC_CLE = TRCC.L_TR_CLE
         AND TRCC.I_LA_CLE = 1
         AND I.L_SI_CLE = S.L_SI_CLE
         AND I.I_ST_CLE = 532 --Actifs seulement
       ORDER BY I.NAME;
  END SP_L2_INTERVENANT_PARENT;

  --Attribution d'un intervenant à un groupe
  PROCEDURE SP_E_INTERVENANT_GROUPE(action       IN CHAR,
                                    NEW_L_GS_CLE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                    NEW_V_NAME   IN IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO IGS_INTERVENANT_GROUPE_SEC
        (L_GS_CLE, V_NAME)
      VALUES
        (NEW_L_GS_CLE, NEW_V_NAME);
      COMMIT;
    ELSIF action = 'D' THEN
      DELETE FROM IGS_INTERVENANT_GROUPE_SEC WHERE L_GS_CLE = NEW_L_GS_CLE;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : SP_E_INTERVENANT_GROUPE',
                              TRUE);
  END SP_E_INTERVENANT_GROUPE;

  --Attribution d'un groupe à un intervenant
  PROCEDURE SP_E_GROUPE_INTERVENANT(action       IN CHAR,
                                    NEW_L_GS_CLE IN IGS_INTERVENANT_GROUPE_SEC.L_GS_CLE%TYPE,
                                    NEW_V_NAME   IN IGS_INTERVENANT_GROUPE_SEC.V_NAME%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO IGS_INTERVENANT_GROUPE_SEC
        (L_GS_CLE, V_NAME)
      VALUES
        (NEW_L_GS_CLE, NEW_V_NAME);
      COMMIT;
    ELSIF action = 'D' THEN
      DELETE FROM IGS_INTERVENANT_GROUPE_SEC WHERE V_NAME = NEW_V_NAME;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : SP_E_INTERVENANT_GROUPE',
                              TRUE);
  END SP_E_GROUPE_INTERVENANT;

  --Attribution d'un rôle à un groupe de sécurité
  PROCEDURE SP_E_ROLE_GROUPE(action       IN CHAR,
                             NEW_L_GR_CLE IN GR_GROUPE_ROLE.L_GR_CLE%TYPE,
                             NEW_V_ROLE   IN GR_GROUPE_ROLE.V_GR_ROLE%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO GR_GROUPE_ROLE
        (L_GR_CLE, V_GR_ROLE)
      VALUES
        (NEW_L_GR_CLE, NEW_V_ROLE);
      COMMIT;
    ELSIF action = 'D' THEN
      -- Cas de liaison de rôles à des groupes
      DELETE FROM GR_GROUPE_ROLE WHERE L_GR_CLE = NEW_L_GR_CLE;
      COMMIT;
    ELSIF action = 'G' THEN
      -- Cas de liaison de groupes à des rôles
      DELETE FROM GR_GROUPE_ROLE WHERE V_GR_ROLE = NEW_V_ROLE;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000, 'Database : SP_E_ROLE_GROUPE', TRUE);
  END SP_E_ROLE_GROUPE;

  --Attribution d'un rôle à un intervenant
  PROCEDURE SP_E_ROLE_INTERVENANT(action        IN CHAR,
                                  NEW_V_NAME    IN IR_INTERVENANT_ROLE.V_NAME%TYPE,
                                  NEW_V_IR_ROLE IN IR_INTERVENANT_ROLE.V_IR_ROLE%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO IR_INTERVENANT_ROLE
        (V_NAME, V_IR_ROLE)
      VALUES
        (NEW_V_NAME, NEW_V_IR_ROLE);
      COMMIT;
    ELSIF action = 'D' THEN
      DELETE FROM IR_INTERVENANT_ROLE WHERE V_NAME = NEW_V_NAME;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000,
                              'Database : SP_E_ROLE_INTERVENANT',
                              TRUE);
  END SP_E_ROLE_INTERVENANT;

  --Attribution d'un sous-groupe à un groupe
  PROCEDURE SP_E_SOUS_GROUPE(action     IN CHAR,
                             NEW_PARENT IN SGR_SOUS_GROUPE_SECURITE.L_SGR_PARENT_CLE%TYPE,
                             NEW_ENFANT IN SGR_SOUS_GROUPE_SECURITE.L_SGR_ENFANT_CLE%TYPE) IS
  BEGIN
    IF action = 'I' THEN
      INSERT INTO SGR_SOUS_GROUPE_SECURITE
      --On s'assure que le parent et l'enfant n'ont pas déjà été associés comme enfant et comme parent.
      --Autrement, la lecture des rôles associés provoquerait une boucle infinie.
        SELECT NEW_PARENT, NEW_ENFANT
          FROM DUAL
         WHERE NOT EXISTS (SELECT *
                  FROM SGR_SOUS_GROUPE_SECURITE T2
                 WHERE T2.L_SGR_PARENT_CLE = NEW_ENFANT
                   AND T2.L_SGR_ENFANT_CLE = NEW_PARENT);
      COMMIT;
    ELSIF action = 'D' THEN
      DELETE FROM SGR_SOUS_GROUPE_SECURITE
       WHERE L_SGR_PARENT_CLE = NEW_PARENT;
      COMMIT;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20000, 'Database : SP_E_SOUS_GROUPE', TRUE);
  END SP_E_SOUS_GROUPE;

  --Lecture des sous-groupes d'un groupe de sécurité
  PROCEDURE SP_L_SOUS_GROUPE(NEW_L_GS_CLE IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
                             rc1          OUT DTS_SOUS_GROUPE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT SGR.L_SGR_PARENT_CLE,
             GS.V_GS_NOM AS "PARENT",
             SGR.L_SGR_ENFANT_CLE,
             GS2.V_GS_NOM AS "ENFANT"
        FROM GS_GROUPE_SECURITE       GS,
             SGR_SOUS_GROUPE_SECURITE SGR,
             GS_GROUPE_SECURITE       GS2
       WHERE SGR.L_SGR_PARENT_CLE = GS.L_GS_CLE
         AND SGR.L_SGR_ENFANT_CLE = GS2.L_GS_CLE
         AND SGR.L_SGR_PARENT_CLE = NEW_L_GS_CLE;
  END SP_L_SOUS_GROUPE;

  --Copie des rôles et des groupes d'un profil à l'autre
  PROCEDURE SP_E_PROFIL(NEW_PROFIL_SOURCE      IN IN_INTERVENANT.NAME%TYPE,
                        NEW_PROFIL_DESTINATION IN IN_INTERVENANT.NAME%TYPE) IS
  BEGIN
    INSERT INTO IR_INTERVENANT_ROLE
      SELECT NEW_PROFIL_DESTINATION, IR.V_IR_ROLE
        FROM IR_INTERVENANT_ROLE IR
       WHERE IR.V_NAME = NEW_PROFIL_SOURCE;
    INSERT INTO IGS_INTERVENANT_GROUPE_SEC
      SELECT NEW_PROFIL_DESTINATION, IGS.L_GS_CLE
        FROM IGS_INTERVENANT_GROUPE_SEC IGS
       WHERE IGS.V_NAME = NEW_PROFIL_SOURCE;
    COMMIT;
  END SP_E_PROFIL;

  --Copie des rôles et des sous-groupes d'un groupe à l'autre
  PROCEDURE SP_E_GROUPE(NEW_GROUPE_SOURCE      IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE,
                        NEW_GROUPE_DESTINATION IN GS_GROUPE_SECURITE.L_GS_CLE%TYPE) IS
  BEGIN
    INSERT INTO GR_GROUPE_ROLE
      SELECT NEW_GROUPE_DESTINATION, GR.V_GR_ROLE
        FROM GR_GROUPE_ROLE GR
       WHERE GR.L_GR_CLE = NEW_GROUPE_SOURCE;
    INSERT INTO SGR_SOUS_GROUPE_SECURITE
      SELECT NEW_GROUPE_DESTINATION, SGR.L_SGR_ENFANT_CLE
        FROM SGR_SOUS_GROUPE_SECURITE SGR
       WHERE SGR.L_SGR_PARENT_CLE = NEW_GROUPE_SOURCE;
    COMMIT;
  END SP_E_GROUPE;

  --Lecture des messages à afficher au Menu principal de Cardex
  PROCEDURE SP_L_ME_MESSAGE_COURANT(rc1 IN OUT Cardex_Special.DTS_ME_MESSAGE) IS
  BEGIN
    OPEN rc1 FOR
      SELECT ME.*
        FROM ME_MESSAGE ME
       WHERE (ME.D_ME_DATE_DEBUT IS NULL OR ME.D_ME_DATE_DEBUT < SYSDATE)
         AND (ME.D_ME_DATE_FIN IS NULL OR ME.D_ME_DATE_FIN > SYSDATE)
       ORDER BY ME.I_ME_NIVEAU, ME.D_ME_DATE_DEBUT;
  END SP_L_ME_MESSAGE_COURANT;

  --Sert à transférer les données liées d'une société à l'autre. On place ensuite la société source en 
  --confidentialité 8 pour qu'elle soit éventuellement épurée. 
  PROCEDURE SP_E_COPIER_SOCIETE(NEW_L_SO_CLE_SOURCE IN SO_SOCIETE.L_SO_CLE%TYPE,
                                NEW_L_SI_CLE_SOURCE IN SO_SOCIETE.L_SI_CLE%TYPE,
                                NEW_L_SO_CLE_DEST   IN SO_SOCIETE.L_SO_CLE%TYPE,
                                NEW_L_SI_CLE_DEST   IN OUT SO_SOCIETE.L_SI_CLE%TYPE) IS
  BEGIN
    UPDATE LDD_LIEN_DOSSIER L
       SET L.L_LDD_DOSSIER_ASSOCIE = NEW_L_SO_CLE_DEST,
           L.L_LDD_SITE            = NEW_L_SI_CLE_DEST
     WHERE L.L_LDD_DOSSIER_ASSOCIE = NEW_L_SO_CLE_SOURCE
       AND L.L_LDD_SITE = NEW_L_SI_CLE_SOURCE
       AND L.C_LDD_GENRE = 'SO';
    UPDATE CO_COMMENTAIRE2 C
       SET C.L_CO_REFERENCE = NEW_L_SO_CLE_DEST,
           C.L_CO_REF_SITE  = NEW_L_SI_CLE_DEST
     WHERE C.L_CO_REFERENCE = NEW_L_SO_CLE_SOURCE
       AND C.L_CO_REF_SITE = NEW_L_SI_CLE_SOURCE
       AND C.C_CO_REF_GENRE = 'SO';
    UPDATE SO_SOCIETE S
       SET S.I_CC_CLE = 14920
     WHERE S.L_SO_CLE = NEW_L_SO_CLE_SOURCE
       AND S.L_SI_CLE = NEW_L_SI_CLE_SOURCE;
    COMMIT;
  END SP_E_COPIER_SOCIETE;

  --Sert à transférer les données liées d'un sujet à l'autre. On place ensuite le sujet source en 
  --confidentialité 8 pour qu'il soit éventuellement épuré. 
  PROCEDURE SP_E_COPIER_SUJET(NEW_L_SU_CLE_SOURCE IN SU_SUJET.L_SU_CLE%TYPE,
                              NEW_L_SI_CLE_SOURCE IN SU_SUJET.L_SI_CLE%TYPE,
                              NEW_L_SU_CLE_DEST   IN SU_SUJET.L_SU_CLE%TYPE,
                              NEW_L_SI_CLE_DEST   IN OUT SU_SUJET.L_SI_CLE%TYPE) IS
  BEGIN
    UPDATE LDD_LIEN_DOSSIER L
       SET L.L_LDD_DOSSIER_ASSOCIE = NEW_L_SU_CLE_DEST,
           L.L_LDD_SITE            = NEW_L_SI_CLE_DEST
     WHERE L.L_LDD_DOSSIER_ASSOCIE = NEW_L_SU_CLE_SOURCE
       AND L.L_LDD_SITE = NEW_L_SI_CLE_SOURCE
       AND L.C_LDD_GENRE = 'SU';
    UPDATE LDD_LIEN_DOSSIER L
       SET L.L_DO_CLE = NEW_L_SU_CLE_DEST, L.L_DO_SITE = NEW_L_SI_CLE_DEST
     WHERE L.L_DO_CLE = NEW_L_SU_CLE_SOURCE
       AND L.L_DO_SITE = NEW_L_SI_CLE_SOURCE
       AND L.C_DO_GENRE = 'SU';
    UPDATE CO_COMMENTAIRE2 C
       SET C.L_CO_REFERENCE = NEW_L_SU_CLE_DEST,
           C.L_CO_REF_SITE  = NEW_L_SI_CLE_DEST
     WHERE C.L_CO_REFERENCE = NEW_L_SU_CLE_SOURCE
       AND C.L_CO_REF_SITE = NEW_L_SI_CLE_SOURCE
       AND C.C_CO_REF_GENRE = 'SU';
    UPDATE AD_ADRESSE A
       SET A.L_AD_REFERENCE = NEW_L_SU_CLE_DEST,
           A.L_AD_REF_SITE  = NEW_L_SI_CLE_DEST
     WHERE A.L_AD_REFERENCE = NEW_L_SU_CLE_SOURCE
       AND A.L_AD_REF_SITE = NEW_L_SI_CLE_SOURCE
       AND A.C_AD_REF_GENRE = 'SU';
    UPDATE MM_MULTIMEDIA M
       SET M.B_MM_SELECTIONNER = 'no '
     WHERE (M.L_MM_CLE, M.L_SI_CLE) IN
           (SELECT LM.L_MM_CLE, LM.L_MM_REF_SITE
              FROM LMM_LIEN_MULTIMEDIA LM
             WHERE LM.L_LMM_REFERENCE = NEW_L_SU_CLE_DEST
               AND LM.L_LMM_REF_SITE = NEW_L_SI_CLE_DEST);
    UPDATE LMM_LIEN_MULTIMEDIA LMM
       SET LMM.L_LMM_REFERENCE = NEW_L_SU_CLE_DEST,
           LMM.L_LMM_REF_SITE  = NEW_L_SI_CLE_DEST
     WHERE LMM.L_LMM_REFERENCE = NEW_L_SU_CLE_SOURCE
       AND LMM.L_LMM_REF_SITE = NEW_L_SI_CLE_SOURCE
       AND LMM.C_LMM_REF_GENRE = 'SU';
    UPDATE SU_SUJET S
       SET S.I_CC_CLE = 14920
     WHERE S.L_SU_CLE = NEW_L_SU_CLE_SOURCE
       AND S.L_SI_CLE = NEW_L_SI_CLE_SOURCE;
    COMMIT;
  END SP_E_COPIER_SUJET;

  --Épuration de la table AC_ACCES, sauf les insertions.
  PROCEDURE sp_epuration_acces IS
  BEGIN
    --Épuration des accès de dossiers non actifs plus vieux que trois ans.
    delete from ac_acces a
     where not exists (select *
              from do_dossier d
             where a.l_ref_cle = d.l_do_cle
               and a.l_ref_site = d.l_si_cle
               and d.i_st_cle = 359
               and d.d_do_date_debut < sysdate - 1095)
       and a.c_ac_action <> 'I'
       and a.c_gf_origine = 'DO'
       and a.d_ac_date_acces < sysdate - 1095;
    --Épuration des autres modules
    delete from ac_acces a
     where a.c_ac_action <> 'I'
       and a.c_gf_origine <> 'DO'
       and a.d_ac_date_acces < sysdate - 1095;
    commit;
  END sp_epuration_acces;

  --Recherche du sigle du site applicable d'un dossier d'investigation actif
  PROCEDURE SP_L_RECHERCHE_SIGLE(NEW_L_DO_CLE IN DO_DOSSIER.L_DO_CLE%TYPE,
                                 NEW_L_SI_CLE IN DO_DOSSIER.L_SI_CLE%TYPE,
                                 rc1          IN OUT DTS_RECHERCHE_SIGLE) IS
  begin
    OPEN rc1 FOR
      SELECT S.*
        FROM DO_DOSSIER           D,
             IS_INSCRIPTION       I,
             SIS_SITE_INSCRIPTION SIS,
             SI_SITE              S
       WHERE D.L_DO_CLE = I.L_IS_REFERENCE
         AND D.L_SI_CLE = I.L_IS_REF_SITE
         AND I.L_IS_CLE = SIS.L_IS_CLE
         AND I.L_SI_CLE = SIS.L_SI_CLE
         AND SIS.L_IS_SITE = S.L_SI_CLE
         AND D.L_DO_CLE = NEW_L_DO_CLE
         AND D.L_SI_CLE = NEW_L_SI_CLE
         AND I.I_ST_CLE = 359;
  END SP_L_RECHERCHE_SIGLE;

  --Recherche du code parent d'un code authentifié par TAM. Le code parent
  --permet ensuite de retrouver les profils associés à l'utilisateur.
  PROCEDURE SP_L_RECHERCHE_CODE_PARENT(NEW_CODE_TAM IN AUT_AUTHENTIFICATION.V_AUT_AUTHENTIFICATION%TYPE,
                                       rc1          IN OUT DTS_RECHERCHE_CODE_PARENT) IS
  begin
    OPEN rc1 FOR
      SELECT i.v_in_name_parent
        from aut_authentification a, in_intervenant i
       where a.v_aut_authentification = NEW_CODE_TAM
         and a.v_aut_name = i.name;
  
  END SP_L_RECHERCHE_CODE_PARENT;
END Cardex_Special;
/
