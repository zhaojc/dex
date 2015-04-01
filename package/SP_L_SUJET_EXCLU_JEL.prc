-- Testé projet "TestCardex" avec com.lotoQuebec.cardex.testCase.action.TestProjetAction

CREATE OR REPLACE PROCEDURE "SP_L_SUJET_EXCLU_JEL"(NEW_V_SU_PRENOM         IN SU_SUJET.V_SU_PRENOM%TYPE,
                                                   NEW_V_SU_NOM            IN SU_SUJET.V_SU_NOM%TYPE,
                                                   NEW_D_SU_DATE_NAISSANCE IN VARCHAR, --SU_SUJET.D_SU_DATE_NAISSANCE%TYPE,
                                                   NEW_V_AD_NUM_MUNICIPAL  IN AD_ADRESSE.V_AD_NUM_MUNICIPAL%TYPE,
                                                   NEW_V_AD_CODE_POSTAL    IN AD_ADRESSE.V_AD_CODE_POSTAL%TYPE,
                                                   RET_CODE                OUT NUMBER,
                                                   RET_NUMERO_FICHE        OUT SU_SUJET.V_SU_REFERENCE_3%TYPE,
                                                   RET_D_DO_DATE_FIN       OUT DO_DOSSIER.D_DO_DATE_FIN%TYPE) IS
  VAR_PERIODE         DO_DOSSIER.I_PE_CLE%TYPE;
  I_VAR_PERIODE       DO_DOSSIER.I_PE_CLE%TYPE;
  I_RET_NUMERO_FICHE  SU_SUJET.V_SU_REFERENCE_3%TYPE;
  I_RET_D_DO_DATE_FIN DO_DOSSIER.D_DO_DATE_FIN%TYPE;
  I_RET_L_SI_CLE      DO_DOSSIER.L_SI_CLE%TYPE;

  -- Perfect match
  --Nom + prénom + DDN + C.P. + No civique (Actuel)
  CURSOR sujet_complet IS
    select distinct su.v_su_reference_3,
                    do.i_pe_cle,
                    do.d_do_date_fin,
                    do.l_si_cle
      from su_sujet su, do_dossier do, ldd_lien_dossier ldd, ad_adresse ad
     where translate(convert(upper(su.v_su_nom), 'US7ASCII'),
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ') =
           translate(convert(upper(NEW_V_SU_NOM), 'US7ASCII'),
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ')
       and translate(convert(upper(su.v_su_prenom), 'US7ASCII'),
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ') =
           translate(convert(upper(NEW_V_SU_PRENOM), 'US7ASCII'),
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                     'ABCDEFGHIJKLMNOPQRSTUVWXYZ')
       and su.d_su_date_naissance =
           to_date(trim(NEW_D_SU_DATE_NAISSANCE), 'yyyy-MM-dd')
       and (do.i_ca_cle = 112 -- Autoexclusion
            or do.i_ca_cle = 26722) -- Jeux en ligne
       and ldd.l_do_cle = do.l_do_cle
       and ldd.l_do_site = do.l_si_cle
       and ldd.c_do_genre = 'DO'
       and ldd.l_ldd_dossier_associe = su.l_su_cle
       and ldd.l_ldd_site = su.l_si_cle
       and ldd.c_ldd_genre = 'SU'
       and do.i_st_cle = 359
       and ad.l_ad_reference = su.l_su_cle
       and ad.l_ad_ref_site = su.l_si_cle
       and ad.c_ad_ref_genre = 'SU'
       and ad.v_ad_num_municipal = trim(NEW_V_AD_NUM_MUNICIPAL)
       and upper(replace(ad.v_ad_code_postal, ' ', '')) =
           upper(replace(trim(NEW_V_AD_CODE_POSTAL), ' ', ''));

  --Partial match
  --Nom + prénom + DDN (actuel) 
  --Nom + prénom + C.P. + No civique 
  --DDN + C.P. + No civique 
  --Soundex(Nom) + Soundex(Prénom) + DDN -> Recherche phonétique
  CURSOR sujet_incomplet IS
    select distinct do.i_pe_cle, do.d_do_date_fin, do.l_si_cle
      from su_sujet su, do_dossier do, ldd_lien_dossier ldd, ad_adresse ad
     where ((translate(convert(upper(su.v_su_nom), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') =
           translate(convert(upper(NEW_V_SU_NOM), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') and
           translate(convert(upper(su.v_su_prenom), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') =
           translate(convert(upper(NEW_V_SU_PRENOM), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') and
           (su.d_su_date_naissance =
           to_date(trim(NEW_D_SU_DATE_NAISSANCE), 'yyyy-MM-dd') or
           (ad.l_ad_reference = su.l_su_cle and
           ad.l_ad_ref_site = su.l_si_cle and ad.c_ad_ref_genre = 'SU' and
           ad.v_ad_num_municipal = trim(NEW_V_AD_NUM_MUNICIPAL) and
           upper(replace(ad.v_ad_code_postal, ' ', '')) =
           upper(replace(trim(NEW_V_AD_CODE_POSTAL), ' ', ''))))) or
           (su.d_su_date_naissance =
           to_date(trim(NEW_D_SU_DATE_NAISSANCE), 'yyyy-MM-dd') and
           ((ad.l_ad_reference = su.l_su_cle and
           ad.l_ad_ref_site = su.l_si_cle and ad.c_ad_ref_genre = 'SU' and
           ad.v_ad_num_municipal = trim(NEW_V_AD_NUM_MUNICIPAL) and
           upper(replace(ad.v_ad_code_postal, ' ', '')) =
           upper(replace(trim(NEW_V_AD_CODE_POSTAL), ' ', ''))) or
           (convert(upper(su.C_SU_SNDX_NOM), 'US7ASCII') =
           soundex(translate(convert(upper(NEW_V_SU_NOM), 'US7ASCII'),
                                 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                                 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')) and
           convert(upper(su.C_SU_SNDX_PRENOM), 'US7ASCII') =
           soundex(translate(convert(upper(NEW_V_SU_PRENOM), 'US7ASCII'),
                                 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                                 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'))))) or
           (translate(convert(upper(su.v_su_nom), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') like
           '%' || translate(convert(upper(NEW_V_SU_NOM), 'US7ASCII'),
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ') || '%' and
           translate(convert(upper(su.v_su_prenom), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') like
           '%' || translate(convert(upper(NEW_V_SU_PRENOM), 'US7ASCII'),
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ') || '%' and
           su.d_su_date_naissance =
           to_date(trim(NEW_D_SU_DATE_NAISSANCE), 'yyyy-MM-dd')) or
           (translate(convert(upper(NEW_V_SU_NOM), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') like
           '%' || translate(convert(upper(su.v_su_nom), 'US7ASCII'),
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ') || '%' and
           translate(convert(upper(NEW_V_SU_PRENOM), 'US7ASCII'),
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                       'ABCDEFGHIJKLMNOPQRSTUVWXYZ') like
           '%' || translate(convert(upper(su.v_su_prenom), 'US7ASCII'),
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ~!@#$%^&*()_+}{":?><`-=]['''';/.,',
                              'ABCDEFGHIJKLMNOPQRSTUVWXYZ') || '%' and
           su.d_su_date_naissance =
           to_date(trim(NEW_D_SU_DATE_NAISSANCE), 'yyyy-MM-dd')))
       and (do.i_ca_cle = 112 -- Autoexclusion
           or do.i_ca_cle = 26722) -- Jeux en ligne    
       and ldd.l_do_cle = do.l_do_cle
       and ldd.l_do_site = do.l_si_cle
       and ldd.c_do_genre = 'DO'
       and ldd.l_ldd_dossier_associe = su.l_su_cle
       and ldd.l_ldd_site = su.l_si_cle
       and ldd.c_ldd_genre = 'SU'
       and do.i_st_cle = 359;
BEGIN
  -- Sujet identifié COMPLET
  OPEN sujet_complet;
  LOOP
    FETCH sujet_complet
      INTO I_RET_NUMERO_FICHE, I_VAR_PERIODE, I_RET_D_DO_DATE_FIN, I_RET_L_SI_CLE;
  
    if I_RET_D_DO_DATE_FIN is null or
       I_RET_D_DO_DATE_FIN > RET_D_DO_DATE_FIN or
       sujet_complet%ROWCOUNT = 1 then
      VAR_PERIODE       := I_VAR_PERIODE;
      RET_NUMERO_FICHE  := I_RET_NUMERO_FICHE;
      RET_D_DO_DATE_FIN := I_RET_D_DO_DATE_FIN;
    end if;
  
    EXIT WHEN sujet_complet%NOTFOUND;
  END LOOP;

  if sujet_complet%ROWCOUNT > 0 then
    RET_CODE := 10;
  else
    -- Sujet identifié INCOMPLET
    OPEN sujet_incomplet;
  
    LOOP
      FETCH sujet_incomplet
        into I_VAR_PERIODE, I_RET_D_DO_DATE_FIN, I_RET_L_SI_CLE;
      if I_RET_D_DO_DATE_FIN is null or
         I_RET_D_DO_DATE_FIN > RET_D_DO_DATE_FIN or
         sujet_incomplet%ROWCOUNT = 1 then
        VAR_PERIODE       := I_VAR_PERIODE;
        RET_NUMERO_FICHE  := I_RET_NUMERO_FICHE;
        RET_D_DO_DATE_FIN := I_RET_D_DO_DATE_FIN;
      end if;
      EXIT WHEN sujet_incomplet%NOTFOUND;
    END LOOP;
  
    if sujet_incomplet%ROWCOUNT > 0 then
      RET_CODE := 20;
    else
      RET_CODE := 0;
    end if;
    CLOSE sujet_incomplet;
  end if;
  CLOSE sujet_complet;

  -- Il faut ajouter 100 ans à la date de fin de contrat si c'est "à suivre"
  -- S'applique seulement au casino de Montréal (site 7) ou Maison Jean-Lapointe (site 52)
  if RET_CODE is not null and VAR_PERIODE = 22950 and
     RET_D_DO_DATE_FIN is not null and
     (I_RET_L_SI_CLE = 7 or I_RET_L_SI_CLE = 52) then
    RET_D_DO_DATE_FIN := ADD_MONTHS(RET_D_DO_DATE_FIN, 1200);
  end if;

  -- Il faut ajouter 100 ans à la date de fin de contrat, si la date de fin est null
  if RET_CODE != 0 and RET_D_DO_DATE_FIN is null then
    RET_D_DO_DATE_FIN := ADD_MONTHS(sysdate, 1200);
  end if;

END SP_L_SUJET_EXCLU_JEL;
/
